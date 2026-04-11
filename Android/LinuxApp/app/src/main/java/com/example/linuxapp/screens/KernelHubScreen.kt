package com.example.linuxapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import com.example.linuxapp.R
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
    onDeferredWork: () -> Unit,
    onDataStructures: () -> Unit,
    onVfs: () -> Unit,
    onFileAccessWhole: () -> Unit,
    onMemoryAccessWhole: () -> Unit,
    onPendOperations: () -> Unit
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
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.linux_background),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                KernelButton(label = "Loadable Kernel Module", modifier = Modifier.weight(1f), onClick = onLkm)
                KernelButton(label = "Char Device", modifier = Modifier.weight(1f), onClick = onCharDevice)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                KernelButton(label = "Block Device", modifier = Modifier.weight(1f), onClick = onBlockDevice)
                KernelButton(label = "Net Device", modifier = Modifier.weight(1f), onClick = onNetDevice)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                KernelButton(label = "OS Structs", modifier = Modifier.weight(1f), onClick = onOsStructs)
                KernelButton(label = "Low Level Principles", modifier = Modifier.weight(1f), onClick = onLowLevel)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                KernelButton(label = "Low Level Principles Pt. 2", modifier = Modifier.weight(1f), onClick = onLowLevel2)
                KernelButton(label = "Kernel Debugging", modifier = Modifier.weight(1f), onClick = onDebugging)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                KernelButton(label = "Deferred Work", modifier = Modifier.weight(1f), onClick = onDeferredWork)
                KernelButton(label = "Kernel Data Structures", modifier = Modifier.weight(1f), onClick = onDataStructures)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                KernelButton(label = "VFS", modifier = Modifier.weight(1f), onClick = onVfs)
                KernelButton(label = "How To Pend\nOperations", modifier = Modifier.weight(1f), onClick = onPendOperations)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                KernelButton(label = "File Access:\nWhole Picture", modifier = Modifier.weight(1f), onClick = onFileAccessWhole)
                KernelButton(label = "Memory Access:\nWhole Picture", modifier = Modifier.weight(1f), onClick = onMemoryAccessWhole)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        }
    }
}

@Composable
private fun KernelButton(label: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.heightIn(min = 52.dp),
        border = BorderStroke(1.dp, Color(0xFF00FF41)),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        )
    ) {
        Text(
            text = label,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}
