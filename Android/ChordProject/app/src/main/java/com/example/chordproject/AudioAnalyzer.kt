package com.example.chordproject

import kotlin.math.*

data class FrequencyPeak(val freqHz: Float, val magnitude: Float, val noteName: String)

data class FrameResult(
    val timeSeconds: Float,
    val notes: List<String>,
    val chord: String,
    val peaks: List<FrequencyPeak> = emptyList()
)

data class AggregatedFrameResult(
    val startTimeSeconds: Float,
    val endTimeSeconds: Float,
    val notes: List<String>,
    val chord: String,
    val frameCount: Int,
    val peaks: List<FrequencyPeak> = emptyList()
)

data class TempoResult(val bpm: Float, val confidence: Float, val onsetCount: Int)

fun aggregateFrames(frames: List<FrameResult>, hopSizeSeconds: Float): List<AggregatedFrameResult> {
    if (frames.isEmpty()) return emptyList()
    val result = mutableListOf<AggregatedFrameResult>()
    var groupStart = frames[0]
    var count = 1
    for (i in 1 until frames.size) {
        val frame = frames[i]
        if (frame.chord == groupStart.chord && frame.notes == groupStart.notes) {
            count++
        } else {
            result.add(AggregatedFrameResult(groupStart.timeSeconds, frame.timeSeconds, groupStart.notes, groupStart.chord, count, groupStart.peaks))
            groupStart = frame
            count = 1
        }
    }
    result.add(AggregatedFrameResult(groupStart.timeSeconds, groupStart.timeSeconds + count * hopSizeSeconds, groupStart.notes, groupStart.chord, count, groupStart.peaks))
    return result
}

fun simplifyMelody(frames: List<AggregatedFrameResult>): List<AggregatedFrameResult> {
    var prevNotes = emptySet<String>()
    return frames.map { frame ->
        val simplified = when {
            frame.notes.size <= 2 -> frame.notes
            else -> {
                val changed = frame.notes.filter { it !in prevNotes }
                (if (changed.isNotEmpty()) changed else frame.notes).take(2)
            }
        }
        prevNotes = frame.notes.toSet()
        frame.copy(notes = simplified)
    }
}

enum class AnalysisMethod { FFT, CQT }

object AudioAnalyzer {
    private const val FRAME_SIZE         = 4096
    const val HOP_SIZE                   = 2048
    private const val MIN_FREQ           = 80.0
    private const val MAX_FREQ           = 2000.0
    private const val PEAK_THRESHOLD     = 0.25f
    private const val SILENCE_THRESHOLD  = 0.008f  // ~-42 dBFS; tune if needed

    private const val SMOOTHING_VERIFIED_CENTS    = 30f
    private const val SMOOTHING_MAYBE_CENTS       = 50f
    private const val SMOOTHING_PROPAGATION_CENTS = 50f
    private const val SMOOTHING_CONFLICT_CENTS    = 100f // max gap (cents) between two maybe-peaks to treat as same physical note

    private const val CQT_FRAME_SIZE         = 8192    // larger FFT for better low-freq resolution
    private const val CQT_HOP_SIZE           = 2048    // same hop as FFT → same timeline
    private const val CQT_BINS_TOTAL         = 60      // C2–C7, 5 octaves × 12 semitones
    private const val CQT_F0                 = 65.406f // C2 in Hz
    private const val CQT_Q                  = 17.3f   // 1 / (2^(1/12) - 1)
    private const val CHROMA_THRESHOLD       = 0.05f   // min L2-norm before treating as silence
    private const val CHROMA_MIN_SIMILARITY  = 0.5f    // minimum cosine score to name a chord

    // Tempo detection
    private const val TEMPO_FLUX_MULTIPLIER  = 1.5f
    private const val TEMPO_FLUX_WINDOW      = 10
    private const val TEMPO_MIN_BPM          = 60f
    private const val TEMPO_MAX_BPM          = 200f
    private const val TEMPO_IOI_BIN_MS       = 5
    private const val TEMPO_MIN_ONSET_GAP_MS = 100

    private val NOTE_NAMES = arrayOf("C","C#","D","D#","E","F","F#","G","G#","A","A#","B")

    // Chord templates: semitone intervals from root
    private val CHORD_TEMPLATES = mapOf(
        "Major" to intArrayOf(0, 4, 7),
        "Minor" to intArrayOf(0, 3, 7),
        "Dom7"  to intArrayOf(0, 4, 7, 10),
        "Maj7"  to intArrayOf(0, 4, 7, 11),
        "Min7"  to intArrayOf(0, 3, 7, 10),
        "Dim"   to intArrayOf(0, 3, 6),
        "Sus2"  to intArrayOf(0, 2, 7),
        "Sus4"  to intArrayOf(0, 5, 7)
    )

    // All 96 chroma templates (8 types × 12 roots), pre-normalized — built once at init
    private val CHROMA_TEMPLATES: Map<String, FloatArray> = buildChromaTemplates()

    // Pre-allocated CQT buffers (analysis is single-threaded on Dispatchers.Default)
    private val cqtWindow = hammingWindow(CQT_FRAME_SIZE)
    private val cqtReal   = FloatArray(CQT_FRAME_SIZE)
    private val cqtImag   = FloatArray(CQT_FRAME_SIZE)

    fun hammingWindow(size: Int): FloatArray =
        FloatArray(size) { i -> (0.54 - 0.46 * cos(2.0 * PI * i / (size - 1))).toFloat() }

    /** In-place Cooley-Tukey FFT. [real] and [imag] must be the same power-of-2 length. */
    fun fft(real: FloatArray, imag: FloatArray) {
        val n = real.size
        // Bit-reversal permutation
        var j = 0
        for (i in 1 until n) {
            var bit = n shr 1
            while (j and bit != 0) { j = j xor bit; bit = bit shr 1 }
            j = j xor bit
            if (i < j) {
                real[i] = real[j].also { real[j] = real[i] }
                imag[i] = imag[j].also { imag[j] = imag[i] }
            }
        }
        // Butterfly stages
        var len = 2
        while (len <= n) {
            val halfLen = len / 2
            val ang = -2.0 * PI / len
            val wRe = cos(ang).toFloat()
            val wIm = sin(ang).toFloat()
            var start = 0
            while (start < n) {
                var urRe = 1f
                var urIm = 0f
                for (k in 0 until halfLen) {
                    val uRe = real[start + k]
                    val uIm = imag[start + k]
                    val lo  = start + k + halfLen
                    val vRe = real[lo] * urRe - imag[lo] * urIm
                    val vIm = real[lo] * urIm + imag[lo] * urRe
                    real[start + k] = uRe + vRe
                    imag[start + k] = uIm + vIm
                    real[lo]        = uRe - vRe
                    imag[lo]        = uIm - vIm
                    val newUrRe = urRe * wRe - urIm * wIm
                    urIm = urRe * wIm + urIm * wRe
                    urRe = newUrRe
                }
                start += len
            }
            len *= 2
        }
    }

    private fun buildChromaTemplates(): Map<String, FloatArray> {
        val templates = mutableMapOf<String, FloatArray>()
        for (root in 0..11) {
            for ((name, intervals) in CHORD_TEMPLATES) {
                val chroma = FloatArray(12)
                for (interval in intervals) chroma[(root + interval) % 12] = 1f
                l2Normalize(chroma)
                templates["${NOTE_NAMES[root]} $name"] = chroma
            }
        }
        return templates
    }

    private fun l2Normalize(vec: FloatArray) {
        var norm = 0f
        for (v in vec) norm += v * v
        norm = sqrt(norm)
        if (norm < 1e-6f) return
        for (i in vec.indices) vec[i] /= norm
    }

    private fun computeChroma(pcmSamples: ShortArray, frameStart: Int, sampleRate: Int): Triple<FloatArray, IntArray, FloatArray>? {
        // Silence gate
        var sumSq = 0.0
        for (i in 0 until CQT_FRAME_SIZE) {
            val s = pcmSamples[frameStart + i].toFloat() / 32768f
            sumSq += s * s
        }
        val rms = sqrt(sumSq / CQT_FRAME_SIZE).toFloat()
        if (rms < SILENCE_THRESHOLD) return null

        // Hamming window + copy to FFT buffers
        for (i in 0 until CQT_FRAME_SIZE) {
            cqtReal[i] = pcmSamples[frameStart + i].toFloat() / 32768f * cqtWindow[i]
            cqtImag[i] = 0f
        }
        fft(cqtReal, cqtImag)

        // Magnitude spectrum (positive frequencies only)
        val mag = FloatArray(CQT_FRAME_SIZE / 2) { i ->
            sqrt(cqtReal[i] * cqtReal[i] + cqtImag[i] * cqtImag[i])
        }

        // CQT accumulation: 60 bins (C2–C7)
        val cqtBins = FloatArray(CQT_BINS_TOTAL)
        for (k in 0 until CQT_BINS_TOTAL) {
            val fk    = CQT_F0 * 2.0.pow(k.toDouble() / 12.0).toFloat()
            val bwk   = fk / CQT_Q
            val loFFT = ((fk - bwk / 2f) * CQT_FRAME_SIZE / sampleRate).toInt().coerceAtLeast(0)
            val hiFFT = ((fk + bwk / 2f) * CQT_FRAME_SIZE / sampleRate).toInt().coerceAtMost(CQT_FRAME_SIZE / 2 - 1)
            var weightedSum = 0f
            for (b in loFFT..hiFFT) {
                val binFreq = b.toFloat() * sampleRate / CQT_FRAME_SIZE
                val normalizedOffset = (binFreq - fk) / (bwk / 2f)
                val weight = (0.54 + 0.46 * cos(PI * normalizedOffset)).toFloat()
                weightedSum += weight * mag[b]
            }
            cqtBins[k] = weightedSum
        }

        // Fold to 12 chroma bins, tracking dominant octave per pitch class
        val chroma         = FloatArray(12)
        val dominantOctave = IntArray(12) { -1 }
        val dominantMag    = FloatArray(12) { 0f }
        for (k in 0 until CQT_BINS_TOTAL) {
            val pc     = k % 12
            val octave = k / 12 + 2  // CQT_F0=C2: bins 0–11=oct2, 12–23=oct3, …
            chroma[pc] += cqtBins[k]
            if (cqtBins[k] > dominantMag[pc]) {
                dominantMag[pc]    = cqtBins[k]
                dominantOctave[pc] = octave
            }
        }

        // L2-normalize; return null if below threshold
        var norm = 0f
        for (v in chroma) norm += v * v
        norm = sqrt(norm)
        if (norm < CHROMA_THRESHOLD) return null
        for (i in chroma.indices) chroma[i] /= norm

        return Triple(chroma, dominantOctave, dominantMag)
    }

    private fun notesFromChroma(chroma: FloatArray, dominantOctave: IntArray): List<String> {
        val maxVal = chroma.maxOrNull() ?: return emptyList()
        return (0..11).filter { chroma[it] >= maxVal * 0.4f }.map { pc ->
            val oct = dominantOctave[pc]
            if (oct >= 0) "${NOTE_NAMES[pc]}($oct)" else NOTE_NAMES[pc]
        }
    }

    private fun detectChordFromChroma(chroma: FloatArray): String {
        var bestScore        = Float.NEGATIVE_INFINITY
        var bestTemplateSize = Int.MAX_VALUE
        var bestChord        = "(no chord)"
        for ((name, template) in CHROMA_TEMPLATES) {
            var score = 0f
            for (i in 0..11) score += chroma[i] * template[i]
            val typeName     = name.substring(name.indexOf(' ') + 1)
            val templateSize = CHORD_TEMPLATES[typeName]?.size ?: 3
            val better = score > bestScore || (score == bestScore && templateSize < bestTemplateSize)
            if (better) {
                bestScore        = score
                bestTemplateSize = templateSize
                bestChord        = name
            }
        }
        return if (bestScore < CHROMA_MIN_SIMILARITY) "(no chord)" else bestChord
    }

    private fun detectChord(pitchClasses: Set<Int>): String {
        if (pitchClasses.size < 2) return "(no chord)"
        var bestScore        = 0
        var bestTemplateSize = Int.MAX_VALUE
        var bestChord        = "(no chord)"
        for (root in 0..11) {
            for ((name, intervals) in CHORD_TEMPLATES) {
                val matched = intervals.count { ((root + it) % 12) in pitchClasses }
                if (matched >= 2) {
                    val better = matched > bestScore ||
                                 (matched == bestScore && intervals.size < bestTemplateSize)
                    if (better) {
                        bestScore        = matched
                        bestTemplateSize = intervals.size
                        bestChord        = "${NOTE_NAMES[root]} $name"
                    }
                }
            }
        }
        return bestChord
    }

    private const val MIN_CHORD_FRAMES = 3  // was 5; ~140ms at 46ms/frame

    private fun smoothChords(frames: List<FrameResult>): List<FrameResult> {
        if (frames.isEmpty()) return frames
        val result = frames.toMutableList()
        var currentChord = frames.first().chord
        var pendingChord = currentChord
        var pendingCount = 0
        for (i in frames.indices) {
            val raw = frames[i].chord
            when {
                // Silence is unambiguous — accept immediately, reset pending state
                raw == "(silence)" -> {
                    currentChord = "(silence)"
                    pendingChord = "(silence)"
                    pendingCount = 0
                }
                // Coming out of silence — accept immediately, no smoothing lag needed
                currentChord == "(silence)" -> {
                    currentChord = raw
                    pendingChord = raw
                    pendingCount = 0
                }
                raw == currentChord -> {
                    pendingChord = raw; pendingCount = 0
                }
                raw == pendingChord -> {
                    pendingCount++
                    if (pendingCount >= MIN_CHORD_FRAMES) {
                        currentChord = pendingChord; pendingCount = 0
                    } else {
                        result[i] = result[i].copy(chord = currentChord)
                    }
                }
                else -> {
                    pendingChord = raw; pendingCount = 1
                    result[i] = result[i].copy(chord = currentChord)
                }
            }
        }
        return result
    }

    private fun noteNameToMidi(name: String): Int {
        val match = Regex("""^([A-G]#?)\((\d+)\)$""").matchEntire(name) ?: return -1
        val pc = NOTE_NAMES.indexOf(match.groupValues[1]).takeIf { it >= 0 } ?: return -1
        val octave = match.groupValues[2].toIntOrNull() ?: return -1
        return (octave + 1) * 12 + pc
    }

    private fun midiToHz(midi: Int): Float =
        (440.0 * 2.0.pow((midi - 69) / 12.0)).toFloat()

    private fun centsDev(detectedHz: Float, referenceHz: Float): Float =
        (1200.0 * log2(detectedHz.toDouble() / referenceHz.toDouble())).toFloat()

    private fun smoothNotes(frames: List<FrameResult>): List<FrameResult> {
        if (frames.isEmpty()) return frames
        val n = frames.size

        val status        = Array(n) { i -> Array(frames[i].peaks.size) { "unclassified" } }
        val correctedName = Array(n) { i -> Array(frames[i].peaks.size) { j -> frames[i].peaks[j].noteName } }
        val theoreticalHz = Array(n) { i -> FloatArray(frames[i].peaks.size) }
        val queue         = ArrayDeque<Pair<Int, Int>>()

        // Step 1: classify each peak as verified, maybe, or unclassified
        for (i in 0 until n) {
            for (j in frames[i].peaks.indices) {
                val peak = frames[i].peaks[j]
                val midi = noteNameToMidi(peak.noteName)
                if (midi < 0) continue
                val theoHz = midiToHz(midi)
                val dev = abs(centsDev(peak.freqHz, theoHz))
                when {
                    dev <= SMOOTHING_VERIFIED_CENTS -> {
                        status[i][j] = "verified"
                        theoreticalHz[i][j] = theoHz
                        queue.add(Pair(i, j))
                    }
                    dev <= SMOOTHING_MAYBE_CENTS -> {
                        status[i][j] = "maybe"
                    }
                }
            }
        }

        // Step 2: BFS — propagate verified notes into adjacent maybe-note frames
        while (queue.isNotEmpty()) {
            val (i, j) = queue.removeFirst()
            val vHz   = theoreticalHz[i][j]
            val vName = correctedName[i][j]
            for (ni in listOf(i - 1, i + 1)) {
                if (ni < 0 || ni >= n) continue
                for (k in frames[ni].peaks.indices) {
                    if (status[ni][k] != "maybe") continue
                    if (abs(centsDev(frames[ni].peaks[k].freqHz, vHz)) <= SMOOTHING_PROPAGATION_CENTS) {
                        status[ni][k]        = "verified"
                        correctedName[ni][k] = vName
                        theoreticalHz[ni][k] = vHz
                        queue.add(Pair(ni, k))
                    }
                }
            }
        }

        // Step 2b: resolve conflicting maybe-notes in adjacent frames (iterative until stable)
        var conflictFound = true
        while (conflictFound) {
            conflictFound = false
            outer@ for (i in 0 until n) {
                for (j in frames[i].peaks.indices) {
                    if (status[i][j] != "maybe") continue
                    val peakHz     = frames[i].peaks[j].freqHz
                    val peakMidi   = noteNameToMidi(correctedName[i][j])
                    if (peakMidi < 0) continue
                    val peakTheoHz = midiToHz(peakMidi)
                    val peakDev    = abs(centsDev(peakHz, peakTheoHz))

                    for (ni in listOf(i - 1, i + 1)) {
                        if (ni < 0 || ni >= n) continue
                        for (k in frames[ni].peaks.indices) {
                            if (status[ni][k] != "maybe") continue
                            if (correctedName[ni][k] == correctedName[i][j]) continue  // same note — skip

                            val neighborHz = frames[ni].peaks[k].freqHz
                            if (abs(centsDev(neighborHz, peakHz)) > SMOOTHING_CONFLICT_CENTS) continue

                            val neighborMidi    = noteNameToMidi(correctedName[ni][k])
                            if (neighborMidi < 0) continue
                            val neighborTheoHz  = midiToHz(neighborMidi)
                            val neighborDev     = abs(centsDev(neighborHz, neighborTheoHz))

                            // Winner = note whose theoretical is closest to its own detected frequency
                            val (winnerName, winnerTheoHz) = if (peakDev <= neighborDev)
                                Pair(correctedName[i][j], peakTheoHz)
                            else
                                Pair(correctedName[ni][k], neighborTheoHz)

                            status[i][j]        = "verified"; correctedName[i][j]  = winnerName; theoreticalHz[i][j]  = winnerTheoHz; queue.add(Pair(i, j))
                            status[ni][k]       = "verified"; correctedName[ni][k] = winnerName; theoreticalHz[ni][k] = winnerTheoHz; queue.add(Pair(ni, k))
                            conflictFound = true
                            continue@outer
                        }
                    }
                }
            }

            // Re-run BFS from newly verified anchors
            while (queue.isNotEmpty()) {
                val (i, j) = queue.removeFirst()
                val vHz   = theoreticalHz[i][j]
                val vName = correctedName[i][j]
                for (ni in listOf(i - 1, i + 1)) {
                    if (ni < 0 || ni >= n) continue
                    for (k in frames[ni].peaks.indices) {
                        if (status[ni][k] != "maybe") continue
                        if (abs(centsDev(frames[ni].peaks[k].freqHz, vHz)) <= SMOOTHING_PROPAGATION_CENTS) {
                            status[ni][k]        = "verified"
                            correctedName[ni][k] = vName
                            theoreticalHz[ni][k] = vHz
                            queue.add(Pair(ni, k))
                        }
                    }
                }
            }
        }

        // Step 3: rebuild FrameResult list with corrected note names
        return frames.mapIndexed { i, frame ->
            val newPeaks = frame.peaks.mapIndexed { j, peak ->
                peak.copy(noteName = correctedName[i][j])
            }
            frame.copy(notes = newPeaks.map { it.noteName }.distinct(), peaks = newPeaks)
        }
    }

    private fun analyzeFFT(pcmSamples: ShortArray, sampleRate: Int): List<FrameResult> {
        val results  = mutableListOf<FrameResult>()
        val window   = hammingWindow(FRAME_SIZE)
        val real     = FloatArray(FRAME_SIZE)
        val imag     = FloatArray(FRAME_SIZE)
        val minBin   = (MIN_FREQ * FRAME_SIZE / sampleRate).toInt().coerceAtLeast(1)
        val maxBin   = (MAX_FREQ * FRAME_SIZE / sampleRate).toInt().coerceAtMost(FRAME_SIZE / 2 - 1)

        var frameStart = 0
        while (frameStart + FRAME_SIZE <= pcmSamples.size) {
            val timeSeconds = frameStart.toFloat() / sampleRate

            // Silence gate: skip frame if signal energy is too low
            var sumSq = 0.0
            for (i in 0 until FRAME_SIZE) {
                val s = pcmSamples[frameStart + i].toFloat() / 32768f
                sumSq += s * s
            }
            val rms = sqrt(sumSq / FRAME_SIZE).toFloat()
            if (rms < SILENCE_THRESHOLD) {
                results.add(FrameResult(timeSeconds, emptyList(), "(silence)"))
                frameStart += HOP_SIZE
                continue
            }

            // Apply Hamming window
            for (i in 0 until FRAME_SIZE) {
                real[i] = pcmSamples[frameStart + i].toFloat() / 32768f * window[i]
                imag[i] = 0f
            }
            fft(real, imag)

            // Magnitude spectrum (only positive frequencies)
            val mag = FloatArray(FRAME_SIZE / 2) { i -> sqrt(real[i] * real[i] + imag[i] * imag[i]) }

            // Dynamic threshold: 15% of peak magnitude in target range
            var maxMag = 0f
            for (b in minBin..maxBin) if (mag[b] > maxMag) maxMag = mag[b]
            val threshold = maxMag * PEAK_THRESHOLD

            val pitchClasses = mutableSetOf<Int>()
            val noteMap      = mutableMapOf<Int, String>()  // pc -> "C#(4)"
            val peaks        = mutableListOf<FrequencyPeak>()
            for (bin in minBin..maxBin) {
                if (mag[bin] >= threshold &&
                    mag[bin] >= mag[bin - 1] &&
                    mag[bin] >= mag[bin + 1]
                ) {
                    val freq     = bin.toDouble() * sampleRate / FRAME_SIZE
                    val midi     = (69 + 12 * log2(freq / 440.0)).roundToInt()
                    val pc       = ((midi % 12) + 12) % 12
                    val octave   = midi / 12 - 1
                    val noteName = "${NOTE_NAMES[pc]}($octave)"
                    pitchClasses.add(pc)
                    noteMap.putIfAbsent(pc, noteName)
                    peaks.add(FrequencyPeak(freq.toFloat(), mag[bin], noteName))
                }
            }

            results.add(
                FrameResult(
                    timeSeconds = timeSeconds,
                    notes       = noteMap.values.toList(),
                    chord       = detectChord(pitchClasses),
                    peaks       = peaks.sortedByDescending { it.magnitude }
                )
            )
            frameStart += HOP_SIZE
        }
        return smoothChords(smoothNotes(results))
    }

    private fun analyzeCQT(pcmSamples: ShortArray, sampleRate: Int): List<FrameResult> {
        val results = mutableListOf<FrameResult>()
        var frameStart = 0
        while (frameStart + CQT_FRAME_SIZE <= pcmSamples.size) {
            val timeSeconds = frameStart.toFloat() / sampleRate
            val chromaResult = computeChroma(pcmSamples, frameStart, sampleRate)
            results.add(
                if (chromaResult == null)
                    FrameResult(timeSeconds, emptyList(), "(silence)")
                else {
                    val (chroma, dominantOctave, dominantMag) = chromaResult
                    val peaks = (0..11).mapNotNull { pc ->
                        val oct = dominantOctave[pc]
                        if (oct < 0 || dominantMag[pc] == 0f) null
                        else {
                            val noteName = "${NOTE_NAMES[pc]}($oct)"
                            val freqHz = (440.0 * Math.pow(2.0, ((oct + 1) * 12 + pc - 69) / 12.0)).toFloat()
                            FrequencyPeak(freqHz, dominantMag[pc], noteName)
                        }
                    }.sortedByDescending { it.magnitude }
                    FrameResult(timeSeconds, notesFromChroma(chroma, dominantOctave), detectChordFromChroma(chroma), peaks)
                }
            )
            frameStart += CQT_HOP_SIZE
        }
        return smoothChords(smoothNotes(results))
    }

    fun analyze(pcmSamples: ShortArray, sampleRate: Int, method: AnalysisMethod = AnalysisMethod.FFT): List<FrameResult> =
        when (method) {
            AnalysisMethod.FFT -> analyzeFFT(pcmSamples, sampleRate)
            AnalysisMethod.CQT -> analyzeCQT(pcmSamples, sampleRate)
        }

    fun hopSizeFor(method: AnalysisMethod) = if (method == AnalysisMethod.CQT) CQT_HOP_SIZE else HOP_SIZE

    private fun addToHistogram(
        histogram: FloatArray,
        interval: Float,
        minInterval: Float,
        binSize: Float,
        numBins: Int,
        weight: Float
    ) {
        if (interval < minInterval || interval > minInterval + numBins * binSize) return
        val bin = ((interval - minInterval) / binSize).toInt().coerceIn(0, numBins - 1)
        histogram[bin] += weight
    }

    fun detectTempo(samples: ShortArray, sampleRate: Int): TempoResult? {
        // Step 1: spectral flux per frame
        val window  = hammingWindow(FRAME_SIZE)
        val real    = FloatArray(FRAME_SIZE)
        val imag    = FloatArray(FRAME_SIZE)
        val prevMag = FloatArray(FRAME_SIZE / 2)
        val fluxList = mutableListOf<Float>()
        var frameStart = 0
        var firstFrame = true
        while (frameStart + FRAME_SIZE <= samples.size) {
            for (i in 0 until FRAME_SIZE) {
                real[i] = samples[frameStart + i].toFloat() / 32768f * window[i]
                imag[i] = 0f
            }
            fft(real, imag)
            val mag = FloatArray(FRAME_SIZE / 2) { i -> sqrt(real[i] * real[i] + imag[i] * imag[i]) }
            val flux = if (firstFrame) {
                firstFrame = false; 0f
            } else {
                var f = 0f
                for (b in 0 until FRAME_SIZE / 2) f += maxOf(0f, mag[b] - prevMag[b])
                f
            }
            fluxList.add(flux)
            mag.copyInto(prevMag)
            frameStart += HOP_SIZE
        }
        if (fluxList.size < 4) return null

        // Step 2: adaptive onset peak picking
        val hopSec = HOP_SIZE.toFloat() / sampleRate
        val minGapFrames = (TEMPO_MIN_ONSET_GAP_MS / 1000f / hopSec).toInt().coerceAtLeast(1)
        val onsetTimes = mutableListOf<Float>()
        var lastOnsetFrame = -minGapFrames
        for (i in fluxList.indices) {
            val lo = maxOf(0, i - TEMPO_FLUX_WINDOW)
            val hi = minOf(fluxList.size - 1, i + TEMPO_FLUX_WINDOW)
            var sum = 0f
            for (k in lo..hi) sum += fluxList[k]
            val localMean = sum / (hi - lo + 1)
            val isLocalMax = (i == 0 || fluxList[i] >= fluxList[i - 1]) &&
                             (i == fluxList.size - 1 || fluxList[i] >= fluxList[i + 1])
            if (isLocalMax && fluxList[i] > localMean * TEMPO_FLUX_MULTIPLIER && i - lastOnsetFrame >= minGapFrames) {
                onsetTimes.add(i * hopSec)
                lastOnsetFrame = i
            }
        }
        if (onsetTimes.size < 4) return null

        // Step 3: IOI histogram with harmonic weighting
        val minInterval = 60f / TEMPO_MAX_BPM
        val maxInterval = 60f / TEMPO_MIN_BPM
        val binSizeSec  = TEMPO_IOI_BIN_MS / 1000f
        val numBins     = ((maxInterval - minInterval) / binSizeSec).toInt() + 1
        val histogram   = FloatArray(numBins)
        for (i in 0 until onsetTimes.size - 1) {
            val ioi = onsetTimes[i + 1] - onsetTimes[i]
            addToHistogram(histogram, ioi,        minInterval, binSizeSec, numBins, 1.0f)
            addToHistogram(histogram, ioi * 2f,   minInterval, binSizeSec, numBins, 0.5f)
            addToHistogram(histogram, ioi * 0.5f, minInterval, binSizeSec, numBins, 0.5f)
        }

        // Step 4: best BPM from histogram peak
        var bestBin   = -1
        var bestCount = 0f
        for (b in histogram.indices) {
            if (histogram[b] > bestCount) { bestCount = histogram[b]; bestBin = b }
        }
        if (bestBin < 0 || bestCount < 1f) return null

        val bestIntervalSec = minInterval + (bestBin + 0.5f) * binSizeSec
        val bpm             = 60f / bestIntervalSec
        val confidence      = (bestCount / (onsetTimes.size - 1).toFloat()).coerceIn(0f, 1f)
        return TempoResult(bpm, confidence, onsetTimes.size)
    }
}
