package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
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

private data class CookingEntry(val en: String, val pt: String, val notes: String = "")

private val coffeeAndDrinks = listOf(
    CookingEntry("bica", "bica", "EP espresso shot"),
    CookingEntry("to grind", "moer"),
    CookingEntry("ground coffee", "café moído"),
    CookingEntry("ground beans", "grãos moídos"),
    CookingEntry("to brew", "preparar / fazer"),
    CookingEntry("to roast (coffee)", "torrar"),
    CookingEntry("tea", "chá"),
    CookingEntry("green tea", "chá verde"),
    CookingEntry("milk", "leite"),
    CookingEntry("soy", "soja")
)

private val cookingVerbs = listOf(
    CookingEntry("to cook", "cozinhar"),
    CookingEntry("to boil", "ferver"),
    CookingEntry("to fry", "fritar"),
    CookingEntry("to stir", "mexer"),
    CookingEntry("to simmer", "cozinhar em lume brando"),
    CookingEntry("to freeze", "congelar"),
    CookingEntry("to bake", "cozer no forno"),
    CookingEntry("deep fry", "fritar em óleo abundante"),
    CookingEntry("to mash", "fazer puré"),
    CookingEntry("to cut", "cortar"),
    CookingEntry("to whisk", "bater"),
    CookingEntry("to roast (meat/veg)", "assar"),
    CookingEntry("to dry", "secar"),
    CookingEntry("to mix", "misturar"),
    CookingEntry("to mix in", "incorporar"),
    CookingEntry("to add", "adicionar / juntar"),
    CookingEntry("to break", "partir"),
    CookingEntry("to stew", "guisar"),
    CookingEntry("to extract", "extrair"),
    CookingEntry("to pre-heat", "pré-aquecer"),
    CookingEntry("to fill", "encher / rechear"),
    CookingEntry("to remove", "retirar"),
    CookingEntry("to squeeze", "espremer"),
    CookingEntry("let it rest", "deixar repousar"),
    CookingEntry("the dough rises", "a massa cresce / levedar")
)

private val kitchenEquipment = listOf(
    CookingEntry("pan", "frigideira"),
    CookingEntry("skillet", "frigideira de ferro"),
    CookingEntry("pot", "panela"),
    CookingEntry("stove", "fogão"),
    CookingEntry("freezer", "congelador"),
    CookingEntry("oven", "forno"),
    CookingEntry("microwave", "microondas"),
    CookingEntry("blender", "liquidificador / varinha mágica", "varinha mágica = EP immersion blender"),
    CookingEntry("mixer", "batedeira"),
    CookingEntry("knife", "faca"),
    CookingEntry("fork", "garfo"),
    CookingEntry("spoon", "colher"),
    CookingEntry("table spoon", "colher de sopa"),
    CookingEntry("tea spoon", "colher de chá"),
    CookingEntry("measurement cup", "copo medidor")
)

private val pantryAndSpices = listOf(
    CookingEntry("oil", "óleo"),
    CookingEntry("olive oil", "azeite"),
    CookingEntry("canola oil", "óleo de colza"),
    CookingEntry("vinegar", "vinagre"),
    CookingEntry("butter", "manteiga"),
    CookingEntry("salt", "sal"),
    CookingEntry("black pepper", "pimenta preta"),
    CookingEntry("white pepper", "pimenta branca"),
    CookingEntry("paprika", "colorau / páprica"),
    CookingEntry("cinnamon", "canela"),
    CookingEntry("parsley", "salsa"),
    CookingEntry("garlic", "alho"),
    CookingEntry("basil", "manjericão"),
    CookingEntry("powder", "pó"),
    CookingEntry("cornflour", "farinha de milho / amido de milho")
)

private val baking = listOf(
    CookingEntry("dough", "massa"),
    CookingEntry("fermentation", "fermentação"),
    CookingEntry("yeast", "levedura / fermento"),
    CookingEntry("baking powder", "fermento em pó"),
    CookingEntry("flour", "farinha"),
    CookingEntry("wheat", "trigo"),
    CookingEntry("corn", "milho"),
    CookingEntry("without gluten", "sem glúten"),
    CookingEntry("allergen", "alergénio"),
    CookingEntry("pre-heated oven", "forno pré-aquecido")
)

private val waterAndTemperature = listOf(
    CookingEntry("boiling water", "água a ferver"),
    CookingEntry("cold water", "água fria"),
    CookingEntry("ice cold", "gelado/a")
)

private val produceAndFruits = listOf(
    CookingEntry("onion", "cebola"),
    CookingEntry("mushrooms", "cogumelos"),
    CookingEntry("sweet potato", "batata-doce"),
    CookingEntry("potato", "batata"),
    CookingEntry("tomato", "tomate"),
    CookingEntry("lettuce", "alface"),
    CookingEntry("lemon", "limão"),
    CookingEntry("orange", "laranja"),
    CookingEntry("apple", "maçã"),
    CookingEntry("pear", "pêra"),
    CookingEntry("peach", "pêssego"),
    CookingEntry("eggs", "ovos"),
    CookingEntry("sugar", "açúcar")
)

private val dairyAndFats = listOf(
    CookingEntry("cream", "natas"),
    CookingEntry("cheese", "queijo"),
    CookingEntry("cheddar", "queijo cheddar"),
    CookingEntry("mozzarella", "mozzarella"),
    CookingEntry("chocolate", "chocolate"),
    CookingEntry("vanilla", "baunilha")
)

private val meatAndProteins = listOf(
    CookingEntry("meat", "carne"),
    CookingEntry("steak", "bife"),
    CookingEntry("ground beef", "carne picada"),
    CookingEntry("turkey", "peru"),
    CookingEntry("chicken", "frango"),
    CookingEntry("sausage", "salsicha"),
    CookingEntry("hot dogs", "cachorros quentes")
)

private val breadAndPastry = listOf(
    CookingEntry("bread", "pão"),
    CookingEntry("pastry", "pastelaria / massa"),
    CookingEntry("puff pastry", "massa folhada"),
    CookingEntry("dessert", "sobremesa")
)

private val stateAdjectives = listOf(
    CookingEntry("fried", "frito/a"),
    CookingEntry("boiled", "cozido/a"),
    CookingEntry("mashed", "em puré"),
    CookingEntry("cooked", "cozinhado/a"),
    CookingEntry("raw", "cru/crua")
)

private val tasteAndTexture = listOf(
    CookingEntry("sweet", "doce"),
    CookingEntry("sour", "azedo/a"),
    CookingEntry("spicy", "picante"),
    CookingEntry("savory", "salgado/a"),
    CookingEntry("salty", "salgado/a")
)

private val general = listOf(
    CookingEntry("stew", "guisado"),
    CookingEntry("ingredients", "ingredientes")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CookingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cooking") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { SectionHeader("Coffee & Hot Drinks") }
            items(coffeeAndDrinks) { CookingCard(it) }

            item { SectionHeader("Cooking Verbs") }
            items(cookingVerbs) { CookingCard(it) }

            item { SectionHeader("Kitchen Equipment") }
            items(kitchenEquipment) { CookingCard(it) }

            item { SectionHeader("Pantry & Spices") }
            items(pantryAndSpices) { CookingCard(it) }

            item { SectionHeader("Baking") }
            items(baking) { CookingCard(it) }

            item { SectionHeader("Water & Temperature") }
            items(waterAndTemperature) { CookingCard(it) }

            item { SectionHeader("Produce & Fruits") }
            items(produceAndFruits) { CookingCard(it) }

            item { SectionHeader("Dairy & Sweets") }
            items(dairyAndFats) { CookingCard(it) }

            item { SectionHeader("Meat & Proteins") }
            items(meatAndProteins) { CookingCard(it) }

            item { SectionHeader("Bread & Pastry") }
            items(breadAndPastry) { CookingCard(it) }

            item { SectionHeader("State (Adjectives)") }
            items(stateAdjectives) { CookingCard(it) }

            item { SectionHeader("Taste & Texture") }
            items(tasteAndTexture) { CookingCard(it) }

            item { SectionHeader("General") }
            items(general) { CookingCard(it) }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 8.dp, bottom = 2.dp)
    )
}

@Composable
private fun CookingCard(entry: CookingEntry) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = entry.en,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = entry.pt,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            if (entry.notes.isNotEmpty()) {
                Text(
                    text = entry.notes,
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
