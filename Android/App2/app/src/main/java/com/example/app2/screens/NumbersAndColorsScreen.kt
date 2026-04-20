package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class NacNumberEntry(val numeral: Int, val cardinal: String, val ordinal: String)
private data class NacColorEntry(val pt: String, val en: String, val note: String = "")
private data class NacColorCategory(val title: String, val entries: List<NacColorEntry>)

private val nacSingleDigits = listOf(
    NacNumberEntry(0, "zero", "—"),
    NacNumberEntry(1, "um / uma", "primeiro / primeira"),
    NacNumberEntry(2, "dois / duas", "segundo / segunda"),
    NacNumberEntry(3, "três", "terceiro / terceira"),
    NacNumberEntry(4, "quatro", "quarto / quarta"),
    NacNumberEntry(5, "cinco", "quinto / quinta"),
    NacNumberEntry(6, "seis", "sexto / sexta"),
    NacNumberEntry(7, "sete", "sétimo / sétima"),
    NacNumberEntry(8, "oito", "oitavo / oitava"),
    NacNumberEntry(9, "nove", "nono / nona")
)

private val nacTens = listOf(
    NacNumberEntry(10, "dez", "décimo / décima"),
    NacNumberEntry(20, "vinte", "vigésimo / vigésima"),
    NacNumberEntry(30, "trinta", "trigésimo / trigésima"),
    NacNumberEntry(40, "quarenta", "quadragésimo / quadragésima"),
    NacNumberEntry(50, "cinquenta", "quinquagésimo / quinquagésima"),
    NacNumberEntry(60, "sessenta", "sexagésimo / sexagésima"),
    NacNumberEntry(70, "setenta", "septuagésimo / septuagésima"),
    NacNumberEntry(80, "oitenta", "octogésimo / octogésima"),
    NacNumberEntry(90, "noventa", "nonagésimo / nonagésima"),
    NacNumberEntry(100, "cem / cento*", "centésimo / centésima")
)

private val nacColorCategories = listOf(
    NacColorCategory(
        "Basic Colors",
        listOf(
            NacColorEntry("vermelho / vermelha", "red", "gender-variable"),
            NacColorEntry("azul", "blue", "invariable"),
            NacColorEntry("verde", "green", "invariable"),
            NacColorEntry("amarelo / amarela", "yellow", "gender-variable"),
            NacColorEntry("laranja", "orange", "invariable"),
            NacColorEntry("roxo / roxa", "purple", "gender-variable"),
            NacColorEntry("cor-de-rosa", "pink", "invariable"),
            NacColorEntry("castanho / castanha", "brown", "gender-variable"),
            NacColorEntry("preto / preta", "black", "gender-variable"),
            NacColorEntry("branco / branca", "white", "gender-variable"),
            NacColorEntry("cinzento / cinzenta", "grey", "gender-variable"),
            NacColorEntry("cinza", "grey (alt.)", "invariable, informal")
        )
    ),
    NacColorCategory(
        "Shades & Modifiers",
        listOf(
            NacColorEntry("claro / clara", "light (e.g. azul claro)", "gender-variable"),
            NacColorEntry("escuro / escura", "dark (e.g. verde escuro)", "gender-variable"),
            NacColorEntry("vivo / viva", "bright", "gender-variable"),
            NacColorEntry("pálido / pálida", "pale", "gender-variable")
        )
    ),
    NacColorCategory(
        "More Colors",
        listOf(
            NacColorEntry("bege", "beige", "invariable"),
            NacColorEntry("bordeaux", "burgundy / wine", "invariable"),
            NacColorEntry("dourado / dourada", "golden", "gender-variable"),
            NacColorEntry("prateado / prateada", "silver", "gender-variable"),
            NacColorEntry("creme", "cream", "invariable"),
            NacColorEntry("turquesa", "turquoise", "invariable"),
            NacColorEntry("lilás", "lilac", "invariable"),
            NacColorEntry("índigo", "indigo", "invariable")
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumbersAndColorsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Numbers and Colors") },
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
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Numbers", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    }
                }
            }
            item { NacNumberCard("0–9", nacSingleDigits) }
            item { NacNumberCard("10–100", nacTens) }
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("Notes", style = MaterialTheme.typography.titleSmall)
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
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Colors", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    }
                }
            }
            items(nacColorCategories) { category ->
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(text = category.title, style = MaterialTheme.typography.titleMedium)
                        category.entries.forEach { entry ->
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = entry.pt,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontStyle = FontStyle.Italic,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Text(
                                        text = entry.en,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                if (entry.note.isNotEmpty()) {
                                    Text(
                                        text = entry.note,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NacNumberCard(title: String, entries: List<NacNumberEntry>) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "#", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.15f))
                Text(text = "Cardinal", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.35f))
                Text(text = "Ordinal", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.50f))
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            entries.forEach { entry ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)) {
                    Text(text = entry.numeral.toString(), style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(0.15f))
                    Text(text = entry.cardinal, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(0.35f))
                    Text(text = entry.ordinal, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(0.50f))
                }
            }
        }
    }
}
