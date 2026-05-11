package com.example.developmentapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
fun GoHubScreen(
    onBack: () -> Unit,
    onAboutGo: () -> Unit,
    onGettingStarted: () -> Unit,
    onDataTypes: () -> Unit,
    onLooping: () -> Unit,
    onConditions: () -> Unit,
    onFunctionsGoto: () -> Unit,
    onAnonFuncs: () -> Unit,
    onStructsArraysSlices: () -> Unit,
    onPointersAddressable: () -> Unit,
    onMethods: () -> Unit,
    onPackagesImports: () -> Unit,
    onMaps: () -> Unit,
    onGoroutinesSync: () -> Unit,
    onChannels: () -> Unit,
    onNewMake: () -> Unit,
    onErrorHandling: () -> Unit,
    onTypesInterfaces: () -> Unit,
    onStandardLibrary: () -> Unit
) {
    HubBackground {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go",
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
        containerColor = Color.Transparent
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))
            GoButtonRow("About Go",                         onAboutGo,
                        "Getting Started",                  onGettingStarted)
            Spacer(Modifier.height(8.dp))
            GoButtonRow("Data Types and Basic Variables",   onDataTypes,
                        "Looping",                          onLooping)
            Spacer(Modifier.height(8.dp))
            GoButtonRow("Conditions",                       onConditions,
                        "Functions, Goto and Defer 101",    onFunctionsGoto)
            Spacer(Modifier.height(8.dp))
            GoButtonRow("Anonymous Functions",              onAnonFuncs,
                        "Structs, Arrays and Slices",       onStructsArraysSlices)
            Spacer(Modifier.height(8.dp))
            GoButtonRow("Pointers and Addressable Objects", onPointersAddressable,
                        "Methods",                          onMethods)
            Spacer(Modifier.height(8.dp))
            GoButtonRow("Packages and Imports",             onPackagesImports,
                        "Maps",                             onMaps)
            Spacer(Modifier.height(8.dp))
            GoButtonRow("Goroutines and Sync",              onGoroutinesSync,
                        "Channels",                         onChannels)
            Spacer(Modifier.height(8.dp))
            GoButtonRow("Error Handling",                   onErrorHandling,
                        "Types and Interfaces",             onTypesInterfaces)
            Spacer(Modifier.height(8.dp))
            GoButtonRow("Standard Library",                 onStandardLibrary,
                        "About new and make",               onNewMake)
            Spacer(Modifier.height(24.dp))
        }
    }
    }
}

@Composable
private fun GoButtonRow(
    label1: String, onClick1: () -> Unit,
    label2: String, onClick2: () -> Unit
) {
    Row(
        modifier              = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GoHubButton(label1, onClick1, Modifier.weight(1f))
        GoHubButton(label2, onClick2, Modifier.weight(1f))
    }
}

@Composable
private fun GoHubButton(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick  = onClick,
        modifier = modifier.height(52.dp),
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
