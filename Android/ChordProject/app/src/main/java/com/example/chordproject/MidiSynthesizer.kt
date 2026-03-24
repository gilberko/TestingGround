package com.example.chordproject

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import kotlin.math.PI
import kotlin.math.sin

object MidiSynthesizer {

    private val NOTE_FREQUENCIES = mapOf(
        "C"  to 261.63f, "C#" to 277.18f, "D"  to 293.66f, "D#" to 311.13f,
        "E"  to 329.63f, "F"  to 349.23f, "F#" to 369.99f, "G"  to 392.00f,
        "G#" to 415.30f, "A"  to 440.00f, "A#" to 466.16f, "B"  to 493.88f
    )

    private val NOTE_PC_NAMES = arrayOf("C","C#","D","D#","E","F","F#","G","G#","A","A#","B")

    // Parses "C#(4)" format; falls back to NOTE_FREQUENCIES for legacy plain names like "C#"
    private fun noteNameToFreq(name: String): Float? {
        val parenStart = name.indexOf('(')
        if (parenStart <= 0) return NOTE_FREQUENCIES[name]
        val pc     = name.substring(0, parenStart)
        val octave = name.substring(parenStart + 1).trimEnd(')').toIntOrNull() ?: return null
        val pcIdx  = NOTE_PC_NAMES.indexOf(pc)
        if (pcIdx < 0) return null
        val midi   = (octave + 1) * 12 + pcIdx
        return (440.0 * Math.pow(2.0, (midi - 69) / 12.0)).toFloat()
    }

    fun synthesizeChord(
        notes: List<String>,
        durationSeconds: Float,
        sampleRate: Int = 44100,
        fadeMillis: Int = 20
    ): ShortArray {
        val numSamples = (durationSeconds * sampleRate).toInt().coerceAtLeast(1)
        val result = ShortArray(numSamples)

        val freqs = notes.mapNotNull { noteNameToFreq(it) }
        if (freqs.isEmpty()) return result  // silence

        val fadeSamples = (fadeMillis * sampleRate / 1000).coerceAtMost(numSamples / 2)
        val scale = Short.MAX_VALUE * 0.85f / freqs.size

        for (i in 0 until numSamples) {
            var sample = 0f
            for (freq in freqs) {
                sample += sin(2.0 * PI * freq * i / sampleRate).toFloat()
            }
            // Linear fade-in/out
            val fade = when {
                i < fadeSamples -> i.toFloat() / fadeSamples
                i >= numSamples - fadeSamples -> (numSamples - 1 - i).toFloat() / fadeSamples
                else -> 1f
            }
            result[i] = (sample * scale * fade).toInt().toShort()
        }
        return result
    }

    suspend fun play(
        segments: List<AggregatedFrameResult>,
        sampleRate: Int,
        onFrameChanged: (Int) -> Unit,
        isActive: () -> Boolean
    ) {
        val minBufSize = AudioTrack.getMinBufferSize(
            sampleRate,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )
        val audioTrack = AudioTrack.Builder()
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            .setAudioFormat(
                AudioFormat.Builder()
                    .setSampleRate(sampleRate)
                    .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                    .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                    .build()
            )
            .setBufferSizeInBytes(minBufSize)
            .setTransferMode(AudioTrack.MODE_STREAM)
            .build()
        audioTrack.play()
        try {
            for ((index, seg) in segments.withIndex()) {
                if (!isActive()) break
                onFrameChanged(index)

                val duration = (seg.endTimeSeconds - seg.startTimeSeconds).coerceAtLeast(0.05f)
                val pcm = synthesizeChord(seg.notes, duration, sampleRate)

                var offset = 0
                val chunkSize = minBufSize / 2  // shorts per write
                while (offset < pcm.size) {
                    if (!isActive()) break
                    val toWrite = minOf(chunkSize, pcm.size - offset)
                    audioTrack.write(pcm, offset, toWrite, AudioTrack.WRITE_BLOCKING)
                    offset += toWrite
                }
            }
        } finally {
            audioTrack.stop()
            audioTrack.release()
        }
    }
}
