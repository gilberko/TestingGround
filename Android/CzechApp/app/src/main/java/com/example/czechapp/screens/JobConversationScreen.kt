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

private val jobSections = listOf(
    ConversationSection("Before The Interview", listOf(
        DialogueLine("Recepční", "Dobrý den. Jak vám mohu pomoci?", "Good afternoon. How can I help you?"),
        DialogueLine("Eva", "Dobrý den. Jmenuji se Eva Nováková. Mám pohovor ve dvě.", "Good afternoon. My name is Eva Nováková. I have an interview at two."),
        DialogueLine("Recepční", "Moment, prosím. Zavolám panu Dvořákovi.", "One moment, please. I'll call Mr. Dvořák."),
        DialogueLine("Eva", "Děkuji.", "Thank you."),
        DialogueLine("Recepční", "Můžete si sednout. On hned přijde.", "Please take a seat. He will be right with you.")
    )),
    ConversationSection("The Interview", listOf(
        DialogueLine("Pan Dvořák", "Dobrý den, paní Nováková. Vítejte. Posaďte se.", "Good afternoon, Ms. Nováková. Welcome. Please sit down."),
        DialogueLine("Eva", "Děkuji. Těší mě.", "Thank you. Nice to meet you."),
        DialogueLine("Pan Dvořák", "Řekněte mi něco o sobě a o vaší zkušenosti.", "Tell me something about yourself and your experience."),
        DialogueLine("Eva", "Jsem vývojářka s pěti lety zkušeností v backendu. Pracovala jsem hlavně s Javou a Kotlinem.", "I am a developer with five years of backend experience. I worked mainly with Java and Kotlin."),
        DialogueLine("Pan Dvořák", "Proč chcete pracovat u nás?", "Why do you want to work for us?"),
        DialogueLine("Eva", "Vaše firma mě zaujala inovativními projekty a přátelskou kulturou.", "Your company attracted me with its innovative projects and friendly culture.")
    )),
    ConversationSection("Wrapping Up", listOf(
        DialogueLine("Pan Dvořák", "Máte nějaké otázky na nás?", "Do you have any questions for us?"),
        DialogueLine("Eva", "Ano. Jaké jsou možnosti kariérního růstu?", "Yes. What are the opportunities for career growth?"),
        DialogueLine("Pan Dvořák", "Máme jasné kariérní cesty a pravidelné hodnocení.", "We have clear career paths and regular performance reviews."),
        DialogueLine("Eva", "To zní skvěle. A jaký je plat pro tuto pozici?", "That sounds great. And what is the salary for this position?"),
        DialogueLine("Pan Dvořák", "Je to v rozmezí osmdesát až sto tisíc korun měsíčně. Záleží na zkušenostech.", "It's in the range of eighty to one hundred thousand crowns per month. It depends on experience."),
        DialogueLine("Eva", "Rozumím. Děkuji za pohovor.", "I understand. Thank you for the interview.")
    ))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Applying For A Job") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            jobSections.forEach { section ->
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
