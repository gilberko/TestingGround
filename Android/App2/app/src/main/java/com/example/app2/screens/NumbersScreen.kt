package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
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

private data class NumberEntry(
    val numeral: Int,
    val cardinal: String,
    val ordinal: String
)

private val singleDigits = listOf(
    NumberEntry(0, "zero", "—"),
    NumberEntry(1, "um / uma", "primeiro / primeira"),
    NumberEntry(2, "dois / duas", "segundo / segunda"),
    NumberEntry(3, "três", "terceiro / terceira"),
    NumberEntry(4, "quatro", "quarto / quarta"),
    NumberEntry(5, "cinco", "quinto / quinta"),
    NumberEntry(6, "seis", "sexto / sexta"),
    NumberEntry(7, "sete", "sétimo / sétima"),
    NumberEntry(8, "oito", "oitavo / oitava"),
    NumberEntry(9, "nove", "nono / nona")
)

private val tens = listOf(
    NumberEntry(10, "dez", "décimo / décima"),
    NumberEntry(20, "vinte", "vigésimo / vigésima"),
    NumberEntry(30, "trinta", "trigésimo / trigésima"),
    NumberEntry(40, "quarenta", "quadragésimo / quadragésima"),
    NumberEntry(50, "cinquenta", "quinquagésimo / quinquagésima"),
    NumberEntry(60, "sessenta", "sexagésimo / sexagésima"),
    NumberEntry(70, "setenta", "septuagésimo / septuagésima"),
    NumberEntry(80, "oitenta", "octogésimo / octogésima"),
    NumberEntry(90, "noventa", "nonagésimo / nonagésima"),
    NumberEntry(100, "cem / cento*", "centésimo / centésima")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumbersScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Numbers") },
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
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { NumberCard("0–9", singleDigits) }
            item { NumberCard("10–100", tens) }
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Notes",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "* cem is used for exactly 100; cento is used in compounds (e.g. cento e um = 101).",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "Cardinal numbers 1 and 2 agree in gender: um/uma, dois/duas.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NumberCard(title: String, entries: List<NumberEntry>) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            // Header row
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "#",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(0.15f)
                )
                Text(
                    text = "Cardinal",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(0.35f)
                )
                Text(
                    text = "Ordinal",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(0.50f)
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            entries.forEach { entry ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)
                ) {
                    Text(
                        text = entry.numeral.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(0.15f)
                    )
                    Text(
                        text = entry.cardinal,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(0.35f)
                    )
                    Text(
                        text = entry.ordinal,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(0.50f)
                    )
                }
            }
        }
    }
}
