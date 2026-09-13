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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PastTenseScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Past Tense", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            PASSection("How Czech Past Tense Works")
            PASNote("The Czech past tense has two components: a helper form of být + the l-form (past participle).")
            PASNote("The l-form is built from the verb stem and changes its ending depending on the gender and number of the subject.")

            PASSection("Building the Past Participle Stem")
            PASNote("This form is called the past participle (also known as the l-participle). The general rule: drop the infinitive -t and add -l (plus the gender/number ending).")
            PASNote("dělat → dělal  (to do → did)")
            PASNote("Consonant stems: the -l attaches directly to the stem's final consonant, with no extra vowel inserted.")
            PASNote("nést → nesl  (to carry → carried)")
            PASNote("vést → vedl  (to lead → led)")
            PASNote("moci → mohl  (to be able to → could)")
            PASNote("říct → řekl  (to say → said)")
            PASNote("péct → pekl  (to bake → baked)")
            PASNote("-nout verbs: two forms exist side by side — a longer one keeping -nu-, and a shorter one dropping it.")
            PASNote("tisknout → tisknul / tiskl  (to print → printed)")
            PASNote("padnout → padnul / padl  (to fall → fell)")
            PASNote("zvednout → zvednul / zvedl  (to lift → lifted)")
            PASNote("Irregular: zapomenout → zapomněl  (to forget → forgot) — does not follow the usual -nout pattern.")
            PASNote("Vowel shortening: many verbs whose infinitive has a long vowel (á, í, ý) shorten it in the participle.")
            PASNote("spát → spal  (to sleep → slept) — notice the á loses its accent mark; this is the shortening rule in action.")
            PASNote("brát → bral  (to take → took)")
            PASNote("mýt → myl  (to wash → washed)")
            PASNote("pít → pil  (to drink → drank)")
            PASNote("mít → měl  (to have → had)")
            PASNote("být → byl  (to be → was)")
            PASNote("Exceptions that keep the long vowel: hrát → hrál (to play), stát → stál (to stand), bát se → bál se (to be afraid), přát → přál (to wish).")
            PASNote("Watch out: spal (slept, from spát) is not the same word as spál (burned — the past participle of spálit). Same letters, different accent, different verb.")

            PASSection("The l-form — Five Endings")
            PASNote("Singular masculine:  -l  →  dělal  (he did / I did — male)")
            PASNote("Singular feminine:  -la  →  dělala  (she did / I did — female)")
            PASNote("Singular neuter:  -lo  →  dělalo  (it did)")
            PASNote("Plural masculine animate:  -li  →  dělali  (they did — mixed or all-male group)")
            PASNote("Plural feminine:  -ly  →  dělaly  (they did — all-women or all-girls group)")

            PASSection("Helper Verb Být — When to Use It")
            PASNote("The helper verb is required for já / ty / my / vy. Third-person subjects (on, ona, ono, oni, ony) use no helper — the l-form alone is enough.")
            PASNote("já: jsem  |  ty: jsi  |  on / ona / ono: —  |  my: jsme  |  vy: jste  |  oni / ony: —")

            PASSection("Past Tense of Být (to be)")
            PASNote("byl / byla / bylo — was  |  byli / byly — were")
            PASTable(
                verb = "být — past",
                label = "irregular",
                rows = listOf(
                    "já" to "jsem byl / jsem byla",
                    "ty" to "jsi byl / jsi byla",
                    "on" to "byl",
                    "ona" to "byla",
                    "ono" to "bylo",
                    "my" to "jsme byli / jsme byly",
                    "vy" to "jste byli / jste byly",
                    "oni" to "byli",
                    "ony" to "byly"
                )
            )
            PASNote("For já and ty, choose byl or byla (and for my/vy: byli or byly) to match the gender of the speaker or subject.")

            PASSection("Example Verb — dělat (to do)")
            PASTable(
                verb = "dělat — past",
                label = "Type 1 (-at)",
                rows = listOf(
                    "já" to "jsem dělal / jsem dělala",
                    "ty" to "jsi dělal / jsi dělala",
                    "on" to "dělal",
                    "ona" to "dělala",
                    "ono" to "dělalo",
                    "my" to "jsme dělali / jsme dělaly",
                    "vy" to "jste dělali / jste dělaly",
                    "oni" to "dělali",
                    "ony" to "dělaly"
                )
            )

            PASSection("Dropping the Subject Pronoun — Word Order")
            PASNote("Czech very commonly drops the subject pronoun (já, ty, my, vy). When the pronoun is dropped, the helper verb (jsem / jsi / jsme / jste) moves to second position in the sentence — after the l-form or after another word.")
            PASNote("Já jsem dělal.  →  Dělal jsem.  (I was doing it. — masc.)")
            PASNote("Ty jsi dělala.  →  Dělala jsi.  (You were doing it. — fem.)")
            PASNote("My jsme byli doma.  →  Byli jsme doma.  (We were at home.)")
            PASNote("The rule is: jsem / jsi / jsme / jste must occupy the second position in the sentence — never first. The first position can be the l-form itself, or any other element you put at the front (a time word, a topic, an adverb).")
            PASNote("Ráno jsem dokončil projekt.  (In the morning, I finished the project.) — Ráno is first; jsem is second, before dokončil. The clitic does not move after the verb — it always sits in second position, whatever comes first.")
            PASNote("on / ona / ono / oni / ony never use a helper verb in the past tense, so no reordering applies to them.")

            PASSection("Reflexive Past Tense — jsi + se/si = ses/sis")
            PASNote("When jsi lands right next to the reflexive particle se or si of the verb being conjugated, standard Czech fuses them into one word — this is called a contracted form (kontrakce): jsi + se → ses, jsi + si → sis. It is not just colloquial shorthand — ses/sis is the correct, prescribed written form.")
            PASNote("Ty jsi se díval.  →  Ty ses díval.  (You were looking.)")
            PASNote("Všiml jsi si toho?  →  Všiml sis toho?  (Did you notice that?)")
            PASNote("Exception: this only fuses with the reflexive particle se, not the preposition se (a variant of s, meaning 'with'). Jsi se mnou. (You are with me.) never contracts — it stays jsi se mnou.")
            PASNote("The same fusion happens in the conditional mood, fully lexicalized: by + jsi → bys, aby + jsi → abys, kdyby + jsi → kdybys. Jsi is the one person that always fuses with what follows it — jsem / jsme / jste never do.")
            PASNote("See Reflexive Verbs for the full se/si system.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PASSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun PASNote(text: String) {
    Text(text = text, fontSize = 14.sp, color = Color.DarkGray, modifier = Modifier.padding(vertical = 4.dp))
}

@Composable
private fun PASTable(verb: String, label: String, rows: List<Pair<String, String>>) {
    Spacer(modifier = Modifier.height(8.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 2.dp, end = 2.dp, bottom = 4.dp)
    ) {
        Text(text = verb, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Text(text = label, fontSize = 13.sp, fontStyle = FontStyle.Italic, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Text("Subject", modifier = Modifier.weight(0.7f), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Text("Past form", modifier = Modifier.weight(1.3f), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
        }
        HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
        rows.forEach { (subject, form) ->
            Row(modifier = Modifier.padding(vertical = 3.dp)) {
                Text(subject, modifier = Modifier.weight(0.7f), fontSize = 14.sp, color = Color.DarkGray)
                Text(form, modifier = Modifier.weight(1.3f), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}
