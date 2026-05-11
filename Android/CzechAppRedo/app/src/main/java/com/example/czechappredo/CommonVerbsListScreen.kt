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
            CVLRow("mít", "Imperf.", "to have")
            CVLRow("moci / moct", "Imperf.", "to be able to / can", "same verb — moci = formal/written; moct = colloquial/spoken")
            CVLRow("umět", "Imperf.", "to know how to / can (skill)")
            CVLRow("muset", "Imperf.", "to have to / must")
            CVLRow("potřebovat", "Imperf.", "to need")
            CVLRow("vyžadovat", "Imperf.", "to require")

            // ── 1. Daily Life ─────────────────────────────────────────────
            CVLSection("Daily Life")
            CVLRow("dělat", "Imperf.", "to do", "→ udělat (Perf.)")
            CVLRow("udělat", "Perf.", "to do (complete)", "→ dělat (Imperf.)")
            CVLRow("jíst", "Imperf.", "to eat", "→ sníst (Perf.)")
            CVLRow("sníst", "Perf.", "to eat up / finish eating", "→ jíst (Imperf.)")
            CVLRow("spát", "Imperf.", "to sleep")
            CVLRow("připravovat", "Imperf.", "to prepare", "→ připravit (Perf.)")
            CVLRow("připravit", "Perf.", "to prepare (complete)", "→ připravovat (Imperf.)")
            CVLRow("vařit", "Imperf.", "to cook", "→ uvařit (Perf.)")
            CVLRow("uvařit", "Perf.", "to cook (complete)", "→ vařit (Imperf.)")
            CVLRow("uklízet", "Imperf.", "to clean", "→ uklidit (Perf.)")
            CVLRow("uklidit", "Perf.", "to clean up (complete)", "→ uklízet (Imperf.)")
            CVLRow("mýt", "Imperf.", "to wash", "→ umýt (Perf.)")
            CVLRow("umýt", "Perf.", "to wash (complete)", "→ mýt (Imperf.)")
            CVLRow("nosit", "Imperf.", "to wear / carry")
            CVLRow("odpočívat", "Imperf.", "to rest", "→ odpočinout si (Perf.)")
            CVLRow("odpočinout si", "Perf.", "to take a rest", "→ odpočívat (Imperf.)")
            CVLRow("kouřit", "Imperf.", "to smoke")

            // ── 2. Movement & Travel ──────────────────────────────────────
            CVLSection("Movement & Travel")
            CVLRow("jít", "Imperf.", "to walk / go (one direction)", "→ přijít / odejít (Perf.)")
            CVLRow("chodit", "Imperf.", "to walk / go (regular / multi-directional)")
            CVLRow("turistovat", "Imperf.", "to hike")
            CVLRow("běžet", "Imperf.", "to run (one direction)", "→ doběhnout (Perf.)")
            CVLRow("běhat", "Imperf.", "to run (regularly)")
            CVLRow("budit se", "Imperf.", "to wake up", "→ vzbudit se (Perf.)")
            CVLRow("vzbudit se", "Perf.", "to wake up", "→ budit se (Imperf.)")
            CVLRow("řídit", "Imperf.", "to drive")
            CVLRow("navigovat", "Imperf.", "to navigate")
            CVLRow("plavat", "Imperf.", "to swim", "→ přeplavat (Perf.)")
            CVLRow("letět", "Imperf.", "to fly (one direction)", "→ přiletět (Perf.)")
            CVLRow("létat", "Imperf.", "to fly (regularly)")
            CVLRow("plout", "Imperf.", "to sail", "→ přeplout (Perf.)")
            CVLRow("pohybovat se", "Imperf.", "to move", "→ přesunout se (Perf.)")
            CVLRow("cestovat", "Imperf.", "to travel")
            CVLRow("přicházet", "Imperf.", "to come (on foot)", "→ přijít (Perf.)")
            CVLRow("přijít", "Perf.", "to come (once)", "→ přicházet (Imperf.)")
            CVLRow("odcházet", "Imperf.", "to leave", "→ odejít (Perf.)")
            CVLRow("odejít", "Perf.", "to leave (complete)", "→ odcházet (Imperf.)")
            CVLRow("přijíždět", "Imperf.", "to arrive (by vehicle)", "→ přijet (Perf.)")
            CVLRow("přijet", "Perf.", "to arrive (complete)", "→ přijíždět (Imperf.)")
            CVLRow("spěchat", "Imperf.", "to hurry")
            CVLRow("procházet", "Imperf.", "to pass by / go through", "→ projít (Perf.)")
            CVLRow("projít", "Perf.", "to pass (complete)", "→ procházet (Imperf.)")

            // ── 3. Communication & Speech ─────────────────────────────────
            CVLSection("Communication & Speech")
            CVLRow("mluvit", "Imperf.", "to speak")
            CVLRow("povídat", "Imperf.", "to talk / chat", "→ povědět (Perf.)")
            CVLRow("křičet", "Imperf.", "to shout", "→ zakřičet (Perf.)")
            CVLRow("řvát", "Imperf.", "to yell / roar", "→ zařvat (Perf.)")
            CVLRow("volat", "Imperf.", "to call", "→ zavolat (Perf.)")
            CVLRow("odpovídat", "Imperf.", "to answer", "→ odpovědět (Perf.)")
            CVLRow("ptát se", "Imperf.", "to ask / question", "→ zeptat se (Perf.)")
            CVLRow("říkat", "Imperf.", "to say / tell", "→ říct (Perf.)")
            CVLRow("hádat se", "Imperf.", "to argue", "→ pohádat se (Perf.)")

            // ── 4. Mind & Knowledge ───────────────────────────────────────
            CVLSection("Mind & Knowledge")
            CVLRow("vědět", "Imperf.", "to know (a fact)")
            CVLRow("znát", "Imperf.", "to know (a person / place)")
            CVLRow("chtít", "Imperf.", "to want")
            CVLRow("zjišťovat", "Imperf.", "to find out", "→ zjistit (Perf.)")
            CVLRow("zjistit", "Perf.", "to find out (complete)", "→ zjišťovat (Imperf.)")
            CVLRow("myslet", "Imperf.", "to think")
            CVLRow("pamatovat si", "Imperf.", "to remember", "→ zapamatovat si (Perf.)")
            CVLRow("zapamatovat si", "Perf.", "to memorize / remember", "→ pamatovat si (Imperf.)")
            CVLRow("zapomínat", "Imperf.", "to forget", "→ zapomenout (Perf.)")
            CVLRow("zapomenout", "Perf.", "to forget (completely)", "→ zapomínat (Imperf.)")
            CVLRow("procvičovat", "Imperf.", "to practice", "→ procvičit (Perf.)")
            CVLRow("procvičit", "Perf.", "to practice (complete)", "→ procvičovat (Imperf.)")
            CVLRow("rozumět", "Imperf.", "to understand", "→ pochopit (Perf.)")
            CVLRow("pochopit", "Perf.", "to understand (grasp)", "→ rozumět (Imperf.)")
            CVLRow("odhadovat", "Imperf.", "to estimate", "→ odhadnout (Perf.)")
            CVLRow("odhadnout", "Perf.", "to estimate (once)", "→ odhadovat (Imperf.)")
            CVLRow("určovat", "Imperf.", "to determine", "→ určit (Perf.)")
            CVLRow("určit", "Perf.", "to determine (complete)", "→ určovat (Imperf.)")

            // ── 5. Emotions & Feelings ────────────────────────────────────
            CVLSection("Emotions & Feelings")
            CVLRow("milovat", "Imperf.", "to love")
            CVLRow("mít rád / ráda", "Imperf.", "to like / be fond of")
            CVLRow("nenávidět", "Imperf.", "to hate")
            CVLRow("obtěžovat", "Imperf.", "to annoy / bother")
            CVLRow("chválit", "Imperf.", "to compliment / praise", "→ pochválit (Perf.)")
            CVLRow("pochválit", "Perf.", "to compliment (complete)", "→ chválit (Imperf.)")
            CVLRow("starat se", "Imperf.", "to care (about)")
            CVLRow("oceňovat", "Imperf.", "to appreciate", "→ ocenit (Perf.)")
            CVLRow("ocenit", "Perf.", "to appreciate (once)", "→ oceňovat (Imperf.)")
            CVLRow("pečovat", "Imperf.", "to nurture / care for")

            // ── 6. Work & Study ───────────────────────────────────────────
            CVLSection("Work & Study")
            CVLRow("studovat", "Imperf.", "to study")
            CVLRow("pracovat", "Imperf.", "to work")
            CVLRow("učit se", "Imperf.", "to learn", "→ naučit se (Perf.)")
            CVLRow("naučit se", "Perf.", "to learn (master)", "→ učit se (Imperf.)")
            CVLRow("přednášet", "Imperf.", "to lecture", "→ přednést (Perf.)")
            CVLRow("léčit", "Imperf.", "to treat (medically)", "→ vyléčit (Perf.)")
            CVLRow("vyléčit", "Perf.", "to cure / treat (complete)", "→ léčit (Imperf.)")
            CVLRow("vyšetřovat", "Imperf.", "to investigate", "→ vyšetřit (Perf.)")
            CVLRow("vyšetřit", "Perf.", "to investigate (complete)", "→ vyšetřovat (Imperf.)")
            CVLRow("zvládat", "Imperf.", "to handle", "→ zvládnout (Perf.)")
            CVLRow("zvládnout", "Perf.", "to handle (complete)", "→ zvládat (Imperf.)")
            CVLRow("spravovat", "Imperf.", "to manage / administer")
            CVLRow("zakládat", "Imperf.", "to establish / found", "→ založit (Perf.)")
            CVLRow("založit", "Perf.", "to establish (complete)", "→ zakládat (Imperf.)")
            CVLRow("povyšovat", "Imperf.", "to promote (career)", "→ povýšit (Perf.)")
            CVLRow("povýšit", "Perf.", "to promote (complete)", "→ povyšovat (Imperf.)")
            CVLRow("degradovat", "Imperf.", "to demote")
            CVLRow("dosahovat", "Imperf.", "to achieve", "→ dosáhnout (Perf.)")
            CVLRow("dosáhnout", "Perf.", "to achieve (complete)", "→ dosahovat (Imperf.)")

            // ── 7. Objects & Household Actions ───────────────────────────
            CVLSection("Objects & Household Actions")
            CVLRow("zapínat", "Imperf.", "to turn on", "→ zapnout (Perf.)")
            CVLRow("zapnout", "Perf.", "to turn on (complete)", "→ zapínat (Imperf.)")
            CVLRow("vypínat", "Imperf.", "to turn off", "→ vypnout (Perf.)")
            CVLRow("vypnout", "Perf.", "to turn off (complete)", "→ vypínat (Imperf.)")
            CVLRow("zamykat", "Imperf.", "to lock", "→ zamknout (Perf.)")
            CVLRow("zamknout", "Perf.", "to lock (complete)", "→ zamykat (Imperf.)")
            CVLRow("otevírat", "Imperf.", "to open", "→ otevřít (Perf.)")
            CVLRow("otevřít", "Perf.", "to open (complete)", "→ otevírat (Imperf.)")
            CVLRow("zavírat", "Imperf.", "to close", "→ zavřít (Perf.)")
            CVLRow("zavřít", "Perf.", "to close (complete)", "→ zavírat (Imperf.)")
            CVLRow("brát", "Imperf.", "to take", "→ vzít (Perf.)")
            CVLRow("vzít", "Perf.", "to take (complete)", "→ brát (Imperf.)")
            CVLRow("dávat", "Imperf.", "to put / give", "→ dát (Perf.)")
            CVLRow("dát", "Perf.", "to put / give (complete)", "→ dávat (Imperf.)")
            CVLRow("posílat", "Imperf.", "to send", "→ poslat (Perf.)")
            CVLRow("poslat", "Perf.", "to send (complete)", "→ posílat (Imperf.)")
            CVLRow("dostávat", "Imperf.", "to receive", "→ dostat (Perf.)")
            CVLRow("dostat", "Perf.", "to receive (complete)", "→ dostávat (Imperf.)")
            CVLRow("zapalovat", "Imperf.", "to ignite / light (fire)", "→ zapálit (Perf.)")
            CVLRow("zapálit", "Perf.", "to ignite (complete)", "→ zapalovat (Imperf.)")
            CVLRow("rozsvěcovat", "Imperf.", "to light up / switch on", "→ rozsvítit (Perf.)")
            CVLRow("rozsvítit", "Perf.", "to light up (complete)", "→ rozsvěcovat (Imperf.)")

            // ── 8. Digital & Tech Actions ─────────────────────────────────
            CVLSection("Digital & Tech Actions")
            CVLRow("mačkat", "Imperf.", "to press", "→ zmáčknout (Perf.)")
            CVLRow("zmáčknout", "Perf.", "to press (once)", "→ mačkat (Imperf.)")
            CVLRow("klikat", "Imperf.", "to click", "→ kliknout (Perf.)")
            CVLRow("kliknout", "Perf.", "to click (once)", "→ klikat (Imperf.)")
            CVLRow("sledovat", "Imperf.", "to follow / watch")
            CVLRow("navrhovat", "Imperf.", "to design", "→ navrhnout (Perf.)")
            CVLRow("navrhnout", "Perf.", "to design (complete)", "→ navrhovat (Imperf.)")
            CVLRow("tisknout", "Imperf.", "to print", "→ vytisknout (Perf.)")
            CVLRow("vytisknout", "Perf.", "to print (complete)", "→ tisknout (Imperf.)")
            CVLRow("používat", "Imperf.", "to use", "→ použít (Perf.)")
            CVLRow("použít", "Perf.", "to use (once)", "→ používat (Imperf.)")
            CVLRow("ladit", "Imperf.", "to debug", "→ odladit (Perf.)")
            CVLRow("odladit", "Perf.", "to debug (complete)", "→ ladit (Imperf.)")
            CVLRow("kompilovat", "Imperf.", "to compile")
            CVLRow("linkovat", "Imperf.", "to link (build process)")
            CVLRow("programovat", "Imperf.", "to code / program")
            CVLRow("zpracovávat", "Imperf.", "to process", "→ zpracovat (Perf.)")
            CVLRow("zpracovat", "Perf.", "to process (complete)", "→ zpracovávat (Imperf.)")
            CVLRow("blokovat", "Imperf.", "to block")
            CVLRow("povolovat", "Imperf.", "to allow", "→ povolit (Perf.)")
            CVLRow("povolit", "Perf.", "to allow (complete)", "→ povolovat (Imperf.)")
            CVLRow("zakazovat", "Imperf.", "to deny / forbid", "→ zakázat (Perf.)")
            CVLRow("zakázat", "Perf.", "to deny (complete)", "→ zakazovat (Imperf.)")
            CVLRow("načítat", "Imperf.", "to load (data)", "→ načíst (Perf.)")
            CVLRow("načíst", "Perf.", "to load (complete)", "→ načítat (Imperf.)")
            CVLRow("ukládat", "Imperf.", "to save (data)", "→ uložit (Perf.)")
            CVLRow("uložit", "Perf.", "to save (complete)", "→ ukládat (Imperf.)")
            CVLRow("připojovat", "Imperf.", "to connect", "→ připojit (Perf.)")
            CVLRow("připojit", "Perf.", "to connect (complete)", "→ připojovat (Imperf.)")
            CVLRow("odpojovat", "Imperf.", "to disconnect", "→ odpojit (Perf.)")
            CVLRow("odpojit", "Perf.", "to disconnect (complete)", "→ odpojovat (Imperf.)")

            // ── 9. Food & Cooking ─────────────────────────────────────────
            CVLSection("Food & Cooking")
            CVLRow("sekat", "Imperf.", "to chop", "→ nasekat (Perf.)")
            CVLRow("nasekat", "Perf.", "to chop (complete)", "→ sekat (Imperf.)")
            CVLRow("smažit", "Imperf.", "to fry", "→ usmažit (Perf.)")
            CVLRow("usmažit", "Perf.", "to fry (complete)", "→ smažit (Imperf.)")
            CVLRow("péct", "Imperf.", "to bake", "→ upéct (Perf.)")
            CVLRow("upéct", "Perf.", "to bake (complete)", "→ péct (Imperf.)")
            CVLRow("míchat", "Imperf.", "to mix / stir", "→ zamíchat (Perf.)")
            CVLRow("zamíchat", "Perf.", "to mix (complete)", "→ míchat (Imperf.)")
            CVLRow("krájet", "Imperf.", "to cut / slice", "→ nakrájet (Perf.)")
            CVLRow("nakrájet", "Perf.", "to slice (complete)", "→ krájet (Imperf.)")
            CVLRow("zahřívat", "Imperf.", "to heat", "→ zahřát (Perf.)")
            CVLRow("zahřát", "Perf.", "to heat up (complete)", "→ zahřívat (Imperf.)")
            CVLRow("chladit", "Imperf.", "to cool", "→ ochladit (Perf.)")
            CVLRow("ochladit", "Perf.", "to cool down (complete)", "→ chladit (Imperf.)")
            CVLRow("ochutnávat", "Imperf.", "to taste", "→ ochutnat (Perf.)")
            CVLRow("ochutnat", "Perf.", "to taste (once)", "→ ochutnávat (Imperf.)")
            CVLRow("zkoušet", "Imperf.", "to try", "→ zkusit (Perf.)")
            CVLRow("zkusit", "Perf.", "to try (once)", "→ zkoušet (Imperf.)")
            CVLRow("preferovat", "Imperf.", "to prefer")
            CVLRow("doporučovat", "Imperf.", "to recommend", "→ doporučit (Perf.)")
            CVLRow("doporučit", "Perf.", "to recommend (complete)", "→ doporučovat (Imperf.)")

            // ── 10. Commerce & Transactions ───────────────────────────────
            CVLSection("Commerce & Transactions")
            CVLRow("nakupovat", "Imperf.", "to shop")
            CVLRow("kupovat", "Imperf.", "to buy", "→ koupit (Perf.)")
            CVLRow("koupit", "Perf.", "to buy (complete)", "→ kupovat (Imperf.)")
            CVLRow("prodávat", "Imperf.", "to sell", "→ prodat (Perf.)")
            CVLRow("prodat", "Perf.", "to sell (complete)", "→ prodávat (Imperf.)")
            CVLRow("objednávat", "Imperf.", "to order", "→ objednat (Perf.)")
            CVLRow("objednat", "Perf.", "to order (complete)", "→ objednávat (Imperf.)")
            CVLRow("žádat", "Imperf.", "to request / ask for", "→ požádat (Perf.)")
            CVLRow("požádat", "Perf.", "to request (complete)", "→ žádat (Imperf.)")
            CVLRow("investovat", "Imperf.", "to invest")
            CVLRow("šetřit", "Imperf.", "to save (money)")
            CVLRow("zachraňovat", "Imperf.", "to save / rescue", "→ zachránit (Perf.)")
            CVLRow("zachránit", "Perf.", "to rescue (complete)", "→ zachraňovat (Imperf.)")
            CVLRow("vyhrávat", "Imperf.", "to win", "→ vyhrát (Perf.)")
            CVLRow("vyhrát", "Perf.", "to win (complete)", "→ vyhrávat (Imperf.)")
            CVLRow("prohrávat", "Imperf.", "to lose (a game)", "→ prohrát (Perf.)")
            CVLRow("prohrát", "Perf.", "to lose (complete)", "→ prohrávat (Imperf.)")
            CVLRow("sázet", "Imperf.", "to bet / gamble", "→ vsadit (Perf.)")
            CVLRow("vsadit", "Perf.", "to place a bet", "→ sázet (Imperf.)")
            CVLRow("propagovat", "Imperf.", "to market / promote")
            CVLRow("distribuovat", "Imperf.", "to distribute")

            // ── 11. Social & Relationships ────────────────────────────────
            CVLSection("Social & Relationships")
            CVLRow("pomáhat", "Imperf.", "to help", "→ pomoct (Perf.)")
            CVLRow("pomoct", "Perf.", "to help (complete)", "→ pomáhat (Imperf.)")
            CVLRow("setkávat se", "Imperf.", "to meet", "→ setkat se (Perf.)")
            CVLRow("setkat se", "Perf.", "to meet (once)", "→ setkávat se (Imperf.)")
            CVLRow("domlouvat", "Imperf.", "to arrange", "→ domluvit (Perf.)")
            CVLRow("domluvit", "Perf.", "to arrange (complete)", "→ domlouvat (Imperf.)")
            CVLRow("vybírat", "Imperf.", "to choose / select", "→ vybrat (Perf.)")
            CVLRow("vybrat", "Perf.", "to choose (complete)", "→ vybírat (Imperf.)")
            CVLRow("zvát", "Imperf.", "to invite", "→ pozvat (Perf.)")
            CVLRow("pozvat", "Perf.", "to invite (complete)", "→ zvát (Imperf.)")
            CVLRow("navštěvovat", "Imperf.", "to visit", "→ navštívit (Perf.)")
            CVLRow("navštívit", "Perf.", "to visit (complete)", "→ navštěvovat (Imperf.)")
            CVLRow("jmenovat se", "Imperf.", "to be called / named")
            CVLRow("přibližovat se", "Imperf.", "to approach", "→ přiblížit se (Perf.)")
            CVLRow("přiblížit se", "Perf.", "to approach (complete)", "→ přibližovat se (Imperf.)")
            CVLRow("flirtovat", "Imperf.", "to flirt")
            CVLRow("dvořit se", "Imperf.", "to court / woo")
            CVLRow("otravovat", "Imperf.", "to nag / pester")
            CVLRow("modlit se", "Imperf.", "to pray")

            // ── 12. Creation & Maintenance ────────────────────────────────
            CVLSection("Creation & Maintenance")
            CVLRow("vytvářet", "Imperf.", "to create", "→ vytvořit (Perf.)")
            CVLRow("vytvořit", "Perf.", "to create (complete)", "→ vytvářet (Imperf.)")
            CVLRow("ničit", "Imperf.", "to destroy", "→ zničit (Perf.)")
            CVLRow("zničit", "Perf.", "to destroy (complete)", "→ ničit (Imperf.)")
            CVLRow("stavět", "Imperf.", "to build", "→ postavit (Perf.)")
            CVLRow("postavit", "Perf.", "to build (complete)", "→ stavět (Imperf.)")
            CVLRow("malovat", "Imperf.", "to paint", "→ namalovat (Perf.)")
            CVLRow("namalovat", "Perf.", "to paint (complete)", "→ malovat (Imperf.)")
            CVLRow("opravovat", "Imperf.", "to fix / repair", "→ opravit (Perf.)")
            CVLRow("opravit", "Perf.", "to fix (complete)", "→ opravovat (Imperf.)")
            CVLRow("měnit", "Imperf.", "to change", "→ změnit (Perf.)")
            CVLRow("změnit", "Perf.", "to change (complete)", "→ měnit (Imperf.)")
            CVLRow("vyměňovat", "Imperf.", "to replace / exchange", "→ vyměnit (Perf.)")
            CVLRow("vyměnit", "Perf.", "to replace (complete)", "→ vyměňovat (Imperf.)")
            CVLRow("organizovat", "Imperf.", "to organize", "→ zorganizovat (Perf.)")
            CVLRow("zorganizovat", "Perf.", "to organize (complete)", "→ organizovat (Imperf.)")
            CVLRow("oddělovat", "Imperf.", "to separate", "→ oddělit (Perf.)")
            CVLRow("oddělit", "Perf.", "to separate (complete)", "→ oddělovat (Imperf.)")
            CVLRow("vynalézat", "Imperf.", "to invent", "→ vynalést (Perf.)")
            CVLRow("vynalést", "Perf.", "to invent (complete)", "→ vynalézat (Imperf.)")
            CVLRow("upravovat", "Imperf.", "to adjust", "→ upravit (Perf.)")
            CVLRow("upravit", "Perf.", "to adjust (complete)", "→ upravovat (Imperf.)")

            // ── 13. Processes & Life Events ───────────────────────────────
            CVLSection("Processes & Life Events")
            CVLRow("být", "Imperf.", "to be")
            CVLRow("žít", "Imperf.", "to live")
            CVLRow("umírat", "Imperf.", "to die", "→ zemřít (Perf.)")
            CVLRow("zemřít", "Perf.", "to die", "→ umírat (Imperf.)")
            CVLRow("rodit se", "Imperf.", "to be born", "→ narodit se (Perf.)")
            CVLRow("narodit se", "Perf.", "to be born", "→ rodit se (Imperf.)")
            CVLRow("zastavovat", "Imperf.", "to stop", "→ zastavit (Perf.)")
            CVLRow("zastavit", "Perf.", "to stop (complete)", "→ zastavovat (Imperf.)")
            CVLRow("začínat", "Imperf.", "to begin", "→ začít (Perf.)")
            CVLRow("začít", "Perf.", "to begin", "→ začínat (Imperf.)")
            CVLRow("končit", "Imperf.", "to finish", "→ skončit (Perf.)")
            CVLRow("skončit", "Perf.", "to finish (complete)", "→ končit (Imperf.)")
            CVLRow("dokončovat", "Imperf.", "to complete", "→ dokončit (Perf.)")
            CVLRow("dokončit", "Perf.", "to complete (finish)", "→ dokončovat (Imperf.)")
            CVLRow("vyhýbat se", "Imperf.", "to avoid", "→ vyhnout se (Perf.)")
            CVLRow("vyhnout se", "Perf.", "to avoid (successfully)", "→ vyhýbat se (Imperf.)")
            CVLRow("obnovovat", "Imperf.", "to resume / renew", "→ obnovit (Perf.)")
            CVLRow("obnovit", "Perf.", "to resume (complete)", "→ obnovovat (Imperf.)")
            CVLRow("pozastavovat", "Imperf.", "to pause", "→ pozastavit (Perf.)")
            CVLRow("pozastavit", "Perf.", "to pause (complete)", "→ pozastavovat (Imperf.)")
            CVLRow("ukazovat", "Imperf.", "to point / show", "→ ukázat (Perf.)")
            CVLRow("ukázat", "Perf.", "to point / show (once)", "→ ukazovat (Imperf.)")
            CVLRow("pasovat", "Imperf.", "to match / fit")
            CVLRow("počítat", "Imperf.", "to count", "→ spočítat (Perf.)")
            CVLRow("spočítat", "Perf.", "to count (complete)", "→ počítat (Imperf.)")
            CVLRow("objevovat se", "Imperf.", "to appear", "→ objevit se (Perf.)")
            CVLRow("objevit se", "Perf.", "to appear (once)", "→ objevovat se (Imperf.)")
            CVLRow("dýchat", "Imperf.", "to breathe")
            CVLRow("přestávat", "Imperf.", "to quit / stop doing", "→ přestat (Perf.)")
            CVLRow("přestat", "Perf.", "to quit (complete)", "→ přestávat (Imperf.)")
            CVLRow("stahovat se", "Imperf.", "to withdraw", "→ stáhnout se (Perf.)")
            CVLRow("stáhnout se", "Perf.", "to withdraw (complete)", "→ stahovat se (Imperf.)")
            CVLRow("odcházet do důchodu", "Imperf.", "to retire")
            CVLRow("topit se", "Imperf.", "to drown", "→ utopit se (Perf.)")
            CVLRow("utopit se", "Perf.", "to drown (complete)", "→ topit se (Imperf.)")
            CVLRow("růst", "Imperf.", "to grow (intransitive)", "→ vyrůst (Perf.)")
            CVLRow("vyrůst", "Perf.", "to grow up (complete)", "→ růst (Imperf.)")
            CVLRow("pěstovat", "Imperf.", "to grow (plants / raise)", "→ vypěstovat (Perf.)")
            CVLRow("vypěstovat", "Perf.", "to grow (complete)", "→ pěstovat (Imperf.)")

            // ── 14. Senses & Perception ───────────────────────────────────
            CVLSection("Senses & Perception")
            CVLRow("poslouchat", "Imperf.", "to listen", "→ vyslechnout (Perf.)")
            CVLRow("slyšet", "Imperf.", "to hear")
            CVLRow("vidět", "Imperf.", "to see")
            CVLRow("dívat se", "Imperf.", "to watch", "→ podívat se (Perf.)")
            CVLRow("podívat se", "Perf.", "to take a look", "→ dívat se (Imperf.)")
            CVLRow("čichat", "Imperf.", "to smell")
            CVLRow("hledět", "Imperf.", "to gaze")
            CVLRow("zírat", "Imperf.", "to stare")

            // ── 15. Reading, Writing & Language ──────────────────────────
            CVLSection("Reading, Writing & Language")
            CVLRow("číst", "Imperf.", "to read", "→ přečíst (Perf.)")
            CVLRow("přečíst", "Perf.", "to read (complete)", "→ číst (Imperf.)")
            CVLRow("psát", "Imperf.", "to write", "→ napsat (Perf.)")
            CVLRow("napsat", "Perf.", "to write (complete)", "→ psát (Imperf.)")

            // ── 16. Arts & Entertainment ──────────────────────────────────
            CVLSection("Arts & Entertainment")
            CVLRow("tančit", "Imperf.", "to dance")
            CVLRow("zpívat", "Imperf.", "to sing")
            CVLRow("hrát", "Imperf.", "to play (a game)")
            CVLRow("hrát na", "Imperf.", "to play (an instrument)")

            // ── 17. Math & Quantities ─────────────────────────────────────
            CVLSection("Math & Quantities")
            CVLRow("přidávat", "Imperf.", "to add", "→ přidat (Perf.)")
            CVLRow("přidat", "Perf.", "to add (complete)", "→ přidávat (Imperf.)")
            CVLRow("odečítat", "Imperf.", "to subtract", "→ odečíst (Perf.)")
            CVLRow("odečíst", "Perf.", "to subtract (complete)", "→ odečítat (Imperf.)")
            CVLRow("násobit", "Imperf.", "to multiply", "→ znásobit (Perf.)")
            CVLRow("znásobit", "Perf.", "to multiply (complete)", "→ násobit (Imperf.)")
            CVLRow("dělit", "Imperf.", "to divide", "→ rozdělit (Perf.)")
            CVLRow("rozdělit", "Perf.", "to divide (complete)", "→ dělit (Imperf.)")
            CVLRow("zvyšovat", "Imperf.", "to raise", "→ zvýšit (Perf.)")
            CVLRow("zvýšit", "Perf.", "to raise (complete)", "→ zvyšovat (Imperf.)")
            CVLRow("snižovat", "Imperf.", "to lower", "→ snížit (Perf.)")
            CVLRow("snížit", "Perf.", "to lower (complete)", "→ snižovat (Imperf.)")

            // ── 18. Conflict & Influence ──────────────────────────────────
            CVLSection("Conflict & Influence")
            CVLRow("útočit", "Imperf.", "to attack", "→ zaútočit (Perf.)")
            CVLRow("zaútočit", "Perf.", "to attack (once)", "→ útočit (Imperf.)")
            CVLRow("bránit", "Imperf.", "to defend", "→ ubránit (Perf.)")
            CVLRow("ubránit", "Perf.", "to defend (successfully)", "→ bránit (Imperf.)")
            CVLRow("přesvědčovat", "Imperf.", "to persuade / convince", "→ přesvědčit (Perf.)")
            CVLRow("přesvědčit", "Perf.", "to persuade (complete)", "→ přesvědčovat (Imperf.)")
            CVLRow("ovlivňovat", "Imperf.", "to influence", "→ ovlivnit (Perf.)")
            CVLRow("ovlivnit", "Perf.", "to influence (complete)", "→ ovlivňovat (Imperf.)")
            CVLRow("podvádět", "Imperf.", "to trick / deceive", "→ oklamat (Perf.)")
            CVLRow("oklamat", "Perf.", "to trick (complete)", "→ podvádět (Imperf.)")
            CVLRow("hypnotizovat", "Imperf.", "to hypnotize")

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
private fun CVLRow(czech: String, aspect: String, english: String, pair: String = "") {
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
    }
}
