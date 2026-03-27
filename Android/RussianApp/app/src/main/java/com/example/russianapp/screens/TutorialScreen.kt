package com.example.russianapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialScreen(
    onBack: () -> Unit,
    onAlphabet: () -> Unit,
    onGrammarCases: () -> Unit,
    onVerbConjugation: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tutorial") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedButton(
                onClick = onAlphabet,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Alphabet")
            }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onGrammarCases,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Grammar - Cases")
            }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onVerbConjugation,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Verb Conjugation")
            }
        }
    }
}
