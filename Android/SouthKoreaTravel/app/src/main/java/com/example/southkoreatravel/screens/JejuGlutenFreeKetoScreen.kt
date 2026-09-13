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
fun JejuGlutenFreeKetoScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Gluten Free and Keto Friendly", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            BodyText("Jeju doesn't have a well-documented list of certified gluten-free restaurants the way some mainland cities do, so treat this as general dietary guidance rather than a list of guaranteed celiac-safe venues — always confirm with staff before ordering.")

            SectionHeader("Gluten Free")
            BodyText(boldNames("Fresh seafood is a strong gluten-free option on Jeju — raw fish, grilled fish, and haenyeo-caught abalone or sea urchin are naturally gluten-free, though it's worth double-checking any dipping sauces. The main things to watch for are ssamjang and gochujang (both traditionally made with fermented wheat or barley) and soy-sauce-based marinades used in banchan side dishes — ask for meat grilled and served plain if you need to avoid these.", listOf("haenyeo", "ssamjang", "gochujang")))

            SectionHeader("Keto Friendly")
            BodyText(boldNames("Jeju black pork (heuk-dwaeji), grilled at restaurants along Black Pork Street near Dongmun Market in Jeju City, is a naturally low-carb choice when ordered without rice or noodles — the meat is grilled and wrapped in lettuce rather than served with batter or bread. Commonly recommended spots include Donsadon, Dombae Heuk-dwaeji, and Donhyanggi. Fresh seafood is equally keto-friendly for the same reason seafood works for gluten-free diners.", listOf("Black Pork Street", "Dongmun Market", "Jeju City", "Donsadon", "Dombae Heuk-dwaeji", "Donhyanggi")))
        }
    }
}
