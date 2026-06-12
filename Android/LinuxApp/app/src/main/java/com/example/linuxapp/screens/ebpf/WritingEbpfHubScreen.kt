package com.example.linuxapp.screens.ebpf

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
fun WritingEbpfHubScreen(
    onBack: () -> Unit,
    onEbpfBasicCSyntax: () -> Unit,
    onEbpfCompiling: () -> Unit,
    onEbpfPythonBcc: () -> Unit,
    onEbpfSimpleExample: () -> Unit,
    onEbpfAdvanced: () -> Unit,
    onEbpfSharingData: () -> Unit,
    onEbpfKptrs: () -> Unit,
    onEbpfModifyReturn: () -> Unit,
    onEbpfSamplePrograms: () -> Unit,
    onBpftrace: () -> Unit,
    onBpftraceScriptSyntax: () -> Unit
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
                painter = painterResource(id = R.drawable.linux_hub_background),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    WritingEbpfButton(label = "Basic C\nSyntax", onClick = onEbpfBasicCSyntax, modifier = Modifier.weight(1f))
                    WritingEbpfButton(label = "Compiling C\neBPF Program", onClick = onEbpfCompiling, modifier = Modifier.weight(1f))
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    WritingEbpfButton(label = "Python\nand BCC", onClick = onEbpfPythonBcc, modifier = Modifier.weight(1f))
                    WritingEbpfButton(label = "A Simple\nExample", onClick = onEbpfSimpleExample, modifier = Modifier.weight(1f))
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    WritingEbpfButton(label = "A Bit More\nAdvanced", onClick = onEbpfAdvanced, modifier = Modifier.weight(1f))
                    WritingEbpfButton(label = "Sharing\nData", onClick = onEbpfSharingData, modifier = Modifier.weight(1f))
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    WritingEbpfButton(label = "kptrs", onClick = onEbpfKptrs, modifier = Modifier.weight(1f))
                    WritingEbpfButton(label = "Modify Return\nValue", onClick = onEbpfModifyReturn, modifier = Modifier.weight(1f))
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    WritingEbpfButton(label = "Sample\nPrograms", onClick = onEbpfSamplePrograms, modifier = Modifier.weight(1f))
                    WritingEbpfButton(label = "bpftrace", onClick = onBpftrace, modifier = Modifier.weight(1f))
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    WritingEbpfButton(label = "bpftrace\nScript Syntax", onClick = onBpftraceScriptSyntax, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun WritingEbpfButton(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(64.dp),
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
