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
fun ConnectingWordsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Connecting Words", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
            CWSection("Basic Connectors")
            CWRow("a", "and")
            CWRow("ale", "but")
            CWRow("nebo", "or")
            CWRow("ani … ani …", "neither … nor …", "see also: Negation screen")

            CWSection("Adding, Explaining & Consequence")
            CWRow("navíc / kromě toho", "moreover / in addition to that")
            CWRow("a proto / proto", "and that's why / therefore")
            CWRow("protože / jelikož", "because", "followed by a verb clause: Jdu domů, protože jsem unavený. = I'm going home because I'm tired.")
            CWRow("totiž", "namely / you see", "adds explanation after the fact — sentence-internal: Je nemocný, totiž. = He's sick, you see.")
            CWRow("tedy", "so / thus", "concluding: Jsi unavený, tedy jdi spát. = You're tired, so go to sleep.")

            CWSection("Contrast")
            CWRow("nicméně / avšak / však", "however", "nicméně and avšak are slightly more formal; však is common in speech")
            CWRow("přestože / i když", "even though / although", "Přestože prší, jdu ven. = Even though it's raining, I'm going out.")

            CWSection("As / Like — jako + nominative")
            CWNote("'jako' (as/like) is followed by the nominative case — the noun takes no special case ending.")
            CWRow("pracovat jako bankéř", "to work as a banker", "bankéř stays nominative after jako")
            CWRow("Chová se jako dítě.", "He behaves like a child.", "dítě stays nominative")
            CWRow("v jako ve slově vlak", "v as in the word vlak", "how Czechs clarify letter spelling; 've' used before the consonant cluster 'sl'")

            CWSection("After — po + locative")
            CWNote("po (after, in a time sense) takes the locative case.")
            CWRow("po jídle", "after the meal", "jídlo → jídle (locative)")
            CWRow("po práci", "after work", "práce → práci (locative)")
            CWRow("po snídani", "after breakfast", "snídaně → snídani (locative)")
            CWRow("po hodině", "after an hour", "hodina → hodině (locative)")

            CWSection("Before — před + instrumental")
            CWNote("před (before, in time) takes the instrumental case.")
            CWRow("před obědem", "before lunch", "oběd → obědem (instrumental)")
            CWRow("před prací", "before work", "práce → prací (instrumental)")
            CWRow("před večeří", "before dinner", "večeře → večeří (instrumental)")
            CWNote("před also means 'in front of' (location) with instrumental: před domem = in front of the house.")

            CWSection("Between … and … — mezi + instrumental")
            CWNote("mezi (between) takes the instrumental case on both nouns.")
            CWRow("mezi pondělím a středou", "between Monday and Wednesday", "pondělí → pondělím; středa → středou")
            CWRow("mezi Prahou a Brnem", "between Prague and Brno", "Praha → Prahou; Brno → Brnem")
            CWRow("mezi námi", "between us", "my → námi (instrumental plural)")

            CWSection("Until — do + genitive / dokud")
            CWNote("do (until / by) takes the genitive case when followed by a noun.")
            CWRow("do pátku", "until Friday / by Friday", "pátek → pátku (genitive)")
            CWRow("do 20:00", "until 20:00")
            CWRow("do konce týdne", "by the end of the week", "konec → konce (genitive)")
            CWRow("dokud + verb", "until (before a verb clause)", "Počkej, dokud nepřijde. = Wait until he comes.")

            CWSection("With — s / se + instrumental")
            CWNote("s/se (with) takes the instrumental case. Use 'se' before consonant clusters.")
            CWRow("s přítelem", "with a friend (male)", "přítel → přítelem")
            CWRow("se mnou", "with me", "já → mnou (irregular instrumental)")
            CWRow("s rodinou", "with the family", "rodina → rodinou")
            CWRow("s kamarádem", "with a friend", "kamarád → kamarádem")

            CWSection("Without — bez + genitive")
            CWNote("bez (without) always takes the genitive case.")
            CWRow("bez mléka", "without milk", "mléko → mléka (genitive)")
            CWRow("bez problémů", "without problems", "problémy → problémů (genitive plural)")
            CWRow("bez cukru", "without sugar", "cukr → cukru (genitive)")
            CWNote("See also: Negation screen for extended bez + genitive examples.")

            CWSection("Note on Old vs. Modern Czech")
            CWNote("A few prepositions shifted case requirements over time. Example: 'mimo' (besides/outside) — archaic Czech used genitive (mimo Prahy), modern Czech uses accusative (mimo Prahu). The connecting words listed above all follow stable modern usage.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CWSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun CWNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun CWRow(czech: String, english: String, note: String = "") {
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
