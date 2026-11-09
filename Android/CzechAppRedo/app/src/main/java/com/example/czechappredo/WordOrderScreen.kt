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

            WOSection("Between se and the Verb")
            WONote("se / si is not tied to its verb — it is tied to the second position of the clause. So whenever something other than the verb takes first position (a subject, a time word, a question word, že...), se lands BEFORE the verb, and the verb can end up much further along.")
            WORow("Dívám se.", "I'm watching.", "verb is 1st → se comes after it")
            WORow("Já se dívám.", "I'm watching.", "subject is 1st → se comes before the verb")
            WORow("Ráno se umyju.", "In the morning I'll wash.", "time word is 1st → se before the verb")
            WORow("Na co se díváš?", "What are you looking at?", "question phrase is 1st → se before the verb")
            WONote("First position is one constituent, not one word — a whole phrase with its adjectives counts as a single slot, and se still comes right after it:")
            WORow("Můj starší bratr se včera oženil.", "My older brother got married yesterday.", "'Můj starší bratr' = slot 1, se = slot 2")

            Text("✔ What CAN come between se and the verb", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(top = 10.dp, bottom = 2.dp))
            WONote("Practically anything that is not a clitic — the gap is just the normal 'middle' of the sentence:")
            WORow("Včera se Petr oženil.", "Petr got married yesterday.", "the subject (Petr) sits between se and the verb")
            WORow("…, že se Petr oženil.", "…that Petr got married.", "same inside a že-clause: že is 1st, se 2nd, subject in the gap")
            WORow("Petr se včera oženil.", "Petr got married yesterday.", "time word (včera) in the gap")
            WORow("Já se tam rychle umyju.", "I'll quickly wash there.", "adverbs of place and manner (tam, rychle) in the gap")
            WORow("Petr se na ten film díval.", "Petr was watching that film.", "prepositional object (na ten film) in the gap")
            WORow("Já se toho psa bojím.", "I'm afraid of that dog.", "genitive object of bát se (toho psa) in the gap")
            WORow("Já si to nové červené auto koupím.", "I'll buy (myself) that new red car.", "a direct object with its adjectives in the gap — typical with si verbs")
            WORow("Zítra se budu učit.", "Tomorrow I'll study.", "another verb: budu (future helper — a full word, not a clitic) in the gap")
            WORow("Já se musím učit.", "I have to study.", "modal verb musím in the gap — se climbed out of 'učit se' (clitic climbing)")
            WORow("Petr se chce začít učit.", "Petr wants to start studying.", "two verbs (chce začít) in the gap — se climbs over both")
            WORow("Já se nebojím.", "I'm not afraid.", "negation is fine — ne- is part of the verb itself")
            WONote("The gap can get long, e.g. Já se na to zítra musím pořádně podívat. (I have to take a proper look at it tomorrow.) The same topic–focus rule from the top of this screen still decides the order inside the gap: the new / emphasized information tends to go last, often right before or as the verb.")
            WONote("Pronoun clitics also follow se / si, but they are not really 'in the gap' — they join the clitic cluster immediately after se / si (dative first, then accusative), and only then does the gap start:")
            WORow("Ty se mi líbíš.", "I like you. (lit. You are pleasing to me.)", "se → mi (dative) → verb")
            WORow("Já si ho koupím.", "I'll buy it (for myself).", "si → ho (accusative) → verb")

            Text("✘ What CANNOT happen", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(top = 10.dp, bottom = 2.dp))
            WORow("Já jsem se díval. ✔\nJá se jsem díval. ✘", "I was watching.", "the past / conditional helper (jsem, bych...) comes BEFORE se in the cluster, never between se and the verb")
            WORow("Já bych se díval. ✔\nJá se bych díval. ✘", "I would watch.", "same for the conditional helper")
            WORow("Já si ho koupím. ✔\nJá ho si koupím. ✘", "I'll buy it.", "pronoun clitics come after se / si, not before")
            WORow("Petr se včera oženil. ✔\nPetr včera se oženil. ✘", "Petr got married yesterday.", "in a neutral sentence se goes right after the first constituent (Petr), not 3rd. Exception: after a conjunction like že, both orders occur — že se Petr rozhodl and že Petr se rozhodl")
            WORow("Se dívám. ✘", "—", "se can never start a clause — use Dívám se.")
            WORow("Máma řekla, že se můžu dívat. ✔\nMáma se řekla, že můžu dívat. ✘", "Mom said I can watch.", "se can climb out of an infinitive, but never out of its own clause")
            WORow("Já se nebojím. ✔\nJá se ne bojím. ✘", "I'm not afraid.", "ne- is a prefix written together with the verb, so it can't be split off into the gap")

            Text("si instead of se", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(top = 10.dp, bottom = 2.dp))
            WONote("si follows exactly the same placement rules as se — same slot in the clitic cluster, same second position, same ability to climb. The only practical difference: si (the dative 'for / to oneself') usually comes with a direct object, so you'll see objects in the gap far more often with si.")
            WORow("Koupil jsem si nové auto.", "I bought myself a new car.", "verb 1st → jsem si → object after the verb")
            WORow("Já si nové auto koupím.", "I'll buy myself a new car.", "subject 1st → si → object in the gap → verb")
            WORow("Já si ho koupím.", "I'll buy it (for myself).", "object replaced by a pronoun clitic → si ho → verb")
            WORow("Musím si to rozmyslet.", "I have to think it over.", "si climbs from rozmyslet si to follow musím")
            WONote("When two verbs in one sentence each have a reflexive, it depends which ones meet:")
            WORow("Přinutili se smát.", "They forced themselves to laugh.", "se + se: one se is usually dropped (přinutit se + smát se). Keeping both is not wrong — and both must stay if dropping one changes the meaning: Snažím se nezlobit se. (I try not to be angry) vs Snažím se nezlobit. (I try not to be naughty)")
            WORow("Dovolujeme si obrátit se na Vás.", "We take the liberty of contacting you.", "se + si: NEITHER can be dropped — both stay (si with dovolit si, se with obrátit se)")
            WONote("Usually only one reflexive climbs to the clitic slot and the other stays with its infinitive, as above. When se and si do end up in the same cluster, the usual order is se si.")

            WOTable(
                verb = "Summary — the order of a clause with se / si",
                label = "left to right",
                rows = listOf(
                    "1. first constituent" to "Já / Ráno / Na co / že / verb",
                    "2. helper verb" to "jsem / bych ...",
                    "3. reflexive" to "se / si",
                    "4. pronoun clitics" to "mi, ti... then ho, ji...",
                    "5. the gap" to "subject, adverbs, objects, budu / modals",
                    "6. the verb" to "(often last, as the focus)"
                )
            )

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
