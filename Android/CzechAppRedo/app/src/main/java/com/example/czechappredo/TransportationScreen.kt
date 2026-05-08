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
fun TransportationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Transportation", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            TRSection("Road Vehicles")
            TRRow("auto / osobní auto", "car", "n. — auto is the everyday word; osobní auto is the full term")
            TRRow("nákladní auto / kamion", "truck", "n. / m. — nákladní auto is general; kamion is a large lorry/semi")
            TRRow("dodávka", "van", "f.")
            TRRow("autobus", "bus", "m.")
            TRRow("taxi", "taxi", "n. — indeclinable")
            TRRow("motorka / motocykl", "motorcycle", "f. / m. — motorka is colloquial, motocykl is formal")
            TRRow("kolo / jízdní kolo", "bicycle", "n. — kolo is colloquial, jízdní kolo is formal")
            TRRow("koloběžka", "kick scooter", "f.")
            TRRow("skútr", "motorized scooter", "m.")
            TRRow("skateboard", "skateboard", "m. — borrowed word")

            TRSection("Rail & Air")
            TRRow("vlak", "train", "m.")
            TRRow("letadlo", "airplane", "n.")
            TRRow("kyvadlový spoj", "shuttle", "m. — transport shuttle; space shuttle = raketoplán")

            TRSection("Water")
            TRRow("loď", "ship", "f.")
            TRRow("člun", "boat", "m.")
            TRRow("trajekt / přívoz", "ferry", "m. / m. — trajekt is a large vehicle ferry; přívoz is a small river crossing")

            TRSection("City Transit")
            TRRow("tramvaj", "tram", "f.")
            TRRow("metro", "metro / subway", "n.")
            TRRow("trolejbus", "trolleybus", "m.")

            TRSection("Stations & Travel Info")
            TRRow("nádraží / vlakové nádraží", "train station", "n.")
            TRRow("autobusová zastávka", "bus stop", "f.")
            TRRow("autobusové nádraží", "bus station", "n.")
            TRRow("letiště", "airport", "n.")
            TRRow("nástupiště", "platform / track", "n. — the boarding area at a train or metro station")
            TRRow("jízdní řád", "timetable", "m. — jízdní = travel/riding, řád = schedule")
            TRRow("přestupní uzel", "transit hub", "m. — a station where multiple lines connect; přestupovat = to transfer")

            TRSection("Documents")
            TRRow("řidičský průkaz", "driver's license", "m. — průkaz = card/document; řidičský = driving")
            TRRow("technický průkaz (malý TP)", "vehicle registration card", "m. — the small document kept in the car at all times")
            TRRow("osvědčení o technické způsobilosti (velký TP)", "roadworthiness certificate", "n. — the larger technical document")
            TRNote("Both are colloquially called 'technický průkaz' or 'TP'; malý vs. velký distinguishes them in formal usage.")

            TRSection("Tickets & Seating")
            TRRow("jízdenka", "ticket (general)", "f. — the standard word for any transit ticket; used for bus, tram, metro")
            TRRow("jízdenka na vlak / vlakový lístek", "train ticket", "f. / m. — jízdenka na vlak is the fuller form; lístek is the colloquial everyday word")
            TRRow("jízdenka na autobus", "bus ticket", "f.")
            TRRow("letenka", "plane ticket", "f. — a distinct word, not jízdenka; always used for air travel")
            TRRow("Kde si mohu koupit jízdenku na vlak?", "Where can I buy a ticket for the train?", "phrase — si mohu = may I / can I (polite); koupit = to buy (perfective)")
            TRRow("Kde si mohu koupit jízdenku na autobus?", "Where can I buy a ticket for the bus?", "phrase")
            TRRow("sedadlo", "seat", "n.")
            TRRow("přední sedadlo", "front seat", "n. — přední = front/forward")
            TRRow("zadní sedadlo", "back seat", "n. — zadní = rear/back")

            TRSection("Car Parts")
            TRRow("pneumatika / guma", "tire", "f. / f. — pneumatika is standard; guma is the very common colloquial form")
            TRRow("defekt / prasklá pneumatika", "flat tire", "m. / f. — defekt is the everyday term (mám defekt = I have a flat); prasklá pneumatika is descriptive")
            TRRow("volant", "steering wheel", "m. — borrowed from French; fully naturalized in Czech")
            TRRow("brzdy", "brakes", "f.pl. — plural is standard in everyday speech; singular brzda exists but is rare in context")
            TRRow("ruční brzda", "hand brake / parking brake", "f. — ruční = hand/manual")
            TRRow("bezpečnostní pás / pás", "safety belt / seatbelt", "m. — bezpečnostní pás is the full term; pás alone is widely understood in context")
            TRRow("rezerva / rezervní kolo", "spare tire", "f. / n. — rezerva is the colloquial shorthand; rezervní kolo is explicit")
            TRRow("motor", "engine", "m.")
            TRRow("kufr", "trunk (car boot)", "m. — note: kufr also means 'suitcase'; context disambiguates")

            TRSection("Fuel & Transmission")
            TRRow("čerpací stanice / benzínka", "gas station / petrol station", "f. / f. — čerpací stanice is the formal/signage term; benzínka is the very common colloquial word")
            TRRow("benzín", "gasoline / petrol", "m. — the standard word; natural 95 is the most common grade at Czech pumps")
            TRRow("nafta", "diesel", "f. — a distinct word; do not confuse with benzín when fueling")
            TRRow("manuální převodovka / manuál", "manual transmission", "f. / m. — převodovka is the full term; manuál is the spoken shorthand")
            TRRow("automatická převodovka / automat", "automatic transmission", "f. / m. — automat is the very common spoken form")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun TRSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun TRRow(czech: String, english: String, note: String = "") {
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
private fun TRNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
