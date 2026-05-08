package com.example.linuxapp.screens.kernel

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.linuxapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WritingKernelModulesHubScreen(
    onBack: () -> Unit,
    onLkm: () -> Unit,
    onDeviceTypes: () -> Unit,
    onKernelMemoryAccess: () -> Unit,
    onKernelThreading: () -> Unit,
    onPendOperations: () -> Unit,
    onDeferredWork: () -> Unit,
    onNetlink: () -> Unit,
    onNetworking: () -> Unit,
    onFileAccess: () -> Unit,
    onLinuxDeviceModel: () -> Unit,
    onExportingApi: () -> Unit,
    onAltModuleComm: () -> Unit,
    onNotifierChains: () -> Unit
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
                    WritingKernelModulesButton(label = "Loadable Kernel\nModule", modifier = Modifier.weight(1f), onClick = onLkm)
                    WritingKernelModulesButton(label = "Device Types", modifier = Modifier.weight(1f), onClick = onDeviceTypes)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    WritingKernelModulesButton(label = "Kernel Memory\nAccess", modifier = Modifier.weight(1f), onClick = onKernelMemoryAccess)
                    WritingKernelModulesButton(label = "Threading\nAnd Sync", modifier = Modifier.weight(1f), onClick = onKernelThreading)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    WritingKernelModulesButton(label = "How To Pend\nOperations", modifier = Modifier.weight(1f), onClick = onPendOperations)
                    WritingKernelModulesButton(label = "Interrupt Handling\n& Deferred Work", modifier = Modifier.weight(1f), onClick = onDeferredWork)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    WritingKernelModulesButton(label = "Netlink", modifier = Modifier.weight(1f), onClick = onNetlink)
                    WritingKernelModulesButton(label = "Networking", modifier = Modifier.weight(1f), onClick = onNetworking)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    WritingKernelModulesButton(label = "File Access", modifier = Modifier.weight(1f), onClick = onFileAccess)
                    WritingKernelModulesButton(label = "Linux Device\nModel", modifier = Modifier.weight(1f), onClick = onLinuxDeviceModel)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    WritingKernelModulesButton(label = "Exporting Kernel\nModule API", modifier = Modifier.weight(1f), onClick = onExportingApi)
                    WritingKernelModulesButton(label = "Alt. Module-to-\nModule Comm.", modifier = Modifier.weight(1f), onClick = onAltModuleComm)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    WritingKernelModulesButton(label = "Notifier Chains", modifier = Modifier.weight(1f), onClick = onNotifierChains)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun WritingKernelModulesButton(label: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
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
