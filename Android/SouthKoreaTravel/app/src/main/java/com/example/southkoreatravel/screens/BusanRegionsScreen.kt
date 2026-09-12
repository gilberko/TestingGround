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
fun BusanRegionsScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Regions", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Haeundae")
            BodyText(boldNames("Haeundae (해운대), Busan's flagship beach district, is a skyline of luxury high-rises and hotels facing Korea's most famous urban beach. It's home to big shopping malls like Shinsegae Centum City, the Haeundae Blueline Park Sky Capsule and beach train, the SEA LIFE Busan Aquarium, and Dongbaek Island. Polished and resort-like, it's the postcard image of Busan.", listOf("Haeundae", "Shinsegae Centum City", "Haeundae Blueline Park", "SEA LIFE Busan Aquarium", "Dongbaek Island")))

            SectionHeader("Gwangalli / Gwangan")
            BodyText(boldNames("Gwangalli (광안리), one subway stop from Haeundae, has a more local, laid-back feel — a beachfront strip of bars, cafes, and seafood restaurants facing the nightly-illuminated Gwangan Bridge (also called the Diamond Bridge). It's popular for evening strolls and watching the bridge lights over a drink.", listOf("Gwangalli", "Gwangan Bridge", "Diamond Bridge")))

            SectionHeader("Nampo-dong / Jagalchi")
            BodyText(boldNames("Nampo-dong and Jagalchi (남포동/자갈치) form Busan's old downtown core — dense, historic, and market-driven. It's anchored by Jagalchi Fish Market, BIFF Square, Gukje Market, and Yongdusan Park with Busan Tower. This is the best area for street food, traditional markets, and old-town atmosphere.", listOf("Nampo-dong", "Jagalchi", "Jagalchi Fish Market", "BIFF Square", "Gukje Market", "Yongdusan Park", "Busan Tower")))

            SectionHeader("Seomyeon")
            BodyText(boldNames("Seomyeon (서면) is Busan's modern commercial and nightlife hub, often described as where locals actually hang out rather than tourists. It's dense with shopping streets, underground malls, restaurants, and bars, and sits at the interchange of Subway Line 1 and Line 2, making it a major transit and business hub as well.", listOf("Seomyeon")))

            SectionHeader("Gamcheon Culture Village")
            BodyText(boldNames("Gamcheon Culture Village (감천문화마을), in Saha-gu, is a former hillside slum reborn as a pastel-colored art village of stacked houses, narrow alleys, murals, and small galleries and cafes — visually often compared to Santorini. It's more a single distinctive attraction than a broad district, but distinct enough to be worth knowing as its own area.", listOf("Gamcheon Culture Village")))

            SectionHeader("Songjeong")
            BodyText(boldNames("Songjeong (송정), Busan's northernmost major beach, is quieter and more laid-back than Haeundae — a favored spot for surfing and relaxing, connected to Haeundae by the Blueline Park beach train.", listOf("Songjeong", "Haeundae", "Blueline Park")))

            SectionHeader("Dongnae")
            BodyText(boldNames("Dongnae (동래), an inland district about 7 minutes by subway from Seomyeon, is historically significant as the site of the old Dongnae fortress and hot springs. It has a more residential, local feel and is known for hot spring bathhouses and traditional fortress history — less touristy than the coastal districts.", listOf("Dongnae", "Seomyeon")))
        }
    }
}
