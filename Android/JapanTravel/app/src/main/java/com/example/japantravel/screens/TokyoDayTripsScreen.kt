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
fun TokyoDayTripsScreen(
    onBack: () -> Unit,
    onOpenHakone: () -> Unit,
    onOpenNikko: () -> Unit,
    onOpenLakesKawaguchiko: () -> Unit,
    onOpenMountTakao: () -> Unit,
    onOpenGhibliPark: () -> Unit,
    onOpenYokohama: () -> Unit,
    onOpenKawagoe: () -> Unit,
    onOpenOkutamaMitake: () -> Unit
) {
    ScreenScaffold(title = "Recommended Day Trips", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Button(
                onClick = onOpenHakone,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Hakone") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenNikko,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Nikko") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenLakesKawaguchiko,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Lakes Region and Kawaguchiko") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenMountTakao,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Mount Takao") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenGhibliPark,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Ghibli Park") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenYokohama,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Yokohama") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenKawagoe,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Kawagoe") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenOkutamaMitake,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Okutama / Mount Mitake") }
        }
    }
}
