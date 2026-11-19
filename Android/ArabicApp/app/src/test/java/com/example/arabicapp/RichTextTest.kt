package com.example.arabicapp

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.example.arabicapp.screens.boldTermLine
import com.example.arabicapp.screens.richText
import org.junit.Assert.assertEquals
import org.junit.Test

class RichTextTest {
    @Test fun boldAndUnderlineMarkup() {
        val t = richText("I was __at__ work **from** 8")
        assertEquals("I was at work from 8", t.text)
        val spans = t.spanStyles.sortedBy { it.start }
        assertEquals(2, spans.size)
        assertEquals("at", t.text.substring(spans[0].start, spans[0].end))
        assertEquals(TextDecoration.Underline, spans[0].item.textDecoration)
        assertEquals("from", t.text.substring(spans[1].start, spans[1].end))
        assertEquals(FontWeight.Bold, spans[1].item.fontWeight)
    }

    @Test fun unmatchedMarkerIsKeptLiterally() = assertEquals("a ** b", richText("a ** b").text)

    @Test fun topLevelLineBoldsArabicAndArabizi() = assertEquals(
        "**مبروك** — **mabruk** — Congratulations!",
        boldTermLine("مبروك — mabruk — Congratulations!")
    )

    @Test fun indentedReplyBoldsPairsOnly() = assertEquals(
        "  Reply: **الله يسلمك** — **Allah ysallmak** (to a man) / x",
        boldTermLine("  Reply: الله يسلمك — Allah ysallmak (to a man) / x")
    )

    @Test fun englishOnlyLineUntouched() {
        val s = "Most of these are call-and-response — a fixed reply."
        assertEquals(s, boldTermLine(s))
    }
}
