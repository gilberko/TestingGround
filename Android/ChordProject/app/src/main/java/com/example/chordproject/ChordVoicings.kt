package com.example.chordproject

data class ChordVoicing(
    val label: String,
    val frets: IntArray,   // 6 values, low E → high e: -1=muted, 0=open, N=absolute fret number
    val baseFret: Int = 1  // diagram top row = this fret; 1 → draw nut, else → print "Nfr" label
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ChordVoicing) return false
        return label == other.label && frets.contentEquals(other.frets) && baseFret == other.baseFret
    }
    override fun hashCode(): Int = 31 * (31 * label.hashCode() + frets.contentHashCode()) + baseFret
}

private val ROOT_SEMITONES = mapOf(
    "C" to 0, "C#" to 1, "D" to 2, "D#" to 3, "E" to 4, "F" to 5,
    "F#" to 6, "G" to 7, "G#" to 8, "A" to 9, "A#" to 10, "B" to 11
)

private fun transposeVoicing(v: ChordVoicing, semitones: Int): ChordVoicing {
    if (semitones == 0) return v
    val newFrets = IntArray(6) { i -> val f = v.frets[i]; if (f <= 0) f else f + semitones }
    val minFret = newFrets.filter { it > 0 }.minOrNull() ?: 1
    return v.copy(frets = newFrets, baseFret = minFret)
}

private val C_VOICINGS: Map<String, List<ChordVoicing>> = mapOf(
    "Major" to listOf(
        ChordVoicing("Open",       intArrayOf(-1, 3, 2, 0, 1, 0), baseFret = 1),
        ChordVoicing("Barre 3rd",  intArrayOf(-1, -1, 5, 5, 5, 3), baseFret = 3),
        ChordVoicing("Barre 8th",  intArrayOf(8, 10, 10, 9, 8, 8), baseFret = 8)
    ),
    "Minor" to listOf(
        ChordVoicing("Barre 3rd",  intArrayOf(-1, -1, 5, 5, 4, 3), baseFret = 3),
        ChordVoicing("Alt 3rd",    intArrayOf(-1, 3, 5, 5, 4, 3), baseFret = 3),
        ChordVoicing("Barre 8th",  intArrayOf(8, 10, 10, 9, 8, 8), baseFret = 8)
    ),
    "Dom7" to listOf(
        ChordVoicing("Open",       intArrayOf(-1, 3, 2, 3, 1, 0), baseFret = 1),
        ChordVoicing("Barre 8th",  intArrayOf(8, 10, 8, 9, 8, 8), baseFret = 8),
        ChordVoicing("Alt",        intArrayOf(-1, -1, 5, 3, 5, 3), baseFret = 3)
    ),
    "Maj7" to listOf(
        ChordVoicing("Open",       intArrayOf(-1, 3, 2, 0, 0, 0), baseFret = 1),
        ChordVoicing("Barre 8th",  intArrayOf(8, 10, 9, 9, 8, 8), baseFret = 8),
        ChordVoicing("Alt",        intArrayOf(-1, -1, 5, 4, 5, 4), baseFret = 4)
    ),
    "Min7" to listOf(
        ChordVoicing("Barre 3rd",  intArrayOf(-1, -1, 5, 3, 4, 3), baseFret = 3),
        ChordVoicing("Barre 8th",  intArrayOf(8, 10, 8, 9, 8, 8), baseFret = 8),
        ChordVoicing("Open-style", intArrayOf(-1, 3, 1, 3, 1, 3), baseFret = 1)
    ),
    "Dim" to listOf(
        ChordVoicing("Position 1", intArrayOf(-1, -1, 2, 3, 2, 0), baseFret = 1),
        ChordVoicing("Position 2", intArrayOf(-1, 3, 4, 3, 2, -1), baseFret = 2),
        ChordVoicing("Barre",      intArrayOf(-1, -1, 5, 6, 5, 3), baseFret = 3)
    ),
    "Sus2" to listOf(
        ChordVoicing("Open",       intArrayOf(-1, 3, 0, 0, 3, -1), baseFret = 1),
        ChordVoicing("Barre 3rd",  intArrayOf(-1, -1, 5, 5, 3, 3), baseFret = 3),
        ChordVoicing("High",       intArrayOf(-1, -1, 10, 10, 8, 8), baseFret = 8)
    ),
    "Sus4" to listOf(
        ChordVoicing("Open",       intArrayOf(-1, 3, 3, 0, 1, 1), baseFret = 1),
        ChordVoicing("Barre 3rd",  intArrayOf(-1, -1, 5, 5, 6, 3), baseFret = 3),
        ChordVoicing("High",       intArrayOf(-1, -1, 10, 10, 11, 8), baseFret = 8)
    ),
    "5" to listOf(
        ChordVoicing("Open",       intArrayOf(-1, 3, 5, 5, -1, -1), baseFret = 1),
        ChordVoicing("Barre 3rd",  intArrayOf(-1, -1, -1, 5, 3, -1), baseFret = 3),
        ChordVoicing("Low E",      intArrayOf(8, 10, 10, -1, -1, -1), baseFret = 8)
    ),
    "Aug" to listOf(
        ChordVoicing("Position 1", intArrayOf(-1, 3, 2, 1, 1, 0), baseFret = 1),
        ChordVoicing("Position 2", intArrayOf(-1, -1, 2, 1, 1, 4), baseFret = 1),
        ChordVoicing("Barre",      intArrayOf(-1, -1, 6, 5, 5, 4), baseFret = 4)
    )
)

fun getVoicings(root: String, type: String): List<ChordVoicing> {
    val semitones = ROOT_SEMITONES[root] ?: return emptyList()
    val baseShapes = C_VOICINGS[type] ?: return emptyList()
    return baseShapes
        .map { transposeVoicing(it, semitones) }
        .filter { v -> v.frets.filter { it > 0 }.all { it <= 14 } }
}
