package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun MorePrepositionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Prepositions — Pronouns") },
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
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { Spacer(modifier = Modifier.height(4.dp)) }

            // Intro
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Pronouns after Prepositions",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = "After a preposition, Portuguese uses special pronoun forms. Only eu and tu change — eu becomes mim, and tu becomes ti. All other persons keep the same form as the subject pronoun.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        // Table
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Subject",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "After preposition",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                        listOf(
                            "eu" to "mim",
                            "tu" to "ti",
                            "ele" to "ele",
                            "ela" to "ela",
                            "nós" to "nós",
                            "vós" to "vós",
                            "eles" to "eles",
                            "elas" to "elas"
                        ).forEach { (subject, prep) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp)
                            ) {
                                Text(
                                    text = subject,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = prep,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = if (prep == "mim" || prep == "ti") FontWeight.Bold else FontWeight.Normal,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }

            // para + pronouns
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "para + pronoun",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        listOf(
                            "para mim", "para ti", "para ele", "para ela",
                            "para nós", "para vós", "para eles", "para elas"
                        ).forEach { form ->
                            Text(
                                text = form,
                                style = MaterialTheme.typography.bodyMedium,
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier.padding(vertical = 1.dp)
                            )
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
                        Text(
                            text = "Este presente é para ti.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "This present is for you.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = "Comprei isto para mim.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "I bought this for myself.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = "Fizemos isto para eles.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "We did this for them.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // com + pronouns (special forms)
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "com + pronoun (special merged forms)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = "com merges with pronouns to form a single word. These forms are unique to Portuguese.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "com + …",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "Result",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                        listOf(
                            "mim" to "comigo",
                            "ti" to "contigo",
                            "ele / ela / você" to "consigo *",
                            "nós" to "connosco",
                            "vós" to "convosco",
                            "eles / elas" to "consigo *"
                        ).forEach { (source, result) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp)
                            ) {
                                Text(
                                    text = source,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = result,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
                        Text(
                            text = "* consigo is the reflexive form (the subject and the pronoun refer to the same person). When they differ, use com ele / com ela / com eles.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        listOf(
                            "Vens comigo?" to "Are you coming with me?",
                            "Ela trouxe o filho consigo." to "She brought her son with her.",
                            "Vou contigo ao mercado." to "I'll go to the market with you.",
                            "Viemos connosco todos juntos." to "We all came together."
                        ).forEach { (pt, en) ->
                            Text(
                                text = pt,
                                style = MaterialTheme.typography.bodyMedium,
                                fontStyle = FontStyle.Italic
                            )
                            Text(
                                text = en,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }
                    }
                }
            }

            // de + pronouns
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "de + pronoun (contractions)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = "de contracts with third-person pronouns. First and second persons do not contract.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        listOf(
                            "de + mim" to "de mim  (no change)",
                            "de + ti" to "de ti  (no change)",
                            "de + ele" to "dele",
                            "de + ela" to "dela",
                            "de + nós" to "de nós  (no change)",
                            "de + eles" to "deles",
                            "de + elas" to "delas"
                        ).forEach { (source, result) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp)
                            ) {
                                Text(
                                    text = source,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "→  $result",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
                        listOf(
                            "Fala muito dele." to "He talks a lot about him.",
                            "Gosto de ti." to "I like you.",
                            "O livro é dela." to "The book is hers.",
                            "Sou amigo deles." to "I am a friend of theirs."
                        ).forEach { (pt, en) ->
                            Text(
                                text = pt,
                                style = MaterialTheme.typography.bodyMedium,
                                fontStyle = FontStyle.Italic
                            )
                            Text(
                                text = en,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }
                    }
                }
            }

            // a + pronouns
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Other prepositions + pronoun",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = "Most other prepositions (a, em, por, sobre, entre…) do not contract with personal pronouns — the pronoun form stands as-is.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        listOf(
                            "Falei a ela diretamente." to "I spoke to her directly.",
                            "Penso muitas vezes em ti." to "I often think about you.",
                            "Fiz isso por mim." to "I did that for myself.",
                            "Isto é entre mim e ti." to "This is between me and you."
                        ).forEach { (pt, en) ->
                            Text(
                                text = pt,
                                style = MaterialTheme.typography.bodyMedium,
                                fontStyle = FontStyle.Italic
                            )
                            Text(
                                text = en,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
