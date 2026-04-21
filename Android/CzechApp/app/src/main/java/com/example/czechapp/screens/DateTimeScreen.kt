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
            items.forEachIndexed { i, (en, cz) ->
                val bg = if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(0.4f) else MaterialTheme.colorScheme.surface
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp), colors = CardDefaults.cardColors(bg), elevation = CardDefaults.cardElevation(0.dp)) {
                    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp)) {
                        Text(en, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.5f))
                        Text(cz, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1.5f))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimeScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Date & Time") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { SectionHeader("Days of the Week") }
            item {
                PairTable(listOf(
                    "Monday"    to "pondělí",
                    "Tuesday"   to "úterý",
                    "Wednesday" to "středa",
                    "Thursday"  to "čtvrtek",
                    "Friday"    to "pátek",
                    "Saturday"  to "sobota",
                    "Sunday"    to "neděle"
                ))
            }
            item { SectionHeader("Months") }
            item {
                PairTable(listOf(
                    "January"   to "leden",
                    "February"  to "únor",
                    "March"     to "březen",
                    "April"     to "duben",
                    "May"       to "květen",
                    "June"      to "červen",
                    "July"      to "červenec",
                    "August"    to "srpen",
                    "September" to "září",
                    "October"   to "říjen",
                    "November"  to "listopad",
                    "December"  to "prosinec"
                ))
            }
            item { SectionHeader("Seasons") }
            item {
                PairTable(listOf(
                    "Spring" to "jaro",
                    "Summer" to "léto",
                    "Autumn / Fall" to "podzim",
                    "Winter" to "zima"
                ))
            }
            item { SectionHeader("Telling the Time") }
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Kolik je hodin? — What time is it?", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.height(6.dp))
                        listOf(
                            "Je jedna hodina." to "It is one o'clock.",
                            "Jsou dvě hodiny." to "It is two o'clock.",
                            "Je půl třetí." to "It is half past two. (lit. half of three)",
                            "Je čtvrt na tři." to "It is quarter past two. (lit. quarter to three)",
                            "Je tři čtvrtě na tři." to "It is quarter to three.",
                            "Je deset hodin ráno." to "It is 10 AM.",
                            "Je šest hodin večer." to "It is 6 PM."
                        ).forEach { (cz, en) ->
                            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                                Text(cz, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                                Text(en, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(1.5f))
                            }
                        }
                    }
                }
            }
            item { SectionHeader("Useful Date & Time Phrases") }
            item {
                PairTable(listOf(
                    "today"             to "dnes / dneska",
                    "yesterday"         to "včera",
                    "tomorrow"          to "zítra",
                    "this week"         to "tento týden",
                    "last week"         to "minulý týden",
                    "next week"         to "příští týden",
                    "in the morning"    to "ráno / dopoledne",
                    "in the afternoon"  to "odpoledne",
                    "in the evening"    to "večer",
                    "at night"          to "v noci",
                    "now"               to "teď / nyní",
                    "soon"              to "brzy",
                    "later"             to "později",
                    "always"            to "vždy / pořád",
                    "never"             to "nikdy",
                    "sometimes"         to "někdy",
                    "often"             to "často",
                    "rarely"            to "zřídka"
                ))
            }
        }
    }
}
