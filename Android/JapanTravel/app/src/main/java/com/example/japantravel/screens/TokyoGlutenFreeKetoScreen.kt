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
                "**T's Kitchen** (**Roppongi** and **Ueno**) is already covered in Food and Cafes and About " +
                    "Food - a 100% gluten-free restaurant certified by the Gluten Intolerance " +
                    "Group, the first such certification in Asia."
            )
            BodyText(
                "**GEN-TEN** and **RICEHACK**, both in **Shibuya**, are dedicated rice-flour gluten-free " +
                    "bakeries - a good option for bread, pastries, and sandwiches without hunting " +
                    "through a mixed menu."
            )
            BodyText(
                "**Otsuna Sushi** can put together a gluten-free omakase if you ask in advance - worth " +
                    "knowing if a high-end sushi experience is on your list and you don't want to " +
                    "settle for conveyor-belt safety."
            )
            BodyText(
                "**Mo Mo Paradise**, a shabu-shabu/sukiyaki chain with an English menu, isn't a " +
                    "dedicated gluten-free kitchen but is genuinely well set up for it: on " +
                    "request, staff bring a separate gluten-free broth and a bottle of " +
                    "gluten-free (tamari) soy sauce for dipping, rather than just leaving you " +
                    "with plain broth - so you're not limited to unseasoned shabu-shabu. The " +
                    "vegetable buffet is kept isolated from other allergens and items are " +
                    "labeled for wheat, nuts, and milk. As with any non-dedicated kitchen, it's " +
                    "not guaranteed celiac-safe - call ahead to confirm current practices. **Tokyo** " +
                    "branches: **Shinjuku** Higashiguchi (3-28-10 Shinjuku, HUMAX Pavilion 3F/4F), " +
                    "**Shinjuku** Meijidori (3-5-4 Shinjuku, Rainbow Village Bldg 7F), **Shinjuku** " +
                    "Kabukicho (1-20-1 Kabuki-cho, Humax Pavilion 8F), **Shinjuku** 3-chome " +
                    "(3-30-11 Shinjuku, Takano Daini Bldg 8F), **Shibuya** Koen-dori (20-15 " +
                    "Udagawacho, Humax Pavilion 8F), **Shibuya** Center-Gai (31-2 Udagawacho, BEAM " +
                    "Bldg 6F), and **Ikebukuro** (1-21-2 Minami-Ikebukuro, Humax Pavilion 8F)."
            )

            SectionHeader("Keto-friendly")
            BodyText(
                "Yakiniku (grill-it-yourself Japanese BBQ) and shabu-shabu (thin-sliced meat " +
                    "swished through broth) are naturally keto-friendly formats - just go easy on " +
                    "sweet dipping sauces and stick to soy sauce. **Kollabo**, a Korean-BBQ/yakiniku " +
                    "hybrid with locations in **Shibuya**, **Shinjuku**, and **Kichijoji**, works well for " +
                    "this. **Gyu-Kaku** is a nationwide all-you-can-eat yakiniku chain, and **Mo Mo** " +
                    "**Paradise** is a shabu-shabu chain with an English menu - see Gluten-free " +
                    "above for its allergy accommodations and branch list."
            )
            BodyText(
                "At conveyor-belt sushi chains (see Food and Cafes), ordering sashimi and " +
                    "nigiri-without-rice plates keeps things low-carb while still letting you eat " +
                    "at a normal tourist-friendly spot. **Tsukiji Outer Market**'s grilled seafood and " +
                    "skewer stalls (see City Regions - Fish markets) are another naturally " +
                    "low-carb option for a quick meal while sightseeing."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
