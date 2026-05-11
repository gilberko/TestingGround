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
fun FutureTenseScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Future Tense", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            FTSection("Two Ways to Express Future")
            FTNote("Czech has two ways to talk about the future:")
            FTNote("1. Use a perfective verb conjugated in the present tense. Because perfective verbs have no ongoing present meaning, their present-tense forms are always interpreted as future.")
            FTNote("2. Use budu / budeš / bude... + an infinitive. This describes an ongoing or habitual future action.")

            FTSection("Future of Být — budu / budeš / bude...")
            FTNote("The verb být has a dedicated future form. It is also the helper verb used in the budu + infinitive construction.")
            FTTable(
                verb = "být — future",
                label = "irregular",
                rows = listOf(
                    "já" to "budu",
                    "ty" to "budeš",
                    "on" to "bude",
                    "ona" to "bude",
                    "ono" to "bude",
                    "my" to "budeme",
                    "vy" to "budete",
                    "oni" to "budou",
                    "ony" to "budou"
                )
            )
            FTNote("on / ona / ono all share bude. oni / ony both use budou.")

            FTSection("Perfective Verb in Present = Future")
            FTNote("Perfective verbs describe completed, one-time actions. Their present-tense conjugation always refers to the future.")
            FTNote("Udělám to.  —  I will do it.  (udělat, perfective)")
            FTNote("Přijdeš?  —  Will you come?  (přijít, perfective)")
            FTNote("Napíše dopis.  —  She will write a letter.  (napsat, perfective)")
            FTNote("Přečtu tu knihu.  —  I will read that book.  (přečíst, perfective)")

            FTSection("budu + Infinitive")
            FTNote("Used with imperfective verbs. Describes a future action that is ongoing, repeated, or without a specific endpoint.")
            FTNote("Budu dělat domácí úkoly.  —  I will be doing homework.")
            FTNote("Budeme pracovat celý den.  —  We will be working all day.")
            FTNote("Co budeš jíst?  —  What will you be eating?")
            FTNote("Bude pršet.  —  It will be raining.")

            FTSection("Key Distinction")
            FTNote("Udělám domácí úkoly.  —  I will do (and finish) the homework.  [perfective — completed action]")
            FTNote("Budu dělat domácí úkoly.  —  I will be doing homework.  [imperfective — ongoing, no completion implied]")
            FTNote("Both are correct. The choice depends on whether the focus is on completing the action or simply that it will be happening.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun FTSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun FTNote(text: String) {
    Text(text = text, fontSize = 14.sp, color = Color.DarkGray, modifier = Modifier.padding(vertical = 4.dp))
}

@Composable
private fun FTTable(verb: String, label: String, rows: List<Pair<String, String>>) {
    Spacer(modifier = Modifier.height(8.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 2.dp, end = 2.dp, bottom = 4.dp)
    ) {
        Text(text = verb, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Text(text = label, fontSize = 13.sp, fontStyle = FontStyle.Italic, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Text("Subject", modifier = Modifier.weight(1f), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Text("Form", modifier = Modifier.weight(0.8f), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
        }
        HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
        rows.forEach { (subject, form) ->
            Row(modifier = Modifier.padding(vertical = 3.dp)) {
                Text(subject, modifier = Modifier.weight(1f), fontSize = 14.sp, color = Color.DarkGray)
                Text(form, modifier = Modifier.weight(0.8f), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}
