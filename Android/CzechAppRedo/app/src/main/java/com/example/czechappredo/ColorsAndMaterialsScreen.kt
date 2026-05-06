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
fun ColorsAndMaterialsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Colors & Materials", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // ── Colors ────────────────────────────────────────────────────
            CMSection("Colors")
            CMNote("Color words in Czech are adjectives and agree in gender with the noun they describe. The forms given below are masculine / feminine / neuter.")
            CMNote("Example: černý kůň (black horse, masc) — černá kočka (black cat, fem) — černé auto (black car, neut)")
            Spacer(modifier = Modifier.height(4.dp))
            CMRow("barva", "color", "f. — the noun meaning \"color\"; the adjective forms below describe which color")
            CMRow("černý / černá / černé", "black")
            CMRow("bílý / bílá / bílé", "white")
            CMRow("zelený / zelená / zelené", "green")
            CMRow("růžový / růžová / růžové", "pink")
            CMRow("oranžový / oranžová / oranžové", "orange")
            CMRow("červený / červená / červené", "red")
            CMRow("hnědý / hnědá / hnědé", "brown")
            CMRow("modrý / modrá / modré", "blue", "světle modrý / světle modrá / světle modré = light blue")
            CMRow("fialový / fialová / fialové", "purple / violet")
            CMRow("šedý / šedá / šedé", "gray")
            CMRow("tyrkysový / tyrkysová / tyrkysové", "turquoise")
            CMRow("žlutý / žlutá / žluté", "yellow")
            CMRow("zlatý / zlatá / zlaté", "gold", "e.g. zlatá medaile — gold medal")
            CMRow("stříbrný / stříbrná / stříbrné", "silver")
            CMRow("bronzový / bronzová / bronzové", "bronze")

            // ── Materials ─────────────────────────────────────────────────
            CMSection("Materials")
            CMNote("Each material has a noun form and an adjective form. The adjective is used to describe what something is made of and also agrees in gender.")
            Spacer(modifier = Modifier.height(4.dp))
            CMRow("kámen / kamenný", "stone / (made of) stone")
            CMRow("dřevo / dřevěný", "wood / wooden")
            CMRow("hliník / hliníkový", "aluminium / aluminium (adj)")
            CMRow("ocel / ocelový", "steel / (made of) steel")
            CMRow("písek / písčitý", "sand / sandy")
            CMRow("sklo / skleněný", "glass / (made of) glass", "e.g. skleněná láhev — glass bottle")
            CMRow("plast / plastový", "plastic / (made of) plastic", "e.g. plastové hračky — plastic toys")
            CMRow("kov / kovový", "metal / metallic")
            CMRow("papír / papírový", "paper / (made of) paper")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CMSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun CMNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun CMRow(czech: String, english: String, note: String = "") {
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
