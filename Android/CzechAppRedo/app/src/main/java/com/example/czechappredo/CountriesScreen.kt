package com.example.czechappredo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun CountriesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Countries & Languages", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Country names, capitals, nationalities (Masculine / Feminine), and the main language(s) spoken.",
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            CTSection("Kontinenty / Continents")
            listOf(
                "Evropa" to "Europe",
                "Asie" to "Asia",
                "Afrika" to "Africa",
                "Severní Amerika" to "North America",
                "Jižní Amerika" to "South America",
                "Austrálie / Oceánie" to "Australia / Oceania",
                "Blízký východ" to "Middle East (region, not a continent)"
            ).forEach { (czech, english) ->
                CTSimpleRow(czech, english)
            }

            Spacer(modifier = Modifier.height(16.dp))

            CTTable(
                rows = listOf(
                    // ── Evropa / Europe ───────────────────────────────────────────────────────
                    CTRow.Header("Evropa / Europe"),
                    CTRow.Entry("Anglie (England)", "Londýn (London)", "Angličan / Angličanka", "angličtina"),
                    CTRow.Entry("Irsko (Ireland)", "Dublin", "Ir / Irka", "irština / angl."),
                    CTRow.Entry("Francie (France)", "Paříž (Paris)", "Francouz / Francouzka", "francouzština"),
                    CTRow.Entry("Belgie (Belgium)", "Brusel (Brussels)", "Belgičan / Belgičanka", "franc. / niz. / něm."),
                    CTRow.Entry("Nizozemsko (Netherlands)", "Amsterdam", "Nizozemec / Nizozemka", "nizozemština"),
                    CTRow.Entry("Německo (Germany)", "Berlín (Berlin)", "Němec / Němka", "němčina"),
                    CTRow.Entry("Rakousko (Austria)", "Vídeň (Vienna)", "Rakušan / Rakušanka", "němčina"),
                    CTRow.Entry("Švýcarsko (Switzerland)", "Bern", "Švýcar / Švýcarka", "něm. / franc. / ital."),
                    CTRow.Entry("Andorra (Andorra)", "Andorra la Vella", "Andořan / Andořanka", "katalánština"),
                    CTRow.Entry("Španělsko (Spain)", "Madrid", "Španěl / Španělka", "španělština"),
                    CTRow.Entry("Portugalsko (Portugal)", "Lisabon (Lisbon)", "Portugalec / Portugalka", "portugalština"),
                    CTRow.Entry("Itálie (Italy)", "Řím (Rome)", "Ital / Italka", "italština"),
                    CTRow.Entry("Malta (Malta)", "Valletta", "Malťan / Malťanka", "maltština / angl."),
                    CTRow.Entry("Řecko (Greece)", "Atény (Athens)", "Řek / Řekyně", "řečtina"),
                    CTRow.Entry("Kypr (Cyprus)", "Nikósie (Nicosia)", "Kypřan / Kypřanka", "řečtina / turečtina"),
                    CTRow.Entry("Švédsko (Sweden)", "Stockholm", "Švéd / Švédka", "švédština"),
                    CTRow.Entry("Norsko (Norway)", "Oslo", "Nor / Norka", "norština"),
                    CTRow.Entry("Dánsko (Denmark)", "Kodaň (Copenhagen)", "Dán / Dánka", "dánština"),
                    CTRow.Entry("Finsko (Finland)", "Helsinky (Helsinki)", "Fin / Finka", "finština"),
                    CTRow.Entry("Island (Iceland)", "Reykjavík", "Islanďan / Islanďanka", "islandština"),
                    CTRow.Entry("Česká republika (Czech Republic)", "Praha (Prague)", "Čech / Češka", "čeština"),
                    CTRow.Entry("Rumunsko (Romania)", "Bukurešť (Bucharest)", "Rumun / Rumunka", "rumunština"),
                    CTRow.Entry("Maďarsko (Hungary)", "Budapešť (Budapest)", "Maďar / Maďarka", "maďarština"),
                    CTRow.Entry("Srbsko (Serbia)", "Bělehrad (Belgrade)", "Srb / Srbka", "srbština"),
                    CTRow.Entry("Chorvatsko (Croatia)", "Záhřeb (Zagreb)", "Chorvat / Chorvatka", "chorvatština"),
                    CTRow.Entry("Slovinsko (Slovenia)", "Lublaň (Ljubljana)", "Slovinec / Slovinka", "slovinština"),
                    CTRow.Entry("Černá Hora (Montenegro)", "Podgorica", "Černohorec / Černohorka", "černohorština"),
                    CTRow.Entry("Litva (Lithuania)", "Vilnius", "Litevec / Litevka", "litevština"),
                    CTRow.Entry("Estonsko (Estonia)", "Tallinn", "Estonec / Estonka", "estonština"),
                    CTRow.Entry("Rusko (Russia)", "Moskva (Moscow)", "Rus / Ruska", "ruština"),
                    CTRow.Entry("Arménie (Armenia)", "Jerevan (Yerevan)", "Arménec / Arménka", "arménština"),
                    CTRow.Entry("Ázerbájdžán (Azerbaijan)", "Baku", "Ázerbájdžánec / Ázerbájdžánka", "ázerbájdžánština"),
                    CTRow.Entry("Gruzie (Georgia)", "Tbilisi", "Gruzínec / Gruzínka", "gruzínština"),

                    // ── Blízký východ a severní Afrika / Middle East & North Africa ──────────
                    CTRow.Header("Blízký východ a severní Afrika / Middle East & North Africa"),
                    CTRow.Entry("Maroko (Morocco)", "Rabat", "Maročan / Maročanka", "arabština"),
                    CTRow.Entry("Turecko (Turkey)", "Ankara", "Turek / Turkyně", "turečtina"),
                    CTRow.Entry("Sýrie (Syria)", "Damašek (Damascus)", "Syřan / Syřanka", "arabština"),
                    CTRow.Entry("Libanon (Lebanon)", "Bejrút (Beirut)", "Libanonec / Libanoňanka", "arabština"),
                    CTRow.Entry("Jordánsko (Jordan)", "Ammán (Amman)", "Jordánec / Jordánka", "arabština"),
                    CTRow.Entry("Irák (Iraq)", "Bagdád (Baghdad)", "Iráčan / Iráčanka", "arabština"),
                    CTRow.Entry("Írán (Iran)", "Teherán (Tehran)", "Íránec / Íránka", "perština"),
                    CTRow.Entry("Izrael (Israel)", "Jeruzalém (Jerusalem)", "Izraelec / Izraelka", "hebrejština"),
                    CTRow.Entry("Egypt (Egypt)", "Káhira (Cairo)", "Egypťan / Egypťanka", "arabština"),

                    // ── Severní a Střední Amerika / North & Central America ───────────────────
                    CTRow.Header("Severní a Střední Amerika / North & Central America"),
                    CTRow.Entry("Spojené státy (USA)", "Washington D.C.", "Američan / Američanka", "angličtina"),
                    CTRow.Entry("Kanada (Canada)", "Ottawa", "Kanaďan / Kanaďanka", "angl. / franc."),
                    CTRow.Entry("Mexiko (Mexico)", "Ciudad de México", "Mexičan / Mexičanka", "španělština"),
                    CTRow.Entry("Kuba (Cuba)", "Havana", "Kubánec / Kubánka", "španělština"),
                    CTRow.Entry("Kostarika (Costa Rica)", "San José", "Kostaričan / Kostaričanka", "španělština"),

                    // ── Jižní Amerika / South America ─────────────────────────────────────────
                    CTRow.Header("Jižní Amerika / South America"),
                    CTRow.Entry("Argentina (Argentina)", "Buenos Aires", "Argentinec / Argentinka", "španělština"),
                    CTRow.Entry("Brazílie (Brazil)", "Brasília", "Brazilec / Brazilka", "portugalština"),
                    CTRow.Entry("Peru (Peru)", "Lima", "Peruánec / Peruánka", "španělština"),
                    CTRow.Entry("Uruguay (Uruguay)", "Montevideo", "Uruguayec / Uruguayka", "španělština"),
                    CTRow.Entry("Paraguay (Paraguay)", "Asunción", "Paraguayec / Paraguayka", "šp. / guaraní"),

                    // ── Afrika / Africa ────────────────────────────────────────────────────────
                    CTRow.Header("Afrika / Africa"),
                    CTRow.Entry("Senegal (Senegal)", "Dakar", "Senegalec / Senegalka", "francouzština"),

                    // ── Asie / Asia ────────────────────────────────────────────────────────────
                    CTRow.Header("Asie / Asia"),
                    CTRow.Entry("Čína (China)", "Peking (Beijing)", "Číňan / Číňanka", "čínština / mandar."),
                    CTRow.Entry("Jižní Korea (South Korea)", "Soul (Seoul)", "Korejec / Korejka", "korejština"),
                    CTRow.Entry("Japonsko (Japan)", "Tokio (Tokyo)", "Japonec / Japonka", "japonština"),
                    CTRow.Entry("Thajsko (Thailand)", "Bangkok", "Thajec / Thajka", "thajština"),
                    CTRow.Entry("Vietnam (Vietnam)", "Hanoj (Hanoi)", "Vietnamec / Vietnamka", "vietnamština"),

                    // ── Oceánie / Oceania ──────────────────────────────────────────────────────
                    CTRow.Header("Oceánie / Oceania"),
                    CTRow.Entry("Austrálie (Australia)", "Canberra", "Australan / Australanka", "angličtina")
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            CTSection("Slavné ostrovy / Famous Islands")
            Text(
                text = "These islands are not independent countries — nationality and language follow the parent country.",
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            CTIslandTable(
                rows = listOf(
                    Triple("Ibiza (Ibiza)", "Španělsko (Spain)", "španělština"),
                    Triple("Mallorca (Mallorca)", "Španělsko (Spain)", "španělština"),
                    Triple("Rhodos (Rhodes)", "Řecko (Greece)", "řečtina"),
                    Triple("Kréta (Crete)", "Řecko (Greece)", "řečtina"),
                    Triple("Sicílie (Sicily)", "Itálie (Italy)", "italština"),
                    Triple("Sardinie (Sardinia)", "Itálie (Italy)", "italština"),
                    Triple("Havaj (Hawaii)", "USA", "angličtina"),
                    Triple("Bali (Bali)", "Indonésie (Indonesia)", "indonéština")
                )
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

private sealed class CTRow {
    data class Header(val label: String) : CTRow()
    data class Entry(
        val country: String,
        val capital: String,
        val nationality: String,
        val language: String
    ) : CTRow()
}

@Composable
private fun CTSection(text: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun CTSimpleRow(czech: String, english: String) {
    Row(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(czech, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(1f))
        Text(english, fontSize = 14.sp, color = Color.DarkGray, modifier = Modifier.weight(1.4f))
    }
}

@Composable
private fun CTTable(rows: List<CTRow>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("Country", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ButtonBlue, modifier = Modifier.weight(0.9f))
                Text("Capital", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ButtonBlue, modifier = Modifier.weight(0.75f))
                Text("Nationality M / F", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ButtonBlue, modifier = Modifier.weight(1.1f))
                Text("Language", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ButtonBlue, modifier = Modifier.weight(0.75f))
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = Color.LightGray, thickness = 0.5.dp)
            rows.forEach { row ->
                when (row) {
                    is CTRow.Header -> {
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(ButtonBlue, shape = RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 3.dp)
                        ) {
                            Text(row.label, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                    is CTRow.Entry -> {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp)
                        ) {
                            Text(row.country, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(0.9f))
                            Text(row.capital, fontSize = 11.sp, color = Color.DarkGray, modifier = Modifier.weight(0.75f))
                            Text(row.nationality, fontSize = 11.sp, color = Color.DarkGray, modifier = Modifier.weight(1.1f))
                            Text(row.language, fontSize = 11.sp, color = Color.DarkGray, modifier = Modifier.weight(0.75f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CTIslandTable(rows: List<Triple<String, String, String>>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("Island", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ButtonBlue, modifier = Modifier.weight(1f))
                Text("Belongs to", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ButtonBlue, modifier = Modifier.weight(1f))
                Text("Language", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ButtonBlue, modifier = Modifier.weight(1f))
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = Color.LightGray, thickness = 0.5.dp)
            rows.forEach { (island, country, language) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                ) {
                    Text(island, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(1f))
                    Text(country, fontSize = 11.sp, color = Color.DarkGray, modifier = Modifier.weight(1f))
                    Text(language, fontSize = 11.sp, color = Color.DarkGray, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
