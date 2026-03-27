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

/** A four-column gender/number row used inside declension tables. */
@Composable
private fun DeclensionRow(
    label: String,
    ending: String,
    form: String,
    example: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
    ) {
        Text(label,   style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.5f))
        Text(ending,  style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1f))
        Text(form,    style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
        Text(example, style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontStyle = FontStyle.Italic, modifier = Modifier.weight(2f))
    }
}

@Composable
private fun RowHeader() {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
        Text("Gender/No.", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
        Text("Ending",     style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        Text("Form",       style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
        Text("Example",    style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
    }
    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
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
                        "This screen covers the nominative case (the basic dictionary form).",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "There are two main stem types — hard and soft — which determine the endings used.",
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

            // Unstressed -ый
            Text(
                text = "Unstressed ending: -ый (e.g. новый — new)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            RowHeader()
            DeclensionRow("Masculine",  "-ый",  "новый",   "новый дом (new house)")
            DeclensionRow("Feminine",   "-ая",  "новая",   "новая книга (new book)")
            DeclensionRow("Neuter",     "-ое",  "новое",   "новое слово (new word)")
            DeclensionRow("Plural",     "-ые",  "новые",   "новые дома (new houses)")

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(10.dp))

            // Stressed -ой
            Text(
                text = "Stressed ending: -ой (e.g. молодой — young)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            RowHeader()
            DeclensionRow("Masculine",  "-ой",  "молодой",   "молодой человек (young person)")
            DeclensionRow("Feminine",   "-ая",  "молодая",   "молодая женщина (young woman)")
            DeclensionRow("Neuter",     "-ое",  "молодое",   "молодое вино (young wine)")
            DeclensionRow("Plural",     "-ые",  "молодые",   "молодые люди (young people)")

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Note: the feminine, neuter, and plural endings are the same for -ый and -ой; only the masculine form differs in stress.",
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
                        "This changes the masculine nominative ending from -ый to -ий, " +
                        "and the plural from -ые to -ие. The feminine and neuter remain -ая / -ое.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Example: высокий (tall) — stem ends in к",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(6.dp))
            RowHeader()
            DeclensionRow("Masculine",  "-ий",  "высокий",   "высокий мужчина (tall man)")
            DeclensionRow("Feminine",   "-ая",  "высокая",   "высокая башня (tall tower)")
            DeclensionRow("Neuter",     "-ое",  "высокое",   "высокое здание (tall building)")
            DeclensionRow("Plural",     "-ие",  "высокие",   "высокие горы (tall mountains)")

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "More examples: маленький (small), тихий (quiet), хороший (good), горячий (hot), свежий (fresh).",
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
                text = "Truly soft-stem adjectives have a soft sign (ь) in the stem. These use -яя / -ее endings instead of -ая / -ое.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Example: синий (dark blue)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            RowHeader()
            DeclensionRow("Masculine",  "-ий",  "синий",   "синий цвет (dark blue colour)")
            DeclensionRow("Feminine",   "-яя",  "синяя",   "синяя ручка (dark blue pen)")
            DeclensionRow("Neuter",     "-ее",  "синее",   "синее небо (dark blue sky)")
            DeclensionRow("Plural",     "-ие",  "синие",   "синие глаза (dark blue eyes)")

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Other soft-stem adjectives: осенний (autumn), зимний (winter), летний (summer), весенний (spring), утренний (morning), последний (last).",
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
                        "They are formed by removing the long-form ending and adding a short suffix.",
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
                Text("Form",      style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                Text("Suffix",    style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Short",     style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                Text("Sentence",  style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2.5f))
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
            Text(
                text = "More examples:",
                style = MaterialTheme.typography.labelMedium
            )
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
                text = "Quick Reference — Nominative Endings",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("Type",       style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
                Text("Masc.",      style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Fem.",       style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Neut.",      style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Plural",     style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))

            listOf(
                listOf("Hard (-ый)",         "-ый",  "-ая",  "-ое",  "-ые"),
                listOf("Hard stressed (-ой)", "-ой",  "-ая",  "-ое",  "-ые"),
                listOf("After г/к/х/ж/ш/щ/ч", "-ий", "-ая",  "-ое",  "-ие"),
                listOf("Soft (-ний etc.)",   "-ий",  "-яя",  "-ее",  "-ие")
            ).forEachIndexed { i, (type, m, f, n, pl) ->
                val bg = if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                         else MaterialTheme.colorScheme.surface
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = bg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 4.dp)) {
                        Text(type, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(2f))
                        Text(m,    style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary,   fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Text(f,    style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Text(n,    style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.tertiary,  fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Text(pl,   style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outline,   fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
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

            item { SectionHeader("Summary Table") }
            item { SummaryCard() }
        }
    }
}
