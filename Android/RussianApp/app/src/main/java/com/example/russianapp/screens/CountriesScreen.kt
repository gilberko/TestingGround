package com.example.russianapp.screens

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

private data class CountryEntry(val ru: String, val gender: String, val en: String)
private data class NationalityEntry(val country: String, val masc: String, val fem: String, val pl: String, val note: String = "")
private data class LanguageEntry(val ru: String, val poForm: String, val en: String, val countries: String)
private data class CountriesVocabEntry(val ru: String, val en: String, val note: String = "")

private val countries = listOf(
    CountryEntry("Португалия", "ж", "Portugal"),
    CountryEntry("Испания", "ж", "Spain"),
    CountryEntry("Германия", "ж", "Germany"),
    CountryEntry("Греция", "ж", "Greece"),
    CountryEntry("Финляндия", "ж", "Finland"),
    CountryEntry("Великобритания", "ж", "Britain"),
    CountryEntry("Англия", "ж", "England"),
    CountryEntry("Шотландия", "ж", "Scotland"),
    CountryEntry("Уэльс", "м", "Wales"),
    CountryEntry("Австрия", "ж", "Austria"),
    CountryEntry("Чехия", "ж", "Czech Republic"),
    CountryEntry("Венгрия", "ж", "Hungary"),
    CountryEntry("Россия", "ж", "Russia"),
    CountryEntry("Китай", "м", "China"),
    CountryEntry("Япония", "ж", "Japan"),
    CountryEntry("Южная Корея", "ж", "South Korea"),
    CountryEntry("Вьетнам", "м", "Vietnam"),
    CountryEntry("Таиланд", "м", "Thailand"),
    CountryEntry("Израиль", "м", "Israel"),
    CountryEntry("Египет", "м", "Egypt"),
    CountryEntry("Иордания", "ж", "Jordan"),
    CountryEntry("Марокко", "неизм", "Morocco"),
    CountryEntry("Ливан", "м", "Lebanon"),
    CountryEntry("Сирия", "ж", "Syria"),
    CountryEntry("Иран", "м", "Iran"),
    CountryEntry("Ирак", "м", "Iraq"),
    CountryEntry("США", "мн", "United States"),
    CountryEntry("Бразилия", "ж", "Brazil"),
    CountryEntry("Аргентина", "ж", "Argentina"),
    CountryEntry("Перу", "неизм", "Peru"),
    CountryEntry("Турция", "ж", "Turkey"),
    CountryEntry("Литва", "ж", "Lithuania"),
    CountryEntry("Румыния", "ж", "Romania"),
    CountryEntry("Италия", "ж", "Italy"),
    CountryEntry("Франция", "ж", "France"),
    CountryEntry("Кипр", "м", "Cyprus"),
    CountryEntry("Австралия", "ж", "Australia"),
    CountryEntry("Нидерланды", "мн", "The Netherlands"),
    CountryEntry("Армения", "ж", "Armenia"),
    CountryEntry("Индия", "ж", "India"),
    CountryEntry("Пакистан", "м", "Pakistan"),
    CountryEntry("Украина", "ж", "Ukraine"),
    CountryEntry("Андорра", "ж", "Andorra"),
    CountryEntry("Польша", "ж", "Poland"),
    CountryEntry("Швеция", "ж", "Sweden"),
    CountryEntry("Швейцария", "ж", "Switzerland"),
    CountryEntry("Исландия", "ж", "Iceland"),
    CountryEntry("Дания", "ж", "Denmark"),
    CountryEntry("Норвегия", "ж", "Norway"),
    CountryEntry("Мексика", "ж", "Mexico"),
    CountryEntry("Канада", "ж", "Canada"),
    CountryEntry("Куба", "ж", "Cuba"),
    CountryEntry("Боливия", "ж", "Bolivia"),
    CountryEntry("Чили", "неизм", "Chile"),
    CountryEntry("Уругвай", "м", "Uruguay"),
    CountryEntry("Парагвай", "м", "Paraguay")
)

private val nationalities = listOf(
    NationalityEntry("Португалия", "португальский", "португальская", "португальские"),
    NationalityEntry("Испания", "испанский", "испанская", "испанские"),
    NationalityEntry("Германия", "немецкий", "немецкая", "немецкие"),
    NationalityEntry("Греция", "греческий", "греческая", "греческие"),
    NationalityEntry("Финляндия", "финский", "финская", "финские"),
    NationalityEntry("Великобритания", "британский", "британская", "британские"),
    NationalityEntry("Англия", "английский", "английская", "английские"),
    NationalityEntry("Шотландия", "шотландский", "шотландская", "шотландские"),
    NationalityEntry("Уэльс", "валлийский", "валлийская", "валлийские"),
    NationalityEntry("Австрия", "австрийский", "австрийская", "австрийские"),
    NationalityEntry("Чехия", "чешский", "чешская", "чешские"),
    NationalityEntry("Венгрия", "венгерский", "венгерская", "венгерские"),
    NationalityEntry("Россия", "русский", "русская", "русские"),
    NationalityEntry("Китай", "китайский", "китайская", "китайские"),
    NationalityEntry("Япония", "японский", "японская", "японские"),
    NationalityEntry("Южная Корея", "корейский", "корейская", "корейские", "also: южнокорейский"),
    NationalityEntry("Вьетнам", "вьетнамский", "вьетнамская", "вьетнамские"),
    NationalityEntry("Таиланд", "тайский", "тайская", "тайские"),
    NationalityEntry("Израиль", "израильский", "израильская", "израильские"),
    NationalityEntry("Египет", "египетский", "египетская", "египетские"),
    NationalityEntry("Иордания", "иорданский", "иорданская", "иорданские"),
    NationalityEntry("Марокко", "марокканский", "марокканская", "марокканские"),
    NationalityEntry("Ливан", "ливанский", "ливанская", "ливанские"),
    NationalityEntry("Сирия", "сирийский", "сирийская", "сирийские"),
    NationalityEntry("Иран", "иранский", "иранская", "иранские"),
    NationalityEntry("Ирак", "иракский", "иракская", "иракские"),
    NationalityEntry("США", "американский", "американская", "американские"),
    NationalityEntry("Бразилия", "бразильский", "бразильская", "бразильские"),
    NationalityEntry("Аргентина", "аргентинский", "аргентинская", "аргентинские"),
    NationalityEntry("Перу", "перуанский", "перуанская", "перуанские"),
    NationalityEntry("Турция", "турецкий", "турецкая", "турецкие"),
    NationalityEntry("Литва", "литовский", "литовская", "литовские"),
    NationalityEntry("Румыния", "румынский", "румынская", "румынские"),
    NationalityEntry("Италия", "итальянский", "итальянская", "итальянские"),
    NationalityEntry("Франция", "французский", "французская", "французские"),
    NationalityEntry("Кипр", "кипрский", "кипрская", "кипрские"),
    NationalityEntry("Австралия", "австралийский", "австралийская", "австралийские"),
    NationalityEntry("Нидерланды", "нидерландский", "нидерландская", "нидерландские", "colloq: голландский"),
    NationalityEntry("Армения", "армянский", "армянская", "армянские"),
    NationalityEntry("Индия", "индийский", "индийская", "индийские"),
    NationalityEntry("Пакистан", "пакистанский", "пакистанская", "пакистанские"),
    NationalityEntry("Украина", "украинский", "украинская", "украинские"),
    NationalityEntry("Андорра", "андоррский", "андоррская", "андоррские"),
    NationalityEntry("Польша", "польский", "польская", "польские"),
    NationalityEntry("Швеция", "шведский", "шведская", "шведские"),
    NationalityEntry("Швейцария", "швейцарский", "швейцарская", "швейцарские"),
    NationalityEntry("Исландия", "исландский", "исландская", "исландские"),
    NationalityEntry("Дания", "датский", "датская", "датские"),
    NationalityEntry("Норвегия", "норвежский", "норвежская", "норвежские"),
    NationalityEntry("Мексика", "мексиканский", "мексиканская", "мексиканские"),
    NationalityEntry("Канада", "канадский", "канадская", "канадские"),
    NationalityEntry("Куба", "кубинский", "кубинская", "кубинские"),
    NationalityEntry("Боливия", "боливийский", "боливийская", "боливийские"),
    NationalityEntry("Чили", "чилийский", "чилийская", "чилийские"),
    NationalityEntry("Уругвай", "уругвайский", "уругвайская", "уругвайские"),
    NationalityEntry("Парагвай", "парагвайский", "парагвайская", "парагвайские")
)

private val languages = listOf(
    LanguageEntry("португальский", "по-португальски", "Portuguese", "Португалия, Бразилия"),
    LanguageEntry("испанский", "по-испански", "Spanish", "Испания, Мексика, Аргентина, Перу, Куба, Боливия, Чили, Уругвай, Парагвай"),
    LanguageEntry("немецкий", "по-немецки", "German", "Германия, Австрия, Швейцария (частично)"),
    LanguageEntry("греческий", "по-гречески", "Greek", "Греция, Кипр"),
    LanguageEntry("финский", "по-фински", "Finnish", "Финляндия"),
    LanguageEntry("английский", "по-английски", "English", "Великобритания, Англия, Шотландия, Уэльс, США, Австралия, Канада"),
    LanguageEntry("чешский", "по-чешски", "Czech", "Чехия"),
    LanguageEntry("венгерский", "по-венгерски", "Hungarian", "Венгрия"),
    LanguageEntry("русский", "по-русски", "Russian", "Россия"),
    LanguageEntry("китайский", "по-китайски", "Chinese", "Китай"),
    LanguageEntry("японский", "по-японски", "Japanese", "Япония"),
    LanguageEntry("корейский", "по-корейски", "Korean", "Южная Корея"),
    LanguageEntry("вьетнамский", "по-вьетнамски", "Vietnamese", "Вьетнам"),
    LanguageEntry("тайский", "по-тайски", "Thai", "Таиланд"),
    LanguageEntry("иврит", "на иврите", "Hebrew", "Израиль"),
    LanguageEntry("арабский", "по-арабски", "Arabic", "Египет, Иордания, Марокко, Ливан, Сирия, Ирак"),
    LanguageEntry("персидский / фарси", "по-персидски", "Persian / Farsi", "Иран"),
    LanguageEntry("турецкий", "по-турецки", "Turkish", "Турция, Кипр (частично)"),
    LanguageEntry("литовский", "по-литовски", "Lithuanian", "Литва"),
    LanguageEntry("румынский", "по-румынски", "Romanian", "Румыния"),
    LanguageEntry("итальянский", "по-итальянски", "Italian", "Италия, Швейцария (частично)"),
    LanguageEntry("французский", "по-французски", "French", "Франция, Швейцария (частично), Канада (частично)"),
    LanguageEntry("нидерландский", "по-нидерландски", "Dutch", "Нидерланды"),
    LanguageEntry("армянский", "по-армянски", "Armenian", "Армения"),
    LanguageEntry("хинди", "на хинди", "Hindi", "Индия (среди других)"),
    LanguageEntry("урду", "на урду", "Urdu", "Пакистан"),
    LanguageEntry("украинский", "по-украински", "Ukrainian", "Украина"),
    LanguageEntry("каталанский", "по-каталански", "Catalan", "Андорра (среди других)"),
    LanguageEntry("польский", "по-польски", "Polish", "Польша"),
    LanguageEntry("шведский", "по-шведски", "Swedish", "Швеция"),
    LanguageEntry("исландский", "по-исландски", "Icelandic", "Исландия"),
    LanguageEntry("датский", "по-датски", "Danish", "Дания"),
    LanguageEntry("норвежский", "по-норвежски", "Norwegian", "Норвегия")
)

private val vocabWords = listOf(
    CountriesVocabEntry("язык (м)", "language / tongue", "also means \"tongue\" (body part); родной язык = native language"),
    CountriesVocabEntry("иностранец (м) / иностранка (ж)", "foreigner"),
    CountriesVocabEntry("родной / родная (прил.)", "native (adj)", "родной язык = native language; родина = homeland"),
    CountriesVocabEntry("иностранный / иностранная (прил.)", "foreign (adj)", "иностранный язык = foreign language"),
    CountriesVocabEntry("нация (ж)", "nation"),
    CountriesVocabEntry("страна (ж)", "country"),
    CountriesVocabEntry("уроженец (м) / уроженка (ж)", "native (of a place)", "уроженец Москвы = a native of Moscow"),
    CountriesVocabEntry("носитель языка (м) / носительница языка (ж)", "native speaker"),
    CountriesVocabEntry("иммиграция (ж)", "immigration"),
    CountriesVocabEntry("иммигрант (м) / иммигрантка (ж)", "immigrant"),
    CountriesVocabEntry("эмигрант (м) / эмигрантка (ж)", "expat / emigrant", "экспат also used as a borrowing")
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
            item { CasesExplanationCard() }
            item { CountriesCard() }
            item { NationalitiesCard() }
            item { LanguagesCard() }
            item { VocabCard() }
        }
    }
}

@Composable
private fun CasesExplanationCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                "Countries and Cases",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
            Text(
                "Russian has no articles, but prepositions + grammatical cases encode location, direction, and origin. Country gender (shown in the table below) determines case endings.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                "• Location (в + Prepositional): в России, в Китае, в Японии",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "• Direction (в + Accusative): в Россию, в Китай, в Японию",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "• Origin (из + Genitive): из России, из Китая, из Японии",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Special — на countries (islands + Ukraine by tradition):",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                "  на Кубе / с Кубы   |   на Кипре / с Кипра",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic
            )
            Text(
                "  на Украине / с Украины   (в Украине is also accepted)",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                "Indeclinable (неизм — Марокко, Перу, Чили): form never changes — в Марокко, из Перу, в Чили.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                "Plural (мн — США, Нидерланды): в США, в Нидерландах, из США, из Нидерландов.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Examples:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                "Я еду в Россию. — I'm going to Russia.",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "Я живу в России. — I live in Russia.",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "Я приехал из России. — I came from Russia.",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "Я живу на Кубе. — I live in Cuba.",
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
                "Countries — Страны",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            HorizontalDivider(modifier = Modifier.padding(bottom = 2.dp))
            Text(
                "м = masculine  |  ж = feminine  |  неизм = indeclinable neuter  |  мн = plural",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Род",
                    modifier = Modifier.weight(0.15f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "По-русски",
                    modifier = Modifier.weight(0.50f),
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
                        entry.gender,
                        modifier = Modifier.weight(0.15f),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        entry.ru,
                        modifier = Modifier.weight(0.50f),
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
                "Nationalities — Национальности",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            HorizontalDivider(modifier = Modifier.padding(bottom = 2.dp))
            Text(
                "Nationality words in Russian are adjectives — they agree with the noun in gender, number, and case. Table shows Nominative forms.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                "\"русский паспорт\" (a Russian passport)  /  \"русская виза\" (a Russian visa)  /  \"русские деньги\" (Russian money)",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Country",
                    modifier = Modifier.weight(0.22f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "Masc.",
                    modifier = Modifier.weight(0.26f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "Fem.",
                    modifier = Modifier.weight(0.26f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "Plural",
                    modifier = Modifier.weight(0.26f),
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
                            modifier = Modifier.weight(0.22f),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            entry.masc,
                            modifier = Modifier.weight(0.26f),
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            entry.fem,
                            modifier = Modifier.weight(0.26f),
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            entry.pl,
                            modifier = Modifier.weight(0.26f),
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
                            modifier = Modifier.padding(bottom = 2.dp)
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
                "Languages — Языки",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            HorizontalDivider(modifier = Modifier.padding(bottom = 2.dp))
            Text(
                "Language names are the masculine adjective form + язык (language), but язык is often dropped in context.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                "• To say \"I speak\": use the adverb по- + adjective root — \"Я говорю по-русски.\"",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "• Formal alternative: \"Я говорю на русском (языке).\"",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "• As adjective: \"русская книга\" = a Russian book (of Russian origin/style)",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "• Book in a language: \"книга на русском\" / \"книга на русском языке\"",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "• иврит (Hebrew): borrowed word, not derived from adjective. \"Я говорю на иврите.\" The adjective is ивритский.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                "• хинди and урду are indeclinable: \"Я говорю на хинди.\" / \"на урду\"",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "По-русски",
                    modifier = Modifier.weight(0.23f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "По-form",
                    modifier = Modifier.weight(0.20f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "English",
                    modifier = Modifier.weight(0.20f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "Spoken in",
                    modifier = Modifier.weight(0.37f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
            languages.forEach { entry ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        entry.ru,
                        modifier = Modifier.weight(0.23f),
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        entry.poForm,
                        modifier = Modifier.weight(0.20f),
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        entry.en,
                        modifier = Modifier.weight(0.20f),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        entry.countries,
                        modifier = Modifier.weight(0.37f),
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
                "Key Vocabulary — Ключевые слова",
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
                        text = entry.ru,
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
