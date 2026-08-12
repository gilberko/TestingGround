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
fun AdjectiveConjugationSmartScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Smart (chytrý)", fontSize = 15.sp, fontWeight = FontWeight.Bold) },
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
            CCNote("chytrý (smart) is a regular hard adjective (-ý / -á / -é). The same four nouns — muž (man), žena (woman), auto (car), muži (men) — are used across all four cases below so you can compare the adjective endings directly.")
            CCNote("Note the r → ř softening in the masculine plural: chytrý → chytří (nominative/vocative), unlike silný → silní or zajímavý → zajímaví, which don't soften.")

            // ── chytrý muž ────────────────────────────────────────────────
            CCPhrase("chytrý muž", "a smart man", "masculine animate · hard adjective · singular")
            CCCase("1. Nominativ", "Chytrý muž přišel pozdě.", "A smart man arrived late.", "Chytrý muž")
            CCCase("2. Genitiv", "Bál jsem se chytrého muže.", "I was afraid of a smart man.", "chytrého muže")
            CCCase("3. Dativ", "Dal jsem radu chytrému muži.", "I gave advice to a smart man.", "chytrému muži")
            CCCase("4. Akuzativ", "Potkal jsem chytrého muže.", "I met a smart man.", "chytrého muže")
            CCCase("5. Vokativ", "Chytrý muži, poraď mi!", "Smart man, give me some advice!", "Chytrý muži")
            CCCase("6. Lokál", "Mluvili jsme o chytrém muži.", "We talked about a smart man.", "chytrém muži")
            CCCase("7. Instrumentál", "Pracuji s chytrým mužem.", "I work with a smart man.", "chytrým mužem")

            // ── chytrá žena ───────────────────────────────────────────────
            CCPhrase("chytrá žena", "a smart woman", "feminine · hard adjective · singular")
            CCCase("1. Nominativ", "Chytrá žena vyřešila ten problém.", "A smart woman solved the problem.", "Chytrá žena")
            CCCase("2. Genitiv", "Bez chytré ženy bychom to nezvládli.", "Without a smart woman we wouldn't manage it.", "chytré ženy")
            CCCase("3. Dativ", "Dal jsem radu chytré ženě.", "I gave advice to a smart woman.", "chytré ženě")
            CCCase("4. Akuzativ", "Znám chytrou ženu.", "I know a smart woman.", "chytrou ženu")
            CCCase("5. Vokativ", "Chytrá ženo, poraď mi!", "Smart woman, give me some advice!", "Chytrá ženo")
            CCCase("6. Lokál", "Mluvili jsme o chytré ženě.", "We talked about a smart woman.", "chytré ženě")
            CCCase("7. Instrumentál", "Pracuji s chytrou ženou.", "I work with a smart woman.", "chytrou ženou")

            // ── chytré auto ───────────────────────────────────────────────
            CCPhrase("chytré auto", "a smart car", "neuter · hard adjective · singular — accusative equals the nominative because the noun is inanimate")
            CCCase("1. Nominativ", "Chytré auto samo zaparkuje.", "A smart car parks itself.", "Chytré auto")
            CCCase("2. Genitiv", "Cena chytrého auta je vysoká.", "The price of a smart car is high.", "chytrého auta")
            CCCase("3. Dativ", "Nainstalovali novou aplikaci chytrému autu.", "They installed a new app for the smart car.", "chytrému autu")
            CCCase("4. Akuzativ", "Koupil jsem si chytré auto.", "I bought myself a smart car.", "chytré auto")
            CCCaseNote("5. Vokativ", "Inanimate object — the vocative is identical to the nominative; Czech doesn't address things directly.", "chytré auto")
            CCCase("6. Lokál", "Mluvíme o chytrém autě.", "We're talking about the smart car.", "chytrém autě")
            CCCase("7. Instrumentál", "Jel jsem chytrým autem.", "I drove there in a smart car.", "chytrým autem")

            // ── chytří muži ───────────────────────────────────────────────
            CCPhrase("chytří muži", "smart men", "masculine animate · plural — note the r → ř softening before í (chytrý → chytří), and that the accusative plural (chytré) differs from the nominative (chytří)")
            CCCase("1. Nominativ", "Chytří muži vždycky najdou řešení.", "Smart men always find a solution.", "Chytří muži")
            CCCase("2. Genitiv", "Bez chytrých mužů by firma zkrachovala.", "Without smart men the company would go bankrupt.", "chytrých mužů")
            CCCase("3. Dativ", "Věřím chytrým mužům.", "I trust smart men.", "chytrým mužům")
            CCCase("4. Akuzativ", "Znám chytré muže.", "I know smart men.", "chytré muže")
            CCCase("5. Vokativ", "Chytří muži, poslouchejte!", "Smart men, listen!", "Chytří muži")
            CCCase("6. Lokál", "Píšou o chytrých mužích.", "They write about smart men.", "chytrých mužích")
            CCCase("7. Instrumentál", "Spolupracuji s chytrými muži.", "I collaborate with smart men.", "chytrými muži")

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
