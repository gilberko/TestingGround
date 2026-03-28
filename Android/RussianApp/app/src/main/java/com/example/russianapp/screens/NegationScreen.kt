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
fun NegationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Negation") },
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

            // ── не ──────────────────────────────────────────────────────────
            item { NegSectionHeader("не — not") }
            item {
                NegCard(title = "не (not)") {
                    Text(
                        text = "Place не directly before the verb or adjective being negated.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    NegExampleRow("Он не говорит по-русски", "He does not speak Russian", highlight = false)
                    NegExampleRow("Это не сложно", "This is not difficult", highlight = true)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Genitive shift: the direct object of a negated verb often moves from Accusative → Genitive.",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    NegExampleRow("Я вижу книгу (Acc)", "I see the book", highlight = false)
                    NegExampleRow("Я не вижу книги (Gen)", "I don't see the book", highlight = true)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Note: the Genitive shift is common but not obligatory. Accusative is also acceptable and sounds more colloquial.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            // ── нет ─────────────────────────────────────────────────────────
            item { NegSectionHeader("нет — there is no") }
            item {
                NegCard(title = "нет (there is no / none)") {
                    Text(
                        text = "нет always takes the Genitive case.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    NegExampleRow("Нет времени", "There's no time", highlight = false)
                    NegExampleRow("Нет молока", "There's no milk", highlight = true)
                    NegExampleRow("Его нет дома", "He's not home", highlight = false)
                }
            }

            // ── I don't have ────────────────────────────────────────────────
            item { NegSectionHeader("У меня нет — I don't have") }
            item {
                NegCard(title = "У [person] нет [thing] — both take Genitive") {
                    Text(
                        text = "Structure:  у + [person in Genitive] + нет + [thing in Genitive]\n\n" +
                                "The person is expressed with the preposition у, which always takes Genitive " +
                                "(у меня, у него, у неё, у нас, у вас, у них). " +
                                "The thing that is not possessed also takes Genitive, because нет always requires Genitive.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Column headers
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Russian", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.6f))
                        Text("English", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.4f))
                        Text("Genitive form", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.4f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))

                    listOf(
                        Triple("У меня нет машины",  "I don't have a car",       "машина → машины"),
                        Triple("У меня нет времени", "I don't have time",        "время → времени"),
                        Triple("У него нет денег",   "He doesn't have money",    "деньги → денег"),
                        Triple("У неё нет брата",    "She doesn't have a brother","брат → брата"),
                        Triple("У нас нет билетов",  "We don't have tickets",    "билеты → билетов")
                    ).forEachIndexed { i, (ru, en, gen) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 5.dp)
                        ) {
                            Text(ru, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.6f))
                            Text(en, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic, modifier = Modifier.weight(1.4f))
                            Text(gen, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.4f))
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "The positive counterpart uses у + Genitive person + есть + Nominative thing:\n" +
                                "У меня есть машина (Nom) — I have a car.\n" +
                                "When negated, есть disappears and the thing shifts to Genitive: У меня нет машины.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // Combined example
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "Combined with ни … ни …:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "У меня нет ни этого, ни того.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "I have neither this nor that.",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Both ни…ни… items are Genitive (этого, того) because нет requires Genitive. " +
                                        "ни does not change the case — it simply keeps whatever case the grammar already demands.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }

            // ── без ─────────────────────────────────────────────────────────
            item { NegSectionHeader("без — without") }
            item {
                NegCard(title = "без (without) — always Genitive") {
                    NegExampleRow("без воды", "without water", highlight = false)
                    NegExampleRow("без тебя", "without you", highlight = true)
                    NegExampleRow("кофе без сахара", "coffee without sugar", highlight = false)
                }
            }

            // ── Double negation ──────────────────────────────────────────────
            item { NegSectionHeader("Double Negation — Required in Russian") }
            item {
                NegCard(title = "никогда / нигде / никто / ничего") {
                    Text(
                        text = "Russian requires double negation: a negative word (никогда, никто, etc.) must be paired with не before the verb. " +
                                "This is not an error — it is the grammatical rule.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // Highlight box for the key rule
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "Key example:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Я никогда там не был.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "I was never there.",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Both никогда (never) AND не (not) are present before the verb. Omitting не would be incorrect.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Table header
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Word", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Text("Meaning", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.9f))
                        Text("Example (не is required)", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2.1f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))

                    listOf(
                        Triple("никогда", "never",   "Я никогда не ем мясо — I never eat meat"),
                        Triple("нигде",   "nowhere", "Я нигде не нашёл его — I found him nowhere"),
                        Triple("никто",   "no one",  "Никто не знает — No one knows"),
                        Triple("ничего",  "nothing", "Он ничего не сказал — He said nothing")
                    ).forEachIndexed { i, (word, meaning, example) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 5.dp)
                        ) {
                            Text(word, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1f))
                            Text(meaning, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(0.9f))
                            Text(example, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(2.1f))
                        }
                    }
                }
            }

            // ── ни...ни... ───────────────────────────────────────────────────
            item { NegSectionHeader("ни … ни … — Neither … Nor …") }
            item {
                NegCard(title = "ни … ни … (neither … nor …)") {
                    Text(
                        text = "Each element in ни…ни… takes whatever case the grammar of the sentence normally requires — " +
                                "ни does not change the case. Double negation still applies: не before the verb is required.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // Highlight box — user's example
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "Key example:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Это ни то, ни другое.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "It is neither that nor this.",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "то and другое are Nominative — they are predicates after это, so Nominative is the required case. " +
                                        "No не is needed here because the verb \"to be\" is implicit (есть is dropped in Russian present tense).",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "More examples — case follows normal sentence grammar:",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    listOf(
                        Pair(
                            "Он не читает ни книг, ни газет.",
                            "He reads neither books nor newspapers.\n(Genitive — negated direct object shifts to Gen)"
                        ),
                        Pair(
                            "Я не говорил ни с ним, ни с ней.",
                            "I spoke with neither him nor her.\n(Instrumental — required by preposition с)"
                        ),
                        Pair(
                            "Мне не нравится ни то, ни другое.",
                            "I like neither this nor that.\n(Nominative — то/другое are subjects of нравится)"
                        ),
                        Pair(
                            "У него нет ни денег, ни времени.",
                            "He has neither money nor time.\n(Genitive — required by нет)"
                        )
                    ).forEachIndexed { i, (ru, en) ->
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
                                Text(ru, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
                                Text(en, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Rule of thumb: identify what case each noun would have without ни. That is the case it keeps inside ни…ни…",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            // ── никто / ничто declension ─────────────────────────────────────
            item { NegSectionHeader("никто & ничто — Case Declension") }
            item {
                NegCard(title = "Declining negative pronouns") {
                    Text(
                        text = "These pronouns decline through all cases. When a preposition is needed, it splits the word: ни + preposition + rest.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Header
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Case", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.1f))
                        Text("никто (no one)", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f))
                        Text("ничто (nothing)", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))

                    listOf(
                        Triple("Nominative",   "никто",    "ничто"),
                        Triple("Genitive",     "никого",   "ничего"),
                        Triple("Dative",       "никому",   "ничему"),
                        Triple("Accusative",   "никого",   "ничто / ничего"),
                        Triple("Instrumental", "никем",    "ничем"),
                        Triple("Prepositional","ни о ком", "ни о чём")
                    ).forEachIndexed { i, (case, nkto, nchto) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 5.dp)
                        ) {
                            Text(case, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.1f))
                            Text(nkto, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f))
                            Text(nchto, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f))
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Preposition splits the word: ни с кем (with no one), ни о чём (about nothing), ни для кого (for no one).",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun NegSectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun NegCard(title: String, content: @Composable ColumnScope.() -> Unit) {
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
private fun NegExampleRow(russian: String, english: String, highlight: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (highlight) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                else MaterialTheme.colorScheme.surface
            )
            .padding(vertical = 4.dp)
    ) {
        Text(russian, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.4f))
        Text(english, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic, modifier = Modifier.weight(1.6f))
    }
}
