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
fun JejuPlacesOfInterestScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Places Of Interest", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Hallasan National Park")
            BodyText(boldNames("Hallasan is South Korea's tallest mountain at 1,950m, a dormant volcano with a crater lake at the summit, and a UNESCO Biosphere Reserve. Several marked trails lead up it, ranging from a few hours to a full-day round trip to the summit.", listOf("Hallasan")))

            SectionHeader("Seongsan Ilchulbong (Sunrise Peak)")
            BodyText(boldNames("Seongsan Ilchulbong is a UNESCO World Heritage volcanic tuff cone on the island's east coast. The climb to the crater rim takes about 20 minutes and is one of the most popular sunrise-viewing spots in Korea.", listOf("Seongsan Ilchulbong")))

            SectionHeader("Manjanggul Cave")
            BodyText(boldNames("Manjanggul is a lava tube around 8km long, among the longest in the world, with a walkable public section showing lava formations, columns, and one of the world's largest lava stalagmites.", listOf("Manjanggul")))

            SectionHeader("Udo Island")
            BodyText(boldNames("Udo is a small island just off Jeju's east coast, reached by a short ferry, known for coastal walking trails, peanut ice cream, and calm farmland-and-sea scenery — a popular half-day trip.", listOf("Udo")))

            SectionHeader("Jeju Olle Trail")
            BodyText(boldNames("The Jeju Olle Trail is a network of 21 coastal walking routes covering about 437km around the island, ranging from easy coastal strolls to longer full-day hikes.", listOf("Jeju Olle Trail")))

            SectionHeader("Waterfalls: Cheonjeyeon and Jeongbang")
            BodyText(boldNames("Cheonjeyeon is a scenic three-tiered waterfall reached by a short hike. Jeongbang Falls is unusual for directly emptying into the ocean — one of the only waterfalls in Asia to do so — and is only a few minutes' walk from parking, though neither site is stroller- or wheelchair-accessible.", listOf("Cheonjeyeon", "Jeongbang Falls")))

            SectionHeader("Haenyeo Diving Culture")
            BodyText(boldNames("Jeju's haenyeo are female free-divers who harvest shellfish and seaweed by hand without scuba gear, a centuries-old tradition recognized by UNESCO as Intangible Cultural Heritage. You can sometimes see them working near Jeongbang Falls and other coastal spots, and learn more about the tradition at the Jeju Haenyeo Museum.", listOf("haenyeo", "Jeju Haenyeo Museum")))

            SectionHeader("Family-Friendly Options")
            BodyText(boldNames("The Jungmun resort area has family attractions including the Teddy Bear Museum and Pacific Land marine park, and Shinhwa World in Andeok combines theme-park rides with resort hotels — good options if traveling with kids.", listOf("Jungmun", "Teddy Bear Museum", "Pacific Land", "Shinhwa World")))
        }
    }
}
