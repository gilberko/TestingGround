package com.example.frenchproject.screens.dictionary

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

private data class FoodEntry(val french: String, val english: String)
private data class FoodSection(val title: String, val entries: List<FoodEntry>)

private val foodSections = listOf(
    FoodSection("Meals & General", listOf(
        FoodEntry("le petit-déjeuner", "breakfast"),
        FoodEntry("le déjeuner", "lunch"),
        FoodEntry("le dîner", "dinner"),
        FoodEntry("le goûter", "snack (afternoon)"),
        FoodEntry("la nourriture", "food")
    )),
    FoodSection("Utensils & Tableware", listOf(
        FoodEntry("le couteau", "knife"),
        FoodEntry("la fourchette", "fork"),
        FoodEntry("la cuillère", "spoon"),
        FoodEntry("la cuillère à soupe", "tablespoon"),
        FoodEntry("la cuillère à café", "teaspoon"),
        FoodEntry("l'assiette (f.)", "plate"),
        FoodEntry("le verre", "glass"),
        FoodEntry("la tasse", "cup")
    )),
    FoodSection("Drinks", listOf(
        FoodEntry("l'eau (f.)", "water"),
        FoodEntry("le café", "coffee"),
        FoodEntry("le thé", "tea"),
        FoodEntry("le lait", "milk"),
        FoodEntry("le vin", "wine"),
        FoodEntry("la bière", "beer")
    )),
    FoodSection("Meat", listOf(
        FoodEntry("la viande", "meat"),
        FoodEntry("le poulet", "chicken")
    )),
    FoodSection("Vegetables", listOf(
        FoodEntry("les pommes de terre (f.)", "potatoes"),
        FoodEntry("les carottes (f.)", "carrots"),
        FoodEntry("les oignons (m.)", "onions"),
        FoodEntry("les poivrons (m.)", "peppers"),
        FoodEntry("les concombres (m.)", "cucumbers"),
        FoodEntry("le persil", "parsley"),
        FoodEntry("l'aubergine (f.)", "eggplant"),
        FoodEntry("la patate douce", "sweet potato"),
        FoodEntry("l'ail (m.)", "garlic"),
        FoodEntry("les olives (f.)", "olives"),
        FoodEntry("le maïs", "corn")
    )),
    FoodSection("Pantry & Grains", listOf(
        FoodEntry("le pain", "bread"),
        FoodEntry("le riz", "rice"),
        FoodEntry("les pâtes (f.) / les nouilles (f.)", "pasta / noodles"),
        FoodEntry("la farine", "flour"),
        FoodEntry("le blé", "wheat"),
        FoodEntry("le beurre", "butter"),
        FoodEntry("l'huile (f.)", "oil"),
        FoodEntry("l'huile d'olive (f.)", "olive oil"),
        FoodEntry("le sel", "salt"),
        FoodEntry("la salade", "salad"),
        FoodEntry("la soupe", "soup")
    ))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Food", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FrenchBlue)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                Text(
                    text = "French food nouns have grammatical gender shown by the article: le (masculine), la (feminine), l' (before vowel), les (plural). The plural article les does not reveal gender — it is noted in parentheses.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            foodSections.forEach { section ->
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = section.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = FrenchBlue,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.35f))
                    Spacer(modifier = Modifier.height(4.dp))
                }
                items(section.entries.size) { i -> FoodCard(section.entries[i]) }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun FoodCard(entry: FoodEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 9.dp)
        ) {
            Text(
                text = entry.french,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 14.sp,
                modifier = Modifier.width(200.dp)
            )
            Text(
                text = entry.english,
                style = MaterialTheme.typography.bodyMedium,
                color = FrenchNavy
            )
        }
    }
}
