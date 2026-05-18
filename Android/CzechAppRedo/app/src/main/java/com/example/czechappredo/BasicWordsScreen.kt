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
fun BasicWordsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Basic Words, Expressions & Greetings", fontSize = 15.sp, fontWeight = FontWeight.Bold) },
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

            // ── Yes & No ──────────────────────────────────────────────────
            BWSection("Yes & No")
            BWRow("ano", "yes", "formal / written form")
            BWRow("jo", "yes", "informal, spoken")
            BWRow("ne", "no")

            // ── Connectors ────────────────────────────────────────────────
            BWSection("Connectors")
            BWRow("a", "and")
            BWRow("nebo", "or")
            BWRow("ale", "but")
            BWRow("tak", "so / well / then")
            BWRow("i", "and / also / even", "literary variant of 'a'; i...i = both...and; e.g. i já = me too")
            BWRow("znovu", "again", "also: zase (colloquial), zas (very short form)")
            BWRow("zase / zas", "again (colloquial)", "variant of znovu; zas is the clipped form")
            BWRow("anebo", "or (emphatic)", "stronger variant of nebo; implies 'or else'")
            BWRow("jako", "as a / like", "jako + role/profession: Pracuji jako programátor. (man) / Pracuji jako manažerka. (woman). Also means \"like\" in comparisons: Je to jako doma. = It's like home.")
            BWNote("In Czech, job descriptions use no article after jako — just the noun directly. Women use the feminine form of the profession: manažer (m.) → manažerka (f.), programátor (m.) → programátorka (f.).")
            BWRow("pak", "then / afterwards", "common in spoken Czech — temporal sequence")
            BWRow("potom", "then / afterwards", "neutral variant; pak and potom are largely interchangeable")
            BWNote("More formal alternatives: nato = right after that; poté = after that (written/formal). Example: Nejdřív jsem pracoval, pak jsem šel domů. — First I worked, then I went home.")

            // ── Greetings ─────────────────────────────────────────────────
            BWSection("Greetings")
            BWRow("Dobré ráno", "Good morning")
            BWRow("Dobrý den", "Good day / Hello", "standard greeting throughout the day")
            BWRow("Dobré odpoledne", "Good afternoon")
            BWRow("Dobrý večer", "Good evening")
            BWRow("Dobrou noc", "Good night")
            BWRow("Ahoj", "Hi / Bye", "informal — used among friends")
            BWRow("Čau", "Hi / Bye", "very informal, from Italian ciao")
            BWRow("Nashledanou", "Goodbye", "formal farewell")

            // ── Special Occasion Greetings ────────────────────────────────
            BWSection("Special Occasion Greetings")
            BWRow("Všechno nejlepší k narozeninám!", "Happy Birthday!", "lit. \"All the best for your birthday\" — the most common form")
            BWRow("Šťastné narozeniny!", "Happy Birthday!", "more direct form")
            BWRow("Veselé Vánoce!", "Merry Christmas!")
            BWRow("Šťastný Nový rok!", "Happy New Year!")
            BWRow("Veselé svátky!", "Happy Holidays! / Merry Celebrations!")
            BWRow("Šťastné svátky!", "Happy Holidays!")
            BWNote("Vánoce (Christmas) is always plural in Czech. Veselé and Šťastné agree with the plural: Veselé Vánoce! The same applies to svátky (holidays / celebrations).")

            // ── Pleasantries ──────────────────────────────────────────────
            BWSection("Pleasantries")
            BWRow("Prosím", "Please / You're welcome / Here you go", "extremely versatile — context tells you which meaning")
            BWRow("Děkuji", "Thank you", "formal")
            BWRow("Děkuju", "Thank you", "colloquial — same meaning, more casual")
            BWNote("Like the -uji/-uju distinction seen in verbs (studuji/studuju), děkuji and děkuju are fully interchangeable. Děkuju is very common in everyday spoken Czech.")
            BWRow("Děkuji mockrát / Mockrát děkuji", "Thank you very much")
            BWRow("Děkuji moc / Děkuju moc", "Thank you very much", "colloquial; moc = a lot / very much used informally as an adverb. Word order can be reversed: Moc děkuji / Moc děkuju. In formal or written Czech, mockrát or velmi is preferred.")
            BWRow("Díky moc", "Thanks a lot", "very casual; combines díky (informal thanks) with moc (a lot). Common in everyday spoken Czech.")
            BWRow("Díky", "Thanks", "informal")
            BWRow("Promiňte", "Excuse me / Sorry", "formal")
            BWRow("Promiň", "Excuse me / Sorry", "informal")
            BWNote("You're welcome — responding to děkuji:")
            BWRow("Prosím.", "You're welcome.", "when said in response to děkuji, prosím means 'you're welcome'. It is the most natural and common reply.")
            BWRow("Není zač.", "Don't mention it.", "lit. 'There is nothing to thank for.' Very common in everyday Czech.")
            BWRow("Rádo se stalo.", "My pleasure.", "lit. 'It happened gladly.' Slightly warmer / more formal.")

            // ── How Are You? ──────────────────────────────────────────────
            BWSection("How Are You?")
            BWNote("The key to this section: mít se is completely different from mít. mít alone = to have (Mám auto. = I have a car.). mít se = a fixed reflexive idiom meaning 'to be doing' — how one is getting along. The se changes the meaning entirely. So 'Jak se máš?' literally means 'How do you have yourself?' — which is how Czech expresses 'How are you doing?'")
            BWNote("This is why the answer is 'Mám se dobře.' (I'm doing well.) — not just 'Jsem dobře.' Both are used, but 'Mám se dobře.' is the idiomatic match for 'Jak se máš?'")
            BWRow("Jak se máte?", "How are you doing?", "formal — mít se = to be doing (not mít = to have)")
            BWRow("Jak se máš?", "How are you doing?", "informal")
            BWRow("Mám se dobře.", "I'm doing well.", "mám se = I'm doing; dobře = well")
            BWRow("Mám se špatně.", "I'm not doing well.", "špatně = badly/poorly")
            BWRow("A vy?", "And you?", "formal")
            BWRow("A ty?", "And you?", "informal")
            BWNote("mít volno — to have free time / to have time off. volno (n.) = free time, a day off. Use mít volno any time you have no obligations: a free afternoon, a day off work, school holidays, etc.")
            BWRow("Mám volno.", "I have free time. / I'm off.")
            BWRow("Nemám volno.", "I don't have free time. / I'm not off.")
            BWRow("Měl jsem volno.", "I had free time. / I had the day off.", "said by a man; woman: Měla jsem volno.")
            BWRow("Máš dnes volno?", "Do you have the day off today?", "informal; formal: Máte dnes volno?")
            BWNote("Word order note: The standard spoken form drops 'já' — 'Měl jsem volno.' is natural. 'Já jsem měl volno.' is also correct but puts extra emphasis on 'I' (já = emphatic I).")

            // ── Nice To Meet You ──────────────────────────────────────────
            BWSection("Nice To Meet You")
            BWRow("Těší mě.", "Nice to meet you.", "lit. \"It pleases me.\"")

            // ── What Is Your Name? ────────────────────────────────────────
            BWSection("What Is Your Name?")
            BWNote("To say your name in Czech, use jmenovat se (to be called). It conjugates like pracovat: jmenuji/jmenuju se · jmenuješ se · jmenuje se · jmenujeme se · jmenujete se · jmenují se. The se is a clitic that sits in the second slot of the sentence.")
            BWRow("Jmenuji se Gil. / Jmenuju se Gil.", "My name is Gil.", "jmenuji = formal; jmenuju = colloquial — both correct")
            BWRow("Jak se jmenuješ?", "What is your name?", "informal")
            BWRow("Jak se jmenujete?", "What is your name?", "formal / addressing a group")
            BWRow("Jak se jmenuje?", "What is his / her name?", "3rd person singular — same form for he and she")
            BWRow("Jak se jmenují?", "What are their names?", "3rd person plural")
            BWRow("Jmenuje se Tomáš.", "His name is Tomáš.")
            BWRow("Jmenuje se Marie.", "Her name is Marie.")
            BWRow("Jak se jmenuje tvůj bratr?", "What is your brother's name?", "informal")
            BWRow("Jak se jmenují tví rodiče?", "What are your parents' names?", "informal")

            // ── Where Are You From? ───────────────────────────────────────
            BWSection("Where Are You From?")
            BWRow("Odkud jste?", "Where are you from?", "formal")
            BWRow("Odkud jsi?", "Where are you from?", "informal")
            BWRow("Jsem z ...", "I'm from ...", "uses Genitive — the country name changes its ending. Example: Jsem z Anglie.  (I'm from England.)")

            // ── Do You Study / Work? ──────────────────────────────────────
            BWSection("Do You Study? Do You Work?")
            BWNote("Verbs like studovat and pracovat can form the \"I\" (já) in two ways: -uji is the formal, written standard; -uju is the colloquial, spoken alternative. You will hear both — neither is wrong, but -uju is far more common in everyday conversation.")
            BWRow("Studujete?", "Do you study?", "formal")
            BWRow("Studuješ?", "Do you study?", "informal")
            BWRow("Pracujete?", "Do you work?", "formal")
            BWRow("Pracuješ?", "Do you work?", "informal")
            BWRow("Studuji v ...", "I study in ...", "formal / written. Uses Locative — city name changes ending. Example: Studuji v Praze.  (I study in Prague.)")
            BWRow("Studuju v ...", "I study in ...", "colloquial / spoken alternative. Example: Studuju v Praze.")
            BWRow("Pracuji v ...", "I work in ...", "formal / written. Uses Locative. Example: Pracuji v Brně.  (I work in Brno.)")
            BWRow("Pracuju v ...", "I work in ...", "colloquial / spoken alternative. Example: Pracuju v Brně.")

            // ── Asking Questions Politely ─────────────────────────────────
            BWSection("Asking Questions Politely")
            BWNote("In Czech, using the negative form of a verb in a question softens it — like saying \"You wouldn't happen to know...?\" instead of \"Do you know...?\" This is very common when asking strangers for help, especially for directions.")
            BWNote("Vědět (to know) conjugates: vím · víš · ví · víme · víte · vědí. Adding ne- gives: nevím · nevíš · neví · nevíte...")
            BWRow("Nevíte, kde je...?", "Would you happen to know where ... is?", "formal — use with strangers or in polite situations")
            BWRow("Nevíš, kde je...?", "Do you know where ... is?", "informal — lit. \"Don't you know where ... is?\"")
            BWRow("Nevíte, kde je nádraží?", "Would you happen to know where the train station is?", "example with a stranger (formal)")
            BWRow("Nevíš, kde je pošta?", "Do you know where the post office is?", "example with a friend (informal)")

            // ── At a Restaurant ───────────────────────────────────────────
            BWSection("At a Restaurant")
            BWRow("jídelní lístek", "menu (the printed menu card)", "also commonly just called menu — a borrowed word")
            BWRow("menu", "set menu / today's lunch special", "in Czech restaurants, 'menu' often means a fixed-price lunch deal, not the full menu card")
            BWRow("koruna", "1 crown", "1 koruna česká (Kč) — the Czech currency")
            BWRow("koruny", "2–4 crowns", "e.g. 2 koruny, 3 koruny, 4 koruny")
            BWRow("korun", "5 or more crowns", "e.g. 5 korun, 60 korun, 200 korun")
            BWNote("The rule: 1 → koruna,  2–4 → koruny,  5 or more → korun. Example: Zmrzlina stojí 60 korun. (The ice cream costs 60 crowns.)")
            BWRow("To stojí 60 korun.", "It costs 60 crowns.")
            BWRow("Zaplatím.", "I will pay. / I'd like to pay.", "from zaplatit (to pay). Common when asking for the bill.")
            BWRow("Zaplatím kartou.", "I will pay by card.")
            BWRow("Zaplatím hotově.", "I will pay in cash.")

            // ── How Old Are You? ──────────────────────────────────────────
            BWSection("How Old Are You?")
            BWNote("Czech uses the dative pronoun + je (is) + number + rok / roky / let. Rule: 1 → rok,  2–4 → roky,  5 or more → let.")
            BWRow("Kolik je ti let?", "How old are you?", "informal")
            BWRow("Kolik je vám let?", "How old are you?", "formal")
            BWRow("Je mi X let.", "I am X years old.")
            BWRow("Je ti X let.", "You are X years old.", "informal")
            BWRow("Je mu X let.", "He is X years old.")
            BWRow("Je jí X let.", "She is X years old.")
            BWRow("Je nám X let.", "We are X years old.")
            BWRow("Je vám X let.", "You are X years old.", "formal / plural")
            BWRow("Je jim X let.", "They are X years old.")
            BWNote("Examples showing the rok / roky / let rule:")
            BWRow("Je mu 1 rok.", "He is 1 year old.")
            BWRow("Je jí 3 roky.", "She is 3 years old.")
            BWRow("Je mi 25 let.", "I am 25 years old.")
            BWRow("Je vám 50 let?", "Are you 50 years old?", "formal")

            // ── Common Phrases ────────────────────────────────────────────
            BWSection("Common Phrases")
            BWRow("mám rád", "I like (said by a man)", "literally 'I have gladly'; rád is the masculine form")
            BWRow("mám ráda", "I like (said by a woman)", "ráda is the feminine form; the verb mám does not change")
            BWRow("nemám rád / nemám ráda", "I don't like", "nemám = I don't have; rád / ráda same rule as above")
            BWRow("dám si", "I'll have (ordering food or drink)", "literally 'I'll give myself'; e.g. Dám si polévku. = I'll have the soup.")
            BWRow("nedám si", "I won't have / I'll pass", "literally 'I won't give myself'")
            BWNote("To say you like to do something, use rád (man) or ráda (woman) + conjugated verb. This is different from mám rád + noun — here a verb follows directly:")
            BWRow("Rád piju kávu.", "I like to drink coffee.", "said by a man; rád marks gender, verb is conjugated normally")
            BWRow("Ráda piju čaj.", "I like to drink tea.", "said by a woman; ráda is the feminine form")
            BWNote("To say you don't like to do something, use nerad (man) or nerada (woman) + conjugated verb. Written as one word:")
            BWRow("Nerad uklízím.", "I don't like to clean.", "said by a man; infinitive: uklízet")
            BWRow("Nerada jezdím autobusem.", "I don't like to go by bus.", "said by a woman; infinitive: jezdit")

            // ── Welcome ───────────────────────────────────────────────────
            BWSection("Welcome")
            BWRow("Vítejte!", "Welcome!", "formal or addressing a group")
            BWRow("Vítej!", "Welcome!", "informal, addressing one person")
            BWRow("Vítejte v Praze!", "Welcome to Prague!", "uses Locative: Praha → Praze")
            BWRow("Vítejte v České republice!", "Welcome to the Czech Republic!")

            // ── Saying You Like or Love Someone ───────────────────────────
            BWSection("Saying You Like or Love Someone")
            BWNote("Czech has three different expressions for liking or loving — each carries a different shade of meaning and uses a different verb structure.")

            BWNote("Líbit se — to appeal to / to be attractive to someone")
            BWNote("Verb: líbit se (imperfective, reflexive). Structure: [subject] + se + [dative] + líbí. The subject is the thing or person being liked; the dative is the person doing the liking. This expression also works for objects — not just people.")
            BWRow("Líbíš se mi", "I like you / I find you attractive")
            BWRow("To triko se mi líbí", "I like that shirt", "yes — líbit se works for objects too")
            BWRow("Líbí se mi to auto", "I like that car")
            BWRow("Nelíbíš se mi", "I don't like you / I don't find you attractive", "add ne- prefix to negate")
            BWRow("Navzájem se si nelíbí", "They don't appeal to each other", "navzájem = each other; si = reflexive dative")

            BWNote("Mít rád / ráda — to like / to be fond of")
            BWNote("Verb phrase: mít (to have) + rád — an adjective that agrees with the speaker's gender. Rád (male speaker), ráda (female speaker), rádi (male/mixed group), rády (female group). Works for both people and things.")
            BWRow("Mám tě rád", "I like you", "male speaker")
            BWRow("Mám tě ráda", "I like you", "female speaker")
            BWRow("Mám rád kávu", "I like coffee", "male speaker — works for things too")
            BWRow("Mám ráda kávu", "I like coffee", "female speaker")
            BWRow("Nemám tě rád / ráda", "I don't like you", "negate with ne- prefix on mám")
            BWRow("Nemají se rádi", "They don't like each other", "reflexive se makes it reciprocal")

            BWNote("Milovat — to love")
            BWNote("Verb: milovat (imperfective). The strongest expression — romantic or deep love. Conjugates regularly: miluju / miluješ / miluje / milujeme / milujete / milují.")
            BWRow("Miluju tě", "I love you")
            BWRow("Miluješ mě?", "Do you love me?")
            BWRow("Nemiluju tě", "I don't love you", "ne- prefix negates the verb")
            BWRow("Nemilují se", "They don't love each other", "reflexive se = each other")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun BWSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun BWNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun BWRow(czech: String, english: String, note: String = "") {
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
