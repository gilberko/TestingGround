package com.example.czechapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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

private val adjectives = listOf(
    "big / large" to "velký", "small / little" to "malý", "tall / high" to "vysoký",
    "short / low" to "nízký / malý", "long" to "dlouhý", "short (length)" to "krátký",
    "wide / broad" to "široký", "narrow" to "úzký", "heavy" to "těžký",
    "light (weight)" to "lehký", "hard / difficult" to "těžký / náročný",
    "easy / simple" to "snadný / jednoduchý", "fast / quick" to "rychlý",
    "slow" to "pomalý", "new" to "nový", "old" to "starý",
    "young" to "mladý", "good" to "dobrý", "bad" to "špatný",
    "beautiful / nice" to "krásný / hezký", "ugly" to "ošklivý",
    "interesting" to "zajímavý", "boring" to "nudný",
    "important" to "důležitý", "expensive" to "drahý", "cheap" to "levný",
    "full" to "plný", "empty" to "prázdný", "hot" to "horký",
    "cold" to "studený / chladný", "warm" to "teplý",
    "clean" to "čistý", "dirty" to "špinavý",
    "healthy" to "zdravý", "sick / ill" to "nemocný",
    "happy" to "šťastný / spokojený", "sad" to "smutný",
    "tired" to "unavený", "hungry" to "hladový",
    "thirsty" to "žíznivý", "strong" to "silný",
    "weak" to "slabý", "clever / smart" to "chytrý / inteligentní",
    "kind / nice" to "hodný / milý", "funny" to "vtipný"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdjectivesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adjectives") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                Text(
                    "Forms shown are masculine nominative singular. Add -á (feminine), -é (neuter) endings for agreement.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                            Text("English", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                            Text("Czech", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                        }
                        HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                        adjectives.forEachIndexed { i, (en, cz) ->
                            val bg = if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(0.4f) else MaterialTheme.colorScheme.surface
                            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp), colors = CardDefaults.cardColors(bg), elevation = CardDefaults.cardElevation(0.dp)) {
                                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp)) {
                                    Text(en, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.5f))
                                    Text(cz, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
