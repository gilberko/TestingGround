package com.example.arabicapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DictionaryScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { navController.navigate("numbers") },
                modifier = Modifier.weight(1f)
            ) { Text("The Numbers") }
            Button(
                onClick = { navController.navigate("common_words") },
                modifier = Modifier.weight(1f)
            ) { Text("Common Words") }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { navController.navigate("common_verbs") },
                modifier = Modifier.weight(1f)
            ) { Text("Common Verbs") }
            Button(
                onClick = { navController.navigate("common_adjectives") },
                modifier = Modifier.weight(1f)
            ) { Text("Common Adjectives") }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { navController.navigate("common_adverbs") },
                modifier = Modifier.weight(1f)
            ) { Text("Common Adverbs") }
            Button(
                onClick = { navController.navigate("colors") },
                modifier = Modifier.weight(1f)
            ) { Text("Colors") }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { navController.navigate("greetings") },
                modifier = Modifier.weight(1f)
            ) { Text("Common Greetings and Expressions") }
            Button(
                onClick = { navController.navigate("foods_eating") },
                modifier = Modifier.weight(1f)
            ) { Text("Foods and Eating") }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { navController.navigate("time_words") },
                modifier = Modifier.weight(1f)
            ) { Text("Time") }
            Button(
                onClick = { navController.navigate("location_movement") },
                modifier = Modifier.weight(1f)
            ) { Text("Location and Movement") }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { navController.navigate("jobs_occupations") },
                modifier = Modifier.weight(1f)
            ) { Text("Jobs and Occupations") }
            Button(
                onClick = { navController.navigate("city_nature") },
                modifier = Modifier.weight(1f)
            ) { Text("City and Nature") }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { navController.navigate("transportation") },
                modifier = Modifier.weight(1f)
            ) { Text("Transportation") }
            Button(
                onClick = { navController.navigate("simple_conj_verbs") },
                modifier = Modifier.weight(1f)
            ) { Text("Simple Conjugated Verbs") }
        }
    }
}
