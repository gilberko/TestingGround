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
fun GyeongjuWhereToStayScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Where To Stay", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Families")
            BodyText(boldNames("The Bomun Lake Resort district is the best fit — large resort hotels (including a family-friendly Hilton Gyeongju), the Gyeongju World amusement park, and a scenic lakeside setting to unwind in after temple-hopping.", listOf("Bomun Lake Resort", "Hilton Gyeongju", "Gyeongju World")))

            SectionHeader("Business Travelers")
            BodyText(boldNames("Gyeongju City Center, near Gyeongju Station, has the widest range of standard hotels, easy transit access, and nearby restaurants and cafes — the practical choice for a short work trip.", listOf("Gyeongju Station")))

            SectionHeader("Young Travelers")
            BodyText(boldNames("Dongbang-dong puts you within walking distance of the historic sites, cafes, and a livelier evening scene, and a hanok guesthouse stay (several are available near the old town) is a popular pick for travelers wanting a more atmospheric, independent visit.", listOf("Dongbang-dong")))

            SectionHeader("Areas To Avoid")
            BodyText("Current travel sources don't flag any specific district in Gyeongju as unsafe for tourists — it's generally described as a low-key, safe city built around its historic core, so there's no particular area you need to steer clear of.")
        }
    }
}
