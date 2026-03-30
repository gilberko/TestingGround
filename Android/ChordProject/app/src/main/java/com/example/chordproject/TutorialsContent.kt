package com.example.chordproject

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

// ── Data ────────────────────────────────────────────────────────────────────

/** string: 0 = high e (top of diagram), 5 = low E (bottom). fret: 0-based offset from box start. */
data class NeckDot(val string: Int, val fret: Int, val isRoot: Boolean)

data class ScalePattern(
    val name: String,
    val intervals: String,
    val description: String,
    val practical: String,
    val positions: List<Pair<Int, List<NeckDot>>>,   // Pair(startFretLabel, dots)
    val fretCount: Int = 5
)

data class ArpeggioPattern(
    val name: String,
    val intervals: String,
    val description: String,
    val dots: List<NeckDot>,
    val fretCount: Int = 5
)

// ── Scale dot data ───────────────────────────────────────────────────────────
// All patterns: string 0=high e, 5=low E; fret = relative offset from root position.
// Open strings: E(0)=E, A(0)=A, D(0)=D, G(0)=G, B(0)=B, e(0)=E
// Root sits at rel-fret 0 on the low E string; other strings start at a different pitch.

private fun minorPentatonicDots() = listOf(
    NeckDot(0, 0, true),  NeckDot(0, 3, false), // high e: R, b3
    NeckDot(1, 0, false), NeckDot(1, 3, false),  // B:    P5, b7
    NeckDot(2, 0, false), NeckDot(2, 2, false),  // G:    b3, P4
    NeckDot(3, 0, false), NeckDot(3, 2, true),   // D:    b7, R(oct)
    NeckDot(4, 0, false), NeckDot(4, 2, false),  // A:    P4, P5
    NeckDot(5, 0, true),  NeckDot(5, 3, false)   // low E: R, b3
)

private fun majorPentatonicDots() = listOf(
    // high e: R(0), M2(2), M3(4)
    NeckDot(0, 0, true),  NeckDot(0, 2, false), NeckDot(0, 4, false),
    // B: M6(0), R(2) — B string starts 2 semitones below high e pattern
    // At root-position box on B string: M6 of root = fret 0 on B, R = fret 2
    NeckDot(1, 0, false), NeckDot(1, 2, true),
    // G: M3(0), P5(2) — G string at root-box: M3 at rel0, P5 at rel2
    NeckDot(2, 0, false), NeckDot(2, 2, false),
    // D: R(0), M2(2) — D string at root-box: root octave at rel0? Actually M6 is at rel0...
    // D string in this box: M6 is at rel0(=5th fret of D = G# if root is A? No.)
    // Let me recalculate: A major pentatonic, root A at fret 5 of low E.
    // D string at fret 5 = G. G is not in A major pentatonic (A B C# E F#).
    // Fret 7 = A (root!), fret 9 = B (M2), fret 11 = C# (M3). Only A(rel2) and B(rel4) in 5-fret box.
    NeckDot(3, 2, true),  NeckDot(3, 4, false),
    // A string at fret 5 = D. Not in A maj pent. Fret 7 = E (P5), fret 9 = F# (M6). E(rel2), F#(rel4).
    NeckDot(4, 2, false), NeckDot(4, 4, false),
    // low E: A(rel0=R), B(rel2=M2)
    NeckDot(5, 0, true),  NeckDot(5, 2, false)
)

private fun bluesDots() = listOf(
    NeckDot(0, 0, true),  NeckDot(0, 3, false),             // high e: R, b3
    NeckDot(1, 0, false), NeckDot(1, 3, false),              // B:    P5, b7
    NeckDot(2, 0, false), NeckDot(2, 2, false),              // G:    b3, P4
    NeckDot(3, 0, false), NeckDot(3, 2, true),               // D:    b7, R(oct)
    NeckDot(4, 0, false), NeckDot(4, 1, false), NeckDot(4, 2, false), // A: P4, b5(passing), P5
    NeckDot(5, 0, true),  NeckDot(5, 3, false)               // low E: R, b3
)

// Major scale: R M2 M3 P4 P5 M6 M7
// Root A at fret 5 of low E. 7-fret box (frets 5-11).
// low E: A(0)R, B(2)M2, C#(4)M3
// A str: D(0)P4, E(2)P5, F#(4)M6
// D str: G(0)P4... wait: D string at fret 5 = G. G is P7 from A? No: A to G is m7. G is not in A major.
// Actually A major: A B C# D E F# G#. D string fret 5 = G (not in scale). Fret 7 = A(R), fret 9 = B(M2), fret 11=C#(M3).
// So D string: A(2=R), B(4=M2), C#(6=M3) — but rel6 is outside 7-fret box? 7-fret = rel0 to rel6. Barely fits at rel6.
// G string fret 5 = C, 6=C#(M3), 7=D(P4), 8=D#, 9=E(P5), 10=F, 11=F#(M6). C#(1=M3), D(2=P4), E(4=P5), F#(6=M6).
// B string fret 5 = E(P5), 7=F#(M6), 9=G#(M7). E(0=P5), F#(2=M6), G#(4=M7).
// high e: same as low E: A(0=R), B(2=M2), C#(4=M3).
private fun majorScaleDots() = listOf(
    NeckDot(0, 0, true),  NeckDot(0, 2, false), NeckDot(0, 4, false),  // e: R M2 M3
    NeckDot(1, 0, false), NeckDot(1, 2, false), NeckDot(1, 4, false),  // B: P5 M6 M7
    NeckDot(2, 1, false), NeckDot(2, 2, false), NeckDot(2, 4, false), NeckDot(2, 6, false), // G: M3 P4 P5 M6
    NeckDot(3, 2, true),  NeckDot(3, 4, false), NeckDot(3, 6, false),  // D: R M2 M3
    NeckDot(4, 0, false), NeckDot(4, 2, false), NeckDot(4, 4, false),  // A: P4 P5 M6
    NeckDot(5, 0, true),  NeckDot(5, 2, false), NeckDot(5, 4, false)   // E: R M2 M3
)

// Natural Minor: R M2 b3 P4 P5 b6 b7
// A natural minor: A B C D E F G. Root A at fret 5 of low E.
// low E: A(0=R), B(2=M2), C(3=b3)
// A str: D(0=P4), E(2=P5), F(3=b6)
// D str: A(2=R), B(4=M2), C(5=b3) — rel5 in 7-fret box ✓
// G str: C(0=b3), D(2=P4), E(4=P5), F(5=b6) — rel5 in 7-fret box ✓
// B str: E(0=P5), F(1=b6), G(3=b7)
// high e: A(0=R), B(2=M2), C(3=b3)
private fun naturalMinorDots() = listOf(
    NeckDot(0, 0, true),  NeckDot(0, 2, false), NeckDot(0, 3, false),  // e: R M2 b3
    NeckDot(1, 0, false), NeckDot(1, 1, false), NeckDot(1, 3, false),  // B: P5 b6 b7
    NeckDot(2, 0, false), NeckDot(2, 2, false), NeckDot(2, 4, false), NeckDot(2, 5, false), // G: b3 P4 P5 b6
    NeckDot(3, 2, true),  NeckDot(3, 4, false), NeckDot(3, 5, false),  // D: R M2 b3
    NeckDot(4, 0, false), NeckDot(4, 2, false), NeckDot(4, 3, false),  // A: P4 P5 b6
    NeckDot(5, 0, true),  NeckDot(5, 2, false), NeckDot(5, 3, false)   // E: R M2 b3
)

// Harmonic Minor: R M2 b3 P4 P5 b6 M7  (raised 7th vs natural minor)
// A harmonic minor: A B C D E F G#
// Same as natural minor but G→G# on every string:
// low E: A(0=R), B(2=M2), C(3=b3) — same
// A str: D(0=P4), E(2=P5), F(3=b6) — same
// D str: A(2=R), B(4=M2), C(5=b3) — same
// G str: C(0=b3), D(2=P4), E(4=P5), F(5=b6) — same
// B str: E(0=P5), F(1=b6), G#(4=M7)  ← G→G# : was rel3, now rel4
// high e: A(0=R), B(2=M2), C(3=b3) — same (G# at rel4 not in 5-fret portion = rel4 barely fits)
// Also: on G string, G# would appear at rel5 (already have F at rel5; G# = rel6 barely outside). Skip for now.
private fun harmonicMinorDots() = listOf(
    NeckDot(0, 0, true),  NeckDot(0, 2, false), NeckDot(0, 3, false),  // e: R M2 b3
    NeckDot(1, 0, false), NeckDot(1, 1, false), NeckDot(1, 4, false),  // B: P5 b6 M7
    NeckDot(2, 0, false), NeckDot(2, 2, false), NeckDot(2, 4, false), NeckDot(2, 5, false), // G: b3 P4 P5 b6
    NeckDot(3, 2, true),  NeckDot(3, 4, false), NeckDot(3, 5, false),  // D: R M2 b3
    NeckDot(4, 0, false), NeckDot(4, 2, false), NeckDot(4, 3, false),  // A: P4 P5 b6
    NeckDot(5, 0, true),  NeckDot(5, 2, false), NeckDot(5, 3, false)   // E: R M2 b3
)

// Melodic Minor (ascending): R M2 b3 P4 P5 M6 M7  (b3 only difference from major)
// A melodic minor: A B C D E F# G#
// Same as major scale except b3 (C) instead of M3 (C#):
// low E: A(0=R), B(2=M2), C(3=b3) ← C not C#
// A str: D(0=P4), E(2=P5), F#(4=M6) — same as major
// D str: A(2=R), B(4=M2), C(5=b3) ← C not C# at rel6
// G str: C(0=b3), D(2=P4), E(4=P5), F#(6=M6) — C replaces C# at rel1→rel0
// B str: E(0=P5), F#(2=M6), G#(4=M7) — same as major
// high e: A(0=R), B(2=M2), C(3=b3)
private fun melodicMinorDots() = listOf(
    NeckDot(0, 0, true),  NeckDot(0, 2, false), NeckDot(0, 3, false),  // e: R M2 b3
    NeckDot(1, 0, false), NeckDot(1, 2, false), NeckDot(1, 4, false),  // B: P5 M6 M7
    NeckDot(2, 0, false), NeckDot(2, 2, false), NeckDot(2, 4, false), NeckDot(2, 6, false), // G: b3 P4 P5 M6
    NeckDot(3, 2, true),  NeckDot(3, 4, false), NeckDot(3, 5, false),  // D: R M2 b3
    NeckDot(4, 0, false), NeckDot(4, 2, false), NeckDot(4, 4, false),  // A: P4 P5 M6
    NeckDot(5, 0, true),  NeckDot(5, 2, false), NeckDot(5, 3, false)   // E: R M2 b3
)

private val SCALE_PATTERNS = listOf(
    ScalePattern(
        name = "Minor Pentatonic",
        intervals = "R  b3  P4  P5  b7",
        description = "Five notes, no half-steps — every note sounds good over a minor chord. The most important scale for blues and rock improvisation.",
        practical = "Start on any root note on the low E or A string. The same box shape moves up the neck chromatically.",
        positions = listOf(5 to minorPentatonicDots(), 8 to minorPentatonicDots()),
        fretCount = 4
    ),
    ScalePattern(
        name = "Major Pentatonic",
        intervals = "R  M2  M3  P5  M6",
        description = "The bright, uplifting sibling of the minor pentatonic. Used in country, pop, and over major chords. The minor pentatonic of the relative minor shares exactly the same notes.",
        practical = "C major pentatonic = A minor pentatonic. Shift one shape, get two sounds.",
        positions = listOf(5 to majorPentatonicDots(), 8 to majorPentatonicDots()),
        fretCount = 5
    ),
    ScalePattern(
        name = "Blues Scale",
        intervals = "R  b3  P4  b5  P5  b7",
        description = "Minor pentatonic with one extra 'blue note': the b5 (tritone). This passing tone creates the characteristic tension and grit of blues music.",
        practical = "The b5 is a passing tone — slide through it, don't linger. It's the note that makes the blues cry.",
        positions = listOf(5 to bluesDots()),
        fretCount = 4
    ),
    ScalePattern(
        name = "Major Scale",
        intervals = "R  M2  M3  P4  P5  M6  M7",
        description = "The foundation of Western music. Bright and resolved. All diatonic chords (I ii iii IV V vi vii°) are built from it.",
        practical = "The interval pattern W-W-H-W-W-W-H repeats for every major key. Memorise the pattern, not just C major.",
        positions = listOf(5 to majorScaleDots()),
        fretCount = 7
    ),
    ScalePattern(
        name = "Natural Minor",
        intervals = "R  M2  b3  P4  P5  b6  b7",
        description = "The Aeolian mode. Darker and more melancholic than major. The relative minor of every major key shares its notes (e.g. A minor = C major).",
        practical = "For every major key you know, you automatically know its relative natural minor — just start from scale degree 6.",
        positions = listOf(5 to naturalMinorDots()),
        fretCount = 6
    ),
    ScalePattern(
        name = "Harmonic Minor",
        intervals = "R  M2  b3  P4  P5  b6  M7",
        description = "Natural minor with a raised 7th. The raised 7th creates a strong leading tone (half-step pull back to the root) and gives the scale an exotic, Middle Eastern flavour.",
        practical = "The augmented 2nd interval between b6 and M7 is the signature sound. Common in classical, flamenco, and metal.",
        positions = listOf(5 to harmonicMinorDots()),
        fretCount = 6
    ),
    ScalePattern(
        name = "Melodic Minor",
        intervals = "R  M2  b3  P4  P5  M6  M7  (ascending)",
        description = "Major scale with a b3. Raises both the 6th and 7th of natural minor to smooth the melodic line. In jazz it is used the same way ascending and descending.",
        practical = "Think of it as a major scale with a minor 3rd. Widely used in jazz over minor-major7 chords and Lydian Dominant contexts.",
        positions = listOf(5 to melodicMinorDots()),
        fretCount = 7
    )
)

// ── Arpeggio dot data ─────────────────────────────────────────────────────────
// Root on low E (string 5), 5-fret box (rel0–rel4).
// Confirmed positions for root A at fret 5:
//   low E: A=0, B=2, C=3, C#=4, E=7(outside)
//   A str: C#=rel-1(out), D=0, E=2, A=7(out)  → P4(rel0), P5(rel2) [or M3=rel-1 outside]
//   D str: G=0, G#=1, A=2, B=4  → b7(rel0), M7(rel1), R(rel2), M2(rel4)
//   G str: C=0, C#=1, D=2, D#=3, E=4  → b3(rel0), M3(rel1), P4(rel2), P5(rel4)
//   B str: E=0, F=1, F#=2, G=3, G#=4  → P5(rel0), b6(rel1), M6(rel2), b7(rel3), M7(rel4)
//   high e: A=0, A#=1, B=2, C=3, C#=4  → R(rel0), b2(rel1), M2(rel2), b3(rel3), M3(rel4)

private val ARPEGGIO_PATTERNS = listOf(
    ArpeggioPattern(
        name = "Major",
        intervals = "R  M3  P5",
        description = "The three notes of a major chord. Bright and stable. Use over any major chord in a progression.",
        dots = listOf(
            NeckDot(0, 0, true),  NeckDot(0, 4, false),  // e: R, M3
            NeckDot(1, 0, false),                          // B: P5
            NeckDot(2, 1, false), NeckDot(2, 4, false),   // G: M3, P5
            NeckDot(3, 2, true),                           // D: R(oct)
            NeckDot(4, 2, false),                          // A: P5
            NeckDot(5, 0, true),  NeckDot(5, 4, false)    // E: R, M3
        )
    ),
    ArpeggioPattern(
        name = "Minor",
        intervals = "R  b3  P5",
        description = "The three notes of a minor chord. Darker and more introspective. Use over any minor chord.",
        dots = listOf(
            NeckDot(0, 0, true),  NeckDot(0, 3, false),   // e: R, b3
            NeckDot(1, 0, false),                           // B: P5
            NeckDot(2, 0, false), NeckDot(2, 4, false),    // G: b3, P5
            NeckDot(3, 2, true),                            // D: R(oct)
            NeckDot(4, 2, false),                           // A: P5
            NeckDot(5, 0, true),  NeckDot(5, 3, false)     // E: R, b3
        )
    ),
    ArpeggioPattern(
        name = "Major 7 (Maj7)",
        intervals = "R  M3  P5  M7",
        description = "A major chord with a major 7th added. Lush and jazzy. Common in jazz standards, bossa nova, and neo-soul.",
        dots = listOf(
            NeckDot(0, 0, true),  NeckDot(0, 4, false),   // e: R, M3
            NeckDot(1, 0, false), NeckDot(1, 4, false),    // B: P5, M7
            NeckDot(2, 1, false), NeckDot(2, 4, false),    // G: M3, P5
            NeckDot(3, 1, false), NeckDot(3, 2, true),     // D: M7, R(oct)
            NeckDot(4, 2, false),                           // A: P5
            NeckDot(5, 0, true),  NeckDot(5, 4, false)     // E: R, M3
        )
    ),
    ArpeggioPattern(
        name = "Minor 7 (Min7)",
        intervals = "R  b3  P5  b7",
        description = "A minor chord with a minor 7th. Smooth and relaxed. The backbone of minor jazz and funk grooves.",
        dots = listOf(
            NeckDot(0, 0, true),  NeckDot(0, 3, false),   // e: R, b3
            NeckDot(1, 0, false), NeckDot(1, 3, false),    // B: P5, b7
            NeckDot(2, 0, false), NeckDot(2, 4, false),    // G: b3, P5
            NeckDot(3, 0, false), NeckDot(3, 2, true),     // D: b7, R(oct)
            NeckDot(4, 2, false),                           // A: P5
            NeckDot(5, 0, true),  NeckDot(5, 3, false)     // E: R, b3
        )
    )
)

// ── NeckDiagram ──────────────────────────────────────────────────────────────

@Composable
fun NeckDiagram(
    dots: List<NeckDot>,
    fretCount: Int,
    startFretLabel: Int,
    label: String,
    modifier: Modifier = Modifier
) {
    val rootColor   = Color(0xFF66BB6A)
    val noteColor   = Color(0xFF90CAF9)
    val lineColor   = Color(0xFFAAAAAA)
    val labelColor  = Color(0xFFCCCCCC)
    val stringNames = listOf("e", "B", "G", "D", "A", "E")

    Column(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
        ) {
            val w = size.width
            val h = size.height

            val leftPad  = 36f   // room for string names
            val rightPad = 16f
            val topPad   = 14f
            val botPad   = 14f

            val gridL = leftPad
            val gridR = w - rightPad
            val gridT = topPad
            val gridB = h - botPad

            val fGap = (gridR - gridL) / fretCount.toFloat()
            val sGap = (gridB - gridT) / 5f

            // Fret lines
            for (f in 0..fretCount) {
                val x = gridL + f * fGap
                val stroke = if (f == 0 && startFretLabel == 0) 5f else 1f
                drawLine(lineColor, Offset(x, gridT), Offset(x, gridB), strokeWidth = stroke)
            }

            // String lines + string name labels
            drawIntoCanvas { canvas ->
                val p = android.graphics.Paint().apply {
                    color = labelColor.toArgb()
                    textSize = 22f
                    isAntiAlias = true
                    textAlign = android.graphics.Paint.Align.RIGHT
                }
                for (s in 0..5) {
                    val y = gridT + s * sGap
                    drawLine(lineColor, Offset(gridL, y), Offset(gridR, y), strokeWidth = 1f)
                    canvas.nativeCanvas.drawText(stringNames[s], leftPad - 6f, y + 8f, p)
                }
            }

            // Start-fret label
            if (startFretLabel > 0) {
                drawIntoCanvas { canvas ->
                    val p = android.graphics.Paint().apply {
                        color = labelColor.toArgb()
                        textSize = 22f
                        isAntiAlias = true
                    }
                    canvas.nativeCanvas.drawText("${startFretLabel}fr", gridL + 4f, gridT - 2f, p)
                }
            }

            // Dots
            val dotR = sGap * 0.32f
            for (dot in dots) {
                if (dot.fret > fretCount) continue
                val cx = gridL + (dot.fret + 0.5f) * fGap
                val cy = gridT + dot.string * sGap
                drawCircle(if (dot.isRoot) rootColor else noteColor, radius = dotR, center = Offset(cx, cy))
            }
        }

        // Diagram label below
        if (label.isNotEmpty()) {
            Text(
                label,
                color = Color(0xFF888888),
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 4.dp, top = 2.dp, bottom = 8.dp)
            )
        }
    }
}

// ── Cycle of Fifths ──────────────────────────────────────────────────────────

@Composable
fun CycleOfFifthsDiagram() {
    val majorKeys  = listOf("C", "G", "D", "A", "E", "B", "F#/Gb", "Db", "Ab", "Eb", "Bb", "F")
    val minorKeys  = listOf("Am", "Em", "Bm", "F#m", "C#m", "G#m", "Ebm", "Bbm", "Fm", "Cm", "Gm", "Dm")
    val accidentals = listOf("0", "1♯", "2♯", "3♯", "4♯", "5♯", "6♯/6♭", "5♭", "4♭", "3♭", "2♭", "1♭")

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
    ) {
        val cx = size.width / 2f
        val cy = size.height / 2f
        val outerR = min(cx, cy) - 8f
        val midR   = outerR * 0.68f
        val innerR = outerR * 0.42f

        val accentColor  = Color(0xFF2E7D32)
        val sectorColor  = Color(0xFF1E1E1E)
        val sectorHL     = Color(0xFF263238)
        val borderColor  = Color(0xFF444444)
        val majorColor   = Color(0xFFE0E0E0)
        val minorColor   = Color(0xFF90CAF9)
        val accColor     = Color(0xFF888888)

        for (i in 0 until 12) {
            val startAngle = (i * 30f - 105f)   // -90 = top, offset -15 to center each slice
            val sweepAngle = 30f

            // Sector background
            val bg = if (i == 0) sectorHL else sectorColor
            drawArc(bg, startAngle, sweepAngle, useCenter = true,
                topLeft = Offset(cx - outerR, cy - outerR), size = Size(outerR * 2, outerR * 2))
            drawArc(borderColor, startAngle, sweepAngle, useCenter = true, style = Stroke(1.5f),
                topLeft = Offset(cx - outerR, cy - outerR), size = Size(outerR * 2, outerR * 2))

            // Inner circle fill (minor ring)
            val innerBg = if (i == 0) accentColor.copy(alpha = 0.25f) else Color(0xFF181818)
            drawArc(innerBg, startAngle, sweepAngle, useCenter = true,
                topLeft = Offset(cx - innerR, cy - innerR), size = Size(innerR * 2, innerR * 2))
            drawArc(borderColor, startAngle, sweepAngle, useCenter = true, style = Stroke(1f),
                topLeft = Offset(cx - innerR, cy - innerR), size = Size(innerR * 2, innerR * 2))

            // Text angle (centre of slice)
            val angleRad = Math.toRadians(((startAngle + 15f)).toDouble())
            val cosA = cos(angleRad).toFloat()
            val sinA = sin(angleRad).toFloat()

            // Major key label (outer band)
            val majorR = (midR + outerR) / 2f
            drawIntoCanvas { canvas ->
                val p = android.graphics.Paint().apply {
                    isAntiAlias = true
                    textAlign = android.graphics.Paint.Align.CENTER
                }
                // Major key name
                p.color = majorColor.toArgb()
                p.textSize = 28f
                p.isFakeBoldText = true
                canvas.nativeCanvas.drawText(majorKeys[i], cx + cosA * majorR, cy + sinA * majorR + 10f, p)
                // Accidental count
                p.color = accColor.toArgb()
                p.textSize = 18f
                p.isFakeBoldText = false
                canvas.nativeCanvas.drawText(accidentals[i], cx + cosA * majorR, cy + sinA * majorR + 26f, p)
                // Minor key label (inner ring)
                p.color = minorColor.toArgb()
                p.textSize = 20f
                val minorR2 = (innerR + midR) / 2f
                canvas.nativeCanvas.drawText(minorKeys[i], cx + cosA * minorR2, cy + sinA * minorR2 + 8f, p)
            }
        }

        // Outer and inner border circles
        drawCircle(borderColor, radius = outerR, center = Offset(cx, cy), style = Stroke(2f))
        drawCircle(borderColor, radius = midR,   center = Offset(cx, cy), style = Stroke(1f))
        drawCircle(borderColor, radius = innerR, center = Offset(cx, cy), style = Stroke(1f))

        // Centre label
        drawIntoCanvas { canvas ->
            val p = android.graphics.Paint().apply {
                color = Color(0xFF555555).toArgb()
                textSize = 20f
                isAntiAlias = true
                textAlign = android.graphics.Paint.Align.CENTER
            }
            canvas.nativeCanvas.drawText("Cycle", cx, cy - 8f, p)
            canvas.nativeCanvas.drawText("of 5ths", cx, cy + 14f, p)
        }
    }
}

// ── Section helpers ──────────────────────────────────────────────────────────

@Composable
private fun SectionHeader(text: String) {
    Text(
        text,
        color      = Color(0xFF90CAF9),
        style      = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.SemiBold,
        modifier   = Modifier.padding(top = 20.dp, bottom = 4.dp)
    )
}

@Composable
private fun BodyText(text: String) {
    Text(
        text,
        color  = Color(0xFFCCCCCC),
        style  = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 6.dp)
    )
}

@Composable
private fun ItalicNote(text: String) {
    Text(
        text,
        color     = Color(0xFF888888),
        style     = MaterialTheme.typography.bodySmall,
        fontStyle = FontStyle.Italic,
        modifier  = Modifier.padding(bottom = 8.dp)
    )
}

// ── Content screens ──────────────────────────────────────────────────────────

@Composable
private fun ScalesContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        BodyText("A scale is a set of notes arranged by pitch following a fixed interval pattern. The pattern determines the scale's character — bright, dark, exotic, or neutral.")

        for (scale in SCALE_PATTERNS) {
            HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
            SectionHeader(scale.name)
            Text(
                scale.intervals,
                color  = Color(0xFFFFCC80),
                style  = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            BodyText(scale.description)
            ItalicNote(scale.practical)
            scale.positions.forEachIndexed { idx, (startFret, dotList) ->
                val posLabel = if (scale.positions.size > 1) "Position ${idx + 1}  (root at fret $startFret)" else "Root at fret $startFret"
                NeckDiagram(
                    dots           = dotList,
                    fretCount      = scale.fretCount,
                    startFretLabel = startFret,
                    label          = posLabel,
                    modifier       = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun CycleContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        SectionHeader("What Is the Cycle of Fifths?")
        BodyText("The cycle of fifths arranges all 12 musical keys in a circle where each step clockwise is a perfect fifth (7 semitones) higher. Starting from C, the next key is G, then D, A, E, B, F#/Gb, and so on until you return to C after 12 steps.")
        BodyText("It is one of the most useful tools in music theory because keys that sit next to each other share the most notes in common, making transitions between them smooth.")

        CycleOfFifthsDiagram()

        SectionHeader("Key Signatures — Sharps")
        val sharpKeys = listOf(
            "C major" to "0 sharps",
            "G major" to "1 sharp  — F#",
            "D major" to "2 sharps — F#, C#",
            "A major" to "3 sharps — F#, C#, G#",
            "E major" to "4 sharps — F#, C#, G#, D#",
            "B major" to "5 sharps — F#, C#, G#, D#, A#",
            "F# major" to "6 sharps — F#, C#, G#, D#, A#, E#"
        )
        for ((key, sig) in sharpKeys) {
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text(key, color = Color(0xFFFFCC80), style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(0.35f))
                Text(sig, color = Color(0xFFCCCCCC), style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(0.65f))
            }
        }

        SectionHeader("Key Signatures — Flats")
        val flatKeys = listOf(
            "F major"  to "1 flat   — Bb",
            "Bb major" to "2 flats  — Bb, Eb",
            "Eb major" to "3 flats  — Bb, Eb, Ab",
            "Ab major" to "4 flats  — Bb, Eb, Ab, Db",
            "Db major" to "5 flats  — Bb, Eb, Ab, Db, Gb",
            "Gb major" to "6 flats  — Bb, Eb, Ab, Db, Gb, Cb"
        )
        for ((key, sig) in flatKeys) {
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text(key, color = Color(0xFFFFCC80), style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(0.35f))
                Text(sig, color = Color(0xFFCCCCCC), style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(0.65f))
            }
        }

        SectionHeader("How It Helps You")
        BodyText("Clockwise = +1 sharp (or −1 flat). Counter-clockwise = +1 flat.")
        BodyText("Chord progressions naturally move around the cycle. The V–I cadence (e.g. G→C) is one step counter-clockwise. The ii–V–I (e.g. Dm–G–C) is two steps counter-clockwise.")
        BodyText("Related keys (relative major/minor) appear in the inner ring. C major and A minor share all the same notes — they are relative pairs.")
        ItalicNote("Tip: If a song sounds like it only has 2 or 3 chords, those chords are probably neighbours on the cycle.")
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ImprovisationContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        BodyText("Improvisation is the art of creating music spontaneously. These ideas work at every level — from your first blues solo to advanced jazz.")

        SectionHeader("1. Target Chord Tones")
        BodyText("The root, 3rd, and 5th of the current chord are always \"safe\" landing notes. Your ear hears them as resolved and intentional.")
        BodyText("Try this: land on the root (R) of each chord on beat 1. Use other scale notes freely in between — they are passing tones leading to your target.")
        ItalicNote("Example: Over a C major chord, aim for C, E, or G on the strong beats. Over Am, aim for A, C, or E.")

        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 10.dp))

        SectionHeader("2. Chromatic Approach Notes")
        BodyText("A chromatic approach note is a note one half-step above or below your target that resolves into it. It creates tension that the target note then releases.")
        BodyText("Approach from below: if you want to land on E, play D# just before it. From above: play F just before E. Both give a jazz-flavoured pull toward the chord tone.")
        ItalicNote("Keep approach notes short (one beat or less) so the resolution hits on the strong beat.")

        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 10.dp))

        SectionHeader("3. Repeating Patterns (Sequences)")
        BodyText("Take a short 3–4 note melodic cell and repeat it starting on successive scale degrees. The pattern stays the same; only the starting pitch shifts.")
        BodyText("Example cell: up 3 notes (1–2–3), then down 1 (2). Applied to C major: C-D-E-D, D-E-F-E, E-F-G-F, etc. This gives your solo organisation without forcing you to think of new ideas every bar.")
        ItalicNote("Sequences make you sound prepared even when you are not. They're used constantly in Bach, bebop, and shred guitar.")

        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 10.dp))

        SectionHeader("4. Phrasing — Say Something, Then Breathe")
        BodyText("A phrase is a musical sentence. It has a beginning (tension), a middle (development), and an end (resolution or a deliberate cliffhanger). After each phrase, leave a rest — silence is not empty, it is part of the music.")
        BodyText("Good phrasing tips:")
        listOf(
            "Start a phrase on an upbeat (the 'and' of beat 4) to create forward momentum.",
            "Vary phrase lengths — a short punchy phrase followed by a long flowing one.",
            "Use dynamics: play some notes louder, some softer, even within a single phrase.",
            "Call and response: play an ascending, questioning phrase, then answer it with a descending, resolved one."
        ).forEach { bullet ->
            Row(modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)) {
                Text("• ", color = Color(0xFF90CAF9), style = MaterialTheme.typography.bodyMedium)
                Text(bullet, color = Color(0xFFCCCCCC), style = MaterialTheme.typography.bodyMedium)
            }
        }

        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 10.dp))

        SectionHeader("5. Pentatonic Scale on 12-Bar Blues")
        BodyText("The 12-bar blues uses three chords: I7, IV7, and V7 (e.g. A7, D7, E7 in the key of A). The minor pentatonic of the I chord (A minor pentatonic) works over all three — here's why:")
        listOf(
            "A min. pent. over A7:  R b3 P4 P5 b7 — all strong chord tones or accepted blue notes.",
            "A min. pent. over D7:  the A pent. notes sound as P5 b7 R M2 P4 over D — soft and passing.",
            "A min. pent. over E7:  the A pent. notes sound as P4 b6 b7 R b3 over E — classic blues grit."
        ).forEach { bullet ->
            Row(modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)) {
                Text("• ", color = Color(0xFF90CAF9), style = MaterialTheme.typography.bodyMedium)
                Text(bullet, color = Color(0xFFCCCCCC), style = MaterialTheme.typography.bodyMedium)
            }
        }
        BodyText("The box shape also helps: you never need to move your hand — all five notes fit under your fingers in one position. This lets you focus on feel and expression rather than finding notes.")
        ItalicNote("Once comfortable, try targeting the 3rd of each chord as you change (F# over D7, G# over E7) for a more sophisticated sound.")
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ArpeggiosContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        BodyText("An arpeggio plays the notes of a chord one at a time. Knowing the arpeggio shapes across the neck lets you outline the harmony precisely — every note you play is a chord tone.")

        for (arp in ARPEGGIO_PATTERNS) {
            HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
            SectionHeader(arp.name)
            Text(
                arp.intervals,
                color      = Color(0xFFFFCC80),
                style      = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                modifier   = Modifier.padding(bottom = 4.dp)
            )
            BodyText(arp.description)
            NeckDiagram(
                dots           = arp.dots,
                fretCount      = arp.fretCount,
                startFretLabel = 5,
                label          = "Root at fret 5 (A). Green = root, blue = chord tones.",
                modifier       = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

// ── Hub ──────────────────────────────────────────────────────────────────────

@Composable
private fun TutorialsHub(onSelect: (String) -> Unit) {
    val topics = listOf(
        "scales"     to "Scales",
        "cycle"      to "Cycle of Fifths",
        "improv"     to "Improvisation",
        "arpeggios"  to "Arpeggios"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Select a topic",
            color = Color(0xFF888888),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        topics.forEach { (key, label) ->
            Button(
                onClick  = { onSelect(key) },
                modifier = Modifier.fillMaxWidth(),
                colors   = ButtonDefaults.buttonColors(containerColor = Color(0xFF1F3A5F))
            ) {
                Text(label, style = MaterialTheme.typography.titleSmall)
            }
        }
    }
}

// ── TutorialsScreen ──────────────────────────────────────────────────────────

@Composable
fun TutorialsScreen(onDismiss: () -> Unit) {
    var selectedTopic by remember { mutableStateOf<String?>(null) }

    val title = when (selectedTopic) {
        "scales"    -> "Scales"
        "cycle"     -> "Cycle of Fifths"
        "improv"    -> "Improvisation"
        "arpeggios" -> "Arpeggios"
        else        -> "Tutorials"
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1F1F1F))
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (selectedTopic != null) {
                        TextButton(onClick = { selectedTopic = null }) { Text("← Back") }
                    }
                    Text(
                        title,
                        style  = MaterialTheme.typography.titleMedium,
                        color  = Color.White,
                        modifier = Modifier.weight(1f).padding(start = if (selectedTopic != null) 4.dp else 0.dp)
                    )
                    TextButton(onClick = onDismiss) { Text("Close") }
                }

                // Body
                when (selectedTopic) {
                    "scales"    -> ScalesContent()
                    "cycle"     -> CycleContent()
                    "improv"    -> ImprovisationContent()
                    "arpeggios" -> ArpeggiosContent()
                    else        -> TutorialsHub(onSelect = { selectedTopic = it })
                }
            }
        }
    }
}
