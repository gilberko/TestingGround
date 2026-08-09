package com.example.japantravel.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PlaceDetailScreen(
    placeName: String,
    onBack: () -> Unit
) {
    ScreenScaffold(title = placeName, onBack = onBack) {
        Text(
            text = "Content for $placeName is coming soon.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxSize().padding(24.dp)
        )
    }
}
