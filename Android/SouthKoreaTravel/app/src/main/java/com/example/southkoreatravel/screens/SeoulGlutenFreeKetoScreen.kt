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
            BodyText(boldNames("Nageunae (나그네), in Yeongdeungpo-gu, is a fully gluten-free Korean restaurant that makes its own gluten-free soy sauce and gochujang — it's reportedly the only place in Seoul serving gluten-free tteokbokki.", listOf("Nageunae")))
            BodyText(boldNames("Sunnyhouse (also known as Sunny Bread, 써니브레드) is a 100% gluten-free bakery-cafe run by a celiac owner, offering waffles, bagels, cakes, and sandwiches — its address has changed more than once, so check their Instagram (@sunnyhousekr) for the current location before visiting.", listOf("Sunnyhouse", "Sunny Bread")))
            BodyText(boldNames("237 Pizza (237피자), in the Itaewon/Hannam area, is a dedicated gluten-free Neapolitan-style pizzeria — however, there are reports it may currently be closed, so confirm it's open before making a special trip.", listOf("237 Pizza")))
            BodyText(boldNames("Cafe Interact, in Gangnam-gu, makes everything flour-free and sugar-free, making it both gluten-free and keto-friendly, with dairy-free milk options for drinks.", listOf("Cafe Interact")))
            BodyText(boldNames("6Day-Chicken (6일닭강정), in Jongno-gu, is a dedicated gluten-free Korean fried chicken shop using a patented gluten-free rice-flour batter, and is featured on Visit Seoul's official tourism site. Monil2 House (모닐이네하우스), in Yeonnam-dong/Hongdae, is another fully gluten-free bakery, run by a celiac owner, offering rice-flour-based pastries, bagels, and pizza — note it appears to be a separate business from any other bakery simply called \"Monil.\" As for gluten-free Korean corn dogs, no dedicated gluten-free corn dog shop was found in Seoul — the standard version uses a wheat-and-panko batter and isn't celiac-safe.", listOf("6Day-Chicken", "Monil2 House")))

            SectionHeader("Korean BBQ")
            BodyText(boldNames("853, in Insadong, Jongno-gu, is a Korean BBQ restaurant specifically listed as gluten-free-friendly, with English-speaking staff experienced in accommodating dietary requests — it's popular, so reservations are recommended, especially before 6:30pm.", listOf("853")))
            BodyText(boldNames("Woobaekjang (우백장), near the Gyeongui Line Forest Trail in the Hongdae area of Mapo-gu, is recommended by celiac travelers for its wagyu beef and gluten-free-friendly approach — it's a small, local spot, so check its current address on a map app before visiting.", listOf("Woobaekjang")))
            BodyText(boldNames("Ziu BBQ, in the Myeongdong/Namsan-dong area of Jung-gu, is a family-run Korean BBQ restaurant (operating since 2002) listed as gluten-free-friendly.", listOf("Ziu BBQ")))
            BodyText(boldNames("Mapletree House, with branches in Itaewon, Samcheong-dong, Myeongdong, and Gangnam, doesn't have a dedicated gluten-free menu, but diners report staff will point out at least two unmarinated (naturally gluten-free) meat options when shown a Korean-language gluten-free explanation card — marinated dishes and some banchan side dishes are not safe, so stick to the plain cuts.", listOf("Mapletree House")))
            BodyText(boldNames("Hongdae Korean BBQ Jeju Special House, in Mapo-gu, specializes in charcoal-grilled (not gas) Jeju pork and is listed as gluten-free-friendly — the plain charcoal pork is the safer choice over the spicy beef and marinated bulgogi options, which likely contain regular soy sauce.", listOf("Hongdae Korean BBQ Jeju Special House")))
            BodyText(boldNames("Myeongdong Korean BBQ Mongvely, in Jung-gu, is an all-you-can-eat, self-serve Korean BBQ buffet where nearly all the meat is unmarinated and there's no shared marinade tray, which reduces cross-contact risk — bring your own gluten-free soy sauce, since the house condiments aren't confirmed gluten-free.", listOf("Myeongdong Korean BBQ Mongvely")))
            BodyText(boldNames("Taecho, with branches in Hongdae and Myeongdong, is best approached with extra caution — its own gluten-free-directory listing flags that it isn't a dedicated gluten-free facility, and its signature galbi is marinated in a traditional soy-sauce-based sauce that likely contains gluten. If you go, confirm with staff and stick to unmarinated items only.", listOf("Taecho")))
            BodyText(boldNames("None of the Korean BBQ restaurants above are certified gluten-free kitchens — regular Korean soy sauce and gochujang contain wheat, so safety generally comes down to ordering unmarinated cuts, choosing self-serve/buffet formats that avoid shared marinade trays, and confirming with gluten-aware staff before ordering. A dedicated gluten-free gyeran-bbang (Korean egg bread, 계란빵) seller could not be confirmed in Seoul — most gluten-free bakeries found (in areas like Mangwon-dong, Yeonnam-dong, and Seongdong-gu) focus on rice-flour breads and cakes rather than egg bread specifically.", listOf("gyeran-bbang")))

            SectionHeader("Keto Friendly")
            BodyText(boldNames("Ketobbang (케토빵), in Mapo-gu, is a dedicated keto dessert shop making no-added-sugar, wheat-free, low-carb treats — a solid choice covering both keto and gluten-free needs at once.", listOf("Ketobbang")))
            BodyText(boldNames("Preppers Diet Food, in Yongsan-gu, sells high-protein diet meals, but they don't explicitly market themselves as gluten-free or keto, and some menu items (like pasta and rice bowls) clearly aren't low-carb — check individual dishes rather than assuming the whole menu qualifies.", listOf("Preppers Diet Food")))
            BodyText(boldNames("Salady (샐러디) is a large Korean salad and grain-bowl chain with many Seoul locations; it's plausibly keto-friendly given the build-your-own-bowl format, but this isn't confirmed against their current menu, so it's worth double-checking ingredients when you order.", listOf("Salady")))
            BodyText(boldNames("Slow Cali (슬로우캘리) is a poke bowl chain with several Seoul branches — a customizable protein-and-vegetable bowl without rice could work for keto, but its gluten-free status is unconfirmed, since Korean poke sauces commonly use regular wheat-based soy sauce rather than gluten-free tamari. Ask staff about their sauce ingredients rather than assuming it's celiac-safe.", listOf("Slow Cali")))
        }
    }
}
