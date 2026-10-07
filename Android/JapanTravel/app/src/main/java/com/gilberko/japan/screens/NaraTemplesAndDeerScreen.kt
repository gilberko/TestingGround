package com.gilberko.japan.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gilberko.japan.R

@Composable
fun NaraTemplesAndDeerScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Nara Park & Temples", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("The deer")
            PlacePhoto(R.drawable.nara_deer, "Deer at Kasuga Taisha", "A deer among the stone lanterns of Kasuga Taisha. Photo: Zairon / Wikimedia Commons, CC BY 4.0")
            BodyText(
                "Yes - **Nara Park** is home to around 1,200 free-roaming sika deer, considered " +
                    "sacred messengers of the gods in Shinto belief. They wander freely among " +
                    "visitors and will bow for food. Official shika-senbei deer crackers are sold " +
                    "by licensed vendors around the park for about ¥200 a pack - keep crackers " +
                    "out of sight until you're ready to feed them, and show empty hands afterward " +
                    "so the deer don't nudge or nip for more. Entry to the park itself is free."
            )

            SectionHeader("Todai-ji")
            PlacePhoto(R.drawable.nara_todaiji, "Todai-ji Great Buddha Hall", "Photo: Cun Cun / Wikimedia Commons, CC BY-SA 4.0")
            BodyText(
                "Home to Japan's **Great Buddha**, a roughly 15-metre bronze statue housed inside the " +
                    "**Daibutsuden**, the main hall and one of the world's largest wooden buildings. " +
                    "Entering the hall costs about ¥800."
            )

            SectionHeader("Kasuga Taisha")
            PlacePhoto(R.drawable.nara_kasuga_taisha, "Kasuga Taisha lanterns", "Photo: Gzzz / Wikimedia Commons, CC BY-SA 4.0")
            BodyText(
                "A Shinto shrine reached via paths lined with roughly 2,000 stone lanterns, with " +
                    "around 1,000 more bronze lanterns hanging inside the shrine itself."
            )

            SectionHeader("Kofuku-ji")
            PlacePhoto(R.drawable.nara_kofukuji, "Kofuku-ji five-story pagoda", "Photo: Zairon / Wikimedia Commons, CC BY 4.0")
            BodyText(
                "A temple complex known for its five-story pagoda, one of the tallest wooden " +
                    "pagodas in Japan and a symbol of **Nara**."
            )

            SectionHeader("Planning your visit")
            BodyText(
                "Budget roughly 3-4 hours to comfortably walk the full loop from the park through " +
                    "Todai-ji to Kasuga Taisha and Kofuku-ji. Spring (March-May) and autumn " +
                    "(October-November) are especially recommended for the scenery."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
