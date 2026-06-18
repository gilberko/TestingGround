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
fun SportsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sports", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            SPSection("General Terms")
            SPRow("míč", "ball", "m.; fotbalový míč = football; basketbalový míč = basketball")
            SPRow("gól / branka", "goal", "gól m. = a goal scored; branka f. = the net / frame structure")
            SPRow("tyč", "goalpost / crossbar", "f.; brankový tyč = goalpost")
            SPRow("brankář / gólman", "goalkeeper / goalie", "brankář m. = formal; gólman m. = colloquial loanword")
            SPRow("rozhodčí", "referee / umpire", "m./f.; adjectival noun — same form for both genders")
            SPRow("ofsajd", "offside", "m.; loanword from English")
            SPRow("koš", "hoop / basket", "m.; basketball: dát koš = to score a basket")
            SPRow("puk", "puck", "m.; ice hockey term")
            SPRow("pálka", "bat", "f.; baseball bat; also: raketa = tennis / badminton racket")
            SPRow("hřiště", "playing field / pitch / court", "n.; fotbalové hřiště = soccer pitch; basketbalové hřiště = basketball court")
            SPRow("faul", "foul", "m.; loanword: dopustit se faulu = to commit a foul")
            SPRow("žlutá karta", "yellow card", "f.; dostat žlutou kartu = to receive a yellow card")
            SPRow("červená karta", "red card", "f.; dostat červenou kartu = player is sent off (vyloučen ze hry)")
            SPRow("tým / mužstvo", "team", "tým m. = loanword, common; mužstvo n. = native Czech equivalent")
            SPRow("pravidla", "rules", "pl., n.; pravidla hry = the rules of the game")
            SPRow("zranění", "injury", "n.; verbal noun from zranit; utrpět zranění = to sustain an injury")
            SPRow("zraněný / zraněná", "injured (m./f.)", "past passive participle of zranit; hráč je zraněný = the player is injured")
            SPRow("výsledek / skóre", "score / result", "výsledek m. = result; skóre n. = score (loanword); konečný výsledek = final score")
            SPRow("výsledková tabule", "scoreboard", "f.; also: elektronická tabule")

            SPSection("Sports Names")
            SPRow("fotbal", "soccer / football", "m.; the default sport called 'fotbal' in Czech")
            SPRow("americký fotbal", "American football", "m.")
            SPRow("basketbal", "basketball", "m.")
            SPRow("hokej / lední hokej", "ice hockey", "m.; Czechs usually say just 'hokej' — ice hockey is the default meaning; lední = ice / rink adj.")
            SPNote("Ice hockey is enormously popular in the Czech Republic. The Czech national team is among the world's best. 'Hokej' alone almost always means lední hokej.")
            SPRow("bowling", "bowling", "m.; loanword")
            SPRow("golf", "golf", "m.")
            SPRow("baseball", "baseball", "m.; pálka = bat")
            SPRow("lyžování / lyže", "skiing / skis", "lyžování n. = the activity (verbal noun); lyže f.pl. = skis")
            SPRow("bruslení / krasobruslení", "ice skating / figure skating", "bruslení n. = skating in general; krasobruslení n. = figure skating; brusle f.pl. = skates")
            SPRow("plavání", "swimming", "n.; verbal noun from plavat")
            SPRow("jezdectví / jezdit na koni", "horse riding / equestrianism", "jezdectví n. = the sport; jezdit na koni = to ride a horse (na koni = on a horse, locative)")
            SPRow("box", "boxing", "m.")
            SPRow("cyklistika / kolo", "cycling / bicycle", "cyklistika f. = the sport; kolo n. = bicycle (lit. 'wheel')")
            SPRow("surfování", "surfing", "n.")
            SPRow("potápění / skok do vody", "diving", "potápění n. = scuba / underwater diving; skok do vody = platform / cliff diving (lit. 'jump into water')")
            SPRow("windsurfing", "windsurfing", "m.; loanword")
            SPRow("kitesurfing", "kitesurfing", "m.; loanword")

            SPSection("Players & Roles")
            SPRow("fotbalista / fotbalistka", "soccer player (m./f.)")
            SPRow("basketbalista / basketbalistka", "basketball player (m./f.)")
            SPRow("hokejista / hokejistka", "ice hockey player (m./f.)")
            SPRow("golfista / golfistka", "golfer (m./f.)")
            SPRow("brankář / gólman", "goalkeeper / goalie", "brankář also used in soccer and other goal sports")
            SPRow("trenér / trenérka", "coach (m./f.)")
            SPRow("sportovec / sportovkyně", "athlete / sportsperson (m./f.)", "atlet / atletka = more formal; used in track and field context")
            SPRow("útočník / útočnice", "attacker / forward / striker (m./f.)")
            SPRow("obránce / obránkyně", "defender (m./f.)")

            SPSection("Game Structure")
            SPRow("první poločas", "first half", "m.; fotbal: 2 × 45 min")
            SPRow("druhý poločas", "second half", "m.")
            SPRow("první čtvrtina", "first quarter", "f.; basketbal: 4 × 10 or 12 min")
            SPRow("druhá čtvrtina", "second quarter", "f.")
            SPRow("třetí čtvrtina", "third quarter", "f.")
            SPRow("poslední čtvrtina", "last quarter / fourth quarter", "f.")
            SPRow("prodloužení", "overtime / extra time", "n.; verbal noun from prodloužit — to extend")
            SPRow("oddechový čas", "time out", "m.; lit. 'breathing time'")
            SPRow("základní skupina", "group stage", "f.; also: skupinová fáze — teams play round-robin within groups of 4, e.g. at the World Cup")
            SPRow("čtvrtfinále", "quarter finals", "n.")
            SPRow("semifinále", "semi finals", "n.")
            SPRow("finále", "finals / final", "n.")
            SPRow("turnaj", "tournament", "m.")
            SPRow("pohár", "cup", "m.; lit. 'goblet'; e.g. Český pohár = Czech Cup")
            SPRow("mistrovství světa", "World Cup / World Championship", "n.; lit. 'world championship'; fotbalové MS = Football World Cup")
            SPRow("útok", "offense / attack", "m.; útočná hra = offensive play; útočník = forward/striker")
            SPRow("obrana", "defense", "f.; obranná hra = defensive play; obránce = defender")

            SPSection("Actions")
            SPRow("přihrát / přihrávat míč", "to pass the ball (perf./imperf.)", "přihrávka f. = a pass / assist")
            SPRow("kopnout do míče", "to kick the ball", "kopnout = perf.; kopat = imperf.")
            SPRow("dát gól / skórovat", "to score a goal", "dát gól = natural Czech; skórovat = loanword, also used")
            SPRow("minout / netrefit", "to miss", "minout = to miss / pass; netrefit = to fail to hit the target")
            SPRow("míč je out / míč je za čárou", "the ball is out", "out = loanword; za čárou = behind the line (instrumental case)")
            SPRow("hrát", "to play a sport", "hraju / hraješ / hraje / hrajeme / hrajete / hrají; hraju fotbal = I play soccer")
            SPRow("driblovat", "to dribble", "imperf.; driblink m. = a dribble; obejít hráče = to go past a player")
            SPRow("střelit / střílet na bránu", "to shoot at goal", "střelit = perf.; střílet = imperf.; střela f. = a shot")
            SPRow("zakmeknout / dát koš", "to dunk (basketball)", "zakmeknout = colloquial for slam dunk; dát koš = to score a basket")
            SPRow("hvízdat / hvízdnout", "to whistle", "hvízdat = imperf.; hvízdnout = perf.; rozhodčí hvízdá = the referee is whistling")
            SPRow("zastavit hru", "to stop the game", "zastavit = perf.; also: přerušit hru = to halt play")
            SPRow("zahájit zápas", "to start / kick off the game", "zahájit = perf.; výkop m. = kick-off (noun)")
            SPRow("zápas skončil", "the game ended / is over", "also: zápas byl ukončen = the match was concluded")
            SPRow("jít do skluzu", "to slide tackle", "skluz m. = a slide tackle; lit. 'go into a slide'")
            SPRow("proti", "against", "preposition + dative: hrát proti Slavii = to play against Slavia; also: zápas proti = match against")
            SPRow("hrát proti", "to play against", "hrát = imperf.; + dative: Sparta hraje proti Slavii = Sparta is playing against Slavia")
            SPRow("střídat / vystřídat hráče", "to substitute a player", "střídat = imperf.; vystřídat = perf.; střídání n. = substitution; náhradník m. = substitute player; jít na střídačku = to go to the bench")

            SPSection("Special Terms")
            SPRow("penalta", "penalty kick", "f.; from English 'penalty'")
            SPRow("penaltový rozstřel", "penalty shoot-out", "m.; used after extra time in soccer; rozstřel = lit. 'shoot-out'")
            SPRow("náhlá smrt / sudden death", "sudden death overtime", "náhlá smrt f. = Czech translation; 'sudden death' also used in Czech hockey commentary")
            SPRow("touchdown", "touchdown", "m.; American football term; used as-is in Czech")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SPSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun SPRow(czech: String, english: String, note: String = "") {
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
private fun SPNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
