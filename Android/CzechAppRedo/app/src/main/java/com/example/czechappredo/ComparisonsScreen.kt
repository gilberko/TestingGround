package com.example.czechappredo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComparisonsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comparisons", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {

            // ── Comparative ───────────────────────────────────────────────
            COMSection("Comparative — More / Less")
            COMNote("To say something is 'more X', Czech uses a comparative form of the adjective. There is no separate word for 'more' — the comparative ending does all the work.")
            COMNote("Pattern 1 — add -ější or -ejší: drop the -ý/-á/-é ending and add -ější. Used by most multi-syllable adjectives. Example: nový → novější (newer).")
            COMNote("Pattern 2 — add -ší with a possible consonant change: mainly shorter adjectives. Key shifts: sk→š (vysoký→vyšší), zk→ž (nízký→nižší), hk→č (lehký→lehčí, hezký→hezčí), gh→ž (dlouhý→delší, also shortens), h→ž (drahý→dražší), ch→š (tichý→tišší).")
            COMNote("Gender: the comparative form is the SAME for all genders — no -ý/-á/-é distinction. starší muž / starší žena / starší dítě — all use starší.")
            COMTable(
                headers = listOf("Positive", "Comparative", "English"),
                rows = listOf(
                    listOf("nový", "novější", "newer"),
                    listOf("rychlý", "rychlejší", "faster"),
                    listOf("pomalý", "pomalejší", "slower"),
                    listOf("starý", "starší", "older"),
                    listOf("mladý", "mladší", "younger"),
                    listOf("vysoký", "vyšší", "higher / taller"),
                    listOf("nízký", "nižší", "lower / shorter"),
                    listOf("dlouhý", "delší", "longer"),
                    listOf("krátký", "kratší", "shorter"),
                    listOf("těžký", "těžší", "heavier / harder"),
                    listOf("lehký", "lehčí", "lighter / easier"),
                    listOf("hezký", "hezčí", "more beautiful / nicer"),
                    listOf("drahý", "dražší", "more expensive"),
                    listOf("levný", "levnější", "cheaper"),
                    listOf("tichý", "tišší", "quieter"),
                    listOf("hlasitý", "hlasitější", "louder"),
                    listOf("chytrý", "chytřejší", "smarter"),
                    listOf("silný", "silnější", "stronger"),
                    listOf("slabý", "slabší", "weaker"),
                    listOf("tvrdý", "tvrdší", "harder / stiffer")
                ),
                weights = listOf(1.0f, 1.0f, 1.0f)
            )
            COMNote("Irregular comparatives — must be memorised:")
            COMTable(
                headers = listOf("Positive", "Comparative", "English"),
                rows = listOf(
                    listOf("dobrý (good)", "lepší", "better"),
                    listOf("špatný / zlý (bad)", "horší", "worse"),
                    listOf("velký (big)", "větší", "bigger"),
                    listOf("malý (small)", "menší", "smaller")
                ),
                weights = listOf(1.1f, 0.9f, 0.9f)
            )

            // ── Than ──────────────────────────────────────────────────────
            COMSection("Than — než")
            COMNote("The word for 'than' in comparisons is než. After než, the noun stays in the nominative case for subject comparisons.")
            COMRow("Je vyšší než ona.", "He is taller than she.")
            COMRow("Petr je starší než Pavel.", "Petr is older than Pavel.")
            COMRow("Tato kniha je zajímavější než ta druhá.", "This book is more interesting than that other one.")
            COMRow("Mluvím česky lépe než anglicky.", "I speak Czech better than English.")
            COMRow("Mám méně peněz než ty.", "I have less money than you.")
            COMNote("To express a difference by an amount, use o + accusative: o pět let starší = five years older (lit. 'by five years older').")
            COMRow("Je o deset centimetrů vyšší než já.", "He is ten centimetres taller than I am.")
            COMRow("Tenhle film je o hodně lepší.", "This film is much better. (o hodně = by a lot)")

            // ── Superlative ───────────────────────────────────────────────
            COMSection("Superlative — The Most")
            COMNote("The superlative is formed by adding nej- before the comparative form. Always. No exceptions. The same gender rule applies — one form for all genders.")
            COMTable(
                headers = listOf("Positive", "Comparative", "Superlative"),
                rows = listOf(
                    listOf("nový", "novější", "nejnovější"),
                    listOf("rychlý", "rychlejší", "nejrychlejší"),
                    listOf("starý", "starší", "nejstarší"),
                    listOf("mladý", "mladší", "nejmladší"),
                    listOf("vysoký", "vyšší", "nejvyšší"),
                    listOf("nízký", "nižší", "nejnižší"),
                    listOf("dlouhý", "delší", "nejdelší"),
                    listOf("krátký", "kratší", "nejkratší"),
                    listOf("těžký", "těžší", "nejtěžší"),
                    listOf("lehký", "lehčí", "nejlehčí"),
                    listOf("hezký", "hezčí", "nejhezčí"),
                    listOf("drahý", "dražší", "nejdražší"),
                    listOf("dobrý", "lepší", "nejlepší"),
                    listOf("špatný / zlý", "horší", "nejhorší"),
                    listOf("velký", "větší", "největší"),
                    listOf("malý", "menší", "nejmenší")
                ),
                weights = listOf(1.0f, 1.0f, 1.0f)
            )
            COMRow("Je nejrychlejší ze všech.", "He is the fastest of all.")
            COMRow("To je nejlepší restaurace ve městě.", "That is the best restaurant in the city.")
            COMRow("Praha je největší město v Česku.", "Prague is the biggest city in the Czech Republic.")
            COMRow("Je to nejhorší film, jaký jsem viděl/a.", "It's the worst film I've seen.")

            // ── Less / The Least ──────────────────────────────────────────
            COMSection("Less…Than / The Least")
            COMNote("'Less X than' = méně / míň + adjective + než. Both méně (formal) and míň (colloquial) are correct. The adjective stays in its positive form.")
            COMRow("Je méně zkušený než jeho bratr.", "He is less experienced than his brother.")
            COMRow("Tato cesta je méně bezpečná než ta druhá.", "This route is less safe than the other one.")
            COMNote("'The least X' = nejméně + adjective (positive form). In practice, Czechs often prefer the superlative of the opposite adjective — it sounds more natural.")
            COMRow("Je nejméně zkušený v týmu.", "He is the least experienced on the team.")
            COMRow("Tato cesta je nejméně bezpečná.", "This route is the least safe.")
            COMNote("More natural alternative — use the superlative of the antonym:")
            COMRow("Je nejnižší z celé třídy.", "He is the least tall in the class. (lit. the shortest)")
            COMRow("Je nejlevnější ze všech možností.", "It is the least expensive of all options. (lit. the cheapest)")
            COMNote("Standalone words for quantity comparisons: více = more, méně / míň = less / fewer. Mám více peněz. = I have more money. Mám méně peněz. = I have less money.")

            // ── As…As ─────────────────────────────────────────────────────
            COMSection("As…As — stejně…jako / tak…jako")
            COMNote("For equality comparisons use stejně + adjective + jako (as...as). In negative sentences tak + adjective + jako is also very common.")
            COMRow("Jsem stejně vysoký/á jako ty.", "I'm as tall as you.")
            COMRow("Je stejně chytrá jako její sestra.", "She is as smart as her sister.")
            COMRow("Mluví stejně rychle jako rodilý mluvčí.", "He speaks as fast as a native speaker.")
            COMRow("Není tak dobrý jako ona.", "He's not as good as she is.")
            COMRow("To není tak těžké, jak si myslíš.", "It's not as hard as you think.")
            COMNote("stejně…jako = as...as (neutral, works in both positive and negative sentences). tak…jako = as...as (slightly more common in negatives and informal speech). Both are fully interchangeable.")

            // ── Example Sentences ─────────────────────────────────────────
            COMSection("Example Sentences")
            COMRow("On je vyšší než ona.", "He is taller than she.")
            COMRow("Ona je nižší než on.", "She is shorter than him.")
            COMRow("On je nejvyšší z celé třídy.", "He is the tallest in the class.")
            COMRow("Ona je nejnižší.", "She is the shortest.")
            COMRow("Je nejméně vysoký v týmu.", "He is the least tall on the team.")
            COMRow("Tenhle film je lepší než ten předchozí.", "This film is better than the previous one.")
            COMRow("Je to nejlepší film, jaký jsem viděl/a.", "It's the best film I have seen.")
            COMRow("Tento test byl těžší než minulý.", "This test was harder than the last one.")
            COMRow("Byl to nejtěžší test v roce.", "It was the hardest test of the year.")
            COMRow("Tvůj batoh je těžší než můj.", "Your backpack is heavier than mine.")
            COMRow("Můj batoh je nejlehčí ze všech.", "My backpack is the lightest of all.")
            COMRow("Praha je větší než Brno.", "Prague is bigger than Brno.")
            COMRow("Ale Moskva je největší.", "But Moscow is the biggest.")
            COMRow("Je chytřejší, než vypadá.", "He is smarter than he looks.")
            COMRow("Je nejchytřejší člověk, kterého znám.", "She is the smartest person I know.")
            COMRow("Tato kavárna je levnější než ta druhá.", "This café is cheaper than that one.")
            COMRow("Hledám nejlevnější letenku.", "I'm looking for the cheapest flight ticket.")
            COMRow("Je o rok mladší než já.", "She is a year younger than me.")
            COMRow("Kdo je nejstarší v rodině?", "Who is the oldest in the family?")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun COMSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun COMNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

@Composable
private fun COMRow(czech: String, english: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = czech, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Text(text = english, fontSize = 15.sp, color = Color.DarkGray, modifier = Modifier.padding(start = 6.dp))
    }
}

@Composable
private fun COMTable(headers: List<String>, rows: List<List<String>>, weights: List<Float>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            headers.forEachIndexed { i, h ->
                Text(
                    text = h,
                    modifier = Modifier.weight(weights[i]).padding(horizontal = 4.dp, vertical = 4.dp),
                    fontWeight = FontWeight.Bold, fontSize = 13.sp, color = ButtonBlue
                )
            }
        }
        HorizontalDivider(color = ButtonBlue, thickness = 1.dp)
        rows.forEach { row ->
            Row(modifier = Modifier.fillMaxWidth()) {
                row.forEachIndexed { i, cell ->
                    Text(
                        text = cell,
                        modifier = Modifier.weight(weights[i]).padding(horizontal = 4.dp, vertical = 5.dp),
                        fontSize = 13.sp, color = Color.Black
                    )
                }
            }
            HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp)
        }
    }
}
