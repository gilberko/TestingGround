package com.example.developmentapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onCpp: () -> Unit,
    onRust: () -> Unit,
    onGo: () -> Unit,
    onPython: () -> Unit,
    onAssembly: () -> Unit,
    onDataStructures: () -> Unit,
    onAlgorithms: () -> Unit,
    onTcpIp: () -> Unit,
    onDebuggingProfilingTracing: () -> Unit,
    onAiNeuralNetworks: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 32.dp)
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HomeButtonRow("C/C++", onCpp, "Rust", onRust)
        Spacer(Modifier.height(12.dp))
        HomeButtonRow("Go", onGo, "Python", onPython)
        Spacer(Modifier.height(12.dp))
        HomeButtonRow("Assembly", onAssembly, "Data Structures", onDataStructures)
        Spacer(Modifier.height(12.dp))
        HomeButtonRow("Algorithms", onAlgorithms, "TCP/IP", onTcpIp)
        Spacer(Modifier.height(12.dp))
        HomeButtonRow("Debugging, Profiling And Tracing", onDebuggingProfilingTracing, "AI and Neural Networks", onAiNeuralNetworks)
    }
}

@Composable
private fun HomeButtonRow(
    label1: String, onClick1: () -> Unit,
    label2: String, onClick2: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HomeButton(label1, onClick1, Modifier.weight(1f))
        HomeButton(label2, onClick2, Modifier.weight(1f))
    }
}

@Composable
private fun HomeButton(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick  = onClick,
        modifier = modifier.heightIn(min = 52.dp),
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
            color      = Color(0xFF00FF41),
            textAlign  = TextAlign.Center,
            fontSize   = 11.sp
        )
    }
}
