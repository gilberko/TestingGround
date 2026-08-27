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
fun SuperPowersAndMagicScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Super Powers and Magic", fontSize = 16.sp, fontWeight = FontWeight.Bold) },
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

            SMSection("People")
            SMRow("kouzelník", "magician")
            SMRow("čaroděj", "wizard")
            SMRow("čarodějnice", "witch")
            SMRow("superhrdina", "superhero")
            SMRow("superzloduch", "supervillain")

            SMSection("Core Concepts")
            SMRow("kouzlo", "magic (a spell/trick)")
            SMRow("magie", "magic (the general force)")
            SMRow("superschopnosti", "super powers")
            SMRow("zbraň", "weapon")
            SMRow("kouzelná hůlka", "magic wand")
            SMRow("elektřina", "electricity")
            SMRow("blesk", "lightning")

            SMSection("Abilities — Movement")
            SMRow("létat", "to fly")
            SMRow("levitovat", "to levitate")
            SMRow("pohybovat se bleskovou rychlostí", "to run / move very quickly")
            SMRow("superrychlost", "super speed")

            SMSection("Abilities — Power & Force")
            SMRow("super síla / nadlidská síla", "super strength")
            SMRow("super chytrý / geniální", "super smart")
            SMRow("nezničitelný / velmi odolný", "super durable")
            SMRow("ničit / zničit", "to destroy")
            SMRow("tvořit / vytvořit", "to create")
            SMRow("střílet laser", "to shoot a laser")
            SMRow("vrhat blesky", "to shoot lightning")

            SMSection("Abilities — Mind & Time")
            SMRow("číst myšlenky", "to read minds")
            SMRow("ovládat něčí mysl", "to control someone's mind")
            SMRow("ovládat předměty myslí / telekineze", "to control objects (telekinesis)")
            SMRow("zastavit čas", "to freeze time")
            SMRow("vrátit čas", "to reverse time")

            SMSection("Abilities — Appearing & Multiplying")
            SMRow("zmizet", "to disappear")
            SMRow("znovu se objevit", "to reappear")
            SMRow("množit se", "to multiply")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SMSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun SMRow(czech: String, english: String, note: String = "") {
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
