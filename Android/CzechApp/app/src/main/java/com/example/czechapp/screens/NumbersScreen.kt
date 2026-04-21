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

private val cardinals = listOf(
    "0" to "nula", "1" to "jedna / jeden / jedno", "2" to "dva / dvě",
    "3" to "tři", "4" to "čtyři", "5" to "pět", "6" to "šest",
    "7" to "sedm", "8" to "osm", "9" to "devět", "10" to "deset",
    "11" to "jedenáct", "12" to "dvanáct", "13" to "třináct",
    "14" to "čtrnáct", "15" to "patnáct", "16" to "šestnáct",
    "17" to "sedmnáct", "18" to "osmnáct", "19" to "devatenáct",
    "20" to "dvacet", "21" to "dvacet jedna", "30" to "třicet",
    "40" to "čtyřicet", "50" to "padesát", "60" to "šedesát",
    "70" to "sedmdesát", "80" to "osmdesát", "90" to "devadesát",
    "100" to "sto", "200" to "dvě stě", "1000" to "tisíc",
    "1 000 000" to "milion"
)

private val ordinals = listOf(
    "1st" to "první", "2nd" to "druhý", "3rd" to "třetí",
    "4th" to "čtvrtý", "5th" to "pátý", "6th" to "šestý",
    "7th" to "sedmý", "8th" to "osmý", "9th" to "devátý",
    "10th" to "desátý"
)

@Composable
private fun SectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun PairTable(items: List<Pair<String, String>>) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("Number", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Czech", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(2f))
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
            items.forEachIndexed { i, (num, cz) ->
                val bg = if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(0.4f) else MaterialTheme.colorScheme.surface
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp), colors = CardDefaults.cardColors(bg), elevation = CardDefaults.cardElevation(0.dp)) {
                    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp)) {
                        Text(num, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1f))
                        Text(cz, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(2f))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumbersScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Numbers") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { SectionHeader("Cardinal Numbers") }
            item { PairTable(cardinals) }
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Number Agreement with Nouns", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.height(6.dp))
                        listOf(
                            "1 (jeden/jedna/jedno)" to "Nominative singular: jeden muž (one man)",
                            "2–4 (dva/tři/čtyři)" to "Nominative plural animate/inanimate: dva muži, tři ženy",
                            "5+ (pět, šest...)" to "Genitive plural: pět mužů, šest žen, deset měst"
                        ).forEach { (num, rule) ->
                            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                                Text(num, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.3f))
                                Text(rule, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(2f))
                            }
                        }
                    }
                }
            }
            item { SectionHeader("Ordinal Numbers") }
            item { PairTable(ordinals) }
        }
    }
}
