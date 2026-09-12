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
fun FoodScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Food", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Street Food")
            BodyText(boldNames("Tteokbokki is the single most iconic Korean street food — chewy cylindrical rice cakes simmered in a spicy-sweet gochujang (red chili paste) sauce, usually with fish cakes and scallions. You'll find it at street carts, bunsik (casual snack) shops, and dedicated restaurants everywhere.", listOf("Tteokbokki")))
            BodyText(boldNames("Kimchi is Korea's famous fermented, spiced cabbage (or radish) side dish — sour, spicy, and served with almost every meal, not just a street-food item, but essential to understanding Korean food overall.", listOf("Kimchi")))
            BodyText(boldNames("Other must-try street snacks: hotteok, a sweet stuffed pancake (honey, brown sugar, or nut filling) especially popular in winter; gimbap, rice and fillings rolled in seaweed, similar in idea to sushi rolls but distinctly Korean in flavor; and bungeoppang, a fish-shaped pastry filled with sweet red bean paste.", listOf("hotteok", "gimbap", "bungeoppang")))

            SectionHeader("Korean Pub Food & BBQ")
            BodyText(boldNames("Anju is the general term for food eaten alongside alcohol at a Korean bar or pub — in Korean drinking culture, it's considered bad form to drink without eating something alongside it. Classic anju includes fried chicken, pork belly, and pajeon (scallion pancake), usually shared over soju or beer.", listOf("Anju", "pajeon")))
            BodyText(boldNames("A pojangmacha is a small tent-covered street stall that serves alcohol alongside simple, warming anju like fishcake soup, tteokbokki, and ramyeon — a fun, casual, late-night way to eat and drink like a local, especially popular in the evenings.", listOf("pojangmacha", "tteokbokki", "ramyeon")))
            BodyText(boldNames("Korean BBQ restaurants (gogijip) are a whole dining experience: you grill meat — usually pork belly (samgyeopsal) or marinated beef (bulgogi/galbi) — yourself at a table-mounted grill, wrapping cooked pieces in lettuce leaves with rice, garlic, and ssamjang (a savory-spicy dipping sauce), alongside an array of free side dishes (banchan).", listOf("Korean BBQ", "gogijip", "samgyeopsal", "bulgogi/galbi", "ssamjang", "banchan")))

            SectionHeader("Fried Chicken & Corn Dogs")
            BodyText(boldNames("Korean fried chicken (chikin) is a food culture in its own right — twice-fried for extra crunch, often glazed in sauces like soy-garlic (ganjang) or sweet-spicy (yangnyeom), and a hugely popular pairing with beer (a combination affectionately nicknamed \"chimaek,\" chicken + maekju/beer).", listOf("Korean fried chicken", "chikin", "chimaek")))
            BodyText(boldNames("Korean corn dogs (hotdog) have become a street-food phenomenon of their own — a sausage (or sometimes mozzarella cheese, or a sausage-cheese combo) on a stick, coated in a doughy or panko-crumb batter, deep-fried, and then often rolled in sugar or topped with things like ramen-noodle crumbs, fries, or extra cheese. Popular in tourist areas like Myeongdong and Hongdae.", listOf("Korean corn dogs", "hotdog")))

            SectionHeader("Jeon & Jjigae")
            BodyText(boldNames("Jeon is Korea's broad category of savory pan-fried pancakes and fritters — vegetables, meat, or seafood mixed into a wheat-flour-and-egg batter and shallow-fried until crisp-edged. Pajeon (scallion pancake, already mentioned above) is the best-known example, but the category is much bigger.", listOf("Jeon", "Pajeon")))
            BodyText(boldNames("Kimchi-jeon folds chopped kimchi straight into the batter for a tangy, spicy pancake — arguably the single most common jeon ordered alongside soju. Haemul-pajeon is a seafood-and-scallion version loaded with squid, shrimp, or mussels. Gamja-jeon is made from grated or blended raw potato rather than wheat batter, giving it a denser, slightly gluey texture. Bindaetteok is a thicker, heartier pancake made from ground mung beans, usually studded with pork, kimchi, and bean sprouts.", listOf("Kimchi-jeon", "Haemul-pajeon", "Gamja-jeon", "Bindaetteok")))
            BodyText(boldNames("Jjigae is Korea's category of thick, heavily seasoned stews — saltier and more concentrated than the lighter beef soups (tang) mentioned below. Kimchi-jjigae, simmered from aged kimchi with pork or canned tuna, is the everyday classic. Doenjang-jjigae uses fermented soybean paste (doenjang) as its base, with tofu, zucchini, and potato. Sundubu-jjigae is built on silky, uncurdled soft tofu, often served bubbling in a stone pot with a raw egg cracked in at the table. Budae-jjigae (\"army stew\") is a Korean War-era fusion dish born near U.S. military bases, mixing spam, sausage, baked beans, and instant ramyeon noodles into one spicy pot.", listOf("Jjigae", "Kimchi-jjigae", "Doenjang-jjigae", "Sundubu-jjigae", "Budae-jjigae")))
            BodyText(boldNames("On gluten: jeon pancakes (pajeon, kimchi-jeon, haemul-pajeon, bindaetteok) are almost always made with a wheat-flour batter, so they generally aren't gluten-free as typically served. Gamja-jeon is the one exception worth asking about — some restaurants make it from just grated potato with little or no wheat flour, though recipes vary by kitchen, so it's worth confirming rather than assuming.", listOf("Gamja-jeon")))
            BodyText(boldNames("Jjigae is more mixed: sundubu-jjigae is often the safest bet, since it's mainly tofu, egg, and vegetables, but doenjang-jjigae and kimchi-jjigae can still contain wheat through the doenjang, gochujang, or soy sauce used in the base — some brands are wheat-free and some aren't, so check with the kitchen or look for a confirmed gluten-free option rather than assuming any jjigae is automatically safe.", listOf("Jjigae", "sundubu-jjigae", "doenjang-jjigae", "kimchi-jjigae")))

            SectionHeader("More Foods & Trends")
            BodyText(boldNames("Gimbap (김밥) is one of Korea's most everyday foods — seasoned rice and fillings (pickled radish, carrot, spinach, egg, and a protein) rolled tightly in a sheet of seaweed and sliced into rounds. Common variations include chamchi gimbap (tuna mayo), cheese gimbap, and yachae (vegetable) gimbap. It's sold everywhere from convenience stores — often as a triangle-shaped snack called samgak-gimbap — to dedicated gimbap restaurants, making it one of the easiest, cheapest meals to grab on the go.", listOf("Gimbap", "chamchi gimbap", "cheese gimbap", "yachae", "samgak-gimbap")))
            BodyText(boldNames("The fish-shaped pastry filled with red bean is bungeoppang (붕어빵, \"carp bread\") — a waffle-like pastry baked in a fish-shaped mold, traditionally filled with sweet red bean paste, and sold from street carts especially in the colder months. Look closely and you may also spot its cousin, ingeoppang (잉어빵, \"common carp bread\") — a thinner, chewier version made with a glutinous-rice batter that lets the red bean filling show through a more translucent skin. They're genuinely different snacks, not just regional spellings, and ingeoppang has become the more popular of the two in many areas in recent years.", listOf("bungeoppang", "ingeoppang")))
            BodyText(boldNames("Korea also has several beef-based soups worth trying, though most are technically \"tang\" (soup) rather than thick jjigae-style stews. Galbitang (갈비탕) is a clear, light beef short-rib soup with royal-court origins, often served at celebrations. Yukgaejang (육개장) is a spicy, smoky shredded-beef soup loaded with vegetables like gosari (fernbrake) — the heartiest and most stew-like of the group. Seolleongtang (설렁탕) is a milky ox-bone soup, mild and everyday (often eaten for breakfast) — it's frequently confused with beef stew, but it's really a long-simmered bone broth rather than a rib-meat stew.", listOf("Galbitang", "Yukgaejang", "Seolleongtang")))
            BodyText(boldNames("Malatang (마라탕) has exploded in popularity in Korea, especially among people in their 20s and 30s — it's a self-serve hot pot originally from Sichuan, China: you pick your own vegetables, tofu, noodles, and meat or seafood balls, they're weighed at the counter, then cooked in a numbing-spicy mala broth. It's worth noting malatang isn't a traditional Korean dish — it's a Chinese import — but malatang restaurants have become common in commercial districts across Seoul.", listOf("Malatang")))
            BodyText(boldNames("Tanghulu (탕후루) is another recent Chinese-origin food trend that swept Korea, especially in 2023 — skewered fruit (strawberry, grape, tangerine, and more) dipped in a hard candy shell. It was historically sold by vendors in Incheon's Chinatown and in Seoul neighborhoods like Myeongdong and Hongdae, before exploding into a mainstream, social-media-driven trend nationwide. Dalkom Wang Ga Tanghulu is the best-known chain, having grown from roughly 50 to over 300 stores within just months during the 2023 boom.", listOf("Tanghulu", "Dalkom Wang Ga Tanghulu")))
        }
    }
}
