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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasculineInanimateConjugationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Masculine Inanimate", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
            Text(
                text = "Masculine inanimate nouns name things, not people or animals. Below are the full singular and plural declensions of the two standard model words (vzory) for this category.",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            MICWord(
                noun = "hrad  (castle)",
                type = "Masculine inanimate, hard",
                singRows = listOf(
                    "1. Nominativ" to "hrad",
                    "2. Genitiv" to "hradu",
                    "3. Dativ" to "hradu",
                    "4. Akuzativ" to "hrad",
                    "5. Vokativ" to "hrade",
                    "6. Lokál" to "hradě / hradu",
                    "7. Instrumentál" to "hradem"
                ),
                plRows = listOf(
                    "1. Nominativ" to "hrady",
                    "2. Genitiv" to "hradů",
                    "3. Dativ" to "hradům",
                    "4. Akuzativ" to "hrady",
                    "5. Vokativ" to "hrady",
                    "6. Lokál" to "hradech",
                    "7. Instrumentál" to "hrady"
                )
            )

            MICWord(
                noun = "stroj  (machine)",
                type = "Masculine inanimate, soft",
                singRows = listOf(
                    "1. Nominativ" to "stroj",
                    "2. Genitiv" to "stroje",
                    "3. Dativ" to "stroji",
                    "4. Akuzativ" to "stroj",
                    "5. Vokativ" to "stroji",
                    "6. Lokál" to "stroji",
                    "7. Instrumentál" to "strojem"
                ),
                plRows = listOf(
                    "1. Nominativ" to "stroje",
                    "2. Genitiv" to "strojů",
                    "3. Dativ" to "strojům",
                    "4. Akuzativ" to "stroje",
                    "5. Vokativ" to "stroje",
                    "6. Lokál" to "strojích",
                    "7. Instrumentál" to "stroji"
                ),
                lastWord = true
            )
        }
    }
}

@Composable
private fun MICWord(
    noun: String,
    type: String,
    singRows: List<Pair<String, String>>,
    plRows: List<Pair<String, String>>,
    lastWord: Boolean = false
) {
    Text(text = noun, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Text(text = type, fontSize = 13.sp, color = Color.Gray, fontStyle = FontStyle.Italic)
    Spacer(modifier = Modifier.height(8.dp))
    MICTable("Singular", singRows)
    Spacer(modifier = Modifier.height(12.dp))
    MICTable("Plural", plRows)
    Spacer(modifier = Modifier.height(20.dp))
    if (!lastWord) {
        HorizontalDivider(modifier = Modifier.padding(bottom = 20.dp))
    }
}

@Composable
private fun MICTable(label: String, rows: List<Pair<String, String>>) {
    Text(text = label, fontSize = 13.sp, color = Color.Gray, fontStyle = FontStyle.Italic)
    Spacer(modifier = Modifier.height(4.dp))
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Case",
            modifier = Modifier.weight(1f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = ButtonBlue
        )
        Text(
            text = "Form",
            modifier = Modifier.weight(0.8f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = ButtonBlue
        )
    }
    HorizontalDivider(modifier = Modifier.padding(vertical = 3.dp))
    rows.forEach { (caseName, form) ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp)
        ) {
            Text(text = caseName, modifier = Modifier.weight(1f), fontSize = 15.sp, color = Color.DarkGray)
            Text(
                text = form,
                modifier = Modifier.weight(0.8f),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}
