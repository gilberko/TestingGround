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
fun ObjectPronounsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Object Pronouns", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            OPSection("Personal Object Pronouns")
            OPNote("Czech personal pronouns change form depending on their grammatical role (case). This screen shows how to say me / you / him / her / it / us / them in each case.")
            OPNote("Short / clitic forms (mě, mi, mu, tě, ti, ho, ji, je...) are unstressed — they sit in the second position of the clause, after the first stressed word.")
            OPNote("Long / emphatic forms (mne, mně, tebe, tobě, jemu...) are used after prepositions and for emphasis.")
            OPNote("3rd-person pronouns after a preposition always take the n- prefix: ho → něho, jí → ní, jim → nim, etc.")

            OPSection("Nominative — Reference Only")
            OPNote("The nominative is the subject / dictionary form. Personal pronouns as objects do not appear in the nominative — this table is for reference.")
            OPTable(
                headers = listOf("Pronoun (English)", "Form", "—"),
                weights = listOf(1.4f, 0.8f, 0.8f),
                rows = listOf(
                    listOf("já  (I)", "já", ""),
                    listOf("ty  (you sg.)", "ty", ""),
                    listOf("on  (he)", "on", ""),
                    listOf("ona  (she)", "ona", ""),
                    listOf("ono  (it)", "ono", ""),
                    listOf("my  (we)", "my", ""),
                    listOf("vy  (you pl.)", "vy", ""),
                    listOf("oni / ony  (they)", "oni / ony", "")
                )
            )

            OPSection("Genitive — of / without / after do, z, od, bez, u, kolem...")
            OPTable(
                headers = listOf("Pronoun", "Standard / Short", "Long / After preposition"),
                weights = listOf(1.2f, 0.8f, 1.5f),
                rows = listOf(
                    listOf("já", "mě", "mne"),
                    listOf("ty", "tě", "tebe"),
                    listOf("on / ono", "ho", "jeho;  after prep: něho / něj"),
                    listOf("ona", "jí", "after prep: ní"),
                    listOf("my", "nás", "nás"),
                    listOf("vy", "vás", "vás"),
                    listOf("oni / ony", "jich", "after prep: nich")
                )
            )
            OPNote("Example: Čekám na tebe. (I'm waiting for you.) · Bojím se ho. (I'm afraid of him.) · Kolem ní. (Around her.)")

            OPSection("Dative — to / for — indirect object, after k, díky...")
            OPTable(
                headers = listOf("Pronoun", "Standard / Short", "Long / After preposition"),
                weights = listOf(1.2f, 0.8f, 1.5f),
                rows = listOf(
                    listOf("já", "mi", "mně"),
                    listOf("ty", "ti", "tobě"),
                    listOf("on / ono", "mu", "jemu;  after prep: němu"),
                    listOf("ona", "jí", "after prep: ní"),
                    listOf("my", "nám", "nám"),
                    listOf("vy", "vám", "vám"),
                    listOf("oni / ony", "jim", "after prep: nim")
                )
            )
            OPNote("Example: Dám ti to. (I'll give it to you.) · Řekl mu to. (He told it to him.) · K ní. (To her.)")

            OPSection("Accusative — direct object, after na, do, za, pro, přes...")
            OPTable(
                headers = listOf("Pronoun", "Standard / Short", "Long / After preposition"),
                weights = listOf(1.2f, 0.8f, 1.5f),
                rows = listOf(
                    listOf("já", "mě", "mne"),
                    listOf("ty", "tě", "tebe"),
                    listOf("on", "ho", "jej / jeho;  after prep: něho / něj"),
                    listOf("ono", "ho", "je;  after prep: něho / ně"),
                    listOf("ona", "ji", "after prep: ni"),
                    listOf("my", "nás", "nás"),
                    listOf("vy", "vás", "vás"),
                    listOf("oni / ony", "je", "after prep: ně")
                )
            )
            OPNote("Example: Vidím tě. (I see you.) · Miluju ji. (I love her.) · Čekám na něj. (I'm waiting for him.)")

            OPSection("Locative — location / topic — always with a preposition (v, na, o, po, při)")
            OPNote("Because locative is always used with a preposition, 3rd-person forms always use the n- prefix here.")
            OPTable(
                headers = listOf("Pronoun", "Form", "(always after preposition)"),
                weights = listOf(1.2f, 0.8f, 1.5f),
                rows = listOf(
                    listOf("já", "mně", "o mně (about me)"),
                    listOf("ty", "tobě", "o tobě (about you)"),
                    listOf("on / ono", "něm", "o něm (about him/it)"),
                    listOf("ona", "ní", "o ní (about her)"),
                    listOf("my", "nás", "o nás (about us)"),
                    listOf("vy", "vás", "o vás (about you)"),
                    listOf("oni / ony", "nich", "o nich (about them)")
                )
            )
            OPNote("Example: Mluvili o ní. (They talked about her.) · Snil o nich. (He dreamed about them.)")

            OPSection("Instrumental — with / by means of — after s, pod, nad, před, za, mezi...")
            OPTable(
                headers = listOf("Pronoun", "Standard / Short", "After preposition"),
                weights = listOf(1.2f, 0.8f, 1.5f),
                rows = listOf(
                    listOf("já", "mnou", "se mnou (with me)"),
                    listOf("ty", "tebou", "s tebou (with you)"),
                    listOf("on / ono", "jím", "after prep: ním  →  s ním"),
                    listOf("ona", "jí", "after prep: ní  →  s ní"),
                    listOf("my", "námi", "s námi (with us)"),
                    listOf("vy", "vámi", "s vámi (with you)"),
                    listOf("oni / ony", "jimi", "after prep: nimi  →  s nimi")
                )
            )
            OPNote("Example: Šel jsem s ním. (I went with him.) · Mluvila s ní. (She spoke with her.) · Mezi námi. (Between us.)")

            OPSection("Key Notes")
            OPNote("Short / clitic forms (mě, mi, mu, tě, ti, ho, ji, je, nás, nám, vás, vám, jich, jim, je) are always unstressed and sit in second clause position — after the first stressed word or phrase.")
            OPNote("Long / emphatic forms (mne, mně, tebe, tobě, jeho, jemu, jí, jeho, nás, nám, vás, vám, jich, jim, je) are used after prepositions and for contrastive emphasis: Tebe vidím, ne jeho. (I see YOU, not him.)")
            OPNote("3rd-person after any preposition: the n- prefix is always added — ho → něho, jí → ní, mu → němu, jim → nim, etc.")
            OPNote("Locative is always used with a preposition, so 3rd-person pronouns in the locative are always the n- form.")

            OPSection("What about Vocative?")
            OPNote("Vocative — N/A. The Czech vocative case applies to nouns and proper names used in direct address (e.g. Petře!, Pane doktore!). Personal pronouns do not take vocative forms — you cannot address someone as 'him' or 'them' in the vocative. Vocative is simply not part of the personal pronoun system.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun OPSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun OPNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

@Composable
private fun OPTable(
    headers: List<String>,
    rows: List<List<String>>,
    weights: List<Float> = emptyList()
) {
    val w = if (weights.size == headers.size) weights else List(headers.size) { 1f }
    Spacer(modifier = Modifier.height(6.dp))
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
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ButtonBlue,
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
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp)) {
                    row.forEachIndexed { i, cell ->
                        Text(
                            text = cell,
                            fontSize = 13.sp,
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
