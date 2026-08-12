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
fun AdjectiveConjugationWrongScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Wrong (špatný)", fontSize = 15.sp, fontWeight = FontWeight.Bold) },
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
            CCNote("špatný (wrong / bad) is a regular hard adjective (-ý / -á / -é). The same four nouns — muž (man), žena (woman), auto (car), muži (men) — are used across all four cases below so you can compare the adjective endings directly.")

            // ── špatný muž ────────────────────────────────────────────────
            CCPhrase("špatný muž", "a bad man", "masculine animate · hard adjective · singular")
            CCCase("1. Nominativ", "Špatný muž lhal policii.", "A bad man lied to the police.", "Špatný muž")
            CCCase("2. Genitiv", "Bála se toho špatného muže.", "She was afraid of that bad man.", "špatného muže")
            CCCase("3. Dativ", "Nevěřím tomu špatnému muži.", "I don't trust that bad man.", "špatnému muži")
            CCCase("4. Akuzativ", "Poznal jsem toho špatného muže.", "I recognized that bad man.", "špatného muže")
            CCCase("5. Vokativ", "Špatný muži, zmiz odsud!", "Bad man, get out of here!", "Špatný muži")
            CCCase("6. Lokál", "Mluvili jsme o špatném muži.", "We talked about the bad man.", "špatném muži")
            CCCase("7. Instrumentál", "Nechci mít nic společného se špatným mužem.", "I don't want anything to do with a bad man.", "se špatným mužem")

            // ── špatná žena ───────────────────────────────────────────────
            CCPhrase("špatná žena", "a bad woman", "feminine · hard adjective · singular")
            CCCase("1. Nominativ", "Špatná žena okradla svého souseda.", "A bad woman robbed her neighbor.", "Špatná žena")
            CCCase("2. Genitiv", "Bála jsem se té špatné ženy.", "I was afraid of that bad woman.", "špatné ženy")
            CCCase("3. Dativ", "Nevěřím té špatné ženě.", "I don't trust that bad woman.", "špatné ženě")
            CCCase("4. Akuzativ", "Poznal jsem tu špatnou ženu.", "I recognized that bad woman.", "špatnou ženu")
            CCCase("5. Vokativ", "Špatná ženo, zmiz odsud!", "Bad woman, get out of here!", "Špatná ženo")
            CCCase("6. Lokál", "Mluvili jsme o špatné ženě.", "We talked about the bad woman.", "špatné ženě")
            CCCase("7. Instrumentál", "Nechci mít nic společného se špatnou ženou.", "I don't want anything to do with a bad woman.", "se špatnou ženou")

            // ── špatné auto ───────────────────────────────────────────────
            CCPhrase("špatné auto", "a bad car", "neuter · hard adjective · singular — accusative equals the nominative because the noun is inanimate")
            CCCase("1. Nominativ", "Špatné auto se pořád rozbíjí.", "A bad car keeps breaking down.", "Špatné auto")
            CCCase("2. Genitiv", "Prodejce se zbavil špatného auta.", "The dealer got rid of the bad car.", "špatného auta")
            CCCase("3. Dativ", "Dali jsme tomu špatnému autu poslední šanci.", "We gave that bad car one last chance.", "špatnému autu")
            CCCase("4. Akuzativ", "Prodal jsem to špatné auto.", "I sold that bad car.", "špatné auto")
            CCCaseNote("5. Vokativ", "Inanimate object — the vocative is identical to the nominative; Czech doesn't address things directly.", "špatné auto")
            CCCase("6. Lokál", "Mluvíme o tom špatném autě.", "We're talking about that bad car.", "špatném autě")
            CCCase("7. Instrumentál", "Měl jsem problémy s tím špatným autem.", "I had problems with that bad car.", "špatným autem")

            // ── špatní muži ───────────────────────────────────────────────
            CCPhrase("špatní muži", "bad men", "masculine animate · plural — no consonant softening needed here (špatn- + í = špatní)")
            CCCase("1. Nominativ", "Špatní muži se schovávali ve stínu.", "Bad men were hiding in the shadows.", "Špatní muži")
            CCCase("2. Genitiv", "Bez špatných mužů by tu bylo bezpečněji.", "Without bad men it would be safer here.", "špatných mužů")
            CCCase("3. Dativ", "Policie nevěří špatným mužům.", "The police don't trust bad men.", "špatným mužům")
            CCCase("4. Akuzativ", "Zatkli špatné muže.", "They arrested the bad men.", "špatné muže")
            CCCase("5. Vokativ", "Špatní muži, ruce vzhůru!", "Bad men, hands up!", "Špatní muži")
            CCCase("6. Lokál", "Mluvili o špatných mužích.", "They talked about the bad men.", "špatných mužích")
            CCCase("7. Instrumentál", "Nechci nic mít společného se špatnými muži.", "I don't want anything to do with bad men.", "se špatnými muži")

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
