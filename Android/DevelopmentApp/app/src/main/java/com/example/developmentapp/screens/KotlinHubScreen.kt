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
fun KotlinHubScreen(
    onBack: () -> Unit,
    onAboutKotlin: () -> Unit,
    onHelloWorld: () -> Unit,
    onVariableTypes: () -> Unit,
    onBasicSyntax: () -> Unit,
    onClasses101: () -> Unit,
    onPackages: () -> Unit,
    onClasses2: () -> Unit,
    onLambdas: () -> Unit,
    onGenerics: () -> Unit,
    onCollections: () -> Unit,
    onExceptions: () -> Unit,
    onThreadingSync: () -> Unit,
    onIO: () -> Unit
) {
    HubBackground {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin",
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
            KotlinButtonRow("About Kotlin",    onAboutKotlin,
                            "Hello, World!",   onHelloWorld)
            Spacer(Modifier.height(8.dp))
            KotlinButtonRow("Variable Types",  onVariableTypes,
                            "Basic Syntax",    onBasicSyntax)
            Spacer(Modifier.height(8.dp))
            KotlinButtonRow("Classes 101",     onClasses101,
                            "Packages",        onPackages)
            Spacer(Modifier.height(8.dp))
            KotlinButtonRow("Classes - Part 2", onClasses2,
                            "Lambdas",          onLambdas)
            Spacer(Modifier.height(8.dp))
            KotlinButtonRow("Generics",        onGenerics,
                            "Collections",     onCollections)
            Spacer(Modifier.height(8.dp))
            KotlinButtonRow("Exceptions",      onExceptions,
                            "Threading & Sync", onThreadingSync)
            Spacer(Modifier.height(8.dp))
            KotlinSingleButton("I/O", onIO)
            Spacer(Modifier.height(24.dp))
        }
    }
    }
}

@Composable
private fun KotlinButtonRow(
    label1: String, onClick1: () -> Unit,
    label2: String, onClick2: () -> Unit
) {
    Row(
        modifier              = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        KotlinHubButton(label1, onClick1, Modifier.weight(1f))
        KotlinHubButton(label2, onClick2, Modifier.weight(1f))
    }
}

@Composable
private fun KotlinSingleButton(label: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick  = onClick,
        modifier = Modifier.fillMaxWidth().heightIn(min = 52.dp),
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

@Composable
private fun KotlinHubButton(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
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
