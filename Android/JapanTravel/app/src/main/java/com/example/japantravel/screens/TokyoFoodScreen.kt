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
fun TokyoFoodScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "About Food", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Street food & famous dishes")
            BodyText(
                "Yakitori - skewered, grilled chicken (different skewers use different cuts), " +
                    "often eaten at small stalls or izakayas. Sukiyaki - thin-sliced beef and " +
                    "vegetables simmered in a sweet soy-based sauce, traditionally dipped in raw " +
                    "beaten egg before eating."
            )
            BodyText(
                "Other common street food and dishes worth knowing: takoyaki (fried octopus " +
                    "batter balls), okonomiyaki (savory grilled cabbage pancake), taiyaki " +
                    "(fish-shaped cake, often filled with red bean paste), ramen, tempura " +
                    "(battered, deep-fried seafood/vegetables), and onigiri (rice balls, sold " +
                    "everywhere including convenience stores)."
            )

            SectionHeader("What is an izakaya?")
            BodyText(
                "An izakaya is a casual Japanese gastropub - a relaxed spot for drinks alongside " +
                    "many small shared dishes, popular for after-work socializing. Menus are " +
                    "typically ordered dish-by-dish rather than as one big individual meal."
            )

            SectionHeader("Food allergens")
            BodyText(
                "Japan legally requires 9 allergens to be labeled on packaged food as of April " +
                    "2026: shrimp, cashew nut, crab, walnut, wheat, buckwheat, egg, milk, and " +
                    "peanut. A further 21 allergens (including soy and sesame) are recommended but " +
                    "not legally required to be labeled."
            )
            BodyText(
                "Two common hidden risks for travelers with dietary restrictions: soy sauce often " +
                    "contains wheat, and dashi (the base stock used in many soups and sauces) is " +
                    "often fish-based even when a dish otherwise looks vegetarian. Restaurant staff " +
                    "may not proactively mention either. See the Specific Celiac Information " +
                    "section for a printable Japanese explanation card covering gluten specifically."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
