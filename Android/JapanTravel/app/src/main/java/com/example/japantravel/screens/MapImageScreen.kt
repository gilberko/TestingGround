package com.example.japantravel.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MapImageScreen(
    title: String,
    onBack: () -> Unit,
    imageRes: Int,
    sourceCredit: String
) {
    ScreenScaffold(title = title, onBack = onBack) {
        Column(modifier = Modifier.fillMaxSize()) {
            ZoomableImage(
                imageRes = imageRes,
                contentDescription = title,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = sourceCredit,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
