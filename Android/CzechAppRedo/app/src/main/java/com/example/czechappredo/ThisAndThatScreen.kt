package com.example.czechappredo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun ThisAndThatScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("This and That", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // ── No Articles ───────────────────────────────────────────────
            TATHeader("No Definite or Indefinite Articles")
            TATNote("Czech has no equivalent of \"the\", \"a\", or \"an\". The noun stands alone and context tells you whether it is definite or indefinite.")
            TATNote("muž — a man / the man / man (all three, depending on context)")
            TATNote("žena — a woman / the woman / woman")
            TATNote("město — a city / the city / city")

            // ── Ten / Ta / To ─────────────────────────────────────────────
            TATHeader("Ten / Ta / To — This and That")
            TATNote("Czech uses ten, ta, to to mean both \"this\" and \"that\" in everyday speech. They also act as a soft definite pointer — similar to \"the\" — when the speaker wants to be specific.")
            TATNote("For something emphatically nearby (\"this right here\"), Czechs use tenhle / tahle / tohle.")
            Spacer(modifier = Modifier.height(8.dp))
            TATNote("Nominative forms at a glance:")
            TATTable3(
                headers = Triple("Gender", "Singular", "Plural"),
                rows = listOf(
                    Triple("Masculine animate", "ten", "ti"),
                    Triple("Masculine inanimate", "ten", "ty"),
                    Triple("Feminine", "ta", "ty"),
                    Triple("Neuter", "to", "ta")
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            TATNote("Examples (Nominative):")
            TATNote("ten muž — that man    |    ti muži — those men")
            TATNote("ta žena — that woman    |    ty ženy — those women")
            TATNote("to město — that city    |    ta města — those cities")
            TATNote("tenhle muž — this man (right here)    |    tahle žena — this woman (right here)")

            // ── Feminine endings ──────────────────────────────────────────
            TATHeader("Feminine Nouns: -a vs -e Endings")
            TATNote("Feminine nouns can end in -a (e.g. žena = woman) or in -e / -ě (e.g. růže = rose). The demonstrative form depends only on the gender — not on the specific ending — so both types of feminine noun use the same demonstrative throughout all cases.")
            TATNote("Examples showing that the demonstrative is identical for both endings:")
            TATNote("Nominative:    ta žena / ta růže")
            TATNote("Genitive:    té ženy / té růže")
            TATNote("Dative:    té ženě / té růži")
            TATNote("Accusative:    tu ženu / tu růži")
            TATNote("Locative:    té ženě / té růži")
            TATNote("Instrumental:    tou ženou / tou růží")

            // ── Full case tables ──────────────────────────────────────────
            TATHeader("All Cases — Full Declension Tables")
            TATNote("The tables below show how ten / ta / to changes across all cases, for all four gender/animacy categories.")

            Spacer(modifier = Modifier.height(6.dp))
            TATCaseLabel("Nominative (subject of the sentence)")
            TATTable3(
                headers = Triple("Gender", "Singular", "Plural"),
                rows = listOf(
                    Triple("Masculine animate", "ten", "ti"),
                    Triple("Masculine inanimate", "ten", "ty"),
                    Triple("Feminine", "ta", "ty"),
                    Triple("Neuter", "to", "ta")
                )
            )

            Spacer(modifier = Modifier.height(10.dp))
            TATCaseLabel("Genitive (of, possession, negation, some prepositions)")
            TATTable3(
                headers = Triple("Gender", "Singular", "Plural"),
                rows = listOf(
                    Triple("Masculine animate", "toho", "těch"),
                    Triple("Masculine inanimate", "toho", "těch"),
                    Triple("Feminine", "té", "těch"),
                    Triple("Neuter", "toho", "těch")
                )
            )

            Spacer(modifier = Modifier.height(10.dp))
            TATCaseLabel("Dative (to, for, indirect object)")
            TATTable3(
                headers = Triple("Gender", "Singular", "Plural"),
                rows = listOf(
                    Triple("Masculine animate", "tomu", "těm"),
                    Triple("Masculine inanimate", "tomu", "těm"),
                    Triple("Feminine", "té", "těm"),
                    Triple("Neuter", "tomu", "těm")
                )
            )

            Spacer(modifier = Modifier.height(10.dp))
            TATCaseLabel("Accusative (direct object, motion toward)")
            TATNote("Note: Masculine animate uses toho in singular (like Genitive), while masculine inanimate keeps ten.")
            TATTable3(
                headers = Triple("Gender", "Singular", "Plural"),
                rows = listOf(
                    Triple("Masculine animate", "toho", "ty"),
                    Triple("Masculine inanimate", "ten", "ty"),
                    Triple("Feminine", "tu", "ty"),
                    Triple("Neuter", "to", "ta")
                )
            )

            Spacer(modifier = Modifier.height(10.dp))
            TATCaseLabel("Locative (location, used with v, na, o, při, po)")
            TATTable3(
                headers = Triple("Gender", "Singular", "Plural"),
                rows = listOf(
                    Triple("Masculine animate", "tom", "těch"),
                    Triple("Masculine inanimate", "tom", "těch"),
                    Triple("Feminine", "té", "těch"),
                    Triple("Neuter", "tom", "těch")
                )
            )

            Spacer(modifier = Modifier.height(10.dp))
            TATCaseLabel("Instrumental (by means of, with, used with s/se, za, nad, pod, před, mezi)")
            TATTable3(
                headers = Triple("Gender", "Singular", "Plural"),
                rows = listOf(
                    Triple("Masculine animate", "tím", "těmi"),
                    Triple("Masculine inanimate", "tím", "těmi"),
                    Triple("Feminine", "tou", "těmi"),
                    Triple("Neuter", "tím", "těmi")
                )
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun TATHeader(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun TATCaseLabel(text: String) {
    Text(text = text, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.DarkGray)
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun TATNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun TATTable3(
    headers: Triple<String, String, String>,
    rows: List<Triple<String, String, String>>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(headers.first, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray, modifier = Modifier.weight(1.4f))
                Text(headers.second, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray, modifier = Modifier.weight(0.8f))
                Text(headers.third, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray, modifier = Modifier.weight(0.8f))
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = Color.LightGray, thickness = 0.5.dp)
            rows.forEach { (gender, singular, plural) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                ) {
                    Text(gender, fontSize = 13.sp, color = Color.DarkGray, modifier = Modifier.weight(1.4f))
                    Text(singular, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(0.8f))
                    Text(plural, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(0.8f))
                }
            }
        }
    }
}
