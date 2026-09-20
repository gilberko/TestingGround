package com.gilberko.japan.screens

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
fun EatingInJapanScreen(
    onBack: () -> Unit,
    onOpenLocalFood: () -> Unit,
    onOpenFoodAllergenSafety: () -> Unit,
    onOpenFoodAndCafes: () -> Unit
) {
    ScreenScaffold(title = "Eating In Japan", onBack = onBack, showBackground = true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Button(
                onClick = onOpenLocalFood,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Local Food") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenFoodAllergenSafety,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Food Allergen Safety") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenFoodAndCafes,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Restaurants, Food Chains and Cafes") }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
