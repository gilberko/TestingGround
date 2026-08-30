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
fun NaraScreen(
    onBack: () -> Unit,
    onOpenGettingThere: () -> Unit,
    onOpenTemplesAndDeer: () -> Unit
) {
    ScreenScaffold(title = "Nara", onBack = onBack, showBackground = true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Button(
                onClick = onOpenGettingThere,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Getting There") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenTemplesAndDeer,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Nara Park & Temples") }
        }
    }
}
