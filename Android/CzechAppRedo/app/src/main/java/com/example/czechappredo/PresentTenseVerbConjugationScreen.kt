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
fun PresentTenseVerbConjugationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Present Tense Verb Conjugation", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Personal Pronouns
            PTSection("Personal Pronouns")
            PTNote("Czech has the following personal pronouns:")
            PTNote(
                "já — I\n" +
                "ty — you (singular, informal)\n" +
                "on — he\n" +
                "ona — she\n" +
                "to — it (neuter, everyday usage)\n" +
                "my — we\n" +
                "vy — you (plural, or singular formal)\n" +
                "oni — they"
            )
            PTNote("Note: to is technically a demonstrative pronoun (this/that), but it is overwhelmingly used as the neuter subject \"it\" in everyday Czech. The formal neuter personal pronoun ono does exist but is rarely heard in modern speech. The verb form is identical for on, ona, to, and ono.")

            // Být
            PTSection("Být — To Be (Irregular)")
            PTNote("být is highly irregular and must be memorised.")
            VerbTable(
                verb = "být",
                label = "to be — irregular",
                rows = listOf(
                    "já" to "jsem",
                    "ty" to "jsi",
                    "on / ona / to" to "je",
                    "my" to "jsme",
                    "vy" to "jste",
                    "oni" to "jsou"
                )
            )

            // Type 1
            PTSection("Type 1 — Verbs Ending in -at (já → -ám)")
            PTNote("The stem gains a long mark on the final vowel in singular forms.")
            VerbTable(
                verb = "dělat",
                label = "to do / to make",
                rows = listOf(
                    "já" to "dělám",
                    "ty" to "děláš",
                    "on / ona / to" to "dělá",
                    "my" to "děláme",
                    "vy" to "děláte",
                    "oni" to "dělají"
                )
            )

            // Type 2
            PTSection("Type 2 — Verbs Ending in -it (já → -ím)")
            PTNote("Common for many everyday action verbs.")
            VerbTable(
                verb = "mluvit",
                label = "to speak",
                rows = listOf(
                    "já" to "mluvím",
                    "ty" to "mluvíš",
                    "on / ona / to" to "mluví",
                    "my" to "mluvíme",
                    "vy" to "mluvíte",
                    "oni" to "mluví"
                )
            )

            // Type 3
            PTSection("Type 3 — Verbs Ending in -ovat (já → -uji / -uju)")
            PTNote("Two accepted forms exist for já and oni.")
            VerbTable(
                verb = "pracovat",
                label = "to work",
                rows = listOf(
                    "já" to "pracuji / pracuju",
                    "ty" to "pracuješ",
                    "on / ona / to" to "pracuje",
                    "my" to "pracujeme",
                    "vy" to "pracujete",
                    "oni" to "pracují / pracujou"
                )
            )
            PTNote("pracuji / pracují — formal and written Czech\npracuju / pracujou — colloquial and spoken Czech\nBoth are correct and widely understood.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PTSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun PTNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun VerbTable(verb: String, label: String, rows: List<Pair<String, String>>) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = verb, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    Text(
        text = label,
        fontSize = 13.sp,
        color = Color.Gray,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Pronoun",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "Form",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(0.8f)
        )
    }
    HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
    rows.forEach { (pronoun, form) ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp)
        ) {
            Text(text = pronoun, fontSize = 14.sp, modifier = Modifier.weight(1f))
            Text(text = form, fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.8f))
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}
