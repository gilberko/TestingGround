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
fun JobsAndProfessionsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jobs & Professions", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
            JPSection("Arts & Entertainment")
            JPRow("herec", "herečka", "actor")
            JPRow("režisér", "režisérka", "film director")
            JPRow("hudebník", "hudebnice", "musician")
            JPRow("zpěvák", "zpěvačka", "singer")
            JPRow("spisovatel", "spisovatelka", "writer")

            JPSection("Food & Hospitality")
            JPRow("pekař", "pekařka", "baker")
            JPRow("barista", "baristka", "barista")
            JPRow("barman", "barmanka", "bartender")
            JPRow("šéfkuchař", "šéfkuchařka", "chef")
            JPRow("číšník", "číšnice", "waiter")

            JPSection("Medicine & Law")
            JPRow("lékař", "lékařka", "doctor", "also: doktor / doktorka (colloquial)")
            JPRow("zubař", "zubařka", "dentist")
            JPRow("právník", "právnice", "lawyer", "also: advokát / advokátka")

            JPSection("Office, Tech & Education")
            JPRow("manažer", "manažerka", "manager")
            JPRow("úředník", "úřednice", "office worker")
            JPRow("programátor", "programátorka", "programmer")
            JPRow("inženýr", "inženýrka", "engineer")
            JPRow("architekt", "architektka", "architect")
            JPRow("učitel", "učitelka", "teacher")

            JPSection("Trades & Crafts")
            JPRow("obuvník", "obuvnice", "cobbler", "also: ševec / ševcová (old-fashioned)")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun JPSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun JPRow(masc: String, fem: String, english: String, note: String = "") {
    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)) {
                    append(masc)
                }
                withStyle(SpanStyle(fontSize = 16.sp, color = Color.Gray)) {
                    append(" / ")
                }
                withStyle(SpanStyle(fontSize = 16.sp, color = Color.DarkGray)) {
                    append(fem)
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
