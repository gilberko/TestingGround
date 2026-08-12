package com.example.japantravel.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onOpenPlaces: () -> Unit,
    onOpenGeneralInfo: () -> Unit,
    onOpenSimAndEsim: () -> Unit,
    onOpenUsefulApps: () -> Unit,
    onOpenStoresAndChains: () -> Unit,
    onOpenClassicalCulture: () -> Unit,
    onOpenMedicalIssues: () -> Unit,
    onOpenFoodAndCafes: () -> Unit,
    onOpenSpecialAnimalPlaces: () -> Unit,
    onOpenAboutFood: () -> Unit,
    onOpenWeather: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Japan Travel Guide",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedButton(
                onClick = onOpenPlaces,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Places") }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onOpenGeneralInfo,
                modifier = Modifier.fillMaxWidth()
            ) { Text("General Information") }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onOpenSimAndEsim,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Sim and eSim") }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onOpenUsefulApps,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Useful Apps") }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onOpenStoresAndChains,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Stores and Chains") }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onOpenClassicalCulture,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Classical Japanese Culture") }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onOpenMedicalIssues,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Medical Issues") }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onOpenFoodAndCafes,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Food and Cafes") }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onOpenAboutFood,
                modifier = Modifier.fillMaxWidth()
            ) { Text("About Food") }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onOpenSpecialAnimalPlaces,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Special Places with Animals") }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onOpenWeather,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Weather") }
        }
    }
}
