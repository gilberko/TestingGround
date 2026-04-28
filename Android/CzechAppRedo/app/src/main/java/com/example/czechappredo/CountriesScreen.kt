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
                text = "Country names, nationalities, and the main language(s) spoken. Nationality nouns agree in gender — forms shown are Masculine / Feminine.",
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            CTTable(
                rows = listOf(
                    // ── Americas ──────────────────────────────────────────
                    Triple("Spojené státy (United States)", "Američan / Američanka", "angličtina (English)"),
                    Triple("Kanada (Canada)", "Kanaďan / Kanaďanka", "angličtina / francouzština (English / French)"),
                    Triple("Mexiko (Mexico)", "Mexičan / Mexičanka", "španělština (Spanish)"),
                    Triple("Argentina (Argentina)", "Argentinec / Argentinka", "španělština (Spanish)"),
                    Triple("Brazílie (Brazil)", "Brazilec / Brazilka", "portugalština (Portuguese)"),
                    Triple("Peru (Peru)", "Peruánec / Peruánka", "španělština (Spanish)"),
                    // ── Western Europe ────────────────────────────────────
                    Triple("Anglie (England)", "Angličan / Angličanka", "angličtina (English)"),
                    Triple("Irsko (Ireland)", "Ir / Irka", "irština / angličtina (Irish / English)"),
                    Triple("Francie (France)", "Francouz / Francouzka", "francouzština (French)"),
                    Triple("Belgie (Belgium)", "Belgičan / Belgičanka", "francouzština / nizozemština / němčina (French / Dutch / German)"),
                    Triple("Nizozemsko (Netherlands)", "Nizozemec / Nizozemka", "nizozemština (Dutch)"),
                    Triple("Německo (Germany)", "Němec / Němka", "němčina (German)"),
                    Triple("Rakousko (Austria)", "Rakušan / Rakušanka", "němčina (German)"),
                    Triple("Švýcarsko (Switzerland)", "Švýcar / Švýcarka", "němčina / francouzština / italština (German / French / Italian)"),
                    Triple("Andorra (Andorra)", "Andořan / Andořanka", "katalánština (Catalan)"),
                    Triple("Španělsko (Spain)", "Španěl / Španělka", "španělština (Spanish)"),
                    Triple("Portugalsko (Portugal)", "Portugalec / Portugalka", "portugalština (Portuguese)"),
                    Triple("Itálie (Italy)", "Ital / Italka", "italština (Italian)"),
                    Triple("Řecko (Greece)", "Řek / Řekyně", "řečtina (Greek)"),
                    Triple("Kypr (Cyprus)", "Kypřan / Kypřanka", "řečtina / turečtina (Greek / Turkish)"),
                    // ── Nordics & Iceland ─────────────────────────────────
                    Triple("Švédsko (Sweden)", "Švéd / Švédka", "švédština (Swedish)"),
                    Triple("Norsko (Norway)", "Nor / Norka", "norština (Norwegian)"),
                    Triple("Dánsko (Denmark)", "Dán / Dánka", "dánština (Danish)"),
                    Triple("Finsko (Finland)", "Fin / Finka", "finština (Finnish)"),
                    Triple("Island (Iceland)", "Islanďan / Islanďanka", "islandština (Icelandic)"),
                    // ── Eastern Europe ────────────────────────────────────
                    Triple("Česká republika (Czech Republic)", "Čech / Češka", "čeština (Czech)"),
                    Triple("Rumunsko (Romania)", "Rumun / Rumunka", "rumunština (Romanian)"),
                    Triple("Maďarsko (Hungary)", "Maďar / Maďarka", "maďarština (Hungarian)"),
                    Triple("Srbsko (Serbia)", "Srb / Srbka", "srbština (Serbian)"),
                    Triple("Chorvatsko (Croatia)", "Chorvat / Chorvatka", "chorvatština (Croatian)"),
                    Triple("Slovinsko (Slovenia)", "Slovinec / Slovinka", "slovinština (Slovenian)"),
                    Triple("Černá Hora (Montenegro)", "Černohorec / Černohorka", "černohorština (Montenegrin)"),
                    Triple("Litva (Lithuania)", "Litevec / Litevka", "litevština (Lithuanian)"),
                    Triple("Estonsko (Estonia)", "Estonec / Estonka", "estonština (Estonian)"),
                    // ── Eastern Europe / Caucasus ─────────────────────────
                    Triple("Rusko (Russia)", "Rus / Ruska", "ruština (Russian)"),
                    Triple("Arménie (Armenia)", "Arménec / Arménka", "arménština (Armenian)"),
                    Triple("Ázerbájdžán (Azerbaijan)", "Ázerbájdžánec / Ázerbájdžánka", "ázerbájdžánština (Azerbaijani)"),
                    Triple("Gruzie (Georgia)", "Gruzínec / Gruzínka", "gruzínština (Georgian)"),
                    // ── Middle East & North Africa ────────────────────────
                    Triple("Turecko (Turkey)", "Turek / Turkyně", "turečtina (Turkish)"),
                    Triple("Sýrie (Syria)", "Syřan / Syřanka", "arabština (Arabic)"),
                    Triple("Libanon (Lebanon)", "Libanonec / Libanoňanka", "arabština (Arabic)"),
                    Triple("Jordánsko (Jordan)", "Jordánec / Jordánka", "arabština (Arabic)"),
                    Triple("Irák (Iraq)", "Iráčan / Iráčanka", "arabština (Arabic)"),
                    Triple("Írán (Iran)", "Íránec / Íránka", "perština (Persian / Farsi)"),
                    Triple("Izrael (Israel)", "Izraelec / Izraelka", "hebrejština (Hebrew)"),
                    Triple("Egypt (Egypt)", "Egypťan / Egypťanka", "arabština (Arabic)"),
                    // ── Asia & Pacific ────────────────────────────────────
                    Triple("Jižní Korea (South Korea)", "Korejec / Korejka", "korejština (Korean)"),
                    Triple("Japonsko (Japan)", "Japonec / Japonka", "japonština (Japanese)"),
                    Triple("Čína (China)", "Číňan / Číňanka", "čínština / mandarínština (Chinese / Mandarin)"),
                    Triple("Thajsko (Thailand)", "Thajec / Thajka", "thajština (Thai)"),
                    Triple("Vietnam (Vietnam)", "Vietnamec / Vietnamka", "vietnamština (Vietnamese)"),
                    Triple("Austrálie (Australia)", "Australan / Australanka", "angličtina (English)")
                )
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CTTable(rows: List<Triple<String, String, String>>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("Country", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ButtonBlue, modifier = Modifier.weight(1f))
                Text("Nationality M / F", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ButtonBlue, modifier = Modifier.weight(1.3f))
                Text("Language", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ButtonBlue, modifier = Modifier.weight(0.9f))
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = Color.LightGray, thickness = 0.5.dp)
            rows.forEach { (country, nationality, language) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                ) {
                    Text(country, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(1f))
                    Text(nationality, fontSize = 12.sp, color = Color.DarkGray, modifier = Modifier.weight(1.3f))
                    Text(language, fontSize = 12.sp, color = Color.DarkGray, modifier = Modifier.weight(0.9f))
                }
            }
        }
    }
}
