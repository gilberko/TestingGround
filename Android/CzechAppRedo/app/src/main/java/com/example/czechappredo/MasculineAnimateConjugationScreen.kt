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
fun MasculineAnimateConjugationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Masculine Animate", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
                text = "Masculine animate nouns name people or animals. Below are the full singular and plural declensions of the four standard model words (vzory) for this category.",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            MACWord(
                noun = "pán  (lord / sir / gentleman)",
                type = "Masculine animate, hard",
                singRows = listOf(
                    "1. Nominativ" to "pán",
                    "2. Genitiv" to "pána",
                    "3. Dativ" to "pánovi / pánu",
                    "4. Akuzativ" to "pána",
                    "5. Vokativ" to "pane",
                    "6. Lokál" to "pánovi / pánu",
                    "7. Instrumentál" to "pánem"
                ),
                plRows = listOf(
                    "1. Nominativ" to "pánové / páni",
                    "2. Genitiv" to "pánů",
                    "3. Dativ" to "pánům",
                    "4. Akuzativ" to "pány",
                    "5. Vokativ" to "pánové / páni",
                    "6. Lokál" to "pánech",
                    "7. Instrumentál" to "pány"
                )
            )

            MACWord(
                noun = "muž  (man / husband)",
                type = "Masculine animate, soft",
                singRows = listOf(
                    "1. Nominativ" to "muž",
                    "2. Genitiv" to "muže",
                    "3. Dativ" to "mužovi / muži",
                    "4. Akuzativ" to "muže",
                    "5. Vokativ" to "muži",
                    "6. Lokál" to "mužovi / muži",
                    "7. Instrumentál" to "mužem"
                ),
                plRows = listOf(
                    "1. Nominativ" to "muži / mužové",
                    "2. Genitiv" to "mužů",
                    "3. Dativ" to "mužům",
                    "4. Akuzativ" to "muže",
                    "5. Vokativ" to "muži / mužové",
                    "6. Lokál" to "mužích",
                    "7. Instrumentál" to "muži"
                )
            )

            MACWord(
                noun = "předseda  (chairman)",
                type = "Masculine animate, -a ending",
                singRows = listOf(
                    "1. Nominativ" to "předseda",
                    "2. Genitiv" to "předsedy",
                    "3. Dativ" to "předsedovi",
                    "4. Akuzativ" to "předsedu",
                    "5. Vokativ" to "předsedo",
                    "6. Lokál" to "předsedovi",
                    "7. Instrumentál" to "předsedou"
                ),
                plRows = listOf(
                    "1. Nominativ" to "předsedové",
                    "2. Genitiv" to "předsedů",
                    "3. Dativ" to "předsedům",
                    "4. Akuzativ" to "předsedy",
                    "5. Vokativ" to "předsedové",
                    "6. Lokál" to "předsedech",
                    "7. Instrumentál" to "předsedy"
                )
            )

            MACWord(
                noun = "soudce  (judge)",
                type = "Masculine animate, -e ending",
                singRows = listOf(
                    "1. Nominativ" to "soudce",
                    "2. Genitiv" to "soudce",
                    "3. Dativ" to "soudcovi / soudci",
                    "4. Akuzativ" to "soudce",
                    "5. Vokativ" to "soudce",
                    "6. Lokál" to "soudcovi / soudci",
                    "7. Instrumentál" to "soudcem"
                ),
                plRows = listOf(
                    "1. Nominativ" to "soudci / soudcové",
                    "2. Genitiv" to "soudců",
                    "3. Dativ" to "soudcům",
                    "4. Akuzativ" to "soudce",
                    "5. Vokativ" to "soudci / soudcové",
                    "6. Lokál" to "soudcích",
                    "7. Instrumentál" to "soudci"
                ),
                lastWord = true
            )
        }
    }
}

@Composable
private fun MACWord(
    noun: String,
    type: String,
    singRows: List<Pair<String, String>>,
    plRows: List<Pair<String, String>>,
    lastWord: Boolean = false
) {
    Text(text = noun, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Text(text = type, fontSize = 13.sp, color = Color.Gray, fontStyle = FontStyle.Italic)
    Spacer(modifier = Modifier.height(8.dp))
    MACTable("Singular", singRows)
    Spacer(modifier = Modifier.height(12.dp))
    MACTable("Plural", plRows)
    Spacer(modifier = Modifier.height(20.dp))
    if (!lastWord) {
        HorizontalDivider(modifier = Modifier.padding(bottom = 20.dp))
    }
}

@Composable
private fun MACTable(label: String, rows: List<Pair<String, String>>) {
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
