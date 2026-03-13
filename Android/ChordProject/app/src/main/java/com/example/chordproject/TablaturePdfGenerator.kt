package com.example.chordproject

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import java.io.File

object TablaturePdfGenerator {

    private val NOTE_NAMES = arrayOf("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")

    // Fret on high-E string (MIDI 64 = E4, open = 0): fret = (pitchClassIndex - 4 + 12) % 12
    private fun noteToFret(note: String): Int {
        val pc = NOTE_NAMES.indexOf(note)
        if (pc < 0) return -1
        return (pc - 4 + 12) % 12
    }

    fun generate(context: Context, segments: List<AggregatedFrameResult>): File {
        val pageWidth  = 595
        val pageHeight = 842
        val margin     = 40f
        val usableWidth = pageWidth - 2 * margin  // 515 pts

        val cols      = 8
        val colWidth  = usableWidth / cols         // ~64.375 pts
        val rowHeight = 100f
        val rowsPerPage = 6

        val chordLabelHeight = 14f
        val staffTop         = chordLabelHeight + 4f  // offset within column
        val staffLines       = 6
        val staffLineSpacing = (rowHeight - staffTop - 16f) / (staffLines - 1)

        val paintLine = Paint().apply {
            color = Color.BLACK
            strokeWidth = 0.8f
            style = Paint.Style.STROKE
        }
        val paintText = Paint().apply {
            color = Color.BLACK
            textSize = 9f
            typeface = Typeface.DEFAULT_BOLD
            isAntiAlias = true
        }
        val paintFret = Paint().apply {
            color = Color.BLACK
            textSize = 8f
            isAntiAlias = true
        }
        val paintTimestamp = Paint().apply {
            color = Color.DKGRAY
            textSize = 7f
            isAntiAlias = true
        }

        val doc = PdfDocument()
        var pageIndex = 0
        var segIndex  = 0

        while (segIndex < segments.size) {
            val pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, ++pageIndex).create()
            val page     = doc.startPage(pageInfo)
            val canvas: Canvas = page.canvas

            for (row in 0 until rowsPerPage) {
                if (segIndex >= segments.size) break
                val rowY = margin + row * rowHeight

                for (col in 0 until cols) {
                    if (segIndex >= segments.size) break
                    val seg  = segments[segIndex++]
                    val colX = margin + col * colWidth

                    // Chord label
                    val label = if (seg.chord == "(silence)") "(silence)" else seg.chord
                    paintText.color = if (seg.chord == "(silence)") Color.GRAY else Color.BLACK
                    canvas.drawText(label, colX + 2f, rowY + chordLabelHeight, paintText)

                    // 6-string staff lines
                    val staffStartY = rowY + staffTop + 4f
                    val staffEndX   = colX + colWidth - 2f
                    for (s in 0 until staffLines) {
                        val y = staffStartY + s * staffLineSpacing
                        paintLine.color = Color.BLACK
                        canvas.drawLine(colX + 2f, y, staffEndX, y, paintLine)
                    }

                    // Fret numbers on high-E string (string index 0 = top line)
                    if (seg.notes.isNotEmpty()) {
                        val frets = seg.notes.mapNotNull { note ->
                            val f = noteToFret(note)
                            if (f >= 0) f else null
                        }.distinct()

                        // Place on high-E line (top = string 0)
                        val highEY = staffStartY
                        val fretStr = frets.joinToString(",")
                        paintFret.color = Color.rgb(0, 100, 0)
                        canvas.drawText(fretStr, colX + colWidth / 2f - 6f, highEY + 1f, paintFret)
                    }

                    // Timestamp below staff
                    val timeY = staffStartY + (staffLines - 1) * staffLineSpacing + 9f
                    canvas.drawText("%.2fs".format(seg.startTimeSeconds), colX + 2f, timeY, paintTimestamp)

                    // Vertical barline at right edge of column
                    paintLine.color = Color.LTGRAY
                    canvas.drawLine(colX + colWidth - 1f, rowY, colX + colWidth - 1f, rowY + rowHeight - 4f, paintLine)
                }
            }

            doc.finishPage(page)
        }

        val file = File(context.cacheDir, "tablature.pdf")
        file.outputStream().use { doc.writeTo(it) }
        doc.close()
        return file
    }
}
