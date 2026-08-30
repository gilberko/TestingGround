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
fun OsakaParksScreen(
    onBack: () -> Unit,
    onOpenUsj: () -> Unit,
    onOpenTeamLabBotanicalGarden: () -> Unit
) {
    ScreenScaffold(title = "Parks and Attractions", onBack = onBack, showBackground = true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Button(
                onClick = onOpenUsj,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Universal Studios Japan") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenTeamLabBotanicalGarden,
                modifier = Modifier.fillMaxWidth()
            ) { Text("teamLab Botanical Garden Osaka") }
        }
    }
}
