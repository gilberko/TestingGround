package com.example.italytravel.screens

import androidx.compose.runtime.Composable

@Composable
fun RomeScreen(
    onBack: () -> Unit,
    onOpenNature: () -> Unit,
    onOpenCoffee: () -> Unit,
    onOpenFood: () -> Unit,
    onOpenShopping: () -> Unit,
    onOpenAttractions: () -> Unit,
    onOpenGeneralInfo: () -> Unit
) {
    MenuScreen(
        title = "Rome",
        onBack = onBack,
        items = listOf(
            "Nature And Day Trips" to onOpenNature,
            "Coffee" to onOpenCoffee,
            "Food" to onOpenFood,
            "Shopping" to onOpenShopping,
            "Attractions" to onOpenAttractions,
            "General Information" to onOpenGeneralInfo
        )
    )
}
