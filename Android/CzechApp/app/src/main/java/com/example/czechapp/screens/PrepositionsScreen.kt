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

private data class PrepEntry(val prep: String, val cases: String, val meaning: String, val example: String)

private val prepositions = listOf(
    PrepEntry("v / ve", "Locative (where), Accusative (when)", "in, at, into", "Jsem v Praze. (I am in Prague.) / Přijdu ve středu. (I'll come on Wednesday.)"),
    PrepEntry("na", "Locative (where), Accusative (direction/purpose)", "on, at, to", "Jsem na stole. (I am on the table.) / Jdu na nákup. (I'm going shopping.)"),
    PrepEntry("do", "Genitive", "to, into, until", "Jdu do školy. (I'm going to school.) / Pracuji do pěti. (I work until five.)"),
    PrepEntry("z / ze", "Genitive", "from, out of", "Jsem z Prahy. (I'm from Prague.) / Vyjdi ze dveří. (Come out of the door.)"),
    PrepEntry("od", "Genitive", "from, since, by (author)", "Od pondělí. (Since Monday.) / Dopis od přítele. (A letter from a friend.)"),
    PrepEntry("k / ke", "Dative", "to, towards, for", "Jdu k lékaři. (I'm going to the doctor.) / K čemu to je? (What is it for?)"),
    PrepEntry("s / se", "Instrumental", "with, together with", "Jdu s přítelem. (I'm going with a friend.)"),
    PrepEntry("bez", "Genitive", "without", "Káva bez cukru. (Coffee without sugar.)"),
    PrepEntry("pro", "Accusative", "for (benefit), because of", "To je pro tebe. (This is for you.)"),
    PrepEntry("o", "Locative, Accusative", "about, by (amount)", "Mluvím o tom. (I'm talking about it.) / Starší o rok. (Older by a year.)"),
    PrepEntry("po", "Locative (after), Accusative (along)", "after, along, all over", "Po práci. (After work.) / Chodím po parku. (I walk around the park.)"),
    PrepEntry("při", "Locative", "during, while, at the time of", "Při jídle. (While eating.) / Při práci. (During work.)"),
    PrepEntry("za", "Instrumental (behind), Accusative (in, within time)", "behind, in, for", "Za rohem. (Around the corner.) / Za hodinu. (In an hour.)"),
    PrepEntry("před", "Instrumental (location), Accusative (movement)", "in front of, before, ago", "Stojím před domem. (I'm standing in front of the house.) / Před hodinou. (An hour ago.)"),
    PrepEntry("nad", "Instrumental (location), Accusative (direction)", "above, over", "Obraz nad stolem. (Picture above the table.)"),
    PrepEntry("pod", "Instrumental (location), Accusative (direction)", "under, below", "Kočka pod stolem. (Cat under the table.)"),
    PrepEntry("mezi", "Instrumental (location), Accusative (direction)", "between, among", "Mezi přáteli. (Among friends.)")
)

@Composable
private fun SectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrepositionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Prepositions") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                Text(
                    text = "Czech prepositions always govern a specific case (or cases). The same preposition may take different cases depending on meaning (e.g., location vs. direction).",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            item { SectionHeader("Common Prepositions") }
            prepositions.forEach { prep ->
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(prep.prep, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.7f))
                                Text(prep.cases, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.5f))
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(prep.meaning, style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(prep.example, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }
        }
    }
}
