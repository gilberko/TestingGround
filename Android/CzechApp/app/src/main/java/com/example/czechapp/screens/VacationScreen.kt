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

private val vacation = listOf(
    "vacation / holiday" to "dovolená", "travel / trip" to "cesta / výlet",
    "journey" to "cesta / výprava", "tourism" to "turistika / cestovní ruch",
    "tourist" to "turista", "passport" to "pas / cestovní pas",
    "visa" to "vízum", "ticket" to "lístek / jízdenka / letenka",
    "suitcase / luggage" to "kufr / zavazadlo", "backpack" to "batoh",
    "hotel" to "hotel", "hostel" to "hostel", "room" to "pokoj",
    "check-in / check-out" to "přihlášení / odhlášení",
    "reservation / booking" to "rezervace",
    "flight" to "let / přelet", "plane" to "letadlo",
    "airport" to "letiště", "train" to "vlak",
    "bus" to "autobus", "car" to "auto",
    "taxi" to "taxi", "boat" to "loď",
    "map" to "mapa", "guide" to "průvodce",
    "tour" to "prohlídka / zájezd",
    "beach" to "pláž", "sea / ocean" to "moře / oceán",
    "mountain" to "hora", "hiking" to "turistika / túra",
    "camping" to "kempování", "tent" to "stan",
    "souvenir" to "suvenýr", "photo" to "fotka / fotografie",
    "currency / money" to "měna / peníze",
    "exchange rate" to "směnný kurz", "ATM" to "bankomat"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VacationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Vacation") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                            Text("English", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                            Text("Czech", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                        }
                        HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                        vacation.forEachIndexed { i, (en, cz) ->
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
