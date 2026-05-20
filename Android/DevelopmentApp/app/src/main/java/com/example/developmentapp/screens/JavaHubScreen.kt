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
fun JavaHubScreen(
    onBack: () -> Unit,
    onAboutJava: () -> Unit,
    onHelloWorld: () -> Unit,
    onBasicTypes: () -> Unit,
    onCondLoops: () -> Unit,
    onBasicSyntax: () -> Unit,
    onClasses101: () -> Unit,
    onPackages: () -> Unit,
    onClasses2: () -> Unit,
    onInterfaces: () -> Unit,
    onJni: () -> Unit,
    onGenerics: () -> Unit,
    onLambdas: () -> Unit,
    onDataStructures: () -> Unit,
    onExceptions: () -> Unit,
    onThreadingSync: () -> Unit,
    onEnums: () -> Unit,
    onIO: () -> Unit,
    onAvailableLibraries: () -> Unit,
    onAwt: () -> Unit,
    onSwing: () -> Unit
) {
    HubBackground {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java",
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
            JavaButtonRow("About Java",               onAboutJava,
                          "Hello, World!",            onHelloWorld)
            Spacer(Modifier.height(8.dp))
            JavaButtonRow("Basic Types and Operators", onBasicTypes,
                          "Conditionals and Loops",   onCondLoops)
            Spacer(Modifier.height(8.dp))
            JavaButtonRow("Basic Syntax",              onBasicSyntax,
                          "Classes 101",               onClasses101)
            Spacer(Modifier.height(8.dp))
            JavaButtonRow("Packages",                  onPackages,
                          "Classes - Part 2",          onClasses2)
            Spacer(Modifier.height(8.dp))
            JavaButtonRow("Interfaces",                onInterfaces,
                          "JNI",                       onJni)
            Spacer(Modifier.height(8.dp))
            JavaButtonRow("Lambdas",          onLambdas,
                          "Data Structures",   onDataStructures)
            Spacer(Modifier.height(8.dp))
            JavaButtonRow("Exceptions",        onExceptions,
                          "Threading & Sync",  onThreadingSync)
            Spacer(Modifier.height(8.dp))
            JavaButtonRow("Enums",  onEnums,
                          "I/O",    onIO)
            Spacer(Modifier.height(8.dp))
            JavaButtonRow("Available Libraries", onAvailableLibraries,
                          "AWT",                 onAwt)
            Spacer(Modifier.height(8.dp))
            JavaButtonRow("Swing",    onSwing,
                          "Generics", onGenerics)
            Spacer(Modifier.height(24.dp))
        }
    }
    }
}

@Composable
private fun JavaButtonRow(
    label1: String, onClick1: () -> Unit,
    label2: String, onClick2: () -> Unit
) {
    Row(
        modifier              = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        JavaHubButton(label1, onClick1, Modifier.weight(1f))
        JavaHubButton(label2, onClick2, Modifier.weight(1f))
    }
}

@Composable
private fun JavaHubButton(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
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
