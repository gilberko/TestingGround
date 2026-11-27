package com.example.italytravel.screens

import androidx.compose.runtime.Composable

// Placeholder screens for the six Rome topics. Each can become a select
// screen (MenuScreen with buttons) or an information screen (InfoScreen) as content is added.

@Composable
fun RomeNatureScreen(onBack: () -> Unit) {
    InfoScreen(title = "Rome - Nature And Day Trips", onBack = onBack) {
        BodyText("Content coming soon.")
    }
}

@Composable
fun RomeCoffeeScreen(onBack: () -> Unit) {
    InfoScreen(title = "Rome - Coffee", onBack = onBack) {
        BodyText("Content coming soon.")
    }
}

@Composable
fun RomeFoodScreen(onBack: () -> Unit) {
    InfoScreen(title = "Rome - Food", onBack = onBack) {
        BodyText("Content coming soon.")
    }
}

@Composable
fun RomeShoppingScreen(onBack: () -> Unit) {
    InfoScreen(title = "Rome - Shopping", onBack = onBack) {
        BodyText("Content coming soon.")
    }
}

@Composable
fun RomeAttractionsScreen(onBack: () -> Unit) {
    InfoScreen(title = "Rome - Attractions", onBack = onBack) {
        BodyText("Content coming soon.")
    }
}

@Composable
fun RomeGeneralInformationScreen(onBack: () -> Unit) {
    InfoScreen(title = "Rome - General Information", onBack = onBack) {
        BodyText("Content coming soon.")
    }
}
