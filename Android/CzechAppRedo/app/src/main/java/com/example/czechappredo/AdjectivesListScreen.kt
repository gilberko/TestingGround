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
fun AdjectivesListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adjectives", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            ALSection("About These Forms")
            ALNote("Each adjective is shown as: masculine / feminine / neuter. These are the nominative (subject) forms. See Learning → Adjectives for how they change in other cases.")

            ALSection("Colors")
            ALRow("zelený / zelená / zelené", "green")
            ALRow("červený / červená / červené", "red")

            ALSection("Light & Dark")
            ALRow("tmavý / tmavá / tmavé", "dark")
            ALRow("světlý / světlá / světlé", "light / bright", "světlý = light-colored / not dark; refers to hue or tone (světlá místnost = bright/light room)")
            ALRow("jasný / jasná / jasné", "bright / clear", "jasné světlo = bright light; jasná obloha = clear sky; also means obvious: Je to jasné. = It's obvious.")

            ALSection("Size")
            ALRow("malý / malá / malé", "small")
            ALRow("velký / velká / velké", "big / large")
            ALRow("vysoký / vysoká / vysoké", "tall / high")
            ALRow("nízký / nízká / nízké", "short / low", "for height")
            ALRow("tenký / tenká / tenké", "thin / slim", "for objects; štíhlý / štíhlá / štíhlé = slim for people")
            ALRow("tlustý / tlustá / tlusté", "thick / fat", "for objects; silný / silná / silné = strong / thick for materials")

            ALSection("Temperature")
            ALRow("horký / horká / horké", "hot")
            ALRow("studený / studená / studené", "cold", "chladný / chladná / chladné = cool / chilly")

            ALSection("Water")
            ALRow("perlivý / perlivá / perlivé", "sparkling")
            ALRow("neperlivý / neperlivá / neperlivé", "still / non-sparkling", "also: voda bez bublinek = water without bubbles")

            ALSection("Value & Quality")
            ALRow("bohatý / bohatá / bohaté", "rich")
            ALRow("chudý / chudá / chudé", "poor")
            ALRow("drahý / drahá / drahé", "expensive / dear", "drahé dítě = expensive child (sounds funny — children aren't bought!) or dear child (affectionate/literary: moje drahé dítě = my dear child)")
            ALRow("levný / levná / levné", "cheap / affordable")
            ALRow("dobrý / dobrá / dobré", "good")
            ALRow("špatný / špatná / špatné", "bad")
            ALRow("nový / nová / nové", "new")
            ALRow("starý / stará / staré", "old")
            ALRow("mladý / mladá / mladé", "young")
            ALRow("dostupný / dostupná / dostupné", "affordable / accessible", "cenově dostupný = price-affordable; also means available/accessible in general")
            ALRow("efektivní / efektivní / efektivní", "efficient / effective", "soft adjective — same form for all genders in nominative")

            ALSection("Character & Appearance")
            ALRow("laskavý / laskavá / laskavé", "kind / gentle")
            ALRow("hezký / hezká / hezké", "handsome / nice-looking", "for people and things")
            ALRow("pohledný / pohledná / pohledné", "attractive / good-looking", "specifically for people")
            ALRow("krásný / krásná / krásné", "beautiful")

            ALSection("Speed & Reliability")
            ALRow("rychlý / rychlá / rychlé", "fast")
            ALRow("pomalý / pomalá / pomalé", "slow")
            ALRow("spolehlivý / spolehlivá / spolehlivé", "reliable")

            ALSection("Personality & Character")
            ALRow("hodný / hodná / hodné", "kind / well-behaved / good-natured", "also used as \"good\" for children — Buď hodný! = Be good!")
            ALRow("veselý / veselá / veselé", "cheerful / merry")
            ALRow("přísný / přísná / přísné", "strict")
            ALRow("trpělivý / trpělivá / trpělivé", "patient")
            ALRow("netrpělivý / netrpělivá / netrpělivé", "impatient")
            ALRow("impulzivní / impulzivní / impulzivní", "impulsive")
            ALNote("impulzivní is a soft adjective — all three genders share the same nominative form.")

            ALSection("Emotions & Mood")
            ALRow("šťastný / šťastná / šťastné", "happy", "also means \"lucky\" — context determines which")
            ALRow("smutný / smutná / smutné", "sad")
            ALRow("unavený / unavená / unavené", "tired")
            ALRow("rozzlobený / rozzlobená / rozzlobené", "angry / upset", "neutral register; colloquial/stronger alternative: naštvaný / naštvaná / naštvanné")

            ALSection("Style & Modernity")
            ALRow("moderní / moderní / moderní", "modern")
            ALRow("staromódní / staromódní / staromódní", "old-fashioned")
            ALNote("moderní and staromódní are soft adjectives — all three genders share the same nominative form.")

            ALSection("Interest & Complexity")
            ALRow("nudný / nudná / nudné", "boring")
            ALRow("zajímavý / zajímavá / zajímavé", "interesting")
            ALRow("fascinující / fascinující / fascinující", "fascinating")
            ALNote("fascinující is a soft adjective — same form for all three genders in the nominative.")
            ALRow("složitý / složitá / složité", "complicated")
            ALRow("jednoduchý / jednoduchá / jednoduché", "simple / easy")
            ALRow("překvapující / překvapující / překvapující", "surprising", "soft adjective; also: překvapivý/á/é (hard adj, same meaning, slightly more common in writing)")

            ALSection("Comparative Forms")
            ALNote("Comparatives are formed with a -ší or -í suffix and always decline as soft adjectives.")
            ALRow("mladší", "younger", "comparative of mladý")
            ALRow("starší", "older", "comparative of starý")
            ALRow("větší", "bigger", "comparative of velký")
            ALRow("menší", "smaller", "comparative of malý")
            ALRow("rychlejší", "faster", "comparative of rychlý")
            ALRow("pomalejší", "slower", "comparative of pomalý")
            ALNote("Example: Mám mladšího bratra a starší sestru.  —  I have a younger brother and an older sister.")
            ALNote("'mladšího' uses the soft adjective accusative masculine animate ending -ího (→ mladšího bratra). 'starší sestru' — soft adjective feminine accusative is unchanged.")

            ALSection("Time & Order")
            ALRow("poslední / poslední / poslední", "last / the last", "soft adjective; poslední autobus = the last bus; v poslední době = lately (lit. in the last time)")
            ALRow("předchozí / předchozí / předchozí", "previous / preceding", "soft adjective; formal/written style; also: minulý/á/é = previous/last (common in speech: minulý týden = last week)")
            ALRow("minulý / minulá / minulé", "last / past", "most common for 'last' in speech: minulý rok = last year; minulý týden = last week")
            ALRow("příští / příští / příští", "next", "soft adjective; příští týden = next week; příštích 5 let = the next 5 years")
            ALRow("další / další / další", "next / another / further", "soft adjective; next in sequence: další zastávka = the next stop; or another one: Chcete další kávu? = Do you want another coffee?")
            ALRow("starověký / starověká / starověké", "ancient / of antiquity", "from starověk (antiquity, ancient times); starověký Řím = ancient Rome")
            ALRow("prastarý / prastará / prastaré", "very ancient / age-old", "pra- prefix intensifies; prastarý strom = an age-old tree")
            ALRow("vintage / vintage / vintage", "vintage", "loanword, indeclinable — same form for all genders and cases; also: retro; starožitný = antique (specifically old collectibles)")

            ALSection("Funny, Weird & Unbelievable")
            ALNote("Czech has distinct words for different shades of 'funny' and 'weird'.")
            ALRow("divný / divná / divné", "weird / odd / strange", "most common in everyday speech: To je divné. = That's weird.")
            ALRow("zvláštní / zvláštní / zvláštní", "strange / peculiar / special", "soft adjective; slightly more formal than divný; also means 'special': zvláštní případ = special case")
            ALRow("vtipný / vtipná / vtipné", "funny / witty", "from vtip (joke); implies intentional humor")
            ALRow("legrační / legrační / legrační", "funny / comical / amusing", "soft adjective; from legranda (fun); often for situations or behavior: legrační situace = funny situation")
            ALRow("srandovní / srandovní / srandovní", "hilarious / very funny", "soft adjective; colloquial, from sranda (fun/laugh): To bylo srandovní! = That was hilarious!")
            ALRow("neuvěřitelný / neuvěřitelná / neuvěřitelné", "unbelievable / incredible", "ne- (un-) + uvěřitelný (believable): To je neuvěřitelné! = That's unbelievable!")

            ALSection("Famous & Known")
            ALRow("slavný / slavná / slavné", "famous / celebrated", "slavný herec = famous actor; also means 'glorious' in historical context")
            ALRow("proslulý / proslulá / proslulé", "renowned / famous for something", "slightly more formal than slavný; proslulý svou kuchyní = famous for his cooking")
            ALRow("neznámý / neznámá / neznámé", "unknown", "ne- + známý (known/familiar); neznámý člověk = unknown/unfamiliar person")
            ALRow("známý / známá / známé", "known / familiar / well-known", "Jsi mi známý. = You look familiar to me. Also a noun: můj známý = my acquaintance")

            ALSection("Intelligence")
            ALRow("chytrý / chytrá / chytré", "smart / clever", "the most common everyday word for 'smart'")
            ALRow("bystrý / bystrá / bystré", "bright / sharp / quick-witted", "implies mental quickness: bystré dítě = bright/quick child")
            ALRow("inteligentní / inteligentní / inteligentní", "intelligent", "soft adjective; loanword; more formal/clinical than chytrý")
            ALRow("hloupý / hloupá / hloupé", "stupid / foolish", "the standard neutral word; colloquial alternative: blbý / blbá / blbé — more informal/rude, use carefully")

            ALSection("Health & Safety")
            ALRow("zdravý / zdravá / zdravé", "healthy", "zdravé jídlo = healthy food; Buď zdráv! = Be well! (formal farewell or after a sneeze)")
            ALRow("jedovatý / jedovatá / jedovaté", "poisonous / toxic", "from jed (poison); jedovatá houba = poisonous mushroom; colloquially also 'venomous/nasty' of a person")
            ALRow("bezlepkový / bezlepková / bezlepkové", "gluten-free", "bez (without) + lepek (gluten) + -ový suffix; bezlepková dieta = gluten-free diet")
            ALRow("alergický / alergická / alergické", "allergic", "Jsem alergický/á na lepek. = I am allergic to gluten.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ALSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun ALNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

@Composable
private fun ALRow(forms: String, english: String, note: String = "") {
    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)) {
                    append(forms)
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
