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
            val under = src.startsWith("__", i)
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
// " — ", ";", ".", ", or ", ", Leb", "→" or end of line.
private val AR_ARABIZI_PAIR = Regex(
    "([\\u0600-\\u06FF][\\u0600-\\u06FF\\s]*?) — ([^—(/;\"]+?)(?=\\s*(?:\\(| / | — |;|\\.|, or |, Leb|→|$))"
)

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
    return AR_ARABIZI_PAIR.replace(s) { m -> "**${m.groupValues[1]}** — **${m.groupValues[2]}**" }
}
