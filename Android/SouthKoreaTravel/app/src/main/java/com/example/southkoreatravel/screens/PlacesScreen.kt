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
fun PlacesScreen(
    onBack: () -> Unit,
    onOpenSeoul: () -> Unit,
    onOpenBusan: () -> Unit,
    onOpenGyeongju: () -> Unit,
    onOpenJeju: () -> Unit,
    onOpenJeonju: () -> Unit
) {
    ScreenScaffold(title = "Places", onBack = onBack, showBackground = true) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.fillMaxHeight(0.35f))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp)
            ) {
                Button(onClick = onOpenSeoul, modifier = Modifier.fillMaxWidth()) {
                    Text("Seoul")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onOpenBusan, modifier = Modifier.fillMaxWidth()) {
                    Text("Busan")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onOpenGyeongju, modifier = Modifier.fillMaxWidth()) {
                    Text("Gyeongju")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onOpenJeju, modifier = Modifier.fillMaxWidth()) {
                    Text("Jeju")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onOpenJeonju, modifier = Modifier.fillMaxWidth()) {
                    Text("Jeonju")
                }
            }
        }
    }
}
