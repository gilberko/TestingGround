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
fun NeedHaveAllowedScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Need To, Have To, Allowed", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            NHASection("Need To / Have To")
            NHANote("muset = must / have to (a firm obligation). potřebovat = to need (softer, more about necessity than duty).")
            NHARow("Musím to udělat.", "I have to do this. / I need to do this.", "muset — firm obligation")
            NHARow("Musím dávat pozor.", "I need to pay attention.")
            NHARow("Potřebuji to udělat.", "I need to do this.", "potřebovat — softer than muset")
            NHARow("Nemusím tam jít.", "I don't have to go there.", "nemuset = not have to (NOT the same as forbidden!)")

            NHASection("Necessary / Required")
            NHANote("Impersonal 'it is necessary' constructions, plus vyžadovat (to require) and požadavek (requirement, noun) — vyžadovat and its passive already appear in Common Verbs List.")
            NHARow("Je nutné to udělat.", "It's necessary to do this.")
            NHARow("Je potřeba to udělat.", "It's necessary / needed to do this.", "potřeba here is an indeclinable predicate word, not the verb")
            NHARow("Vyžadují to od nás.", "They require us to do it.", "vyžadovat + od + genitive (from us)")
            NHARow("Je to vyžadováno.", "It is required.", "passive of vyžadovat")
            NHARow("Je to základní požadavek.", "It's a basic requirement.", "požadavek (m.) — requirement, noun")

            NHASection("Allowed / Not Allowed / Forbidden")
            NHANote("smět = to be allowed to (modal, already covered in Useful Conjugated Verbs). zakázáno = forbidden (a stronger, often official prohibition). nesmí se / není dovoleno = not allowed (more general).")
            NHARow("Smím tu parkovat?", "Am I allowed to park here?", "smět — modal verb")
            NHARow("Kouření je dovoleno.", "Smoking is allowed.", "dovoleno = allowed (from dovolit/dovolovat)")
            NHARow("Kouření je zakázáno.", "Smoking is forbidden.", "zakázáno — official prohibition, e.g. signs/rules")
            NHARow("Kouřit se nesmí.", "Smoking is not allowed.", "reflexive-impersonal, from smět")
            NHARow("Není dovoleno tu parkovat.", "It's not allowed to park here.")
            NHANote("Register note: zakázáno reads as an official ban (like a posted sign); nesmí se / není dovoleno is the everyday way to say something isn't allowed.")

            NHASection("Should — Měl Bych")
            NHANote("měl bych / měla bych = I should (idiomatic conditional of mít + the by-particle, not a literal 'would have').")
            NHARow("Měl bych to udělat.", "I should do this.", "man speaking")
            NHARow("Měla bych to udělat.", "I should do this.", "woman speaking")
            NHARow("Měl bys víc odpočívat.", "You should rest more.", "ty-form: měl bys / měla bys")

            NHASection("Would — Bych")
            NHANote("'Would' in English maps to the same conditional particle bych/bys/by... taught in full (all six persons) in the Conditions screen. It is NOT limited to if-clauses — Czech uses it standalone for hypotheticals, wishes, and polite statements too, exactly like the examples below.")
            NHARow("Udělal bych to.", "I would do this.", "man speaking; woman: Udělala bych to.")
            NHARow("Asi bych to udělal.", "I would probably do this.", "asi = probably")
            NHARow("Určitě bych to udělal.", "I would definitely do this.", "určitě = definitely")

            NHASection("Might / Maybe — Možná")
            NHANote("možná + present/future tense expresses 'might / maybe' — no special mood needed, just the adverb možná.")
            NHARow("Možná to udělám.", "I might do this. / Maybe I'll do this.")
            NHARow("Možná přijdu.", "I might come.")
            NHARow("Možná bude pršet.", "It might rain.")

            NHASection("Prefer, Choose, Decide, Consider")
            NHARow("Preferuji čaj.", "I prefer tea.", "preferovat + accusative")
            NHARow("Dávám přednost čaji.", "I prefer tea.", "dávat přednost + dative — more idiomatic than preferovat")
            NHARow("Vyberu si tohle.", "I'll choose this.", "vybrat si (pf.) / vybírat si (impf.)")
            NHARow("Rozhodl jsem se odejít.", "I decided to leave.", "rozhodnout se (pf.) / rozhodovat se (impf.)")
            NHARow("Musím to zvážit.", "I need to consider it.", "zvážit (pf.) / zvažovat (impf.)")
            NHARow("Uvažuji o tom.", "I'm considering it.", "uvažovat o + locative")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun NHASection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun NHARow(czech: String, english: String, note: String = "") {
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

@Composable
private fun NHANote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
