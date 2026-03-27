package com.example.chordproject

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import kotlin.math.PI
import kotlin.math.min
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

    // Picks the top 2 notes from a segment: deduplicates by pitch class (keeps strongest octave),
    // then takes the top 2 by magnitude. Falls back to seg.notes.take(2) when peaks are absent.
    private fun selectTopNotes(seg: AggregatedFrameResult): List<String> {
        if (seg.peaks.isEmpty()) return seg.notes.take(2)
        return seg.peaks
            .groupBy { it.noteName.substringBefore('(') }
            .values
            .map { group -> group.maxByOrNull { it.magnitude }!! }
            .sortedByDescending { it.magnitude }
            .take(2)
            .map { it.noteName }
    }

    // Synthesizes one segment's PCM with phase-continuous sine waves.
    // Fade-in is only applied to notes absent from prevNotes; fade-out only to notes absent from nextNotes.
    // Continuing notes play through segment boundaries without any amplitude dip.
    private fun synthesizeLegatoChunk(
        notes: List<String>,
        prevNotes: List<String>,
        nextNotes: List<String>,
        numSamples: Int,
        totalSamplesOffset: Long,
        sampleRate: Int,
        fadeSamples: Int
    ): ShortArray {
        val result = ShortArray(numSamples)
        val freqsWithFade = notes.mapNotNull { name ->
            val freq = noteNameToFreq(name) ?: return@mapNotNull null
            val fadeIn  = name !in prevNotes
            val fadeOut = name !in nextNotes
            Triple(freq, fadeIn, fadeOut)
        }
        if (freqsWithFade.isEmpty()) return result

        val scale = Short.MAX_VALUE * 0.85f / freqsWithFade.size
        for (s in 0 until numSamples) {
            var sample = 0f
            for ((freq, fadeIn, fadeOut) in freqsWithFade) {
                val phase = 2.0 * PI * freq * (totalSamplesOffset + s) / sampleRate
                val envIn  = if (fadeIn)  min(s, fadeSamples).toFloat() / fadeSamples else 1f
                val envOut = if (fadeOut) min(numSamples - 1 - s, fadeSamples).toFloat() / fadeSamples else 1f
                sample += sin(phase).toFloat() * envIn * envOut
            }
            result[s] = (sample * scale).toInt().coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
        }
        return result
    }

    suspend fun playSimplified(
        segments: List<AggregatedFrameResult>,
        sampleRate: Int,
        onFrameChanged: (Int) -> Unit,
        isActive: () -> Boolean
    ) {
        val notesPerSegment = segments.map { selectTopNotes(it) }
        val fadeSamples = (20 * sampleRate / 1000).coerceAtLeast(1)

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

        var totalSamplesWritten = 0L
        try {
            for ((index, seg) in segments.withIndex()) {
                if (!isActive()) break
                onFrameChanged(index)

                val numSamples = ((seg.endTimeSeconds - seg.startTimeSeconds)
                    .coerceAtLeast(0.05f) * sampleRate).toInt()
                val prevNotes = if (index > 0) notesPerSegment[index - 1] else emptyList()
                val nextNotes = if (index < segments.lastIndex) notesPerSegment[index + 1] else emptyList()

                val pcm = synthesizeLegatoChunk(
                    notes              = notesPerSegment[index],
                    prevNotes          = prevNotes,
                    nextNotes          = nextNotes,
                    numSamples         = numSamples,
                    totalSamplesOffset = totalSamplesWritten,
                    sampleRate         = sampleRate,
                    fadeSamples        = fadeSamples
                )

                var offset = 0
                val chunkSize = minBufSize / 2
                while (offset < pcm.size) {
                    if (!isActive()) break
                    val toWrite = minOf(chunkSize, pcm.size - offset)
                    audioTrack.write(pcm, offset, toWrite, AudioTrack.WRITE_BLOCKING)
                    offset += toWrite
                }
                totalSamplesWritten += numSamples
            }
        } finally {
            audioTrack.stop()
            audioTrack.release()
        }
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
