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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionsHubScreen(
    onBack: () -> Unit,
    onFilePermissions: () -> Unit,
    onCgroups: () -> Unit,
    onTun: () -> Unit,
    onBootProcess: () -> Unit,
    onAffinity: () -> Unit,
    onProcessScheduling: () -> Unit,
    onCallingConventions: () -> Unit,
    onLsm: () -> Unit,
    onMakefileCmake: () -> Unit,
    onIptablesNetfilter: () -> Unit,
    onDeviceTrees: () -> Unit,
    onPlugAndPlay: () -> Unit,
    onStackFrames: () -> Unit,
    onKernelVmDebugging: () -> Unit
) {
    Scaffold(
        containerColor = Color.Transparent,
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
        }
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
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PermissionsHubButton(
                        label = "Capabilities, File\nPermissions & chmod",
                        onClick = onFilePermissions,
                        modifier = Modifier.weight(1f)
                    )
                    PermissionsHubButton(
                        label = "cgroups",
                        onClick = onCgroups,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PermissionsHubButton(
                        label = "TUN Device",
                        onClick = onTun,
                        modifier = Modifier.weight(1f)
                    )
                    PermissionsHubButton(
                        label = "Boot Process",
                        onClick = onBootProcess,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PermissionsHubButton(
                        label = "CPU\nAffinity",
                        onClick = onAffinity,
                        modifier = Modifier.weight(1f)
                    )
                    PermissionsHubButton(
                        label = "Process\nScheduling",
                        onClick = onProcessScheduling,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PermissionsHubButton(
                        label = "Calling\nConventions",
                        onClick = onCallingConventions,
                        modifier = Modifier.weight(1f)
                    )
                    PermissionsHubButton(
                        label = "LSM",
                        onClick = onLsm,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PermissionsHubButton(
                        label = "Makefile and\nCMake",
                        onClick = onMakefileCmake,
                        modifier = Modifier.weight(1f)
                    )
                    PermissionsHubButton(
                        label = "iptables &\nnetfilter",
                        onClick = onIptablesNetfilter,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PermissionsHubButton(
                        label = "Device Trees\n& ACPI",
                        onClick = onDeviceTrees,
                        modifier = Modifier.weight(1f)
                    )
                    PermissionsHubButton(
                        label = "Plug And\nPlay",
                        onClick = onPlugAndPlay,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PermissionsHubButton(
                        label = "Stacks &\nStack Frames",
                        onClick = onStackFrames,
                        modifier = Modifier.weight(1f)
                    )
                    PermissionsHubButton(
                        label = "Kernel VM\nDebugging",
                        onClick = onKernelVmDebugging,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun PermissionsHubButton(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.heightIn(min = 64.dp),
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
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}
