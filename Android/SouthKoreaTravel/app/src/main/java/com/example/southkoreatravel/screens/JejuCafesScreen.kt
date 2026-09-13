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
fun JejuCafesScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Cafes", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Fritz Coffee Company (Seongsan)")
            BodyText(boldNames("A specialty roastery set in a renovated former seafood hall near Seongsan Ilchulbong, often called the prettiest coffee spot on the island thanks to its views of the sunrise peak.", listOf("Fritz Coffee Company", "Seongsan Ilchulbong")))

            SectionHeader("Orrrn")
            BodyText(boldNames("A bakery-cafe designed to resemble a small oreum (volcanic cone), with ocean views and a canola-flower field out back. Known for its croffles, pies, and its signature Orrrn Latte.", listOf("Orrrn", "Orrrn Latte")))

            SectionHeader("Cafe C.Nic")
            BodyText(boldNames("Sits right in front of Woljeongri Beach, popular for its emerald-water views over coffee.", listOf("Cafe C.Nic", "Woljeongri Beach")))

            SectionHeader("Cafe Delmoondo")
            BodyText(boldNames("Famous for its view over Hamdeok Beach, one of the most photographed cafe views on Jeju.", listOf("Cafe Delmoondo", "Hamdeok Beach")))

            SectionHeader("Little Waves City Roasters")
            BodyText(boldNames("A specialty roastery with a Japanese-influenced interior, well regarded for coffee quality over its views.", listOf("Little Waves City Roasters")))

            SectionHeader("Local Flavor: Citrus Drinks")
            BodyText(boldNames("Jeju is famous for tangerines and hallabong citrus, and most cafes put a local spin on the menu with a gyul (tangerine) latte or hallabong cake — worth trying somewhere on the island.", listOf("hallabong", "gyul")))
        }
    }
}
