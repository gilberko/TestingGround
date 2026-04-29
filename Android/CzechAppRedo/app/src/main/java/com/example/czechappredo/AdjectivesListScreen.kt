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
fun AdjectivesListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adjectives", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            ALSection("About These Forms")
            ALNote("Each adjective is shown as: masculine / feminine / neuter. These are the nominative (subject) forms. See Learning → Adjectives for how they change in other cases.")

            ALSection("Colors")
            ALRow("zelený / zelená / zelené", "green")
            ALRow("červený / červená / červené", "red")

            ALSection("Light & Dark")
            ALRow("tmavý / tmavá / tmavé", "dark")
            ALRow("světlý / světlá / světlé", "light / bright")

            ALSection("Size")
            ALRow("malý / malá / malé", "small")
            ALRow("velký / velká / velké", "big / large")
            ALRow("vysoký / vysoká / vysoké", "tall / high")
            ALRow("nízký / nízká / nízké", "short / low", "for height")
            ALRow("tenký / tenká / tenké", "thin / slim", "for objects; štíhlý / štíhlá / štíhlé = slim for people")
            ALRow("tlustý / tlustá / tlusté", "thick / fat", "for objects; silný / silná / silné = strong / thick for materials")

            ALSection("Temperature")
            ALRow("horký / horká / horké", "hot")
            ALRow("studený / studená / studené", "cold", "chladný / chladná / chladné = cool / chilly")

            ALSection("Water")
            ALRow("perlivý / perlivá / perlivé", "sparkling")
            ALRow("neperlivý / neperlivá / neperlivé", "still / non-sparkling", "also: voda bez bublinek = water without bubbles")

            ALSection("Value & Quality")
            ALRow("bohatý / bohatá / bohaté", "rich")
            ALRow("chudý / chudá / chudé", "poor")
            ALRow("drahý / drahá / drahé", "expensive")
            ALRow("levný / levná / levné", "cheap / affordable")
            ALRow("dobrý / dobrá / dobré", "good")
            ALRow("špatný / špatná / špatné", "bad")
            ALRow("nový / nová / nové", "new")
            ALRow("starý / stará / staré", "old")
            ALRow("mladý / mladá / mladé", "young")

            ALSection("Character & Appearance")
            ALRow("laskavý / laskavá / laskavé", "kind / gentle")
            ALRow("hezký / hezká / hezké", "handsome / nice-looking", "for people and things")
            ALRow("pohledný / pohledná / pohledné", "attractive / good-looking", "specifically for people")
            ALRow("krásný / krásná / krásné", "beautiful")

            ALSection("Speed & Reliability")
            ALRow("rychlý / rychlá / rychlé", "fast")
            ALRow("pomalý / pomalá / pomalé", "slow")
            ALRow("spolehlivý / spolehlivá / spolehlivé", "reliable")

            ALSection("Comparative Forms")
            ALNote("Comparatives are formed with a -ší or -í suffix and always decline as soft adjectives.")
            ALRow("mladší", "younger", "comparative of mladý")
            ALRow("starší", "older", "comparative of starý")
            ALRow("větší", "bigger", "comparative of velký")
            ALRow("menší", "smaller", "comparative of malý")
            ALRow("rychlejší", "faster", "comparative of rychlý")
            ALRow("pomalejší", "slower", "comparative of pomalý")
            ALNote("Example: Mám mladšího bratra a starší sestru.  —  I have a younger brother and an older sister.")
            ALNote("'mladšího' uses the soft adjective accusative masculine animate ending -ího (→ mladšího bratra). 'starší sestru' — soft adjective feminine accusative is unchanged.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ALSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun ALNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

@Composable
private fun ALRow(forms: String, english: String, note: String = "") {
    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)) {
                    append(forms)
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
