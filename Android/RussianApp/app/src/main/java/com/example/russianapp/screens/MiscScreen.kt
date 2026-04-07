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
fun MiscScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Misc — Expressions & Figures of Speech") },
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
                            text = "Interesting expressions & figures of speech",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "This section covers idiomatic Russian expressions — phrases whose meaning goes beyond " +
                                    "the sum of their words, as well as connectors and time expressions that learners often " +
                                    "find tricky. Mastering these will make your Russian sound much more natural.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            // ── до сих пор ──────────────────────────────────────────────────
            item { MiscSectionHeader("до сих пор — still / up until now") }
            item {
                MiscCard(title = "до сих пор  (lit. \"up to these times\")") {
                    Text(
                        text = "Used to say something that started in the past is still true or hasn't happened yet. " +
                                "It points from a past moment all the way up to now.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    MiscExampleBlock(
                        listOf(
                            Pair("Я до сих пор не понимаю.", "I still don't understand."),
                            Pair("Она до сих пор живёт там.", "She still lives there."),
                            Pair("Мы до сих пор не получили ответа.", "We still haven't received an answer."),
                            Pair("Он до сих пор помнит тот день.", "He still remembers that day.")
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Tip: до сих пор is especially common with negation — \"still haven't done X\" — " +
                                "but works in positive sentences too.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            // ── до тех пор, пока ────────────────────────────────────────────
            item { MiscSectionHeader("до тех пор, пока — until / as long as") }
            item {
                MiscCard(title = "до тех пор, пока  (lit. \"up to those times, while\")") {
                    Text(
                        text = "A slightly formal or emphatic way to express a time limit. " +
                                "With a negative verb in the second clause it means \"until\"; " +
                                "with a positive verb it means \"as long as\".",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "\"as long as\" — both clauses positive",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    MiscExampleBlock(
                        listOf(
                            Pair("До тех пор, пока ты здесь, мне не страшно.",
                                "As long as you are here, I'm not afraid."),
                            Pair("До тех пор, пока есть надежда, нужно бороться.",
                                "As long as there is hope, one must fight.")
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "\"until\" — second clause uses не",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    MiscExampleBlock(
                        listOf(
                            Pair("Я буду ждать до тех пор, пока ты не придёшь.",
                                "I will wait until you come."),
                            Pair("Он не уйдёт до тех пор, пока не получит ответ.",
                                "He won't leave until he gets an answer.")
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Note: In everyday speech до тех пор, пока is often shortened to just пока (не). " +
                                "The longer form adds emphasis or formality.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            // ── пока vs когда ───────────────────────────────────────────────
            item { MiscSectionHeader("пока vs когда — while vs when") }
            item {
                MiscCard(title = "The key difference") {
                    Text(
                        text = "Both translate loosely as \"when\", but they describe different relationships between events:\n\n" +
                                "• пока — two actions are happening at the same time (while, during the time that)\n" +
                                "• когда — one event happens at a specific moment (when, at the point that)",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            item {
                MiscCard(title = "пока — while (simultaneous actions)") {
                    Text(
                        text = "Use пока when both actions are ongoing at the same time. " +
                                "The imperfective aspect is natural in both clauses.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Present tense — ongoing right now",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    MiscExampleBlock(
                        listOf(
                            Pair("Пока я читаю, она готовит.",
                                "While I'm reading, she is cooking."),
                            Pair("Пока он работает, дети играют.",
                                "While he is working, the kids are playing."),
                            Pair("Пока мы разговариваем, время идёт.",
                                "While we are talking, time passes.")
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Past tense — two things were happening at the same time",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    MiscExampleBlock(
                        listOf(
                            Pair("Пока он спал, я работал.",
                                "While he was sleeping, I was working."),
                            Pair("Пока она готовила, я убирал квартиру.",
                                "While she was cooking, I was cleaning the flat."),
                            Pair("Пока шёл дождь, мы сидели дома.",
                                "While it was raining, we stayed at home.")
                        )
                    )
                }
            }

            item {
                MiscCard(title = "когда — when (a specific moment or point in time)") {
                    Text(
                        text = "Use когда when one event happens at or around the moment of another — " +
                                "often sequential rather than truly simultaneous.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    MiscExampleBlock(
                        listOf(
                            Pair("Когда он пришёл, я уже ушёл.",
                                "When he arrived, I had already left."),
                            Pair("Когда я был молодым, я любил спорт.",
                                "When I was young, I loved sport."),
                            Pair("Когда она позвонила, я спал.",
                                "When she called, I was sleeping."),
                            Pair("Когда ты вернёшься, мы поговорим.",
                                "When you return, we will talk.")
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Notice: \"Когда она позвонила, я спал\" — she called (a moment), and I was sleeping " +
                                "at that moment. Compare: \"Пока она звонила, я спал\" would mean she was calling " +
                                "for a period of time and I slept through the whole thing.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            // ── Quick Reference ──────────────────────────────────────────────
            item { MiscSectionHeader("Quick Reference") }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                            Text("Expression", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.4f))
                            Text("Meaning", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                            Text("Key idea", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.6f))
                        }
                        HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                        listOf(
                            Triple("до сих пор",          "still / up until now",  "past → present continuity"),
                            Triple("до тех пор, пока",    "as long as / until",    "emphatic time boundary"),
                            Triple("пока (+ imperfective)","while",                 "two simultaneous actions"),
                            Triple("пока не",             "until",                 "action waits for event"),
                            Triple("когда",               "when",                  "specific moment / point in time")
                        ).forEachIndexed { i, (expr, meaning, idea) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                        else MaterialTheme.colorScheme.surface
                                    )
                                    .padding(vertical = 5.dp)
                            ) {
                                Text(expr, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.4f))
                                Text(meaning, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1f))
                                Text(idea, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.6f))
                            }
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun MiscSectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun MiscCard(title: String, content: @Composable ColumnScope.() -> Unit) {
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
private fun MiscExampleBlock(examples: List<Pair<String, String>>) {
    examples.forEachIndexed { i, (russian, english) ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                    else MaterialTheme.colorScheme.surface
                )
                .padding(vertical = 5.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(russian, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
                Text(english, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
