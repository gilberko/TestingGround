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

    private const val CQT_FRAME_SIZE         = 8192    // larger FFT for better low-freq resolution
    private const val CQT_HOP_SIZE           = 2048    // same hop as FFT → same timeline
    private const val CQT_BINS_TOTAL         = 60      // C2–C7, 5 octaves × 12 semitones
    private const val CQT_F0                 = 65.406f // C2 in Hz
    private const val CQT_Q                  = 17.3f   // 1 / (2^(1/12) - 1)
    private const val CHROMA_THRESHOLD       = 0.05f   // min L2-norm before treating as silence
    private const val CHROMA_MIN_SIMILARITY  = 0.5f    // minimum cosine score to name a chord

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
        return smoothChords(results)
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
        return smoothChords(results)
    }

    fun analyze(pcmSamples: ShortArray, sampleRate: Int, method: AnalysisMethod = AnalysisMethod.FFT): List<FrameResult> =
        when (method) {
            AnalysisMethod.FFT -> analyzeFFT(pcmSamples, sampleRate)
            AnalysisMethod.CQT -> analyzeCQT(pcmSamples, sampleRate)
        }

    fun hopSizeFor(method: AnalysisMethod) = if (method == AnalysisMethod.CQT) CQT_HOP_SIZE else HOP_SIZE
}
