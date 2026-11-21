package com.example.arabicapp.screens

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle

// Tiny markup for screen text:
//   **x** → bold
//   __x__ → bold + underline
// The text is passed through fixArabizi() first so digit-initial arabizi stays in place.
// For Arabic-script prepositions that are attached as a prefix (بـ / لـ / عـ), mark the
// whole word rather than just the letter, so the letters keep joining.
internal fun richText(s: String): AnnotatedString {
    val src = fixArabizi(s)
    return buildAnnotatedString {
        var i = 0
        while (i < src.length) {
            val bold = src.startsWith("**", i)
            // "___" (a fill-in blank) is literal text, not an underline marker
            val under = src.startsWith("__", i) && !src.startsWith("___", i) && (i == 0 || src[i - 1] != '_')
            if (bold || under) {
                val marker = if (bold) "**" else "__"
                val end = src.indexOf(marker, i + 2)
                if (end > i + 2) {
                    val style = if (bold) SpanStyle(fontWeight = FontWeight.Bold)
                    else SpanStyle(fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline)
                    withStyle(style) { append(src.substring(i + 2, end)) }
                    i = end + 2
                    continue
                }
            }
            append(src[i])
            i++
        }
    }
}

private val ARABIC_CHAR = Regex("[\\u0600-\\u06FF]")

// "عربي — arabizi" pair inside a line: Arabic run, " — ", then arabizi up to "(", " / ",
// ")", ",", " — ", ";", ".", "→" or end of line.
private val AR_ARABIZI_PAIR = Regex(
    "([\\u0600-\\u06FF][\\u0600-\\u06FF\\s]*?) — ([^—(/;\"),]+?)(?=\\s*(?:\\(|\\)|,| / | — |;|\\.|→|$))"
)

// "/ عربي (arabizi)" — a second form (usually the feminine) given after a slash.
private val SLASH_AR_PAREN = Regex("/ ([\\u0600-\\u06FF][\\u0600-\\u06FF\\s]*?) \\(([^()\\u0600-\\u06FF]+)\\)")

// Markup check that ignores "___" fill-in blanks.
private val MARKUP = Regex("\\*\\*|(?<!_)__(?!_)")

// "e.g. …" up to the closing parenthesis: example text, never bolded.
private val EG_CLAUSE = Regex("e\\.g\\.[^)]*")

// Bolds "عربي — arabizi" pairs and "/ عربي (arabizi)" forms, skipping "e.g. …" clauses.
private fun boldPairs(s: String): String {
    fun plain(t: String) = SLASH_AR_PAREN.replace(
        AR_ARABIZI_PAIR.replace(t) { m -> "**${m.groupValues[1]}** — **${m.groupValues[2]}**" }
    ) { m -> "/ **${m.groupValues[1]}** (**${m.groupValues[2]}**)" }
    val sb = StringBuilder()
    var last = 0
    for (m in EG_CLAUSE.findAll(s)) {
        sb.append(plain(s.substring(last, m.range.first)))
        sb.append(m.value)
        last = m.range.last + 1
    }
    sb.append(plain(s.substring(last)))
    return sb.toString()
}

// For dictionary-style lines "عربي — arabizi — English": bolds the Arabic and the arabizi.
// A non-indented line whose first segment has Arabic gets its first two segments bolded;
// otherwise every "Arabic — arabizi" pair inside the line is bolded (e.g. "  Reply: ...").
// Lines that already contain manual markup are left alone.
internal fun boldTermLine(s: String): String {
    if (s.contains("**") || s.contains("__")) return s
    if (!s.startsWith(" ")) {
        val parts = s.split(" — ")
        if (parts.size >= 3 && ARABIC_CHAR.containsMatchIn(parts[0])) {
            return "**${parts[0]}** — **${parts[1]}** — " + parts.drop(2).joinToString(" — ")
        }
    }
    return TERM_PAIR.replace(s) { m -> "**${m.groupValues[1]}** — **${m.groupValues[2]}**" }
}

// boldTermLine's pair pattern (Greetings): like AR_ARABIZI_PAIR but lets a comma stay inside the
// arabizi, so replies such as "منيح، نشكر الله — mnii7, nushkur allah" are bolded whole.
private val TERM_PAIR = Regex(
    "([\\u0600-\\u06FF][\\u0600-\\u06FF\\s]*?) — ([^—(/;\"]+?)(?=\\s*(?:\\(| / | — |;|\\.|, or |, Leb|→|$))"
)

// Arabizi head of a segment: everything before a "(" note, a ";" or a "/ عربي" variant, trimmed.
private val ARABIZI_HEAD = Regex("^(.*?)(\\s*(?:[(;]| / [\\u0600-\\u06FF]).*)?$")

private val LATIN_CHAR = Regex("[A-Za-z]")

// An arabizi-only alternate after a bolded form: "**kiifak?** (m) / kiifik? (f)" → bold "kiifik?".
private val ARABIZI_ALT = Regex(
    "\\*\\* (\\([^()]*\\) )?/ ([^\\s()*/\\u0600-\\u06FF][^()*/\\u0600-\\u06FF;]*?)(?=\\s+\\(|;|$)"
)

private fun boldArabiziAlternates(s: String): String {
    var cur = s
    while (true) {
        val next = ARABIZI_ALT.replace(cur) { m -> "** ${m.groupValues[1]}/ **${m.groupValues[2]}**" }
        if (next == cur) return cur
        cur = next
    }
}

// Bolds the headword of a dictionary line, plus every other Arabic/arabizi word the line
// introduces (feminine forms, "also:" / "formal:" variants). Formats:
//   "كبير / كبيرة (kbir / kbiire) — big"  → the whole Arabic+(arabizi) head before the first " — "
//   "كتير — ktiir — very …"                → the first two parts
//   "man — رجّال — rajjal (m) / … (note)"  → the Arabic and the arabizi (each ";  "-separated
//                                            segment handled separately)
// After the head, "عربي — arabizi" pairs, "/ عربي (arabizi)" forms and "(m) / arabizi (f)"
// alternates are bolded too; "e.g. …" clauses, "(pl: عربي arabizi)" notes and examples stay plain.
// Indented lines (sub-notes, info lines), example lines and lines with manual markup are left alone.
internal fun boldHeadwordLine(s: String): String {
    if (MARKUP.containsMatchIn(s) || s.startsWith(" ") || !s.contains(" — ")) return s
    if (s.startsWith("e.g.") || s.startsWith("Example")) return s
    val parts = s.split(" — ")
    if (ARABIC_CHAR.containsMatchIn(parts[0])) {
        // Prose or "label: عربي — arabizi" lines: only bold the pairs inside.
        if (parts[0].contains(":") || parts[0].length > 80 || !ARABIC_CHAR.matches(parts[0].take(1)))
            return boldArabiziAlternates(boldPairs(s))
        return boldArabiziAlternates(
            if (parts.size >= 3 && !parts[0].contains("(") && !ARABIC_CHAR.containsMatchIn(parts[1]))
                "**${parts[0]}** — **${parts[1]}** — " + boldPairs(parts.drop(2).joinToString(" — "))
            else "**${parts[0]}** — " + boldPairs(parts.drop(1).joinToString(" — "))
        )
    }
    return boldArabiziAlternates(s.split(";  ").joinToString(";  ") { seg ->
        val p = seg.split(" — ", limit = 3)
        if (p.size == 3 && !ARABIC_CHAR.containsMatchIn(p[0]) && ARABIC_CHAR.containsMatchIn(p[1]) &&
            !LATIN_CHAR.containsMatchIn(p[1]) && !ARABIC_CHAR.containsMatchIn(p[2].take(1))
        ) {
            val head = ARABIZI_HEAD.find(p[2])!!.groupValues[1].trimEnd()
            val rest = p[2].substring(head.length)
            if (head.isBlank()) "${p[0]} — **${p[1]}** — ${boldPairs(p[2])}"
            else "${p[0]} — **${p[1]}** — **$head**${boldPairs(rest)}"
        } else boldPairs(seg)
    })
}
