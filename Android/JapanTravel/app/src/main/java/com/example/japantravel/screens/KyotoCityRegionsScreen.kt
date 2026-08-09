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
fun KyotoCityRegionsScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "City Regions", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Higashiyama")
            BodyText(
                "Historic foothills district east of the city center, capturing old Kyoto's feel. " +
                    "Includes Kiyomizu-dera Temple, and the preserved Ninenzaka and Sannenzaka " +
                    "slopes lined with shops and restaurants, plus Yasaka Shrine."
            )

            SectionHeader("Gion")
            BodyText(
                "Kyoto's famous geisha district. Hanamikoji-dori and Shirakawa's willow-lined " +
                    "canal are the best-known streets, with traditional teahouses where you might " +
                    "spot a geiko or maiko in the evening."
            )

            SectionHeader("Arashiyama")
            BodyText(
                "A nature-focused area in western Kyoto, known for the Arashiyama Bamboo Grove, " +
                    "the Togetsukyo Bridge over the Katsura River, and Tenryu-ji Temple (a UNESCO " +
                    "World Heritage site). Popular for cherry blossoms in spring and autumn leaves " +
                    "in fall."
            )

            SectionHeader("Fushimi")
            BodyText(
                "Home to Fushimi Inari-Taisha and its thousands of vermilion torii gates climbing " +
                    "the mountain, plus Tofuku-ji Temple nearby. Fushimi is also one of Japan's top " +
                    "sake-producing areas, with breweries offering tastings and tours."
            )

            SectionHeader("Kyoto Station area")
            BodyText(
                "The city's transit hub, with the striking modern Kyoto Station building, Kyoto " +
                    "Tower, and major department stores - a practical base with fast access " +
                    "everywhere else."
            )

            SectionHeader("Kinkaku-ji / Kitayama")
            BodyText(
                "Home to Kinkaku-ji, the Golden Pavilion, one of Kyoto's most iconic sights, set " +
                    "beside a reflective pond."
            )

            SectionHeader("Nishiki Market")
            BodyText(
                "A narrow, covered shopping street nicknamed \"Kyoto's Kitchen,\" packed with " +
                    "food stalls, produce, pickles, and street snacks - a good spot to sample " +
                    "local specialties."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
