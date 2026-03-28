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
fun ConditionalsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Conditionals") },
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

            // ── Real Conditional ────────────────────────────────────────────
            item { CondSectionHeader("Type 1 — Real Conditional (если)") }
            item {
                CondCard(title = "если — if (something that can actually happen)") {
                    Text(
                        text = "Structure:  если + [present or future tense],  [future tense]\n" +
                                "Use when the condition is genuinely possible.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CondExampleBlock(
                        listOf(
                            Pair("Если это случится, я это сделаю.",   "If this happens, I will do that."),
                            Pair("Если он придёт, мы начнём.",          "If he comes, we will start."),
                            Pair("Если идёт дождь, я остаюсь дома.",   "If it is raining, I stay home."),
                            Pair("Если ты устал, отдохни.",             "If you are tired, rest.")
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Note: the result clause can use future tense (for a prediction) or imperative (for a command/suggestion), " +
                                "depending on context.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            // ── Hypothetical / Unreal (бы) ──────────────────────────────────
            item { CondSectionHeader("Type 2 — Hypothetical Conditional (если бы … бы)") }
            item {
                CondCard(title = "если бы — if (imaginary or contrary to reality)") {
                    Text(
                        text = "Structure:  если бы + [past tense],  [past tense + бы]\n" +
                                "Both clauses use the past tense form. бы appears in the result clause " +
                                "right after the verb (or can float earlier in the clause).",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Highlight box for the user's example
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "Classic example:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Если бы у меня были деньги, я бы купил дом.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "If I had money, I would buy a house.",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    CondExampleBlock(
                        listOf(
                            Pair("Если бы я знал, я бы сказал тебе.",           "If I knew, I would tell you."),
                            Pair("Если бы она пришла, было бы весело.",          "If she came, it would be fun."),
                            Pair("Если бы у нас было время, мы бы поехали.",    "If we had time, we would go."),
                            Pair("Я бы помог, если бы мог.",                     "I would help if I could.")
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Note: бы is a particle, not a full word — it cannot stand at the start of a clause. " +
                                "It is often written together with other words: чтобы (so that), " +
                                "как бы (as if), хотя бы (at least).",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            // ── Past Counterfactual ─────────────────────────────────────────
            item { CondSectionHeader("Type 3 — Past Counterfactual (если бы … бы)") }
            item {
                CondCard(title = "Same structure as Type 2 — context tells you it's past") {
                    Text(
                        text = "Russian uses the same если бы … бы structure for both present hypotheticals (\"would\") " +
                                "and past counterfactuals (\"would have\"). " +
                                "Context — time words, shared knowledge — makes the meaning clear.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CondExampleBlock(
                        listOf(
                            Pair("Если бы я знал об этом тогда, я бы не пошёл.", "If I had known about it then, I would not have gone."),
                            Pair("Если бы ты позвонил вчера, я бы ответил.",     "If you had called yesterday, I would have answered."),
                            Pair("Она бы выиграла, если бы тренировалась больше.", "She would have won if she had trained more.")
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Time words like тогда (then), вчера (yesterday), or раньше (before) signal that " +
                                "the counterfactual refers to the past.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            // ── даже если ───────────────────────────────────────────────────
            item { CondSectionHeader("даже если — Even if") }
            item {
                CondCard(title = "даже если (concessive conditional)") {
                    Text(
                        text = "Combines with either Type 1 (real) or Type 2 (hypothetical) depending on how likely the scenario is.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CondExampleBlock(
                        listOf(
                            Pair("Даже если ты придёшь, я не открою дверь.",         "Even if you come, I won't open the door.  (real)"),
                            Pair("Даже если бы у меня были деньги, я бы не купил это.", "Even if I had money, I wouldn't buy this.  (hypothetical)"),
                            Pair("Даже если идёт дождь, мы идём.",                   "Even if it's raining, we go.")
                        )
                    )
                }
            }

            // ── раз ─────────────────────────────────────────────────────────
            item { CondSectionHeader("раз — Since / Given that") }
            item {
                CondCard(title = "раз (colloquial — the condition is already true)") {
                    Text(
                        text = "раз is used when the condition is already a fact. " +
                                "It is more colloquial than так как or поскольку.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CondExampleBlock(
                        listOf(
                            Pair("Раз ты здесь, помоги нам.",          "Since you are here, help us."),
                            Pair("Раз он знает, пусть скажет.",        "Since he knows, let him say it."),
                            Pair("Раз уж начал, заканчивай.",          "Since you've already started, finish it.")
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "раз уж adds emphasis: \"since you've already gone and done it…\"",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            // ── как только / когда ─────────────────────────────────────────
            item { CondSectionHeader("как только / когда — As soon as / When") }
            item {
                CondCard(title = "Temporal conditionals") {
                    Text(
                        text = "These introduce a condition based on timing rather than possibility. " +
                                "Both use the future or present tense in the condition clause.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Sub-header: как только
                    Text(
                        text = "как только — as soon as",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    CondExampleBlock(
                        listOf(
                            Pair("Как только он придёт, мы пойдём.",    "As soon as he arrives, we will go."),
                            Pair("Как только закончишь, позвони мне.",  "As soon as you finish, call me.")
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Sub-header: когда
                    Text(
                        text = "когда — when",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    CondExampleBlock(
                        listOf(
                            Pair("Когда ты вернёшься, мы поговорим.", "When you return, we will talk."),
                            Pair("Когда хочешь есть, иди на кухню.",  "When you want to eat, go to the kitchen.")
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "как только implies immediacy (the moment X happens). " +
                                "когда is more general (at whatever point X happens).",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            // ── Summary table ───────────────────────────────────────────────
            item { CondSectionHeader("Quick Reference") }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                            Text("Keyword", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.1f))
                            Text("Type", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f))
                            Text("Structure", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
                        }
                        HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                        listOf(
                            Triple("если",       "Real",              "если + present/future → future"),
                            Triple("если бы…бы", "Hypothetical",      "если бы + past → past + бы"),
                            Triple("если бы…бы", "Past counterfactual","same structure + time word"),
                            Triple("даже если",  "Concessive",        "даже если + real or бы structure"),
                            Triple("раз",        "Causal (given that)","раз + present → result"),
                            Triple("как только", "Temporal (immediate)","как только + future → future"),
                            Triple("когда",      "Temporal (general)", "когда + present/future → future")
                        ).forEachIndexed { i, (kw, type, structure) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                        else MaterialTheme.colorScheme.surface
                                    )
                                    .padding(vertical = 5.dp)
                            ) {
                                Text(kw, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.1f))
                                Text(type, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.3f))
                                Text(structure, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(2f))
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
private fun CondSectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun CondCard(title: String, content: @Composable ColumnScope.() -> Unit) {
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
private fun CondExampleBlock(examples: List<Pair<String, String>>) {
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
