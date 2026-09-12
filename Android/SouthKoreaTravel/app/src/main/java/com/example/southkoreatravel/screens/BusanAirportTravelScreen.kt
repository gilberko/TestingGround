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
fun BusanAirportTravelScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Airport Travel", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Gimhae International Airport (PUS)")
            BodyText(boldNames("Gimhae International Airport (PUS) is Busan's main airport, about 13 minutes by light rail to the Seomyeon interchange, or roughly 26km — 24 to 60 minutes by road depending on traffic — to Haeundae.", listOf("Gimhae International Airport")))

            SectionHeader("Busan-Gimhae Light Rail Transit")
            BodyText(boldNames("The Busan-Gimhae Light Rail Transit (BGLRT) runs from Gimhae Airport Station to Sasang Station in about 30 minutes, where you transfer to Busan Metro Line 2 for downtown or Haeundae. Total fare, including the transfer, runs roughly ₩2,800-3,700. Stations have escalators and elevators and English signage, but the required transfer — with potentially several flights of stairs or escalators between the light rail and subway platforms, and crowding at peak times — makes it better suited to light packers than to a family with a lot of luggage.", listOf("Busan-Gimhae Light Rail Transit", "Sasang Station", "Busan Metro Line 2")))

            SectionHeader("Airport Limousine Bus")
            BodyText(boldNames("Two Airport Limousine Bus routes run from Gimhae Airport: Limousine 1 goes via Jangsan Station and Haeundae Beach, crossing the Gwangan Bridge, for about ₩9,500; Limousine 2 goes via Bujeon Station and Seomyeon Station (Lotte Department Store) for about ₩7,000. Both carry up to 2 pieces of luggage per passenger (under 30kg total) in a dedicated luggage hold, and go direct to their destination with no transfers.", listOf("Airport Limousine Bus", "Jangsan Station", "Haeundae Beach", "Gwangan Bridge", "Bujeon Station", "Seomyeon Station")))

            SectionHeader("Taxi")
            BodyText(boldNames("A taxi from Gimhae Airport takes roughly 15-25 minutes to Seomyeon or 24-60 minutes to Haeundae depending on traffic, for around ₩22,000-27,000 to the city center. Designated taxi stands are outside both the international and domestic terminals.", listOf("Gimhae Airport", "Seomyeon", "Haeundae")))

            SectionHeader("Family With Luggage")
            BodyText(boldNames("For a family traveling with luggage, the Airport Limousine Bus is generally the best balance of value and convenience — it has a dedicated luggage hold, runs direct to Haeundae or Seomyeon with no transfers or stairs, and costs far less than a taxi per person. A taxi remains the better choice for a late-night arrival after the limousine buses stop running, or for a destination off the two limousine routes, and can be cost-competitive once split across a family of four or more. The light rail is the cheapest option but is best suited to travelers packing light, since it requires an in-station transfer to the subway.", listOf("Airport Limousine Bus")))
        }
    }
}
