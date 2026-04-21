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

private val airportSections = listOf(
    ConversationSection("Check-In", listOf(
        DialogueLine("Pracovník", "Dobrý den. Váš pas a letenku, prosím.", "Good morning. Your passport and ticket, please."),
        DialogueLine("Martin", "Prosím. Letím do Prahy.", "Here you are. I'm flying to Prague."),
        DialogueLine("Pracovník", "Máte jen příruční zavazadlo?", "Do you have only carry-on luggage?"),
        DialogueLine("Martin", "Ne, mám jeden kufr na odbavení.", "No, I have one suitcase to check in."),
        DialogueLine("Pracovník", "Položte kufr na páse, prosím.", "Please place the suitcase on the belt."),
        DialogueLine("Martin", "Tady máte. Jaký mám odletový terminál?", "Here you go. Which departure terminal do I have?")
    )),
    ConversationSection("Security And Boarding Gate", listOf(
        DialogueLine("Pracovník", "Terminál B, brána číslo třináct.", "Terminal B, gate number thirteen."),
        DialogueLine("Martin", "Kde je bezpečnostní kontrola?", "Where is the security check?"),
        DialogueLine("Pracovník", "Rovně a pak doprava. Palubní lístek vám vydám za chvíli.", "Straight ahead and then to the right. I'll give you the boarding pass in a moment."),
        DialogueLine("Martin", "Kdy je nástup do letadla?", "When is boarding?"),
        DialogueLine("Pracovník", "Nástup je v čase čtrnáct třicet. Spěchejte, letadlo odletí ve patnáct hodin.", "Boarding is at fourteen thirty. Hurry, the plane departs at fifteen hundred.")
    )),
    ConversationSection("On The Plane", listOf(
        DialogueLine("Letuška", "Dobrý den. Mohu vidět váš palubní lístek?", "Good morning. May I see your boarding pass?"),
        DialogueLine("Martin", "Samozřejmě. Tady je.", "Of course. Here it is."),
        DialogueLine("Letuška", "Vaše sedadlo je jedenáct A, u okna na levé straně.", "Your seat is eleven A, at the window on the left side."),
        DialogueLine("Martin", "Děkuji. Jak dlouho trvá let?", "Thank you. How long does the flight take?"),
        DialogueLine("Letuška", "Let trvá asi hodinu a čtyřicet minut.", "The flight takes about one hour and forty minutes."),
        DialogueLine("Martin", "Výborně. Mohu si objednat kávu?", "Excellent. Can I order a coffee?"),
        DialogueLine("Letuška", "Samozřejmě. Nabídneme nápoje po vzletu.", "Of course. We'll offer drinks after take-off.")
    ))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AirportConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("At The Airport") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            airportSections.forEach { section ->
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
