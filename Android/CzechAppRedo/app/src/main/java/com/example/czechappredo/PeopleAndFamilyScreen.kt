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
fun PeopleAndFamilyScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("People & Family", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
            PFSection("People")
            PFRow("muž", "man", "m.")
            PFRow("žena", "woman", "f.")
            PFRow("kluk / chlapec", "boy", "m. — kluk is colloquial, chlapec is formal")
            PFRow("holka / dívka", "girl", "f. — holka is colloquial, dívka is formal")
            PFRow("děvče", "girl / young woman", "n. — slightly literary; less common than holka or dívka in everyday speech, but widely understood")
            PFRow("člověk / osoba", "person", "člověk m. = human being (general); osoba f. = individual (formal contexts)")
            PFRow("miminko", "baby", "n.")
            PFRow("batole", "toddler", "n. — a child who has started walking but is not yet school age")
            PFRow("kojenec", "infant", "m. — specifically a nursing infant")
            PFRow("dítě", "kid / child", "n.")
            PFRow("děti", "children", "pl. of dítě — irregular plural. e.g. Mám dvě děti. (I have two children.)")
            PFNote("Neuter gender: Several Czech words for children or young people are neuter, even when they refer to a female. miminko (baby), batole (toddler), dítě (child), and děvče (girl) are all neuter (n.). This surprises English speakers, but Czech follows a historical pattern where young or small beings often take neuter gender — similar to German das Kind (child) or das Mädchen (girl). This means pronouns and adjectives for these nouns use neuter forms: malé dítě (small child), hezké děvče (pretty girl).")

            PFSection("Immediate Family")
            PFRow("rodič / rodiče", "parent / parents", "rodič m. = one parent; rodiče = both parents together (always plural in this sense). e.g. Moji rodiče jsou z Prahy. = My parents are from Prague.")
            PFRow("otec", "father", "m., formal/neutral")
            PFRow("táta", "dad", "m., colloquial")
            PFRow("matka", "mother", "f., formal/neutral")
            PFRow("máma", "mom", "f., colloquial")
            PFRow("tatínek", "daddy / dear dad", "m. — affectionate diminutive of táta; fleeting-e pattern: tatínek → tatínka (Gen/Acc)")
            PFRow("maminka", "mommy / dear mom", "f. — affectionate diminutive of máma")
            PFNote("The spectrum: otec / matka = formal (\"father\" / \"mother\") · táta / máma = everyday colloquial (\"dad\" / \"mom\") · tatínek / maminka = affectionate / tender (\"daddy\" / \"mommy\"). Tatínek and maminka are widely used by adults, not only children.")
            PFRow("manžel", "husband", "m.")
            PFRow("manželka", "wife", "f.")
            PFRow("syn", "son", "m.")
            PFRow("dcera", "daughter", "f.")
            PFRow("bratr", "brother", "m.")
            PFRow("sestra", "sister", "f.")

            PFSection("Extended Family")
            PFRow("dědeček", "grandfather", "m.")
            PFRow("babička", "grandmother", "f.")
            PFRow("strýc", "uncle", "m. — same word regardless of which side of the family")
            PFRow("teta", "aunt", "f. — same word regardless of which side of the family")
            PFRow("bratranec", "cousin (male)", "m. — Czech uses separate words for male and female cousins")
            PFRow("sestřenice", "cousin (female)", "f.")
            PFRow("synovec", "nephew", "m.")
            PFRow("neteř", "niece", "f.")
            PFRow("švagr", "brother-in-law", "m.")
            PFRow("švagrová", "sister-in-law", "f.")
            PFRow("tchán", "father-in-law", "m. — parent of one's spouse; used by both partners in modern Czech")
            PFRow("tchyně", "mother-in-law", "f. — parent of one's spouse; used by both partners in modern Czech")

            PFSection("Romantic Partners")
            PFRow("přítel", "(male) friend / boyfriend", "m. — context determines the meaning; Mám přítele. = I have a boyfriend.")
            PFRow("přítelkyně", "(female) friend / girlfriend", "f. — Mám přítelkyni. = I have a girlfriend. Both přítel and přítelkyně can mean friend or romantic partner; in romantic context they are the standard term. The colloquial kluk (boyfriend, lit. guy) and holka (girlfriend, lit. girl) are also common.")
            PFRow("snoubenec", "fiancé", "m.")
            PFRow("snoubenka", "fiancée", "f.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PFNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun PFSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun PFRow(czech: String, english: String, note: String = "") {
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
