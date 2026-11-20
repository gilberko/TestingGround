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
                title = { Text("Questions and Answers", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            QSSection("How Long? — Duration (Jak dlouho / Na jak dlouho / Za jak dlouho)")
            QSNote("English 'how long' covers three different time questions. Czech keeps them apart with a preposition:")
            QSRow("Jak dlouho?", "How long? — how much time something lasts / has lasted")
            QSRow("Na jak dlouho?", "For how long? — a planned period in the future (na + accusative)")
            QSRow("Za jak dlouho?", "In how long? / How soon? — time until something happens (za + accusative)")
            QSNote("Important: for something that started in the past and still continues, Czech uses the PRESENT tense where English uses 'have been / have lived'. Often with už (already).")
            QSExample("Jak dlouho tu bydlíš?", "How long have you lived here? (lit. 'How long do you live here?')")
            QSExample("Bydlím tu (už) tři roky.", "I've been living here for three years.")
            QSExample("Jak dlouho už čekáš?", "How long have you been waiting?")
            QSExample("Jak dlouho to trvá?", "How long does it take? (trvat = to last / take)")
            QSExample("Jak dlouho trvá cesta do Brna?", "How long is the journey to Brno?")
            QSExample("Asi dvě a půl hodiny vlakem.", "About two and a half hours by train.")
            QSExample("Na jak dlouho jedete do Itálie?", "How long are you going to Italy for?")
            QSExample("Na týden.", "For a week.")
            QSExample("Za jak dlouho přijede autobus?", "How soon will the bus come?")
            QSExample("Za deset minut.", "In ten minutes.")
            QSNote("Duration answers use the accusative with no preposition: celou noc (all night), jednu hodinu (one hour), dva dny (two days), pět let (five years).")

            QSSection("How Long / Tall / Big? — Size and Measurement")
            QSNote("For physical size Czech uses Jak + adjective (agreeing with the noun), or the verb měřit (to measure) with Kolik.")
            QSRow("Jak dlouhý / dlouhá / dlouhé?", "How long? (length — agrees with gender)")
            QSRow("Jak velký / velká / velké?", "How big?")
            QSRow("Jak vysoký / vysoká / vysoké?", "How tall / high?")
            QSRow("Kolik měří...?", "How long / tall is...? (lit. 'how much does it measure')")
            QSRow("Kolik váží...?", "How much does ... weigh?")
            QSExample("Jak dlouhý je Karlův most?", "How long is Charles Bridge? (most = m.)")
            QSExample("Měří asi 500 metrů.", "It's about 500 metres long.")
            QSExample("Kolik měříš?", "How tall are you?")
            QSExample("Měřím metr osmdesát.", "I'm 1.80 m tall. (lit. 'I measure a metre eighty')")
            QSExample("Jak velký je ten byt?", "How big is the apartment?")
            QSExample("Kolik váží ten kufr?", "How much does the suitcase weigh?")

            QSSection("How Far? (Jak daleko?)")
            QSNote("daleko = far, blízko = near. odsud = from here, odtud = from there. Answers give either distance or travel time.")
            QSRow("Jak daleko?", "How far?")
            QSRow("Je to daleko?", "Is it far?")
            QSRow("Kolik je to kilometrů?", "How many kilometres is it?")
            QSExample("Jak daleko je to na nádraží?", "How far is it to the train station?")
            QSExample("Je to odsud daleko?", "Is it far from here?")
            QSExample("Ne, je to blízko — pět minut pěšky.", "No, it's close — five minutes on foot.")
            QSExample("Kolik je to kilometrů do Prahy?", "How many kilometres is it to Prague?")
            QSExample("Asi sto kilometrů.", "About a hundred kilometres.")
            QSExample("Jak daleko je to autem?", "How far is it by car? (usually answered in time: Půl hodiny.)")

            QSSection("How Much Does It Cost? (Kolik to stojí?)")
            QSNote("stát = to cost (also 'to stand'!). Same 3rd-person form for singular and plural: to stojí / ty boty stojí. The price comes after as a plain number + korun.")
            QSRow("Kolik to stojí?", "How much does it cost?")
            QSRow("Kolik stojí...?", "How much is / are ...?")
            QSRow("Kolik to dělá?", "How much does it come to? (at the checkout — total)")
            QSRow("Kolik platím?", "How much do I pay? (asking for the bill total)")
            QSRow("Za kolik?", "For how much? (za + price)")
            QSExample("Kolik stojí ten chleba?", "How much is the bread?")
            QSExample("Kolik stojí ty boty?", "How much are the shoes?")
            QSExample("Stojí to sto padesát korun.", "It costs 150 crowns.")
            QSExample("Za kolik jsi to koupil?", "How much did you buy it for?")
            QSExample("Za dvě stě korun.", "For 200 crowns.")
            QSExample("To je moc drahé. Nemáte něco levnějšího?", "That's too expensive. Don't you have something cheaper?")
            QSNote("Crown agreement follows the number rule below: 1 koruna, 2–4 koruny, 5+ korun (genitive plural).")

            QSSection("How Many / How Much? (Kolik + Genitive)")
            QSNote("Kolik is followed by the GENITIVE: genitive PLURAL for things you can count, genitive SINGULAR for uncountable stuff (water, money as a mass, time).")
            QSRow("Kolik + gen. plural", "How many? — Kolik holubů? Kolik lidí? Kolik dětí?")
            QSRow("Kolik + gen. singular", "How much? — Kolik vody? Kolik času? Kolik cukru?")
            QSNote("The verb after kolik is 3rd-person SINGULAR, and in the past tense it is NEUTER (-o): Kolik je...? / Kolik bylo...? — even though the noun is plural.")
            QSExample("Kolik je tam holubů?", "How many pigeons are there? (holub → holubů, gen. pl.)")
            QSExample("Kolik holubů je na Staroměstském náměstí?", "How many pigeons are there on Old Town Square?")
            QSExample("Kolik tam bylo lidí?", "How many people were there? (bylo — neuter singular)")
            QSExample("Kolik máš sourozenců?", "How many brothers and sisters do you have?")
            QSExample("Kolik chceš vody?", "How much water do you want? (voda → vody, gen. sg.)")
            QSExample("Kolik máme času?", "How much time do we have?")
            QSNote("Answering — the number decides the case and the verb:")
            QSTable(
                headers = listOf("Number", "Noun form", "Example"),
                rows = listOf(
                    listOf("1", "nominative sg.", "Je tam jeden holub."),
                    listOf("2, 3, 4", "nominative pl. + plural verb", "Jsou tam tři holubi."),
                    listOf("5 and up", "genitive pl. + singular verb", "Je tam pět holubů."),
                    listOf("many / few", "genitive pl. + singular verb", "Je tam hodně / málo holubů.")
                ),
                weights = listOf(0.7f, 1.1f, 1.2f)
            )
            QSNote("Past tense with 5+: Bylo tam pět holubů (neuter singular), but Byli tam tři holubi (plural).")

            QSSection("Other 'How...?' and Kolik- Questions")
            QSRow("Kolik je ti let?", "How old are you? (lit. 'How many years is to you?' — dative)")
            QSRow("Je mi dvacet let.", "I'm twenty. (1 rok, 2–4 roky, 5+ let)")
            QSRow("Kolik je hodin?", "What time is it?")
            QSRow("Jak často?", "How often?")
            QSRow("Kolikrát?", "How many times?")
            QSRow("Kolikátého je dnes?", "What's the date today? → Dnes je pátého. (the fifth)")
            QSRow("Kolikátý / kolikátá / kolikáté?", "Which number in order? — no direct English word")
            QSExample("Jak často jezdíš do Prahy?", "How often do you go to Prague?")
            QSExample("Dvakrát týdně.", "Twice a week.")
            QSExample("Kolikrát jsi tam byl?", "How many times have you been there?")
            QSExample("Kolikátý jsi skončil v závodě?", "What place did you finish in the race? → Třetí. (Third.)")

            QSSection("Answering Why — Because (Protože)")
            QSNote("protože = because. It introduces a full clause (subject + verb) and is the everyday answer to Proč? A comma always comes before protože in the middle of a sentence.")
            QSRow("Protože...", "Because... (answer on its own, or joins two clauses)")
            QSRow("..., protože...", "..., because... (comma is obligatory)")
            QSRow("Proto...", "That's why / Therefore... — NOT 'because'!")
            QSRow("..., totiž...", "..., you see / the thing is... (gives a reason, placed after the first word)")
            QSRow("Kvůli tomu, že...", "Because of the fact that... (a clause after kvůli)")
            QSRow("Díky tomu, že...", "Thanks to the fact that... (positive reason)")
            QSRow("Prostě proto.", "Just because. (refusing to give a reason)")
            QSRow("Proč ne?", "Why not?")
            QSNote("Protože vs. proto: they look alike but point opposite ways. protože gives the CAUSE, proto gives the RESULT. Jsem unavený, proto jdu spát = I'm tired, so I'm going to bed. Jdu spát, protože jsem unavený = I'm going to bed because I'm tired.")
            QSNote("Clitics (jsem, se, si, mi...) come right after protože, because protože fills the first position: protože JSEM byl nemocný, protože SE MI to líbí.")
            QSExample("Proč jsi nepřišel? — Protože jsem byl nemocný.", "Why didn't you come? — Because I was ill.")
            QSExample("Učím se česky, protože mám českou přítelkyni.", "I'm learning Czech because I have a Czech girlfriend.")
            QSExample("Proč tam chceš jet? — Protože se mi to tam líbí.", "Why do you want to go there? — Because I like it there.")
            QSExample("Nepřijdu, jsem totiž nemocná.", "I won't come — I'm ill, you see. (feminine speaker)")
            QSExample("Prší, proto zůstaneme doma.", "It's raining, so we'll stay at home.")
            QSExample("Proč? — Prostě proto.", "Why? — Just because.")

            QSSection("I Don't Know / I Don't Remember")
            QSRow("Nevím.", "I don't know. (vědět → nevím)")
            QSRow("Opravdu nevím.", "I really don't know.")
            QSRow("Nemám tušení. / Netuším.", "I have no idea.")
            QSRow("Nevím jistě.", "I don't know for sure.")
            QSRow("Nepamatuju si. / Nepamatuji si.", "I don't remember. (colloquial / standard)")
            QSRow("Nevzpomínám si.", "I can't recall. (vzpomínat si = to recall)")
            QSRow("Už si nevzpomenu.", "I can't remember any more. (it's gone)")
            QSRow("Zapomněl/a jsem.", "I forgot. (m./f. speaker)")
            QSNote("si is part of pamatovat si / vzpomenout si — it never drops: Nepamatuju SI, kde to je.")
            QSExample("Kde je pošta? — Nevím, nejsem odsud.", "Where's the post office? — I don't know, I'm not from here.")
            QSExample("Nevím, kolik to stojí.", "I don't know how much it costs. (comma before the question word)")
            QSExample("Jak se jmenoval? — Nepamatuju si.", "What was his name? — I don't remember.")
            QSExample("Už si nevzpomenu, kde jsem nechal klíče.", "I can't remember where I left the keys.")

            QSSection("I'm Sure / I'm Not Sure")
            QSNote("jistý = sure, certain. Agrees with the speaker: jistý (m.), jistá (f.). The reflexive si is always there: být si jistý. 'Sure about something' = instrumental: jistý tím (about that).")
            QSRow("Jsem si jistý / jistá.", "I'm sure.")
            QSRow("Jsem si jistý / jistá, že...", "I'm sure that...")
            QSRow("Jsem si docela jistý / jistá, že...", "I'm quite / pretty sure that...")
            QSRow("Jsem si skoro jistý / jistá.", "I'm almost sure.")
            QSRow("Jsem si úplně jistý / jistá.", "I'm completely sure.")
            QSRow("Nejsem si jistý / jistá.", "I'm not sure.")
            QSRow("Nejsem si tím jistý / jistá.", "I'm not sure about that.")
            QSRow("Určitě.", "Definitely. / Surely. (very common one-word answer)")
            QSRow("Rozhodně.", "Absolutely. / Definitely.")
            QSRow("Samozřejmě.", "Of course.")
            QSExample("Jsem si jistý, že jsem ho zavřel.", "I'm sure I closed it. (male speaker)")
            QSExample("Jsem si docela jistá, že vlak jede v osm.", "I'm quite sure the train leaves at eight. (female speaker)")
            QSExample("Je to správně? — Nejsem si jistý.", "Is it right? — I'm not sure.")
            QSExample("Přijdeš? — Určitě!", "Are you coming? — Definitely!")

            QSSection("I Think / Maybe / Could Be")
            QSNote("Myslím, že... = I think that... — the comma before že is obligatory. Myslím si, že... means the same (si adds a slight 'in my opinion').")
            QSRow("Myslím, že...", "I think that...")
            QSRow("Myslím, že ano. / Myslím, že jo.", "I think so. (standard / colloquial)")
            QSRow("Myslím, že ne. / Nemyslím si.", "I don't think so.")
            QSRow("Podle mě...", "In my opinion... (lit. 'according to me')")
            QSRow("Možná.", "Maybe.")
            QSRow("Možná ano, možná ne.", "Maybe yes, maybe no.")
            QSRow("Asi. / Asi ano. / Asi ne.", "Probably. / Probably yes. / Probably not.")
            QSRow("Třeba.", "Maybe. / Perhaps. (colloquial)")
            QSRow("Snad.", "Hopefully. / Perhaps.")
            QSRow("Může být.", "Could be. — ALSO: 'That's fine / OK' (e.g. agreeing to an offer)")
            QSRow("To je možné.", "That's possible.")
            QSRow("Mohlo by to být...", "It could be... (conditional)")
            QSRow("Doufám, že...", "I hope that...")
            QSRow("Záleží na tom. / Jak kdy.", "It depends. / Depends on when.")
            QSNote("Může být in a restaurant: Dáte si kávu? — Může být. = Will you have coffee? — Sure, why not. It's an acceptance, not doubt.")
            QSNote("asi is stronger than možná: asi = probably (I lean yes), možná = maybe (50/50).")
            QSExample("Myslím, že má pravdu.", "I think he's right.")
            QSExample("Bude zítra pršet? — Myslím, že ne.", "Will it rain tomorrow? — I don't think so.")
            QSExample("Možná přijdu později.", "Maybe I'll come later.")
            QSExample("Je doma? — Asi jo.", "Is he home? — Probably.")
            QSExample("To by mohl být jeho bratr.", "That could be his brother.")
            QSExample("Je to on? — Může být, nevím jistě.", "Is it him? — Could be, I don't know for sure.")
            QSExample("Doufám, že to stihneme.", "I hope we make it in time.")

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
