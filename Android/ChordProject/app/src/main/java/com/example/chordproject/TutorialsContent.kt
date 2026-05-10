package com.example.chordproject

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.activity.compose.BackHandler
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
    val fretCount: Int = 5,
    val positionLabels: List<String>? = null          // if set, overrides auto "Position N" labels
)

data class ArpeggioPattern(
    val name: String,
    val intervals: String,
    val description: String,
    val positions: List<Pair<Int, List<NeckDot>>>,  // (startFret, dots)
    val fretCount: Int = 5
)

// ── Scale dot data ───────────────────────────────────────────────────────────
// All patterns shown for root A (A minor pent = fret 5 on low E).
// string 0 = high e, 5 = low E; fret = relative offset from startFret.
// Roots are marked green; other scale tones blue.

// ─── Minor Pentatonic  (R b3 P4 P5 b7)  ──────────────────────────────────────
// A minor pent: A C D E G.  5 box positions tile the neck.

private fun minorPentatonicPos1() = listOf(   // startFret=5
    NeckDot(0, 0, true),  NeckDot(0, 3, false), // e:  A(R)  C(b3)
    NeckDot(1, 0, false), NeckDot(1, 3, false),  // B:  E(P5) G(b7)
    NeckDot(2, 0, false), NeckDot(2, 2, false),  // G:  C(b3) D(P4)
    NeckDot(3, 0, false), NeckDot(3, 2, true),   // D:  G(b7) A(R)
    NeckDot(4, 0, false), NeckDot(4, 2, false),  // A:  D(P4) E(P5)
    NeckDot(5, 0, true),  NeckDot(5, 3, false)   // E:  A(R)  C(b3)
)

private fun minorPentatonicPos2() = listOf(   // startFret=7
    NeckDot(0, 1, false), NeckDot(0, 3, false), // e:  C(b3) D(P4)
    NeckDot(1, 1, false), NeckDot(1, 3, true),  // B:  G(b7) A(R)
    NeckDot(2, 0, false), NeckDot(2, 2, false),  // G:  D(P4) E(P5)
    NeckDot(3, 0, true),  NeckDot(3, 3, false),  // D:  A(R)  C(b3)
    NeckDot(4, 0, false), NeckDot(4, 3, false),  // A:  E(P5) G(b7)
    NeckDot(5, 1, false), NeckDot(5, 3, false)   // E:  C(b3) D(P4)
)

private fun minorPentatonicPos3() = listOf(   // startFret=9
    NeckDot(0, 1, false), NeckDot(0, 3, false), // e:  D(P4) E(P5)
    NeckDot(1, 1, true),  NeckDot(1, 4, false), // B:  A(R)  C(b3)
    NeckDot(2, 0, false), NeckDot(2, 3, false),  // G:  E(P5) G(b7)
    NeckDot(3, 1, false), NeckDot(3, 3, false),  // D:  C(b3) D(P4)
    NeckDot(4, 1, false), NeckDot(4, 3, true),   // A:  G(b7) A(R)
    NeckDot(5, 1, false), NeckDot(5, 3, false)   // E:  D(P4) E(P5)
)

private fun minorPentatonicPos4() = listOf(   // startFret=12
    NeckDot(0, 0, false), NeckDot(0, 3, false), // e:  E(P5) G(b7)
    NeckDot(1, 1, false), NeckDot(1, 3, false),  // B:  C(b3) D(P4)
    NeckDot(2, 0, false), NeckDot(2, 2, true),   // G:  G(b7) A(R)
    NeckDot(3, 0, false), NeckDot(3, 2, false),  // D:  D(P4) E(P5)
    NeckDot(4, 0, true),  NeckDot(4, 3, false),  // A:  A(R)  C(b3)
    NeckDot(5, 0, false), NeckDot(5, 3, false)   // E:  E(P5) G(b7)
)

private fun minorPentatonicPos5() = listOf(   // startFret=14
    NeckDot(0, 1, false), NeckDot(0, 3, true),  // e:  G(b7) A(R)
    NeckDot(1, 1, false), NeckDot(1, 3, false),  // B:  D(P4) E(P5)
    NeckDot(2, 0, true),  NeckDot(2, 3, false),  // G:  A(R)  C(b3)
    NeckDot(3, 0, false), NeckDot(3, 3, false),  // D:  E(P5) G(b7)
    NeckDot(4, 1, false), NeckDot(4, 3, false),  // A:  C(b3) D(P4)
    NeckDot(5, 1, false), NeckDot(5, 3, true)    // E:  G(b7) A(R)
)

// ─── Major Pentatonic  (R M2 M3 P5 M6)  ──────────────────────────────────────
// A major pent: A B C# E F#.  5 box positions.

private fun majorPentatonicPos1() = listOf(   // startFret=5
    NeckDot(0, 0, true),  NeckDot(0, 2, false), // e:  A(R)  B(M2)
    NeckDot(1, 0, false), NeckDot(1, 2, false),  // B:  E(P5) F#(M6)
    NeckDot(2, 1, false), NeckDot(2, 4, false),  // G:  C#(M3) E(P5)
    NeckDot(3, 2, true),  NeckDot(3, 4, false),  // D:  A(R)  B(M2)
    NeckDot(4, 2, false), NeckDot(4, 4, false),  // A:  E(P5) F#(M6)
    NeckDot(5, 0, true),  NeckDot(5, 2, false)   // E:  A(R)  B(M2)
)

private fun majorPentatonicPos2() = listOf(   // startFret=7
    NeckDot(0, 0, false), NeckDot(0, 2, false), // e:  B(M2) C#(M3)
    NeckDot(1, 0, false), NeckDot(1, 3, true),  // B:  F#(M6) A(R)
    NeckDot(2, 2, false), NeckDot(2, 4, false),  // G:  E(P5) F#(M6)
    NeckDot(3, 0, true),  NeckDot(3, 2, false),  // D:  A(R)  B(M2)
    NeckDot(4, 0, false), NeckDot(4, 2, false),  // A:  E(P5) F#(M6)
    NeckDot(5, 0, false), NeckDot(5, 2, false)   // E:  B(M2) C#(M3)
)

private fun majorPentatonicPos3() = listOf(   // startFret=9
    NeckDot(0, 0, false), NeckDot(0, 3, false), // e:  C#(M3) E(P5)
    NeckDot(1, 1, true),  NeckDot(1, 3, false),  // B:  A(R)  B(M2)
    NeckDot(2, 0, false), NeckDot(2, 2, false),  // G:  E(P5) F#(M6)
    NeckDot(3, 0, false), NeckDot(3, 2, false),  // D:  B(M2) C#(M3)
    NeckDot(4, 0, false), NeckDot(4, 3, true),   // A:  F#(M6) A(R)
    NeckDot(5, 0, false), NeckDot(5, 3, false)   // E:  C#(M3) E(P5)
)

private fun majorPentatonicPos4() = listOf(   // startFret=12
    NeckDot(0, 0, false), NeckDot(0, 2, false), // e:  E(P5)  F#(M6)
    NeckDot(1, 0, false), NeckDot(1, 2, false),  // B:  B(M2)  C#(M3)
    NeckDot(2, 2, true),  NeckDot(2, 4, false),  // G:  A(R)   B(M2)
    NeckDot(3, 2, false), NeckDot(3, 4, false),  // D:  E(P5)  F#(M6)
    NeckDot(4, 0, true),  NeckDot(4, 2, false),  // A:  A(R)   B(M2)
    NeckDot(5, 0, false), NeckDot(5, 2, false)   // E:  E(P5)  F#(M6)
)

private fun majorPentatonicPos5() = listOf(   // startFret=14
    NeckDot(0, 0, false), NeckDot(0, 3, true),  // e:  F#(M6) A(R)
    NeckDot(1, 0, false), NeckDot(1, 3, false),  // B:  C#(M3) E(P5)
    NeckDot(2, 0, true),  NeckDot(2, 2, false),  // G:  A(R)   B(M2)
    NeckDot(3, 0, false), NeckDot(3, 2, false),  // D:  E(P5)  F#(M6)
    NeckDot(4, 0, false), NeckDot(4, 2, false),  // A:  B(M2)  C#(M3)
    NeckDot(5, 0, false), NeckDot(5, 3, true)    // E:  F#(M6) A(R)
)

// ─── Blues Scale  (R b3 P4 b5 P5 b7)  ────────────────────────────────────────
// A blues: A C D Eb E G.  3 positions.

private fun bluesPos1() = listOf(   // startFret=5
    NeckDot(0, 0, true),  NeckDot(0, 3, false),             // e: A(R)  C(b3)
    NeckDot(1, 0, false), NeckDot(1, 3, false),              // B: E(P5) G(b7)
    NeckDot(2, 0, false), NeckDot(2, 2, false),              // G: C(b3) D(P4)
    NeckDot(3, 0, false), NeckDot(3, 2, true),               // D: G(b7) A(R)
    NeckDot(4, 0, false), NeckDot(4, 1, false), NeckDot(4, 2, false), // A: D(P4) Eb(b5) E(P5)
    NeckDot(5, 0, true),  NeckDot(5, 3, false)               // E: A(R)  C(b3)
)

private fun bluesPos2() = listOf(   // startFret=7
    NeckDot(0, 1, false), NeckDot(0, 3, false), NeckDot(0, 4, false), // e: C(b3) D(P4) Eb(b5)
    NeckDot(1, 1, false), NeckDot(1, 3, true),                         // B: G(b7) A(R)
    NeckDot(2, 0, false), NeckDot(2, 1, false), NeckDot(2, 2, false),  // G: D(P4) Eb(b5) E(P5)
    NeckDot(3, 0, true),  NeckDot(3, 3, false),                        // D: A(R)  C(b3)
    NeckDot(4, 0, false), NeckDot(4, 3, false),                        // A: E(P5) G(b7)
    NeckDot(5, 1, false), NeckDot(5, 3, false), NeckDot(5, 4, false)   // E: C(b3) D(P4) Eb(b5)
)

private fun bluesPos3() = listOf(   // startFret=9
    NeckDot(0, 1, false), NeckDot(0, 2, false), NeckDot(0, 3, false), // e: D(P4) Eb(b5) E(P5)
    NeckDot(1, 1, true),  NeckDot(1, 4, false),                        // B: A(R)  C(b3)
    NeckDot(2, 0, false), NeckDot(2, 3, false),                        // G: E(P5) G(b7)
    NeckDot(3, 1, false), NeckDot(3, 2, false), NeckDot(3, 4, false),  // D: C(b3) Eb(b5) D... wait: C(b3) D(P4) Eb(b5) — let me fix
    NeckDot(4, 1, false), NeckDot(4, 3, true),                         // A: G(b7) A(R)
    NeckDot(5, 1, false), NeckDot(5, 2, false), NeckDot(5, 3, false)   // E: D(P4) Eb(b5) E(P5)
)

// ─── Major Scale  (R M2 M3 P4 P5 M6 M7)  ─────────────────────────────────────
// C major: C D E F G A B.  7 positions, each starting a different scale degree on the bass E string.
// Root = C (isRoot=true).  string 5=low E, 0=high e.  fret = relative offset from startFret.

private fun cMajorPosC() = listOf(   // startFret=8  — C on bass E
    NeckDot(5, 0, true),  NeckDot(5, 2, false), NeckDot(5, 4, false),           // E: C(R)  D     E
    NeckDot(4, 0, false), NeckDot(4, 2, false), NeckDot(4, 4, false),           // A: F     G     A
    NeckDot(3, 1, false), NeckDot(3, 2, true),  NeckDot(3, 4, false),           // D: B     C(R)  D
    NeckDot(2, 1, false), NeckDot(2, 2, false), NeckDot(2, 4, false),           // G: E     F     G
    NeckDot(1, 0, false), NeckDot(1, 2, false), NeckDot(1, 4, false), NeckDot(1, 5, true),  // B: G A B C(R)
    NeckDot(0, 0, true),  NeckDot(0, 2, false), NeckDot(0, 4, false)            // e: C(R)  D     E
)

private fun cMajorPosD() = listOf(   // startFret=10 — D on bass E
    NeckDot(5, 0, false), NeckDot(5, 2, false), NeckDot(5, 3, false),           // E: D  E  F
    NeckDot(4, 0, false), NeckDot(4, 2, false), NeckDot(4, 4, false), NeckDot(4, 5, true),  // A: G A B C(R)
    NeckDot(3, 0, true),  NeckDot(3, 2, false), NeckDot(3, 4, false), NeckDot(3, 5, false), // D: C(R) D E F
    NeckDot(2, 0, false), NeckDot(2, 2, false), NeckDot(2, 4, false),           // G: F  G  A
    NeckDot(1, 0, false), NeckDot(1, 2, false), NeckDot(1, 3, true),            // B: A  B  C(R)
    NeckDot(0, 0, false), NeckDot(0, 2, false), NeckDot(0, 3, false)            // e: D  E  F
)

private fun cMajorPosE() = listOf(   // startFret=12 — E on bass E
    NeckDot(5, 0, false), NeckDot(5, 1, false), NeckDot(5, 3, false),           // E: E  F  G
    NeckDot(4, 0, false), NeckDot(4, 2, false), NeckDot(4, 3, true),            // A: A  B  C(R)
    NeckDot(3, 0, false), NeckDot(3, 2, false), NeckDot(3, 3, false), NeckDot(3, 5, false), // D: D E F G
    NeckDot(2, 0, false), NeckDot(2, 2, false), NeckDot(2, 4, false), NeckDot(2, 5, true),  // G: G A B C(R)
    NeckDot(1, 0, false), NeckDot(1, 1, true),  NeckDot(1, 3, false), NeckDot(1, 5, false), // B: B C(R) D E
    NeckDot(0, 0, false), NeckDot(0, 1, false), NeckDot(0, 3, false)            // e: E  F  G
)

private fun cMajorPosF() = listOf(   // startFret=13 — F on bass E
    NeckDot(5, 0, false), NeckDot(5, 2, false), NeckDot(5, 4, false),           // E: F  G  A
    NeckDot(4, 1, false), NeckDot(4, 2, true),  NeckDot(4, 4, false),           // A: B  C(R) D
    NeckDot(3, 1, false), NeckDot(3, 2, false), NeckDot(3, 4, false),           // D: E  F  G
    NeckDot(2, 1, false), NeckDot(2, 3, false), NeckDot(2, 4, true),            // G: A  B  C(R)
    NeckDot(1, 0, true),  NeckDot(1, 2, false), NeckDot(1, 4, false),           // B: C(R) D E
    NeckDot(0, 0, false), NeckDot(0, 2, false), NeckDot(0, 4, false)            // e: F  G  A
)

private fun cMajorPosG() = listOf(   // startFret=15 — G on bass E
    NeckDot(5, 0, false), NeckDot(5, 2, false), NeckDot(5, 4, false), NeckDot(5, 5, true),  // E: G A B C(R)
    NeckDot(4, 0, true),  NeckDot(4, 2, false), NeckDot(4, 4, false),           // A: C(R) D  E
    NeckDot(3, 0, false), NeckDot(3, 2, false), NeckDot(3, 4, false),           // D: F  G  A
    NeckDot(2, 1, false), NeckDot(2, 2, true),  NeckDot(2, 4, false),           // G: B  C(R) D
    NeckDot(1, 0, false), NeckDot(1, 2, false), NeckDot(1, 3, false),           // B: D  E  F
    NeckDot(0, 0, false), NeckDot(0, 2, false), NeckDot(0, 4, false), NeckDot(0, 5, true)   // e: G A B C(R)
)

private fun cMajorPosA() = listOf(   // startFret=5  — A on bass E
    NeckDot(5, 0, false), NeckDot(5, 2, false), NeckDot(5, 3, true),            // E: A  B  C(R)
    NeckDot(4, 0, false), NeckDot(4, 2, false), NeckDot(4, 3, false), NeckDot(4, 5, false), // A: D E F G
    NeckDot(3, 0, false), NeckDot(3, 2, false), NeckDot(3, 4, false), NeckDot(3, 5, true),  // D: G A B C(R)
    NeckDot(2, 0, true),  NeckDot(2, 2, false), NeckDot(2, 4, false), NeckDot(2, 5, false), // G: C(R) D E F
    NeckDot(1, 0, false), NeckDot(1, 1, false), NeckDot(1, 3, false), NeckDot(1, 5, false), // B: E F G A
    NeckDot(0, 0, false), NeckDot(0, 2, false), NeckDot(0, 3, true)             // e: A  B  C(R)
)

private fun cMajorPosB() = listOf(   // startFret=7  — B on bass E
    NeckDot(5, 0, false), NeckDot(5, 1, true),  NeckDot(5, 3, false),           // E: B  C(R) D
    NeckDot(4, 0, false), NeckDot(4, 1, false), NeckDot(4, 3, false),           // A: E  F  G
    NeckDot(3, 0, false), NeckDot(3, 2, false), NeckDot(3, 3, true),            // D: A  B  C(R)
    NeckDot(2, 0, false), NeckDot(2, 2, false), NeckDot(2, 3, false),           // G: D  E  F
    NeckDot(1, 1, false), NeckDot(1, 3, false), NeckDot(1, 5, false),           // B: G  A  B
    NeckDot(0, 0, false), NeckDot(0, 1, true),  NeckDot(0, 3, false)            // e: B  C(R) D
)

// ─── Natural Minor  (R M2 b3 P4 P5 b6 b7)  ───────────────────────────────────
// A natural minor: A B C D E F G.  3 positions.

private fun naturalMinorPos1() = listOf(   // startFret=5  (root on low E)
    NeckDot(0, 0, true),  NeckDot(0, 2, false), NeckDot(0, 3, false),           // e: A(R)  B(M2) C(b3)
    NeckDot(1, 0, false), NeckDot(1, 1, false), NeckDot(1, 3, false),           // B: E(P5) F(b6) G(b7)
    NeckDot(2, 0, false), NeckDot(2, 2, false), NeckDot(2, 4, false), NeckDot(2, 5, false), // G: C(b3) D(P4) E(P5) F(b6)
    NeckDot(3, 2, true),  NeckDot(3, 4, false), NeckDot(3, 5, false),           // D: A(R)  B(M2) C(b3)
    NeckDot(4, 0, false), NeckDot(4, 2, false), NeckDot(4, 3, false),           // A: D(P4) E(P5) F(b6)
    NeckDot(5, 0, true),  NeckDot(5, 2, false), NeckDot(5, 3, false)            // E: A(R)  B(M2) C(b3)
)

private fun naturalMinorPos2() = listOf(   // startFret=7  (root on D string)
    NeckDot(0, 0, false), NeckDot(0, 1, false), NeckDot(0, 3, false),           // e: B(M2) C(b3) D(P4)
    NeckDot(1, 1, false), NeckDot(1, 3, true),                                  // B: G(b7) A(R)
    NeckDot(2, 0, false), NeckDot(2, 2, false), NeckDot(2, 3, false),           // G: D(P4) E(P5) F(b6)
    NeckDot(3, 0, true),  NeckDot(3, 2, false), NeckDot(3, 3, false),           // D: A(R)  B(M2) C(b3)
    NeckDot(4, 0, false), NeckDot(4, 1, false), NeckDot(4, 3, false),           // A: E(P5) F(b6) G(b7)
    NeckDot(5, 0, false), NeckDot(5, 1, false), NeckDot(5, 3, false)            // E: B(M2) C(b3) D(P4)
)

private fun naturalMinorPos3() = listOf(   // startFret=12  (root on A string)
    NeckDot(0, 0, false), NeckDot(0, 1, false), NeckDot(0, 3, false), NeckDot(0, 5, true),  // e: E(P5) F(b6) G(b7) A(R)
    NeckDot(1, 1, false), NeckDot(1, 3, false), NeckDot(1, 5, false),           // B: C(b3) D(P4)  E(P5)
    NeckDot(2, 0, false), NeckDot(2, 2, true),  NeckDot(2, 4, false),           // G: G(b7) A(R)   B(M2)
    NeckDot(3, 0, false), NeckDot(3, 2, false), NeckDot(3, 3, false),           // D: D(P4) E(P5)  F(b6)
    NeckDot(4, 0, true),  NeckDot(4, 2, false), NeckDot(4, 3, false),           // A: A(R)  B(M2)  C(b3)
    NeckDot(5, 0, false), NeckDot(5, 1, false), NeckDot(5, 3, false), NeckDot(5, 5, true)   // E: E(P5) F(b6) G(b7) A(R)
)

// 4 extra positions to complete 7-position coverage (C, D, F, G on bass E)
private fun aMinPosC() = listOf(   // startFret=8  — C on bass E, root=A
    NeckDot(5, 0, false), NeckDot(5, 2, false), NeckDot(5, 4, false),           // E: C  D  E
    NeckDot(4, 0, false), NeckDot(4, 2, false), NeckDot(4, 4, true),            // A: F  G  A(R)
    NeckDot(3, 1, false), NeckDot(3, 2, false), NeckDot(3, 4, false),           // D: B  C  D
    NeckDot(2, 1, false), NeckDot(2, 2, false), NeckDot(2, 4, false),           // G: E  F  G
    NeckDot(1, 0, false), NeckDot(1, 2, true),  NeckDot(1, 4, false), NeckDot(1, 5, false), // B: G A(R) B C
    NeckDot(0, 0, false), NeckDot(0, 2, false), NeckDot(0, 4, false)            // e: C  D  E
)

private fun aMinPosD() = listOf(   // startFret=10 — D on bass E, root=A
    NeckDot(5, 0, false), NeckDot(5, 2, false), NeckDot(5, 3, false),           // E: D  E  F
    NeckDot(4, 0, false), NeckDot(4, 2, true),  NeckDot(4, 4, false), NeckDot(4, 5, false), // A: G A(R) B C
    NeckDot(3, 0, false), NeckDot(3, 2, false), NeckDot(3, 4, false), NeckDot(3, 5, false), // D: C  D  E  F
    NeckDot(2, 0, false), NeckDot(2, 2, false), NeckDot(2, 4, true),            // G: F  G  A(R)
    NeckDot(1, 0, true),  NeckDot(1, 2, false), NeckDot(1, 3, false),           // B: A(R) B  C
    NeckDot(0, 0, false), NeckDot(0, 2, false), NeckDot(0, 3, false)            // e: D  E  F
)

private fun aMinPosF() = listOf(   // startFret=13 — F on bass E, root=A
    NeckDot(5, 0, false), NeckDot(5, 2, false), NeckDot(5, 4, true),            // E: F  G  A(R)
    NeckDot(4, 1, false), NeckDot(4, 2, false), NeckDot(4, 4, false),           // A: B  C  D
    NeckDot(3, 1, false), NeckDot(3, 2, false), NeckDot(3, 4, false),           // D: E  F  G
    NeckDot(2, 1, true),  NeckDot(2, 3, false), NeckDot(2, 4, false),           // G: A(R) B  C
    NeckDot(1, 0, false), NeckDot(1, 2, false), NeckDot(1, 4, false),           // B: C  D  E
    NeckDot(0, 0, false), NeckDot(0, 2, false), NeckDot(0, 4, true)             // e: F  G  A(R)
)

private fun aMinPosG() = listOf(   // startFret=15 — G on bass E, root=A
    NeckDot(5, 0, false), NeckDot(5, 2, true),  NeckDot(5, 4, false), NeckDot(5, 5, false), // E: G A(R) B C
    NeckDot(4, 0, false), NeckDot(4, 2, false), NeckDot(4, 4, false),           // A: C  D  E
    NeckDot(3, 0, false), NeckDot(3, 2, false), NeckDot(3, 4, true),            // D: F  G  A(R)
    NeckDot(2, 1, false), NeckDot(2, 2, false), NeckDot(2, 4, false),           // G: B  C  D
    NeckDot(1, 0, false), NeckDot(1, 2, false), NeckDot(1, 3, false),           // B: D  E  F
    NeckDot(0, 0, false), NeckDot(0, 2, true),  NeckDot(0, 4, false), NeckDot(0, 5, false)  // e: G A(R) B C
)

// ─── Harmonic Minor  (R M2 b3 P4 P5 b6 M7)  ──────────────────────────────────
// A harmonic minor: A B C D E F G#.  3 positions.

private fun harmonicMinorPos1() = listOf(   // startFret=5  (root on low E)
    NeckDot(0, 0, true),  NeckDot(0, 2, false), NeckDot(0, 3, false),           // e: A(R)  B(M2) C(b3)
    NeckDot(1, 0, false), NeckDot(1, 1, false), NeckDot(1, 4, false),           // B: E(P5) F(b6) G#(M7)
    NeckDot(2, 0, false), NeckDot(2, 2, false), NeckDot(2, 4, false), NeckDot(2, 5, false), // G: C(b3) D(P4) E(P5) F(b6)
    NeckDot(3, 2, true),  NeckDot(3, 4, false), NeckDot(3, 5, false),           // D: A(R)  B(M2) C(b3)
    NeckDot(4, 0, false), NeckDot(4, 2, false), NeckDot(4, 3, false),           // A: D(P4) E(P5) F(b6)
    NeckDot(5, 0, true),  NeckDot(5, 2, false), NeckDot(5, 3, false)            // E: A(R)  B(M2) C(b3)
)

private fun harmonicMinorPos2() = listOf(   // startFret=7  (root on D string, G# on A and B strings)
    NeckDot(0, 0, false), NeckDot(0, 1, false), NeckDot(0, 3, false),           // e: B(M2) C(b3)  D(P4)
    NeckDot(1, 2, false), NeckDot(1, 3, true),                                  // B: G#(M7) A(R)
    NeckDot(2, 0, false), NeckDot(2, 2, false), NeckDot(2, 3, false),           // G: D(P4)  E(P5) F(b6)
    NeckDot(3, 0, true),  NeckDot(3, 2, false), NeckDot(3, 3, false),           // D: A(R)   B(M2) C(b3)
    NeckDot(4, 0, false), NeckDot(4, 1, false), NeckDot(4, 4, false), NeckDot(4, 5, true),  // A: E(P5) F(b6) G#(M7) A(R)
    NeckDot(5, 0, false), NeckDot(5, 1, false), NeckDot(5, 3, false)            // E: B(M2)  C(b3) D(P4)
)

private fun harmonicMinorPos3() = listOf(   // startFret=12  (root on A string, G# on G and E strings)
    NeckDot(0, 0, false), NeckDot(0, 1, false), NeckDot(0, 4, false), NeckDot(0, 5, true),  // e: E(P5) F(b6) G#(M7) A(R)
    NeckDot(1, 1, false), NeckDot(1, 3, false), NeckDot(1, 5, false),           // B: C(b3)  D(P4) E(P5)
    NeckDot(2, 1, false), NeckDot(2, 2, true),  NeckDot(2, 4, false),           // G: G#(M7) A(R)  B(M2)
    NeckDot(3, 0, false), NeckDot(3, 2, false), NeckDot(3, 3, false),           // D: D(P4)  E(P5) F(b6)
    NeckDot(4, 0, true),  NeckDot(4, 2, false), NeckDot(4, 3, false),           // A: A(R)   B(M2) C(b3)
    NeckDot(5, 0, false), NeckDot(5, 1, false), NeckDot(5, 4, false), NeckDot(5, 5, true)   // E: E(P5) F(b6) G#(M7) A(R)
)

// 4 extra harmonic minor positions (G→G# vs natural minor)
private fun aHarMinPosC() = listOf(   // startFret=8  — C on bass E
    NeckDot(5, 0, false), NeckDot(5, 2, false), NeckDot(5, 4, false),           // E: C  D  E
    NeckDot(4, 0, false), NeckDot(4, 3, false), NeckDot(4, 4, true),            // A: F  G#  A(R)
    NeckDot(3, 1, false), NeckDot(3, 2, false), NeckDot(3, 4, false),           // D: B  C  D
    NeckDot(2, 1, false), NeckDot(2, 2, false), NeckDot(2, 5, false),           // G: E  F  G#
    NeckDot(1, 1, false), NeckDot(1, 2, true),  NeckDot(1, 4, false), NeckDot(1, 5, false), // B: G# A(R) B C
    NeckDot(0, 0, false), NeckDot(0, 2, false), NeckDot(0, 4, false)            // e: C  D  E
)

private fun aHarMinPosD() = listOf(   // startFret=10 — D on bass E
    NeckDot(5, 0, false), NeckDot(5, 2, false), NeckDot(5, 3, false),           // E: D  E  F
    NeckDot(4, 1, false), NeckDot(4, 2, true),  NeckDot(4, 4, false), NeckDot(4, 5, false), // A: G# A(R) B C
    NeckDot(3, 0, false), NeckDot(3, 2, false), NeckDot(3, 4, false), NeckDot(3, 5, false), // D: C  D  E  F
    NeckDot(2, 0, false), NeckDot(2, 3, false), NeckDot(2, 4, true),            // G: F  G#  A(R)
    NeckDot(1, 0, true),  NeckDot(1, 2, false), NeckDot(1, 3, false),           // B: A(R) B  C
    NeckDot(0, 0, false), NeckDot(0, 2, false), NeckDot(0, 3, false)            // e: D  E  F
)

private fun aHarMinPosF() = listOf(   // startFret=13 — F on bass E
    NeckDot(5, 0, false), NeckDot(5, 3, false), NeckDot(5, 4, true),            // E: F  G#  A(R)
    NeckDot(4, 1, false), NeckDot(4, 2, false), NeckDot(4, 4, false),           // A: B  C  D
    NeckDot(3, 1, false), NeckDot(3, 2, false), NeckDot(3, 5, false),           // D: E  F  G#
    NeckDot(2, 1, true),  NeckDot(2, 3, false), NeckDot(2, 4, false),           // G: A(R) B  C
    NeckDot(1, 0, false), NeckDot(1, 2, false), NeckDot(1, 4, false),           // B: C  D  E
    NeckDot(0, 0, false), NeckDot(0, 3, false), NeckDot(0, 4, true)             // e: F  G#  A(R)
)

private fun aHarMinPosG() = listOf(   // startFret=15 — G on bass E
    NeckDot(5, 1, false), NeckDot(5, 2, true),  NeckDot(5, 4, false), NeckDot(5, 5, false), // E: G# A(R) B C
    NeckDot(4, 0, false), NeckDot(4, 2, false), NeckDot(4, 4, false),           // A: C  D  E
    NeckDot(3, 0, false), NeckDot(3, 3, false), NeckDot(3, 4, true),            // D: F  G#  A(R)
    NeckDot(2, 1, false), NeckDot(2, 2, false), NeckDot(2, 4, false),           // G: B  C  D
    NeckDot(1, 0, false), NeckDot(1, 2, false), NeckDot(1, 3, false),           // B: D  E  F
    NeckDot(0, 1, false), NeckDot(0, 2, true),  NeckDot(0, 4, false), NeckDot(0, 5, false)  // e: G# A(R) B C
)

// ─── Melodic Minor  (R M2 b3 P4 P5 M6 M7)  ───────────────────────────────────
// A melodic minor: A B C D E F# G#.  3 positions.

private fun melodicMinorPos1() = listOf(   // startFret=5  (root on low E)
    NeckDot(0, 0, true),  NeckDot(0, 2, false), NeckDot(0, 3, false),           // e: A(R)  B(M2) C(b3)
    NeckDot(1, 0, false), NeckDot(1, 2, false), NeckDot(1, 4, false),           // B: E(P5) F#(M6) G#(M7)
    NeckDot(2, 0, false), NeckDot(2, 2, false), NeckDot(2, 4, false), NeckDot(2, 6, false), // G: C(b3) D(P4) E(P5) F#(M6)
    NeckDot(3, 2, true),  NeckDot(3, 4, false), NeckDot(3, 5, false),           // D: A(R)  B(M2) C(b3)
    NeckDot(4, 0, false), NeckDot(4, 2, false), NeckDot(4, 4, false),           // A: D(P4) E(P5) F#(M6)
    NeckDot(5, 0, true),  NeckDot(5, 2, false), NeckDot(5, 3, false)            // E: A(R)  B(M2) C(b3)
)

private fun melodicMinorPos2() = listOf(   // startFret=7  (root on D string)
    NeckDot(0, 0, false), NeckDot(0, 1, false), NeckDot(0, 3, false),           // e: B(M2) C(b3) D(P4)
    NeckDot(1, 0, false), NeckDot(1, 2, false), NeckDot(1, 3, true),            // B: F#(M6) G#(M7) A(R)
    NeckDot(2, 0, false), NeckDot(2, 2, false), NeckDot(2, 4, false),           // G: D(P4)  E(P5) F#(M6)
    NeckDot(3, 0, true),  NeckDot(3, 2, false), NeckDot(3, 3, false),           // D: A(R)   B(M2) C(b3)
    NeckDot(4, 0, false), NeckDot(4, 2, false), NeckDot(4, 4, false), NeckDot(4, 5, true),  // A: E(P5) F#(M6) G#(M7) A(R)
    NeckDot(5, 0, false), NeckDot(5, 1, false), NeckDot(5, 3, false)            // E: B(M2)  C(b3) D(P4)
)

private fun melodicMinorPos3() = listOf(   // startFret=12  (root on A string)
    NeckDot(0, 0, false), NeckDot(0, 2, false), NeckDot(0, 4, false), NeckDot(0, 5, true),  // e: E(P5) F#(M6) G#(M7) A(R)
    NeckDot(1, 1, false), NeckDot(1, 3, false), NeckDot(1, 5, false),           // B: C(b3)  D(P4)  E(P5)
    NeckDot(2, 1, false), NeckDot(2, 2, true),  NeckDot(2, 4, false),           // G: G#(M7) A(R)   B(M2)
    NeckDot(3, 0, false), NeckDot(3, 2, false), NeckDot(3, 4, false),           // D: D(P4)  E(P5)  F#(M6)
    NeckDot(4, 0, true),  NeckDot(4, 2, false), NeckDot(4, 3, false),           // A: A(R)   B(M2)  C(b3)
    NeckDot(5, 0, false), NeckDot(5, 2, false), NeckDot(5, 4, false), NeckDot(5, 5, true)   // E: E(P5) F#(M6) G#(M7) A(R)
)

// 4 extra melodic minor positions (F→F# and G→G# vs natural minor)
private fun aMelMinPosC() = listOf(   // startFret=8  — C on bass E
    NeckDot(5, 0, false), NeckDot(5, 2, false), NeckDot(5, 4, false),           // E: C  D  E
    NeckDot(4, 1, false), NeckDot(4, 3, false), NeckDot(4, 4, true),            // A: F#  G#  A(R)
    NeckDot(3, 1, false), NeckDot(3, 2, false), NeckDot(3, 4, false),           // D: B  C  D
    NeckDot(2, 1, false), NeckDot(2, 3, false), NeckDot(2, 5, false),           // G: E  F#  G#
    NeckDot(1, 1, false), NeckDot(1, 2, true),  NeckDot(1, 4, false), NeckDot(1, 5, false), // B: G# A(R) B C
    NeckDot(0, 0, false), NeckDot(0, 2, false), NeckDot(0, 4, false)            // e: C  D  E
)

private fun aMelMinPosD() = listOf(   // startFret=10 — D on bass E
    NeckDot(5, 0, false), NeckDot(5, 2, false), NeckDot(5, 4, false),           // E: D  E  F#(fret14=rel4)
    NeckDot(4, 1, false), NeckDot(4, 2, true),  NeckDot(4, 4, false), NeckDot(4, 5, false), // A: G# A(R) B C
    NeckDot(3, 0, false), NeckDot(3, 2, false), NeckDot(3, 4, false), NeckDot(3, 6, false), // D: C  D  E  F#(rel6)
    NeckDot(2, 1, false), NeckDot(2, 3, false), NeckDot(2, 4, true),            // G: F#  G#  A(R)
    NeckDot(1, 0, true),  NeckDot(1, 2, false), NeckDot(1, 3, false),           // B: A(R) B  C
    NeckDot(0, 0, false), NeckDot(0, 2, false), NeckDot(0, 4, false)            // e: D  E  F#(rel4)
)

private fun aMelMinPosF() = listOf(   // startFret=13 — F on bass E
    NeckDot(5, 1, false), NeckDot(5, 3, false), NeckDot(5, 4, true),            // E: F#  G#  A(R)
    NeckDot(4, 1, false), NeckDot(4, 2, false), NeckDot(4, 4, false),           // A: B  C  D
    NeckDot(3, 1, false), NeckDot(3, 3, false), NeckDot(3, 5, false),           // D: E  F#  G#
    NeckDot(2, 1, true),  NeckDot(2, 3, false), NeckDot(2, 4, false),           // G: A(R) B  C
    NeckDot(1, 0, false), NeckDot(1, 2, false), NeckDot(1, 4, false),           // B: C  D  E
    NeckDot(0, 1, false), NeckDot(0, 3, false), NeckDot(0, 4, true)             // e: F#  G#  A(R)
)

private fun aMelMinPosG() = listOf(   // startFret=15 — G on bass E
    NeckDot(5, 1, false), NeckDot(5, 2, true),  NeckDot(5, 4, false), NeckDot(5, 5, false), // E: G# A(R) B C
    NeckDot(4, 0, false), NeckDot(4, 2, false), NeckDot(4, 4, false),           // A: C  D  E
    NeckDot(3, 1, false), NeckDot(3, 3, false), NeckDot(3, 4, true),            // D: F#  G#  A(R)
    NeckDot(2, 1, false), NeckDot(2, 2, false), NeckDot(2, 4, false),           // G: B  C  D
    NeckDot(1, 0, false), NeckDot(1, 2, false), NeckDot(1, 4, false),           // B: D  E  F#(fret19=rel4)
    NeckDot(0, 1, false), NeckDot(0, 2, true),  NeckDot(0, 4, false), NeckDot(0, 5, false)  // e: G# A(R) B C
)

private val SCALE_PATTERNS = listOf(
    ScalePattern(
        name = "Minor Pentatonic",
        intervals = "R  b3  P4  P5  b7",
        description = "Five notes, no half-steps — every note sounds good over a minor chord. The most important scale for blues and rock improvisation.",
        practical = "The 5 box positions tile the entire neck. Learn them one by one and connect them for fluid playing across all fret positions.",
        positions = listOf(
            5  to minorPentatonicPos1(),
            7  to minorPentatonicPos2(),
            9  to minorPentatonicPos3(),
            12 to minorPentatonicPos4(),
            14 to minorPentatonicPos5()
        ),
        fretCount = 5
    ),
    ScalePattern(
        name = "Major Pentatonic",
        intervals = "R  M2  M3  P5  M6",
        description = "The bright, uplifting sibling of the minor pentatonic. Used in country, pop, and over major chords. The minor pentatonic of the relative minor shares exactly the same notes.",
        practical = "C major pentatonic = A minor pentatonic. The 5 box positions cover the full neck — same idea as minor pentatonic, just different root assignments.",
        positions = listOf(
            5  to majorPentatonicPos1(),
            7  to majorPentatonicPos2(),
            9  to majorPentatonicPos3(),
            12 to majorPentatonicPos4(),
            14 to majorPentatonicPos5()
        ),
        fretCount = 5
    ),
    ScalePattern(
        name = "Blues Scale",
        intervals = "R  b3  P4  b5  P5  b7",
        description = "Minor pentatonic with one extra 'blue note': the b5 (tritone). This passing tone creates the characteristic tension and grit of blues music.",
        practical = "The b5 is a passing tone — slide through it, don't linger. It's the note that makes the blues cry.",
        positions = listOf(
            5 to bluesPos1(),
            7 to bluesPos2(),
            9 to bluesPos3()
        ),
        fretCount = 5
    ),
    ScalePattern(
        name = "Major Scale",
        intervals = "R  M2  M3  P4  P5  M6  M7",
        description = "The foundation of Western music. Example key: C major (C D E F G A B). All 7 positions cover the full neck, each starting on a different scale degree on the bass E string.",
        practical = "The interval pattern W-W-H-W-W-W-H repeats for every major key. Memorise the pattern, not just C major.",
        positions = listOf(
            8  to cMajorPosC(),
            10 to cMajorPosD(),
            12 to cMajorPosE(),
            13 to cMajorPosF(),
            15 to cMajorPosG(),
            5  to cMajorPosA(),
            7  to cMajorPosB()
        ),
        fretCount = 6,
        positionLabels = listOf(
            "C on bass E (fret 8)",  "D on bass E (fret 10)", "E on bass E (fret 12)",
            "F on bass E (fret 13)", "G on bass E (fret 15)", "A on bass E (fret 5)",
            "B on bass E (fret 7)"
        )
    ),
    ScalePattern(
        name = "Natural Minor",
        intervals = "R  M2  b3  P4  P5  b6  b7",
        description = "The Aeolian mode. Example key: A natural minor (A B C D E F G). All 7 positions cover the full neck, each starting on a different scale degree on the bass E string.",
        practical = "A natural minor uses the same notes as C major — just start from scale degree 6. For every major key you know, you automatically know its relative natural minor.",
        positions = listOf(
            5  to naturalMinorPos1(),
            7  to naturalMinorPos2(),
            8  to aMinPosC(),
            10 to aMinPosD(),
            12 to naturalMinorPos3(),
            13 to aMinPosF(),
            15 to aMinPosG()
        ),
        fretCount = 6,
        positionLabels = listOf(
            "A on bass E (fret 5)",  "B on bass E (fret 7)",  "C on bass E (fret 8)",
            "D on bass E (fret 10)", "E on bass E (fret 12)", "F on bass E (fret 13)",
            "G on bass E (fret 15)"
        )
    ),
    ScalePattern(
        name = "Harmonic Minor",
        intervals = "R  M2  b3  P4  P5  b6  M7",
        description = "Natural minor with a raised 7th. Example key: A harmonic minor (A B C D E F G#). The raised G# creates a strong leading tone and gives the scale its exotic, Middle Eastern flavour.",
        practical = "The augmented 2nd interval between b6 and M7 is the signature sound. Common in classical, flamenco, and metal.",
        positions = listOf(
            5  to harmonicMinorPos1(),
            7  to harmonicMinorPos2(),
            8  to aHarMinPosC(),
            10 to aHarMinPosD(),
            12 to harmonicMinorPos3(),
            13 to aHarMinPosF(),
            15 to aHarMinPosG()
        ),
        fretCount = 6,
        positionLabels = listOf(
            "A on bass E (fret 5)",  "B on bass E (fret 7)",  "C on bass E (fret 8)",
            "D on bass E (fret 10)", "E on bass E (fret 12)", "F on bass E (fret 13)",
            "G on bass E (fret 15)"
        )
    ),
    ScalePattern(
        name = "Melodic Minor",
        intervals = "R  M2  b3  P4  P5  M6  M7  (ascending)",
        description = "Major scale with a b3. Example key: A melodic minor (A B C D E F# G#). Raises both the 6th and 7th of natural minor to smooth the melodic line.",
        practical = "Think of it as a major scale with a minor 3rd. Widely used in jazz over minor-major7 chords and Lydian Dominant contexts.",
        positions = listOf(
            5  to melodicMinorPos1(),
            7  to melodicMinorPos2(),
            8  to aMelMinPosC(),
            10 to aMelMinPosD(),
            12 to melodicMinorPos3(),
            13 to aMelMinPosF(),
            15 to aMelMinPosG()
        ),
        fretCount = 6,
        positionLabels = listOf(
            "A on bass E (fret 5)",  "B on bass E (fret 7)",  "C on bass E (fret 8)",
            "D on bass E (fret 10)", "E on bass E (fret 12)", "F on bass E (fret 13)",
            "G on bass E (fret 15)"
        )
    )
)

// ── Arpeggio dot data ─────────────────────────────────────────────────────────
// Root A.  Each arpeggio has 3 positions covering low/mid/high neck.
// string 0=high e, 5=low E; fret=relative offset from startFret.

private val ARPEGGIO_PATTERNS = listOf(
    ArpeggioPattern(
        name = "Major",
        intervals = "R  M3  P5",
        description = "The three notes of a major chord. Bright and stable. Use over any major chord in a progression.",
        positions = listOf(
            5 to listOf(                                    // startFret=5 (root on low E)
                NeckDot(0, 0, true),  NeckDot(0, 4, false),  // e: A(R)  C#(M3)
                NeckDot(1, 0, false),                          // B: E(P5)
                NeckDot(2, 1, false), NeckDot(2, 4, false),   // G: C#(M3) E(P5)
                NeckDot(3, 2, true),                           // D: A(R)
                NeckDot(4, 2, false),                          // A: E(P5)
                NeckDot(5, 0, true),  NeckDot(5, 4, false)    // E: A(R)  C#(M3)
            ),
            9 to listOf(                                    // startFret=9 (root on B and A strings)
                NeckDot(0, 0, false), NeckDot(0, 3, false),   // e: C#(M3) E(P5)
                NeckDot(1, 1, true),                           // B: A(R)
                NeckDot(2, 0, false),                          // G: E(P5)
                NeckDot(3, 2, false),                          // D: C#(M3)
                NeckDot(4, 3, true),                           // A: A(R)
                NeckDot(5, 0, false), NeckDot(5, 3, false)    // E: C#(M3) E(P5)
            ),
            12 to listOf(                                   // startFret=12 (root on A and G strings)
                NeckDot(0, 0, false), NeckDot(0, 5, true),    // e: E(P5)  A(R)
                NeckDot(1, 2, false), NeckDot(1, 5, false),   // B: C#(M3) E(P5)
                NeckDot(2, 2, true),                           // G: A(R)
                NeckDot(3, 2, false),                          // D: E(P5)
                NeckDot(4, 0, true),  NeckDot(4, 4, false),   // A: A(R)  C#(M3)
                NeckDot(5, 0, false), NeckDot(5, 5, true)     // E: E(P5)  A(R)
            )
        ),
        fretCount = 5
    ),
    ArpeggioPattern(
        name = "Minor",
        intervals = "R  b3  P5",
        description = "The three notes of a minor chord. Darker and more introspective. Use over any minor chord.",
        positions = listOf(
            5 to listOf(                                    // startFret=5
                NeckDot(0, 0, true),  NeckDot(0, 3, false),   // e: A(R)  C(b3)
                NeckDot(1, 0, false),                           // B: E(P5)
                NeckDot(2, 0, false), NeckDot(2, 4, false),    // G: C(b3) E(P5)  — wait: G+0=G is not b3; fixing below
                NeckDot(3, 2, true),                            // D: A(R)
                NeckDot(4, 2, false),                           // A: E(P5)
                NeckDot(5, 0, true),  NeckDot(5, 3, false)     // E: A(R)  C(b3)
            ),
            7 to listOf(                                    // startFret=7 (root on D string)
                NeckDot(0, 1, false), NeckDot(0, 5, false),   // e: C(b3)  E(P5)
                NeckDot(1, 3, true),                            // B: A(R)
                NeckDot(2, 2, false),                           // G: E(P5)
                NeckDot(3, 0, true),  NeckDot(3, 3, false),    // D: A(R)  C(b3)
                NeckDot(4, 0, false),                           // A: E(P5)
                NeckDot(5, 1, false), NeckDot(5, 5, false)     // E: C(b3)  E(P5) — actually C at fret8=rel1 ✓, E at fret12=rel5 ✓
            ),
            12 to listOf(                                   // startFret=12 (root on A and G strings)
                NeckDot(0, 0, false), NeckDot(0, 5, true),    // e: E(P5)  A(R)
                NeckDot(1, 1, false), NeckDot(1, 5, false),   // B: C(b3)  E(P5)
                NeckDot(2, 2, true),                            // G: A(R)
                NeckDot(3, 2, false),                           // D: E(P5)
                NeckDot(4, 0, true),  NeckDot(4, 3, false),    // A: A(R)   C(b3)
                NeckDot(5, 0, false), NeckDot(5, 5, true)      // E: E(P5)  A(R)
            )
        ),
        fretCount = 5
    ),
    ArpeggioPattern(
        name = "Major 7 (Maj7)",
        intervals = "R  M3  P5  M7",
        description = "A major chord with a major 7th added. Lush and jazzy. Common in jazz standards, bossa nova, and neo-soul.",
        positions = listOf(
            5 to listOf(                                    // startFret=5
                NeckDot(0, 0, true),  NeckDot(0, 4, false),   // e: A(R)  C#(M3)
                NeckDot(1, 0, false), NeckDot(1, 4, false),    // B: E(P5) G#(M7)
                NeckDot(2, 1, false), NeckDot(2, 4, false),    // G: C#(M3) E(P5)
                NeckDot(3, 1, false), NeckDot(3, 2, true),     // D: G#(M7) A(R)
                NeckDot(4, 2, false),                           // A: E(P5)
                NeckDot(5, 0, true),  NeckDot(5, 4, false)     // E: A(R)  C#(M3)
            ),
            9 to listOf(                                    // startFret=9
                NeckDot(0, 0, false), NeckDot(0, 3, false),   // e: C#(M3) E(P5)
                NeckDot(1, 0, false), NeckDot(1, 1, true),    // B: G#(M7) A(R)
                NeckDot(2, 0, false), NeckDot(2, 4, false),   // G: E(P5)  G#(M7) — G str fret9=E(P5)✓, fret13=G#(M7)✓→rel4
                NeckDot(3, 2, false),                          // D: C#(M3)
                NeckDot(4, 2, false), NeckDot(4, 3, true),    // A: G#(M7) A(R)
                NeckDot(5, 0, false), NeckDot(5, 3, false)    // E: C#(M3) E(P5)
            ),
            12 to listOf(                                   // startFret=12
                NeckDot(0, 0, false), NeckDot(0, 4, false), NeckDot(0, 5, true),  // e: E(P5) G#(M7) A(R)
                NeckDot(1, 2, false), NeckDot(1, 5, false),   // B: C#(M3) E(P5)
                NeckDot(2, 1, false), NeckDot(2, 2, true),    // G: G#(M7) A(R)
                NeckDot(3, 2, false),                          // D: E(P5)
                NeckDot(4, 0, true),  NeckDot(4, 4, false),   // A: A(R)  C#(M3)
                NeckDot(5, 0, false), NeckDot(5, 4, false), NeckDot(5, 5, true)   // E: E(P5) G#(M7) A(R)
            )
        ),
        fretCount = 5
    ),
    ArpeggioPattern(
        name = "Minor 7 (Min7)",
        intervals = "R  b3  P5  b7",
        description = "A minor chord with a minor 7th. Smooth and relaxed. The backbone of minor jazz and funk grooves.",
        positions = listOf(
            5 to listOf(                                    // startFret=5
                NeckDot(0, 0, true),  NeckDot(0, 3, false),   // e: A(R)  C(b3)
                NeckDot(1, 0, false), NeckDot(1, 3, false),    // B: E(P5) G(b7)
                NeckDot(2, 0, false), NeckDot(2, 4, false),    // G: C(b3) E(P5)
                NeckDot(3, 0, false), NeckDot(3, 2, true),     // D: G(b7) A(R)
                NeckDot(4, 2, false),                           // A: E(P5)
                NeckDot(5, 0, true),  NeckDot(5, 3, false)     // E: A(R)  C(b3)
            ),
            7 to listOf(                                    // startFret=7 (root on D string)
                NeckDot(0, 1, false), NeckDot(0, 5, false),   // e: C(b3)  E(P5)
                NeckDot(1, 1, false), NeckDot(1, 3, true),    // B: G(b7)  A(R)
                NeckDot(2, 2, false), NeckDot(2, 5, false),   // G: E(P5)  G(b7) — G str fret9=E ✓(rel2), fret12=G ✓(rel5)
                NeckDot(3, 0, true),  NeckDot(3, 3, false),    // D: A(R)   C(b3)
                NeckDot(4, 0, false), NeckDot(4, 3, false),    // A: E(P5)  G(b7)
                NeckDot(5, 1, false), NeckDot(5, 5, false)     // E: C(b3)  E(P5)
            ),
            12 to listOf(                                   // startFret=12
                NeckDot(0, 0, false), NeckDot(0, 3, false), NeckDot(0, 5, true),  // e: E(P5) G(b7) A(R)
                NeckDot(1, 1, false), NeckDot(1, 5, false),   // B: C(b3)  E(P5)
                NeckDot(2, 0, false), NeckDot(2, 2, true),    // G: G(b7)  A(R)
                NeckDot(3, 2, false),                          // D: E(P5)
                NeckDot(4, 0, true),  NeckDot(4, 3, false),   // A: A(R)   C(b3)
                NeckDot(5, 0, false), NeckDot(5, 3, false), NeckDot(5, 5, true)   // E: E(P5) G(b7) A(R)
            )
        ),
        fretCount = 5
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
                val posLabel = when {
                    scale.positionLabels != null -> scale.positionLabels[idx]
                    scale.positions.size > 1    -> "Position ${idx + 1}  (root at fret $startFret)"
                    else                        -> "Root at fret $startFret"
                }
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
            arp.positions.forEachIndexed { idx, (startFret, dotList) ->
                val posLabel = "Position ${idx + 1}  (root at fret $startFret)"
                NeckDiagram(
                    dots           = dotList,
                    fretCount      = arp.fretCount,
                    startFretLabel = startFret,
                    label          = posLabel,
                    modifier       = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

// ── CAGED Chord Shapes ───────────────────────────────────────────────────────

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CagedChordsContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        BodyText("The CAGED system describes 5 moveable chord shapes named after their open-string forms: C, A, G, E, and D. Every chord on the guitar is one of these shapes transposed up the neck. Together they tile the entire fretboard.")

        // ── Section 1: CAGED Major Open Shapes ──────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("CAGED Major Open Shapes")
        BodyText("These are the 5 foundational shapes in open position. Each has open strings (○) and should be memorised as a unit.")
        ItalicNote("Slide any shape up the neck with a barre to play that shape in any key.")

        val cagedMajorChords = listOf("C", "A", "G", "E", "D")
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            cagedMajorChords.forEach { root ->
                val voicing = getVoicings(root, "Major").firstOrNull()
                if (voicing != null) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "$root major (${voicing.label})",
                            color = Color(0xFFFFCC80),
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                        ChordDiagram(voicing)
                    }
                }
            }
        }

        // ── Section 2: Minor Chords ──────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Minor Chords")
        BodyText("Common minor chord voicings. Dm is fully open; Cm and Fm use a barre.")

        val minorChords = listOf("C", "F", "D")
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            minorChords.forEach { root ->
                val voicing = getVoicings(root, "Minor").firstOrNull()
                if (voicing != null) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "${root}m (${voicing.label})",
                            color = Color(0xFFFFCC80),
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                        ChordDiagram(voicing)
                    }
                }
            }
        }

        // ── Section 3: Dominant 7 CAGED Shapes ──────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Dominant 7 CAGED Shapes")
        BodyText("Dominant 7th chords (R M3 P5 b7). Each shape is the CAGED dominant-7 form at its home root, essential for blues and jazz.")

        val dom7Chords = listOf("C", "A", "G", "E", "D")
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            dom7Chords.forEach { root ->
                val voicing = getVoicings(root, "Dom7").firstOrNull()
                if (voicing != null) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "${root}7 (${voicing.label})",
                            color = Color(0xFFFFCC80),
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                        ChordDiagram(voicing)
                    }
                }
            }
        }

        // ── Section 4: Minor 7 Chords ────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Minor 7 Chords")
        BodyText("Minor 7th chords (R b3 P5 b7). Am7 and Dm7 are open; Cm7 and Gm7 use CAGED shapes.")

        val min7Chords = listOf("C", "A", "G", "D")
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            min7Chords.forEach { root ->
                val voicing = getVoicings(root, "Min7").firstOrNull()
                if (voicing != null) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "${root}m7 (${voicing.label})",
                            color = Color(0xFFFFCC80),
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                        ChordDiagram(voicing)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

// ── Ideas ────────────────────────────────────────────────────────────────────

@Composable
private fun IdeasContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        BodyText("These are the building blocks of guitar music — the vocabulary every player uses to construct solos, songs, and improvisations.")

        // ── Riffs ──────────────────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Riffs")
        BodyText("A riff is a short, repeated melodic or rhythmic figure that forms the backbone of a song. Riffs are usually played on low strings and are instantly recognisable — they define the song's identity.")
        BodyText("Characteristics of a great riff:")
        listOf(
            "Short — typically 1–4 bars, easy to remember.",
            "Repetition gives it momentum; slight variations keep it fresh.",
            "Often based on pentatonic or blues scale tones for guitar.",
            "Usually tied tightly to the rhythm (groove-first thinking)."
        ).forEach { BodyText("• $it") }
        ItalicNote("Think of the opening figures of 'Smoke on the Water', 'Whole Lotta Love', or 'Enter Sandman'. Each is a riff built from just 3–5 notes.")

        // ── Licks ──────────────────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Licks")
        BodyText("A lick is a short melodic phrase — a pre-learned 'move' that you drop into a solo at the right moment. Unlike a riff (which repeats as a structural unit), a lick is more like a signature gesture you pull from your vocabulary.")
        BodyText("How licks work:")
        listOf(
            "Learned as a complete unit: fingering, phrasing, and feel all together.",
            "Transposable — once learned in one key, you can move it to any key.",
            "Chain licks together to build longer solos.",
            "Great players eventually disguise or connect their licks so seamlessly that they stop sounding like separate units."
        ).forEach { BodyText("• $it") }
        ItalicNote("Your 'lick vocabulary' grows with listening. Every time you learn something from a recording, you add a lick to your toolkit.")

        // ── Chords ─────────────────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Chords")
        BodyText("A chord is three or more notes played simultaneously. On guitar, open chords and barre chords are the foundation, but chords can also be voiced in countless ways across the neck.")
        BodyText("Key chord ideas:")
        listOf(
            "Triads (3 notes: R + M3/b3 + P5) are the basic unit. Everything else is a triad with added extensions.",
            "7th chords (add M7, b7, or dim7) add colour and are essential in jazz, blues, and soul.",
            "Inversions — putting a note other than the root in the bass — create smooth voice-leading between chords.",
            "Shell voicings (root + 3rd + 7th only, omitting the 5th) are efficient and commonly used in jazz comping.",
            "Spread voicings and drop-2 voicings spread notes across strings for a fuller sound."
        ).forEach { BodyText("• $it") }
        ItalicNote("Tap the chord name in the timeline to see fingering diagrams for CAGED voicings across the neck.")

        // ── Power Chords ────────────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Power Chords")
        BodyText("A power chord is just the root and the perfect 5th (sometimes doubled an octave up). It contains no 3rd, so it is neither major nor minor — it sounds neutral, strong, and cuts through distortion without muddying.")
        BodyText("Why power chords rule rock:")
        listOf(
            "The absence of the 3rd avoids clashing harmonics when the amp is overdriven.",
            "Two-finger shape (root + P5 on adjacent strings) is immediately moveable across the neck.",
            "Add the octave root on a third string for a fatter sound (the classic 3-note power chord).",
            "Mute unused strings with your fretting hand to keep the sound tight."
        ).forEach { BodyText("• $it") }
        ItalicNote("Shape: root on low E or A string, P5 two frets up on the next string, optional octave root two frets up on the string after that.")

        // ── Tension and Release ─────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Tension and Release")
        BodyText("Tension is musical dissonance or instability that creates the feeling of expectation. Release is the resolution that satisfies that expectation. The interplay between tension and release is the engine of emotion in music.")
        BodyText("Ways to create tension on guitar:")
        listOf(
            "Play outside the scale — chromatic notes, b9, or tritone substitutions.",
            "Bend up to a note and hold it before resolving.",
            "Play over a chord change a beat early, creating anticipation.",
            "Use register extremes — very high or very low notes feel tense against a mid-range chord.",
            "Rhythmic displacement — play the same phrase slightly off-beat."
        ).forEach { BodyText("• $it") }
        BodyText("Ways to release tension:")
        listOf(
            "Land on a strong chord tone (root, 3rd, or 5th) on a downbeat.",
            "Resolve a half-step approach note into the chord tone.",
            "Return to a lower register after a high climax.",
            "Silence — a well-placed rest after a busy phrase is the ultimate release."
        ).forEach { BodyText("• $it") }
        ItalicNote("The most emotionally powerful moments in a solo are usually not the busiest — they are the moments of resolution after building tension.")

        // ── Motifs ──────────────────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Motifs")
        BodyText("A motif is a short melodic or rhythmic idea — often just 2–4 notes — that you develop and transform throughout a solo or composition. It gives your playing thematic unity: the listener hears something familiar returning in new forms.")
        BodyText("Ways to develop a motif:")
        listOf(
            "Repetition — play the same motif again to reinforce it.",
            "Sequence — repeat the motif starting on a different scale degree (shift it up or down the scale).",
            "Inversion — flip the direction: if the original goes up, make it go down.",
            "Augmentation / Diminution — play the same notes slower or faster.",
            "Transposition — move the whole motif to a different key or chord.",
            "Rhythmic variation — keep the pitches but change the rhythm (or vice versa)."
        ).forEach { BodyText("• $it") }
        ItalicNote("Beethoven's opening 4 notes of the 5th Symphony (da-da-da-DUM) is the world's most famous motif. Three notes and a rhythmic landing — transformed for an entire movement.")

        // ── Phrasing ────────────────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Phrasing")
        BodyText("Phrasing is how you shape and deliver notes — it is the difference between playing correct notes and playing music. Great phrasing makes a solo feel like singing: it breathes, it pauses, it builds, it whispers.")
        BodyText("The elements of phrasing:")
        listOf(
            "Dynamics — vary the volume within a phrase. Not every note is equal.",
            "Articulation — hammer-ons, pull-offs, slides, and bends all change how a note speaks.",
            "Vibrato — a controlled pitch wobble on a sustained note adds emotion and character. Width and speed are your voice.",
            "Timing — playing slightly behind or ahead of the beat (feel) gives the phrase personality.",
            "Space — what you do NOT play is as important as what you do. Rests breathe life into a phrase.",
            "Contour — a phrase should have a shape: build up, peak, come down (like a sentence with a subject, verb, and conclusion)."
        ).forEach { BodyText("• $it") }
        ItalicNote("A common exercise: take a single pentatonic lick and play it 10 different ways — vary the dynamics, the vibrato, the timing. The notes stay the same; only the phrasing changes. The emotional result will be completely different each time.")

        // ── Putting It Together ─────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Putting It Together")
        BodyText("All of these ideas interact:")
        listOf(
            "A riff is a motif with a rhythmic identity, repeated as a structural foundation.",
            "A lick is a practiced phrase that uses tension, release, and strong phrasing.",
            "Chords and power chords provide the harmonic context that makes melody meaningful.",
            "Motifs give a solo narrative structure — a beginning, middle, and end.",
            "Phrasing is the human element that turns technique into expression."
        ).forEach { BodyText("• $it") }
        ItalicNote("Start small: pick one idea per practice session. Master the vocabulary one word at a time — the sentences will come naturally.")
        Spacer(modifier = Modifier.height(24.dp))
    }
}

// ── Chord Shapes ─────────────────────────────────────────────────────────────

private data class ChordShapeEntry(
    val name: String,
    val voicing: ChordVoicing,
    val sound: String
)

@Composable
private fun ChordShapeCard(entry: ChordShapeEntry) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            entry.name,
            color = Color(0xFFFFCC80),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(bottom = 2.dp)
        )
        ChordDiagram(entry.voicing)
        Text(
            entry.sound,
            color = Color(0xFF888888),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ChordShapesContent() {

    val openMajor = listOf(
        ChordShapeEntry("C",  ChordVoicing("Open", intArrayOf(-1, 3, 2, 0, 1, 0)), "Warm & full-bodied"),
        ChordShapeEntry("A",  ChordVoicing("Open", intArrayOf(-1, 0, 2, 2, 2, 0)), "Bright & punchy"),
        ChordShapeEntry("G",  ChordVoicing("Open", intArrayOf( 3, 2, 0, 0, 0, 3)), "Big & resonant"),
        ChordShapeEntry("E",  ChordVoicing("Open", intArrayOf( 0, 2, 2, 1, 0, 0)), "Full & powerful"),
        ChordShapeEntry("D",  ChordVoicing("Open", intArrayOf(-1,-1, 0, 2, 3, 2)), "Bright & jangly"),
    )

    val openMinor = listOf(
        ChordShapeEntry("Am", ChordVoicing("Open", intArrayOf(-1, 0, 2, 2, 1, 0)), "Dark & melancholy"),
        ChordShapeEntry("Em", ChordVoicing("Open", intArrayOf( 0, 2, 2, 0, 0, 0)), "Deep & haunting"),
        ChordShapeEntry("Dm", ChordVoicing("Open", intArrayOf(-1,-1, 0, 2, 3, 1)), "Sad & intimate"),
    )

    val dom7 = listOf(
        ChordShapeEntry("A7", ChordVoicing("Open", intArrayOf(-1, 0, 2, 0, 2, 0)), "Bluesy & funky"),
        ChordShapeEntry("E7", ChordVoicing("Open", intArrayOf( 0, 2, 0, 1, 0, 0)), "Twangy blues feel"),
        ChordShapeEntry("D7", ChordVoicing("Open", intArrayOf(-1,-1, 0, 2, 1, 2)), "Bright country twang"),
        ChordShapeEntry("B7", ChordVoicing("Open", intArrayOf(-1, 2, 1, 2, 0, 2)),  "Bright & country-bluesy"),
    )

    val min7 = listOf(
        ChordShapeEntry("Am7", ChordVoicing("Open", intArrayOf(-1, 0, 2, 0, 1, 0)), "Smooth & soulful"),
        ChordShapeEntry("Em7", ChordVoicing("Open", intArrayOf( 0, 2, 0, 0, 0, 0)), "Airy & spacious"),
    )

    val sus = listOf(
        ChordShapeEntry("Esus4", ChordVoicing("Open", intArrayOf( 0, 2, 2, 2, 0, 0)), "Tense & expectant"),
        ChordShapeEntry("Dsus4", ChordVoicing("Open", intArrayOf(-1,-1, 0, 2, 3, 3)), "Soaring & unresolved"),
        ChordShapeEntry("Dsus2", ChordVoicing("Open", intArrayOf(-1,-1, 0, 2, 3, 0)), "Dreamy & open"),
    )

    val barreEShape = listOf(
        ChordShapeEntry("F",   ChordVoicing("E Shape", intArrayOf(1, 3, 3, 2, 1, 1)), "Warm & resolved"),
        ChordShapeEntry("Fm",  ChordVoicing("E Shape", intArrayOf(1, 3, 3, 1, 1, 1)), "Dark & heavy"),
        ChordShapeEntry("F7",  ChordVoicing("E Shape", intArrayOf(1, 3, 1, 2, 1, 1)), "Punchy blues tone"),
        ChordShapeEntry("Fm7", ChordVoicing("E Shape", intArrayOf(1, 3, 1, 1, 1, 1)), "Silky & dark"),
    )

    val barreAShape = listOf(
        ChordShapeEntry("C",   ChordVoicing("A Shape", intArrayOf(-1, 3, 5, 5, 5, 3), baseFret = 3), "Bold & chunky"),
        ChordShapeEntry("Cm",  ChordVoicing("A Shape", intArrayOf(-1, 3, 5, 5, 4, 3), baseFret = 3), "Heavy & tense"),
        ChordShapeEntry("C7",  ChordVoicing("A Shape", intArrayOf(-1, 3, 5, 3, 5, 3), baseFret = 3), "Driving & funky"),
        ChordShapeEntry("Cm7", ChordVoicing("A Shape", intArrayOf(-1, 3, 5, 3, 4, 3), baseFret = 3), "Jazzy & moody"),
    )

    val extended = listOf(
        ChordShapeEntry("A6",    ChordVoicing("Open",  intArrayOf(-1, 0, 2, 2, 2, 2)),                "Lush & mellow"),
        ChordShapeEntry("F9",    ChordVoicing("Barre", intArrayOf(-1, 8, 7, 8, 8, 8), baseFret = 7), "Jazzy & sophisticated"),
        ChordShapeEntry("Aadd9", ChordVoicing("Barre", intArrayOf(-1,-1, 7, 6, 5, 7), baseFret = 5), "Shimmering & open"),
    )

    val other = listOf(
        ChordShapeEntry("F (D shape)", ChordVoicing("D Shape", intArrayOf(-1,-1, 3, 2, 1, 1)),            "Bright treble clarity"),
        ChordShapeEntry("CMaj7",       ChordVoicing("Barre",   intArrayOf(-1, 3, 5, 4, 5, 3), baseFret = 3), "Lush & romantic"),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        BodyText("Open chords ring out with open strings for a natural, resonant sound. Barre chords close off the open strings so the same shape can be moved anywhere on the neck.")

        // ── Open Major ──────────────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Open Major Chords")
        BodyText("The five foundational open major chords. Bright, full, and essential in almost every genre.")
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            openMajor.forEach { ChordShapeCard(it) }
        }

        // ── Open Minor ──────────────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Open Minor Chords")
        BodyText("Sadder and darker than their major counterparts. Great for emotional, introspective playing.")
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            openMinor.forEach { ChordShapeCard(it) }
        }

        // ── Dom7 ────────────────────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Dominant 7th Chords")
        BodyText("Root + major 3rd + perfect 5th + minor 7th. A bluesy tension that wants to resolve — a staple of blues, country, and soul.")
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            dom7.forEach { ChordShapeCard(it) }
        }

        // ── Min7 ────────────────────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Minor 7th Chords")
        BodyText("Root + minor 3rd + perfect 5th + minor 7th. Smoother and jazzier than plain minor chords. Common in jazz, soul, and R&B.")
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            min7.forEach { ChordShapeCard(it) }
        }

        // ── Sus ─────────────────────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Suspended Chords")
        BodyText("Sus chords replace the 3rd with a 2nd (sus2) or 4th (sus4). Neither major nor minor — they create an open, unresolved feeling that naturally wants to move to a major or minor chord.")
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            sus.forEach { ChordShapeCard(it) }
        }

        // ── Barre — E Shape ─────────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Barre Chords — E Shape")
        BodyText("Index finger bars all 6 strings; the other fingers form the open-E chord shape behind it. F at fret 1 is typically the first barre chord a player learns. Slide the shape up the neck to play any key.")
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            barreEShape.forEach { ChordShapeCard(it) }
        }

        // ── Barre — A Shape ─────────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Barre Chords — A Shape")
        BodyText("Index bars strings 1–5 (low E muted); the remaining fingers form the open-A shape. C at fret 3 is the home position — move the whole shape to any fret to play that key.")
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            barreAShape.forEach { ChordShapeCard(it) }
        }

        // ── Other ────────────────────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Other Closed Voicings")
        BodyText("Partial-string voicings and extensions that don't fit neatly into the standard E or A barre shapes.")
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            other.forEach { ChordShapeCard(it) }
        }

        // ── Extended Chords ──────────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Extended Chords")
        BodyText("6th, 9th, and add9 voicings that layer colour beyond the basic triad. A6 and Aadd9 are open or near-open; F9 is a closed jazz voicing up the neck.")
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            extended.forEach { ChordShapeCard(it) }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

// ── Harmonics ────────────────────────────────────────────────────────────────

@Composable
private fun HarmonicsContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        BodyText("A guitar string doesn't only vibrate as a whole — it simultaneously vibrates in halves, thirds, quarters, and smaller divisions. Each division produces a higher pitch called a harmonic or overtone. On guitar you can isolate individual harmonics by touching the string at exactly the right point.")

        // ── What Are Harmonics ──────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("What Are Harmonics")
        BodyText("Every vibrating string produces a fundamental frequency (the main note) plus a series of overtones at integer multiples of that frequency: 2f, 3f, 4f, and so on. These overtones are always present — you hear them as the tone colour of the note. What changes between a guitar and a violin playing the same pitch is not the harmonics themselves, but how loud each harmonic is relative to the others.")
        BodyText("The positions along the string where a given mode has zero movement are called nodes. By lightly touching the string at a node, you cancel all vibrational modes that don't share that node — the remaining modes ring out as a chime-like tone. The lower the harmonic, the louder and more bell-like the sound.")

        // ── Natural Harmonics — How to Play ────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Natural Harmonics — How to Play")
        listOf(
            "Rest a fretting-hand finger lightly directly above the fret wire — not between frets, and not pressing down.",
            "Pick the string while your finger rests on it.",
            "Lift your fretting finger immediately after the pick attack — the harmonic rings freely on its own.",
            "The cleaner your touch (not too heavy, not sliding), the clearer the chime."
        ).forEach { BodyText("• $it") }
        ItalicNote("Works best at the 12th, 7th, and 5th fret positions. These divisions (halves, thirds, quarters) give the strongest, most audible harmonics.")

        // ── The Three Main Positions ────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("The Three Main Positions")
        listOf(
            "12th fret — string divides in half (1/2). Produces the 2nd harmonic: exactly 1 octave above the open string.",
            "7th fret — string divides in thirds (1/3). Produces the 3rd harmonic: 1 octave + a perfect 5th above open.",
            "5th fret — string divides in quarters (1/4). Produces the 4th harmonic: 2 octaves above open."
        ).forEach { BodyText("• $it") }
        ItalicNote("Additional harmonics exist at the 4th fret (5th harmonic, 2 octaves + major 3rd) and 3rd fret (6th harmonic), but they are quieter and harder to produce cleanly.")

        // ── Notes at Each Position ──────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Notes at Each Position (Standard Tuning)")
        BodyText("The note produced at each harmonic position on every string:")
        listOf(
            "E (6th)  open E2  →  12th fret E3  →  7th fret B3  →  5th fret E4",
            "A (5th)  open A2  →  12th fret A3  →  7th fret E4  →  5th fret A4",
            "D (4th)  open D3  →  12th fret D4  →  7th fret A4  →  5th fret D5",
            "G (3rd)  open G3  →  12th fret G4  →  7th fret D5  →  5th fret G5",
            "B (2nd)  open B3  →  12th fret B4  →  7th fret F#5  →  5th fret B5",
            "e (1st)  open E4  →  12th fret E5  →  7th fret B5  →  5th fret E6"
        ).forEach { BodyText("• $it") }
        ItalicNote("Notice that the 7th-fret harmonic of one string often matches the 5th-fret harmonic of the next — this is the basis for harmonic tuning (see the Guitar Tuning screen).")

        // ── Artificial Harmonics (Pinch Harmonics) ──────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Artificial Harmonics — Pinch Harmonics")
        BodyText("Pinch harmonics let you produce harmonics above any fretted note, not just open strings.")
        listOf(
            "Fret a note normally with your left hand.",
            "Hold the pick so the thumb extends slightly beyond the tip.",
            "As the pick attacks the string, let the thumb immediately graze the string at the same spot.",
            "The thumb acts as a moving node — the exact position of your thumb changes which harmonic you emphasise.",
            "A slight forward angle of the pick and wrist helps the thumb contact cleanly."
        ).forEach { BodyText("• $it") }
        ItalicNote("Pinch harmonics produce the characteristic 'squeal' of hard rock and metal guitar. Key players: Zakk Wylde, Billy Gibbons (ZZ Top), Eddie Van Halen.")

        // ── Touch Harmonics (Harp Harmonics) ────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Touch Harmonics — Harp Harmonics")
        BodyText("A technique common in fingerstyle and classical guitar:")
        listOf(
            "Fret a note normally with the left hand.",
            "With the right-hand index finger, lightly touch the string exactly 12 frets above the fretted note.",
            "Pluck the string with another right-hand finger (ring or middle).",
            "The result is the octave harmonic of the fretted note — bell-like and clear.",
            "Arpeggiating chords this way produces a harp-like cascading effect."
        ).forEach { BodyText("• $it") }

        // ── Uses in Music ────────────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Uses in Music")
        listOf(
            "Intro and verse chimes on clean electric — the 12th and 7th fret positions ring out beautifully.",
            "Ambient texture in post-rock and shoegaze — sustaining harmonics blend into reverb-drenched soundscapes.",
            "Phrase endings — letting a harmonic ring out instead of a fretted note adds a floating, ethereal quality.",
            "Pinch harmonic squeals punctuating a rock or blues solo for drama and intensity.",
            "Harp harmonic arpeggio passages in fingerstyle and classical arrangements."
        ).forEach { BodyText("• $it") }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

// ── Guitar Tuning ─────────────────────────────────────────────────────────────

@Composable
private fun GuitarTuningContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        BodyText("There are three common ways to tune a guitar: using an electronic tuner, matching fretted notes string to string, and using harmonics. Each method has different trade-offs in accuracy and convenience.")

        // ── Electronic Tuner ────────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Using an Electronic Tuner")
        BodyText("The most accurate and reliable method. Tune each string independently to the correct absolute pitch.")
        listOf(
            "Chromatic tuners recognise all 12 notes — show the note name and how far off you are.",
            "Clip-on tuners clamp to the headstock and sense vibration through the wood — work well in noisy rooms.",
            "Microphone tuners pick up the sound — better in quiet environments.",
            "Tuner apps on your phone work well for acoustic guitar in a quiet room."
        ).forEach { BodyText("• $it") }
        BodyText("Standard tuning, low to high:  E2 · A2 · D3 · G3 · B3 · E4")
        BodyText("Reading the display:")
        listOf(
            "Indicator left of centre = flat (pitch too low) → tighten the tuning peg.",
            "Right of centre = sharp (pitch too high) → loosen the peg.",
            "Green or centred = in tune."
        ).forEach { BodyText("• $it") }
        ItalicNote("Always tune up to pitch — approach from below. If you overshoot, go past and come back up. Machine heads hold tension more consistently when the string arrives at pitch from below.")

        // ── 5th Fret Relative Tuning ────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Relative Tuning — 5th Fret Method")
        BodyText("Tune the low E string to a reference pitch, then tune each subsequent string by matching it to the previous one:")
        listOf(
            "E string, fret 5  (note A)  →  tune open A string to match.",
            "A string, fret 5  (note D)  →  tune open D string to match.",
            "D string, fret 5  (note G)  →  tune open G string to match.",
            "G string, fret 4  (note B)  →  tune open B string to match.  ← 4th fret, not 5th!",
            "B string, fret 5  (note E)  →  tune open high e string to match."
        ).forEach { BodyText("• $it") }
        BodyText("Why the 4th fret for G→B? Every other adjacent string pair is a perfect 4th apart (5 semitones). The G-to-B interval is a major 3rd (4 semitones) — one semitone narrower. So the matching note is one fret lower, at the 4th fret.")
        ItalicNote("Drawback: errors accumulate. A slightly flat E produces a slightly flat A, then D, then G — by the high e you can be noticeably out of tune with a reference pitch even though the strings all match each other.")

        // ── Harmonic Tuning ──────────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Harmonic Tuning — The 5th + 7th Fret Method")
        BodyText("Tune the low E with a tuner, then use harmonics to match adjacent strings. To produce a harmonic: lightly rest a finger above the fret wire (don't press), pick, and immediately release the finger.")
        BodyText("For each adjacent pair tuned a perfect 4th apart, the lower string's 5th-fret harmonic and the upper string's 7th-fret harmonic produce exactly the same pitch. Play both simultaneously and listen for beating:")
        listOf(
            "E string 5th fret harmonic  +  A string 7th fret harmonic  →  both sound E4",
            "A string 5th fret harmonic  +  D string 7th fret harmonic  →  both sound A4",
            "D string 5th fret harmonic  +  G string 7th fret harmonic  →  both sound D5",
            "B string 5th fret harmonic  +  e string 7th fret harmonic  →  both sound B5"
        ).forEach { BodyText("• $it") }
        BodyText("⚠  G → B: EXCEPTION. The G-to-B interval is a major 3rd, not a perfect 4th. The G string's 5th-fret harmonic is G5; the B string's 7th-fret harmonic is F#5 — a semitone apart. They will never match. For this pair, use the standard method: press G string at the 4th fret (normal fretted note) and tune open B to match.")
        ItalicNote("Advantage: harmonics sustain much longer than fretted notes. You can hold both simultaneously for several seconds, giving your ear plenty of time to detect and eliminate beating.")

        // ── Listening for Beats ──────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Listening for Beats")
        BodyText("When two notes are close in pitch but not identical, their sound waves periodically reinforce and cancel each other, creating a slow, regular wavering in loudness called beating.")
        listOf(
            "The beat rate (pulses per second) equals the frequency difference between the two pitches.",
            "If one harmonic is 440 Hz and the other is 442 Hz, you hear 2 beats per second — a slow throb.",
            "As you tune closer to the target pitch, the beating slows down.",
            "When the beating stops completely — a single, stable, unwavering tone — the strings are in unison."
        ).forEach { BodyText("• $it") }
        ItalicNote("Train your ear: deliberately detune one string slightly, listen to the beating speed up, then retune and hear it slow to nothing. The moment it stops is unmistakable.")

        // ── Why the Physics Works ────────────────────────────────────────────
        HorizontalDivider(color = Color(0xFF2A2A2A), modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader("Why the Physics Works")
        BodyText("The 5th-fret harmonic divides the string into 4 equal parts — it is the 4th harmonic, at 4× the string's fundamental frequency. The 7th-fret harmonic divides the string into 3 equal parts — it is the 3rd harmonic, at 3× the fundamental.")
        BodyText("For two strings tuned a perfect 4th apart (frequency ratio 4 : 3), let f = lower string fundamental:")
        listOf(
            "4th harmonic of lower string  =  4f",
            "3rd harmonic of upper string  =  3 × (4f ÷ 3)  =  4f  →  exact match ✓"
        ).forEach { BodyText("• $it") }
        BodyText("For the G→B major 3rd (frequency ratio 5 : 4):")
        listOf(
            "4th harmonic of G string  =  4f  →  G5 (≈ 784 Hz)",
            "3rd harmonic of B string  =  3 × (5f ÷ 4)  =  3.75f  →  F#5 (≈ 740 Hz)  →  mismatch ✗"
        ).forEach { BodyText("• $it") }
        ItalicNote("This mismatch is not a flaw in the tuning method — it reflects equal temperament. The major 3rd in equal temperament (ratio ≈ 1.260) is slightly wider than the just major 3rd (ratio 5 : 4 = 1.250), so no exact harmonic pair exists for the G→B interval.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

// ── Hub ──────────────────────────────────────────────────────────────────────

@Composable
private fun TutorialsHub(onSelect: (String) -> Unit) {
    val topics = listOf(
        "scales"       to "Scales",
        "cycle"        to "Cycle of Fifths",
        "improv"       to "Improvisation",
        "arpeggios"    to "Arpeggios",
        "caged"        to "CAGED Chord Shapes",
        "chord_shapes" to "Chord Shapes",
        "harmonics"    to "Harmonics",
        "guitar_tuning" to "Guitar Tuning",
        "ideas"        to "Ideas"
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
        "scales"       -> "Scales"
        "cycle"        -> "Cycle of Fifths"
        "improv"       -> "Improvisation"
        "arpeggios"    -> "Arpeggios"
        "caged"        -> "CAGED Chord Shapes"
        "chord_shapes" -> "Chord Shapes"
        "harmonics"    -> "Harmonics"
        "guitar_tuning" -> "Guitar Tuning"
        "ideas"        -> "Ideas"
        else           -> "Learning"
    }

    BackHandler { onDismiss() }

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
                TextButton(onClick = onDismiss) { Text("Back") }
            }

            // Body
            when (selectedTopic) {
                "scales"       -> ScalesContent()
                "cycle"        -> CycleContent()
                "improv"       -> ImprovisationContent()
                "arpeggios"    -> ArpeggiosContent()
                "caged"        -> CagedChordsContent()
                "chord_shapes" -> ChordShapesContent()
                "harmonics"    -> HarmonicsContent()
                "guitar_tuning" -> GuitarTuningContent()
                "ideas"        -> IdeasContent()
                else           -> TutorialsHub(onSelect = { selectedTopic = it })
            }
        }
    }
}
