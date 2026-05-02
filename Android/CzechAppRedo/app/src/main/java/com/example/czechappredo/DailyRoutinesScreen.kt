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
fun DailyRoutinesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daily Routines", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            DRSection("My Day")
            DRRow("Vstávám v 7:00.", "I wake up at 7:00.", "infinitive: vstávat")
            DRRow("Sprchuju se.", "I take a shower.", "infinitive: sprchovat se")
            DRRow("Na snídani jím bagely a salát.", "I eat bagels and salad for breakfast.", "infinitive: jíst")
            DRRow("Procházím se v parku.", "I walk around the park.", "infinitive: procházet se")
            DRRow("Piju kávu.", "I drink coffee.", "infinitive: pít")
            DRRow("Začínám pracovat v 9:00.", "I start working at 9:00.", "infinitive: začínat")
            DRRow("Obědvám ve 13:00.", "I eat lunch at 13:00.", "infinitive: obědvat")
            DRRow("Končím práci v 18:30.", "I finish working at 18:30.", "infinitive: končit")
            DRRow("Večeřím s přáteli v 20:00.", "I eat dinner with friends at 20:00.", "infinitive: večeřet")
            DRRow("Setkávám se s přáteli v kavárně večer.", "I meet friends at a café in the evening.", "infinitive: setkávat se")
            DRRow("Jdu spát v 23:00.", "I go to sleep at 23:00.", "infinitive: jít spát")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun DRSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun DRRow(czech: String, english: String, note: String = "") {
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
