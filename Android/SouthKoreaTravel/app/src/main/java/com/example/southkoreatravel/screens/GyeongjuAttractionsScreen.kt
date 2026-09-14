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
fun GyeongjuAttractionsScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Attractions", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Gyeongju World")
            BodyText(boldNames("Gyeongju World is an amusement park, opened in 1985, inside the Bomun Lake Resort tourist complex — it has the most rollercoasters of any park in Korea. It sits on Bomun-ro, about 6km east of downtown Gyeongju, within the same Bomun Tourist Complex that also has the resort hotels, Yukbu Village, and the lake itself.", listOf("Gyeongju World", "Bomun Lake Resort", "Bomun Tourist Complex")))
            BodyText(boldNames("There's no direct train station at the park — from downtown Gyeongju, take a city bus toward Bomun Resort, or a taxi. A one-day adult ticket runs roughly ₩48,000, or about ₩34,000 for an afternoon/evening ticket; children under 36 months get in free with ID.", listOf("Gyeongju World")))
            BodyText(boldNames("The park's signature rides are Draken, a B&M dive coaster that at 63m tall and 117km/h is the tallest and fastest rollercoaster in Korea, and Phaethon, an inverted coaster. As with other Korean theme parks, treat these prices and times as approximate and check the official site before you go.", listOf("Draken", "Phaethon")))
        }
    }
}
