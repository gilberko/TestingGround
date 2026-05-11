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
fun AdverbListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adverbs", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            AVLSection("Manner — Jak? (How?)")
            AVLRow("dobře", "well")
            AVLRow("špatně", "badly / poorly")
            AVLRow("rychle", "quickly / fast")
            AVLRow("pomalu", "slowly")
            AVLRow("tiše", "quietly")
            AVLRow("hlasitě", "loudly")
            AVLRow("opatrně", "carefully")
            AVLRow("správně", "correctly / properly")
            AVLRow("úplně", "completely / totally")
            AVLRow("asi", "probably / approximately")

            AVLSection("Time — Kdy? (When?)")
            AVLRow("teď / nyní", "now")
            AVLRow("hned", "right away / immediately")
            AVLRow("pak / potom", "then / afterwards")
            AVLRow("dnes", "today")
            AVLRow("včera", "yesterday")
            AVLRow("zítra", "tomorrow")
            AVLRow("ráno", "in the morning")
            AVLRow("večer", "in the evening")
            AVLRow("brzy", "soon / early")
            AVLRow("pozdě", "late")
            AVLRow("vždy / vždycky", "always")
            AVLRow("nikdy", "never")
            AVLRow("někdy", "sometimes")
            AVLRow("občas", "occasionally / from time to time")
            AVLRow("ještě", "still / yet")
            AVLRow("už", "already / anymore")
            AVLRow("znovu / zase", "again")
            AVLRow("dávno", "long ago")

            AVLSection("Place — Kde? (Where?)")
            AVLRow("tady / zde", "here")
            AVLRow("tam", "there")
            AVLRow("blízko", "nearby / close")
            AVLRow("daleko", "far")
            AVLRow("venku", "outside")
            AVLRow("doma", "at home")
            AVLRow("vlevo", "to the left / on the left")
            AVLRow("vpravo", "to the right / on the right")
            AVLRow("nahoru", "upward / up")
            AVLRow("dolů", "downward / down")

            AVLSection("Degree — Jak moc? (How much?)")
            AVLRow("velmi / moc", "very / a lot")
            AVLRow("trochu / trošku", "a little / a bit")
            AVLRow("dost / dosti", "enough / quite")
            AVLRow("příliš", "too / too much")
            AVLRow("skoro / téměř", "almost / nearly")
            AVLRow("jen / pouze", "only / just")
            AVLRow("docela", "quite / pretty")
            AVLRow("spíše", "rather")
            AVLRow("zvlášť", "especially / particularly")
            AVLRow("zcela", "completely / entirely")

            AVLSection("Comparison of Adverbs")
            AVLRow("dobře", "well", "→ lépe / líp (better) → nejlépe / nejlíp (best)")
            AVLRow("špatně", "badly", "→ hůře / hůř (worse) → nejhůře / nejhůř (worst)")
            AVLRow("rychle", "fast", "→ rychleji (faster) → nejrychleji (fastest)")
            AVLRow("pomalu", "slowly", "→ pomaleji (more slowly) → nejpomaleji (most slowly)")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun AVLSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun AVLRow(czech: String, english: String, note: String = "") {
    Row(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)) { append(czech) }
                withStyle(SpanStyle(color = Color.DarkGray)) { append("  —  $english") }
                if (note.isNotEmpty()) {
                    withStyle(SpanStyle(color = Color.Gray, fontStyle = FontStyle.Italic)) { append("  $note") }
                }
            },
            fontSize = 14.sp
        )
    }
}
