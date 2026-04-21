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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NounsAndDemonstrativesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("About Nouns And Basic Demonstratives", fontSize = 16.sp, fontWeight = FontWeight.Bold) },
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

            // ── What Is A Noun? ───────────────────────────────────────────
            NADSectionHeader("What Is A Noun?")
            NADNote("A noun names a thing, a person, a place, or an idea — muž (man), Praha (Prague), svoboda (freedom). In Czech, every noun has a grammatical gender: masculine, feminine, or neuter. Gender affects how other words in the sentence — adjectives, pronouns, demonstratives — change their endings.")

            // ── Guessing Gender From The Ending ──────────────────────────
            NADSectionHeader("Guessing Gender From The Ending")
            NADNote("You can often — not always — tell a noun's gender from its last letter or letters:")
            NADRow("Consonant ending", "usually masculine  →  muž (man), hrad (castle), den (day)")
            NADRow("Ends in -a", "usually feminine  →  žena (woman), kniha (book), Praha (Prague)")
            NADRow("Ends in -o", "usually neuter  →  město (city), okno (window), auto (car)")
            NADRow("Ends in -e", "neuter or feminine — must be learned individually  →  moře (sea, neuter), růže (rose, feminine)")
            NADNote("Exceptions exist. For example, táta (dad) ends in -a but is masculine. When in doubt, learn the gender together with the word itself.")

            // ── Demonstratives: This / That ───────────────────────────────
            NADSectionHeader("Demonstratives: This / That")
            NADNote("Czech uses ten, ta, to to mean both \"this\" and \"that\" in everyday speech. They also do the job of a definite article — Czech has no word for \"the\", so ten / ta / to often fills that role.")
            NADRow("ten", "masculine  →  ten muž  (the/that man)")
            NADRow("ta", "feminine  →  ta žena  (the/that woman)")
            NADRow("to", "neuter  →  to město  (the/that city)")
            NADNote("For a more precise \"this\" (something nearby), Czech uses tento / tato / toto, or in everyday speech the colloquial forms tenhle / tahle / tohle.")
            NADRow("tenhle muž", "this man (right here)")
            NADRow("tahle žena", "this woman (right here)")
            NADRow("tohle město", "this city (right here)")

            // ── Plural Demonstratives ────────────────────────────────────
            NADSectionHeader("Plural Demonstratives")
            NADNote("In the plural the demonstrative changes again. There are three forms, shared across genders:")
            NADRow("ti", "masculine animate plural  →  ti muži  (those men)")
            NADRow("ty", "feminine plural  →  ty ženy  (those women)")
            NADRow("ty", "masculine inanimate plural  →  ty hrady  (those castles)")
            NADRow("ta", "neuter plural  →  ta města  (those cities)")
            NADNote("Note: the noun endings also change in the plural. The full patterns are covered in the Cases screen.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun NADSectionHeader(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun NADNote(text: String) {
    Text(
        text = text,
        fontSize = 15.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun NADRow(czech: String, english: String) {
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)) {
                append(czech)
            }
            withStyle(SpanStyle(fontSize = 15.sp, color = Color.DarkGray)) {
                append("  —  $english")
            }
        },
        modifier = Modifier.padding(vertical = 3.dp)
    )
}
