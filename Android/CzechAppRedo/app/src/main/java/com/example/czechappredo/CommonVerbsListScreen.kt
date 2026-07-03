package com.example.czechappredo

import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.ui.unit.Dp
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
            CVLModalVerbsSection()
            CVLDailyLifeSection()
            CVLMovementTravelSection()
            CVLCommunicationSpeechSection()
            CVLMindKnowledgeSection()
            CVLEmotionsFeelingsSection()
            CVLWorkStudySection()
            CVLObjectsHouseholdSection()
            CVLDigitalTechSection()
            CVLFoodCookingSection()
            CVLRestaurantDiningSection()
            CVLCommerceTransactionsSection()
            CVLSocialRelationshipsSection()
            CVLCreationMaintenanceSection()
            CVLProcessesLifeEventsSection()
            CVLSensesPerceptionSection()
            CVLReadingWritingSection()
            CVLArtsEntertainmentSection()
            CVLMathQuantitiesSection()
            CVLConflictInfluenceSection()

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────
// Data model + shared table composable
// ─────────────────────────────────────────────────────────────────────────

private data class VerbEntry(
    val english: String,
    val imperfective: String = "",
    val impfPresent: String = "",
    val impfPast: String = "",
    val perfective: String = "",
    val pfPresent: String = "",
    val pfPast: String = "",
    val note: String = ""
)

private val CVLHeaders = listOf("English", "Imperfective", "Já Pres.", "Já Past", "Perfective", "Já Pres.", "Já Past")
private val CVLColWidths: List<Dp> = listOf(170.dp, 130.dp, 120.dp, 130.dp, 130.dp, 120.dp, 130.dp)

@Composable
private fun CVLSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun CVLVerbTable(verbs: List<VerbEntry>) {
    val scrollState = rememberScrollState()
    Column {
        Row(modifier = Modifier.horizontalScroll(scrollState)) {
            CVLHeaders.forEachIndexed { i, h ->
                Text(
                    text = h,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = ButtonBlue,
                    modifier = Modifier.width(CVLColWidths[i]).padding(end = 6.dp)
                )
            }
        }
        HorizontalDivider(color = Color.LightGray)
        verbs.forEach { v ->
            Row(
                modifier = Modifier
                    .horizontalScroll(scrollState)
                    .padding(vertical = 5.dp)
            ) {
                val cells = listOf(v.english, v.imperfective, v.impfPresent, v.impfPast, v.perfective, v.pfPresent, v.pfPast)
                cells.forEachIndexed { i, c ->
                    Text(
                        text = c,
                        fontSize = 12.sp,
                        color = if (i == 0) Color.DarkGray else Color.Black,
                        fontWeight = if (i == 1 || i == 4) FontWeight.Medium else FontWeight.Normal,
                        modifier = Modifier.width(CVLColWidths[i]).padding(end = 6.dp)
                    )
                }
            }
            if (v.note.isNotEmpty()) {
                Text(
                    text = v.note,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 2.dp, bottom = 5.dp)
                )
            }
            HorizontalDivider(color = Color(0xFFEEEEEE))
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────
// 0. Modal Verbs
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLModalVerbsSection() {
    CVLSection("Modal Verbs")
    CVLVerbTable(listOf(
        VerbEntry("to have", "mít", "mám", "měl/a jsem"),
        VerbEntry("to be doing (how one is)", "mít se", "mám se", "měl/a jsem se",
            note = "completely different from mít (to have) — se changes the meaning entirely. Mám se dobře. = I'm doing well. Jak se máš? = How are you doing? (mám se is correct, not mám si)"),
        VerbEntry("to be able to / can", "moci / moct", "mohu/můžu", "mohl/a jsem",
            note = "moci = formal/written; moct = colloquial/spoken (same verb, not an aspect pair)"),
        VerbEntry("to know how to / can (skill)", "umět", "umím", "uměl/a jsem"),
        VerbEntry("to have to / must", "muset", "musím", "musel/a jsem"),
        VerbEntry("to need", "potřebovat", "potřebuji/potřebuju", "potřeboval/a jsem"),
        VerbEntry("to require", "vyžadovat", "vyžaduji/vyžaduju", "vyžadoval/a jsem"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 1. Daily Life
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLDailyLifeSection() {
    CVLSection("Daily Life")
    CVLVerbTable(listOf(
        VerbEntry("to do", "dělat", "dělám", "dělal/a jsem", "udělat", "udělám", "udělal/a jsem"),
        VerbEntry("to eat", "jíst", "jím", "jedl/a jsem", "sníst", "sním", "snědl/a jsem"),
        VerbEntry("to sleep", "spát", "spím", "spal/a jsem"),
        VerbEntry("to prepare (e.g. dinner)", "připravovat", "připravuji/připravuju", "připravoval/a jsem", "připravit", "připravím", "připravil/a jsem"),
        VerbEntry("to cook", "vařit", "vařím", "vařil/a jsem", "uvařit", "uvařím", "uvařil/a jsem"),
        VerbEntry("to clean", "uklízet", "uklízím", "uklízel/a jsem", "uklidit", "uklidím", "uklidil/a jsem"),
        VerbEntry("to wash (e.g. dishes, a car)", "mýt", "myji/myju", "myl/a jsem", "umýt", "umyji/umyju", "umyl/a jsem"),
        VerbEntry("to wash oneself", "mýt se", "myji/myju se", "myl/a jsem se", "umýt se", "umyji/umyju se", "umyl/a jsem se",
            note = "→ se = reflexive accusative — the person washing IS the direct object (oneself)"),
        VerbEntry("to wash one's (own) hands", "mýt si ruce", "myji/myju si ruce", "myl/a jsem si ruce", "umýt si ruce", "umyji/umyju si ruce", "umyl/a jsem si ruce",
            note = "si = reflexive dative ('for oneself'); ruce = acc. of ruce (hands). Compare: mýt se (wash self, acc. se) vs mýt si ruce (wash hands for oneself, dative si + acc. ruce). Same pattern generalizes to other body parts, e.g. mýt si obličej = to wash one's face"),
        VerbEntry("to wear / carry", "nosit", "nosím", "nosil/a jsem"),
        VerbEntry("to rest", "odpočívat", "odpočívám", "odpočíval/a jsem", "odpočinout si", "odpočinu si", "odpočinul/a jsem si"),
        VerbEntry("to relax", "relaxovat", "relaxuji/relaxuju", "relaxoval/a jsem",
            note = "biaspectual — the same form serves as both imperfective and perfective. Distinct from odpočívat/odpočinout si (to rest), which implies recovering from tiredness"),
        VerbEntry("to smoke", "kouřit", "kouřím", "kouřil/a jsem"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 2. Movement & Travel
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLMovementTravelSection() {
    CVLSection("Movement & Travel")
    CVLVerbTable(listOf(
        VerbEntry("to walk / go (one direction)", "jít", "jdu", "šel/šla jsem",
            note = "no single perfective — direction-specific results are expressed via prefixed forms like přijít (arrive on foot), odejít (leave on foot), depending on direction"),
        VerbEntry("to walk / go (regular / multi-directional)", "chodit", "chodím", "chodil/a jsem"),
        VerbEntry("to hike", "turistovat", "turistuji/turistuju", "turistoval/a jsem"),
        VerbEntry("to run (one direction)", "běžet", "běžím", "běžel/a jsem", "doběhnout", "doběhnu", "doběhl/a jsem"),
        VerbEntry("to run (regularly)", "běhat", "běhám", "běhal/a jsem"),
        VerbEntry("to race / run fast", "pádit", "pádím", "pádil/a jsem",
            note = "colloquial/expressive register; no natural perfective counterpart"),
        VerbEntry("to wake up", "budit se", "budím se", "budil/a jsem se", "vzbudit se", "vzbudím se", "vzbudil/a jsem se"),
        VerbEntry("to drive", "řídit", "řídím", "řídil/a jsem"),
        VerbEntry("to navigate", "navigovat", "naviguji/naviguju", "navigoval/a jsem"),
        VerbEntry("to swim", "plavat", "plavu", "plaval/a jsem", "přeplavat", "přeplavu", "přeplaval/a jsem",
            note = "přeplavat = to swim across"),
        VerbEntry("to dive", "potápět se", "potápím se", "potápěl/a jsem se", "potopit se", "potopím se", "potopil/a jsem se"),
        VerbEntry("to fly (one direction)", "letět", "letím", "letěl/a jsem", "přiletět", "přiletím", "přiletěl/a jsem"),
        VerbEntry("to fly (regularly)", "létat", "létám", "létal/a jsem"),
        VerbEntry("to sail", "plout", "pluji/pluju", "plul/a jsem", "přeplout", "přepluji/přepluju", "přeplul/a jsem",
            note = "přeplout = to sail across"),
        VerbEntry("to move (be in motion)", "pohybovat se", "pohybuji/pohybuju se", "pohyboval/a jsem se", "přesunout se", "přesunu se", "přesunul/a jsem se",
            note = "general/reflexive motion — distinct from stěhovat se (move house) and posouvat (move an object) below"),
        VerbEntry("to move (to a different apartment/house)", "stěhovat se", "stěhuji/stěhuju se", "stěhoval/a jsem se", "přestěhovat se", "přestěhuji/přestěhuju se", "přestěhoval/a jsem se"),
        VerbEntry("to move (something)", "posouvat", "posouvám", "posouval/a jsem", "posunout", "posunu", "posunul/a jsem",
            note = "for deliberately repositioning an object. A near-synonym pair, hýbat/pohnout, leans more toward small/incremental motion ('budging') than full repositioning"),
        VerbEntry("to travel", "cestovat", "cestuji/cestuju", "cestoval/a jsem"),
        VerbEntry("to cross (e.g. the street)", "přecházet", "přecházím", "přecházel/a jsem", "přejít", "přejdu", "přešel/přešla jsem"),
        VerbEntry("to come (on foot)", "přicházet", "přicházím", "přicházel/a jsem", "přijít", "přijdu", "přišel/přišla jsem"),
        VerbEntry("to leave", "odcházet", "odcházím", "odcházel/a jsem", "odejít", "odejdu", "odešel/odešla jsem"),
        VerbEntry("to arrive (by vehicle)", "přijíždět", "přijíždím", "přijížděl/a jsem", "přijet", "přijedu", "přijel/a jsem"),
        VerbEntry("to hurry", "spěchat", "spěchám", "spěchal/a jsem"),
        VerbEntry("to pass by / go through", "procházet", "procházím", "procházel/a jsem", "projít", "projdu", "prošel/prošla jsem"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 3. Communication & Speech
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLCommunicationSpeechSection() {
    CVLSection("Communication & Speech")
    CVLVerbTable(listOf(
        VerbEntry("to speak", "mluvit", "mluvím", "mluvil/a jsem"),
        VerbEntry("to talk / chat", "povídat", "povídám", "povídal/a jsem", "povědět", "povím", "pověděl/a jsem"),
        VerbEntry("to whisper", "šeptat", "šeptám", "šeptal/a jsem", "zašeptat", "zašeptám", "zašeptal/a jsem"),
        VerbEntry("to shout", "křičet", "křičím", "křičel/a jsem", "zakřičet", "zakřičím", "zakřičel/a jsem"),
        VerbEntry("to yell / roar", "řvát", "řvu", "řval/a jsem", "zařvat", "zařvu", "zařval/a jsem"),
        VerbEntry("to call", "volat", "volám", "volal/a jsem", "zavolat", "zavolám", "zavolal/a jsem"),
        VerbEntry("to answer / respond", "odpovídat", "odpovídám", "odpovídal/a jsem", "odpovědět", "odpovím", "odpověděl/a jsem"),
        VerbEntry("to react", "reagovat", "reaguji/reaguju", "reagoval/a jsem", "zareagovat", "zareaguji/zareaguju", "zareagoval/a jsem"),
        VerbEntry("to ask / question", "ptát se", "ptám se", "ptal/a jsem se", "zeptat se", "zeptám se", "zeptal/a jsem se"),
        VerbEntry("to say / tell", "říkat", "říkám", "říkal/a jsem", "říct", "řeknu", "řekl/a jsem"),
        VerbEntry("to discuss", "probírat", "probírám", "probíral/a jsem", "probrat", "proberu", "probral/a jsem",
            note = "diskutovat is also common but is imperfective-only in practice — a more formal/academic register"),
        VerbEntry("to explain", "vysvětlovat", "vysvětluji/vysvětluju", "vysvětloval/a jsem", "vysvětlit", "vysvětlím", "vysvětlil/a jsem"),
        VerbEntry("to argue", "hádat se", "hádám se", "hádal/a jsem se", "pohádat se", "pohádám se", "pohádal/a jsem se"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 4. Mind & Knowledge
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLMindKnowledgeSection() {
    CVLSection("Mind & Knowledge")
    CVLVerbTable(listOf(
        VerbEntry("to know (a fact)", "vědět", "vím", "věděl/a jsem"),
        VerbEntry("to know (a person / place)", "znát", "znám", "znal/a jsem"),
        VerbEntry("to want", "chtít", "chci", "chtěl/a jsem"),
        VerbEntry("to find out", "zjišťovat", "zjišťuji/zjišťuju", "zjišťoval/a jsem", "zjistit", "zjistím", "zjistil/a jsem"),
        VerbEntry("to think", "myslet", "myslím", "myslel/a jsem"),
        VerbEntry("to believe", "věřit", "věřím", "věřil/a jsem", "uvěřit", "uvěřím", "uvěřil/a jsem"),
        VerbEntry("to doubt", "pochybovat", "pochybuji/pochybuju", "pochyboval/a jsem", "zapochybovat", "zapochybuji/zapochybuju", "zapochyboval/a jsem",
            note = "zapochybovat is ingressive — 'to have a moment of doubt / start doubting' — not a completion of the doubting"),
        VerbEntry("to expect", "očekávat", "očekávám", "očekával/a jsem",
            note = "no perfective counterpart (imperfectivum tantum)"),
        VerbEntry("to hope", "doufat", "doufám", "doufal/a jsem",
            note = "effectively imperfective-only"),
        VerbEntry("to dream", "snít", "sním", "snil/a jsem",
            note = "for dreaming during sleep, the idiomatic construction is the impersonal zdát se: Zdálo se mi, že... = I dreamed that... A perfective vysnít si exists but means 'to dream up/fantasize an idealized outcome,' not completing a night's dream"),
        VerbEntry("to decide", "rozhodovat se", "rozhoduji/rozhoduju se", "rozhodoval/a jsem se", "rozhodnout se", "rozhodnu se", "rozhodl/a jsem se"),
        VerbEntry("to imagine", "představovat si", "představuji/představuju si", "představoval/a jsem si", "představit si", "představím si", "představil/a jsem si"),
        VerbEntry("to remember", "pamatovat si", "pamatuji/pamatuju si", "pamatoval/a jsem si", "zapamatovat si", "zapamatuji/zapamatuju si", "zapamatoval/a jsem si"),
        VerbEntry("to forget", "zapomínat", "zapomínám", "zapomínal/a jsem", "zapomenout", "zapomenu", "zapomněl/a jsem"),
        VerbEntry("to practice", "procvičovat", "procvičuji/procvičuju", "procvičoval/a jsem", "procvičit", "procvičím", "procvičil/a jsem"),
        VerbEntry("to understand", "rozumět", "rozumím", "rozuměl/a jsem", "pochopit", "pochopím", "pochopil/a jsem"),
        VerbEntry("to estimate", "odhadovat", "odhaduji/odhaduju", "odhadoval/a jsem", "odhadnout", "odhadnu", "odhadnul/a jsem"),
        VerbEntry("to determine", "určovat", "určuji/určuju", "určoval/a jsem", "určit", "určím", "určil/a jsem"),
        VerbEntry("to check", "kontrolovat", "kontroluji/kontroluju", "kontroloval/a jsem", "zkontrolovat", "zkontroluji/zkontroluju", "zkontroloval/a jsem"),
        VerbEntry("to verify", "ověřovat", "ověřuji/ověřuju", "ověřoval/a jsem", "ověřit", "ověřím", "ověřil/a jsem"),
        VerbEntry("to realize", "uvědomovat si", "uvědomuji/uvědomuju si", "uvědomoval/a jsem si", "uvědomit si", "uvědomím si", "uvědomil/a jsem si"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 5. Emotions & Feelings
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLEmotionsFeelingsSection() {
    CVLSection("Emotions & Feelings")
    CVLVerbTable(listOf(
        VerbEntry("to love", "milovat", "miluji/miluju", "miloval/a jsem"),
        VerbEntry("to like / be fond of", "mít rád / ráda", "mám rád/ráda", "měl/a jsem rád/ráda"),
        VerbEntry("to hate", "nenávidět", "nenávidím", "nenáviděl/a jsem"),
        VerbEntry("to fear / to be afraid", "bát se", "bojím se", "bál/a jsem se",
            note = "inherently reflexive — no perfective pair for ongoing fear; leknout se (Perf.) = to get startled (sudden fright — related but different)"),
        VerbEntry("to smile", "usmívat se", "usmívám se", "usmíval/a jsem se", "usmát se", "usměji se", "usmál/a jsem se"),
        VerbEntry("to laugh", "smát se", "směji/směju se", "smál/a jsem se", "zasmát se", "zasměji/zasměju se", "zasmál/a jsem se"),
        VerbEntry("to cry", "plakat", "pláču", "plakal/a jsem", "zaplakat", "zapláču", "zaplakal/a jsem",
            note = "zaplakat = to have a cry (delimitative, the direct aspectual partner); rozplakat se = to burst into tears (inceptive, emphasizes the onset)"),
        VerbEntry("to brag", "chlubit se", "chlubím se", "chlubil/a jsem se", "pochlubit se", "pochlubím se", "pochlubil/a jsem se"),
        VerbEntry("to annoy / bother", "obtěžovat", "obtěžuji/obtěžuju", "obtěžoval/a jsem"),
        VerbEntry("to compliment / praise", "chválit", "chválím", "chválil/a jsem", "pochválit", "pochválím", "pochválil/a jsem"),
        VerbEntry("to care (about)", "starat se", "starám se", "staral/a jsem se"),
        VerbEntry("to appreciate", "oceňovat", "oceňuji/oceňuju", "oceňoval/a jsem", "ocenit", "ocením", "ocenil/a jsem"),
        VerbEntry("to nurture / care for", "pečovat", "pečuji/pečuju", "pečoval/a jsem"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 6. Work & Study
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLWorkStudySection() {
    CVLSection("Work & Study")
    CVLVerbTable(listOf(
        VerbEntry("to study", "studovat", "studuji/studuju", "studoval/a jsem"),
        VerbEntry("to work", "pracovat", "pracuji/pracuju", "pracoval/a jsem"),
        VerbEntry("to train (sports / practice)", "trénovat", "trénuji/trénuju", "trénoval/a jsem", "vytrénovat", "vytrénuji/vytrénuju", "vytrénoval/a jsem"),
        VerbEntry("to learn", "učit se", "učím se", "učil/a jsem se", "naučit se", "naučím se", "naučil/a jsem se"),
        VerbEntry("to teach", "učit", "učím", "učil/a jsem", "naučit", "naučím", "naučil/a jsem",
            note = "non-reflexive — contrasts with reflexive učit se (to learn)"),
        VerbEntry("to lecture", "přednášet", "přednáším", "přednášel/a jsem", "přednést", "přednesu", "přednesl/a jsem"),
        VerbEntry("to treat (medically)", "léčit", "léčím", "léčil/a jsem", "vyléčit", "vyléčím", "vyléčil/a jsem"),
        VerbEntry("to investigate", "vyšetřovat", "vyšetřuji/vyšetřuju", "vyšetřoval/a jsem", "vyšetřit", "vyšetřím", "vyšetřil/a jsem"),
        VerbEntry("to handle / to manage", "zvládat", "zvládám", "zvládal/a jsem", "zvládnout", "zvládnu", "zvládnul/a jsem"),
        VerbEntry("to manage / administer", "spravovat", "spravuji/spravuju", "spravoval/a jsem"),
        VerbEntry("to establish / found", "zakládat", "zakládám", "zakládal/a jsem", "založit", "založím", "založil/a jsem"),
        VerbEntry("to promote (career)", "povyšovat", "povyšuji/povyšuju", "povyšoval/a jsem", "povýšit", "povýším", "povýšil/a jsem"),
        VerbEntry("to demote", "degradovat", "degraduji/degraduju", "degradoval/a jsem"),
        VerbEntry("to achieve", "dosahovat", "dosahuji/dosahuju", "dosahoval/a jsem", "dosáhnout", "dosáhnu", "dosáhnul/a jsem"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 7. Objects & Household Actions
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLObjectsHouseholdSection() {
    CVLSection("Objects & Household Actions")
    CVLVerbTable(listOf(
        VerbEntry("to turn on", "zapínat", "zapínám", "zapínal/a jsem", "zapnout", "zapnu", "zapnul/a jsem"),
        VerbEntry("to turn off", "vypínat", "vypínám", "vypínal/a jsem", "vypnout", "vypnu", "vypnul/a jsem"),
        VerbEntry("to lock", "zamykat", "zamykám", "zamykal/a jsem", "zamknout", "zamknu", "zamknul/a jsem"),
        VerbEntry("to open", "otevírat", "otevírám", "otevíral/a jsem", "otevřít", "otevřu", "otevřel/a jsem"),
        VerbEntry("to close", "zavírat", "zavírám", "zavíral/a jsem", "zavřít", "zavřu", "zavřel/a jsem"),
        VerbEntry("to take", "brát", "beru", "bral/a jsem", "vzít", "vezmu", "vzal/a jsem"),
        VerbEntry("to bring", "přinášet", "přináším", "přinášel/a jsem", "přinést", "přinesu", "přinesl/a jsem"),
        VerbEntry("to put / give", "dávat", "dávám", "dával/a jsem", "dát", "dám", "dal/a jsem"),
        VerbEntry("to send", "posílat", "posílám", "posílal/a jsem", "poslat", "pošlu", "poslal/a jsem"),
        VerbEntry("to receive", "dostávat", "dostávám", "dostával/a jsem", "dostat", "dostanu", "dostal/a jsem"),
        VerbEntry("to ignite / light (fire)", "zapalovat", "zapaluji/zapaluju", "zapaloval/a jsem", "zapálit", "zapálím", "zapálil/a jsem"),
        VerbEntry("to light up / switch on", "rozsvěcovat", "rozsvěcuji/rozsvěcuju", "rozsvěcoval/a jsem", "rozsvítit", "rozsvítím", "rozsvítil/a jsem"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 8. Digital & Tech Actions
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLDigitalTechSection() {
    CVLSection("Digital & Tech Actions")
    CVLVerbTable(listOf(
        VerbEntry("to press", "mačkat", "mačkám", "mačkal/a jsem", "zmáčknout", "zmáčknu", "zmáčknul/a jsem"),
        VerbEntry("to click", "klikat", "klikám", "klikal/a jsem", "kliknout", "kliknu", "kliknul/a jsem"),
        VerbEntry("to follow / watch", "sledovat", "sleduji/sleduju", "sledoval/a jsem"),
        VerbEntry("to design", "navrhovat", "navrhuji/navrhuju", "navrhoval/a jsem", "navrhnout", "navrhnu", "navrhnul/a jsem"),
        VerbEntry("to print", "tisknout", "tisknu", "tisknul/a jsem", "vytisknout", "vytisknu", "vytisknul/a jsem"),
        VerbEntry("to take a picture", "fotit", "fotím", "fotil/a jsem", "vyfotit", "vyfotím", "vyfotil/a jsem",
            note = "colloquial; more formal alternative: fotografovat / vyfotografovat"),
        VerbEntry("to use", "používat", "používám", "používal/a jsem", "použít", "použiji/použiju", "použil/a jsem"),
        VerbEntry("to debug", "ladit", "ladím", "ladil/a jsem", "odladit", "odladím", "odladil/a jsem"),
        VerbEntry("to compile", "kompilovat", "kompiluji/kompiluju", "kompiloval/a jsem"),
        VerbEntry("to link (build process)", "linkovat", "linkuji/linkuju", "linkoval/a jsem"),
        VerbEntry("to code / program", "programovat", "programuji/programuju", "programoval/a jsem"),
        VerbEntry("to process", "zpracovávat", "zpracovávám", "zpracovával/a jsem", "zpracovat", "zpracuji/zpracuju", "zpracoval/a jsem"),
        VerbEntry("to block", "blokovat", "blokuji/blokuju", "blokoval/a jsem"),
        VerbEntry("to forbid / to deny (permission)", "zakazovat", "zakazuji/zakazuju", "zakazoval/a jsem", "zakázat", "zakáži", "zakázal/a jsem"),
        VerbEntry("to allow", "povolovat", "povoluji/povoluju", "povoloval/a jsem", "povolit", "povolím", "povolil/a jsem"),
        VerbEntry("to load (data)", "načítat", "načítám", "načítal/a jsem", "načíst", "načtu", "načetl/a jsem"),
        VerbEntry("to save (data)", "ukládat", "ukládám", "ukládal/a jsem", "uložit", "uložím", "uložil/a jsem"),
        VerbEntry("to connect", "připojovat", "připojuji/připojuju", "připojoval/a jsem", "připojit", "připojím", "připojil/a jsem"),
        VerbEntry("to disconnect", "odpojovat", "odpojuji/odpojuju", "odpojoval/a jsem", "odpojit", "odpojím", "odpojil/a jsem"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 9. Food & Cooking
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLFoodCookingSection() {
    CVLSection("Food & Cooking")
    CVLVerbTable(listOf(
        VerbEntry("to chop", "sekat", "sekám", "sekal/a jsem", "nasekat", "nasekám", "nasekal/a jsem"),
        VerbEntry("to fry", "smažit", "smažím", "smažil/a jsem", "usmažit", "usmažím", "usmažil/a jsem"),
        VerbEntry("to bake", "péct", "peču/peku", "pekl/a jsem", "upéct", "upeču/upeku", "upekl/a jsem"),
        VerbEntry("to mix / stir", "míchat", "míchám", "míchal/a jsem", "zamíchat", "zamíchám", "zamíchal/a jsem"),
        VerbEntry("to cut / slice (food)", "krájet", "krájím", "krájel/a jsem", "nakrájet", "nakrájím", "nakrájel/a jsem"),
        VerbEntry("to heat", "zahřívat", "zahřívám", "zahříval/a jsem", "zahřát", "zahřeji/zahřeju", "zahřál/a jsem"),
        VerbEntry("to cool", "chladit", "chladím", "chladil/a jsem", "ochladit", "ochladím", "ochladil/a jsem"),
        VerbEntry("to taste", "ochutnávat", "ochutnávám", "ochutnával/a jsem", "ochutnat", "ochutnám", "ochutnal/a jsem"),
        VerbEntry("to try", "zkoušet", "zkouším", "zkoušel/a jsem", "zkusit", "zkusím", "zkusil/a jsem"),
        VerbEntry("to prefer", "preferovat", "preferuji/preferuju", "preferoval/a jsem"),
        VerbEntry("to recommend", "doporučovat", "doporučuji/doporučuju", "doporučoval/a jsem", "doporučit", "doporučím", "doporučil/a jsem"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 10. Restaurant & Dining (new)
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLRestaurantDiningSection() {
    CVLSection("Restaurant & Dining")
    CVLVerbTable(listOf(
        VerbEntry("to wait", "čekat", "čekám", "čekal/a jsem", "počkat", "počkám", "počkal/a jsem"),
        VerbEntry("to drink", "pít", "piji/piju", "pil/a jsem", "vypít", "vypiji/vypiju", "vypil/a jsem"),
        VerbEntry("to have breakfast", "snídat", "snídám", "snídal/a jsem", "nasnídat se", "nasnídám se", "nasnídal/a jsem se"),
        VerbEntry("to have lunch", "obědvat", "obědvám", "obědval/a jsem", "naobědvat se", "naobědvám se", "naobědval/a jsem se"),
        VerbEntry("to have dinner", "večeřet", "večeřím", "večeřel/a jsem", "navečeřet se", "navečeřím se", "navečeřel/a jsem se"),
        VerbEntry("to have / order (food or drink)", perfective = "dát si", pfPresent = "dám si", pfPast = "dal/a jsem si",
            note = "e.g. \"Dám si kávu\" = I'll have a coffee. Perfective, reflexive-dative — correct as dám si, not dám se. Dávat si exists but implies a habitual/repeated action, not one-off ordering. For ordering in general (not just food), see objednat below"),
        VerbEntry("to pay", "platit", "platím", "platil/a jsem", "zaplatit", "zaplatím", "zaplatil/a jsem"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 11. Commerce & Transactions
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLCommerceTransactionsSection() {
    CVLSection("Commerce & Transactions")
    CVLVerbTable(listOf(
        VerbEntry("to shop", "nakupovat", "nakupuji/nakupuju", "nakupoval/a jsem"),
        VerbEntry("to buy", "kupovat", "kupuji/kupuju", "kupoval/a jsem", "koupit", "koupím", "koupil/a jsem"),
        VerbEntry("to sell", "prodávat", "prodávám", "prodával/a jsem", "prodat", "prodám", "prodal/a jsem"),
        VerbEntry("to order", "objednávat", "objednávám", "objednával/a jsem", "objednat", "objednám", "objednal/a jsem"),
        VerbEntry("to request / ask for (something)", "žádat", "žádám", "žádal/a jsem", "požádat", "požádám", "požádal/a jsem"),
        VerbEntry("to owe (someone)", "dlužit", "dlužím", "dlužil/a jsem",
            note = "no natural perfective"),
        VerbEntry("to return (something)", "vracet", "vracím", "vracel/a jsem", "vrátit", "vrátím", "vrátil/a jsem"),
        VerbEntry("to borrow", "půjčovat si", "půjčuji/půjčuju si", "půjčoval/a jsem si", "půjčit si", "půjčím si", "půjčil/a jsem si"),
        VerbEntry("to rent", "pronajímat si", "pronajímám si", "pronajímal/a jsem si", "pronajmout si", "pronajmu si", "pronajal/pronajala jsem si"),
        VerbEntry("to loan / to lend", "půjčovat", "půjčuji/půjčuju", "půjčoval/a jsem", "půjčit", "půjčím", "půjčil/a jsem"),
        VerbEntry("to invest", "investovat", "investuji/investuju", "investoval/a jsem"),
        VerbEntry("to save (money)", "šetřit", "šetřím", "šetřil/a jsem"),
        VerbEntry("to save / rescue", "zachraňovat", "zachraňuji/zachraňuju", "zachraňoval/a jsem", "zachránit", "zachráním", "zachránil/a jsem"),
        VerbEntry("to win", "vyhrávat", "vyhrávám", "vyhrával/a jsem", "vyhrát", "vyhraji/vyhráju", "vyhrál/a jsem"),
        VerbEntry("to lose (a game/competition)", "prohrávat", "prohrávám", "prohrával/a jsem", "prohrát", "prohraji/prohraju", "prohrál/a jsem"),
        VerbEntry("to lose (an object)", "ztrácet", "ztrácím", "ztrácel/a jsem", "ztratit", "ztratím", "ztratil/a jsem"),
        VerbEntry("to succeed", perfective = "uspět", pfPresent = "uspěji/uspěju", pfPast = "uspěl/a jsem",
            note = "no direct imperfective partner; dařit se is a related but distinct impersonal construction — 'daří se mi' = 'it's going well for me'"),
        VerbEntry("to fail", "selhávat", "selhávám", "selhával/a jsem", "selhat", "selžu", "selhal/a jsem",
            note = "irregular present/future stem"),
        VerbEntry("to race (against someone)", "závodit", "závodím", "závodil/a jsem",
            note = "no perfective counterpart for this meaning"),
        VerbEntry("to compete", "soutěžit", "soutěžím", "soutěžil/a jsem", "zasoutěžit", "zasoutěžím", "zasoutěžil/a jsem",
            note = "broader than závodit (any contest, e.g. quiz/skill-based), whereas závodit specifically implies speed-based racing"),
        VerbEntry("to bet / gamble", "sázet", "sázím", "sázel/a jsem", "vsadit", "vsadím", "vsadil/a jsem"),
        VerbEntry("to market / promote", "propagovat", "propaguji/propaguju", "propagoval/a jsem"),
        VerbEntry("to distribute", "distribuovat", "distribuuji/distribuuju", "distribuoval/a jsem"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 12. Social & Relationships
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLSocialRelationshipsSection() {
    CVLSection("Social & Relationships")
    CVLVerbTable(listOf(
        VerbEntry("to help", "pomáhat", "pomáhám", "pomáhal/a jsem", "pomoct", "pomohu/pomůžu", "pomohl/a jsem"),
        VerbEntry("to meet (by arrangement)", "setkávat se", "setkávám se", "setkával/a jsem se", "setkat se", "setkám se", "setkal/a jsem se"),
        VerbEntry("to meet (by chance / encounter)", "potkávat", "potkávám", "potkával/a jsem", "potkat", "potkám", "potkal/a jsem"),
        VerbEntry("to arrange", "domlouvat", "domlouvám", "domlouval/a jsem", "domluvit", "domluvím", "domluvil/a jsem"),
        VerbEntry("to choose / select", "vybírat", "vybírám", "vybíral/a jsem", "vybrat", "vyberu", "vybral/a jsem"),
        VerbEntry("to invite", "zvát", "zvu", "zval/a jsem", "pozvat", "pozvu", "pozval/a jsem"),
        VerbEntry("to visit", "navštěvovat", "navštěvuji/navštěvuju", "navštěvoval/a jsem", "navštívit", "navštívím", "navštívil/a jsem"),
        VerbEntry("to be called / named", "jmenovat se", "jmenuji/jmenuju se", "jmenoval/a jsem se"),
        VerbEntry("to approach", "přibližovat se", "přibližuji/přibližuju se", "přibližoval/a jsem se", "přiblížit se", "přiblížím se", "přiblížil/a jsem se"),
        VerbEntry("to flirt", "flirtovat", "flirtuji/flirtuju", "flirtoval/a jsem"),
        VerbEntry("to court / woo", "dvořit se", "dvořím se", "dvořil/a jsem se"),
        VerbEntry("to nag / pester", "otravovat", "otravuji/otravuju", "otravoval/a jsem"),
        VerbEntry("to pray", "modlit se", "modlím se", "modlil/a jsem se"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 13. Creation & Maintenance
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLCreationMaintenanceSection() {
    CVLSection("Creation & Maintenance")
    CVLVerbTable(listOf(
        VerbEntry("to create", "vytvářet", "vytvářím", "vytvářel/a jsem", "vytvořit", "vytvořím", "vytvořil/a jsem"),
        VerbEntry("to destroy", "ničit", "ničím", "ničil/a jsem", "zničit", "zničím", "zničil/a jsem"),
        VerbEntry("to build", "stavět", "stavím", "stavěl/a jsem", "postavit", "postavím", "postavil/a jsem"),
        VerbEntry("to paint", "malovat", "maluji/maluju", "maloval/a jsem", "namalovat", "namaluji/namaluju", "namaloval/a jsem"),
        VerbEntry("to fix / repair", "opravovat", "opravuji/opravuju", "opravoval/a jsem", "opravit", "opravím", "opravil/a jsem"),
        VerbEntry("to mend (sew, e.g. clothing)", "zašívat", "zašívám", "zašíval/a jsem", "zašít", "zašiji/zašiju", "zašil/a jsem"),
        VerbEntry("to break (an object)", "rozbíjet", "rozbíjím", "rozbíjel/a jsem", "rozbít", "rozbiji/rozbiju", "rozbil/a jsem"),
        VerbEntry("to cut (with scissors, e.g. hair/paper)", "stříhat", "stříhám", "stříhal/a jsem", "ostříhat", "ostříhám", "ostříhal/a jsem",
            note = "textbook aspect pair is stříhat/střihnout (semelfactive, a single snip) — ostříhat better fits a completed general cut"),
        VerbEntry("to change", "měnit", "měním", "měnil/a jsem", "změnit", "změním", "změnil/a jsem"),
        VerbEntry("to replace / exchange", "vyměňovat", "vyměňuji/vyměňuju", "vyměňoval/a jsem", "vyměnit", "vyměním", "vyměnil/a jsem"),
        VerbEntry("to organize", "organizovat", "organizuji/organizuju", "organizoval/a jsem", "zorganizovat", "zorganizuji/zorganizuju", "zorganizoval/a jsem"),
        VerbEntry("to separate", "oddělovat", "odděluji/odděluju", "odděloval/a jsem", "oddělit", "oddělím", "oddělil/a jsem"),
        VerbEntry("to invent", "vynalézat", "vynalézám", "vynalézal/a jsem", "vynalést", "vynalezu", "vynalezl/a jsem"),
        VerbEntry("to adjust", "upravovat", "upravuji/upravuju", "upravoval/a jsem", "upravit", "upravím", "upravil/a jsem"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 14. Processes & Life Events
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLProcessesLifeEventsSection() {
    CVLSection("Processes & Life Events")
    CVLVerbTable(listOf(
        VerbEntry("to be", "být", "jsem", "byl/a jsem"),
        VerbEntry("to live", "žít", "žiji/žiju", "žil/a jsem"),
        VerbEntry("to die", "umírat", "umírám", "umíral/a jsem", "zemřít", "zemřu", "zemřel/a jsem"),
        VerbEntry("to be born", "rodit se", "rodím se", "rodil/a jsem se", "narodit se", "narodím se", "narodil/a jsem se"),
        VerbEntry("to adopt", "adoptovat", "adoptuji/adoptuju", "adoptoval/a jsem",
            note = "biaspectual — the identical form serves as both imperfective and perfective"),
        VerbEntry("to stop", "zastavovat", "zastavuji/zastavuju", "zastavoval/a jsem", "zastavit", "zastavím", "zastavil/a jsem"),
        VerbEntry("to begin", "začínat", "začínám", "začínal/a jsem", "začít", "začnu", "začal/a jsem"),
        VerbEntry("to finish", "končit", "končím", "končil/a jsem", "skončit", "skončím", "skončil/a jsem"),
        VerbEntry("to complete", "dokončovat", "dokončuji/dokončuju", "dokončoval/a jsem", "dokončit", "dokončím", "dokončil/a jsem"),
        VerbEntry("to avoid", "vyhýbat se", "vyhýbám se", "vyhýbal/a jsem se", "vyhnout se", "vyhnu se", "vyhnul/a jsem se"),
        VerbEntry("to resume / renew", "obnovovat", "obnovuji/obnovuju", "obnovoval/a jsem", "obnovit", "obnovím", "obnovil/a jsem"),
        VerbEntry("to pause", "pozastavovat", "pozastavuji/pozastavuju", "pozastavoval/a jsem", "pozastavit", "pozastavím", "pozastavil/a jsem"),
        VerbEntry("to point / show", "ukazovat", "ukazuji/ukazuju", "ukazoval/a jsem", "ukázat", "ukáži", "ukázal/a jsem"),
        VerbEntry("to match / fit", "pasovat", "pasuji/pasuju", "pasoval/a jsem"),
        VerbEntry("to count", "počítat", "počítám", "počítal/a jsem", "spočítat", "spočítám", "spočítal/a jsem"),
        VerbEntry("to appear", "objevovat se", "objevuji/objevuju se", "objevoval/a jsem se", "objevit se", "objevím se", "objevil/a jsem se"),
        VerbEntry("to breathe", "dýchat", "dýchám", "dýchal/a jsem"),
        VerbEntry("to quit / stop doing", "přestávat", "přestávám", "přestával/a jsem", "přestat", "přestanu", "přestal/a jsem"),
        VerbEntry("to withdraw", "stahovat se", "stahuji/stahuju se", "stahoval/a jsem se", "stáhnout se", "stáhnu se", "stáhnul/a jsem se"),
        VerbEntry("to retire", "odcházet do důchodu", "odcházím do důchodu", "odcházel/a jsem do důchodu"),
        VerbEntry("to drown", "topit se", "topím se", "topil/a jsem se", "utopit se", "utopím se", "utopil/a jsem se"),
        VerbEntry("to grow (intransitive)", "růst", "rostu", "rostl/a jsem", "vyrůst", "vyrostu", "vyrostl/a jsem"),
        VerbEntry("to grow (plants / raise)", "pěstovat", "pěstuji/pěstuju", "pěstoval/a jsem", "vypěstovat", "vypěstuji/vypěstuju", "vypěstoval/a jsem"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 15. Senses & Perception
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLSensesPerceptionSection() {
    CVLSection("Senses & Perception")
    CVLVerbTable(listOf(
        VerbEntry("to listen", "poslouchat", "poslouchám", "poslouchal/a jsem", "vyslechnout", "vyslechnu", "vyslechl/a jsem"),
        VerbEntry("to hear", "slyšet", "slyším", "slyšel/a jsem"),
        VerbEntry("to see", "vidět", "vidím", "viděl/a jsem"),
        VerbEntry("to watch", "dívat se", "dívám se", "díval/a jsem se", "podívat se", "podívám se", "podíval/a jsem se"),
        VerbEntry("to smell", "čichat", "čichám", "čichal/a jsem"),
        VerbEntry("to gaze", "hledět", "hledím", "hleděl/a jsem"),
        VerbEntry("to stare", "zírat", "zírám", "zíral/a jsem"),
        VerbEntry("to look for / to search", "hledat", "hledám", "hledal/a jsem",
            note = "no direct perfective from the same root — the result is expressed by najít (to find)"),
        VerbEntry("to find (ongoing) / to be located", "nacházet", "nacházím", "nacházel/a jsem", "najít", "najdu", "našel/našla jsem"),
        VerbEntry("to search (investigate)", "pátrat", "pátrám", "pátral/a jsem", "vypátrat", "vypátrám", "vypátral/a jsem",
            note = "more intensive than hledat. Policie pátrá po pachateli. = The police are searching for the perpetrator."),
        VerbEntry("to observe", "pozorovat", "pozoruji/pozoruju", "pozoroval/a jsem",
            note = "careful, attentive watching — distinct from sledovat (to follow / track) and dívat se (to watch / look at)"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 16. Reading, Writing & Language
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLReadingWritingSection() {
    CVLSection("Reading, Writing & Language")
    CVLVerbTable(listOf(
        VerbEntry("to read", "číst", "čtu", "četl/a jsem", "přečíst", "přečtu", "přečetl/a jsem"),
        VerbEntry("to write", "psát", "píšu/píši", "psal/a jsem", "napsat", "napíšu/napíši", "napsal/a jsem"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 17. Arts & Entertainment
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLArtsEntertainmentSection() {
    CVLSection("Arts & Entertainment")
    CVLVerbTable(listOf(
        VerbEntry("to dance", "tančit", "tančím", "tančil/a jsem"),
        VerbEntry("to sing", "zpívat", "zpívám", "zpíval/a jsem"),
        VerbEntry("to play (a game)", "hrát", "hraji/hraju", "hrál/a jsem"),
        VerbEntry("to play (an instrument)", "hrát na", "hraji/hraju na", "hrál/a jsem na"),
        VerbEntry("to perform (on stage)", "vystupovat", "vystupuji/vystupuju", "vystupoval/a jsem", "vystoupit", "vystoupím", "vystoupil/a jsem"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 18. Math & Quantities
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLMathQuantitiesSection() {
    CVLSection("Math & Quantities")
    CVLVerbTable(listOf(
        VerbEntry("to add", "přidávat", "přidávám", "přidával/a jsem", "přidat", "přidám", "přidal/a jsem"),
        VerbEntry("to subtract", "odečítat", "odečítám", "odečítal/a jsem", "odečíst", "odečtu", "odečetl/a jsem"),
        VerbEntry("to multiply", "násobit", "násobím", "násobil/a jsem", "znásobit", "znásobím", "znásobil/a jsem"),
        VerbEntry("to divide", "dělit", "dělím", "dělil/a jsem", "rozdělit", "rozdělím", "rozdělil/a jsem"),
        VerbEntry("to raise", "zvyšovat", "zvyšuji/zvyšuju", "zvyšoval/a jsem", "zvýšit", "zvýším", "zvýšil/a jsem"),
        VerbEntry("to lower", "snižovat", "snižuji/snižuju", "snižoval/a jsem", "snížit", "snížím", "snížil/a jsem"),
    ))
}

// ─────────────────────────────────────────────────────────────────────────
// 19. Conflict & Influence
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun CVLConflictInfluenceSection() {
    CVLSection("Conflict & Influence")
    CVLVerbTable(listOf(
        VerbEntry("to attack", "útočit", "útočím", "útočil/a jsem", "zaútočit", "zaútočím", "zaútočil/a jsem"),
        VerbEntry("to defend", "bránit", "bráním", "bránil/a jsem", "ubránit", "ubráním", "ubránil/a jsem"),
        VerbEntry("to persuade / convince", "přesvědčovat", "přesvědčuji/přesvědčuju", "přesvědčoval/a jsem", "přesvědčit", "přesvědčím", "přesvědčil/a jsem"),
        VerbEntry("to influence", "ovlivňovat", "ovlivňuji/ovlivňuju", "ovlivňoval/a jsem", "ovlivnit", "ovlivním", "ovlivnil/a jsem"),
        VerbEntry("to trick / deceive", "podvádět", "podvádím", "podváděl/a jsem", "oklamat", "oklamám", "oklamal/a jsem"),
        VerbEntry("to steal", "krást", "kradu", "kradl/a jsem", "ukrást", "ukradnu", "ukradl/a jsem"),
        VerbEntry("to deny (a claim)", "popírat", "popírám", "popíral/a jsem", "popřít", "popřu", "popřel/a jsem"),
        VerbEntry("to agree", "souhlasit", "souhlasím", "souhlasil/a jsem",
            note = "no natural perfective"),
        VerbEntry("to disagree", "nesouhlasit", "nesouhlasím", "nesouhlasil/a jsem",
            note = "no natural perfective — simply the negation of souhlasit"),
        VerbEntry("to hypnotize", "hypnotizovat", "hypnotizuji/hypnotizuju", "hypnotizoval/a jsem"),
        VerbEntry("to ignore", "ignorovat", "ignoruji/ignoruju", "ignoroval/a jsem"),
        VerbEntry("to accept", "přijímat", "přijímám", "přijímal/a jsem", "přijmout", "přijmu", "přijal/a jsem"),
        VerbEntry("to reject / refuse", "odmítat", "odmítám", "odmítal/a jsem", "odmítnout", "odmítnu", "odmítl/a jsem"),
    ))
}
