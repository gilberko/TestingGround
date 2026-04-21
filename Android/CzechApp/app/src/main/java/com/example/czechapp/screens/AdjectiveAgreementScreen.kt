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

private data class AdjRow(val case: String, val mascAnim: String, val mascInan: String, val fem: String, val neut: String, val plural: String)

private val novyForms = listOf(
    AdjRow("Nominative", "nový",  "nový",  "nová",  "nové",  "noví / nové"),
    AdjRow("Genitive",   "nového","nového","nové",  "nového","nových"),
    AdjRow("Dative",     "novému","novému","nové",  "novému","novým"),
    AdjRow("Accusative", "nového","nový",  "novou", "nové",  "nové / nové"),
    AdjRow("Vocative",   "nový!", "nový!", "nová!", "nové!", "noví / nové!"),
    AdjRow("Locative",   "novém", "novém", "nové",  "novém", "nových"),
    AdjRow("Instrumental","novým","novým", "novou", "novým", "novými")
)

@Composable
private fun SectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun AdjTable() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("nový (new) — hard adjective declension", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("Case", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f))
                Text("M.An", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1f))
                Text("M.In", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1f))
                Text("Fem", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.tertiary, modifier = Modifier.weight(1f))
                Text("Neut", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.outline, modifier = Modifier.weight(1f))
                Text("Pl", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
            novyForms.forEachIndexed { i, row ->
                val bg = if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f) else MaterialTheme.colorScheme.surface
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = bg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 3.dp)) {
                        Text(row.case,     style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.3f))
                        Text(row.mascAnim, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1f))
                        Text(row.mascInan, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1f))
                        Text(row.fem,      style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.tertiary, modifier = Modifier.weight(1f))
                        Text(row.neut,     style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outline, modifier = Modifier.weight(1f))
                        Text(row.plural,   style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.2f))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdjectiveAgreementScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adjective Agreement") },
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
                    text = "Czech adjectives agree with the noun they modify in gender, number, and case. There are two declension types: hard (ending in -ý/-á/-é) and soft (ending in -í for all genders).",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            item { SectionHeader("Hard Adjectives (-ý/-á/-é)") }
            item { AdjTable() }
            item { SectionHeader("Soft Adjectives (-í)") }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Soft adjectives use the same form (-í) for all genders in nominative, and decline with -ího, -ím, etc. in other cases. They are simpler than hard adjectives.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Examples of soft adjectives:", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
                        listOf(
                            "jarní (spring/springtime) — jarní den / jarní ráno / jarní noc",
                            "domácí (home/homemade) — domácí mazlíček (pet)",
                            "cizí (foreign) — cizí jazyk (foreign language)",
                            "moderní (modern) — moderní město (modern city)"
                        ).forEach {
                            Text("• $it", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(vertical = 1.dp))
                        }
                    }
                }
            }
            item { SectionHeader("Short / Predicative Forms") }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "When an adjective follows být (to be) as a predicate, Czech sometimes uses a short/nominal form.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        listOf(
                            "To je zajímavé. (That is interesting.)",
                            "Je to nové? (Is it new?)",
                            "Jsem rád/ráda. (I am glad. — m/f)",
                            "Byl/Byla jsi unavený/unavená? (Were you tired? — m/f)"
                        ).forEach {
                            Text("• $it", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(vertical = 1.dp))
                        }
                    }
                }
            }
        }
    }
}
