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
fun SeoulDayTripsScreen(
    onBack: () -> Unit,
    onOpenSuwon: () -> Unit,
    onOpenDmz: () -> Unit,
    onOpenKoreanFolkVillage: () -> Unit,
    onOpenNamiIsland: () -> Unit,
    onOpenGangneung: () -> Unit
) {
    ScreenScaffold(title = "Day Trips", onBack = onBack, showBackground = true) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.fillMaxHeight(0.35f))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp)
            ) {
                Button(onClick = onOpenSuwon, modifier = Modifier.fillMaxWidth()) {
                    Text("Suwon")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onOpenDmz, modifier = Modifier.fillMaxWidth()) {
                    Text("DMZ")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onOpenKoreanFolkVillage, modifier = Modifier.fillMaxWidth()) {
                    Text("Korean Folk Village")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onOpenNamiIsland, modifier = Modifier.fillMaxWidth()) {
                    Text("Nami Island / Gapyeong")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onOpenGangneung, modifier = Modifier.fillMaxWidth()) {
                    Text("Gangneung")
                }
            }
        }
    }
}
