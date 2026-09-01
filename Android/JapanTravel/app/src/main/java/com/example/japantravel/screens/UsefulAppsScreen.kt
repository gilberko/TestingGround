package com.example.japantravel.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UsefulAppsScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Useful Apps", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Klook")
            BodyText(
                "A travel-booking app covering attractions, tours, activities, and Japan Rail " +
                    "tickets and passes. Good for bundling sightseeing tickets and transport in " +
                    "one place, often with skip-the-line entry."
            )

            SectionHeader("Ticket Pia (ぴあ)")
            BodyText(
                "Japan's major entertainment-ticket platform, for concerts, sports, theater, and " +
                    "other live events. Similar services worth knowing about are e+ (eplus) and " +
                    "Lawson Ticket, which sell overlapping but not identical event listings."
            )

            SectionHeader("GO")
            BodyText(
                "Japan's most-used taxi-hailing app, with well over 35 million downloads and by " +
                    "far the largest share of taxi-app usage in the country. **GO** dispatches " +
                    "licensed taxis nationwide across all 47 prefectures, so it generally has the " +
                    "fastest pickup times and the best coverage, including smaller towns where " +
                    "other apps have little presence. The app supports English, and fares are " +
                    "metered and paid in-app by credit card, avoiding any language barrier with " +
                    "the driver."
            )

            SectionHeader("Uber")
            BodyText(
                "**Uber** does operate in Japan, but differently than in most countries: Japanese " +
                    "law bars private ride-share drivers, so the **Uber** app dispatches the same " +
                    "kind of licensed, metered taxis as **GO** rather than independent drivers. " +
                    "Coverage is strongest in central Tokyo (Shinjuku, Shibuya, Ginza, Roppongi), " +
                    "thinner in Osaka and Kyoto, and expanding to more cities. Its main advantage " +
                    "is familiarity — travelers who already use **Uber** at home can book with the " +
                    "same account and app without installing anything new."
            )
            BodyText(
                "**Bolt**, common in Europe, does not operate in Japan. Other apps worth knowing " +
                    "about are **S.RIDE** and **DiDi**, which work similarly to **GO** but have " +
                    "smaller coverage."
            )

            SectionHeader("Weather Apps")
            BodyText(
                "**tenki.jp** and the **Yahoo! Weather Japan** app both give detailed local " +
                    "forecasts. For official forecasts and typhoon warnings in English, the " +
                    "**Japan Meteorological Agency** (JMA, jma.go.jp) is the authoritative " +
                    "source - see the Weather section for more detail on all three."
            )

            SectionHeader("Public Transportation Apps")
            BodyText(
                "**Google Maps** is surprisingly reliable for Japanese public transport - JR " +
                    "lines, private railways, subways, and buses - with accurate timetables " +
                    "and clear transfer/platform instructions. **Japan Travel by NAVITIME** " +
                    "(English) offers door-to-door route search, voice guidance, and offline " +
                    "maps, and is especially useful for longer, multi-line intercity trips. " +
                    "**Japan Transit Planner** by Jorudan is a solid alternative to NAVITIME, " +
                    "with route search, fares, and platform numbers."
            )

            SectionHeader("Food Allergen Apps")
            BodyText(
                "The app's dedicated Japan food-scanning coverage - **Foodfit Japan**, which " +
                    "gives Safe/Caution/Avoid verdicts against dietary profiles like Vegan, " +
                    "Halal, and Gluten-Free on Japanese labels - is covered in the About Food " +
                    "section. General-purpose global barcode scanners such as **Yuka** also " +
                    "exist, but their coverage of Japanese products is thinner and less " +
                    "reliable than an app built specifically for Japan, so Foodfit Japan " +
                    "remains the better choice for reading supermarket labels here."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
