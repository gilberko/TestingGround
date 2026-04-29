package com.example.developmentapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RustHubScreen(
    onBack: () -> Unit,
    onAboutRust: () -> Unit,
    onHelloWorld: () -> Unit,
    onVariables: () -> Unit,
    onComparisonsLoops: () -> Unit,
    onFunctions: () -> Unit,
    onOwnershipBorrowing: () -> Unit,
    onEnumsStructs: () -> Unit,
    onTraits: () -> Unit,
    onTuplesArraysSlices: () -> Unit,
    onVectorsStrings: () -> Unit,
    onModulesCrates: () -> Unit,
    onClosures: () -> Unit,
    onOptionsResult: () -> Unit,
    onStdLib: () -> Unit,
    onCommentsDocs: () -> Unit,
    onThreads: () -> Unit,
    onUnsafe: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Rust",
                        color      = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize   = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint               = Color.White
                        )
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
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RustButtonRow("About Rust",                        onAboutRust,
                          "Hello, World!",                     onHelloWorld)
            Spacer(Modifier.height(8.dp))
            RustButtonRow("Variables",                         onVariables,
                          "Comparisons and Loops",             onComparisonsLoops)
            Spacer(Modifier.height(8.dp))
            RustButtonRow("Functions",                               onFunctions,
                          "Lifetimes, Statics, Ownership and Borrowing", onOwnershipBorrowing)
            Spacer(Modifier.height(8.dp))
            RustButtonRow("Enums and Structs",                 onEnumsStructs,
                          "Traits and Generic Functions",      onTraits)
            Spacer(Modifier.height(8.dp))
            RustButtonRow("Tuples, Arrays and Slices",         onTuplesArraysSlices,
                          "Vectors and Strings",               onVectorsStrings)
            Spacer(Modifier.height(8.dp))
            RustButtonRow("Modules and Crates",                onModulesCrates,
                          "Closures",                          onClosures)
            Spacer(Modifier.height(8.dp))
            RustButtonRow("Options, Result, and Error Handling", onOptionsResult,
                          "Standard Library",                    onStdLib)
            Spacer(Modifier.height(8.dp))
            RustButtonRow("Comments and Documentation",          onCommentsDocs,
                          "Threads",                             onThreads)
            Spacer(Modifier.height(8.dp))
            RustHubButton(
                label    = "Unsafe",
                onClick  = onUnsafe,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun RustButtonRow(
    label1: String, onClick1: () -> Unit,
    label2: String, onClick2: () -> Unit
) {
    Row(
        modifier              = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RustHubButton(label1, onClick1, Modifier.weight(1f))
        RustHubButton(label2, onClick2, Modifier.weight(1f))
    }
}

@Composable
private fun RustHubButton(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick  = onClick,
        modifier = modifier.heightIn(min = 52.dp),
        border   = BorderStroke(1.dp, Color(0xFF00FF41)),
        colors   = ButtonDefaults.outlinedButtonColors(
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
