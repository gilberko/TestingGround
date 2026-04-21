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

private val clothes = listOf(
    "clothes / clothing" to "oblečení", "shirt" to "košile",
    "T-shirt" to "tričko", "blouse" to "halenka",
    "trousers / pants" to "kalhoty", "jeans" to "džíny",
    "skirt" to "sukně", "dress" to "šaty",
    "suit (men's)" to "oblek", "jacket" to "bunda / sako",
    "coat" to "kabát", "sweater / jumper" to "svetr",
    "hoodie" to "mikina s kapucí", "socks" to "ponožky",
    "underwear" to "spodní prádlo",
    "shoes" to "boty", "boots" to "holínky / boty",
    "sneakers / trainers" to "tenisky",
    "sandals" to "sandály", "slippers" to "pantofle",
    "hat" to "čepice / klobouk", "scarf" to "šála",
    "gloves" to "rukavice", "belt" to "pásek",
    "tie" to "kravata", "glasses" to "brýle",
    "bag / handbag" to "taška / kabelka",
    "backpack" to "batoh", "wallet" to "peněženka",
    "watch" to "hodinky", "jewellery" to "šperky",
    "ring" to "prsten", "necklace" to "náhrdelník",
    "swimsuit / swimwear" to "plavky",
    "uniform" to "uniforma", "pyjamas" to "pyžamo",
    "size" to "velikost", "material / fabric" to "materiál / látka"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Clothes") },
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
                        clothes.forEachIndexed { i, (en, cz) ->
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
