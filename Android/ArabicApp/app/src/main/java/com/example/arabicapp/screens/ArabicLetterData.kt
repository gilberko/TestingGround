package com.example.arabicapp.screens

internal data class ArabicLetter(
    val isolated: String,
    val name: String,
    val sound: String,
    val initialForm: String,
    val medialForm: String,
    val finalForm: String,
    val isNonConnector: Boolean = false
)

internal val ALL_LETTERS = listOf(
    ArabicLetter("ا", "Alef", "aa / glottal stop (2)", "ا", "ـا", "ـا", isNonConnector = true),
    ArabicLetter("ب", "Ba", "b", "بـ", "ـبـ", "ـب"),
    ArabicLetter("ت", "Ta", "t", "تـ", "ـتـ", "ـت"),
    ArabicLetter("ث", "Tha", "th → t or s", "ثـ", "ـثـ", "ـث"),
    ArabicLetter("ج", "Jim", "j", "جـ", "ـجـ", "ـج"),
    ArabicLetter("ح", "Ha", "7 (breathy h, from throat)", "حـ", "ـحـ", "ـح"),
    ArabicLetter("خ", "Kha", "kh / 5 (like Scottish 'loch')", "خـ", "ـخـ", "ـخ"),
    ArabicLetter("د", "Dal", "d", "د", "ـد", "ـد", isNonConnector = true),
    ArabicLetter("ذ", "Dhal", "dh → d", "ذ", "ـذ", "ـذ", isNonConnector = true),
    ArabicLetter("ر", "Ra", "r (rolled/trilled)", "ر", "ـر", "ـر", isNonConnector = true),
    ArabicLetter("ز", "Zain", "z", "ز", "ـز", "ـز", isNonConnector = true),
    ArabicLetter("س", "Sin", "s", "سـ", "ـسـ", "ـس"),
    ArabicLetter("ش", "Shin", "sh", "شـ", "ـشـ", "ـش"),
    ArabicLetter("ص", "Sad", "s (emphatic s)", "صـ", "ـصـ", "ـص"),
    ArabicLetter("ض", "Dad", "d (emphatic d)", "ضـ", "ـضـ", "ـض"),
    ArabicLetter("ط", "Ta", "t (emphatic t)", "طـ", "ـطـ", "ـط"),
    ArabicLetter("ظ", "Dha", "z → emphatic z or d", "ظـ", "ـظـ", "ـظ"),
    ArabicLetter("ع", "Ain", "3 (voiced throat squeeze)", "عـ", "ـعـ", "ـع"),
    ArabicLetter("غ", "Ghain", "gh (gargled r)", "غـ", "ـغـ", "ـغ"),
    ArabicLetter("ف", "Fa", "f", "فـ", "ـفـ", "ـف"),
    ArabicLetter("ق", "Qaf", "q → glottal stop (2)", "قـ", "ـقـ", "ـق"),
    ArabicLetter("ك", "Kaf", "k", "كـ", "ـكـ", "ـك"),
    ArabicLetter("ل", "Lam", "l", "لـ", "ـلـ", "ـل"),
    ArabicLetter("م", "Mim", "m", "مـ", "ـمـ", "ـم"),
    ArabicLetter("ن", "Nun", "n", "نـ", "ـنـ", "ـن"),
    ArabicLetter("ه", "Ha", "h", "هـ", "ـهـ", "ـه"),
    ArabicLetter("و", "Waw", "w", "و", "ـو", "ـو", isNonConnector = true),
    ArabicLetter("ي", "Ya", "y", "يـ", "ـيـ", "ـي")
)
