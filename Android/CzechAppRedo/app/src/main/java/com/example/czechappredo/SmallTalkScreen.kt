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
fun SmallTalkScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Small Talk", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // ── How Are You? ──────────────────────────────────────────────
            STKSection("How Are You?")
            STKNote("The key to this section: mít se is completely different from mít. mít alone = to have (Mám auto. = I have a car.). mít se = a fixed reflexive idiom meaning 'to be doing' — how one is getting along. The se changes the meaning entirely. So 'Jak se máš?' literally means 'How do you have yourself?' — which is how Czech expresses 'How are you doing?'")
            STKNote("This is why the answer is 'Mám se dobře.' (I'm doing well.) — not just 'Jsem dobře.' Both are used, but 'Mám se dobře.' is the idiomatic match for 'Jak se máš?'")
            STKRow("Jak se máte?", "How are you doing?", "formal — mít se = to be doing (not mít = to have)")
            STKRow("Jak se máš?", "How are you doing?", "informal")
            STKRow("Mám se dobře.", "I'm doing well.", "mám se = I'm doing; dobře = well")
            STKRow("Mám se špatně.", "I'm not doing well.", "špatně = badly/poorly")
            STKNote("Warning — common trap: do NOT say 'Mám se rád' as an answer to 'Jak se máš?'. The phrase 'mít se rád' is a fixed reflexive idiom meaning 'to like oneself' — a statement of self-esteem / self-love, not well-being. The key word for well-being is dobře (well), not rád (gladly/fondly). So 'Mám se dobře.' = I'm doing well, but 'Mám se rád.' = I like myself.")
            STKRow("Mám se rád. / Mám se ráda.", "I like myself. / I love myself.", "self-esteem statement; rád = male speaker, ráda = female. NOT an answer to 'How are you?'")
            STKRow("Mám rád sebe.", "I like myself.", "more explicit version using sebe (the accusative reflexive pronoun) — same meaning, less ambiguous than the se form above")
            STKRow("A vy?", "And you?", "formal")
            STKRow("A ty?", "And you?", "informal")
            STKNote("mít volno — to have free time / to have time off. volno (n.) = free time, a day off. Use mít volno any time you have no obligations: a free afternoon, a day off work, school holidays, etc.")
            STKRow("Mám volno.", "I have free time. / I'm off.")
            STKRow("Nemám volno.", "I don't have free time. / I'm not off.")
            STKRow("Měl jsem volno.", "I had free time. / I had the day off.", "said by a man; woman: Měla jsem volno.")
            STKRow("Máš dnes volno?", "Do you have the day off today?", "informal; formal: Máte dnes volno?")
            STKNote("Word order note: The standard spoken form drops 'já' — 'Měl jsem volno.' is natural. 'Já jsem měl volno.' is also correct but puts extra emphasis on 'I' (já = emphatic I).")

            // ── Nice To Meet You ──────────────────────────────────────────
            STKSection("Nice To Meet You")
            STKRow("Těší mě.", "Nice to meet you.", "lit. \"It pleases me.\"")

            // ── What Is Your Name? ────────────────────────────────────────
            STKSection("What Is Your Name?")
            STKNote("To say your name in Czech, use jmenovat se (to be called). It conjugates like pracovat: jmenuji/jmenuju se · jmenuješ se · jmenuje se · jmenujeme se · jmenujete se · jmenují se. The se is a clitic that sits in the second slot of the sentence.")
            STKRow("Jmenuji se Gil. / Jmenuju se Gil.", "My name is Gil.", "jmenuji = formal; jmenuju = colloquial — both correct")
            STKRow("Jak se jmenuješ?", "What is your name?", "informal")
            STKRow("Jak se jmenujete?", "What is your name?", "formal / addressing a group")
            STKRow("Jak se jmenuje?", "What is his / her name?", "3rd person singular — same form for he and she")
            STKRow("Jak se jmenují?", "What are their names?", "3rd person plural")
            STKRow("Jmenuje se Tomáš.", "His name is Tomáš.")
            STKRow("Jmenuje se Marie.", "Her name is Marie.")
            STKRow("Jak se jmenuje tvůj bratr?", "What is your brother's name?", "informal")
            STKRow("Jak se jmenují tví rodiče?", "What are your parents' names?", "informal")

            // ── Where Are You From? ───────────────────────────────────────
            STKSection("Where Are You From?")
            STKRow("Odkud jste?", "Where are you from?", "formal")
            STKRow("Odkud jsi?", "Where are you from?", "informal")
            STKRow("Jsem z ...", "I'm from ...", "uses Genitive — the country name changes its ending. Example: Jsem z Anglie.  (I'm from England.)")

            // ── Do You Study / Work? ──────────────────────────────────────
            STKSection("Do You Study? Do You Work?")
            STKNote("Verbs like studovat and pracovat can form the \"I\" (já) in two ways: -uji is the formal, written standard; -uju is the colloquial, spoken alternative. You will hear both — neither is wrong, but -uju is far more common in everyday conversation.")
            STKRow("Studujete?", "Do you study?", "formal")
            STKRow("Studuješ?", "Do you study?", "informal")
            STKRow("Pracujete?", "Do you work?", "formal")
            STKRow("Pracuješ?", "Do you work?", "informal")
            STKRow("Studuji v ...", "I study in ...", "formal / written. Uses Locative — city name changes ending. Example: Studuji v Praze.  (I study in Prague.)")
            STKRow("Studuju v ...", "I study in ...", "colloquial / spoken alternative. Example: Studuju v Praze.")
            STKRow("Pracuji v ...", "I work in ...", "formal / written. Uses Locative. Example: Pracuji v Brně.  (I work in Brno.)")
            STKRow("Pracuju v ...", "I work in ...", "colloquial / spoken alternative. Example: Pracuju v Brně.")

            // ── Asking Questions Politely ─────────────────────────────────
            STKSection("Asking Questions Politely")
            STKNote("In Czech, using the negative form of a verb in a question softens it — like saying \"You wouldn't happen to know...?\" instead of \"Do you know...?\" This is very common when asking strangers for help, especially for directions.")
            STKNote("Vědět (to know) conjugates: vím · víš · ví · víme · víte · vědí. Adding ne- gives: nevím · nevíš · neví · nevíte...")
            STKRow("Nevíte, kde je...?", "Would you happen to know where ... is?", "formal — use with strangers or in polite situations")
            STKRow("Nevíš, kde je...?", "Do you know where ... is?", "informal — lit. \"Don't you know where ... is?\"")
            STKRow("Nevíte, kde je nádraží?", "Would you happen to know where the train station is?", "example with a stranger (formal)")
            STKRow("Nevíš, kde je pošta?", "Do you know where the post office is?", "example with a friend (informal)")

            // ── At a Restaurant ───────────────────────────────────────────
            STKSection("At a Restaurant")
            STKRow("jídelní lístek", "menu (the printed menu card)", "also commonly just called menu — a borrowed word")
            STKRow("menu", "set menu / today's lunch special", "in Czech restaurants, 'menu' often means a fixed-price lunch deal, not the full menu card")
            STKRow("koruna", "1 crown", "1 koruna česká (Kč) — the Czech currency")
            STKRow("koruny", "2–4 crowns", "e.g. 2 koruny, 3 koruny, 4 koruny")
            STKRow("korun", "5 or more crowns", "e.g. 5 korun, 60 korun, 200 korun")
            STKNote("The rule: 1 → koruna,  2–4 → koruny,  5 or more → korun. Example: Zmrzlina stojí 60 korun. (The ice cream costs 60 crowns.)")
            STKRow("To stojí 60 korun.", "It costs 60 crowns.")
            STKRow("Zaplatím.", "I will pay. / I'd like to pay.", "from zaplatit (to pay). Common when asking for the bill.")
            STKRow("Zaplatím kartou.", "I will pay by card.")
            STKRow("Zaplatím hotově.", "I will pay in cash.")
            STKNote("Ordering — the waiter asks, you answer with dám si (I'll have) + accusative:")
            STKRow("Co si dáte? / Co si dáš?", "What would you like (to order)?", "formal / informal — the standard waiter question; lit. 'What will you give yourself?'")
            STKRow("Co si dáte k jídlu?", "What would you like to eat?")
            STKRow("Co si dáte k pití?", "What would you like to drink?", "also heard: Co si dáte na pití? / Co to bude k pití?")
            STKRow("Dám si steak.", "I'll have the steak.", "Czech has no 'the' — dám si + accusative: Dám si polévku. = I'll have the soup.")
            STKRow("Nemáte to přesně? / Máte to přesně?", "Do you have exact change?", "what a cashier asks; the negative form is the softer, more polite one (see Asking Questions Politely)")
            STKRow("Máte drobné?", "Do you have small change?", "drobné = coins / small change")

            // ── How Old Are You? ──────────────────────────────────────────
            STKSection("How Old Are You?")
            STKNote("Czech uses the dative pronoun + je (is) + number + rok / roky / let. Rule: 1 → rok,  2–4 → roky,  5 or more → let.")
            STKRow("Kolik je ti let?", "How old are you?", "informal")
            STKRow("Kolik je vám let?", "How old are you?", "formal")
            STKRow("Je mi X let.", "I am X years old.")
            STKRow("Je ti X let.", "You are X years old.", "informal")
            STKRow("Je mu X let.", "He is X years old.")
            STKRow("Je jí X let.", "She is X years old.")
            STKRow("Je nám X let.", "We are X years old.")
            STKRow("Je vám X let.", "You are X years old.", "formal / plural")
            STKRow("Je jim X let.", "They are X years old.")
            STKNote("Examples showing the rok / roky / let rule:")
            STKRow("Je mu 1 rok.", "He is 1 year old.")
            STKRow("Je jí 3 roky.", "She is 3 years old.")
            STKRow("Je mi 25 let.", "I am 25 years old.")
            STKRow("Je vám 50 let?", "Are you 50 years old?", "formal")

            // ── Common Phrases ────────────────────────────────────────────
            STKSection("Common Phrases")
            STKRow("mám rád", "I like (said by a man)", "literally 'I have gladly'; rád is the masculine form")
            STKRow("mám ráda", "I like (said by a woman)", "ráda is the feminine form; the verb mám does not change")
            STKRow("nemám rád / nemám ráda", "I don't like", "nemám = I don't have; rád / ráda same rule as above")
            STKRow("dám si", "I'll have (ordering food or drink)", "literally 'I'll give myself'; e.g. Dám si polévku. = I'll have the soup.")
            STKRow("nedám si", "I won't have / I'll pass", "literally 'I won't give myself'")
            STKNote("To say you like to do something, use rád (man) or ráda (woman) + conjugated verb. This is different from mám rád + noun — here a verb follows directly:")
            STKRow("Rád piju kávu.", "I like to drink coffee.", "said by a man; rád marks gender, verb is conjugated normally")
            STKRow("Ráda piju čaj.", "I like to drink tea.", "said by a woman; ráda is the feminine form")
            STKNote("To say you don't like to do something, use nerad (man) or nerada (woman) + conjugated verb. Written as one word:")
            STKRow("Nerad uklízím.", "I don't like to clean.", "said by a man; infinitive: uklízet")
            STKRow("Nerada jezdím autobusem.", "I don't like to go by bus.", "said by a woman; infinitive: jezdit")

            // ── Saying You Like or Love Someone ───────────────────────────
            STKSection("Saying You Like or Love Someone")
            STKNote("Czech has three different expressions for liking or loving — each carries a different shade of meaning and uses a different verb structure.")

            STKNote("Líbit se — to appeal to / to be attractive to someone")
            STKNote("Verb: líbit se (imperfective, reflexive). Structure: [subject] + se + [dative] + líbí. The subject is the thing or person being liked; the dative is the person doing the liking. This expression also works for objects — not just people.")
            STKRow("Líbíš se mi", "I like you / I find you attractive")
            STKRow("To triko se mi líbí", "I like that shirt", "yes — líbit se works for objects too")
            STKRow("Líbí se mi to auto", "I like that car")
            STKRow("Nelíbíš se mi", "I don't like you / I don't find you attractive", "add ne- prefix to negate")
            STKRow("Navzájem se si nelíbí", "They don't appeal to each other", "navzájem = each other; si = reflexive dative")

            STKNote("Mít rád / ráda — to like / to be fond of")
            STKNote("Verb phrase: mít (to have) + rád — an adjective that agrees with the speaker's gender. Rád (male speaker), ráda (female speaker), rádi (male/mixed group), rády (female group). Works for both people and things.")
            STKRow("Mám tě rád", "I like you", "male speaker")
            STKRow("Mám tě ráda", "I like you", "female speaker")
            STKRow("Mám rád kávu", "I like coffee", "male speaker — works for things too")
            STKRow("Mám ráda kávu", "I like coffee", "female speaker")
            STKRow("Nemám tě rád / ráda", "I don't like you", "negate with ne- prefix on mám")
            STKRow("Nemají se rádi", "They don't like each other", "reflexive se makes it reciprocal")

            STKNote("Milovat — to love")
            STKNote("Verb: milovat (imperfective). The strongest expression — romantic or deep love. Conjugates regularly: miluju / miluješ / miluje / milujeme / milujete / milují.")
            STKRow("Miluju tě", "I love you")
            STKRow("Miluješ mě?", "Do you love me?")
            STKRow("Nemiluju tě", "I don't love you", "ne- prefix negates the verb")
            STKRow("Nemilují se", "They don't love each other", "reflexive se = each other")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun STKSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun STKNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun STKRow(czech: String, english: String, note: String = "") {
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
