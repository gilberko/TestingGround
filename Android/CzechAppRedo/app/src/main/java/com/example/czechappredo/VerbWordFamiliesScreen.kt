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
fun VerbWordFamiliesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Verbs and Related Nouns, Adjectives, Adverbs", fontSize = 16.sp, fontWeight = FontWeight.Bold) },
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

            // 1. Overview
            VWFSection("Yes — One Verb, a Whole Family")
            VWFNote("Czech builds nouns, adjectives and adverbs from verbs with a fairly regular set of suffixes. Once you know the verb, you can often guess (and recognise) a dozen related words. Here is the family of pracovat (to work):")
            VWFTable(
                header = listOf("Kind", "Word", "Meaning"),
                rows = listOf(
                    listOf("the work (noun)", "práce", "work — the everyday noun"),
                    listOf("the act of working", "pracování", "working (rare — práce is used instead)"),
                    listOf("person", "pracovník / pracovnice", "worker, employee"),
                    listOf("person (from dělat)", "dělník / dělnice", "(manual) worker, labourer"),
                    listOf("active adjective", "pracující", "working (pracující matka = a working mother)"),
                    listOf("passive adjective", "zpracovaný", "processed (from zpracovat)"),
                    listOf("relational adjective", "pracovní", "work-, work-related (pracovní doba = working hours)"),
                    listOf("tendency adjective", "pracovitý", "hardworking, diligent"),
                    listOf("abstract noun", "pracovitost", "diligence"),
                    listOf("adverb", "pracovitě", "diligently"),
                    listOf("place", "pracovna", "study, workroom")
                )
            )
            VWFNote("So \"the working man\" is either pracující člověk (a person who is working / has a job) or dělník (a worker, labourer). Plain pracovat has no passive (it takes no object), so the passive adjective comes from the prefixed zpracovat (to process).")

            // 2. Corrections
            VWFSection("Your Endings — Spelling Check")
            VWFNote("All the endings you listed are real. Most just needed their accents (čárky and háčky):")
            VWFTable(
                header = listOf("You wrote", "Correct", "What it makes"),
                rows = listOf(
                    listOf("-ani / -eni / -nuti / -ti", "-ání (-aní) / -ení (-ění) / -nutí / -tí", "✔ action noun"),
                    listOf("-tel / -č / -nik", "-tel / -č (-ač, -ič) / -ník", "✔ person (+ -ec, -ce, -ář)"),
                    listOf("-ici / -ajici / -ujici", "-ící / -ající / -ující (+ -oucí)", "✔ active adjective (\"-ing\")"),
                    listOf("-ny / -eny / -ty", "-ný (-aný) / -ený / -tý", "✔ passive / result adjective"),
                    listOf("-aci / -ici", "-ací / -icí (-ecí, -ycí) — short i!", "✔ purpose adjective"),
                    listOf("-ivy / -avy", "-ivý / -avý (+ -itý)", "✔ tendency adjective"),
                    listOf("-ost", "-ost", "✔ abstract noun (from an adjective)"),
                    listOf("-ě / -e / -y", "-ě / -e / -y", "✔ adverb (from an adjective)")
                )
            )

            // 3a. Action nouns
            VWFSection("1. Action Nouns — -ání, -ení, -nutí, -tí")
            VWFNote("Made from the passive participle + -í: psán → psaní, čten → čtení, zapomenut → zapomenutí, pit → pití. Verbs in -ět give -ění (vyprávět → vyprávění). They are all NEUTER and decline like náměstí. Almost every verb has one — this is how Czech says \"-ing\" as a noun.")
            VWFNote("⚠ Short or long a? In two-syllable nouns the a is short when the prefixed verb shortens it: psát → napsat → psaní; also hraní, spaní, praní. Otherwise it's long: dělání, plavání, stání, and all longer nouns (parkování, čekání).")
            VWFRow("kouřit → kouření", "smoking",
                example = "Kouření zakázáno.", translation = "No smoking.")
            VWFRow("parkovat → parkování", "parking",
                example = "Zákaz parkování.", translation = "No parking.")
            VWFRow("číst → čtení", "reading",
                example = "Čtení mě baví.", translation = "I enjoy reading.")
            VWFRow("psát → psaní", "writing",
                example = "Psaní je těžší než čtení.", translation = "Writing is harder than reading.")
            VWFRow("vařit → vaření", "cooking",
                example = "Vaření je můj koníček.", translation = "Cooking is my hobby.")
            VWFRow("plavat → plavání", "swimming",
                example = "Plavání je zdravé.", translation = "Swimming is healthy.")
            VWFRow("bydlet → bydlení", "housing, accommodation",
                example = "Hledám bydlení v Praze.", translation = "I'm looking for accommodation in Prague.")
            VWFRow("rozhodnout → rozhodnutí  (-nutí)", "decision",
                example = "Je to těžké rozhodnutí.", translation = "It's a difficult decision.")
            VWFRow("stisknout → stisknutí  (-nutí)", "a press",
                example = "Stačí krátké stisknutí tlačítka.", translation = "A short press of the button is enough.")
            VWFRow("mýt → mytí  (-tí, short y)", "washing",
                example = "Mytí nádobí nesnáším.", translation = "I hate washing the dishes.")
            VWFRow("pít → pití  (-tí)", "drinking; a drink",
                example = "Chceš něco k pití?", translation = "Do you want something to drink?")
            VWFRow("pozvat → pozvání", "invitation",
                example = "Díky za pozvání!", translation = "Thanks for the invitation!")
            VWFNote("The noun keeps the verb's aspect: oblékání (getting dressed — a process, from oblékat) vs oblečení (from obléct — and in practice \"clothes\"). Some such nouns have become ordinary words: vzdělání (education), oblečení (clothes), pití (a drink), psaní (old-fashioned: a letter).")

            // 3b. Person nouns
            VWFSection("2. Who Does It — -tel, -č, -ník, -ec, -ce, -ář")
            VWFRow("učit → učitel / učitelka  (-tel)", "teacher",
                example = "Moje učitelka je z Brna.", translation = "My teacher is from Brno.")
            VWFRow("řídit → ředitel / ředitelka  (-tel)", "director, headmaster",
                example = "Ředitel školy má dnes volno.", translation = "The headmaster is off today.")
            VWFRow("zaměstnávat → zaměstnavatel  (-tel)", "employer",
                example = "Můj zaměstnavatel platí kurz češtiny.", translation = "My employer pays for the Czech course.")
            VWFRow("hrát → hráč / hráčka  (-č)", "player",
                example = "Je to nejlepší hráč týmu.", translation = "He's the team's best player.")
            VWFRow("řídit → řidič / řidička  (-ič)", "driver",
                example = "Řidič autobusu byl milý.", translation = "The bus driver was nice.")
            VWFRow("prodávat → prodavač / prodavačka  (-ač)", "shop assistant",
                example = "Zeptej se prodavačky.", translation = "Ask the shop assistant.")
            VWFRow("poslouchat → posluchač  (-ač)", "listener",
                example = "Vážení posluchači…", translation = "Dear listeners…")
            VWFRow("pracovat → pracovník / pracovnice  (-ník)", "worker, employee",
                example = "Firma hledá nové pracovníky.", translation = "The company is looking for new staff.")
            VWFRow("pomoci → pomocník / pomocnice  (-ník)", "helper, assistant",
                example = "Jsi můj malý pomocník.", translation = "You're my little helper.")
            VWFRow("chodit → chodec  (-ec)", "pedestrian",
                example = "Pozor na chodce!", translation = "Watch out for pedestrians!")
            VWFRow("plavat → plavec / plavkyně  (-ec)", "swimmer",
                example = "Je to výborný plavec.", translation = "He's an excellent swimmer.")
            VWFRow("soudit → soudce / soudkyně  (-ce)", "judge",
                example = "Soudce vynesl rozsudek.", translation = "The judge delivered the verdict.")
            VWFRow("číst → čtenář / čtenářka  (-ář)", "reader",
                example = "Je to vášnivý čtenář.", translation = "He's a passionate reader.")
            VWFRow("kouřit → kuřák / kuřačka  (-ák)", "smoker",
                example = "Jsem nekuřák.", translation = "I'm a non-smoker.")
            VWFNote("Feminines: -tel → -telka, -č → -čka, -ník → -nice, -ec → -kyně (plavkyně, chodkyně), -ce → -kyně (soudkyně). Note zaměstnavatel (employer, -tel) vs zaměstnanec (employee, -ec from the passive zaměstnaný).")
            VWFSub("-č, -ák, -ík can also be a TOOL")
            VWFNote("vysávat → vysavač (vacuum cleaner) · vařit → vařič (stove, cooker) · počítat → počítač (computer) · zapalovat → zapalovač (lighter) · budit → budík (alarm clock) · otvírat → otvírák (opener)")

            // 3c. Active adjectives
            VWFSection("3. Active Adjectives (\"-ing\") — -ící, -ající, -ující, -oucí")
            VWFNote("Take the oni form of the present tense and add -cí: dělají → dělající, pracují → pracující, mluví → mluvící, jedou → jedoucí, píšou → píšící. These describe someone/something doing the action RIGHT NOW (or in general). The -oucí type was missing from your list: it comes from -ou verbs (jdou, jedou, vedou, rostou).")
            VWFRow("pracující", "working",
                example = "Je to pracující matka.", translation = "She's a working mother.")
            VWFRow("mluvící", "talking",
                example = "Máme mluvícího papouška.", translation = "We have a talking parrot.")
            VWFRow("spící", "sleeping",
                example = "Nebuď spící dítě.", translation = "Don't wake the sleeping child.")
            VWFRow("vařící", "boiling",
                example = "Zalij čaj vařící vodou.", translation = "Pour boiling water over the tea.")
            VWFRow("hořící", "burning",
                example = "Hasiči vběhli do hořícího domu.", translation = "The firefighters ran into the burning house.")
            VWFRow("čtoucí", "reading",
                example = "Žena čtoucí knihu seděla u okna.", translation = "A woman reading a book sat by the window.")
            VWFRow("jedoucí  (-oucí)", "moving (vehicle)",
                example = "Nevyskakuj z jedoucího vlaku!", translation = "Don't jump off a moving train!")
            VWFRow("rostoucí  (-oucí)", "rising, growing",
                example = "Lidi trápí rostoucí ceny.", translation = "Rising prices worry people.")
            VWFSub("Some became nouns")
            VWFNote("vedoucí (manager, boss — \"the leading one\") · cestující (passenger) · kolemjdoucí (passer-by) · přednášející (lecturer) · pracující (working people) · prodávající / kupující (the seller / the buyer, in contracts). They still decline like adjectives: Zeptej se vedoucího.")

            // 3d. Passive adjectives
            VWFSection("4. Passive / Result Adjectives — -ný, -ený, -tý")
            VWFNote("The same form as the passive voice (see Verbs and Tenses → Passive Voice): the result of the action. Usually from perfective verbs.")
            VWFRow("prodat → prodaný", "sold",
                example = "Ten dům je už prodaný.", translation = "That house is already sold.")
            VWFRow("napsat → napsaný", "written",
                example = "Mám napsaný dopis.", translation = "I have the letter written.")
            VWFRow("zavřít → zavřený", "closed",
                example = "Obchod je zavřený.", translation = "The shop is closed.")
            VWFRow("otevřít → otevřený", "open(ed)",
                example = "Nech otevřené okno.", translation = "Leave the window open.")
            VWFRow("vařit → vařený", "boiled",
                example = "K snídani mám vařené vejce.", translation = "I have a boiled egg for breakfast.")
            VWFRow("smažit → smažený", "fried",
                example = "Dám si smažený sýr.", translation = "I'll have fried cheese.")
            VWFRow("umýt → umytý  (-tý)", "washed",
                example = "Nádobí je umyté.", translation = "The dishes are washed.")
            VWFRow("rozbít → rozbitý  (-tý)", "broken",
                example = "Mám rozbitý telefon.", translation = "My phone is broken.")
            VWFRow("zapomenout → zapomenutý  (-tý)", "forgotten",
                example = "Ve vlaku našli zapomenutý deštník.", translation = "They found a forgotten umbrella on the train.")

            // 3e. Purpose adjectives
            VWFSection("5. Purpose Adjectives (\"for doing X\") — -cí")
            VWFNote("Made from the PAST stem (psal, šil, holil) + -cí, with a SHORT vowel before -cí: -ací, -icí, -ecí, -ycí. They describe what a thing is FOR.")
            VWFRow("psát (psal) → psací", "for writing",
                example = "psací stůl", translation = "writing desk")
            VWFRow("šít (šil) → šicí", "for sewing",
                example = "šicí stroj", translation = "sewing machine")
            VWFRow("prát (pral) → prací", "for washing (laundry)",
                example = "prací prášek", translation = "washing powder")
            VWFRow("holit (holil) → holicí", "for shaving",
                example = "holicí strojek", translation = "electric shaver")
            VWFRow("mýt (myl) → mycí", "for washing",
                example = "mycí prostředek", translation = "detergent, washing-up liquid")
            VWFRow("spát (spal) → spací", "for sleeping",
                example = "spací pytel", translation = "sleeping bag")
            VWFRow("hrát (hrál) → hrací", "for playing",
                example = "hrací kostka", translation = "dice")
            VWFRow("otevírat (otevíral) → otevírací", "opening",
                example = "otevírací doba", translation = "opening hours")
            VWFRow("napájet (napájel) → napájecí", "power supply",
                example = "napájecí kabel", translation = "power cable")
            VWFSub("⚠ Short i (purpose) vs long í (doing it now)")
            VWFTable(
                header = listOf("Purpose -cí (what it's FOR)", "Active -ící (doing it NOW)"),
                rows = listOf(
                    listOf("šicí stroj — sewing machine", "šijící babička — grandma (who is) sewing"),
                    listOf("psací pero — pen for writing", "píšící student — a student (who is) writing"),
                    listOf("čisticí prostředek — cleaning agent", "uklízečka čistící okna — a cleaner cleaning windows"),
                    listOf("řídicí jednotka — control unit", "člověk řídící auto — a person driving a car"),
                    listOf("spací pytel — sleeping bag", "spící dítě — a sleeping child"),
                    listOf("hrací kostka — dice", "hrající si děti — children playing")
                )
            )
            VWFNote("Funny coincidence: prací (prací prášek, from prát) is spelled exactly like prací, a case form of práce (instrumental singular s prací = with work, and genitive plural).")

            // 3f. Tendency
            VWFSection("6. Tendency / Character — -ivý, -avý (-itý)")
            VWFNote("Describe someone or something that TENDS to do the action, or has it as a quality.")
            VWFRow("hrát → hravý", "playful",
                example = "Naše kočka je hodně hravá.", translation = "Our cat is very playful.")
            VWFRow("vědět → zvědavý", "curious, nosy",
                example = "Nebuď tak zvědavý!", translation = "Don't be so nosy!")
            VWFRow("kousat → kousavý", "biting; sarcastic",
                example = "Má kousavý humor.", translation = "He has a biting sense of humour.")
            VWFRow("zapomenout → zapomnětlivý", "forgetful",
                example = "Děda je čím dál zapomnětlivější.", translation = "Grandpa is getting more and more forgetful.")
            VWFRow("mlčet → mlčenlivý", "taciturn, discreet",
                example = "Je to mlčenlivý člověk.", translation = "He's a man of few words.")
            VWFRow("hýbat (pohnout) → pohyblivý", "movable, mobile",
                example = "pohyblivé schody", translation = "escalator (\"moving stairs\")")
            VWFRow("učit se → učenlivý", "quick to learn",
                example = "Je to učenlivý student.", translation = "He's a quick learner.")
            VWFRow("číst → čtivý", "readable, a good read",
                example = "Ta kniha je hodně čtivá.", translation = "That book is a really good read.")
            VWFRow("trpět → trpělivý", "patient",
                example = "Buď trpělivý.", translation = "Be patient.")
            VWFRow("pracovat → pracovitý  (-itý)", "hardworking",
                example = "Je velmi pracovitá.", translation = "She's very hardworking.")
            VWFNote("Not every verb has one, and the ending isn't always -ivý/-avý: pracovitý (-itý), ospalý (sleepy, from spát), upovídaný (talkative, from povídat).")

            // 3g. -ost
            VWFSection("7. Abstract Nouns — -ost")
            VWFNote("-ost is added to an ADJECTIVE, not directly to the verb — but since many adjectives come from verbs, you get chains: verb → adjective → -ost. All -ost nouns are feminine (like kost).")
            VWFRow("trpět → trpělivý → trpělivost", "patience",
                example = "Trpělivost růže přináší.", translation = "Patience brings roses. (proverb)")
            VWFRow("pracovat → pracovitý → pracovitost", "diligence",
                example = "Oceňuji jeho pracovitost.", translation = "I appreciate his diligence.")
            VWFRow("vědět → zvědavý → zvědavost", "curiosity",
                example = "Zvědavost mi nedala.", translation = "Curiosity got the better of me.")
            VWFRow("zapomenout → zapomnětlivý → zapomnětlivost", "forgetfulness",
                example = "Omluv mou zapomnětlivost.", translation = "Excuse my forgetfulness.")
            VWFRow("číst → čitelný → čitelnost", "legibility, readability",
                example = "Čitelnost textu je důležitá.", translation = "The legibility of the text is important.")
            VWFRow("spolehnout se → spolehlivý → spolehlivost", "reliability",
                example = "Cením si jeho spolehlivosti.", translation = "I value his reliability.")

            // 3h. Adverbs
            VWFSection("8. Adverbs — -ě, -e, -y")
            VWFNote("Adverbs are made from ADJECTIVES (drop -ý/-í, add the ending), so the chain is verb → adjective → adverb:")
            VWFNote("• -ě after most consonants: zvědavý → zvědavě, otevřený → otevřeně, trpělivý → trpělivě")
            VWFNote("• -e after l, s, z and soft consonants (where ě can't be written): rychlý → rychle, veselý → vesele, tichý → tiše, lehký → lehce")
            VWFNote("• -y mainly after -ský / -cký: český → česky, anglický → anglicky (and a few others: hezký → hezky)")
            VWFRow("zvědavě", "curiously",
                example = "Zvědavě se díval z okna.", translation = "He looked out of the window curiously.")
            VWFRow("hravě", "playfully; easily",
                example = "Tu zkoušku hravě zvládl.", translation = "He passed that exam with ease.")
            VWFRow("otevřeně", "openly",
                example = "Mluvili spolu otevřeně.", translation = "They spoke openly with each other.")
            VWFRow("unaveně", "tiredly",
                example = "Unaveně se usmála.", translation = "She smiled tiredly.")
            VWFRow("nadšeně", "enthusiastically",
                example = "Diváci nadšeně tleskali.", translation = "The audience clapped enthusiastically.")
            VWFRow("trpělivě", "patiently",
                example = "Trpělivě čekal.", translation = "He waited patiently.")
            VWFRow("čitelně · písemně", "legibly · in writing",
                example = "Pište čitelně. · Odpovězte písemně.", translation = "Write legibly. · Answer in writing.")
            VWFNote("Active -ící adjectives rarely make adverbs; Czech uses a phrase instead: vyhovujícím způsobem (in a satisfactory way). Old adverbial participles (přechodníky) survive as frozen adverbs: chtě nechtě (willy-nilly, from chtít), takříkajíc (so to speak).")

            // 3i. Bonus
            VWFSection("9. Bonus — Other Common Patterns")
            VWFSub("Relational adjectives -ní / -cký (\"to do with X\")")
            VWFNote("pracovní doba (working hours) · pracovní den (working day) · prodejní cena (selling price) · plavecký bazén (swimming pool) · písemná zkouška (written exam) · jízdní řád (timetable, from jízda / jezdit)")
            VWFSub("Places -na / -árna / -írna")
            VWFNote("pracovat → pracovna (study) · učit → učebna (classroom) · čekat → čekárna (waiting room) · jíst → jídelna (canteen, dining room) · péct → pekárna (bakery) · prát → prádelna (laundry room) · prodávat → prodejna (shop)")
            VWFSub("Things -dlo")
            VWFNote("mýt → mýdlo (soap), umyvadlo (washbasin) · letět → letadlo (aeroplane) · sedět → sedadlo (seat)")
            VWFSub("Result nouns with no ending (or -ba / -ka)")
            VWFNote("prodat → prodej (sale) · nakoupit → nákup (purchase, shopping) · odejít → odchod (departure) · přijít → příchod (arrival) · vrátit se → návrat (return) · stavět → stavba (building, construction) · zkoušet → zkouška (exam, test)")

            // 4. Families
            VWFSection("Whole Families")
            VWFNote("\"—\" = no natural word of that type.")
            VWFFamily("psát — to write", listOf(
                "action" to "psaní — writing",
                "person" to "pisatel (writer of a text), spisovatel (author)",
                "active" to "píšící — writing",
                "passive" to "napsaný / psaný — written (psaná čeština = written Czech)",
                "purpose" to "psací — psací stůl (desk)",
                "relational" to "písemný — written (písemná zkouška)",
                "adverb" to "písemně — in writing"
            ))
            VWFFamily("učit (se) — to teach (to learn)", listOf(
                "action" to "učení — teaching, learning",
                "person" to "učitel / učitelka — teacher",
                "active" to "učící (se) — teaching / learning",
                "passive" to "naučený — learnt; učený — learned, scholarly",
                "tendency" to "učenlivý — quick to learn",
                "-ost" to "učenlivost",
                "place / thing" to "učebna (classroom), učebnice (textbook)"
            ))
            VWFFamily("číst — to read", listOf(
                "action" to "čtení — reading",
                "person" to "čtenář / čtenářka — reader",
                "active" to "čtoucí — reading",
                "passive" to "přečtený — read",
                "tendency" to "čtivý — a good read; čitelný — legible",
                "-ost" to "čtivost, čitelnost",
                "adverb" to "čtivě, čitelně",
                "tool" to "čtečka — e-reader, card reader"
            ))
            VWFFamily("hrát — to play", listOf(
                "action" to "hraní — playing",
                "person" to "hráč / hráčka — player",
                "active" to "hrající — playing",
                "passive" to "hraný — played; feigned (hraná radost)",
                "purpose" to "hrací — hrací kostka (dice)",
                "tendency" to "hravý — playful",
                "-ost" to "hravost — playfulness",
                "adverb" to "hravě — playfully, easily"
            ))
            VWFFamily("spát — to sleep", listOf(
                "action" to "spaní — sleeping",
                "person" to "spáč — sleeper",
                "active" to "spící — sleeping",
                "passive" to "— (vyspaný = well-rested, from vyspat se)",
                "purpose" to "spací — spací pytel (sleeping bag)",
                "tendency" to "ospalý — sleepy",
                "-ost / adverb" to "ospalost — sleepiness; ospale — sleepily",
                "place" to "— (bedroom = ložnice, from a different root)"
            ))
            VWFFamily("vařit — to cook, boil", listOf(
                "action" to "vaření — cooking",
                "person" to "— (cook = kuchař, a different root)",
                "tool" to "vařič — stove, cooker",
                "active" to "vařící — boiling",
                "passive" to "vařený — boiled, cooked",
                "relational" to "varný — varná konvice (kettle)"
            ))
            VWFFamily("řídit — to drive, manage", listOf(
                "action" to "řízení — driving; management",
                "person" to "řidič / řidička (driver), ředitel / ředitelka (director)",
                "active" to "řídící — driving / managing (now)",
                "passive" to "řízený — controlled, guided (řízená střela = guided missile)",
                "purpose" to "řídicí — řídicí jednotka (control unit)"
            ))
            VWFFamily("prodat / prodávat — to sell", listOf(
                "action" to "prodej (sale), prodávání (selling)",
                "person" to "prodavač / prodavačka (shop assistant), prodejce (seller, dealer)",
                "active" to "prodávající — selling; also = the seller (in contracts)",
                "passive" to "prodaný — sold",
                "relational" to "prodejní — prodejní cena (selling price)",
                "tendency" to "prodejný — corruptible, venal",
                "place" to "prodejna — shop"
            ))
            VWFFamily("plavat — to swim", listOf(
                "action" to "plavání — swimming",
                "person" to "plavec / plavkyně — swimmer",
                "active" to "plavající — swimming",
                "passive" to "—",
                "relational" to "plavecký — plavecký bazén (swimming pool)",
                "thing" to "plavky — swimsuit"
            ))

            // 5. Q&A
            VWFSection("Your Questions Answered")
            VWFQA("Is there such a relation between a verb and nouns / adjectives / adverbs?",
                "✔ Yes — Czech word-formation is very regular. From a verb you get the action noun, the person, active/passive/purpose adjectives; from those adjectives you get -ost nouns and adverbs.")
            VWFQA("Were my endings right?",
                "✔ All of them are real; they just need accents: -ání/-ení/-nutí/-tí, -tel/-č/-ník, -ící/-ající/-ující (+ -oucí), -ný/-ený/-tý, -ací/-icí (short i!), -ivý/-avý, -ost, -ě/-e/-y.")
            VWFQA("\"The work\" and \"the working man\" from pracovat?",
                "The work = práce (pracování exists but is rare). The working man = pracující člověk (active adjective) or dělník / pracovník (person nouns).")
            VWFQA("Adverb from pracovat?",
                "Through the adjective: pracovat → pracovitý (hardworking) → pracovitě (diligently).")
            VWFQA("Do -ost and adverbs come straight from the verb?",
                "✘ Not directly — both are built on an adjective, which is often itself made from the verb (trpět → trpělivý → trpělivost / trpělivě).")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun VWFSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun VWFSub(text: String) {
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = text, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    Spacer(modifier = Modifier.height(2.dp))
}

@Composable
private fun VWFRow(term: String, meaning: String, example: String, translation: String) {
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
private fun VWFNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

@Composable
private fun VWFQA(question: String, answer: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = question, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    Text(text = answer, fontSize = 14.sp, color = Color.DarkGray, modifier = Modifier.padding(start = 4.dp, top = 2.dp))
}

@Composable
private fun VWFFamily(title: String, rows: List<Pair<String, String>>) {
    VWFSub(title)
    VWFTable(header = listOf("Type", "Word"), rows = rows.map { listOf(it.first, it.second) }, weights = listOf(1f, 2.5f))
}

@Composable
private fun VWFTable(header: List<String>, rows: List<List<String>>, weights: List<Float>? = null) {
    val w = weights ?: List(header.size) { 1f }
    Spacer(modifier = Modifier.height(6.dp))
    Column(modifier = Modifier.fillMaxWidth().border(1.dp, Color.LightGray)) {
        Row(modifier = Modifier.fillMaxWidth().background(ButtonBlue)) {
            header.forEachIndexed { j, h ->
                Text(
                    text = h,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.weight(w[j]).padding(6.dp)
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
                        modifier = Modifier.weight(w[j]).padding(6.dp)
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(6.dp))
}
