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
fun NamespacesHubScreen(
    onBack: () -> Unit,
    onAboutNamespaces: () -> Unit,
    onPidNamespace: () -> Unit,
    onNetNamespace: () -> Unit,
    onMountNamespace: () -> Unit,
    onUtsNamespace: () -> Unit,
    onIpcNamespace: () -> Unit,
    onUserNamespace: () -> Unit,
    onTimeNamespace: () -> Unit,
    onCgroupNamespace: () -> Unit,
    onAccessingNamespaces: () -> Unit
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
                    NamespacesHubButton(
                        label = "About\nNamespaces",
                        onClick = onAboutNamespaces,
                        modifier = Modifier.weight(1f)
                    )
                    NamespacesHubButton(
                        label = "PID\nNamespace",
                        onClick = onPidNamespace,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    NamespacesHubButton(
                        label = "Net\nNamespace",
                        onClick = onNetNamespace,
                        modifier = Modifier.weight(1f)
                    )
                    NamespacesHubButton(
                        label = "Mount\nNamespace",
                        onClick = onMountNamespace,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    NamespacesHubButton(
                        label = "UTS\nNamespace",
                        onClick = onUtsNamespace,
                        modifier = Modifier.weight(1f)
                    )
                    NamespacesHubButton(
                        label = "IPC\nNamespace",
                        onClick = onIpcNamespace,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    NamespacesHubButton(
                        label = "User\nNamespace",
                        onClick = onUserNamespace,
                        modifier = Modifier.weight(1f)
                    )
                    NamespacesHubButton(
                        label = "Time\nNamespace",
                        onClick = onTimeNamespace,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    NamespacesHubButton(
                        label = "Cgroup\nNamespace",
                        onClick = onCgroupNamespace,
                        modifier = Modifier.weight(1f)
                    )
                    NamespacesHubButton(
                        label = "Accessing\nNamespaces\nFrom The Host",
                        onClick = onAccessingNamespaces,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun NamespacesHubButton(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
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
