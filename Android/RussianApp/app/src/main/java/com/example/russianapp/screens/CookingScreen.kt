package com.example.russianapp.screens

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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// ── Data ──────────────────────────────────────────────────────────────────────

private data class CookingEntry(
    val english: String,
    val russian: String,
    val type: String,   // "m"|"f"|"n"|"pl"|"m*"|"n*"|"verb"|"adj"|"phrase"
    val note: String = ""
)

private val cookingSections: List<Pair<String, List<CookingEntry>>> = listOf(
    "Kitchen Equipment & Utensils" to listOf(
        CookingEntry("pan",                         "сковорода",                    "f"),
        CookingEntry("skillet",                     "сковорода",                    "f"),
        CookingEntry("pot",                         "кастрюля",                     "f"),
        CookingEntry("stove",                       "плита",                        "f"),
        CookingEntry("oven",                        "духовка",                      "f"),
        CookingEntry("freezer",                     "морозилка",                    "f"),
        CookingEntry("blender",                     "блендер",                      "m"),
        CookingEntry("mixer",                       "миксер",                       "m"),
        CookingEntry("microwave",                   "микроволновка",                "f"),
        CookingEntry("knife",                       "нож",                          "m"),
        CookingEntry("fork",                        "вилка",                        "f"),
        CookingEntry("spoon",                       "ложка",                        "f"),
        CookingEntry("tablespoon",                  "столовая ложка",               "f"),
        CookingEntry("teaspoon",                    "чайная ложка",                 "f"),
        CookingEntry("measuring cup",               "мерный стакан",                "m")
    ),
    "Spices & Seasonings" to listOf(
        CookingEntry("salt",                        "соль",                         "f"),
        CookingEntry("black pepper",                "чёрный перец",                 "m"),
        CookingEntry("white pepper",                "белый перец",                  "m"),
        CookingEntry("paprika",                     "паприка",                      "f"),
        CookingEntry("cinnamon",                    "корица",                       "f"),
        CookingEntry("parsley",                     "петрушка",                     "f"),
        CookingEntry("onion",                       "лук",                          "m"),
        CookingEntry("garlic",                      "чеснок",                       "m"),
        CookingEntry("basil",                       "базилик",                      "m"),
        CookingEntry("vinegar",                     "уксус",                        "m"),
        CookingEntry("powder",                      "порошок",                      "m")
    ),
    "Oils & Liquids" to listOf(
        CookingEntry("oil",                         "масло",                        "n"),
        CookingEntry("olive oil",                   "оливковое масло",              "n"),
        CookingEntry("canola oil",                  "рапсовое масло",               "n"),
        CookingEntry("butter",                      "сливочное масло",              "n"),
        CookingEntry("boiling water",               "кипяток",                      "m"),
        CookingEntry("cold water",                  "холодная вода",                "f")
    ),
    "Dry Goods & Baking" to listOf(
        CookingEntry("flour",                       "мука",                         "f"),
        CookingEntry("wheat",                       "пшеница",                      "f"),
        CookingEntry("corn",                        "кукуруза",                     "f"),
        CookingEntry("cornflour",                   "кукурузная мука",              "f"),
        CookingEntry("dough",                       "тесто",                        "n"),
        CookingEntry("yeast",                       "дрожжи",                       "pl"),
        CookingEntry("baking powder",               "разрыхлитель",                 "m"),
        CookingEntry("sugar",                       "сахар",                        "m"),
        CookingEntry("bread",                       "хлеб",                         "m"),
        CookingEntry("pastry",                      "выпечка",                      "f"),
        CookingEntry("puff pastry",                 "слоёное тесто",                "n"),
        CookingEntry("dessert",                     "десерт",                       "m"),
        CookingEntry("porridge",                    "каша",                         "f"),
        CookingEntry("buckwheat groats",            "гречка",                       "f"),
        CookingEntry("without gluten",              "без глютена",                  "phrase"),
        CookingEntry("allergen",                    "аллерген",                     "m")
    ),
    "Fruits & Vegetables" to listOf(
        CookingEntry("potato",                      "картошка",                     "f"),
        CookingEntry("sweet potato",                "батат",                        "m"),
        CookingEntry("tomato",                      "помидор",                      "m"),
        CookingEntry("lemon",                       "лимон",                        "m"),
        CookingEntry("orange",                      "апельсин",                     "m"),
        CookingEntry("apple",                       "яблоко",                       "n"),
        CookingEntry("pear",                        "груша",                        "f"),
        CookingEntry("peach",                       "персик",                       "m"),
        CookingEntry("lettuce",                     "салат",                        "m"),
        CookingEntry("mushrooms",                   "грибы",                        "pl")
    ),
    "Dairy, Meat & Proteins" to listOf(
        CookingEntry("eggs",                        "яйца",                         "pl"),
        CookingEntry("milk",                        "молоко",                       "n"),
        CookingEntry("cream",                       "сливки",                       "pl"),
        CookingEntry("cheese",                      "сыр",                          "m"),
        CookingEntry("cheddar",                     "чеддер",                       "m"),
        CookingEntry("mozzarella",                  "моцарелла",                    "f"),
        CookingEntry("soy",                         "соя",                          "f"),
        CookingEntry("meat",                        "мясо",                         "n"),
        CookingEntry("steak",                       "стейк",                        "m"),
        CookingEntry("ground beef",                 "говяжий фарш",                 "m"),
        CookingEntry("turkey",                      "индейка",                      "f"),
        CookingEntry("chicken",                     "курица",                       "f"),
        CookingEntry("sausage",                     "колбаса",                      "f"),
        CookingEntry("hot dogs",                    "сосиски",                      "pl")
    ),
    "Drinks" to listOf(
        CookingEntry("tea",                         "чай",                          "m"),
        CookingEntry("green tea",                   "зелёный чай",                  "m"),
        CookingEntry("ground coffee",               "молотый кофе",                 "m*",  "кофе is indeclinable"),
        CookingEntry("ground beans",                "молотые зёрна",                "pl"),
        CookingEntry("chocolate",                   "шоколад",                      "m"),
        CookingEntry("vanilla",                     "ваниль",                       "f")
    ),
    "Cooking Actions" to listOf(
        CookingEntry("to grind",                    "молоть",                       "verb"),
        CookingEntry("to boil (water)",             "кипятить",                     "verb"),
        CookingEntry("to boil (food)",              "варить",                       "verb"),
        CookingEntry("to fry",                      "жарить",                       "verb"),
        CookingEntry("to cook",                     "готовить",                     "verb"),
        CookingEntry("to stir",                     "помешивать",                   "verb"),
        CookingEntry("to freeze",                   "замораживать",                 "verb"),
        CookingEntry("to bake",                     "выпекать",                     "verb"),
        CookingEntry("to deep fry",                 "жарить во фритюре",            "verb"),
        CookingEntry("to mash",                     "разминать",                    "verb"),
        CookingEntry("to cut",                      "нарезать",                     "verb"),
        CookingEntry("to whisk",                    "взбивать",                     "verb"),
        CookingEntry("to roast",                    "обжаривать",                   "verb"),
        CookingEntry("to brew",                     "заваривать",                   "verb"),
        CookingEntry("to dry",                      "сушить",                       "verb"),
        CookingEntry("to extract",                  "извлекать",                    "verb"),
        CookingEntry("to pre-heat",                 "предварительно разогревать",   "verb"),
        CookingEntry("to fill",                     "наполнять",                    "verb"),
        CookingEntry("to remove",                   "убирать",                      "verb"),
        CookingEntry("to squeeze",                  "выжимать",                     "verb"),
        CookingEntry("to mix",                      "смешивать",                    "verb"),
        CookingEntry("to mix in",                   "вмешивать",                    "verb"),
        CookingEntry("to add",                      "добавлять",                    "verb"),
        CookingEntry("to stew",                     "тушить",                       "verb"),
        CookingEntry("to break",                    "разбивать",                    "verb"),
        CookingEntry("to ferment",                  "ферментировать",               "verb")
    ),
    "States & Textures" to listOf(
        CookingEntry("fried",                       "жареный",                      "adj"),
        CookingEntry("boiled",                      "варёный",                      "adj"),
        CookingEntry("mashed",                      "размятый",                     "adj"),
        CookingEntry("cooked",                      "приготовленный",               "adj"),
        CookingEntry("raw",                         "сырой",                        "adj"),
        CookingEntry("sweet",                       "сладкий",                      "adj"),
        CookingEntry("sour",                        "кислый",                       "adj"),
        CookingEntry("spicy",                       "острый",                       "adj"),
        CookingEntry("savory",                      "несладкий",                    "adj"),
        CookingEntry("salty",                       "солёный",                      "adj"),
        CookingEntry("ice cold",                    "ледяной",                      "adj")
    ),
    "Phrases & Concepts" to listOf(
        CookingEntry("fermentation",                "брожение",                     "n"),
        CookingEntry("stew",                        "рагу",                         "n*",  "indeclinable"),
        CookingEntry("ingredients",                 "ингредиенты",                  "pl"),
        CookingEntry("pre-heated oven",             "разогретая духовка",           "f"),
        CookingEntry("the dough rises",             "тесто поднимается",            "phrase"),
        CookingEntry("let it rest",                 "дать настояться",              "phrase"),
        CookingEntry("boiling",                     "кипение",                      "n")
    )
)

// ── Composables ───────────────────────────────────────────────────────────────

@Composable
private fun CookingSectionHeader(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        HorizontalDivider(
            modifier = Modifier.padding(top = 4.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

private fun cookingTypeLabel(t: String) = when (t) {
    "m"      -> "masc."
    "m*"     -> "masc.*"
    "f"      -> "fem."
    "n"      -> "neut."
    "n*"     -> "neut.*"
    "pl"     -> "pl."
    "verb"   -> "verb"
    "adj"    -> "adj."
    "phrase" -> "phrase"
    else     -> t
}

private fun cookingTypeColor(t: String, scheme: androidx.compose.material3.ColorScheme) = when (t) {
    "m", "m*" -> scheme.primary
    "f"        -> scheme.secondary
    "n", "n*"  -> scheme.tertiary
    "verb"     -> scheme.tertiary
    "adj"      -> scheme.secondary
    else       -> scheme.outline
}

@Composable
private fun CookingTableHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        Text("English",  style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.8f))
        Text("Russian",  style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.8f))
        Text("Type",     style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.8f))
    }
}

@Composable
private fun CookingRow(entry: CookingEntry, isEven: Boolean) {
    val bg = if (isEven)
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
    else
        MaterialTheme.colorScheme.surface
    val tColor = cookingTypeColor(entry.type, MaterialTheme.colorScheme)
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
        colors = CardDefaults.cardColors(containerColor = bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(entry.english, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.8f))
                Text(entry.russian, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.8f))
                Text(cookingTypeLabel(entry.type), style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold, color = tColor, modifier = Modifier.weight(0.8f))
            }
            if (entry.note.isNotEmpty()) {
                Text(
                    text = entry.note,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(top = 1.dp)
                )
            }
        }
    }
}

@Composable
private fun CookingLegendCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Type legend:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Row(modifier = Modifier.fillMaxWidth().padding(top = 6.dp)) {
                Text("masc.", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1f))
                Text("masculine noun", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(3f))
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                Text("fem.", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1f))
                Text("feminine noun", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(3f))
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                Text("neut.", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary, modifier = Modifier.weight(1f))
                Text("neuter noun", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(3f))
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                Text("pl.", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.outline, modifier = Modifier.weight(1f))
                Text("plural-only noun", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(3f))
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                Text("masc.*", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1f))
                Text("indeclinable — never changes form", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(3f))
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                Text("verb", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary, modifier = Modifier.weight(1f))
                Text("verb (imperfective aspect)", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(3f))
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                Text("adj.", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1f))
                Text("adjective (masculine short form shown)", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(3f))
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                Text("phrase", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.outline, modifier = Modifier.weight(1f))
                Text("fixed expression or phrase", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(3f))
            }
        }
    }
}

// ── Screen ────────────────────────────────────────────────────────────────────

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
                .padding(innerPadding)
                .padding(horizontal = 8.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                CookingLegendCard()
            }
            cookingSections.forEach { (sectionTitle, entries) ->
                item(key = "header_$sectionTitle") {
                    CookingSectionHeader(sectionTitle)
                    CookingTableHeader()
                }
                items(entries.size, key = { "$sectionTitle-$it" }) { index ->
                    CookingRow(entries[index], isEven = index % 2 == 0)
                }
            }
        }
    }
}
