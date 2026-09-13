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
fun GyeongjuGlutenFreeKetoScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Gluten Free and Keto Friendly", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            BodyText("Gyeongju has a much smaller documented gluten-free/keto scene than Seoul or Busan, so treat this as a starting point rather than a full list — always confirm with staff before ordering.")

            SectionHeader("Gluten Free")
            BodyText(boldNames("Dim Tao, a Chinese restaurant in the city, offers gluten-free options. Nerd, a local cafe, offers gluten-free bread. Beyond these, the same general caution applies as elsewhere in Korea: ssamjang, gochujang, and soy-sauce marinades are typically wheat-based, so ask for meat grilled and served plain if you need to avoid gluten.", listOf("Dim Tao", "Nerd", "ssamjang", "gochujang")))

            SectionHeader("Keto Friendly")
            BodyText(boldNames("No restaurant in Gyeongju specifically markets itself as keto, but the same logic as elsewhere in Korea applies — grilled meat (Korean BBQ/galbi) ordered without rice or noodles, or a bibimbap eaten around the rice, are naturally low-carb choices.", listOf("galbi", "bibimbap")))
        }
    }
}
