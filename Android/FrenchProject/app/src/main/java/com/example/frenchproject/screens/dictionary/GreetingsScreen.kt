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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

private data class Greeting(val french: String, val english: String, val note: String? = null)
private data class GreetingSection(val title: String, val entries: List<Greeting>)

private val greetingSections = listOf(
    GreetingSection("Greetings — When You Arrive", listOf(
        Greeting("Bonjour", "Hello / Good day", "Universal — works morning, afternoon, with anyone"),
        Greeting("Bonsoir", "Good evening", "Use from around 6 pm onwards"),
        Greeting("Salut", "Hi", "Informal — with friends and people you know well"),
        Greeting("Coucou", "Hey / Hi", "Very informal and friendly; common among close friends"),
        Greeting("Allô", "Hello", "Used when answering the phone"),
        Greeting("Bienvenue", "Welcome", "Said to a guest or someone arriving somewhere new")
    )),
    GreetingSection("Farewells — When You Leave", listOf(
        Greeting("Au revoir", "Goodbye", "Standard farewell, suitable for all situations"),
        Greeting("Bonne journée", "Have a good day", "Said when parting during the daytime"),
        Greeting("Bonne soirée", "Have a good evening", "Said when parting in the evening"),
        Greeting("Bonne nuit", "Good night", "Said at bedtime or when parting very late"),
        Greeting("Salut", "Bye", "Same word as Hi — informal farewell too"),
        Greeting("À bientôt", "See you soon"),
        Greeting("À tout à l'heure", "See you later", "Specifically later the same day"),
        Greeting("À demain", "See you tomorrow"),
        Greeting("À la prochaine", "Until next time")
    )),
    GreetingSection("Special Occasions", listOf(
        Greeting("Bonne année", "Happy New Year"),
        Greeting("Joyeux anniversaire", "Happy Birthday"),
        Greeting("Félicitations", "Congratulations"),
        Greeting("Bonne chance", "Good luck"),
        Greeting("Bon courage", "Stay strong / Hang in there", "Encouragement when someone faces a challenge"),
        Greeting("Joyeux Noël", "Merry Christmas"),
        Greeting("Joyeuses Pâques", "Happy Easter")
    )),
    GreetingSection("How Are You?", listOf(
        Greeting("Comment allez-vous ?", "How are you?", "Formal — use with strangers, elders, or professionals"),
        Greeting("Comment vas-tu ?", "How are you?", "Informal — use with friends and family"),
        Greeting("Ça va ?", "How's it going?", "Very casual; the most common everyday version"),
        Greeting("Ça va bien, merci.", "I'm doing well, thank you.", "Standard positive reply"),
        Greeting("Pas mal.", "Not bad.", "Common relaxed reply"),
        Greeting("Enchanté(e)", "Nice to meet you", "Add -e at the end if you are female")
    ))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Greetings", color = Color.White) },
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
                    text = "French greetings change depending on the time of day, whether you are arriving or leaving, and how formal the situation is. Note that Bonjour does it all — it covers both \"hello\" and \"good morning/afternoon\".",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            greetingSections.forEach { section ->
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
                items(section.entries.size) { i -> GreetingCard(section.entries[i]) }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun GreetingCard(greeting: Greeting) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 9.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = greeting.french,
                    fontWeight = FontWeight.Bold,
                    color = FrenchBlue,
                    fontSize = 14.sp,
                    modifier = Modifier.width(200.dp)
                )
                Text(
                    text = greeting.english,
                    style = MaterialTheme.typography.bodyMedium,
                    color = FrenchNavy
                )
            }
            if (greeting.note != null) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = greeting.note,
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
