package com.example.linuxapp.screens

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onLinuxUsage: () -> Unit,
    onShellScripting: () -> Unit,
    onLinuxHistory: () -> Unit,
    onUserMode: () -> Unit,
    onKernelMode: () -> Unit,
    onEbpf: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Stranger In A Strange Land",
                color = Color(0xFF00FF41),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Linux Edition",
                color = Color(0xFF00FF41),
                fontSize = 18.sp,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(48.dp))
            HomeButton(label = "Linux Usage", onClick = onLinuxUsage)
            Spacer(modifier = Modifier.height(12.dp))
            HomeButton(label = "Linux Shell Scripting", onClick = onShellScripting)
            Spacer(modifier = Modifier.height(12.dp))
            HomeButton(label = "Linux History", onClick = onLinuxHistory)
            Spacer(modifier = Modifier.height(12.dp))
            HomeButton(label = "Linux User Mode Programming", onClick = onUserMode)
            Spacer(modifier = Modifier.height(12.dp))
            HomeButton(label = "Linux Kernel Mode Programming", onClick = onKernelMode)
            Spacer(modifier = Modifier.height(12.dp))
            HomeButton(label = "eBPF", onClick = onEbpf)
        }
        Text(
            text = "v0.6",
            color = Color.White.copy(alpha = 0.4f),
            fontSize = 12.sp,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}

@Composable
private fun HomeButton(label: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        )
    ) {
        Text(
            text = label,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Medium
        )
    }
}
