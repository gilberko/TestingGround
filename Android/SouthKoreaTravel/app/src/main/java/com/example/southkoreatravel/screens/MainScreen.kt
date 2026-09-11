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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    onOpenPlaces: () -> Unit,
    onOpenGeneralInfo: () -> Unit,
    onOpenMedicalIssues: () -> Unit,
    onOpenShopping: () -> Unit,
    onOpenFood: () -> Unit,
    onOpenUsefulApps: () -> Unit,
    onOpenCeliacCard: () -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        HubBackground {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Spacer(modifier = Modifier.fillMaxHeight(0.35f))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp)
                ) {
                    Button(onClick = onOpenPlaces, modifier = Modifier.fillMaxWidth()) {
                        Text("Places")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = onOpenGeneralInfo, modifier = Modifier.fillMaxWidth()) {
                        Text("General Information")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = onOpenMedicalIssues, modifier = Modifier.fillMaxWidth()) {
                        Text("Medical Issues")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = onOpenShopping, modifier = Modifier.fillMaxWidth()) {
                        Text("Shopping")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = onOpenFood, modifier = Modifier.fillMaxWidth()) {
                        Text("Food")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = onOpenUsefulApps, modifier = Modifier.fillMaxWidth()) {
                        Text("Useful Apps")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = onOpenCeliacCard, modifier = Modifier.fillMaxWidth()) {
                        Text("Celiac Card")
                    }
                }
            }
        }
    }
}
