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
fun PeopleAndFamilyScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("People & Family", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
            PFSection("People")
            PFRow("muž", "man", "m.")
            PFRow("žena", "woman", "f.")
            PFRow("kluk / chlapec", "boy", "m. — kluk is colloquial, chlapec is formal")
            PFRow("holka / dívka", "girl", "f. — holka is colloquial, dívka is formal")
            PFRow("člověk / osoba", "person", "člověk m. = human being (general); osoba f. = individual (formal contexts)")
            PFRow("miminko", "baby", "n.")
            PFRow("kojenec", "infant", "m. — specifically a nursing infant")
            PFRow("dítě", "kid / child", "n.")

            PFSection("Immediate Family")
            PFRow("manžel", "husband", "m.")
            PFRow("manželka", "wife", "f.")
            PFRow("syn", "son", "m.")
            PFRow("dcera", "daughter", "f.")
            PFRow("bratr", "brother", "m.")
            PFRow("sestra", "sister", "f.")

            PFSection("Extended Family")
            PFRow("dědeček", "grandfather", "m.")
            PFRow("babička", "grandmother", "f.")
            PFRow("strýc", "uncle", "m. — same word regardless of which side of the family")
            PFRow("teta", "aunt", "f. — same word regardless of which side of the family")
            PFRow("bratranec", "cousin (male)", "m. — Czech uses separate words for male and female cousins")
            PFRow("sestřenice", "cousin (female)", "f.")
            PFRow("synovec", "nephew", "m.")
            PFRow("neteř", "niece", "f.")
            PFRow("švagr", "brother-in-law", "m.")
            PFRow("švagrová", "sister-in-law", "f.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PFSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun PFRow(czech: String, english: String, note: String = "") {
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
