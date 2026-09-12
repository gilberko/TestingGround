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
fun BusanGlutenFreeKetoScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Keto Friendly and Gluten Free", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            BodyText("Busan doesn't have any restaurants independently confirmed as certified gluten-free across multiple sources, so treat the recommendations below as traveler-reported rather than guaranteed celiac-safe, and confirm directly with the venue before ordering.")

            SectionHeader("Gluten Free")
            BodyText(boldNames("Chaseonchaeg (차선책), in the Seomyeon/Busanjin-gu area, is a cafe where the baked goods and cakes are reportedly all housemade and gluten-free — one traveler account specifically described them as both gluten-free and keto.", listOf("Chaseonchaeg")))
            BodyText(boldNames("10 Won Coin Cake Shop, near Songjeong Station, uses 100% rice flour with no added gluten, cooked on a griddle kept separate from its sandwich-making station to reduce cross-contact risk.", listOf("10 Won Coin Cake Shop")))
            BodyText(boldNames("Sunssalppang (선쌀빵), near Gwangalli Beach, is a bakery offering rice-based breads aimed at gluten-sensitive customers.", listOf("Sunssalppang")))
            BodyText(boldNames("Moonggoojeom Vegan Bakery, in Busanjin-gu, specializes in gluten-free vegan baked goods made from scratch.", listOf("Moonggoojeom Vegan Bakery")))
            BodyText(boldNames("Buda Myeonoak, near Haeundae Street Market, serves North Korean-style buckwheat noodles made from 100% buckwheat with no wheat mixed in — a naturally gluten-free noodle base, though it's worth confirming the broth and sauces separately.", listOf("Buda Myeonoak")))
            BodyText(boldNames("Kkachi Kkachi (까치까치), next to Gamcheon Culture Village in Seo-gu, offers gluten-free pasta, bread, and scones and will cook on a separate clean pan on request, though reported cross-contamination awareness is limited.", listOf("Kkachi Kkachi", "Gamcheon Culture Village")))

            SectionHeader("Keto Friendly")
            BodyText(boldNames("No restaurant chain in Busan specifically markets itself as keto, but Korean BBQ restaurants generally are a safe, naturally low-carb choice — grilled meat and vegetables ordered without rice or noodles work for most keto diets, even though none of Busan's BBQ restaurants are explicitly marketed toward keto diners.", listOf("Korean BBQ")))
        }
    }
}
