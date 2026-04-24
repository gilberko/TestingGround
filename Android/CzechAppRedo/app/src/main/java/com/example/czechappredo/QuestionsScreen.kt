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
fun QuestionsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Questions", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            QSSection("Question Words")
            QSRow("Co?", "What?")
            QSRow("Kdo?", "Who?")
            QSRow("Kde?", "Where? (location — static)")
            QSRow("Kam?", "Where to? (direction — motion)")
            QSRow("Odkud?", "Where from? (origin)")
            QSRow("Kdy?", "When?")
            QSRow("Jak?", "How?")
            QSRow("Proč?", "Why?")
            QSRow("Nač? / K čemu?", "What for? (purpose)")
            QSRow("Kolik?", "How many? / How much?")
            QSRow("Jaký / Jaká / Jaké?", "What kind of? (agrees with noun gender)")
            QSRow("Který / Která / Které?", "Which? — choosing from a set (agrees with noun gender)")

            QSSection("Who / To Whom / From Whom")
            QSNote("kdo (who) declines like a pronoun: Nominative kdo, Genitive koho, Dative komu, Accusative koho, Instrumental kým.")
            QSRow("Kdo?", "Who? (subject)")
            QSRow("Koho?", "Who? / Whom? (object — Accusative/Genitive)")
            QSRow("Komu?", "To whom? (Dative)")
            QSRow("Od koho?", "From whom? (Genitive)")
            QSRow("Pro koho?", "For whom? (Accusative with pro)")
            QSRow("S kým?", "With whom? (Instrumental)")

            QSSection("Time Questions")
            QSRow("Kdy?", "When?")
            QSRow("Od kdy?", "From when? / Since when?")
            QSRow("Do kdy?", "Until when? / By when?")
            QSRow("Na jak dlouho?", "For how long?")
            QSRow("Od kdy do kdy?", "From when until when?")

            QSSection("Examples")
            QSExample("Odkud jsi?", "Where are you from?")
            QSExample("Kam jdeš?", "Where are you going? (on foot)")
            QSExample("Kam jedeš?", "Where are you going? (by vehicle)")
            QSExample("Co děláš?", "What are you doing?")
            QSExample("Proč to děláš?", "Why are you doing that?")
            QSExample("Jak se jmenuješ?", "What is your name?")
            QSExample("Kolik to stojí?", "How much does it cost?")
            QSExample("Kdy přijdeš?", "When will you come?")
            QSExample("S kým jdeš?", "Who are you going with?")
            QSExample("Komu to dáš?", "Who will you give it to?")
            QSExample("Pro koho je to?", "Who is it for?")
            QSExample("Na jak dlouho jdeš?", "For how long are you going?")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun QSSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun QSRow(czech: String, english: String) {
    Spacer(modifier = Modifier.height(5.dp))
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
}

@Composable
private fun QSNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

@Composable
private fun QSExample(czech: String, english: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = czech, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    Text(
        text = english,
        fontSize = 14.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(start = 4.dp, top = 1.dp)
    )
}
