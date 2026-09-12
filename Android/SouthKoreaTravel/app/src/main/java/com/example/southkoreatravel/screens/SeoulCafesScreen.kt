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
fun SeoulCafesScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Cafes", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Specialty Coffee Chains")
            BodyText(boldNames("%Arabica is a sleek, minimalist Kyoto-founded chain with branches at the Starfield Library/COEX Mall (Gangnam), Magazine B in the Sounds Hannam complex (Yongsan), and Shinsegae Gangnam. It serves espresso-based drinks, drip coffee, and a matcha latte.", listOf("%Arabica")))
            BodyText(boldNames("Anthracite Coffee Roasters (앤트러사이트) roasts its own beans and has locations in Hapjeong (the original, in a converted shoe factory), Seogyo, Hannam, Yeonhui, and Jeju. Both hand drip and espresso are on the menu; matcha shows up as a dessert flavor (a matcha tiramisu) rather than as a confirmed drink.", listOf("Anthracite Coffee Roasters")))

            SectionHeader("Design-Forward Cafes")
            BodyText(boldNames("Cafe Onion (어니언) is known as much for its striking renovated spaces — a 1920s hanok in Anguk, an old factory building in Seongsu — as for its coffee, with an in-house bakery famous for its scones. Matcha appears there as a pastry flavor rather than a confirmed espresso-based drink.", listOf("Cafe Onion")))
            BodyText(boldNames("Fritz Coffee Company (프릳츠), with locations in Dohwa-dong/Mapo (the original, a converted mansion), Wonseo, and Yangjae, is a serious roastery — they roast their own beans in Incheon — offering both drip and espresso. Their mascot is a seal, not a bird.", listOf("Fritz Coffee Company")))

            SectionHeader("More Seoul Coffee Worth Knowing")
            BodyText(boldNames("Lowkey Coffee (로우키), with a well-known branch in Seongsu, is one of the early pillars of Seoul's third-wave coffee scene, known for creative espresso-based drinks. Coffee Libre in Yeonnam-dong sources direct-trade beans from over a hundred farms and keeps its menu minimal — espresso, filter coffee, and retail beans. Terarosa, with branches near Gwanghwamun and inside the POSCO Center, runs a large roastery-bakery-museum concept with an extensive drip coffee selection. Cafe Layered, in Bukchon and Yeonnam-dong, is best known for English-style scones alongside its coffee. Center Coffee, near Seoul Forest and in Samseong, is worth a stop for its unusual mugwort latte alongside standard espresso and hand drip. Blue Bottle Coffee Korea's Samcheong-dong branch, near Gyeongbokgung, offers drip and espresso along with a reservation-only tasting room.", listOf("Lowkey Coffee", "Coffee Libre", "Terarosa", "Cafe Layered", "Center Coffee", "Blue Bottle Coffee Korea")))

            SectionHeader("More Specialty Cafes")
            BodyText(boldNames("Alegria Coffee Roasters, with branches in Gwanghwamun and Pangyo, is also sometimes abbreviated \"ACR\" — worth noting it's a completely different cafe from Anthracite Coffee Roasters above, which shares the same abbreviation. Open since 2011, it offers hand-drip single-origin coffee, espresso, a full bakery line, and affogato including a matcha version.", listOf("Alegria Coffee Roasters", "Anthracite Coffee Roasters")))
            BodyText(boldNames("Coffee Montage (커피 몽타주), based in Seongnae-dong, Gangdong-gu, with a roasting facility in nearby Hanam, is a Seoul-area roaster (not Gangneung-based, despite that city's coffee-town reputation) known for named espresso blends, single-origin beans, cold brew, and drip bags, supplying beans to roughly 150 cafes nationwide.", listOf("Coffee Montage")))
            BodyText(boldNames("Cafe Pokpo (카페 폭포, \"waterfall cafe\"), in Seodaemun-gu near Hongje Station (Line 3, Exit 4), sits right beside the man-made Hongjecheon waterfall installation, with outdoor riverside seating — especially popular during cherry blossom season.", listOf("Cafe Pokpo")))
            BodyText(boldNames("Hell Cafe Roasters (헬카페 로스터즈), in Bogwang-dong, Yongsan-gu, was founded in 2013 by an espresso specialist and a hand-drip specialist. The striking name was chosen deliberately to be unforgettable, earning it the local nickname \"Hell Tea House\" — signature drinks include the Hell Drip and Hell Latte.", listOf("Hell Cafe Roasters", "Hell Tea House", "Hell Drip", "Hell Latte")))
            BodyText(boldNames("Mesh Coffee, in Seongsu-dong, has no indoor seating and focuses on pour-over coffee alongside a signature coffee shake. Felt Coffee (펠트커피), with branches including Gwanghwamun and Dosan Park, is known for its minimalist space and service-focused baristas — these are two separate cafes, not one combined venue.", listOf("Mesh Coffee", "Felt Coffee")))
            BodyText(boldNames("A few more worth seeking out: Namusairo, near Gwanghwamun in a renovated hanok, opened in 2002 as one of Seoul's first specialty cafes and is known for its Okinawa Brown Sugar Cappuccino; Manufact Coffee, with a flagship in Yeonhui-dong plus a Seochon branch, for clean, modern-style brews; C.Through Cafe in Yeonnam-dong, known for hand-drawn \"Cream Art\" piped onto espresso drinks; Cafe Knotted, Korea's best-known cream-donut bakery-cafe brand with branches across the city; and NUDAKE Haus Dosan, an avant-garde dessert cafe inside the Gentle Monster building in Apgujeong, known for its matcha-filled \"Peak Cake.\"", listOf("Namusairo", "Manufact Coffee", "C.Through Cafe", "Cafe Knotted", "NUDAKE Haus Dosan")))
        }
    }
}
