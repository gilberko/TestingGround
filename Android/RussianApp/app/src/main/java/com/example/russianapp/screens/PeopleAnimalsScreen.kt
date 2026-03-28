package com.example.russianapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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

private data class PaEntry(
    val english: String,
    val russian: String,
    val gender: String,
    val note: String = ""
)

private val peopleWords = listOf(
    // Core
    PaEntry("person / human",    "человек",       "m"),
    PaEntry("man",               "мужчина",       "m"),
    PaEntry("woman",             "женщина",       "f"),
    PaEntry("boy",               "мальчик",       "m"),
    PaEntry("girl",              "девочка",       "f"),
    PaEntry("child",             "ребёнок",       "m"),
    PaEntry("children",          "дети",          "pl"),
    PaEntry("baby / infant",     "младенец",      "m"),
    PaEntry("adult",             "взрослый",      "m",  "adj. used as noun"),
    PaEntry("friend (male)",     "друг",          "m"),
    PaEntry("friend (female)",   "подруга",       "f"),
    PaEntry("neighbour (m)",     "сосед",         "m"),
    PaEntry("neighbour (f)",     "соседка",       "f"),
    // Parents & grandparents
    PaEntry("father / dad",      "отец / папа",   "m"),
    PaEntry("mother / mum",      "мать / мама",   "f"),
    PaEntry("grandfather",       "дедушка",       "m"),
    PaEntry("grandmother",       "бабушка",       "f"),
    // Spouse & in-laws
    PaEntry("husband",           "муж",           "m"),
    PaEntry("wife",              "жена",          "f"),
    PaEntry("son-in-law",        "зять",          "m"),
    PaEntry("daughter-in-law",   "невестка",      "f"),
    PaEntry("father-in-law",     "тесть",         "m",  "wife's father"),
    PaEntry("mother-in-law",     "тёща",          "f",  "wife's mother"),
    PaEntry("father-in-law",     "свёкор",        "m",  "husband's father"),
    PaEntry("mother-in-law",     "свекровь",      "f",  "husband's mother"),
    // Siblings & their spouses
    PaEntry("brother",           "брат",          "m"),
    PaEntry("sister",            "сестра",        "f"),
    PaEntry("brother-in-law",    "деверь",        "m",  "husband's brother"),
    PaEntry("brother-in-law",    "зять",          "m",  "sister's husband"),
    PaEntry("sister-in-law",     "невестка",      "f",  "brother's wife"),
    PaEntry("sister-in-law",     "золовка",       "f",  "husband's sister"),
    // Children & their spouses
    PaEntry("son",               "сын",           "m"),
    PaEntry("daughter",          "дочь",          "f"),
    PaEntry("grandson",          "внук",          "m"),
    PaEntry("granddaughter",     "внучка",        "f"),
    // Aunts & uncles
    PaEntry("uncle",             "дядя",          "m"),
    PaEntry("aunt",              "тётя",          "f"),
    PaEntry("nephew",            "племянник",     "m"),
    PaEntry("niece",             "племянница",    "f"),
    // Others
    PaEntry("twin (m)",          "близнец",       "m"),
    PaEntry("orphan",            "сирота",        "m/f"),
    PaEntry("parent",            "родитель",      "m"),
    PaEntry("parents",           "родители",      "pl"),
    PaEntry("relatives",         "родственники",  "pl"),
)

private val animalWords = listOf(
    // Pets
    PaEntry("dog",               "собака",        "f"),
    PaEntry("cat",               "кошка",         "f"),
    PaEntry("parrot",            "попугай",       "m"),
    PaEntry("hamster",           "хомяк",         "m"),
    PaEntry("rabbit",            "кролик",        "m"),
    PaEntry("fish",              "рыба",          "f",  "generic"),
    PaEntry("turtle",            "черепаха",      "f"),
    // Farm animals
    PaEntry("horse",             "лошадь",        "f"),
    PaEntry("cow",               "корова",        "f"),
    PaEntry("pig",               "свинья",        "f"),
    PaEntry("chicken",           "курица",        "f"),
    PaEntry("rooster",           "петух",         "m"),
    PaEntry("sheep",             "овца",          "f"),
    PaEntry("goat",              "коза",          "f"),
    PaEntry("donkey",            "осёл",          "m"),
    // Wild / forest
    PaEntry("bear",              "медведь",       "m"),
    PaEntry("wolf",              "волк",          "m"),
    PaEntry("fox",               "лиса",          "f"),
    PaEntry("squirrel",          "белка",         "f"),
    PaEntry("hare / rabbit",     "заяц",          "m"),
    PaEntry("deer",              "олень",         "m"),
    PaEntry("moose / elk",       "лось",          "m"),
    PaEntry("boar",              "кабан",         "m"),
    PaEntry("hedgehog",          "ёж",            "m"),
    PaEntry("mouse",             "мышь",          "f"),
    PaEntry("rat",               "крыса",         "f"),
    // Big cats & savanna
    PaEntry("lion",              "лев",           "m"),
    PaEntry("tiger",             "тигр",          "m"),
    PaEntry("leopard",           "леопард",       "m"),
    PaEntry("cheetah",           "гепард",        "m"),
    PaEntry("elephant",          "слон",          "m"),
    PaEntry("rhinoceros",        "носорог",       "m"),
    PaEntry("hippopotamus",      "бегемот",       "m"),
    PaEntry("giraffe",           "жираф",         "m"),
    PaEntry("zebra",             "зебра",         "f"),
    PaEntry("monkey",            "обезьяна",      "f"),
    PaEntry("gorilla",           "горилла",       "f"),
    // Reptiles & insects
    PaEntry("crocodile",         "крокодил",      "m"),
    PaEntry("snake",             "змея",          "f"),
    PaEntry("lizard",            "ящерица",       "f"),
    PaEntry("scorpion",          "скорпион",      "m"),
    PaEntry("spider",            "паук",          "m"),
    PaEntry("bee",               "пчела",         "f"),
    PaEntry("butterfly",         "бабочка",       "f"),
    PaEntry("ant",               "муравей",       "m"),
    PaEntry("fly",               "муха",          "f"),
    PaEntry("mosquito",          "комар",         "m"),
    // Birds
    PaEntry("bird",              "птица",         "f"),
    PaEntry("eagle",             "орёл",          "m"),
    PaEntry("crow",              "ворона",        "f"),
    PaEntry("sparrow",           "воробей",       "m"),
    PaEntry("pigeon / dove",     "голубь",        "m"),
    PaEntry("owl",               "сова",          "f"),
    PaEntry("duck",              "утка",          "f"),
    PaEntry("goose",             "гусь",          "m"),
    PaEntry("swan",              "лебедь",        "m"),
    PaEntry("penguin",           "пингвин",       "m"),
    // Sea creatures
    PaEntry("dolphin",           "дельфин",       "m"),
    PaEntry("whale",             "кит",           "m"),
    PaEntry("shark",             "акула",         "f"),
    PaEntry("octopus",           "осьминог",      "m"),
    PaEntry("jellyfish",         "медуза",        "f"),
    PaEntry("crab",              "краб",          "m"),
    PaEntry("lobster",           "омар",          "m"),
)

// ── UI helpers ────────────────────────────────────────────────────────────────

@Composable
private fun PaSectionHeader(title: String) {
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

@Composable
private fun PaGenderLegend() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
            Text("m = masculine  ", style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("f = feminine  ", style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold)
            Text("pl = plural only", style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun PaTableHeader() {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text("English",  style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.8f))
        Text("Russian",  style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.8f))
        Text("Gender",   style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.7f))
    }
    HorizontalDivider()
}

@Composable
private fun PaRow(entry: PaEntry, index: Int) {
    val bg = if (index % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
             else MaterialTheme.colorScheme.surface
    val genderColor = when (entry.gender) {
        "f"   -> MaterialTheme.colorScheme.secondary
        "n"   -> MaterialTheme.colorScheme.tertiary
        "pl"  -> MaterialTheme.colorScheme.outline
        "m/f" -> MaterialTheme.colorScheme.outline
        else  -> MaterialTheme.colorScheme.primary
    }
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(entry.english, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.8f))
                Text(entry.russian, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1.8f))
                Text(entry.gender,  style = MaterialTheme.typography.bodySmall,
                    color = genderColor, fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(0.7f))
            }
            if (entry.note.isNotEmpty()) {
                Text(entry.note, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(start = 2.dp, bottom = 2.dp))
            }
        }
    }
}

// ── Screen ────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleAnimalsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("People & Animals") },
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
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { PaGenderLegend() }

            item { PaSectionHeader("People  (Люди)") }
            item { PaTableHeader() }
            itemsIndexed(peopleWords) { i, entry -> PaRow(entry, i) }

            item { Spacer(modifier = Modifier.height(8.dp)) }
            item { PaSectionHeader("Animals  (Животные)") }
            item { PaTableHeader() }
            itemsIndexed(animalWords) { i, entry -> PaRow(entry, i) }
        }
    }
}
