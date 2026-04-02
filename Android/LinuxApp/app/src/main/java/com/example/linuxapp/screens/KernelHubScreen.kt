package com.example.linuxapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KernelHubScreen(
    onBack: () -> Unit,
    onLkm: () -> Unit,
    onCharDevice: () -> Unit,
    onBlockDevice: () -> Unit,
    onNetDevice: () -> Unit,
    onLowLevel: () -> Unit,
    onOsStructs: () -> Unit,
    onLowLevel2: () -> Unit,
    onDebugging: () -> Unit,
    onDeferredWork: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Linux Kernel Mode\nProgramming",
                color = Color(0xFF00FF41),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            KernelButton(label = "Loadable Kernel Module", onClick = onLkm)
            Spacer(modifier = Modifier.height(12.dp))
            KernelButton(label = "Char Device", onClick = onCharDevice)
            Spacer(modifier = Modifier.height(12.dp))
            KernelButton(label = "Block Device", onClick = onBlockDevice)
            Spacer(modifier = Modifier.height(12.dp))
            KernelButton(label = "Net Device", onClick = onNetDevice)
            Spacer(modifier = Modifier.height(12.dp))
            KernelButton(label = "OS Structs", onClick = onOsStructs)
            Spacer(modifier = Modifier.height(12.dp))
            KernelButton(label = "Low Level Programming Principles", onClick = onLowLevel)
            Spacer(modifier = Modifier.height(12.dp))
            KernelButton(label = "Low Level Programming Principles Pt. 2", onClick = onLowLevel2)
            Spacer(modifier = Modifier.height(12.dp))
            KernelButton(label = "Kernel Debugging And Tracing", onClick = onDebugging)
            Spacer(modifier = Modifier.height(12.dp))
            KernelButton(label = "Deferred Work", onClick = onDeferredWork)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun KernelButton(label: String, onClick: () -> Unit) {
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
