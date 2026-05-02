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
