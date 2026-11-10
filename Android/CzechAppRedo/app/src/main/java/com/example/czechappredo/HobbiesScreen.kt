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
fun HobbiesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hobbies", fontSize = 16.sp, fontWeight = FontWeight.Bold) },
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
            HBNote("To talk about hobbies: Mým koníčkem je... (My hobby is...) or Rád / Ráda + verb: Rád vařím. (I like to cook.)")
            HBRow("koníček / hobby", "hobby", "koníček literally = little horse")
            HBNote("Key trap: instruments take hrát na + accusative (hrát na kytaru), but games and sports take a plain accusative with no na (hrát karty, hrát šipky, hrát fotbal).")

            HBSection("Outdoors and Active")
            HBRow("zahradničení / práce na zahradě", "gardening", "zahradničit = to garden")
            HBRow("běhání / jogging", "jogging / running", "běhat = to run (as a habit)")
            HBRow("turistika", "hiking", "chodit na túry / na výlety = to go hiking")
            HBRow("cestování / cestovat", "traveling / to travel")

            HBSection("Games")
            HBRow("hrát karty", "to play cards", "karta = card; no na")
            HBRow("šipky", "darts", "plural: hrát šipky; also šipka = arrow sign")

            HBSection("Music")
            HBRow("hrát na hudební nástroj", "to play an instrument", "hrát na + accusative")
            HBRow("hrát na kytaru", "to play the guitar")
            HBRow("hrát na klavír / na piano", "to play the piano")
            HBRow("cvičit", "to practise", "cvičit na klavír = to practise piano. cvičit also = to exercise (in the gym).")
            HBRow("zpěv / zpívat", "singing / to sing")
            HBRow("psaní písní / skládání písní", "songwriting", "psát písně = to write songs; skládat = to compose")

            HBSection("Art and Crafts")
            HBRow("malování / malovat", "painting / to paint", "also = to paint walls")
            HBRow("kreslení / kreslit", "drawing / to draw")
            HBRow("sochařství", "sculpting / sculpture", "socha = statue")
            HBRow("řezbářství / vyřezávat", "carving / to carve", "vyřezávat ze dřeva = to carve wood")
            HBRow("pletení / plést", "knitting / to knit", "plést si = also to mix up / confuse")

            HBSection("Collecting")
            HBRow("sbírat / sbírání", "to collect / collecting", "sběratel / sběratelka = collector")
            HBRow("známka", "stamp", "also = a school grade")
            HBRow("sbírat známky", "to collect stamps", "filatelie = philately")
            HBRow("sbírka známek", "stamp collection", "sbírka = a collection")

            HBSection("Making and Fixing")
            HBRow("vaření / vařit", "cooking / to cook")
            HBRow("renovace / rekonstrukce", "renovation / renovating", "předělávat byt = to redo the apartment")
            HBRow("kutilství / kutil", "DIY / DIY-er", "kutit = to do DIY / tinker")
            HBRow("stavění / stavět", "building / to build", "stavět modely = to build models")
            HBRow("svařování / svařovat", "welding / to weld")
            HBRow("elektronika", "electronics", "bastlit (colloquial) = to tinker, especially with electronics")

            HBSection("Mind")
            HBRow("programování / programovat", "programming / to program")
            HBRow("debatování / debatovat", "debating / to debate", "diskutovat = to discuss")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun HBSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun HBNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun HBRow(czech: String, english: String, note: String = "") {
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
