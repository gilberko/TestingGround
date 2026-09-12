package com.example.southkoreatravel.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
fun BusanScreen(
    onBack: () -> Unit,
    onOpenRegions: () -> Unit,
    onOpenPlacesOfInterest: () -> Unit,
    onOpenAirportTravel: () -> Unit,
    onOpenGlutenFreeKeto: () -> Unit,
    onOpenWhereToStay: () -> Unit,
    onOpenAttractions: () -> Unit,
    onOpenCafes: () -> Unit
) {
    ScreenScaffold(title = "Busan", onBack = onBack, showBackground = true) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.fillMaxHeight(0.35f))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp)
            ) {
                Button(onClick = onOpenRegions, modifier = Modifier.fillMaxWidth()) {
                    Text("Regions")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onOpenPlacesOfInterest, modifier = Modifier.fillMaxWidth()) {
                    Text("Places Of Interest")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onOpenAirportTravel, modifier = Modifier.fillMaxWidth()) {
                    Text("Airport Travel")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onOpenGlutenFreeKeto, modifier = Modifier.fillMaxWidth()) {
                    Text("Keto Friendly and Gluten Free")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onOpenWhereToStay, modifier = Modifier.fillMaxWidth()) {
                    Text("Where To Stay")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onOpenAttractions, modifier = Modifier.fillMaxWidth()) {
                    Text("Attractions")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onOpenCafes, modifier = Modifier.fillMaxWidth()) {
                    Text("Cafes")
                }
            }
        }
    }
}
