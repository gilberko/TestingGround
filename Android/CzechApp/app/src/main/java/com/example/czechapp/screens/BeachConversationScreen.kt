package com.example.czechapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private val beachSections = listOf(
    ConversationSection("Arriving At The Beach", listOf(
        DialogueLine("Petra", "Konečně jsme tady! Moře vypadá krásně.", "We're finally here! The sea looks beautiful."),
        DialogueLine("Milan", "Ano, a počasí je perfektní. Slunce svítí.", "Yes, and the weather is perfect. The sun is shining."),
        DialogueLine("Petra", "Najdeme si místo blízko vody?", "Shall we find a spot near the water?"),
        DialogueLine("Milan", "Dobrý nápad. Tam vlevo je volné místo.", "Good idea. There's a free spot on the left."),
        DialogueLine("Petra", "Výborně. Půjdeme se koupat?", "Excellent. Shall we go for a swim?"),
        DialogueLine("Milan", "Nejdřív si natřu opalovací krém.", "First let me put on sunscreen.")
    )),
    ConversationSection("In The Water", listOf(
        DialogueLine("Petra", "Jak je voda?", "How is the water?"),
        DialogueLine("Milan", "Je trochu studená, ale příjemná.", "It's a bit cold, but pleasant."),
        DialogueLine("Petra", "Umíš plavat?", "Can you swim?"),
        DialogueLine("Milan", "Ano, plavání mě baví. A ty?", "Yes, I enjoy swimming. And you?"),
        DialogueLine("Petra", "Plavat umím, ale radši se brodím u břehu.", "I can swim, but I prefer to wade by the shore."),
        DialogueLine("Milan", "Pozor, vlny jsou dnes velké!", "Careful, the waves are big today!")
    )),
    ConversationSection("Relaxing On The Beach", listOf(
        DialogueLine("Petra", "Jsem unavená. Lehnu si na ručník.", "I'm tired. I'll lie down on the towel."),
        DialogueLine("Milan", "Chceš zmrzlinu? Vidím tam stánek.", "Do you want ice cream? I see a stall over there."),
        DialogueLine("Petra", "Ano, prosím! Jahoda nebo čokoláda.", "Yes, please! Strawberry or chocolate."),
        DialogueLine("Milan", "Dobře. A dáš si něco k pití?", "OK. And would you like something to drink?"),
        DialogueLine("Petra", "Minerálku, prosím. Mám velkou žízeň.", "Sparkling water, please. I'm very thirsty."),
        DialogueLine("Milan", "Hned se vrátím.", "I'll be right back.")
    ))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeachConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("At The Beach") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            beachSections.forEach { section ->
                item {
                    Text(section.title, style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp, bottom = 4.dp))
                    Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(2.dp)) {
                        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            section.lines.forEach { line ->
                                Column {
                                    Text("${line.speaker}:", style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                    Text(line.czech, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
                                    Text(line.english, style = MaterialTheme.typography.bodySmall,
                                        fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
