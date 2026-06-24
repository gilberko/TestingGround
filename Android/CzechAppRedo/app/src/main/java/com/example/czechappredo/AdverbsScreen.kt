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
fun AdverbsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adverbs", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            ADVSection("What Are Adverbs")
            ADVNote("Adverbs modify verbs, adjectives, or other adverbs. They describe how, when, where, or to what degree something happens. Unlike adjectives, adverbs do not change form — they are invariable in Czech.")
            ADVNote("She speaks well.  —  modifies a verb (speaks)")
            ADVNote("He is very tall.  —  modifies an adjective (tall)")
            ADVNote("She runs very quickly.  —  modifies another adverb (quickly)")

            ADVSection("Forming Adverbs from Adjectives")
            ADVNote("Many Czech adverbs are formed from adjectives by changing the ending.")
            ADVNote("Hard adjectives (-ý / -á / -é): change to -e or -ě")
            ADVRow("dobrý", "good", "→ dobře (well)")
            ADVRow("rychlý", "fast", "→ rychle (quickly)")
            ADVRow("tichý", "quiet", "→ tiše (quietly)")
            ADVRow("hlasitý", "loud", "→ hlasitě (loudly)")
            ADVRow("opatrný", "careful", "→ opatrně (carefully)")
            ADVNote("Some are irregular: pomalý (slow) → pomalu (slowly)")
            ADVNote("Soft adjectives (-í): add -e  →  moderní (modern) → moderně (in a modern way)")

            ADVSection("Manner Adverbs — Jak? (How?)")
            ADVRow("dobře", "well")
            ADVRow("špatně", "badly / poorly")
            ADVRow("rychle", "quickly / fast")
            ADVRow("pomalu", "slowly")
            ADVRow("tiše", "quietly")
            ADVRow("hlasitě", "loudly")
            ADVRow("opatrně", "carefully")
            ADVRow("správně", "correctly / properly")
            ADVRow("úplně", "completely / totally")
            ADVRow("unaveně", "tiredly", "← unavený (tired); hard adj. -ný → -ně: unavený → unaveně")

            ADVSection("Time Adverbs — Kdy? (When?)")
            ADVRow("teď / nyní", "now")
            ADVRow("hned", "right away / immediately")
            ADVRow("pak / potom", "then / afterwards")
            ADVRow("dnes", "today")
            ADVRow("včera", "yesterday")
            ADVRow("zítra", "tomorrow")
            ADVRow("brzy", "soon / early")
            ADVRow("pozdě", "late")
            ADVRow("vždy / vždycky", "always")
            ADVRow("nikdy", "never")
            ADVRow("někdy", "sometimes")
            ADVRow("ještě", "still / yet")
            ADVRow("už", "already / anymore")
            ADVRow("znovu / zase", "again")

            ADVSection("Place Adverbs — Kde? (Where?)")
            ADVRow("tady / zde", "here")
            ADVRow("tam", "there")
            ADVRow("blízko", "nearby / close")
            ADVRow("daleko", "far")
            ADVRow("venku", "outside")
            ADVRow("doma", "at home")
            ADVRow("vlevo", "to the left / on the left")
            ADVRow("vpravo", "to the right / on the right")
            ADVRow("nahoru", "upward / up")
            ADVRow("dolů", "downward / down")

            ADVSection("Degree Adverbs — Jak moc? (How much?)")
            ADVRow("velmi / moc", "very / a lot")
            ADVRow("trochu", "a little / a bit")
            ADVRow("dost", "enough / quite")
            ADVRow("příliš", "too / too much")
            ADVRow("skoro / téměř", "almost / nearly")
            ADVRow("jen / pouze", "only / just")
            ADVRow("docela", "quite / pretty")
            ADVRow("spíše", "rather")

            ADVSection("Comparing Adverbs")
            ADVNote("Adverbs can be compared just like adjectives.")
            ADVRow("dobře", "well", "→ lépe / líp (better) → nejlépe / nejlíp (best)")
            ADVRow("špatně", "badly", "→ hůře / hůř (worse) → nejhůře / nejhůř (worst)")
            ADVRow("rychle", "fast", "→ rychleji (faster) → nejrychleji (fastest)")
            ADVRow("pomalu", "slowly", "→ pomaleji (more slowly) → nejpomaleji (most slowly)")

            ADVSection("Adverb Position & Adding \"Very\" / \"A Bit\"")
            ADVNote("The neutral position for a manner adverb is right after the verb it modifies.")
            ADVExample("Mluví dobře.", "He speaks well.")
            ADVExample("Jede rychle.", "He drives fast.")
            ADVNote("Moving the adverb before the verb is also grammatical — it shifts emphasis onto the adverb itself, following the same flexible, emphasis-driven word order that governs the rest of a Czech sentence (see the Word Order In A Sentence screen). Compare the neutral Mluví dobře česky. with the emphatic Dobře mluví česky. — both mean 'He speaks Czech well,' but the second highlights 'well'.")
            ADVNote("The same degree words used with adjectives — velmi / moc / hodně (very), dost (quite), trochu / málo (a bit), příliš (too) — also intensify adverbs, and they go directly before the adverb, not before the verb: [verb] + [degree word] + [adverb].")
            ADVExample("Mluví velmi dobře.", "He speaks very well.")
            ADVExample("Jede dost rychle.", "He's driving quite fast.")
            ADVExample("Pracuje trochu pomalu.", "He works a bit slowly.")

            ADVSection("Examples")
            ADVExample("Mluví dobře česky.", "He speaks Czech well.")
            ADVExample("Přijdeme brzy.", "We will come soon.")
            ADVExample("Bydlím tady.", "I live here.")
            ADVExample("Je velmi chytrý.", "He is very smart.")
            ADVExample("Nikdy nejím maso.", "I never eat meat.")
            ADVExample("Ona běží rychleji než já.", "She runs faster than me.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ADVSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun ADVNote(text: String) {
    Text(text = text, fontSize = 14.sp, color = Color.DarkGray, modifier = Modifier.padding(vertical = 3.dp))
}

@Composable
private fun ADVRow(czech: String, english: String, note: String = "") {
    Row(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)) { append(czech) }
                withStyle(SpanStyle(color = Color.DarkGray)) { append("  —  $english") }
                if (note.isNotEmpty()) {
                    withStyle(SpanStyle(color = Color.Gray, fontStyle = FontStyle.Italic)) { append("  $note") }
                }
            },
            fontSize = 14.sp
        )
    }
}

@Composable
private fun ADVExample(czech: String, english: String) {
    Row(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)) { append(czech) }
                withStyle(SpanStyle(color = Color.Gray, fontStyle = FontStyle.Italic)) { append("  —  $english") }
            },
            fontSize = 14.sp
        )
    }
}
