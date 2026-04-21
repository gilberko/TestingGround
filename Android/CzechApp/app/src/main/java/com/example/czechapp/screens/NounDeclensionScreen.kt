package com.example.czechapp.screens

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class DeclensionRow(val case: String, val singular: String, val plural: String)

private val muzDeclension = listOf(
    DeclensionRow("Nominative", "muž", "muži / mužové"),
    DeclensionRow("Genitive",   "muže", "mužů"),
    DeclensionRow("Dative",     "muži / mužovi", "mužům"),
    DeclensionRow("Accusative", "muže", "muže"),
    DeclensionRow("Vocative",   "muži!", "muži / mužové!"),
    DeclensionRow("Locative",   "o muži", "o mužích"),
    DeclensionRow("Instrumental","mužem", "muži")
)

private val zenaDeclension = listOf(
    DeclensionRow("Nominative", "žena", "ženy"),
    DeclensionRow("Genitive",   "ženy", "žen"),
    DeclensionRow("Dative",     "ženě", "ženám"),
    DeclensionRow("Accusative", "ženu", "ženy"),
    DeclensionRow("Vocative",   "ženo!", "ženy!"),
    DeclensionRow("Locative",   "o ženě", "o ženách"),
    DeclensionRow("Instrumental","ženou", "ženami")
)

private val mestoDeclension = listOf(
    DeclensionRow("Nominative", "město", "města"),
    DeclensionRow("Genitive",   "města", "měst"),
    DeclensionRow("Dative",     "městu", "městům"),
    DeclensionRow("Accusative", "město", "města"),
    DeclensionRow("Vocative",   "město!", "města!"),
    DeclensionRow("Locative",   "o městě", "o městech"),
    DeclensionRow("Instrumental","městem", "městy")
)

@Composable
private fun SectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun DeclensionTable(title: String, rows: List<DeclensionRow>) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("Case",       style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                Text("Singular",   style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                Text("Plural",     style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.5f))
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
            rows.forEachIndexed { i, row ->
                val bg = if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f) else MaterialTheme.colorScheme.surface
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = bg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 3.dp)) {
                        Text(row.case,     style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.5f))
                        Text(row.singular, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                        Text(row.plural,   style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.5f))
                    }
                }
            }
        }
    }
}

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
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                Text(
                    text = "Czech nouns change their endings based on grammatical case. There are several declension patterns based on gender (masculine animate, masculine inanimate, feminine, neuter) and the stem. Three representative patterns are shown below.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            item { SectionHeader("Masculine Animate: muž (man)") }
            item { DeclensionTable("muž — masculine animate", muzDeclension) }
            item { SectionHeader("Feminine: žena (woman)") }
            item { DeclensionTable("žena — feminine", zenaDeclension) }
            item { SectionHeader("Neuter: město (city)") }
            item { DeclensionTable("město — neuter", mestoDeclension) }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Key patterns to remember", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onTertiaryContainer)
                        Spacer(modifier = Modifier.height(4.dp))
                        listOf(
                            "Genitive plural is often 'zero' ending for neuter nouns: měst (of cities)",
                            "Locative always requires a preposition (o, v, na, při, po)",
                            "Dative and Locative singular are often identical for the same noun",
                            "Animate masculine nouns take -e/-i in vocative singular"
                        ).forEach {
                            Text("• $it", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onTertiaryContainer, modifier = Modifier.padding(vertical = 1.dp))
                        }
                    }
                }
            }
        }
    }
}
