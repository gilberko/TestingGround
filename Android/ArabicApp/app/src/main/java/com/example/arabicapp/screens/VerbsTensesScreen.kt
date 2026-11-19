package com.example.arabicapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
fun VerbsTensesScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 32.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("verb_conjugation_overview") }, modifier = Modifier.weight(1f)) { Text("Verb Conjugation Overview") }
            Button(onClick = { navController.navigate("verb_forms") }, modifier = Modifier.weight(1f)) { Text("Verb Forms") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("present_tense") }, modifier = Modifier.weight(1f)) { Text("Present Tense") }
            Button(onClick = { navController.navigate("past_tense") }, modifier = Modifier.weight(1f)) { Text("Past Tense") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("future_tense") }, modifier = Modifier.weight(1f)) { Text("Future Tense") }
            Button(onClick = { navController.navigate("imperative") }, modifier = Modifier.weight(1f)) { Text("Imperative") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("continuous") }, modifier = Modifier.weight(1f)) { Text("Continuous") }
            Button(onClick = { navController.navigate("passive_voice") }, modifier = Modifier.weight(1f)) { Text("Passive Voice") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("conditions") }, modifier = Modifier.weight(1f)) { Text("Conditions") }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
