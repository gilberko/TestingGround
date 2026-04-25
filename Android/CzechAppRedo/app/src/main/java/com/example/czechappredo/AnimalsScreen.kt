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
fun AnimalsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Animals", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
            ANSection("Birds")
            ANRow("pták", "bird", "m.")
            ANRow("orel", "eagle", "m.")
            ANRow("vrabec", "sparrow", "m.")
            ANRow("papoušek", "parrot", "m.")

            ANSection("Sea & Water Animals")
            ANRow("delfín", "dolphin", "m.")
            ANRow("ryba", "fish", "f.")
            ANRow("žralok", "shark", "m.")
            ANRow("velryba", "whale", "f.")
            ANRow("úhoř", "eel", "m.")

            ANSection("Pets & Farm Animals")
            ANRow("pes", "dog", "m. — pl. psi")
            ANRow("kočka", "cat", "f.")
            ANRow("kůň", "horse", "m. — pl. koně")
            ANRow("osel", "donkey", "m.")
            ANRow("ovce", "sheep", "f.")

            ANSection("African & Asian Animals")
            ANRow("lev", "lion", "m.")
            ANRow("tygr", "tiger", "m.")
            ANRow("leopard", "leopard", "m.")
            ANRow("hyena", "hyena", "f.")
            ANRow("žirafa", "giraffe", "f.")
            ANRow("gorila", "gorilla", "f.")
            ANRow("opice", "monkey", "f.")
            ANRow("nosorožec", "rhinoceros", "m.")
            ANRow("hroch", "hippopotamus", "m.")
            ANRow("zebra", "zebra", "f.")

            ANSection("Reptiles & Bugs")
            ANRow("had", "snake", "m.")
            ANRow("krokodýl", "crocodile", "m.")
            ANRow("aligátor", "alligator", "m.")
            ANRow("škorpion", "scorpion", "m.")
            ANRow("pavouk", "spider", "m.")

            ANSection("Other Animals")
            ANRow("medvěd", "bear", "m.")
            ANRow("los", "moose", "m.")
            ANRow("sob", "reindeer", "m.")
            ANRow("bobr", "beaver", "m.")
            ANRow("myš", "mouse", "f.")
            ANRow("krysa", "rat", "f.")
            ANRow("klokan", "kangaroo", "m.")
            ANRow("koala", "koala", "m.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ANSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun ANRow(czech: String, english: String, note: String = "") {
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
