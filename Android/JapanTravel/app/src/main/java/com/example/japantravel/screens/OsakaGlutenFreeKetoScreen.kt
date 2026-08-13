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
fun OsakaGlutenFreeKetoScreen(onBack: () -> Unit) {
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
                "**OKO - Fun Okonomiyaki Bar**, in the **Shinsaibashi-suji** shopping arcade, makes its " +
                    "signature okonomiyaki with soybean flour instead of wheat, and also offers " +
                    "gluten-free dumplings and fries. The menu marks gluten-free items clearly and " +
                    "posts its cross-contamination precautions - a rare place where a celiac " +
                    "traveler can actually eat this **Osaka** specialty. It's popular, so expect waits " +
                    "of around 45 minutes at peak times."
            )

            SectionHeader("Keto-friendly")
            BodyText(
                "**Osaka** has branches of the same nationwide yakiniku and shabu-shabu chains " +
                    "covered under **Tokyo**'s Gluten Free and Keto Friendly screen - **Gyu-Kaku** " +
                    "(all-you-can-eat yakiniku) and **Mo Mo Paradise** (shabu-shabu) both work well " +
                    "for a low-carb, meat-and-vegetables meal."
            )
            BodyText(
                "**Kuromon Ichiba Market**'s grilled seafood and skewer stalls are an easy low-carb " +
                    "option while wandering the market - point-and-eat seafood, wagyu skewers, and " +
                    "sashimi without needing a sit-down restaurant."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
