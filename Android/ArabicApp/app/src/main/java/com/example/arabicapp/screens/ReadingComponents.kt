package com.example.arabicapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

internal const val REGION_LEV = "Levantine (all)"
internal const val REGION_PAL = "Palestinian"
internal const val REGION_PAL_JOR = "Palestinian / Jordanian"
internal const val REGION_LEB_SYR = "Lebanese / Syrian"
internal const val REGION_FORMAL = "Formal + spoken"
internal const val REGION_ARAB_WIDE = "Proverb — all Arab countries"

@Composable
internal fun ReadingNoteCard(text: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Text(
            text = fixArabizi(text),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Composable
internal fun ReadingRegionTag(region: String) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Text(
            text = region,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Composable
internal fun ReadingBigArabic(text: String, sizeSp: Int = 34) {
    Text(
        text = text,
        fontSize = sizeSp.sp,
        lineHeight = (sizeSp * 1.5).sp,
        textAlign = TextAlign.End,
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    )
}

@Composable
internal fun ReadingExpandHint(expanded: Boolean, collapsedText: String) {
    Text(
        text = if (expanded) "▲ Hide" else "▼ $collapsedText",
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 6.dp)
    )
}

@Composable
internal fun ReadingLetterBreakdown(word: String) {
    val letters = remember(word) { readingBreakdown(word) }
    Column(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
        Text(
            text = "The letters, read right to left — each in the shape it has in this word:",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold
        )
        ReadingBigArabic(letters.joinToString("   ") { it.shape }, sizeSp = 28)
        HorizontalDivider()
        letters.forEachIndexed { i, l -> ReadingLetterRow(i + 1, l) }
    }
}

@Composable
private fun ReadingLetterRow(number: Int, l: ReadingLetter) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = l.shape,
            fontSize = 30.sp,
            lineHeight = 44.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(64.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "$number. ${l.name}  (${l.letter})",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "${l.place.replaceFirstChar { it.uppercase() }} · ${l.form.label}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = fixArabizi("Sound: ${l.sound}"),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Hebrew: ${l.hebrew}",
                style = MaterialTheme.typography.bodySmall
            )
            l.notes.forEach { note ->
                Text(
                    text = "• $note",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
    HorizontalDivider(thickness = 0.5.dp)
}

@Composable
internal fun ReadingSpacerBottom() {
    Spacer(modifier = Modifier.height(24.dp))
}
