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
fun ObjectPronounConjugationExamplesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Object Pronouns", fontSize = 16.sp, fontWeight = FontWeight.Bold) },
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
            OPExNote("Object Pronouns (Learning hub) shows the bare declension tables. This screen puts them to work — for each case, all 8 pronouns (já, ty, on, ona, ono, my, vy, oni/ony) get a real sentence, cycling through a different preposition each time so you see many prepositions in action, not just one.")
            OPExNote("After any preposition, 3rd-person pronouns take the n- prefix (ho → něho, jí → ní, jim → nim, jimi → nimi, etc.) — you'll see this throughout every section below.")
            OPExNote("Nominativ is skipped — personal pronouns as objects don't appear there. Vokativ doesn't apply to personal pronouns at all.")

            // ══════════════════════════════════════════════════════════════
            OPExSection("Genitiv — of / from / without")
            OPExCase("já → bez", "Beze mě to nedokážeš.", "You won't manage it without me.", "mě")
            OPExCase("ty → od", "Mám dárek od tebe.", "I have a gift from you.", "tebe")
            OPExCase("on → u", "Byl jsem u něho včera večer.", "I was at his place last night.", "něho")
            OPExCase("ona → kolem", "Prošli jsme kolem ní.", "We walked past her.", "ní")
            OPExCase("ono → z", "To je moje nové auto. Právě jsem z něj vystoupil.", "This is my new car. I just got out of it.", "něj")
            OPExCase("my → blízko", "Bydlí blízko nás.", "He lives near us.", "nás")
            OPExCase("vy → bez", "Bez vás by ta oslava nebyla stejná.", "Without you the party wouldn't be the same.", "vás")
            OPExCase("oni/ony → od", "Dostal jsem dopis od nich.", "I got a letter from them.", "nich")

            // ══════════════════════════════════════════════════════════════
            OPExSection("Dativ — to / for (indirect object)")
            OPExCase("já → k", "Pojď ke mně.", "Come to my place.", "mně")
            OPExCase("ty → díky", "Díky tobě jsem to zvládl.", "Thanks to you I managed it.", "tobě")
            OPExCase("on → k", "Jdu k němu.", "I'm going to his place.", "němu")
            OPExCase("ona → díky", "Díky ní jsem to pochopil.", "Thanks to her I understood it.", "ní")
            OPExCase("ono → kvůli", "To rozhodnutí bylo těžké. Kvůli němu jsme se pohádali.", "The decision was hard. We argued because of it.", "němu")
            OPExCase("my → naproti", "Bydlí naproti nám.", "He lives across from us.", "nám")
            OPExCase("vy → k", "Přijdu k vám zítra.", "I'll come to your place tomorrow.", "vám")
            OPExCase("oni/ony → kvůli", "Kvůli nim jsme přišli pozdě.", "Because of them we arrived late.", "nim")

            // ══════════════════════════════════════════════════════════════
            OPExSection("Akuzativ — direct object")
            OPExCase("já → na", "Čekáš na mě?", "Are you waiting for me?", "mě")
            OPExCase("ty → pro", "Mám pro tebe dárek.", "I have a gift for you.", "tebe")
            OPExCase("on → za", "Schoval se za něj.", "He hid behind him.", "něj")
            OPExCase("ona → pro", "Udělal jsem to pro ni.", "I did it for her.", "ni")
            OPExCase("ono → přes", "Je tam plot. Museli jsme přes něj přelézt.", "There's a fence. We had to climb over it.", "něj")
            OPExCase("my → za", "Schovali se za nás.", "They hid behind us.", "nás")
            OPExCase("vy → na", "Čekám na vás.", "I'm waiting for you.", "vás")
            OPExCase("oni/ony → pro", "Udělal jsem to pro ně.", "I did it for them.", "ně")

            // ══════════════════════════════════════════════════════════════
            OPExSection("Lokál — location / topic (always with a preposition)")
            OPExCase("já → o", "Mluvili o mně.", "They talked about me.", "mně")
            OPExCase("ty → na", "Záleží mi na tobě.", "You matter to me.", "tobě")
            OPExCase("on → o", "Mluvili o něm.", "They talked about him.", "něm")
            OPExCase("ona → po", "Stýská se mi po ní.", "I miss her.", "ní")
            OPExCase("ono → v", "To auto? Bylo v něm plno věcí.", "That car? It was full of stuff in it.", "něm")
            OPExCase("my → na", "Záleží na nás.", "It depends on us.", "nás")
            OPExCase("vy → při", "Budeme při vás stát.", "We'll stand by you.", "vás")
            OPExCase("oni/ony → o", "Mluvili o nich.", "They talked about them.", "nich")

            // ══════════════════════════════════════════════════════════════
            OPExSection("Instrumentál — with / by means of")
            OPExCase("já → s", "Pojď se mnou.", "Come with me.", "mnou")
            OPExCase("ty → před", "Stál jsem před tebou ve frontě.", "I stood in front of you in line.", "tebou")
            OPExCase("on → s", "Mluvil jsem s ním.", "I spoke with him.", "ním")
            OPExCase("ona → za", "Šel jsem za ní.", "I went after her.", "ní")
            OPExCase("ono → pod", "Pod ním je koberec.", "There's a carpet under it.", "ním")
            OPExCase("my → mezi", "Sedni si mezi nás.", "Sit between us.", "nás")
            OPExCase("vy → s", "Chci mluvit s vámi.", "I want to speak with you.", "vámi")
            OPExCase("oni/ony → nad", "Letěli jsme nad nimi.", "We flew above them.", "nimi")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun OPExNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun OPExSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
    HorizontalDivider(modifier = Modifier.padding(bottom = 6.dp))
}

@Composable
private fun OPExCase(pronounPrep: String, czech: String, english: String, form: String) {
    Column(modifier = Modifier.padding(vertical = 5.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = pronounPrep, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
            Text(text = form, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
        Text(text = czech, fontSize = 15.sp, color = Color.DarkGray, modifier = Modifier.padding(top = 2.dp))
        Text(text = english, fontSize = 13.sp, fontStyle = FontStyle.Italic, color = Color.Gray)
    }
}
