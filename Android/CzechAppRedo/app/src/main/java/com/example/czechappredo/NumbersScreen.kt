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
fun NumbersScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Numbers", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // 0–9
            NUSection("Cardinal Numbers: 0–9")
            NUNote("1 agrees in gender: jeden (m), jedna (f), jedno (n). 2: dva (m/n), dvě (f).")
            NURow("0", "nula")
            NURow("1", "jedna / jeden / jedno")
            NURow("2", "dva / dvě")
            NURow("3", "tři")
            NURow("4", "čtyři")
            NURow("5", "pět")
            NURow("6", "šest")
            NURow("7", "sedm")
            NURow("8", "osm")
            NURow("9", "devět")

            // Tens and 100
            NUSection("Tens and 100")
            NURow("10", "deset")
            NURow("20", "dvacet")
            NURow("30", "třicet")
            NURow("40", "čtyřicet")
            NURow("50", "padesát")
            NURow("60", "šedesát")
            NURow("70", "sedmdesát")
            NURow("80", "osmdesát")
            NURow("90", "devadesát")
            NURow("100", "sto")

            // Teens
            NUSection("Teens: 11–19")
            NURow("11", "jedenáct")
            NURow("12", "dvanáct")
            NURow("13", "třináct")
            NURow("14", "čtrnáct")
            NURow("15", "patnáct")
            NURow("16", "šestnáct")
            NURow("17", "sedmnáct")
            NURow("18", "osmnáct")
            NURow("19", "devatenáct")

            // Hundreds and 1000
            NUSection("Hundreds and 1000")
            NUNote("The word sto changes form depending on how many hundreds: 200 = dvě stě, 300–400 = tři/čtyři sta, 500–900 = X set.")
            NURow("100", "sto")
            NURow("200", "dvě stě")
            NURow("300", "tři sta")
            NURow("400", "čtyři sta")
            NURow("500", "pět set")
            NURow("600", "šest set")
            NURow("700", "sedm set")
            NURow("800", "osm set")
            NURow("900", "devět set")
            NURow("1000", "tisíc")

            // Examples
            NUSection("Compound Number Examples")
            NUNote("Compound numbers are formed by saying the larger unit first, then the smaller.")
            NURow("4", "čtyři")
            NURow("13", "třináct")
            NURow("59", "padesát devět")
            NURow("180", "sto osmdesát")
            NURow("250", "dvě stě padesát")
            NURow("870", "osm set sedmdesát")

            // Ordinal numbers
            NUSection("Ordinal Numbers")
            NUNote("Ordinal numbers decline like adjectives and agree in gender, number, and case. The forms below are masculine nominative singular.")
            NURow("1st", "první")
            NURow("2nd", "druhý")
            NURow("3rd", "třetí")
            NURow("4th", "čtvrtý")
            NURow("5th", "pátý")
            NURow("6th", "šestý")
            NURow("7th", "sedmý")
            NURow("8th", "osmý")
            NURow("9th", "devátý")
            NURow("10th", "desátý")
            NURow("11th", "jedenáctý")
            NURow("12th", "dvanáctý")
            NURow("13th", "třináctý")
            NURow("14th", "čtrnáctý")
            NURow("15th", "patnáctý")
            NURow("16th", "šestnáctý")
            NURow("17th", "sedmnáctý")
            NURow("18th", "osmnáctý")
            NURow("19th", "devatenáctý")
            NURow("20th", "dvacátý")
            NURow("21st", "dvacátý první")
            NURow("22nd", "dvacátý druhý")
            NURow("23rd", "dvacátý třetí")
            NURow("24th", "dvacátý čtvrtý")
            NURow("25th", "dvacátý pátý")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun NUSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun NUNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

@Composable
private fun NURow(numeral: String, czech: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.DarkGray)) {
                    append(numeral)
                }
            },
            modifier = Modifier.width(60.dp)
        )
        Text(
            text = czech,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}
