package com.example.linuxapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linuxapp.R

@Composable
fun HomeScreen(
    onLinuxUsage: () -> Unit,
    onLinuxUsage2: () -> Unit,
    onShellScripting: () -> Unit,
    onLinuxHistory: () -> Unit,
    onUserMode: () -> Unit,
    onKernelMode: () -> Unit,
    onEbpf: () -> Unit,
    onPermissions: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.linux_hub_background),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HomeButton(label = "Linux Usage", onClick = onLinuxUsage)
            Spacer(modifier = Modifier.height(12.dp))
            HomeButton(label = "Linux Usage 2", onClick = onLinuxUsage2)
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
            Spacer(modifier = Modifier.height(12.dp))
            HomeButton(label = "Advanced Topics", onClick = onPermissions)
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .border(1.dp, Color(0xFF00FF41), RoundedCornerShape(4.dp))
                    .background(Color.Black, RoundedCornerShape(4.dp))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "v4.6",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
        }
    }
}

@Composable
private fun HomeButton(label: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        border = BorderStroke(1.dp, Color(0xFF00FF41)),
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
