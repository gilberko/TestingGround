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
            UVAspect("Imperfective (unidirectional). Perfective with prefix: přijít (arrive), odejít (leave).")
            UVTable(
                verb = "jít", label = "irregular",
                rows = listOf(
                    Triple("já", "jdu", "jsem šel / šla"),
                    Triple("ty", "jdeš", "jsi šel / šla"),
                    Triple("on / ona / ono", "jde", "šel / šla / šlo"),
                    Triple("my", "jdeme", "jsme šli / šly"),
                    Triple("vy", "jdete", "jste šli / šly"),
                    Triple("oni / ony", "jdou", "šli / šly")
                )
            )
            UVVerbEntry(
                czech = "jet",
                english = "to go (by vehicle)",
                rule = "Same preposition patterns as jít — use jet whenever riding.",
                example = "Jedu do Prahy.",
                translation = "I'm going to Prague (by vehicle)."
            )
            UVAspect("Imperfective (unidirectional). Perfective with prefix: přijet (arrive), odjet (leave).")
            UVTable(
                verb = "jet", label = "irregular",
                rows = listOf(
                    Triple("já", "jedu", "jsem jel / jela"),
                    Triple("ty", "jedeš", "jsi jel / jela"),
                    Triple("on / ona / ono", "jede", "jel / jela / jelo"),
                    Triple("my", "jedeme", "jsme jeli / jely"),
                    Triple("vy", "jedete", "jste jeli / jely"),
                    Triple("oni / ony", "jedou", "jeli / jely")
                )
            )

            UVSection("Modal Verbs")
            UVVerbEntry(
                czech = "muset",
                english = "must / have to",
                rule = "Followed directly by an infinitive — no preposition or case change.",
                example = "Musím jít.",
                translation = "I have to go."
            )
            UVAspect("Imperfective. No perfective counterpart (modal verb).")
            UVTable(
                verb = "muset", label = "Type 2 (-et)",
                rows = listOf(
                    Triple("já", "musím", "jsem musel / musela"),
                    Triple("ty", "musíš", "jsi musel / musela"),
                    Triple("on / ona / ono", "musí", "musel / musela / muselo"),
                    Triple("my", "musíme", "jsme museli / musely"),
                    Triple("vy", "musíte", "jste museli / musely"),
                    Triple("oni / ony", "musí", "museli / musely")
                )
            )
            UVVerbEntry(
                czech = "moct",
                english = "can / to be able to",
                rule = "Followed directly by an infinitive.",
                example = "Můžeš mi pomoci?",
                translation = "Can you help me?"
            )
            UVAspect("Imperfective. No perfective counterpart (modal verb).")
            UVAspect("Also written: moci — an archaic/formal variant of the same infinitive. moct is the modern standard; moci appears in literary or formal contexts. Both are listed in Czech dictionaries. The present tense has two registers: můžu / můžou (colloquial, everyday speech) and mohu / mohou (formal, written Czech) — both are correct; the table shows both.")
            UVTable(
                verb = "moct", label = "irregular",
                rows = listOf(
                    Triple("já", "můžu / mohu", "jsem mohl / mohla"),
                    Triple("ty", "můžeš", "jsi mohl / mohla"),
                    Triple("on / ona / ono", "může", "mohl / mohla / mohlo"),
                    Triple("my", "můžeme", "jsme mohli / mohly"),
                    Triple("vy", "můžete", "jste mohli / mohly"),
                    Triple("oni / ony", "můžou / mohou", "mohli / mohly")
                )
            )

            UVSection("Giving, Taking & Transfer")
            UVVerbEntry(
                czech = "dát",
                english = "to give",
                rule = "Takes Dative (to whom) + Accusative (what).",
                example = "Dám ti knihu.",
                translation = "I'll give you a book."
            )
            UVAspect("Perfective. Imperfective: dávat.")
            UVTable(
                verb = "dát", label = "irregular",
                rows = listOf(
                    Triple("já", "dám", "jsem dal / dala"),
                    Triple("ty", "dáš", "jsi dal / dala"),
                    Triple("on / ona / ono", "dá", "dal / dala / dalo"),
                    Triple("my", "dáme", "jsme dali / daly"),
                    Triple("vy", "dáte", "jste dali / daly"),
                    Triple("oni / ony", "dají", "dali / daly")
                )
            )
            UVVerbEntry(
                czech = "vzít",
                english = "to take",
                rule = "Takes Accusative (what you take). Reflexive vzít si = to take for oneself.",
                example = "Vezmu si kabelku.",
                translation = "I'll take the bag."
            )
            UVAspect("Perfective. Imperfective: brát.")
            UVTable(
                verb = "vzít", label = "irregular",
                rows = listOf(
                    Triple("já", "vezmu", "jsem vzal / vzala"),
                    Triple("ty", "vezmeš", "jsi vzal / vzala"),
                    Triple("on / ona / ono", "vezme", "vzal / vzala / vzalo"),
                    Triple("my", "vezmeme", "jsme vzali / vzaly"),
                    Triple("vy", "vezmete", "jste vzali / vzaly"),
                    Triple("oni / ony", "vezmou", "vzali / vzaly")
                )
            )
            UVVerbEntry(
                czech = "poslat",
                english = "to send",
                rule = "Takes Dative (to whom) + Accusative (what).",
                example = "Pošlu ti zprávu.",
                translation = "I'll send you a message."
            )
            UVAspect("Perfective. Imperfective: posílat.")
            UVTable(
                verb = "poslat", label = "irregular",
                rows = listOf(
                    Triple("já", "pošlu", "jsem poslal / poslala"),
                    Triple("ty", "pošleš", "jsi poslal / poslala"),
                    Triple("on / ona / ono", "pošle", "poslal / poslala / poslalo"),
                    Triple("my", "pošleme", "jsme poslali / poslaly"),
                    Triple("vy", "pošlete", "jste poslali / poslaly"),
                    Triple("oni / ony", "pošlou", "poslali / poslaly")
                )
            )
            UVVerbEntry(
                czech = "dostat",
                english = "to receive / to get",
                rule = "Takes Accusative (what is received).",
                example = "Dostal jsem dopis.",
                translation = "I received a letter."
            )
            UVAspect("Perfective. Imperfective: dostávat.")
            UVTable(
                verb = "dostat", label = "irregular",
                rows = listOf(
                    Triple("já", "dostanu", "jsem dostal / dostala"),
                    Triple("ty", "dostaneš", "jsi dostal / dostala"),
                    Triple("on / ona / ono", "dostane", "dostal / dostala / dostalo"),
                    Triple("my", "dostaneme", "jsme dostali / dostaly"),
                    Triple("vy", "dostanete", "jste dostali / dostaly"),
                    Triple("oni / ony", "dostanou", "dostali / dostaly")
                )
            )
            UVVerbEntry(
                czech = "přinést",
                english = "to bring",
                rule = "Takes Dative (for whom) + Accusative (what).",
                example = "Přinesu ti vodu.",
                translation = "I'll bring you water."
            )
            UVAspect("Perfective. Imperfective: přinášet.")
            UVTable(
                verb = "přinést", label = "irregular",
                rows = listOf(
                    Triple("já", "přinesu", "jsem přinesl / přinesla"),
                    Triple("ty", "přineseš", "jsi přinesl / přinesla"),
                    Triple("on / ona / ono", "přinese", "přinesl / přinesla / přineslo"),
                    Triple("my", "přineseme", "jsme přinesli / přinesly"),
                    Triple("vy", "přinesete", "jste přinesli / přinesly"),
                    Triple("oni / ony", "přinesou", "přinesli / přinesly")
                )
            )

            UVSection("Common Everyday Verbs")
            UVVerbEntry(
                czech = "mít",
                english = "to have",
                rule = "Takes Accusative (what you have).",
                example = "Mám knihu.",
                translation = "I have a book."
            )
            UVAspect("Imperfective (stative). No standard perfective.")
            UVTable(
                verb = "mít", label = "irregular",
                rows = listOf(
                    Triple("já", "mám", "jsem měl / měla"),
                    Triple("ty", "máš", "jsi měl / měla"),
                    Triple("on / ona / ono", "má", "měl / měla / mělo"),
                    Triple("my", "máme", "jsme měli / měly"),
                    Triple("vy", "máte", "jste měli / měly"),
                    Triple("oni / ony", "mají", "měli / měly")
                )
            )
            UVVerbEntry(
                czech = "dělat",
                english = "to do / to make",
                rule = "Takes Accusative (what you do or make).",
                example = "Co děláš?",
                translation = "What are you doing?"
            )
            UVAspect("Imperfective. Perfective: udělat.")
            UVTable(
                verb = "dělat", label = "Type 1 (-at)",
                rows = listOf(
                    Triple("já", "dělám", "jsem dělal / dělala"),
                    Triple("ty", "děláš", "jsi dělal / dělala"),
                    Triple("on / ona / ono", "dělá", "dělal / dělala / dělalo"),
                    Triple("my", "děláme", "jsme dělali / dělaly"),
                    Triple("vy", "děláte", "jste dělali / dělaly"),
                    Triple("oni / ony", "dělají", "dělali / dělaly")
                )
            )
            UVVerbEntry(
                czech = "pracovat",
                english = "to work",
                rule = "Location: v / ve + Locative (where you work).",
                example = "Pracuji v kanceláři.",
                translation = "I work in an office."
            )
            UVAspect("Imperfective. No standard perfective.")
            UVTable(
                verb = "pracovat", label = "Type 3 (-ovat)",
                rows = listOf(
                    Triple("já", "pracuji / pracuju", "jsem pracoval / pracovala"),
                    Triple("ty", "pracuješ", "jsi pracoval / pracovala"),
                    Triple("on / ona / ono", "pracuje", "pracoval / pracovala / pracovalo"),
                    Triple("my", "pracujeme", "jsme pracovali / pracovaly"),
                    Triple("vy", "pracujete", "jste pracovali / pracovaly"),
                    Triple("oni / ony", "pracují / pracujou", "pracovali / pracovaly")
                )
            )
            UVVerbEntry(
                czech = "studovat",
                english = "to study",
                rule = "Takes Accusative (subject being studied).",
                example = "Studuji češtinu.",
                translation = "I'm studying Czech."
            )
            UVAspect("Imperfective. No standard perfective.")
            UVTable(
                verb = "studovat", label = "Type 3 (-ovat)",
                rows = listOf(
                    Triple("já", "studuji / studuju", "jsem studoval / studovala"),
                    Triple("ty", "studuješ", "jsi studoval / studovala"),
                    Triple("on / ona / ono", "studuje", "studoval / studovala / studovalo"),
                    Triple("my", "studujeme", "jsme studovali / studovaly"),
                    Triple("vy", "studujete", "jste studovali / studovaly"),
                    Triple("oni / ony", "studují / studujou", "studovali / studovaly")
                )
            )
            UVVerbEntry(
                czech = "myslet",
                english = "to think",
                rule = "myslet na + Accusative = to think about something. myslet si = to believe/to think (opinion).",
                example = "Myslím na tebe.",
                translation = "I'm thinking about you."
            )
            UVAspect("Imperfective. Perfective: pomyslet (si) (to have a thought).")
            UVTable(
                verb = "myslet", label = "Type 2 (-et)",
                rows = listOf(
                    Triple("já", "myslím", "jsem myslel / myslela"),
                    Triple("ty", "myslíš", "jsi myslel / myslela"),
                    Triple("on / ona / ono", "myslí", "myslel / myslela / myslelo"),
                    Triple("my", "myslíme", "jsme mysleli / myslely"),
                    Triple("vy", "myslíte", "jste mysleli / myslely"),
                    Triple("oni / ony", "myslí", "mysleli / myslely")
                )
            )
            UVVerbEntry(
                czech = "přestat",
                english = "to stop (doing something)",
                rule = "Followed directly by an infinitive — no preposition.",
                example = "Přestaň mluvit!",
                translation = "Stop talking!"
            )
            UVAspect("Perfective. Imperfective: přestávat.")
            UVTable(
                verb = "přestat", label = "irregular",
                rows = listOf(
                    Triple("já", "přestanu", "jsem přestal / přestala"),
                    Triple("ty", "přestaneš", "jsi přestal / přestala"),
                    Triple("on / ona / ono", "přestane", "přestal / přestala / přestalo"),
                    Triple("my", "přestaneme", "jsme přestali / přestaly"),
                    Triple("vy", "přestanete", "jste přestali / přestaly"),
                    Triple("oni / ony", "přestanou", "přestali / přestaly")
                )
            )
            UVVerbEntry(
                czech = "běžet",
                english = "to run",
                rule = "Destination: do + Genitive or na + Accusative.",
                example = "Běžím do parku.",
                translation = "I'm running to the park."
            )
            UVAspect("Imperfective (unidirectional). Perfective with prefix: přiběhnout (run here), odběhnout (run away).")
            UVTable(
                verb = "běžet", label = "Type 2 (-et)",
                rows = listOf(
                    Triple("já", "běžím", "jsem běžel / běžela"),
                    Triple("ty", "běžíš", "jsi běžel / běžela"),
                    Triple("on / ona / ono", "běží", "běžel / běžela / běželo"),
                    Triple("my", "běžíme", "jsme běželi / běžely"),
                    Triple("vy", "běžíte", "jste běželi / běžely"),
                    Triple("oni / ony", "běží", "běželi / běžely")
                )
            )
            UVVerbEntry(
                czech = "vracet se",
                english = "to return / to come back",
                rule = "Imperfective form used here for present tense. Perfective vrátit se expresses a single completed return. Back home: vracet se domů.",
                example = "Vracím se domů.",
                translation = "I'm returning home."
            )
            UVAspect("Imperfective. Perfective: vrátit se.")
            UVTable(
                verb = "vracet se", label = "Type 1 (-at), reflexive",
                rows = listOf(
                    Triple("já", "vracím se", "jsem se vracel / vracela"),
                    Triple("ty", "vracíš se", "jsi se vracel / vracela"),
                    Triple("on / ona / ono", "vrací se", "vracel se / vracela se / vracelo se"),
                    Triple("my", "vracíme se", "jsme se vraceli / vracely"),
                    Triple("vy", "vracíte se", "jste se vraceli / vracely"),
                    Triple("oni / ony", "vracejí se", "vraceli se / vracely se")
                )
            )

            UVSection("Shopping")
            UVVerbEntry(
                czech = "kupovat",
                english = "to buy",
                rule = "Takes Accusative (what you buy).",
                example = "Kupuji si nové boty.",
                translation = "I'm buying new shoes."
            )
            UVAspect("Imperfective. Perfective: koupit.")
            UVTable(
                verb = "kupovat", label = "Type 3 (-ovat)",
                rows = listOf(
                    Triple("já", "kupuji / kupuju", "jsem kupoval / kupovala"),
                    Triple("ty", "kupuješ", "jsi kupoval / kupovala"),
                    Triple("on / ona / ono", "kupuje", "kupoval / kupovala / kupovalo"),
                    Triple("my", "kupujeme", "jsme kupovali / kupovaly"),
                    Triple("vy", "kupujete", "jste kupovali / kupovaly"),
                    Triple("oni / ony", "kupují / kupujou", "kupovali / kupovaly")
                )
            )
            UVVerbEntry(
                czech = "prodávat",
                english = "to sell",
                rule = "Takes Accusative (what you sell).",
                example = "Prodávám auto.",
                translation = "I'm selling a car."
            )
            UVAspect("Imperfective. Perfective: prodat.")
            UVTable(
                verb = "prodávat", label = "Type 1 (-at)",
                rows = listOf(
                    Triple("já", "prodávám", "jsem prodával / prodávala"),
                    Triple("ty", "prodáváš", "jsi prodával / prodávala"),
                    Triple("on / ona / ono", "prodává", "prodával / prodávala / prodávalo"),
                    Triple("my", "prodáváme", "jsme prodávali / prodávaly"),
                    Triple("vy", "prodáváte", "jste prodávali / prodávaly"),
                    Triple("oni / ony", "prodávají", "prodávali / prodávaly")
                )
            )

            UVSection("Actions")
            UVVerbEntry(
                czech = "otevírat",
                english = "to open",
                rule = "Takes Accusative (what you open).",
                example = "Otevírám okno.",
                translation = "I'm opening the window."
            )
            UVAspect("Imperfective. Perfective: otevřít.")
            UVTable(
                verb = "otevírat", label = "Type 1 (-at)",
                rows = listOf(
                    Triple("já", "otevírám", "jsem otevíral / otevírala"),
                    Triple("ty", "otevíráš", "jsi otevíral / otevírala"),
                    Triple("on / ona / ono", "otevírá", "otevíral / otevírala / otevíralo"),
                    Triple("my", "otevíráme", "jsme otevírali / otevíraly"),
                    Triple("vy", "otevíráte", "jste otevírali / otevíraly"),
                    Triple("oni / ony", "otevírají", "otevírali / otevíraly")
                )
            )
            UVVerbEntry(
                czech = "zavírat",
                english = "to close",
                rule = "Takes Accusative (what you close).",
                example = "Zavírám dveře.",
                translation = "I'm closing the door."
            )
            UVAspect("Imperfective. Perfective: zavřít.")
            UVTable(
                verb = "zavírat", label = "Type 1 (-at)",
                rows = listOf(
                    Triple("já", "zavírám", "jsem zavíral / zavírala"),
                    Triple("ty", "zavíráš", "jsi zavíral / zavírala"),
                    Triple("on / ona / ono", "zavírá", "zavíral / zavírala / zavíralo"),
                    Triple("my", "zavíráme", "jsme zavírali / zavíraly"),
                    Triple("vy", "zavíráte", "jste zavírali / zavíraly"),
                    Triple("oni / ony", "zavírají", "zavírali / zavíraly")
                )
            )
            UVVerbEntry(
                czech = "hrát",
                english = "to play",
                rule = "Sport: hrát + Accusative (hrát fotbal). Instrument: hrát na + Accusative (hrát na kytaru).",
                example = "Hrajeme fotbal.",
                translation = "We're playing football."
            )
            UVAspect("Imperfective. Perfective: zahrát (si).")
            UVTable(
                verb = "hrát", label = "irregular (-át)",
                rows = listOf(
                    Triple("já", "hraji / hraju", "jsem hrál / hrála"),
                    Triple("ty", "hraješ", "jsi hrál / hrála"),
                    Triple("on / ona / ono", "hraje", "hrál / hrála / hrálo"),
                    Triple("my", "hrajeme", "jsme hráli / hrály"),
                    Triple("vy", "hrajete", "jste hráli / hrály"),
                    Triple("oni / ony", "hrají", "hráli / hrály")
                )
            )
            UVVerbEntry(
                czech = "čekat",
                english = "to wait",
                rule = "čekat na + Accusative = to wait for someone/something.",
                example = "Čekám na autobus.",
                translation = "I'm waiting for the bus."
            )
            UVAspect("Imperfective. Perfective: počkat.")
            UVTable(
                verb = "čekat", label = "Type 1 (-at)",
                rows = listOf(
                    Triple("já", "čekám", "jsem čekal / čekala"),
                    Triple("ty", "čekáš", "jsi čekal / čekala"),
                    Triple("on / ona / ono", "čeká", "čekal / čekala / čekalo"),
                    Triple("my", "čekáme", "jsme čekali / čekaly"),
                    Triple("vy", "čekáte", "jste čekali / čekaly"),
                    Triple("oni / ony", "čekají", "čekali / čekaly")
                )
            )

            UVSection("States")
            UVVerbEntry(
                czech = "sedět",
                english = "to sit / to be seated",
                rule = "Location: na + Locative (na židli = on a chair) or v / ve + Locative.",
                example = "Sedím na lavičce.",
                translation = "I'm sitting on a bench."
            )
            UVAspect("Imperfective. Perfective: sednout si (to sit down).")
            UVTable(
                verb = "sedět", label = "Type 2 (-et)",
                rows = listOf(
                    Triple("já", "sedím", "jsem seděl / seděla"),
                    Triple("ty", "sedíš", "jsi seděl / seděla"),
                    Triple("on / ona / ono", "sedí", "seděl / seděla / sedělo"),
                    Triple("my", "sedíme", "jsme seděli / seděly"),
                    Triple("vy", "sedíte", "jste seděli / seděly"),
                    Triple("oni / ony", "sedí", "seděli / seděly")
                )
            )
            UVVerbEntry(
                czech = "spát",
                english = "to sleep",
                rule = "No special case requirement. spát dobře = to sleep well.",
                example = "Spím osm hodin.",
                translation = "I sleep eight hours."
            )
            UVAspect("Imperfective. Perfective: prospat (to sleep through something).")
            UVTable(
                verb = "spát", label = "irregular",
                rows = listOf(
                    Triple("já", "spím", "jsem spal / spala"),
                    Triple("ty", "spíš", "jsi spal / spala"),
                    Triple("on / ona / ono", "spí", "spal / spala / spalo"),
                    Triple("my", "spíme", "jsme spali / spaly"),
                    Triple("vy", "spíte", "jste spali / spaly"),
                    Triple("oni / ony", "spí", "spali / spaly")
                )
            )
            UVVerbEntry(
                czech = "jíst",
                english = "to eat",
                rule = "Takes Accusative (what you eat).",
                example = "Jím polévku.",
                translation = "I'm eating soup."
            )
            UVAspect("Imperfective. Perfective: sníst (to eat up / finish eating).")
            UVTable(
                verb = "jíst", label = "irregular",
                rows = listOf(
                    Triple("já", "jím", "jsem jedl / jedla"),
                    Triple("ty", "jíš", "jsi jedl / jedla"),
                    Triple("on / ona / ono", "jí", "jedl / jedla / jedlo"),
                    Triple("my", "jíme", "jsme jedli / jedly"),
                    Triple("vy", "jíte", "jste jedli / jedly"),
                    Triple("oni / ony", "jedí", "jedli / jedly")
                )
            )
            UVVerbEntry(
                czech = "pít",
                english = "to drink",
                rule = "Takes Accusative (what you drink).",
                example = "Piji kávu.",
                translation = "I'm drinking coffee."
            )
            UVAspect("Imperfective. Perfective: vypít (to drink up).")
            UVTable(
                verb = "pít", label = "irregular",
                rows = listOf(
                    Triple("já", "piji / piju", "jsem pil / pila"),
                    Triple("ty", "piješ", "jsi pil / pila"),
                    Triple("on / ona / ono", "pije", "pil / pila / pilo"),
                    Triple("my", "pijeme", "jsme pili / pily"),
                    Triple("vy", "pijete", "jste pili / pily"),
                    Triple("oni / ony", "pijí / pijou", "pili / pily")
                )
            )

            UVSection("Being & Living")

            UVVerbEntry(
                czech = "být",
                english = "to be",
                rule = "Core linking verb. Used for identity, description, location, and existence.",
                example = "Jsem doma.",
                translation = "I am at home."
            )
            UVAspect("Imperfective. No perfective counterpart.")
            UVTable(
                verb = "být", label = "irregular",
                rows = listOf(
                    Triple("já", "jsem", "jsem byl / jsem byla"),
                    Triple("ty", "jsi", "jsi byl / jsi byla"),
                    Triple("on / ona / ono", "je", "byl / byla / bylo"),
                    Triple("my", "jsme", "jsme byli / jsme byly"),
                    Triple("vy", "jste", "jste byli / jste byly"),
                    Triple("oni / ony", "jsou", "byli / byly")
                )
            )

            UVVerbEntry(
                czech = "žít",
                english = "to be alive / to live (life)",
                rule = "Location with v/ve + Locative. Distinct from bydlet (physical residence).",
                example = "Žiju v Praze.",
                translation = "I live in Prague."
            )
            UVAspect("Imperfective. Perfective: prožít (to live through / experience fully).")
            UVTable(
                verb = "žít", label = "irregular",
                rows = listOf(
                    Triple("já", "žiji / žiju", "jsem žil / žila"),
                    Triple("ty", "žiješ", "jsi žil / žila"),
                    Triple("on / ona / ono", "žije", "žil / žila / žilo"),
                    Triple("my", "žijeme", "jsme žili / žily"),
                    Triple("vy", "žijete", "jste žili / žily"),
                    Triple("oni / ony", "žijí / žijou", "žili / žily")
                )
            )

            UVVerbEntry(
                czech = "bydlet",
                english = "to reside / to live (at an address)",
                rule = "Location with v/ve + Locative. Use for physical address, not general life.",
                example = "Bydlím v Brně.",
                translation = "I reside in Brno."
            )
            UVAspect("Imperfective. No standard perfective.")
            UVTable(
                verb = "bydlet", label = "Type 2 (-et)",
                rows = listOf(
                    Triple("já", "bydlím", "jsem bydlel / bydlela"),
                    Triple("ty", "bydlíš", "jsi bydlel / bydlela"),
                    Triple("on / ona / ono", "bydlí", "bydlel / bydlela / bydlelo"),
                    Triple("my", "bydlíme", "jsme bydleli / bydlely"),
                    Triple("vy", "bydlíte", "jste bydleli / bydlely"),
                    Triple("oni / ony", "bydlí", "bydleli / bydlely")
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
            UVAspect("Imperfective. Perfective: promluvit (to say something / speak up).")
            UVTable(
                verb = "mluvit", label = "Type 2 (-it)",
                rows = listOf(
                    Triple("já", "mluvím", "jsem mluvil / mluvila"),
                    Triple("ty", "mluvíš", "jsi mluvil / mluvila"),
                    Triple("on / ona / ono", "mluví", "mluvil / mluvila / mluvilo"),
                    Triple("my", "mluvíme", "jsme mluvili / mluvily"),
                    Triple("vy", "mluvíte", "jste mluvili / mluvily"),
                    Triple("oni / ony", "mluví", "mluvili / mluvily")
                )
            )

            UVVerbEntry(
                czech = "poslouchat",
                english = "to listen (to)",
                rule = "Takes Accusative (what/whom you listen to).",
                example = "Poslouchám hudbu.",
                translation = "I'm listening to music."
            )
            UVAspect("Imperfective. Perfective: poslechnout (si).")
            UVTable(
                verb = "poslouchat", label = "Type 1 (-at)",
                rows = listOf(
                    Triple("já", "poslouchám", "jsem poslouchal / poslouchala"),
                    Triple("ty", "posloucháš", "jsi poslouchal / poslouchala"),
                    Triple("on / ona / ono", "poslouchá", "poslouchal / poslouchala / poslouchalo"),
                    Triple("my", "posloucháme", "jsme poslouchali / poslouchaly"),
                    Triple("vy", "posloucháte", "jste poslouchali / poslouchaly"),
                    Triple("oni / ony", "poslouchají", "poslouchali / poslouchaly")
                )
            )

            UVVerbEntry(
                czech = "říkat",
                english = "to say / to tell (imperfective)",
                rule = "Imperfective (habitual/ongoing). Perfective říct → řeknu / řekneš / řekne / řekneme / řeknete / řeknou.",
                example = "Říkám ti pravdu.",
                translation = "I'm telling you the truth."
            )
            UVAspect("Imperfective. Perfective: říct / říci.")
            UVTable(
                verb = "říkat", label = "Type 1 (-at)",
                rows = listOf(
                    Triple("já", "říkám", "jsem říkal / říkala"),
                    Triple("ty", "říkáš", "jsi říkal / říkala"),
                    Triple("on / ona / ono", "říká", "říkal / říkala / říkalo"),
                    Triple("my", "říkáme", "jsme říkali / říkaly"),
                    Triple("vy", "říkáte", "jste říkali / říkaly"),
                    Triple("oni / ony", "říkají", "říkali / říkaly")
                )
            )

            UVVerbEntry(
                czech = "vidět",
                english = "to see",
                rule = "Takes Accusative (what/whom you see).",
                example = "Vidím tě.",
                translation = "I see you."
            )
            UVAspect("Imperfective (stative). Perfective: uvidět, spatřit.")
            UVTable(
                verb = "vidět", label = "Type 2 (-et)",
                rows = listOf(
                    Triple("já", "vidím", "jsem viděl / viděla"),
                    Triple("ty", "vidíš", "jsi viděl / viděla"),
                    Triple("on / ona / ono", "vidí", "viděl / viděla / vidělo"),
                    Triple("my", "vidíme", "jsme viděli / viděly"),
                    Triple("vy", "vidíte", "jste viděli / viděly"),
                    Triple("oni / ony", "vidí", "viděli / viděly")
                )
            )

            UVVerbEntry(
                czech = "dívat se",
                english = "to look (at) / to watch",
                rule = "na + Accusative (direction of gaze). Reflexive particle se is always required.",
                example = "Dívám se na film.",
                translation = "I'm watching a film."
            )
            UVAspect("Imperfective. Perfective: podívat se.")
            UVTable(
                verb = "dívat se", label = "Type 1 (-at), reflexive",
                rows = listOf(
                    Triple("já", "dívám se", "jsem se díval / dívala"),
                    Triple("ty", "díváš se", "jsi se díval / dívala"),
                    Triple("on / ona / ono", "dívá se", "díval se / dívala se / dívalo se"),
                    Triple("my", "díváme se", "jsme se dívali / dívaly"),
                    Triple("vy", "díváte se", "jste se dívali / dívaly"),
                    Triple("oni / ony", "dívají se", "dívali se / dívaly se")
                )
            )

            UVVerbEntry(
                czech = "jmenovat se",
                english = "to be called / to be named",
                rule = "Reflexive verb — se is a clitic occupying the second position in the sentence. Conjugates like pracovat (-ovat type). Formal: jmenuji se; colloquial: jmenuju se — both are correct.",
                example = "Jak se jmenuješ?",
                translation = "What is your name? (informal)"
            )
            UVAspect("Imperfective. No standard perfective.")
            UVTable(
                verb = "jmenovat se", label = "Type 3 (-ovat), reflexive",
                rows = listOf(
                    Triple("já", "jmenuji se  (jmenuju se)", "jsem se jmenoval / jmenovala"),
                    Triple("ty", "jmenuješ se", "jsi se jmenoval / jmenovala"),
                    Triple("on / ona / ono", "jmenuje se", "jmenoval se / jmenovala se / jmenovalo se"),
                    Triple("my", "jmenujeme se", "jsme se jmenovali / jmenovaly"),
                    Triple("vy", "jmenujete se", "jste se jmenovali / jmenovaly"),
                    Triple("oni / ony", "jmenují se", "jmenovali se / jmenovaly se")
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
            UVAspect("Imperfective (stative). No standard perfective.")
            UVTable(
                verb = "vědět", label = "irregular",
                rows = listOf(
                    Triple("já", "vím", "jsem věděl / věděla"),
                    Triple("ty", "víš", "jsi věděl / věděla"),
                    Triple("on / ona / ono", "ví", "věděl / věděla / vědělo"),
                    Triple("my", "víme", "jsme věděli / věděly"),
                    Triple("vy", "víte", "jste věděli / věděly"),
                    Triple("oni / ony", "vědí", "věděli / věděly")
                )
            )

            UVVerbEntry(
                czech = "znát",
                english = "to know (a person / place)",
                rule = "Takes Accusative. Use for familiarity with people, places, or works — not for facts.",
                example = "Znám Prahu.",
                translation = "I know Prague."
            )
            UVAspect("Imperfective (stative). No standard perfective.")
            UVTable(
                verb = "znát", label = "Type 1 (-at)",
                rows = listOf(
                    Triple("já", "znám", "jsem znal / znala"),
                    Triple("ty", "znáš", "jsi znal / znala"),
                    Triple("on / ona / ono", "zná", "znal / znala / znalo"),
                    Triple("my", "známe", "jsme znali / znaly"),
                    Triple("vy", "znáte", "jste znali / znaly"),
                    Triple("oni / ony", "znají", "znali / znaly")
                )
            )

            UVVerbEntry(
                czech = "potkávat",
                english = "to meet / to run into (imperfective)",
                rule = "Takes Accusative. Casual/chance encounter. For arranged meeting use setkat se s + Instrumental.",
                example = "Potkávám ho každý den.",
                translation = "I run into him every day."
            )
            UVAspect("Imperfective. Perfective: potkat.")
            UVTable(
                verb = "potkávat", label = "Type 1 (-at)",
                rows = listOf(
                    Triple("já", "potkávám", "jsem potkával / potkávala"),
                    Triple("ty", "potkáváš", "jsi potkával / potkávala"),
                    Triple("on / ona / ono", "potkává", "potkával / potkávala / potkávalo"),
                    Triple("my", "potkáváme", "jsme potkávali / potkávaly"),
                    Triple("vy", "potkáváte", "jste potkávali / potkávaly"),
                    Triple("oni / ony", "potkávají", "potkávali / potkávaly")
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
            UVAspect("Imperfective. Perfective: zapamatovat si.")
            UVTable(
                verb = "pamatovat si", label = "Type 3 (-ovat), reflexive",
                rows = listOf(
                    Triple("já", "pamatuji si / pamatuju si", "jsem si pamatoval / pamatovala"),
                    Triple("ty", "pamatuješ si", "jsi si pamatoval / pamatovala"),
                    Triple("on / ona / ono", "pamatuje si", "pamatoval si / pamatovala si / pamatovalo si"),
                    Triple("my", "pamatujeme si", "jsme si pamatovali / pamatovaly"),
                    Triple("vy", "pamatujete si", "jste si pamatovali / pamatovaly"),
                    Triple("oni / ony", "pamatují si / pamatujou si", "pamatovali si / pamatovaly si")
                )
            )

            UVVerbEntry(
                czech = "zapomínat",
                english = "to forget (imperfective)",
                rule = "na + Accusative (what you forget). Imperfective; perfective is zapomenout.",
                example = "Zapomínám na klíče.",
                translation = "I keep forgetting the keys."
            )
            UVAspect("Imperfective. Perfective: zapomenout.")
            UVTable(
                verb = "zapomínat", label = "Type 1 (-at)",
                rows = listOf(
                    Triple("já", "zapomínám", "jsem zapomínal / zapomínala"),
                    Triple("ty", "zapomínáš", "jsi zapomínal / zapomínala"),
                    Triple("on / ona / ono", "zapomíná", "zapomínal / zapomínala / zapomínalo"),
                    Triple("my", "zapomínáme", "jsme zapomínali / zapomínaly"),
                    Triple("vy", "zapomínáte", "jste zapomínali / zapomínaly"),
                    Triple("oni / ony", "zapomínají", "zapomínali / zapomínaly")
                )
            )

            UVVerbEntry(
                czech = "rozhodovat se",
                english = "to decide (imperfective)",
                rule = "pro + Accusative (deciding in favour of) or o + Locative (deciding about). Reflexive se required.",
                example = "Rozhoduji se pro nové auto.",
                translation = "I'm deciding on a new car."
            )
            UVAspect("Imperfective. Perfective: rozhodnout se.")
            UVTable(
                verb = "rozhodovat se", label = "Type 3 (-ovat), reflexive",
                rows = listOf(
                    Triple("já", "rozhoduji se / rozhoduju se", "jsem se rozhodoval / rozhodovala"),
                    Triple("ty", "rozhoduješ se", "jsi se rozhodoval / rozhodovala"),
                    Triple("on / ona / ono", "rozhoduje se", "rozhodoval se / rozhodovala se / rozhodovalo se"),
                    Triple("my", "rozhodujeme se", "jsme se rozhodovali / rozhodovaly"),
                    Triple("vy", "rozhodujete se", "jste se rozhodovali / rozhodovaly"),
                    Triple("oni / ony", "rozhodují se / rozhodujou se", "rozhodovali se / rozhodovaly se")
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
            UVAspect("Imperfective. Perfective: najít (irregular: najdu / najdeš / najde).")
            UVTable(
                verb = "hledat", label = "Type 1 (-at)",
                rows = listOf(
                    Triple("já", "hledám", "jsem hledal / hledala"),
                    Triple("ty", "hledáš", "jsi hledal / hledala"),
                    Triple("on / ona / ono", "hledá", "hledal / hledala / hledalo"),
                    Triple("my", "hledáme", "jsme hledali / hledaly"),
                    Triple("vy", "hledáte", "jste hledali / hledaly"),
                    Triple("oni / ony", "hledají", "hledali / hledaly")
                )
            )

            UVVerbEntry(
                czech = "nacházet",
                english = "to find (imperfective)",
                rule = "Takes Accusative. Imperfective of najít (perf.: najdu / najdeš / najde / najdeme / najdete / najdou).",
                example = "Nacházím řešení.",
                translation = "I'm finding a solution."
            )
            UVAspect("Imperfective. Perfective: najít.")
            UVTable(
                verb = "nacházet", label = "Type 2-like (-et)",
                rows = listOf(
                    Triple("já", "nacházím", "jsem nacházel / nacházela"),
                    Triple("ty", "nacházíš", "jsi nacházel / nacházela"),
                    Triple("on / ona / ono", "nachází", "nacházel / nacházela / nacházelo"),
                    Triple("my", "nacházíme", "jsme nacházeli / nacházely"),
                    Triple("vy", "nacházíte", "jste nacházeli / nacházely"),
                    Triple("oni / ony", "nacházejí", "nacházeli / nacházely")
                )
            )

            UVVerbEntry(
                czech = "dovolovat",
                english = "to allow / to permit (imperfective)",
                rule = "Takes Dative (to whom) + infinitive. Perfective is dovolit.",
                example = "Dovoluje mi jít ven.",
                translation = "He allows me to go outside."
            )
            UVAspect("Imperfective. Perfective: dovolit.")
            UVTable(
                verb = "dovolovat", label = "Type 3 (-ovat)",
                rows = listOf(
                    Triple("já", "dovoluji / dovoluju", "jsem dovoloval / dovolovala"),
                    Triple("ty", "dovoluješ", "jsi dovoloval / dovolovala"),
                    Triple("on / ona / ono", "dovoluje", "dovoloval / dovolovala / dovolovalo"),
                    Triple("my", "dovolujeme", "jsme dovolovali / dovolovaly"),
                    Triple("vy", "dovolujete", "jste dovolovali / dovolovaly"),
                    Triple("oni / ony", "dovolují / dovolujou", "dovolovali / dovolovaly")
                )
            )

            UVVerbEntry(
                czech = "smět",
                english = "to be allowed to (modal)",
                rule = "Followed directly by an infinitive. Expresses permission.",
                example = "Smím tu parkovat?",
                translation = "Am I allowed to park here?"
            )
            UVAspect("Imperfective. No standard perfective (modal verb).")
            UVTable(
                verb = "smět", label = "irregular modal",
                rows = listOf(
                    Triple("já", "smím", "jsem směl / směla"),
                    Triple("ty", "smíš", "jsi směl / směla"),
                    Triple("on / ona / ono", "smí", "směl / směla / smělo"),
                    Triple("my", "smíme", "jsme směli / směly"),
                    Triple("vy", "smíte", "jste směli / směly"),
                    Triple("oni / ony", "smí", "směli / směly")
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
private fun UVAspect(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        color = Color.Gray,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(top = 2.dp, bottom = 2.dp, start = 2.dp)
    )
}

@Composable
private fun UVTable(verb: String, label: String, rows: List<Triple<String, String, String>>) {
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
            Text("Pronoun", modifier = Modifier.weight(0.9f), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Text("Present", modifier = Modifier.weight(0.8f), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Text("Past", modifier = Modifier.weight(1.3f), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
        }
        HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
        rows.forEach { (pronoun, present, past) ->
            Row(modifier = Modifier.padding(vertical = 3.dp)) {
                Text(pronoun, modifier = Modifier.weight(0.9f), fontSize = 14.sp, color = Color.DarkGray)
                Text(present, modifier = Modifier.weight(0.8f), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(past, modifier = Modifier.weight(1.3f), fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}
