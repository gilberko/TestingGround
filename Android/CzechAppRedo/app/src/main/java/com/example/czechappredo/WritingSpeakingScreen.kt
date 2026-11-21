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
fun WritingSpeakingScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Writing And Speaking", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // ── Punctuation and Symbols ───────────────────────────────────
            WSSection("Punctuation and Symbols (Interpunkce)")
            WSRow("čárka  ,", "comma", "f. — Czech uses many more commas than English: before že, který, protože, aby… — Vím, že máš pravdu.")
            WSRow("tečka  .", "full stop / period / dot", "f. — also 'dot' in web addresses: seznam tečka cz")
            WSRow("dvojtečka  :", "colon", "f. — lit. 'double dot'")
            WSRow("středník  ;", "semicolon", "m.")
            WSRow("vykřičník  !", "exclamation mark", "m. — from vykřiknout (to cry out)")
            WSRow("otazník  ?", "question mark", "m. — from otázka (question)")
            WSRow("uvozovky  „ “  \"", "quotation marks", "plural only (f. pl.). Czech writes them low-then-high: „Ahoj,“ řekl. — the opening mark sits at the bottom of the line.")
            WSRow("apostrof  '", "apostrophe", "m. — rare in Czech; appears mostly in foreign names and colloquial clipped forms")
            WSRow("procento / znak procenta  %", "percent / percent sign", "n. — 50 % = padesát procent. In Czech you put a space between the number and % (50 %); written together (50%) it means the adjective 'fifty-percent'.")
            WSRow("pomlčka  –", "dash", "f. — the longer mark between words, with spaces: Praha – Brno")
            WSRow("spojovník  -", "hyphen", "m. — the short joining mark, no spaces: česko-anglický slovník")
            WSRow("závorka  ( )", "bracket / parenthesis", "f. — v závorce = in brackets")
            WSRow("lomítko  /", "slash", "n.")
            WSRow("zavináč  @", "at sign", "m. — lit. a 'rolled herring' (rollmops), from its shape")
            WSRow("hvězdička  *", "asterisk / star", "f. — diminutive of hvězda (star)")
            WSRow("mřížka  #", "hash / pound sign", "f. — lit. 'small grid'; also called křížek")
            WSRow("tři tečky  …", "ellipsis", "lit. 'three dots'")
            WSNote("Numbers are written differently: Czech uses a decimal comma — 3,5 = tři celá pět (three point five) — and a space between thousands — 1 000 000 (one million).")

            // ── Letters, Words and Text ───────────────────────────────────
            WSSection("Letters, Words and Text (Písmena, slova a text)")
            WSRow("písmeno", "letter (a, b, c…)", "n. — Jak se píše to písmeno? = How do you write that letter?")
            WSRow("velké písmeno / malé písmeno", "capital letter / small letter", "Jména se píšou s velkým písmenem. = Names are written with a capital letter.")
            WSNote("Careful: písmeno = a letter of the alphabet; dopis = a letter you send (see below). English uses one word for both, Czech uses two.")
            WSRow("abeceda", "alphabet", "f.")
            WSRow("hláskovat", "to spell (out loud)", "Můžete to hláskovat? = Can you spell it?")
            WSRow("slovo", "word", "n. — pl. slova. Nové slovo = a new word.")
            WSRow("věta", "sentence", "f. — Napiš jednu větu. = Write one sentence.")
            WSRow("odstavec", "paragraph", "m.")
            WSRow("text", "text", "m.")
            WSRow("stránka / strana", "page", "f. — na straně deset = on page ten")
            WSRow("kapitola", "chapter", "f.")
            WSRow("slovník", "dictionary", "m.")
            WSRow("význam", "meaning", "m. — Jaký je význam toho slova? = What is the meaning of that word?")

            // ── Writing and Reading ───────────────────────────────────────
            WSSection("Writing and Reading (Psaní a čtení)")
            WSRow("psát / napsat", "to write", "imperfective / perfective: Píšu dopis. = I'm writing a letter. Napsal jsem dopis. = I wrote (finished) a letter.")
            WSRow("číst / přečíst", "to read", "Rád čtu. = I like to read. Přečetl jsem celou knihu. = I read the whole book.")
            WSRow("dopis", "letter (that you send)", "m. — Poslal jsem dopis babičce. = I sent a letter to grandma.")
            WSRow("e-mail", "email", "m. — Napiš mi e-mail. = Write me an email.")
            WSRow("zpráva", "message / news", "f. — Poslal jsem ti zprávu. = I sent you a message.")
            WSRow("poznámka", "note", "f. — dělat si poznámky = to take notes")
            WSRow("pravopis", "spelling (orthography)", "m.")
            WSRow("chyba", "mistake / error", "f. — Udělal jsem chybu. = I made a mistake.")
            WSRow("opravit", "to correct / fix", "Opravte mě, prosím. = Please correct me. Informal: Oprav mě.")

            // ── Translation ───────────────────────────────────────────────
            WSSection("Translation (Překlad)")
            WSRow("překlad", "translation", "m.")
            WSRow("překládat / přeložit", "to translate", "imperfective / perfective: Překládám knihu. = I'm translating a book.")
            WSRow("překladatel / překladatelka", "translator (m. / f.)", "translates written text")
            WSRow("tlumočník / tlumočnice", "interpreter (m. / f.)", "translates spoken language")
            WSRow("Jak se řekne ___ česky?", "How do you say ___ in Czech?", "the most natural everyday question: Jak se řekne 'apple' česky? — Jablko.")
            WSRow("Jak přeložit ___ do češtiny?", "How do you translate ___ into Czech?")
            WSRow("Můžete mi přeložit ___ do češtiny?", "Can you translate ___ into Czech for me?", "formal; informal: Můžeš mi přeložit ___ do češtiny?")
            WSRow("Co znamená ___?", "What does ___ mean?", "Co znamená 'rozumět'? — To understand.")
            WSRow("Jak se to píše?", "How is it written / spelled?")
            WSRow("do češtiny / z češtiny / do angličtiny", "into Czech / from Czech / into English", "do + genitive (to), z + genitive (from)")
            WSNote("česky is an adverb (in Czech): mluvit česky, rozumět česky, říct to česky. čeština is the noun (the Czech language): učit se češtinu, přeložit do češtiny. Same pattern: anglicky / angličtina, německy / němčina.")

            // ── Do You Speak Czech? ───────────────────────────────────────
            WSSection("Do You Speak Czech? (Mluvíte česky?)")
            WSRow("Mluvíte česky? / Mluvíš česky?", "Do you speak Czech?", "formal / informal")
            WSRow("Rozumíte česky? / Rozumíš česky?", "Do you understand Czech?", "formal / informal")
            WSRow("Mluvíte anglicky?", "Do you speak English?")
            WSRow("Ano. / Jo.", "Yes.", "ano = standard; jo = colloquial")
            WSRow("Ne.", "No.")
            WSRow("Mluvím česky.", "I speak Czech.")
            WSRow("Rozumím česky.", "I understand Czech.")
            WSRow("trochu / trošku", "a bit / a little", "trošku is the softer, more colloquial diminutive")
            WSRow("Mluvím jen trochu česky.", "I only speak a little Czech.")
            WSRow("Rozumím trošku.", "I understand a little.")
            WSRow("docela dobře", "quite well", "Mluví docela dobře česky. = He speaks Czech quite well.")
            WSRow("plynně", "fluently", "Mluví plynně česky. = She speaks Czech fluently.")
            WSRow("Nemluvím česky.", "I don't speak Czech.")
            WSRow("Nerozumím.", "I don't understand.")
            WSRow("Učím se česky.", "I'm learning Czech.", "also: Učím se češtinu.")

            // ── Asking for Help ───────────────────────────────────────────
            WSSection("Asking for Help (Prosba o pomoc)")
            WSRow("Můžete to zopakovat?", "Can you repeat that?", "informal: Můžeš to zopakovat?")
            WSRow("Můžete to vysvětlit?", "Can you explain it?", "informal: Můžeš to vysvětlit?")
            WSRow("Můžete mluvit pomaleji, prosím?", "Can you speak slower, please?")
            WSRow("Pomaleji, prosím.", "Slower, please.")
            WSRow("Rychleji, prosím.", "Faster, please.")
            WSRow("Ještě jednou, prosím.", "Once more, please.")
            WSRow("Můžete ___? / Můžeš ___?", "Can you ___?", "formal / informal + infinitive: Můžete mi pomoct? = Can you help me?")
            WSRow("Mohl byste ___? / Mohla byste ___?", "Could you ___?", "more polite (conditional); mohl to a man, mohla to a woman: Mohl byste to napsat? = Could you write it down?")
            WSRow("vysvětlit / vysvětlovat", "to explain", "perfective / imperfective")
            WSRow("zopakovat / opakovat", "to repeat", "perfective / imperfective")
            WSRow("přeložit / překládat", "to translate", "perfective / imperfective")

            // ── Useful Answers ────────────────────────────────────────────
            WSSection("Useful Answers")
            WSRow("Děkuji. / Děkuju. / Díky.", "Thank you. / Thanks.", "formal / colloquial / informal")
            WSRow("Nejsem si jistý. / Nejsem si jistá.", "I'm not sure.", "said by a man / a woman")
            WSRow("Nevím.", "I don't know.")
            WSRow("Rozumím. / Už rozumím.", "I understand. / Now I understand.")
            WSRow("Promiňte, nerozuměl jsem. / nerozuměla jsem.", "Sorry, I didn't catch that.", "said by a man / a woman")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun WSSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun WSNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun WSRow(czech: String, english: String, note: String = "") {
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
