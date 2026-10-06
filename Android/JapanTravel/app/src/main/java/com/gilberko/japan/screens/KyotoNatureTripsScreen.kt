package com.gilberko.japan.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private const val VACCINE_NOTE =
    "**Do I need a vaccine?** No vaccine is required to enter Japan or to hike here. Just make " +
        "sure your routine vaccines (including tetanus) are up to date. The US CDC suggests the " +
        "**Japanese encephalitis** vaccine only be considered for travelers spending long periods " +
        "in rural areas, mainly June to September - it's not generally recommended for a short " +
        "day hike like this one. **Tick-borne encephalitis** in Japan is mostly a Hokkaido " +
        "concern. Practical tips: use insect repellent in summer, wear long trousers, and check " +
        "for ticks afterwards. For personal advice, ask a travel clinic before your trip."

@Composable
fun KyotoNatureTripsScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Nature Trips", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            BodyText(
                "Both hikes below are in the forested mountains on the edge of **Kyoto** city. " +
                    "**Bear caution (applies to both):** Asian black bear sightings in Kyoto's " +
                    "northern and western hills rose sharply from autumn 2025, and Kyoto City has " +
                    "put up multilingual \"beware of bears\" signs along trails. Attacks on hikers " +
                    "on these busy routes are rare, but: don't hike alone at dawn or dusk, make " +
                    "noise (many hikers carry a bell), keep food sealed, and if you see a bear, back " +
                    "away slowly without running."
            )

            SectionHeader("Takao → Kiyotaki")
            BodyText(
                "**What is it?** **Takao** is a small mountain village in northwest Kyoto (not to be " +
                    "confused with Tokyo's **Mount Takao**, which has its own section under Tokyo). " +
                    "The walk goes from Takao's temples down the **Kiyotaki River** valley to the " +
                    "hamlet of **Kiyotaki**, and can continue further down to **Hozukyo** and " +
                    "**Arashiyama**."
            )
            BodyText(
                "**What to see and do:** Three temples in Takao: **Jingo-ji** (about 1,200 years " +
                    "old, reached by a long stone stairway - at the top you can throw small clay " +
                    "discs, kawarake, off a cliff for good luck), the small **Saimyo-ji**, and " +
                    "**Kozan-ji**, part of Kyoto's UNESCO World Heritage listing and famous for the " +
                    "Chōjū-giga \"animal caricature\" scrolls. Then a peaceful riverside trail with " +
                    "clear water, rocks and red bridges, an optional side trip to the mossy " +
                    "**Kuya-no-taki** waterfall, and riverside tea houses in Kiyotaki."
            )
            BodyText(
                "**Animals:** Monkeys, deer and wild boar live in these mountains, and you may see " +
                    "herons and other birds along the river. Bears: sightings have been reported " +
                    "around the Kiyotaki River and Kozan-ji area (for example, a mother and cubs in " +
                    "April 2026) - see the bear caution above."
            )
            BodyText(
                "**Best weather and season:** Autumn is the famous peak - Takao is one of Kyoto's " +
                    "best maple spots, usually mid-to-late November (expect crowds on the buses). " +
                    "Spring greenery is lovely, and in summer the shaded river valley is noticeably " +
                    "cooler than the city. Avoid rainy days (the riverside rocks get slippery) and " +
                    "typhoon periods (roughly August-September), when the river can rise."
            )
            BodyText(
                "**How far / getting there:** About 45-55 minutes by **JR Bus** (Takao-Keihoku Line) " +
                    "from **Kyoto Station** to the **Yamashiro-Takao** bus stop; the same bus also " +
                    "stops at **JR Nijo Station** and other points in central/west Kyoto. Fares are " +
                    "a few hundred yen - check the current fare. To get back: from Kiyotaki take a " +
                    "bus back into the city, or continue walking to **JR Hozukyo Station** (one stop " +
                    "from Saga-Arashiyama) or on into **Arashiyama**."
            )
            BodyText(
                "**Trail length and effort:** Takao to Kiyotaki along the river is about 3-4km and " +
                    "1-1.5 hours of walking. The full Takao → Hozukyo hike is about 11km and takes " +
                    "4-6 hours including temple visits. The riverside path itself is fairly flat " +
                    "but it's a natural dirt path with rocks, roots and some steps, so wear proper " +
                    "shoes. The hardest part is the long stone stairway up to Jingo-ji (and back " +
                    "down). **Difficulty: easy to moderate.**"
            )
            BodyText(
                "**Children and older people:** Fine for school-age children who are used to " +
                    "walking, but not stroller-friendly. Older people who are steady on their feet " +
                    "can do the Takao → Kiyotaki section at a slow pace; the Jingo-ji stairs and " +
                    "uneven ground are the main challenge. Shorter option: visit only the Takao " +
                    "temples and take the bus back, skipping the riverside walk."
            )
            BodyText(
                "**Is it recommended?** Yes - it's one of the most beautiful easy nature walks near " +
                    "Kyoto, and much quieter than central sights like Arashiyama's bamboo grove " +
                    "(except in peak foliage season)."
            )
            BodyText(VACCINE_NOTE)

            SectionHeader("Kurama → Kibune (Kifune)")
            BodyText(
                "**Kibune or Kifune?** Both - same place. The village is usually written **Kibune**, " +
                    "while the shrine itself uses the older reading, **Kifune Shrine**. **Kurama** " +
                    "and **Kibune** are two small mountain villages in the hills north of Kyoto, " +
                    "joined by a forest trail over **Mount Kurama**."
            )
            BodyText(
                "**What to see and do:** **Kurama-dera**, a mountain temple linked to legends of " +
                    "tengu (mountain spirits), with a ¥500 entry fee that also covers the trail; " +
                    "the forest path over the mountain past giant cedars with exposed roots; " +
                    "**Kifune Shrine**, a shrine to the god of water known for its lantern-lined " +
                    "stairway and \"water fortune\" slips whose text appears when floated on " +
                    "water; and in summer, **kawadoko** - dining on wooden platforms built right " +
                    "over the Kibune river (roughly May to late September; usually pricey, " +
                    "reservations recommended). **Kurama Onsen**, a hot spring at the Kurama end, " +
                    "reopened in November 2024 after a long closure - a nice finish after the hike."
            )
            BodyText(
                "**Animals:** Forest wildlife including monkeys, deer and many birds. Bears: Mount " +
                    "Kurama is one of the Kyoto areas with the most recorded bear sightings - see " +
                    "the bear caution above. In late summer, also watch out for hornets, and don't " +
                    "linger near nests."
            )
            BodyText(
                "**Best weather and season:** Autumn colors (usually mid-to-late November) and " +
                    "fresh spring green are the most beautiful times. Summer is the kawadoko season " +
                    "and the valley is cooler than the city, though the climb is still sweaty. " +
                    "Winter snow is rare but very scenic. Avoid heavy rain - the stone steps and " +
                    "tree roots get slippery."
            )
            BodyText(
                "**How far / getting there:** About an hour from central Kyoto. Get to " +
                    "**Demachiyanagi** Station (Keihan Line terminus), then take the **Eizan " +
                    "Railway** Kurama Line to the end, **Kurama** Station (about 30 min, ~¥470). " +
                    "From Kibune, walk or take a short bus down to **Kibune-guchi** Station on the " +
                    "same Eizan line to return."
            )
            BodyText(
                "**Trail length and effort:** About 3.9km, taking 2-3 hours of walking (allow 5-6 " +
                    "hours to fully enjoy the temple, shrine and villages). It starts with a steady " +
                    "climb up stone steps from Kurama to the temple - a small cable car covers the " +
                    "lower part (around ¥200 one way), but it has had suspension periods, so check " +
                    "whether it's running. After the temple, the trail crosses the ridge on " +
                    "uneven, root-covered paths and then descends steeply into Kibune. " +
                    "**Difficulty: easy to moderate.** Go Kurama → Kibune: the reverse direction " +
                    "starts with a much steeper climb."
            )
            BodyText(
                "**Children and older people:** Fine for active children, but not for strollers. " +
                    "For older people the many steep steps and roots can be tiring - those less " +
                    "steady on their feet can skip the hike and visit each village separately by " +
                    "train (Kurama Station / Kibune-guchi Station plus the short bus to Kibune)."
            )
            BodyText(
                "**Is it recommended?** Yes - it's a classic half-day trip from Kyoto that combines " +
                    "a temple, a shrine, forest walking and riverside food in one easy outing."
            )
            BodyText(VACCINE_NOTE)
        }
    }
}
