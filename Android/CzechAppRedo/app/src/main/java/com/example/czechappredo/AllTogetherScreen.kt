package com.example.czechappredo

import androidx.compose.foundation.background
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

// ── Data ──────────────────────────────────────────────────────────────────────
// Each table row = possessive form + adjective/noun form for the same case.
// Case order everywhere: Nominativ, Genitiv, Dativ, Akuzativ, Vokativ, Lokál, Instrumentál.

enum class ATGender(val title: String, val animate: Boolean) {
    MASC_INANIMATE("Masculine (Inanimate)", false),
    MASC_ANIMATE("Masculine (Animate)", true),
    NEUTER("Neuter", false),
    FEMININE("Feminine", true)
}

private val atCaseLabels = listOf(
    "1. Nominativ", "2. Genitiv", "3. Dativ", "4. Akuzativ",
    "5. Vokativ", "6. Lokál", "7. Instrumentál"
)

private data class ATExpression(
    val english: String,
    val englishPlural: String,
    val sg: List<String>,
    val pl: List<String>,
    val animate: Boolean = false,
    val note: String? = null
)

private data class ATPossessive(
    val english: String,
    val lemma: String,
    val forms: Map<ATGender, Pair<List<String>, List<String>>>
)

private val mujForms: Map<ATGender, Pair<List<String>, List<String>>> = mapOf(
    ATGender.MASC_ANIMATE to (listOf("můj", "mého", "mému", "mého", "můj", "mém", "mým") to
        listOf("moji", "mých", "mým", "moje", "moji", "mých", "mými")),
    ATGender.MASC_INANIMATE to (listOf("můj", "mého", "mému", "můj", "můj", "mém", "mým") to
        listOf("moje", "mých", "mým", "moje", "moje", "mých", "mými")),
    ATGender.NEUTER to (listOf("moje", "mého", "mému", "moje", "moje", "mém", "mým") to
        listOf("moje", "mých", "mým", "moje", "moje", "mých", "mými")),
    ATGender.FEMININE to (listOf("moje", "mé", "mé", "moji", "moje", "mé", "mojí") to
        listOf("moje", "mých", "mým", "moje", "moje", "mých", "mými"))
)

private val nasForms: Map<ATGender, Pair<List<String>, List<String>>> = mapOf(
    ATGender.MASC_ANIMATE to (listOf("náš", "našeho", "našemu", "našeho", "náš", "našem", "naším") to
        listOf("naši", "našich", "našim", "naše", "naši", "našich", "našimi")),
    ATGender.MASC_INANIMATE to (listOf("náš", "našeho", "našemu", "náš", "náš", "našem", "naším") to
        listOf("naše", "našich", "našim", "naše", "naše", "našich", "našimi")),
    ATGender.NEUTER to (listOf("naše", "našeho", "našemu", "naše", "naše", "našem", "naším") to
        listOf("naše", "našich", "našim", "naše", "naše", "našich", "našimi")),
    ATGender.FEMININE to (listOf("naše", "naší", "naší", "naši", "naše", "naší", "naší") to
        listOf("naše", "našich", "našim", "naše", "naše", "našich", "našimi"))
)

private val jejiForms: Map<ATGender, Pair<List<String>, List<String>>> = mapOf(
    ATGender.MASC_ANIMATE to (listOf("její", "jejího", "jejímu", "jejího", "její", "jejím", "jejím") to
        listOf("její", "jejích", "jejím", "její", "její", "jejích", "jejími")),
    ATGender.MASC_INANIMATE to (listOf("její", "jejího", "jejímu", "její", "její", "jejím", "jejím") to
        listOf("její", "jejích", "jejím", "její", "její", "jejích", "jejími")),
    ATGender.NEUTER to (listOf("její", "jejího", "jejímu", "její", "její", "jejím", "jejím") to
        listOf("její", "jejích", "jejím", "její", "její", "jejích", "jejími")),
    ATGender.FEMININE to (List(7) { "její" } to
        listOf("její", "jejích", "jejím", "její", "její", "jejích", "jejími"))
)

private fun indeclinable(word: String): Map<ATGender, Pair<List<String>, List<String>>> =
    ATGender.entries.associateWith { List(7) { word } to List(7) { word } }

// tvůj declines exactly like můj (m → tv), váš exactly like náš (n → v)
private fun swapStem(
    forms: Map<ATGender, Pair<List<String>, List<String>>>,
    from: String,
    to: String
): Map<ATGender, Pair<List<String>, List<String>>> =
    forms.mapValues { (_, p) ->
        p.first.map { to + it.removePrefix(from) } to p.second.map { to + it.removePrefix(from) }
    }

private val atPossessives = listOf(
    ATPossessive("my", "můj / moje", mujForms),
    ATPossessive("your (singular, informal)", "tvůj / tvoje", swapStem(mujForms, "m", "tv")),
    ATPossessive("your (plural / formal)", "váš / vaše", swapStem(nasForms, "n", "v")),
    ATPossessive("his", "jeho", indeclinable("jeho")),
    ATPossessive("her", "její", jejiForms),
    ATPossessive("our", "náš / naše", nasForms),
    ATPossessive("their", "jejich", indeclinable("jejich"))
)

private val atExpressions: Map<ATGender, List<ATExpression>> = mapOf(
    ATGender.MASC_ANIMATE to listOf(
        ATExpression("smart husband", "smart husbands",
            listOf("chytrý manžel", "chytrého manžela", "chytrému manželovi", "chytrého manžela", "chytrý manželi", "chytrém manželovi", "chytrým manželem"),
            listOf("chytří manželé", "chytrých manželů", "chytrým manželům", "chytré manžele", "chytří manželé", "chytrých manželích", "chytrými manželi"),
            animate = true,
            note = "r → ř softening in the plural (chytrý → chytří). Dativ/Lokál singular also allow manželi."),
        ATExpression("old friend", "old friends",
            listOf("starý přítel", "starého přítele", "starému příteli", "starého přítele", "starý příteli", "starém příteli", "starým přítelem"),
            listOf("staří přátelé", "starých přátel", "starým přátelům", "staré přátele", "staří přátelé", "starých přátelích", "starými přáteli"),
            animate = true,
            note = "přítel has an irregular plural: přátelé. Dativ/Lokál singular also allow přítelovi."),
        ATExpression("tall brother", "tall brothers",
            listOf("vysoký bratr", "vysokého bratra", "vysokému bratrovi", "vysokého bratra", "vysoký bratře", "vysokém bratrovi", "vysokým bratrem"),
            listOf("vysocí bratři", "vysokých bratrů", "vysokým bratrům", "vysoké bratry", "vysocí bratři", "vysokých bratrech", "vysokými bratry"),
            animate = true,
            note = "k → c softening in the plural (vysoký → vysocí); vocative bratře."),
        ATExpression("good teacher", "good teachers",
            listOf("dobrý učitel", "dobrého učitele", "dobrému učiteli", "dobrého učitele", "dobrý učiteli", "dobrém učiteli", "dobrým učitelem"),
            listOf("dobří učitelé", "dobrých učitelů", "dobrým učitelům", "dobré učitele", "dobří učitelé", "dobrých učitelích", "dobrými učiteli"),
            animate = true,
            note = "učitel is a soft noun (pattern muž/učitel), compare with the hard bratr. Dativ/Lokál singular also allow učitelovi.")
    ),
    ATGender.MASC_INANIMATE to listOf(
        ATExpression("broken plate", "broken plates",
            listOf("rozbitý talíř", "rozbitého talíře", "rozbitému talíři", "rozbitý talíř", "rozbitý talíři", "rozbitém talíři", "rozbitým talířem"),
            listOf("rozbité talíře", "rozbitých talířů", "rozbitým talířům", "rozbité talíře", "rozbité talíře", "rozbitých talířích", "rozbitými talíři")),
        ATExpression("white castle", "white castles",
            listOf("bílý hrad", "bílého hradu", "bílému hradu", "bílý hrad", "bílý hrade", "bílém hradě", "bílým hradem"),
            listOf("bílé hrady", "bílých hradů", "bílým hradům", "bílé hrady", "bílé hrady", "bílých hradech", "bílými hrady"),
            note = "Lokál singular can be hradě or hradu (na hradě is the most common)."),
        ATExpression("tasty hamburger", "tasty hamburgers",
            listOf("chutný hamburger", "chutného hamburgeru", "chutnému hamburgeru", "chutný hamburger", "chutný hamburgere", "chutném hamburgeru", "chutným hamburgerem"),
            listOf("chutné hamburgery", "chutných hamburgerů", "chutným hamburgerům", "chutné hamburgery", "chutné hamburgery", "chutných hamburgerech", "chutnými hamburgery")),
        ATExpression("big table", "big tables",
            listOf("velký stůl", "velkého stolu", "velkému stolu", "velký stůl", "velký stole", "velkém stole", "velkým stolem"),
            listOf("velké stoly", "velkých stolů", "velkým stolům", "velké stoly", "velké stoly", "velkých stolech", "velkými stoly"),
            note = "stůl shortens its vowel in every other form: stůl → stolu, stole, stoly.")
    ),
    ATGender.NEUTER to listOf(
        ATExpression("fast car", "fast cars",
            listOf("rychlé auto", "rychlého auta", "rychlému autu", "rychlé auto", "rychlé auto", "rychlém autě", "rychlým autem"),
            listOf("rychlá auta", "rychlých aut", "rychlým autům", "rychlá auta", "rychlá auta", "rychlých autech", "rychlými auty"),
            note = "auto is neuter in Czech, even though English speakers often expect a masculine noun."),
        ATExpression("red pen", "red pens",
            listOf("červené pero", "červeného pera", "červenému peru", "červené pero", "červené pero", "červeném peru", "červeným perem"),
            listOf("červená pera", "červených per", "červeným perům", "červená pera", "červená pera", "červených perech", "červenými pery"),
            note = "pero (pen) is neuter. Lokál singular: (o) peru."),
        ATExpression("new bicycle", "new bicycles",
            listOf("nové kolo", "nového kola", "novému kolu", "nové kolo", "nové kolo", "novém kole", "novým kolem"),
            listOf("nová kola", "nových kol", "novým kolům", "nová kola", "nová kola", "nových kolech", "novými koly"),
            note = "Lokál singular: (na) kole."),
        ATExpression("big window", "big windows",
            listOf("velké okno", "velkého okna", "velkému oknu", "velké okno", "velké okno", "velkém okně", "velkým oknem"),
            listOf("velká okna", "velkých oken", "velkým oknům", "velká okna", "velká okna", "velkých oknech", "velkými okny"),
            note = "Genitiv plural inserts an e: oken.")
    ),
    ATGender.FEMININE to listOf(
        ATExpression("smart girlfriend", "smart girlfriends",
            listOf("chytrá přítelkyně", "chytré přítelkyně", "chytré přítelkyni", "chytrou přítelkyni", "chytrá přítelkyně", "chytré přítelkyni", "chytrou přítelkyní"),
            listOf("chytré přítelkyně", "chytrých přítelkyň", "chytrým přítelkyním", "chytré přítelkyně", "chytré přítelkyně", "chytrých přítelkyních", "chytrými přítelkyněmi"),
            animate = true,
            note = "přítelkyně is a soft feminine noun (pattern růže)."),
        ATExpression("lucky wife", "lucky wives",
            listOf("šťastná manželka", "šťastné manželky", "šťastné manželce", "šťastnou manželku", "šťastná manželko", "šťastné manželce", "šťastnou manželkou"),
            listOf("šťastné manželky", "šťastných manželek", "šťastným manželkám", "šťastné manželky", "šťastné manželky", "šťastných manželkách", "šťastnými manželkami"),
            animate = true,
            note = "k → c before -e in Dativ/Lokál singular (manželce)."),
        ATExpression("new pencil", "new pencils",
            listOf("nová tužka", "nové tužky", "nové tužce", "novou tužku", "nová tužko", "nové tužce", "novou tužkou"),
            listOf("nové tužky", "nových tužek", "novým tužkám", "nové tužky", "nové tužky", "nových tužkách", "novými tužkami"),
            note = "tužka (pencil) is feminine. k → c in Dativ/Lokál singular (tužce)."),
        ATExpression("white glove", "white gloves",
            listOf("bílá rukavice", "bílé rukavice", "bílé rukavici", "bílou rukavici", "bílá rukavice", "bílé rukavici", "bílou rukavicí"),
            listOf("bílé rukavice", "bílých rukavic", "bílým rukavicím", "bílé rukavice", "bílé rukavice", "bílých rukavicích", "bílými rukavicemi"),
            note = "rukavice (glove) is a soft feminine noun."),
        ATExpression("loud guitar", "loud guitars",
            listOf("hlasitá kytara", "hlasité kytary", "hlasité kytaře", "hlasitou kytaru", "hlasitá kytaro", "hlasité kytaře", "hlasitou kytarou"),
            listOf("hlasité kytary", "hlasitých kytar", "hlasitým kytarám", "hlasité kytary", "hlasité kytary", "hlasitých kytarách", "hlasitými kytarami"),
            note = "r → ř before -e in Dativ/Lokál singular (kytaře)."),
        ATExpression("spicy meatball", "spicy meatballs",
            listOf("pálivá masová kulička", "pálivé masové kuličky", "pálivé masové kuličce", "pálivou masovou kuličku", "pálivá masová kuličko", "pálivé masové kuličce", "pálivou masovou kuličkou"),
            listOf("pálivé masové kuličky", "pálivých masových kuliček", "pálivým masovým kuličkám", "pálivé masové kuličky", "pálivé masové kuličky", "pálivých masových kuličkách", "pálivými masovými kuličkami"),
            note = "Meatballs = masové kuličky (literally \"meat balls\"), so there are two adjectives here and both agree with the noun.")
    )
)

// ── Screens ───────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllTogetherHubScreen(navController: NavController) {
    HubBackground {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("All Together", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        val items = listOf(
            "Masculine (Inanimate)" to "all_together_masc_inanimate",
            "Masculine (Animate)" to "all_together_masc_animate",
            "Neuter" to "all_together_neuter",
            "Feminine" to "all_together_feminine"
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items.chunked(2).forEach { pair ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    pair.forEach { (label, route) ->
                        DictNavButton(label = label, modifier = Modifier.weight(1f)) {
                            navController.navigate(route)
                        }
                    }
                    if (pair.size == 1) Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllTogetherGenderScreen(navController: NavController, gender: ATGender) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("All Together — ${gender.title}", fontSize = 15.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
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
            ATNote("Possessive pronoun + adjective + noun, all agreeing in gender, number and case. Every expression is paired with each possessive: my, your (singular), your (plural / formal), his, her, our, their. Each pairing gets a singular table and a plural table.")
            ATNote("Gender follows the Czech noun, not the English word. For example, auto (car) and pero (pen) are neuter, and tužka (pencil), rukavice (glove) and kytara (guitar) are feminine.")
            ATNote("Vokativ: possessive pronouns have no separate vocative form, so they keep their Nominativ form (můj milý příteli!). Only the adjective and noun change.")
            if (gender == ATGender.MASC_ANIMATE) {
                ATNote("Masculine animate is the only gender where Akuzativ singular = Genitiv (mého chytrého manžela), and where the plural Nominativ (moji chytří manželé) differs from the plural Akuzativ (moje chytré manžele).")
            }
            if (gender == ATGender.MASC_INANIMATE) {
                ATNote("Masculine inanimate: Akuzativ = Nominativ in both singular and plural (vidím můj bílý hrad / moje bílé hrady).")
            }

            atExpressions.getValue(gender).forEach { expr ->
                ATExpressionHeader(expr)
                atPossessives.forEach { poss ->
                    val (pSg, pPl) = poss.forms.getValue(gender)
                    ATPairingHeader(
                        czech = "${pSg[0]} ${expr.sg[0]}",
                        english = "${poss.english.substringBefore(" (")} ${expr.english}",
                        possLabel = "${poss.lemma} — ${poss.english}"
                    )
                    ATTable("Singular", pSg, expr.sg, expr.animate)
                    ATTable("Plural — ${poss.english.substringBefore(" (")} ${expr.englishPlural}", pPl, expr.pl, expr.animate)
                    ATPossessiveNote(poss.lemma, pSg, pPl)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// ── Helpers ───────────────────────────────────────────────────────────────────

@Composable
private fun ATNote(text: String) {
    Text(text = text, fontSize = 14.sp, color = Color.DarkGray, modifier = Modifier.padding(vertical = 4.dp))
}

@Composable
private fun ATExpressionHeader(expr: ATExpression) {
    Spacer(modifier = Modifier.height(24.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(ButtonBlue)
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Column {
            Text(text = "${expr.sg[0]} — ${expr.english}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = "plural: ${expr.pl[0]} — ${expr.englishPlural}", fontSize = 13.sp, color = Color.White)
        }
    }
    expr.note?.let {
        Text(text = it, fontSize = 13.sp, fontStyle = FontStyle.Italic, color = Color.Gray, modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable
private fun ATPairingHeader(czech: String, english: String, possLabel: String) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = czech, fontSize = 17.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Text(text = "$english  ·  $possLabel", fontSize = 13.sp, fontStyle = FontStyle.Italic, color = Color.Gray)
    HorizontalDivider(modifier = Modifier.padding(top = 4.dp))
}

@Composable
private fun ATTable(label: String, poss: List<String>, phrase: List<String>, animate: Boolean) {
    val full = poss.zip(phrase) { p, n -> "$p $n" }
    Column(modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 4.dp)) {
        Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
        Spacer(modifier = Modifier.height(2.dp))
        full.forEachIndexed { i, form ->
            val remark = when {
                i == 4 && !animate -> "rarely used: things aren't normally addressed"
                i > 0 && form == full[0] -> "= Nominativ"
                else -> null
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(if (i % 2 == 0) Color(0xFFF2F5FA) else Color.White)
                    .padding(horizontal = 6.dp, vertical = 4.dp)
            ) {
                Text(atCaseLabels[i], modifier = Modifier.weight(0.75f), fontSize = 13.sp, color = Color.DarkGray)
                Column(modifier = Modifier.weight(1.25f)) {
                    Text(form, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    if (remark != null) {
                        Text(remark, fontSize = 11.sp, fontStyle = FontStyle.Italic, color = Color.Gray)
                    }
                }
            }
        }
    }
}

@Composable
private fun ATPossessiveNote(lemma: String, sg: List<String>, pl: List<String>) {
    val all = sg + pl
    val text = if (all.distinct().size == 1) {
        "\"${all[0]}\" never changes: it is the same in every case, in singular and plural, for every gender."
    } else {
        fun unchanged(forms: List<String>) =
            (1 until 7).filter { forms[it] == forms[0] }.map { atCaseLabels[it].substringAfter(". ") }
        val sgSame = unchanged(sg)
        val plSame = unchanged(pl)
        listOfNotNull(
            if (sgSame.isNotEmpty()) "singular: \"${sg[0]}\" also in ${sgSame.joinToString(", ")}" else null,
            if (plSame.isNotEmpty()) "plural: \"${pl[0]}\" also in ${plSame.joinToString(", ")}" else null
        ).takeIf { it.isNotEmpty() }?.let { "Possessive ($lemma) unchanged from Nominativ in " + it.joinToString("; ") + "." }
    }
    text?.let {
        Text(text = it, fontSize = 12.sp, fontStyle = FontStyle.Italic, color = Color(0xFF8A5A00), modifier = Modifier.padding(bottom = 4.dp))
    }
}
