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
fun FeminineConjugationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Feminine", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
                text = "Below are the full singular and plural declensions of the four standard model words (vzory) for feminine nouns.",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            FECWord(
                noun = "žena  (woman / wife)",
                type = "Feminine, hard",
                singRows = listOf(
                    "1. Nominativ" to "žena",
                    "2. Genitiv" to "ženy",
                    "3. Dativ" to "ženě",
                    "4. Akuzativ" to "ženu",
                    "5. Vokativ" to "ženo",
                    "6. Lokál" to "ženě",
                    "7. Instrumentál" to "ženou"
                ),
                plRows = listOf(
                    "1. Nominativ" to "ženy",
                    "2. Genitiv" to "žen",
                    "3. Dativ" to "ženám",
                    "4. Akuzativ" to "ženy",
                    "5. Vokativ" to "ženy",
                    "6. Lokál" to "ženách",
                    "7. Instrumentál" to "ženami"
                )
            )

            FECWord(
                noun = "růže  (rose)",
                type = "Feminine, soft",
                singRows = listOf(
                    "1. Nominativ" to "růže",
                    "2. Genitiv" to "růže",
                    "3. Dativ" to "růži",
                    "4. Akuzativ" to "růži",
                    "5. Vokativ" to "růže",
                    "6. Lokál" to "růži",
                    "7. Instrumentál" to "růží"
                ),
                plRows = listOf(
                    "1. Nominativ" to "růže",
                    "2. Genitiv" to "růží",
                    "3. Dativ" to "růžím",
                    "4. Akuzativ" to "růže",
                    "5. Vokativ" to "růže",
                    "6. Lokál" to "růžích",
                    "7. Instrumentál" to "růžemi"
                )
            )

            FECWord(
                noun = "píseň  (song)",
                type = "Feminine, soft zero-ending",
                singRows = listOf(
                    "1. Nominativ" to "píseň",
                    "2. Genitiv" to "písně",
                    "3. Dativ" to "písni",
                    "4. Akuzativ" to "píseň",
                    "5. Vokativ" to "písni",
                    "6. Lokál" to "písni",
                    "7. Instrumentál" to "písní"
                ),
                plRows = listOf(
                    "1. Nominativ" to "písně",
                    "2. Genitiv" to "písní",
                    "3. Dativ" to "písním",
                    "4. Akuzativ" to "písně",
                    "5. Vokativ" to "písně",
                    "6. Lokál" to "písních",
                    "7. Instrumentál" to "písněmi"
                )
            )

            FECWord(
                noun = "kost  (bone)",
                type = "Feminine, i-stem",
                singRows = listOf(
                    "1. Nominativ" to "kost",
                    "2. Genitiv" to "kosti",
                    "3. Dativ" to "kosti",
                    "4. Akuzativ" to "kost",
                    "5. Vokativ" to "kosti",
                    "6. Lokál" to "kosti",
                    "7. Instrumentál" to "kostí"
                ),
                plRows = listOf(
                    "1. Nominativ" to "kosti",
                    "2. Genitiv" to "kostí",
                    "3. Dativ" to "kostem",
                    "4. Akuzativ" to "kosti",
                    "5. Vokativ" to "kosti",
                    "6. Lokál" to "kostech",
                    "7. Instrumentál" to "kostmi"
                ),
                lastWord = true
            )
        }
    }
}

@Composable
private fun FECWord(
    noun: String,
    type: String,
    singRows: List<Pair<String, String>>,
    plRows: List<Pair<String, String>>,
    lastWord: Boolean = false
) {
    Text(text = noun, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Text(text = type, fontSize = 13.sp, color = Color.Gray, fontStyle = FontStyle.Italic)
    Spacer(modifier = Modifier.height(8.dp))
    FECTable("Singular", singRows)
    Spacer(modifier = Modifier.height(12.dp))
    FECTable("Plural", plRows)
    Spacer(modifier = Modifier.height(20.dp))
    if (!lastWord) {
        HorizontalDivider(modifier = Modifier.padding(bottom = 20.dp))
    }
}

@Composable
private fun FECTable(label: String, rows: List<Pair<String, String>>) {
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
