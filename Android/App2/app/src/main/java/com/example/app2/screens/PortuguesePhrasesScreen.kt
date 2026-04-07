package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
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

private data class PhraseEntry(
    val phrase: String,
    val literal: String,
    val meaning: String
)

private val phrases = listOf(
    PhraseEntry(
        phrase = "Não mexas mais na maionese",
        literal = "Stop stirring the mayonnaise",
        meaning = "Stop overcomplicating things / leave it as it is"
    ),
    PhraseEntry(
        phrase = "Estás a mexer demasiado na maionese",
        literal = "You're stirring the mayonnaise too much",
        meaning = "You're overthinking / overcomplicating this"
    ),
    PhraseEntry(
        phrase = "Andar com a mosca atrás da orelha",
        literal = "To walk with a fly behind the ear",
        meaning = "To have a nagging suspicion that something is off"
    ),
    PhraseEntry(
        phrase = "Ficar a ver navios",
        literal = "To be left watching ships",
        meaning = "To be left with nothing / to get stood up or disappointed after expecting something"
    ),
    PhraseEntry(
        phrase = "Mandar às favas",
        literal = "To send (someone) to the beans",
        meaning = "To tell someone to get lost / to rudely dismiss someone"
    ),
    PhraseEntry(
        phrase = "Não ter papas na língua",
        literal = "To have no mush / porridge on the tongue",
        meaning = "To speak one's mind bluntly, without mincing words"
    ),
    PhraseEntry(
        phrase = "Pagar o pato",
        literal = "To pay the duck",
        meaning = "To take the blame for something you didn't do"
    ),
    PhraseEntry(
        phrase = "Meter os pés pelas mãos",
        literal = "To put your feet through your hands",
        meaning = "To mess everything up / to do things completely wrong"
    ),
    PhraseEntry(
        phrase = "Andar com os azeites",
        literal = "To walk with the olive oils",
        meaning = "To be in a bad mood / to be grumpy"
    ),
    PhraseEntry(
        phrase = "Estar-se nas tintas",
        literal = "To be in the inks",
        meaning = "To not care at all / couldn't care less"
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortuguesePhrasesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Portuguese Phrases") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(phrases) { entry ->
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = entry.phrase,
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Literal: ${entry.literal}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = entry.meaning,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
