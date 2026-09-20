package com.example.japantravel.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private const val CELIAC_CARD_JAPANESE =
    "私はセリアック病(グルテン不耐症)です。\n\n" +
        "小麦・大麦・ライ麦などグルテンを含む食品を食べることができません。\n\n" +
        "パン、麺類、天ぷらの衣、醤油(小麦を含むことが多いです)にもご注意ください。\n\n" +
        "グルテンを含まない食材で調理していただけますか?\n\n" +
        "ご協力よろしくお願いいたします。"

@Composable
fun FoodAllergenSafetyScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Food Allergen Safety", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
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
                    "may not proactively mention either. See the Celiac Card section below for a " +
                    "printable Japanese explanation card covering gluten specifically."
            )

            SectionHeader("Food Scanning Apps")
            BodyText(
                "Foodfit Japan (free, Android and iOS) scans Japanese food labels with the camera " +
                    "and uses AI to translate and flag ingredients against dietary profiles: Vegan, " +
                    "Vegetarian, Halal, Gluten-Free, and Lactose-Free. Each scanned item gets a " +
                    "Safe / Caution / Avoid verdict, with ingredient translation in 30 languages - " +
                    "handy for reading labels at **7-Eleven**, **Lawson**, **FamilyMart**, and supermarkets " +
                    "like **Aeon** and **Life**, where packaging is Japanese-only."
            )
            BodyText(
                "Note this is a diet-profile scanner, not a medical allergen database - it's built " +
                    "around dietary philosophies (vegan, halal, etc.) rather than exhaustive allergen " +
                    "detection. For a specific allergy (especially anything serious), still cross-check " +
                    "with the allergen list above and use the Celiac Card below for restaurant meals."
            )

            SectionHeader("Celiac Card")
            BodyText("Show this card to restaurant or hotel staff.")
            Spacer(Modifier.height(8.dp))
            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = CELIAC_CARD_JAPANESE,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(20.dp)
                )
            }
            Spacer(Modifier.height(8.dp))
            BodyText(
                "For a professionally vetted, printable Japanese celiac/gluten-free card, " +
                    "see Celiac Travel (celiactravel.com), Equal Eats (equaleats.com), or " +
                    "OpenKyoto's free downloadable PDF (openkyoto.com)."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
