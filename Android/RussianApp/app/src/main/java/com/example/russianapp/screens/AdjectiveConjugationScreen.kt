package com.example.russianapp.screens

import androidx.compose.foundation.layout.Column
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

// ── Shared helpers ────────────────────────────────────────────────────────────

@Composable
private fun SectionHeader(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        HorizontalDivider(
            modifier = Modifier.padding(top = 4.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

/** Five-column row: Case | Masc | Fem | Neut | Plural */
@Composable
private fun CaseRow(
    caseName: String,
    masc: String,
    fem: String,
    neut: String,
    plural: String,
    highlight: Boolean = false
) {
    val bg = if (highlight) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
             else MaterialTheme.colorScheme.surface
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
        colors = CardDefaults.cardColors(containerColor = bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 4.dp)) {
            Text(caseName, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.8f))
            Text(masc,   style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary,   fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
            Text(fem,    style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
            Text(neut,   style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.tertiary,  fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
            Text(plural, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outline,   fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
        }
    }
}

@Composable
private fun CaseTableHeader() {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
        Text("Case",   style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.8f))
        Text("Masc.",  style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
        Text("Fem.",   style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
        Text("Neut.",  style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
        Text("Plural", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
    }
    HorizontalDivider(modifier = Modifier.padding(bottom = 2.dp))
}

// ── Content cards ─────────────────────────────────────────────────────────────

@Composable
private fun IntroCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Russian adjectives agree with the noun they describe in gender, number, and case. " +
                        "All six cases are shown for each adjective type.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "There are two main stem types — hard and soft — which determine the endings used. " +
                        "Animacy affects the Accusative: animate masculine/plural mirrors Genitive; inanimate mirrors Nominative.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun HardStemCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Hard-stem adjectives are the most common. The stem ends in a plain consonant.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Unstressed -ый: новый
            Text(
                text = "Unstressed ending: -ый  (e.g. новый — new)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            CaseTableHeader()
            CaseRow("Nominative",   "новый",  "новая",  "новое",  "новые",  highlight = false)
            CaseRow("Genitive",     "нового", "новой",  "нового", "новых",  highlight = true)
            CaseRow("Dative",       "новому", "новой",  "новому", "новым",  highlight = false)
            CaseRow("Accusative*",  "нов./нового", "новую", "новое", "нов./новых", highlight = true)
            CaseRow("Instrumental", "новым",  "новой",  "новым",  "новыми", highlight = false)
            CaseRow("Prepositional","новом",  "новой",  "новом",  "новых",  highlight = true)

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(10.dp))

            // Stressed -ой: молодой
            Text(
                text = "Stressed ending: -ой  (e.g. молодой — young)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            CaseTableHeader()
            CaseRow("Nominative",   "молодой",  "молодая",  "молодое",  "молодые",  highlight = false)
            CaseRow("Genitive",     "молодого", "молодой",  "молодого", "молодых",  highlight = true)
            CaseRow("Dative",       "молодому", "молодой",  "молодому", "молодым",  highlight = false)
            CaseRow("Accusative*",  "мол./молодого", "молодую", "молодое", "мол./молодых", highlight = true)
            CaseRow("Instrumental", "молодым",  "молодой",  "молодым",  "молодыми", highlight = false)
            CaseRow("Prepositional","молодом",  "молодой",  "молодом",  "молодых",  highlight = true)

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "* Accusative: inanimate = Nominative form; animate = Genitive form.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
private fun VelarAndHusherCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Spelling rule: after г, к, х, ж, ш, щ, ч",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Russian spelling prohibits ы after these consonants — use и instead. " +
                        "Masculine nominative -ый → -ий; plural -ые → -ие. Feminine and neuter remain -ая / -ое.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Example: высокий (tall) — stem ends in к",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(6.dp))
            CaseTableHeader()
            CaseRow("Nominative",   "высокий",  "высокая",  "высокое",  "высокие",  highlight = false)
            CaseRow("Genitive",     "высокого", "высокой",  "высокого", "высоких",  highlight = true)
            CaseRow("Dative",       "высокому", "высокой",  "высокому", "высоким",  highlight = false)
            CaseRow("Accusative*",  "выс./высокого", "высокую", "высокое", "выс./высоких", highlight = true)
            CaseRow("Instrumental", "высоким",  "высокой",  "высоким",  "высокими", highlight = false)
            CaseRow("Prepositional","высоком",  "высокой",  "высоком",  "высоких",  highlight = true)

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "* Accusative: inanimate = Nominative form; animate = Genitive form.\n" +
                        "More examples: маленький, тихий, хороший, горячий, свежий.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
private fun SoftStemCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Truly soft-stem adjectives have a soft sign (ь) in the stem. They use -яя / -ее instead of -ая / -ое.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Example: синий (dark blue)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            CaseTableHeader()
            CaseRow("Nominative",   "синий",  "синяя",  "синее",  "синие",  highlight = false)
            CaseRow("Genitive",     "синего", "синей",  "синего", "синих",  highlight = true)
            CaseRow("Dative",       "синему", "синей",  "синему", "синим",  highlight = false)
            CaseRow("Accusative*",  "синий/синего", "синюю", "синее", "синие/синих", highlight = true)
            CaseRow("Instrumental", "синим",  "синей",  "синим",  "синими", highlight = false)
            CaseRow("Prepositional","синем",  "синей",  "синем",  "синих",  highlight = true)

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "* Accusative: inanimate = Nominative form; animate = Genitive form.\n" +
                        "Other soft-stem adjectives: осенний, зимний, летний, весенний, утренний, последний.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
private fun ShortFormCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Short-form adjectives are used only in predicate position (after быть / to be). " +
                        "They do not decline by case — only by gender/number.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Example: красивый (beautiful) → short form",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))

            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("Form",     style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                Text("Suffix",   style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Short",    style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                Text("Sentence", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2.5f))
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))

            listOf(
                listOf("Masculine",  "—",   "красив",   "Он красив. (He is handsome.)"),
                listOf("Feminine",   "-а",  "красива",  "Она красива. (She is beautiful.)"),
                listOf("Neuter",     "-о",  "красиво",  "Это красиво. (This is beautiful.)"),
                listOf("Plural",     "-ы",  "красивы",  "Они красивы. (They are beautiful.)")
            ).forEach { (form, suffix, short, sentence) ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)) {
                    Text(form,     style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.5f))
                    Text(suffix,   style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1f))
                    Text(short,    style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                    Text(sentence, style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic, modifier = Modifier.weight(2.5f))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "More examples:", style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.height(4.dp))
            listOf(
                "умный → умён / умна / умно / умны  (smart)",
                "свободный → свободен / свободна / свободно / свободны  (free/available)",
                "готовый → готов / готова / готово / готовы  (ready)"
            ).forEach { example ->
                Text("• $example",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 1.dp))
            }
        }
    }
}

@Composable
private fun SummaryCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Quick Reference — All Cases",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Masculine endings by case and stem type:",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("Case",              style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
                Text("Hard (-ый)",        style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                Text("Velar (-ий)",       style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                Text("Soft (-ний)",       style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))

            listOf(
                listOf("Nominative",    "-ый/-ой", "-ий",  "-ий"),
                listOf("Genitive",      "-ого",    "-ого", "-его"),
                listOf("Dative",        "-ому",    "-ому", "-ему"),
                listOf("Accusative",    "-ый/-ого","-ий/-ого","-ий/-его"),
                listOf("Instrumental",  "-ым",     "-им",  "-им"),
                listOf("Prepositional", "-ом",     "-ом",  "-ем")
            ).forEachIndexed { i, (case, hard, velar, soft) ->
                val bg = if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                         else MaterialTheme.colorScheme.surface
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = bg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 4.dp)) {
                        Text(case,  style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(2f))
                        Text(hard,  style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary,   fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                        Text(velar, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                        Text(soft,  style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.tertiary,  fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                    }
                }
            }
        }
    }
}

// ── Screen ────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdjectiveConjugationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adjective Declension") },
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
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { SectionHeader("Overview") }
            item { IntroCard() }

            item { SectionHeader("Hard-Stem Adjectives (-ый / -ой)") }
            item { HardStemCard() }

            item { SectionHeader("After Velars & Hushers (-ий)") }
            item { VelarAndHusherCard() }

            item { SectionHeader("Soft-Stem Adjectives (-ний etc.)") }
            item { SoftStemCard() }

            item { SectionHeader("Short Form (Predicate Use)") }
            item { ShortFormCard() }

            item { SectionHeader("Summary — Masculine Endings by Case") }
            item { SummaryCard() }
        }
    }
}
