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
fun CaseConjugationExamplesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Examples of Conjugation of Cases", fontSize = 15.sp, fontWeight = FontWeight.Bold) },
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
            CCNote("Cases and Adjectives (Learning hub) show the bare declension tables. This screen puts them to work — for each noun+adjective phrase below, every one of the 7 cases gets a natural example sentence with the conjugated phrase highlighted, so you can see exactly how the endings change in context.")
            CCNote("Order of cases: 1. Nominativ (subject) · 2. Genitiv (of / without) · 3. Dativ (to / for) · 4. Akuzativ (direct object) · 5. Vokativ (addressing someone) · 6. Lokál (in / on / about — always with a preposition) · 7. Instrumentál (with / by means of).")

            // ── chytrý muž ────────────────────────────────────────────────
            CCPhrase("chytrý muž", "a smart man", "masculine animate · soft-pattern noun (muž) + hard adjective (chytrý) · singular")
            CCCase("1. Nominativ", "Chytrý muž přišel pozdě.", "A smart man arrived late.", "Chytrý muž")
            CCCase("2. Genitiv", "Bál jsem se chytrého muže.", "I was afraid of a smart man.", "chytrého muže")
            CCCase("3. Dativ", "Dal jsem radu chytrému muži.", "I gave advice to a smart man.", "chytrému muži")
            CCCase("4. Akuzativ", "Potkal jsem chytrého muže.", "I met a smart man.", "chytrého muže")
            CCCase("5. Vokativ", "Chytrý muži, poraď mi!", "Smart man, give me some advice!", "Chytrý muži")
            CCCase("6. Lokál", "Mluvili jsme o chytrém muži.", "We talked about a smart man.", "chytrém muži")
            CCCase("7. Instrumentál", "Pracuji s chytrým mužem.", "I work with a smart man.", "chytrým mužem")

            CCPhrase("chytří muži", "smart men", "masculine animate · plural — note the r → ř softening before í (chytrý → chytří), and that the accusative plural (chytré) differs from the nominative (chytří)")
            CCCase("1. Nominativ", "Chytří muži vždycky najdou řešení.", "Smart men always find a solution.", "Chytří muži")
            CCCase("2. Genitiv", "Bez chytrých mužů by firma zkrachovala.", "Without smart men the company would go bankrupt.", "chytrých mužů")
            CCCase("3. Dativ", "Věřím chytrým mužům.", "I trust smart men.", "chytrým mužům")
            CCCase("4. Akuzativ", "Znám chytré muže.", "I know smart men.", "chytré muže")
            CCCase("5. Vokativ", "Chytří muži, poslouchejte!", "Smart men, listen!", "Chytří muži")
            CCCase("6. Lokál", "Píšou o chytrých mužích.", "They write about smart men.", "chytrých mužích")
            CCCase("7. Instrumentál", "Spolupracuji s chytrými muži.", "I collaborate with smart men.", "chytrými muži")

            // ── krásná žena ───────────────────────────────────────────────
            CCPhrase("krásná žena", "a beautiful woman", "feminine · hard noun pattern (žena) + hard adjective (krásná) · singular")
            CCCase("1. Nominativ", "Krásná žena vešla do místnosti.", "A beautiful woman entered the room.", "Krásná žena")
            CCCase("2. Genitiv", "Bez krásné ženy by ten večírek nebyl stejný.", "Without a beautiful woman the party wouldn't be the same.", "krásné ženy")
            CCCase("3. Dativ", "Dal květiny krásné ženě.", "He gave flowers to a beautiful woman.", "krásné ženě")
            CCCase("4. Akuzativ", "Vidím krásnou ženu.", "I see a beautiful woman.", "krásnou ženu")
            CCCase("5. Vokativ", "Krásná ženo, pojď se mnou!", "Beautiful woman, come with me!", "Krásná ženo")
            CCCase("6. Lokál", "Přemýšlím o krásné ženě.", "I'm thinking about a beautiful woman.", "krásné ženě")
            CCCase("7. Instrumentál", "Tančil jsem s krásnou ženou.", "I danced with a beautiful woman.", "krásnou ženou")

            CCPhrase("krásné ženy", "beautiful women", "feminine · plural — nominative, accusative and vocative all share the same ending (krásné ženy)")
            CCCase("1. Nominativ", "Krásné ženy seděly u stolu.", "Beautiful women sat at the table.", "Krásné ženy")
            CCCase("2. Genitiv", "Bez krásných žen by tu nebylo veselo.", "Without beautiful women it wouldn't be cheerful here.", "krásných žen")
            CCCase("3. Dativ", "Dal dárky krásným ženám.", "He gave gifts to beautiful women.", "krásným ženám")
            CCCase("4. Akuzativ", "Znám krásné ženy.", "I know beautiful women.", "krásné ženy")
            CCCase("5. Vokativ", "Krásné ženy, pojďte sem!", "Beautiful women, come here!", "Krásné ženy")
            CCCase("6. Lokál", "Mluvíme o krásných ženách.", "We're talking about beautiful women.", "krásných ženách")
            CCCase("7. Instrumentál", "Seděl jsem s krásnými ženami.", "I sat with beautiful women.", "krásnými ženami")

            // ── červené auto ──────────────────────────────────────────────
            CCPhrase("červené auto", "a red car", "neuter · hard noun pattern (auto, like město) + hard adjective (červené) · singular — accusative equals the nominative because the noun is inanimate")
            CCCase("1. Nominativ", "Červené auto stojí před domem.", "A red car is parked in front of the house.", "Červené auto")
            CCCase("2. Genitiv", "Cena červeného auta je vysoká.", "The price of a red car is high.", "červeného auta")
            CCCase("3. Dativ", "Dali jsme červenému autu nový lak.", "We gave the red car a new paint job.", "červenému autu")
            CCCase("4. Akuzativ", "Koupil jsem červené auto.", "I bought a red car.", "červené auto")
            CCCaseNote("5. Vokativ", "Inanimate object — the vocative is identical to the nominative; Czech doesn't address things directly.", "červené auto")
            CCCase("6. Lokál", "Mluvíme o červeném autě.", "We're talking about the red car.", "červeném autě")
            CCCase("7. Instrumentál", "Jel jsem červeným autem.", "I drove there in a red car.", "červeným autem")

            CCPhrase("červená auta", "red cars", "neuter · plural — note the -á ending (červená auta), distinct from the -é used for feminine/masc.-inanimate plurals above")
            CCCase("1. Nominativ", "Červená auta jezdí po silnici.", "Red cars drive on the road.", "Červená auta")
            CCCase("2. Genitiv", "Bez červených aut by ulice vypadala jinak.", "Without red cars the street would look different.", "červených aut")
            CCCase("3. Dativ", "Mechanik dal červeným autům nové pneumatiky.", "The mechanic gave the red cars new tires.", "červeným autům")
            CCCase("4. Akuzativ", "Vidím červená auta.", "I see red cars.", "červená auta")
            CCCaseNote("5. Vokativ", "Inanimate object — identical to the nominative.", "červená auta")
            CCCase("6. Lokál", "Čteme o červených autech.", "We're reading about red cars.", "červených autech")
            CCCase("7. Instrumentál", "Závodili jsme s červenými auty.", "We raced with red cars.", "červenými auty")

            // ── nový telefon ──────────────────────────────────────────────
            CCPhrase("nový telefon", "a new telephone", "masculine inanimate · hard noun pattern (telefon, like hrad) + hard adjective (nový) · singular")
            CCCase("1. Nominativ", "Nový telefon leží na stole.", "A new phone is lying on the table.", "Nový telefon")
            CCCase("2. Genitiv", "Cena nového telefonu mě překvapila.", "The price of the new phone surprised me.", "nového telefonu")
            CCCase("3. Dativ", "Koupil jsem pouzdro k novému telefonu.", "I bought a case for the new phone.", "novému telefonu")
            CCCase("4. Akuzativ", "Koupil jsem si nový telefon.", "I bought myself a new phone.", "nový telefon")
            CCCaseNote("5. Vokativ", "Inanimate object — identical to the nominative.", "nový telefon")
            CCCase("6. Lokál", "Mám hodně fotek v novém telefonu.", "I have a lot of photos in my new phone.", "novém telefonu")
            CCCase("7. Instrumentál", "Natočil jsem video novým telefonem.", "I recorded a video with the new phone.", "novým telefonem")

            // ── cizí student ──────────────────────────────────────────────
            CCPhrase("cizí student", "a foreign student", "masculine animate · hard noun pattern (student, like pán) + soft adjective (cizí) · singular — compare cizí's much flatter endings to chytrý above")
            CCCase("1. Nominativ", "Cizí student se ztratil ve městě.", "A foreign student got lost in the city.", "Cizí student")
            CCCase("2. Genitiv", "Všiml jsem si cizího studenta.", "I noticed a foreign student.", "cizího studenta")
            CCCase("3. Dativ", "Pomohl jsem cizímu studentovi.", "I helped a foreign student.", "cizímu studentovi")
            CCCase("4. Akuzativ", "Potkal jsem cizího studenta.", "I met a foreign student.", "cizího studenta")
            CCCase("5. Vokativ", "Cizí studente, potřebuješ pomoc?", "Foreign student, do you need help?", "Cizí studente")
            CCCase("6. Lokál", "Mluvili jsme o cizím studentovi.", "We talked about a foreign student.", "cizím studentovi")
            CCCase("7. Instrumentál", "Bavím se s cizím studentem.", "I'm chatting with a foreign student.", "cizím studentem")

            // ── moderní kuchyně ───────────────────────────────────────────
            CCPhrase("moderní kuchyně", "a modern kitchen", "feminine · soft noun pattern (kuchyně, like růže) + soft adjective (moderní) · singular — moderní barely changes at all; it's the noun that carries most of the case marking")
            CCCase("1. Nominativ", "Moderní kuchyně vypadá skvěle.", "A modern kitchen looks great.", "Moderní kuchyně")
            CCCase("2. Genitiv", "Bez moderní kuchyně by byt nebyl tak praktický.", "Without a modern kitchen the apartment wouldn't be as practical.", "moderní kuchyně")
            CCCase("3. Dativ", "Přidali jsme moderní kuchyni nové spotřebiče.", "We added new appliances to the modern kitchen.", "moderní kuchyni")
            CCCase("4. Akuzativ", "Navrhli jsme moderní kuchyni.", "We designed a modern kitchen.", "moderní kuchyni")
            CCCaseNote("5. Vokativ", "Inanimate object — identical to the nominative.", "moderní kuchyně")
            CCCase("6. Lokál", "Vařím v moderní kuchyni.", "I cook in a modern kitchen.", "moderní kuchyni")
            CCCase("7. Instrumentál", "Jsem spokojený s moderní kuchyní.", "I'm happy with the modern kitchen.", "moderní kuchyní")

            // ── modré moře ────────────────────────────────────────────────
            CCPhrase("modré moře", "a blue sea", "neuter · soft noun pattern (moře) + hard adjective (modré) · singular — shows that a noun's and an adjective's hardness are independent of each other")
            CCCase("1. Nominativ", "Modré moře se třpytí na slunci.", "The blue sea sparkles in the sun.", "Modré moře")
            CCCase("2. Genitiv", "Voda modrého moře je čistá.", "The water of the blue sea is clean.", "modrého moře")
            CCCase("3. Dativ", "Obdivujeme se modrému moři.", "We admire the blue sea.", "modrému moři")
            CCCase("4. Akuzativ", "Vidíme modré moře.", "We see the blue sea.", "modré moře")
            CCCaseNote("5. Vokativ", "Inanimate object — identical to the nominative.", "modré moře")
            CCCase("6. Lokál", "Plavali jsme v modrém moři.", "We swam in the blue sea.", "modrém moři")
            CCCase("7. Instrumentál", "Loď pluje nad modrým mořem.", "The ship sails over the blue sea.", "modrým mořem")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CCNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun CCPhrase(phrase: String, english: String, info: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp, color = ButtonBlue)) {
                append(phrase)
            }
            withStyle(SpanStyle(fontSize = 16.sp, color = Color.DarkGray)) {
                append("  —  $english")
            }
        }
    )
    Text(
        text = info,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(top = 2.dp, bottom = 6.dp)
    )
    HorizontalDivider(modifier = Modifier.padding(bottom = 6.dp))
}

@Composable
private fun CCCase(caseLabel: String, czech: String, english: String, form: String) {
    Column(modifier = Modifier.padding(vertical = 5.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = caseLabel, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
            Text(text = form, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
        Text(text = czech, fontSize = 15.sp, color = Color.DarkGray, modifier = Modifier.padding(top = 2.dp))
        Text(text = english, fontSize = 13.sp, fontStyle = FontStyle.Italic, color = Color.Gray)
    }
}

@Composable
private fun CCCaseNote(caseLabel: String, note: String, form: String) {
    Column(modifier = Modifier.padding(vertical = 5.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = caseLabel, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
            Text(text = form, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
        Text(
            text = note,
            fontSize = 13.sp,
            fontStyle = FontStyle.Italic,
            color = Color.Gray,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}
