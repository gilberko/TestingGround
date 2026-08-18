package com.example.japantravel.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TokyoDayPlansScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Recommended Day Plans", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            BodyText(
                "A 6-day plan covering Tokyo proper, with each day kept to one area of the " +
                    "city to minimize crossing town. This is separate from **Recommended Day " +
                    "Trips**, which covers places outside Tokyo like Hakone and Nikko."
            )

            SectionHeader("Day 1: Asakusa & Ueno")
            BodyText(
                "**Senso-ji & Nakamise Street** - Tokyo's oldest temple, reached through the " +
                    "Kaminarimon (\"Thunder Gate\") and a shopping street selling traditional " +
                    "snacks and souvenirs."
            )
            BodyText(
                "**Tokyo Skytree view** - Asakusa has some of the best photo spots looking " +
                    "across the Sumida River toward the Skytree."
            )
            BodyText(
                "**Ueno Park** - A short trip away (Ginza Line, about 5 minutes), home to Ueno " +
                    "Zoo, the Tokyo National Museum, and several other museums clustered " +
                    "together."
            )
            BodyText(
                "**Ameyoko Market** - A lively market street just outside Ueno Station, good for " +
                    "street food and bargain shopping to close out the day."
            )

            SectionHeader("Day 2: Shibuya, Harajuku & Omotesando")
            BodyText(
                "**Meiji Shrine** - Start in the forested grounds of this shrine dedicated to " +
                    "Emperor Meiji, right next to Harajuku Station."
            )
            BodyText(
                "**Takeshita Street** - Harajuku's famously colorful, crowded street for youth " +
                    "fashion and crepes."
            )
            BodyText(
                "**Omotesando** - A short walk away, a tree-lined avenue of upscale flagship " +
                    "stores and architecture - a calmer contrast to Takeshita Street."
            )
            BodyText(
                "**Shibuya Crossing & Shibuya Sky** - Finish at the world's busiest pedestrian " +
                    "crossing, then head up Shibuya Sky's rooftop observation deck for sunset " +
                    "views."
            )

            SectionHeader("Day 3: Shinjuku")
            BodyText(
                "**Shinjuku Gyoen** - A large, peaceful national garden that mixes Japanese, " +
                    "French, and English landscaping styles - a good contrast to Shinjuku's " +
                    "otherwise frantic pace."
            )
            BodyText(
                "**Tokyo Metropolitan Government Building** - Free observation decks near the " +
                    "top of this twin-towered skyscraper, one of the best views in the city for " +
                    "no cost."
            )
            BodyText(
                "**Kabukicho & Omoide Yokocho** - In the evening, wander Kabukicho's neon-lit " +
                    "streets and the tiny yakitori alleys of Omoide Yokocho (\"Memory Lane\")."
            )
            BodyText(
                "**Golden Gai** - A cluster of over 200 tiny, closet-sized bars packed into a few " +
                    "narrow alleys - worth a look even just for the atmosphere."
            )

            SectionHeader("Day 4: Imperial Palace, Marunouchi, Ginza & Tsukiji")
            BodyText(
                "**Imperial Palace East Gardens** - Free public gardens on the grounds of the " +
                    "former Edo Castle, in the heart of the city."
            )
            BodyText(
                "**Marunouchi & Tokyo Station** - The business district around Tokyo Station, " +
                    "including the restored red-brick station building itself."
            )
            BodyText(
                "**Ginza** - Tokyo's upscale shopping district, with flagship department stores " +
                    "and, on weekend afternoons, a pedestrian-only main street."
            )
            BodyText(
                "**Tsukiji Outer Market** - Even though the wholesale fish auctions moved to " +
                    "Toyosu, the outer market at Tsukiji is still packed with seafood stalls and " +
                    "street food."
            )

            SectionHeader("Day 5: Akihabara & Ryogoku")
            BodyText(
                "**Akihabara** - Tokyo's electronics-and-anime district, with multi-floor " +
                    "electronics stores, arcades, and manga/anime shops."
            )
            BodyText(
                "**Ryogoku** - A short trip east into Tokyo's traditional sumo district. Visit the " +
                    "**Edo-Tokyo Museum** (Edo-period city history) and the **Sumida Hokusai " +
                    "Museum**, and check whether a tournament is on at Ryogoku Kokugikan."
            )

            SectionHeader("Day 6: Bay Area (Toyosu & Odaiba)")
            BodyText(
                "**Toyosu Market** - Start early for a fresh seafood or sushi breakfast at the " +
                    "market that took over Tsukiji's wholesale fish auctions, with a public " +
                    "observation deck overlooking the auction floor."
            )
            BodyText(
                "**Odaiba** - Spend the rest of the day on this artificial island in Tokyo Bay: " +
                    "**teamLab Planets** or **teamLab Borderless** for immersive digital art (see " +
                    "the Parks and Attractions section for details), views of the Rainbow Bridge, " +
                    "the life-size Gundam statue at DiverCity, and the shopping malls along the " +
                    "waterfront - a good spot to watch the sunset and evening skyline before " +
                    "heading back."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
