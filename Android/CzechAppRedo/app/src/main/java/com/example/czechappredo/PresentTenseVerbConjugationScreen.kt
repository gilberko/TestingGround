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
                "oni — they (group with at least one man, or masculine animate nouns)\n" +
                "ony — they (all-women/girls group; all-feminine nouns; neuter nouns; masculine inanimate nouns)"
            )
            PTNote("Note: to is technically a demonstrative pronoun (this/that), but it is overwhelmingly used as the neuter subject \"it\" in everyday Czech. The formal neuter personal pronoun ono does exist but is rarely heard in modern speech. The verb form is identical for on, ona, to, and ono.\n\noni vs ony: In the present tense, oni and ony always take the same verb form, so they are shown together in the tables below. The distinction matters most in the past tense, where the verb must agree in gender: oni dělali (men or mixed group did) but ony dělaly (women-only or inanimate group did).")

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
                    "oni / ony" to "jsou"
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
                    "oni / ony" to "dělají"
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
                    "oni / ony" to "mluví"
                )
            )

            // Type 3
            PTSection("Type 3 — Verbs Ending in -ovat (já → -uji / -uju)")
            PTNote("Two accepted forms exist for já and oni / ony.")
            VerbTable(
                verb = "pracovat",
                label = "to work",
                rows = listOf(
                    "já" to "pracuji / pracuju",
                    "ty" to "pracuješ",
                    "on / ona / to" to "pracuje",
                    "my" to "pracujeme",
                    "vy" to "pracujete",
                    "oni / ony" to "pracují / pracujou"
                )
            )
            PTNote("pracuji / pracují — formal and written Czech\npracuju / pracujou — colloquial and spoken Czech\nBoth are correct and widely understood.")

            // Type 2b
            PTSection("Type 2b — Verbs Ending in -et / -ět (also → -ím)")
            PTNote("Most -et and -ět infinitives conjugate exactly like -it verbs (Type 2), taking -ím in the já form.")
            VerbTable(
                verb = "sedět",
                label = "to sit",
                rows = listOf(
                    "já" to "sedím",
                    "ty" to "sedíš",
                    "on / ona / to" to "sedí",
                    "my" to "sedíme",
                    "vy" to "sedíte",
                    "oni / ony" to "sedí"
                )
            )
            VerbTable(
                verb = "vidět",
                label = "to see",
                rows = listOf(
                    "já" to "vidím",
                    "ty" to "vidíš",
                    "on / ona / to" to "vidí",
                    "my" to "vidíme",
                    "vy" to "vidíte",
                    "oni / ony" to "vidí"
                )
            )
            VerbTable(
                verb = "rozumět",
                label = "to understand",
                rows = listOf(
                    "já" to "rozumím",
                    "ty" to "rozumíš",
                    "on / ona / to" to "rozumí",
                    "my" to "rozumíme",
                    "vy" to "rozumíte",
                    "oni / ony" to "rozumějí"
                )
            )
            PTNote("A subset of -ět verbs (rozumět, umět, and prefixed verbs of motion) take -ějí in the formal oni / ony form instead of -í. Colloquial speech often uses -í for both, e.g. rozumí instead of rozumějí.")

            // Type 4
            PTSection("Type 4 — Verbs Ending in -nout (já → -nu)")
            PTNote("A distinct, predictable pattern: the stem drops -nout and adds the personal endings directly.")
            VerbTable(
                verb = "tisknout",
                label = "to print",
                rows = listOf(
                    "já" to "tisknu",
                    "ty" to "tiskneš",
                    "on / ona / to" to "tiskne",
                    "my" to "tiskneme",
                    "vy" to "tisknete",
                    "oni / ony" to "tisknou"
                )
            )

            // Type 5
            PTSection("Type 5 — Verbs Ending in -st / -zt / -ct (Consonant-Stem, já → -u)")
            PTNote("These verbs have unpredictable stem changes between the infinitive and the present tense, so each one must be memorised individually.")
            VerbTable(
                verb = "nést",
                label = "to carry",
                rows = listOf(
                    "já" to "nesu",
                    "ty" to "neseš",
                    "on / ona / to" to "nese",
                    "my" to "neseme",
                    "vy" to "nesete",
                    "oni / ony" to "nesou"
                )
            )
            VerbTable(
                verb = "číst",
                label = "to read — stem shortens číst → čt-",
                rows = listOf(
                    "já" to "čtu",
                    "ty" to "čteš",
                    "on / ona / to" to "čte",
                    "my" to "čteme",
                    "vy" to "čtete",
                    "oni / ony" to "čtou"
                )
            )
            VerbTable(
                verb = "moct / moci",
                label = "can / to be able",
                rows = listOf(
                    "já" to "můžu / mohu",
                    "ty" to "můžeš",
                    "on / ona / to" to "může",
                    "my" to "můžeme",
                    "vy" to "můžete",
                    "oni / ony" to "můžou / mohou"
                )
            )

            // Other irregular verbs
            PTSection("Other Common Irregular Verbs")
            PTNote("These verbs don't fit any pattern above and must be memorised on their own.")
            VerbTable(
                verb = "chtít",
                label = "to want",
                rows = listOf(
                    "já" to "chci",
                    "ty" to "chceš",
                    "on / ona / to" to "chce",
                    "my" to "chceme",
                    "vy" to "chcete",
                    "oni / ony" to "chtějí"
                )
            )
            VerbTable(
                verb = "vědět",
                label = "to know (a fact)",
                rows = listOf(
                    "já" to "vím",
                    "ty" to "víš",
                    "on / ona / to" to "ví",
                    "my" to "víme",
                    "vy" to "víte",
                    "oni / ony" to "vědí"
                )
            )
            PTNote("vědět is irregular in the oni / ony form: vědí does not follow the ví- stem seen in the rest of the paradigm.")
            VerbTable(
                verb = "jíst",
                label = "to eat",
                rows = listOf(
                    "já" to "jím",
                    "ty" to "jíš",
                    "on / ona / to" to "jí",
                    "my" to "jíme",
                    "vy" to "jíte",
                    "oni / ony" to "jedí"
                )
            )
            VerbTable(
                verb = "jít",
                label = "to go (on foot)",
                rows = listOf(
                    "já" to "jdu",
                    "ty" to "jdeš",
                    "on / ona / to" to "jde",
                    "my" to "jdeme",
                    "vy" to "jdete",
                    "oni / ony" to "jdou"
                )
            )
            VerbTable(
                verb = "pít",
                label = "to drink",
                rows = listOf(
                    "já" to "piju / piji",
                    "ty" to "piješ",
                    "on / ona / to" to "pije",
                    "my" to "pijeme",
                    "vy" to "pijete",
                    "oni / ony" to "pijou / pijí"
                )
            )
            VerbTable(
                verb = "brát",
                label = "to take — looks like -at (Type 1), but conjugates irregularly",
                rows = listOf(
                    "já" to "beru",
                    "ty" to "bereš",
                    "on / ona / to" to "bere",
                    "my" to "bereme",
                    "vy" to "berete",
                    "oni / ony" to "berou"
                )
            )
            VerbTable(
                verb = "psát",
                label = "to write — same trap: looks like -at, but is irregular",
                rows = listOf(
                    "já" to "píšu / píši",
                    "ty" to "píšeš",
                    "on / ona / to" to "píše",
                    "my" to "píšeme",
                    "vy" to "píšete",
                    "oni / ony" to "píšou / píší"
                )
            )

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
