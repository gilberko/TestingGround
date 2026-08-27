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
fun PassiveVoiceScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Passive Voice", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            PVNote("Czech has two ways to build a passive sentence. The reflexive se-passive is used for general, habitual statements with no particular agent in mind. The n/t-participle passive (být + a special participle, e.g. napsán) describes a specific action or result, and works in any tense — this is the construction behind forms like \"napsán.\"")

            PVSection("The Reflexive (se) Passive")
            PVNote("Formation: a 3rd-person verb + se. This is the everyday, most common way to make a general or impersonal statement — cross-reference Learning → Reflexive Verbs, which covers se's other, true-reflexive use (an action done to oneself). This section is a different job for the same little word.")
            PVRow("To se dělá takto.", "This is done like this.")
            PVRow("Jak se to řekne česky?", "How is that said in Czech?")
            PVRow("Byty se prodávají rychle.", "Apartments sell/are sold quickly.")
            PVRow("Tady se nekouří.", "No smoking here. (lit. \"here it isn't smoked\")")

            PVSection("The n/t-Participle Passive")
            PVNote("Formation: verb stem + -n / -en / -t, giving a short predicate participle used together with být. This short form (napsán) is different from the long adjectival form used before a noun (napsaný dopis = \"a written letter\") — the short form only ever sits in the predicate, after být.")
            PVRow("Dopis je psán.", "The letter is being written. (present, imperfective participle — formal/literary; Dopis se píše. is the everyday equivalent)")
            PVRow("Dopis byl napsán.", "The letter was written. (past, perfective participle — a completed result)")
            PVRow("Dopis bude napsán.", "The letter will be written. (future)")
            PVRow("Dopis byl napsán Petrem.", "The letter was written by Petr. (the agent, when named, goes in the instrumental — no separate \"by\"-word needed)")

            PVSection("Gender and Number Agreement")
            PVNote("The participle agrees with the subject in gender and number, exactly like an adjective. Singular:")
            PVTable(
                listOf(
                    Triple("masculine", "napsán", "Dopis byl napsán."),
                    Triple("feminine", "napsána", "Kniha byla napsána."),
                    Triple("neuter", "napsáno", "Auto bylo opraveno.")
                )
            )
            PVNote("Plural — this is the direct answer to \"is there a different form when everything is feminine?\": no. Feminine plural does not get an ending of its own — it shares -y with masculine inanimate plural. Only masculine ANIMATE plural gets its own distinct ending, -i.")
            PVTable(
                listOf(
                    Triple("masc. animate", "napsáni", "Chlapci byli pozváni."),
                    Triple("masc. inanimate / feminine", "napsány", "Dopisy byly napsány. · Knihy byly napsány."),
                    Triple("neuter", "napsána", "Auta byla opravena.")
                )
            )
            PVNote("This -i / -y / -a three-way split is exactly the same pattern already used for the past-tense l-participle in Learning → Past Tense (dělali / dělaly / dělala) — same rule, same shape, just a different participle.")
            PVNote("A few more participles for practice: otevřít→otevřen (opened), zavřít→zavřen (closed), koupit→koupen (bought), postavit→postaven (built), ukončit→ukončen (ended/concluded).")

            PVSection("Which One To Use?")
            PVRow("se-passive", "everyday, general/habitual statements, no specific agent — pairs naturally with imperfective verbs")
            PVRow("n/t-participle passive", "a specific event or result, any tense, and it can name an agent (instrumental) if needed")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PVSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun PVRow(czech: String, english: String) {
    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)) {
                    append(czech)
                }
            }
        )
        Text(
            text = english,
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(start = 4.dp, top = 1.dp)
        )
    }
}

@Composable
private fun PVNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

@Composable
private fun PVTable(entries: List<Triple<String, String, String>>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 6.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Text("Gender", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ButtonBlue, modifier = Modifier.weight(1.1f))
            Text("Form", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ButtonBlue, modifier = Modifier.weight(0.8f))
            Text("Example", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ButtonBlue, modifier = Modifier.weight(1.6f))
        }
        HorizontalDivider(color = Color.LightGray)
        entries.forEach { (gender, form, example) ->
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                Text(gender, fontSize = 13.sp, color = Color.DarkGray, modifier = Modifier.weight(1.1f))
                Text(form, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(0.8f))
                Text(example, fontSize = 13.sp, color = Color.DarkGray, modifier = Modifier.weight(1.6f))
            }
            HorizontalDivider(color = Color(0xFFEEEEEE))
        }
    }
}
