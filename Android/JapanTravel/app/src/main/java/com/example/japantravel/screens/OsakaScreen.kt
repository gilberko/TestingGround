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
fun OsakaScreen(
    onBack: () -> Unit,
    onOpenParksAndAttractions: () -> Unit,
    onOpenOverview: () -> Unit,
    onOpenTeamLabBotanicalGarden: () -> Unit,
    onOpenGlutenFreeAndKeto: () -> Unit,
    onOpenGettingAround: () -> Unit
) {
    ScreenScaffold(title = "Osaka", onBack = onBack, showBackground = true) {
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
                onClick = onOpenParksAndAttractions,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Parks and Attractions") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenTeamLabBotanicalGarden,
                modifier = Modifier.fillMaxWidth()
            ) { Text("teamLab Botanical Garden Osaka") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenGlutenFreeAndKeto,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Gluten Free and Keto Friendly") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenGettingAround,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Getting Around") }
        }
    }
}
