package com.example.japantravel.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HakoneScreen(
    onBack: () -> Unit,
    onOpenRopeway: () -> Unit,
    onOpenGettingThere: () -> Unit
) {
    ScreenScaffold(title = "Hakone", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Button(
                onClick = onOpenGettingThere,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Getting There") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onOpenRopeway,
                modifier = Modifier.fillMaxWidth()
            ) { Text("The Ropeway") }
        }
    }
}
