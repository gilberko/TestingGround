package com.example.czechapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

private val people = listOf(
    "man" to "muž", "woman" to "žena", "boy" to "chlapec / kluk",
    "girl" to "dívka / holka", "child" to "dítě", "baby" to "miminko",
    "person / people" to "člověk / lidé", "family" to "rodina",
    "mother" to "matka / máma", "father" to "otec / táta",
    "parents" to "rodiče", "son" to "syn", "daughter" to "dcera",
    "brother" to "bratr", "sister" to "sestra", "siblings" to "sourozenci",
    "husband" to "manžel", "wife" to "manželka", "couple" to "pár",
    "grandparents" to "prarodiče", "grandfather" to "dědeček",
    "grandmother" to "babička", "uncle" to "strýc", "aunt" to "teta",
    "cousin" to "bratranec (m) / sestřenice (f)", "nephew" to "synovec",
    "niece" to "neteř", "friend" to "přítel / přítěl / kamarád",
    "colleague" to "kolega", "neighbour" to "soused",
    "stranger" to "cizinec / neznámý"
)

private val animals = listOf(
    "dog" to "pes", "cat" to "kočka", "bird" to "pták",
    "fish" to "ryba", "horse" to "kůň", "cow" to "kráva",
    "pig" to "prase", "sheep" to "ovce", "goat" to "koza",
    "chicken / hen" to "kuře / slepice", "rabbit" to "králík",
    "bear" to "medvěd", "wolf" to "vlk", "fox" to "liška",
    "deer" to "jelen", "wild boar" to "divočák / kanec",
    "lion" to "lev", "tiger" to "tygr", "elephant" to "slon",
    "monkey" to "opice", "snake" to "had", "frog" to "žába",
    "bee" to "včela", "butterfly" to "motýl", "mouse" to "myš",
    "rat" to "krysa", "spider" to "pavouk"
)

@Composable
private fun SectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 12.dp, bottom = 4.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun WordTable(items: List<Pair<String, String>>) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("English", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                Text("Czech", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
            items.forEachIndexed { i, (en, cz) ->
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleAnimalsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("People & Animals") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { SectionHeader("People & Family") }
            item { WordTable(people) }
            item { SectionHeader("Animals") }
            item { WordTable(animals) }
        }
    }
}
