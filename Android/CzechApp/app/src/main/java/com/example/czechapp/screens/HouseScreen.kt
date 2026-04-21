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

private val houseWords = listOf(
    "house" to "dům", "apartment / flat" to "byt", "room" to "pokoj / místnost",
    "kitchen" to "kuchyně", "living room" to "obývací pokoj",
    "bedroom" to "ložnice", "bathroom" to "koupelna",
    "toilet / WC" to "záchod / WC", "hallway" to "chodba",
    "dining room" to "jídelna", "balcony" to "balkon",
    "garden / yard" to "zahrada", "garage" to "garáž",
    "basement / cellar" to "sklep", "attic" to "půda",
    "door" to "dveře", "window" to "okno", "wall" to "zeď / stěna",
    "floor" to "podlaha", "ceiling" to "strop", "roof" to "střecha",
    "stairs" to "schody", "elevator / lift" to "výtah",
    "table" to "stůl", "chair" to "židle", "sofa / couch" to "pohovka / gauč",
    "bed" to "postel", "wardrobe / closet" to "skříň",
    "shelf" to "police", "drawer" to "šuplík",
    "lamp" to "lampa", "television / TV" to "televize / TV",
    "refrigerator / fridge" to "lednice", "oven" to "trouba",
    "stove / cooker" to "sporák", "washing machine" to "pračka",
    "dishwasher" to "myčka (nádobí)", "microwave" to "mikrovlnka",
    "sink" to "dřez (kitchen) / umyvadlo (bathroom)",
    "shower" to "sprcha", "bathtub" to "vana",
    "mirror" to "zrcadlo", "carpet / rug" to "koberec",
    "curtain" to "záclona / závěs", "pillow" to "polštář",
    "blanket" to "deka", "key" to "klíč", "lock" to "zámek"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HouseScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("House") },
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
                        houseWords.forEachIndexed { i, (en, cz) ->
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
