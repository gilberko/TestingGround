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
fun UsefulVerbsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Very Useful Verbs", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            UVSection("Movement Verbs")
            UVVerbEntry(
                czech = "jít",
                english = "to go (on foot)",
                rule = "Destination: do + Genitive (enclosed place) or na + Accusative (event/open space).",
                example = "Jdu do školy.",
                translation = "I'm going to school."
            )
            UVVerbEntry(
                czech = "jet",
                english = "to go (by vehicle)",
                rule = "Same preposition patterns as jít — use jet whenever riding.",
                example = "Jedu do Prahy.",
                translation = "I'm going to Prague (by vehicle)."
            )

            UVSection("Modal Verbs")
            UVVerbEntry(
                czech = "muset",
                english = "must / have to",
                rule = "Followed directly by an infinitive — no preposition or case change.",
                example = "Musím jít.",
                translation = "I have to go."
            )
            UVVerbEntry(
                czech = "moct",
                english = "can / to be able to",
                rule = "Followed directly by an infinitive.",
                example = "Můžeš mi pomoci?",
                translation = "Can you help me?"
            )

            UVSection("Giving, Taking & Transfer")
            UVVerbEntry(
                czech = "dát",
                english = "to give",
                rule = "Takes Dative (to whom) + Accusative (what).",
                example = "Dám ti knihu.",
                translation = "I'll give you a book."
            )
            UVVerbEntry(
                czech = "vzít",
                english = "to take",
                rule = "Takes Accusative (what you take). Reflexive vzít si = to take for oneself.",
                example = "Vezmu si kabelku.",
                translation = "I'll take the bag."
            )
            UVVerbEntry(
                czech = "poslat",
                english = "to send",
                rule = "Takes Dative (to whom) + Accusative (what).",
                example = "Pošlu ti zprávu.",
                translation = "I'll send you a message."
            )
            UVVerbEntry(
                czech = "dostat",
                english = "to receive / to get",
                rule = "Takes Accusative (what is received).",
                example = "Dostal jsem dopis.",
                translation = "I received a letter."
            )
            UVVerbEntry(
                czech = "přinést",
                english = "to bring",
                rule = "Takes Dative (for whom) + Accusative (what).",
                example = "Přinesu ti vodu.",
                translation = "I'll bring you water."
            )

            UVSection("Common Everyday Verbs")
            UVVerbEntry(
                czech = "mít",
                english = "to have",
                rule = "Takes Accusative (what you have).",
                example = "Mám knihu.",
                translation = "I have a book."
            )
            UVVerbEntry(
                czech = "dělat",
                english = "to do / to make",
                rule = "Takes Accusative (what you do or make).",
                example = "Co děláš?",
                translation = "What are you doing?"
            )
            UVVerbEntry(
                czech = "pracovat",
                english = "to work",
                rule = "Location: v / ve + Locative (where you work).",
                example = "Pracuji v kanceláři.",
                translation = "I work in an office."
            )
            UVVerbEntry(
                czech = "studovat",
                english = "to study",
                rule = "Takes Accusative (subject being studied).",
                example = "Studuji češtinu.",
                translation = "I'm studying Czech."
            )
            UVVerbEntry(
                czech = "myslet",
                english = "to think",
                rule = "myslet na + Accusative = to think about something. myslet si = to believe/to think (opinion).",
                example = "Myslím na tebe.",
                translation = "I'm thinking about you."
            )
            UVVerbEntry(
                czech = "přestat",
                english = "to stop (doing something)",
                rule = "Followed directly by an infinitive — no preposition.",
                example = "Přestaň mluvit!",
                translation = "Stop talking!"
            )
            UVVerbEntry(
                czech = "běžet",
                english = "to run",
                rule = "Destination: do + Genitive or na + Accusative.",
                example = "Běžím do parku.",
                translation = "I'm running to the park."
            )
            UVVerbEntry(
                czech = "vrátit se",
                english = "to return / to come back",
                rule = "Back home: vrátit se domů. Back to a place: do + Genitive.",
                example = "Vrátím se domů.",
                translation = "I'll return home."
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun UVSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun UVVerbEntry(
    czech: String,
    english: String,
    rule: String,
    example: String,
    translation: String
) {
    Spacer(modifier = Modifier.height(10.dp))
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
    Text(
        text = rule,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(top = 2.dp, start = 2.dp)
    )
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)) {
                append(example)
            }
            withStyle(SpanStyle(fontSize = 14.sp, color = Color.Gray, fontStyle = FontStyle.Italic)) {
                append("  —  $translation")
            }
        },
        modifier = Modifier.padding(top = 3.dp, start = 2.dp)
    )
}
