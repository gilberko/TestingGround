package com.example.italytravel.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/** Main screen: one button per city. No back button. */
@Composable
fun MainScreen(
    onOpenRome: () -> Unit,
    onOpenMilan: () -> Unit,
    onOpenRideApps: () -> Unit
) {
    AppBackground {
        Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.Transparent) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                MenuButtons(
                    items = listOf(
                        "Rome" to onOpenRome,
                        "Milan" to onOpenMilan,
                        "Public Transportation Apps" to onOpenRideApps
                    ),
                    header = {
                        OverlayText("Italy Family Adventure", style = MaterialTheme.typography.headlineMedium, bold = true)
                    }
                )
            }
        }
    }
}
