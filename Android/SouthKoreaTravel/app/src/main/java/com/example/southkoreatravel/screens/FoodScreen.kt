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
            BodyText("Tteokbokki is the single most iconic Korean street food — chewy cylindrical rice cakes simmered in a spicy-sweet gochujang (red chili paste) sauce, usually with fish cakes and scallions. You'll find it at street carts, bunsik (casual snack) shops, and dedicated restaurants everywhere.")
            BodyText("Kimchi is Korea's famous fermented, spiced cabbage (or radish) side dish — sour, spicy, and served with almost every meal, not just a street-food item, but essential to understanding Korean food overall.")
            BodyText("Other must-try street snacks: hotteok, a sweet stuffed pancake (honey, brown sugar, or nut filling) especially popular in winter; gimbap, rice and fillings rolled in seaweed, similar in idea to sushi rolls but distinctly Korean in flavor; and bungeoppang, a fish-shaped pastry filled with sweet red bean paste.")

            SectionHeader("Korean Pub Food & BBQ")
            BodyText("Anju is the general term for food eaten alongside alcohol at a Korean bar or pub — in Korean drinking culture, it's considered bad form to drink without eating something alongside it. Classic anju includes fried chicken, pork belly, and pajeon (scallion pancake), usually shared over soju or beer.")
            BodyText("A pojangmacha is a small tent-covered street stall that serves alcohol alongside simple, warming anju like fishcake soup, tteokbokki, and ramyeon — a fun, casual, late-night way to eat and drink like a local, especially popular in the evenings.")
            BodyText("Korean BBQ restaurants (gogijip) are a whole dining experience: you grill meat — usually pork belly (samgyeopsal) or marinated beef (bulgogi/galbi) — yourself at a table-mounted grill, wrapping cooked pieces in lettuce leaves with rice, garlic, and ssamjang (a savory-spicy dipping sauce), alongside an array of free side dishes (banchan).")

            SectionHeader("Fried Chicken & Corn Dogs")
            BodyText("Korean fried chicken (chikin) is a food culture in its own right — twice-fried for extra crunch, often glazed in sauces like soy-garlic (ganjang) or sweet-spicy (yangnyeom), and a hugely popular pairing with beer (a combination affectionately nicknamed \"chimaek,\" chicken + maekju/beer).")
            BodyText("Korean corn dogs (hotdog) have become a street-food phenomenon of their own — a sausage (or sometimes mozzarella cheese, or a sausage-cheese combo) on a stick, coated in a doughy or panko-crumb batter, deep-fried, and then often rolled in sugar or topped with things like ramen-noodle crumbs, fries, or extra cheese. Popular in tourist areas like Myeongdong and Hongdae.")
        }
    }
}
