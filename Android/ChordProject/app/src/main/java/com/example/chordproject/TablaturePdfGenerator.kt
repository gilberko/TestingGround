@file:OptIn(kotlin.contracts.ExperimentalContracts::class, kotlin.ExperimentalUnsignedTypes::class)

package com.example.chordproject

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import kotlin.math.abs

object TablaturePdfGenerator {

    // AlphaTab string numbering: string 1 = low E (bottom of TAB), string 6 = high E (top).
    // We index from low E so idx=0→string 1 (E2), idx=5→string 6 (E4).
    private val STRING_MIDI = intArrayOf(40, 45, 50, 55, 59, 64) // E2 A2 D3 G3 B3 E4
    private val NOTE_NAMES = arrayOf("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")

    private fun noteNameToMidi(note: String): Int? {
        val pitchClass = note.substringBefore('(').trim()
        val octave = note.substringAfter('(', "").substringBefore(')', "").toIntOrNull() ?: return null
        val pc = NOTE_NAMES.indexOf(pitchClass)
        if (pc < 0) return null
        return (octave + 1) * 12 + pc
    }

    // Returns all playable (string, fret) options for a note, sorted by fret number.
    // String 1=low E … string 6=high E, matching AlphaTab's convention.
    private fun noteNameToCandidates(note: String): List<Pair<Int, Int>> {
        val midi = noteNameToMidi(note) ?: return emptyList()
        val primary = mutableListOf<Pair<Int, Int>>()
        val fallback = mutableListOf<Pair<Int, Int>>()
        for ((idx, openMidi) in STRING_MIDI.withIndex()) {
            val fret = midi - openMidi
            val string = idx + 1
            if (fret in 0..12) primary += Pair(string, fret)
            else if (fret in 13..22) fallback += Pair(string, fret)
        }
        primary.sortBy { it.second }
        fallback.sortBy { it.second }
        return primary + fallback
    }

    // Pick the best (string, fret) for a note given the current hand position.
    // Rules:
    //   1. Always prefer primary (frets 0-12) over fallback (frets 13-22).
    //   2. Among fallbacks, prefer treble strings (5=B, 6=e) over bass strings —
    //      high-fret playing naturally lives on the thinner strings.
    //   3. Within each pool, pick the candidate closest to targetFret.
    private fun bestCandidate(note: String, targetFret: Float): Pair<Int, Int>? {
        val candidates = noteNameToCandidates(note)
        val primary  = candidates.filter { it.second <= 12 }
        val fallback = candidates.filter { it.second >  12 }
        return when {
            primary.isNotEmpty() -> primary.minByOrNull { abs(it.second - targetFret) }
            fallback.isNotEmpty() -> {
                val treble = fallback.filter { it.first >= 5 } // strings 5 (B) and 6 (e)
                val pool   = if (treble.isNotEmpty()) treble else fallback
                pool.minByOrNull { abs(it.second - targetFret) }
            }
            else -> null
        }
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
        // AlphaTab expects tuning from string 1 (low E) to string N (high E)
        val tuningList = DoubleList()
        tuningList.push(40.0) // E2 low E  — string 1
        tuningList.push(45.0) // A2
        tuningList.push(50.0) // D3
        tuningList.push(55.0) // G3
        tuningList.push(59.0) // B3
        tuningList.push(64.0) // E4 high E — string 6
        staff.stringTuning = Tuning("Standard", tuningList, true)
        track.staves.push(staff)
        score.addTrack(track)

        var barIndex = 0
        var segIdx = 0
        var centerFret = 5f // tracks current hand position to minimise jumps

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
                    val assignedFrets = mutableListOf<Int>()
                    for (noteName in seg.notes) {
                        val (str, fret) = bestCandidate(noteName, centerFret) ?: continue
                        val note = Note()
                        note.string = str.toDouble()
                        note.fret = fret.toDouble()
                        beat.addNote(note)
                        assignedFrets += fret
                    }
                    if (assignedFrets.isNotEmpty()) centerFret = assignedFrets.average().toFloat()
                }

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

    suspend fun generate(context: Context, segments: List<AggregatedFrameResult>, physicalScale: Double = 2.0): File {
        val renderWidthPx = 794   // A4 width in logical pixels (determines layout)

        val settings = Settings()
        settings.display.staveProfile = StaveProfile.Tab

        val score = buildScore(segments)
        score.finish(settings)

        data class Chunk(val x: Float, val y: Float, val bitmap: Bitmap)

        // ScoreRenderer requires AlphaTab's native Skia + Bravura font to be initialized first.
        // AndroidEnvironment.initializeAndroid is internal so we call it via reflection.
        // It has an _isInitialized guard so repeated calls are free.
        val (chunks, totalHeight) = withContext(Dispatchers.Main) {
            Class.forName("alphaTab.platform.android.AndroidEnvironment")
                .getDeclaredField("Companion")
                .also { it.isAccessible = true }
                .get(null)
                .let { companion ->
                    companion.javaClass
                        .getDeclaredMethod("initializeAndroid", Context::class.java)
                        .also { it.isAccessible = true }
                        .invoke(companion, context)
                }
            // physicalScale controls output DPI. Chunk bitmaps come back at physicalScale× their
            // logical size; chunk (x,y) coordinates remain in logical units, so we multiply when
            // compositing. Setting HighDpiFactor here affects the global but initializeAndroid's
            // guard prevents it from being reset on subsequent Export taps.
            alphaTab.Environment.HighDpiFactor = physicalScale

            suspendCancellableCoroutine { cont ->
                val renderer = ScoreRenderer(settings)
                renderer.width = renderWidthPx.toDouble()

                val chunks = mutableListOf<Chunk>()
                var totalHeight = 100.0

                renderer.partialLayoutFinished.on { e ->
                    renderer.renderResult(e.id)
                }

                renderer.partialRenderFinished.on { e ->
                    val image = e.renderResult as? alphaTab.platform.skia.AlphaSkiaImage
                    val pngBytes = image?.toPng()?.toByteArray()
                    if (pngBytes != null) {
                        val bmp = BitmapFactory.decodeByteArray(pngBytes, 0, pngBytes.size)
                        if (bmp != null) chunks.add(Chunk(e.x.toFloat(), e.y.toFloat(), bmp))
                    }
                }

                renderer.renderFinished.on { e ->
                    totalHeight = e.totalHeight
                    cont.resume(Pair(chunks, totalHeight))
                }

                renderer.error.on { e ->
                    cont.resumeWith(Result.failure(e))
                }

                val trackIndexes = DoubleList()
                trackIndexes.push(0.0)
                renderer.renderScore(score, trackIndexes)
            }
        }

        // Composite all rendered chunks onto a single white bitmap (IO-safe)
        return withContext(Dispatchers.IO) {
            val renderWidthPhys = (renderWidthPx * physicalScale).toInt()
            val totalHeightPhys = (totalHeight * physicalScale).toInt().coerceAtLeast(100)

            val fullBitmap = Bitmap.createBitmap(renderWidthPhys, totalHeightPhys, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(fullBitmap)
            canvas.drawColor(Color.WHITE)
            for (chunk in chunks) {
                // chunk (x,y) are logical units; bitmaps are physicalScale× larger
                canvas.drawBitmap(chunk.bitmap, chunk.x * physicalScale.toFloat(), chunk.y * physicalScale.toFloat(), null)
            }

            // Slice into A4 pages. pdfPageWidth/Height are in PDF user units (pts).
            // bitmapPageH = how many physical pixels correspond to one A4 page height.
            val pdfPageWidth = 595
            val pdfPageHeight = 842
            val bitmapPageH = (pdfPageHeight.toDouble() * renderWidthPhys / pdfPageWidth).toInt()
            val doc = PdfDocument()
            var pageIndex = 0
            var sliceY = 0

            while (sliceY < fullBitmap.height) {
                val sliceH = minOf(bitmapPageH, fullBitmap.height - sliceY)
                // Convert physical slice height back to PDF pts to keep correct aspect ratio
                val pdfSliceH = (sliceH.toDouble() * pdfPageWidth / renderWidthPhys).toInt()
                val info = PdfDocument.PageInfo.Builder(pdfPageWidth, pdfSliceH, ++pageIndex).create()
                val page = doc.startPage(info)
                val srcRect = Rect(0, sliceY, renderWidthPhys, sliceY + sliceH)
                val dstRect = RectF(0f, 0f, pdfPageWidth.toFloat(), pdfSliceH.toFloat())
                page.canvas.drawBitmap(fullBitmap, srcRect, dstRect, null)
                doc.finishPage(page)
                sliceY += bitmapPageH
            }

            fullBitmap.recycle()

            val file = File(context.cacheDir, "tablature.pdf")
            file.outputStream().use { doc.writeTo(it) }
            doc.close()
            file
        }
    }
}
