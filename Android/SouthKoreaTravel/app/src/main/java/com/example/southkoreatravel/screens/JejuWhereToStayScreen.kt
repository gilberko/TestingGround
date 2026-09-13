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
fun JejuWhereToStayScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Where To Stay", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Families")
            BodyText(boldNames("The Jungmun Tourist Complex, near Seogwipo on the south coast, is the go-to base for families — it's built around large international resort hotels with pools, close to the Teddy Bear Museum, Pacific Land, and Jungmun Beach. Shinhwa World in Andeok is another good option, combining a theme park with family resort stays.", listOf("Jungmun", "Seogwipo", "Teddy Bear Museum", "Pacific Land", "Shinhwa World")))

            SectionHeader("Business Travelers")
            BodyText(boldNames("Shin-Jeju (New Jeju) is the modern business district, about 10 minutes from Jeju International Airport, with business hotels, cafes, and easy access for short work trips without needing a rental car.", listOf("Shin-Jeju", "Jeju International Airport")))

            SectionHeader("Young Travelers and Nightlife")
            BodyText(boldNames("Jeju City (the older Gu-Jeju downtown) has the island's best concentration of restaurants, bars, and nightlife, plus easy airport access and markets like Dongmun Traditional Market and Black Pork Street nearby. Aewol, a trendy coastal town, is also popular with younger travelers for its cafes and sunset spots.", listOf("Jeju City", "Gu-Jeju", "Dongmun Traditional Market", "Black Pork Street", "Aewol")))

            SectionHeader("Areas To Avoid")
            BodyText("Jeju doesn't have a specific district with a reputation for being unsafe for tourists — travel guides consistently describe the island as broadly safe throughout. The only general cautions are the ordinary ones (poorly lit coastal roads at night, staying clear of working port/industrial areas), not any particular neighborhood. Note that as of 2025 local authorities have introduced fines for bad tourist behavior like littering and smoking in undesignated areas, so it's worth being a considerate guest rather than worrying about a no-go zone.")
        }
    }
}
