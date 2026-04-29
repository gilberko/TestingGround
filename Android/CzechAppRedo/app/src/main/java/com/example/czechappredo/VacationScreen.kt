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
fun VacationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Vacation", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            VASection("Travel Verbs")
            VARow("létat", "to fly", "imperfective — repeated or habitual; Létám každý rok. = I fly every year.")
            VARow("letět", "to fly", "directional — one specific trip; Letím do Prahy. = I am flying to Prague.")
            VARow("cestovat", "to travel", "Type 3 -ovat verb")
            VARow("plavat", "to swim")
            VARow("odpočívat", "to rest / to relax")
            VARow("objednat si", "to order (a taxi, food, etc.)", "perfective — Objednal jsem si taxík. = I ordered a taxi.")
            VARow("rezervovat", "to book / to reserve", "Type 3; Chci rezervovat pokoj. = I want to book a room.")
            VARow("udělat rezervaci", "to make a reservation", "lit. \"to make a reservation\"; udělat = to do / make (perfective)")

            VASection("Documents & Tickets")
            VARow("pas", "passport", "m.")
            VARow("vízum", "visa", "n. — travel visa")
            VARow("letenka", "plane ticket", "f.")
            VARow("rezervace", "reservation / booking", "f.")

            VASection("Transport")
            VARow("letadlo", "plane", "n.")
            VARow("letiště", "airport", "n.")
            VARow("vlak", "train", "m.")
            VARow("autobus", "bus", "m.")
            VARow("taxi / taxík", "taxi", "taxík is the colloquial diminutive — very common in spoken Czech")
            VARow("loď", "boat / ship", "f.")

            VASection("Accommodation")
            VARow("hotel", "hotel", "m.")
            VARow("pokoj", "room", "m. — hotel room or bedroom")
            VARow("recepce", "reception / front desk", "f.")
            VARow("host", "guest", "m. — plural: hosté (animate masc. plural)")
            VARow("dovolená", "vacation / holiday", "f. — jít na dovolenou = to go on vacation (Accusative: dovolenou)")
            VARow("restaurace", "restaurant", "f.")

            VASection("Beach & Pool")
            VARow("pláž", "beach", "f.")
            VARow("písek", "sand", "m.")
            VARow("plavky", "swimsuit", "always plural in Czech — like trousers in English")
            VARow("sandály", "sandals", "always plural")
            VARow("bazén", "swimming pool", "m.")
            VARow("plavání", "swimming (the activity)", "n.")

            VASection("Luggage & Gear")
            VARow("batoh", "backpack", "m.")
            VARow("kufr", "suitcase", "m.")
            VARow("zavazadlo", "luggage / baggage", "n. — plural: zavazadla")
            VARow("mapa", "map", "f.")

            VASection("Attractions & Activities")
            VARow("atrakce", "attraction", "f.")
            VARow("zábavní park", "amusement park")
            VARow("aquapark / vodní park", "water park", "both terms used; aquapark is more common")
            VARow("horská dráha", "roller coaster", "lit. \"mountain railway\"")
            VARow("tobogán", "water slide", "m. — plural: tobogány")
            VARow("ruské kolo", "Ferris wheel", "lit. \"Russian wheel\"")
            VARow("park", "park", "m.")
            VARow("turista / turistka", "tourist", "m. / f.")

            VASection("Planning")
            VARow("plány", "plans", "pl. of plán (m.) — Máte plány? = Do you have plans? (formal) / Máš plány? = informal")
            VARow("itinerář", "itinerary", "m.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun VASection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun VARow(czech: String, english: String, note: String = "") {
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
