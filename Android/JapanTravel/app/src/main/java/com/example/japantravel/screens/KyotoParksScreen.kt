package com.example.japantravel.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    onOpenOverview: () -> Unit,
    onOpenTeamLabBiovortex: () -> Unit,
    onOpenToeiStudioPark: () -> Unit
) {
    ScreenScaffold(title = "Parks and Attractions", onBack = onBack, showBackground = true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
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
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenTeamLabBiovortex,
                modifier = Modifier.fillMaxWidth()
            ) { Text("teamLab Biovortex Kyoto") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenToeiStudioPark,
                modifier = Modifier.fillMaxWidth()
            ) { Text("TOEI Kyoto Studio Park") }
        }
    }
}
