package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Greetings") },
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
            item { GreetingsCard() }
        }
    }
}

@Composable
private fun GreetingsCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // ── Time-of-day greetings ─────────────────────────────────────
            Text("Time-of-day Greetings", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            GreetingEntry(
                term = "Bom dia",
                meaning = "Good morning",
                description = "Used from when you wake up until roughly noon. Literally \"good day\".",
                examples = listOf(
                    "Bom dia! Como está?" to "Good morning! How are you?",
                    "Bom dia a todos." to "Good morning, everyone."
                )
            )
            GreetingEntry(
                term = "Boa tarde",
                meaning = "Good afternoon / Good day",
                description = "Used from noon until around 8 pm, when it starts getting dark.",
                examples = listOf(
                    "Boa tarde, posso ajudar?" to "Good afternoon, can I help?",
                    "Boa tarde, tudo bem?" to "Good afternoon, everything okay?"
                )
            )
            GreetingEntry(
                term = "Boa noite",
                meaning = "Good evening / Good night",
                description = "Used from roughly 8 pm onwards — both as a greeting when arriving AND as a farewell when leaving at night. Unlike English, one phrase covers both.",
                examples = listOf(
                    "Boa noite! Bem-vindo." to "Good evening! Welcome.",
                    "Boa noite, até amanhã." to "Good night, see you tomorrow."
                )
            )
            GreetingEntry(
                term = "Boas",
                meaning = "Hi / Hey (informal)",
                description = "Casual shortening of any of the above — used at any time of day among friends. Very colloquial; avoid in formal settings.",
                examples = listOf(
                    "Boas! Tudo bem?" to "Hey! All good?",
                    "Boas, pessoal!" to "Hey, guys!"
                )
            )
            GreetingEntry(
                term = "Olá",
                meaning = "Hello",
                description = "Neutral, works at any time of day in both casual and moderately formal situations.",
                examples = listOf(
                    "Olá, como te chamas?" to "Hello, what's your name?",
                    "Olá! Entra, entra." to "Hello! Come in, come in."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            // ── Farewells & Thanks ────────────────────────────────────────
            Text("Farewells & Thanks", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            GreetingEntry(
                term = "Obrigado / Obrigada",
                meaning = "Thank you",
                description = "The speaker agrees in gender with themselves — a male speaker says obrigado, a female speaker says obrigada. It is not about the listener.",
                examples = listOf(
                    "Obrigado pela ajuda!" to "Thank you for the help! (male speaking)",
                    "Obrigada, foi muito gentil." to "Thank you, that was very kind. (female speaking)"
                )
            )
            GreetingEntry(
                term = "Tchau",
                meaning = "Bye (informal)",
                description = "Borrowed from Italian ciao. Very common in everyday speech.",
                examples = listOf(
                    "Tchau! Até logo." to "Bye! See you soon.",
                    "Tchau, cuida-te." to "Bye, take care."
                )
            )
            GreetingEntry(
                term = "Até logo",
                meaning = "See you soon / Bye for now",
                description = "Implies you expect to see the person again fairly soon — often later that day. Logo means \"soon\".",
                examples = listOf(
                    "Vou ao supermercado, até logo." to "I'm going to the supermarket, see you soon.",
                    "Até logo! Boa sorte." to "Bye for now! Good luck."
                )
            )
            GreetingEntry(
                term = "Até à próxima",
                meaning = "Until next time / See you next time",
                description = "No specific timeframe — just \"next time we happen to meet\". More open-ended than até logo; use it when you don't know when you'll see them again.",
                examples = listOf(
                    "Foi um prazer. Até à próxima!" to "It was a pleasure. Until next time!",
                    "Até à próxima visita." to "Until the next visit."
                ),
                note = "Difference: até logo = see you soon (probably today); até à próxima = next time we meet (no specific date)."
            )
            GreetingEntry(
                term = "Até já",
                meaning = "See you in a moment / Be right back",
                description = "Even shorter-term than até logo — implies you'll be back within minutes.",
                examples = listOf(
                    "Vou à casa de banho, até já." to "I'm going to the bathroom, back in a moment.",
                    "Até já!" to "See you in a sec!"
                )
            )
        }
    }
}

@Composable
private fun GreetingEntry(
    term: String,
    meaning: String,
    description: String,
    examples: List<Pair<String, String>>,
    note: String? = null
) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(term, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
        Text(meaning, style = MaterialTheme.typography.bodyMedium, fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.primary)
        Text(description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(2.dp))
        examples.forEach { (pt, en) ->
            Text(
                "\u2022 $pt",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic
            )
            Text(
                "  $en",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        if (note != null) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                "Note: $note",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}
