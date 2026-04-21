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

private val directionsSections = listOf(
    ConversationSection("Asking For Directions", listOf(
        DialogueLine("Adam", "Promiňte, nevíte, kde je tady metro?", "Excuse me, do you know where the metro is around here?"),
        DialogueLine("Bára", "Ano, víte. Jděte rovně asi dvě stě metrů.", "Yes, I do. Go straight ahead for about two hundred metres."),
        DialogueLine("Adam", "A potom?", "And then?"),
        DialogueLine("Bára", "Potom odbočte doleva na rohu u banky.", "Then turn left at the corner by the bank."),
        DialogueLine("Adam", "Je to daleko?", "Is it far?"),
        DialogueLine("Bára", "Ne, je to blízko. Asi pět minut pěšky.", "No, it's close. About five minutes on foot.")
    )),
    ConversationSection("Finding A Landmark", listOf(
        DialogueLine("Adam", "Kde je nejbližší lékárna?", "Where is the nearest pharmacy?"),
        DialogueLine("Bára", "Lékárna je naproti poště.", "The pharmacy is opposite the post office."),
        DialogueLine("Adam", "A kde je pošta?", "And where is the post office?"),
        DialogueLine("Bára", "Pošta je za rohem, vedle obchodu.", "The post office is around the corner, next to the shop."),
        DialogueLine("Adam", "Děkuji mnohokrát.", "Thank you very much."),
        DialogueLine("Bára", "Není zač. Hezký den!", "Don't mention it. Have a nice day!")
    )),
    ConversationSection("Using Public Transport", listOf(
        DialogueLine("Adam", "Kterým autobusem se dostanu na náměstí?", "Which bus goes to the square?"),
        DialogueLine("Bára", "Vezměte si číslo dvanáct nebo číslo dvacet tři.", "Take number twelve or number twenty-three."),
        DialogueLine("Adam", "Kde se nastupuje?", "Where do I board?"),
        DialogueLine("Bára", "Zastávka je tam, napravo.", "The stop is over there, on the right."),
        DialogueLine("Adam", "Jak dlouho trvá cesta?", "How long does the journey take?"),
        DialogueLine("Bára", "Asi deset minut.", "About ten minutes.")
    ))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DirectionsConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Asking For Directions") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            directionsSections.forEach { section ->
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
