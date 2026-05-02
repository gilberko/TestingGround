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
fun DaysOfWeekScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Days of the Week", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            DWSection("Days of the Week")
            DWRow("pondělí", "Monday")
            DWRow("úterý", "Tuesday")
            DWRow("středa", "Wednesday")
            DWRow("čtvrtek", "Thursday")
            DWRow("pátek", "Friday")
            DWRow("sobota", "Saturday")
            DWRow("neděle", "Sunday")

            DWSection("On [day] — v / ve + Accusative")
            DWRow("v pondělí", "on Monday", "pondělí = neuter; Accusative = Nominative — no change")
            DWRow("v úterý", "on Tuesday", "úterý = neuter; Accusative = Nominative — no change")
            DWRow("ve středu", "on Wednesday", "středa (f.) → středu in Accusative; ve before stř- cluster")
            DWRow("ve čtvrtek", "on Thursday", "čtvrtek (m.inanim.) → Accusative = Nominative — no change; ve before čtv- cluster")
            DWRow("v pátek", "on Friday", "pátek (m.inanim.) → Accusative = Nominative — no change")
            DWRow("v sobotu", "on Saturday", "sobota (f.) → sobotu in Accusative")
            DWRow("v neděli", "on Sunday", "neděle (f.) → neděli in Accusative")
            DWNote("ve is used instead of v before words starting with consonant clusters that are hard to pronounce after v: ve středu, ve čtvrtek.")
            DWNote("v/ve does not always take Accusative — it depends on meaning. When expressing location (where something is), it takes Locative: v Praze (in Prague), ve škole (at school), v Brně (in Brno). When expressing time — specifically days of the week and certain time expressions — it takes Accusative: v pondělí, ve středu, v sobotu. Czech treats days of the week as an Accusative time expression, as if you are moving into that day.")
            DWNote("Historical note: In old/archaic Czech, v/ve + Accusative was also used for directional motion — physically moving into a place. Example: vběhnout v dům (to run into a house), where v dům is Accusative of dům. In modern Czech this usage has almost entirely disappeared, replaced by do + Genitive: vběhnout do domu. You may still encounter it in literary texts or old proverbs, but it is not used in everyday speech.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun DWSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun DWRow(czech: String, english: String, note: String = "") {
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

@Composable
private fun DWNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}
