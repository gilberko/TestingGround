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


private val coffeeAndDrinks = listOf(
    DictWord("bica", "bica", "EP espresso shot"),
    DictWord("moer", "to grind"),
    DictWord("café moído", "ground coffee"),
    DictWord("grãos moídos", "ground beans"),
    DictWord("preparar / fazer", "to brew"),
    DictWord("torrar", "to roast (coffee)"),
    DictWord("chá", "tea"),
    DictWord("chá verde", "green tea"),
    DictWord("leite", "milk"),
    DictWord("soja", "soy")
)

private val cookingVerbs = listOf(
    DictWord("cozinhar", "to cook"),
    DictWord("ferver", "to boil"),
    DictWord("fritar", "to fry"),
    DictWord("mexer", "to stir"),
    DictWord("cozinhar em lume brando", "to simmer"),
    DictWord("congelar", "to freeze"),
    DictWord("cozer no forno", "to bake"),
    DictWord("fritar em óleo abundante", "deep fry"),
    DictWord("fazer puré", "to mash"),
    DictWord("cortar", "to cut"),
    DictWord("bater", "to whisk"),
    DictWord("assar", "to roast (meat/veg)"),
    DictWord("secar", "to dry"),
    DictWord("misturar", "to mix"),
    DictWord("incorporar", "to mix in"),
    DictWord("adicionar / juntar", "to add"),
    DictWord("partir", "to break"),
    DictWord("guisar", "to stew"),
    DictWord("extrair", "to extract"),
    DictWord("pré-aquecer", "to pre-heat"),
    DictWord("encher / rechear", "to fill"),
    DictWord("retirar", "to remove"),
    DictWord("espremer", "to squeeze"),
    DictWord("deixar repousar", "let it rest"),
    DictWord("a massa cresce / levedar", "the dough rises")
)

private val kitchenEquipment = listOf(
    DictWord("frigideira", "pan"),
    DictWord("frigideira de ferro", "skillet"),
    DictWord("panela", "pot"),
    DictWord("fogão", "stove"),
    DictWord("congelador", "freezer"),
    DictWord("forno", "oven"),
    DictWord("microondas", "microwave"),
    DictWord("liquidificador / varinha mágica", "blender", "varinha mágica = EP immersion blender"),
    DictWord("batedeira", "mixer"),
    DictWord("faca", "knife"),
    DictWord("garfo", "fork"),
    DictWord("colher", "spoon"),
    DictWord("colher de sopa", "table spoon"),
    DictWord("colher de chá", "tea spoon"),
    DictWord("copo medidor", "measurement cup")
)

private val pantryAndSpices = listOf(
    DictWord("óleo", "oil"),
    DictWord("azeite", "olive oil"),
    DictWord("óleo de colza", "canola oil"),
    DictWord("vinagre", "vinegar"),
    DictWord("manteiga", "butter"),
    DictWord("sal", "salt"),
    DictWord("pimenta preta", "black pepper"),
    DictWord("pimenta branca", "white pepper"),
    DictWord("colorau / páprica", "paprika"),
    DictWord("canela", "cinnamon"),
    DictWord("salsa", "parsley"),
    DictWord("alho", "garlic"),
    DictWord("manjericão", "basil"),
    DictWord("pó", "powder"),
    DictWord("farinha de milho / amido de milho", "cornflour")
)

private val baking = listOf(
    DictWord("massa", "dough"),
    DictWord("fermentação", "fermentation"),
    DictWord("levedura / fermento", "yeast"),
    DictWord("fermento em pó", "baking powder"),
    DictWord("farinha", "flour"),
    DictWord("trigo", "wheat"),
    DictWord("milho", "corn"),
    DictWord("sem glúten", "without gluten"),
    DictWord("alergénio", "allergen"),
    DictWord("forno pré-aquecido", "pre-heated oven")
)

private val waterAndTemperature = listOf(
    DictWord("água a ferver", "boiling water"),
    DictWord("água fria", "cold water"),
    DictWord("gelado/a", "ice cold")
)

private val produceAndFruits = listOf(
    DictWord("cebola", "onion"),
    DictWord("cogumelos", "mushrooms"),
    DictWord("batata-doce", "sweet potato"),
    DictWord("batata", "potato"),
    DictWord("tomate", "tomato"),
    DictWord("alface", "lettuce"),
    DictWord("limão", "lemon"),
    DictWord("laranja", "orange"),
    DictWord("maçã", "apple"),
    DictWord("pêra", "pear"),
    DictWord("pêssego", "peach"),
    DictWord("ovos", "eggs"),
    DictWord("açúcar", "sugar")
)

private val dairyAndFats = listOf(
    DictWord("natas", "cream"),
    DictWord("queijo", "cheese"),
    DictWord("queijo cheddar", "cheddar"),
    DictWord("mozzarella", "mozzarella"),
    DictWord("chocolate", "chocolate"),
    DictWord("baunilha", "vanilla")
)

private val meatAndProteins = listOf(
    DictWord("carne", "meat"),
    DictWord("bife", "steak"),
    DictWord("carne picada", "ground beef"),
    DictWord("peru", "turkey"),
    DictWord("frango", "chicken"),
    DictWord("salsicha", "sausage"),
    DictWord("cachorros quentes", "hot dogs")
)

private val breadAndPastry = listOf(
    DictWord("pão", "bread"),
    DictWord("pastelaria / massa", "pastry"),
    DictWord("massa folhada", "puff pastry"),
    DictWord("sobremesa", "dessert")
)

private val stateAdjectives = listOf(
    DictWord("frito/a", "fried"),
    DictWord("cozido/a", "boiled"),
    DictWord("em puré", "mashed"),
    DictWord("cozinhado/a", "cooked"),
    DictWord("cru/crua", "raw")
)

private val tasteAndTexture = listOf(
    DictWord("doce", "sweet"),
    DictWord("azedo/a", "sour"),
    DictWord("picante", "spicy"),
    DictWord("salgado/a", "savory"),
    DictWord("salgado/a", "salty")
)

private val general = listOf(
    DictWord("guisado", "stew"),
    DictWord("ingredientes", "ingredients")
)

@Composable
fun CookingScreen(onBack: () -> Unit) {
    DictScreenScaffold(title = "Cooking", onBack = onBack) {
        item { DictCategoryCard("Coffee & Hot Drinks", coffeeAndDrinks) }
        item { DictCategoryCard("Cooking Verbs", cookingVerbs) }
        item { DictCategoryCard("Kitchen Equipment", kitchenEquipment) }
        item { DictCategoryCard("Pantry & Spices", pantryAndSpices) }
        item { DictCategoryCard("Baking", baking) }
        item { DictCategoryCard("Water & Temperature", waterAndTemperature) }
        item { DictCategoryCard("Produce & Fruits", produceAndFruits) }
        item { DictCategoryCard("Dairy & Sweets", dairyAndFats) }
        item { DictCategoryCard("Meat & Proteins", meatAndProteins) }
        item { DictCategoryCard("Bread & Pastry", breadAndPastry) }
        item { DictCategoryCard("State (Adjectives)", stateAdjectives) }
        item { DictCategoryCard("Taste & Texture", tasteAndTexture) }
        item { DictCategoryCard("General", general) }
    }
}
