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
            JPRow("dirigent", "dirigentka", "orchestra conductor")
            JPRow("designér", "designérka", "designer")
            JPRow("gymnasta", "gymnastka", "gymnast")

            JPSection("Food & Hospitality")
            JPRow("pekař", "pekařka", "baker")
            JPRow("barista", "baristka", "barista")
            JPRow("barman", "barmanka", "bartender")
            JPRow("šéfkuchař", "šéfkuchařka", "chef")
            JPRow("číšník", "číšnice", "waiter")
            JPRow("kuchař", "kuchařka", "cook", "šéfkuchař / šéfkuchařka = chef (head cook)")
            JPRow("gastronomický kritik", "gastronomická kritička", "food critic")

            JPSection("Medicine & Law")
            JPRow("lékař", "lékařka", "doctor", "also: doktor / doktorka (colloquial)")
            JPRow("zubař", "zubařka", "dentist")
            JPRow("právník", "právnice", "lawyer", "also: advokát / advokátka")
            JPRow("soudce", "soudkyně", "judge")
            JPRow("veterinář", "veterinářka", "veterinarian")
            JPRow("psycholog", "psycholožka", "psychologist")
            JPRow("terapeut", "terapeutka", "therapist")

            JPSection("Office, Tech & Education")
            JPRow("manažer", "manažerka", "manager")
            JPRow("úředník", "úřednice", "office worker")
            JPRow("programátor", "programátorka", "programmer")
            JPRow("vývojář", "vývojářka", "developer", "software developer — distinct from programátor (programmer / coder)")
            JPRow("inženýr", "inženýrka", "engineer")
            JPRow("architekt", "architektka", "architect")
            JPRow("učitel", "učitelka", "teacher")
            JPRow("profesor", "profesorka", "professor")
            JPRow("politik", "politička", "politician")

            JPSection("Trades & Crafts")
            JPRow("obuvník", "obuvnice", "cobbler", "also: ševec / ševcová (old-fashioned)")
            JPRow("krejčí", "krejčová", "tailor")
            JPRow("kadeřník", "kadeřnice", "hairdresser")
            JPRow("instalatér", "instalatérka", "plumber")
            JPRow("elektrikář", "elektrikářka", "electrician")
            JPRow("technik", "technička", "technician")

            JPSection("Public Service & Safety")
            JPRow("záchranář", "záchranářka", "lifeguard / rescue worker", "plavčík / plavčice = pool or beach lifeguard specifically")
            JPRow("policista", "policistka", "police officer")
            JPRow("detektiv", "detektivka", "detective", "detektivka can also mean 'detective novel' — context tells you which")
            JPRow("voják", "vojačka", "soldier", "vojákyně = formal feminine form")

            JPSection("Media & Communication")
            JPRow("novinář", "novinářka", "journalist")
            JPRow("moderátor zpráv", "moderátorka zpráv", "news anchor")
            JPRow("producent", "producentka", "film producer")
            JPRow("rozhlasový hlasatel", "rozhlasová hlasatelka", "radio broadcaster")
            JPRow("vypravěč", "vypravěčka", "narrator")
            JPRow("překladatel", "překladatelka", "translator")

            JPSection("Business & Finance")
            JPRow("podnikatel", "podnikatelka", "businessman")
            JPRow("účetní", "účetní", "accountant", "same form for both genders — agreement shows up elsewhere in the sentence")
            JPRow("investor", "investorka", "investor")
            JPRow("bankéř", "bankéřka", "banker")

            JPSection("Transport & Services")
            JPRow("řidič", "řidička", "driver")
            JPRow("průvodčí", "průvodčí", "train conductor", "same form for masculine and feminine")

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
