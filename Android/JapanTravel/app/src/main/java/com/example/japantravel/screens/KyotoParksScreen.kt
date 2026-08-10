package com.example.japantravel.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun KyotoParksScreen(
    onBack: () -> Unit,
    onOpenNinjaMuseum: () -> Unit,
    onOpenNintendoMuseum: () -> Unit,
    onOpenOverview: () -> Unit
) {
    ScreenScaffold(title = "Parks and Attractions", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Button(
                onClick = onOpenOverview,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Overview") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenNinjaMuseum,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Ninja Museum") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenNintendoMuseum,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Nintendo Museum") }
        }
    }
}
