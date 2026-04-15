package com.example.developmentapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onCpp: () -> Unit,
    onRust: () -> Unit,
    onGo: () -> Unit,
    onPython: () -> Unit,
    onAssembly: () -> Unit,
    onDataStructures: () -> Unit,
    onAlgorithms: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HomeButton("C/C++",           onCpp)
            Spacer(Modifier.height(12.dp))
            HomeButton("Rust",            onRust)
            Spacer(Modifier.height(12.dp))
            HomeButton("Go",              onGo)
            Spacer(Modifier.height(12.dp))
            HomeButton("Python",          onPython)
            Spacer(Modifier.height(12.dp))
            HomeButton("Assembly",        onAssembly)
            Spacer(Modifier.height(12.dp))
            HomeButton("Data Structures", onDataStructures)
            Spacer(Modifier.height(12.dp))
            HomeButton("Algorithms",      onAlgorithms)
        }
    }
}

@Composable
private fun HomeButton(label: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick  = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        border = BorderStroke(1.dp, Color(0xFF00FF41)),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Black,
            contentColor   = Color(0xFF00FF41)
        )
    ) {
        Text(
            text       = label,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Medium,
            color      = Color(0xFF00FF41)
        )
    }
}
