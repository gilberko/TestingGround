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

            // ── 5. Emotions & Feelings ────────────────────────────────────
            CVLSection("Emotions & Feelings")
            CVLRow("milovat", "Imperf.", "to love")
            CVLRow("mít rád / ráda", "Imperf.", "to like / be fond of")
            CVLRow("nenávidět", "Imperf.", "to hate")
            CVLRow("obtěžovat", "Imperf.", "to annoy / bother")
            CVLRow("chválit", "Imperf.", "to compliment / praise", "→ pochválit (Perf.)")
            CVLRow("pochválit", "Perf.", "to compliment (complete)", "→ chválit (Imperf.)")

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

            // ── 14. Senses & Perception ───────────────────────────────────
            CVLSection("Senses & Perception")
            CVLRow("poslouchat", "Imperf.", "to listen", "→ vyslechnout (Perf.)")
            CVLRow("slyšet", "Imperf.", "to hear")
            CVLRow("vidět", "Imperf.", "to see")
            CVLRow("dívat se", "Imperf.", "to watch", "→ podívat se (Perf.)")
            CVLRow("podívat se", "Perf.", "to take a look", "→ dívat se (Imperf.)")
            CVLRow("čichat", "Imperf.", "to smell")

            // ── 15. Reading, Writing & Language ──────────────────────────
            CVLSection("Reading, Writing & Language")
            CVLRow("číst", "Imperf.", "to read", "→ přečíst (Perf.)")
            CVLRow("přečíst", "Perf.", "to read (complete)", "→ číst (Imperf.)")
            CVLRow("psát", "Imperf.", "to write", "→ napsat (Perf.)")
            CVLRow("napsat", "Perf.", "to write (complete)", "→ psát (Imperf.)")

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
