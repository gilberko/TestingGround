package com.example.russianapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun BasicWordsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Basic Words, Expressions & Greetings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {

            // ── Intro ────────────────────────────────────────────────────────
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Essential words & phrases for getting started",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "The most common words, greetings, and everyday expressions you will need from day one. " +
                                    "Formality notes are included where Russian has distinct formal and informal forms.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            // ── Basic Words ──────────────────────────────────────────────────
            item { BasicWordsSectionHeader("Basic Words") }
            item {
                BasicWordsCard(title = "да, нет, или — yes, no, or") {
                    BasicPhraseRows(listOf(
                        "да" to "yes",
                        "нет" to "no",
                        "или" to "or"
                    ))
                }
            }
            item {
                BasicWordsCard(title = "и vs а — and (two different words)") {
                    Text(
                        text = "Russian has two words for \"and\" that are not interchangeable:",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "и — additive \"and\" (similar / same-direction ideas)",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Я говорю по-русски и по-английски.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "I speak Russian and English.",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Он читает и пишет.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "He reads and writes.",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "а — contrastive \"and/but\" (contrasting or different ideas)",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Я из Москвы, а ты из Лондона.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Text(
                                text = "I'm from Moscow, and you're from London.",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Он работает, а я отдыхаю.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Text(
                                text = "He's working, and I'm resting.",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Rule of thumb: if you could replace \"and\" with \"but\" in English, use а. Otherwise use и.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // ── Greetings ────────────────────────────────────────────────────
            item { BasicWordsSectionHeader("Greetings") }
            item {
                BasicWordsCard(title = "Time-based greetings") {
                    BasicPhraseRows(listOf(
                        "Доброе утро!" to "Good morning!",
                        "Добрый день!" to "Good day! / Good afternoon!",
                        "Добрый вечер!" to "Good evening!",
                        "Спокойной ночи." to "Good night."
                    ))
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Добрый (masc.) agrees with день and вечер; Доброе (neut.) agrees with утро. " +
                                "Спокойной ночи is Genitive — literally \"of a peaceful night.\"",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // ── Hello & Goodbye ──────────────────────────────────────────────
            item { BasicWordsSectionHeader("Hello & Goodbye") }
            item {
                BasicWordsCard(title = "Saying hello and goodbye") {
                    BasicPhraseRows(listOf(
                        "Привет!" to "Hi! (informal)",
                        "Здравствуйте!" to "Hello! (formal)",
                        "Пока!" to "Bye! (informal)",
                        "До свидания." to "Goodbye. (formal)",
                        "Увидимся!" to "See you! (lit. \"We'll see each other\")",
                        "До скорого!" to "See you soon!"
                    ))
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Use Здравствуйте and До свидания with strangers, colleagues, and elders. " +
                                "Привет and Пока are for friends and family.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // ── Polite Phrases ───────────────────────────────────────────────
            item { BasicWordsSectionHeader("Polite Phrases") }
            item {
                BasicWordsCard(title = "Excuse me, sorry, please") {
                    BasicPhraseRows(listOf(
                        "Извините / Извини" to "Excuse me / Sorry (formal / informal)",
                        "Простите / Прости" to "Forgive me / Sorry (formal / informal)",
                        "Пожалуйста" to "Please / You're welcome"
                    ))
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Пожалуйста serves double duty: \"please\" when asking, and \"you're welcome\" in reply to спасибо. " +
                                "Извините is for minor interruptions; Простите is for more sincere apologies.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // ── Common Questions ─────────────────────────────────────────────
            item { BasicWordsSectionHeader("Common Questions & Answers") }
            item {
                BasicWordsCard(title = "Introductions and small talk") {
                    BasicPhraseRows(listOf(
                        "Как дела?" to "How are you?",
                        "Хорошо, спасибо. / Нормально." to "Fine, thanks. / Okay.",
                        "Как вас зовут? / Как тебя зовут?" to "What's your name? (formal / informal)",
                        "Меня зовут…" to "My name is… (lit. \"They call me…\")",
                        "Откуда вы? / Откуда ты?" to "Where are you from? (formal / informal)",
                        "Я из…" to "I'm from…",
                        "Приятно познакомиться. / Очень приятно." to "Nice to meet you."
                    ))
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Меня зовут literally means \"They call me\" — the name goes in the Accusative case here. " +
                                "Я из… takes the Genitive case after из (e.g., Я из Москвы).",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // ── Useful Phrases ───────────────────────────────────────────────
            item { BasicWordsSectionHeader("Useful Phrases") }
            item {
                BasicWordsCard(title = "When you're confused or uncertain") {
                    BasicPhraseRows(listOf(
                        "Что это?" to "What is it? / What's this?",
                        "Я не знаю." to "I don't know.",
                        "Я не понимаю." to "I don't understand.",
                        "Я не помню." to "I don't remember."
                    ))
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun BasicWordsSectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun BasicWordsCard(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}

@Composable
private fun BasicPhraseRows(phrases: List<Pair<String, String>>) {
    phrases.forEachIndexed { i, (russian, english) ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                    else MaterialTheme.colorScheme.surface
                )
                .padding(vertical = 5.dp)
        ) {
            Text(russian, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
            Text(english, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
