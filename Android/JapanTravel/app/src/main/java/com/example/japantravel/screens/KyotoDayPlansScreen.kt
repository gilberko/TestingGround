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
fun KyotoDayPlansScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Recommended Day Plans", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            BodyText(
                "A 3-day plan grouped geographically so each day stays on one side of the " +
                    "city - eastern, western, then southern/central - rather than crossing back " +
                    "and forth across Kyoto."
            )

            SectionHeader("Day 1: Eastern Kyoto (Higashiyama)")
            BodyText(
                "One continuous north-to-south walking corridor along the eastern hills - the " +
                    "standard, most efficient way to see Higashiyama."
            )
            BodyText(
                "**Ginkaku-ji (Silver Pavilion)** - Start in the north at this understated Zen " +
                    "temple, known for its raked-gravel \"sea of silver sand\" garden rather than " +
                    "any actual silver."
            )
            BodyText(
                "**Philosopher's Path** - A quiet, canal-side walking path south from Ginkaku-ji, " +
                    "lined with cherry trees (spectacular in early April) leading toward Nanzen-ji."
            )
            BodyText(
                "**Nanzen-ji** - A major Zen temple complex with a striking brick aqueduct " +
                    "running through the grounds."
            )
            BodyText(
                "**Kiyomizu-dera** - Continue south to this famous wooden temple, built without " +
                    "nails, with a large veranda overlooking the city."
            )
            BodyText(
                "**Sannenzaka & Ninenzaka** - The preserved sloped lanes just below Kiyomizu-" +
                    "dera, lined with traditional shops and teahouses - one of the most photogenic " +
                    "streets in Kyoto."
            )
            BodyText(
                "**Yasaka Shrine & Gion** - Finish the day in Gion, Kyoto's famous geisha " +
                    "(geiko/maiko) district, with Yasaka Shrine at its entrance and Hanamikoji " +
                    "Street for an evening stroll."
            )

            SectionHeader("Day 2: Western Kyoto (Arashiyama & Kinkaku-ji)")
            BodyText(
                "Both of today's areas are on the western side of Kyoto, about 20 minutes apart " +
                    "by bus or train, so the whole day stays on one side of the city."
            )
            BodyText(
                "**Arashiyama Bamboo Grove** - A short, otherworldly walk through towering " +
                    "bamboo stalks - go early morning to avoid the crowds that build up by " +
                    "midday."
            )
            BodyText(
                "**Tenryu-ji Temple** - A UNESCO World Heritage temple right next to the bamboo " +
                    "grove, with a classic Japanese landscape garden built around a pond."
            )
            BodyText(
                "**Togetsukyo Bridge** - Arashiyama's landmark bridge over the Katsura River, " +
                    "with the wooded hills as a backdrop; a nice spot for lunch nearby."
            )
            BodyText(
                "**Kinkaku-ji (Golden Pavilion)** - In the afternoon, head to this gold leaf-" +
                    "covered pavilion reflected in its surrounding pond - probably Kyoto's most " +
                    "photographed sight."
            )
            BodyText(
                "**Ryoan-ji** - Just south of Kinkaku-ji, home to Japan's most famous Zen rock " +
                    "garden - fifteen stones arranged so that at least one is always hidden from " +
                    "view no matter where you stand."
            )

            SectionHeader("Day 3: Southern & Central Kyoto")
            BodyText(
                "**Fushimi Inari Taisha** - Start early (before 8am if possible) at this shrine " +
                    "famous for its thousands of vermilion torii gates climbing the mountainside - " +
                    "arriving early avoids both the crowds and the heat."
            )
            BodyText(
                "**Nijo Castle** - Mid-morning, head to this former shogun residence in central " +
                    "Kyoto, known for its \"nightingale floors\" that chirp underfoot as a built-in " +
                    "security measure against intruders."
            )
            BodyText(
                "**Nishiki Market** - A narrow covered market street in downtown Kyoto (Shijo-" +
                    "Karasuma area), good for an afternoon of grazing on street food and " +
                    "specialty shops - sometimes called \"Kyoto's Kitchen\"."
            )
            BodyText(
                "**Pontocho Alley** - Finish the trip with dinner in this narrow, lantern-lit alley " +
                    "of restaurants along the Kamo River, just next to Gion."
            )

            SectionHeader("A note on the routing")
            BodyText(
                "An earlier draft of this plan paired Kinkaku-ji with Fushimi Inari and Nijo " +
                    "Castle, but that would mean crossing the entire city (roughly 8-10km, " +
                    "opposite sides of Kyoto) in a single day. Grouping Kinkaku-ji with " +
                    "Arashiyama instead - both on the western side - keeps each day's travel " +
                    "much shorter."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
