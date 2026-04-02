package com.example.russianapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
fun MyselfScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My, Myself, You, Yourself") },
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

            // ── сам / сама / само / сами ──────────────────────────────────────
            item { MyselfSectionHeader("сам, сама, само, сами — doing something yourself") }
            item {
                MyselfCard(title = "сам / сама / само / сами — by oneself, personally") {
                    Text(
                        text = "These emphatic pronouns stress that the subject did something personally or without help. " +
                                "They agree with the SUBJECT in gender and number.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Subject", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                        Text("Form", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        "я (male speaker)"    to "сам",
                        "я (female speaker)"  to "сама",
                        "он / оно"            to "сам / само",
                        "она"                 to "сама",
                        "мы / вы / они"       to "сами"
                    ).forEachIndexed { i, (subj, form) ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 4.dp)
                        ) {
                            Text(subj, style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.weight(1.5f))
                            Text(form, style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.weight(1f))
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    MyselfExampleRow("Я сам это сделал.", "I did it myself. (male speaker)")
                    MyselfExampleRow("Я сама это сделала.", "I did it myself. (female speaker)")
                    MyselfExampleRow("Он сам починил машину.", "He fixed the car himself.")
                    MyselfExampleRow("Она сама приготовила ужин.", "She cooked dinner herself.")
                    MyselfExampleRow("Дети сами убрали комнату.", "The children tidied the room themselves.")
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "сам / сама / само / сами decline across cases like adjectives. The forms above are nominative.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // ── свой ─────────────────────────────────────────────────────────
            item { MyselfSectionHeader("свой — one's own (reflexive possessive)") }
            item {
                MyselfCard(title = "свой, своя, своё, свои — when the possessor is the subject") {
                    Text(
                        text = "Use свой when the possessed thing belongs to the SUBJECT of the sentence — " +
                                "regardless of whether the subject is I, you, he, she, or they. " +
                                "This is called the reflexive possessive.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    MyselfExampleRow("Я читаю свою книгу.", "I am reading my (own) book.")
                    MyselfExampleRow("Он читает свою книгу.", "He is reading his (own) book.")
                    MyselfExampleRow("Она любит свою кошку.", "She loves her (own) cat.")
                    MyselfExampleRow("Они взяли свои вещи.", "They took their (own) things.")
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Key rule: if the thing belongs to whoever is the subject → use свой.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            item {
                MyselfCard(title = "свой vs мой / его / её — a critical contrast") {
                    Text(
                        text = "When the possessor is NOT the subject, use мой / твой / его / её instead.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "Я читаю свою книгу.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "I'm reading my own book. (it's mine — subject = possessor)",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = "Я читаю его книгу.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "I'm reading his book. (it's someone else's — possessor ≠ subject)",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "свой, своя, своё, свои agree with the possessed noun in gender, number and case — " +
                                "exactly like adjectives. мой / твой follow the exact same declension pattern.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            item {
                MyselfCard(title = "свой / мой declension (identical adjective-like pattern)") {
                    Text(
                        text = "Both свой and мой decline identically. Here are the свой forms:",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Case", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.9f))
                        Text("m / n", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.weight(1.2f))
                        Text("f", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.weight(1f))
                        Text("pl", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        listOf("Nom", "свой / своё",      "своя",  "свои"),
                        listOf("Gen", "своего",           "своей", "своих"),
                        listOf("Dat", "своему",           "своей", "своим"),
                        listOf("Acc", "свой / своего",    "свою",  "свои / своих"),
                        listOf("Ins", "своим",            "своей", "своими"),
                        listOf("Pre", "своём",            "своей", "своих")
                    ).forEachIndexed { i, row ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 4.dp)
                        ) {
                            Text(row[0], style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.9f))
                            Text(row[1], style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.weight(1.2f))
                            Text(row[2], style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.weight(1f))
                            Text(row[3], style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f))
                        }
                    }
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Acc animate m/pl uses the genitive form (своего / своих). " +
                                "Replace свой with мой everywhere above and you get the мой declension.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // ── себя ─────────────────────────────────────────────────────────
            item { MyselfSectionHeader("себя — oneself (reflexive object)") }
            item {
                MyselfCard(title = "себя — to do something to / for oneself") {
                    Text(
                        text = "себя is the reflexive pronoun: the object of the action is the same person as the subject. " +
                                "It has no nominative form — it is never the subject, always the object. " +
                                "себя itself never changes; only the verb conjugates to match the subject.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "чувствовать себя — to feel (well/poorly)",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.height(6.dp))
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Subject", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Text("Russian", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(2.2f))
                        Text("English", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        Triple("Я",        "Я плохо себя чувствую.",         "I don't feel well."),
                        Triple("Ты",       "Ты плохо себя чувствуешь.",      "You don't feel well."),
                        Triple("Он / Она", "Он плохо себя чувствует.",       "He / She doesn't feel well."),
                        Triple("Мы",       "Мы плохо себя чувствуем.",       "We don't feel well."),
                        Triple("Вы",       "Вы плохо себя чувствуете.",      "You (formal/pl) don't feel well."),
                        Triple("Они",      "Они плохо себя чувствуют.",      "They don't feel well.")
                    ).forEachIndexed { i, (subj, rus, eng) ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 4.dp)
                        ) {
                            Text(subj, style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.weight(1f))
                            Text(rus, style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold, modifier = Modifier.weight(2.2f))
                            Text(eng, style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.weight(2f))
                        }
                    }
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "себя stays the same across all persons. Only чувствую / чувствуешь / чувствует… changes.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            item {
                MyselfCard(title = "себя — declension and common phrases") {
                    Text(
                        text = "себя declines across cases (no nominative). The case depends on the verb or preposition:",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Case", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.7f))
                        Text("Form", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.8f))
                        Text("Example", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(2.5f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        Triple("Gen",  "себя",  "Я боюсь только себя. — I fear only myself."),
                        Triple("Dat",  "себе",  "Возьми это себе. — Take it for yourself."),
                        Triple("Acc",  "себя",  "Я плохо себя чувствую. — I don't feel well."),
                        Triple("Ins",  "собой", "Он доволен собой. — He is pleased with himself."),
                        Triple("Pre",  "себе",  "Она думает о себе. — She thinks about herself.")
                    ).forEachIndexed { i, (case, form, ex) ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 4.dp)
                        ) {
                            Text(case, style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.7f))
                            Text(form, style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.weight(0.8f))
                            Text(ex, style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.weight(2.5f))
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    Text("More phrases with себя:",
                        style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(4.dp))
                    MyselfExampleRow("вести себя", "to behave (oneself)")
                    MyselfExampleRow("Он ведёт себя хорошо.", "He behaves well.")
                    MyselfExampleRow("Она не в себе.", "She is beside herself / not herself.")
                    MyselfExampleRow("читать про себя", "to read silently (lit. to oneself)")
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun MyselfSectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun MyselfCard(title: String, content: @Composable ColumnScope.() -> Unit) {
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
private fun MyselfExampleRow(russian: String, english: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(russian, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
        Text(english, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
    Spacer(modifier = Modifier.height(2.dp))
}
