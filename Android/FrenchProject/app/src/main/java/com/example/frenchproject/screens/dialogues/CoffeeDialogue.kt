package com.example.frenchproject.screens.dialogues

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

private data class CoffeeLine(val speaker: String, val fr: String, val en: String)

private val coffeeLines = listOf(
    CoffeeLine("Client", "Bonjour ! Je ne suis jamais venu ici avant. Vous semblez être un café spécialisé ?", "Hello! I've never been here before. You seem to be a specialty café?"),
    CoffeeLine("Barista", "Bonjour ! Oui, exactement. Nous proposons des cafés de spécialité, mais aussi des cafés plus classiques.", "Hello! Yes, exactly. We offer specialty coffees, but also more classic ones."),
    CoffeeLine("Client", "Quelle est la différence ?", "What's the difference?"),
    CoffeeLine("Barista", "Les cafés de spécialité, ce sont des grains d'origine unique — souvent avec des notes fruitées, florales ou noisette. On les prépare en extraction lente. Les cafés classiques, comme notre espresso italiano, c'est un mélange de robusta et d'arabica, torréfaction foncée — intense et fort.", "Specialty coffees use single-origin beans — often with fruity, floral, or nutty notes. We prepare them with slow extraction. Classic coffees, like our Italian espresso, are a blend of robusta and arabica, dark roast — intense and strong."),
    CoffeeLine("Client", "Très intéressant ! Et vous faites du matcha ?", "Very interesting! And do you make matcha?"),
    CoffeeLine("Barista", "Oui ! Nous utilisons du matcha cérémoniel japonais. Vous pouvez le prendre en matcha latte avec du lait de vache, d'avoine ou d'amande.", "Yes! We use Japanese ceremonial matcha. You can have it as a matcha latte with cow's milk, oat milk, or almond milk."),
    CoffeeLine("Client", "Et comme cafés au lait, vous avez quoi ?", "And what milk-based coffees do you have?"),
    CoffeeLine("Barista", "Nous avons le flat white — un double espresso avec du lait très soyeux, concentré et onctueux. Le cortado — un espresso avec la même quantité de lait chaud. Et bien sûr le cappuccino classique avec sa mousse.", "We have the flat white — a double espresso with very silky milk, concentrated and creamy. The cortado — an espresso with an equal amount of warm milk. And of course the classic cappuccino with its foam."),
    CoffeeLine("Client", "Super ! Dans ce cas, je vais prendre un matcha latte et un cappuccino.", "Great! In that case, I'll have a matcha latte and a cappuccino."),
    CoffeeLine("Barista", "Vous mangez sur place ?", "Are you eating in?"),
    CoffeeLine("Client", "Oui, on va s'installer ici. Mais pourriez-vous quand même mettre les boissons dans des gobelets à emporter ?", "Yes, we'll sit here. But could you still put the drinks in takeaway cups?"),
    CoffeeLine("Barista", "Bien sûr, pas de souci ! Ce sera prêt dans deux minutes.", "Of course, no problem! It'll be ready in two minutes.")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Coffee", color = Color.White) },
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
                .padding(horizontal = 12.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item { CoffeeSectionHeader("En français") }
            items(coffeeLines.size) { i ->
                CoffeeCard(line = coffeeLines[i], showFrench = true)
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { CoffeeSectionHeader("English Translation") }
            items(coffeeLines.size) { i ->
                CoffeeCard(line = coffeeLines[i], showFrench = false)
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun CoffeeSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = FrenchBlue,
        modifier = Modifier.padding(vertical = 8.dp)
    )
    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.4f))
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun CoffeeCard(line: CoffeeLine, showFrench: Boolean) {
    val isClient = line.speaker == "Client"
    val containerColor = if (isClient)
        MaterialTheme.colorScheme.secondaryContainer
    else
        MaterialTheme.colorScheme.tertiaryContainer
    val onContainerColor = if (isClient)
        MaterialTheme.colorScheme.onSecondaryContainer
    else
        MaterialTheme.colorScheme.onTertiaryContainer

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = line.speaker,
                fontWeight = FontWeight.Bold,
                color = onContainerColor,
                fontSize = 12.sp,
                modifier = Modifier.width(60.dp)
            )
            Text(
                text = if (showFrench) line.fr else line.en,
                style = MaterialTheme.typography.bodySmall,
                color = onContainerColor,
                lineHeight = 18.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
