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
