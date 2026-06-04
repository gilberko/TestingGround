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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectingSentencesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Connecting Sentences", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // ── Because / Since ───────────────────────────────────────────
            CSSSection("Because / Since")
            CSSNote("Three words: protože (everyday speech), jelikož (formal), neboť (literary/written). All take a full verb clause. The two clauses can appear in either order.")
            CSSRow("Jdu domů, protože jsem unavený.", "I'm going home because I'm tired.")
            CSSRow("Nepřišel, jelikož byl nemocný.", "He didn't come since he was ill.")
            CSSRow("Nevyšel, neboť bylo chladno.", "He didn't go out, for it was cold.")
            CSSNote("protože = by far the most common. jelikož = slightly formal, implies 'given that / seeing as'. neboť = literary and relatively rare in everyday speech.")

            // ── Therefore / So / And So ───────────────────────────────────
            CSSSection("Therefore / So / And So")
            CSSNote("Several words express consequence. proto and a proto point back at a cause already stated. takže introduces the result clause directly. tedy draws a logical conclusion. a tak is casual spoken. tudíž is formal.")
            CSSRow("Byl jsem unavený, proto jsem šel spát.", "I was tired, therefore I went to sleep.")
            CSSRow("Pršelo, takže jsme zůstali doma.", "It was raining, so we stayed home.")
            CSSRow("Jsi unavený, tedy jdi spát.", "You're tired, so go to sleep.")
            CSSRow("Byl pozdě, a tak zmeškal vlak.", "He was late, and so he missed the train.")
            CSSRow("Zásoby nedošly, tudíž pokračujeme.", "Supplies have not run out; therefore we continue.")
            CSSNote("proto / a proto = therefore (points backward). takže = so/consequently (introduces result clause). tedy = so/thus (logical deduction or instruction). a tak = and so (casual). tudíž = therefore (formal).")

            // ── Although / Even Though ────────────────────────────────────
            CSSSection("Although / Even Though")
            CSSNote("Four words, ranging from casual to literary. All take a full verb clause. The concessive clause can come before or after the main clause.")
            CSSRow("Přestože prší, jdu ven.", "Even though it's raining, I'm going out.")
            CSSRow("I když byl unavený, pracoval.", "Even though he was tired, he worked.")
            CSSRow("Ačkoliv to věděl, nic neřekl.", "Although he knew it, he said nothing.")
            CSSRow("Třebaže bylo pozdě, zůstal jsem.", "Though it was late, I stayed.")
            CSSNote("i když = most common in speech. přestože = neutral, very common. ačkoliv / ačkoli = more formal/written. třebaže = literary. All four are interchangeable in meaning; the difference is register.")

            // ── While / Whereas ───────────────────────────────────────────
            CSSSection("While / Whereas")
            CSSNote("zatímco = while (two simultaneous actions) OR whereas (contrast). kdežto = whereas (contrast only — more literary).")
            CSSRow("Zatímco jsem vařil, ona četla.", "While I was cooking, she was reading.")
            CSSRow("Petr pracoval, zatímco Pavel spal.", "Petr was working while Pavel was sleeping.")
            CSSRow("Ona zpívala, kdežto on tančil.", "She was singing, whereas he was dancing.")
            CSSRow("Já šetřím, kdežto ty utrácíš.", "I save money, whereas you spend it.")
            CSSNote("zatímco is versatile: temporal (two simultaneous events) or contrastive (two opposite situations). kdežto is purely contrastive and more formal.")

            // ── After / Once ──────────────────────────────────────────────
            CSSSection("After / Once")
            CSSNote("Three main options. poté, co is the explicit formal phrase. co alone (as a temporal conjunction) is colloquial but fully correct — used in everyday speech and literature. jakmile carries a slightly more immediate sense ('as soon as').")
            CSSRow("Poté, co odešel, zavolal mi.", "After he left, he called me.")
            CSSRow("Co přišel, vstal a pozdravil.", "Once he arrived, he got up and said hello.")
            CSSRow("Jakmile ho uvidím, řeknu ti.", "As soon as I see him, I'll tell you.")
            CSSRow("Co to řekla, zalitovala.", "After she said it, she regretted it.")
            CSSNote("'co' as a temporal conjunction — YES, this is correct Czech. Co here means 'once / after / as soon as'. It is the same word as the interrogative co (what), but when used before a verb clause it functions as a temporal connector. Register: informal/spoken and literary. Less formal than jakmile, more concise than poté, co.")
            CSSNote("Comma note: poté, co always has a comma before co. co used as a temporal conjunction also takes a comma when it begins a mid-sentence clause: Vstal, co přišla. = He stood up when she arrived.")

            // ── Before ────────────────────────────────────────────────────
            CSSSection("Before")
            CSSNote("než is the core word for 'before' in front of a verb clause. dříve než and předtím než are more emphatic variants. Note: než also means 'than' in comparisons — context makes the meaning clear.")
            CSSRow("Než odešel, zavolal mi.", "Before he left, he called me.")
            CSSRow("Dříve než začneš, přečti si instrukce.", "Before you start, read the instructions.")
            CSSRow("Předtím než jsem odešel, zamkl jsem dveře.", "Before I left, I locked the door.")
            CSSNote("než = before (most common with verb clauses). dříve než = before / earlier than (emphasises earliness). předtím než = before that (explicit). All three take a full verb clause.")

            // ── In Order To / So That — aby ───────────────────────────────
            CSSSection("In Order To / So That — aby")
            CSSNote("aby expresses purpose ('in order to / so that'). It conjugates — with the same person endings as the conditional particle by. The verb in the aby-clause takes the l-form (past participle).")
            CSSTable(
                headers = listOf("Form", "Meaning"),
                rows = listOf(
                    listOf("abych", "so that I / in order for me to"),
                    listOf("abys", "so that you (sg.)"),
                    listOf("aby", "so that he / she / it"),
                    listOf("abychom", "so that we"),
                    listOf("abyste", "so that you (pl. / formal)"),
                    listOf("aby", "so that they")
                ),
                weights = listOf(0.7f, 1.3f)
            )
            CSSRow("Učím se, abych se zlepšil/a.", "I study so that I improve.")
            CSSRow("Přišel brzy, aby stihl vlak.", "He came early in order to catch the train.")
            CSSRow("Řekni mi to, abych věděl/a.", "Tell me so that I know.")
            CSSRow("Mluvte pomaleji, abychom rozuměli.", "Speak more slowly so that we understand.")
            CSSNote("aby fuses a + by + person ending — the same endings as the conditional: abych = aby+ch (1sg), abys = aby+s (2sg), abychom = aby+chom (1pl), abyste = aby+ste (2pl). The main verb before aby can be any tense; the verb inside the aby-clause always uses the l-form.")

            // ── In Addition / On Top of That ──────────────────────────────
            CSSSection("In Addition / On Top of That")
            CSSNote("These are adverbs and phrases that add information — they introduce a new clause or sentence rather than fusing two clauses with a single conjunction.")
            CSSRow("navíc", "moreover / on top of that / besides")
            CSSRow("kromě toho", "in addition / besides that / apart from that")
            CSSRow("a k tomu", "and on top of that / and what's more")
            CSSRow("mimo to", "aside from that / beyond that")
            CSSRow("nadto", "moreover / furthermore (formal/written)")
            CSSRow("Byl unavený, navíc byl nemocný.", "He was tired; on top of that, he was sick.")
            CSSRow("Přišel pozdě. Kromě toho zapomněl klíče.", "He arrived late. In addition, he forgot his keys.")
            CSSRow("A k tomu, bylo velké vedro.", "And on top of that, there was intense heat.")
            CSSNote("navíc is the most neutral and common. kromě toho is slightly more formal. These words come after a sentence end or comma — they start the next independent clause, not a subordinate clause.")

            // ── Despite / In Spite Of ─────────────────────────────────────
            CSSSection("Despite / In Spite Of")
            CSSNote("Three ways to express 'despite'. přesto is an adverb pointing back at a situation already mentioned. přes + accusative and navzdory + dative precede a noun directly.")
            CSSRow("Věděl to, přesto nic neřekl.", "He knew it; despite that, he said nothing.")
            CSSRow("Přes déšť jsme šli ven.", "Despite the rain, we went out.")
            CSSRow("Navzdory problémům jsme uspěli.", "In spite of the problems, we succeeded.")
            CSSRow("Přes všechny obtíže to zvládl.", "Despite all the difficulties, he managed it.")
            CSSRow("Navzdory únavě pokračovala.", "Despite her tiredness, she continued.")
            CSSNote("přesto = despite that (refers back — no noun follows directly). přes + accusative = despite + noun. navzdory + dative = in spite of + noun (more formal). All three mean the same — choice depends on what follows and register.")

            // ── Quick Reference ───────────────────────────────────────────
            CSSSection("Quick Reference")
            CSSNote("A summary of the sentence connectors covered on this screen:")
            CSSTable(
                headers = listOf("Czech", "English", "Example"),
                rows = listOf(
                    listOf("protože", "because", "Jdu domů, protože jsem unavený."),
                    listOf("proto / a proto", "therefore", "Byl unavený, proto šel spát."),
                    listOf("takže", "so (result)", "Pršelo, takže jsme zůstali doma."),
                    listOf("tedy", "so / thus", "Jsi unavený, tedy jdi spát."),
                    listOf("přestože", "even though", "Přestože prší, jdu ven."),
                    listOf("i když", "even though (spoken)", "I když byl unavený, pracoval."),
                    listOf("ačkoliv", "although (formal)", "Ačkoliv to věděl, nic neřekl."),
                    listOf("zatímco", "while / whereas", "Zatímco jsem vařil, ona četla."),
                    listOf("poté, co", "after (formal)", "Poté, co odešel, zavolal mi."),
                    listOf("co (temporal)", "once / after (spoken)", "Co přišel, vstal a pozdravil."),
                    listOf("jakmile", "as soon as", "Jakmile ho uvidím, řeknu ti."),
                    listOf("než", "before", "Než odešel, zavolal mi."),
                    listOf("aby", "so that / in order to", "Přišel brzy, aby stihl vlak."),
                    listOf("navíc", "moreover", "Byl unavený, navíc byl nemocný."),
                    listOf("kromě toho", "in addition", "Přišel pozdě. Kromě toho zapomněl klíče."),
                    listOf("přesto", "despite that", "Věděl to, přesto nic neřekl."),
                    listOf("přes + acc.", "despite + noun", "Přes déšť jsme šli ven."),
                    listOf("navzdory + dat.", "in spite of + noun", "Navzdory problémům jsme uspěli.")
                ),
                weights = listOf(0.75f, 0.9f, 1.35f)
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CSSSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun CSSNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

@Composable
private fun CSSRow(czech: String, english: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = czech, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Text(text = english, fontSize = 15.sp, color = Color.DarkGray, modifier = Modifier.padding(start = 6.dp))
    }
}

@Composable
private fun CSSTable(headers: List<String>, rows: List<List<String>>, weights: List<Float>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            headers.forEachIndexed { i, h ->
                Text(
                    text = h,
                    modifier = Modifier.weight(weights[i]).padding(horizontal = 4.dp, vertical = 4.dp),
                    fontWeight = FontWeight.Bold, fontSize = 13.sp, color = ButtonBlue
                )
            }
        }
        HorizontalDivider(color = ButtonBlue, thickness = 1.dp)
        rows.forEach { row ->
            Row(modifier = Modifier.fillMaxWidth()) {
                row.forEachIndexed { i, cell ->
                    Text(
                        text = cell,
                        modifier = Modifier.weight(weights[i]).padding(horizontal = 4.dp, vertical = 5.dp),
                        fontSize = 13.sp, color = Color.Black
                    )
                }
            }
            HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp)
        }
    }
}
