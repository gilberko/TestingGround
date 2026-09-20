package com.gilberko.japan.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
    onOpenRentingCarsAndDriving: () -> Unit,
    onOpenHotelsOnsensRyokans: () -> Unit,
    onOpenUsefulApps: () -> Unit,
    onOpenStoresAndChains: () -> Unit,
    onOpenClassicalCulture: () -> Unit,
    onOpenMedicalIssues: () -> Unit,
    onOpenEatingInJapan: () -> Unit
) {
    Scaffold { innerPadding ->
        HubBackground {
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
            Button(
                onClick = onOpenPlaces,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Where To Visit") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenGeneralInfo,
                modifier = Modifier.fillMaxWidth()
            ) { Text("General Information") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenRentingCarsAndDriving,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Renting Cars And Driving") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenHotelsOnsensRyokans,
                modifier = Modifier.fillMaxWidth()
            ) { Text("About Hotels, Onsens and Ryokans") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenUsefulApps,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Useful Apps") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenStoresAndChains,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Stores and Chains") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenClassicalCulture,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Classical Japanese Culture") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenMedicalIssues,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Medical Issues") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenEatingInJapan,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Eating In Japan") }
        }
        }
    }
}
