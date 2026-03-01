package com.example.chordproject

import kotlin.math.*

data class FrameResult(
    val timeSeconds: Float,
    val notes: List<String>,
    val chord: String
)

object AudioAnalyzer {
    private const val FRAME_SIZE = 4096
    private const val HOP_SIZE   = 2048
    private const val MIN_FREQ   = 80.0
    private const val MAX_FREQ   = 2000.0
    private const val PEAK_THRESHOLD = 0.15f

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

    private const val MIN_CHORD_FRAMES = 5

    private fun smoothChords(frames: List<FrameResult>): List<FrameResult> {
        if (frames.isEmpty()) return frames
        val result = frames.toMutableList()
        var currentChord = frames.first().chord
        var pendingChord = currentChord
        var pendingCount = 0
        for (i in frames.indices) {
            val raw = frames[i].chord
            when {
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

    fun analyze(pcmSamples: ShortArray, sampleRate: Int): List<FrameResult> {
        val results  = mutableListOf<FrameResult>()
        val window   = hammingWindow(FRAME_SIZE)
        val real     = FloatArray(FRAME_SIZE)
        val imag     = FloatArray(FRAME_SIZE)
        val minBin   = (MIN_FREQ * FRAME_SIZE / sampleRate).toInt().coerceAtLeast(1)
        val maxBin   = (MAX_FREQ * FRAME_SIZE / sampleRate).toInt().coerceAtMost(FRAME_SIZE / 2 - 1)

        var frameStart = 0
        while (frameStart + FRAME_SIZE <= pcmSamples.size) {
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
            val notes        = mutableSetOf<String>()
            for (bin in minBin..maxBin) {
                if (mag[bin] >= threshold &&
                    mag[bin] >= mag[bin - 1] &&
                    mag[bin] >= mag[bin + 1]
                ) {
                    val freq = bin.toDouble() * sampleRate / FRAME_SIZE
                    val midi = (69 + 12 * log2(freq / 440.0)).roundToInt()
                    val pc   = ((midi % 12) + 12) % 12
                    pitchClasses.add(pc)
                    notes.add(NOTE_NAMES[pc])
                }
            }

            results.add(
                FrameResult(
                    timeSeconds = frameStart.toFloat() / sampleRate,
                    notes       = notes.toList(),
                    chord       = detectChord(pitchClasses)
                )
            )
            frameStart += HOP_SIZE
        }
        return smoothChords(results)
    }
}
