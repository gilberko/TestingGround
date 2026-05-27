package com.example.arabicapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp

private data class MarketMeetingLine(val speaker: String, val ar: String, val translit: String, val en: String)

private val marketMeetingDialogue = listOf(
    MarketMeetingLine("T", "مرحبا!", "Marhaba!", "Hello!"),
    MarketMeetingLine("L", "هلا، أهلاً!", "Hala, ahlan!", "Hi, welcome!"),
    MarketMeetingLine("T", "شو اسمك؟", "Shu ismak?", "What's your name?"),
    MarketMeetingLine("L", "اسمي أحمد. وإنتا، شو اسمك؟", "Ismi Ahmad. W inta, shu ismak?", "My name is Ahmad. And you, what's your name?"),
    MarketMeetingLine("T", "اسمي ماركو. تشرّفنا.", "Ismi Marco. Tsharrafna.", "My name is Marco. Nice to meet you."),
    MarketMeetingLine("L", "تشرّفنا! من وين إنتا؟", "Tsharrafna! Min wein inta?", "Nice to meet you! Where are you from?"),
    MarketMeetingLine("T", "أنا من إيطاليا. وإنتا؟", "Ana min Italia. W inta?", "I'm from Italy. And you?"),
    MarketMeetingLine("L", "أنا من هون، من البلد.", "Ana min hon, min el-balad.", "I'm from here, from the city."),
    MarketMeetingLine("T", "الشوق كتير حلو! هاي أول مرة بيجي هون.", "El-shuk ktir hilu! Hayi awwal marra biji hon.", "The market is very beautiful! This is my first time here."),
    MarketMeetingLine("L", "أهلاً فيك! بدك تشتري شي؟", "Ahlan fik! Biddak tishtiri shi?", "Welcome! Do you want to buy something?"),
    MarketMeetingLine("T", "أيوا، بس ما بعرف من وين أبدأ.", "Aywa, bas ma ba'rif min wein abda.", "Yes, but I don't know where to start."),
    MarketMeetingLine("L", "لازم تشتري زعتر — هون أحسن زعتر بالمنطقة.", "Lazem tishtiri za'tar — hon ahsan za'tar bi-el-mantika.", "You must buy za'atar — here is the best za'atar in the region."),
    MarketMeetingLine("T", "والزيتون؟", "W el-zeitun?", "And olives?"),
    MarketMeetingLine("L", "أيوا! الزيتون والزيت هون أحلى شي.", "Aywa! El-zeitun w el-zeit hon ahla shi.", "Yes! Olives and olive oil here are the best thing."),
    MarketMeetingLine("T", "ووين بشتري كنافة؟ بدي أجرب.", "W wein bishtiri knafe? Biddi ujrib.", "And where do I buy knafeh? I want to try it."),
    MarketMeetingLine("L", "روح عالمحل اللي قدام البوابة — أحسن كنافة بالبلد.", "Ruh 'al-mahall illi 'uddam el-bawwabe — ahsan knafe bi-el-balad.", "Go to the shop in front of the gate — the best knafeh in town."),
    MarketMeetingLine("T", "شكراً كتير، أحمد! إنتا كتير لطيف.", "Shukran ktir, Ahmad! Inta ktir latif.", "Thank you very much, Ahmad! You are very kind."),
    MarketMeetingLine("L", "أهلاً فيك! تشرّفنا.", "Ahlan fik! Tsharrafna.", "You're welcome! Nice meeting you.")
)

@Composable
private fun MarketMeetingSection(title: String) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(4.dp))
    HorizontalDivider()
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun MarketMeetingCard(line: MarketMeetingLine, showArabic: Boolean) {
    val containerColor = when (line.speaker) {
        "T" -> MaterialTheme.colorScheme.secondaryContainer
        else -> MaterialTheme.colorScheme.tertiaryContainer
    }
    val labelColor = when (line.speaker) {
        "T" -> MaterialTheme.colorScheme.onSecondaryContainer
        else -> MaterialTheme.colorScheme.onTertiaryContainer
    }
    val speakerLabel = when (line.speaker) {
        "T" -> "T (tourist — Marco)"
        else -> "L (local — Ahmad)"
    }
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = speakerLabel,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = labelColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (showArabic) {
                Text(
                    text = line.ar,
                    style = MaterialTheme.typography.bodyLarge.copy(textDirection = TextDirection.Rtl),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = line.translit,
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                Text(
                    text = line.en,
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun MarketMeetingScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item { MarketMeetingSection("Arabic & Pronunciation") }
        items(marketMeetingDialogue) { line ->
            MarketMeetingCard(line = line, showArabic = true)
            Spacer(modifier = Modifier.height(6.dp))
        }
        item { MarketMeetingSection("English Translation") }
        items(marketMeetingDialogue) { line ->
            MarketMeetingCard(line = line, showArabic = false)
            Spacer(modifier = Modifier.height(6.dp))
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}
