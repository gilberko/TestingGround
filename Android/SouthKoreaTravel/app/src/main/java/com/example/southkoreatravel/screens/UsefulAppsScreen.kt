package com.example.southkoreatravel.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UsefulAppsScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Useful Apps", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Does Google Maps Work in Korea?")
            BodyText(boldNames("Only partly. A decades-old national security law has long blocked detailed map data from leaving Korea, so Google Maps can show the map layer and do basic searches, but it cannot give turn-by-turn driving directions, reliable walking directions, or public-transit routing. In February 2026 the Korean government agreed to let Google export the data it needs under strict conditions (local server processing, government approval, blurring sensitive sites), but as of now Google Maps still hasn't gained full navigation support in Korea — don't count on it for directions.", listOf("Google Maps")))
            BodyText(boldNames("One partial workaround: Waze does work for driving navigation in Korea, if you'd rather stick with a Google-family app.", listOf("Waze")))

            SectionHeader("Navigation Apps")
            BodyText(boldNames("For actual point-to-point navigation, use Naver Map and Kakao Map — both are Korean-made and both can be switched to English (Naver Map: Menu → Settings → Language → English). Naver Map has the stronger search and is generally the most tourist-friendly for walking and transit directions; Kakao Map has a cleaner interface and is often preferred for driving and taxis. Many travelers just install both, since each is a bit stronger in different areas.", listOf("Naver Map", "Kakao Map")))

            SectionHeader("Public Transportation Apps")
            BodyText(boldNames("The same two apps, Naver Map and Kakao Map, handle subway and bus route planning — enter a destination and either will give you a full transit route with line numbers, transfer points, and timing, similar to how Google Maps transit works in other countries.", listOf("Naver Map", "Kakao Map", "Google Maps")))

            SectionHeader("Taxi Apps")
            BodyText(boldNames("Kakao T is by far the dominant taxi app in Korea, with over 90% market share and the fastest pickup times. One catch: registering a foreign credit card for automatic in-app payment currently isn't supported, since it requires a Korean phone number for verification — instead you pay the driver directly at the end of the ride with a physical card, cash, or a T-money card, which works smoothly in the vast majority of cars.", listOf("Kakao T", "T-money")))
            BodyText(boldNames("Uber does operate in Korea, but not as private ride-sharing — instead it dispatches the same licensed taxis as Kakao T (the app switches to a \"UT\" icon once you're in Korea). Its advantage for visitors is that it accepts international credit cards directly in-app with no local account setup needed, which can be simpler than Kakao T if you don't want to deal with a physical-payment handoff. Bolt does not operate in South Korea at all, so it isn't an option here.", listOf("Uber", "Kakao T", "Bolt")))

            SectionHeader("Allergen / Food Safety Scanning Apps")
            BodyText(boldNames("A handful of apps let you scan a product's barcode or ingredient label in a Korean supermarket or convenience store and get an allergen read-out in English, which is useful since Korean packaging is rarely bilingual. Allergen Scout (iOS and Android, free) reads ingredient labels in 100+ languages and flags major allergens — peanuts, tree nuts, dairy, eggs, wheat/gluten, soy, fish, shellfish, sesame — including \"may contain\" warnings.", listOf("Allergen Scout")))
            BodyText(boldNames("Food Dog (Android) scans barcodes for the 9 major allergens plus nutrition and diet tags (vegan, halal, kosher, etc.). It runs on the crowdsourced OpenFoodFacts database, so coverage of Korean and imported products is incomplete — a blank result usually means \"no data available\" rather than \"confirmed safe,\" so don't read a lack of a warning as a green light. Nutristant (iOS and Android, free) is a similar AI label/barcode scanner flagging allergens plus additives and sugar, explicitly positioned as label-reading support rather than medical advice.", listOf("Food Dog", "Nutristant")))
            BodyText(boldNames("Moya - Korean Food Scanner (iOS) and SPICE - Food Allergy Scanner (Android) are two more scanner apps aimed at this same use case; SPICE in particular appears to be a smaller, less-established app, so results may be less consistent than the more widely-used options above.", listOf("Moya - Korean Food Scanner", "SPICE - Food Allergy Scanner")))
            BodyText("None of these apps are official Korean government tools, and database coverage of Korean products varies between them. For anything more serious than a mild sensitivity, don't rely on an app alone — pair it with a printed Korean allergy card or by asking staff directly.")
        }
    }
}
