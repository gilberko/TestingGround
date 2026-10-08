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
fun EverydayScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Everyday", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            EVSection("Computers & Tech")
            EVRow("počítač", "computer")
            EVRow("notebook / laptop", "laptop", "notebook is the usual Czech word for a laptop — not a paper notebook (that is sešit)")
            EVRow("tablet", "tablet")
            EVRow("klávesnice", "keyboard")
            EVRow("myš", "mouse", "same word as the animal")
            EVRow("tiskárna", "printer")
            EVRow("kabel", "cable")
            EVRow("nabíječka", "charger")
            EVRow("pevný disk", "hard drive")
            EVRow("cloudové úložiště / online úložiště", "online storage / cloud storage")
            EVRow("AI agent", "AI agent", "agent umělé inteligence in formal writing")

            EVSection("Phones & Messages")
            EVRow("telefon", "phone / telephone")
            EVRow("mobil / mobilní telefon", "mobile phone", "mobil is what most people say")
            EVRow("chytrý telefon / smartphone", "smartphone")
            EVRow("pevná linka", "landline (telephone)")
            EVRow("poslat zprávu", "to send a message", "posílat (impf.) / poslat (pf.)")
            EVRow("dostat zprávu", "to receive a message", "dostávat (impf.) / dostat (pf.); formal: obdržet")
            EVRow("poslat e-mail", "to send an email")
            EVRow("dostat e-mail", "to receive an email")

            EVSection("Shopping & Money")
            EVRow("nakupování", "shopping (noun)")
            EVRow("jít nakupovat", "to go shopping")
            EVRow("obchod", "store / shop")
            EVRow("nákupní centrum / obchodní centrum", "mall / shopping center", "same thing in Czech; colloquially obchoďák")
            EVRow("pokladna", "checkout / cash register")
            EVRow("pokladní", "cashier (the person)", "declines like an adjective: pokladní (m. and f.)")
            EVRow("peníze", "money", "plural only: Nemám peníze.")
            EVRow("kupovat / koupit", "to buy")
            EVRow("prodávat / prodat", "to sell")
            EVRow("pronajmout si", "to rent (from someone)", "Pronajal jsem si byt. — I rented an apartment.")
            EVRow("pronajmout", "to rent out (to someone)", "Pronajímám byt studentům. — I rent out my apartment to students.")
            EVRow("půjčit si", "to borrow / to rent (a bike, car, tools)", "Půjčil jsem si kolo. — I rented a bike.")
            EVRow("půjčit", "to lend / to loan", "Půjčíš mi sto korun? — Will you lend me a hundred crowns?")
            EVRow("vrátit", "to return (give back) something to someone", "Vrátím ti to zítra. — I'll give it back to you tomorrow. (vrátit se = to come back)")

            EVSection("Eating Out & Going Out")
            EVRow("restaurace", "restaurant")
            EVRow("bar", "bar")
            EVRow("jídelní lístek / menu", "menu")
            EVRow("objednat si jídlo", "to order food", "si — ordering for yourself")
            EVRow("zaplatit účet", "to pay the bill / cheque")
            EVRow("Zaplatím, prosím. / Účet, prosím.", "The cheque, please.", "the usual way to ask for the bill")
            EVRow("jít do kina", "to go to the movies")
            EVRow("pozvat někoho na večeři", "to take someone out to dinner", "lit. 'to invite someone to dinner' — implies you're paying")

            EVSection("The Car")
            EVRow("auto", "car")
            EVRow("garáž", "garage (where you park)")
            EVRow("autoservis / servis", "garage (car repair shop)")
            EVRow("tankovat / natankovat", "to refuel / to fill up (gas)", "Musím natankovat. — I need to get gas.")
            EVRow("benzín / nafta", "gasoline / diesel")
            EVRow("přezout pneumatiky", "to change tires (summer ↔ winter)", "přezutí = the seasonal tire swap; vyměnit pneumatiky = replace them")
            EVRow("zkontrolovat tlak v pneumatikách", "to check the tire pressure")
            EVRow("zkontrolovat olej", "to check the oil")

            EVSection("Public Transport")
            EVRow("jízdenka na autobus", "bus ticket")
            EVRow("autobusové nádraží", "bus station")
            EVRow("jízdenka na vlak", "train ticket")
            EVRow("nádraží / vlakové nádraží", "train station", "nádraží alone usually means the train station")
            EVRow("nástupiště", "train platform", "kolej = the track number on the platform")

            EVSection("Bikes & Getting Around")
            EVRow("kolo", "bike")
            EVRow("helma / přilba", "helmet", "helma is colloquial")
            EVRow("jezdit na kole", "to ride a bike")
            EVRow("stopovat / jet stopem", "to hitchhike")

            EVSection("Home")
            EVRow("bydlet", "to live (reside somewhere)", "Bydlím v Praze. — I live in Prague.")
            EVRow("žít", "to live (be alive / live one's life)", "Žiju naplno. — I live life to the full.")
            EVRow("byt", "apartment")
            EVRow("dům", "house")
            EVRow("čtvrť / sousedství", "neighborhood", "čtvrť = part of town; sousedství = the people/area around you")
            EVRow("ve třetím patře", "on the 3rd floor")
            EVNote("Floors: Czech counts the ground floor (přízemí) as zero. So Czech 'první patro' = American 2nd floor, and an American '3rd floor' = druhé patro in Czech.")
            EVRow("dveře", "door", "plural only, even for one door: Zavři dveře.")
            EVRow("okno", "window")
            EVRow("kuchyně", "kitchen")
            EVRow("obývák / obývací pokoj", "living room", "obývák is colloquial")
            EVRow("ložnice", "bedroom")
            EVRow("postel", "bed")
            EVRow("klíče", "keys", "one key = klíč")
            EVRow("ztratit klíče", "to lose the keys")
            EVRow("najít klíče", "to find the keys")

            EVSection("Chores & Bills")
            EVRow("uklízet / uklidit", "to clean / tidy up (a room, the house)")
            EVRow("čistit / vyčistit", "to clean (an object)", "čistit boty — to clean shoes")
            EVRow("daně", "taxes", "one tax = daň")
            EVRow("účty", "bills", "same word as 'bank accounts' and 'restaurant bill'")
            EVRow("platit účty", "to pay bills")

            EVSection("School")
            EVRow("školka / mateřská škola", "kindergarten")
            EVRow("škola", "school")
            EVRow("základní škola", "elementary school")
            EVRow("střední škola", "high school", "gymnázium = academic high school")
            EVRow("univerzita / vysoká škola", "university", "vysoká škola = any higher-education school")
            EVRow("domácí úkol", "homework")
            EVRow("odmaturovat", "to graduate from high school", "maturita = the final high school exam")
            EVRow("dostudovat / absolvovat", "to graduate (finish studies)", "promovat = to have the university graduation ceremony")

            EVSection("Work")
            EVRow("pracovat", "to work")
            EVRow("plat", "salary")
            EVRow("kancelář", "office")

            EVSection("Dating & Family")
            EVRow("chodit s někým", "to date someone", "lit. 'to walk with someone': Chodí spolu rok. — They've been dating for a year.")
            EVRow("přítel / kluk", "boyfriend", "kluk = more casual")
            EVRow("přítelkyně / holka", "girlfriend", "holka = more casual")
            EVRow("vzít si (někoho)", "to marry (someone)", "Vzali se v létě. — They got married in the summer.")
            EVRow("oženit se", "to get married (said of a man)")
            EVRow("vdát se", "to get married (said of a woman)")
            EVRow("manžel", "husband")
            EVRow("manželka", "wife")
            EVRow("rozvést se", "to divorce")
            EVRow("mít děti", "to have kids")

            EVSection("General")
            EVRow("čas", "time", "Nemám čas. — I don't have time.")
            EVRow("vzpomínky", "memories", "one memory = vzpomínka")

            EVSection("Little Everyday Words")
            EVRow("teď / nyní", "now", "teď = everyday; nyní = formal")
            EVRow("tady / tu", "here", "tady is the most common in speech")
            EVRow("skoro / téměř", "almost", "skoro = everyday; téměř = more formal: Skoro jsem zapomněl. — I almost forgot.")
            EVRow("určitě / jistě", "sure / certainly", "Určitě! — Sure!  Jsi si jistý? — Are you sure?")
            EVRow("brzy / brzo", "soon", "also means 'early': Přijdu brzy. — I'll come soon.")
            EVRow("nikdy", "never", "takes a double negative: Nikdy nekouřím. — I never smoke.")
            EVRow("vždy / vždycky", "always", "vždycky is the spoken form")
            EVRow("někdy", "sometime / sometimes", "Přijď někdy. — Come by sometime.")
            EVRow("někde", "somewhere")
            EVRow("někdo", "someone / somebody")
            EVRow("každý / každá / každé", "every / each", "každý den — every day; každá noc — every night")

            EVSection("Clothes & Accessories")
            EVRow("brýle", "eyeglasses", "plural only; sluneční brýle = sunglasses")
            EVRow("kalhoty", "pants / trousers", "plural only, like English 'pants'")
            EVRow("košile", "shirt", "a button-up shirt; tričko = T-shirt")
            EVRow("pásek", "belt")
            EVRow("ponožky", "socks", "one sock = ponožka")
            EVRow("spodní prádlo", "underwear", "trenýrky = boxers; kalhotky = panties")
            EVRow("boty", "shoes", "one shoe = bota")
            EVRow("sandály", "sandals")
            EVRow("klobouk / čepice", "hat", "klobouk = brimmed hat; čepice = cap / winter hat")
            EVRow("sukně", "skirt")
            EVRow("šaty", "dress", "plural only, even for one dress; also means 'clothes' in general")
            EVRow("pyžamo", "pajamas")
            EVRow("bunda / sako", "jacket", "bunda = outdoor jacket; sako = suit jacket / blazer")
            EVRow("deštník", "umbrella")
            EVRow("vlasy", "hair (on the head)", "plural; vlas = a single hair")

            EVSection("Weather & Day / Night")
            EVRow("déšť", "rain", "Prší. — It's raining.")
            EVRow("sníh", "snow", "Sněží. — It's snowing.")
            EVRow("slunce", "sun", "Svítí slunce. — The sun is shining.")
            EVRow("den", "day", "Dobrý den — hello / good day; přes den = during the day")
            EVRow("noc", "night", "v noci = at night; Dobrou noc — good night")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun EVSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun EVNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun EVRow(czech: String, english: String, note: String = "") {
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
