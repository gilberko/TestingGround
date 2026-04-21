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

private val techMeetingSections = listOf(
    ConversationSection("Starting The Meeting", listOf(
        DialogueLine("Kateřina", "Dobré ráno, všichni. Začneme schůzi.", "Good morning, everyone. Let's start the meeting."),
        DialogueLine("Ondřej", "Dobrý den. Mám sdílet obrazovku?", "Good morning. Should I share my screen?"),
        DialogueLine("Kateřina", "Ano, prosím. Nejdřív ale přivítám nového kolegu.", "Yes, please. But first let me welcome our new colleague."),
        DialogueLine("Lukáš", "Dobrý den. Jmenuji se Lukáš. Jsem nový vývojář.", "Good morning. My name is Lukáš. I'm the new developer."),
        DialogueLine("Kateřina", "Vítejte v týmu, Lukáši.", "Welcome to the team, Lukáš.")
    )),
    ConversationSection("Discussing A Bug", listOf(
        DialogueLine("Ondřej", "Máme problém s produkcí. Server padá každých pár hodin.", "We have a production issue. The server crashes every few hours."),
        DialogueLine("Kateřina", "Máš logy? Co říkají?", "Do you have logs? What do they say?"),
        DialogueLine("Ondřej", "Ano. Vypadá to jako únik paměti v novém modulu.", "Yes. It looks like a memory leak in the new module."),
        DialogueLine("Lukáš", "Já se na to mohl podívat. Mám zkušenosti s tímhle typem chyb.", "I could look into it. I have experience with this type of bug."),
        DialogueLine("Kateřina", "Skvěle. Ondřeji, pošli mu přístup do repozitáře.", "Great. Ondřej, give him access to the repository."),
        DialogueLine("Ondřej", "Dobře. Udělám to hned po schůzi.", "OK. I'll do it right after the meeting.")
    )),
    ConversationSection("Planning The Sprint", listOf(
        DialogueLine("Kateřina", "Teď k plánování sprintu. Jaké máme priority?", "Now for sprint planning. What are our priorities?"),
        DialogueLine("Ondřej", "Oprava chyby musí být první. Pak nová funkce pro klienty.", "The bug fix must be first. Then the new feature for clients."),
        DialogueLine("Lukáš", "Kolik mám odhadnout story pointů za bug fix?", "How many story points should I estimate for the bug fix?"),
        DialogueLine("Kateřina", "Řekni mi tvůj odhad a pak to prodiskutujeme.", "Tell me your estimate and then we'll discuss it."),
        DialogueLine("Lukáš", "Myslím, že osm bodů. Je to složitý problém.", "I think eight points. It's a complex problem."),
        DialogueLine("Kateřina", "Souhlasím. Zapíšeme to do Jiry.", "I agree. We'll log it in Jira.")
    ))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechMeetingConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tech Meeting") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            techMeetingSections.forEach { section ->
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
