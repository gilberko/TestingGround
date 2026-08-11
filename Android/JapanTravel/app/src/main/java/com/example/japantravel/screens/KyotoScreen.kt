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
fun KyotoScreen(
    onBack: () -> Unit,
    onOpenCityRegions: () -> Unit,
    onOpenParksAndAttractions: () -> Unit,
    onOpenTeamLabBiovortex: () -> Unit
) {
    ScreenScaffold(title = "Kyoto", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Button(
                onClick = onOpenCityRegions,
                modifier = Modifier.fillMaxWidth()
            ) { Text("City Regions") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenParksAndAttractions,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Parks and Attractions") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenTeamLabBiovortex,
                modifier = Modifier.fillMaxWidth()
            ) { Text("teamLab Biovortex Kyoto") }
        }
    }
}
