package com.example.frenchproject.screens.dictionary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

private data class CountryEntry(
    val article: String,
    val french: String,
    val english: String,
    val toForm: String
)

private data class CountryRegion(val title: String, val entries: List<CountryEntry>)

private data class NationalityEntry(val country: String, val masculine: String, val feminine: String, val note: String? = null)

private data class LanguageEntry(val french: String, val english: String, val countries: String)

private data class VocabEntry(val french: String, val english: String)

private val europeEntries = listOf(
    CountryEntry("le", "Portugal", "Portugal", "au Portugal"),
    CountryEntry("l'", "Espagne", "Spain", "en Espagne"),
    CountryEntry("la", "France", "France", "en France"),
    CountryEntry("l'", "Italie", "Italy", "en Italie"),
    CountryEntry("l'", "Allemagne", "Germany", "en Allemagne"),
    CountryEntry("l'", "Autriche", "Austria", "en Autriche"),
    CountryEntry("la", "Suisse", "Switzerland", "en Suisse"),
    CountryEntry("les", "Pays-Bas", "Netherlands", "aux Pays-Bas"),
    CountryEntry("l'", "Andorre", "Andorra", "en Andorre"),
    CountryEntry("la", "Finlande", "Finland", "en Finlande"),
    CountryEntry("la", "Suède", "Sweden", "en Suède"),
    CountryEntry("le", "Danemark", "Denmark", "au Danemark"),
    CountryEntry("la", "Norvège", "Norway", "en Norvège"),
    CountryEntry("l'", "Islande", "Iceland", "en Islande"),
    CountryEntry("la", "Grande-Bretagne", "Britain", "en Grande-Bretagne"),
    CountryEntry("l'", "Angleterre", "England", "en Angleterre"),
    CountryEntry("l'", "Écosse", "Scotland", "en Écosse"),
    CountryEntry("le", "Pays de Galles", "Wales", "au Pays de Galles"),
    CountryEntry("la", "République tchèque", "Czech Republic", "en République tchèque"),
    CountryEntry("la", "Hongrie", "Hungary", "en Hongrie"),
    CountryEntry("la", "Pologne", "Poland", "en Pologne"),
    CountryEntry("la", "Roumanie", "Romania", "en Roumanie"),
    CountryEntry("la", "Lituanie", "Lithuania", "en Lituanie"),
    CountryEntry("l'", "Ukraine", "Ukraine", "en Ukraine"),
    CountryEntry("la", "Russie", "Russia", "en Russie"),
    CountryEntry("l'", "Arménie", "Armenia", "en Arménie"),
    CountryEntry("la", "Grèce", "Greece", "en Grèce"),
    CountryEntry("—", "Chypre", "Cyprus", "à Chypre")
)

private val middleEastEntries = listOf(
    CountryEntry("la", "Turquie", "Turkey", "en Turquie"),
    CountryEntry("—", "Israël", "Israel", "en Israël"),
    CountryEntry("l'", "Égypte", "Egypt", "en Égypte"),
    CountryEntry("la", "Jordanie", "Jordan", "en Jordanie"),
    CountryEntry("le", "Maroc", "Morocco", "au Maroc"),
    CountryEntry("le", "Liban", "Lebanon", "au Liban"),
    CountryEntry("la", "Syrie", "Syria", "en Syrie"),
    CountryEntry("l'", "Iran", "Iran", "en Iran"),
    CountryEntry("l'", "Irak", "Iraq", "en Irak")
)

private val asiaEntries = listOf(
    CountryEntry("la", "Chine", "China", "en Chine"),
    CountryEntry("le", "Japon", "Japan", "au Japon"),
    CountryEntry("la", "Corée du Sud", "South Korea", "en Corée du Sud"),
    CountryEntry("le", "Viêt Nam", "Vietnam", "au Viêt Nam"),
    CountryEntry("la", "Thaïlande", "Thailand", "en Thaïlande"),
    CountryEntry("l'", "Inde", "India", "en Inde"),
    CountryEntry("le", "Pakistan", "Pakistan", "au Pakistan")
)

private val americasEntries = listOf(
    CountryEntry("les", "États-Unis", "United States", "aux États-Unis"),
    CountryEntry("le", "Canada", "Canada", "au Canada"),
    CountryEntry("le", "Mexique", "Mexico", "au Mexique"),
    CountryEntry("—", "Cuba", "Cuba", "à Cuba"),
    CountryEntry("le", "Brésil", "Brazil", "au Brésil"),
    CountryEntry("l'", "Argentine", "Argentina", "en Argentine"),
    CountryEntry("le", "Pérou", "Peru", "au Pérou"),
    CountryEntry("la", "Bolivie", "Bolivia", "en Bolivie"),
    CountryEntry("le", "Chili", "Chile", "au Chili"),
    CountryEntry("l'", "Uruguay", "Uruguay", "en Uruguay"),
    CountryEntry("le", "Paraguay", "Paraguay", "au Paraguay")
)

private val oceaniaEntries = listOf(
    CountryEntry("l'", "Australie", "Australia", "en Australie")
)

private val regions = listOf(
    CountryRegion("Europe", europeEntries),
    CountryRegion("Middle East & Africa", middleEastEntries),
    CountryRegion("Asia", asiaEntries),
    CountryRegion("Americas", americasEntries),
    CountryRegion("Oceania", oceaniaEntries)
)

private val nationalities = listOf(
    NationalityEntry("Portugal", "portugais", "portugaise"),
    NationalityEntry("Spain", "espagnol", "espagnole"),
    NationalityEntry("France", "français", "française"),
    NationalityEntry("Italy", "italien", "italienne"),
    NationalityEntry("Germany", "allemand", "allemande"),
    NationalityEntry("Austria", "autrichien", "autrichienne"),
    NationalityEntry("Switzerland", "suisse", "suisse", "invariable"),
    NationalityEntry("Netherlands", "néerlandais", "néerlandaise"),
    NationalityEntry("Andorra", "andorran", "andorrane"),
    NationalityEntry("Finland", "finlandais", "finlandaise"),
    NationalityEntry("Sweden", "suédois", "suédoise"),
    NationalityEntry("Denmark", "danois", "danoise"),
    NationalityEntry("Norway", "norvégien", "norvégienne"),
    NationalityEntry("Iceland", "islandais", "islandaise"),
    NationalityEntry("Britain", "britannique", "britannique", "invariable"),
    NationalityEntry("England", "anglais", "anglaise"),
    NationalityEntry("Scotland", "écossais", "écossaise"),
    NationalityEntry("Wales", "gallois", "galloise"),
    NationalityEntry("Czech Republic", "tchèque", "tchèque", "invariable"),
    NationalityEntry("Hungary", "hongrois", "hongroise"),
    NationalityEntry("Poland", "polonais", "polonaise"),
    NationalityEntry("Romania", "roumain", "roumaine"),
    NationalityEntry("Lithuania", "lituanien", "lituanienne"),
    NationalityEntry("Ukraine", "ukrainien", "ukrainienne"),
    NationalityEntry("Russia", "russe", "russe", "invariable"),
    NationalityEntry("Armenia", "arménien", "arménienne"),
    NationalityEntry("Greece", "grec", "grecque", "irregular"),
    NationalityEntry("Cyprus", "chypriote", "chypriote", "invariable"),
    NationalityEntry("Turkey", "turc", "turque", "irregular"),
    NationalityEntry("Israel", "israélien", "israélienne"),
    NationalityEntry("Egypt", "égyptien", "égyptienne"),
    NationalityEntry("Jordan", "jordanien", "jordanienne"),
    NationalityEntry("Morocco", "marocain", "marocaine"),
    NationalityEntry("Lebanon", "libanais", "libanaise"),
    NationalityEntry("Syria", "syrien", "syrienne"),
    NationalityEntry("Iran", "iranien", "iranienne"),
    NationalityEntry("Iraq", "irakien", "irakienne"),
    NationalityEntry("China", "chinois", "chinoise"),
    NationalityEntry("Japan", "japonais", "japonaise"),
    NationalityEntry("South Korea", "sud-coréen", "sud-coréenne"),
    NationalityEntry("Vietnam", "vietnamien", "vietnamienne"),
    NationalityEntry("Thailand", "thaïlandais", "thaïlandaise"),
    NationalityEntry("India", "indien", "indienne"),
    NationalityEntry("Pakistan", "pakistanais", "pakistanaise"),
    NationalityEntry("United States", "américain", "américaine"),
    NationalityEntry("Canada", "canadien", "canadienne"),
    NationalityEntry("Mexico", "mexicain", "mexicaine"),
    NationalityEntry("Cuba", "cubain", "cubaine"),
    NationalityEntry("Brazil", "brésilien", "brésilienne"),
    NationalityEntry("Argentina", "argentin", "argentine"),
    NationalityEntry("Peru", "péruvien", "péruvienne"),
    NationalityEntry("Bolivia", "bolivien", "bolivienne"),
    NationalityEntry("Chile", "chilien", "chilienne"),
    NationalityEntry("Uruguay", "uruguayen", "uruguayenne"),
    NationalityEntry("Paraguay", "paraguayen", "paraguayenne"),
    NationalityEntry("Australia", "australien", "australienne")
)

private val languages = listOf(
    LanguageEntry("l'anglais", "English", "England, US, Canada, Australia…"),
    LanguageEntry("l'espagnol", "Spanish", "Spain, Mexico, Cuba, Argentina, Peru, Bolivia, Chile, Uruguay, Paraguay…"),
    LanguageEntry("le portugais", "Portuguese", "Portugal, Brazil"),
    LanguageEntry("le français", "French", "France, Canada (partly), Switzerland (partly), Andorra…"),
    LanguageEntry("l'allemand", "German", "Germany, Austria, Switzerland (partly)…"),
    LanguageEntry("le russe", "Russian", "Russia, Ukraine (partly)…"),
    LanguageEntry("l'arabe", "Arabic", "Egypt, Jordan, Morocco, Lebanon, Syria, Iraq…"),
    LanguageEntry("le chinois", "Chinese", "China — mandarin: le mandarin"),
    LanguageEntry("le japonais", "Japanese", "Japan"),
    LanguageEntry("le coréen", "Korean", "South Korea"),
    LanguageEntry("le vietnamien", "Vietnamese", "Vietnam"),
    LanguageEntry("le thaï", "Thai", "Thailand"),
    LanguageEntry("l'hébreu ★", "Hebrew", "Israel — see note below"),
    LanguageEntry("le grec", "Greek", "Greece, Cyprus (partly)"),
    LanguageEntry("le finnois ★", "Finnish", "Finland — not 'le finlandais'"),
    LanguageEntry("le suédois", "Swedish", "Sweden"),
    LanguageEntry("le danois", "Danish", "Denmark"),
    LanguageEntry("le norvégien", "Norwegian", "Norway"),
    LanguageEntry("l'islandais", "Icelandic", "Iceland"),
    LanguageEntry("le polonais", "Polish", "Poland"),
    LanguageEntry("le hongrois", "Hungarian", "Hungary"),
    LanguageEntry("le tchèque", "Czech", "Czech Republic"),
    LanguageEntry("le roumain", "Romanian", "Romania"),
    LanguageEntry("le lituanien", "Lithuanian", "Lithuania"),
    LanguageEntry("l'ukrainien", "Ukrainian", "Ukraine"),
    LanguageEntry("l'arménien", "Armenian", "Armenia"),
    LanguageEntry("le turc", "Turkish", "Turkey"),
    LanguageEntry("le persan", "Persian / Farsi", "Iran"),
    LanguageEntry("le hindi", "Hindi", "India (one of many)"),
    LanguageEntry("l'ourdou", "Urdu", "Pakistan"),
    LanguageEntry("le néerlandais", "Dutch", "Netherlands"),
    LanguageEntry("le catalan", "Catalan", "Andorra — main official language")
)

private val vocabulary = listOf(
    VocabEntry("la langue", "language (a specific language, e.g. la langue française)"),
    VocabEntry("le langage", "language (as a concept or style of speech)"),
    VocabEntry("parler une langue", "to speak a language"),
    VocabEntry("un étranger / une étrangère", "a foreigner"),
    VocabEntry("étranger / étrangère", "foreign (adjective)"),
    VocabEntry("un natif / une native", "a native (person from a place)"),
    VocabEntry("natif / native", "native (adjective)"),
    VocabEntry("autochtone", "indigenous / native (invariable, used for both genders)"),
    VocabEntry("un locuteur natif / une locutrice native", "a native speaker"),
    VocabEntry("la nation", "nation"),
    VocabEntry("le pays", "country"),
    VocabEntry("l'immigration (f.)", "immigration"),
    VocabEntry("un immigré / une immigrée", "an immigrant (settled)"),
    VocabEntry("un immigrant / une immigrante", "an immigrant (in process of immigrating)"),
    VocabEntry("un expatrié / une expatriée", "an expat (informal: un expat / une expat)")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Countries, Nationalities & Languages", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FrenchBlue)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 8.dp)
        ) {
            // ── Article Rule ──────────────────────────────────────────────
            item {
                SectionHeader("Articles & Prepositions")
                NoteCard(
                    "In French, country names have grammatical gender — masculine or feminine — and most take a definite article: " +
                    "la (f.), le (m.), l' (before a vowel), les (plural).\n\n" +
                    "The article determines which preposition to use:\n\n" +
                    "• Feminine (la / l'): en + country,  de / d' + country\n" +
                    "  → en France / de France,  en Espagne / d'Espagne\n\n" +
                    "• Masculine, consonant (le): au + country,  du + country\n" +
                    "  → au Japon / du Japon,  au Maroc / du Maroc\n\n" +
                    "• Masculine, vowel (l'): en + country,  d' + country\n" +
                    "  → en Iran / d'Iran,  en Irak / d'Irak\n\n" +
                    "• Plural (les): aux + country,  des + country\n" +
                    "  → aux États-Unis / des États-Unis\n\n" +
                    "• No article (islands & a few others): à or en + country,  de / d' + country\n" +
                    "  → à Cuba / de Cuba,  à Chypre / de Chypre,  en Israël / d'Israël"
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // ── Countries by region ───────────────────────────────────────
            item {
                SectionHeader("Countries")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                ) {
                    Text("French (article)", fontWeight = FontWeight.SemiBold, color = FrenchBlue, fontSize = 12.sp, modifier = Modifier.weight(1.1f))
                    Text("English", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 12.sp, modifier = Modifier.weight(0.8f))
                    Text("To →", fontWeight = FontWeight.SemiBold, color = FrenchBlue, fontSize = 12.sp, fontStyle = FontStyle.Italic, modifier = Modifier.weight(1.1f))
                }
            }

            regions.forEach { region ->
                item {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = region.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = FrenchBlue,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.35f))
                    Spacer(modifier = Modifier.height(4.dp))
                }
                items(region.entries) { entry ->
                    CountryCard(entry)
                }
            }

            item {
                Spacer(modifier = Modifier.height(4.dp))
                NoteCard(
                    "From → use de / d' for feminine and vowel-start countries (de France, d'Espagne, d'Iran), " +
                    "du for masculine consonant-start (du Japon, du Maroc), des for plural (des États-Unis), " +
                    "de / d' for no-article (de Cuba, de Chypre, d'Israël)."
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            // ── Nationalities ─────────────────────────────────────────────
            item {
                SectionHeader("Nationalities")
                NoteCard(
                    "Nationalities in French are adjectives — they agree in gender and number with the noun they describe " +
                    "and are never capitalized (unlike English).\n\n" +
                    "Most form the feminine by adding -e: français → française, allemand → allemande.\n" +
                    "Some are invariable (same form for M and F): russe, suisse, tchèque, britannique, chypriote.\n" +
                    "A few are irregular: grec → grecque,  turc → turque.\n" +
                    "Plurals: add -s, unless the word already ends in -s (e.g. français stays français).\n\n" +
                    "As a noun for a person, capitalize: un Français, une Française."
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                ) {
                    Text("Country", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 12.sp, modifier = Modifier.weight(1f))
                    Text("Masculine", fontWeight = FontWeight.SemiBold, color = FrenchBlue, fontSize = 12.sp, modifier = Modifier.weight(1f))
                    Text("Feminine", fontWeight = FontWeight.SemiBold, color = FrenchBlue, fontSize = 12.sp, fontStyle = FontStyle.Italic, modifier = Modifier.weight(1f))
                }
                HorizontalDivider(color = FrenchBlue.copy(alpha = 0.35f))
                Spacer(modifier = Modifier.height(4.dp))
            }

            items(nationalities) { entry ->
                NationalityCard(entry)
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            // ── Languages ─────────────────────────────────────────────────
            item {
                SectionHeader("Languages")
                NoteCard(
                    "Language names in French are the same word as the masculine singular nationality adjective " +
                    "(e.g. français, anglais, arabe) and are never capitalized.\n\n" +
                    "A language name can be used in three ways:\n" +
                    "1. As a noun (with article): le français est une belle langue.\n" +
                    "2. After parler (no article): je parle français.\n" +
                    "3. After en (no article, means 'in [language]'): un livre en français.\n\n" +
                    "As an adjective it agrees with the noun: un film français / une chanson française. " +
                    "This can mean 'from that country' or 'in that language' — context usually clarifies. " +
                    "To be explicit about the language, prefer en: un roman en espagnol.\n\n" +
                    "★ Hebrew exception: the noun is l'hébreu (masculine). The adjective is irregular — " +
                    "masculine: hébreu, feminine: hébraïque (not hébreue). " +
                    "Examples: un texte hébreu / la littérature hébraïque / un livre en hébreu.\n\n" +
                    "★ Finnish: the language is le finnois — not le finlandais. " +
                    "Finlandais is the nationality adjective; le finnois is the language name."
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                ) {
                    Text("French", fontWeight = FontWeight.SemiBold, color = FrenchBlue, fontSize = 12.sp, modifier = Modifier.weight(1f))
                    Text("English", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 12.sp, modifier = Modifier.weight(0.7f))
                    Text("Countries", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 12.sp, modifier = Modifier.weight(1.3f))
                }
                HorizontalDivider(color = FrenchBlue.copy(alpha = 0.35f))
                Spacer(modifier = Modifier.height(4.dp))
            }

            items(languages) { entry ->
                LanguageCard(entry)
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            // ── Vocabulary ────────────────────────────────────────────────
            item {
                SectionHeader("Key Vocabulary")
                Spacer(modifier = Modifier.height(4.dp))
            }

            items(vocabulary) { entry ->
                VocabCard(entry)
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = FrenchNavy,
        modifier = Modifier.padding(bottom = 6.dp)
    )
}

@Composable
private fun NoteCard(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = FrenchNavy,
            modifier = Modifier.padding(12.dp),
            lineHeight = 18.sp
        )
    }
}

@Composable
private fun CountryCard(entry: CountryEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 9.dp)
        ) {
            Column(modifier = Modifier.weight(1.1f)) {
                Text(
                    text = "${entry.article} ${entry.french}".trim(),
                    fontWeight = FontWeight.Bold,
                    color = FrenchBlue,
                    fontSize = 13.sp
                )
            }
            Text(
                text = entry.english,
                color = FrenchNavy,
                fontSize = 13.sp,
                modifier = Modifier.weight(0.8f)
            )
            Text(
                text = entry.toForm,
                fontStyle = FontStyle.Italic,
                color = FrenchBlue,
                fontSize = 13.sp,
                modifier = Modifier.weight(1.1f)
            )
        }
    }
}

@Composable
private fun NationalityCard(entry: NationalityEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 9.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = entry.country,
                    color = FrenchNavy,
                    fontSize = 13.sp
                )
                if (entry.note != null) {
                    Text(
                        text = entry.note,
                        color = FrenchNavy.copy(alpha = 0.6f),
                        fontSize = 10.sp,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
            Text(
                text = entry.masculine,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 13.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = entry.feminine,
                fontStyle = FontStyle.Italic,
                color = FrenchBlue,
                fontSize = 13.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun LanguageCard(entry: LanguageEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 9.dp)
        ) {
            Text(
                text = entry.french,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 13.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = entry.english,
                color = FrenchNavy,
                fontSize = 13.sp,
                modifier = Modifier.weight(0.7f)
            )
            Text(
                text = entry.countries,
                color = FrenchNavy.copy(alpha = 0.75f),
                fontSize = 11.sp,
                modifier = Modifier.weight(1.3f),
                lineHeight = 15.sp
            )
        }
    }
}

@Composable
private fun VocabCard(entry: VocabEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 9.dp)
        ) {
            Text(
                text = entry.french,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 13.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = entry.english,
                color = FrenchNavy,
                fontSize = 13.sp,
                modifier = Modifier.weight(1f),
                lineHeight = 18.sp
            )
        }
    }
}
