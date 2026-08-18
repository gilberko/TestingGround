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
fun KyotoMapsScreen(
    onBack: () -> Unit,
    onOpenSubwayMap: () -> Unit,
    onOpenCityMap: () -> Unit
) {
    ScreenScaffold(title = "Maps", onBack = onBack, showBackground = true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Button(
                onClick = onOpenSubwayMap,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Subway Map") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenCityMap,
                modifier = Modifier.fillMaxWidth()
            ) { Text("City Map") }
        }
    }
}
