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

private data class DemonstrativeRow(val case: String, val masc: String, val fem: String, val neut: String, val pl: String)

private val etotRows = listOf(
    DemonstrativeRow("Nom",          "этот",   "эта",  "это",   "эти"),
    DemonstrativeRow("Gen",          "этого",  "этой", "этого", "этих"),
    DemonstrativeRow("Dat",          "этому",  "этой", "этому", "этим"),
    DemonstrativeRow("Acc (inanim)", "этот",   "эту",  "это",   "эти"),
    DemonstrativeRow("Acc (anim)",   "этого",  "эту",  "это",   "этих"),
    DemonstrativeRow("Inst",         "этим",   "этой", "этим",  "этими"),
    DemonstrativeRow("Prep",         "этом",   "этой", "этом",  "этих")
)

private val totRows = listOf(
    DemonstrativeRow("Nom",          "тот",   "та",  "то",   "те"),
    DemonstrativeRow("Gen",          "того",  "той", "того", "тех"),
    DemonstrativeRow("Dat",          "тому",  "той", "тому", "тем"),
    DemonstrativeRow("Acc (inanim)", "тот",   "ту",  "то",   "те"),
    DemonstrativeRow("Acc (anim)",   "того",  "ту",  "то",   "тех"),
    DemonstrativeRow("Inst",         "тем",   "той", "тем",  "теми"),
    DemonstrativeRow("Prep",         "том",   "той", "том",  "тех")
)

private val takojRows = listOf(
    DemonstrativeRow("Nom",          "такой",  "такая",  "такое",  "такие"),
    DemonstrativeRow("Gen",          "такого", "такой",  "такого", "таких"),
    DemonstrativeRow("Dat",          "такому", "такой",  "такому", "таким"),
    DemonstrativeRow("Acc (inanim)", "такой",  "такую",  "такое",  "такие"),
    DemonstrativeRow("Acc (anim)",   "такого", "такую",  "такое",  "таких"),
    DemonstrativeRow("Inst",         "таким",  "такой",  "таким",  "такими"),
    DemonstrativeRow("Prep",         "таком",  "такой",  "таком",  "таких")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThisAndThatScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("This and That") },
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
                            text = "Demonstrative pronouns and related words",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Russian has two sets of demonstrative pronouns: proximal (this/these — close to the speaker) " +
                                    "and distal (that/those — farther away or previously mentioned). Both sets agree with the noun " +
                                    "in gender, number, and case.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            // ── Это / Этот ───────────────────────────────────────────────────
            item { TatSectionHeader("Это / Этот / Эта / Эти — This / These") }
            item {
                TatCard(title = "Predicative это — invariant") {
                    Text(
                        text = "When это introduces or identifies a noun (\"This is…\" / \"That is…\"), it is invariant — " +
                                "it never changes regardless of the gender or number of the noun that follows.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TatExampleRow("Это стол.", "This is a table. (masc)")
                    TatExampleRow("Это книга.", "This is a book. (fem)")
                    TatExampleRow("Это окно.", "This is a window. (neut)")
                    TatExampleRow("Это дети.", "These are children. (pl)")
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "In all four sentences это is identical — it acts as the predicate, not as a modifier of the noun.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            item {
                TatCard(title = "Modifier forms — agrees with the noun") {
                    Text(
                        text = "When used directly before a noun (\"this table\", \"these children\"), " +
                                "the demonstrative must agree in gender, number, and case.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TatExampleRow("этот стол", "this table (masc)")
                    TatExampleRow("эта книга", "this book (fem)")
                    TatExampleRow("это окно", "this window (neut)")
                    TatExampleRow("эти дети", "these children (pl)")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Declension — all 6 cases:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    DemTable(etotRows)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Animate masculine and plural Accusative use the Genitive form (этого / этих).",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // ── То / Тот ────────────────────────────────────────────────────
            item { TatSectionHeader("То / Тот / Та / Те — That / Those") }
            item {
                TatCard(title = "Distal demonstratives — same rules, farther reference") {
                    Text(
                        text = "The тот/та/то/те set works exactly like этот/эта/это/эти but refers to something " +
                                "farther away from the speaker, or to something previously mentioned in the conversation.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TatExampleRow("тот стол", "that table (masc)")
                    TatExampleRow("та книга", "that book (fem)")
                    TatExampleRow("то окно", "that window (neut)")
                    TatExampleRow("те дети", "those children (pl)")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Declension — all 6 cases:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    DemTable(totRows)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Animate masculine and plural Accusative use the Genitive form (того / тех).",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // ── Так ─────────────────────────────────────────────────────────
            item { TatSectionHeader("Так — So / That Way") }
            item {
                TatCard(title = "Так — an invariant adverb") {
                    Text(
                        text = "Так is an adverb meaning \"so\", \"thus\", \"that way\", or \"like that\". " +
                                "It never changes form — no gender, number, or case agreement needed.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TatExampleRow("Не делай так.", "Don't do it like that.")
                    TatExampleRow("Это так важно!", "It's so important!")
                    TatExampleRow("Так и есть.", "That's exactly it. / So it is.")
                    TatExampleRow("Я так думаю.", "I think so.")
                    TatExampleRow("Почему ты так говоришь?", "Why do you say it like that?")
                }
            }

            // ── Такой ────────────────────────────────────────────────────────
            item { TatSectionHeader("Такой / Такая / Такое / Такие — Such / So…") }
            item {
                TatCard(title = "Intensifier meaning \"such a\" or \"so\" + adjective") {
                    Text(
                        text = "Такой is used before adjective+noun combinations to mean \"such a\" or to intensify. " +
                                "Unlike так, it agrees with the noun in gender, number, and case — just like an adjective.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TatExampleRow("Такой красивый город!", "Such a beautiful city!")
                    TatExampleRow("Такая интересная книга!", "Such an interesting book!")
                    TatExampleRow("Такое вкусное блюдо!", "Such a delicious dish!")
                    TatExampleRow("Такие добрые люди!", "Such kind people!")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Declension — all 6 cases:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    DemTable(takojRows)
                    Spacer(modifier = Modifier.height(8.dp))
                    TatExampleRow("Я никогда не видел такого фильма.", "I've never seen such a film. (Gen)")
                    TatExampleRow("Он пришёл с такими людьми.", "He came with such people. (Inst)")
                }
            }

            // ── То, что ──────────────────────────────────────────────────────
            item { TatSectionHeader("То, что — That Which / What") }
            item {
                TatCard(title = "Relative conjunction — \"the thing that\"") {
                    Text(
                        text = "То, что is a relative conjunction meaning \"that which\" or \"what\" (in the sense of \"the thing that\"). " +
                                "The то is typically followed by a comma before the subordinate clause.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Contrast with plain что:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "что alone — reporting a fact:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Я знаю, что он сказал.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "I know what he said. (reporting a specific thing)",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "то, что — making the clause the subject or object:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "То, что он сказал, неправда.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "What he said is not true. (\"the thing that he said\")",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    TatExampleRow("То, что ты делаешь, важно.", "What you're doing is important.")
                    TatExampleRow("Дай мне то, что лежит на столе.", "Give me what's on the table.")
                    TatExampleRow("Это не то, что я имел в виду.", "That's not what I meant.")
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "The то in \"то, что\" can decline: \"дай мне то, что…\" (Acc) → \"я доволен тем, что…\" (Inst).",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // ── Quick Reference ───────────────────────────────────────────────
            item { TatSectionHeader("Quick Reference") }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                            Text("Word", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.4f))
                            Text("Meaning / Use", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
                        }
                        HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                        listOf(
                            "это (predicative)" to "This is… / That is… — invariant, identifies nouns",
                            "этот / эта / это / эти" to "This (modifier) — agrees with noun in gender, number, case",
                            "тот / та / то / те" to "That (modifier) — distal or previously mentioned",
                            "так" to "So, thus, that way — invariant adverb",
                            "такой / такая / такое / такие" to "Such a, so… — intensifier, agrees with noun",
                            "то, что" to "That which / What — \"the thing that\" (relative clause)"
                        ).forEachIndexed { i, (word, meaning) ->
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
                                    color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.4f))
                                Text(meaning, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(2f))
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
private fun TatSectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun TatCard(title: String, content: @Composable ColumnScope.() -> Unit) {
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
private fun TatExampleRow(russian: String, english: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(russian, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
        Text(english, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
    Spacer(modifier = Modifier.height(2.dp))
}

@Composable
private fun DemTable(rows: List<DemonstrativeRow>) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
        Text("Case", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
        Text("Masc", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        Text("Fem",  style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        Text("Neut", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        Text("Pl",   style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
    }
    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
    rows.forEachIndexed { i, row ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                    else MaterialTheme.colorScheme.surface
                )
                .padding(vertical = 4.dp)
        ) {
            Text(row.case, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.5f))
            Text(row.masc, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text(row.fem,  style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text(row.neut, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text(row.pl,   style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        }
    }
}
