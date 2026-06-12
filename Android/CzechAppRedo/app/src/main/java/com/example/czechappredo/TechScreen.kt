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
fun TechScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tech", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            TESection("The Workplace")
            TERow("schůzka", "meeting / appointment", "f.; also used for a date or any scheduled get-together")
            TERow("kolega / kolegyně", "colleague (m./f.)")
            TERow("team leader", "team leader", "also: vedoucí týmu — leader of the team")
            TERow("zákazník / zákaznice", "customer (m./f.)")
            TERow("urgentní / naléhavý", "urgent", "urgentní = loanword; naléhavý = native Czech equivalent")

            TESection("Roles & Titles")
            TERow("vývojář / vývojářka", "developer (m./f.)")
            TERow("programátor / programátorka", "programmer (m./f.)")
            TERow("softwarový inženýr / softwarová inženýrka", "software engineer (m./f.)")
            TERow("softwarový architekt / softwarová architektka", "software architect (m./f.)")
            TERow("QA", "QA / quality assurance", "used as an abbreviation in Czech IT; also: QA inženýr / QA inženýrka")

            TESection("Communication & Email")
            TERow("email", "email", "pl. emaily; widely used as-is")
            TERow("zpráva", "message", "f.; also means 'news' — context distinguishes")
            TERow("posílat / poslat", "to send (imperf./perf.)")
            TERow("dostávat / dostat", "to receive (imperf./perf.)", "also: přijímat / přijmout — more formal")
            TERow("kontrolovat emaily", "to check emails")

            TESection("Development & Code")
            TERow("programovat", "to program / to code", "imperf.; programuji / programuju")
            TERow("vyvíjet / vyvinout", "to develop (imperf./perf.)", "vyvíjím / vyvinout — used for software and products")
            TERow("psát kód", "to write code")
            TERow("kód", "code", "m.; also: zdrojový kód = source code")
            TERow("modul", "module", "m.")
            TERow("nízkoúrovňový kód", "low-level code")
            TERow("IDE", "IDE", "vývojové prostředí in full Czech; IDE used universally in Czech IT")

            TESection("Build & Tools")
            TERow("kompilátor", "compiler", "m.")
            TERow("kompilovat", "to compile", "imperf.; kompiluji / kompiluju")
            TERow("linker", "linker", "m.; Czech calque sestavovač exists but linker is universally used")
            TERow("linkovat", "to link", "imperf.; linkuji / linkuju")
            TERow("software", "software", "m.; used as-is")
            TERow("hardware", "hardware", "m.; used as-is")

            TESection("Code Review & Collaboration")
            TERow("code review", "code review", "přezkoumání kódu in full Czech; code review used universally")
            TERow("merge request", "merge request", "kept as English in Czech IT; also pull request on GitHub")
            TERow("pull request", "pull request", "kept as English; abbreviated PR")
            TERow("integrace", "integration", "f.")

            TESection("Testing & Bugs")
            TERow("testovat", "to test", "imperf.; testuji / testuju")
            TERow("testování", "testing", "n.; verbal noun")
            TERow("ladit / debugovat", "to debug", "ladit / odladit = Czech; debugovat = loanword widely used in speech")
            TERow("chyba / bug", "bug", "chyba = native Czech (f.); bug = loanword (m.) — both are common in IT")
            TERow("chyba / error", "error", "same word as bug; context determines: compilation error = chyba při kompilaci")
            TERow("oprava / fix", "fix", "oprava = native Czech (f.); fix = loanword used in IT speech")
            TERow("problém", "problem", "m.")

            TESection("Infrastructure")
            TERow("rozhraní", "interface", "n.; interface also used as a loanword")
            TERow("API", "API", "n.; application programming interface")
            TERow("databáze", "database", "f.; one word — not 'data báze'")
            TERow("služba", "service", "f.; service loanword also common in IT context")
            TERow("mikroslužba", "microservice", "f.; one word — not 'mikro služba'; microservice loanword also used")

            TESection("AI")
            TERow("používat AI / využívat AI", "to use AI", "používat = to use; využívat = to make use of / leverage")
            TERow("AI agent", "AI agent", "used as-is in Czech tech; agentní AI is a longer alternative")

            TESection("Devices & State")
            TERow("online", "online", "used as-is; also: připojený = connected")
            TERow("offline", "offline", "used as-is; also: nepřipojený = disconnected")
            TERow("zapnout / zapínat", "to turn on (perf./imperf.)")
            TERow("vypnout / vypínat", "to shut down / turn off (perf./imperf.)")
            TERow("pád / crash", "crash", "pád = native Czech (m.); crash = loanword; pád aplikace = application crash")
            TERow("to spadlo / to crashovalo", "it crashed", "to spadlo = more natural; to crashovalo = informal loanword")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun TESection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun TERow(czech: String, english: String, note: String = "") {
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
