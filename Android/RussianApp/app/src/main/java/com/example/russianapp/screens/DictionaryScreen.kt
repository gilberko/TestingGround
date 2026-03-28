package com.example.russianapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.russianapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreen(
    onBack: () -> Unit,
    onVerbs: () -> Unit,
    onColors: () -> Unit,
    onNumbers: () -> Unit,
    onMovement: () -> Unit,
    onAdjectives: () -> Unit,
    onAdverbs: () -> Unit,
    onFood: () -> Unit,
    onPlaces: () -> Unit,
    onPeopleAnimals: () -> Unit,
    onWork: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.russian_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { Text("Dictionary", color = Color.Black) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 32.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                listOf(
                    "Verbs"            to onVerbs,
                    "Colors"           to onColors,
                    "Numbers"          to onNumbers,
                    "Movement"         to onMovement,
                    "Adjectives"       to onAdjectives,
                    "Adverbs"          to onAdverbs,
                    "Food"             to onFood,
                    "Places"           to onPlaces,
                    "People & Animals" to onPeopleAnimals,
                    "Work & Jobs"      to onWork
                ).forEachIndexed { index, (label, action) ->
                    if (index > 0) Spacer(modifier = Modifier.height(12.dp))
                    OutlinedButton(
                        onClick = action,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.Gray.copy(alpha = 0.8f),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(label)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
