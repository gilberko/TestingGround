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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class MotionPair(val pronoun: String, val jit: String, val chodit: String)
private val footRows = listOf(
    MotionPair("já",       "jdu",   "chodím"),
    MotionPair("ty",       "jdeš",  "chodíš"),
    MotionPair("on/ona",   "jde",   "chodí"),
    MotionPair("my",       "jdeme", "chodíme"),
    MotionPair("vy",       "jdete", "chodíte"),
    MotionPair("oni/ony",  "jdou",  "chodí")
)
private data class VehiclePair(val pronoun: String, val jet: String, val jezdit: String)
private val vehicleRows = listOf(
    VehiclePair("já",       "jedu",   "jezdím"),
    VehiclePair("ty",       "jedeš",  "jezdíš"),
    VehiclePair("on/ona",   "jede",   "jezdí"),
    VehiclePair("my",       "jedeme", "jezdíme"),
    VehiclePair("vy",       "jedete", "jezdíte"),
    VehiclePair("oni/ony",  "jedou",  "jezdí")
)

@Composable
private fun SectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovementScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movement") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { SectionHeader("On Foot: jít vs. chodit") }
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("jít — going somewhere right now (unidirectional)\nchodit — going regularly or back and forth (habitual)", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("• Jdu do školy. (I'm going to school — right now.)", style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic)
                        Text("• Chodím do školy každý den. (I go to school every day.)", style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                            Text("", modifier = Modifier.weight(1f))
                            Text("jít", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                            Text("chodit", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                        }
                        footRows.forEach { row ->
                            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp)) {
                                Text(row.pronoun, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(1f))
                                Text(row.jit, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.5f))
                                Text(row.chodit, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.5f))
                            }
                        }
                    }
                }
            }
            item { SectionHeader("By Vehicle: jet vs. jezdit") }
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("jet — travelling right now (unidirectional)\njezdit — travelling regularly or back and forth (habitual)", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("• Jedu do Prahy. (I'm going to Prague — right now, by vehicle.)", style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic)
                        Text("• Jezdím do Prahy každý měsíc. (I go to Prague every month.)", style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                            Text("", modifier = Modifier.weight(1f))
                            Text("jet", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                            Text("jezdit", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                        }
                        vehicleRows.forEach { row ->
                            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp)) {
                                Text(row.pronoun, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(1f))
                                Text(row.jet, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.5f))
                                Text(row.jezdit, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.5f))
                            }
                        }
                    }
                }
            }
            item { SectionHeader("Direction Words") }
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        listOf(
                            "straight ahead" to "rovně / přímo",
                            "left" to "vlevo / nalevo",
                            "right" to "vpravo / napravo",
                            "turn left" to "odbočit doleva",
                            "turn right" to "odbočit doprava",
                            "north / south / east / west" to "sever / jih / východ / západ",
                            "near / far" to "blízko / daleko",
                            "up / down" to "nahoře / dole",
                            "in front of" to "před",
                            "behind" to "za",
                            "next to" to "vedle"
                        ).forEachIndexed { i, (en, cz) ->
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
