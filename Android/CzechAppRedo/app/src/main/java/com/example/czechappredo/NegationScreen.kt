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
fun NegationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Negation", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // ── Yes and No ────────────────────────────────────────────────
            NGSection("Yes and No")
            NGRow("Ano.", "Yes.")
            NGRow("Ne.", "No.")

            // ── Neither...nor ─────────────────────────────────────────────
            NGSection("Neither...nor (ani...ani)")
            NGRow("ani ... ani ...", "neither ... nor ...")
            NGRow("Není to ani nové, ani staré.", "It is neither new nor old.")
            NGRow("Nemám ani auto, ani kolo.", "I have neither a car nor a bike.")
            NGNote("The verb must also be negated: není = is not, nemám = don't have.")

            // ── Negating Adjectives ───────────────────────────────────────
            NGSection("Negating Adjectives")
            NGNote("In Czech you negate the verb \"být\" (to be), not the adjective itself.")
            NGRow("Není nový.", "It is not new.", "není = is not")
            NGRow("Není starý.", "It is not old.")
            NGNote("Some adjectives have a \"ne-\" prefix form that creates an ANTONYM (not just negation): šťastný → nešťastný (happy → unhappy), zdravý → nezdravý (healthy → unhealthy). These are real opposites, not simple negation.")

            // ── Not Too... ────────────────────────────────────────────────
            NGSection("Not Too... (ne příliš)")
            NGRow("Není příliš starý.", "It is not too old.")
            NGRow("Není příliš nový.", "It is not too new.")
            NGRow("Není příliš drahý.", "It is not too expensive.")

            // ── Negating Verbs ────────────────────────────────────────────
            NGSection("Negating Verbs (ne- prefix)")
            NGNote("Add \"ne-\" directly before the conjugated verb form.")
            NGRow("Vidím. → Nevidím.", "I see. → I don't see.", "nevidím = I don't see; nevidí = he/she doesn't see")
            NGRow("Mluvím. → Nemluvím.", "I speak. → I don't speak.")
            NGRow("Mám. → Nemám.", "I have. → I don't have.")
            NGRow("Jdu. → Nejdu.", "I go. → I don't go.")
            NGNote("Special verb být (to be) — irregular negation:")
            NGRow("jsem → nejsem", "I am → I am not")
            NGRow("je → není", "it / he / she is → is not")
            NGRow("jsou → nejsou", "they are → they are not")
            NGRow("jsme → nejsme", "we are → we are not")
            NGRow("jste → nejste", "you are → you are not (formal / plural)")

            // ── Double Negation ───────────────────────────────────────────
            NGSection("Double Negation (Required in Czech!)")
            NGNote("Czech REQUIRES double negation. Unlike English where \"I don't see nothing\" is wrong, in Czech this structure is grammatically correct and required.")
            NGRow("Nevidím nic.", "I don't see anything.", "literally: I don't see nothing")
            NGRow("Nemám nic.", "I don't have anything.")
            NGRow("Nikdo nepřišel.", "Nobody came.", "nikdo = nobody, nepřišel = didn't come")
            NGNote("Negative words: nic (nothing), nikdo (nobody), nikdy (never), nikde (nowhere).")

            // ── I Don't Have... ───────────────────────────────────────────
            NGSection("I Don't Have... (Nemám)")
            NGNote("In a positive sentence, the object is in the accusative case: Mám kočku. (I have a cat.) In a negative sentence, everyday Czech keeps the accusative case — it does not change.")
            NGRow("Mám kočku.", "I have a cat.", "kočka → accusative: kočku")
            NGRow("Nemám kočku.", "I don't have a cat.", "same accusative form: kočku")
            NGRow("Mám psa.", "I have a dog.", "pes → accusative: psa")
            NGRow("Nemám psa.", "I don't have a dog.", "same form: psa")
            NGNote("Grammar note: Traditional Czech grammar teaches a \"genitive of negation\" (záporný genitiv) — the object shifts to genitive when the verb is negated. Example: Nemám kočky. (kočky = genitive). This form is more formal or literary. In modern everyday speech, the accusative (Nemám kočku.) is just as correct and much more common.")
            NGNote("For masculine animate nouns like pes, the accusative and genitive are the same form (psa), so there is no practical difference either way.")

            // ── Without (bez) ─────────────────────────────────────────────
            NGSection("Without (bez + genitive)")
            NGNote("\"bez\" (without) always takes the genitive case.")
            NGRow("Chci salát bez cibule.", "I want a salad without onion.", "cibule (f.) → genitive: cibule")
            NGRow("Káva bez cukru.", "Coffee without sugar.", "cukr (m.) → genitive: cukru")
            NGRow("Čaj bez mléka.", "Tea without milk.", "mléko (n.) → genitive: mléka")
            NGRow("Pizza bez masa.", "Pizza without meat.", "maso (n.) → genitive: masa")
            NGRow("Bez problémů.", "Without problems.", "problém (m.) → genitive plural: problémů")
            NGNote("Tip: bez is one of the most common genitive prepositions. The genitive singular endings to remember: masculine -a/-u, feminine -y/-e, neuter -a.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun NGSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun NGNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun NGRow(czech: String, english: String, note: String = "") {
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
