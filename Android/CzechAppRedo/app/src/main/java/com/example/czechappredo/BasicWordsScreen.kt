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
            BWRow("taky / také", "also / too", "taky is the colloquial spoken form; také is the formal/written form. Já taky. = Me too.")
            BWRow("tak", "so / well / then")
            BWRow("i", "and / also / even", "literary variant of 'a'; i...i = both...and; e.g. i já = me too")
            BWRow("znovu / znova", "again", "znovu is the standard written form; znova is a colloquial variant — more common in Moravia and in spoken Czech. Both are fully correct and interchangeable.")
            BWRow("zase / zas", "again (colloquial)", "another common variant of znovu; zas is the clipped spoken form. Often carries a slight nuance of 'yet again' or 'once more', though in everyday speech it's essentially interchangeable.")
            BWRow("anebo", "or (emphatic)", "stronger variant of nebo; implies 'or else'")
            BWRow("jako", "as a / like", "jako + role/profession: Pracuji jako programátor. (man) / Pracuji jako manažerka. (woman). Also means \"like\" in comparisons: Je to jako doma. = It's like home.")
            BWNote("In Czech, job descriptions use no article after jako — just the noun directly. Women use the feminine form of the profession: manažer (m.) → manažerka (f.), programátor (m.) → programátorka (f.).")
            BWRow("pak", "then / afterwards", "common in spoken Czech — temporal sequence")
            BWRow("potom", "then / afterwards", "neutral variant; pak and potom are largely interchangeable")
            BWNote("More formal alternatives: nato = right after that; poté = after that (written/formal). Example: Nejdřív jsem pracoval, pak jsem šel domů. — First I worked, then I went home.")

            // ── Aby — So That / In Order To ──────────────────────────────
            BWSection("Aby — So That / In Order To")
            BWNote("aby is the Czech equivalent of Russian чтобы — a conjunction that introduces a purpose or goal clause ('so that' / 'in order to'). Like Russian чтобы, it pairs with a verb in a past-tense-shaped form: aby conjugates for person and number, and the verb that follows takes its L-participle (the same form used to build the past tense, e.g. dělal, pomohl, mohla), agreeing in gender and number with the subject of that clause.")
            BWRow("abych", "so that I…", "1st person singular: Přišel jsem, abych ti pomohl. = I came so that I could help you.")
            BWRow("abys", "so that you…", "2nd person singular, informal: Volám ti, abys nezapomněl. = I'm calling you so you won't forget.")
            BWRow("aby", "so that he/she/it…", "3rd person singular: Naučila se česky, aby mohla číst knihy v originále. = She learned Czech so she could read books in the original.")
            BWRow("abychom", "so that we…", "1st person plural: Sešli jsme se, abychom to probrali. = We met up so that we could discuss it.")
            BWRow("abyste", "so that you…", "2nd person plural / formal: Pospěšte si, abyste nepřišli pozdě. = Hurry up so you won't be late.")
            BWRow("aby", "so that they…", "3rd person plural — same form as 3rd person singular: Zavřeli okno, aby jim nebyla zima. = They closed the window so they wouldn't be cold.")
            BWNote("More examples: Zavřel okno, aby mu nebyla zima. = He closed the window so he wouldn't be cold (mu = dative 'to him', nebyla agrees with the neuter-feeling 'zima' as feminine). Chci, abys byl šťastný. = I want you to be happy (lit. 'I want, so-that-you were happy' — Czech often uses aby where English uses an infinitive after verbs like chtít).")
            BWNote("See also: Connecting Words → Connecting Sentences (Learning hub) for the full conjugation table and more sentence patterns.")

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
            BWRow("Uvidíme se později", "See you later", "lit. \"we'll see each other later\"; colloquial alternative: Tak zatím / Zatím")
            BWRow("Uvidíme se v pondělí", "See you on Monday", "v + day name = \"on [day]\"")
            BWRow("Uvidíme se příště", "See you next time", "příště = next time")
            BWRow("Uvidíme se brzy", "See you soon", "more common alternative in everyday speech: Na shledanou / Ahoj")

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
            BWNote("Warning — common trap: do NOT say 'Mám se rád' as an answer to 'Jak se máš?'. The phrase 'mít se rád' is a fixed reflexive idiom meaning 'to like oneself' — a statement of self-esteem / self-love, not well-being. The key word for well-being is dobře (well), not rád (gladly/fondly). So 'Mám se dobře.' = I'm doing well, but 'Mám se rád.' = I like myself.")
            BWRow("Mám se rád. / Mám se ráda.", "I like myself. / I love myself.", "self-esteem statement; rád = male speaker, ráda = female. NOT an answer to 'How are you?'")
            BWRow("Mám rád sebe.", "I like myself.", "more explicit version using sebe (the accusative reflexive pronoun) — same meaning, less ambiguous than the se form above")
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

            // ── There Is / There Was ─────────────────────────────────────
            BWSection("There Is / There Was")
            BWNote("Czech has no expletive 'there' like English 'there is' or French 'il y a'. You simply use the verb být (to be) — je / jsou for present, byl / byla / bylo / byli / byly for past — and put the location wherever it fits naturally in the sentence. Word order is flexible; the location often comes first.")
            BWRow("Na stole je kniha.", "There is a book on the table.", "lit. 'On the table is a book.' — je = is, singular")
            BWRow("Na stole jsou knihy.", "There are books on the table.", "jsou = are, plural")
            BWRow("V lednici je mléko.", "There is milk in the fridge.")
            BWRow("V parku jsou děti.", "There are children in the park.")
            BWRow("Není tu nikdo.", "There is no one here.", "není = is not; double negation with nikdo (no one). tu = here.")
            BWRow("Nejsou tu žádné židle.", "There are no chairs here.", "nejsou = are not; žádné = none / not any")
            BWNote("Past tense agrees with the gender and number of the subject (the thing that 'is there'). The l-participle of být takes 4 endings: byl (m.), byla (f.), bylo (n.), byli/byly (pl.).")
            BWRow("Na stole byla kniha.", "There was a book on the table.", "kniha is feminine → byla")
            BWRow("Na stole byl chléb.", "There was bread on the table.", "chléb is masculine → byl")
            BWRow("Na stole bylo jablko.", "There was an apple on the table.", "jablko is neuter → bylo")
            BWRow("Na stole byly knihy.", "There were books on the table.", "knihy is feminine plural → byly")
            BWRow("V parku byli lidé.", "There were people in the park.", "lidé is animate masculine plural → byli (with -i)")
            BWNote("Optional intensifier tam (there) can be added for emphasis or to point at a specific place: Byla tam kniha. = There was a book (there). The verbs existovat (to exist) and nacházet se (to be located) are more formal alternatives, used in writing.")

            // ── Maybe, Something, Anything ───────────────────────────────
            BWSection("Maybe, Something, Anything")
            BWNote("Czech indefinite words for uncertain quantities and possibilities. Note the ně- prefix: it marks indefiniteness (něco = something, někdo = someone, někde = somewhere). Compare to the negative ni- prefix already seen: nic / nikdo / nikde.")
            BWRow("možná", "maybe / perhaps", "the most common word for 'maybe'. Možná přijdu. = Maybe I'll come. Goes at the start of the clause or after the verb. Neutral in tone.")
            BWRow("snad", "perhaps / hopefully", "softer than možná — often carries a hopeful tone. Snad to půjde. = Hopefully it'll work out.")
            BWRow("asi", "probably / approximately", "expresses likelihood. Asi přijde. = He'll probably come. Also used for rough numbers: asi deset = about ten.")
            BWRow("něco", "something", "Něco vidím. = I see something. Něco k jídlu? = Something to eat?")
            BWRow("někdo", "someone / somebody", "Někdo zvoní. = Someone is ringing (the bell).")
            BWRow("někde", "somewhere", "Někde jsem to viděl. = I saw it somewhere.")
            BWRow("někdy", "sometimes / sometime", "Někdy chodím do kina. = I sometimes go to the cinema. Also: Přijď někdy. = Come by sometime.")
            BWRow("nějaký", "some / a / some kind of", "indefinite adjective; agrees in gender. Nějaký člověk volal. = Some person called. Máš nějakou otázku? = Do you have a (any) question?")
            BWNote("Pattern summary: ně- = indefinite (some-), ni- = negative (no-), vše-/každý = universal (every-). Examples: něco / nic / všechno (something / nothing / everything), někdo / nikdo / každý (someone / no one / everyone), někde / nikde / všude (somewhere / nowhere / everywhere), někdy / nikdy / vždy (sometimes / never / always). Learning these triplets is one of the fastest ways to expand your everyday vocabulary.")

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

            // ── Today, Yesterday, Tomorrow ───────────────────────────────
            BWSection("Today, Yesterday, Tomorrow")
            BWRow("dnes", "today")
            BWRow("dneska", "today", "colloquial form, interchangeable with dnes in everyday speech")
            BWRow("včera", "yesterday")
            BWRow("zítra", "tomorrow")
            BWNote("Czech has no articles. Time words go directly into the sentence: Jdu tam dnes. = I'm going there today. Přijdeš zítra? = Will you come tomorrow?")

            // ── Before, After, Previously & In The Future ────────────────
            BWSection("Before, After, Previously & In The Future")
            BWRow("dříve", "before / previously / earlier", "Dříve jsem bydlel v Brně. = I used to live in Brno before / I lived in Brno earlier.")
            BWRow("předtím", "before that / beforehand", "Předtím jsem o tom nevěděl. = Before that, I didn't know about it. See also Connecting Sentences for než / dříve než / předtím než (\"before\" + a full clause).")
            BWRow("před + instr.", "before (preposition — time or place)", "před obědem = before lunch; před domem = in front of the house. před takes the Instrumental case.")
            BWRow("po + loc.", "after (preposition)", "po obědě = after lunch. po takes the Locative case. The adverb 'then / afterwards' is already covered by pak / potom above.")
            BWRow("v budoucnu / v budoucnosti", "in the future", "V budoucnu se to změní. = In the future, this will change.")

            // ── Here, There & Right Now ───────────────────────────────────
            BWSection("Here, There & Right Now")
            BWRow("tady", "here / right here", "also: tu (slightly more formal); tady is preferred in everyday speech")
            BWRow("tam", "there", "Bydlím tam. = I live there. Jdi tam. = Go there. Pairs with tady the way 'here / there' do in English.")
            BWRow("teď", "now", "also: nyní (more formal / written). Teď nemám čas. = I don't have time now.")
            BWRow("právě", "just / right now", "Právě jsem přišel domů. = I just came home. Also means 'exactly': Právě proto. = Exactly because of that.")
            BWRow("právě teď", "just now / right now", "combines právě (just/exactly) + teď (now)")
            BWRow("hned", "right away / immediately", "implies immediacy: Přijdu hned. = I'll come right away.")
            BWRow("hned teď", "right now (this very moment)", "slightly stronger than právě teď — right this instant")
            BWRow("přímo tady", "right here", "přímo = directly / exactly; emphasizes the specific location")

            // ── Still, Already, Yet ──────────────────────────────────────
            BWSection("Still, Already, Yet (ještě / už)")
            BWNote("These two adverbs are the Czech equivalents of Russian ещё and уже. They mark whether an action is ongoing or has finished — together they cover most uses of English 'still', 'already', 'yet', 'anymore', and 'one more'.")
            BWRow("ještě", "still / yet / more", "ongoing or additional. Ještě pracuju. = I'm still working. Ještě jedno pivo. = One more beer. Ještě ne. = Not yet.")
            BWRow("už", "already / now (with negation: anymore)", "completed or changed state. Už jsem doma. = I'm already home. Už nepracuju. = I don't work anymore.")
            BWRow("ještě ne", "not yet", "Ještě ne, počkej chvíli. = Not yet, wait a moment.")
            BWRow("už ano / už jo", "already / yes already", "Už ano. = Yes, already. / Affirmative answer to 'Are you done yet?'")
            BWRow("ještě jednou", "one more time / once more", "Zopakuj to ještě jednou. = Repeat it one more time.")
            BWRow("ještě nikdy", "never yet / never before", "double negative with a negated verb: Ještě nikdy jsem tam nebyl. = I've never been there before.")
            BWRow("už nikdy", "never again", "Už nikdy to neudělám. = I'll never do it again.")
            BWNote("Emphatic negation: combine už with vůbec (at all / in general) to intensify a negative statement into 'not at all' / 'absolutely not'. Here už doesn't mean 'already' — it acts as an intensifying particle that sharpens the negation, much like English 'at all' sharpens 'not'.")
            BWRow("vůbec", "at all / in general", "the actual carrier of the 'at all' meaning; už only intensifies it. Vůbec nerozumím. = I don't understand at all.")
            BWRow("už vůbec ne", "not at all / absolutely not", "Líbí se ti to? — Už vůbec ne! = Do you like it? — Not at all! Stronger and more dismissive than a plain 'ne'.")
            BWRow("už vůbec nerozumím", "I don't understand at all", "Už vůbec nerozumím, co tím myslíš. = I have no idea at all what you mean by that.")
            BWRow("to se mi už vůbec nelíbí", "I don't like that at all", "už vůbec strengthens nelíbí (don't like) into a much stronger rejection than nelíbí se mi alone.")
            BWNote("Mental model: ještě = the situation hasn't changed yet (or there's more to come). už = the situation has changed — something started, or with ne- something stopped. už vůbec ne = a separate, emphatic pattern: už here intensifies vůbec's negation rather than marking a change of state.")

            // ── Suddenly, All Of A Sudden & Totally ───────────────────────
            BWSection("Suddenly, All Of A Sudden & Totally")
            BWRow("náhle", "suddenly", "more formal / written. Náhle přestalo pršet. = It suddenly stopped raining.")
            BWRow("najednou", "suddenly / all of a sudden", "the most common, colloquial word. Najednou začal křičet. = All of a sudden he started shouting. Also means 'all at once / in one go': Udělej to najednou. = Do it all at once.")
            BWRow("úplně", "totally / completely", "a different word from vůbec (at all) — also appears in Adverbs. Souhlasím úplně. = I totally agree.")

            // ── Very — Velmi / Hodně / Moc ───────────────────────────────
            BWSection("Very — Velmi / Hodně / Moc")
            BWNote("Czech has three common words for 'very / a lot', each with a different register:")
            BWRow("velmi", "very", "formal/literary — most natural before adjectives and adverbs: velmi dobrý (very good), velmi rychle (very fast). Rarely used with verbs.")
            BWRow("hodně", "a lot / very", "neutral/colloquial — works before adjectives, adverbs, and also with verbs: hodně dobrý (very good), hodně se usmívá (smiles a lot), studuju hodně (I study a lot).")
            BWRow("moc", "very much / a lot", "familiar/casual — most frequent in everyday speech; often interchangeable with hodně: moc dobrý, moc děkuji (thanks a lot). Also a noun: moc = power.")
            BWNote("Quick rule: very + adjective/adverb → velmi in formal contexts, hodně/moc informally. With verbs meaning 'a lot' → hodně or moc, not velmi.")
            BWNote("Examples: Je velmi inteligentní. (formal) / Je hodně chytrý. (neutral) / Je moc hodný. (warm/casual)")

            // ── Only, Just, Nothing But ──────────────────────────────────
            BWSection("Only, Just, Nothing But")
            BWRow("jen / jenom", "only / just", "interchangeable; jen is slightly shorter, jenom slightly more emphatic")
            BWRow("pouze", "only", "more formal than jen; used in written Czech and formal speech — identical in meaning")
            BWRow("jen jedno jablko", "only one apple", "example of jen as a restrictive particle")
            BWRow("teprve", "only / not until", "implies 'not sooner than expected': Teprve teď rozumím. = Only now do I understand.")
            BWRow("teprve teď", "only now", "emphasizes that something is happening later than expected")
            BWNote("jen/jenom = 'only' as a restriction (I only want water = Chci jen vodu.). teprve = 'only' as in 'not until now' — there's an implied sense that it took longer than expected.")

            // ── Never, Nowhere, No One ───────────────────────────────────
            BWSection("Never, Nowhere, No One")
            BWNote("Czech requires double negation — use these negative words together with a negated verb. See also: Learning → Negation.")
            BWRow("nikdy", "never", "Nikdy nejím maso. = I never eat meat.")
            BWRow("nikde", "nowhere / not anywhere", "Nikde ho nevidím. = I don't see him anywhere.")
            BWRow("nikdo", "no one / nobody", "Nikdo nepřišel. = No one came.")

            // ── Always, Everywhere, Everyone ─────────────────────────────
            BWSection("Always, Everywhere, Everyone")
            BWRow("vždy / vždycky", "always", "vždy is slightly more formal/written; vždycky is more colloquial — both very common")
            BWRow("pokaždé", "every time", "po (each) + každé (each); Pokaždé se směje. = He laughs every time.")
            BWRow("všude", "everywhere", "Hledám tě všude. = I'm looking for you everywhere.")
            BWRow("každý", "everyone / each one", "masculine singular form; gender agreement: každý muž (every man), každá žena (every woman), každé dítě (every child)")
            BWRow("všichni", "everyone / all of them", "plural for mixed or male groups; all-female group: všechny")
            BWNote("každý = each individual person (emphasizes one by one). všichni = all of them as a group. Both are common; the choice depends on whether you're thinking of individuals or the whole group.")

            // ── Even, Neither...Nor... ────────────────────────────────────
            BWSection("Even, Neither...Nor...")
            BWRow("ani", "not even / even (in negative)", "Ani jedno jablko jsem nedostal. = I didn't get even one apple. Requires a negative verb.")
            BWRow("ani … ani …", "neither … nor …", "Nechci ani kávu, ani čaj. = I want neither coffee nor tea. Always used with a negative verb.")
            BWRow("dokonce", "even (additive / emphatic)", "Dokonce přišel včas. = He even came on time. Works in positive sentences — adds a sense of surprise or extra emphasis.")
            BWNote("ani = 'not even' (requires a negated verb). dokonce = 'even' in the sense of 'surprisingly also' — works in positive sentences without any negation.")

            // ── While, Between & Against ──────────────────────────────────
            BWSection("While, Between & Against")
            BWRow("zatímco", "while / whereas", "introduces simultaneity or contrast. Zatímco já vařím, ty prostíráš stůl. = While I cook, you set the table.")
            BWRow("mezi + instr.", "between / among", "static location, Instrumental case. Sedím mezi kamarády. = I'm sitting among friends. Motion 'to a spot between' uses the Accusative instead.")
            BWRow("proti + dat.", "against", "Dative case. Jsem proti tomu. = I'm against that. Hraje proti nám. = He's playing against us.")
            BWRow("chvíle / chvilka", "a moment / a while", "chvíle = a while (f. noun); chvilka = diminutive, a little while")
            BWRow("na chvíli / chvilku", "for a while / for a moment", "accusative after na: Odskočím na chvíli. = I'll step away for a moment.")
            BWRow("za chvíli", "in a moment / in a little while", "near future. Přijdu za chvíli. = I'll come in a moment.")
            BWRow("před chvílí", "a moment ago / just now", "Byl tady před chvílí. = He was here a moment ago.")
            BWRow("chvíli počkej", "wait a moment", "casual imperative. Chvíli počkej, hned jsem. = Wait a moment, I'll be right there.")

            // ── For A Long Time — Dlouho ──────────────────────────────────
            BWSection("For A Long Time — Dlouho")
            BWRow("dlouho", "for a long time / long", "adverb of time. Čekal jsem dlouho. = I waited a long time.")
            BWRow("příliš dlouho", "too long", "Nečekal jsem příliš dlouho. = I didn't wait too long.")
            BWRow("Dlouho jsme se neviděli.", "It's been a while. / Long time no see.", "lit. 'We haven't seen each other for a long time' — the standard Czech way to say 'it's been a while' between people meeting again.")
            BWRow("Je to dlouho, co jsme se neviděli.", "It's been a long time since we've seen each other.", "more explicit form of the same idea")
            BWNote("'Dlouho' is an adverb (how long). The closest Czech equivalent to English 'it's been a while' is 'Dlouho jsme se neviděli.' — literally 'We haven't seen each other for a long time.'")

            // ── Odd and Even (Numbers) ────────────────────────────────────
            BWSection("Odd and Even (Numbers)")
            BWNote("Used in mathematics, page numbers, street address numbering, and everyday scheduling.")
            BWRow("sudý", "even (number)", "2, 4, 6, 8… — sudé číslo = even number; sudá strana = even side")
            BWRow("lichý", "odd (number)", "1, 3, 5, 7… — liché číslo = odd number; lichá strana = odd side")
            BWRow("sudá čísla", "even numbers")
            BWRow("lichá čísla", "odd numbers")

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
