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

private data class RestaurantDLine(val speaker: String, val ar: String, val translit: String, val en: String)

private val restaurantDDialogue = listOf(
    RestaurantDLine("W", "مسا الخير! أهلاً وسهلاً.", "Masa el-khir! Ahlan w sahlan.", "Good evening! Welcome."),
    RestaurantDLine("A", "مسا النور! منيح، شكراً.", "Masa el-nur! Mnih, shukran.", "Good evening! Fine, thank you."),
    RestaurantDLine("W", "بدكم تشوفوا المنيو؟", "Biddkum tshufu el-minyu?", "Would you like to see the menu?"),
    RestaurantDLine("B", "أيوا، من فضلك.", "Aywa, min fadlak.", "Yes, please."),
    RestaurantDLine("W", "تفضّلوا. اليوم عنّا منسف، مقلوبة، وفرّوج مشوي.", "Tfaddalu. El-yom indna mansaf, maqlube, w farrouj mashwi.", "Here you go. Today we have mansaf, maqluba, and grilled chicken."),
    RestaurantDLine("A", "شو هو المنسف؟", "Shu huwwe el-mansaf?", "What is mansaf?"),
    RestaurantDLine("W", "المنسف طبخة وطنية — لحمة غنم مع رز وصلص الجميد. كتير لذيذ.", "El-mansaf tabkha wataniyye — lahme ghanam ma' ruzz w sals el-jameed. Ktir laziz.", "Mansaf is a national dish — lamb with rice and jameed sauce. Very delicious."),
    RestaurantDLine("B", "والمقلوبة؟", "W el-maqlube?", "And the maqluba?"),
    RestaurantDLine("W", "المقلوبة رز مع خضرا ودجاج. بينحبّوها الكل.", "El-maqlube ruzz ma' khudra w djaj. Byinhbbuha el-kull.", "Maqluba is rice with vegetables and chicken. Everyone loves it."),
    RestaurantDLine("B", "بدي فرّوج مشوي. بكم؟", "Biddi farrouj mashwi. Bi'kam?", "I'd like the grilled chicken. How much is it?"),
    RestaurantDLine("W", "بثمانية وعشرين شيكل.", "Bi tmaniyye w ishrin shekel.", "Twenty-eight shekels."),
    RestaurantDLine("A", "بدي منسف. بكم؟", "Biddi mansaf. Bi'kam?", "I'd like the mansaf. How much is it?"),
    RestaurantDLine("W", "بخمسة وثلاثين شيكل.", "Bi khamse w talatin shekel.", "Thirty-five shekels."),
    RestaurantDLine("A", "كويّس. وشو في للمشرب؟", "Kwayyes. W shu fi lil-mashreb?", "Good. And what do you have to drink?"),
    RestaurantDLine("W", "عندنا قهوة، شاي، عصير، وماء.", "Indna 'ahwe, shai, 'asir, w mayy.", "We have coffee, tea, juice, and water."),
    RestaurantDLine("B", "بدي شاي، شكراً.", "Biddi shai, shukran.", "I'd like tea, thank you."),
    RestaurantDLine("A", "وأنا بدي قهوة، لو سمحت.", "W ana biddi 'ahwe, law samaht.", "And I'd like coffee, please."),
    RestaurantDLine("W", "تمام! بيجي عالطول.", "Tamam! Byeji 'ala tool.", "Alright! Coming right away.")
)

@Composable
private fun RestaurantDSection(title: String) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(4.dp))
    HorizontalDivider()
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun RestaurantDCard(line: RestaurantDLine, showArabic: Boolean) {
    val containerColor = when (line.speaker) {
        "A" -> MaterialTheme.colorScheme.secondaryContainer
        "B" -> MaterialTheme.colorScheme.surfaceVariant
        else -> MaterialTheme.colorScheme.tertiaryContainer
    }
    val labelColor = when (line.speaker) {
        "A" -> MaterialTheme.colorScheme.onSecondaryContainer
        "B" -> MaterialTheme.colorScheme.onSurfaceVariant
        else -> MaterialTheme.colorScheme.onTertiaryContainer
    }
    val speakerLabel = when (line.speaker) {
        "A" -> "A (friend)"
        "B" -> "B (friend)"
        else -> "W (waiter)"
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
fun RestaurantDialogueScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item { RestaurantDSection("Arabic & Pronunciation") }
        items(restaurantDDialogue) { line ->
            RestaurantDCard(line = line, showArabic = true)
            Spacer(modifier = Modifier.height(6.dp))
        }
        item { RestaurantDSection("English Translation") }
        items(restaurantDDialogue) { line ->
            RestaurantDCard(line = line, showArabic = false)
            Spacer(modifier = Modifier.height(6.dp))
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}
