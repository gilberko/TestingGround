package com.example.japantravel.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private const val CELIAC_CARD_JAPANESE =
    "私はセリアック病(グルテン不耐症)です。\n\n" +
        "小麦・大麦・ライ麦などグルテンを含む食品を食べることができません。\n\n" +
        "パン、麺類、天ぷらの衣、醤油(小麦を含むことが多いです)にもご注意ください。\n\n" +
        "グルテンを含まない食材で調理していただけますか?\n\n" +
        "ご協力よろしくお願いいたします。"

@Composable
fun CeliacCardScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Celiac Card", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "Show this card to restaurant or hotel staff.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(12.dp))
            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = CELIAC_CARD_JAPANESE,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(20.dp)
                )
            }
            Spacer(Modifier.height(24.dp))
            Text(
                text = "Other resources",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(Modifier.height(8.dp))
            Text(
                text = "For a professionally vetted, printable Japanese celiac/gluten-free card, " +
                    "see Celiac Travel (celiactravel.com), Equal Eats (equaleats.com), or " +
                    "OpenKyoto's free downloadable PDF (openkyoto.com).",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
