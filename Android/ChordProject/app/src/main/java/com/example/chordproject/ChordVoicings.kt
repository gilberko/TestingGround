package com.example.chordproject

data class ChordVoicing(
    val label: String,
    val frets: IntArray,   // 6 values, low E → high e: -1=muted, 0=open, N=absolute fret number
    val baseFret: Int = 1, // diagram top row = this fret; 1 → draw nut, else → print "Nfr" label
    val isMoveable: Boolean = false  // if true, fret 0 shifts with transposition (barre shape)
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ChordVoicing) return false
        return label == other.label && frets.contentEquals(other.frets)
            && baseFret == other.baseFret && isMoveable == other.isMoveable
    }
    override fun hashCode(): Int =
        31 * (31 * (31 * label.hashCode() + frets.contentHashCode()) + baseFret) +
        isMoveable.hashCode()
}

private val ROOT_SEMITONES = mapOf(
    "C" to 0, "C#" to 1, "D" to 2, "D#" to 3, "E" to 4, "F" to 5,
    "F#" to 6, "G" to 7, "G#" to 8, "A" to 9, "A#" to 10, "B" to 11
)

private data class CagedBase(val homeSemitone: Int, val shapes: Map<String, ChordVoicing>)

private fun transposeVoicing(v: ChordVoicing, semitones: Int): ChordVoicing {
    if (semitones == 0) return v
    val newFrets = IntArray(6) { i ->
        val f = v.frets[i]
        when {
            f < 0 -> f                           // muted: always keep -1
            v.isMoveable && f == 0 -> semitones  // open on barre shape: becomes barre fret
            else -> f + semitones                // fretted: shift up
        }
    }
    val minFret = newFrets.filter { it > 0 }.minOrNull() ?: 1
    return v.copy(frets = newFrets, baseFret = minFret)
}

private val CAGED_SHAPES: List<CagedBase> = listOf(

    // ── E shape ── home: E = semitone 4
    CagedBase(homeSemitone = 4, shapes = mapOf(
        "Major"  to ChordVoicing("E Shape", intArrayOf( 0, 2, 2, 1, 0, 0), isMoveable = true),
        "Minor"  to ChordVoicing("E Shape", intArrayOf( 0, 2, 2, 0, 0, 0), isMoveable = true),
        "Dom7"   to ChordVoicing("E Shape", intArrayOf( 0, 2, 0, 1, 0, 0), isMoveable = true),
        "Maj7"   to ChordVoicing("E Shape", intArrayOf( 0, 2, 1, 1, 0, 0), isMoveable = true),
        "Min7"   to ChordVoicing("E Shape", intArrayOf( 0, 2, 0, 0, 0, 0), isMoveable = true),
        "Dim"    to ChordVoicing("E Shape", intArrayOf( 0, 1, 2, 0,-1,-1), isMoveable = true),
        "Sus2"   to ChordVoicing("E Shape", intArrayOf( 0, 2, 4, 4, 0, 0), isMoveable = true),
        "Sus4"   to ChordVoicing("E Shape", intArrayOf( 0, 2, 2, 2, 0, 0), isMoveable = true),
        "5"      to ChordVoicing("E Shape", intArrayOf( 0, 2, 2,-1,-1,-1), isMoveable = true),
        "Aug"    to ChordVoicing("E Shape", intArrayOf( 0, 3, 2, 1, 1, 0), isMoveable = true),
    )),

    // ── A shape ── home: A = semitone 9
    CagedBase(homeSemitone = 9, shapes = mapOf(
        "Major"  to ChordVoicing("A Shape", intArrayOf(-1, 0, 2, 2, 2, 0), isMoveable = true),
        "Minor"  to ChordVoicing("A Shape", intArrayOf(-1, 0, 2, 2, 1, 0), isMoveable = true),
        "Dom7"   to ChordVoicing("A Shape", intArrayOf(-1, 0, 2, 0, 2, 0), isMoveable = true),
        "Maj7"   to ChordVoicing("A Shape", intArrayOf(-1, 0, 2, 1, 2, 0), isMoveable = true),
        "Min7"   to ChordVoicing("A Shape", intArrayOf(-1, 0, 2, 0, 1, 0), isMoveable = true),
        "Dim"    to ChordVoicing("A Shape", intArrayOf(-1, 0, 1, 2, 1,-1), isMoveable = true),
        "Sus2"   to ChordVoicing("A Shape", intArrayOf(-1, 0, 2, 2, 0, 0), isMoveable = true),
        "Sus4"   to ChordVoicing("A Shape", intArrayOf(-1, 0, 2, 2, 3, 0), isMoveable = true),
        "5"      to ChordVoicing("A Shape", intArrayOf(-1, 0, 2, 2,-1,-1), isMoveable = true),
        "Aug"    to ChordVoicing("A Shape", intArrayOf(-1, 0, 3, 2, 2, 1), isMoveable = true),
    )),

    // ── D shape ── home: D = semitone 2
    CagedBase(homeSemitone = 2, shapes = mapOf(
        "Major"  to ChordVoicing("D Shape", intArrayOf(-1,-1, 0, 2, 3, 2), isMoveable = true),
        "Minor"  to ChordVoicing("D Shape", intArrayOf(-1,-1, 0, 2, 3, 1), isMoveable = true),
        "Dom7"   to ChordVoicing("D Shape", intArrayOf(-1,-1, 0, 2, 1, 2), isMoveable = true),
        "Maj7"   to ChordVoicing("D Shape", intArrayOf(-1,-1, 0, 2, 2, 2), isMoveable = true),
        "Min7"   to ChordVoicing("D Shape", intArrayOf(-1,-1, 0, 2, 1, 1), isMoveable = true),
        "Dim"    to ChordVoicing("D Shape", intArrayOf(-1,-1, 0, 1, 3, 1), isMoveable = true),
        "Sus2"   to ChordVoicing("D Shape", intArrayOf(-1,-1, 0, 2, 3, 0), isMoveable = true),
        "Sus4"   to ChordVoicing("D Shape", intArrayOf(-1,-1, 0, 2, 3, 3), isMoveable = true),
        "5"      to ChordVoicing("D Shape", intArrayOf(-1,-1, 0, 2,-1,-1), isMoveable = true),
        "Aug"    to ChordVoicing("D Shape", intArrayOf(-1,-1, 0, 3, 3, 2), isMoveable = true),
    )),

    // ── G shape ── home: G = semitone 7
    CagedBase(homeSemitone = 7, shapes = mapOf(
        "Major"  to ChordVoicing("G Shape", intArrayOf( 3, 2, 0, 0, 0, 3), isMoveable = true),
        "Minor"  to ChordVoicing("G Shape", intArrayOf( 3, 1, 0, 0, 3, 3), isMoveable = true),
        "Dom7"   to ChordVoicing("G Shape", intArrayOf( 3, 2, 0, 0, 0, 1), isMoveable = true),
        "Maj7"   to ChordVoicing("G Shape", intArrayOf( 3, 2, 0, 0, 0, 2), isMoveable = true),
        "Min7"   to ChordVoicing("G Shape", intArrayOf( 3, 1, 0, 0, 3, 1), isMoveable = true),
        "Dim"    to ChordVoicing("G Shape", intArrayOf( 3, 4, 5, 3,-1,-1), isMoveable = true),
        "Sus2"   to ChordVoicing("G Shape", intArrayOf( 3, 0, 0, 0, 3, 3), isMoveable = true),
        "Sus4"   to ChordVoicing("G Shape", intArrayOf( 3, 3, 0, 0, 1, 3), isMoveable = true),
        "5"      to ChordVoicing("G Shape", intArrayOf( 3, 5, 5,-1,-1,-1), isMoveable = true),
        "Aug"    to ChordVoicing("G Shape", intArrayOf( 3, 2, 1, 0, 0, 3), isMoveable = true),
    )),

    // ── C shape ── home: C = semitone 0
    CagedBase(homeSemitone = 0, shapes = mapOf(
        "Major"  to ChordVoicing("C Shape", intArrayOf(-1, 3, 2, 0, 1, 0), isMoveable = true),
        "Minor"  to ChordVoicing("C Shape", intArrayOf(-1, 3, 1, 0, 1, 3), isMoveable = true),
        "Dom7"   to ChordVoicing("C Shape", intArrayOf(-1, 3, 2, 3, 1, 3), isMoveable = true),
        "Maj7"   to ChordVoicing("C Shape", intArrayOf(-1, 3, 2, 0, 0, 0), isMoveable = true),
        "Min7"   to ChordVoicing("C Shape", intArrayOf(-1, 3, 1, 3, 1, 3), isMoveable = true),
        "Dim"    to ChordVoicing("C Shape", intArrayOf(-1, 3, 4, 5, 4,-1), isMoveable = true),
        "Sus2"   to ChordVoicing("C Shape", intArrayOf(-1, 3, 0, 0, 3,-1), isMoveable = true),
        "Sus4"   to ChordVoicing("C Shape", intArrayOf(-1, 3, 3, 0, 1, 1), isMoveable = true),
        "5"      to ChordVoicing("C Shape", intArrayOf(-1, 3, 5, 5,-1,-1), isMoveable = true),
        "Aug"    to ChordVoicing("C Shape", intArrayOf(-1, 3, 2, 1, 1, 0), isMoveable = true),
    )),
)

fun getVoicings(root: String, type: String): List<ChordVoicing> {
    val targetSemitone = ROOT_SEMITONES[root] ?: return emptyList()
    return CAGED_SHAPES.mapNotNull { base ->
        val shape = base.shapes[type] ?: return@mapNotNull null
        val offset = (targetSemitone - base.homeSemitone + 12) % 12
        transposeVoicing(shape, offset)
    }
    .filter { v -> v.frets.filter { it > 0 }.all { it <= 14 } }
    .sortedBy { it.baseFret }
}
