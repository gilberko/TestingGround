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
fun QuestionsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Questions", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            QSSection("Question Words")
            QSRow("Co?", "What?")
            QSRow("Kdo?", "Who?")
            QSRow("Kde?", "Where? (location — static)")
            QSRow("Kam?", "Where to? (direction — motion)")
            QSRow("Odkud?", "Where from? (origin)")
            QSRow("Kdy?", "When?")
            QSRow("Jak?", "How?")
            QSRow("Proč?", "Why?")
            QSNote("Answering proč — common ways to give a reason:")
            QSRow("Protože...", "Because... (most common — followed by a clause)")
            QSRow("Kvůli + dative", "Due to / Because of... (neutral or negative cause)")
            QSRow("Díky + dative", "Thanks to... (positive cause only — use kvůli for negative)")
            QSRow("Jelikož...", "Since / Because... (formal/literary register)")
            QSRow("Z důvodu + genitive", "Due to / For the reason of... (formal/bureaucratic)")
            QSNote("Kvůli vs. díky: Kvůli nehodě = due to the accident (negative — correct). Díky nehodě = thanks to the accident (sounds wrong — accident is bad). Díky dobrému počasí = thanks to the good weather (positive — correct).")
            QSRow("Nač? / K čemu?", "What for? (purpose)")
            QSRow("Kolik?", "How many? / How much?")
            QSRow("Jaký / Jaká / Jaké?", "What kind of? (agrees with noun gender)")
            QSRow("Který / Která / Které?", "Which? — choosing from a set (agrees with noun gender)")

            QSSection("Who / To Whom / From Whom")
            QSNote("kdo (who) declines like a pronoun: Nominative kdo, Genitive koho, Dative komu, Accusative koho, Locative o kom, Instrumental kým.")
            QSRow("Kdo?", "Who? (subject)")
            QSRow("Koho?", "Who? / Whom? (object — Accusative/Genitive)")
            QSRow("Komu?", "To whom? (Dative)")
            QSRow("Od koho?", "From whom? (Genitive)")
            QSRow("Pro koho?", "For whom? (Accusative with pro)")
            QSRow("S kým?", "With whom? (Instrumental)")
            QSRow("O kom?", "About whom? (Locative — always with preposition o)")
            QSRow("Čí?", "Whose? (possessive interrogative — declines like an adjective)")
            QSNote("O kom mluvíte? = Who are you talking about? Čí je to auto? = Whose car is that?")

            QSSection("Question Words Across All Cases — kdo / co")
            QSNote("Just like Russian кто/что, Czech has parallel question words for animate (kdo) and inanimate (co) referents across all cases.")
            QSTable(
                headers = listOf("Case", "Animate: kdo", "Inanimate: co"),
                rows = listOf(
                    listOf("Nominative", "kdo", "co"),
                    listOf("Genitive", "koho", "čeho"),
                    listOf("Dative", "komu", "čemu"),
                    listOf("Accusative", "koho", "co"),
                    listOf("Locative", "o kom", "o čem"),
                    listOf("Instrumental", "kým", "čím")
                ),
                weights = listOf(0.9f, 0.8f, 0.8f)
            )
            QSNote("Accusative animate = same as genitive (koho). Accusative inanimate = same as nominative (co). This mirrors the animate/inanimate split in noun accusative forms.")
            QSNote("Czech masculine hard adjective endings follow the same pattern as these question words:")
            QSTable(
                headers = listOf("Case", "Masculine hard adj. ending — examples"),
                rows = listOf(
                    listOf("Genitive (-ého)", "nového, dobrého"),
                    listOf("Dative (-ému)", "novému, dobrému"),
                    listOf("Locative (-ém)", "novém, dobrém"),
                    listOf("Instrumental (-ým)", "novým, dobrým")
                ),
                weights = listOf(1f, 1.2f)
            )
            QSNote("Koho se bojíš? (Gen) → bojím se starého muže. Komu to dáš? (Dat) → mladému studentovi. O kom mluvíte? (Loc) → o novém učiteli. S kým jdeš? (Ins) → s dobrým přítelem.")

            QSSection("Time Questions")
            QSRow("Kdy?", "When?")
            QSRow("Od kdy?", "From when? / Since when?")
            QSRow("Do kdy?", "Until when? / By when?")
            QSRow("Na jak dlouho?", "For how long?")
            QSRow("Od kdy do kdy?", "From when until when?")

            QSSection("Examples")
            QSExample("Odkud jsi?", "Where are you from?")
            QSExample("Kam jdeš?", "Where are you going? (on foot)")
            QSExample("Kam jedeš?", "Where are you going? (by vehicle)")
            QSExample("Co děláš?", "What are you doing?")
            QSExample("Proč to děláš?", "Why are you doing that?")
            QSExample("Jak se jmenuješ?", "What is your name?")
            QSExample("Kolik to stojí?", "How much does it cost?")
            QSExample("Kdy přijdeš?", "When will you come?")
            QSExample("S kým jdeš?", "Who are you going with?")
            QSExample("Komu to dáš?", "Who will you give it to?")
            QSExample("Pro koho je to?", "Who is it for?")
            QSExample("Na jak dlouho jdeš?", "For how long are you going?")

            QSSection("At What Time (V kolik hodin?)")
            QSRow("V kolik hodin?", "At what time? / At what hour?")
            QSNote("'v' (at) + kolik (how many) + hodin (hours, genitive plural). Asks for a specific clock time. 'v' becomes 've' before consonant clusters.")
            QSExample("V kolik hodin odjíždí vlak?", "At what time does the train leave?")
            QSExample("V kolik hodin odlétá letadlo?", "At what time does the plane depart?")
            QSExample("V kolik hodin začíná film?", "At what time does the film start?")
            QSExample("V osm hodin.", "At eight o'clock.")
            QSExample("Ve tři hodiny.", "At three o'clock. ('v' → 've' before 'tř')")

            QSSection("What Kind of (Jaký / Jaká / Jaké)")
            QSNote("Asks about the nature or type of something. Agrees with noun gender: jaký (m.), jaká (f.), jaké (n.).")
            QSRow("Jaký dort to je?", "What kind of cake is it? (dort = m.)")
            QSRow("Jaké auto to je?", "What kind of car is it? (auto = n.)")
            QSRow("Jaká polévka to je?", "What kind of soup is it? (polévka = f.)")
            QSRow("Jaká je to restaurace?", "What kind of restaurant is it? / What is the restaurant like?")
            QSNote("Jaký can also ask about quality: Jaké to bylo? = How was it?")

            QSSection("Which (Který / Která / Které)")
            QSNote("Selects from a known or visible set. Agrees with noun gender: který (m.), která (f.), které (n.).")
            QSRow("Které auto je vaše?", "Which car is yours? (auto = n.)")
            QSRow("Které z těch aut je vaše?", "Which of those cars is yours? (aut = genitive plural)")
            QSRow("Který kabát si vezmeš?", "Which coat will you take? (kabát = m.)")
            QSRow("Která ulice je to?", "Which street is it? (ulice = f.)")
            QSNote("Distinction: Jaký asks 'what kind/type?'; Který asks 'which specific one from a set?'")

            QSSection("Questions with -pak — Curiosity, Surprise and Wonder")
            QSNote("Adding -pak directly to a question word gives it a colloquial, expressive tone — conveying curiosity, mild surprise, wonder, or gentle reproach. These forms are informal and primarily spoken.")
            QSNote("Important: copak (one word) = surprise/wonder. Co pak (two words) = 'what then? / what next?' — different meaning!")
            QSRow("Copak?", "What on earth? / What's this? (surprise or mild reproach)")
            QSRow("Kdopak?", "Who (I wonder)? / Who on earth?")
            QSRow("Kdepak?", "1. Where on earth? (place question)   OR   2. Of course not! / No way! (dismissal)")
            QSRow("Kdypak?", "When (I wonder)?")
            QSRow("Kampak?", "Where to (I wonder)? (directional — from kam)")
            QSRow("Jakpak?", "How (I wonder)?")
            QSRow("Jakýpak?", "What kind of (I wonder)? (masculine; declines like an adjective)")
            QSNote("Kdepak used as dismissal: Ale kdepak! = Absolutely not! / No chance! — completely different from the place question.")
            QSExample("Kdopak to je?", "Who on earth is that? / I wonder who that is.")
            QSExample("Copak to je?", "What on earth is this?")
            QSExample("Kampak jdeš?", "Where are you off to then?")
            QSExample("Kdypak vy poletíte?", "So when are you flying? / I wonder when you're flying.")
            QSExample("Jakpak asi skončí ten zápas.", "I wonder how that match will end. (not a question — note the period)")
            QSExample("Ale kdepak, nemám čas.", "No way, I don't have time. / Absolutely not.")

            QSSection("Softening Requests — Nevíš / Nemohl bys")
            QSNote("A very common Czech politeness strategy is using a negative verb form to soften a question. It signals you're prepared for 'no,' reducing the imposition on the listener.")
            QSRow("Nevíš...?", "Do you (happen to) know...? (softer than Víš...?)")
            QSRow("Nemohl bys...?", "Couldn't you...? (polite request — masculine speaker)")
            QSRow("Nemohla bys...?", "Couldn't you...? (polite request — feminine speaker)")
            QSRow("Neviděl/a jsi...?", "Did you (happen to) see...?")
            QSRow("Nechcete si sednout?", "Won't you sit down? (polite offer)")
            QSRow("Nemáte náhodou...?", "Don't you happen to have...?")
            QSNote("náhodou (by chance / perhaps) adds extra softening. Nemáte náhodou papír a tužku? = Do you happen to have paper and a pencil?")
            QSExample("Víš, kde je nádraží?", "Do you know where the train station is?")
            QSExample("Nevíš, kde je nádraží?", "Do you happen to know where the train station is? (softer, more polite)")
            QSExample("Mohl bys mi pomoci?", "Could you help me?")
            QSExample("Nemohl bys mi pomoci?", "Couldn't you help me? (more polite — signals you're OK with 'no')")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun QSSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun QSRow(czech: String, english: String) {
    Spacer(modifier = Modifier.height(5.dp))
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
}

@Composable
private fun QSNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

@Composable
private fun QSExample(czech: String, english: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = czech, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    Text(
        text = english,
        fontSize = 14.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(start = 4.dp, top = 1.dp)
    )
}

@Composable
private fun QSTable(headers: List<String>, rows: List<List<String>>, weights: List<Float>) {
    Spacer(modifier = Modifier.height(6.dp))
    Row(modifier = Modifier.fillMaxWidth()) {
        headers.forEachIndexed { i, h ->
            Text(
                text = h,
                modifier = Modifier.weight(weights[i]),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = ButtonBlue
            )
        }
    }
    HorizontalDivider(thickness = 1.dp, color = ButtonBlue)
    rows.forEach { row ->
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)) {
            row.forEachIndexed { i, cell ->
                Text(
                    text = cell,
                    modifier = Modifier.weight(weights[i]),
                    fontSize = 13.sp,
                    color = Color.Black
                )
            }
        }
        HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
    }
    Spacer(modifier = Modifier.height(4.dp))
}
