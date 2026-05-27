package com.example.arabicapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DictionaryScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navController.navigate("numbers") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("The Numbers")
        }
        Button(
            onClick = { navController.navigate("common_words") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Common Words")
        }
        Button(
            onClick = { navController.navigate("common_verbs") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Common Verbs")
        }
        Button(
            onClick = { navController.navigate("common_adjectives") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Common Adjectives")
        }
        Button(
            onClick = { navController.navigate("common_adverbs") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Common Adverbs")
        }
        Button(
            onClick = { navController.navigate("colors") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Colors")
        }
        Button(
            onClick = { navController.navigate("greetings") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Common Greetings and Expressions")
        }
        Button(
            onClick = { navController.navigate("foods_eating") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Foods and Eating")
        }
        Button(
            onClick = { navController.navigate("time_words") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Time")
        }
        Button(
            onClick = { navController.navigate("location_movement") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Location and Movement")
        }
    }
}
