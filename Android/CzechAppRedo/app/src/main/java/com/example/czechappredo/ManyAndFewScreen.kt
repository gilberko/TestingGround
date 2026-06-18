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
fun ManyAndFewScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Many and Few", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            MFSection("Quantifiers and Genitive")
            MFNote("Czech quantity words (hodně, mnoho, málo, několik) are followed by the genitive case — not nominative.")
            MFNote("Countable nouns → genitive plural:  hodně lidí (a lot of people),  několik hodin (a few hours).")
            MFNote("Mass / uncountable nouns → genitive singular:  málo času (little time),  hodně peněz (a lot of money — peníze is plural but treated as mass).")
            MFNote("Verb agreement: when these quantifiers form the subject, the verb is typically 3rd person singular neuter:  Hodně lidí přišlo.  (A lot of people came.)  Několik aut stálo venku.  (Several cars stood outside.)")

            MFSection("Hodně / Mnoho / Moc — A Lot Of / Many")
            MFNote("hodně = a lot of  (informal, spoken — the most common in everyday speech)")
            MFNote("mnoho = many  (formal / written Czech)")
            MFNote("moc = a lot  (very informal; also means 'too much' — Nemluv moc. = Don't talk too much.)")
            MFNote("All three take genitive:")
            MFRow("a lot of people", "hodně lidí", "gen. pl. of lidé")
            MFRow("many cars", "mnoho aut", "gen. pl. of auto")
            MFRow("many hours", "hodně hodin", "gen. pl. of hodina")
            MFRow("many tasks", "hodně úkolů", "gen. pl. of úkol")
            MFRow("a lot of money", "moc peněz", "gen. pl. of peníze")
            MFRow("a lot of time", "hodně času", "gen. sg. of čas (mass)")
            MFRow("many problems", "mnoho problémů", "gen. pl. of problém")

            MFSection("Málo — Few / Little")
            MFNote("málo = few (countable) / little (uncountable). Also takes genitive.")
            MFRow("few people", "málo lidí", "gen. pl.")
            MFRow("few cars", "málo aut", "gen. pl.")
            MFRow("few hours", "málo hodin", "gen. pl.")
            MFRow("few tasks", "málo úkolů", "gen. pl.")
            MFRow("little money", "málo peněz", "gen. pl.")
            MFRow("little time", "málo času", "gen. sg. (mass noun)")
            MFNote("Verb: Málo lidí přišlo. (Few people came.)")

            MFSection("Několik — A Few / Several")
            MFNote("několik = a few / several. Always takes genitive plural.")
            MFRow("a few hours", "několik hodin", "gen. pl.")
            MFRow("a few tasks", "několik úkolů", "gen. pl.")
            MFRow("a few people", "několik lidí", "gen. pl.")
            MFRow("a few cars", "několik aut", "gen. pl.")
            MFRow("a few days", "několik dní", "gen. pl. of den — irregular: dní or dnů")
            MFRow("a few weeks", "několik týdnů", "gen. pl. of týden")
            MFRow("a few years", "několik let", "gen. pl. of rok — irregular: let")
            MFNote("Verb: Několik lidí odešlo. (A few people left.)")

            MFSection("Counting Nouns — 1 / 2–4 / 5+")
            MFNote("Czech, like Russian, changes the noun form depending on the number. Unlike Russian (which uses genitive singular for 2–4), Czech uses nominative plural for 2–4.")
            MFNote("1  →  nominative singular:  jeden (masc) · jedna (fem) · jedno (neut)")
            MFNote("2, 3, 4  →  nominative plural:  dva (masc) · dvě (fem + neut) · tři · čtyři")
            MFNote("5 and above  →  genitive plural — same as hodně/mnoho/málo/několik")
            MFNote("Russian uses genitive singular for 2–4 (два замка = two castles, gen.sg.). Czech uses nominative plural (dva hrady). For 5+, both languages use genitive plural.")
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                Column {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text("Noun", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = ButtonBlue, modifier = Modifier.weight(1.2f))
                        Text("1", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = ButtonBlue, modifier = Modifier.weight(1.1f))
                        Text("2–4", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = ButtonBlue, modifier = Modifier.weight(1.1f))
                        Text("5+", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = ButtonBlue, modifier = Modifier.weight(1.1f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = Color.LightGray, thickness = 0.5.dp)
                    listOf(
                        listOf("hrad (castle, m.inan.)", "jeden hrad", "dva hrady", "pět hradů"),
                        listOf("auto (car, neut.)", "jedno auto", "dvě auta", "pět aut"),
                        listOf("muž (man, m.anim.)", "jeden muž", "dva muži", "pět mužů"),
                        listOf("žena (woman, fem.)", "jedna žena", "dvě ženy", "pět žen"),
                        listOf("lampa (lamp, fem.)", "jedna lampa", "dvě lampy", "pět lamp")
                    ).forEach { cols ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp)
                        ) {
                            Text(text = cols[0], fontSize = 12.sp, color = Color.DarkGray, modifier = Modifier.weight(1.2f))
                            Text(text = cols[1], fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(1.1f))
                            Text(text = cols[2], fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(1.1f))
                            Text(text = cols[3], fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(1.1f))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            MFRow("1 castle", "jeden hrad", "nom.sg.")
            MFRow("2 castles", "dva hrady", "nom.pl. — NOT dvě hrady")
            MFRow("4 castles", "čtyři hrady", "nom.pl.")
            MFRow("5 castles", "pět hradů", "gen.pl.")
            MFRow("10 men", "deset mužů", "gen.pl.")
            MFRow("many women", "hodně žen", "gen.pl.")
            MFRow("a few cars", "několik aut", "gen.pl.")
            MFRow("a lot of lamps", "hodně lamp", "gen.pl.")
            MFNote("'dva' is masculine; 'dvě' is feminine AND neuter:  dva hrady / muži  but  dvě ženy / lampy / auta.")
            MFNote("For masculine animate nouns (men, animals), the nominative plural ends in -i/-é:  dva muži,  dva koně. Same 1/2-4/5+ rule applies.")

            MFSection("Mass Nouns — Quantities of Money, Water, Air")
            MFNote("Mass/uncountable nouns and plurale tantum nouns cannot be counted with 1/2/5. They use quantifiers + genitive.")
            MFNote("peníze (money) — always plural in Czech (plurale tantum, like 'scissors' in English). Genitive plural: peněz.")
            MFRow("a lot of money", "hodně peněz", "gen.pl.")
            MFRow("not too much money", "ne příliš peněz", "ne příliš = not too much")
            MFRow("little money", "málo peněz", "gen.pl.")
            MFNote("voda (water) — mass noun. Genitive singular: vody.")
            MFRow("a lot of water", "hodně vody", "gen.sg.")
            MFRow("not too much water", "ne příliš vody", "")
            MFRow("a little water", "trochu vody", "trochu = a little bit")
            MFNote("vzduch (air) — mass noun. Genitive singular: vzduchu.")
            MFRow("a lot of air", "hodně vzduchu", "gen.sg.")
            MFRow("fresh air", "čerstvý vzduch", "no quantifier — just adjective + noun")
            MFNote("For mass nouns: use genitive singular. For peníze: use genitive plural. You cannot say '2 vody' or '5 vzduchů' — use a container: dvě sklenice vody (two glasses of water).")

            MFSection("Celý — The Whole / All")
            MFNote("celý is an adjective, not a quantifier. It AGREES with the noun in gender and case — it does NOT govern the genitive.")
            MFNote("Hard adjective forms:  celý (masc) · celá (fem) · celé (neut)")
            MFNote("When used as a time expression without a preposition, the noun is in the accusative (expressing duration):")
            MFRow("the whole day / all day", "celý den", "masc acc — celý agrees with den")
            MFRow("the whole year", "celý rok", "masc acc — celý agrees with rok")
            MFRow("all night", "celou noc", "fem acc — celou agrees with noc")
            MFRow("all summer", "celé léto", "neut acc — celé agrees with léto")
            MFRow("all week", "celý týden", "masc acc")
            MFNote("Examples in sentences:")
            MFNote("Celý den jsem pracoval.  (I worked all day.)  — celý is nominative here since den is subject")
            MFNote("Pracoval jsem celý den.  (I worked all day.)  — celý den in acc as time duration")
            MFNote("Celý rok jsme cestovali.  (We traveled all year.)")
            MFNote("Seděla tam celou noc.  (She sat there all night.)")
            MFNote("Key contrast: hodně/mnoho/málo/několik → followed by genitive. celý → agrees with its noun; the noun's case depends on its role in the sentence.")

            MFSection("Summary — Useful Quantity Phrases")
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                Column {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text("Phrase", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = ButtonBlue, modifier = Modifier.weight(1.3f))
                        Text("Czech", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = ButtonBlue, modifier = Modifier.weight(1.1f))
                        Text("Case of noun", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = ButtonBlue, modifier = Modifier.weight(0.9f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = Color.LightGray, thickness = 0.5.dp)
                    listOf(
                        Triple("a lot of people", "hodně lidí", "gen. pl."),
                        Triple("many cars", "mnoho aut", "gen. pl."),
                        Triple("a few hours", "několik hodin", "gen. pl."),
                        Triple("many hours", "hodně hodin", "gen. pl."),
                        Triple("many tasks", "hodně úkolů", "gen. pl."),
                        Triple("a lot of tasks", "hodně úkolů", "gen. pl."),
                        Triple("a few tasks", "několik úkolů", "gen. pl."),
                        Triple("the whole day", "celý den", "acc., agrees"),
                        Triple("the whole year", "celý rok", "acc., agrees"),
                        Triple("all day long", "celý den", "acc. (duration)")
                    ).forEach { (phrase, czech, caseNote) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp)
                        ) {
                            Text(text = phrase, fontSize = 13.sp, color = Color.DarkGray, modifier = Modifier.weight(1.3f))
                            Text(text = czech, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(1.1f))
                            Text(text = caseNote, fontSize = 12.sp, fontStyle = FontStyle.Italic, color = Color.Gray, modifier = Modifier.weight(0.9f))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun MFSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun MFNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun MFRow(phrase: String, czech: String, caseNote: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
    ) {
        Text(text = phrase, fontSize = 13.sp, color = Color.DarkGray, modifier = Modifier.weight(1.3f))
        Text(text = czech, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(1.1f))
        Text(text = caseNote, fontSize = 12.sp, fontStyle = FontStyle.Italic, color = Color.Gray, modifier = Modifier.weight(0.9f))
    }
}
