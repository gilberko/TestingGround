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
fun GovernmentAndArmyScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Government and Army", fontSize = 16.sp, fontWeight = FontWeight.Bold) },
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
            GANote("Most people-nouns have a male and a female form, shown as male / female (politik / politička).")

            GASection("Government")
            GARow("vláda", "government", "f. — also = rule, reign")
            GARow("politik / politička", "politician")
            GARow("politika", "politics / policy", "f. — one word for both: zahraniční politika = foreign policy")
            GARow("prezident / prezidentka", "president")
            GARow("předseda vlády / premiér", "prime minister", "lit. \"chairman of the government\"; premiér / premiérka is the everyday word")
            GARow("ministr / ministryně", "minister", "ministerstvo = ministry")
            GARow("ministr zahraničí", "minister of foreign affairs / Secretary of State", "The US Secretary of State is called ministr zahraničí USA. Careful: sekretář / sekretářka = an office secretary; tajemník = secretary of an organisation (generální tajemník OSN = UN Secretary-General).")
            GARow("parlament", "parliament")
            GARow("poslanec / poslankyně", "member of parliament (MP)", "Poslanecká sněmovna = Chamber of Deputies (the lower house)")
            GARow("Senát / senátor", "Senate / senator")
            GARow("poradce / poradkyně", "advisor")
            GARow("právník / právnička", "lawyer", "advokát / advokátka = attorney (a lawyer who represents clients)")
            GARow("zákon", "law (a specific law)")

            GASection("Systems of Government")
            GARow("demokracie", "democracy")
            GARow("diktatura", "dictatorship")
            GARow("diktátor", "dictator")
            GARow("republika", "republic", "Česká republika")
            GARow("monarchie", "monarchy")
            GARow("království", "kingdom", "n. — Spojené království = the United Kingdom")

            GASection("Elections and Parties")
            GARow("volby", "elections", "plural only: volby jsou v říjnu")
            GARow("volit / hlasovat", "to vote", "volit = to vote in an election (also to choose); hlasovat = to vote on something (in parliament, a show of hands)")
            GARow("hlas", "a vote", "also = voice")
            GARow("hlasovací lístek", "ballot (paper)")
            GARow("kandidát / kandidátka", "candidate")
            GARow("volební kampaň", "election campaign", "f. — kampaň on its own = campaign")
            GARow("propaganda", "propaganda")
            GARow("politická strana", "political party", "strana also means side and page. A party (celebration) is večírek or párty.")
            GARow("koalice", "coalition")
            GARow("opozice", "opposition")

            GASection("Monarchy and Royalty")
            GARow("král", "king")
            GARow("královna", "queen")
            GARow("princ", "prince")
            GARow("princezna", "princess")
            GARow("koruna", "crown", "also the Czech currency")
            GARow("trůn", "throne")
            GARow("dědic / dědička", "heir", "also an heir to money or property")
            GARow("následník trůnu", "heir to the throne")

            GASection("Army")
            GARow("armáda", "army")
            GARow("voják / vojačka", "soldier")
            GARow("důstojník", "officer", "armádní důstojník = army officer")
            GARow("velitel", "commander", "a role (the one in command), not a rank")

            GASection("Army Ranks")
            GANote("Lowest to highest. English and Czech ranks don't map 1:1; the equivalents below follow the NATO rank codes and are approximate.")
            GARow("vojín", "private")
            GARow("rotmistr", "sergeant", "NATO OR-5. For US/UK armies Czech also just says seržant.")
            GARow("nadrotmistr", "staff sergeant", "NATO OR-6. For the US rank also štábní seržant.")
            GARow("poručík", "second lieutenant")
            GARow("nadporučík", "lieutenant / first lieutenant")
            GARow("kapitán", "captain", "also the captain of a ship or team")
            GARow("major", "major")
            GARow("podplukovník", "lieutenant colonel", "pod- = under: one step below plukovník")
            GARow("plukovník", "colonel", "from pluk = regiment")
            GARow("generál", "general")
            GARow("admirál", "admiral", "navy. The navy rank \"Commander\" is fregatní kapitán (Czechia itself has no navy).")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun GASection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun GANote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun GARow(czech: String, english: String, note: String = "") {
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
