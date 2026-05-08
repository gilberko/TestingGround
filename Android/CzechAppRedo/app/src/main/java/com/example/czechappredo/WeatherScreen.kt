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
fun WeatherScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
            WESection("Seasons (Roční období)")
            WERow("léto", "summer", "n.")
            WERow("jaro", "spring", "n.")
            WERow("zima", "winter / cold", "f. — same word for both: Je zima. = It's cold. / It's winter. (context distinguishes)")
            WERow("podzim", "autumn / fall", "m.")

            WESection("Weather Conditions (Počasí)")
            WERow("počasí", "weather", "n.")
            WERow("slunečno", "it's sunny", "predicative form — used with je: Je slunečno.")
            WERow("oblačno", "it's cloudy", "predicative form — Je oblačno.")
            WERow("větrno / větrný den", "it's windy / a windy day", "větrno = predicative (Je větrno.); větrný = adjective (větrný den = windy day)")
            WERow("mrzne", "it's freezing", "3rd sg. of mrznout — used impersonally: Venku mrzne. = It's freezing outside.")
            WERow("sníh", "snow", "m. — sněží = it's snowing (3rd sg. of sněžit)")
            WERow("déšť", "rain", "m. — prší = it's raining (3rd sg. of pršet)")
            WERow("bouřka", "thunderstorm", "f.")
            WERow("mlha", "fog", "f.")
            WERow("duha", "rainbow", "f.")

            WESection("Temperature (Teplota)")
            WERow("teplota", "temperature", "f.")
            WERow("Jaká je teplota?", "What is the temperature?")
            WERow("Jaká je venku teplota?", "What is the temperature outside?")
            WERow("Dnes máme 28 stupňů.", "Today we have 28 degrees.", "colloquial but standard; Je 28 stupňů. also correct")
            WERow("Dnes máme minus pět stupňů.", "Today we have -5 degrees.")
            WERow("Je teplo.", "It's warm.")
            WERow("Je zima.", "It's cold.", "Venku je zima. = It's cold outside.")
            WERow("Je horko.", "It's hot.")
            WERow("silný vítr", "strong wind", "silný = strong; vítr m.")
            WERow("stupeň", "degree (temperature)", "m. — pl. stupně; genitive plural: stupňů — used for numbers: 28 stupňů")

            WESection("Weather Adjectives")
            WERow("teplý / teplá / teplé", "warm", "teplé počasí = warm weather")
            WERow("studený / studená / studené", "cold", "studený vítr = cold wind")
            WERow("horký / horká / horké", "hot", "horké léto = hot summer")
            WERow("mokrý / mokrá / mokré", "wet")
            WERow("suchý / suchá / suché", "dry", "suché léto = dry summer")
            WERow("zamračený / zamračená / zamračené", "overcast / cloudy", "zamračená obloha = overcast sky")
            WERow("slunečný / slunečná / slunečné", "sunny", "slunečný den = sunny day")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun WESection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun WERow(czech: String, english: String, note: String = "") {
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
