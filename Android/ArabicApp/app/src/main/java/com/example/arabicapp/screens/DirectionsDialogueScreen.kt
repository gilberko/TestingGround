package com.example.arabicapp.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle

private data class DirectionsDLine(
    val speaker: String,
    val ar: String,
    val translit: String,
    val en: String
)

private val directionsDialogue = listOf(
    DirectionsDLine("T (tourist — Marco)", "بعذرك، وين الملعب؟", "ba'zrak, wein el-mal'ab?", "Excuse me, where is the stadium?"),
    DirectionsDLine("L (local — Ahmad)", "الملعب؟ شوي بعيد من هون ماشي، يمكن أربعين دقيقة", "el-mal'ab? shwai ba'id min hon mashi, ymkan arba'in daʔiʔa", "The stadium? It's a bit far on foot, maybe forty minutes."),
    DirectionsDLine("T (tourist — Marco)", "أربعين دقيقة؟ في طريق أقصر؟", "arba'in daʔiʔa? fi tariʔ aʔsar?", "Forty minutes? Is there a shorter way?"),
    DirectionsDLine("L (local — Ahmad)", "إذا حابب تمشي، روح دغري، بعدين خد يمين عند الجامع الكبير، وامشي دغري تلاقيه", "iza Habiib timshi, ruH dughri, ba'dein khod yameen 'and el-jami' el-kbir, w-imshi dughri tlaaʔiih", "If you want to walk, go straight, then turn right at the big mosque, and keep walking — you'll find it."),
    DirectionsDLine("T (tourist — Marco)", "في باص بروح على الملعب؟", "fi bas baruH 'ala el-mal'ab?", "Is there a bus to the stadium?"),
    DirectionsDLine("L (local — Ahmad)", "أيوا، في محطة باص على بُعد خمس دقايق من هون", "aywa, fi maHaTTa bas 'ala bo'd khamis daʔayeʔ min hon", "Yes, there's a bus stop about five minutes from here."),
    DirectionsDLine("L (local — Ahmad)", "خد الخط ستين، بيوقف قدام الملعب", "khod el-khaT sittin, biywaʔʔaf 'uddaam el-mal'ab", "Take line 60, it stops right in front of the stadium."),
    DirectionsDLine("T (tourist — Marco)", "كيف بوصل على المحطة؟", "kif buwSal 'ala el-maHaTTa?", "How do I get to the bus stop?"),
    DirectionsDLine("L (local — Ahmad)", "روح دغري بهالشارع، بعدين خد شمال عند الإشارة الأولى، المحطة على إيدك اليمين", "ruH dughri b-hal-share', ba'dein khod shimaal 'and el-ishara el-oola, el-maHaTTa 'ala idak el-yameen", "Go straight down this street, then turn left at the first traffic light — the stop is on your right."),
    DirectionsDLine("T (tourist — Marco)", "وين بشتري التذكرة؟", "wein bashtri el-tazkara?", "Where do I buy the ticket?"),
    DirectionsDLine("L (local — Ahmad)", "بتشتريها من السوفر لمّا تطلع على الباص", "btishtriiha min el-sofar lamma tTla' 'ala el-bas", "You buy it from the driver when you get on the bus."),
    DirectionsDLine("T (tourist — Marco)", "بكم التذكرة؟", "b'kam el-tazkara?", "How much is the ticket?"),
    DirectionsDLine("L (local — Ahmad)", "سبع شواقل", "sab' shawaʔel", "Seven shekels."),
    DirectionsDLine("T (tourist — Marco)", "شكراً كتير! مع السلامة!", "shukran ktir! ma' es-salame!", "Thank you very much! Goodbye!"),
    DirectionsDLine("L (local — Ahmad)", "أهلاً وسهلاً! ربّك معك!", "ahlan wa-sahlan! rabbak ma'ak!", "You're welcome! God be with you!")
)

@Composable
fun DirectionsDialogueScreen() {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item { DirectionsSection("Arabic & Pronunciation") }
        items(directionsDialogue) { line ->
            DirectionsCard(line = line, showArabic = true)
        }
        item { DirectionsSection("English Translation") }
        items(directionsDialogue) { line ->
            DirectionsCard(line = line, showArabic = false)
        }
    }
}

@Composable
private fun DirectionsSection(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
    )
}

@Composable
private fun DirectionsCard(line: DirectionsDLine, showArabic: Boolean) {
    val containerColor = if (line.speaker.startsWith("T"))
        MaterialTheme.colorScheme.secondaryContainer
    else
        MaterialTheme.colorScheme.tertiaryContainer

    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Text(
            text = line.speaker,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 12.dp, top = 8.dp, end = 12.dp)
        )
        if (showArabic) {
            Text(
                text = line.ar,
                style = TextStyle(textDirection = TextDirection.Rtl),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 2.dp)
            )
            Text(
                text = line.translit,
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
            )
        } else {
            Text(
                text = line.en,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp, top = 2.dp)
            )
        }
    }
}
