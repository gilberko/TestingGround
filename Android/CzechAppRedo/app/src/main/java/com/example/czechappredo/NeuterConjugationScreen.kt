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
fun NeuterConjugationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Neuter", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
                text = "Below are the full singular and plural declensions of the four standard model words (vzory) for neuter nouns.",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            NECWord(
                noun = "město  (town / city)",
                type = "Neuter, hard",
                singRows = listOf(
                    "1. Nominativ" to "město",
                    "2. Genitiv" to "města",
                    "3. Dativ" to "městu",
                    "4. Akuzativ" to "město",
                    "5. Vokativ" to "město",
                    "6. Lokál" to "městě / městu",
                    "7. Instrumentál" to "městem"
                ),
                plRows = listOf(
                    "1. Nominativ" to "města",
                    "2. Genitiv" to "měst",
                    "3. Dativ" to "městům",
                    "4. Akuzativ" to "města",
                    "5. Vokativ" to "města",
                    "6. Lokál" to "městech",
                    "7. Instrumentál" to "městy"
                )
            )

            NECWord(
                noun = "moře  (sea)",
                type = "Neuter, soft",
                singRows = listOf(
                    "1. Nominativ" to "moře",
                    "2. Genitiv" to "moře",
                    "3. Dativ" to "moři",
                    "4. Akuzativ" to "moře",
                    "5. Vokativ" to "moře",
                    "6. Lokál" to "moři",
                    "7. Instrumentál" to "mořem"
                ),
                plRows = listOf(
                    "1. Nominativ" to "moře",
                    "2. Genitiv" to "moří",
                    "3. Dativ" to "mořím",
                    "4. Akuzativ" to "moře",
                    "5. Vokativ" to "moře",
                    "6. Lokál" to "mořích",
                    "7. Instrumentál" to "moři"
                )
            )

            NECWord(
                noun = "kuře  (chick / chicken)",
                type = "Neuter, t-stem — irregular plural stem kuřat-",
                singRows = listOf(
                    "1. Nominativ" to "kuře",
                    "2. Genitiv" to "kuřete",
                    "3. Dativ" to "kuřeti",
                    "4. Akuzativ" to "kuře",
                    "5. Vokativ" to "kuře",
                    "6. Lokál" to "kuřeti",
                    "7. Instrumentál" to "kuřetem"
                ),
                plRows = listOf(
                    "1. Nominativ" to "kuřata",
                    "2. Genitiv" to "kuřat",
                    "3. Dativ" to "kuřatům",
                    "4. Akuzativ" to "kuřata",
                    "5. Vokativ" to "kuřata",
                    "6. Lokál" to "kuřatech",
                    "7. Instrumentál" to "kuřaty"
                )
            )

            NECWord(
                noun = "stavení  (building / farmhouse)",
                type = "Neuter, -í ending — most singular forms identical",
                singRows = listOf(
                    "1. Nominativ" to "stavení",
                    "2. Genitiv" to "stavení",
                    "3. Dativ" to "stavení",
                    "4. Akuzativ" to "stavení",
                    "5. Vokativ" to "stavení",
                    "6. Lokál" to "stavení",
                    "7. Instrumentál" to "stavením"
                ),
                plRows = listOf(
                    "1. Nominativ" to "stavení",
                    "2. Genitiv" to "stavení",
                    "3. Dativ" to "stavením",
                    "4. Akuzativ" to "stavení",
                    "5. Vokativ" to "stavení",
                    "6. Lokál" to "staveních",
                    "7. Instrumentál" to "staveními"
                ),
                lastWord = true
            )
        }
    }
}

@Composable
private fun NECWord(
    noun: String,
    type: String,
    singRows: List<Pair<String, String>>,
    plRows: List<Pair<String, String>>,
    lastWord: Boolean = false
) {
    Text(text = noun, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Text(text = type, fontSize = 13.sp, color = Color.Gray, fontStyle = FontStyle.Italic)
    Spacer(modifier = Modifier.height(8.dp))
    NECTable("Singular", singRows)
    Spacer(modifier = Modifier.height(12.dp))
    NECTable("Plural", plRows)
    Spacer(modifier = Modifier.height(20.dp))
    if (!lastWord) {
        HorizontalDivider(modifier = Modifier.padding(bottom = 20.dp))
    }
}

@Composable
private fun NECTable(label: String, rows: List<Pair<String, String>>) {
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
