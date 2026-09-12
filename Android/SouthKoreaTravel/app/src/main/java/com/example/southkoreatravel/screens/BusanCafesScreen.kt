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
fun BusanCafesScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Cafes", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            BodyText("Busan has increasingly been described in travel press as a rising \"coffee capital\" of Korea, a reputation partly credited to Busan-connected champion baristas and the city's dense cluster of independent specialty roasters.")

            SectionHeader("Momos Coffee")
            BodyText(boldNames("Momos Coffee, with its flagship in Geumjeong-gu and several branches around the city, is Busan's most famous homegrown specialty roastery — it's been ranked among the World's 100 Best Coffee Shops, one of only two Korean cafes to make that list. Momos Yeongdo Roastery, in Yeongdo-gu, is a sleek, minimalist branch with its own on-site roastery.", listOf("Momos Coffee", "Momos Yeongdo Roastery")))

            SectionHeader("Established Specialty Roasters")
            BodyText(boldNames("Franklin Coffee Roasters, in Busanjin-gu, and WERK Roasters, based in Suyeong-gu with a notable multi-floor Jeonpo branch (a basement service bar with church-pew seating), are two of Busan's best-regarded specialty roasters, both well established in the local scene since the late 2010s.", listOf("Franklin Coffee Roasters", "WERK Roasters")))

            SectionHeader("Jeonpo Cafe Street")
            BodyText(boldNames("Jeonpo Cafe Street, near Seomyeon, is a named cafe-hub district worth exploring on foot. It's home to Blackup Coffee, opened in 2006 as one of Busan's first specialty cafes and still known for its low-acidity, cacao-forward house blends (Nero, Mono, and a rotating seasonal Ego blend), and Naive Brewers, a tiny, living-room-like independent space nearby.", listOf("Jeonpo Cafe Street", "Blackup Coffee", "Naive Brewers")))

            SectionHeader("Gwangalli Coffee Cluster")
            BodyText(boldNames("The area around Gwangalli Beach has become a noted specialty-coffee cluster: Hytte Roastery (with a Gwangalli branch among others) is known for a light-roast, single-origin-forward style in cozy, home-like interiors; Oas Roasters is known for a broad bean selection and creative desserts; and Berg Roasters, sometimes called a Gwangalli \"specialty coffee mecca\" in travel coverage, rounds out the area.", listOf("Hytte Roastery", "Oas Roasters", "Berg Roasters")))

            SectionHeader("Haeundae")
            BodyText(boldNames("RBH Coffee, in Haeundae, roasts its own beans on-site and is active in Busan's local coffee community, including running its own barista classes and seminars.", listOf("RBH Coffee")))
        }
    }
}
