package com.example.japantravel.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OsakaScreen(
    onBack: () -> Unit,
    onOpenParksAndAttractions: () -> Unit
) {
    ScreenScaffold(title = "Osaka", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Button(
                onClick = onOpenParksAndAttractions,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Parks and Attractions") }
        }
    }
}
