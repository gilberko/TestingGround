package com.example.frenchproject.screens.dictionary

import androidx.compose.foundation.layout.Column
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
import com.example.frenchproject.ui.theme.FrenchNavy

private data class Animal(val french: String, val english: String)

private data class AnimalSection(val title: String, val animals: List<Animal>)

private val animalSections = listOf(
    AnimalSection("Pets — Animaux domestiques", listOf(
        Animal("le chien", "dog"),
        Animal("la chienne", "female dog / bitch"),
        Animal("le chat", "cat (male)"),
        Animal("la chatte", "cat (female)"),
        Animal("le lapin", "rabbit"),
        Animal("le hamster", "hamster"),
        Animal("le perroquet", "parrot"),
        Animal("le poisson rouge", "goldfish"),
        Animal("la tortue", "turtle / tortoise"),
        Animal("le cochon d'Inde", "guinea pig")
    )),
    AnimalSection("Farm Animals — Animaux de la ferme", listOf(
        Animal("la vache", "cow"),
        Animal("le taureau", "bull"),
        Animal("le cheval", "horse"),
        Animal("la jument", "mare"),
        Animal("le mouton", "sheep"),
        Animal("la brebis", "ewe (female sheep)"),
        Animal("le cochon / le porc", "pig"),
        Animal("la poule", "hen"),
        Animal("le coq", "rooster"),
        Animal("le poulet", "chicken (meat)"),
        Animal("le canard", "duck"),
        Animal("l'oie (f)", "goose"),
        Animal("la dinde", "turkey (female)"),
        Animal("la chèvre", "goat"),
        Animal("l'âne (m)", "donkey"),
        Animal("le bœuf", "ox / beef")
    )),
    AnimalSection("Wild Animals — Animaux sauvages", listOf(
        Animal("le lion", "lion"),
        Animal("la lionne", "lioness"),
        Animal("le tigre", "tiger"),
        Animal("l'éléphant (m)", "elephant"),
        Animal("la girafe", "giraffe"),
        Animal("le zèbre", "zebra"),
        Animal("le singe", "monkey / ape"),
        Animal("l'ours (m)", "bear"),
        Animal("le loup", "wolf"),
        Animal("le renard", "fox"),
        Animal("le cerf", "deer / stag"),
        Animal("l'écureuil (m)", "squirrel"),
        Animal("le sanglier", "wild boar"),
        Animal("le lapin sauvage", "wild rabbit"),
        Animal("le rhinocéros", "rhinoceros"),
        Animal("l'hippopotame (m)", "hippopotamus"),
        Animal("le léopard", "leopard"),
        Animal("la hyène", "hyena")
    )),
    AnimalSection("Sea Life — Vie marine", listOf(
        Animal("le poisson", "fish"),
        Animal("le requin", "shark"),
        Animal("le dauphin", "dolphin"),
        Animal("la baleine", "whale"),
        Animal("le crabe", "crab"),
        Animal("le homard", "lobster"),
        Animal("la pieuvre", "octopus"),
        Animal("la méduse", "jellyfish"),
        Animal("le phoque", "seal"),
        Animal("la crevette", "shrimp / prawn"),
        Animal("la raie", "ray (fish)")
    )),
    AnimalSection("Birds — Oiseaux", listOf(
        Animal("l'oiseau (m)", "bird"),
        Animal("l'aigle (m)", "eagle"),
        Animal("le hibou", "owl"),
        Animal("le perroquet", "parrot"),
        Animal("le pingouin", "penguin"),
        Animal("le flamant rose", "flamingo"),
        Animal("le moineau", "sparrow"),
        Animal("le corbeau", "crow / raven"),
        Animal("la colombe", "dove"),
        Animal("le pigeon", "pigeon"),
        Animal("le canari", "canary"),
        Animal("la cigogne", "stork")
    )),
    AnimalSection("Reptiles & Amphibians", listOf(
        Animal("le serpent", "snake"),
        Animal("la grenouille", "frog"),
        Animal("le lézard", "lizard"),
        Animal("le crocodile", "crocodile"),
        Animal("la tortue", "turtle / tortoise"),
        Animal("le caméléon", "chameleon"),
        Animal("le crapaud", "toad")
    ))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Animals", color = Color.White) },
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
                    text = "French animal nouns have grammatical gender. Each entry shows the article (le / la / l') which indicates the gender. Many animals have distinct masculine and feminine forms.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            animalSections.forEach { section ->
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = section.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = FrenchBlue,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.35f))
                    Spacer(modifier = Modifier.height(4.dp))
                }
                items(section.animals.size) { i -> AnimalCard(section.animals[i]) }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun AnimalCard(animal: Animal) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 9.dp)
        ) {
            Text(
                text = animal.french,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 14.sp,
                modifier = Modifier.width(180.dp)
            )
            Text(
                text = animal.english,
                style = MaterialTheme.typography.bodyMedium,
                color = FrenchNavy
            )
        }
    }
}
