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
fun TimeRelatedExpressionsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Time Related Expressions", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            TMXSection("Moments")
            TMXRow("chvíle", "a moment / a while", "the basic word. Common forms: chvíli (accusative) — Počkej chvíli. = Wait a moment.")
            TMXRow("chvilka", "a short moment / a little while", "the diminutive of chvíle (-ka ending, like kniha → knížka). Same meaning, but sounds shorter, softer and more conversational — chvilku is what you'll hear most in everyday speech.")
            TMXRow("okamžik / moment", "an instant / a moment", "okamžik is a split second. Okamžik! / Moment! on its own = \"Just a moment!\"")
            TMXRow("Máš chvilku? / Máte chvilku?", "Do you have a moment? (informal / formal)", "yes — this is exactly how you ask. Máš chvíli? also works, but chvilku is the usual, more polite-sounding choice.")
            TMXRow("Máš chvilku na kafe?", "Do you have a moment for a coffee?", "na + accusative = a moment for something.")
            TMXRow("Počkej chvilku. / Počkejte chvilku.", "Wait a moment. (informal / formal)")
            TMXRow("za chvíli / za chvilku", "in a moment / shortly")
            TMXRow("na chvíli / na chvilku", "for a while / for a moment", "Můžu si sednout na chvilku? = Can I sit down for a moment?")
            TMXRow("před chvílí / před chvilkou", "a moment ago")

            TMXSection("Units of Time")
            TMXRow("vteřina / sekunda", "a second", "both are used; vteřina is the more traditional Czech word.")
            TMXRow("minuta", "a minute")
            TMXRow("hodina", "an hour")
            TMXRow("den", "a day")
            TMXRow("týden", "a week")
            TMXRow("měsíc", "a month")
            TMXRow("rok", "a year")
            TMXRow("desetiletí / dekáda", "a decade", "desetiletí (literally \"ten-years\") is the standard word; dekáda is also used.")

            TMXSection("The Past")
            TMXRow("právě / zrovna", "just (a moment ago)", "Právě se to stalo před pár minutami. = It just happened a few minutes ago. Zrovna is a bit more colloquial.")
            TMXRow("nedávno", "recently")
            TMXRow("včera", "yesterday")
            TMXRow("před měsícem", "a month ago", "před + instrumental = ago.")
            TMXRow("před rokem", "a year ago")
            TMXRow("před pár lety / před několika lety", "a few years ago")
            TMXRow("(už) dávno / před dlouhou dobou", "a long time ago", "To bylo už dávno. = That was a long time ago.")
            TMXRow("v minulosti", "in the past")
            TMXRow("když jsem byl malý / když jsem byla malá", "when I was little", "byl malý = a male speaker, byla malá = a female speaker.")

            TMXSection("The Future")
            TMXRow("okamžitě / hned", "immediately / right away", "hned is the everyday word (Hned jsem tam! = I'll be right there!); okamžitě is stronger — \"this very instant.\"")
            TMXRow("brzy / brzo", "soon", "brzo is slightly more colloquial.")
            TMXRow("za chvíli / zakrátko", "shortly / in a little while")
            TMXRow("zítra", "tomorrow")
            TMXRow("za týden", "in a week", "za + accusative = in (a period of time from now).")
            TMXRow("za měsíc", "in a month")
            TMXRow("za rok", "in a year")
            TMXRow("za pár let / za několik let", "in a few years")
            TMXRow("v budoucnu", "in the future", "\"See you in the future\" → Uvidíme se (někdy) v budoucnu is understood but sounds literal. More natural goodbyes: Tak zase někdy! (Until some other time!) / Uvidíme se! (See you!) / Tak někdy příště. (Next time, then.)")
            TMXRow("někdy", "sometime", "Zavolej mi někdy. = Call me sometime. Careful — někdy also means \"sometimes\" (see How Often).")
            TMXRow("jednou / jednoho dne", "someday / one day", "Jednou to pochopíš. = Someday you'll understand. Also: někdy v budoucnu = sometime in the future.")

            TMXSection("How Long")
            TMXRow("krátce", "briefly (for a short time)", "Krátce jsme se viděli. = We saw each other briefly.")
            TMXRow("stručně", "briefly (concisely, in few words)", "Řekni to stručně. = Say it briefly. krátce is about time, stručně is about how much you say.")
            TMXRow("dlouho / dlouhou dobu", "a long time / for a long time", "Čekal jsem dlouho. = I waited a long time.")
            TMXRow("Trvá to dlouho.", "It takes a long time.")
            TMXRow("Bude to trvat dlouho.", "It will take a long time.")

            TMXSection("How Often")
            TMXRow("vždy / vždycky", "always", "vždycky is the everyday spoken form.")
            TMXRow("obvykle / většinou / zpravidla", "usually", "většinou = mostly; zpravidla = as a rule.")
            TMXRow("často", "often")
            TMXRow("někdy", "sometimes")
            TMXRow("občas / čas od času", "occasionally / from time to time", "příležitostně also means \"occasionally\" but sounds formal.")
            TMXRow("zřídka / málokdy", "rarely / seldom")
            TMXRow("nikdy", "never", "Czech uses double negation: Nikdy jsem tam nebyl. = I've never been there (literally \"never I wasn't there\").")
            TMXRow("každý den", "every day")
            TMXRow("obden / každý druhý den", "every other day", "ob- means \"skipping one\": obden = ob + den.")
            TMXRow("ob týden / každý druhý týden", "every other week")

            TMXSection("Before, After, During, Until")
            TMXRow("po + locative", "after (a noun)", "po obědě = after lunch, po práci = after work.")
            TMXRow("poté, co / až", "after (+ a clause)", "Poté, co jsem uklidil, zavolal jsem jí. = After I cleaned up, I called her. Use až for the future: Až přijdeš, zavolej. = When/after you arrive, call.")
            TMXRow("před + instrumental", "before (a noun)", "před obědem = before lunch.")
            TMXRow("než", "before (+ a clause)", "Než odejdeš, zamkni. = Before you leave, lock up.")
            TMXRow("během + genitive / za + genitive", "during", "během dne = during the day; za války = during the war.")
            TMXRow("zatímco / když", "while", "Zatímco jsem se sprchoval, zazvonil telefon. = While I was showering, the phone rang.")
            TMXRow("do + genitive", "until (a time)", "do pátku = until (by) Friday, do pěti = until five.")
            TMXRow("dokud … ne / až", "until (+ a clause)", "Počkám, dokud nepřijdeš. = I'll wait until you come (the verb takes ne-). Also: Počkám, až přijdeš.")
            TMXRow("mezi + instrumental", "between", "mezi pátou a šestou = between five and six.")
            TMXRow("ne dříve než / nejdříve", "not before / at the earliest", "nejdříve v pondělí = not before Monday.")
            TMXRow("pět pracovních dnů / dní", "5 working days", "both genitive plurals (dnů and dní) are correct.")
            TMXNote("For the full grammar of joining sentences with než, poté co, zatímco, and dokud, see Learning The Language → Connecting Sentences.")

            TMXSection("Calendar")
            TMXRow("víkend", "the weekend", "o víkendu = on/at the weekend.")
            TMXRow("hospodářský rok", "fiscal year", "the official accounting term for a year not starting in January. fiskální rok is also heard (mostly about state budgets); účetní období = accounting period.")
            TMXRow("letní čas", "daylight saving time (summer time)", "přechod na letní čas = the switch to summer time. The clocks change on the last Sunday in March and October.")
            TMXRow("zimní čas", "winter time (standard time)", "officially středoevropský čas (SEČ) = Central European Time.")

            TMXSection("Parts of the Day")
            TMXRow("ráno", "morning / in the morning")
            TMXRow("dopoledne", "late morning / before noon")
            TMXRow("poledne", "noon / midday", "the same word for both. v poledne = at noon.")
            TMXRow("odpoledne", "afternoon / in the afternoon")
            TMXRow("večer", "evening / in the evening")
            TMXRow("noc", "night", "v noci = at night.")
            TMXRow("půlnoc", "midnight", "o půlnoci = at midnight.")
            TMXNote("ráno, dopoledne, odpoledne and večer double as adverbs: Ráno piju kávu. = In the morning I drink coffee.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun TMXSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun TMXRow(czech: String, english: String, note: String = "") {
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

@Composable
private fun TMXNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontStyle = FontStyle.Italic,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 6.dp)
    )
}
