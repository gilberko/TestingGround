package com.example.japantravel.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TokyoGlutenFreeKetoScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Gluten Free and Keto Friendly", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            BodyText(
                "Availability and menus change fairly often, so it's worth confirming current " +
                    "details before visiting any specific spot below."
            )

            SectionHeader("Gluten-free")
            BodyText(
                "T's Kitchen (Roppongi and Ueno) is already covered in Food and Cafes and About " +
                    "Food - a 100% gluten-free restaurant certified by the Gluten Intolerance " +
                    "Group, the first such certification in Asia."
            )
            BodyText(
                "GEN-TEN and RICEHACK, both in Shibuya, are dedicated rice-flour gluten-free " +
                    "bakeries - a good option for bread, pastries, and sandwiches without hunting " +
                    "through a mixed menu."
            )
            BodyText(
                "Otsuna Sushi can put together a gluten-free omakase if you ask in advance - worth " +
                    "knowing if a high-end sushi experience is on your list and you don't want to " +
                    "settle for conveyor-belt safety."
            )

            SectionHeader("Keto-friendly")
            BodyText(
                "Yakiniku (grill-it-yourself Japanese BBQ) and shabu-shabu (thin-sliced meat " +
                    "swished through broth) are naturally keto-friendly formats - just go easy on " +
                    "sweet dipping sauces and stick to soy sauce. Kollabo, a Korean-BBQ/yakiniku " +
                    "hybrid with locations in Shibuya, Shinjuku, and Kichijoji, works well for " +
                    "this. Gyu-Kaku is a nationwide all-you-can-eat yakiniku chain, and Mo Mo " +
                    "Paradise is a shabu-shabu chain with an English menu."
            )
            BodyText(
                "At conveyor-belt sushi chains (see Food and Cafes), ordering sashimi and " +
                    "nigiri-without-rice plates keeps things low-carb while still letting you eat " +
                    "at a normal tourist-friendly spot. Tsukiji Outer Market's grilled seafood and " +
                    "skewer stalls (see City Regions - Fish markets) are another naturally " +
                    "low-carb option for a quick meal while sightseeing."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
