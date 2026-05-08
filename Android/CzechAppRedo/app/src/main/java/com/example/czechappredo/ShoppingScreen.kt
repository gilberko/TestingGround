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
fun ShoppingScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shopping", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SHSection("Store Types")
            SHRow("obchod / prodejna", "shop / store", "m. / f.")
            SHRow("obchodní centrum", "shopping center / mall", "n. — also: nákupní centrum")
            SHRow("supermarket", "supermarket", "m.")
            SHRow("potraviny", "grocery store", "pl., f. — also the word for food/groceries themselves")
            SHRow("tržiště / trh", "market / bazaar", "n. / m.")

            SHSection("Paying (Placení)")
            SHRow("Kolik to stojí?", "How much does it cost?")
            SHRow("Cena je 500 korun.", "The price is 500 CZK.", "korun = genitive plural of koruna")
            SHRow("Stojí to 500 korun.", "It costs 500 CZK.")
            SHRow("Mohu platit kartou?", "Can I pay with card?", "kartou = instrumental of karta")
            SHRow("Berete kartu? / Přijímáte kartu?", "Do you accept card?", "kartu = accusative of karta")
            SHRow("hotovost", "cash", "f.")
            SHRow("Nemám hotovost.", "I don't have cash.")
            SHRow("Nemám dost hotovosti.", "I don't have enough cash.", "hotovosti = genitive of hotovost, required after dost (enough)")
            SHRow("Máte drobné?", "Do you have change?", "drobné = small change, n. pl.")
            SHRow("účtenka / doklad", "receipt", "f. / m.")

            SHSection("Discounts & Sales (Slevy a výprodeje)")
            SHRow("sleva", "discount", "f.")
            SHRow("Je sleva 30 procent.", "There is a discount of 30 percent.", "procent = genitive plural of procento")
            SHRow("Je výprodej?", "Is there a sale?", "výprodej = clearance sale")
            SHRow("zlevněno", "marked down / on sale", "predicate adjective")
            SHRow("akce", "special offer / deal", "f. — colloquial: V akci = on offer")

            SHSection("Store Hours (Otevírací doba)")
            SHRow("Obchod je otevřený.", "The store is open.")
            SHRow("Obchod je zavřený.", "The store is closed.")
            SHRow("Obchod je otevřený do 20:00.", "The store is open until 20:00.")
            SHRow("Kdy se otvírá obchod?", "When does the store open?", "reflexive passive: se otvírá = opens (itself)")
            SHRow("Kdy se zavírá obchod?", "When does the store close?")

            SHSection("People & Places in a Store")
            SHRow("prodavač / prodavačka", "salesperson", "m. / f.")
            SHRow("zákazník / zákaznice", "customer", "m. / f.")
            SHRow("pokladna", "cashier / checkout / till", "f.")
            SHRow("Kde je pokladna?", "Where is the checkout?")
            SHRow("Kde se platí?", "Where do I pay?", "reflexive passive")
            SHRow("ulička", "aisle", "f. — in a supermarket: ulička č. 3 = aisle 3")

            SHSection("Shopping Phrases")
            SHRow("Hledám …", "I'm looking for …", "followed by accusative: Hledám máslo. = I'm looking for butter.")
            SHRow("Chci to vrátit.", "I want to return it.", "vrátit = to return/give back")
            SHRow("Máte to v jiné velikosti?", "Do you have this in another size?", "velikosti = locative of velikost")
            SHRow("Máte to v jiných barvách?", "Do you have this in other colors?", "barvách = locative plural of barva")
            SHRow("Jaká je to velikost?", "What size is it?")
            SHRow("Mohu si to vyzkoušet?", "Can I try it on?", "vyzkoušet = to try on clothing")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SHSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun SHRow(czech: String, english: String, note: String = "") {
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
