package com.example.italytravel.screens

import androidx.compose.runtime.Composable

@Composable
fun MilanScreen(
    onBack: () -> Unit,
    onOpenCoffee: () -> Unit,
    onOpenGlutenFree: () -> Unit
) {
    MenuScreen(
        title = "Milan",
        onBack = onBack,
        items = listOf(
            "Coffee" to onOpenCoffee,
            "Gluten Free" to onOpenGlutenFree
        )
    )
}
