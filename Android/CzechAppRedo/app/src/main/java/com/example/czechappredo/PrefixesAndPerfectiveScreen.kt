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
fun PrefixesAndPerfectiveScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Prefixes and Perfective Verbs", fontSize = 16.sp, fontWeight = FontWeight.Bold) },
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

            PZNote("Czech verbs come in aspect pairs: imperfective (an ongoing, repeated, or general action) and perfective (a single, completed, bounded action). A large share of perfectives are formed by attaching a prefix to an imperfective stem — and the prefix very often nudges or changes the meaning too, not just the aspect. That's why the same root can branch into a whole family of related-but-distinct verbs.")

            PZSection("Common Prefixes at a Glance")
            PZNote("One typical example pair per prefix. The same prefix can behave differently on different verbs — sometimes it's almost \"empty\" (just marks completion), sometimes it adds real meaning of its own.")
            PZRow("u-", "completion / result",
                example = "dělat → udělat", translation = "to do (ongoing) → to get [it] done")
            PZRow("za-", "begin / momentary / cover",
                example = "volat → zavolat", translation = "to call (ongoing) → to make a call")
            PZRow("na-", "onto a surface, a quantity, or purely perfectivizing",
                example = "psát → napsat", translation = "to write (ongoing) → to write [and finish] something")
            PZRow("vy-", "out / up / thoroughly",
                example = "jít → vyjít", translation = "to go (on foot) → to go out / come up")
            PZRow("po-", "a bit / for a while, or distributive",
                example = "sedět → posedět", translation = "to sit → to sit for a while")
            PZRow("pře-", "over / across / re- (again)",
                example = "psát → přepsat", translation = "to write → to rewrite / retype")
            PZRow("do-", "reach the end / finish",
                example = "číst → dočíst", translation = "to read → to finish reading")
            PZRow("od-", "away / un- (reverse)",
                example = "jít → odejít", translation = "to go → to leave, go away")
            PZRow("při-", "toward / arrive / add a bit",
                example = "jít → přijít", translation = "to go → to come, arrive")
            PZRow("roz-", "apart / in different directions / set in motion",
                example = "bít → rozbít", translation = "to hit/beat → to smash, break")
            PZRow("s- / z-", "down / together, or purely perfectivizing",
                example = "jíst → sníst", translation = "to eat → to eat up")
            PZRow("v(e)-", "into",
                example = "jít → vejít", translation = "to go → to go into, enter")

            PZSection("Deep Dive — The psát (\"to write\") Family")
            PZNote("One root, five prefixes, five distinct verbs. This shows how far a single imperfective stem can branch once prefixes get involved.")
            PZRow("psát", "to write (imperfective, ongoing/general)",
                example = "Píšu dopis.", translation = "I'm writing a letter.")
            PZRow("napsat  (na-)", "to write [and finish] — perfective of psát",
                example = "Napsal jsem dopis.", translation = "I wrote a letter.")
            PZRow("přepsat  (pře-)", "to rewrite / retype / transcribe",
                example = "Přepsal jsem esej.", translation = "I rewrote the essay.")
            PZRow("opsat / opisovat  (o-)", "to copy [by writing] / copy off, plagiarize",
                example = "Opsal úkol od kamaráda.", translation = "He copied the homework from a friend.")
            PZRow("podepsat  (pod-)", "to sign — literally \"write under\"",
                example = "Podepsal jsem smlouvu.", translation = "I signed the contract.")
            PZRow("zapsat  (za-)", "to write down / note / enroll",
                example = "Zapsal jsem si její číslo.", translation = "I wrote down her number.")
            PZNote("The base pair psát/napsat already has a full conjugation table in Simple Dictionary → Common Verbs List → Reading, Writing & Language.")

            PZSection("Deep Dive — Forget, Remind, Recall, Remember")
            PZNote("Three of these four verbs share the same root (-pomínat / -pomenout) and differ only by prefix — a perfect illustration of how a prefix can turn one root into several related-but-distinct meanings. The fourth, pamatovat si, is a separate root entirely, from paměť (\"memory\").")
            PZRow("zapomínat / zapomenout  (za- = away)", "to forget",
                example = "Zapomněl jsem klíče doma. · Nezapomeň zavolat!", translation = "I forgot my keys at home. · Don't forget to call!")
            PZRow("připomínat / připomenout  (při- = toward)", "to remind",
                example = "Připomeň mi to zítra. · Připomínáš mi mou sestru.", translation = "Remind me about it tomorrow. · You remind me of my sister.")
            PZRow("vzpomínat si / vzpomenout si  (vz- = up)", "to recall / remember spontaneously",
                example = "Vzpomínám si na tebe. · Nemůžu si vzpomenout na její jméno.", translation = "I remember/recall you. · I can't recall her name.")
            PZRow("pamatovat si / zapamatovat si  (different root — paměť)", "to remember / memorize",
                example = "Pamatuješ si na mě? · Musím si to zapamatovat.", translation = "Do you remember me? · I have to memorize this.")
            PZNote("Irregular past tense: zapomněl, připomněl, vzpomněl — not \"-pomenul.\" All three -pomenout verbs share this same irregular past stem. Full conjugation tables for all four verbs are in Simple Dictionary → Common Verbs List → Mind & Knowledge.")

            PZSection("And Other Families — volat (\"to call\")")
            PZNote("The same prefix meanings from the table above, applied to a second root.")
            PZRow("volat → zavolat  (za-)", "to call / make a phone call",
                example = "Zavolám ti večer.", translation = "I'll call you tonight.")
            PZRow("odvolat  (od-)", "to cancel / call off",
                example = "Schůzka byla odvolána.", translation = "The meeting was called off.")
            PZRow("přivolat  (při-)", "to summon",
                example = "Přivolali jsme pomoc.", translation = "We summoned help.")
            PZRow("povolat  (po-)", "to call up / draft (e.g. military)",
                example = "Byl povolán do armády.", translation = "He was drafted into the army.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PZSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun PZRow(term: String, meaning: String, example: String, translation: String) {
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)) {
                append(term)
            }
            withStyle(SpanStyle(fontSize = 15.sp, color = Color.DarkGray)) {
                append("  —  $meaning")
            }
        }
    )
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)) {
                append(example)
            }
            withStyle(SpanStyle(fontSize = 14.sp, color = Color.Gray, fontStyle = FontStyle.Italic)) {
                append("  —  $translation")
            }
        },
        modifier = Modifier.padding(top = 2.dp, start = 4.dp)
    )
}

@Composable
private fun PZNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}
