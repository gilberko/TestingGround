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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerbsOfMovementScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Verbs Of Movement", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Jít vs Jet
            VMSection("Jít vs. Jet — The Key Difference")
            VMNote("jít = to go on foot / to walk")
            VMNote("jet = to go by vehicle (car, bus, train, bike, etc.)")
            VMNote("Use jít when you are walking. Use jet when you are riding.")

            // Jít
            VMSection("Jít — To Go (On Foot)")
            VMNote("Irregular verb — the conjugation does not follow any standard type.")
            VMTable(
                verb = "jít",
                label = "to go (on foot) — irregular",
                rows = listOf(
                    "já" to "jdu",
                    "ty" to "jdeš",
                    "on / ona / to" to "jde",
                    "my" to "jdeme",
                    "vy" to "jdete",
                    "oni" to "jdou"
                )
            )

            // Jet
            VMSection("Jet — To Go (By Vehicle)")
            VMNote("Also irregular. Note how the stem jed- appears in all forms.")
            VMTable(
                verb = "jet",
                label = "to go (by vehicle) — irregular",
                rows = listOf(
                    "já" to "jedu",
                    "ty" to "jedeš",
                    "on / ona / to" to "jede",
                    "my" to "jedeme",
                    "vy" to "jedete",
                    "oni" to "jedou"
                )
            )

            // Habitual forms
            VMSection("Habitual Forms — Chodit & Jezdit")
            VMNote("jít / jet describe a single trip happening now or in the near future.")
            VMNote("chodit / jezdit describe a regular or habitual action (\"I go to school every day\").")
            VMTable(
                verb = "chodit",
                label = "to go (on foot) regularly — Type 2 (-it)",
                rows = listOf(
                    "já" to "chodím",
                    "ty" to "chodíš",
                    "on / ona / to" to "chodí",
                    "my" to "chodíme",
                    "vy" to "chodíte",
                    "oni" to "chodí"
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            VMTable(
                verb = "jezdit",
                label = "to go (by vehicle) regularly — Type 2 (-it)",
                rows = listOf(
                    "já" to "jezdím",
                    "ty" to "jezdíš",
                    "on / ona / to" to "jezdí",
                    "my" to "jezdíme",
                    "vy" to "jezdíte",
                    "oni" to "jezdí"
                )
            )

            // Prepositions with destinations
            VMSection("With Destinations — Prepositions")
            VMNote("do + Genitive — going into or to an enclosed place:")
            VMNote("  Jdu do školy.  —  I'm going to school.")
            VMNote("  Jedu do Prahy.  —  I'm going to Prague (by vehicle).")
            VMNote("na + Accusative — going to an event or open space:")
            VMNote("  Jdu na trh.  —  I'm going to the market.")
            VMNote("  Jedu na výlet.  —  I'm going on a trip.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun VMSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun VMNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun VMTable(verb: String, label: String, rows: List<Pair<String, String>>) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = verb, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    Text(
        text = label,
        fontSize = 13.sp,
        color = Color.Gray,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Pronoun", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
        Text(text = "Form", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(0.8f))
    }
    HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
    rows.forEach { (pronoun, form) ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp)
        ) {
            Text(text = pronoun, fontSize = 14.sp, modifier = Modifier.weight(1f))
            Text(text = form, fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.8f))
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}
