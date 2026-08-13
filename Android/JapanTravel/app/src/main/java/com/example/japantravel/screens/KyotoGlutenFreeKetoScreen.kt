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
fun KyotoGlutenFreeKetoScreen(onBack: () -> Unit) {
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
                "**Gion Soy Milk Ramen**, in **Higashiyama**, is Japan's only dedicated 100% gluten-free " +
                    "(and vegan) ramen shop - a rare find in a category that's normally very hard " +
                    "for celiac travelers."
            )
            BodyText(
                "**Toshoan** is a fully gluten-free bakery and pancake cafe - a good stop for bread " +
                    "and baked goods when most bakeries around it aren't an option."
            )

            SectionHeader("Keto-friendly")
            BodyText(
                "**Hyoto Shijo Karasuma** is a well-regarded shabu-shabu spot with premium meat and " +
                    "seafood sets - naturally low-carb once you skip the rice/noodle add-ons."
            )
            BodyText(
                "**Nishiki Market**'s grilled skewer and sashimi stalls (see Parks and Attractions - " +
                    "Overview) make for an easy low-carb graze while sightseeing. Kaiseki " +
                    "restaurants more broadly are worth considering too - many courses in a " +
                    "kaiseki meal are naturally low-carb protein and vegetable dishes, so asking " +
                    "to skip or reduce the rice course can keep the whole meal keto-friendly."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
