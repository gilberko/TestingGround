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

private val restaurantSections = listOf(
    ConversationSection("Getting A Table", listOf(
        DialogueLine("Číšník", "Dobrý večer. Máte rezervaci?", "Good evening. Do you have a reservation?"),
        DialogueLine("Jana", "Dobrý večer. Ne, nemáme. Je volný stůl?", "Good evening. No, we don't. Is there a free table?"),
        DialogueLine("Číšník", "Ano, pro kolik osob?", "Yes, for how many people?"),
        DialogueLine("Jana", "Pro dvě osoby, prosím.", "For two people, please."),
        DialogueLine("Číšník", "Pojďte se mnou. Tady je váš stůl.", "Come with me. Here is your table.")
    )),
    ConversationSection("Ordering Food", listOf(
        DialogueLine("Číšník", "Jste připraveni objednat?", "Are you ready to order?"),
        DialogueLine("Jana", "Ano. Já si dám svíčkovou s knedlíky.", "Yes. I'll have beef sirloin with dumplings."),
        DialogueLine("Tomáš", "A já bych chtěl smažený sýr se salátem.", "And I would like fried cheese with salad."),
        DialogueLine("Číšník", "A co se napít?", "And something to drink?"),
        DialogueLine("Jana", "Dvě piva, prosím. Jedno světlé a jedno tmavé.", "Two beers, please. One light and one dark."),
        DialogueLine("Číšník", "Dobře. Hned to přinesu.", "Very well. I'll bring it right away.")
    )),
    ConversationSection("Paying The Bill", listOf(
        DialogueLine("Jana", "Pane číšníku, zaplatíme prosím.", "Waiter, we would like to pay, please."),
        DialogueLine("Číšník", "Samozřejmě. Platíte dohromady nebo zvlášť?", "Of course. Are you paying together or separately?"),
        DialogueLine("Jana", "Dohromady, prosím.", "Together, please."),
        DialogueLine("Číšník", "To bude šest set korun.", "That will be six hundred crowns."),
        DialogueLine("Jana", "Tady máte. Nechte si drobné.", "Here you are. Keep the change."),
        DialogueLine("Číšník", "Děkuji. Dobrou chuť a hezký večer!", "Thank you. Bon appétit and a lovely evening!")
    ))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("At The Restaurant") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            restaurantSections.forEach { section ->
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
