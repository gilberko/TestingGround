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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicWordsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Basic Words, Expressions & Greetings", fontSize = 15.sp, fontWeight = FontWeight.Bold) },
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

            // ── Yes & No ──────────────────────────────────────────────────
            BWSection("Yes & No")
            BWRow("ano", "yes", "formal / written form")
            BWRow("jo", "yes", "informal, spoken")
            BWRow("ne", "no")

            // ── Connectors ────────────────────────────────────────────────
            BWSection("Connectors")
            BWRow("a", "and")
            BWRow("nebo", "or")
            BWRow("ale", "but")
            BWRow("tak", "so / well / then")
            BWRow("i", "and / also / even", "literary variant of 'a'; i...i = both...and; e.g. i já = me too")
            BWRow("znovu", "again", "also: zase (colloquial), zas (very short form)")
            BWRow("zase / zas", "again (colloquial)", "variant of znovu; zas is the clipped form")
            BWRow("anebo", "or (emphatic)", "stronger variant of nebo; implies 'or else'")

            // ── Greetings ─────────────────────────────────────────────────
            BWSection("Greetings")
            BWRow("Dobré ráno", "Good morning")
            BWRow("Dobrý den", "Good day / Hello", "standard greeting throughout the day")
            BWRow("Dobré odpoledne", "Good afternoon")
            BWRow("Dobrý večer", "Good evening")
            BWRow("Dobrou noc", "Good night")
            BWRow("Ahoj", "Hi / Bye", "informal — used among friends")
            BWRow("Čau", "Hi / Bye", "very informal, from Italian ciao")
            BWRow("Nashledanou", "Goodbye", "formal farewell")

            // ── Pleasantries ──────────────────────────────────────────────
            BWSection("Pleasantries")
            BWRow("Prosím", "Please / You're welcome / Here you go", "extremely versatile — context tells you which meaning")
            BWRow("Děkuji", "Thank you", "formal")
            BWRow("Díky", "Thanks", "informal")
            BWRow("Promiňte", "Excuse me / Sorry", "formal")
            BWRow("Promiň", "Excuse me / Sorry", "informal")

            // ── How Are You? ──────────────────────────────────────────────
            BWSection("How Are You?")
            BWRow("Jak se máte?", "How are you?", "formal")
            BWRow("Jak se máš?", "How are you?", "informal")
            BWRow("Mám se dobře.", "I'm fine.")
            BWRow("A vy?", "And you?", "formal")
            BWRow("A ty?", "And you?", "informal")

            // ── Nice To Meet You ──────────────────────────────────────────
            BWSection("Nice To Meet You")
            BWRow("Těší mě.", "Nice to meet you.", "lit. \"It pleases me.\"")

            // ── Where Are You From? ───────────────────────────────────────
            BWSection("Where Are You From?")
            BWRow("Odkud jste?", "Where are you from?", "formal")
            BWRow("Odkud jsi?", "Where are you from?", "informal")
            BWRow("Jsem z ...", "I'm from ...", "uses Genitive — the country name changes its ending. Example: Jsem z Anglie.  (I'm from England.)")

            // ── Do You Study / Work? ──────────────────────────────────────
            BWSection("Do You Study? Do You Work?")
            BWNote("Verbs like studovat and pracovat can form the \"I\" (já) in two ways: -uji is the formal, written standard; -uju is the colloquial, spoken alternative. You will hear both — neither is wrong, but -uju is far more common in everyday conversation.")
            BWRow("Studujete?", "Do you study?", "formal")
            BWRow("Studuješ?", "Do you study?", "informal")
            BWRow("Pracujete?", "Do you work?", "formal")
            BWRow("Pracuješ?", "Do you work?", "informal")
            BWRow("Studuji v ...", "I study in ...", "formal / written. Uses Locative — city name changes ending. Example: Studuji v Praze.  (I study in Prague.)")
            BWRow("Studuju v ...", "I study in ...", "colloquial / spoken alternative. Example: Studuju v Praze.")
            BWRow("Pracuji v ...", "I work in ...", "formal / written. Uses Locative. Example: Pracuji v Brně.  (I work in Brno.)")
            BWRow("Pracuju v ...", "I work in ...", "colloquial / spoken alternative. Example: Pracuju v Brně.")

            // ── Common Phrases ────────────────────────────────────────────
            BWSection("Common Phrases")
            BWRow("mám rád", "I like (said by a man)", "literally 'I have gladly'; rád is the masculine form")
            BWRow("mám ráda", "I like (said by a woman)", "ráda is the feminine form; the verb mám does not change")
            BWRow("nemám rád / nemám ráda", "I don't like", "nemám = I don't have; rád / ráda same rule as above")
            BWRow("dám si", "I'll have (ordering food or drink)", "literally 'I'll give myself'; e.g. Dám si polévku. = I'll have the soup.")
            BWRow("nedám si", "I won't have / I'll pass", "literally 'I won't give myself'")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun BWSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun BWNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun BWRow(czech: String, english: String, note: String = "") {
    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)) {
                    append(czech)
                }
                withStyle(SpanStyle(fontSize = 16.sp, color = Color.DarkGray)) {
                    append("  —  $english")
                }
            }
        )
        if (note.isNotEmpty()) {
            Text(
                text = note,
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray,
                modifier = Modifier.padding(start = 4.dp, top = 1.dp)
            )
        }
    }
}
