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
fun PossessivePronounsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Possessive Pronouns", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // ── What Are Possessive Pronouns? ──────────────────────────────
            PPSection("What Are Possessive Pronouns?")
            PPNote("Possessive pronouns show that something belongs to someone: my, your, his, her, its, our, your (formal/plural), their.")
            PPNote("Think of it as describing person A's belonging — my brother, your car, their house. The 'A' is the owner. The possessive changes to match the noun it describes in gender, number, and case — just like an adjective.")
            PPNote("Examples: můj bratr (my brother, m.) · moje sestra (my sister, f.) · moje auto (my car, n.)")
            PPNote("Notice: moje auto — the word auto is neuter, yet we use moje, which looks the same as the feminine form. In the nominative and accusative, neuter possessives share their form with feminine (moje). In all other cases (genitive, dative, locative, instrumental), neuter follows masculine (mého, mému, mém, mým). So neuter is like feminine only in nominative/accusative.")
            PPNote("jeho (his/its) and jejich (their) are invariant — they never change regardless of the following noun's gender, number, or case. její (her) does fully decline. ono (it) shares the same possessive jako on (he): jeho.")

            // ── Nominative ────────────────────────────────────────────────
            PPSection("Nominative — the owner as subject")
            PPNote("Singular (Pronoun · Masculine · Feminine · Neuter):")
            PPTable(
                headers = listOf("Pronoun", "Masculine", "Feminine", "Neuter"),
                weights = listOf(1.3f, 0.9f, 1.0f, 1.0f),
                rows = listOf(
                    listOf("já (I)", "můj", "moje (má)", "moje (mé)"),
                    listOf("ty (you)", "tvůj", "tvoje (tvá)", "tvoje (tvé)"),
                    listOf("on (he)", "jeho", "jeho", "jeho"),
                    listOf("ona (she)", "její", "její", "její"),
                    listOf("ono (it)", "jeho", "jeho", "jeho"),
                    listOf("my (we)", "náš", "naše", "naše"),
                    listOf("vy (you pl.)", "váš", "vaše", "vaše"),
                    listOf("oni (they)", "jejich", "jejich", "jejich")
                )
            )
            PPNote("Plural (Pronoun · Masc animate pl. · Other plural):")
            PPTable(
                headers = listOf("Pronoun", "Masc anim pl.", "Other pl."),
                weights = listOf(1.3f, 1.1f, 1.0f),
                rows = listOf(
                    listOf("já", "moji", "moje"),
                    listOf("ty", "tvoji", "tvoje"),
                    listOf("on / ono", "jeho", "jeho"),
                    listOf("ona", "její", "její"),
                    listOf("my", "naši", "naše"),
                    listOf("vy", "vaši", "vaše"),
                    listOf("oni", "jejich", "jejich")
                )
            )
            PPNote("Examples: moji přátelé (my friends, m. anim pl.) · moje sestry (my sisters) · naše auta (our cars)")

            // ── Genitive ──────────────────────────────────────────────────
            PPSection("Genitive — possession, absence, after do / z / bez / od...")
            PPNote("Neuter follows masculine in all oblique cases (genitive through instrumental).")
            PPTable(
                headers = listOf("Pronoun", "Masc / Neut", "Feminine", "Plural"),
                weights = listOf(1.3f, 0.9f, 0.9f, 0.9f),
                rows = listOf(
                    listOf("já", "mého", "mé (mojí)", "mých"),
                    listOf("ty", "tvého", "tvé (tvojí)", "tvých"),
                    listOf("on / ono", "jeho", "jeho", "jeho"),
                    listOf("ona", "jejího", "její", "jejích"),
                    listOf("my", "našeho", "naší", "našich"),
                    listOf("vy", "vašeho", "vaší", "vašich"),
                    listOf("oni", "jejich", "jejich", "jejich")
                )
            )
            PPNote("Examples: bez mého bratra (without my brother) · od naší sestry (from our sister) · do jejich domu (to their house)")

            // ── Dative ────────────────────────────────────────────────────
            PPSection("Dative — to / for (indirect object)")
            PPTable(
                headers = listOf("Pronoun", "Masc / Neut", "Feminine", "Plural"),
                weights = listOf(1.3f, 0.9f, 0.9f, 0.9f),
                rows = listOf(
                    listOf("já", "mému", "mé (mojí)", "mým"),
                    listOf("ty", "tvému", "tvé (tvojí)", "tvým"),
                    listOf("on / ono", "jeho", "jeho", "jeho"),
                    listOf("ona", "jejímu", "její", "jejím"),
                    listOf("my", "našemu", "naší", "našim"),
                    listOf("vy", "vašemu", "vaší", "vašim"),
                    listOf("oni", "jejich", "jejich", "jejich")
                )
            )
            PPNote("Examples: dávám to mému bratrovi (I give it to my brother) · píšu naší mamince (I write to our mom)")

            // ── Accusative ────────────────────────────────────────────────
            PPSection("Accusative — direct object")
            PPNote("Masc animate acc. = genitive. Masc inanimate, neuter, and plural acc. = nominative (shown as '= nom.' below).")
            PPTable(
                headers = listOf("Pronoun", "Masc anim", "Feminine", "Inan / Neut / Pl."),
                weights = listOf(1.3f, 0.85f, 0.85f, 1.1f),
                rows = listOf(
                    listOf("já", "mého", "moji (mou)", "= nom."),
                    listOf("ty", "tvého", "tvoji (tvou)", "= nom."),
                    listOf("on / ono", "jeho", "jeho", "jeho"),
                    listOf("ona", "jejího", "její", "její"),
                    listOf("my", "našeho", "naši", "= nom."),
                    listOf("vy", "vašeho", "vaši", "= nom."),
                    listOf("oni", "jejich", "jejich", "jejich")
                )
            )
            PPNote("Examples: vidím mého bratra (I see my brother) · vidím moji sestru (I see my sister) · vidím naše auto (I see our car — neuter = nom.)")

            // ── Locative ──────────────────────────────────────────────────
            PPSection("Locative — location / topic (always with a preposition)")
            PPTable(
                headers = listOf("Pronoun", "Masc / Neut", "Feminine", "Plural"),
                weights = listOf(1.3f, 0.9f, 0.9f, 0.9f),
                rows = listOf(
                    listOf("já", "mém", "mé", "mých"),
                    listOf("ty", "tvém", "tvé", "tvých"),
                    listOf("on / ono", "jeho", "jeho", "jeho"),
                    listOf("ona", "jejím", "její", "jejích"),
                    listOf("my", "našem", "naší", "našich"),
                    listOf("vy", "vašem", "vaší", "vašich"),
                    listOf("oni", "jejich", "jejich", "jejich")
                )
            )
            PPNote("Examples: v našem bytě (in our apartment) · o mém bratrovi (about my brother) · v její práci (at her work)")

            // ── Instrumental ──────────────────────────────────────────────
            PPSection("Instrumental — with / by means of")
            PPTable(
                headers = listOf("Pronoun", "Masc / Neut", "Feminine", "Plural"),
                weights = listOf(1.3f, 0.9f, 1.0f, 0.9f),
                rows = listOf(
                    listOf("já", "mým", "mojí (mou)", "mými"),
                    listOf("ty", "tvým", "tvojí (tvou)", "tvými"),
                    listOf("on / ono", "jeho", "jeho", "jeho"),
                    listOf("ona", "jejím", "její", "jejími"),
                    listOf("my", "naším", "naší", "našimi"),
                    listOf("vy", "vaším", "vaší", "vašimi"),
                    listOf("oni", "jejich", "jejich", "jejich")
                )
            )
            PPNote("Examples: s mým bratrem (with my brother) · s naší rodinou (with our family) · před vaším domem (in front of your house)")

            PPSection("What about Vocative?")
            PPNote("Possessive pronouns (můj, tvůj, jeho, etc.) do not have vocative forms. In Czech, the vocative case applies to nouns and some adjectives used as titles when directly addressing someone — e.g. \"Petře!\" (Hey, Peter!) or \"Pane doktore!\" (Doctor!).")
            PPNote("Possessive pronouns modify nouns but do not themselves take a vocative ending. When you address someone, only their name or title goes into the vocative — the possessive stays in whatever case the sentence requires.")
            PPNote("Example: \"Petře, kde je tvoje auto?\" — Petře is vocative; tvoje stays in nominative because it describes the subject (auto). The possessive never changes to match the vocative context.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PPSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun PPNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

@Composable
private fun PPTable(
    headers: List<String>,
    rows: List<List<String>>,
    weights: List<Float> = emptyList()
) {
    val w = if (weights.size == headers.size) weights else List(headers.size) { if (it == 0) 1.3f else 0.9f }
    Spacer(modifier = Modifier.height(4.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                headers.forEachIndexed { i, h ->
                    Text(
                        text = h,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray,
                        modifier = Modifier.weight(w[i])
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 4.dp),
                color = Color.LightGray,
                thickness = 0.5.dp
            )
            rows.forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                ) {
                    row.forEachIndexed { i, cell ->
                        Text(
                            text = cell,
                            fontSize = 12.sp,
                            fontWeight = if (i == 0) FontWeight.Normal else FontWeight.Bold,
                            color = if (i == 0) Color.DarkGray else Color.Black,
                            modifier = Modifier.weight(w[i])
                        )
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}
