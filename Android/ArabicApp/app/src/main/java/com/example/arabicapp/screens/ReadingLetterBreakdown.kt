package com.example.arabicapp.screens

// Splits an Arabic word into its letters and works out, for each letter, which of its
// four shapes (alone / beginning / middle / end) is used in this word. Used by the
// "Examples Of Reading In Arabic" Words and Sentences screens.

internal enum class ReadingForm(val label: String) {
    ISOLATED("alone form"),
    INITIAL("beginning form"),
    MEDIAL("middle form"),
    FINAL("end form")
}

internal data class ReadingLetter(
    val letter: String,
    val name: String,
    val form: ReadingForm,
    val shape: String,
    val place: String,
    val sound: String,
    val hebrew: String,
    val notes: List<String>
)

// Letters not in ALL_LETTERS (hamza seats, ta marbuta, alef maqsura).
private val EXTRA_READING_LETTERS = listOf(
    ArabicLetter("أ", "Alef with hamza above", "a / u with a glottal stop (2)", "أ", "ـأ", "ـأ", isNonConnector = true),
    ArabicLetter("إ", "Alef with hamza below", "i with a glottal stop (2)", "إ", "ـإ", "ـإ", isNonConnector = true),
    ArabicLetter("آ", "Alef madda", "long aa (2aa)", "آ", "ـآ", "ـآ", isNonConnector = true),
    ArabicLetter("ؤ", "Hamza on waw", "glottal stop (2)", "ؤ", "ـؤ", "ـؤ", isNonConnector = true),
    ArabicLetter("ئ", "Hamza on ya (no dots)", "glottal stop (2)", "ئـ", "ـئـ", "ـئ"),
    ArabicLetter("ء", "Hamza (on the line)", "glottal stop (2)", "ء", "ء", "ء", isNonConnector = true),
    ArabicLetter("ة", "Ta marbuta", "-a / -e at the end of the word (-et / -it when a noun follows)", "ة", "ـة", "ـة", isNonConnector = true),
    ArabicLetter("ى", "Alef maqsura", "long a at the end (a ya without dots)", "ى", "ـى", "ـى", isNonConnector = true)
)

private val READING_LOOKUP: Map<Char, ArabicLetter> =
    (ALL_LETTERS + EXTRA_READING_LETTERS).associateBy { it.isolated.first() }

private val NAME_OVERRIDE = mapOf(
    'ط' to "Ṭa (emphatic t)",
    'ح' to "Ḥa (7)",
    'ه' to "Ha (soft h)",
    'ت' to "Ta"
)

private val SOUND_OVERRIDE = mapOf(
    'ا' to "long aa in the middle / end; at the start it only carries a vowel (a / i / u)",
    'و' to "w — or the long vowel uu / oo",
    'ي' to "y — or the long vowel ii / ei / e",
    'ث' to "th → t or s in spoken Levantine",
    'ذ' to "dh → d or z in spoken Levantine",
    'ظ' to "emphatic z / d",
    'ق' to "q → 2 (glottal stop) in city Levantine; rural Palestinian k, Jordanian / Bedouin g",
    'ه' to "h (at the end often silent or a soft -h; as an ending -o / -u = 'his')"
)

private val HEBREW_PARALLEL = mapOf(
    'ا' to "א", 'أ' to "א", 'إ' to "א", 'آ' to "א", 'ء' to "א", 'ؤ' to "א", 'ئ' to "א",
    'ب' to "ב", 'ت' to "ת", 'ث' to "ת׳ (th)", 'ج' to "ג׳", 'ح' to "ח",
    'خ' to "כ / ח׳ (kh)", 'د' to "ד", 'ذ' to "ד׳ (dh)", 'ر' to "ר", 'ز' to "ז",
    'س' to "ס", 'ش' to "ש", 'ص' to "צ (but said as an emphatic s)", 'ض' to "צ׳ (emphatic d)",
    'ط' to "ט", 'ظ' to "ט׳ (emphatic z)", 'ع' to "ע", 'غ' to "ע׳ (gh)", 'ف' to "פ",
    'ق' to "ק", 'ك' to "כ", 'ل' to "ל", 'م' to "מ", 'ن' to "נ", 'ه' to "ה",
    'و' to "ו", 'ي' to "י", 'ة' to "ה (like the ending of ילדה)", 'ى' to "ה / א ending"
)

private val DIACRITIC_NOTES = mapOf(
    'ّ' to "ّ  shadda on top: the letter is doubled (held longer / said twice)",
    'ً' to "ً  tanween: read -an at the end of the word (the alef under it is just a seat, not aa)",
    'ٌ' to "ٌ  tanween: read -un at the end",
    'ٍ' to "ٍ  tanween: read -in at the end",
    'َ' to "َ  fatha: a short a after this letter",
    'ُ' to "ُ  damma: a short u after this letter",
    'ِ' to "ِ  kasra: a short i after this letter",
    'ْ' to "ْ  sukun: no vowel after this letter"
)

private val ALEF_FAMILY = setOf('ا', 'أ', 'إ', 'آ')

internal fun readingBreakdown(word: String): List<ReadingLetter> {
    val letters = mutableListOf<Char>()
    val marks = mutableListOf<MutableList<Char>>()
    for (c in word) {
        when {
            c in DIACRITIC_NOTES -> marks.lastOrNull()?.add(c)
            c in READING_LOOKUP -> { letters.add(c); marks.add(mutableListOf()) }
            else -> Unit // tatweel, spaces, bidi marks, punctuation
        }
    }
    val last = letters.lastIndex
    return letters.mapIndexed { i, c ->
        val info = READING_LOOKUP.getValue(c)
        val prevChar = letters.getOrNull(i - 1)
        val prev = prevChar?.let { READING_LOOKUP.getValue(it) }
        val nextChar = letters.getOrNull(i + 1)
        val joinsPrev = prev != null && !prev.isNonConnector && c != 'ء'
        val joinsNext = nextChar != null && !info.isNonConnector && nextChar != 'ء'
        val form = when {
            joinsPrev && joinsNext -> ReadingForm.MEDIAL
            joinsNext -> ReadingForm.INITIAL
            joinsPrev -> ReadingForm.FINAL
            else -> ReadingForm.ISOLATED
        }
        val shape = when (form) {
            ReadingForm.ISOLATED -> info.isolated
            ReadingForm.INITIAL -> info.initialForm
            ReadingForm.MEDIAL -> info.medialForm
            ReadingForm.FINAL -> info.finalForm
        }
        val name = NAME_OVERRIDE[c] ?: info.name
        val place = when {
            letters.size == 1 -> "the only letter"
            i == 0 -> "first letter"
            i == last -> "last letter"
            else -> "middle of the word"
        }
        val notes = mutableListOf<String>()
        val prevName = prevChar?.let { NAME_OVERRIDE[it] ?: READING_LOOKUP.getValue(it).name }
        if (letters.size > 1) {
            when {
                i == 0 && form == ReadingForm.ISOLATED ->
                    notes += "First letter, but it keeps its alone form: $name never connects to the letter after it."
                i in 1 until last && form == ReadingForm.FINAL ->
                    notes += "In the middle of the word, but it uses its END form: it joins the letter before it, and $name never joins the letter after it."
                i in 1 until last && form == ReadingForm.INITIAL ->
                    notes += "In the middle of the word, but it uses its BEGINNING form: the letter before it ($prevName) never connects forward, so the writing 'restarts' here."
                i in 1 until last && form == ReadingForm.ISOLATED ->
                    notes += "In the middle of the word, but in its ALONE form: $prevName before it doesn't connect forward, and $name doesn't connect forward either."
                i == last && form == ReadingForm.ISOLATED ->
                    notes += "Last letter in its ALONE form: the letter before it ($prevName) never connects forward."
            }
            if (info.isNonConnector && i < last) {
                notes += "Doesn't connect to the next letter — the next letter starts a new piece (a small gap appears)."
            }
        }
        if (c == 'ل' && nextChar in ALEF_FAMILY) {
            notes += "ل + ا are written together as one special shape: لا (lam-alef)."
        }
        if (c in ALEF_FAMILY && prevChar == 'ل') {
            notes += "Joined with the ل before it into the lam-alef shape لا."
        }
        marks[i].forEach { m -> notes += DIACRITIC_NOTES.getValue(m) }
        ReadingLetter(
            letter = c.toString(),
            name = name,
            form = form,
            shape = shape,
            place = place,
            sound = SOUND_OVERRIDE[c] ?: info.sound,
            hebrew = HEBREW_PARALLEL[c] ?: "—",
            notes = notes
        )
    }
}
