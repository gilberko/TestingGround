package com.example.arabicapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CommonVerbsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        CVBodyText("Verbs are listed in the past tense (3rd person masculine singular — \"he did\"), which is the standard Arabic dictionary form. Where two English verbs share the same Palestinian Arabic word, both are shown together.")

        CVSectionHeader("Movement")
        CVBodyText("to walk — مشى — masha")
        CVBodyText("to run — ركض — rakad")
        CVBodyText("to go — راح — raḥ")
        CVBodyText("to return — رجع — rija'")
        CVBodyText("to drive — ساق — sa' (qaf → glottal stop)")
        CVBodyText("to stop / to stand — وقف — wi'if")
        CVBodyText("  (same word; context distinguishes: \"wi'if!\" = Stop! / \"wi'if 'ala rijleih\" = standing on his feet)")

        CVSectionHeader("Communication")
        CVBodyText("to speak / to talk — حكى — ḥaka (same word for both)")
        CVBodyText("to say — قال — 'aal (qaf → glottal stop)")
        CVBodyText("to shout / to yell — صرخ — ṣarakh (same word for both)")
        CVBodyText("to hear / to listen — سمع — simi'")
        CVBodyText("  (same word in everyday Palestinian; نصّت naṣṣat = to pay close attention, more formal)")

        CVSectionHeader("Daily Life")
        CVBodyText("to eat — أكل — akal")
        CVBodyText("to drink — شرب — shirib")
        CVBodyText("to sleep — نام — naam")
        CVBodyText("to sit — قعد — 'a'ad (both ق → glottal stop)")
        CVBodyText("to rest — ارتاح — irtaaḥ")
        CVBodyText("to watch — شاف — shaaf (lit. \"saw / watched\")")
        CVBodyText("to turn on — ولّع — walla' (lit. \"ignited\")")
        CVBodyText("to turn off — طفّى — ṭaffa")

        CVSectionHeader("Actions & Tasks")
        CVBodyText("to open — فتح — fataḥ")
        CVBodyText("to close — سكّر — sakkar (colloquial; lit. \"locked / sealed\")")
        CVBodyText("to take — أخذ — aakhadh")
        CVBodyText("to give — عطى — 'aṭa")
        CVBodyText("to do / to make — عمل — 'amil (same word for both)")
        CVBodyText("to prepare — جهّز — jahaz")
        CVBodyText("to paint / to draw — رسم — rasam")
        CVBodyText("to read — قرأ — 'ara (qaf → glottal stop)")
        CVBodyText("to write — كتب — katab")
        CVBodyText("to study — درس — daras")
        CVBodyText("to teach — علّم — 'allam")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun CVSectionHeader(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    HorizontalDivider()
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun CVBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
