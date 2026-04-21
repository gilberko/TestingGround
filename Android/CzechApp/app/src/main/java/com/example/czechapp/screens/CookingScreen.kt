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

private val cooking = listOf(
    "to cook" to "vařit", "to bake" to "péct",
    "to fry" to "smažit", "to boil" to "vařit / vařit ve vodě",
    "to grill / barbecue" to "grilovat",
    "to roast" to "péct / opékat", "to steam" to "dusit párou",
    "to mix / stir" to "míchat", "to chop" to "sekat / krájet",
    "to slice" to "krájet na plátky", "to peel" to "loupat",
    "to season" to "ochucovat / kořenit",
    "to marinate" to "marinovat", "to serve" to "servírovat",
    "recipe" to "recept", "ingredient" to "ingredience / přísada",
    "pot / pan" to "hrnec / pánev", "oven" to "trouba",
    "frying pan" to "pánev (na smažení)",
    "knife" to "nůž", "fork" to "vidlička", "spoon" to "lžíce",
    "plate" to "talíř", "bowl" to "miska",
    "cutting board" to "prkénko", "grater" to "struhadlo",
    "colander / strainer" to "cedník",
    "raw" to "syrový", "cooked / done" to "uvařený / hotový",
    "fried" to "smažený", "baked" to "pečený",
    "grilled" to "grilovaný", "boiled" to "vařený",
    "spicy" to "pálivý / kořeněný", "sweet" to "sladký",
    "sour" to "kyselý", "salty" to "slaný",
    "bitter" to "hořký", "tasty / delicious" to "chutný / výborný",
    "portion" to "porce", "menu" to "jídelní lístek"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CookingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cooking") },
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
                        cooking.forEachIndexed { i, (en, cz) ->
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
