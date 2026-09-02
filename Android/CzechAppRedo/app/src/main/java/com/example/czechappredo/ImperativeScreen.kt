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
fun ImperativeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Imperative", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            ImpSection("What Is the Imperative?")
            ImpNote("The imperative is used to give commands, make requests, or issue invitations. In Czech it is most commonly formed for the 2nd person singular (ty — you), 2nd person plural (vy — you all / formal you), and 1st person plural (my — let's...).")
            ImpRow("Pij vodu!", "Drink water!")
            ImpRow("Pojďte dál!", "Come in! (formal/plural)")
            ImpRow("Pojďme!", "Let's go!")

            ImpSection("2nd Person Singular (ty) — Dělej! / Mluv!")
            ImpNote("Formation depends on the verb's conjugation group. Look at the 3rd person singular present tense to find the group.")
            ImpNote("Group I — verbs ending in -á (3rd sg): strip -á, add -ej")
            ImpRow("dělá → dělej!", "do! (from dělat — to do)")
            ImpRow("volá → volej!", "call! (from volat — to call)")
            ImpRow("čeká → čekej!", "wait! (from čekat — to wait)")
            ImpNote("Group II — verbs ending in -í (3rd sg): strip -í, use the bare stem (sometimes with a softening)")
            ImpRow("mluví → mluv!", "speak! (from mluvit — to speak)")
            ImpRow("prosí → pros!", "please / ask for! (from prosit — to ask/request)")
            ImpRow("platí → plať!", "pay! (from platit — to pay)")
            ImpRow("učí → uč!", "teach / study! (from učit — to teach)")
            ImpNote("Group III — verbs ending in -e or -je (3rd sg): use the bare stem")
            ImpRow("pije → pij!", "drink! (from pít — to drink)")
            ImpRow("čte → čti!", "read! (from číst — to read)")
            ImpRow("jde → jdi!", "go! (from jít — to go)")
            ImpNote("Irregulars:")
            ImpRow("být → buď!", "be!")
            ImpRow("pojít → pojď!", "come on! / go on! (very common informal \"let's go\")")
            ImpRow("vzít → vezmi!", "take!")
            ImpRow("jíst → jez!", "eat!")

            ImpSection("2nd Person Plural (vy) — Dělejte! / Mluvte!")
            ImpNote("Add -te to the 2nd person singular imperative form. This is also the polite form used when addressing one person formally.")
            ImpRow("dělej → dělejte!", "do! (plural / formal)")
            ImpRow("mluv → mluvte!", "speak! (plural / formal)")
            ImpRow("pojď → pojďte!", "come on! / please come! (plural / formal)")
            ImpRow("čekej → čekejte!", "wait! (plural / formal)")
            ImpRow("jdi → jděte!", "go! (plural / formal)")
            ImpNote("If you are speaking to one person formally (using vy), you use exactly the same -te form.")
            ImpRow("Počkejte, prosím.", "Please wait. (polite, to one person)")

            ImpSection("1st Person Plural — \"Let's...\" (Pojďme!)")
            ImpNote("Add -me to the 2nd person singular imperative. This is the Czech equivalent of \"let's\" and is widely used.")
            ImpRow("dělej → dělejme!", "let's do it!")
            ImpRow("mluv → mluvme!", "let's speak!")
            ImpRow("čekej → čekejme!", "let's wait!")
            ImpNote("The most common \"let's go\" expressions:")
            ImpRow("Pojďme!", "Let's go! (standard, from pojít/pojď)")
            ImpRow("Jdeme!", "Let's go! / We're going! (informal — lit. \"we go\", used colloquially as a prompt to leave)")
            ImpNote("Compare with Russian: Пойдём ≈ Pojďme! — both are 1st-person plural imperatives. Пошли is a past tense form that Russians repurpose as an abrupt \"let's go\" — Czech does NOT do this. The informal Czech equivalent is Jdeme! (present tense, not past).")

            ImpSection("Negative Imperative — Nedělej! (Don't do!)")
            ImpNote("Simply add ne- before the imperative. The structure is the same — nothing else changes.")
            ImpRow("Nedělej to!", "Don't do that!")
            ImpRow("Nemluvte tak rychle!", "Don't speak so fast!")
            ImpRow("Nepij tolik!", "Don't drink so much!")
            ImpRow("Nebuď nervózní!", "Don't be nervous!")
            ImpRow("Nečekejte na mě!", "Don't wait for me!")
            ImpNote("Important: in negative imperatives, imperfective verbs are strongly preferred. Nedělej (impf.) sounds natural; neudělej (pf.) sounds unnatural in most contexts.")

            ImpSection("With Reflexive Verbs — Umyj se! / Bavte se!")
            ImpNote("The reflexive particle (se or si) comes immediately after the imperative verb — it does not move to 2nd position here.")
            ImpRow("Umyj se!", "Wash yourself! (se — accusative reflexive)")
            ImpRow("Bav se!", "Have fun! (sg.)")
            ImpRow("Bavte se!", "Have fun! (pl. / formal)")
            ImpRow("Neboj se!", "Don't be afraid!")
            ImpRow("Zapamatuj si!", "Remember it! (si — dative reflexive)")
            ImpRow("Dej si pozor!", "Be careful! (lit. \"give attention to yourself\")")
            ImpRow("Posaďte se!", "Please sit down! (formal/plural)")
            ImpNote("Negative reflexive imperatives also keep se/si right after the verb: Neboj se! (Don't be afraid!), Nespěchej! (Don't rush!)")

            ImpSection("Perfective vs Imperfective in the Imperative")
            ImpNote("Both perfective and imperfective verbs form imperatives — the choice changes the nuance.")
            ImpNote("Imperfective → ongoing, repeated, or open-ended action; also used for gentle requests")
            ImpRow("Pij vodu!", "Drink water! (ongoing — keep drinking, in general)")
            ImpRow("Čekej tady!", "Wait here! (keep waiting, open-ended)")
            ImpRow("Dělej domácí úkoly!", "Do your homework! (habit/repeated)")
            ImpNote("Perfective → complete the action once; more urgent or definitive")
            ImpRow("Vypij tu vodu!", "Drink up that water! (finish it, this specific glass)")
            ImpRow("Počkej chvíli!", "Wait a moment! (a specific short wait)")
            ImpRow("Udělej domácí úkol!", "Do the homework! (this specific assignment, get it done)")
            ImpNote("Tip: when in doubt about which to use, ask — is the command about completing something specific (perfective) or about an ongoing behavior (imperfective)?")
            ImpNote("Examples with jít/jít se — similar pair:")
            ImpRow("Jdi domů!", "Go home! (impf. — general direction, go)")
            ImpRow("Přijď zítra!", "Come tomorrow! (pf. — arrive, one-time event)")

            ImpSection("Full Conjugation Tables — Common Verbs")
            ImpNote("Only ty, vy, and my take a direct imperative form — Czech has none for on/ona/ono/oni/ony. Third-person commands use ať + present tense instead: Ať to udělá. = Let him/her do it.")

            ImpConjugationTable(
                verb = "dělat (to do)",
                meaning = "imperfective, regular Group I",
                rows = listOf(
                    ImpFormRow("ty", "dělej!", "nedělej!"),
                    ImpFormRow("vy", "dělejte!", "nedělejte!"),
                    ImpFormRow("my", "dělejme!", "nedělejme!"),
                )
            )
            ImpConjugationTable(
                verb = "mluvit (to speak)",
                meaning = "imperfective, regular Group II",
                rows = listOf(
                    ImpFormRow("ty", "mluv!", "nemluv!"),
                    ImpFormRow("vy", "mluvte!", "nemluvte!"),
                    ImpFormRow("my", "mluvme!", "nemluvme!"),
                )
            )
            ImpConjugationTable(
                verb = "být (to be)",
                meaning = "irregular",
                rows = listOf(
                    ImpFormRow("ty", "buď!", "nebuď!"),
                    ImpFormRow("vy", "buďte!", "nebuďte!"),
                    ImpFormRow("my", "buďme!", "nebuďme!"),
                )
            )
            ImpConjugationTable(
                verb = "jíst (to eat)",
                meaning = "irregular",
                rows = listOf(
                    ImpFormRow("ty", "jez!", "nejez!"),
                    ImpFormRow("vy", "jezte!", "nejezte!"),
                    ImpFormRow("my", "jezme!", "nejezme!"),
                )
            )
            ImpConjugationTable(
                verb = "pít (to drink)",
                meaning = "imperfective, Group III",
                rows = listOf(
                    ImpFormRow("ty", "pij!", "nepij!"),
                    ImpFormRow("vy", "pijte!", "nepijte!"),
                    ImpFormRow("my", "pijme!", "nepijme!"),
                )
            )
            ImpConjugationTable(
                verb = "vzít (to take)",
                meaning = "perfective — positive commands use vzít",
                rows = listOf(
                    ImpFormRow("ty", "vezmi!", "neber!"),
                    ImpFormRow("vy", "vezměte!", "neberte!"),
                    ImpFormRow("my", "vezměme!", "neberme!"),
                ),
                note = "Negative commands switch to the imperfective partner brát, per the same rule already covered above (imperfective preferred in negative imperatives) — Neber! (don't take), not *Nevezmi!"
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ImpSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun ImpNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

@Composable
private fun ImpRow(czech: String, english: String) {
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)) {
                append(czech)
            }
            withStyle(SpanStyle(fontSize = 15.sp, color = Color.DarkGray)) {
                append("  —  $english")
            }
        },
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

private data class ImpFormRow(val pronoun: String, val positive: String, val negative: String)

@Composable
private fun ImpConjugationTable(verb: String, meaning: String, rows: List<ImpFormRow>, note: String = "") {
    Spacer(modifier = Modifier.height(8.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 2.dp, end = 2.dp, bottom = 4.dp)
    ) {
        Text(text = verb, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Text(text = meaning, fontSize = 13.sp, fontStyle = FontStyle.Italic, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Text("Pronoun", modifier = Modifier.weight(0.7f), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Text("Positive", modifier = Modifier.weight(1f), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Text("Negative", modifier = Modifier.weight(1f), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
        }
        HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
        rows.forEach { row ->
            Row(modifier = Modifier.padding(vertical = 3.dp)) {
                Text(row.pronoun, modifier = Modifier.weight(0.7f), fontSize = 14.sp, color = Color.DarkGray)
                Text(row.positive, modifier = Modifier.weight(1f), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(row.negative, modifier = Modifier.weight(1f), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
        if (note.isNotEmpty()) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = note, fontSize = 12.sp, fontStyle = FontStyle.Italic, color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}
