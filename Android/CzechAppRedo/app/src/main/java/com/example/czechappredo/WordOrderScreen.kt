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
fun WordOrderScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Word Order In A Sentence", fontSize = 16.sp, fontWeight = FontWeight.Bold) },
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

            WOSection("Word Order Is Flexible — But Not Random")
            WONote("Czech word order is much freer than English, because case endings — not position — show a word's grammatical role (subject, object, etc.). You can move words around without making a sentence ungrammatical.")
            WONote("The neutral, unmarked order is Subject–Verb–Object (SVO), just like English.")
            WORow("Pes kousl kočku.", "The dog bit the cat.", "neutral SVO — answers 'what happened?'")
            WORow("Kočku kousl pes.", "It was the dog that bit the cat.", "OVS — kočku is fronted as the known topic; pes is pushed to the end as the new, focused information")
            WONote("This is called Topic–Focus Articulation: information that is already known (the topic / theme) tends to come first; new or emphasized information (the focus / rheme) tends to come last — regardless of its grammatical role.")
            WORow("Petr přečetl tu knihu.", "Petr read that book.", "neutral order")
            WORow("Petr tu knihu přečetl.", "Petr did read that book.", "S-O-V — the verb itself is pushed to the end because it is the new information, answering 'what did Petr do with that book?'")

            WOSection("The Second-Position Rule (Clitics)")
            WONote("Certain short, unstressed words called clitics can never start a sentence. They must attach right after the first stressed word or phrase — that first position can be a whole phrase, not necessarily literally word #1. This is known as Wackernagel's Law.")
            WONote("Clitic categories: the reflexive particles se / si; object-pronoun clitics (dative: mi, ti, mu, jí, nám, vám, jim — accusative: mě, tě, ho, ji, nás, vás, je); the past-tense helper verb (jsem, jsi, jsme, jste); the conditional helper (bych, bys, by, bychom, byste); and the formal particle -li.")
            WORow("Prohlížel jsem si ho.", "I was looking at him.", "jsem and si both sit in second position, right after the verb")
            WONote("on / ona / ono / oni / ony never take a past-tense helper verb, so this part of the rule doesn't apply to them in the past tense — see the Past Tense section below.")

            WOSection("When Several Clitics Stack Up")
            WONote("When more than one clitic lands in the second-position slot, they line up in a fixed order — never any other way.")
            WOTable(
                verb = "Clitic cluster order",
                label = "fixed sequence, first to last",
                rows = listOf(
                    "1. -li" to "if-particle",
                    "2. helper verb" to "jsem/jsi/jsme/jste, bych/bys/by",
                    "3. reflexive" to "se / si",
                    "4. dative pronoun" to "mi / ti / mu / jí / nám / vám / jim",
                    "5. accusative pronoun" to "mě / tě / ho / ji / nás / vás / je"
                )
            )
            WORow("Prohlížel jsem si ho.", "I was looking at him.", "helper jsem (slot 2) → reflexive si (slot 3) → accusative ho (slot 5)")
            WONote("You don't need to memorize every possible combination — just remember the order, so jsem si ho sounds right and si jsem ho never does.")

            WOSection("Reflexive Verbs and Clitic Climbing")
            WONote("Reflexive se / si follow the same second-position logic as any clitic — see the Reflexive Verbs screen for the full explanation of when se vs. si is used.")
            WORow("Dívám se.", "I'm watching.", "no subject pronoun is stated, so the verb itself is first and se is second")
            WORow("Já se dívám.", "I am watching.", "já is first, se moves to second, dívám is third")
            WONote("A special case: when a reflexive infinitive is combined with a modal verb like chtít (to want), the reflexive clitic can 'climb' out of the infinitive and attach to the second position of the whole sentence instead of staying next to its own verb. This is called clitic climbing.")
            WORow("Chci se dívat (na televizi).", "I want to watch (TV).", "se climbs to attach right after Chci, not after the infinitive dívat")
            WORow("Máma řekla, že se můžu dívat.", "Mom said that I can watch.", "že starts a new clause, so se attaches right after že — a fresh second-position count begins inside the clause")

            WOSection("Past Tense: Dropping the Pronoun Without Breaking the Rule")
            WONote("The Past Tense screen already covers dropping já / ty / my / vy. Here's how that fits the general second-position rule above: jsem / jsi / jsme / jste are clitics, so they always need a host in first position.")
            WORow("Já jsem psal dopis.", "I wrote a letter.", "pronoun included = emphasis / contrast. já is 1st, jsem is 2nd, psal is 3rd")
            WORow("Psal jsem dopis.", "I wrote a letter.", "default, unmarked form — the l-participle psal moves to first position so jsem still has a host in second position")
            WONote("The clitic never moves to the end — it stays second no matter what occupies first position. First position can even be a fronted time word or topic instead of the verb:")
            WORow("Ráno jsem dokončil projekt.", "In the morning, I finished the project.", "Ráno (the topic) is 1st, jsem is 2nd, dokončil is 3rd")
            WONote("on / ona / ono / oni / ony use no helper verb at all in the past tense — just the l-participle alone — so no clitic-placement question arises for third person.")

            WOSection("Negation and Word Order")
            WONote("The negation prefix ne- attaches directly onto the verb being negated (see the Negation screen for the full rules on double negation). A negated verb still works normally as the clitic's host in first position:")
            WORow("Neviděl jsem ho.", "I didn't see him.", "Neviděl (1st, negated) + jsem (2nd) + ho (3rd)")

            WOSection("Questions and Other Word-Order Effects")
            WONote("In spoken Czech, intonation — not word order — is the main signal for a yes/no question. Nemá Petr nový byt? is understood as a question mainly from rising intonation, though putting the negated verb first is also common in written or formal yes/no questions.")
            WONote("See the Questions screen for question words like Co, Kdo, and Kde, which start a question and count as the 'first position' for any clitic that follows.")
            WONote("The particle -li is a separate, more formal / literary way to form 'if' clauses. It glues directly and only onto a verb — never onto a noun or pronoun.")
            WORow("Víte-li...", "If you know...", "-li attaches straight to the verb Víte; it's first in the clitic cluster (see above)")
            WONote("Finally, adjectives in Czech precede their noun, just like in English — postposition is rare and poetic. And as in the first section, longer phrases and newly introduced information tend to drift toward the end of the clause — the same theme-rheme principle that explains Kočku kousl pes also explains why a long descriptive phrase usually comes last.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun WOSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun WONote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun WORow(czech: String, english: String, note: String = "") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
    ) {
        Text(
            text = czech,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.weight(1.1f)
        )
        Column(modifier = Modifier.weight(1.5f)) {
            Text(text = english, fontSize = 13.sp, color = Color.DarkGray)
            if (note.isNotEmpty()) {
                Text(
                    text = note,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
private fun WOTable(verb: String, label: String, rows: List<Pair<String, String>>) {
    Spacer(modifier = Modifier.height(6.dp))
    Text(text = verb, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    Text(text = label, fontSize = 13.sp, color = Color.Gray, fontStyle = FontStyle.Italic)
    Spacer(modifier = Modifier.height(4.dp))
    rows.forEach { (slot, form) ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp)
        ) {
            Text(text = slot, modifier = Modifier.weight(1f), fontSize = 14.sp, color = Color.DarkGray)
            Text(text = form, modifier = Modifier.weight(1.3f), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}
