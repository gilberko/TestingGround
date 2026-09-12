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
fun BusanAttractionsScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Attractions", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Lotte World Adventure Busan")
            BodyText(boldNames("Lotte World Adventure Busan, in Gijang-gun's Osiria tourist complex, is a large theme park with 6 themed zones and roughly 17-31 rides depending on how you count shows and shops. Its headline rollercoaster is Giant Digger, a launched steel coaster reaching about 105km/h — there's no wooden coaster here, unlike Everland's T Express, despite that sometimes being assumed. Other thrill rides include Giant Splash (a steep water-splash drop ride) and Giant Swing, while a small water-play zone called \"Water Ground\" also sits on-site, separate from the much larger dedicated Gimhae Lotte Water Park nearby. Younger children are covered by kiddie rides like the Candy Train and Baby Pig Bumper Car with lower height gates, while thrill rides are gated higher. A one-day pass runs roughly adult ₩47,000 / teen (13-18) ₩39,000 / child (36 months-12) ₩33,000, with under-36-months free — check current pricing, since these figures come from third-party ticket resellers.", listOf("Lotte World Adventure Busan", "Giant Digger", "Giant Splash", "Giant Swing", "Water Ground", "Gimhae Lotte Water Park")))

            SectionHeader("Vaunce Universe Busan")
            BodyText(boldNames("Vaunce Universe Busan, an indoor trampoline and adventure-sports park in Yongho-dong, has no rollercoaster — it's trampolines, foam pits, a climbing zone, and a \"Sky Challenge\" ninja-style obstacle course, rather than a theme park. No dedicated water- or foam-slide feature was confirmed here. Children under age 3 or under 100cm aren't permitted for safety, and the Sky Challenge course specifically requires 120cm+. A standard 2-hour session runs roughly ₩25,000-26,000, including required grip socks and locker access; non-jumping spectators can watch for around ₩5,000.", listOf("Vaunce Universe Busan", "Sky Challenge")))

            SectionHeader("Gyeongju World")
            BodyText(boldNames("Gyeongju World is worth a clear flag: it isn't actually in Busan. It's in the Bomun Tourist Complex in Gyeongju, roughly 1-1.5 hours away, and is included here as a popular day trip rather than a Busan-city attraction. It's a large theme park billed as having the most rollercoasters of any park in Korea (30+ rides total), including Draken (a dive coaster with a 63m, 90-degree drop reaching about 117km/h), Phaeton (an inverted coaster), Mega Drop (a 70m gyro drop tower), and Sköll & Hati (Asia's first single-rail coaster). A seasonal water park section, California Beach, adds slide-style water rides including the Summerlin Splash, with a 20m drop. It skews toward a stronger thrill-ride mix than Lotte World Busan, though family-friendly rides exist too. A one-day pass runs roughly adult ₩48,000 / teen ₩42,000 / child ₩36,000.", listOf("Gyeongju World", "Draken", "Phaeton", "Mega Drop", "Sköll & Hati", "California Beach", "Summerlin Splash")))

            SectionHeader("Gimhae Lotte Water Park")
            BodyText(boldNames("Gimhae Lotte Water Park also comes with a location note: it's technically in neighboring Gimhae, not Busan proper, though it borders the city and sits close to Gimhae Airport, so it's commonly bundled into Busan travel plans given the proximity. It's a large, Polynesian-themed water park with roughly 17 types and 43 total attractions — named slides include the Tornado Slide, Double Swing Slide, Giant Boomerango, Rafting Slide, Jet Slide, and Racing Slide, plus an adults-only pool section for an extra fee. A \"Magic Pass\" (roughly ₩40,000, limited daily quantity) gives priority access to four of the six named slides. Standard adult day-admission pricing couldn't be reliably confirmed from available sources — only add-on passes were found — so treat any specific gate price as unconfirmed and worth checking directly before a trip.", listOf("Gimhae Lotte Water Park", "Tornado Slide", "Double Swing Slide", "Giant Boomerango", "Rafting Slide", "Jet Slide", "Racing Slide", "Magic Pass")))

            SectionHeader("Club D Oasis")
            BodyText(boldNames("Club D Oasis, at Paradise Hotel Busan in Haeundae, is a premium indoor/outdoor water-park-and-spa complex with infinity pools, a lazy river, water slides, and a full sauna/jjimjilbang side, all with coastal views. There's no rollercoaster here — it's a spa and water-park venue, not an amusement park. High-season pricing found: an All-Use Pass (7 hours) runs adult ₩79,000 / child ₩69,000; Water Park only (6 hours) adult ₩69,000 / child ₩59,000; Spa only (5 hours) adult ₩30,000 / child ₩24,000 — these are high-season figures and off-season pricing is likely cheaper.", listOf("Club D Oasis", "Paradise Hotel Busan")))
        }
    }
}
