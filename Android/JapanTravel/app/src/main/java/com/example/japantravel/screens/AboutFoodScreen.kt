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
fun AboutFoodScreen(onBack: () -> Unit) {
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
            BodyText(
                "Yaki-imo (roasted sweet potato) is a classic cold-weather street food. Vendors " +
                    "drive slow trucks through neighborhoods playing a distinctive, mournful " +
                    "recorded call (\"yaki-imoooo\") to announce themselves, roasting whole " +
                    "sweet potatoes over hot stones in the truck bed. Sweet, dense varieties like " +
                    "Beniharuka and Naruto Kintoki are prized for this. A related dessert version, " +
                    "daigaku imo, is bite-sized fried sweet potato coated in a sticky sugar-soy " +
                    "glaze and sprinkled with black sesame, sold at street stalls and some " +
                    "convenience stores."
            )

            SectionHeader("Sushi")
            BodyText(
                "Nigiri is the classic style - a hand-pressed mound of vinegared rice topped with " +
                    "a slice of fish or seafood. Maki are rolled in nori seaweed: hosomaki (thin, " +
                    "one filling), futomaki (thick, several fillings), and uramaki (rice on the " +
                    "outside, like a California roll). Temaki is a hand-rolled cone you eat " +
                    "immediately before the nori softens. Chirashi is a bowl of rice topped with " +
                    "an assortment of sashimi, and oshizushi is pressed into a block shape, " +
                    "sliced, and served (a specialty of the **Kansai**/**Osaka** region)."
            )
            BodyText(
                "Quality and price span a wide range - from the conveyor-belt chains already " +
                    "covered under Food and Cafes (**Sushiro**, **Kura Sushi**), up to high-end omakase " +
                    "counters where the chef serves a fixed, curated sequence of pieces one at a " +
                    "time."
            )

            SectionHeader("Onigiri")
            BodyText(
                "Triangular or barrel-shaped rice balls, usually wrapped in a sheet of nori. " +
                    "Common fillings include umeboshi (pickled plum), grilled salmon, tuna " +
                    "mayonnaise, kombu (simmered seaweed), and tarako/mentaiko (salted or spicy " +
                    "cod roe). Convenience stores wrap the nori separately in plastic so it stays " +
                    "crisp until you open it just before eating - pull the tabs in the printed " +
                    "order. They're cheap, filling, and available at every convenience store and " +
                    "train station."
            )

            SectionHeader("Ramen")
            BodyText(
                "Ramen is defined mainly by its broth: shoyu (soy sauce based, clear brown), miso " +
                    "(hearty, savory, associated with **Sapporo**), shio (salt based, the lightest/" +
                    "clearest), and tonkotsu (pork bone, rich and cloudy, associated with **Hakata**/" +
                    "**Fukuoka**). Regional styles are worth seeking out: **Hakata** tonkotsu (thin " +
                    "noodles, order extra noodles as \"kaedama\"), **Sapporo** miso (thick noodles, " +
                    "butter and corn toppings common), and **Kitakata** (thin, curly noodles in a " +
                    "light shoyu broth)."
            )

            SectionHeader("Beef dishes")
            BodyText(
                "Sukiyaki (above) and gyudon (beef bowl, covered under Food and Cafes) are the " +
                    "everyday classics. Yakiniku is grill-it-yourself Korean-Japanese style BBQ - " +
                    "thin-sliced beef cuts cooked at the table over a grill and dipped in sauce. " +
                    "Shabu-shabu is thin-sliced beef swished briefly through a pot of simmering " +
                    "broth at the table until just cooked, then dipped in ponzu or sesame sauce. " +
                    "For a splurge, wagyu steak restaurants (teppanyaki counters or dedicated " +
                    "steakhouses) showcase Japan's famously marbled beef, often graded and priced " +
                    "by region (**Kobe**, **Omi**, **Matsusaka** among the best known)."
            )

            SectionHeader("Fish dishes")
            BodyText(
                "Sashimi is raw fish or seafood, thinly sliced and served with soy sauce and " +
                    "wasabi - no rice, unlike sushi. Shioyaki is fish (commonly saba/mackerel or " +
                    "sanma/Pacific saury) simply salted and grilled whole. Unagi is freshwater eel, " +
                    "butterflied, grilled, and glazed in a sweet-savory sauce (kabayaki style), " +
                    "usually served over rice as unadon/unaju - traditionally eaten in summer for " +
                    "stamina. Nimono refers to fish simmered in a soy-sugar-mirin broth, a staple " +
                    "of home cooking and set meals."
            )

            SectionHeader("Chicken dishes")
            BodyText(
                "Yakitori skewers use different cuts by name: momo (thigh), negima (thigh with " +
                    "leek), tsukune (minced chicken meatballs), and kawa (crispy grilled skin) are " +
                    "common orders. Karaage is Japanese-style fried chicken - bite-sized, " +
                    "marinated in soy/ginger/garlic, then double-fried for a crisp crust. Oyakodon " +
                    "(\"parent and child bowl\") is chicken and egg simmered together over rice. " +
                    "Chicken nanban is fried chicken served with a tangy vinegar sauce and tartar " +
                    "sauce, a specialty from **Miyazaki** that's now found nationwide."
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
