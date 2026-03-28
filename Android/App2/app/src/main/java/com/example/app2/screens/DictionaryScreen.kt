package com.example.app2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.app2.R

private val ButtonGray = Color(0xFF808080)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreen(
    onBack: () -> Unit,
    onPrepositions: () -> Unit,
    onCommonVerbs: () -> Unit,
    onTimeExpressions: () -> Unit,
    onNumbers: () -> Unit,
    onColors: () -> Unit,
    onAdjectives: () -> Unit,
    onAdverbs: () -> Unit,
    onCommonWords: () -> Unit,
    onMovement: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.portugal_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {},
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = "Simple Dictionary",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Dicionário Simples",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(48.dp))
                    Button(
                        onClick = onPrepositions,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonGray, contentColor = Color.White)
                    ) { Text("Prepositions") }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = onCommonVerbs,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonGray, contentColor = Color.White)
                    ) { Text("Common Verbs") }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = onTimeExpressions,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonGray, contentColor = Color.White)
                    ) { Text("Time Expressions") }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = onNumbers,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonGray, contentColor = Color.White)
                    ) { Text("Numbers") }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = onColors,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonGray, contentColor = Color.White)
                    ) { Text("Colors") }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = onAdjectives,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonGray, contentColor = Color.White)
                    ) { Text("Adjectives") }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = onAdverbs,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonGray, contentColor = Color.White)
                    ) { Text("Adverbs") }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = onCommonWords,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonGray, contentColor = Color.White)
                    ) { Text("Common Words") }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = onMovement,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonGray, contentColor = Color.White)
                    ) { Text("Movement") }
                }
            }
        }
    }
}
