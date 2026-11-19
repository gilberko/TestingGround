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

private data class TrainStationDLine(val speaker: String, val ar: String, val translit: String, val en: String)

private val trainStationDialogue = listOf(
    TrainStationDLine("M", "لو سمحت! لو سمحت! إنتَ بتشتغل هون؟", "Law sama7t! Law sama7t! Inta btishtghil hoon?", "Excuse me! Excuse me! Do you work here?"),
    TrainStationDLine("N", "أيوا، أنا بشتغل بالمحطّة. شو في؟", "Aywa, ana bishtghil bil-ma7aTTa. Shu fi?", "Yes, I work at the station. What's up?"),
    TrainStationDLine("M", "لازم أطلع بالقطار اللي رايح عالشام، ومش لاقيه!", "Laazim aTla3 bil-2iTaar elli raayi7 3a-sh-Shaam, w mish laa2ii!", "I have to get on the train to Damascus, and I can't find it!"),
    TrainStationDLine("N", "عالشام؟", "3a-sh-Shaam?", "To Damascus?"),
    TrainStationDLine("M", "أيوا، دمشق! بدّه يطلع بعد اتنعشر دقيقة!", "Aywa, Dimashq! Biddo yiTla3 ba3d itna3sh da2ii2a!", "Yes, Damascus! It's supposed to leave in twelve minutes!"),
    TrainStationDLine("N", "طوّل بالك، طوّل بالك. فرجيني التذكرة.", "Tawwil baalak, Tawwil baalak. Farjiini et-tazkara.", "Easy, easy. Show me the ticket."),
    TrainStationDLine("M", "هيّاها.", "Hayyaaha.", "Here it is."),
    TrainStationDLine("N", "آه، هاد القطار بيطلع من رصيف رقم سبعة.", "Aah, hada el-2iTaar biyiTla3 min raSiif ra2am sab3a.", "Ah, this train leaves from platform number seven."),
    TrainStationDLine("M", "سبعة؟! أنا كنت على رصيف تلاتة، ودوّرت بكل المحطّة!", "Sab3a?! Ana kunt 3ala raSiif talaate, w dawwart b-kull el-ma7aTTa!", "Seven?! I was on platform three, and I searched the whole station!"),
    TrainStationDLine("N", "ولا يهمّك. انزل عالدرج هون، لفّ شمال، وكمّل دغري لآخر الممر. رصيف سبعة على إيدك اليمين.", "Wala yhimmak. Inzal 3ad-daraj hoon, liff shmaal, w kammil dughri la-aakhir el-mamarr. RaSiif sab3a 3ala iidak el-yamiin.", "No worries. Go down the stairs here, turn left, and keep going straight to the end of the corridor. Platform seven is on your right."),
    TrainStationDLine("M", "يا الله، لازم أركض! ما رح ألحق!", "Ya allah, laazim arkoD! Ma ra7 al7a2!", "Oh God, I have to run! I won't make it!"),
    TrainStationDLine("N", "لا تركض، ريّح حالك. القطار عمره ما بيطلع عالوقت.", "La trkoD, rayyi7 7aalak. El-2iTaar 3umro ma biyiTla3 3al-wa2t.", "Don't run, relax. The train never leaves on time."),
    TrainStationDLine("M", "عن جد؟", "3an jadd?", "Seriously?"),
    TrainStationDLine("N", "والله. دايماً بيتأخّر ربع ساعة على الأقل. معك وقت تشتري قهوة كمان.", "Wallah. Daayman byit2akhkhar rub3 saa3a 3al-a2all. Ma3ak wa2t tishtiri 2ahwe kamaan.", "I swear. It's always at least a quarter of an hour late. You even have time to buy a coffee."),
    TrainStationDLine("M", "يسلمو! شكراً كتير، الله يخلّيك.", "Yislamu! Shukran ktiir, allah ykhalliik.", "Thanks! Thank you so much, bless you."),
    TrainStationDLine("N", "ولا يهمّك. رحلة سعيدة!", "Wala yhimmak. Ri7le sa3iide!", "Don't mention it. Have a good trip!")
)

private val trainStationNotes = listOf(
    "الشام — esh-Shaam — the everyday name for Damascus (it can also mean Syria / the Levant). دمشق — Dimashq — is the formal name.",
    "قطار — 2iTaar (city 2 for ق; Lebanese also say تران treen, from French train). رصيف — raSiif — platform (also: sidewalk).",
    "Tili3 bi- — to get on (a train / bus / car); nizil min — to get off.",
    "biddo yiTla3 ba3d … — 'it's going to leave in …': bidd- also works as a near future (see Exists, Have, Want → The Future).",
    "Tawwil baalak (lit. 'lengthen your patience') and rayyi7 7aalak ('rest yourself') both mean relax / take it easy. To a woman: Tawwli baalik, rayy7i 7aalik.",
    "3umro ma … — 'never' (lit. 'its life, not …'): 3umri ma ru7t = I've never been.",
    "ma3ak wa2t — 'you have time (on you)'. ma3 + pronoun is used for things you have on you right now; 3ind- is general 'have'.",
    "ma ra7 al7a2 — I won't make it (l7i2 = to catch up / make it in time)."
)

@Composable
private fun TrainStationSection(title: String) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(4.dp))
    HorizontalDivider()
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun TrainStationCard(line: TrainStationDLine, showArabic: Boolean) {
    val containerColor = if (line.speaker == "M") MaterialTheme.colorScheme.secondaryContainer
    else MaterialTheme.colorScheme.tertiaryContainer
    val labelColor = if (line.speaker == "M") MaterialTheme.colorScheme.onSecondaryContainer
    else MaterialTheme.colorScheme.onTertiaryContainer
    val speakerLabel = if (line.speaker == "M") "Matt (traveller)" else "Nabil (station worker)"
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
                    text = fixArabizi(line.translit),
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
fun TrainStationDialogueScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Text(
                text = "Matt is in a hurry: his train to Damascus leaves in 12 minutes and he can't find it. He grabs Nabil, who works at the station.",
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic
            )
        }
        item { TrainStationSection("Arabic & Pronunciation") }
        items(trainStationDialogue) { line ->
            TrainStationCard(line = line, showArabic = true)
            Spacer(modifier = Modifier.height(6.dp))
        }
        item { TrainStationSection("English Translation") }
        items(trainStationDialogue) { line ->
            TrainStationCard(line = line, showArabic = false)
            Spacer(modifier = Modifier.height(6.dp))
        }
        item { TrainStationSection("Notes") }
        items(trainStationNotes) { note ->
            Text(
                text = fixArabizi("• $note"),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 3.dp)
            )
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}
