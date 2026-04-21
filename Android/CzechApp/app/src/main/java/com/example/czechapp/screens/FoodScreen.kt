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

private val food = listOf(
    "bread" to "chléb / chleba", "butter" to "máslo", "cheese" to "sýr",
    "milk" to "mléko", "egg / eggs" to "vejce", "meat" to "maso",
    "chicken" to "kuře / kuřecí", "beef" to "hovězí", "pork" to "vepřové",
    "fish" to "ryba", "vegetable(s)" to "zelenina", "fruit" to "ovoce",
    "apple" to "jablko", "orange" to "pomeranč", "banana" to "banán",
    "strawberry" to "jahoda", "potato" to "brambor / brambora",
    "tomato" to "rajče", "carrot" to "mrkev", "onion" to "cibule",
    "garlic" to "česnek", "pepper (bell)" to "paprika", "rice" to "rýže",
    "pasta" to "těstoviny", "soup" to "polévka", "salad" to "salát",
    "dessert" to "dezert", "cake" to "dort / koláč", "chocolate" to "čokoláda",
    "sugar" to "cukr", "salt" to "sůl", "oil / olive oil" to "olej / olivový olej",
    "coffee" to "káva", "tea" to "čaj", "juice" to "džus / šťáva",
    "water" to "voda", "beer" to "pivo", "wine" to "víno",
    "breakfast" to "snídaně", "lunch" to "oběd", "dinner" to "večeře",
    "snack" to "svačina", "meal" to "jídlo"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Food") },
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
                        food.forEachIndexed { i, (en, cz) ->
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
