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
fun TellingTheTimeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Telling The Time", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            TTSection("Asking The Time")
            TTRow("Kolik je hodin?", "What time is it?", "lit. 'how many is of hours?'; hodin = genitive plural of hodina (hour, f.)")
            TTRow("Kolik je teď hodin?", "What time is it now?")
            TTRow("V kolik hodin...?", "At what time...?", "v + accusative; used to ask when something happens")
            TTRow("Kdy začíná film?", "When does the movie start?", "kdy = when")
            TTRow("V kolik hodin začíná film?", "At what time does the movie start?")
            TTRow("Film začíná v sedm hodin.", "The movie starts at seven o'clock.", "v + accusative: v jednu / v dvě / v pět hodin")
            TTNote("'v' (at) before a time takes accusative: v jednu, v dvě, v deset hodin. For constructions: ve čtvrt na jedenáct (at quarter past ten), v půl jedenácté (at half past ten). Example: Jdeme v půl osmé. — We're going at half past seven.")

            TTSection("On The Hour (Celé hodiny)")
            TTRow("Je jedna.", "It is one o'clock.", "jedna = feminine numeral (hodina is f.); informal: Je jedna hodina.")
            TTRow("Je dvě hodiny.", "It is two o'clock.", "dvě + hodiny (nominative plural) for 2–4")
            TTRow("Je tři hodiny.", "It is three o'clock.")
            TTRow("Je čtyři hodiny.", "It is four o'clock.")
            TTRow("Je pět hodin.", "It is five o'clock.", "pět and above: hodin = genitive plural")
            TTRow("Je šest / sedm / osm / devět / deset hodin.", "It is six / seven / eight / nine / ten o'clock.")
            TTRow("Je jedenáct hodin.", "It is eleven o'clock.")
            TTRow("Je dvanáct hodin.", "It is twelve o'clock.")
            TTRow("Je poledne.", "It is noon.", "poledne = indeclinable neuter noun")
            TTRow("Je půlnoc.", "It is midnight.", "půlnoc = f., genitive: půlnoci")

            TTSection("Parts of The Day (Části Dne)")
            TTRow("ráno", "in the morning / early morning", "time adverb; also: z rána")
            TTRow("dopoledne", "in the morning (before noon)", "lit. 'before midday'")
            TTRow("v poledne", "at noon", "v + locative of poledne; also: Je poledne = It is noon")
            TTRow("odpoledne", "in the afternoon")
            TTRow("večer", "in the evening")
            TTRow("v noci", "at night", "v + locative of noc (f.): v noci")
            TTNote("ráno and dopoledne overlap for early-to-mid morning: ráno implies earlier (before ~9am), dopoledne covers up to noon.")
            TTRow("Je deset hodin ráno / dopoledne.", "It is ten in the morning.", "ráno = early; dopoledne = before noon")
            TTRow("Setkáme se v poledne.", "We'll meet at noon.")
            TTRow("Je tři hodiny odpoledne.", "It is three in the afternoon.")
            TTRow("Je deset hodin večer.", "It is ten in the evening.")
            TTRow("Vrátím se v noci.", "I'll return at night.")

            TTSection("Quarter Past (Čtvrt na...)")
            TTNote("'Je čtvrt na [next hour in accusative]' — the quarter-past construction counts toward the NEXT hour.")
            TTRow("Je čtvrt na jedenáct.", "It is quarter past ten.  (10:15)", "čtvrt = quarter; na + accusative of next hour")
            TTRow("Je čtvrt na dvanáct.", "It is quarter past eleven.  (11:15)")
            TTRow("Je čtvrt na jednu.", "It is quarter past twelve.  (12:15)", "jedna → jednu (accusative feminine)")
            TTRow("Je čtvrt na dvě.", "It is quarter past one.  (1:15)")

            TTSection("Half Past (Půl...)")
            TTNote("'Je půl [next hour as ordinal genitive feminine]' — Czech counts to half of the COMING hour, not past the current one.")
            TTRow("Je půl jedenácté.", "It is half past ten.  (10:30)", "jedenácté = genitive singular feminine of ordinal jedenáctý — half of the eleventh hour")
            TTRow("Je půl dvanácté.", "It is half past eleven.  (11:30)")
            TTRow("Je půl jedné.", "It is half past twelve.  (12:30)")
            TTRow("Je půl druhé.", "It is half past one.  (1:30)", "druhé = genitive singular feminine of druhý")

            TTSection("Three-Quarters / Quarter To (Tři čtvrtě na...)")
            TTNote("'Je tři čtvrtě na [next hour accusative]' — three-quarters toward the next hour.")
            TTRow("Je tři čtvrtě na jedenáct.", "It is quarter to eleven.  (10:45)")
            TTRow("Je tři čtvrtě na dvanáct.", "It is quarter to twelve.  (11:45)")
            TTRow("Je tři čtvrtě na jednu.", "It is quarter to one.  (12:45)")

            TTSection("Specific Minutes — First Half (Minutes 1–30)")
            TTNote("First half: '[X minut] po [current hour ordinal locative]' — X minutes after the Nth hour.")
            TTRow("dvě minuty po desáté", "two minutes past ten  (10:02)", "po + locative of feminine ordinal: desáté")
            TTRow("čtrnáct minut po desáté", "fourteen minutes past ten  (10:14)")
            TTRow("šest minut před půl jedenáctou", "six minutes before half-eleven  (10:24)", "před + instrumental of půl jedenácté → půl jedenáctou")

            TTSection("Specific Minutes — Second Half (Minutes 31–59)")
            TTNote("Second half: 'za [X minut] [next hour nominative]' — in X minutes, it will be [next hour].")
            TTRow("za dvanáct jedenáct", "twelve minutes to eleven  (10:48)", "short form: 'in twelve, eleven'; formal: dvanáct minut před jedenáctou")
            TTRow("za dvě jedenáct", "two minutes to eleven  (10:58)", "short form; formal: dvě minuty před jedenáctou")
            TTRow("dvanáct minut před jedenáctou", "twelve minutes before eleven  (10:48 — formal)", "před + instrumental: jedenáctou")
            TTRow("dvě minuty před jedenáctou", "two minutes before eleven  (10:58 — formal)")

            TTSection("Czech vs. Russian Comparison")
            TTNote("Both languages divide the hour into two halves and reference surrounding hour marks rather than using purely digital notation.")
            TTRow("Czech first half — 'po [current hour ordinal locative]'", "e.g. dvě minuty po desáté = 2 min past ten", "references the PAST hour using 'po' (after) + ordinal in locative")
            TTRow("Russian first half — '[X] минут одиннадцатого'", "e.g. две минуты одиннадцатого = 2 min past ten", "references the COMING hour with genitive of the ordinal — 'of the eleventh'")
            TTRow("Czech second half — 'za [X minut] [next hour nominative]'", "e.g. za dvě jedenáct = in 2 min, eleven", "refers to the coming hour; za = within/in")
            TTRow("Russian second half — 'без [X] [next hour]'", "e.g. без двух одиннадцать = without two, eleven", "refers to the coming hour; без = minus/without")
            TTNote("Key difference: first half — Czech references the hour just passed ('after the tenth'), Russian references the coming hour ('of the eleventh'). Second half — both refer to the coming hour; Czech says 'in X minutes it will be [hour]', Russian says '[hour] minus X minutes'.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun TTSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun TTRow(czech: String, english: String, note: String = "") {
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

@Composable
private fun TTNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
