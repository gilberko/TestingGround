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
fun TokyoScreen(
    onBack: () -> Unit,
    onOpenParksAndAttractions: () -> Unit,
    onOpenCityRegions: () -> Unit,
    onOpenAboutFood: () -> Unit,
    onOpenSpecialCafes: () -> Unit
) {
    ScreenScaffold(title = "Tokyo", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Button(
                onClick = onOpenParksAndAttractions,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Parks and Attractions") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenCityRegions,
                modifier = Modifier.fillMaxWidth()
            ) { Text("City Regions") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenAboutFood,
                modifier = Modifier.fillMaxWidth()
            ) { Text("About Food") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenSpecialCafes,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Special Cafes") }
        }
    }
}
