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
fun TypesOfAnyScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Types Of Any") },
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

            // ── что-то ───────────────────────────────────────────────────────
            item { AnySectionHeader("что-то, что-нибудь, что-либо") }
            item {
                AnyCard(title = "что-то — something (it definitely exists)") {
                    Text(
                        text = "Use что-то when the speaker knows something exists but doesn't know exactly what it is, " +
                                "or refers to a specific but vague thing. Most common in statements and past-tense contexts.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AnyExampleRow("Кто-то звонил.", "Someone called. (I know it happened, but not who)")
                    AnyExampleRow("Он сказал что-то интересное.", "He said something interesting.")
                    AnyExampleRow("В холодильнике есть что-то.", "There's something in the fridge.")
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Key signal: a real, definite (if unknown) thing already exists in the situation.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // ── что-нибудь ───────────────────────────────────────────────────
            item {
                AnyCard(title = "что-нибудь — anything (open / hypothetical)") {
                    Text(
                        text = "Use что-нибудь in questions, imperatives, conditionals, and future-tense sentences — " +
                                "when the outcome is uncertain or the speaker doesn't care which specific thing. " +
                                "\"Any one will do.\"",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AnyExampleRow("Ты хочешь что-нибудь поесть?", "Do you want anything to eat? (question)")
                    AnyExampleRow("Скажи что-нибудь.", "Say something. (imperative — anything at all)")
                    AnyExampleRow("Если найдёшь что-нибудь интересное, покажи мне.", "If you find anything interesting, show me. (conditional)")
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Key signal: question mark, imperative verb, если/когда clause, or future tense.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // ── что-либо ─────────────────────────────────────────────────────
            item {
                AnyCard(title = "что-либо — anything at all (formal / literary)") {
                    Text(
                        text = "что-либо is interchangeable with что-нибудь but carries a more formal or literary register. " +
                                "It often stresses \"anything whatsoever\" and is common in written language, official speech, or legal contexts.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AnyExampleRow("Если вы знаете что-либо об этом деле…", "If you know anything at all about this matter…")
                    AnyExampleRow("Не существует что-либо подобного.", "Nothing of the sort exists. (formal)")
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "In everyday speech что-нибудь is far more common; что-либо sounds bookish.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // ── Quick comparison ─────────────────────────────────────────────
            item {
                AnyCard(title = "Quick comparison") {
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Word", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f))
                        Text("Register", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.9f))
                        Text("Use when…", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.8f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        Triple("что-то",      "Neutral", "You know it exists, but not what"),
                        Triple("что-нибудь",  "Neutral", "Questions, imperatives, future, conditionals"),
                        Triple("что-либо",    "Formal",  "Same as что-нибудь but literary/official")
                    ).forEachIndexed { i, (word, reg, use) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 5.dp)
                        ) {
                            Text(word, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.3f))
                            Text(reg, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(0.9f))
                            Text(use, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.8f))
                        }
                    }
                }
            }

            // ── Other question words ──────────────────────────────────────────
            item { AnySectionHeader("The same pattern: где, кто, куда + suffix") }
            item {
                AnyCard(title = "Suffix rule — applies to any question word") {
                    Text(
                        text = "The suffixes -то, -нибудь, and -либо attach to all question words the same way. " +
                                "The meaning rule is identical: -то for something definite-but-unknown, " +
                                "-нибудь for something open/hypothetical, -либо for the formal equivalent of -нибудь.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Base word", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                        Text("+ -то", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.4f))
                        Text("+ -нибудь", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.4f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))

                    listOf(
                        Triple("что (what)",       "что-то\n(something)",          "что-нибудь\n(anything)"),
                        Triple("где (where)",      "где-то\n(somewhere)",          "где-нибудь\n(anywhere)"),
                        Triple("кто (who)",        "кто-то\n(someone)",            "кто-нибудь\n(anyone)"),
                        Triple("куда (to where)",  "куда-то\n(somewhere, to)",     "куда-нибудь\n(anywhere, to)")
                    ).forEachIndexed { i, (base, sto, snibud) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 5.dp)
                        ) {
                            Text(base,   style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.2f))
                            Text(sto,    style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.4f))
                            Text(snibud, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.4f))
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "куда vs где: где states or asks about a location (where is it?), " +
                                "куда states or asks about a destination (where to?).",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Highlight card — куда-то vs где-то
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "куда-то vs где-то:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Он ушёл куда-то.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "He went off somewhere. (movement to a destination)",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Он был где-то.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "He was somewhere. (static location)",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun AnySectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun AnyCard(title: String, content: @Composable ColumnScope.() -> Unit) {
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
private fun AnyExampleRow(russian: String, english: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(russian, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
        Text(english, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
    Spacer(modifier = Modifier.height(2.dp))
}
