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
        }
    }
}
