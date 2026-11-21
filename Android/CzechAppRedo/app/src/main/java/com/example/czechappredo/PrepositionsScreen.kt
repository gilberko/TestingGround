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

private const val NO_CASE = "conjunction — no case"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrepositionsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Prepositions", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // ══ 0. How to read this screen ══════════════════════════════
            PPSection("How to Read This Screen")
            PPNote("Prepositions are grouped by what they are used for — places, time, reasons, everyday use and verbs. The blue tag after each preposition tells you which case the following noun takes (+ Gen, + Dat, + Acc, + Loc, + Instr). Words tagged \"conjunction — no case\" join whole clauses and do not change the noun after them.")
            PPNote("The motion rule: na, pod, nad, před, za and mezi take the Accusative for motion (kam? = where to?) but the Instrumental — or the Locative for na — for position (kde? = where?). Kočka vlezla pod stůl. (motion, Acc) → Kočka je pod stolem. (position, Instr).")
            PPNote("Extra vowel: a one-consonant preposition gets an -e before words starting with a similar sound or a hard cluster: z → ze (ze školy), v → ve (ve Francii, ve škole), s → se (se sestrou), k → ke (ke mně, ke konci), od → ode (ode mě), před → přede (přede mnou).")

            // ══ 1. Locations ════════════════════════════════════════════
            PPSection("1. Locations — Where Things Are and Where They Go")

            PPSub("In, Into, To, At")
            PPRow("v / ve", "+ Loc", "in / inside (position)",
                "Bydlím v Praze." to "I live in Prague.",
                "Klíče jsou ve skříni." to "The keys are in the wardrobe.",
                "Pracuju v bance." to "I work in a bank.")
            PPRow("na", "+ Loc", "on / at (position — surfaces, open spaces, events)",
                "Kniha je na stole." to "The book is on the table.",
                "Jsem na poště." to "I'm at the post office.",
                "Byli jsme na koncertě." to "We were at a concert.")
            PPRow("na", "+ Acc", "onto / to (direction — surfaces, open spaces, events)",
                "Polož to na stůl." to "Put it on the table.",
                "Jdu na nádraží." to "I'm going to the station.",
                "Jedeme na hory." to "We're going to the mountains.")
            PPRow("do", "+ Gen", "into / to (a destination)",
                "Jdu do školy." to "I'm going to school.",
                "Dej to do skříně." to "Put it in the wardrobe.",
                "Je to vlak do Brna?" to "Is this the train to Brno?")
            PPNote("do is the standard destination preposition: buildings and enclosed places (do školy, do obchodu, do kina), but also countries, cities and parks (do Prahy, do Rakouska, do parku, do města).")
            PPRow("k / ke", "+ Dat", "toward / to (a person or a point — not entering)",
                "Jdu k lékaři." to "I'm going to the doctor's.",
                "Pojď ke mně." to "Come to me.",
                "Jedeme k moři." to "We're going to the seaside.")
            PPRow("u", "+ Gen", "at / by (someone's place, next to something)",
                "Jsem u lékaře." to "I'm at the doctor's.",
                "Jsem u babičky." to "I'm at grandma's.",
                "Sedím u okna." to "I'm sitting by the window.")
            PPRow("až do / až k", "+ Gen / + Dat", "all the way to",
                "Jeli jsme až do Vídně." to "We went all the way to Vienna.",
                "Došli jsme až k řece." to "We walked all the way to the river.")
            PPRow("směrem k / směrem na", "+ Dat / + Acc", "in the direction of / heading for",
                "Jedeme směrem k centru." to "We're heading toward the centre.",
                "Vlak jede směrem na Brno." to "The train goes in the direction of Brno.")

            PPSub("From")
            PPRow("z / ze", "+ Gen", "from / out of (a place, origin, material)",
                "Jsem z Prahy." to "I'm from Prague.",
                "Vytáhl to ze skříně." to "He took it out of the wardrobe.",
                "Jdu ze školy." to "I'm coming from school.")
            PPRow("od", "+ Gen", "from (a person, a point) / away from",
                "Mám to od kamaráda." to "I have it from a friend.",
                "Jdu od lékaře." to "I'm coming from the doctor's.",
                "Odstup od okna." to "Step away from the window.")

            PPSub("Through, Across, Around, Along")
            PPRow("přes", "+ Acc", "across / over / via",
                "Jdeme přes most." to "We're walking across the bridge.",
                "Jedeme přes Prahu." to "We're going via Prague.",
                "Skočil přes plot." to "He jumped over the fence.")
            PPRow("skrz / skrze", "+ Acc", "through (passing right through something)",
                "Díval se skrz okno." to "He was looking through the window.",
                "Pes proběhl skrz díru v plotě." to "The dog ran through a hole in the fence.")
            PPRow("(no preposition)", "+ Instr", "through / along a route",
                "Šli jsme lesem." to "We walked through the forest.",
                "Jděte tudy, parkem." to "Go this way, through the park.")
            PPNote("For 'going through' an area, Czech often uses the bare Instrumental with no preposition at all. přes is 'across / via', skrz is 'straight through' something.")
            PPRow("po", "+ Loc", "around / along / over (a surface or area)",
                "Chodíme po městě." to "We're walking around the city.",
                "Jdi po schodech nahoru." to "Go up the stairs.",
                "Cestoval po celém světě." to "He travelled all over the world.")
            PPRow("kolem / okolo", "+ Gen", "around / past",
                "Běháme kolem parku." to "We run around the park.",
                "Sedli jsme si kolem stolu." to "We sat down around the table.",
                "Šel jsem kolem tvého domu." to "I walked past your house.")
            PPRow("podél", "+ Gen", "along",
                "Šli jsme podél řeky." to "We walked along the river.",
                "Podél silnice rostou stromy." to "Trees grow along the road.")

            PPSub("Inside and Outside")
            PPRow("uvnitř", "+ Gen", "inside",
                "Uvnitř domu je teplo." to "It's warm inside the house.",
                "Čekám uvnitř." to "I'm waiting inside. (used alone as an adverb)")
            PPRow("venku", "adverb — no case", "outside / outdoors",
                "Děti jsou venku." to "The kids are outside.",
                "Venku prší." to "It's raining outside.")
            PPRow("mimo", "+ Acc", "outside of / out of / away from",
                "Bydlí mimo město." to "He lives outside the city.",
                "Výtah je mimo provoz." to "The lift is out of order.")
            PPRow("vně", "+ Gen", "outside (formal / technical)",
                "Kouření je povoleno jen vně budovy." to "Smoking is only allowed outside the building.")
            PPNote("'Outside the house' in everyday speech is usually před domem (in front of the house): Čekám před domem. venku is the everyday adverb; mimo means 'outside the limits of'; vně is formal.")

            PPSub("On, Under, Above")
            PPRow("pod", "+ Instr (position) / + Acc (motion)", "under / below",
                "Kočka je pod stolem." to "The cat is under the table.",
                "Kočka vlezla pod stůl." to "The cat crawled under the table.",
                "Bydlí pod námi." to "They live below us.")
            PPRow("nad", "+ Instr (position) / + Acc (motion)", "above / over",
                "Lampa je nad stolem." to "The lamp is above the table.",
                "Pověs obraz nad gauč." to "Hang the picture above the sofa.",
                "Letadlo vystoupalo nad mraky." to "The plane climbed above the clouds.")
            PPNote("'On' is na: na stole (on the table, Loc) / na stůl (onto the table, Acc) — see In, Into, To, At above.")

            PPSub("In Front Of, Behind, Between, Next To, Opposite, Near, Left, Right")
            PPRow("před", "+ Instr (position) / + Acc (motion)", "in front of",
                "Čekám před domem." to "I'm waiting in front of the house.",
                "Postav židli před okno." to "Put the chair in front of the window.",
                "Stál přede mnou." to "He was standing in front of me.")
            PPRow("za", "+ Instr (position)", "behind / beyond",
                "Auto je za domem." to "The car is behind the house.",
                "Za rohem je kavárna." to "There's a café around the corner.",
                "Bydlí za městem." to "He lives just outside town (lit. behind the town).")
            PPRow("za", "+ Acc (motion)", "behind / around (going there)",
                "Zahni za roh." to "Turn around the corner.",
                "Schoval se za strom." to "He hid behind the tree.",
                "Jedeme za město." to "We're going out of town.")
            PPRow("mezi", "+ Instr (position) / + Acc (motion)", "between / among",
                "Sedím mezi přáteli." to "I'm sitting among friends.",
                "Obchod je mezi bankou a poštou." to "The shop is between the bank and the post office.",
                "Sedl si mezi nás." to "He sat down between us.")
            PPRow("vedle", "+ Gen", "next to / beside",
                "Bydlím vedle školy." to "I live next to the school.",
                "Sedni si vedle mě." to "Sit next to me.")
            PPRow("naproti", "+ Dat", "opposite / across from",
                "Bydlím naproti škole." to "I live opposite the school.",
                "Sedí naproti mně." to "He's sitting across from me.")
            PPRow("blízko / poblíž / nedaleko", "+ Gen", "near / close to / not far from",
                "Bydlím blízko centra." to "I live near the centre.",
                "Je tu poblíž nějaká lékárna?" to "Is there a pharmacy near here?",
                "Chata je nedaleko Prahy." to "The cottage is not far from Prague.")
            PPRow("vlevo od / nalevo od", "+ Gen", "to the left of",
                "Banka je vlevo od pošty." to "The bank is to the left of the post office.",
                "Nalevo od dveří je vypínač." to "The switch is to the left of the door.")
            PPRow("vpravo od / napravo od", "+ Gen", "to the right of",
                "Sedni si napravo ode mě." to "Sit to my right.",
                "Vpravo od kostela je hotel." to "To the right of the church is a hotel.")

            // ── Na vs. Do vs. K ────────────────────────────────────────
            PPSection("Na vs. Do vs. K — Three Ways to Say \"To\"")
            PPNote("All three can translate as \"to,\" but they split Czech destinations into three groups. do (+ genitive) is for enclosed spaces and most general destinations. na (+ accusative) is for open spaces, surfaces, events, and a set of institutions/geographic names that are simply conventional exceptions. k/ke (+ dative) is different again — it means motion toward a person or a point, without necessarily arriving inside anything.")
            PPRow("do", "+ Gen", "into / to — enclosed spaces, most destinations",
                "do školy · do práce · do obchodu · do kina · do Prahy · do parku · do zahraničí" to
                    "to school · to work · to the shop · to the cinema · to Prague · to the park · abroad")
            PPRow("na", "+ Acc", "onto / to — open spaces, events, fixed exceptions",
                "na poštu · na nádraží · na letiště · na univerzitu · na koncert · na oběd · na Slovensko · na hory" to
                    "to the post office · to the station · to the airport · to university · to a concert · to lunch · to Slovakia · to the mountains")
            PPRow("k / ke", "+ Dat", "toward — approaching a person or point, not entering",
                "Jdu k lékaři. · Jedeme k moři. · Jdu k oknu. · Přijeď k nám." to
                    "I'm going to the doctor's. · We're heading to the seaside. · I'm going to the window. · Come to our place.")
            PPNote("Compare all three on the same kind of target: Jdu do nemocnice. (into the hospital building) vs. Jdu na pohotovost. (to the emergency department — an event/institution exception) vs. Jdu k lékaři. (to see the doctor — approaching a person, not necessarily entering any building).")
            PPNote("Mnemonic for the matching position preposition, a 3-way pairing: do pairs with v/ve (do školy → jsem ve škole), na pairs with na (na poštu → jsem na poště), k pairs with u (k babičce → jsem u babičky).")
            PPNote("There isn't a fully reliable semantic rule for which nouns take na — it's largely idiomatic and gets learned together with the noun, though open spaces, events, and islands/mountains lean toward na.")

            // ── Na vs. V/Ve ────────────────────────────────────────────
            PPSection("Na vs. V/Ve — Which One for \"In / At\"?")
            PPNote("Both mean roughly \"in\" or \"at\" for a static location, and the split follows the same shape as the destination prepositions above. v/ve (+ locative) is for inside an enclosed, bounded space. na (+ locative) is for a surface, an open area, an event, or one of the same fixed institutional/geographic exceptions — just in their static, \"being there\" form instead of the \"going there\" form.")
            PPRow("v / ve", "+ Loc", "in — enclosed, bounded space",
                "v Praze · v restauraci · v kině · v bance · v nemocnici" to
                    "in Prague · in the restaurant · in the cinema · in the bank · in the hospital")
            PPRow("na", "+ Loc", "on / at — open space, event, fixed exception",
                "na Slovensku · na poště · na koncertě · na univerzitě · na horách" to
                    "in Slovakia · at the post office · at the concert · at university · in the mountains")
            PPNote("More pairs worth memorizing:")
            PPRow("v parku / na zahradě", "+ Loc", "in the park / in the garden",
                "Jsme v parku. · Děti si hrají na zahradě." to "We're in the park. · The kids are playing in the garden.")
            PPRow("v divadle / na koncertě", "+ Loc", "at the theatre / at a concert",
                "Byli jsme v divadle. · Byli jsme na koncertě." to "We were at the theatre. · We were at a concert.")
            PPRow("v Čechách / na Moravě", "+ Loc", "in Bohemia / in Moravia",
                "Bydlím v Čechách, babička bydlí na Moravě." to "I live in Bohemia, grandma lives in Moravia.")
            PPRow("na Kubě · na Krétě", "+ Loc", "in Cuba · on Crete (islands take na)",
                "Byli jsme na dovolené na Kubě." to "We were on holiday in Cuba.")
            PPRow("na náměstí · na ulici", "+ Loc", "on the square · in/on the street",
                "Sejdeme se na náměstí. · Potkal jsem ho na ulici." to "Let's meet on the square. · I met him in the street.")
            PPNote("The na-vs-v split for institutions is largely idiomatic and gets learned together with the noun, not derived from a rule — memorize the phrase as one unit rather than translating from English.")

            // ── Z vs. Od ───────────────────────────────────────────────
            PPSection("Z/Ze vs. Od — Two Ways to Say \"From\"")
            PPNote("Both mean \"from\" and both take the genitive, but they split the same way do/na/k split \"to.\" z/ze is the return trip of do (and of na) — out of an enclosed space, or the origin something came from. od is the return trip of k/u — away from a point, a person, or the outside of a place — plus a separate use for \"since\" a point in time.")
            PPRow("z / ze", "+ Gen", "from — out of an enclosed space, origin",
                "Jdu z kina. · Jsem z Prahy. · Sklenice je ze skla. · Jdu z pošty." to
                    "I'm coming out of the cinema. · I'm from Prague. · The glass is made of glass. · I'm coming from the post office.")
            PPRow("od", "+ Gen", "from — away from a point/person, or \"since\"",
                "Jdu od kina. · Jdu od Elišky. · Mám dárek od kamaráda. · Pracuju tu od pondělí." to
                    "I'm coming from outside the cinema. · I'm coming from Eliška's place. · I have a gift from a friend. · I've worked here since Monday.")
            PPNote("Important rule: od is always used for \"from a person\" — Jdu od Elišky. is correct, Jdu z Elišky. is not, even though z is the more general word for \"from.\"")
            PPNote("The full round trip: do školy → ve škole → ze školy · na poštu → na poště → z pošty · k babičce → u babičky → od babičky.")

            // ══ 2. Time ═════════════════════════════════════════════════
            PPSection("2. Time — When, How Long, Before and After")

            PPSub("When?")
            PPRow("v / ve", "+ Acc", "on / at (days of the week, clock time)",
                "Přijdu v pondělí." to "I'll come on Monday.",
                "Sejdeme se ve tři hodiny." to "Let's meet at three o'clock.",
                "Obědváme v poledne." to "We have lunch at noon.")
            PPRow("v / ve", "+ Loc", "in (months, years, centuries)",
                "Narodil jsem se v lednu." to "I was born in January.",
                "Stalo se to v roce 2020." to "It happened in 2020.",
                "Ve 20. století." to "In the 20th century.")
            PPNote("Careful: v pondělí (day) is Accusative but v lednu (month) is Locative — they look alike but are different cases.")
            PPRow("o", "+ Loc", "at / over (weekends, holidays)",
                "Co děláš o víkendu?" to "What are you doing at the weekend?",
                "O prázdninách jedeme k moři." to "During the summer holidays we're going to the seaside.",
                "O Vánocích jsme doma." to "At Christmas we're at home.")
            PPRow("kolem / okolo", "+ Gen", "around (approximate time)",
                "Přijdu kolem deseti." to "I'll come around ten.",
                "Vrátím se okolo poledne." to "I'll be back around noon.")
            PPRow("k / ke", "+ Dat", "toward (a time) / for (a meal)",
                "K večeru se ochladilo." to "Toward evening it got cooler.",
                "Ke konci filmu jsem usnul." to "Toward the end of the film I fell asleep.",
                "Co máme k snídani?" to "What do we have for breakfast?")

            PPSub("During and While")
            PPRow("během", "+ Gen", "during / in the course of",
                "Během léta hodně cestujeme." to "During the summer we travel a lot.",
                "Během dne pracuju, večer studuju." to "During the day I work, in the evening I study.")
            PPRow("při", "+ Loc", "while / during (an activity)",
                "Poslouchám hudbu při práci." to "I listen to music while working.",
                "Při jídle se nemluví." to "You don't talk while eating.")
            PPRow("za", "+ Gen", "during / in the time of (an era — formal)",
                "Za války žili na venkově." to "During the war they lived in the countryside.",
                "Za komunismu se nesmělo cestovat." to "Under communism you weren't allowed to travel.")
            PPRow("přes", "+ Acc", "over / through (a period) / more than",
                "Zůstal přes noc." to "He stayed overnight.",
                "Přes léto pracuju." to "Over the summer I work.",
                "Čekal jsem přes hodinu." to "I waited more than an hour.")
            PPRow("na", "+ Acc", "for (a planned length of time)",
                "Jedu tam na týden." to "I'm going there for a week.",
                "Pojedeme domů na Vánoce." to "We'll go home for Christmas.")

            PPSub("Before, After, Since, Until, In")
            PPRow("před", "+ Instr", "before / ago",
                "Umyj si ruce před obědem." to "Wash your hands before lunch.",
                "Byl tady před týdnem." to "He was here a week ago.")
            PPRow("po", "+ Loc", "after",
                "Po obědě si dám kávu." to "After lunch I'll have a coffee.",
                "Přijď po páté hodině." to "Come after five o'clock.",
                "Po válce se vrátili." to "After the war they came back.")
            PPRow("za", "+ Acc", "in (after a span of time) / within",
                "Přijdu za hodinu." to "I'll come in an hour.",
                "Udělám to za den." to "I'll do it within a day.")
            PPRow("od", "+ Gen", "since / from (a time)",
                "Pracuju tu od pondělí." to "I've worked here since Monday.",
                "Učím se česky od roku 2020." to "I've been learning Czech since 2020.")
            PPRow("do", "+ Gen", "until / by (a time)",
                "Zůstanu do pátku." to "I'll stay until Friday.",
                "Musím to mít hotové do večera." to "I have to have it done by the evening.")
            PPRow("až do", "+ Gen", "right up until",
                "Pracoval až do půlnoci." to "He worked right up until midnight.")
            PPRow("od … do …", "+ Gen … + Gen", "from … to / until …",
                "Pracuju od devíti do pěti." to "I work from nine to five.",
                "Obchod má otevřeno od pondělí do pátku." to "The shop is open from Monday to Friday.")
            PPRow("mezi … a …", "+ Instr", "between … and …",
                "Přijď mezi pátou a šestou." to "Come between five and six.",
                "Mezi Vánocemi a Novým rokem nepracuju." to "I don't work between Christmas and New Year.")

            PPSub("Before a Whole Clause — Conjunctions (No Case)")
            PPNote("When 'while / before / after / until' is followed by a full clause (with its own verb), Czech uses a conjunction instead of a preposition. Conjunctions do not change the case of anything.")
            PPRow("zatímco", NO_CASE, "while / whereas",
                "Zatímco vařím, ty prostíráš stůl." to "While I cook, you set the table.")
            PPRow("když", NO_CASE, "when / while",
                "Když jsem byl malý, bydleli jsme v Brně." to "When I was little, we lived in Brno.")
            PPRow("než / předtím než", NO_CASE, "before",
                "Než odejdeš, zavři okno." to "Before you leave, close the window.")
            PPRow("potom co / poté co", NO_CASE, "after",
                "Potom co jsme se najedli, šli jsme ven." to "After we ate, we went out.")
            PPRow("dokud (ne-)", NO_CASE, "as long as / until",
                "Počkám, dokud nepřijdeš." to "I'll wait until you come. (lit. 'as long as you don't come')")
            PPRow("až", NO_CASE, "when / once (in the future)",
                "Až přijdeš domů, zavolej mi." to "When you get home, call me.")

            // ══ 3. Reasons & Connecting ═════════════════════════════════
            PPSection("3. Reasons, Contrast & Connecting")
            PPNote("Most English 'connecting words' — because, therefore, moreover, however, but, and — are conjunctions or adverbs in Czech, not prepositions: they join whole clauses and take no case. Only the 'because of / thanks to / despite / instead of' kind are real prepositions, followed by a noun in a specific case.")

            PPSub("Prepositions (Followed by a Noun)")
            PPRow("kvůli", "+ Dat", "because of",
                "Kvůli počasí jsme doma." to "Because of the weather we're home.",
                "Kvůli dešti jsme zůstali doma." to "Because of the rain we stayed home.")
            PPRow("díky", "+ Dat", "thanks to",
                "Díky tobě jsem to stihl." to "Thanks to you I made it.",
                "Díky dobrému počasí jsme byli venku." to "Thanks to the good weather we were outside.")
            PPNote("kvůli usually introduces a negative or neutral cause; díky a positive one.")
            PPRow("vzhledem k", "+ Dat", "given / in view of / due to",
                "Vzhledem k počasí zůstaneme doma." to "Given the weather, we'll stay home.")
            PPRow("z důvodu", "+ Gen", "due to / for reasons of (formal)",
                "Z důvodu nemoci je dnes zavřeno." to "Closed today due to illness.")
            PPRow("v důsledku", "+ Gen", "as a result of",
                "V důsledku nehody byla silnice uzavřena." to "As a result of the accident, the road was closed.")
            PPRow("pro", "+ Acc", "because of (formal, mostly on signs)",
                "Zavřeno pro nemoc." to "Closed due to illness.")
            PPRow("z / ze", "+ Gen", "out of (a motive)",
                "Udělal to z lásky." to "He did it out of love.",
                "Mlčel ze strachu." to "He kept quiet out of fear.")
            PPRow("navzdory", "+ Dat", "despite / in spite of",
                "Navzdory dešti jsme šli ven." to "Despite the rain we went out.")
            PPRow("přes", "+ Acc", "despite (in spite of)",
                "Přes všechnu snahu to nevyšlo." to "Despite all the effort, it didn't work out.")
            PPRow("kromě", "+ Gen", "except / besides / in addition to",
                "Kromě Petra přišli všichni." to "Everyone came except Petr.",
                "Kromě češtiny mluví i německy." to "Besides Czech, she also speaks German.")
            PPRow("místo", "+ Gen", "instead of",
                "Místo kávy si dám čaj." to "Instead of coffee I'll have tea.")

            PPSub("Conjunctions and Connectors (No Case)")
            PPRow("a", NO_CASE, "and",
                "Mám bratra a sestru." to "I have a brother and a sister.")
            PPRow("i", NO_CASE, "and / also / even",
                "Přišel i Petr." to "Even Petr came. / Petr came too.")
            PPRow("ale", NO_CASE, "but",
                "Chci jít ven, ale prší." to "I want to go out, but it's raining.")
            PPRow("však / avšak", NO_CASE, "however",
                "Chtěl jsem přijít, nemohl jsem však." to "I wanted to come; however, I couldn't.",
                "Je to drahé, avšak kvalitní." to "It's expensive, however good quality.")
            PPNote("však usually stands in the second position of its clause (nemohl jsem však); avšak opens the clause.")
            PPRow("nicméně / přesto / ale přesto", NO_CASE, "nevertheless / still / but still",
                "Bylo to drahé, nicméně jsme to koupili." to "It was expensive; nevertheless, we bought it.",
                "Bylo pozdě, ale přesto jsme šli ven." to "It was late, but we still went out.")
            PPRow("i když / ačkoli", NO_CASE, "even though / although",
                "I když prší, jdu běhat." to "Even though it's raining, I'm going running.",
                "Ačkoli je unavený, pracuje dál." to "Although he's tired, he keeps working.")
            PPRow("protože / neboť", NO_CASE, "because / for (neboť is formal)",
                "Nepřišel, protože byl nemocný." to "He didn't come because he was sick.",
                "Zůstal doma, neboť byl nemocný." to "He stayed home, for he was ill.")
            PPRow("proto / a proto", NO_CASE, "therefore / and therefore",
                "Byl nemocný, proto nepřišel." to "He was sick, therefore he didn't come.",
                "Pršelo, a proto jsme zůstali doma." to "It was raining, and therefore we stayed home.")
            PPRow("takže / tedy", NO_CASE, "so / thus",
                "Je pozdě, takže jdu domů." to "It's late, so I'm going home.",
                "Nemáš čas, tedy půjdu sám." to "You don't have time, so I'll go alone.")
            PPRow("navíc / kromě toho", NO_CASE, "moreover / in addition to that / besides",
                "Byt je velký a navíc levný." to "The flat is big and moreover cheap.",
                "Kromě toho umí i vařit." to "In addition to that, he can cook too.")
            PPRow("také / taky", NO_CASE, "also / too",
                "Mluví česky a také anglicky." to "She speaks Czech and also English.")
            PPRow("nebo / ani … ani", NO_CASE, "or / neither … nor",
                "Chceš čaj, nebo kávu?" to "Do you want tea or coffee?",
                "Nechci ani čaj, ani kávu." to "I want neither tea nor coffee.")
            PPNote("See also: Connecting Sentences (Learning hub) for more conjunctions and full sentence patterns.")

            // ══ 4. Other everyday prepositions ══════════════════════════
            PPSection("4. Other Everyday Prepositions")
            PPRow("s / se", "+ Instr", "with (together)",
                "Jdu s přítelem." to "I'm going with a friend.",
                "Kávu s mlékem, prosím." to "Coffee with milk, please.",
                "Bydlím se sestrou." to "I live with my sister.")
            PPRow("bez", "+ Gen", "without",
                "Kávu bez cukru, prosím." to "Coffee without sugar, please.",
                "Nemůžu bez tebe žít." to "I can't live without you.")
            PPRow("pro", "+ Acc", "for (a recipient) / to fetch",
                "Tohle je dárek pro mámu." to "This is a present for mum.",
                "Jdu pro kávu." to "I'm going to get coffee.")
            PPRow("o", "+ Loc", "about",
                "Mluvím o tobě." to "I'm talking about you.",
                "Čtu knihu o Praze." to "I'm reading a book about Prague.")
            PPRow("o", "+ Acc", "against (leaning) / by (an amount)",
                "Opřel se o zeď." to "He leaned against the wall.",
                "Je o rok starší než já." to "He is a year older than me.",
                "Přišel o hodinu dřív." to "He came an hour earlier.")
            PPRow("podle", "+ Gen", "according to / by",
                "Podle mapy je to tudy." to "According to the map it's this way.",
                "Podle mě je to dobrý nápad." to "In my opinion it's a good idea.")
            PPRow("proti", "+ Dat", "against",
                "Jsem proti tomu." to "I'm against it.",
                "Potřebuju lék proti bolesti." to "I need a painkiller.")
            PPRow("za", "+ Acc", "for (in exchange / thanks / price)",
                "Děkuju za pomoc." to "Thanks for the help.",
                "Koupil jsem to za sto korun." to "I bought it for a hundred crowns.")
            PPRow("na", "+ Acc", "for (a purpose)",
                "Máte něco na kašel?" to "Do you have something for a cough?",
                "Šetřím peníze na nájem." to "I'm saving money for the rent.",
                "Jdeme na oběd." to "We're going for lunch.")
            PPRow("po", "+ Loc", "by / via (a medium)",
                "Mluvili jsme po telefonu." to "We talked on the phone.",
                "Pošlu ti to poštou." to "I'll send it by post. (here the bare Instrumental)")
            PPRow("ohledně", "+ Gen", "regarding / concerning",
                "Píšu vám ohledně objednávky." to "I'm writing to you regarding the order.")
            PPRow("včetně", "+ Gen", "including",
                "Cena je včetně DPH." to "The price includes VAT.",
                "Přišli všichni včetně dětí." to "Everyone came, including the children.")

            // ══ 5. Verbs + prepositions ═════════════════════════════════
            PPSection("5. Verbs + Prepositions")
            PPNote("Many verbs always come with a fixed preposition and case — and they often differ from English (wait FOR = čekat NA, be interested IN = zajímat se O). Learn the verb together with its preposition and case as one unit.")

            PPSub("Thinking and Feelings")
            PPRow("myslet na", "+ Acc", "to think of (have on your mind)",
                "Myslím na tebe." to "I'm thinking of you.",
                "Na co myslíš?" to "What are you thinking about?")
            PPRow("myslet si o", "+ Loc", "to think about (have an opinion)",
                "Co si o tom myslíš?" to "What do you think about it?")
            PPRow("přemýšlet o", "+ Loc", "to think about / ponder",
                "Přemýšlím o budoucnosti." to "I'm thinking about the future.")
            PPRow("vzpomínat na", "+ Acc", "to remember / reminisce about",
                "Rád vzpomínám na dětství." to "I like to remember my childhood.")
            PPRow("zapomenout na", "+ Acc", "to forget about",
                "Zapomněl jsem na klíče." to "I forgot about the keys.")
            PPRow("těšit se na", "+ Acc", "to look forward to",
                "Těším se na víkend." to "I'm looking forward to the weekend.")
            PPRow("zajímat se o", "+ Acc", "to be interested in",
                "Zajímám se o historii." to "I'm interested in history.")
            PPRow("starat se o", "+ Acc", "to take care of / look after",
                "Starám se o psa." to "I take care of the dog.")
            PPRow("bát se / bát se o", "+ Gen / + Acc", "to be afraid of / to worry about (someone)",
                "Bojím se psů." to "I'm afraid of dogs.",
                "Bojím se o tebe." to "I'm worried about you.")
            PPRow("mít strach z", "+ Gen", "to be afraid of",
                "Mám strach z výšek." to "I'm afraid of heights.")
            PPRow("zamilovat se do", "+ Gen", "to fall in love with",
                "Zamiloval se do Jany." to "He fell in love with Jana.")
            PPRow("zlobit se na", "+ Acc", "to be angry with",
                "Nezlob se na mě." to "Don't be angry with me.")
            PPRow("věřit / věřit v", "+ Dat / + Acc", "to believe (someone) / to believe in",
                "Věřím ti." to "I believe you.",
                "Věří v Boha." to "She believes in God.")
            PPRow("záležet na / záviset na", "+ Loc", "to depend on",
                "Záleží na tobě." to "It's up to you.",
                "Všechno závisí na penězích." to "Everything depends on money.")
            PPRow("snít o", "+ Loc", "to dream about",
                "Sním o domě u moře." to "I dream about a house by the sea.")

            PPSub("Talking and Communicating")
            PPRow("mluvit o", "+ Loc", "to talk about",
                "Mluvili jsme o práci." to "We talked about work.")
            PPRow("mluvit s", "+ Instr", "to talk with",
                "Mluvil jsem s šéfem." to "I talked with the boss.")
            PPRow("povídat si s … o …", "+ Instr … + Loc", "to chat with … about …",
                "Povídali jsme si s Petrem o fotbale." to "Petr and I chatted about football.")
            PPRow("ptát se / zeptat se na", "+ Acc", "to ask about",
                "Zeptal se na cestu." to "He asked for directions.")
            PPRow("odpovědět na", "+ Acc", "to answer / reply to",
                "Odpověz mi na e-mail." to "Reply to my email.")
            PPRow("psát o / psát", "+ Loc / + Dat", "to write about / to write to (someone)",
                "Píšu knihu o Praze." to "I'm writing a book about Prague.",
                "Píšu mámě dopis." to "I'm writing mum a letter. (no preposition)")
            PPRow("stěžovat si na", "+ Acc", "to complain about",
                "Soused si stěžuje na hluk." to "The neighbour complains about the noise.")
            PPRow("děkovat / poděkovat za", "+ Acc", "to thank for",
                "Děkuju za pomoc." to "Thank you for the help.")
            PPRow("souhlasit s", "+ Instr", "to agree with",
                "Souhlasím s tebou." to "I agree with you.")
            PPRow("hádat se s … o …", "+ Instr … + Acc", "to argue with … about …",
                "Hádá se s rodiči o peníze." to "She argues with her parents about money.")
            PPRow("dozvědět se o", "+ Loc", "to find out / learn about",
                "Dozvěděl jsem se o tom včera." to "I found out about it yesterday.")

            PPSub("Living, Moving and Arriving")
            PPRow("bydlet v / na", "+ Loc", "to live in",
                "Bydlím v Brně." to "I live in Brno.",
                "Bydlíme na Moravě." to "We live in Moravia.")
            PPRow("pracovat v / na / pro", "+ Loc / + Loc / + Acc", "to work in / at / for",
                "Pracuju v bance." to "I work in a bank.",
                "Pracuje na úřadě." to "She works at the (government) office.",
                "Pracuju pro velkou firmu." to "I work for a big company.")
            PPRow("pracovat na", "+ Loc", "to work on (a project)",
                "Pracuju na novém projektu." to "I'm working on a new project.")
            PPRow("přijet / přiletět z / od", "+ Gen", "to arrive from",
                "Přijel z Německa." to "He arrived from Germany.",
                "Přiletěli jsme z Londýna." to "We flew in from London.",
                "Přijela od rodičů." to "She came from her parents' place.")
            PPRow("letět / jet / jít do / na / k", "+ Gen / + Acc / + Dat", "to fly / go to",
                "Letím do Londýna." to "I'm flying to London.",
                "Jedu na Slovensko." to "I'm going to Slovakia.",
                "Jdu k zubaři." to "I'm going to the dentist.")
            PPRow("vrátit se z / do", "+ Gen", "to come back from / to",
                "Vrátil jsem se z dovolené." to "I came back from holiday.",
                "Vrátím se do Prahy v pondělí." to "I'll be back in Prague on Monday.")
            PPRow("čekat na", "+ Acc", "to wait for",
                "Čekám na autobus." to "I'm waiting for the bus.")
            PPRow("dívat se na", "+ Acc", "to look at / watch",
                "Díváme se na film." to "We're watching a film.")
            PPRow("jít pro", "+ Acc", "to go and get / fetch",
                "Jdu pro pivo." to "I'm going to get a beer.")

            PPSub("People and Activities")
            PPRow("hrát si s", "+ Instr", "to play with",
                "Děti si hrají s míčem." to "The children are playing with a ball.")
            PPRow("hrát na", "+ Acc", "to play (an instrument)",
                "Hraju na kytaru." to "I play the guitar.")
            PPRow("hrát", "+ Acc", "to play (a sport / game) — no preposition",
                "Hrajeme fotbal." to "We play football.",
                "Hrajete šachy?" to "Do you play chess?")
            PPRow("seznámit se s", "+ Instr", "to get to know / meet (for the first time)",
                "Seznámil jsem se s Janou na večírku." to "I met Jana at a party.")
            PPRow("setkat se s / sejít se s", "+ Instr", "to meet (with)",
                "Zítra se setkám s klientem." to "Tomorrow I'm meeting a client.")
            PPRow("oženit se s", "+ Instr", "to marry (said of a man)",
                "Oženil se s Marií." to "He married Marie.")
            PPRow("vdát se za", "+ Acc", "to marry (said of a woman)",
                "Vdala se za Tomáše." to "She married Tomáš.")
            PPRow("připravit se na", "+ Acc", "to prepare for",
                "Připravuju se na zkoušku." to "I'm preparing for the exam.")
            PPRow("zaplatit za", "+ Acc", "to pay for",
                "Zaplatil jsem za večeři." to "I paid for dinner.")
            PPRow("pomoct / pomáhat s", "+ Instr", "to help with",
                "Pomůžeš mi s úkolem?" to "Will you help me with my homework?")
            PPRow("začít s / skončit s", "+ Instr", "to start / to quit (an activity)",
                "Začal jsem s jógou." to "I've taken up yoga.",
                "Skončil s kouřením." to "He quit smoking.")
            PPNote("Watch out — these take NO preposition in Czech, unlike English:")
            PPRow("hledat / poslouchat / potřebovat", "+ Acc", "to look for / listen to / need",
                "Hledám klíče." to "I'm looking for the keys.",
                "Poslouchám hudbu." to "I'm listening to music.",
                "Potřebuju pomoc." to "I need help.")
            PPRow("zavolat / smát se", "+ Dat", "to call (someone) / to laugh at",
                "Zavolám ti zítra." to "I'll call you tomorrow.",
                "Nesměj se mi!" to "Don't laugh at me!")

            // ══ 6. Quick review ═════════════════════════════════════════
            PPSection("All Prepositions at a Glance")
            PPNote("Every preposition from this screen, sorted alphabetically, with its case. Conjunctions (no case) are not listed — see section 3.")
            PPMasterTable(
                listOf(
                    Triple("až do", "Genitive", "all the way to / until"),
                    Triple("bez", "Genitive", "without"),
                    Triple("během", "Genitive", "during"),
                    Triple("blízko", "Genitive", "near"),
                    Triple("díky", "Dative", "thanks to"),
                    Triple("do", "Genitive", "to / into (a destination); until"),
                    Triple("k / ke", "Dative", "toward / to (a person)"),
                    Triple("kolem / okolo", "Genitive", "around / past / approximately"),
                    Triple("kromě", "Genitive", "except / besides"),
                    Triple("kvůli", "Dative", "because of"),
                    Triple("mezi", "Accusative", "between (motion)"),
                    Triple("mezi", "Instrumental", "between / among"),
                    Triple("mimo", "Accusative", "outside of / out of"),
                    Triple("místo", "Genitive", "instead of"),
                    Triple("na", "Accusative", "onto / to (direction); for (purpose, time)"),
                    Triple("na", "Locative", "on / at (a place)"),
                    Triple("nad", "Accusative", "above (motion)"),
                    Triple("nad", "Instrumental", "above / over"),
                    Triple("naproti", "Dative", "opposite / across from"),
                    Triple("navzdory", "Dative", "despite"),
                    Triple("nedaleko", "Genitive", "not far from"),
                    Triple("o", "Accusative", "against (leaning) / by (amount)"),
                    Triple("o", "Locative", "about; at (weekends, holidays)"),
                    Triple("od", "Genitive", "from (a person or point); since"),
                    Triple("ohledně", "Genitive", "regarding"),
                    Triple("po", "Locative", "after; around / along (a place)"),
                    Triple("poblíž", "Genitive", "near / nearby"),
                    Triple("pod", "Accusative", "under (motion)"),
                    Triple("pod", "Instrumental", "under"),
                    Triple("podél", "Genitive", "along"),
                    Triple("podle", "Genitive", "according to"),
                    Triple("pro", "Accusative", "for (fetching or purpose)"),
                    Triple("proti", "Dative", "against"),
                    Triple("před", "Accusative", "in front of (motion)"),
                    Triple("před", "Instrumental", "in front of / before / ago"),
                    Triple("přes", "Accusative", "through / across / via; over; despite"),
                    Triple("při", "Locative", "while / during (an activity)"),
                    Triple("s / se", "Instrumental", "with"),
                    Triple("skrz", "Accusative", "through"),
                    Triple("směrem k", "Dative", "in the direction of"),
                    Triple("u", "Genitive", "at / by (someone's place)"),
                    Triple("uvnitř", "Genitive", "inside"),
                    Triple("v / ve", "Accusative", "on / at (days, clock time)"),
                    Triple("v / ve", "Locative", "in (a place; months, years)"),
                    Triple("v důsledku", "Genitive", "as a result of"),
                    Triple("včetně", "Genitive", "including"),
                    Triple("vedle", "Genitive", "next to / beside"),
                    Triple("vlevo / vpravo od", "Genitive", "to the left / right of"),
                    Triple("vně", "Genitive", "outside (formal)"),
                    Triple("vzhledem k", "Dative", "given / in view of"),
                    Triple("z / ze", "Genitive", "from (a place); out of"),
                    Triple("z důvodu", "Genitive", "due to (formal)"),
                    Triple("za", "Accusative", "behind (motion) / in (time) / for"),
                    Triple("za", "Genitive", "during (an era — formal)"),
                    Triple("za", "Instrumental", "behind (position)")
                )
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PPSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun PPSub(text: String) {
    Spacer(modifier = Modifier.height(12.dp))
    Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF37474F))
    Spacer(modifier = Modifier.height(2.dp))
}

@Composable
private fun PPRow(prep: String, case: String, meaning: String, vararg examples: Pair<String, String>) {
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)) {
                append(prep)
            }
            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = ButtonBlue)) {
                append("  [$case]")
            }
            withStyle(SpanStyle(fontSize = 15.sp, color = Color.DarkGray)) {
                append("  —  $meaning")
            }
        }
    )
    examples.forEach { (example, translation) ->
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)) {
                    append(example)
                }
                withStyle(SpanStyle(fontSize = 14.sp, color = Color.Gray, fontStyle = FontStyle.Italic)) {
                    append("  —  $translation")
                }
            },
            modifier = Modifier.padding(top = 2.dp, start = 8.dp)
        )
    }
}

@Composable
private fun PPNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

@Composable
private fun PPMasterTable(entries: List<Triple<String, String, String>>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Text("Prep.", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ButtonBlue, modifier = Modifier.weight(0.8f))
            Text("Case", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ButtonBlue, modifier = Modifier.weight(1f))
            Text("Meaning", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ButtonBlue, modifier = Modifier.weight(1.8f))
        }
        HorizontalDivider(color = Color.LightGray)
        entries.forEach { (prep, case, meaning) ->
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                Text(prep, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(0.8f))
                Text(case, fontSize = 13.sp, color = Color.DarkGray, modifier = Modifier.weight(1f))
                Text(meaning, fontSize = 13.sp, color = Color.DarkGray, modifier = Modifier.weight(1.8f))
            }
            HorizontalDivider(color = Color(0xFFEEEEEE))
        }
    }
}
