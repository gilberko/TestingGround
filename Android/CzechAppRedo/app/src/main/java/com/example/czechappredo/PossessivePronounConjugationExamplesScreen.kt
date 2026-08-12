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
fun PossessivePronounConjugationExamplesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Possessive Pronouns", fontSize = 16.sp, fontWeight = FontWeight.Bold) },
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
            CCNote("Possessive Pronouns (Learning hub) shows the bare declension tables. This screen puts them to work in natural sentences, using the same four nouns throughout — bratr (brother, masc.), sestra (sister, fem.), auto (car, neut.), bratři (brothers, plural) — so the pronoun endings are easy to compare side by side.")
            CCNote("6 cases are shown per pronoun: Nominativ, Genitiv, Dativ, Akuzativ, Lokál, Instrumentál. Possessive pronouns have no Vokativ — see the note at the bottom of this screen.")

            // ══════════════════════════════════════════════════════════════
            PPExSection("můj / moje (my)")

            CCPhrase("můj bratr", "my brother", "masculine animate singular")
            CCCase("1. Nominativ", "Můj bratr bydlí v Praze.", "My brother lives in Prague.", "Můj bratr")
            CCCase("2. Genitiv", "Bez mého bratra bych to nezvládl.", "Without my brother I wouldn't manage it.", "mého bratra")
            CCCase("3. Dativ", "Zavolal jsem mému bratrovi.", "I called my brother.", "mému bratrovi")
            CCCase("4. Akuzativ", "Vidím mého bratra.", "I see my brother.", "mého bratra")
            CCCase("5. Lokál", "Mluvili jsme o mém bratrovi.", "We talked about my brother.", "mém bratrovi")
            CCCase("6. Instrumentál", "Byl jsem s mým bratrem v kině.", "I was at the cinema with my brother.", "mým bratrem")

            CCPhrase("moje sestra", "my sister", "feminine singular")
            CCCase("1. Nominativ", "Moje sestra studuje medicínu.", "My sister studies medicine.", "Moje sestra")
            CCCase("2. Genitiv", "Bez mé sestry by to nešlo.", "Without my sister it wouldn't work.", "mé sestry")
            CCCase("3. Dativ", "Napsal jsem mé sestře dopis.", "I wrote my sister a letter.", "mé sestře")
            CCCase("4. Akuzativ", "Znám moji sestru.", "I know my sister.", "moji sestru")
            CCCase("5. Lokál", "Mluvili jsme o mé sestře.", "We talked about my sister.", "mé sestře")
            CCCase("6. Instrumentál", "Byl jsem s mojí sestrou na výletě.", "I was on a trip with my sister.", "mojí sestrou")

            CCPhrase("moje auto", "my car", "neuter singular — accusative equals the nominative because the noun is inanimate")
            CCCase("1. Nominativ", "Moje auto je nové.", "My car is new.", "Moje auto")
            CCCase("2. Genitiv", "Cena mého auta je vysoká.", "The price of my car is high.", "mého auta")
            CCCase("3. Dativ", "Dali jsme mému autu nové pneumatiky.", "We gave my car new tires.", "mému autu")
            CCCase("4. Akuzativ", "Prodal jsem moje auto.", "I sold my car.", "moje auto")
            CCCase("5. Lokál", "Jel jsem v mém autě.", "I drove in my car.", "mém autě")
            CCCase("6. Instrumentál", "Jel jsem mým autem do práce.", "I drove to work in my car.", "mým autem")

            CCPhrase("moji bratři", "my brothers", "masculine animate plural — accusative drops the animacy split (moje bratry, not moji bratry), same pattern as adjectives")
            CCCase("1. Nominativ", "Moji bratři žijí v zahraničí.", "My brothers live abroad.", "Moji bratři")
            CCCase("2. Genitiv", "Bez mých bratrů bych to nezvládl.", "Without my brothers I wouldn't manage it.", "mých bratrů")
            CCCase("3. Dativ", "Zavolal jsem mým bratrům.", "I called my brothers.", "mým bratrům")
            CCCase("4. Akuzativ", "Vidím moje bratry.", "I see my brothers.", "moje bratry")
            CCCase("5. Lokál", "Mluvili jsme o mých bratrech.", "We talked about my brothers.", "mých bratrech")
            CCCase("6. Instrumentál", "Byl jsem s mými bratry v kině.", "I was at the cinema with my brothers.", "mými bratry")

            // ══════════════════════════════════════════════════════════════
            PPExSection("tvůj / tvoje (your — informal singular)")

            CCPhrase("tvůj bratr", "your brother", "masculine animate singular")
            CCCase("1. Nominativ", "Tvůj bratr bydlí v Praze.", "Your brother lives in Prague.", "Tvůj bratr")
            CCCase("2. Genitiv", "Bez tvého bratra bych to nezvládl.", "Without your brother I wouldn't manage it.", "tvého bratra")
            CCCase("3. Dativ", "Zavolal jsem tvému bratrovi.", "I called your brother.", "tvému bratrovi")
            CCCase("4. Akuzativ", "Vidím tvého bratra.", "I see your brother.", "tvého bratra")
            CCCase("5. Lokál", "Mluvili jsme o tvém bratrovi.", "We talked about your brother.", "tvém bratrovi")
            CCCase("6. Instrumentál", "Byl jsem s tvým bratrem v kině.", "I was at the cinema with your brother.", "tvým bratrem")

            CCPhrase("tvoje sestra", "your sister", "feminine singular")
            CCCase("1. Nominativ", "Tvoje sestra studuje medicínu.", "Your sister studies medicine.", "Tvoje sestra")
            CCCase("2. Genitiv", "Bez tvé sestry by to nešlo.", "Without your sister it wouldn't work.", "tvé sestry")
            CCCase("3. Dativ", "Napsal jsem tvé sestře dopis.", "I wrote your sister a letter.", "tvé sestře")
            CCCase("4. Akuzativ", "Znám tvoji sestru.", "I know your sister.", "tvoji sestru")
            CCCase("5. Lokál", "Mluvili jsme o tvé sestře.", "We talked about your sister.", "tvé sestře")
            CCCase("6. Instrumentál", "Byl jsem s tvojí sestrou na výletě.", "I was on a trip with your sister.", "tvojí sestrou")

            CCPhrase("tvoje auto", "your car", "neuter singular — accusative equals the nominative because the noun is inanimate")
            CCCase("1. Nominativ", "Tvoje auto je nové.", "Your car is new.", "Tvoje auto")
            CCCase("2. Genitiv", "Cena tvého auta je vysoká.", "The price of your car is high.", "tvého auta")
            CCCase("3. Dativ", "Dali jsme tvému autu nové pneumatiky.", "We gave your car new tires.", "tvému autu")
            CCCase("4. Akuzativ", "Prodal jsem tvoje auto.", "I sold your car.", "tvoje auto")
            CCCase("5. Lokál", "Jel jsem v tvém autě.", "I drove in your car.", "tvém autě")
            CCCase("6. Instrumentál", "Jel jsem tvým autem do práce.", "I drove to work in your car.", "tvým autem")

            CCPhrase("tvoji bratři", "your brothers", "masculine animate plural — accusative is tvoje bratry, not tvoji bratry")
            CCCase("1. Nominativ", "Tvoji bratři žijí v zahraničí.", "Your brothers live abroad.", "Tvoji bratři")
            CCCase("2. Genitiv", "Bez tvých bratrů bych to nezvládl.", "Without your brothers I wouldn't manage it.", "tvých bratrů")
            CCCase("3. Dativ", "Zavolal jsem tvým bratrům.", "I called your brothers.", "tvým bratrům")
            CCCase("4. Akuzativ", "Vidím tvoje bratry.", "I see your brothers.", "tvoje bratry")
            CCCase("5. Lokál", "Mluvili jsme o tvých bratrech.", "We talked about your brothers.", "tvých bratrech")
            CCCase("6. Instrumentál", "Byl jsem s tvými bratry v kině.", "I was at the cinema with your brothers.", "tvými bratry")

            // ══════════════════════════════════════════════════════════════
            PPExSection("její (her)")
            CCNote("Unlike jeho, její fully declines — it agrees with the noun it describes just like můj or tvůj.")

            CCPhrase("její bratr", "her brother", "masculine animate singular")
            CCCase("1. Nominativ", "Její bratr bydlí v Praze.", "Her brother lives in Prague.", "Její bratr")
            CCCase("2. Genitiv", "Bez jejího bratra bych to nezvládl.", "Without her brother I wouldn't manage it.", "jejího bratra")
            CCCase("3. Dativ", "Zavolal jsem jejímu bratrovi.", "I called her brother.", "jejímu bratrovi")
            CCCase("4. Akuzativ", "Vidím jejího bratra.", "I see her brother.", "jejího bratra")
            CCCase("5. Lokál", "Mluvili jsme o jejím bratrovi.", "We talked about her brother.", "jejím bratrovi")
            CCCase("6. Instrumentál", "Byl jsem s jejím bratrem v kině.", "I was at the cinema with her brother.", "jejím bratrem")

            CCPhrase("její sestra", "her sister", "feminine singular")
            CCCase("1. Nominativ", "Její sestra studuje medicínu.", "Her sister studies medicine.", "Její sestra")
            CCCase("2. Genitiv", "Bez její sestry by to nešlo.", "Without her sister it wouldn't work.", "její sestry")
            CCCase("3. Dativ", "Napsal jsem její sestře dopis.", "I wrote her sister a letter.", "její sestře")
            CCCase("4. Akuzativ", "Znám její sestru.", "I know her sister.", "její sestru")
            CCCase("5. Lokál", "Mluvili jsme o její sestře.", "We talked about her sister.", "její sestře")
            CCCase("6. Instrumentál", "Byl jsem s její sestrou na výletě.", "I was on a trip with her sister.", "její sestrou")

            CCPhrase("její auto", "her car", "neuter singular — accusative equals the nominative because the noun is inanimate")
            CCCase("1. Nominativ", "Její auto je nové.", "Her car is new.", "Její auto")
            CCCase("2. Genitiv", "Cena jejího auta je vysoká.", "The price of her car is high.", "jejího auta")
            CCCase("3. Dativ", "Dali jsme jejímu autu nové pneumatiky.", "We gave her car new tires.", "jejímu autu")
            CCCase("4. Akuzativ", "Prodala jsem její auto.", "I sold her car.", "její auto")
            CCCase("5. Lokál", "Jel jsem v jejím autě.", "I drove in her car.", "jejím autě")
            CCCase("6. Instrumentál", "Jel jsem jejím autem do práce.", "I drove to work in her car.", "jejím autem")

            CCPhrase("její bratři", "her brothers", "masculine animate plural")
            CCCase("1. Nominativ", "Její bratři žijí v zahraničí.", "Her brothers live abroad.", "Její bratři")
            CCCase("2. Genitiv", "Bez jejích bratrů bych to nezvládl.", "Without her brothers I wouldn't manage it.", "jejích bratrů")
            CCCase("3. Dativ", "Zavolal jsem jejím bratrům.", "I called her brothers.", "jejím bratrům")
            CCCase("4. Akuzativ", "Vidím její bratry.", "I see her brothers.", "její bratry")
            CCCase("5. Lokál", "Mluvili jsme o jejích bratrech.", "We talked about her brothers.", "jejích bratrech")
            CCCase("6. Instrumentál", "Byl jsem s jejími bratry v kině.", "I was at the cinema with her brothers.", "jejími bratry")

            // ══════════════════════════════════════════════════════════════
            PPExSection("náš / naše (our)")

            CCPhrase("náš bratr", "our brother", "masculine animate singular")
            CCCase("1. Nominativ", "Náš bratr bydlí v Praze.", "Our brother lives in Prague.", "Náš bratr")
            CCCase("2. Genitiv", "Bez našeho bratra bychom to nezvládli.", "Without our brother we wouldn't manage it.", "našeho bratra")
            CCCase("3. Dativ", "Zavolali jsme našemu bratrovi.", "We called our brother.", "našemu bratrovi")
            CCCase("4. Akuzativ", "Vidíme našeho bratra.", "We see our brother.", "našeho bratra")
            CCCase("5. Lokál", "Mluvili jsme o našem bratrovi.", "We talked about our brother.", "našem bratrovi")
            CCCase("6. Instrumentál", "Byli jsme s naším bratrem v kině.", "We were at the cinema with our brother.", "naším bratrem")

            CCPhrase("naše sestra", "our sister", "feminine singular")
            CCCase("1. Nominativ", "Naše sestra studuje medicínu.", "Our sister studies medicine.", "Naše sestra")
            CCCase("2. Genitiv", "Bez naší sestry by to nešlo.", "Without our sister it wouldn't work.", "naší sestry")
            CCCase("3. Dativ", "Napsali jsme naší sestře dopis.", "We wrote our sister a letter.", "naší sestře")
            CCCase("4. Akuzativ", "Známe naši sestru.", "We know our sister.", "naši sestru")
            CCCase("5. Lokál", "Mluvili jsme o naší sestře.", "We talked about our sister.", "naší sestře")
            CCCase("6. Instrumentál", "Byli jsme s naší sestrou na výletě.", "We were on a trip with our sister.", "naší sestrou")

            CCPhrase("naše auto", "our car", "neuter singular — accusative equals the nominative because the noun is inanimate")
            CCCase("1. Nominativ", "Naše auto je nové.", "Our car is new.", "Naše auto")
            CCCase("2. Genitiv", "Cena našeho auta je vysoká.", "The price of our car is high.", "našeho auta")
            CCCase("3. Dativ", "Dali jsme našemu autu nové pneumatiky.", "We gave our car new tires.", "našemu autu")
            CCCase("4. Akuzativ", "Prodali jsme naše auto.", "We sold our car.", "naše auto")
            CCCase("5. Lokál", "Jeli jsme v našem autě.", "We drove in our car.", "našem autě")
            CCCase("6. Instrumentál", "Jeli jsme naším autem do práce.", "We drove to work in our car.", "naším autem")

            CCPhrase("naši bratři", "our brothers", "masculine animate plural — accusative is naše bratry, not naši bratry")
            CCCase("1. Nominativ", "Naši bratři žijí v zahraničí.", "Our brothers live abroad.", "Naši bratři")
            CCCase("2. Genitiv", "Bez našich bratrů bychom to nezvládli.", "Without our brothers we wouldn't manage it.", "našich bratrů")
            CCCase("3. Dativ", "Zavolali jsme našim bratrům.", "We called our brothers.", "našim bratrům")
            CCCase("4. Akuzativ", "Vidíme naše bratry.", "We see our brothers.", "naše bratry")
            CCCase("5. Lokál", "Mluvili jsme o našich bratrech.", "We talked about our brothers.", "našich bratrech")
            CCCase("6. Instrumentál", "Byli jsme s našimi bratry v kině.", "We were at the cinema with our brothers.", "našimi bratry")

            // ══════════════════════════════════════════════════════════════
            PPExSection("váš / vaše (your — formal or plural)")

            CCPhrase("váš bratr", "your brother", "masculine animate singular")
            CCCase("1. Nominativ", "Váš bratr bydlí v Praze.", "Your brother lives in Prague.", "Váš bratr")
            CCCase("2. Genitiv", "Bez vašeho bratra byste to nezvládli.", "Without your brother you wouldn't manage it.", "vašeho bratra")
            CCCase("3. Dativ", "Zavolal jsem vašemu bratrovi.", "I called your brother.", "vašemu bratrovi")
            CCCase("4. Akuzativ", "Vidím vašeho bratra.", "I see your brother.", "vašeho bratra")
            CCCase("5. Lokál", "Mluvili jsme o vašem bratrovi.", "We talked about your brother.", "vašem bratrovi")
            CCCase("6. Instrumentál", "Byl jsem s vaším bratrem v kině.", "I was at the cinema with your brother.", "vaším bratrem")

            CCPhrase("vaše sestra", "your sister", "feminine singular")
            CCCase("1. Nominativ", "Vaše sestra studuje medicínu.", "Your sister studies medicine.", "Vaše sestra")
            CCCase("2. Genitiv", "Bez vaší sestry by to nešlo.", "Without your sister it wouldn't work.", "vaší sestry")
            CCCase("3. Dativ", "Napsal jsem vaší sestře dopis.", "I wrote your sister a letter.", "vaší sestře")
            CCCase("4. Akuzativ", "Znám vaši sestru.", "I know your sister.", "vaši sestru")
            CCCase("5. Lokál", "Mluvili jsme o vaší sestře.", "We talked about your sister.", "vaší sestře")
            CCCase("6. Instrumentál", "Byl jsem s vaší sestrou na výletě.", "I was on a trip with your sister.", "vaší sestrou")

            CCPhrase("vaše auto", "your car", "neuter singular — accusative equals the nominative because the noun is inanimate")
            CCCase("1. Nominativ", "Vaše auto je nové.", "Your car is new.", "Vaše auto")
            CCCase("2. Genitiv", "Cena vašeho auta je vysoká.", "The price of your car is high.", "vašeho auta")
            CCCase("3. Dativ", "Dali jsme vašemu autu nové pneumatiky.", "We gave your car new tires.", "vašemu autu")
            CCCase("4. Akuzativ", "Prodal jsem vaše auto.", "I sold your car.", "vaše auto")
            CCCase("5. Lokál", "Jel jsem ve vašem autě.", "I drove in your car.", "vašem autě")
            CCCase("6. Instrumentál", "Jel jsem vaším autem do práce.", "I drove to work in your car.", "vaším autem")

            CCPhrase("vaši bratři", "your brothers", "masculine animate plural — accusative is vaše bratry, not vaši bratry")
            CCCase("1. Nominativ", "Vaši bratři žijí v zahraničí.", "Your brothers live abroad.", "Vaši bratři")
            CCCase("2. Genitiv", "Bez vašich bratrů byste to nezvládli.", "Without your brothers you wouldn't manage it.", "vašich bratrů")
            CCCase("3. Dativ", "Zavolal jsem vašim bratrům.", "I called your brothers.", "vašim bratrům")
            CCCase("4. Akuzativ", "Vidím vaše bratry.", "I see your brothers.", "vaše bratry")
            CCCase("5. Lokál", "Mluvili jsme o vašich bratrech.", "We talked about your brothers.", "vašich bratrech")
            CCCase("6. Instrumentál", "Byl jsem s vašimi bratry v kině.", "I was at the cinema with your brothers.", "vašimi bratry")

            // ══════════════════════════════════════════════════════════════
            PPExSection("jeho (his / its) — invariant")
            CCNote("jeho never changes — same word for every gender, number, and case. Compare to mého/mé/mých above, which all change.")
            CCCase("Masculine", "Jeho bratr bydlí v Praze.", "His brother lives in Prague.", "Jeho bratr")
            CCCase("Feminine", "Jeho sestra studuje medicínu.", "His sister studies medicine.", "Jeho sestra")
            CCCase("Neuter", "Jeho auto je nové.", "His car is new.", "Jeho auto")
            CCCase("Plural", "Jeho bratři žijí v zahraničí.", "His brothers live abroad.", "Jeho bratři")
            CCCase("Oblique case (Genitiv)", "Bez jeho bratra bych to nezvládl.", "Without his brother I wouldn't manage it.", "jeho bratra")
            CCCase("Oblique case (Instrumentál)", "Byl jsem s jeho sestrou na výletě.", "I was on a trip with his sister.", "jeho sestrou")

            // ══════════════════════════════════════════════════════════════
            PPExSection("jejich (their) — invariant")
            CCNote("jejich never changes either — same word for every gender, number, and case.")
            CCCase("Masculine", "Jejich bratr bydlí v Praze.", "Their brother lives in Prague.", "Jejich bratr")
            CCCase("Feminine", "Jejich sestra studuje medicínu.", "Their sister studies medicine.", "Jejich sestra")
            CCCase("Neuter", "Jejich auto je nové.", "Their car is new.", "Jejich auto")
            CCCase("Plural", "Jejich bratři žijí v zahraničí.", "Their brothers live abroad.", "Jejich bratři")
            CCCase("Oblique case (Genitiv)", "Bez jejich bratra bych to nezvládl.", "Without their brother I wouldn't manage it.", "jejich bratra")
            CCCase("Oblique case (Instrumentál)", "Byl jsem s jejich sestrou na výletě.", "I was on a trip with their sister.", "jejich sestrou")

            // ══════════════════════════════════════════════════════════════
            PPExSection("svůj / svoje / svoji — the reflexive possessive")
            CCNote("svůj means \"one's own\" — it always refers back to the subject of its own clause, no matter who that subject is. It declines exactly like můj (svůj, svého, svému... / svoje, své... / svoje, svého...).")
            CCNote("The key contrast: jeho/její/jejich point to somebody else's possession, while svůj points back to the subject itself. Petr vzal jeho auto. = Peter took his (someone else's) car. Petr vzal svoje auto. = Peter took his own car.")
            CCCase("já (I)", "Mám svůj byt.", "I have my own apartment.", "svůj byt")
            CCCase("ty (you)", "Máš svého bratra rád.", "You're fond of your own brother.", "svého bratra")
            CCCase("on (he)", "Má svůj byt.", "He has his own apartment.", "svůj byt")
            CCCase("ona (she)", "Vidí svého bratra.", "She sees her own brother.", "svého bratra")
            CCCase("my (we)", "Bereme si svoje auto.", "We're taking our own car.", "svoje auto")
            CCCase("vy (you pl.)", "Jedete se svou sestrou.", "You're going with your own sister.", "se svou sestrou")
            CCCase("oni (they)", "Prodali svoje auto.", "They sold their own car.", "svoje auto")

            // ══════════════════════════════════════════════════════════════
            PPExSection("What about Vocative?")
            CCNote("Possessive pronouns (můj, tvůj, jeho, svůj, etc.) do not have vocative forms. Only the noun or name being addressed goes into the vocative — the possessive in front of it stays in whatever case the sentence requires.")
            CCNote("Example: \"Bratře, kde je tvoje auto?\" — Bratře is vocative (addressing the brother); tvoje stays in the nominative because it describes the subject (auto), not the person being addressed.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PPExSection(text: String) {
    Spacer(modifier = Modifier.height(24.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
    HorizontalDivider()
}

@Composable
private fun CCNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun CCPhrase(phrase: String, english: String, info: String) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 17.sp, color = ButtonBlue)) {
                append(phrase)
            }
            withStyle(SpanStyle(fontSize = 15.sp, color = Color.DarkGray)) {
                append("  —  $english")
            }
        }
    )
    Text(
        text = info,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(top = 2.dp, bottom = 6.dp)
    )
}

@Composable
private fun CCCase(caseLabel: String, czech: String, english: String, form: String) {
    Column(modifier = Modifier.padding(vertical = 5.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = caseLabel, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
            Text(text = form, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
        Text(text = czech, fontSize = 15.sp, color = Color.DarkGray, modifier = Modifier.padding(top = 2.dp))
        Text(text = english, fontSize = 13.sp, fontStyle = FontStyle.Italic, color = Color.Gray)
    }
}
