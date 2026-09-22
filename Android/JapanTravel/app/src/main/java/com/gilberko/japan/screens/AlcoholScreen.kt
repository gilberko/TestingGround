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

@Composable
fun AlcoholScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Alcohol", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Sake")
            BodyText(
                "**Sake** is a fermented (brewed, not distilled) alcoholic drink made from rice, " +
                    "water, koji mold, and yeast. The koji mold converts the rice's starch into " +
                    "sugar, which yeast then ferments into alcohol - a process happening " +
                    "simultaneously in the same tank, unique to sake brewing. Typical sake is " +
                    "around 15% ABV."
            )
            BodyText(
                "One of the key details on any sake label is the **seimaibuai** (rice polishing " +
                    "rate) - the percentage of the original rice grain that **remains** after " +
                    "polishing away the outer layers. So a seimaibuai of 60% means 40% of the " +
                    "grain was milled away and 60% remains - the lower the number, the more " +
                    "polished the rice. The outer layers contain proteins, fats, and minerals that " +
                    "create rougher, earthier flavors, so more polishing (a lower number) generally " +
                    "produces a lighter, more fragrant, cleaner-tasting sake, while less polishing " +
                    "(a higher number) produces a fuller-bodied, earthier, more umami-heavy sake."
            )
            BodyText(
                "This ratio defines sake's grade labels: **Honjozo** (seimaibuai 70% or less, has " +
                    "a small amount of added brewing alcohol), **Ginjo** (60% or less), and " +
                    "**Daiginjo** (50% or less) - the more polished, generally more delicate and " +
                    "aromatic premium tiers. Separately, **Junmai** (\"pure rice\") means no " +
                    "brewing alcohol was added at all, regardless of polish level - so labels like " +
                    "**Junmai Ginjo** and **Junmai Daiginjo** combine both a polish tier and a " +
                    "no-added-alcohol guarantee. Junmai sakes tend toward more acidity and umami; " +
                    "non-Junmai sakes (with added alcohol) tend to be lighter and smoother."
            )
            BodyText(
                "Well-known brands to look for: **Dassai** (Yamaguchi) - very highly polished " +
                    "(their flagship \"Dassai 23\" uses a 23% seimaibuai), fruity with notes of " +
                    "cantaloupe and white peach, and a clean finish. **Kubota** (Niigata) - a " +
                    "light-and-dry style, subtle and refined, food-friendly. **Hakkaisan** " +
                    "(Niigata) - clean and balanced, in the crisp, dry \"tanrei karakuchi\" " +
                    "regional style. **Kikusui** (Niigata) - smooth, soft, and approachable, a " +
                    "good everyday choice. **Hakutsuru** and **Gekkeikan** - large, long-established " +
                    "Nada (Hyogo) producers, widely available worldwide, reliable solid Junmai-style " +
                    "options for getting started."
            )

            SectionHeader("Japanese Whisky")
            BodyText(
                "Japanese whisky follows the Scotch tradition closely - malted and grain whisky " +
                    "distilled in pot stills, then aged in casks and blended. It was brought to " +
                    "Japan by **Masataka Taketsuru**, who studied distilling in Scotland from 1918 " +
                    "to 1920, then helped found Suntory's **Yamazaki** distillery in 1923 - Japan's " +
                    "first. He later founded his own company, **Nikka**, starting with the " +
                    "**Yoichi** distillery in Hokkaido, chosen for its Scotland-like climate."
            )
            BodyText(
                "Japanese whisky is internationally very well regarded - the **Yamazaki Sherry " +
                    "Cask 2013** was named World Whisky of the Year in Jim Murray's Whisky Bible " +
                    "2015, the first time a Japanese whisky topped the list, ahead of every Scotch " +
                    "that year. Brands worth trying: **Yamazaki**, **Hakushu**, and **Hibiki** " +
                    "(Suntory), and **Nikka From The Barrel**, **Nikka Coffey Grain**, **Yoichi**, " +
                    "and **Taketsuru** (Nikka). Demand has made many aged, award-winning bottlings " +
                    "(like 12 or 18 year Yamazaki/Hakushu) scarce and expensive - **Hibiki " +
                    "Harmony**, **Nikka From The Barrel**, and **Nikka Coffey Grain** are more " +
                    "realistic to find and are excellent starting points."
            )

            SectionHeader("Beer")
            BodyText(
                "The Japanese beer market is dominated by four major brands, all pale lagers " +
                    "around 5% ABV: **Asahi Super Dry** (crisp and dry, the best-selling brand), " +
                    "**Kirin Ichiban** (smooth, made from first-press wort only), **Sapporo** " +
                    "(Japan's oldest brand, its Black Label and Yebisu premium line are well " +
                    "regarded), and **Suntory Premium Malts** (softer, maltier profile). All four " +
                    "are safe, easy recommendations. A smaller craft beer scene also exists - " +
                    "**Yo-Ho Brewing**'s **Yona Yona Ale** is a well-known, widely available " +
                    "example if a more flavorful, hoppier beer is wanted."
            )

            SectionHeader("Shōchū")
            BodyText(
                "**Shōchū** is a distilled spirit, typically around 25% ABV - stronger than sake " +
                    "or beer but weaker than full-strength spirits like vodka or whisky (~40%). " +
                    "Like sake, it starts with koji mold converting starch into fermentable sugar, " +
                    "followed by yeast fermentation, but is then distilled rather than simply " +
                    "pressed. Its character comes from its base ingredient: **Imo** (sweet potato) " +
                    "- smooth to earthy and full-flavored; **Mugi** (barley) - roasted and dry; " +
                    "**Kome** (rice) - mild with few strong aromas; **Kokuto** (brown/black sugar " +
                    "made from sugar cane) - distinctive to the Amami Ōshima islands near " +
                    "Kagoshima, lightly sweet on the finish; **Soba** (buckwheat) - aromatic, " +
                    "pairs naturally with soba noodle dishes; and **Shiso** (perilla leaf) - " +
                    "minty and herbal."
            )
            BodyText(
                "A related but distinct spirit is **Awamori** from Okinawa, made from long-grain " +
                    "indica rice using black koji rather than the rice typically used elsewhere in " +
                    "Japan - it has its own separate tradition and is usually not labeled as a type " +
                    "of shōchū. Shōchū is commonly drunk on the rocks, \"oyu-wari\" (mixed with hot " +
                    "water), \"mizu-wari\" (mixed with cold water), or with soda."
            )

            SectionHeader("Chūhai and Japanese \"sours\"")
            BodyText(
                "**Chūhai** (short for \"shochu highball\") is shōchū or a neutral spirit mixed " +
                    "with carbonated water and flavoring; \"sours\" are essentially the same drink " +
                    "styled as a sour cocktail, like a lemon sour. Today both are mostly sold as " +
                    "canned, ready-to-drink products, a huge and constantly rotating category at " +
                    "any convenience store."
            )
            BodyText(
                "ABV varies a lot by brand: **Horoyoi** sits at around 3% and is deliberately light " +
                    "and sweet, aimed at people who don't like a strong alcohol taste. Standard " +
                    "cans are typically around 5%. The \"Strong\" category - **Strong Zero** and " +
                    "**-196** - is around 9% ABV in Japan, noticeably stronger than the versions of " +
                    "these brands sometimes sold abroad (often diluted to 6% for export), so the " +
                    "domestic can hits harder than travelers may expect. Worth trying a few " +
                    "flavors of Strong Zero/-196 and Horoyoi as accessible, cheap convenience-store " +
                    "picks."
            )

            SectionHeader("Izakaya, convenience stores, and buying alcohol")
            BodyText(
                "An izakaya is the classic casual Japanese pub - a relaxed spot for drinks " +
                    "alongside many small shared dishes, the standard place to drink socially " +
                    "(see Local Food for more on izakaya food). Convenience stores (konbini) sell " +
                    "beer, chūhai, sake, and spirits very cheaply and conveniently, 24 hours a day " +
                    "- checkout involves a quick touchscreen age-confirmation prompt. Alcohol " +
                    "vending machines still exist in Japan but are much rarer than they used to be."
            )
            BodyText(
                "The legal drinking age in Japan is 20, not 18. There's no nationwide " +
                    "open-container law, so drinking in public places like parks and streets is " +
                    "generally fine, but some specific locations post their own no-alcohol " +
                    "restrictions, so it's not unconditionally allowed everywhere."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
