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
fun AdjectiveConjugationStrongScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Strong (silný)", fontSize = 15.sp, fontWeight = FontWeight.Bold) },
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
            CCNote("silný (strong) is a regular hard adjective (-ý / -á / -é). The same four nouns — muž (man), žena (woman), auto (car), muži (men) — are used across all four cases below so you can compare the adjective endings directly.")

            // ── silný muž ─────────────────────────────────────────────────
            CCPhrase("silný muž", "a strong man", "masculine animate · hard adjective · singular")
            CCCase("1. Nominativ", "Silný muž zvedl tu skříň.", "A strong man lifted the wardrobe.", "Silný muž")
            CCCase("2. Genitiv", "Bál jsem se silného muže.", "I was afraid of the strong man.", "silného muže")
            CCCase("3. Dativ", "Dal jsem tu bednu silnému muži.", "I gave the crate to the strong man.", "silnému muži")
            CCCase("4. Akuzativ", "Potkal jsem silného muže.", "I met a strong man.", "silného muže")
            CCCase("5. Vokativ", "Silný muži, pomoz mi s tím!", "Strong man, help me with this!", "Silný muži")
            CCCase("6. Lokál", "Mluvili jsme o silném muži.", "We talked about the strong man.", "silném muži")
            CCCase("7. Instrumentál", "Pracuji se silným mužem.", "I work with a strong man.", "silným mužem")

            // ── silná žena ────────────────────────────────────────────────
            CCPhrase("silná žena", "a strong woman", "feminine · hard adjective · singular")
            CCCase("1. Nominativ", "Silná žena zvládla celé stěhování sama.", "A strong woman handled the whole move by herself.", "Silná žena")
            CCCase("2. Genitiv", "Bez silné ženy bychom ten gauč neuzvedli.", "Without a strong woman we wouldn't lift that couch.", "silné ženy")
            CCCase("3. Dativ", "Dal jsem tu tašku silné ženě.", "I gave the bag to the strong woman.", "silné ženě")
            CCCase("4. Akuzativ", "Vidím silnou ženu.", "I see a strong woman.", "silnou ženu")
            CCCase("5. Vokativ", "Silná ženo, pomoz mi s tím!", "Strong woman, help me with this!", "Silná ženo")
            CCCase("6. Lokál", "Mluvili jsme o silné ženě.", "We talked about the strong woman.", "silné ženě")
            CCCase("7. Instrumentál", "Pracuji se silnou ženou.", "I work with a strong woman.", "silnou ženou")

            // ── silné auto ────────────────────────────────────────────────
            CCPhrase("silné auto", "a powerful car", "neuter · hard adjective · singular — accusative equals the nominative because the noun is inanimate")
            CCCase("1. Nominativ", "Silné auto zvládne i horský terén.", "A powerful car can handle mountain terrain too.", "Silné auto")
            CCCase("2. Genitiv", "Cena silného auta je vysoká.", "The price of a powerful car is high.", "silného auta")
            CCCase("3. Dativ", "Mechanik dal silnému autu nový motor.", "The mechanic gave the powerful car a new engine.", "silnému autu")
            CCCase("4. Akuzativ", "Koupil jsem si silné auto.", "I bought myself a powerful car.", "silné auto")
            CCCaseNote("5. Vokativ", "Inanimate object — the vocative is identical to the nominative; Czech doesn't address things directly.", "silné auto")
            CCCase("6. Lokál", "Mluvíme o silném autě.", "We're talking about the powerful car.", "silném autě")
            CCCase("7. Instrumentál", "Jel jsem silným autem.", "I drove there in a powerful car.", "silným autem")

            // ── silní muži ────────────────────────────────────────────────
            CCPhrase("silní muži", "strong men", "masculine animate · plural — no consonant softening needed here (siln- + í = silní)")
            CCCase("1. Nominativ", "Silní muži přenesli klavír po schodech.", "Strong men carried the piano up the stairs.", "Silní muži")
            CCCase("2. Genitiv", "Bez silných mužů bychom to stěhování nezvládli.", "Without strong men we wouldn't manage the move.", "silných mužů")
            CCCase("3. Dativ", "Věřím silným mužům.", "I trust strong men.", "silným mužům")
            CCCase("4. Akuzativ", "Najali jsme silné muže.", "We hired strong men.", "silné muže")
            CCCase("5. Vokativ", "Silní muži, pojďte sem!", "Strong men, come here!", "Silní muži")
            CCCase("6. Lokál", "Píšou o silných mužích.", "They write about strong men.", "silných mužích")
            CCCase("7. Instrumentál", "Spolupracuji se silnými muži.", "I collaborate with strong men.", "silnými muži")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CCNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun CCPhrase(phrase: String, english: String, info: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp, color = ButtonBlue)) {
                append(phrase)
            }
            withStyle(SpanStyle(fontSize = 16.sp, color = Color.DarkGray)) {
                append("  —  $english")
            }
        }
    )
    Text(
        text = info,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(top = 2.dp, bottom = 6.dp)
    )
    HorizontalDivider(modifier = Modifier.padding(bottom = 6.dp))
}

@Composable
private fun CCCase(caseLabel: String, czech: String, english: String, form: String) {
    Column(modifier = Modifier.padding(vertical = 5.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = caseLabel, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
            Text(text = form, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
        Text(text = czech, fontSize = 15.sp, color = Color.DarkGray, modifier = Modifier.padding(top = 2.dp))
        Text(text = english, fontSize = 13.sp, fontStyle = FontStyle.Italic, color = Color.Gray)
    }
}

@Composable
private fun CCCaseNote(caseLabel: String, note: String, form: String) {
    Column(modifier = Modifier.padding(vertical = 5.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = caseLabel, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
            Text(text = form, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
        Text(
            text = note,
            fontSize = 13.sp,
            fontStyle = FontStyle.Italic,
            color = Color.Gray,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}
