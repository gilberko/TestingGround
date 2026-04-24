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
fun MovementScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movement", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            MVSection("Distance")
            MVRow("daleko", "far")
            MVRow("blízko", "near / close")
            MVRow("vedle", "next to / beside", note = "Takes Genitive: vedle obchodu = next to the store.")
            MVRow("není to příliš daleko", "not too far")
            MVRow("je to trochu daleko", "it's a bit far")
            MVRow("asi pět minut", "about five minutes (away)")

            MVSection("Cardinal Directions")
            MVRow("sever", "north")
            MVRow("jih", "south")
            MVRow("východ", "east")
            MVRow("západ", "west")

            MVSection("Relative Directions")
            MVNote("Motion (direction you move): rovně, přímo, doleva, doprava. Position (where something is): vlevo / nalevo, vpravo / napravo.")
            MVRow("rovně", "straight ahead (motion)")
            MVRow("přímo", "straight / directly (often interchangeable with rovně)")
            MVRow("pořád rovně", "keep going straight")
            MVRow("dopředu", "forward")
            MVRow("doleva", "to the left (motion)")
            MVRow("doprava", "to the right (motion)")
            MVRow("vlevo", "on the left (position)")
            MVRow("nalevo", "on the left (position, synonym for vlevo)")
            MVRow("vpravo", "on the right (position)")
            MVRow("napravo", "on the right (position, synonym for vpravo)")

            MVSection("Up, Down & Back")
            MVRow("nahoru", "up / upward (direction of motion)")
            MVRow("nahoře", "up there / upstairs (position)")
            MVRow("dolů", "down / downward (direction of motion)")
            MVRow("dole", "down there / downstairs (position)")
            MVRow("zpět", "back (more formal)")
            MVRow("zpátky", "back (colloquial, very common)")
            MVNote("zpět and zpátky are interchangeable; zpátky is more colloquial.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun MVSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun MVNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

@Composable
private fun MVRow(czech: String, english: String, note: String = "") {
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
