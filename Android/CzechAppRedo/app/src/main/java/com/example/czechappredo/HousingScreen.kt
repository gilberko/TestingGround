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
fun HousingScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Housing", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // ── Rooms and Areas ───────────────────────────────────────────
            HSSection("Rooms and Areas")
            HSRow("pokoj", "room", "m.")
            HSRow("kuchyně", "kitchen", "f.")
            HSRow("ložnice", "bedroom", "f.")
            HSRow("obývací pokoj", "living room", "m.")
            HSRow("koupelna", "bathroom", "f. — where you bathe/shower; may not include the toilet")
            HSRow("záchod / toaleta", "toilet room", "m. / f. — often a separate room from the bathroom in Czech apartments")
            HSRow("garáž", "garage", "f.")
            HSRow("balkon", "balcony", "m.")
            HSRow("střecha", "roof", "f.")

            // ── Floors ────────────────────────────────────────────────────
            HSSection("Floors")
            HSNote("Czech floor numbering differs from the US system: Czech přízemí = US 1st floor, Czech 1st floor (první patro) = US 2nd floor, and so on.")
            HSRow("podlaha", "floor (the surface)", "f.")
            HSRow("přízemí", "ground floor", "n. — equals the US \"first floor\"")
            HSRow("první patro", "first floor", "n. — equals the US \"second floor\"")
            HSRow("druhé patro", "second floor", "n. — equals the US \"third floor\"")

            // ── Furniture and Items ───────────────────────────────────────
            HSSection("Furniture and Items")
            HSRow("dveře", "door", "pl., f. — always plural in Czech; never used in singular")
            HSRow("okno", "window", "n.")
            HSRow("lampa", "lamp", "f.")
            HSRow("koberec", "rug / carpet", "m.")
            HSRow("pohovka / gauč", "couch / sofa", "f. / m.")
            HSRow("křeslo", "armchair", "n.")
            HSRow("židle", "chair", "f. — plural: židle (same form)")
            HSRow("stůl", "table", "m.")
            HSRow("televize / televizor", "television", "f. / m.")
            HSRow("postel", "bed", "f.")
            HSRow("skříň", "wardrobe / closet", "f.")

            // ── Kitchen and Bathroom ──────────────────────────────────────
            HSSection("Kitchen and Bathroom")
            HSRow("kuchyňská linka", "kitchen unit / fitted kitchen", "f.")
            HSRow("sporák", "stove", "m.")
            HSRow("trouba", "oven", "f. — also means \"pipe/tube\"; context makes it clear")
            HSRow("lednice / lednička", "refrigerator", "f. — lednička is the everyday colloquial form")
            HSRow("mrazák", "freezer", "m.")
            HSRow("mikrovlnka", "microwave", "f. — colloquial; full term: mikrovlnná trouba")
            HSRow("dřez", "kitchen sink", "m.")
            HSRow("ubrus", "tablecloth", "m.")
            HSRow("váza", "vase", "f.")
            HSRow("umyvadlo", "bathroom sink / washbasin / basin", "n.")
            HSRow("vana", "bathtub / bath", "f.")
            HSRow("sprcha", "shower", "f.")
            HSRow("záchod", "toilet (the fixture itself)", "m.")
            HSRow("ručník", "towel", "m. — plural: ručníky")

            // ── Garden ────────────────────────────────────────────────────
            HSSection("Garden")
            HSRow("zahrada", "garden", "f.")
            HSRow("strom", "tree", "m.")
            HSRow("květiny", "flowers", "pl. — singular: květ (m.)")

            // ── Czech Apartment Notation ──────────────────────────────────
            HSSection("Czech Apartment Notation")
            HSNote("Czech apartment listings use shorthand to describe the number of rooms:")
            HSRow("kk", "kuchyňský kout — kitchen nook/corner", "an open kitchen area integrated into a room, NOT a separate room")
            HSRow("+1", "separate kitchen room", "a fully enclosed kitchen room")
            HSRow("3+kk", "3 rooms + open kitchen nook")
            HSRow("3+1", "3 rooms + separate kitchen room")
            HSNote("Generally +kk apartments feel more spacious; +1 gives kitchen privacy.")

            // ── Buying and Renting ────────────────────────────────────────
            HSSection("Buying and Renting")
            HSRow("nájem / nájemné", "rent", "m. / n.")
            HSRow("Kolik je nájemné?", "How much is the rent?")
            HSRow("Nájemné je 10 000 korun za měsíc.", "The rent is 10,000 crowns per month.")
            HSNote("Grammar: \"za\" takes the accusative case. \"měsíc\" (month, masculine inanimate) has the same form in accusative as nominative: za měsíc = per month.")
            HSRow("měsíc", "month (nominative)", "za měsíc = per month (za + accusative)")
            HSRow("koupit", "to buy")
            HSRow("pronajmout si", "to rent (for oneself)")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun HSSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun HSNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun HSRow(czech: String, english: String, note: String = "") {
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
