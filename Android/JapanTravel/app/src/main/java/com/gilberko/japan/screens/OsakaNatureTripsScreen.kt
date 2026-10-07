package com.gilberko.japan.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gilberko.japan.R

@Composable
fun OsakaNatureTripsScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Nature Trips", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Minoo (Minoh) Park")
            PlacePhoto(R.drawable.osaka_minoo_falls, "Minoo Falls", "Minoo Falls. Photo: Kanchi1979 / Wikimedia Commons, CC BY-SA 4.0")
            BodyText(
                "**Minoh or Minoo?** Both - they're the same place. The name (箕面) is romanized " +
                    "several ways: **Minoh**, **Minoo**, or **Minō**. The city government officially " +
                    "uses \"Minoh\", while Hankyu Railway's line and station are spelled \"Minoo\", so " +
                    "you'll see both on signs and maps. **Minoo Park** sits in the forested hills on " +
                    "the northern edge of the Osaka area and is one of the easiest real nature walks " +
                    "you can do from the city."
            )
            BodyText(
                "**What to see:** The main attraction is the **Minoo Falls**, a 33m waterfall at the " +
                    "end of a riverside path through the forest. Along the way you pass traditional " +
                    "shops and restaurants near the station, the small **Ryuan-ji** temple, and the " +
                    "**Minoo Park Insect Museum** (live beetles, water insects and a year-round " +
                    "butterfly greenhouse - a hit with kids). Don't miss **momiji tempura**, Minoh's " +
                    "local snack: maple leaves preserved in salt and then deep-fried in a sweet " +
                    "batter, sold at stalls along the path."
            )
            BodyText(
                "**Animals:** Yes - the park is known for its wild **Japanese macaques** (snow " +
                    "monkeys). They're used to people, so keep snacks and bags closed and never " +
                    "feed them - feeding the monkeys is prohibited, and fed monkeys learn to grab " +
                    "food from visitors. Deer can occasionally be seen too. There have also been " +
                    "occasional Asian black bear reports in the wider Minoh hills, generally away " +
                    "from the busy main path - don't wander off-trail at dusk."
            )
            BodyText(
                "**Best weather and season:** A dry, mild day. The most famous time is autumn, when " +
                    "the maples peak in roughly the second half of November - the most beautiful, " +
                    "but also the busiest. Spring and early summer greenery is lovely and quieter. " +
                    "Summer is hot and humid, though the path is mostly shaded. Avoid days of heavy " +
                    "rain: the path gets slippery and the waterfall turns brown and muddy."
            )
            BodyText(
                "**How far / getting there:** Close - about 25-30 minutes from central Osaka. From " +
                    "**Hankyu Osaka-Umeda** take the Hankyu Takarazuka Line to **Ishibashi " +
                    "Handai-mae** (~15 min), then change to the short Hankyu Minoo Line to the " +
                    "terminus, **Minoo** Station (~5 min). The trail starts right outside the station."
            )
            BodyText(
                "**Trail length and effort:** About 3km one way from the station to the falls, taking " +
                    "45-60 minutes at an easy pace (about 6km and 1.5-2 hours round trip, walking " +
                    "back the same way). The path is paved the whole way: the first half is almost " +
                    "flat past shops and houses, the second half is a gentle but steady uphill " +
                    "through the forest. No real climbing or scrambling - **difficulty: easy**."
            )
            BodyText(
                "**Children and older people:** Very suitable for both. The paved path works for " +
                    "children of all ages (strollers are manageable), and older visitors can walk " +
                    "at their own pace, rest on benches, and turn back at any point - the station " +
                    "end of the path is the flattest part, with plenty of cafes."
            )
            BodyText(
                "**Is it recommended?** Yes - it's a great half-day escape from the city, easy " +
                    "enough for the whole family, and especially rewarding in autumn."
            )
            BodyText(
                "**Do I need a vaccine?** No vaccine is required to enter Japan or to visit a park " +
                    "like this. Just make sure your routine vaccines (including tetanus) are up to " +
                    "date. The US CDC suggests the **Japanese encephalitis** vaccine only be " +
                    "considered for travelers spending long periods in rural areas, mainly June " +
                    "to September - it's not generally recommended for a short day walk like this " +
                    "one. **Tick-borne encephalitis** in Japan is mostly a Hokkaido concern. " +
                    "Practical tips: use insect repellent in summer, wear long trousers on forest " +
                    "trails, and check for ticks afterwards. For personal advice, ask a travel " +
                    "clinic before your trip."
            )
        }
    }
}
