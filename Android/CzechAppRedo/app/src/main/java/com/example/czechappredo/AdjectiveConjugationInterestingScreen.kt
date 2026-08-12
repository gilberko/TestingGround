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
fun AdjectiveConjugationInterestingScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Interesting (zajímavý)", fontSize = 15.sp, fontWeight = FontWeight.Bold) },
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
            CCNote("zajímavý (interesting) is a regular hard adjective (-ý / -á / -é). The same four nouns — muž (man), žena (woman), auto (car), muži (men) — are used across all four cases below so you can compare the adjective endings directly.")

            // ── zajímavý muž ──────────────────────────────────────────────
            CCPhrase("zajímavý muž", "an interesting man", "masculine animate · hard adjective · singular")
            CCCase("1. Nominativ", "Zajímavý muž seděl u baru.", "An interesting man was sitting at the bar.", "Zajímavý muž")
            CCCase("2. Genitiv", "Všiml jsem si zajímavého muže.", "I noticed an interesting man.", "zajímavého muže")
            CCCase("3. Dativ", "Naslouchal jsem zajímavému muži.", "I listened to an interesting man.", "zajímavému muži")
            CCCase("4. Akuzativ", "Potkal jsem zajímavého muže.", "I met an interesting man.", "zajímavého muže")
            CCCase("5. Vokativ", "Zajímavý muži, povězte mi víc!", "Interesting man, tell me more!", "Zajímavý muži")
            CCCase("6. Lokál", "Mluvili jsme o zajímavém muži.", "We talked about an interesting man.", "zajímavém muži")
            CCCase("7. Instrumentál", "Bavil jsem se se zajímavým mužem.", "I chatted with an interesting man.", "se zajímavým mužem")

            // ── zajímavá žena ─────────────────────────────────────────────
            CCPhrase("zajímavá žena", "an interesting woman", "feminine · hard adjective · singular")
            CCCase("1. Nominativ", "Zajímavá žena vyprávěla svůj příběh.", "An interesting woman told her story.", "Zajímavá žena")
            CCCase("2. Genitiv", "Všiml jsem si zajímavé ženy.", "I noticed an interesting woman.", "zajímavé ženy")
            CCCase("3. Dativ", "Naslouchal jsem zajímavé ženě.", "I listened to an interesting woman.", "zajímavé ženě")
            CCCase("4. Akuzativ", "Potkal jsem zajímavou ženu.", "I met an interesting woman.", "zajímavou ženu")
            CCCase("5. Vokativ", "Zajímavá ženo, povězte mi víc!", "Interesting woman, tell me more!", "Zajímavá ženo")
            CCCase("6. Lokál", "Mluvili jsme o zajímavé ženě.", "We talked about an interesting woman.", "zajímavé ženě")
            CCCase("7. Instrumentál", "Bavil jsem se se zajímavou ženou.", "I chatted with an interesting woman.", "se zajímavou ženou")

            // ── zajímavé auto ─────────────────────────────────────────────
            CCPhrase("zajímavé auto", "an interesting car", "neuter · hard adjective · singular — accusative equals the nominative because the noun is inanimate")
            CCCase("1. Nominativ", "Zajímavé auto stálo na výstavě.", "An interesting car stood at the exhibition.", "Zajímavé auto")
            CCCase("2. Genitiv", "Design zajímavého auta mě překvapil.", "The design of the interesting car surprised me.", "zajímavého auta")
            CCCase("3. Dativ", "Věnovali jsme tomu zajímavému autu celý článek.", "We dedicated a whole article to the interesting car.", "zajímavému autu")
            CCCase("4. Akuzativ", "Fotil jsem to zajímavé auto.", "I photographed that interesting car.", "zajímavé auto")
            CCCaseNote("5. Vokativ", "Inanimate object — the vocative is identical to the nominative; Czech doesn't address things directly.", "zajímavé auto")
            CCCase("6. Lokál", "Psali jsme o tom zajímavém autě.", "We wrote about that interesting car.", "zajímavém autě")
            CCCase("7. Instrumentál", "Byl jsem fascinován tím zajímavým autem.", "I was fascinated by that interesting car.", "zajímavým autem")

            // ── zajímaví muži ─────────────────────────────────────────────
            CCPhrase("zajímaví muži", "interesting men", "masculine animate · plural — no consonant softening needed here (zajímav- + í = zajímaví)")
            CCCase("1. Nominativ", "Zajímaví muži přednášeli na konferenci.", "Interesting men gave lectures at the conference.", "Zajímaví muži")
            CCCase("2. Genitiv", "Bez zajímavých mužů by ta debata byla nudná.", "Without interesting men the debate would be boring.", "zajímavých mužů")
            CCCase("3. Dativ", "Kladl jsem otázky zajímavým mužům.", "I asked questions to interesting men.", "zajímavým mužům")
            CCCase("4. Akuzativ", "Pozvali jsme zajímavé muže.", "We invited interesting men.", "zajímavé muže")
            CCCase("5. Vokativ", "Zajímaví muži, přistupte blíž!", "Interesting men, step closer!", "Zajímaví muži")
            CCCase("6. Lokál", "Psali jsme o zajímavých mužích.", "We wrote about interesting men.", "zajímavých mužích")
            CCCase("7. Instrumentál", "Bavil jsem se se zajímavými muži.", "I chatted with interesting men.", "se zajímavými muži")

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
