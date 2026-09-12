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
fun SeoulGlutenFreeKetoScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Gluten Free and Keto Friendly", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Gluten Free")
            BodyText("Nageunae (나그네), in Yeongdeungpo-gu, is a fully gluten-free Korean restaurant that makes its own gluten-free soy sauce and gochujang — it's reportedly the only place in Seoul serving gluten-free tteokbokki.")
            BodyText("Sunnyhouse (also known as Sunny Bread, 써니브레드) is a 100% gluten-free bakery-cafe run by a celiac owner, offering waffles, bagels, cakes, and sandwiches — its address has changed more than once, so check their Instagram (@sunnyhousekr) for the current location before visiting.")
            BodyText("237 Pizza (237피자), in the Itaewon/Hannam area, is a dedicated gluten-free Neapolitan-style pizzeria — however, there are reports it may currently be closed, so confirm it's open before making a special trip.")
            BodyText("Cafe Interact, in Gangnam-gu, makes everything flour-free and sugar-free, making it both gluten-free and keto-friendly, with dairy-free milk options for drinks.")
            BodyText("6Day-Chicken (6일닭강정), in Jongno-gu, is a dedicated gluten-free Korean fried chicken shop using a patented gluten-free rice-flour batter, and is featured on Visit Seoul's official tourism site. Monil2 House (모닐이네하우스), in Yeonnam-dong/Hongdae, is another fully gluten-free bakery, run by a celiac owner, offering rice-flour-based pastries, bagels, and pizza — note it appears to be a separate business from any other bakery simply called \"Monil.\" As for gluten-free Korean corn dogs, no dedicated gluten-free corn dog shop was found in Seoul — the standard version uses a wheat-and-panko batter and isn't celiac-safe.")

            SectionHeader("Keto Friendly")
            BodyText("Ketobbang (케토빵), in Mapo-gu, is a dedicated keto dessert shop making no-added-sugar, wheat-free, low-carb treats — a solid choice covering both keto and gluten-free needs at once.")
            BodyText("Preppers Diet Food, in Yongsan-gu, sells high-protein diet meals, but they don't explicitly market themselves as gluten-free or keto, and some menu items (like pasta and rice bowls) clearly aren't low-carb — check individual dishes rather than assuming the whole menu qualifies.")
            BodyText("Salady (샐러디) is a large Korean salad and grain-bowl chain with many Seoul locations; it's plausibly keto-friendly given the build-your-own-bowl format, but this isn't confirmed against their current menu, so it's worth double-checking ingredients when you order.")
            BodyText("Slow Cali (슬로우캘리) is a poke bowl chain with several Seoul branches — a customizable protein-and-vegetable bowl without rice could work for keto, but its gluten-free status is unconfirmed, since Korean poke sauces commonly use regular wheat-based soy sauce rather than gluten-free tamari. Ask staff about their sauce ingredients rather than assuming it's celiac-safe.")
        }
    }
}
