package com.example.czechappredo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
fun OneTimeVsHabitScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("One Time vs Habit", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // 1. Big picture
            OTHSection("The Big Picture — Three Layers")
            OTHNote("Czech can describe the same action in three ways, depending on whether it happened once and was completed, was ongoing/repeated, or was a habit (\"used to\", \"tends to\").")
            OTHTable(
                header = listOf("Kind", "Verb", "Example"),
                rows = listOf(
                    listOf("Perfective — one time, completed", "udělat", "Udělal jsem to. — I did it (done)."),
                    listOf("Imperfective — ongoing / repeated / general", "dělat", "Dělal jsem to. — I was doing it / I did it (regularly)."),
                    listOf("Iterative -ávat / -ívat — habit, \"used to\"", "dělávat", "Dělával jsem to. — I used to do it (from time to time).")
                )
            )
            OTHNote("Iterative verbs (Czech: iterativa, násobená or opakovací slovesa) are always imperfective. They are a \"habitual\" sub-type of the imperfective, not a separate aspect.")

            // 2. Spelling
            OTHSection("Spelling Check")
            OTHNote("The iterative endings are written with a LONG vowel: -ávat and -ívat (and -ývat after a hard consonant: být → bývat). Short -avat / -ivat is wrong for these verbs.")
            OTHRow("dělat → dělávat", "to do → to used to do / tend to do",
                example = "not \"delat / delavat\"", translation = "ě in dělat, long á in -ávat")
            OTHRow("jít → chodit → chodívat", "to go (one trip) → to go (regularly) → used to go",
                example = "not \"jit / chodivat\"", translation = "long í in jít and in -ívat")

            // 3. dělat vs dělávat
            OTHSection("dělat vs dělávat")
            OTHRow("dělat", "to do / make — ongoing, repeated or general",
                example = "Teď dělám úkoly.", translation = "I'm doing homework now.")
            OTHRow("dělat", "repeated, neutral",
                example = "Každý den dělám úkoly.", translation = "I do homework every day.")
            OTHRow("udělat (perfective)", "one time, completed",
                example = "Udělal jsem úkoly.", translation = "I did (finished) my homework.")
            OTHRow("dělat (past)", "ongoing in the past",
                example = "Dělal jsem úkoly celý večer.", translation = "I was doing homework all evening.")
            OTHRow("dělávat (past)", "a habit in the past — \"used to\"",
                example = "Jako dítě jsem dělával úkoly u babičky.", translation = "As a kid I used to do my homework at grandma's.")
            OTHRow("dělávat (past)", "\"used to\" + colloquial dělat = to work",
                example = "Táta dělával v továrně.", translation = "Dad used to work in a factory.")
            OTHRow("dělávat (past)", "tradition",
                example = "Tohle dělávali už naši dědové.", translation = "Even our grandfathers used to do this.")
            OTHRow("dělávat (present)", "\"usually, as a rule\" — sounds bookish",
                example = "Co děláváš o víkendech?", translation = "What do you usually do on weekends?")
            OTHNote("In everyday speech, \"Co děláš o víkendech?\" is the normal way to ask this. Plain dělat already covers repeated actions; dělávat just adds the \"habit / used to\" colour.")
            OTHTable(
                header = listOf("", "dělávat — present", "past"),
                rows = listOf(
                    listOf("já", "dělávám", "dělával / dělávala jsem"),
                    listOf("ty", "děláváš", "dělával / dělávala jsi"),
                    listOf("on / ona / to", "dělává", "dělával / dělávala / dělávalo"),
                    listOf("my", "děláváme", "dělávali jsme"),
                    listOf("vy", "děláváte", "dělávali jste"),
                    listOf("oni", "dělávají", "dělávali")
                )
            )
            OTHNote("All -ávat / -ívat iteratives conjugate like dělat: -ám, -áš, -á, -áme, -áte, -ají.")

            // 4. More iteratives
            OTHSection("More Iteratives")
            OTHRow("být → bývat", "to be → to tend to be / used to be",
                example = "V pondělí bývá zavřeno. · Dřív tady bývala hospoda.", translation = "It's usually closed on Mondays. · There used to be a pub here.")
            OTHRow("mít → mívat", "to have → to tend to have / used to have",
                example = "Večer mívám hlad. · Mívali jsme psa.", translation = "I tend to get hungry in the evening. · We used to have a dog.")
            OTHRow("vidět → vídat", "to see → to see (regularly, now and then)",
                example = "Vídáme se jen o Vánocích.", translation = "We only see each other at Christmas.")
            OTHRow("slyšet → slýchat", "to hear → to hear (often)",
                example = "To jsem slýchal od dědy pořád.", translation = "I used to hear that from grandpa all the time.")
            OTHRow("jíst → jídat", "to eat → to eat (habitually)",
                example = "V neděli jsme jídali u babičky.", translation = "On Sundays we used to eat at grandma's.")
            OTHRow("spát → spávat", "to sleep → to (usually) sleep",
                example = "V létě spávám s otevřeným oknem.", translation = "In summer I (usually) sleep with the window open.")
            OTHRow("psát → psávat", "to write → used to write",
                example = "Psávali jsme si dopisy.", translation = "We used to write each other letters.")
            OTHRow("říkat → říkávat", "to say → used to say",
                example = "Babička říkávala: „Bez práce nejsou koláče.“", translation = "Grandma used to say: \"No pain, no gain.\"")
            OTHRow("sedět → sedávat", "to sit → used to sit",
                example = "Sedával vždycky u okna.", translation = "He always used to sit by the window.")
            OTHRow("hrát → hrávat", "to play → used to play",
                example = "Hrávali jsme fotbal na ulici.", translation = "We used to play football in the street.")
            OTHRow("nosit → nosívat", "to carry / wear → used to wear",
                example = "Děda nosíval klobouk.", translation = "Grandpa used to wear a hat.")
            OTHRow("jezdit → jezdívat", "to go by vehicle (regularly) → used to go",
                example = "Jezdívali jsme na chatu.", translation = "We used to go to the cottage.")

            // 5. When used
            OTHSection("When Are Iteratives Used?")
            OTHNote("• Mostly in the PAST, for \"used to\", often with a warm, nostalgic, \"back then\" feel: Jako děti jsme hrávali na schovávanou. (As kids we used to play hide-and-seek.)")
            OTHNote("• In the PRESENT for \"tends to / usually / as a rule\", especially bývá and mívá: Tady bývá hodně lidí. (There are usually a lot of people here.) · Mívám migrény. (I get migraines from time to time.)")
            OTHNote("• Almost never in the future.")
            OTHNote("• More typical of written Czech than of speech, and corpus studies show they are slowly getting rarer. You'll meet them in books and hear older people use them. Plain imperfectives (dělat, chodit) + words like vždycky, obvykle, často, dřív are the everyday alternative: Dřív jsem tam chodil často.")
            OTHNote("• Rare \"double\" iteratives also exist, for something that was so a long time ago: bývávalo (it used to be so, once upon a time), dávávat. You'll recognise them; you don't need to produce them.")

            // 6. Motion quick review
            OTHSection("Quick Review — Verbs of Movement")
            OTHNote("Verbs of movement have their own one-trip vs habit split, built into separate verbs. Both verbs of each pair are imperfective. Full conjugations are on the Verbs Of Movement screen.")
            OTHTable(
                header = listOf("One trip, one direction", "Regular / habit / round trips", "Meaning"),
                rows = listOf(
                    listOf("jít", "chodit", "go on foot"),
                    listOf("jet", "jezdit", "go by vehicle, ride"),
                    listOf("běžet", "běhat", "run"),
                    listOf("letět", "létat (spoken lítat)", "fly"),
                    listOf("nést", "nosit", "carry (on foot); nosit also = wear"),
                    listOf("vést", "vodit", "lead, take (someone)"),
                    listOf("vézt", "vozit", "carry / drive (by vehicle)")
                )
            )
            OTHRow("běžet / běhat", "",
                example = "Běžím na autobus. · Běhám každé ráno.", translation = "I'm running for the bus. · I run every morning.")
            OTHRow("letět / létat", "",
                example = "Zítra letím do Londýna. · Často létám služebně.", translation = "Tomorrow I'm flying to London. · I often fly on business.")
            OTHRow("nést / nosit", "",
                example = "Nesu tašku. · Nosím brýle.", translation = "I'm carrying a bag. · I wear glasses.")
            OTHRow("vést / vodit", "",
                example = "Vedu psa na procházku. · Vodím děti do školy.", translation = "I'm taking the dog for a walk. · I take the kids to school (on foot).")
            OTHRow("vézt / vozit", "",
                example = "Vezu dítě k doktorovi. · Vozím děti do školy autem.", translation = "I'm driving my child to the doctor. · I drive the kids to school.")

            // 7. jít vs chodit vs chodívat
            OTHSection("jít vs chodit vs chodívat")
            OTHNote("jít = one directed trip, happening now or about to happen. chodit = regular / repeated trips, round trips, or moving around without one direction. chodívat = the iterative of chodit: \"used to go / tend to go\".")
            OTHRow("Present — jít", "on my way, now",
                example = "Jdu do školy.", translation = "I'm going (on my way) to school.")
            OTHRow("Present — chodit", "regularly / attend",
                example = "Chodím do školy.", translation = "I go to school (I'm a pupil / I go there regularly).")
            OTHRow("Present — chodívat", "as a rule (bookish)",
                example = "V neděli tam chodívám na oběd.", translation = "On Sundays I usually go there for lunch.")
            OTHRow("Past — jít", "one trip",
                example = "Včera jsem šel do kina.", translation = "Yesterday I went to the cinema.")
            OTHRow("Past — chodit", "regularly, or around",
                example = "Chodil jsem do kina každý týden. · Celý den jsem chodil po městě.", translation = "I went to the cinema every week. · I walked around the city all day.")
            OTHRow("Past — chodívat", "used to (nostalgic)",
                example = "Jako student jsem chodíval do kina každý týden.", translation = "As a student I used to go to the cinema every week.")
            OTHRow("Future — jít", "one trip: půjdu (NOT \"budu jít\")",
                example = "Zítra půjdu k doktorovi.", translation = "Tomorrow I'll go to the doctor.")
            OTHRow("Future — chodit", "regularly: budu chodit",
                example = "Od září budu chodit do práce pěšky.", translation = "From September I'll be walking to work.")

            OTHSection("Other Uses of chodit")
            OTHRow("ability", "",
                example = "Malý už chodí.", translation = "The little one can already walk.")
            OTHRow("back and forth / around", "",
                example = "Chodil po pokoji sem a tam.", translation = "He paced back and forth around the room.")
            OTHRow("chodit s někým", "to date someone",
                example = "Chodí spolu dva roky.", translation = "They've been dating for two years.")
            OTHRow("negative imperative", "\"don't go\" uses chodit",
                example = "Jdi domů! · Nechoď tam!", translation = "Go home! · Don't go there!")

            OTHSection("The Same Pattern With jet / jezdit / jezdívat")
            OTHRow("jet", "one trip — future pojedu",
                example = "Jedu do Brna. · Zítra pojedu vlakem.", translation = "I'm going to Brno. · Tomorrow I'll go by train.")
            OTHRow("jezdit", "regularly, or riding around",
                example = "Jezdím do Brna každý týden. · Jezdí na kole po městě.", translation = "I go to Brno every week. · He rides his bike around town.")
            OTHRow("jezdívat", "used to",
                example = "V létě jsme jezdívali k moři.", translation = "In summer we used to go to the seaside.")

            // 8. -ávat -ívat -ovat
            OTHSection("-ávat, -ívat, -ovat: Are They Related?")
            OTHNote("Partly. Very similar-looking endings appear in three DIFFERENT processes, and only the first one means \"habit\".")
            OTHSub("1. Iteratives — imperfective → habit / \"used to\"")
            OTHNote("Built from an unprefixed imperfective verb with -ávat / -ívat (-ývat):")
            OTHNote("dělat → dělávat · chodit → chodívat · být → bývat · mít → mívat · psát → psávat · hrát → hrávat · vidět → vídat · jíst → jídat")
            OTHSub("2. Secondary imperfectives — perfective → ordinary imperfective (NOT a habit)")
            OTHNote("Many perfectives (often prefixed) get a normal imperfective partner with -ávat, -ívat, -ovat, -ínat or -cházet. These are everyday verbs with no \"used to\" meaning:")
            OTHRow("dát → dávat", "to give",
                example = "Dávám ti to. ≠ \"I used to give\"", translation = "I'm giving it to you.")
            OTHRow("prodat → prodávat", "to sell",
                example = "Prodávají tu ovoce.", translation = "They sell fruit here.")
            OTHRow("vstát → vstávat", "to get up",
                example = "Vstávám v šest.", translation = "I get up at six.")
            OTHRow("vydělat → vydělávat", "to earn",
                example = "Kolik vyděláváš?", translation = "How much do you earn?")
            OTHRow("odpočinout si → odpočívat (si)", "to rest",
                example = "Odpočívám.", translation = "I'm resting.")
            OTHRow("zapomenout → zapomínat", "to forget",
                example = "Pořád zapomínám klíče.", translation = "I keep forgetting my keys.")
            OTHRow("přepsat → přepisovat · podepsat → podepisovat", "to rewrite · to sign",
                example = "Právě podepisuju smlouvu.", translation = "I'm signing the contract right now.")
            OTHRow("koupit → kupovat", "to buy",
                example = "Chleba kupuju každý den.", translation = "I buy bread every day.")
            OTHRow("ukázat → ukazovat", "to show",
                example = "Ukazuje nám cestu.", translation = "He's showing us the way.")
            OTHNote("⚠ dávat is NOT \"used to give\" — it's simply the imperfective of dát. Its iterative is the rare dávávat.")
            OTHSub("3. -ovat as a plain verb class — no aspect meaning at all")
            OTHNote("pracovat (work), studovat (study), milovat (love), potřebovat (need), telefonovat (phone), fotografovat (photograph). These are just verbs that happen to end in -ovat (conjugated -uju / -uje). They are not habitual and not derived from anything.")
            OTHSub("How to Tell Them Apart")
            OTHNote("• Unprefixed imperfective base + -ávat / -ívat with a long vowel (dělat → dělávat) → iterative = habit / used to.")
            OTHNote("• A perfective (usually prefixed) base + -ávat / -ívat / -ovat (prodat → prodávat, přepsat → přepisovat) → ordinary imperfective.")
            OTHNote("• Not derived from anything (pracovat, studovat) → just a verb class.")

            // 9. Perfective vs imperfective
            OTHSection("Is This Perfective vs Imperfective?")
            OTHNote("Related, but it's a separate layer. Perfective vs imperfective = completed one-time action vs ongoing/repeated. The iterative is an extra \"habit\" layer on top of the imperfective side. jít vs chodit is yet another axis: one direction vs many/habit — and BOTH are imperfective.")
            OTHRow("perfective (one time, completed)", "present form = future meaning",
                example = "Udělám to zítra. · Přijdu v pět.", translation = "I'll do it tomorrow. · I'll come at five.")
            OTHRow("jít → přijít (pf) → přicházet (impf)", "to come / arrive",
                example = "Přišel pozdě. · Vždycky přichází pozdě.", translation = "He arrived late (once). · He always arrives late.")
            OTHRow("jít → odejít (pf) → odcházet (impf)", "to leave",
                example = "Odešel v osm. · Už odcházím.", translation = "He left at eight. · I'm leaving now.")
            OTHRow("jít → vyjít (pf) → vycházet (impf)", "to go out / rise",
                example = "Vyšli jsme ven. · Slunce vychází v šest.", translation = "We went outside. · The sun rises at six.")
            OTHNote("Prefixed forms of jít are perfective and get their imperfective partner from -cházet (related to chodit). The future of plain jít is půjdu.")
            OTHNote("⚠ pojď / pojďte = \"come (on)!\" (imperative), but the infinitive pojít means \"to die\" — of animals (Pes pošel. = The dog died).")
            OTHNote("You can't make an iterative straight from a perfective: there is no \"habit\" form of udělat. You go through the imperfective: udělat → dělat → dělávat. See also Prefixes and Perfective Verbs.")

            // 10. Q&A
            OTHSection("Your Questions Answered")
            OTHQA("Is dělat vs dělávat one time vs habit?",
                "✔ Mostly. dělávat = habit / \"used to\". But plain dělat is not \"one time\" — it's ongoing or repeated. The one-time, completed verb is the perfective udělat.")
            OTHQA("Are -ávat, -ívat, -ovat related?",
                "Partly. -ávat / -ívat (long á / í) make iteratives from imperfectives, but the same endings plus -ovat also make ordinary imperfectives from perfectives (dát → dávat, koupit → kupovat), and many -ovat verbs are just a verb class (pracovat).")
            OTHQA("Is there a chodívat?",
                "✔ Yes — chodit → chodívat: \"used to go / tend to go\". Chodíval k nám často. = He used to visit us often.")
            OTHQA("Is chodit the same as chodívat?",
                "✘ Not quite. chodit is already the normal \"regular / habit\" verb. chodívat adds \"used to / as a rule\", is mostly past tense, and sounds more bookish or nostalgic.")
            OTHQA("Is chodit one time or repeated?",
                "Repeated / habitual (or walking around, round trips, ability to walk, dating). The single directed trip is jít.")
            OTHQA("Is this related to perfective vs imperfective?",
                "Yes, as a layer on the imperfective side: iteratives are always imperfective. jít and chodit are both imperfective; their perfectives come from prefixes (přijít, odejít) and the -cházet verbs are the imperfective partners.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun OTHSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun OTHSub(text: String) {
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = text, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    Spacer(modifier = Modifier.height(2.dp))
}

@Composable
private fun OTHRow(term: String, meaning: String, example: String, translation: String) {
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)) {
                append(term)
            }
            if (meaning.isNotEmpty()) {
                withStyle(SpanStyle(fontSize = 15.sp, color = Color.DarkGray)) {
                    append("  —  $meaning")
                }
            }
        }
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
        modifier = Modifier.padding(top = 2.dp, start = 4.dp)
    )
}

@Composable
private fun OTHNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

@Composable
private fun OTHQA(question: String, answer: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = question, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    Text(text = answer, fontSize = 14.sp, color = Color.DarkGray, modifier = Modifier.padding(start = 4.dp, top = 2.dp))
}

@Composable
private fun OTHTable(header: List<String>, rows: List<List<String>>) {
    Spacer(modifier = Modifier.height(6.dp))
    Column(modifier = Modifier.fillMaxWidth().border(1.dp, Color.LightGray)) {
        Row(modifier = Modifier.fillMaxWidth().background(ButtonBlue)) {
            header.forEach { h ->
                Text(
                    text = h,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.weight(1f).padding(6.dp)
                )
            }
        }
        rows.forEachIndexed { i, row ->
            Row(modifier = Modifier.fillMaxWidth().background(if (i % 2 == 0) Color.White else Color(0xFFF2F6FB))) {
                row.forEachIndexed { j, cell ->
                    Text(
                        text = cell,
                        fontSize = 13.sp,
                        fontWeight = if (j == 0) FontWeight.Bold else FontWeight.Normal,
                        color = Color.Black,
                        modifier = Modifier.weight(1f).padding(6.dp)
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(6.dp))
}
