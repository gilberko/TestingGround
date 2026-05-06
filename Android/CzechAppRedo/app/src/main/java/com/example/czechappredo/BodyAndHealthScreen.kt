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
fun BodyAndHealthScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Body and Health", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // ── Body Parts ────────────────────────────────────────────────
            BASection("Body Parts")
            BARow("oko", "eye", "n. — oči (pl., irregular)")
            BARow("ucho", "ear", "n. — uši (pl., irregular)")
            BARow("krk", "neck / throat", "m. — covers both colloquially")
            BARow("hrdlo", "throat", "n. — more precise term")
            BARow("ústa", "mouth", "pl. — always plural in Czech")
            BARow("hlava", "head", "f.")
            BARow("zub", "tooth", "m. — zuby (pl.) = teeth")
            BARow("nos", "nose", "m.")
            BARow("vlasy", "hair", "pl. — vlas (m.) = one hair (rarely used)")
            BARow("žaludek", "stomach (the organ)", "m. — břicho (n.) = belly / tummy")
            BARow("záda", "back", "pl. — always plural in Czech")
            BARow("brada", "chin", "f.")
            BARow("loket", "elbow", "m.")
            BARow("koleno", "knee", "n.")
            BARow("noha", "leg / foot", "f. — chodidlo (n.) = foot (more precise)")
            BARow("paže / ruka", "arm", "f. / f.")
            BARow("ruka", "hand", "f.")
            BARow("dlaň", "palm", "f.")
            BARow("hrudník", "chest", "m.")

            // ── Pain and the Doctor ───────────────────────────────────────
            BASection("Pain and the Doctor")
            BARow("bolest", "pain / ache", "f.")
            BARow("Co vás bolí?", "What hurts you?", "formal — doctor's question")
            BARow("Kde vás bolí?", "Where does it hurt?")
            BARow("Máte teplotu?", "Do you have a fever?")

            // ── The Verb bolet ────────────────────────────────────────────
            BASection("The Verb bolet — \"Bolí mě záda\"")
            BANote("\"bolí\" is the 3rd person singular of the verb \"bolet\" (to hurt/ache). The pattern is: bolí + mě (accusative of \"I\") + body part (nominative). The verb stays singular even when the body part is plural.")
            BARow("Bolí mě žaludek.", "My stomach hurts.", "bolí + mě + žaludek (nominative)")
            BARow("Bolí mě záda.", "My back hurts.", "záda is plural, but bolí stays singular")
            BARow("Bolí mě zuby.", "My teeth hurt.")

            // ── I have a pain in... ───────────────────────────────────────
            BASection("Saying \"I have a pain in...\"")
            BANote("Use \"Mám bolest\" + preposition. \"v\" + locative means \"in\"; genitive expresses type (toothache = pain of teeth).")
            BARow("Mám bolest v žaludku.", "I have a pain in my stomach.", "v + locative of žaludek → žaludku")
            BARow("Mám bolest v zádech.", "I have a pain in my back.", "v + locative of záda → zádech")
            BARow("Mám bolest zubů.", "I have a toothache.", "genitive of zuby → zubů")

            // ── Common Symptoms ───────────────────────────────────────────
            BASection("Common Symptoms")
            BARow("Mám kašel. / Kašlu.", "I have a cough. / I am coughing.")
            BARow("Mám teplotu. / Mám horečku.", "I have a fever / temperature.")
            BARow("Mám 38,5 stupňů Celsia.", "I have 38.5 degrees Celsius.")
            BARow("Mám rýmu.", "I have a runny nose.", "rýma = runny nose in Czech")
            BARow("Mám ucpaný nos.", "I have a blocked nose.")

            // ── Doctor's Advice ───────────────────────────────────────────
            BASection("Doctor's Advice")
            BARow("Musíte zůstat v posteli.", "You have to stay in bed.")
            BARow("Musíte odpočívat.", "You have to rest.")
            BARow("Musíte brát tento lék.", "You have to take this medicine.")
            BANote("Times per day — the suffix \"krát\" means \"times\". For 1 use the irregular form jednou. For 2 and above: number + krát.")
            BARow("jednou denně", "once a day", "jednou is irregular — not jednokrát in everyday speech")
            BARow("dvakrát denně", "twice a day", "dva + krát")
            BARow("třikrát denně", "three times a day", "tři + krát")
            BARow("pětkrát denně", "five times a day", "pět + krát")

            // ── Medical Vocabulary ────────────────────────────────────────
            BASection("Medical Vocabulary")
            BARow("lékárna", "pharmacy", "f.")
            BARow("drogerie", "drugstore", "f. — NOTE: sells cosmetics and hygiene items, NOT prescription medicine; different from the English \"drugstore\"")
            BARow("lékař / lékařka", "doctor (formal)", "m. / f.")
            BARow("doktor / doktorka", "doctor (informal)", "m. / f. — colloquial / everyday usage")
            BARow("lékárník / lékárnice", "pharmacist", "m. / f.")
            BARow("recept", "prescription", "m. — NOTE: same word as \"recipe\"!")
            BARow("Tady je recept.", "Here is a prescription.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun BASection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun BANote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun BARow(czech: String, english: String, note: String = "") {
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
