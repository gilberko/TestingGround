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

            PPSection("Genitive (2nd case)")
            PPRow("z / ze", "from (a place)",
                example = "Jsem z Prahy.", translation = "I'm from Prague.")
            PPRow("od", "from (a person or time)",
                example = "Mám to od kamaráda.", translation = "I have it from a friend.")
            PPRow("do", "to / into (a destination)",
                example = "Jedeme do Brna.", translation = "We're going to Brno.")
            PPRow("bez", "without",
                example = "Kávu bez cukru, prosím.", translation = "Coffee without sugar, please.")
            PPRow("u", "at / by (someone's place or location)",
                example = "Jsem u lékaře.", translation = "I'm at the doctor's.")
            PPRow("vedle", "next to / beside",
                example = "Bydlím vedle školy.", translation = "I live next to the school.")
            PPRow("kolem", "around / approximately",
                example = "Přijdu kolem deseti.", translation = "I'll come around ten.")
            PPNote("do means 'to' and is the standard destination preposition. It is most commonly used for buildings and enclosed places (do školy, do obchodu, do kina), but also applies to countries, cities, parks, and most destinations in general — not only closed spaces.")
            PPNote("Examples: do parku (to the park) · do Prahy (to Prague) · do Rakouska (to Austria) · do města (to the city) · do školy (to school) · do obchodu (to the shop)")
            PPNote("Is this the train to Brno? → Je to vlak do Brna?")

            PPSection("Dative (3rd case)")
            PPRow("k / ke", "toward / to (a person)",
                example = "Jdu k lékaři.", translation = "I'm going to the doctor.")
            PPRow("díky", "thanks to",
                example = "Díky tobě.", translation = "Thanks to you.")
            PPRow("kvůli", "because of",
                example = "Kvůli počasí jsme doma.", translation = "Because of the weather we're home.")
            PPNote("k / ke is used for motion toward a person or a point — not a building you enter.")

            PPSection("Accusative (4th case)")
            PPRow("na", "onto / to (direction — stations, offices, events)",
                example = "Jdu na nádraží.", translation = "I'm going to the station.")
            PPRow("přes", "through / across / via",
                example = "Jedeme přes Prahu.", translation = "We're going via Prague.")
            PPRow("za", "around / behind (motion) / in (time)",
                example = "Za rohem je kavárna.", translation = "There's a café around the corner.")
            PPRow("pro", "for (fetching or purpose)",
                example = "Jdu pro kávu.", translation = "I'm going to get coffee.")
            PPNote("na + Accusative (direction) pairs with na + Locative (position): Jdu na poštu. → Jsem na poště.")

            PPSection("Locative (6th case)")
            PPRow("v / ve", "in (a place)",
                example = "Bydlím v Praze.", translation = "I live in Prague.")
            PPRow("na", "on / at (a place — for certain nouns)",
                example = "Jsem na poště.", translation = "I'm at the post office.")
            PPRow("o", "about",
                example = "Mluvím o tobě.", translation = "I'm talking about you.")
            PPRow("po", "after / around (a place)",
                example = "Chodíme po městě.", translation = "We're walking around the city.")
            PPRow("při", "while / during",
                example = "Poslouchám hudbu při práci.", translation = "I listen to music while working.")
            PPNote("v/ve vs. na for location depends on the noun and must be learned: ve škole, na poště, v nemocnici, na nádraží.")

            PPSection("Instrumental (7th case)")
            PPRow("s / se", "with (together)",
                example = "Jdu s přítelem.", translation = "I'm going with a friend.")
            PPRow("před", "in front of / before",
                example = "Čekám před domem.", translation = "I'm waiting in front of the house.")
            PPRow("za", "behind (position)",
                example = "Auto je za domem.", translation = "The car is behind the house.")
            PPRow("pod", "under (position)",
                example = "Kočka je pod stolem.", translation = "The cat is under the table.")
            PPRow("nad", "above / over (position)",
                example = "Lampa je nad stolem.", translation = "The lamp is above the table.")
            PPRow("mezi", "between / among",
                example = "Sedím mezi přáteli.", translation = "I'm sitting among friends.")
            PPNote("za with Accusative = motion (za roh = around the corner); za with Instrumental = position (za domem = behind the house).")

            // ── Na vs. Do vs. K ────────────────────────────────────────
            PPSection("Na vs. Do vs. K")
            PPNote("All three can translate as \"to,\" but they split Czech destinations into three groups. do (+ genitive) is for enclosed spaces and most general destinations. na (+ accusative) is for open spaces, surfaces, events, and a set of institutions/geographic names that are simply conventional exceptions. k/ke (+ dative) is different again — it means motion toward a person or a point, without necessarily arriving inside anything.")
            PPRow("do", "into / to — enclosed spaces, most destinations",
                example = "do školy · do práce · do obchodu · do kina · do Prahy · do parku · do zahraničí",
                translation = "to school · to work · to the shop · to the cinema · to Prague · to the park · abroad")
            PPRow("na", "onto / to — open spaces, events, fixed exceptions",
                example = "na poštu · na nádraží · na letiště · na univerzitu · na koncert · na oběd · na Slovensko · na hory",
                translation = "to the post office · to the station · to the airport · to university · to a concert · to lunch · to Slovakia · to the mountains")
            PPRow("k / ke", "toward — approaching a person or point, not entering",
                example = "Jdu k lékaři. · Jedeme k moři. · Jdu k oknu. · Přijeď k nám.",
                translation = "I'm going to the doctor's. · We're heading to the seaside. · I'm going to the window. · Come to our place.")
            PPNote("Compare all three on the same kind of target: Jdu do nemocnice. (into the hospital building) vs. Jdu na pohotovost. (to the emergency department — an event/institution exception) vs. Jdu k lékaři. (to see the doctor — approaching a person, not necessarily entering any building).")
            PPNote("Mnemonic for the matching position preposition, now a 3-way pairing: do pairs with v/ve (do školy → jsem ve škole), na pairs with na (na poštu → jsem na poště), k pairs with u (k babičce → jsem u babičky).")
            PPNote("There isn't a fully reliable semantic rule for which nouns take na — it's largely idiomatic and gets learned together with the noun, though open spaces, events, and islands/mountains lean toward na.")

            // ── Na vs. V/Ve ────────────────────────────────────────────
            PPSection("Na vs. V/Ve — Which Locative Preposition?")
            PPNote("Both mean roughly \"in\" or \"at\" for a static location, but the split follows the same shape as the destination prepositions above. v/ve (+ locative) is for inside an enclosed, bounded space. na (+ locative) is for a surface, an open area, an event, or one of the same fixed institutional/geographic exceptions already seen above — just in their static, \"being there\" form instead of the \"going there\" form.")
            PPRow("v / ve", "in — enclosed, bounded space",
                example = "V Praze · V restauraci · V kině · V bance · V nemocnici",
                translation = "In Prague · In the restaurant · In the cinema · In the bank · In the hospital")
            PPRow("na", "on / at — open space, event, fixed exception",
                example = "Na Slovensku · Na poště · Na koncertě · Na univerzitě · Na horách",
                translation = "In Slovakia · At the post office · At the concert · At university · In the mountains")
            PPNote("Just like the na-destination exceptions, the na-vs-v split for institutions is largely idiomatic and gets learned together with the noun, not derived from a rule — memorize the phrase as one unit rather than translating from English.")

            // ── Z vs. Od ───────────────────────────────────────────────
            PPSection("Z vs. Od — Two Ways to Say \"From\"")
            PPNote("Both mean \"from\" and both take the genitive, but they split the same way do/na/k split \"to.\" z/ze is the return trip of do — out of an enclosed space, or the origin something came from within. od is the return trip of k/u — away from a point, a person, or the outside of a place — plus a separate use for \"since\" a point in time.")
            PPRow("z / ze", "from — out of an enclosed space, origin",
                example = "Jdu z kina. · Jsem z Prahy. · Sklenice je ze skla.",
                translation = "I'm coming from inside the cinema. · I'm from Prague. · The glass is made of/from glass.")
            PPRow("od", "from — away from a point/person, or \"since\"",
                example = "Jdu od kina. · Jdu od Elišky. · Mám dárek od kamaráda. · Pracuju tu od pondělí.",
                translation = "I'm coming from outside the cinema. · I'm coming from Eliška's place. · I have a gift from a friend. · I've worked here since Monday.")
            PPNote("Important rule: od is always used for \"from a person\" — Jdu od Elišky. is correct, Jdu z Elišky. is not, even though z is the more general word for \"from.\"")
            PPNote("Contrast do vs. k from the section above: Jdu z kina. (I was inside, now I'm out — pairs with do kina) vs. Jdu od kina. (I was near/outside it, now I'm leaving that area — pairs with k/u kina).")

            // ── All Prepositions at a Glance ──────────────────────────
            PPSection("All Prepositions at a Glance")
            PPNote("Every preposition from above, in one place, sorted alphabetically. Not grouped by case — see the sections above for grammar and examples.")
            PPMasterTable(
                listOf(
                    Triple("bez", "Genitive", "without"),
                    Triple("díky", "Dative", "thanks to"),
                    Triple("do", "Genitive", "to / into (a destination)"),
                    Triple("k / ke", "Dative", "toward / to (a person)"),
                    Triple("kolem", "Genitive", "around / approximately"),
                    Triple("kvůli", "Dative", "because of"),
                    Triple("mezi", "Instrumental", "between / among"),
                    Triple("na", "Accusative", "onto / to (direction)"),
                    Triple("na", "Locative", "on / at (a place)"),
                    Triple("nad", "Instrumental", "above / over"),
                    Triple("o", "Locative", "about"),
                    Triple("od", "Genitive", "from (a person or time)"),
                    Triple("po", "Locative", "after / around (a place)"),
                    Triple("pod", "Instrumental", "under"),
                    Triple("pro", "Accusative", "for (fetching or purpose)"),
                    Triple("před", "Instrumental", "in front of / before"),
                    Triple("přes", "Accusative", "through / across / via"),
                    Triple("s / se", "Instrumental", "with"),
                    Triple("u", "Genitive", "at / by (someone's place)"),
                    Triple("v / ve", "Locative", "in (a place)"),
                    Triple("vedle", "Genitive", "next to / beside"),
                    Triple("z / ze", "Genitive", "from (a place)"),
                    Triple("za", "Accusative", "around / behind (motion) / in (time)"),
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
private fun PPRow(prep: String, meaning: String, example: String, translation: String) {
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)) {
                append(prep)
            }
            withStyle(SpanStyle(fontSize = 15.sp, color = Color.DarkGray)) {
                append("  —  $meaning")
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
