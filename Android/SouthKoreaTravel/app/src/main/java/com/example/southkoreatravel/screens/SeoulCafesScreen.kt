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
            BodyText("%Arabica is a sleek, minimalist Kyoto-founded chain with branches at the Starfield Library/COEX Mall (Gangnam), Magazine B in the Sounds Hannam complex (Yongsan), and Shinsegae Gangnam. It serves espresso-based drinks, drip coffee, and a matcha latte.")
            BodyText("Anthracite Coffee Roasters (앤트러사이트) roasts its own beans and has locations in Hapjeong (the original, in a converted shoe factory), Seogyo, Hannam, Yeonhui, and Jeju. Both hand drip and espresso are on the menu; matcha shows up as a dessert flavor (a matcha tiramisu) rather than as a confirmed drink.")

            SectionHeader("Design-Forward Cafes")
            BodyText("Cafe Onion (어니언) is known as much for its striking renovated spaces — a 1920s hanok in Anguk, an old factory building in Seongsu — as for its coffee, with an in-house bakery famous for its scones. Matcha appears there as a pastry flavor rather than a confirmed espresso-based drink.")
            BodyText("Fritz Coffee Company (프릳츠), with locations in Dohwa-dong/Mapo (the original, a converted mansion), Wonseo, and Yangjae, is a serious roastery — they roast their own beans in Incheon — offering both drip and espresso. Their mascot is a seal, not a bird.")

            SectionHeader("More Seoul Coffee Worth Knowing")
            BodyText("Lowkey Coffee (로우키), with a well-known branch in Seongsu, is one of the early pillars of Seoul's third-wave coffee scene, known for creative espresso-based drinks. Coffee Libre in Yeonnam-dong sources direct-trade beans from over a hundred farms and keeps its menu minimal — espresso, filter coffee, and retail beans. Terarosa, with branches near Gwanghwamun and inside the POSCO Center, runs a large roastery-bakery-museum concept with an extensive drip coffee selection. Cafe Layered, in Bukchon and Yeonnam-dong, is best known for English-style scones alongside its coffee. Center Coffee, near Seoul Forest and in Samseong, is worth a stop for its unusual mugwort latte alongside standard espresso and hand drip. Blue Bottle Coffee Korea's Samcheong-dong branch, near Gyeongbokgung, offers drip and espresso along with a reservation-only tasting room.")
        }
    }
}
