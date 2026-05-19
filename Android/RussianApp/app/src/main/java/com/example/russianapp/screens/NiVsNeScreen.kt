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
fun NiVsNeScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("не- vs ни-") },
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

            // ── Overview ──────────────────────────────────────────────────────
            item { NiNeSectionHeader("Overview") }
            item {
                NiNeCard(title = "Two Prefixes, Different Uses") {
                    Text(
                        text = "Russian has two prefixes that appear in negative contexts but work differently:",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    listOf(
                        "ни- words (никто, ничего, нигде…) — always negative pronouns and adverbs. Always require не before the verb (double negation is the rule, not an error).",
                        "не- words (нечего, некого, негде…) — impersonal negative predicates meaning \"there is no [person/thing/time] to...\". No double не is needed.",
                        "Bonus: не- can also produce NON-negative words like некто (someone) and нечто (something) — covered in the last section."
                    ).forEach { line ->
                        Text(
                            text = "• $line",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }
                }
            }

            // ── ни- words ─────────────────────────────────────────────────────
            item { NiNeSectionHeader("ни- Words — Always Negative (Double не Required)") }
            item {
                NiNeCard(title = "никто, ничего, нигде, никуда, никогда…") {
                    Text(
                        text = "ни- words are negative pronouns and adverbs. When used in a sentence with a verb, не must also appear before that verb. This double negation is required — omitting не would be a grammar error.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "Double negation — the rule:",
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
                                text = "Both никогда (never) AND не (not) are present. Saying Я никогда там был is incorrect — не is mandatory.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Word", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Text("Meaning", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.9f))
                        Text("Example (не required)", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2.1f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        Triple("никто",   "no one",          "Никто не пришёл — No one came"),
                        Triple("ничего",  "nothing",         "Он ничего не сказал — He said nothing"),
                        Triple("нигде",   "nowhere (loc.)",  "Я нигде не нашёл — I found it nowhere"),
                        Triple("никуда",  "nowhere (dir.)",  "Я никуда не пойду — I won't go anywhere"),
                        Triple("никогда", "never",           "Я никогда не забуду — I'll never forget"),
                        Triple("никак",   "in no way",       "Я никак не мог — I couldn't manage it"),
                        Triple("ничей",   "no one's",        "Это ничья вещь — This belongs to no one")
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
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Standalone use of ничего:",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    NiNeExampleRow("Что случилось? — Ничего.", "What happened? — Nothing.", highlight = false)
                    NiNeExampleRow("Как дела? — Ничего.", "How's it going? — Okay / Not bad. (colloquial)", highlight = true)
                }
            }

            // ── не- words ─────────────────────────────────────────────────────
            item { NiNeSectionHeader("не- Words — \"There Is No... To...\"") }
            item {
                NiNeCard(title = "нечего, некого, негде, некуда, некогда, незачем") {
                    Text(
                        text = "не- impersonal words say \"there is no [person/thing/place/time] to do something.\" They are predicates, not simple pronouns.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    listOf(
                        "The person affected is in the dative case (optional): Мне, Тебе, Ему, Ей, Нам, Вам, Им",
                        "No double не is needed — the не- in the word itself carries the negation",
                        "These words have no nominative form (they are inherently impersonal)"
                    ).forEach { line ->
                        Text("• $line", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(vertical = 2.dp))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Word", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Text("Meaning", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.1f))
                        Text("Example", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        Triple("некого",  "no one to...",     "Мне некого спросить — I have no one to ask"),
                        Triple("нечего",  "nothing to...",    "Мне нечего делать — I have nothing to do"),
                        Triple("негде",   "nowhere to be",    "Нам негде жить — We have nowhere to live"),
                        Triple("некуда",  "nowhere to go",    "Мне некуда идти — I have nowhere to go"),
                        Triple("некогда", "no time to...",    "Мне некогда читать — I have no time to read"),
                        Triple("незачем", "no reason to...",  "Незачем спешить — There's no reason to rush")
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
                            Text(meaning, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.1f))
                            Text(example, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(2f))
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "некого and нечего decline through all cases except Nominative. The dative person can be omitted when clear from context.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            // ── ничего vs нечего ──────────────────────────────────────────────
            item { NiNeSectionHeader("ничего vs нечего — A Closer Look") }
            item {
                NiNeCard(title = "Your intuition about \"anything\" vs \"nothing\"") {
                    Text(
                        text = "Your comparison is largely on point. Here is the precise picture:",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "ничего — \"nothing\" with double не",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Он ничего не сказал.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "He said nothing. / He didn't say anything.",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "In negative sentences, ничего often translates as \"anything\" in English because English uses single negation (\"didn't say anything\") where Russian uses double negation (ничего + не). So your intuition about ничего ≈ \"anything\" in a negative sentence is correct — the grammar just works differently between the two languages.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Standalone: Ничего. — Nothing. / Not bad. (colloquial)",
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
                                text = "нечего — \"nothing to [do]\"",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Мне нечего сказать.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Text(
                                text = "I have nothing to say.",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "нечего means there is nothing available to do something with. It cannot be translated as \"anything\" — it specifically points to availability or possibility. No не before the verb is needed (there often is no separate verb at all).",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Quick test: is a regular verb present and you are negating its object? → use ничего + не. Are you saying \"there's nothing available to do X\"? → use нечего.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            // ── Full comparison table ──────────────────────────────────────────
            item { NiNeSectionHeader("Full Comparison Table") }
            item {
                NiNeCard(title = "ни- vs не- — Side by Side") {
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("ни- form", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(0.85f))
                        Text("ни- meaning", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Text("не- form", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(0.85f))
                        Text("не- meaning", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        listOf("никто",   "no one (+ не)",      "некого",  "no one to [verb]"),
                        listOf("ничего",  "nothing (+ не)",     "нечего",  "nothing to [verb]"),
                        listOf("нигде",   "nowhere (location)", "негде",   "nowhere to be"),
                        listOf("никуда",  "nowhere (direction)","некуда",  "nowhere to go"),
                        listOf("никогда", "never (+ не)",       "некогда", "no time to [verb]"),
                        listOf("никак",   "in no way (+ не)",   "незачем", "no reason to [verb]")
                    ).forEachIndexed { i, (ni, niMeaning, ne, neMeaning) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 5.dp)
                        ) {
                            Text(ni, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(0.85f))
                            Text(niMeaning, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1f))
                            Text(ne, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(0.85f))
                            Text(neMeaning, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.3f))
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Example pairs:",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    listOf(
                        Pair("Я никого не вижу.",           "I don't see anyone. (ни- + не before verb)"),
                        Pair("Мне некого позвать.",         "I have no one to invite. (не- impersonal, no не)"),
                        Pair("Он нигде не был.",            "He wasn't anywhere. (ни- + не before verb)"),
                        Pair("Нам негде поставить машину.", "We have nowhere to park the car. (не- impersonal)"),
                        Pair("Я никогда не слышал этого.",  "I never heard that. (ни- + не before verb)"),
                        Pair("Мне некогда объяснять.",      "I have no time to explain. (не- impersonal)")
                    ).forEachIndexed { i, (ru, en) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 4.dp)
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(ru, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
                                Text(en, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }

            // ── не- that is NOT negative ───────────────────────────────────────
            item { NiNeSectionHeader("не- That Is NOT Negative") }
            item {
                NiNeCard(title = "некто, нечто, несколько, некоторый") {
                    Text(
                        text = "Not every не- word is negative. Several common не- words have purely positive or neutral meanings:",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    NiNeExampleRow("некто", "a certain person / someone (formal/literary, Nominative only)", highlight = false)
                    Text(
                        text = "    Некто позвонил. — A certain person called. / Someone called.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    NiNeExampleRow("нечто", "something (unusual, extraordinary — formal/literary)", highlight = true)
                    Text(
                        text = "    Произошло нечто странное. — Something strange happened.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    NiNeExampleRow("несколько", "several / a few — not negative at all", highlight = false)
                    Text(
                        text = "    У меня несколько вопросов. — I have several questions.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    NiNeExampleRow("некоторый", "a certain / some — not negative", highlight = true)
                    Text(
                        text = "    Некоторые люди не понимают. — Some people don't understand.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "Your observation about некто is correct — it means \"a certain person / someone\" and is not negative despite the не- prefix. The prefix alone does not determine whether a word is negative. You have to know each word individually.",
                                style = MaterialTheme.typography.bodySmall,
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
private fun NiNeSectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun NiNeCard(title: String, content: @Composable ColumnScope.() -> Unit) {
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
private fun NiNeExampleRow(label: String, description: String, highlight: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (highlight) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                else MaterialTheme.colorScheme.surface
            )
            .padding(vertical = 4.dp)
    ) {
        Text(label, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f), color = MaterialTheme.colorScheme.primary)
        Text(description, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic, modifier = Modifier.weight(1.8f))
    }
}
