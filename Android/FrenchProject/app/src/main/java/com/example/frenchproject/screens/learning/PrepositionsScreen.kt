package com.example.frenchproject.screens.learning

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

private data class PrepEntry(
    val french: String,
    val english: String,
    val examples: List<Pair<String, String>>
)
private data class PrepSection(val title: String, val entries: List<PrepEntry>)

private val prepSections = listOf(
    PrepSection(
        "Location / Place",
        listOf(
            PrepEntry("dans", "in / inside",
                listOf("Le chat est dans la boîte." to "The cat is in the box.",
                    "Elle est dans sa chambre." to "She is in her room.")),
            PrepEntry("sur", "on / on top of",
                listOf("Le livre est sur la table." to "The book is on the table.",
                    "Il y a une tasse sur le bureau." to "There is a cup on the desk.")),
            PrepEntry("sous", "under / beneath",
                listOf("Le chat est sous la chaise." to "The cat is under the chair.",
                    "Les clés sont sous le manteau." to "The keys are under the coat.")),
            PrepEntry("entre", "between",
                listOf("La banque est entre la poste et le café." to "The bank is between the post office and the café.")),
            PrepEntry("devant", "in front of / ahead",
                listOf("Il attend devant la maison." to "He is waiting in front of the house.",
                    "La voiture est garée devant l'école." to "The car is parked in front of the school.")),
            PrepEntry("derrière", "behind",
                listOf("Le jardin est derrière la maison." to "The garden is behind the house.",
                    "Il se cache derrière l'arbre." to "He is hiding behind the tree.")),
            PrepEntry("près de", "near / close to",
                listOf("L'hôtel est près de la gare." to "The hotel is near the station.",
                    "Elle habite près de chez moi." to "She lives near my place.")),
            PrepEntry("loin de", "far from",
                listOf("La plage est loin d'ici." to "The beach is far from here.",
                    "Il travaille loin de chez lui." to "He works far from home.")),
            PrepEntry("au-dessus de", "above / over",
                listOf("L'avion vole au-dessus des nuages." to "The plane flies above the clouds.",
                    "La lampe est au-dessus de la table." to "The lamp is above the table.")),
            PrepEntry("en dessous de", "below / underneath",
                listOf("La cave est en dessous de la maison." to "The cellar is below the house.",
                    "Le thermomètre est en dessous de zéro." to "The thermometer is below zero.")),
            PrepEntry("en face de", "opposite / across from",
                listOf("La boulangerie est en face de la banque." to "The bakery is across from the bank.",
                    "Il habite en face de l'église." to "He lives opposite the church.")),
            PrepEntry("chez", "at someone's home / place",
                listOf("Je suis chez moi." to "I am at home.",
                    "On se retrouve chez Marie." to "We'll meet at Marie's place.",
                    "Elle va chez le médecin." to "She's going to the doctor's."))
        )
    ),
    PrepSection(
        "Direction / Movement",
        listOf(
            PrepEntry("à", "to / at / in",
                listOf("Je vais à Paris." to "I'm going to Paris.",
                    "Il est à la maison." to "He is at home.",
                    "Elle habite à Lyon." to "She lives in Lyon.")),
            PrepEntry("vers", "toward / around (time)",
                listOf("Elle marche vers la gare." to "She is walking toward the station.",
                    "On arrive vers dix heures." to "We're arriving around ten o'clock.")),
            PrepEntry("jusqu'à", "until / up to / as far as",
                listOf("Je travaille jusqu'à dix-huit heures." to "I work until 6 p.m.",
                    "On a marché jusqu'au village." to "We walked as far as the village.")),
            PrepEntry("à travers", "across / through",
                listOf("Il court à travers la forêt." to "He runs through the forest.",
                    "La lumière passe à travers la fenêtre." to "The light comes through the window.")),
            PrepEntry("par", "through / by / via",
                listOf("Passe par la rue principale." to "Go through the main street.",
                    "La lettre est arrivée par la poste." to "The letter arrived by post."))
        )
    ),
    PrepSection(
        "Time",
        listOf(
            PrepEntry("avant", "before",
                listOf("Je mange avant le travail." to "I eat before work.",
                    "Finis ça avant midi." to "Finish this before noon.")),
            PrepEntry("après", "after",
                listOf("On se retrouve après le dîner." to "We'll meet after dinner.",
                    "Elle part après lui." to "She's leaving after him.")),
            PrepEntry("pendant", "during / for (a duration)",
                listOf("Il dort pendant le film." to "He sleeps during the film.",
                    "J'ai étudié pendant deux heures." to "I studied for two hours.")),
            PrepEntry("depuis", "since / for (ongoing from past)",
                listOf("J'habite ici depuis cinq ans." to "I have been living here for five years.",
                    "Elle travaille depuis ce matin." to "She has been working since this morning."))
        )
    ),
    PrepSection(
        "Relationship / Other",
        listOf(
            PrepEntry("de", "of / from",
                listOf("Le livre de Marie." to "Marie's book. (lit. the book of Marie)",
                    "Je viens de Lyon." to "I come from Lyon.")),
            PrepEntry("avec", "with",
                listOf("Je viens avec mes amis." to "I'm coming with my friends.",
                    "Il boit son café avec du lait." to "He drinks his coffee with milk.")),
            PrepEntry("sans", "without",
                listOf("Un café sans sucre, s'il vous plaît." to "A coffee without sugar, please.",
                    "Elle est sortie sans manteau." to "She went out without a coat.")),
            PrepEntry("pour", "for / in order to",
                listOf("C'est un cadeau pour toi." to "It's a gift for you.",
                    "Il travaille pour vivre." to "He works in order to live.")),
            PrepEntry("en", "in / by (means of transport or material)",
                listOf("Elle est en France." to "She is in France.",
                    "On y va en train." to "We're going there by train.",
                    "Une robe en soie." to "A silk dress."))
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrepositionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Prepositions", color = Color.White) },
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
                    text = "Prepositions link nouns, pronouns, and phrases to other parts of the sentence. Several French prepositions — especially à and de — contract with the definite articles le and les: à + le = au, à + les = aux, de + le = du, de + les = des.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            prepSections.forEach { section ->
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    PrepSectionHeader(section.title)
                }
                items(section.entries.size) { i -> PrepCard(section.entries[i]) }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun PrepSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = FrenchBlue,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.4f))
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun PrepCard(entry: PrepEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            Text(
                text = entry.french,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 16.sp
            )
            Text(
                text = entry.english,
                style = MaterialTheme.typography.bodyMedium,
                color = FrenchNavy,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            entry.examples.forEach { (french, english) ->
                Column(modifier = Modifier.padding(bottom = 4.dp)) {
                    Text(
                        text = french,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = FrenchBlue,
                        lineHeight = 18.sp
                    )
                    Text(
                        text = english,
                        style = MaterialTheme.typography.bodySmall,
                        color = FrenchNavy,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}
