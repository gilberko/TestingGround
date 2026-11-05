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
    DirectionsDLine("T (tourist — Marco)", "بعذرك، وين الملعب؟", "ba3zrak, wein el-mal3ab?", "Excuse me, where is the stadium?"),
    DirectionsDLine("L (local — Ahmad)", "الملعب؟ شوي بعيد من هون ماشي، يمكن أربعين دقيقة", "el-mal3ab? shwai ba3id min hon mashi, ymkan arba3in da2i2a", "The stadium? It's a bit far on foot, maybe forty minutes."),
    DirectionsDLine("T (tourist — Marco)", "أربعين دقيقة؟ في طريق أقصر؟", "arba3in da2i2a? fi tari2 a2sar?", "Forty minutes? Is there a shorter way?"),
    DirectionsDLine("L (local — Ahmad)", "إذا حابب تمشي، روح دغري، بعدين خد يمين عند الجامع الكبير، وامشي دغري تلاقيه", "iza 7abiib timshi, ru7 dughri, ba3dein khod yameen 3and el-jami3 el-kbir, w-imshi dughri tlaa2iih", "If you want to walk, go straight, then turn right at the big mosque, and keep walking — you'll find it."),
    DirectionsDLine("T (tourist — Marco)", "في باص بروح على الملعب؟", "fi bas baru7 3ala el-mal3ab?", "Is there a bus to the stadium?"),
    DirectionsDLine("L (local — Ahmad)", "أيوا، في محطة باص على بُعد خمس دقايق من هون", "aywa, fi ma7atta bas 3ala bo3d khamis da2aye2 min hon", "Yes, there's a bus stop about five minutes from here."),
    DirectionsDLine("L (local — Ahmad)", "خد الخط ستين، بيوقف قدام الملعب", "khod el-khat sittin, biywa22if 2uddaam el-mal3ab", "Take line 60, it stops right in front of the stadium."),
    DirectionsDLine("T (tourist — Marco)", "كيف بوصل على المحطة؟", "kif buwsal 3ala el-ma7atta?", "How do I get to the bus stop?"),
    DirectionsDLine("L (local — Ahmad)", "روح دغري بهالشارع، بعدين خد شمال عند الإشارة الأولى، المحطة على إيدك اليمين", "ru7 dughri b-hal-share3, ba3dein khod shimaal 3and el-ishara el-oola, el-ma7atta 3ala idak el-yameen", "Go straight down this street, then turn left at the first traffic light — the stop is on your right."),
    DirectionsDLine("T (tourist — Marco)", "وين بشتري التذكرة؟", "wein bashtri el-tazkara?", "Where do I buy the ticket?"),
    DirectionsDLine("L (local — Ahmad)", "بتشتريها من السوفر لمّا تطلع على الباص", "btishtriiha min el-sofar lamma ttla3 3ala el-bas", "You buy it from the driver when you get on the bus."),
    DirectionsDLine("T (tourist — Marco)", "بكم التذكرة؟", "bikam el-tazkara?", "How much is the ticket?"),
    DirectionsDLine("L (local — Ahmad)", "سبع شواقل", "sab3 shawa2el", "Seven shekels."),
    DirectionsDLine("T (tourist — Marco)", "شكراً كتير! مع السلامة!", "shukran ktir! ma3 es-salame!", "Thank you very much! Goodbye!"),
    DirectionsDLine("L (local — Ahmad)", "أهلاً وسهلاً! ربّك معك!", "ahlan wa-sahlan! rabbak ma3ak!", "You're welcome! God be with you!")
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
