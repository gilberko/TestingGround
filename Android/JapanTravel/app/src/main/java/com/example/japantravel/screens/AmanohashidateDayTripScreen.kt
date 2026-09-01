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
fun AmanohashidateDayTripScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Amanohashidate", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What is Amanohashidate")
            BodyText(
                "A 3.6km pine-covered sandbar across **Miyazu Bay** in northern Kyoto " +
                    "Prefecture. The name means \"bridge in heaven\" - from the right " +
                    "vantage points it looks like a walkway connecting the sea to the sky. " +
                    "It's one of the **Nihon Sankei** (\"Three Views of Japan\"), alongside " +
                    "**Miyajima** and **Matsushima**."
            )
            BodyText(
                "The site is famous for **matanozoki** - bending over and viewing the " +
                    "sandbar upside-down between your legs. The inverted view makes the " +
                    "pine-covered strip appear to float in the sky, which is where the " +
                    "\"bridge in heaven\" name comes from. Both viewpoints below have a " +
                    "dedicated matanozoki platform."
            )

            SectionHeader("Getting there from Kyoto")
            BodyText(
                "Direct: the JR Limited Express **Hashidate** runs straight from Kyoto " +
                    "Station to Amanohashidate Station, about 2 hours, with a mandatory seat " +
                    "reservation. One-way fare is roughly **¥4,800-5,400** depending on the " +
                    "day. There are only about 4 direct departures a day, so a day trip needs " +
                    "to be built around that timetable - check it before committing to an " +
                    "early start."
            )
            BodyText(
                "Alternative: the Limited Express **Kinosaki** from Kyoto to Fukuchiyama " +
                    "(75 minutes, roughly ¥3,050-3,650, runs hourly), then transfer to the " +
                    "Kyoto Tango Railway on to Amanohashidate (40-60 minutes, ¥800-1,750). " +
                    "Useful if the direct Hashidate train doesn't fit the schedule."
            )

            SectionHeader("What to do there")
            BodyText(
                "**View Land**, on the south side near the station and Chion-ji Temple: a " +
                    "chairlift or monorail up to a matanozoki viewpoint over the sandbar."
            )
            BodyText(
                "**Kasamatsu Park**, on the north side near Ichinomiya: a cable car or " +
                    "chairlift up to what's generally considered the better of the two " +
                    "matanozoki views."
            )
            BodyText(
                "Crossing the sandbar itself: walking takes 40-60 minutes, cycling around " +
                    "20 minutes. Bikes can be rented near either pier for about ¥500 for the " +
                    "first two hours plus ¥300 per additional hour, with one-way drop-off " +
                    "accepted at the opposite pier."
            )
            BodyText(
                "A **sightseeing boat** crosses Miyazu Bay between Amanohashidate Pier and " +
                    "Ichinomiya Pier in about 12 minutes - a good way to cover one direction " +
                    "while walking or cycling the other."
            )
            BodyText(
                "**Chion-ji Temple**, at the south end near the station, is dedicated to " +
                    "**Monju Bosatsu**, the Buddhist deity of wisdom. With more time, " +
                    "**Nariai-ji Temple** (further up the mountain past Kasamatsu Park), " +
                    "**Kono Shrine**, and the beach along the sandbar are also worth a look."
            )

            SectionHeader("Tickets and booking")
            BodyText(
                "Free: walking the sandbar, the temples and shrine, and the beach. Ticketed: " +
                    "the two viewpoint lifts, the boat, and bike rental. The combo " +
                    "**\"Two Fantastic Viewpoints\"** ticket covers View Land plus Kasamatsu " +
                    "Park entry for **¥1,800** adult / **¥900** child, valid across 2 days. " +
                    "The boat (Amanohashidate-Ichinomiya route) is **¥800** one-way or " +
                    "**¥1,300** round-trip for adults (about half price for children)."
            )
            BodyText(
                "None of these need advance reservation - only the Hashidate limited " +
                    "express train does, since seating is reserved-only."
            )

            SectionHeader("Example day-trip schedule")
            BodyText(
                "Catch the first direct Hashidate train from Kyoto (about 2 hours) -> " +
                    "arrive Amanohashidate mid-morning -> Chion-ji Temple -> View Land " +
                    "chairlift for the first matanozoki view -> rent a bike and cross the " +
                    "sandbar -> Kasamatsu Park cable car for the second matanozoki view -> " +
                    "sightseeing boat back across the bay -> lunch near the station (local " +
                    "seafood) -> free time on the beach or shopping -> Hashidate train back " +
                    "to Kyoto in the afternoon or early evening."
            )
            BodyText(
                "This is a long day - about 4 hours of round-trip train time alone - so " +
                    "it's best suited to travelers who have already covered central Kyoto."
            )

            SectionHeader("Estimated costs (per adult)")
            BodyText(
                "Round-trip train: roughly **¥9,600-10,800**. \"Two Fantastic Viewpoints\" " +
                    "combo ticket: **¥1,800**. Bike rental: roughly **¥800-1,100**. Boat " +
                    "one-way (if not cycling both directions): roughly **¥800**. Lunch: " +
                    "**¥1,500-2,500**. Total: roughly **¥14,000-17,000 per adult** for the day."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
