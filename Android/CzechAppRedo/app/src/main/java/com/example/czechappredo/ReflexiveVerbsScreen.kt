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
fun ReflexiveVerbsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reflexive Verbs", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            RVSection("What Are Reflexive Verbs?")
            RVNote("The particles se and si redirect the action back to the subject — the subject does something to or for themselves.")
            RVNote("se = accusative reflexive: the action falls on oneself as the direct object.")
            RVNote("si = dative reflexive: the action is done for or to oneself as the indirect object.")
            RVNote("Many Czech verbs are inherently reflexive — se or si is a fixed part of the verb and does not mean 'oneself' as a grammatical object. It is simply how the verb is used.")

            RVSection("Accusative Reflexive — se")
            RVNote("Use se when oneself is the direct object (Accusative) of the action.")
            RVRow("holím se", "I shave myself", "the shaving acts on me")
            RVRow("česám se", "I comb my hair", "the combing acts on me")
            RVRow("oblékám se", "I get dressed", "dressing acts on me")
            RVRow("bavím se", "I'm enjoying myself", "the enjoyment acts on me")
            RVRow("dívám se na film", "I'm watching a film", "se is accusative; na + acc = what I look at")
            RVNote("Full conjugation — sprchovat se (to shower / wash oneself):")
            RVTable(
                verb = "sprchovat se",
                label = "Type 3  (-ovat) — accusative reflexive",
                rows = listOf(
                    "já" to "sprchuju se",
                    "ty" to "sprchujíš se",
                    "on / ona / ono" to "sprchuje se",
                    "my" to "sprchujeme se",
                    "vy" to "sprchujete se",
                    "oni / ony" to "sprchují se"
                )
            )

            RVSection("Dative Reflexive — si")
            RVNote("Use si when oneself is the indirect object (Dative) — the action is done for or to oneself.")
            RVRow("kupuji si auto", "I'm buying myself a car", "car = acc (direct obj), myself = dat (indirect obj)")
            RVRow("objednám si kávu", "I'll order a coffee for myself", "coffee = acc, for myself = dat")
            RVRow("čistím si zuby", "I'm cleaning my teeth", "teeth = acc, for myself = dat")
            RVRow("dám si", "I'll have / treat myself to", "colloquial, used when ordering food or drink")
            RVRow("přeji si nové auto", "I wish for a new car", "new car = acc, for myself = dat")
            RVNote("Key distinction: In kupuji si auto, the car is the Accusative (direct object) and si means 'for myself' (Dative). Compare with holím se, where se is the Accusative object of the shaving.")

            RVSection("Other Cases — Full Reflexive Pronoun (sebe)")
            RVNote("se and si are only the Accusative and Dative short forms. For other cases — Genitive, Locative, Instrumental — Czech uses the full reflexive pronoun sebe / sobě / sebou.")
            RVRow("Gen: sebe", "Bojím se o sebe.", "I worry about myself.  (o + acc)")
            RVRow("Dat: si / sobě", "Kupuji si / sobě auto.", "I'm buying myself a car.  (si = clitic, sobě = after prep or emphasis)")
            RVRow("Acc: se / sebe", "Vidím jen sebe.", "I see only myself.  (sebe = emphatic / after jen)")
            RVRow("Loc: sobě", "Myslím jen o sobě.", "I think only about myself.  (o + loc)")
            RVRow("Ins: sebou", "Jsem spokojený sám se sebou.", "I'm happy with myself.  (s/se + ins)")
            RVRow("Ins: sebou", "Vezměte sebou průvodce.", "Take a guide along.  (mít/vzít sebou = with you)")
            RVNote("se / si are short/clitic forms (always in second clause position). sebe / sobě / sebou are full forms used after prepositions or for emphasis.")

            RVSection("jsi + se/si in the Past Tense — Contraction")
            RVNote("In the past tense, when the helper jsi lands right next to a verb's own se or si, standard Czech fuses them into one word — this is called a contracted form (kontrakce): jsi + se → ses, jsi + si → sis. It is the correct, prescribed written form, not just spoken shorthand.")
            RVRow("Ty ses díval na film.", "You were watching a film.", "jsi + se → ses  (dívat se)")
            RVRow("Zapamatoval sis to?", "Did you remember it?", "jsi + si → sis  (zapamatovat si)")
            RVRow("Bál ses tmy?", "Were you afraid of the dark?", "jsi + se → ses  (bát se)")
            RVNote("Exception: only the reflexive particle se contracts this way — the preposition se ('with', a variant of s) never does. Jsi se mnou spokojený. (You are satisfied with me.) always stays as-is.")
            RVNote("See Past Tense for the full jsi second-position word-order rule this builds on.")

            RVSection("Inherently Reflexive Verbs — se")
            RVNote("These verbs require se as a fixed part of their identity. The se does not mean 'oneself' as a grammatical direct object — it is simply how the verb is expressed in Czech.")
            RVRow("smát se", "to laugh", "Směji se. (I laugh.)  Směješ se mi. (You're laughing at me.)")
            RVRow("bát se", "to fear / to be afraid of", "Bojím se tmy. (I'm afraid of the dark.)")
            RVRow("těšit se", "to look forward to", "Těším se na dovolenou. (I'm looking forward to the holiday.)")
            RVRow("ptát se", "to ask", "Ptám se tě. (I'm asking you.)  Zeptám se učitele.")
            RVRow("dívat se", "to watch / look at", "Dívám se na film. (I'm watching a film.)")
            RVRow("líbit se", "to please / to be liked", "Líbí se mi Praha. (I like Prague — lit. Prague pleases me.)")
            RVRow("stát se", "to become / to happen", "Stalo se něco? (Did something happen?)")
            RVRow("mýlit se", "to be wrong / make a mistake", "Mýlíš se. (You are wrong.)")
            RVRow("chovat se", "to behave", "Chová se dobře. (He behaves well.)")
            RVRow("vrátit se", "to return / come back", "Vrátím se brzy. (I'll come back soon.)")
            RVRow("učit se", "to learn / study", "Učím se česky. (I'm learning Czech.)")
            RVRow("cítit se", "to feel (oneself)", "Cítím se dobře. (I feel well.)")

            RVSection("Inherently Reflexive Verbs — si")
            RVNote("These verbs require si as a fixed part of their identity.")
            RVRow("zapamatovat si", "to memorize / remember", "Zapamatuji si to. (I'll remember that.)")
            RVRow("přát si", "to wish / desire", "Přeji si pokoj. (I wish for peace.)")
            RVRow("povídat si", "to chat / have a conversation", "Povídáme si o všem. (We chat about everything.)")
            RVRow("myslet si", "to think / believe (opinion)", "Myslím si, že máš pravdu. (I think you're right.)")
            RVRow("hrát si", "to play (children, games)", "Děti si hrají v parku. (The children are playing in the park.)")
            RVRow("dát si pozor", "to be careful / watch out", "Dej si pozor! (Be careful!)")

            RVSection("Mít vs. Mít se")
            RVNote("mít = to have. mít se = to be doing (describes one's state or wellbeing). The meaning changes completely.")
            RVNote("mít se is an inherently reflexive verb — the se is a fixed particle, not a direct object meaning 'myself'. The user's intuition is correct: it has nothing to do with 'having oneself' — it simply means 'to fare / to be doing'.")
            RVRow("Mám auto.", "I have a car.", "mít = to have")
            RVRow("Mám tě rád / ráda.", "I like you.", "mít = to have (fixed phrase)")
            RVRow("Jak se máš?", "How are you? (informal)", "mít se = to be doing")
            RVRow("Jak se máte?", "How are you? (formal / plural)", "")
            RVRow("Mám se dobře.", "I'm doing well.", "")
            RVRow("Mám se skvěle.", "I'm doing great.", "")
            RVRow("Nemám se dobře.", "I'm not doing well.", "")
            RVRow("Máme se krásně.", "We're doing wonderfully.", "")
            RVNote("Compare: Mám ho rád. (I like him.) vs. Jak se má? (How is he doing?) — completely different meanings despite using mít.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun RVSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun RVNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun RVRow(czech: String, english: String, note: String = "") {
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
private fun RVTable(verb: String, label: String, rows: List<Pair<String, String>>) {
    Spacer(modifier = Modifier.height(6.dp))
    Text(text = verb, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    Text(text = label, fontSize = 13.sp, color = Color.Gray, fontStyle = FontStyle.Italic)
    Spacer(modifier = Modifier.height(4.dp))
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Pronoun",
            modifier = Modifier.weight(1f),
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = ButtonBlue
        )
        Text(
            text = "Form",
            modifier = Modifier.weight(0.8f),
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = ButtonBlue
        )
    }
    HorizontalDivider(modifier = Modifier.padding(vertical = 3.dp))
    rows.forEach { (pronoun, form) ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp)
        ) {
            Text(text = pronoun, modifier = Modifier.weight(1f), fontSize = 14.sp, color = Color.DarkGray)
            Text(text = form, modifier = Modifier.weight(0.8f), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}
