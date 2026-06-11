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
fun CommonVerbsListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Common Verbs List", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // ── 0. Modal Verbs ────────────────────────────────────────────
            CVLSection("Modal Verbs")
            CVLRow("mít", "Imperf.", "to have", jaPresent = "mám", jaPast = "měl/a jsem")
            CVLRow("mít se", "Imperf.", "to be doing (how one is)", "completely different from mít (to have) — se changes the meaning entirely. Mám se dobře. = I'm doing well. Jak se máš? = How are you doing?", "mám se", "měl/a jsem se")
            CVLRow("moci / moct", "Imperf.", "to be able to / can", "same verb — moci = formal/written; moct = colloquial/spoken", "mohu/můžu", "mohl/a jsem")
            CVLRow("umět", "Imperf.", "to know how to / can (skill)", jaPresent = "umím", jaPast = "uměl/a jsem")
            CVLRow("muset", "Imperf.", "to have to / must", jaPresent = "musím", jaPast = "musel/a jsem")
            CVLRow("potřebovat", "Imperf.", "to need", jaPresent = "potřebuji/potřebuju", jaPast = "potřeboval/a jsem")
            CVLRow("vyžadovat", "Imperf.", "to require", jaPresent = "vyžaduji/vyžaduju", jaPast = "vyžadoval/a jsem")

            // ── 1. Daily Life ─────────────────────────────────────────────
            CVLSection("Daily Life")
            CVLRow("dělat", "Imperf.", "to do", "→ udělat (Perf.)", "dělám", "dělal/a jsem")
            CVLRow("udělat", "Perf.", "to do (complete)", "→ dělat (Imperf.)", "udělám", "udělal/a jsem")
            CVLRow("jíst", "Imperf.", "to eat", "→ sníst (Perf.)", "jím", "jedl/a jsem")
            CVLRow("sníst", "Perf.", "to eat up / finish eating", "→ jíst (Imperf.)", "sním", "snědl/a jsem")
            CVLRow("spát", "Imperf.", "to sleep", jaPresent = "spím", jaPast = "spal/a jsem")
            CVLRow("připravovat", "Imperf.", "to prepare", "→ připravit (Perf.)", "připravuji/připravuju", "připravoval/a jsem")
            CVLRow("připravit", "Perf.", "to prepare (complete)", "→ připravovat (Imperf.)", "připravím", "připravil/a jsem")
            CVLRow("vařit", "Imperf.", "to cook", "→ uvařit (Perf.)", "vařím", "vařil/a jsem")
            CVLRow("uvařit", "Perf.", "to cook (complete)", "→ vařit (Imperf.)", "uvařím", "uvařil/a jsem")
            CVLRow("uklízet", "Imperf.", "to clean", "→ uklidit (Perf.)", "uklízím", "uklízel/a jsem")
            CVLRow("uklidit", "Perf.", "to clean up (complete)", "→ uklízet (Imperf.)", "uklidím", "uklidil/a jsem")
            CVLRow("mýt", "Imperf.", "to wash", "→ umýt (Perf.)", "myji/myju", "myl/a jsem")
            CVLRow("umýt", "Perf.", "to wash (complete)", "→ mýt (Imperf.)", "umyji/umyju", "umyl/a jsem")
            CVLRow("mýt se", "Imperf.", "to wash oneself", "→ umýt se (Perf.); se = reflexive accusative — the person washing IS the direct object (oneself)", "myji/myju se", "myl/a jsem se")
            CVLRow("umýt se", "Perf.", "to wash oneself (complete)", "→ mýt se (Imperf.)", "umyji/umyju se", "umyl/a jsem se")
            CVLRow("mýt si ruce", "Imperf.", "to wash one's (own) hands", "si = reflexive dative ('for oneself'); ruce = acc. of ruce (hands). Compare: mýt se (wash self, acc. se) vs mýt si ruce (wash hands for oneself, dative si + acc. ruce)", "myji/myju si ruce", "myl/a jsem si ruce")
            CVLRow("umýt si ruce", "Perf.", "to wash one's hands (complete)", "→ mýt si ruce (Imperf.)", "umyji/umyju si ruce", "umyl/a jsem si ruce")
            CVLRow("nosit", "Imperf.", "to wear / carry", jaPresent = "nosím", jaPast = "nosil/a jsem")
            CVLRow("odpočívat", "Imperf.", "to rest", "→ odpočinout si (Perf.)", "odpočívám", "odpočíval/a jsem")
            CVLRow("odpočinout si", "Perf.", "to take a rest", "→ odpočívat (Imperf.)", "odpočinu si", "odpočinul/a jsem si")
            CVLRow("kouřit", "Imperf.", "to smoke", jaPresent = "kouřím", jaPast = "kouřil/a jsem")

            // ── 2. Movement & Travel ──────────────────────────────────────
            CVLSection("Movement & Travel")
            CVLRow("jít", "Imperf.", "to walk / go (one direction)", "→ přijít / odejít (Perf.)", "jdu", "šel/šla jsem")
            CVLRow("chodit", "Imperf.", "to walk / go (regular / multi-directional)", jaPresent = "chodím", jaPast = "chodil/a jsem")
            CVLRow("turistovat", "Imperf.", "to hike", jaPresent = "turistuji/turistuju", jaPast = "turistoval/a jsem")
            CVLRow("běžet", "Imperf.", "to run (one direction)", "→ doběhnout (Perf.)", "běžím", "běžel/a jsem")
            CVLRow("běhat", "Imperf.", "to run (regularly)", jaPresent = "běhám", jaPast = "běhal/a jsem")
            CVLRow("budit se", "Imperf.", "to wake up", "→ vzbudit se (Perf.)", "budím se", "budil/a jsem se")
            CVLRow("vzbudit se", "Perf.", "to wake up", "→ budit se (Imperf.)", "vzbudím se", "vzbudil/a jsem se")
            CVLRow("řídit", "Imperf.", "to drive", jaPresent = "řídím", jaPast = "řídil/a jsem")
            CVLRow("navigovat", "Imperf.", "to navigate", jaPresent = "naviguji/naviguju", jaPast = "navigoval/a jsem")
            CVLRow("plavat", "Imperf.", "to swim", "→ přeplavat (Perf.)", "plavu", "plaval/a jsem")
            CVLRow("letět", "Imperf.", "to fly (one direction)", "→ přiletět (Perf.)", "letím", "letěl/a jsem")
            CVLRow("létat", "Imperf.", "to fly (regularly)", jaPresent = "létám", jaPast = "létal/a jsem")
            CVLRow("plout", "Imperf.", "to sail", "→ přeplout (Perf.)", "pluji/pluju", "plul/a jsem")
            CVLRow("pohybovat se", "Imperf.", "to move", "→ přesunout se (Perf.)", "pohybuji/pohybuju se", "pohyboval/a jsem se")
            CVLRow("cestovat", "Imperf.", "to travel", jaPresent = "cestuji/cestuju", jaPast = "cestoval/a jsem")
            CVLRow("přicházet", "Imperf.", "to come (on foot)", "→ přijít (Perf.)", "přicházím", "přicházel/a jsem")
            CVLRow("přijít", "Perf.", "to come (once)", "→ přicházet (Imperf.)", "přijdu", "přišel/přišla jsem")
            CVLRow("odcházet", "Imperf.", "to leave", "→ odejít (Perf.)", "odcházím", "odcházel/a jsem")
            CVLRow("odejít", "Perf.", "to leave (complete)", "→ odcházet (Imperf.)", "odejdu", "odešel/odešla jsem")
            CVLRow("přijíždět", "Imperf.", "to arrive (by vehicle)", "→ přijet (Perf.)", "přijíždím", "přijížděl/a jsem")
            CVLRow("přijet", "Perf.", "to arrive (complete)", "→ přijíždět (Imperf.)", "přijedu", "přijel/a jsem")
            CVLRow("spěchat", "Imperf.", "to hurry", jaPresent = "spěchám", jaPast = "spěchal/a jsem")
            CVLRow("procházet", "Imperf.", "to pass by / go through", "→ projít (Perf.)", "procházím", "procházel/a jsem")
            CVLRow("projít", "Perf.", "to pass (complete)", "→ procházet (Imperf.)", "projdu", "prošel/prošla jsem")

            // ── 3. Communication & Speech ─────────────────────────────────
            CVLSection("Communication & Speech")
            CVLRow("mluvit", "Imperf.", "to speak", jaPresent = "mluvím", jaPast = "mluvil/a jsem")
            CVLRow("povídat", "Imperf.", "to talk / chat", "→ povědět (Perf.)", "povídám", "povídal/a jsem")
            CVLRow("křičet", "Imperf.", "to shout", "→ zakřičet (Perf.)", "křičím", "křičel/a jsem")
            CVLRow("řvát", "Imperf.", "to yell / roar", "→ zařvat (Perf.)", "řvu", "řval/a jsem")
            CVLRow("volat", "Imperf.", "to call", "→ zavolat (Perf.)", "volám", "volal/a jsem")
            CVLRow("odpovídat", "Imperf.", "to answer", "→ odpovědět (Perf.)", "odpovídám", "odpovídal/a jsem")
            CVLRow("ptát se", "Imperf.", "to ask / question", "→ zeptat se (Perf.)", "ptám se", "ptal/a jsem se")
            CVLRow("říkat", "Imperf.", "to say / tell", "→ říct (Perf.)", "říkám", "říkal/a jsem")
            CVLRow("hádat se", "Imperf.", "to argue", "→ pohádat se (Perf.)", "hádám se", "hádal/a jsem se")

            // ── 4. Mind & Knowledge ───────────────────────────────────────
            CVLSection("Mind & Knowledge")
            CVLRow("vědět", "Imperf.", "to know (a fact)", jaPresent = "vím", jaPast = "věděl/a jsem")
            CVLRow("znát", "Imperf.", "to know (a person / place)", jaPresent = "znám", jaPast = "znal/a jsem")
            CVLRow("chtít", "Imperf.", "to want", jaPresent = "chci", jaPast = "chtěl/a jsem")
            CVLRow("zjišťovat", "Imperf.", "to find out", "→ zjistit (Perf.)", "zjišťuji/zjišťuju", "zjišťoval/a jsem")
            CVLRow("zjistit", "Perf.", "to find out (complete)", "→ zjišťovat (Imperf.)", "zjistím", "zjistil/a jsem")
            CVLRow("myslet", "Imperf.", "to think", jaPresent = "myslím", jaPast = "myslel/a jsem")
            CVLRow("pamatovat si", "Imperf.", "to remember", "→ zapamatovat si (Perf.)", "pamatuji/pamatuju si", "pamatoval/a jsem si")
            CVLRow("zapamatovat si", "Perf.", "to memorize / remember", "→ pamatovat si (Imperf.)", "zapamatuji/zapamatuju si", "zapamatoval/a jsem si")
            CVLRow("zapomínat", "Imperf.", "to forget", "→ zapomenout (Perf.)", "zapomínám", "zapomínal/a jsem")
            CVLRow("zapomenout", "Perf.", "to forget (completely)", "→ zapomínat (Imperf.)", "zapomenu", "zapomněl/a jsem")
            CVLRow("procvičovat", "Imperf.", "to practice", "→ procvičit (Perf.)", "procvičuji/procvičuju", "procvičoval/a jsem")
            CVLRow("procvičit", "Perf.", "to practice (complete)", "→ procvičovat (Imperf.)", "procvičím", "procvičil/a jsem")
            CVLRow("rozumět", "Imperf.", "to understand", "→ pochopit (Perf.)", "rozumím", "rozuměl/a jsem")
            CVLRow("pochopit", "Perf.", "to understand (grasp)", "→ rozumět (Imperf.)", "pochopím", "pochopil/a jsem")
            CVLRow("odhadovat", "Imperf.", "to estimate", "→ odhadnout (Perf.)", "odhaduji/odhaduju", "odhadoval/a jsem")
            CVLRow("odhadnout", "Perf.", "to estimate (once)", "→ odhadovat (Imperf.)", "odhadnu", "odhadnul/a jsem")
            CVLRow("určovat", "Imperf.", "to determine", "→ určit (Perf.)", "určuji/určuju", "určoval/a jsem")
            CVLRow("určit", "Perf.", "to determine (complete)", "→ určovat (Imperf.)", "určím", "určil/a jsem")

            // ── 5. Emotions & Feelings ────────────────────────────────────
            CVLSection("Emotions & Feelings")
            CVLRow("milovat", "Imperf.", "to love", jaPresent = "miluji/miluju", jaPast = "miloval/a jsem")
            CVLRow("mít rád / ráda", "Imperf.", "to like / be fond of", jaPresent = "mám rád/ráda", jaPast = "měl/a jsem rád/ráda")
            CVLRow("nenávidět", "Imperf.", "to hate", jaPresent = "nenávidím", jaPast = "nenáviděl/a jsem")
            CVLRow("obtěžovat", "Imperf.", "to annoy / bother", jaPresent = "obtěžuji/obtěžuju", jaPast = "obtěžoval/a jsem")
            CVLRow("chválit", "Imperf.", "to compliment / praise", "→ pochválit (Perf.)", "chválím", "chválil/a jsem")
            CVLRow("pochválit", "Perf.", "to compliment (complete)", "→ chválit (Imperf.)", "pochválím", "pochválil/a jsem")
            CVLRow("starat se", "Imperf.", "to care (about)", jaPresent = "starám se", jaPast = "staral/a jsem se")
            CVLRow("oceňovat", "Imperf.", "to appreciate", "→ ocenit (Perf.)", "oceňuji/oceňuju", "oceňoval/a jsem")
            CVLRow("ocenit", "Perf.", "to appreciate (once)", "→ oceňovat (Imperf.)", "ocením", "ocenil/a jsem")
            CVLRow("pečovat", "Imperf.", "to nurture / care for", jaPresent = "pečuji/pečuju", jaPast = "pečoval/a jsem")
            CVLRow("usmívat se", "Imperf.", "to smile", "→ usmát se (Perf.)", "usmívám se", "usmíval/a jsem se")
            CVLRow("usmát se", "Perf.", "to smile (once)", "→ usmívat se (Imperf.)", "usměji se", "usmál/a jsem se")
            CVLRow("bát se", "Imperf.", "to be afraid / to fear", "inherently reflexive — no perfective pair for ongoing fear; leknout se (Perf.) = to get startled (sudden fright — related but different)", "bojím se", "bál/a jsem se")

            // ── 6. Work & Study ───────────────────────────────────────────
            CVLSection("Work & Study")
            CVLRow("studovat", "Imperf.", "to study", jaPresent = "studuji/studuju", jaPast = "studoval/a jsem")
            CVLRow("pracovat", "Imperf.", "to work", jaPresent = "pracuji/pracuju", jaPast = "pracoval/a jsem")
            CVLRow("učit se", "Imperf.", "to learn", "→ naučit se (Perf.)", "učím se", "učil/a jsem se")
            CVLRow("naučit se", "Perf.", "to learn (master)", "→ učit se (Imperf.)", "naučím se", "naučil/a jsem se")
            CVLRow("přednášet", "Imperf.", "to lecture", "→ přednést (Perf.)", "přednáším", "přednášel/a jsem")
            CVLRow("léčit", "Imperf.", "to treat (medically)", "→ vyléčit (Perf.)", "léčím", "léčil/a jsem")
            CVLRow("vyléčit", "Perf.", "to cure / treat (complete)", "→ léčit (Imperf.)", "vyléčím", "vyléčil/a jsem")
            CVLRow("vyšetřovat", "Imperf.", "to investigate", "→ vyšetřit (Perf.)", "vyšetřuji/vyšetřuju", "vyšetřoval/a jsem")
            CVLRow("vyšetřit", "Perf.", "to investigate (complete)", "→ vyšetřovat (Imperf.)", "vyšetřím", "vyšetřil/a jsem")
            CVLRow("zvládat", "Imperf.", "to handle", "→ zvládnout (Perf.)", "zvládám", "zvládal/a jsem")
            CVLRow("zvládnout", "Perf.", "to handle (complete)", "→ zvládat (Imperf.)", "zvládnu", "zvládnul/a jsem")
            CVLRow("spravovat", "Imperf.", "to manage / administer", jaPresent = "spravuji/spravuju", jaPast = "spravoval/a jsem")
            CVLRow("zakládat", "Imperf.", "to establish / found", "→ založit (Perf.)", "zakládám", "zakládal/a jsem")
            CVLRow("založit", "Perf.", "to establish (complete)", "→ zakládat (Imperf.)", "založím", "založil/a jsem")
            CVLRow("povyšovat", "Imperf.", "to promote (career)", "→ povýšit (Perf.)", "povyšuji/povyšuju", "povyšoval/a jsem")
            CVLRow("povýšit", "Perf.", "to promote (complete)", "→ povyšovat (Imperf.)", "povýším", "povýšil/a jsem")
            CVLRow("degradovat", "Imperf.", "to demote", jaPresent = "degraduji/degraduju", jaPast = "degradoval/a jsem")
            CVLRow("dosahovat", "Imperf.", "to achieve", "→ dosáhnout (Perf.)", "dosahuji/dosahuju", "dosahoval/a jsem")
            CVLRow("dosáhnout", "Perf.", "to achieve (complete)", "→ dosahovat (Imperf.)", "dosáhnu", "dosáhnul/a jsem")

            // ── 7. Objects & Household Actions ───────────────────────────
            CVLSection("Objects & Household Actions")
            CVLRow("zapínat", "Imperf.", "to turn on", "→ zapnout (Perf.)", "zapínám", "zapínal/a jsem")
            CVLRow("zapnout", "Perf.", "to turn on (complete)", "→ zapínat (Imperf.)", "zapnu", "zapnul/a jsem")
            CVLRow("vypínat", "Imperf.", "to turn off", "→ vypnout (Perf.)", "vypínám", "vypínal/a jsem")
            CVLRow("vypnout", "Perf.", "to turn off (complete)", "→ vypínat (Imperf.)", "vypnu", "vypnul/a jsem")
            CVLRow("zamykat", "Imperf.", "to lock", "→ zamknout (Perf.)", "zamykám", "zamykal/a jsem")
            CVLRow("zamknout", "Perf.", "to lock (complete)", "→ zamykat (Imperf.)", "zamknu", "zamknul/a jsem")
            CVLRow("otevírat", "Imperf.", "to open", "→ otevřít (Perf.)", "otevírám", "otevíral/a jsem")
            CVLRow("otevřít", "Perf.", "to open (complete)", "→ otevírat (Imperf.)", "otevřu", "otevřel/a jsem")
            CVLRow("zavírat", "Imperf.", "to close", "→ zavřít (Perf.)", "zavírám", "zavíral/a jsem")
            CVLRow("zavřít", "Perf.", "to close (complete)", "→ zavírat (Imperf.)", "zavřu", "zavřel/a jsem")
            CVLRow("brát", "Imperf.", "to take", "→ vzít (Perf.)", "beru", "bral/a jsem")
            CVLRow("vzít", "Perf.", "to take (complete)", "→ brát (Imperf.)", "vezmu", "vzal/a jsem")
            CVLRow("dávat", "Imperf.", "to put / give", "→ dát (Perf.)", "dávám", "dával/a jsem")
            CVLRow("dát", "Perf.", "to put / give (complete)", "→ dávat (Imperf.)", "dám", "dal/a jsem")
            CVLRow("posílat", "Imperf.", "to send", "→ poslat (Perf.)", "posílám", "posílal/a jsem")
            CVLRow("poslat", "Perf.", "to send (complete)", "→ posílat (Imperf.)", "pošlu", "poslal/a jsem")
            CVLRow("dostávat", "Imperf.", "to receive", "→ dostat (Perf.)", "dostávám", "dostával/a jsem")
            CVLRow("dostat", "Perf.", "to receive (complete)", "→ dostávat (Imperf.)", "dostanu", "dostal/a jsem")
            CVLRow("zapalovat", "Imperf.", "to ignite / light (fire)", "→ zapálit (Perf.)", "zapaluji/zapaluju", "zapaloval/a jsem")
            CVLRow("zapálit", "Perf.", "to ignite (complete)", "→ zapalovat (Imperf.)", "zapálím", "zapálil/a jsem")
            CVLRow("rozsvěcovat", "Imperf.", "to light up / switch on", "→ rozsvítit (Perf.)", "rozsvěcuji/rozsvěcuju", "rozsvěcoval/a jsem")
            CVLRow("rozsvítit", "Perf.", "to light up (complete)", "→ rozsvěcovat (Imperf.)", "rozsvítím", "rozsvítil/a jsem")

            // ── 8. Digital & Tech Actions ─────────────────────────────────
            CVLSection("Digital & Tech Actions")
            CVLRow("mačkat", "Imperf.", "to press", "→ zmáčknout (Perf.)", "mačkám", "mačkal/a jsem")
            CVLRow("zmáčknout", "Perf.", "to press (once)", "→ mačkat (Imperf.)", "zmáčknu", "zmáčknul/a jsem")
            CVLRow("klikat", "Imperf.", "to click", "→ kliknout (Perf.)", "klikám", "klikal/a jsem")
            CVLRow("kliknout", "Perf.", "to click (once)", "→ klikat (Imperf.)", "kliknu", "kliknul/a jsem")
            CVLRow("sledovat", "Imperf.", "to follow / watch", jaPresent = "sleduji/sleduju", jaPast = "sledoval/a jsem")
            CVLRow("navrhovat", "Imperf.", "to design", "→ navrhnout (Perf.)", "navrhuji/navrhuju", "navrhoval/a jsem")
            CVLRow("navrhnout", "Perf.", "to design (complete)", "→ navrhovat (Imperf.)", "navrhnu", "navrhnul/a jsem")
            CVLRow("tisknout", "Imperf.", "to print", "→ vytisknout (Perf.)", "tisknu", "tisknul/a jsem")
            CVLRow("vytisknout", "Perf.", "to print (complete)", "→ tisknout (Imperf.)", "vytisknu", "vytisknul/a jsem")
            CVLRow("používat", "Imperf.", "to use", "→ použít (Perf.)", "používám", "používal/a jsem")
            CVLRow("použít", "Perf.", "to use (once)", "→ používat (Imperf.)", "použiji/použiju", "použil/a jsem")
            CVLRow("ladit", "Imperf.", "to debug", "→ odladit (Perf.)", "ladím", "ladil/a jsem")
            CVLRow("odladit", "Perf.", "to debug (complete)", "→ ladit (Imperf.)", "odladím", "odladil/a jsem")
            CVLRow("kompilovat", "Imperf.", "to compile", jaPresent = "kompiluji/kompiluju", jaPast = "kompiloval/a jsem")
            CVLRow("linkovat", "Imperf.", "to link (build process)", jaPresent = "linkuji/linkuju", jaPast = "linkoval/a jsem")
            CVLRow("programovat", "Imperf.", "to code / program", jaPresent = "programuji/programuju", jaPast = "programoval/a jsem")
            CVLRow("zpracovávat", "Imperf.", "to process", "→ zpracovat (Perf.)", "zpracovávám", "zpracovával/a jsem")
            CVLRow("zpracovat", "Perf.", "to process (complete)", "→ zpracovávat (Imperf.)", "zpracuji/zpracuju", "zpracoval/a jsem")
            CVLRow("blokovat", "Imperf.", "to block", jaPresent = "blokuji/blokuju", jaPast = "blokoval/a jsem")
            CVLRow("povolovat", "Imperf.", "to allow", "→ povolit (Perf.)", "povoluji/povoluju", "povoloval/a jsem")
            CVLRow("povolit", "Perf.", "to allow (complete)", "→ povolovat (Imperf.)", "povolím", "povolil/a jsem")
            CVLRow("zakazovat", "Imperf.", "to deny / forbid", "→ zakázat (Perf.)", "zakazuji/zakazuju", "zakazoval/a jsem")
            CVLRow("zakázat", "Perf.", "to deny (complete)", "→ zakazovat (Imperf.)", "zakáži", "zakázal/a jsem")
            CVLRow("načítat", "Imperf.", "to load (data)", "→ načíst (Perf.)", "načítám", "načítal/a jsem")
            CVLRow("načíst", "Perf.", "to load (complete)", "→ načítat (Imperf.)", "načtu", "načetl/a jsem")
            CVLRow("ukládat", "Imperf.", "to save (data)", "→ uložit (Perf.)", "ukládám", "ukládal/a jsem")
            CVLRow("uložit", "Perf.", "to save (complete)", "→ ukládat (Imperf.)", "uložím", "uložil/a jsem")
            CVLRow("připojovat", "Imperf.", "to connect", "→ připojit (Perf.)", "připojuji/připojuju", "připojoval/a jsem")
            CVLRow("připojit", "Perf.", "to connect (complete)", "→ připojovat (Imperf.)", "připojím", "připojil/a jsem")
            CVLRow("odpojovat", "Imperf.", "to disconnect", "→ odpojit (Perf.)", "odpojuji/odpojuju", "odpojoval/a jsem")
            CVLRow("odpojit", "Perf.", "to disconnect (complete)", "→ odpojovat (Imperf.)", "odpojím", "odpojil/a jsem")

            // ── 9. Food & Cooking ─────────────────────────────────────────
            CVLSection("Food & Cooking")
            CVLRow("sekat", "Imperf.", "to chop", "→ nasekat (Perf.)", "sekám", "sekal/a jsem")
            CVLRow("nasekat", "Perf.", "to chop (complete)", "→ sekat (Imperf.)", "nasekám", "nasekal/a jsem")
            CVLRow("smažit", "Imperf.", "to fry", "→ usmažit (Perf.)", "smažím", "smažil/a jsem")
            CVLRow("usmažit", "Perf.", "to fry (complete)", "→ smažit (Imperf.)", "usmažím", "usmažil/a jsem")
            CVLRow("péct", "Imperf.", "to bake", "→ upéct (Perf.)", "peču/peku", "pekl/a jsem")
            CVLRow("upéct", "Perf.", "to bake (complete)", "→ péct (Imperf.)", "upeču/upeku", "upekl/a jsem")
            CVLRow("míchat", "Imperf.", "to mix / stir", "→ zamíchat (Perf.)", "míchám", "míchal/a jsem")
            CVLRow("zamíchat", "Perf.", "to mix (complete)", "→ míchat (Imperf.)", "zamíchám", "zamíchal/a jsem")
            CVLRow("krájet", "Imperf.", "to cut / slice", "→ nakrájet (Perf.)", "krájím", "krájel/a jsem")
            CVLRow("nakrájet", "Perf.", "to slice (complete)", "→ krájet (Imperf.)", "nakrájím", "nakrájel/a jsem")
            CVLRow("zahřívat", "Imperf.", "to heat", "→ zahřát (Perf.)", "zahřívám", "zahříval/a jsem")
            CVLRow("zahřát", "Perf.", "to heat up (complete)", "→ zahřívat (Imperf.)", "zahřeji/zahřeju", "zahřál/a jsem")
            CVLRow("chladit", "Imperf.", "to cool", "→ ochladit (Perf.)", "chladím", "chladil/a jsem")
            CVLRow("ochladit", "Perf.", "to cool down (complete)", "→ chladit (Imperf.)", "ochladím", "ochladil/a jsem")
            CVLRow("ochutnávat", "Imperf.", "to taste", "→ ochutnat (Perf.)", "ochutnávám", "ochutnával/a jsem")
            CVLRow("ochutnat", "Perf.", "to taste (once)", "→ ochutnávat (Imperf.)", "ochutnám", "ochutnal/a jsem")
            CVLRow("zkoušet", "Imperf.", "to try", "→ zkusit (Perf.)", "zkouším", "zkoušel/a jsem")
            CVLRow("zkusit", "Perf.", "to try (once)", "→ zkoušet (Imperf.)", "zkusím", "zkusil/a jsem")
            CVLRow("preferovat", "Imperf.", "to prefer", jaPresent = "preferuji/preferuju", jaPast = "preferoval/a jsem")
            CVLRow("doporučovat", "Imperf.", "to recommend", "→ doporučit (Perf.)", "doporučuji/doporučuju", "doporučoval/a jsem")
            CVLRow("doporučit", "Perf.", "to recommend (complete)", "→ doporučovat (Imperf.)", "doporučím", "doporučil/a jsem")

            // ── 10. Commerce & Transactions ───────────────────────────────
            CVLSection("Commerce & Transactions")
            CVLRow("nakupovat", "Imperf.", "to shop", jaPresent = "nakupuji/nakupuju", jaPast = "nakupoval/a jsem")
            CVLRow("kupovat", "Imperf.", "to buy", "→ koupit (Perf.)", "kupuji/kupuju", "kupoval/a jsem")
            CVLRow("koupit", "Perf.", "to buy (complete)", "→ kupovat (Imperf.)", "koupím", "koupil/a jsem")
            CVLRow("prodávat", "Imperf.", "to sell", "→ prodat (Perf.)", "prodávám", "prodával/a jsem")
            CVLRow("prodat", "Perf.", "to sell (complete)", "→ prodávat (Imperf.)", "prodám", "prodal/a jsem")
            CVLRow("objednávat", "Imperf.", "to order", "→ objednat (Perf.)", "objednávám", "objednával/a jsem")
            CVLRow("objednat", "Perf.", "to order (complete)", "→ objednávat (Imperf.)", "objednám", "objednal/a jsem")
            CVLRow("žádat", "Imperf.", "to request / ask for", "→ požádat (Perf.)", "žádám", "žádal/a jsem")
            CVLRow("požádat", "Perf.", "to request (complete)", "→ žádat (Imperf.)", "požádám", "požádal/a jsem")
            CVLRow("investovat", "Imperf.", "to invest", jaPresent = "investuji/investuju", jaPast = "investoval/a jsem")
            CVLRow("šetřit", "Imperf.", "to save (money)", jaPresent = "šetřím", jaPast = "šetřil/a jsem")
            CVLRow("zachraňovat", "Imperf.", "to save / rescue", "→ zachránit (Perf.)", "zachraňuji/zachraňuju", "zachraňoval/a jsem")
            CVLRow("zachránit", "Perf.", "to rescue (complete)", "→ zachraňovat (Imperf.)", "zachráním", "zachránil/a jsem")
            CVLRow("vyhrávat", "Imperf.", "to win", "→ vyhrát (Perf.)", "vyhrávám", "vyhrával/a jsem")
            CVLRow("vyhrát", "Perf.", "to win (complete)", "→ vyhrávat (Imperf.)", "vyhraji/vyhráju", "vyhrál/a jsem")
            CVLRow("prohrávat", "Imperf.", "to lose (a game)", "→ prohrát (Perf.)", "prohrávám", "prohrával/a jsem")
            CVLRow("prohrát", "Perf.", "to lose (complete)", "→ prohrávat (Imperf.)", "prohraji/prohraju", "prohrál/a jsem")
            CVLRow("sázet", "Imperf.", "to bet / gamble", "→ vsadit (Perf.)", "sázím", "sázel/a jsem")
            CVLRow("vsadit", "Perf.", "to place a bet", "→ sázet (Imperf.)", "vsadím", "vsadil/a jsem")
            CVLRow("propagovat", "Imperf.", "to market / promote", jaPresent = "propaguji/propaguju", jaPast = "propagoval/a jsem")
            CVLRow("distribuovat", "Imperf.", "to distribute", jaPresent = "distribuuji/distribuuju", jaPast = "distribuoval/a jsem")

            // ── 11. Social & Relationships ────────────────────────────────
            CVLSection("Social & Relationships")
            CVLRow("pomáhat", "Imperf.", "to help", "→ pomoct (Perf.)", "pomáhám", "pomáhal/a jsem")
            CVLRow("pomoct", "Perf.", "to help (complete)", "→ pomáhat (Imperf.)", "pomohu/pomůžu", "pomohl/a jsem")
            CVLRow("setkávat se", "Imperf.", "to meet", "→ setkat se (Perf.)", "setkávám se", "setkával/a jsem se")
            CVLRow("setkat se", "Perf.", "to meet (once)", "→ setkávat se (Imperf.)", "setkám se", "setkal/a jsem se")
            CVLRow("domlouvat", "Imperf.", "to arrange", "→ domluvit (Perf.)", "domlouvám", "domlouval/a jsem")
            CVLRow("domluvit", "Perf.", "to arrange (complete)", "→ domlouvat (Imperf.)", "domluvím", "domluvil/a jsem")
            CVLRow("vybírat", "Imperf.", "to choose / select", "→ vybrat (Perf.)", "vybírám", "vybíral/a jsem")
            CVLRow("vybrat", "Perf.", "to choose (complete)", "→ vybírat (Imperf.)", "vyberu", "vybral/a jsem")
            CVLRow("zvát", "Imperf.", "to invite", "→ pozvat (Perf.)", "zvu", "zval/a jsem")
            CVLRow("pozvat", "Perf.", "to invite (complete)", "→ zvát (Imperf.)", "pozvu", "pozval/a jsem")
            CVLRow("navštěvovat", "Imperf.", "to visit", "→ navštívit (Perf.)", "navštěvuji/navštěvuju", "navštěvoval/a jsem")
            CVLRow("navštívit", "Perf.", "to visit (complete)", "→ navštěvovat (Imperf.)", "navštívím", "navštívil/a jsem")
            CVLRow("jmenovat se", "Imperf.", "to be called / named", jaPresent = "jmenuji/jmenuju se", jaPast = "jmenoval/a jsem se")
            CVLRow("přibližovat se", "Imperf.", "to approach", "→ přiblížit se (Perf.)", "přibližuji/přibližuju se", "přibližoval/a jsem se")
            CVLRow("přiblížit se", "Perf.", "to approach (complete)", "→ přibližovat se (Imperf.)", "přiblížím se", "přiblížil/a jsem se")
            CVLRow("flirtovat", "Imperf.", "to flirt", jaPresent = "flirtuji/flirtuju", jaPast = "flirtoval/a jsem")
            CVLRow("dvořit se", "Imperf.", "to court / woo", jaPresent = "dvořím se", jaPast = "dvořil/a jsem se")
            CVLRow("otravovat", "Imperf.", "to nag / pester", jaPresent = "otravuji/otravuju", jaPast = "otravoval/a jsem")
            CVLRow("modlit se", "Imperf.", "to pray", jaPresent = "modlím se", jaPast = "modlil/a jsem se")

            // ── 12. Creation & Maintenance ────────────────────────────────
            CVLSection("Creation & Maintenance")
            CVLRow("vytvářet", "Imperf.", "to create", "→ vytvořit (Perf.)", "vytvářím", "vytvářel/a jsem")
            CVLRow("vytvořit", "Perf.", "to create (complete)", "→ vytvářet (Imperf.)", "vytvořím", "vytvořil/a jsem")
            CVLRow("ničit", "Imperf.", "to destroy", "→ zničit (Perf.)", "ničím", "ničil/a jsem")
            CVLRow("zničit", "Perf.", "to destroy (complete)", "→ ničit (Imperf.)", "zničím", "zničil/a jsem")
            CVLRow("stavět", "Imperf.", "to build", "→ postavit (Perf.)", "stavím", "stavěl/a jsem")
            CVLRow("postavit", "Perf.", "to build (complete)", "→ stavět (Imperf.)", "postavím", "postavil/a jsem")
            CVLRow("malovat", "Imperf.", "to paint", "→ namalovat (Perf.)", "maluji/maluju", "maloval/a jsem")
            CVLRow("namalovat", "Perf.", "to paint (complete)", "→ malovat (Imperf.)", "namaluji/namaluju", "namaloval/a jsem")
            CVLRow("opravovat", "Imperf.", "to fix / repair", "→ opravit (Perf.)", "opravuji/opravuju", "opravoval/a jsem")
            CVLRow("opravit", "Perf.", "to fix (complete)", "→ opravovat (Imperf.)", "opravím", "opravil/a jsem")
            CVLRow("měnit", "Imperf.", "to change", "→ změnit (Perf.)", "měním", "měnil/a jsem")
            CVLRow("změnit", "Perf.", "to change (complete)", "→ měnit (Imperf.)", "změním", "změnil/a jsem")
            CVLRow("vyměňovat", "Imperf.", "to replace / exchange", "→ vyměnit (Perf.)", "vyměňuji/vyměňuju", "vyměňoval/a jsem")
            CVLRow("vyměnit", "Perf.", "to replace (complete)", "→ vyměňovat (Imperf.)", "vyměním", "vyměnil/a jsem")
            CVLRow("organizovat", "Imperf.", "to organize", "→ zorganizovat (Perf.)", "organizuji/organizuju", "organizoval/a jsem")
            CVLRow("zorganizovat", "Perf.", "to organize (complete)", "→ organizovat (Imperf.)", "zorganizuji/zorganizuju", "zorganizoval/a jsem")
            CVLRow("oddělovat", "Imperf.", "to separate", "→ oddělit (Perf.)", "odděluji/odděluju", "odděloval/a jsem")
            CVLRow("oddělit", "Perf.", "to separate (complete)", "→ oddělovat (Imperf.)", "oddělím", "oddělil/a jsem")
            CVLRow("vynalézat", "Imperf.", "to invent", "→ vynalést (Perf.)", "vynalézám", "vynalézal/a jsem")
            CVLRow("vynalést", "Perf.", "to invent (complete)", "→ vynalézat (Imperf.)", "vynalezu", "vynalezl/a jsem")
            CVLRow("upravovat", "Imperf.", "to adjust", "→ upravit (Perf.)", "upravuji/upravuju", "upravoval/a jsem")
            CVLRow("upravit", "Perf.", "to adjust (complete)", "→ upravovat (Imperf.)", "upravím", "upravil/a jsem")

            // ── 13. Processes & Life Events ───────────────────────────────
            CVLSection("Processes & Life Events")
            CVLRow("být", "Imperf.", "to be", jaPresent = "jsem", jaPast = "byl/a jsem")
            CVLRow("žít", "Imperf.", "to live", jaPresent = "žiji/žiju", jaPast = "žil/a jsem")
            CVLRow("umírat", "Imperf.", "to die", "→ zemřít (Perf.)", "umírám", "umíral/a jsem")
            CVLRow("zemřít", "Perf.", "to die", "→ umírat (Imperf.)", "zemřu", "zemřel/a jsem")
            CVLRow("rodit se", "Imperf.", "to be born", "→ narodit se (Perf.)", "rodím se", "rodil/a jsem se")
            CVLRow("narodit se", "Perf.", "to be born", "→ rodit se (Imperf.)", "narodím se", "narodil/a jsem se")
            CVLRow("zastavovat", "Imperf.", "to stop", "→ zastavit (Perf.)", "zastavuji/zastavuju", "zastavoval/a jsem")
            CVLRow("zastavit", "Perf.", "to stop (complete)", "→ zastavovat (Imperf.)", "zastavím", "zastavil/a jsem")
            CVLRow("začínat", "Imperf.", "to begin", "→ začít (Perf.)", "začínám", "začínal/a jsem")
            CVLRow("začít", "Perf.", "to begin", "→ začínat (Imperf.)", "začnu", "začal/a jsem")
            CVLRow("končit", "Imperf.", "to finish", "→ skončit (Perf.)", "končím", "končil/a jsem")
            CVLRow("skončit", "Perf.", "to finish (complete)", "→ končit (Imperf.)", "skončím", "skončil/a jsem")
            CVLRow("dokončovat", "Imperf.", "to complete", "→ dokončit (Perf.)", "dokončuji/dokončuju", "dokončoval/a jsem")
            CVLRow("dokončit", "Perf.", "to complete (finish)", "→ dokončovat (Imperf.)", "dokončím", "dokončil/a jsem")
            CVLRow("vyhýbat se", "Imperf.", "to avoid", "→ vyhnout se (Perf.)", "vyhýbám se", "vyhýbal/a jsem se")
            CVLRow("vyhnout se", "Perf.", "to avoid (successfully)", "→ vyhýbat se (Imperf.)", "vyhnu se", "vyhnul/a jsem se")
            CVLRow("obnovovat", "Imperf.", "to resume / renew", "→ obnovit (Perf.)", "obnovuji/obnovuju", "obnovoval/a jsem")
            CVLRow("obnovit", "Perf.", "to resume (complete)", "→ obnovovat (Imperf.)", "obnovím", "obnovil/a jsem")
            CVLRow("pozastavovat", "Imperf.", "to pause", "→ pozastavit (Perf.)", "pozastavuji/pozastavuju", "pozastavoval/a jsem")
            CVLRow("pozastavit", "Perf.", "to pause (complete)", "→ pozastavovat (Imperf.)", "pozastavím", "pozastavil/a jsem")
            CVLRow("ukazovat", "Imperf.", "to point / show", "→ ukázat (Perf.)", "ukazuji/ukazuju", "ukazoval/a jsem")
            CVLRow("ukázat", "Perf.", "to point / show (once)", "→ ukazovat (Imperf.)", "ukáži", "ukázal/a jsem")
            CVLRow("pasovat", "Imperf.", "to match / fit", jaPresent = "pasuji/pasuju", jaPast = "pasoval/a jsem")
            CVLRow("počítat", "Imperf.", "to count", "→ spočítat (Perf.)", "počítám", "počítal/a jsem")
            CVLRow("spočítat", "Perf.", "to count (complete)", "→ počítat (Imperf.)", "spočítám", "spočítal/a jsem")
            CVLRow("objevovat se", "Imperf.", "to appear", "→ objevit se (Perf.)", "objevuji/objevuju se", "objevoval/a jsem se")
            CVLRow("objevit se", "Perf.", "to appear (once)", "→ objevovat se (Imperf.)", "objevím se", "objevil/a jsem se")
            CVLRow("dýchat", "Imperf.", "to breathe", jaPresent = "dýchám", jaPast = "dýchal/a jsem")
            CVLRow("přestávat", "Imperf.", "to quit / stop doing", "→ přestat (Perf.)", "přestávám", "přestával/a jsem")
            CVLRow("přestat", "Perf.", "to quit (complete)", "→ přestávat (Imperf.)", "přestanu", "přestal/a jsem")
            CVLRow("stahovat se", "Imperf.", "to withdraw", "→ stáhnout se (Perf.)", "stahuji/stahuju se", "stahoval/a jsem se")
            CVLRow("stáhnout se", "Perf.", "to withdraw (complete)", "→ stahovat se (Imperf.)", "stáhnu se", "stáhnul/a jsem se")
            CVLRow("odcházet do důchodu", "Imperf.", "to retire", jaPresent = "odcházím do důchodu", jaPast = "odcházel/a jsem do důchodu")
            CVLRow("topit se", "Imperf.", "to drown", "→ utopit se (Perf.)", "topím se", "topil/a jsem se")
            CVLRow("utopit se", "Perf.", "to drown (complete)", "→ topit se (Imperf.)", "utopím se", "utopil/a jsem se")
            CVLRow("růst", "Imperf.", "to grow (intransitive)", "→ vyrůst (Perf.)", "rostu", "rostl/a jsem")
            CVLRow("vyrůst", "Perf.", "to grow up (complete)", "→ růst (Imperf.)", "vyrostu", "vyrostl/a jsem")
            CVLRow("pěstovat", "Imperf.", "to grow (plants / raise)", "→ vypěstovat (Perf.)", "pěstuji/pěstuju", "pěstoval/a jsem")
            CVLRow("vypěstovat", "Perf.", "to grow (complete)", "→ pěstovat (Imperf.)", "vypěstuji/vypěstuju", "vypěstoval/a jsem")

            // ── 14. Senses & Perception ───────────────────────────────────
            CVLSection("Senses & Perception")
            CVLRow("poslouchat", "Imperf.", "to listen", "→ vyslechnout (Perf.)", "poslouchám", "poslouchal/a jsem")
            CVLRow("slyšet", "Imperf.", "to hear", jaPresent = "slyším", jaPast = "slyšel/a jsem")
            CVLRow("vidět", "Imperf.", "to see", jaPresent = "vidím", jaPast = "viděl/a jsem")
            CVLRow("dívat se", "Imperf.", "to watch", "→ podívat se (Perf.)", "dívám se", "díval/a jsem se")
            CVLRow("podívat se", "Perf.", "to take a look", "→ dívat se (Imperf.)", "podívám se", "podíval/a jsem se")
            CVLRow("čichat", "Imperf.", "to smell", jaPresent = "čichám", jaPast = "čichal/a jsem")
            CVLRow("hledět", "Imperf.", "to gaze", jaPresent = "hledím", jaPast = "hleděl/a jsem")
            CVLRow("zírat", "Imperf.", "to stare", jaPresent = "zírám", jaPast = "zíral/a jsem")

            // ── 15. Reading, Writing & Language ──────────────────────────
            CVLSection("Reading, Writing & Language")
            CVLRow("číst", "Imperf.", "to read", "→ přečíst (Perf.)", "čtu", "četl/a jsem")
            CVLRow("přečíst", "Perf.", "to read (complete)", "→ číst (Imperf.)", "přečtu", "přečetl/a jsem")
            CVLRow("psát", "Imperf.", "to write", "→ napsat (Perf.)", "píšu/píši", "psal/a jsem")
            CVLRow("napsat", "Perf.", "to write (complete)", "→ psát (Imperf.)", "napíšu/napíši", "napsal/a jsem")

            // ── 16. Arts & Entertainment ──────────────────────────────────
            CVLSection("Arts & Entertainment")
            CVLRow("tančit", "Imperf.", "to dance", jaPresent = "tančím", jaPast = "tančil/a jsem")
            CVLRow("zpívat", "Imperf.", "to sing", jaPresent = "zpívám", jaPast = "zpíval/a jsem")
            CVLRow("hrát", "Imperf.", "to play (a game)", jaPresent = "hraji/hraju", jaPast = "hrál/a jsem")
            CVLRow("hrát na", "Imperf.", "to play (an instrument)", jaPresent = "hraji/hraju na", jaPast = "hrál/a jsem na")

            // ── 17. Math & Quantities ─────────────────────────────────────
            CVLSection("Math & Quantities")
            CVLRow("přidávat", "Imperf.", "to add", "→ přidat (Perf.)", "přidávám", "přidával/a jsem")
            CVLRow("přidat", "Perf.", "to add (complete)", "→ přidávat (Imperf.)", "přidám", "přidal/a jsem")
            CVLRow("odečítat", "Imperf.", "to subtract", "→ odečíst (Perf.)", "odečítám", "odečítal/a jsem")
            CVLRow("odečíst", "Perf.", "to subtract (complete)", "→ odečítat (Imperf.)", "odečtu", "odečetl/a jsem")
            CVLRow("násobit", "Imperf.", "to multiply", "→ znásobit (Perf.)", "násobím", "násobil/a jsem")
            CVLRow("znásobit", "Perf.", "to multiply (complete)", "→ násobit (Imperf.)", "znásobím", "znásobil/a jsem")
            CVLRow("dělit", "Imperf.", "to divide", "→ rozdělit (Perf.)", "dělím", "dělil/a jsem")
            CVLRow("rozdělit", "Perf.", "to divide (complete)", "→ dělit (Imperf.)", "rozdělím", "rozdělil/a jsem")
            CVLRow("zvyšovat", "Imperf.", "to raise", "→ zvýšit (Perf.)", "zvyšuji/zvyšuju", "zvyšoval/a jsem")
            CVLRow("zvýšit", "Perf.", "to raise (complete)", "→ zvyšovat (Imperf.)", "zvýším", "zvýšil/a jsem")
            CVLRow("snižovat", "Imperf.", "to lower", "→ snížit (Perf.)", "snižuji/snižuju", "snižoval/a jsem")
            CVLRow("snížit", "Perf.", "to lower (complete)", "→ snižovat (Imperf.)", "snížím", "snížil/a jsem")

            // ── 18. Conflict & Influence ──────────────────────────────────
            CVLSection("Conflict & Influence")
            CVLRow("útočit", "Imperf.", "to attack", "→ zaútočit (Perf.)", "útočím", "útočil/a jsem")
            CVLRow("zaútočit", "Perf.", "to attack (once)", "→ útočit (Imperf.)", "zaútočím", "zaútočil/a jsem")
            CVLRow("bránit", "Imperf.", "to defend", "→ ubránit (Perf.)", "bráním", "bránil/a jsem")
            CVLRow("ubránit", "Perf.", "to defend (successfully)", "→ bránit (Imperf.)", "ubráním", "ubránil/a jsem")
            CVLRow("přesvědčovat", "Imperf.", "to persuade / convince", "→ přesvědčit (Perf.)", "přesvědčuji/přesvědčuju", "přesvědčoval/a jsem")
            CVLRow("přesvědčit", "Perf.", "to persuade (complete)", "→ přesvědčovat (Imperf.)", "přesvědčím", "přesvědčil/a jsem")
            CVLRow("ovlivňovat", "Imperf.", "to influence", "→ ovlivnit (Perf.)", "ovlivňuji/ovlivňuju", "ovlivňoval/a jsem")
            CVLRow("ovlivnit", "Perf.", "to influence (complete)", "→ ovlivňovat (Imperf.)", "ovlivním", "ovlivnil/a jsem")
            CVLRow("podvádět", "Imperf.", "to trick / deceive", "→ oklamat (Perf.)", "podvádím", "podváděl/a jsem")
            CVLRow("oklamat", "Perf.", "to trick (complete)", "→ podvádět (Imperf.)", "oklamám", "oklamal/a jsem")
            CVLRow("hypnotizovat", "Imperf.", "to hypnotize", jaPresent = "hypnotizuji/hypnotizuju", jaPast = "hypnotizoval/a jsem")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CVLSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun CVLRow(
    czech: String,
    aspect: String,
    english: String,
    pair: String = "",
    jaPresent: String = "",
    jaPast: String = ""
) {
    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)) {
                    append(czech)
                }
                withStyle(SpanStyle(fontSize = 12.sp, color = Color.Gray, fontStyle = FontStyle.Italic)) {
                    append("  ($aspect)")
                }
                withStyle(SpanStyle(fontSize = 14.sp, color = Color.DarkGray)) {
                    append("  —  $english")
                }
            }
        )
        if (pair.isNotEmpty()) {
            Text(
                text = pair,
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray,
                modifier = Modifier.padding(start = 4.dp, top = 1.dp)
            )
        }
        if (jaPresent.isNotEmpty()) {
            Text(
                text = "já: $jaPresent  ·  $jaPast",
                fontSize = 12.sp,
                fontStyle = FontStyle.Italic,
                color = Color(0xFF888888),
                modifier = Modifier.padding(start = 4.dp, top = 1.dp)
            )
        }
    }
}
