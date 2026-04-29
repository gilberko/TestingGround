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
fun UsefulVerbsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Very Useful Verbs", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            UVSection("Movement Verbs")
            UVVerbEntry(
                czech = "jít",
                english = "to go (on foot)",
                rule = "Destination: do + Genitive (enclosed place) or na + Accusative (event/open space).",
                example = "Jdu do školy.",
                translation = "I'm going to school."
            )
            UVTable(
                verb = "jít", label = "irregular",
                rows = listOf("já" to "jdu", "ty" to "jdeš", "on / ona / ono" to "jde",
                    "my" to "jdeme", "vy" to "jdete", "oni" to "jdou")
            )
            UVVerbEntry(
                czech = "jet",
                english = "to go (by vehicle)",
                rule = "Same preposition patterns as jít — use jet whenever riding.",
                example = "Jedu do Prahy.",
                translation = "I'm going to Prague (by vehicle)."
            )
            UVTable(
                verb = "jet", label = "irregular",
                rows = listOf("já" to "jedu", "ty" to "jedeš", "on / ona / ono" to "jede",
                    "my" to "jedeme", "vy" to "jedete", "oni" to "jedou")
            )

            UVSection("Modal Verbs")
            UVVerbEntry(
                czech = "muset",
                english = "must / have to",
                rule = "Followed directly by an infinitive — no preposition or case change.",
                example = "Musím jít.",
                translation = "I have to go."
            )
            UVTable(
                verb = "muset", label = "Type 2 (-et)",
                rows = listOf("já" to "musím", "ty" to "musíš", "on / ona / ono" to "musí",
                    "my" to "musíme", "vy" to "musíte", "oni" to "musí")
            )
            UVVerbEntry(
                czech = "moct",
                english = "can / to be able to",
                rule = "Followed directly by an infinitive.",
                example = "Můžeš mi pomoci?",
                translation = "Can you help me?"
            )
            UVTable(
                verb = "moct", label = "irregular",
                rows = listOf("já" to "můžu / mohu", "ty" to "můžeš", "on / ona / ono" to "může",
                    "my" to "můžeme", "vy" to "můžete", "oni" to "můžou / mohou")
            )

            UVSection("Giving, Taking & Transfer")
            UVVerbEntry(
                czech = "dát",
                english = "to give",
                rule = "Takes Dative (to whom) + Accusative (what).",
                example = "Dám ti knihu.",
                translation = "I'll give you a book."
            )
            UVTable(
                verb = "dát", label = "irregular",
                rows = listOf("já" to "dám", "ty" to "dáš", "on / ona / ono" to "dá",
                    "my" to "dáme", "vy" to "dáte", "oni" to "dají")
            )
            UVVerbEntry(
                czech = "vzít",
                english = "to take",
                rule = "Takes Accusative (what you take). Reflexive vzít si = to take for oneself.",
                example = "Vezmu si kabelku.",
                translation = "I'll take the bag."
            )
            UVTable(
                verb = "vzít", label = "irregular",
                rows = listOf("já" to "vezmu", "ty" to "vezmeš", "on / ona / ono" to "vezme",
                    "my" to "vezmeme", "vy" to "vezmete", "oni" to "vezmou")
            )
            UVVerbEntry(
                czech = "poslat",
                english = "to send",
                rule = "Takes Dative (to whom) + Accusative (what).",
                example = "Pošlu ti zprávu.",
                translation = "I'll send you a message."
            )
            UVTable(
                verb = "poslat", label = "irregular",
                rows = listOf("já" to "pošlu", "ty" to "pošleš", "on / ona / ono" to "pošle",
                    "my" to "pošleme", "vy" to "pošlete", "oni" to "pošlou")
            )
            UVVerbEntry(
                czech = "dostat",
                english = "to receive / to get",
                rule = "Takes Accusative (what is received).",
                example = "Dostal jsem dopis.",
                translation = "I received a letter."
            )
            UVTable(
                verb = "dostat", label = "irregular",
                rows = listOf("já" to "dostanu", "ty" to "dostaneš", "on / ona / ono" to "dostane",
                    "my" to "dostaneme", "vy" to "dostanete", "oni" to "dostanou")
            )
            UVVerbEntry(
                czech = "přinést",
                english = "to bring",
                rule = "Takes Dative (for whom) + Accusative (what).",
                example = "Přinesu ti vodu.",
                translation = "I'll bring you water."
            )
            UVTable(
                verb = "přinést", label = "irregular",
                rows = listOf("já" to "přinesu", "ty" to "přineseš", "on / ona / ono" to "přinese",
                    "my" to "přineseme", "vy" to "přinesete", "oni" to "přinesou")
            )

            UVSection("Common Everyday Verbs")
            UVVerbEntry(
                czech = "mít",
                english = "to have",
                rule = "Takes Accusative (what you have).",
                example = "Mám knihu.",
                translation = "I have a book."
            )
            UVTable(
                verb = "mít", label = "irregular",
                rows = listOf("já" to "mám", "ty" to "máš", "on / ona / ono" to "má",
                    "my" to "máme", "vy" to "máte", "oni" to "mají")
            )
            UVVerbEntry(
                czech = "dělat",
                english = "to do / to make",
                rule = "Takes Accusative (what you do or make).",
                example = "Co děláš?",
                translation = "What are you doing?"
            )
            UVTable(
                verb = "dělat", label = "Type 1 (-at)",
                rows = listOf("já" to "dělám", "ty" to "děláš", "on / ona / ono" to "dělá",
                    "my" to "děláme", "vy" to "děláte", "oni" to "dělají")
            )
            UVVerbEntry(
                czech = "pracovat",
                english = "to work",
                rule = "Location: v / ve + Locative (where you work).",
                example = "Pracuji v kanceláři.",
                translation = "I work in an office."
            )
            UVTable(
                verb = "pracovat", label = "Type 3 (-ovat)",
                rows = listOf("já" to "pracuji / pracuju", "ty" to "pracuješ", "on / ona / ono" to "pracuje",
                    "my" to "pracujeme", "vy" to "pracujete", "oni" to "pracují / pracujou")
            )
            UVVerbEntry(
                czech = "studovat",
                english = "to study",
                rule = "Takes Accusative (subject being studied).",
                example = "Studuji češtinu.",
                translation = "I'm studying Czech."
            )
            UVTable(
                verb = "studovat", label = "Type 3 (-ovat)",
                rows = listOf("já" to "studuji / studuju", "ty" to "studuješ", "on / ona / ono" to "studuje",
                    "my" to "studujeme", "vy" to "studujete", "oni" to "studují / studujou")
            )
            UVVerbEntry(
                czech = "myslet",
                english = "to think",
                rule = "myslet na + Accusative = to think about something. myslet si = to believe/to think (opinion).",
                example = "Myslím na tebe.",
                translation = "I'm thinking about you."
            )
            UVTable(
                verb = "myslet", label = "Type 2 (-et)",
                rows = listOf("já" to "myslím", "ty" to "myslíš", "on / ona / ono" to "myslí",
                    "my" to "myslíme", "vy" to "myslíte", "oni" to "myslí")
            )
            UVVerbEntry(
                czech = "přestat",
                english = "to stop (doing something)",
                rule = "Followed directly by an infinitive — no preposition.",
                example = "Přestaň mluvit!",
                translation = "Stop talking!"
            )
            UVTable(
                verb = "přestat", label = "irregular",
                rows = listOf("já" to "přestanu", "ty" to "přestaneš", "on / ona / ono" to "přestane",
                    "my" to "přestaneme", "vy" to "přestanete", "oni" to "přestanou")
            )
            UVVerbEntry(
                czech = "běžet",
                english = "to run",
                rule = "Destination: do + Genitive or na + Accusative.",
                example = "Běžím do parku.",
                translation = "I'm running to the park."
            )
            UVTable(
                verb = "běžet", label = "Type 2 (-et)",
                rows = listOf("já" to "běžím", "ty" to "běžíš", "on / ona / ono" to "běží",
                    "my" to "běžíme", "vy" to "běžíte", "oni" to "běží")
            )
            UVVerbEntry(
                czech = "vracet se",
                english = "to return / to come back",
                rule = "Imperfective form used here for present tense. Perfective vrátit se expresses a single completed return. Back home: vracet se domů.",
                example = "Vracím se domů.",
                translation = "I'm returning home."
            )
            UVTable(
                verb = "vracet se", label = "Type 1 (-at), reflexive",
                rows = listOf("já" to "vracím se", "ty" to "vracíš se", "on / ona / ono" to "vrací se",
                    "my" to "vracíme se", "vy" to "vracíte se", "oni" to "vracejí se")
            )

            UVSection("Shopping")
            UVVerbEntry(
                czech = "kupovat",
                english = "to buy",
                rule = "Takes Accusative (what you buy). Imperfective; perfective is koupit.",
                example = "Kupuji si nové boty.",
                translation = "I'm buying new shoes."
            )
            UVTable(
                verb = "kupovat", label = "Type 3 (-ovat)",
                rows = listOf("já" to "kupuji / kupuju", "ty" to "kupuješ", "on / ona / ono" to "kupuje",
                    "my" to "kupujeme", "vy" to "kupujete", "oni" to "kupují / kupujou")
            )
            UVVerbEntry(
                czech = "prodávat",
                english = "to sell",
                rule = "Takes Accusative (what you sell). Imperfective; perfective is prodat.",
                example = "Prodávám auto.",
                translation = "I'm selling a car."
            )
            UVTable(
                verb = "prodávat", label = "Type 1 (-at)",
                rows = listOf("já" to "prodávám", "ty" to "prodáváš", "on / ona / ono" to "prodává",
                    "my" to "prodáváme", "vy" to "prodáváte", "oni" to "prodávají")
            )

            UVSection("Actions")
            UVVerbEntry(
                czech = "otevírat",
                english = "to open",
                rule = "Takes Accusative (what you open). Imperfective; perfective is otevřít.",
                example = "Otevírám okno.",
                translation = "I'm opening the window."
            )
            UVTable(
                verb = "otevírat", label = "Type 1 (-at)",
                rows = listOf("já" to "otevírám", "ty" to "otevíráš", "on / ona / ono" to "otevírá",
                    "my" to "otevíráme", "vy" to "otevíráte", "oni" to "otevírají")
            )
            UVVerbEntry(
                czech = "zavírat",
                english = "to close",
                rule = "Takes Accusative (what you close). Imperfective; perfective is zavřít.",
                example = "Zavírám dveře.",
                translation = "I'm closing the door."
            )
            UVTable(
                verb = "zavírat", label = "Type 1 (-at)",
                rows = listOf("já" to "zavírám", "ty" to "zavíráš", "on / ona / ono" to "zavírá",
                    "my" to "zavíráme", "vy" to "zavíráte", "oni" to "zavírají")
            )
            UVVerbEntry(
                czech = "hrát",
                english = "to play",
                rule = "Sport: hrát + Accusative (hrát fotbal). Instrument: hrát na + Accusative (hrát na kytaru).",
                example = "Hrajeme fotbal.",
                translation = "We're playing football."
            )
            UVTable(
                verb = "hrát", label = "irregular (-át)",
                rows = listOf("já" to "hraji / hraju", "ty" to "hraješ", "on / ona / ono" to "hraje",
                    "my" to "hrajeme", "vy" to "hrajete", "oni" to "hrají")
            )
            UVVerbEntry(
                czech = "čekat",
                english = "to wait",
                rule = "čekat na + Accusative = to wait for someone/something.",
                example = "Čekám na autobus.",
                translation = "I'm waiting for the bus."
            )
            UVTable(
                verb = "čekat", label = "Type 1 (-at)",
                rows = listOf("já" to "čekám", "ty" to "čekáš", "on / ona / ono" to "čeká",
                    "my" to "čekáme", "vy" to "čekáte", "oni" to "čekají")
            )

            UVSection("States")
            UVVerbEntry(
                czech = "sedět",
                english = "to sit / to be seated",
                rule = "Location: na + Locative (na židli = on a chair) or v / ve + Locative.",
                example = "Sedím na lavičce.",
                translation = "I'm sitting on a bench."
            )
            UVTable(
                verb = "sedět", label = "Type 2 (-et)",
                rows = listOf("já" to "sedím", "ty" to "sedíš", "on / ona / ono" to "sedí",
                    "my" to "sedíme", "vy" to "sedíte", "oni" to "sedí")
            )
            UVVerbEntry(
                czech = "spát",
                english = "to sleep",
                rule = "No special case requirement. spát dobře = to sleep well.",
                example = "Spím osm hodin.",
                translation = "I sleep eight hours."
            )
            UVTable(
                verb = "spát", label = "irregular",
                rows = listOf("já" to "spím", "ty" to "spíš", "on / ona / ono" to "spí",
                    "my" to "spíme", "vy" to "spíte", "oni" to "spí")
            )
            UVVerbEntry(
                czech = "jíst",
                english = "to eat",
                rule = "Takes Accusative (what you eat).",
                example = "Jím polévku.",
                translation = "I'm eating soup."
            )
            UVTable(
                verb = "jíst", label = "irregular",
                rows = listOf("já" to "jím", "ty" to "jíš", "on / ona / ono" to "jí",
                    "my" to "jíme", "vy" to "jíte", "oni" to "jedí")
            )
            UVVerbEntry(
                czech = "pít",
                english = "to drink",
                rule = "Takes Accusative (what you drink).",
                example = "Piji kávu.",
                translation = "I'm drinking coffee."
            )
            UVTable(
                verb = "pít", label = "irregular",
                rows = listOf("já" to "piji / piju", "ty" to "piješ", "on / ona / ono" to "pije",
                    "my" to "pijeme", "vy" to "pijete", "oni" to "pijí / pijou")
            )

            UVSection("Being & Living")

            UVVerbEntry(
                czech = "být",
                english = "to be",
                rule = "Core linking verb. Used for identity, description, location, and existence.",
                example = "Jsem doma.",
                translation = "I am at home."
            )
            UVTable(
                verb = "být", label = "irregular",
                rows = listOf(
                    "já" to "jsem",
                    "ty" to "jsi",
                    "on / ona / ono" to "je",
                    "my" to "jsme",
                    "vy" to "jste",
                    "oni" to "jsou"
                )
            )

            UVVerbEntry(
                czech = "žít",
                english = "to be alive / to live (life)",
                rule = "Location with v/ve + Locative. Distinct from bydlet (physical residence).",
                example = "Žiju v Praze.",
                translation = "I live in Prague."
            )
            UVTable(
                verb = "žít", label = "irregular",
                rows = listOf(
                    "já" to "žiji / žiju",
                    "ty" to "žiješ",
                    "on / ona / ono" to "žije",
                    "my" to "žijeme",
                    "vy" to "žijete",
                    "oni" to "žijí / žijou"
                )
            )

            UVVerbEntry(
                czech = "bydlet",
                english = "to reside / to live (at an address)",
                rule = "Location with v/ve + Locative. Use for physical address, not general life.",
                example = "Bydlím v Brně.",
                translation = "I reside in Brno."
            )
            UVTable(
                verb = "bydlet", label = "Type 2 (-et)",
                rows = listOf(
                    "já" to "bydlím",
                    "ty" to "bydlíš",
                    "on / ona / ono" to "bydlí",
                    "my" to "bydlíme",
                    "vy" to "bydlíte",
                    "oni" to "bydlí"
                )
            )

            UVSection("Communication")

            UVVerbEntry(
                czech = "mluvit",
                english = "to speak / to talk",
                rule = "Topic: o + Locative. With whom: s + Instrumental.",
                example = "Mluvím česky.",
                translation = "I speak Czech."
            )
            UVTable(
                verb = "mluvit", label = "Type 2 (-it)",
                rows = listOf(
                    "já" to "mluvím",
                    "ty" to "mluvíš",
                    "on / ona / ono" to "mluví",
                    "my" to "mluvíme",
                    "vy" to "mluvíte",
                    "oni" to "mluví"
                )
            )

            UVVerbEntry(
                czech = "poslouchat",
                english = "to listen (to)",
                rule = "Takes Accusative (what/whom you listen to).",
                example = "Poslouchám hudbu.",
                translation = "I'm listening to music."
            )
            UVTable(
                verb = "poslouchat", label = "Type 1 (-at)",
                rows = listOf(
                    "já" to "poslouchám",
                    "ty" to "posloucháš",
                    "on / ona / ono" to "poslouchá",
                    "my" to "posloucháme",
                    "vy" to "posloucháte",
                    "oni" to "poslouchají"
                )
            )

            UVVerbEntry(
                czech = "říkat",
                english = "to say / to tell (imperfective)",
                rule = "Imperfective (habitual/ongoing). Perfective říct → řeknu / řekneš / řekne / řekneme / řeknete / řeknou.",
                example = "Říkám ti pravdu.",
                translation = "I'm telling you the truth."
            )
            UVTable(
                verb = "říkat", label = "Type 1 (-at)",
                rows = listOf(
                    "já" to "říkám",
                    "ty" to "říkáš",
                    "on / ona / ono" to "říká",
                    "my" to "říkáme",
                    "vy" to "říkáte",
                    "oni" to "říkají"
                )
            )

            UVVerbEntry(
                czech = "vidět",
                english = "to see",
                rule = "Takes Accusative (what/whom you see).",
                example = "Vidím tě.",
                translation = "I see you."
            )
            UVTable(
                verb = "vidět", label = "Type 2 (-et)",
                rows = listOf(
                    "já" to "vidím",
                    "ty" to "vidíš",
                    "on / ona / ono" to "vidí",
                    "my" to "vidíme",
                    "vy" to "vidíte",
                    "oni" to "vidí"
                )
            )

            UVVerbEntry(
                czech = "dívat se",
                english = "to look (at) / to watch",
                rule = "na + Accusative (direction of gaze). Reflexive particle se is always required.",
                example = "Dívám se na film.",
                translation = "I'm watching a film."
            )
            UVTable(
                verb = "dívat se", label = "Type 1 (-at), reflexive",
                rows = listOf(
                    "já" to "dívám se",
                    "ty" to "díváš se",
                    "on / ona / ono" to "dívá se",
                    "my" to "díváme se",
                    "vy" to "díváte se",
                    "oni" to "dívají se"
                )
            )

            UVVerbEntry(
                czech = "jmenovat se",
                english = "to be called / to be named",
                rule = "Reflexive verb — se is a clitic occupying the second position in the sentence. Conjugates like pracovat (-ovat type). Formal: jmenuji se; colloquial: jmenuju se — both are correct.",
                example = "Jak se jmenuješ?",
                translation = "What is your name? (informal)"
            )
            UVTable(
                verb = "jmenovat se", label = "Type 3 (-ovat), reflexive",
                rows = listOf(
                    "já" to "jmenuji se  (jmenuju se)",
                    "ty" to "jmenuješ se",
                    "on / ona / ono" to "jmenuje se",
                    "my" to "jmenujeme se",
                    "vy" to "jmenujete se",
                    "oni" to "jmenují se"
                )
            )

            UVSection("Knowing & Meeting")

            UVVerbEntry(
                czech = "vědět",
                english = "to know (a fact)",
                rule = "Takes Accusative or že + clause. Use for facts, not for knowing people/places.",
                example = "Vím, kde to je.",
                translation = "I know where it is."
            )
            UVTable(
                verb = "vědět", label = "irregular",
                rows = listOf(
                    "já" to "vím",
                    "ty" to "víš",
                    "on / ona / ono" to "ví",
                    "my" to "víme",
                    "vy" to "víte",
                    "oni" to "vědí"
                )
            )

            UVVerbEntry(
                czech = "znát",
                english = "to know (a person / place)",
                rule = "Takes Accusative. Use for familiarity with people, places, or works — not for facts.",
                example = "Znám Prahu.",
                translation = "I know Prague."
            )
            UVTable(
                verb = "znát", label = "Type 1 (-at)",
                rows = listOf(
                    "já" to "znám",
                    "ty" to "znáš",
                    "on / ona / ono" to "zná",
                    "my" to "známe",
                    "vy" to "znáte",
                    "oni" to "znají"
                )
            )

            UVVerbEntry(
                czech = "potkávat",
                english = "to meet / to run into (imperfective)",
                rule = "Takes Accusative. Casual/chance encounter. For arranged meeting use setkat se s + Instrumental.",
                example = "Potkávám ho každý den.",
                translation = "I run into him every day."
            )
            UVTable(
                verb = "potkávat", label = "Type 1 (-at)",
                rows = listOf(
                    "já" to "potkávám",
                    "ty" to "potkáváš",
                    "on / ona / ono" to "potkává",
                    "my" to "potkáváme",
                    "vy" to "potkáváte",
                    "oni" to "potkávají"
                )
            )

            UVSection("Mind & Decisions")

            UVVerbEntry(
                czech = "pamatovat si",
                english = "to remember",
                rule = "Takes Accusative. Reflexive si is obligatory — do not drop it.",
                example = "Pamatuji si tvé jméno.",
                translation = "I remember your name."
            )
            UVTable(
                verb = "pamatovat si", label = "Type 3 (-ovat), reflexive",
                rows = listOf(
                    "já" to "pamatuji si / pamatuju si",
                    "ty" to "pamatuješ si",
                    "on / ona / ono" to "pamatuje si",
                    "my" to "pamatujeme si",
                    "vy" to "pamatujete si",
                    "oni" to "pamatují si / pamatujou si"
                )
            )

            UVVerbEntry(
                czech = "zapomínat",
                english = "to forget (imperfective)",
                rule = "na + Accusative (what you forget). Imperfective; perfective is zapomenout.",
                example = "Zapomínám na klíče.",
                translation = "I keep forgetting the keys."
            )
            UVTable(
                verb = "zapomínat", label = "Type 1 (-at)",
                rows = listOf(
                    "já" to "zapomínám",
                    "ty" to "zapomínáš",
                    "on / ona / ono" to "zapomíná",
                    "my" to "zapomínáme",
                    "vy" to "zapomínáte",
                    "oni" to "zapomínají"
                )
            )

            UVVerbEntry(
                czech = "rozhodovat se",
                english = "to decide (imperfective)",
                rule = "pro + Accusative (deciding in favour of) or o + Locative (deciding about). Reflexive se required.",
                example = "Rozhoduji se pro nové auto.",
                translation = "I'm deciding on a new car."
            )
            UVTable(
                verb = "rozhodovat se", label = "Type 3 (-ovat), reflexive",
                rows = listOf(
                    "já" to "rozhoduji se / rozhoduju se",
                    "ty" to "rozhoduješ se",
                    "on / ona / ono" to "rozhoduje se",
                    "my" to "rozhodujeme se",
                    "vy" to "rozhodujete se",
                    "oni" to "rozhodují se / rozhodujou se"
                )
            )

            UVSection("Finding & Permission")

            UVVerbEntry(
                czech = "hledat",
                english = "to look for / to search",
                rule = "Takes Accusative (what you search for).",
                example = "Hledám práci.",
                translation = "I'm looking for work."
            )
            UVTable(
                verb = "hledat", label = "Type 1 (-at)",
                rows = listOf(
                    "já" to "hledám",
                    "ty" to "hledáš",
                    "on / ona / ono" to "hledá",
                    "my" to "hledáme",
                    "vy" to "hledáte",
                    "oni" to "hledají"
                )
            )

            UVVerbEntry(
                czech = "nacházet",
                english = "to find (imperfective)",
                rule = "Takes Accusative. Imperfective of najít (perf.: najdu / najdeš / najde / najdeme / najdete / najdou).",
                example = "Nacházím řešení.",
                translation = "I'm finding a solution."
            )
            UVTable(
                verb = "nacházet", label = "Type 2-like (-et)",
                rows = listOf(
                    "já" to "nacházím",
                    "ty" to "nacházíš",
                    "on / ona / ono" to "nachází",
                    "my" to "nacházíme",
                    "vy" to "nacházíte",
                    "oni" to "nacházejí"
                )
            )

            UVVerbEntry(
                czech = "dovolovat",
                english = "to allow / to permit (imperfective)",
                rule = "Takes Dative (to whom) + infinitive. Perfective is dovolit.",
                example = "Dovoluje mi jít ven.",
                translation = "He allows me to go outside."
            )
            UVTable(
                verb = "dovolovat", label = "Type 3 (-ovat)",
                rows = listOf(
                    "já" to "dovoluji / dovoluju",
                    "ty" to "dovoluješ",
                    "on / ona / ono" to "dovoluje",
                    "my" to "dovolujeme",
                    "vy" to "dovolujete",
                    "oni" to "dovolují / dovolujou"
                )
            )

            UVVerbEntry(
                czech = "smět",
                english = "to be allowed to (modal)",
                rule = "Followed directly by an infinitive. Expresses permission.",
                example = "Smím tu parkovat?",
                translation = "Am I allowed to park here?"
            )
            UVTable(
                verb = "smět", label = "irregular modal",
                rows = listOf(
                    "já" to "smím",
                    "ty" to "smíš",
                    "on / ona / ono" to "smí",
                    "my" to "smíme",
                    "vy" to "smíte",
                    "oni" to "smí"
                )
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun UVSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun UVVerbEntry(
    czech: String,
    english: String,
    rule: String,
    example: String,
    translation: String
) {
    Spacer(modifier = Modifier.height(10.dp))
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
    Text(
        text = rule,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(top = 2.dp, start = 2.dp)
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
        modifier = Modifier.padding(top = 3.dp, start = 2.dp)
    )
}

@Composable
private fun UVTable(verb: String, label: String, rows: List<Pair<String, String>>) {
    Spacer(modifier = Modifier.height(8.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 2.dp, end = 2.dp, bottom = 4.dp)
    ) {
        Text(text = verb, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Text(text = label, fontSize = 13.sp, fontStyle = FontStyle.Italic, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Text("Pronoun", modifier = Modifier.weight(1f), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Text("Form", modifier = Modifier.weight(0.8f), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
        }
        HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
        rows.forEach { (pronoun, form) ->
            Row(modifier = Modifier.padding(vertical = 3.dp)) {
                Text(pronoun, modifier = Modifier.weight(1f), fontSize = 14.sp, color = Color.DarkGray)
                Text(form, modifier = Modifier.weight(0.8f), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}
