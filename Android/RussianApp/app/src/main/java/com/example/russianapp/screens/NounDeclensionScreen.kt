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

// ── Helpers ───────────────────────────────────────────────────────────────────

@Composable
private fun NounSectionHeader(title: String) {
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

@Composable
private fun DeclTableHeader() {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
        Text("Case",        style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
        Text("Singular",    style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
        Text("Plural",      style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
    }
    HorizontalDivider(modifier = Modifier.padding(bottom = 2.dp))
}

@Composable
private fun GenderTableHeader() {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
        Text("Gender",      style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
        Text("Singular",    style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
        Text("Plural",      style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
    }
    HorizontalDivider(modifier = Modifier.padding(bottom = 2.dp))
}

@Composable
private fun DeclRow(caseName: String, singular: String, plural: String, highlight: Boolean = false) {
    val bg = if (highlight) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
             else MaterialTheme.colorScheme.surface
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
        colors = CardDefaults.cardColors(containerColor = bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 4.dp)) {
            Text(caseName,  style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(2f))
            Text(singular,  style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary,   fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
            Text(plural,    style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
        }
    }
}

// ── Cards ─────────────────────────────────────────────────────────────────────

@Composable
private fun SingularPluralIntroCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Plurals are formed by changing a noun's ending — which ending depends on gender " +
                        "and whether the stem is hard or soft. This section covers the nominative case; " +
                        "other cases have their own plural endings, shown in the tables below.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Masculine nouns",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            listOf(
                "Singular: usually a bare consonant ending — стол, брат. Soft stems end in -й or -ь — музей, словарь.",
                "Regular plural: hard stem → -ы (стол → столы); soft stem (-й/-ь) → -и (музей → музеи, словарь → словари).",
                "Spelling rule: -ы becomes -и after г, к, х, ж, ч, ш, щ (учебник → учебники, not \"учебникы\").",
                "Exceptions: some common masculine nouns take a stressed -а/-я plural instead — дом → дома, город → города, учитель → учителя, глаз → глаза. These must be memorized individually."
            ).forEach { line ->
                Text("• $line",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(vertical = 2.dp))
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Neuter nouns",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            listOf(
                "Singular nominative ends in -о or -е.",
                "Plural: -о → -а (слово → слова); -е → -я (море → моря). Regular, with very few exceptions."
            ).forEach { line ->
                Text("• $line",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(vertical = 2.dp))
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Feminine nouns",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            listOf(
                "Singular nominative ends in -а/-я, or in -ь.",
                "Plural: -а → -ы (книга → книги, note г triggers the ы→и spelling rule above); -я → -и (неделя → недели); -ь → -и (дверь → двери)."
            ).forEach { line ->
                Text("• $line",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(vertical = 2.dp))
            }

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Quick reference",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            GenderTableHeader()
            DeclRow("Masculine (hard)", "стол",    "столы",    highlight = false)
            DeclRow("Masculine (soft)", "словарь", "словари",  highlight = true)
            DeclRow("Neuter (-о)",      "слово",   "слова",    highlight = false)
            DeclRow("Neuter (-е)",      "море",    "моря",     highlight = true)
            DeclRow("Feminine (-а)",    "книга",   "книги",    highlight = false)
            DeclRow("Feminine (-ь)",    "дверь",   "двери",    highlight = true)

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "A few nouns are fully irregular: друг → друзья, стул → стулья, брат → братья " +
                        "(consonant stem + -ья). A handful are suppletive, using a different root entirely: " +
                        "человек → люди, ребёнок → дети. These are memorized as vocabulary, not derived from a rule.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
private fun IntroCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Russian nouns decline (change endings) based on grammatical case. " +
                        "There are three main declension patterns, determined by the noun's gender and ending.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            listOf(
                "1st declension — Feminine & masculine nouns ending in -а / -я  (книга, папа, дядя)",
                "2nd declension — Masculine nouns with zero ending + neuter -о / -е  (стол, брат, слово, море)",
                "3rd declension — Feminine nouns ending in -ь  (дверь, ночь, мать*)"
            ).forEach { line ->
                Text("• $line",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(vertical = 2.dp))
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "* Some nouns have irregular stems (мать → матерь-) — those follow special patterns.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
private fun FirstDeclensionCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Nouns ending in -а / -я. Most are feminine; a few masculine (папа, дядя).",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "книга (book) — hard stem",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            DeclTableHeader()
            DeclRow("Nominative",    "книга",   "книги",    highlight = false)
            DeclRow("Genitive",      "книги",   "книг",     highlight = true)
            DeclRow("Dative",        "книге",   "книгам",   highlight = false)
            DeclRow("Accusative",    "книгу",   "книги",    highlight = true)
            DeclRow("Instrumental",  "книгой",  "книгами",  highlight = false)
            DeclRow("Prepositional", "книге",   "книгах",   highlight = true)

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "неделя (week) — soft stem (-я ending)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            DeclTableHeader()
            DeclRow("Nominative",    "неделя",   "недели",    highlight = false)
            DeclRow("Genitive",      "недели",   "недель",    highlight = true)
            DeclRow("Dative",        "неделе",   "неделям",   highlight = false)
            DeclRow("Accusative",    "неделю",   "недели",    highlight = true)
            DeclRow("Instrumental",  "неделей",  "неделями",  highlight = false)
            DeclRow("Prepositional", "неделе",   "неделях",   highlight = true)

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Note: Genitive plural of hard-stem -а nouns: drop -а (книга → книг). " +
                        "Soft-stem -я: drop -я, add ь (недель).",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
private fun SecondDeclensionMascCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Masculine nouns with a zero (no vowel) ending in nominative singular.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "стол (table) — hard stem",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            DeclTableHeader()
            DeclRow("Nominative",    "стол",    "столы",    highlight = false)
            DeclRow("Genitive",      "стола",   "столов",   highlight = true)
            DeclRow("Dative",        "столу",   "столам",   highlight = false)
            DeclRow("Accusative",    "стол",    "столы",    highlight = true)
            DeclRow("Instrumental",  "столом",  "столами",  highlight = false)
            DeclRow("Prepositional", "столе",   "столах",   highlight = true)

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "словарь (dictionary) — soft stem (-ь ending)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            DeclTableHeader()
            DeclRow("Nominative",    "словарь",   "словари",    highlight = false)
            DeclRow("Genitive",      "словаря",   "словарей",   highlight = true)
            DeclRow("Dative",        "словарю",   "словарям",   highlight = false)
            DeclRow("Accusative",    "словарь",   "словари",    highlight = true)
            DeclRow("Instrumental",  "словарём",  "словарями",  highlight = false)
            DeclRow("Prepositional", "словаре",   "словарях",   highlight = true)

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Animate masculine accusative = genitive (брат → вижу брата).\n" +
                        "Inanimate masculine accusative = nominative (стол → вижу стол).",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
private fun SecondDeclensionNeutCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Neuter nouns ending in -о (hard) or -е (soft).",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "слово (word) — hard stem (-о ending)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            DeclTableHeader()
            DeclRow("Nominative",    "слово",   "слова",    highlight = false)
            DeclRow("Genitive",      "слова",   "слов",     highlight = true)
            DeclRow("Dative",        "слову",   "словам",   highlight = false)
            DeclRow("Accusative",    "слово",   "слова",    highlight = true)
            DeclRow("Instrumental",  "словом",  "словами",  highlight = false)
            DeclRow("Prepositional", "слове",   "словах",   highlight = true)

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "море (sea) — soft stem (-е ending)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            DeclTableHeader()
            DeclRow("Nominative",    "море",   "моря",    highlight = false)
            DeclRow("Genitive",      "моря",   "морей",   highlight = true)
            DeclRow("Dative",        "морю",   "морям",   highlight = false)
            DeclRow("Accusative",    "море",   "моря",    highlight = true)
            DeclRow("Instrumental",  "морем",  "морями",  highlight = false)
            DeclRow("Prepositional", "море",   "морях",   highlight = true)

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Neuter accusative always = nominative (neuter nouns are never animate in grammar).",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
private fun ThirdDeclensionCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Feminine nouns ending in -ь (soft sign) in nominative singular.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "дверь (door)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            DeclTableHeader()
            DeclRow("Nominative",    "дверь",    "двери",     highlight = false)
            DeclRow("Genitive",      "двери",    "дверей",    highlight = true)
            DeclRow("Dative",        "двери",    "дверям",    highlight = false)
            DeclRow("Accusative",    "дверь",    "двери",     highlight = true)
            DeclRow("Instrumental",  "дверью",   "дверями",   highlight = false)
            DeclRow("Prepositional", "двери",    "дверях",    highlight = true)

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "ночь (night)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            DeclTableHeader()
            DeclRow("Nominative",    "ночь",    "ночи",     highlight = false)
            DeclRow("Genitive",      "ночи",    "ночей",    highlight = true)
            DeclRow("Dative",        "ночи",    "ночам",    highlight = false)
            DeclRow("Accusative",    "ночь",    "ночи",     highlight = true)
            DeclRow("Instrumental",  "ночью",   "ночами",   highlight = false)
            DeclRow("Prepositional", "ночи",    "ночах",    highlight = true)

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Key feature: Genitive, Dative, and Prepositional singular all end in -и. " +
                        "Instrumental singular always ends in -ью.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

// ── Screen ────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NounDeclensionScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Noun Declension") },
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
            item { NounSectionHeader("Singular vs. Plural — Nominative Case") }
            item { SingularPluralIntroCard() }

            item { NounSectionHeader("Overview — Declension Classes") }
            item { IntroCard() }

            item { NounSectionHeader("1st Declension  (-а / -я)") }
            item { FirstDeclensionCard() }

            item { NounSectionHeader("2nd Declension — Masculine") }
            item { SecondDeclensionMascCard() }

            item { NounSectionHeader("2nd Declension — Neuter  (-о / -е)") }
            item { SecondDeclensionNeutCard() }

            item { NounSectionHeader("3rd Declension  (-ь, feminine)") }
            item { ThirdDeclensionCard() }
        }
    }
}
