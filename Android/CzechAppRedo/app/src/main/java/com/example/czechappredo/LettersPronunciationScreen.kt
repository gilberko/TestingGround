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
fun LettersPronunciationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Letters & Pronunciation", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // ── 0. Czech Alphabet — Letter Names ─────────────────────────
            LPSectionHeader("Czech Alphabet — Letter Names")
            LPNote("When spelling a word aloud, Czechs say each letter by its name — just like English speakers say \"ay, bee, see\". Czech has 42 letters. ch is its own letter in the alphabet, listed after h.")
            LPNote("Each entry: letter — Czech name.")
            LPNote("The Czech word for a letter of the alphabet is písmeno (n., pl. písmena).")
            val alphabet = listOf(
                "a" to "a", "á" to "á", "b" to "bé", "c" to "cé",
                "č" to "čé", "d" to "dé", "ď" to "ďé", "e" to "e",
                "é" to "é", "ě" to "ě", "f" to "ef", "g" to "gé",
                "h" to "há", "ch" to "chá", "i" to "í", "í" to "í",
                "j" to "jé", "k" to "ká", "l" to "el", "m" to "em",
                "n" to "en", "ň" to "eň", "o" to "o", "ó" to "ó",
                "p" to "pé", "q" to "kvé", "r" to "er", "ř" to "eř",
                "s" to "es", "š" to "eš", "t" to "té", "ť" to "ťé",
                "u" to "u", "ú" to "ú", "ů" to "ů", "v" to "vé",
                "w" to "dvojité vé", "x" to "iks", "y" to "ypsilon",
                "ý" to "ý", "z" to "zet", "ž" to "žet"
            )
            LPAlphabetTable(alphabet)

            // ── 1. Word Stress ───────────────────────────────────────────
            LPSectionHeader("Word Stress")
            LPNote("Czech stress always falls on the first syllable of every word, no matter how long the word is. Long vowels (á, é, í…) make a vowel sound longer — they do NOT move the stress.")
            LPNote("Example: ne-dě-le (Sunday) — stress is on \"ne\", not on the long ě.")

            // ── 2. Short Vowels ──────────────────────────────────────────
            LPSectionHeader("Short Vowels")
            LPRow("a", "\"a\" in father", name = "a")
            LPRow("e", "\"e\" in bed", name = "e")
            LPRow("i / y", "\"i\" in sit  (i and y are identical in sound)", name = "í / ypsilon")
            LPRow("o", "\"o\" in hot", name = "o")
            LPRow("u", "\"oo\" in book", name = "u")

            // ── 3. Long Vowels ───────────────────────────────────────────
            LPSectionHeader("Long Vowels — held about twice as long")
            LPRow("á", "\"a\" in father, held longer", name = "á")
            LPRow("é", "\"e\" in bed, held longer", name = "é")
            LPRow("í / ý", "\"ee\" in see  (both letters spell the same sound)", name = "í / ý")
            LPRow("ó", "\"o\" in more, held longer  (rare — mostly loanwords)", name = "ó")
            LPRow("ú / ů", "\"oo\" in moon  (identical sound; ů never starts a word)", name = "ú / ů")

            // ── 4. ě — The Softening Vowel ───────────────────────────────
            LPSectionHeader("ě — The Softening Vowel")
            LPNote("This section and the next one are both examples of softening (also called palatalization) — a consonant shifting toward a softer, \"y\"-like articulation under the influence of a neighboring vowel like i, í, or ě.")
            LPNote("ě is not simply a longer e — it changes the consonant that comes before it:")
            LPRow("dě", "→ \"dye\"")
            LPRow("tě", "→ \"tye\"")
            LPRow("ně", "→ \"nye\"")
            LPRow("mě", "→ \"mnye\"")
            LPNote("Example: město (city) → sounds like \"mnye-sto\"")
            LPNote("After any other consonant, ě simply sounds like \"ye\".")

            // ── 5. Softening Before i and í ──────────────────────────────
            LPSectionHeader("Softening Before i and í")
            LPNote("When d, t, or n is written before i or í, they are automatically pronounced as their soft counterparts ď, ť, ň. The spelling does not change — only the pronunciation does. This applies equally to short i and long í.")
            LPRow("di / dí", "→ pronounced like ď + i/í", note = "Example: dítě (child) — the d sounds like ď")
            LPRow("ti / tí", "→ pronounced like ť + i/í", note = "Example: tichý (quiet) — the t sounds like ť; tisíc (thousand) — both t sounds softened")
            LPRow("ni / ní", "→ pronounced like ň + i/í", note = "Example: nic (nothing) — the n sounds like ň; nikdo (nobody) — the n sounds like ň")
            LPNote("The written háček forms ď, ť, ň are only needed when these soft sounds appear before other vowels (a, e, o, u). Before i/í, the softening is automatic and silent in the spelling.")

            // ── 6. Consonants With Háček ─────────────────────────────────
            LPSectionHeader("Consonants With Háček  ( ˇ )")
            LPRow("č", "\"ch\" in cheese", name = "čé")
            LPRow("š", "\"sh\" in shoe", name = "eš")
            LPRow("ž", "\"s\" in measure  /  French j", name = "žet")
            LPRow("ř", "Unique Czech sound — a simultaneous rolled-r and ž. Like trilling your tongue while saying \"zh\". Widely considered the hardest Czech sound for foreigners.", note = "Example: řeka (river), Dvořák", name = "eř")
            LPRow("ň", "\"ny\" in canyon", name = "eň")
            LPRow("ď", "\"dy\" — soft d, like \"dew\" said quickly", name = "ďé")
            LPRow("ť", "\"ty\" — soft t, like \"tune\" said quickly", name = "ťé")

            // ── 6b. Why Does r Sometimes Become ř? ────────────────────────
            LPSectionHeader("Why Does r Sometimes Become ř?")
            LPNote("This isn't random — it's a fossilized sound change. In Proto-Slavic (the common ancestor of all Slavic languages, roughly the 6th–9th centuries), a \"soft\" r existed whenever r was followed by a front vowel (e, ě, i) or the sound j — contrasting with plain, \"hard\" r elsewhere. Most Slavic languages later let this soft r merge back into plain r (Russian still keeps it as a palatalized рь, e.g. буря — storm). Czech took it further: between roughly the 12th and 14th centuries (Old Czech), that soft r evolved into an entirely new sound — a simultaneous trill and fricative, written ř. Polish underwent the same split but resolved it differently, merging its soft r into ž to create the digraph rz. So ř and Polish rz are historical siblings — two different outcomes of the same old sound.")
            LPNote("The same softening happened to other consonants before front vowels around the same time (a stage called the second palatalization), each getting its own permanent \"soft\" replacement:")
            LPRow("k → c", "", note = "ruka (hand) → v ruce (in the hand)")
            LPRow("h → z", "", note = "noha (leg) → v noze (in the leg); Praha → v Praze (in Prague)")
            LPRow("ch → š", "", note = "moucha (fly) → na mouše (on the fly)")
            LPRow("r → ř", "", note = "sestra (sister) → sestře (to/about sister); díra (hole) → díře (in the hole)")
            LPNote("You'll meet r → ř most often in three places: feminine dative/locative singular (sestra → sestře, díra → díře); masculine vocative singular — the form used to call out to someone (bratr → bratře!, Petr → Petře!); and the comparative of adjectives ending in -r (ostrý 'sharp' → ostřejší 'sharper').")
            LPNote("It does NOT happen every time r is followed by e or i, because this is no longer a live pronunciation rule — it's frozen into specific old grammatical endings. Loanwords borrowed after the change stopped being productive skip it entirely: profesor (professor) → vocative pane profesore! (not profesoře), doktor → doktore. Different endings also skip it: the verb brát (to take) has berete (you take) and ber! (take!), both with a plain r, because those endings come from a different historical layer than the one that caused softening.")

            // ── 6c. Hard, Soft, and Ambiguous Consonants ──────────────────
            LPSectionHeader("Hard, Soft, and Ambiguous Consonants")
            LPNote("Czech school grammar sorts every consonant into one of three groups — tvrdé (hard), měkké (soft), and obojetné (ambiguous). This grouping isn't about how the letters sound; it's about which vowel, i/í or y/ý, is allowed to follow them in spelling.")
            LPRow("Hard (tvrdé)", "h, ch, k, r, d, t, n, g")
            LPRow("Soft (měkké)", "ž, š, č, ř, c, j, ď, ť, ň", note = "Exactly the háček consonants from above, plus c and j.")
            LPRow("Ambiguous (obojetné)", "b, f, l, m, p, s, v, z")
            LPNote("The effect: after a hard consonant, Czech always writes y/ý (\"tvrdé y\"). After a soft consonant, Czech always writes i/í (\"měkké i\"). After an ambiguous consonant, either can appear — which one is used depends on the specific word or grammatical ending and simply has to be learned/memorized. This is a spelling distinction only: i/y and í/ý are pronounced identically in modern Czech.")
            LPNote("This same hard/soft split reappears in noun declension: Czech noun patterns (vzory) are grouped into hard-stem and soft-stem types, each with their own case endings — see the Noun Conjugation and Cases screens.")

            // ── 7. Letters That Surprise English Speakers ────────────────
            LPSectionHeader("Letters That Surprise English Speakers")
            LPRow("c", "\"ts\" in cats  (never \"k\" or \"s\" alone)", name = "cé")
            LPRow("j", "\"y\" in yes  (never \"dj\")", note = "js- words (jsem, jsi, jsme, jste, jsou) are a special case. In careful/formal speech the j is a very light y-sound. In colloquial Bohemian Czech (Prague and central Bohemia) the j is typically dropped entirely — jsem becomes \"sem\", jsme becomes \"sme\". In Moravian speech the j tends to be more clearly pronounced. Both forms are widely heard; this is one of the most recognizable features of everyday spoken Czech.", name = "jé")
            LPRow("ch", "\"kh\" — like Scottish loch or German Bach. A raspy sound made in the throat.", note = "ch is its own letter in the Czech alphabet, listed after h.", name = "chá")
            LPRow("h", "breathy, voiced — slightly softer than English h", name = "há")
            LPRow("w", "same as v  (only in foreign words)", name = "dvojité vé")
            LPRow("q", "\"kv\"  (only in foreign words)", name = "kvé")
            LPRow("x", "\"ks\"  (only in foreign words)", name = "iks")

            // ── 8. Letter Combinations That Change Sound ─────────────────
            LPSectionHeader("Letter Combinations That Change Sound")
            LPRow("ch", "Always \"kh\" — never read as k + h separately. It is a single letter.")
            LPRow("dž", "like \"j\" in jungle. Appears in loanwords.", note = "Example: džem (jam), džíny (jeans)")
            LPNote("The combination of a consonant + ě also changes pronunciation — see the ě section above.")

            // ── 9. Final Devoicing ───────────────────────────────────────
            LPSectionHeader("Final Devoicing")
            LPNote("Voicing assimilation is when neighboring consonants harmonize so they share the same voiced/voiceless status. Czech consonants come in voiced/voiceless pairs, and the language applies this harmonizing in two situations: at the end of a word (devoicing, below) and inside consonant clusters (see Voice Assimilation in Consonant Clusters further down).")
            LPNote("At the end of a word, voiced consonants automatically become their voiceless counterpart. The spelling does not change — only the pronunciation does.")
            LPRow("b → p", "")
            LPRow("d → t", "")
            LPRow("ď → ť", "")
            LPRow("g → k", "")
            LPRow("h → ch", "")
            LPRow("v → f", "")
            LPRow("z → s", "")
            LPRow("ž → š", "")
            LPRow("dž → č", "")
            LPNote("Example: hrad (castle) is spelled with d but pronounced \"hrat\". Another: sníh (snow) is spelled with h but pronounced \"sních\".")

            // ── 10. Voice Assimilation in Clusters ────────────────────────
            LPSectionHeader("Voice Assimilation in Consonant Clusters")
            LPNote("When two consonants appear together, they harmonize in voicing. The last consonant in the cluster decides whether the whole group is voiced or voiceless.")
            LPRow("kde (where)", "k is normally voiceless, but before voiced d it becomes voiced → \"gde\"")
            LPRow("vstát (to stand up)", "v is voiced, but before voiceless st it becomes f → \"fstát\"")

            // ── 11. Syllabic r and l ─────────────────────────────────────
            LPSectionHeader("Syllabic r and l")
            LPNote("In Czech, r and l can act as vowels — they carry a whole syllable by themselves, with no vowel needed.")
            LPRow("krk", "neck — one syllable: k‑R‑k  (no vowel at all)")
            LPRow("vlk", "wolf — one syllable: v‑L‑k")
            LPRow("prst", "finger — one syllable: p‑R‑st")

            // ── 12. The Czech Diacritical Marks ──────────────────────────
            LPSectionHeader("The Czech Diacritical Marks")
            LPNote("Czech uses three special signs that modify letters. Knowing their names helps when reading grammar rules or looking words up.")
            LPRow("Čárka  ( ´ )", "Acute accent — lengthens a vowel", note = "Used on: á, é, í, ý, ó, ú")
            LPRow("Háček  ( ˇ )", "Caron / wedge — softens a consonant or changes a vowel's sound", note = "Used on consonants: č, š, ž, ř, ň, ď, ť — and on the vowel: ě")
            LPRow("Kroužek  ( ˚ )", "Ring above — marks the long vowel ů", note = "Only ever appears on ů; no other Czech letter uses kroužek")

            // ── 13. Ú vs Ů ───────────────────────────────────────────────
            LPSectionHeader("Ú vs Ů — Same Sound, Different Mark")
            LPNote("Both ú and ů are pronounced identically — a long \"oo\" as in moon. The mark you write depends entirely on where the long U appears in the word.")
            LPRow("u", "short — \"oo\" in book", note = "e.g., studovat (to study), ulice (street)")
            LPRow("ú", "long — \"oo\" in moon, written with čárka", note = "Used at the beginning of a word, or in foreign loanwords regardless of position")
            LPRow("ů", "long — \"oo\" in moon, written with kroužek", note = "Used in the middle or end of a word in native Czech words; never at the start")
            LPNote("The position rule — ů at the start: never. Every Czech word that begins with a long U uses ú, not ů. This is a hard rule with no exceptions.")
            LPNote("ú at the start — examples: útok (attack), úkol (task), úžasný (amazing), území (territory), úspěch (success), úsměv (smile).")
            LPNote("ů in the middle or end — examples: dům (house), stůl (table), půl (half), nůž (knife), můj (my), domů (home — direction), růže (rose).")
            LPNote("Foreign loanwords keep ú regardless of position — ů is reserved for native Czech words of Slavic origin. This is also why ů reflects a historical sound shift: long ó in Old Czech gradually changed ó → uo → ů over centuries, which is why it appears in native vocabulary but never in borrowings.")

            // ── 14. Punctuation Marks ────────────────────────────────
            LPSectionHeader("Punctuation Marks")
            LPNote("Common symbols: their English name and Czech name.")
            LPRow(",",  "comma",                                                         name = "čárka")
            LPRow(".",  "period  (British English: full stop)",                          name = "tečka")
            LPRow(";",  "semicolon",                                                     name = "středník")
            LPRow("-",  "hyphen",                                                        name = "spojovník — joins compound words (e.g. česko-slovenský)")
            LPRow("—",  "dash  (em dash)",                                               name = "pomlčka — used for dialogue, pauses, parenthetical remarks; Czech uses the long em dash (—)")
            LPRow(":",  "colon  (note: in English this is not called \"double dots\")",  name = "dvojtečka  (= double dot)")
            LPRow("( )", "parentheses  (singular: parenthesis)",                         name = "závorky  /  kulatá závorka")
            LPRow("?",  "question mark",                                                 name = "otazník")
            LPRow("!",  "exclamation mark  (American English: exclamation point)",       name = "vykřičník")
            LPRow("'",  "apostrophe  /  single quote",                                  name = "apostrof  /  jednoduchá uvozovka")
            LPRow("\"\"", "double quotes  /  quotation marks",                          name = "uvozovky  (Czech style: „text\")")
            LPRow("@",  "at sign  (note: & is ampersand — a different symbol)",         name = "zavináč")
            LPRow("&",  "ampersand  (means \"and\"; used in company names, logos)",      name = "ampersand  /  et")
            LPRow("/",  "slash  (also: forward slash)",                                  name = "lomítko")
            LPRow("\\", "backslash",                                                     name = "zpětné lomítko")
            LPRow("_",  "underscore  (also: underline character)",                       name = "podtržítko")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun LPSectionHeader(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = ButtonBlue
    )
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun LPAlphabetTable(entries: List<Pair<String, String>>) {
    entries.chunked(2).forEach { row ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            row.forEach { (letter, name) ->
                Row(modifier = Modifier.weight(1f)) {
                    Text(
                        text = letter,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "  —  $name",
                        fontSize = 15.sp,
                        color = Color.DarkGray
                    )
                }
            }
            if (row.size == 1) Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun LPRow(letter: String, pronunciation: String, note: String = "", name: String = "") {
    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        if (pronunciation.isNotEmpty()) {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)) {
                        append(letter)
                    }
                    withStyle(SpanStyle(fontSize = 16.sp, color = Color.DarkGray)) {
                        append("  —  $pronunciation")
                    }
                }
            )
        } else {
            Text(
                text = letter,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        if (name.isNotEmpty()) {
            Text(
                text = "Czech name: $name",
                fontSize = 12.sp,
                fontStyle = FontStyle.Italic,
                color = ButtonBlue,
                modifier = Modifier.padding(start = 4.dp, top = 1.dp)
            )
        }
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

@Composable
private fun LPNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}
