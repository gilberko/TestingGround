package com.example.italytravel.screens

import androidx.compose.runtime.Composable

@Composable
fun RomeScreen(
    onBack: () -> Unit,
    onOpenNature: () -> Unit,
    onOpenCoffee: () -> Unit,
    onOpenFood: () -> Unit,
    onOpenFornoPizza: () -> Unit,
    onOpenKoreanFood: () -> Unit,
    onOpenIceCream: () -> Unit,
    onOpenGlutenFree: () -> Unit,
    onOpenFastFood: () -> Unit,
    onOpenShopping: () -> Unit,
    onOpenShoppingCenters: () -> Unit,
    onOpenSupermarkets: () -> Unit,
    onOpenAttractions: () -> Unit,
    onOpenGeneralInfo: () -> Unit,
    onOpenTransport: () -> Unit
) {
    MenuScreen(
        title = "Rome",
        onBack = onBack,
        items = listOf(
            "Nature And Day Trips" to onOpenNature,
            "Coffee" to onOpenCoffee,
            "Food" to onOpenFood,
            "Forno and Rome Pizza (cut to size)" to onOpenFornoPizza,
            "Korean Food" to onOpenKoreanFood,
            "Ice Cream" to onOpenIceCream,
            "Gluten Free" to onOpenGlutenFree,
            "Fast Food Chains" to onOpenFastFood,
            "Shopping" to onOpenShopping,
            "Shopping Centers" to onOpenShoppingCenters,
            "Supermarkets" to onOpenSupermarkets,
            "Attractions" to onOpenAttractions,
            "Public Transportation" to onOpenTransport,
            "General Information" to onOpenGeneralInfo
        )
    )
}
