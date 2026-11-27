package com.example.italytravel.screens

import androidx.compose.runtime.Composable

// Placeholder screens for the remaining Rome topics. Each can become a select
// screen (MenuScreen with buttons) or an information screen (InfoScreen) as content is added.

@Composable
fun RomeGeneralInformationScreen(onBack: () -> Unit) {
    InfoScreen(title = "Rome - General Information", onBack = onBack) {
        BodyText("Content coming soon.")
    }
}
