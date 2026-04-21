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
fun CasesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cases", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // ── Introduction ─────────────────────────────────────────────
            CaseSectionHeader("What Are Cases?")
            CaseNote("Czech has 7 cases. A case is a role that a noun plays in a sentence — subject, object, owner, location, and so on. The noun's ending changes to signal that role. Adjectives and pronouns also change to agree with the noun they describe.")
            CaseNote("Each case answers a question. Learning that question helps you know which ending to use.")

            // ── The 7 Cases ──────────────────────────────────────────────
            CaseSectionHeader("The 7 Cases")

            CaseBlock(
                number = "1",
                caseName = "Nominativ  (Nominative)",
                usage = "The subject of the sentence — who or what is doing the action. Also the dictionary form of a word.",
                example = "Ten muž jde.",
                translation = "The man is going."
            )
            CaseBlock(
                number = "2",
                caseName = "Genitiv  (Genitive)",
                usage = "Possession (\"of\"), absence, negation, and quantity. Also used after many common prepositions.",
                prepositions = "do, z, od, bez, u, pro, podle, kolem, vedle",
                example = "Jsem bez vody.",
                translation = "I am without water."
            )
            CaseBlock(
                number = "3",
                caseName = "Dativ  (Dative)",
                usage = "The indirect object — to/for whom something is given or done.",
                prepositions = "k / ke, kvůli, díky, naproti",
                example = "Dám to Janovi.",
                translation = "I'll give it to Jan."
            )
            CaseBlock(
                number = "4",
                caseName = "Akuzativ  (Accusative)",
                usage = "The direct object — what is directly affected by the action. For masculine animate nouns the Accusative equals the Genitive; for inanimate nouns it equals the Nominative.",
                prepositions = "na, do, za, pro, přes, skrz",
                example = "Vidím Jana.",
                translation = "I see Jan."
            )
            CaseBlock(
                number = "5",
                caseName = "Vokativ  (Vocative)",
                usage = "Direct address — calling or speaking to someone by name or title.",
                example = "Jene!  /  Pane!",
                translation = "Hey Jan!  /  Sir!"
            )
            CaseBlock(
                number = "6",
                caseName = "Lokál  (Locative)",
                usage = "Location or the topic of discussion. Always used with a preposition — it never appears alone.",
                prepositions = "v / ve, na, o, po, při",
                example = "Jsem v Praze.",
                translation = "I am in Prague."
            )
            CaseBlock(
                number = "7",
                caseName = "Instrumentál  (Instrumental)",
                usage = "Means (\"by / with\"), accompaniment, and travelling by vehicle. Also used after certain prepositions.",
                prepositions = "s / se, pod, nad, před, za, mezi",
                example = "Jdu autobusem.",
                translation = "I go by bus."
            )

            // ── Declension Tables ────────────────────────────────────────
            CaseSectionHeader("Declension Tables  (Singular, Hard Patterns)")
            CaseNote("These are the most common patterns. Czech also has soft and consonant-stem patterns, and plural forms — those will be covered separately.")

            Spacer(modifier = Modifier.height(12.dp))
            DeclensionTable(
                noun = "muž  (man)",
                label = "Masculine animate, hard",
                rows = listOf(
                    "1. Nominativ" to "muž",
                    "2. Genitiv" to "muže",
                    "3. Dativ" to "muži",
                    "4. Akuzativ" to "muže",
                    "5. Vokativ" to "muži",
                    "6. Lokál" to "muži",
                    "7. Instrumentál" to "mužem"
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            DeclensionTable(
                noun = "žena  (woman)",
                label = "Feminine, hard  (-a)",
                rows = listOf(
                    "1. Nominativ" to "žena",
                    "2. Genitiv" to "ženy",
                    "3. Dativ" to "ženě",
                    "4. Akuzativ" to "ženu",
                    "5. Vokativ" to "ženo",
                    "6. Lokál" to "ženě",
                    "7. Instrumentál" to "ženou"
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            DeclensionTable(
                noun = "město  (city)",
                label = "Neuter, hard  (-o)",
                rows = listOf(
                    "1. Nominativ" to "město",
                    "2. Genitiv" to "města",
                    "3. Dativ" to "městu",
                    "4. Akuzativ" to "město",
                    "5. Vokativ" to "město",
                    "6. Lokál" to "městě",
                    "7. Instrumentál" to "městem"
                )
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CaseSectionHeader(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun CaseNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun CaseBlock(
    number: String,
    caseName: String,
    usage: String,
    prepositions: String = "",
    example: String,
    translation: String
) {
    Spacer(modifier = Modifier.height(12.dp))
    Text(text = "$number.  $caseName", fontSize = 17.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    Spacer(modifier = Modifier.height(3.dp))
    Text(text = usage, fontSize = 14.sp, color = Color.DarkGray)
    if (prepositions.isNotEmpty()) {
        Text(
            text = "After: $prepositions",
            fontSize = 14.sp,
            fontStyle = FontStyle.Italic,
            color = Color.DarkGray,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
    Text(
        text = example,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier.padding(top = 5.dp)
    )
    Text(text = translation, fontSize = 14.sp, fontStyle = FontStyle.Italic, color = Color.Gray)
}

@Composable
private fun DeclensionTable(noun: String, label: String, rows: List<Pair<String, String>>) {
    Text(text = noun, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    Text(text = label, fontSize = 13.sp, color = Color.Gray, fontStyle = FontStyle.Italic)
    Spacer(modifier = Modifier.height(4.dp))
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Case",
            modifier = Modifier.weight(1f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = ButtonBlue
        )
        Text(
            text = "Form",
            modifier = Modifier.weight(0.6f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = ButtonBlue
        )
    }
    HorizontalDivider(modifier = Modifier.padding(vertical = 3.dp))
    rows.forEach { (caseName, form) ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp)
        ) {
            Text(text = caseName, modifier = Modifier.weight(1f), fontSize = 15.sp, color = Color.DarkGray)
            Text(
                text = form,
                modifier = Modifier.weight(0.6f),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}
