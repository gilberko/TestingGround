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
fun SeoulAttractionsScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Attractions", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Lotte World")
            BodyText(boldNames("Lotte World is a large entertainment complex in Jamsil, Songpa-gu — nearest station Jamsil (Line 2 or 8), Exit 4. One ticket covers two halves: Lotte World Adventure, an indoor theme park (recognized as the world's largest) with around 40 rides, an ice rink, the Magic Castle photo/play centerpiece, and parades; and Magic Island, an outdoor park on a small island in Seokchon Lake with most of the thrill-ride coasters. A small Lotte World Folk Museum also sits inside the complex. Full-day passes run roughly ₩38,800-59,000 depending on whether you buy at the gate or discounted online through sites like Klook, Trazy, or Trip.com (prices change often, so check current rates before you go); infants under 3 get in free. It suits the whole family — gentle kiddie rides alongside major thrill coasters. Note that the adjacent Lotte World Mall, Lotte World Aquarium, and Lotte World Tower are separate, separately-ticketed attractions in the same complex.", listOf("Lotte World", "Lotte World Adventure", "Magic Castle", "Magic Island", "Lotte World Folk Museum", "Lotte World Mall", "Lotte World Aquarium", "Lotte World Tower")))

            SectionHeader("Lotte World Aquarium")
            BodyText(boldNames("Lotte World Aquarium sits inside Lotte World Mall in the same Jamsil complex (combo tickets with the theme park exist), with tickets running roughly ₩29,000-35,000. It houses sharks, otters, and an Arctic-themed beluga whale exhibit. Worth knowing: the beluga's situation has been a genuinely unresolved, controversial story — a promised transfer to an open-water sanctuary has been delayed multiple times, so as of the last check she remains on exhibit rather than released; treat this as an ongoing, contested situation rather than a settled one either way.", listOf("Lotte World Aquarium", "Lotte World Mall")))

            SectionHeader("SEA LIFE Coex Aquarium")
            BodyText(boldNames("SEA LIFE Coex Aquarium is inside COEX Mall in Samseong-dong, Gangnam — nearest station Samseong (Line 2) — and is a completely separate aquarium, under different ownership, from Lotte World Aquarium across town in Jamsil. Tickets run roughly ₩28,000-35,000, cheaper booked online in advance. Inside are sharks, penguins, jellyfish, and touch pools, making it well suited to younger children.", listOf("SEA LIFE Coex Aquarium", "COEX Mall", "Lotte World Aquarium")))

            SectionHeader("Science Museums")
            BodyText(boldNames("Seoul has two similarly-named but distinct science museums for kids, worth telling apart: the National Children's Science Museum (국립어린이과학관) in Jongno-gu (nearest station Hyehwa, Line 4) is a national museum built specifically for children, with tickets around ₩1,000-2,000 (closed Mondays); the Seoul Science Center (서울시립과학관) in Nowon-gu (nearest station Hagye, Line 7) is Seoul's own municipal — not national — science museum, in a different part of the city entirely.", listOf("National Children's Science Museum", "Seoul Science Center")))
            BodyText(boldNames("The Seodaemun Museum of Natural History, in Seodaemun-gu, covers dinosaurs and natural history dioramas for roughly ₩1,000-3,000 depending on age. It has no subway station directly next to it — the closest are Sinchon (Line 2, then local bus 03) or Hongje (Line 3, then bus 7738 or 7739), both requiring a short bus ride up the hill it sits on.", listOf("Seodaemun Museum of Natural History")))
            BodyText(boldNames("The Gwacheon National Science Museum is the big one worth the extra trip — but it's important to know it isn't actually in Seoul; it's in neighboring Gwacheon, Gyeonggi-do, just south of the city. Take Line 4 to Seoul Grand Park Station, then it's roughly a 10-15 minute walk or a short shuttle ride — figure on about 45-60 minutes door-to-door from central Seoul. It's a genuine \"science village\": a natural history hall with large dinosaur skeletons, an outdoor dinosaur park, a planetarium, and an observatory.", listOf("Gwacheon National Science Museum")))

            SectionHeader("Indoor Water Parks")
            BodyText(boldNames("Woongjin Playdoci (Woongjin Play City), in Bucheon, Gyeonggi-do, is roughly 30-50 minutes from Seoul — take Bugae Station (Line 1), Exit 2, then Bus 79, or Samsan Gymnasium Station (Line 7), Exit 1, then about a 650m walk. Its water park, Waterdoci, is billed as Korea's largest indoor water park, sharing the complex with an indoor ski slope (Snowdoci) and a golf range (Golfdoci). Slides include the Zebra Slide (a 12m drop into a dark spiral tunnel) and the Space Ball Slide (a tunnel slide spiraling into a vortex chamber), plus dedicated kids' slides in the Dolphin and Splash kids zones. Children under 36 months get in free; full-day adult admission runs roughly ₩60,000-75,000 depending on the season (low/high/gold pricing tiers), so check current rates before you go.", listOf("Woongjin Playdoci", "Woongjin Play City", "Waterdoci")))
            BodyText(boldNames("SeaLaLa Water Park, in Yeongdeungpo-gu (Yeongdeungpo Station, Line 1, Exit 3), is inside Seoul itself, so no long trip is needed. It's a Mediterranean-themed water park combined with a jjimjilbang sauna in the same building, with a wave pool, lazy river, high-speed slides, and a dedicated \"Aqua Kid's Land\" for younger children. Full-day admission runs roughly ₩50,000 for adults and ₩40,000 for children (12 and under); sauna-only tickets are cheaper, around ₩10,000, if you just want the spa side.", listOf("SeaLaLa Water Park", "Aqua Kid's Land")))
            BodyText(boldNames("Water Kingdom & Spa, in Songpa-gu (Jangji Station, Line 8, Exit 4), is worth a correction: it's Park Habio Water Kingdom & Spa, operated by Daham Habio — not a Hanwha property, despite that sometimes being assumed. It's an indoor water park plus jjimjil spa, sold as separate or combo tickets, with kids' mini-slides and water play features alongside an adult \"Swift Blast\" body/tube slide that requires 120cm+ height. Pricing is seasonal and varies by source: roughly ₩65,000-75,000 for the water park, with the spa priced separately at around ₩18,000-20,000 on weekdays and a bit more on weekends.", listOf("Water Kingdom & Spa", "Park Habio Water Kingdom & Spa")))
            BodyText(boldNames("Aquafield Hanam, on the 3rd floor (and rooftop) of Starfield Hanam mall in Gyeonggi-do, is about an hour from Seoul via Line 5 to Hanam Geomdansan Station, Exit 1 (about a 10-minute walk), or bus 9302/9303 from Jamsil Station. It combines an indoor and rooftop outdoor water park — including Korea's longest infinity pool, with Han River views — with a themed jjimjil spa (cloud, ocher, cypress, and salt rooms). Rather than a single all-day ticket, it's sold as multi-hour passes: a water-park-or-spa pass (up to 6 hours) or a \"Multi Pass\" covering both (up to 9 hours), with an hourly overage fee beyond that. It has open- and tunnel-type slides plus a toddler play area; children under 36 months are free, and under-10s need a guardian. Pricing varies widely by source and season, roughly ₩45,000-55,000 for an adult pass — treat this as approximate and check current rates.", listOf("Aquafield Hanam", "Starfield Hanam")))
            BodyText("Korean water park pricing shifts by season (low, high, and gold tiers, roughly corresponding to off-peak, summer, and peak summer), so treat all the figures above as a guide rather than a fixed price, and check each park's official site closer to your visit.")
        }
    }
}
