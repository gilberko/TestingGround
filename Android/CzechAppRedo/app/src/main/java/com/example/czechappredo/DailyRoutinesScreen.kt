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
            DRRow("Snídám bagely a salát.", "I have breakfast — bagels and salad.", "infinitive: snídat — dedicated verb for 'to have breakfast' (like obědvat for lunch, večeřet for dinner)")
            DRRow("Procházím se v parku.", "I walk around the park.", "infinitive: procházet se")
            DRRow("Piju kávu.", "I drink coffee.", "infinitive: pít")
            DRRow("Začínám pracovat v 9:00.", "I start working at 9:00.", "infinitive: začínat")
            DRRow("Obědvám ve 13:00.", "I eat lunch at 13:00.", "infinitive: obědvat — dedicated verb for 'to have lunch'")
            DRRow("Končím práci v 18:30.", "I finish working at 18:30.", "infinitive: končit")
            DRRow("Večeřím s přáteli v 20:00.", "I eat dinner with friends at 20:00.", "infinitive: večeřet — dedicated verb for 'to have dinner'")
            DRRow("Setkávám se s přáteli v kavárně večer.", "I meet friends at a café in the evening.", "infinitive: setkávat se")
            DRRow("Občas hraju Monopoly nebo karty s přáteli.", "I sometimes play Monopoly or cards with friends.", "infinitive: hrát — irregular -át verb; hraju / hraješ / hraje / hrajeme / hrajete / hrají")
            DRRow("Občas hraju fotbal s přáteli.", "I sometimes play soccer with friends.", "fotbal = soccer/football; same hrát conjugation")
            DRRow("Dívám se na film s přáteli.", "I watch a movie with friends.", "infinitive: dívat se — reflexive, se always required; na + Accusative; dívám / díváš / dívá / díváme / díváte / dívají se")
            DRNote("s přáteli (instr. pl. of přítel) or s kamarády (instr. pl. of kamarád). Kamarád is strictly platonic and more casual/everyday; přítel can also mean romantic partner depending on context. See People & Family.")
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
private fun DRNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(start = 4.dp, top = 2.dp, bottom = 4.dp)
    )
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
