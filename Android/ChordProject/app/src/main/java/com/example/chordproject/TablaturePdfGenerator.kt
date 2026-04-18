@file:OptIn(kotlin.contracts.ExperimentalContracts::class, kotlin.ExperimentalUnsignedTypes::class)

package com.example.chordproject

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.pdf.PdfDocument
import alphaTab.Settings
import alphaTab.StaveProfile
import alphaTab.collections.DoubleList
import alphaTab.model.Bar
import alphaTab.model.Beat
import alphaTab.model.Duration
import alphaTab.model.MasterBar
import alphaTab.model.Note
import alphaTab.model.Score
import alphaTab.model.Staff
import alphaTab.model.Track
import alphaTab.model.Tuning
import alphaTab.model.Voice
import alphaTab.rendering.ScoreRenderer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.coroutines.resume

object TablaturePdfGenerator {

    // Standard guitar tuning: string 1 (high E) to string 6 (low E), MIDI numbers
    private val STRING_MIDI = intArrayOf(64, 59, 55, 50, 45, 40)
    private val NOTE_NAMES = arrayOf("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")

    private fun noteNameToMidi(note: String): Int? {
        val pitchClass = note.substringBefore('(').trim()
        val octave = note.substringAfter('(', "").substringBefore(')', "").toIntOrNull() ?: return null
        val pc = NOTE_NAMES.indexOf(pitchClass)
        if (pc < 0) return null
        return (octave + 1) * 12 + pc
    }

    // Prefer the lowest fret in 0-12; fall back to 0-22 on any string
    private fun noteNameToStringFret(note: String): Pair<Int, Int>? {
        val midi = noteNameToMidi(note) ?: return null
        var bestString = -1
        var bestFret = Int.MAX_VALUE
        for ((idx, openMidi) in STRING_MIDI.withIndex()) {
            val fret = midi - openMidi
            if (fret in 0..12 && fret < bestFret) {
                bestFret = fret
                bestString = idx + 1
            }
        }
        if (bestString != -1) return Pair(bestString, bestFret)
        for ((idx, openMidi) in STRING_MIDI.withIndex()) {
            val fret = midi - openMidi
            if (fret in 0..22) return Pair(idx + 1, fret)
        }
        return null
    }

    private fun buildScore(segments: List<AggregatedFrameResult>): Score {
        val score = Score()
        score.title = "Chord Analysis"
        score.tempo = 120.0

        val track = Track()
        track.index = 0.0
        track.name = "Guitar"

        val staff = Staff()
        staff.index = 0.0
        staff.track = track
        // Standard guitar tuning: E4(64), B3(59), G3(55), D3(50), A2(45), E2(40)
        val tuningList = DoubleList()
        tuningList.push(64.0)
        tuningList.push(59.0)
        tuningList.push(55.0)
        tuningList.push(50.0)
        tuningList.push(45.0)
        tuningList.push(40.0)
        staff.stringTuning = Tuning("Standard", tuningList, true)
        track.staves.push(staff)
        score.addTrack(track)

        var barIndex = 0
        var segIdx = 0
        while (segIdx < segments.size) {
            val masterBar = MasterBar()
            masterBar.index = barIndex.toDouble()
            masterBar.timeSignatureNumerator = 4.0
            masterBar.timeSignatureDenominator = 4.0
            score.addMasterBar(masterBar)

            val bar = Bar()
            bar.index = barIndex.toDouble()
            barIndex++
            staff.addBar(bar)

            val voice = Voice()
            voice.index = 0.0
            bar.addVoice(voice)

            var beatCount = 0
            while (segIdx < segments.size && beatCount < 4) {
                val seg = segments[segIdx++]
                val beat = Beat()
                beat.duration = Duration.Quarter

                if (seg.chord != "(silence)" && seg.notes.isNotEmpty()) {
                    for (noteName in seg.notes) {
                        val (str, fret) = noteNameToStringFret(noteName) ?: continue
                        val note = Note()
                        note.string = str.toDouble()
                        note.fret = fret.toDouble()
                        beat.addNote(note)
                    }
                }
                // Empty beat (no notes) acts as a rest

                voice.addBeat(beat)
                beatCount++
            }

            // Pad bar to 4 beats
            while (beatCount < 4) {
                val rest = Beat()
                rest.duration = Duration.Quarter
                voice.addBeat(rest)
                beatCount++
            }
        }

        return score
    }

    suspend fun generate(context: Context, segments: List<AggregatedFrameResult>): File {
        val renderWidthPx = 794 // A4 width at 96 DPI

        val settings = Settings()
        settings.display.staveProfile = StaveProfile.Tab

        val score = buildScore(segments)
        score.finish(settings)

        data class Chunk(val x: Float, val y: Float, val bitmap: Bitmap)

        // ScoreRenderer creates an Android Handler internally, so it must run on a Looper thread
        val (chunks, totalHeight) = withContext(Dispatchers.Main) {
            suspendCancellableCoroutine { cont ->
                val renderer = ScoreRenderer(settings)
                renderer.width = renderWidthPx.toDouble()

                val chunks = mutableListOf<Chunk>()
                var totalHeight = 100.0

                renderer.partialLayoutFinished.on { e ->
                    renderer.renderResult(e.id)
                }

                renderer.partialRenderFinished.on { e ->
                    (e.renderResult as? Bitmap)?.let { bmp ->
                        chunks.add(Chunk(e.x.toFloat(), e.y.toFloat(), bmp))
                    }
                }

                renderer.renderFinished.on { e ->
                    totalHeight = e.totalHeight
                    cont.resume(Pair(chunks, totalHeight))
                }

                val trackIndexes = DoubleList()
                trackIndexes.push(0.0)
                renderer.renderScore(score, trackIndexes)
            }
        }

        // Composite all rendered chunks onto a single white bitmap (IO-safe)
        return withContext(Dispatchers.IO) {
            val fullBitmap = Bitmap.createBitmap(
                renderWidthPx,
                totalHeight.toInt().coerceAtLeast(100),
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(fullBitmap)
            canvas.drawColor(Color.WHITE)
            for (chunk in chunks) {
                canvas.drawBitmap(chunk.bitmap, chunk.x, chunk.y, null)
            }

            // Slice into A4 pages
            val pdfPageWidth = 595
            val pdfPageHeight = 842
            val doc = PdfDocument()
            var pageIndex = 0
            var sliceY = 0

            while (sliceY < fullBitmap.height) {
                val sliceH = minOf(pdfPageHeight, fullBitmap.height - sliceY)
                val info = PdfDocument.PageInfo.Builder(pdfPageWidth, sliceH, ++pageIndex).create()
                val page = doc.startPage(info)
                val srcRect = Rect(0, sliceY, renderWidthPx, sliceY + sliceH)
                val dstRect = RectF(0f, 0f, pdfPageWidth.toFloat(), sliceH.toFloat())
                page.canvas.drawBitmap(fullBitmap, srcRect, dstRect, null)
                doc.finishPage(page)
                sliceY += pdfPageHeight
            }

            fullBitmap.recycle()

            val file = File(context.cacheDir, "tablature.pdf")
            file.outputStream().use { doc.writeTo(it) }
            doc.close()
            file
        }
    }
}
