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
fun GyeongjuCafesScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Cafes", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Cheongsudang")
            BodyText(boldNames("Often called the prettiest hanok cafe in Gyeongju, known for its fluffy souffle castella.", listOf("Cheongsudang")))

            SectionHeader("Cafe Sabaha")
            BodyText(boldNames("Overlooks Woljeonggyo Bridge with modern decor and indoor and outdoor seating — one of the most photogenic spots in the city.", listOf("Cafe Sabaha", "Woljeonggyo Bridge")))

            SectionHeader("Gabaehyangju")
            BodyText(boldNames("Frequently cited as the best straight-up coffee house in Gyeongju if you care more about the cup than the view.", listOf("Gabaehyangju")))

            SectionHeader("Caffe BEATO")
            BodyText(boldNames("A hidden-gem specialty cafe with a jazz-bar ambiance, known for a Cafe Royal (dripped coffee with sugar and brandy) and strong lattes.", listOf("Caffe BEATO", "Cafe Royal")))

            SectionHeader("Page9")
            BodyText(boldNames("Near the Gyeongju Colossal Coliseum, serving specialty coffee alongside desserts, sandwiches, pasta, and pizza.", listOf("Page9", "Gyeongju Colossal Coliseum")))
        }
    }
}
