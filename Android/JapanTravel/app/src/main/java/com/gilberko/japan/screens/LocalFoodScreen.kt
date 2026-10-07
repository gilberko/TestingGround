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
import com.gilberko.japan.R

@Composable
fun LocalFoodScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Local Food", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Street food & famous dishes")
            BodyText(
                "Yakitori - skewered, grilled chicken (different skewers use different cuts), " +
                    "often eaten at small stalls or izakayas."
            )
            PlacePhoto(R.drawable.food_yakitori, "Yakitori", "Photo: Francesc Fort / Wikimedia Commons, CC BY-SA 4.0")
            BodyText(
                "Takoyaki - fried batter balls with a piece of octopus inside, cooked in a " +
                    "special dimpled griddle and topped with sauce, mayonnaise, bonito flakes " +
                    "and seaweed powder (a specialty of **Osaka**)."
            )
            PlacePhoto(R.drawable.food_takoyaki, "Takoyaki", "Photo: Fumikas Sagisavas / Wikimedia Commons, CC0")
            BodyText(
                "Okonomiyaki - a savory grilled cabbage pancake, cooked on a hot iron plate and " +
                    "topped with sauce, mayonnaise and bonito flakes."
            )
            PlacePhoto(R.drawable.food_okonomiyaki, "Okonomiyaki", "Photo: ume-y / Wikimedia Commons, CC BY 2.0")
            BodyText(
                "Taiyaki - a fish-shaped cake, often filled with red bean paste."
            )
            PlacePhoto(R.drawable.food_taiyaki, "Taiyaki", "Photo: Ocdp / Wikimedia Commons, CC0")
            BodyText(
                "Tempura - battered, deep-fried seafood and vegetables, often served as a set " +
                    "with rice, miso soup and a dipping sauce."
            )
            PlacePhoto(R.drawable.food_tempura, "Tempura", "Photo: Ocdp / Wikimedia Commons, CC0")
            BodyText(
                "Ramen, onigiri (rice balls, sold everywhere including convenience stores) and " +
                    "sukiyaki each have their own section below."
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
            PlacePhoto(R.drawable.food_yaki_imo, "A yaki-imo truck", "Photo: Syced / Wikimedia Commons, CC0")

            SectionHeader("What does \"yaki\" mean?")
            BodyText(
                "Yes - \"yaki\" (焼き) comes from the verb yaku (焼く), which means to grill, " +
                    "roast, bake or burn - cooking with direct, dry heat. When a dish name ends " +
                    "in -yaki, it usually means \"grilled/griddled something\":"
            )
            BodyText(
                "Yakitori = grilled bird (chicken). Yakiniku = grilled meat (Japanese " +
                    "barbecue). Yakizakana = grilled fish. Yaki-imo = roasted sweet potato. " +
                    "Teriyaki = \"shine\" + grill (the glossy sweet soy glaze). Takoyaki = " +
                    "octopus cooked on a griddle. Okonomiyaki = \"grilled as you like it\". " +
                    "Taiyaki = a cake baked in a tai (sea bream) shaped mould. Teppanyaki = " +
                    "grilled on an iron plate. Yakisoba = noodles fried on a griddle."
            )
            BodyText(
                "Yakisoba itself is worth knowing: wheat noodles stir-fried on a hot griddle " +
                    "with pork, cabbage and a thick, sweet Worcestershire-style sauce - a festival " +
                    "stall and home-cooking classic. (Despite the name, they're not buckwheat " +
                    "soba noodles.)"
            )
            PlacePhoto(R.drawable.food_yakisoba, "Yakisoba", "Photo: Quercus acuta / Wikimedia Commons, CC BY-SA 4.0")
            BodyText(
                "Sukiyaki is the odd one out: it's a real name, but modern sukiyaki is mostly " +
                    "simmered, not grilled - see the Sukiyaki section below for why it still " +
                    "carries \"yaki\". And yes, Japan has its own barbecue: yakiniku, covered " +
                    "under Beef dishes."
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
            PlacePhoto(R.drawable.food_sushi, "Salmon nigiri sushi", "Photo: Tim Reckmann / Wikimedia Commons, CC BY 2.0")
            BodyText(
                "Quality and price span a wide range - from the conveyor-belt chains covered " +
                    "under Restaurants, Food Chains and Cafes (**Sushiro**, **Kura Sushi**), up to " +
                    "high-end omakase counters where the chef serves a fixed, curated sequence of " +
                    "pieces one at a time."
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
            PlacePhoto(R.drawable.food_onigiri, "Onigiri", "Photo: Ocdp / Wikimedia Commons, CC0")

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
            PlacePhoto(R.drawable.food_ramen, "A bowl of ramen in Osaka", "Photo: Joli Rumi / Wikimedia Commons, CC BY-SA 4.0")

            SectionHeader("Sukiyaki")
            PlacePhoto(R.drawable.food_sukiyaki, "Sukiyaki", "Photo: ajari / Wikimedia Commons, CC BY 2.0")
            BodyText(
                "Sukiyaki is a classic beef hot pot cooked at the table in a shallow cast-iron " +
                    "pan: thin-sliced beef (often wagyu), tofu, negi (Japanese leek), shiitake " +
                    "mushrooms, shungiku (chrysanthemum greens) and shirataki noodles, cooked in " +
                    "warishita - a sweet-savory sauce of soy sauce, mirin, sugar and sake. Each " +
                    "bite is dipped in raw beaten egg before eating, which cools it and softens " +
                    "the sweetness. It's traditionally a special-occasion or celebration meal, " +
                    "often finished with udon noodles or rice cooked in the leftover sauce."
            )
            BodyText(
                "There are two regional styles: in **Kanto** (the **Tokyo** area) everything is " +
                    "simmered in pre-mixed warishita, while in **Kansai** (**Osaka**/**Kyoto**) the " +
                    "beef is first seared in the hot pan with sugar and soy sauce before the " +
                    "vegetables go in - closer to the original \"yaki\" (grilling)."
            )
            BodyText(
                "About the name: \"suki\" sounds exactly like the Japanese word 好き (suki), " +
                    "\"to like/love\" - which is why sukiyaki is easy to remember as a \"love\" " +
                    "dish. But the suki in sukiyaki is written differently and means something " +
                    "else. The two common explanations are 鋤 (suki), a plough/spade blade that " +
                    "meat was supposedly grilled on in the past, or sukimi, meaning thinly sliced " +
                    "meat - historians aren't sure which is right. \"Yaki\" means grilled, so the " +
                    "name is roughly \"spade-grilled\" or \"thin-slice-grilled\", not \"love-grilled\"."
            )
            BodyText(
                "Gluten-free? Not by default. Standard Japanese soy sauce is brewed with wheat, so " +
                    "regular warishita - and therefore regular sukiyaki - contains gluten. A " +
                    "gluten-free version needs tamari (wheat-free soy sauce), which few " +
                    "restaurants offer. The best lead in this app is **Mo Mo Paradise** (an " +
                    "all-you-can-eat sukiyaki and shabu-shabu chain): on request staff bring a " +
                    "separate gluten-free broth and gluten-free tamari. That setup is confirmed " +
                    "for shabu-shabu, but a gluten-free sukiyaki sauce isn't confirmed, so " +
                    "ask staff whether they can make sukiyaki with their gluten-free tamari - and " +
                    "if not, choose shabu-shabu with the gluten-free broth instead. See Tokyo " +
                    "Gluten Free and Keto Friendly, and show the Celiac Card (Food Allergen " +
                    "Safety)."
            )
            BodyText(
                "Sukiyaki vs. shabu-shabu: they're similar but not the same. Both are cooked at " +
                    "the table from one shared pot, with thin-sliced beef (or pork), vegetables " +
                    "and tofu. Sukiyaki uses a sweet, rich soy-sugar-mirin sauce in a shallow iron " +
                    "pan - ingredients simmer or sear until they soak up the sauce, then get " +
                    "dipped in raw egg. Shabu-shabu uses a light kombu (seaweed) broth in a deeper " +
                    "pot - you swish each slice for just a few seconds (\"shabu-shabu\" imitates " +
                    "that swishing sound) and dip it in ponzu (citrus-soy) or sesame sauce. " +
                    "Shabu-shabu is lighter, and easier to make gluten-free because the broth " +
                    "itself is plain - but ponzu contains soy sauce, so ask for gluten-free " +
                    "tamari to dip in."
            )

            SectionHeader("Beef dishes")
            BodyText(
                "Sukiyaki (above) and gyudon (beef bowl - thin-sliced beef and onion simmered in " +
                    "a sweet soy broth over rice, covered under Restaurants, Food Chains and " +
                    "Cafes) are the everyday classics."
            )
            PlacePhoto(R.drawable.food_gyudon, "Gyudon (beef bowl)", "Photo: Ocdp / Wikimedia Commons, CC0")
            BodyText(
                "Yakiniku is Japanese barbecue - grill-it-yourself, Korean-influenced: " +
                    "thin-sliced beef (plus pork, chicken and vegetables) cooked at the table " +
                    "over a charcoal or gas grill and dipped in sauce. Chains such as " +
                    "**Gyu-Kaku** are everywhere and easy for first-timers."
            )
            PlacePhoto(R.drawable.food_yakiniku, "Yakiniku grill", "Photo: Benlisquare / Wikimedia Commons, CC BY-SA 4.0")
            BodyText(
                "Shabu-shabu is thin-sliced beef swished briefly through a pot of simmering broth " +
                    "at the table until just cooked, then dipped in ponzu or sesame sauce (see " +
                    "Sukiyaki vs. shabu-shabu above)."
            )
            PlacePhoto(R.drawable.food_shabu_shabu, "Shabu-shabu", "Photo: 漱石の猫 / Wikimedia Commons, CC0")
            BodyText(
                "For a splurge, wagyu steak restaurants (teppanyaki counters or dedicated " +
                    "steakhouses) showcase Japan's famously marbled beef, often graded and priced " +
                    "by region (**Kobe**, **Omi**, **Matsusaka** among the best known)."
            )

            SectionHeader("Fish dishes")
            BodyText(
                "Sashimi is raw fish or seafood, thinly sliced and served with soy sauce and " +
                    "wasabi - no rice, unlike sushi."
            )
            PlacePhoto(R.drawable.food_sashimi, "Sashimi platter", "Photo: LeonardKong / Wikimedia Commons, CC BY 2.0")
            BodyText(
                "Shioyaki is fish (commonly saba/mackerel or sanma/Pacific saury) simply salted " +
                    "and grilled whole. Unagi is freshwater eel, butterflied, grilled, and glazed " +
                    "in a sweet-savory sauce (kabayaki style), usually served over rice as " +
                    "unadon/unaju - traditionally eaten in summer for stamina."
            )
            PlacePhoto(R.drawable.food_unagi, "Unadon (grilled eel over rice)", "Photo: 663highland / Wikimedia Commons, CC BY 2.5")
            BodyText(
                "Nimono refers to fish simmered in a soy-sugar-mirin broth, a staple of home " +
                    "cooking and set meals."
            )

            SectionHeader("Chicken dishes")
            BodyText(
                "Yakitori skewers use different cuts by name: momo (thigh), negima (thigh with " +
                    "leek), tsukune (minced chicken meatballs), and kawa (crispy grilled skin) are " +
                    "common orders. Karaage is Japanese-style fried chicken - bite-sized, " +
                    "marinated in soy/ginger/garlic, then double-fried for a crisp crust."
            )
            PlacePhoto(R.drawable.food_karaage, "Chicken karaage", "Photo: Ocdp / Wikimedia Commons, CC0")
            BodyText(
                "Oyakodon (\"parent and child bowl\") is chicken and egg simmered together over " +
                    "rice. Chicken nanban is fried chicken served with a tangy vinegar sauce and " +
                    "tartar sauce, a specialty from **Miyazaki** that's now found nationwide."
            )

            SectionHeader("What is an izakaya?")
            BodyText(
                "An izakaya is a casual Japanese gastropub - a relaxed spot for drinks alongside " +
                    "many small shared dishes, popular for after-work socializing. Menus are " +
                    "typically ordered dish-by-dish rather than as one big individual meal."
            )
            BodyText(
                "Common things to order: edamame (salted boiled soybeans in the pod), yakitori " +
                    "and other skewers (kushiyaki), karaage (fried chicken), agedashi tofu " +
                    "(lightly fried tofu in a warm dashi broth), dashimaki tamago (a rolled " +
                    "omelet), a sashimi platter, hiyayakko (chilled tofu with toppings), " +
                    "potato salad, korokke (croquettes), gyoza (dumplings) and tsukemono " +
                    "(pickles). People often finish with a \"shime\" (closing) dish such as " +
                    "onigiri, ochazuke (rice with tea or broth poured over) or noodles. For " +
                    "drinks, see the Alcohol screen."
            )
            PlacePhoto(R.drawable.food_izakaya, "Edamame", "Photo: jark / Wikimedia Commons, CC BY-SA 2.0")
            BodyText(
                "Otoshi (also called tsukidashi): soon after you sit down, a small dish you " +
                    "didn't order arrives - pickles, a bit of simmered fish, edamame or a small " +
                    "salad. It's not a mistake: it's a per-person table/seat charge in the form " +
                    "of a starter, typically around ¥300-600 (up to about ¥1,000 at upmarket " +
                    "places), and it usually can't be refused."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
