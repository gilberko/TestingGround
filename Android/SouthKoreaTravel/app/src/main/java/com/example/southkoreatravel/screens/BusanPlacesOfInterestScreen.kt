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
fun BusanPlacesOfInterestScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Places Of Interest", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Gamcheon Culture Village")
            BodyText(boldNames("Gamcheon Culture Village (감천문화마을), in Saha-gu — nearest station Toseong (Line 1), then local bus 2 or 2-2 to the Gamcheon Elementary School stop, or about a 15-minute walk — is free to wander (an optional shuttle bus costs around ₩3,000), open roughly 9am-6pm. It's known for colorful terraced houses climbing the hillside, maze-like alleys, murals and small art installations, including a Little Prince statue that's a classic photo spot, plus panoramic sea and city views. Spring and fall are best for weather and photos.", listOf("Gamcheon Culture Village")))

            SectionHeader("Haedong Yonggungsa Temple")
            BodyText(boldNames("Haedong Yonggungsa Temple (해동용궁사), in Gijang-gun on the northeast coast — reachable from Haeundae Station (Line 2) Exit 7 via Bus 181 (about 45 minutes), or by taxi from Haeundae/Jangsan Station (about 20-25 minutes, roughly ₩8,000-10,000) — is free to visit, open roughly 5am-7pm. Founded in 1376 during the Goryeo Dynasty, it's a rare seaside temple (most Korean temples are mountain-set), with dramatic pavilions perched over crashing waves and considered one of Korea's three great sea-temples — a great spot for sunrise.", listOf("Haedong Yonggungsa Temple")))

            SectionHeader("Haeundae Beach")
            BodyText(boldNames("Haeundae Beach (해운대해수욕장), in Haeundae-gu — nearest station Haeundae (Line 2), Exit 3 or 5, about a 7-minute walk — is free and open to the public. At about 1.46km long, it's Korea's most famous beach, drawing over 10 million seasonal visitors, and hosts the Haeundae Sand Festival (Korea's largest sand-sculpture festival, held in late May/early June), the Busan Sea Festival, and a winter Haeundae Lights Festival.", listOf("Haeundae Beach", "Haeundae Sand Festival", "Busan Sea Festival", "Haeundae Lights Festival")))

            SectionHeader("Gwangalli Beach and Gwangan Bridge")
            BodyText(boldNames("Gwangalli Beach (광안리해수욕장), in Suyeong-gu, is free and lined with bars and cafes facing the Gwangan Bridge (also called the Diamond Bridge), which lights up nightly from sunset to midnight, with weekend/holiday shows sometimes synced to music. On most Saturdays, the Gwangalli \"M\" Drone Light Show adds two roughly 12-minute drone displays (around 1,100 drones, up to 3,000 on special occasions like Chuseok or New Year) at 8pm and 10pm in spring/summer, or 7pm and 9pm in fall/winter.", listOf("Gwangalli Beach", "Gwangan Bridge", "Diamond Bridge", "Gwangalli \"M\" Drone Light Show")))

            SectionHeader("Haeundae Blueline Park")
            BodyText(boldNames("Haeundae Blueline Park, accessed from Mipo Blueline Square near Haeundae Beach, runs along the old coastal rail line between Mipo and Songjeong. Its Sky Capsule is a driverless, glass-walled cabin for up to 4 people, priced roughly ₩40,000-50,000 one-way per cabin (pricing varies by source and season, so check current rates); a cheaper open-air Beach Train option also runs the same 4.8km scenic route, passing Dalmaji-gil, Daritdol Skywalk, and the cafes of Gudeokpo. Both run roughly 9am-9:30pm.", listOf("Haeundae Blueline Park", "Sky Capsule", "Beach Train", "Dalmaji-gil", "Daritdol Skywalk")))

            SectionHeader("Other Places Of Interest")
            BodyText(boldNames("Busan Tower and Yongdusan Park, in Nampo-dong (Jagalchi or Nampo Station, Line 1), offer a 120m tower with 360-degree harbor and city views set in a garden park, for roughly ₩4,000 (verify current pricing, as the tower was recently renovated and renamed). Jagalchi Fish Market, right on the Nampo-dong waterfront, is Korea's largest seafood market and a classic Busan food experience — live seafood downstairs, raw-fish restaurants upstairs, free to browse. Taejongdae, on Yeongdo Island (bus or taxi from the Nampo area), is a cliffside park with a lighthouse, ocean overlooks, and a small loop train (the Danubi). Songdo Skywalk, at Songdo Beach in Seo-gu, is a free 365m glass-floor walkway over the sea to Geobukseom (Turtle Island), paired with a cable car (paid, with glass-floor \"Air Cruise\" cars available at extra cost). BIFF Square, in Nampo-dong, is a 400m+ street of Korean street food named for the Busan International Film Festival, with film-history plaques and handprints along the way. Oryukdo Skywalk, in Nam-gu, is a free glass-floor platform 35-37m above the sea with views of the Oryukdo (\"Five-Six\") Islands. Igidae Coastal Walk, also in Nam-gu, is a free 4.7km, roughly 2.5-hour coastal hiking trail running from Yongho Byeolbit Park to the Oryukdo Skywalk, past volcanic rock formations, sea caves, and skyline views of the Gwangan Bridge.", listOf("Busan Tower", "Yongdusan Park", "Jagalchi Fish Market", "Taejongdae", "Songdo Skywalk", "BIFF Square", "Oryukdo Skywalk", "Igidae Coastal Walk")))
        }
    }
}
