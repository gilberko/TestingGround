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
fun KyotoGettingAroundScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Getting Around", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Subway (limited)")
            BodyText(
                "Unlike Tokyo or Osaka, Kyoto's subway is just 2 lines - the north-south Karasuma " +
                    "Line and the east-west Tozai Line, crossing at Karasuma-Oike. It covers " +
                    "central Kyoto but not most of the famous temple areas, so it's only part of " +
                    "the picture here."
            )

            SectionHeader("Buses (the main way to get around)")
            BodyText(
                "Because the subway is so limited, buses are the primary way most visitors reach " +
                    "Kyoto's temples and sights - the Kyoto City Bus network, plus buses run by " +
                    "Keihan Bus and JR West. An all-day bus pass is the standard, cost-effective " +
                    "approach if you're hopping between several temple areas in one day."
            )

            SectionHeader("Private railways")
            BodyText(
                "A few private railways fill in specific corridors faster than the bus: the " +
                    "Keihan Main Line runs along the east side of the city (useful for Gion and " +
                    "Fushimi Inari), the Hankyu Kyoto Line runs from central Kyoto out toward " +
                    "Katsura and Arashiyama (and continues on to Osaka/Kobe), and the Keifuku " +
                    "Electric Railroad - the Randen tram - is a slower but atmospheric option " +
                    "running street-level to Arashiyama."
            )

            SectionHeader("Taxis")
            BodyText(
                "Taxis are reliable and safe but noticeably pricier than buses or trains - most " +
                    "useful late at night, for awkward cross-town routes buses don't serve well, " +
                    "or when carrying heavy luggage."
            )

            SectionHeader("Main stations for popular areas")
            BodyText(
                "Kyoto Station: the central hub, where the Shinkansen, JR lines, the subway, and " +
                    "most bus routes all meet."
            )
            BodyText(
                "Gion-Shijo Station (Keihan) / Kawaramachi Station (Hankyu): for Gion, Pontocho, " +
                    "and the Higashiyama temple district - the two stations sit just across the " +
                    "Kamo River from each other."
            )
            BodyText(
                "Arashiyama Station (Hankyu, or the Randen tram): for the Bamboo Grove and " +
                    "Togetsukyo Bridge."
            )
            BodyText(
                "Inari Station (Keihan Main Line) or Fushimi Inari Station (JR Nara Line): both " +
                    "put you right at the entrance to Fushimi Inari-Taisha."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
