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

private val colors = listOf(
    "red" to "červený/červená/červené",
    "blue" to "modrý/modrá/modré",
    "green" to "zelený/zelená/zelené",
    "yellow" to "žlutý/žlutá/žluté",
    "orange" to "oranžový/oranžová/oranžové",
    "purple / violet" to "fialový/fialová/fialové",
    "pink" to "růžový/růžová/růžové",
    "white" to "bílý/bílá/bílé",
    "black" to "černý/černá/černé",
    "grey" to "šedý/šedá/šedé",
    "brown" to "hnědý/hnědá/hnědé",
    "light blue" to "světle modrý",
    "dark blue" to "tmavě modrý",
    "gold" to "zlatý/zlatá/zlaté",
    "silver" to "stříbrný/stříbrná/stříbrné",
    "colourful" to "barevný/barevná/barevné",
    "light (of colour)" to "světlý",
    "dark (of colour)" to "tmavý"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Colors") },
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
                    text = "Czech color adjectives agree with the noun they modify in gender, number, and case. Forms shown: masculine / feminine / neuter.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                            Text("English", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                            Text("Czech", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(2f))
                        }
                        HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                        colors.forEachIndexed { i, (en, cz) ->
                            val bg = if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(0.4f) else MaterialTheme.colorScheme.surface
                            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp), colors = CardDefaults.cardColors(bg), elevation = CardDefaults.cardElevation(0.dp)) {
                                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp)) {
                                    Text(en, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1f))
                                    Text(cz, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(2f))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
