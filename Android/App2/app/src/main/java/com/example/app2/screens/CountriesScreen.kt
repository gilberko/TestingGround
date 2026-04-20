package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class CountryEntry(val pt: String, val article: String, val en: String)
private data class NationalityEntry(val country: String, val masc: String, val fem: String, val note: String = "")
private data class LanguageEntry(val pt: String, val en: String, val countries: String)
private data class CountriesVocabEntry(val pt: String, val en: String, val note: String = "")

private val countries = listOf(
    CountryEntry("Portugal", "—", "Portugal"),
    CountryEntry("Espanha", "a", "Spain"),
    CountryEntry("Alemanha", "a", "Germany"),
    CountryEntry("Grécia", "a", "Greece"),
    CountryEntry("Finlândia", "a", "Finland"),
    CountryEntry("Grã-Bretanha", "a", "Britain"),
    CountryEntry("Inglaterra", "a", "England"),
    CountryEntry("Escócia", "a", "Scotland"),
    CountryEntry("País de Gales", "o", "Wales"),
    CountryEntry("Áustria", "a", "Austria"),
    CountryEntry("República Checa", "a", "Czech Republic"),
    CountryEntry("Hungria", "a", "Hungary"),
    CountryEntry("Rússia", "a", "Russia"),
    CountryEntry("China", "a", "China"),
    CountryEntry("Japão", "o", "Japan"),
    CountryEntry("Coreia do Sul", "a", "South Korea"),
    CountryEntry("Vietname", "o", "Vietnam"),
    CountryEntry("Tailândia", "a", "Thailand"),
    CountryEntry("Israel", "—", "Israel"),
    CountryEntry("Egipto", "o", "Egypt"),
    CountryEntry("Jordânia", "a", "Jordan"),
    CountryEntry("Marrocos", "o", "Morocco"),
    CountryEntry("Líbano", "o", "Lebanon"),
    CountryEntry("Síria", "a", "Syria"),
    CountryEntry("Irão", "o", "Iran"),
    CountryEntry("Iraque", "o", "Iraq"),
    CountryEntry("Estados Unidos", "os", "United States"),
    CountryEntry("Brasil", "o", "Brazil"),
    CountryEntry("Argentina", "a", "Argentina"),
    CountryEntry("Peru", "o", "Peru"),
    CountryEntry("Turquia", "a", "Turkey"),
    CountryEntry("Lituânia", "a", "Lithuania"),
    CountryEntry("Roménia", "a", "Romania"),
    CountryEntry("Itália", "a", "Italy"),
    CountryEntry("França", "a", "France"),
    CountryEntry("Chipre", "o", "Cyprus"),
    CountryEntry("Austrália", "a", "Australia"),
    CountryEntry("Países Baixos", "os", "The Netherlands"),
    CountryEntry("Arménia", "a", "Armenia"),
    CountryEntry("Índia", "a", "India"),
    CountryEntry("Paquistão", "o", "Pakistan"),
    CountryEntry("Ucrânia", "a", "Ukraine"),
    CountryEntry("Andorra", "—", "Andorra"),
    CountryEntry("Polónia", "a", "Poland"),
    CountryEntry("Suécia", "a", "Sweden"),
    CountryEntry("Suíça", "a", "Switzerland"),
    CountryEntry("Islândia", "a", "Iceland"),
    CountryEntry("Dinamarca", "a", "Denmark"),
    CountryEntry("Noruega", "a", "Norway"),
    CountryEntry("México", "o", "Mexico"),
    CountryEntry("Canadá", "o", "Canada"),
    CountryEntry("Cuba", "—", "Cuba"),
    CountryEntry("Bolívia", "a", "Bolivia"),
    CountryEntry("Chile", "o", "Chile"),
    CountryEntry("Uruguai", "o", "Uruguay"),
    CountryEntry("Paraguai", "o", "Paraguay")
)

private val nationalities = listOf(
    NationalityEntry("Portugal", "português", "portuguesa", "pl: portugueses / portuguesas"),
    NationalityEntry("Espanha", "espanhol", "espanhola", "pl: espanhóis / espanholas"),
    NationalityEntry("Alemanha", "alemão", "alemã", "pl: alemães / alemãs"),
    NationalityEntry("Grécia", "grego", "grega"),
    NationalityEntry("Finlândia", "finlandês", "finlandesa"),
    NationalityEntry("Grã-Bretanha", "britânico", "britânica"),
    NationalityEntry("Inglaterra", "inglês", "inglesa"),
    NationalityEntry("Escócia", "escocês", "escocesa"),
    NationalityEntry("País de Gales", "galês", "galesa"),
    NationalityEntry("Áustria", "austríaco", "austríaca"),
    NationalityEntry("República Checa", "checo", "checa"),
    NationalityEntry("Hungria", "húngaro", "húngara"),
    NationalityEntry("Rússia", "russo", "russa"),
    NationalityEntry("China", "chinês", "chinesa"),
    NationalityEntry("Japão", "japonês", "japonesa"),
    NationalityEntry("Coreia do Sul", "coreano", "coreana"),
    NationalityEntry("Vietname", "vietnamita", "vietnamita", "same m/f form"),
    NationalityEntry("Tailândia", "tailandês", "tailandesa"),
    NationalityEntry("Israel", "israelita", "israelita", "same m/f form"),
    NationalityEntry("Egipto", "egípcio", "egípcia"),
    NationalityEntry("Jordânia", "jordano", "jordana"),
    NationalityEntry("Marrocos", "marroquino", "marroquina"),
    NationalityEntry("Líbano", "libanês", "libanesa"),
    NationalityEntry("Síria", "sírio", "síria"),
    NationalityEntry("Irão", "iraniano", "iraniana"),
    NationalityEntry("Iraque", "iraquiano", "iraquiana"),
    NationalityEntry("Estados Unidos", "americano", "americana", "also: norte-americano/a"),
    NationalityEntry("Brasil", "brasileiro", "brasileira"),
    NationalityEntry("Argentina", "argentino", "argentina"),
    NationalityEntry("Peru", "peruano", "peruana"),
    NationalityEntry("Turquia", "turco", "turca"),
    NationalityEntry("Lituânia", "lituano", "lituana"),
    NationalityEntry("Roménia", "romeno", "romena"),
    NationalityEntry("Itália", "italiano", "italiana"),
    NationalityEntry("França", "francês", "francesa"),
    NationalityEntry("Chipre", "cipriota", "cipriota", "same m/f form"),
    NationalityEntry("Austrália", "australiano", "australiana"),
    NationalityEntry("Países Baixos", "neerlandês", "neerlandesa", "colloq: holandês / holandesa"),
    NationalityEntry("Arménia", "arménio", "arménia"),
    NationalityEntry("Índia", "indiano", "indiana"),
    NationalityEntry("Paquistão", "paquistanês", "paquistanesa"),
    NationalityEntry("Ucrânia", "ucraniano", "ucraniana"),
    NationalityEntry("Andorra", "andorrano", "andorrana"),
    NationalityEntry("Polónia", "polaco", "polaca", "EP form (BP: polonês / polonesa)"),
    NationalityEntry("Suécia", "sueco", "sueca"),
    NationalityEntry("Suíça", "suíço", "suíça"),
    NationalityEntry("Islândia", "islandês", "islandesa"),
    NationalityEntry("Dinamarca", "dinamarquês", "dinamarquesa"),
    NationalityEntry("Noruega", "norueguês", "norueguesa"),
    NationalityEntry("México", "mexicano", "mexicana"),
    NationalityEntry("Canadá", "canadiano", "canadiana", "EP form (BP: canadense)"),
    NationalityEntry("Cuba", "cubano", "cubana"),
    NationalityEntry("Bolívia", "boliviano", "boliviana"),
    NationalityEntry("Chile", "chileno", "chilena"),
    NationalityEntry("Uruguai", "uruguaio", "uruguaia"),
    NationalityEntry("Paraguai", "paraguaio", "paraguaia")
)

private val languages = listOf(
    LanguageEntry("português", "Portuguese", "Portugal, Brasil"),
    LanguageEntry("espanhol (castelhano)", "Spanish", "Espanha, México, Argentina, Peru, Cuba, Bolívia, Chile, Uruguai, Paraguai"),
    LanguageEntry("alemão", "German", "Alemanha, Áustria, Suíça (parcialmente)"),
    LanguageEntry("grego", "Greek", "Grécia, Chipre"),
    LanguageEntry("finlandês", "Finnish", "Finlândia"),
    LanguageEntry("inglês", "English", "Grã-Bretanha, Inglaterra, Escócia, País de Gales, EUA, Austrália, Canadá"),
    LanguageEntry("checo", "Czech", "República Checa"),
    LanguageEntry("húngaro", "Hungarian", "Hungria"),
    LanguageEntry("russo", "Russian", "Rússia"),
    LanguageEntry("chinês / mandarim", "Chinese / Mandarin", "China"),
    LanguageEntry("japonês", "Japanese", "Japão"),
    LanguageEntry("coreano", "Korean", "Coreia do Sul"),
    LanguageEntry("vietnamita", "Vietnamese", "Vietname"),
    LanguageEntry("tailandês", "Thai", "Tailândia"),
    LanguageEntry("hebraico", "Hebrew", "Israel"),
    LanguageEntry("árabe", "Arabic", "Egipto, Jordânia, Marrocos, Líbano, Síria, Iraque"),
    LanguageEntry("persa (farsi)", "Persian (Farsi)", "Irão"),
    LanguageEntry("turco", "Turkish", "Turquia, Chipre (parcialmente)"),
    LanguageEntry("lituano", "Lithuanian", "Lituânia"),
    LanguageEntry("romeno", "Romanian", "Roménia"),
    LanguageEntry("italiano", "Italian", "Itália, Suíça (parcialmente)"),
    LanguageEntry("francês", "French", "França, Suíça (parcialmente), Canadá (parcialmente)"),
    LanguageEntry("neerlandês (holandês)", "Dutch", "Países Baixos"),
    LanguageEntry("arménio", "Armenian", "Arménia"),
    LanguageEntry("hindi", "Hindi", "Índia (entre outras)"),
    LanguageEntry("urdu", "Urdu", "Paquistão"),
    LanguageEntry("ucraniano", "Ukrainian", "Ucrânia"),
    LanguageEntry("catalão", "Catalan", "Andorra (entre outras)"),
    LanguageEntry("polaco", "Polish", "Polónia"),
    LanguageEntry("sueco", "Swedish", "Suécia"),
    LanguageEntry("islandês", "Icelandic", "Islândia"),
    LanguageEntry("dinamarquês", "Danish", "Dinamarca"),
    LanguageEntry("norueguês", "Norwegian", "Noruega")
)

private val vocabWords = listOf(
    CountriesVocabEntry("língua (f) / idioma (m)", "language", "língua also means 'tongue' (body part); idioma emphasises the language system"),
    CountriesVocabEntry("nação (f)", "nation"),
    CountriesVocabEntry("país (m)", "country"),
    CountriesVocabEntry("natural (de)", "native (from)", "\"É natural de Lisboa\" = He's a native of Lisbon; nativo/a also used"),
    CountriesVocabEntry("falante nativo (m) / falante nativa (f)", "native speaker"),
    CountriesVocabEntry("imigrante (m/f)", "immigrant", "same form for masc and fem"),
    CountriesVocabEntry("expatriado (m) / expatriada (f)", "expat", "\"expat\" also used as a borrowing"),
    CountriesVocabEntry("imigração (f)", "immigration"),
    CountriesVocabEntry("estrangeiro (m) / estrangeira (f)", "foreigner"),
    CountriesVocabEntry("estrangeiro/a (adj)", "foreign", "\"língua estrangeira\" = foreign language")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Countries, Nationalities & Languages") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { ArticlesExplanationCard() }
            item { CountriesCard() }
            item { NationalitiesCard() }
            item { LanguagesCard() }
            item { VocabCard() }
        }
    }
}

@Composable
private fun ArticlesExplanationCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                "Articles with Country Names",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
            Text(
                "Most country names in European Portuguese take a definite article. The article agrees in gender and number with the country name:",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                "• Feminine (a): a França, a Alemanha, a Espanha, a Rússia …",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "• Masculine (o): o Brasil, o Japão, o Irão, o México …",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "• Plural (os/as): os Estados Unidos, os Países Baixos",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "• No article: Portugal, Israel, Andorra, Cuba",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "The article contracts with prepositions:",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                "em + o = no   |   de + o = do   |   a + o = ao",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic
            )
            Text(
                "em + a = na   |   de + a = da   |   a + a = à",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Examples:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                "Vou ao Brasil. — I'm going to Brazil.",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "Venho da França. — I come from France.",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "Vivo em Portugal. — I live in Portugal.",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "Estou em Israel. — I'm in Israel.",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun CountriesCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                "Countries — Os Países",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            HorizontalDivider(modifier = Modifier.padding(bottom = 2.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Art.",
                    modifier = Modifier.weight(0.18f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "Português",
                    modifier = Modifier.weight(0.47f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "English",
                    modifier = Modifier.weight(0.35f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
            countries.forEach { entry ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        entry.article,
                        modifier = Modifier.weight(0.18f),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        entry.pt,
                        modifier = Modifier.weight(0.47f),
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        entry.en,
                        modifier = Modifier.weight(0.35f),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun NationalitiesCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                "Nationalities — Nacionalidades",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            HorizontalDivider(modifier = Modifier.padding(bottom = 2.dp))
            Text(
                "Nationality words are adjectives — they agree with the noun they describe:",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                "\"um homem português\" (a Portuguese man)  /  \"uma mulher portuguesa\" (a Portuguese woman)",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic
            )
            Text(
                "Plurals: add -s for most; -ês → -eses (inglês → ingleses); -ão → -ães (alemão → alemães)",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Country",
                    modifier = Modifier.weight(0.30f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "Masculine",
                    modifier = Modifier.weight(0.35f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "Feminine",
                    modifier = Modifier.weight(0.35f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
            nationalities.forEach { entry ->
                Column {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            entry.country,
                            modifier = Modifier.weight(0.30f),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            entry.masc,
                            modifier = Modifier.weight(0.35f),
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            entry.fem,
                            modifier = Modifier.weight(0.35f),
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    if (entry.note.isNotEmpty()) {
                        Text(
                            entry.note,
                            style = MaterialTheme.typography.labelSmall,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.padding(start = 0.dp, bottom = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LanguagesCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                "Languages — Línguas",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            HorizontalDivider(modifier = Modifier.padding(bottom = 2.dp))
            Text(
                "Language names are the same as the masculine nationality adjective.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                "• After falar/perceber/saber, no article: \"Falo russo.\" (I speak Russian.)",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "• As a subject, use the article: \"O russo é difícil.\" (Russian is difficult.)",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "• As an adjective: \"um livro italiano\" = an Italian book (Italian in origin/style)",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "• Language of writing: \"em\" + language — \"um livro em italiano\" = a book in Italian",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Português",
                    modifier = Modifier.weight(0.28f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "English",
                    modifier = Modifier.weight(0.25f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "Spoken in",
                    modifier = Modifier.weight(0.47f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
            languages.forEach { entry ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        entry.pt,
                        modifier = Modifier.weight(0.28f),
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        entry.en,
                        modifier = Modifier.weight(0.25f),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        entry.countries,
                        modifier = Modifier.weight(0.47f),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun VocabCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                "Key Vocabulary — Vocabulário-chave",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
            vocabWords.forEach { entry ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = entry.pt,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = entry.en,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                if (entry.note.isNotEmpty()) {
                    Text(
                        text = entry.note,
                        style = MaterialTheme.typography.labelSmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    }
}
