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
fun TokyoParksScreen(
    onBack: () -> Unit,
    onOpenDisney: () -> Unit,
    onOpenJoypolis: () -> Unit,
    onOpenTokyoDomeCity: () -> Unit,
    onOpenTeamLabPlanets: () -> Unit,
    onOpenTeamLabBorderless: () -> Unit,
    onOpenGhibliMuseum: () -> Unit,
    onOpenYomiuriland: () -> Unit,
    onOpenSummerland: () -> Unit,
    onOpenSamuraiNinjaTeaCeremony: () -> Unit,
    onOpenWarnerBrosHarryPotter: () -> Unit,
    onOpenToeiAnimationMuseum: () -> Unit
) {
    ScreenScaffold(title = "Parks and Attractions", onBack = onBack, showBackground = true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Button(
                onClick = onOpenDisney,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Disneyland & DisneySea") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenJoypolis,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Joypolis") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenTokyoDomeCity,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Tokyo Dome City") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenTeamLabPlanets,
                modifier = Modifier.fillMaxWidth()
            ) { Text("teamLab Planets") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenTeamLabBorderless,
                modifier = Modifier.fillMaxWidth()
            ) { Text("teamLab Borderless") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenGhibliMuseum,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Ghibli Museum") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenYomiuriland,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Yomiuriland") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenSummerland,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Tokyo Summerland") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenSamuraiNinjaTeaCeremony,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Samurai Ninja Museum + Tea Ceremony") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenWarnerBrosHarryPotter,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Warner Bros. The Making Of Harry Potter") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenToeiAnimationMuseum,
                modifier = Modifier.fillMaxWidth()
            ) { Text("TOEI Animation Museum") }
        }
    }
}
