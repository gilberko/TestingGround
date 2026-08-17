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
fun TokyoScreen(
    onBack: () -> Unit,
    onOpenParksAndAttractions: () -> Unit,
    onOpenCityRegions: () -> Unit,
    onOpenSpecialCafes: () -> Unit,
    onOpenOutsideTokyo: () -> Unit,
    onOpenPlacesOfInterest: () -> Unit,
    onOpenGlutenFreeAndKeto: () -> Unit,
    onOpenDayTrips: () -> Unit,
    onOpenAirportTravel: () -> Unit
) {
    ScreenScaffold(title = "Tokyo", onBack = onBack, showBackground = true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
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
                onClick = onOpenSpecialCafes,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Special Cafes") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenPlacesOfInterest,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Places of Interest") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenGlutenFreeAndKeto,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Gluten Free and Keto Friendly") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenDayTrips,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Recommended Day Trips") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenOutsideTokyo,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Outside Tokyo") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenAirportTravel,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Airport Travel") }
        }
    }
}
