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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LearningScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 32.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("the_letters") }, modifier = Modifier.weight(1f)) { Text("The Letters") }
            Button(onClick = { navController.navigate("vowels") }, modifier = Modifier.weight(1f)) { Text("Vowels") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("spoken_vs_msa") }, modifier = Modifier.weight(1f)) { Text("Spoken Arabic vs MSA") }
            Button(onClick = { navController.navigate("negation") }, modifier = Modifier.weight(1f)) { Text("Negation") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("questions") }, modifier = Modifier.weight(1f)) { Text("Questions") }
            Button(onClick = { navController.navigate("subject_pronouns") }, modifier = Modifier.weight(1f)) { Text("Subject Pronouns") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("possessives") }, modifier = Modifier.weight(1f)) { Text("Possessives") }
            Button(onClick = { navController.navigate("object_pronouns") }, modifier = Modifier.weight(1f)) { Text("Object Pronouns") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("past_tense") }, modifier = Modifier.weight(1f)) { Text("Past Tense") }
            Button(onClick = { navController.navigate("future_tense") }, modifier = Modifier.weight(1f)) { Text("Future Tense") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("adjectives") }, modifier = Modifier.weight(1f)) { Text("Adjectives") }
            Button(onClick = { navController.navigate("adverbs") }, modifier = Modifier.weight(1f)) { Text("Adverbs") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("conditions") }, modifier = Modifier.weight(1f)) { Text("Conditions") }
            Button(onClick = { navController.navigate("letters_for_hebrew") }, modifier = Modifier.weight(1f)) { Text("The Letters for Hebrew Speakers") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("prepositions") }, modifier = Modifier.weight(1f)) { Text("Prepositions") }
            Button(onClick = { navController.navigate("passive_voice") }, modifier = Modifier.weight(1f)) { Text("Passive Voice") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("present_tense") }, modifier = Modifier.weight(1f)) { Text("Present Tense") }
            Button(onClick = { navController.navigate("continuous") }, modifier = Modifier.weight(1f)) { Text("Continuous") }
        }
        Button(
            onClick = { navController.navigate("imperative") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Imperative")
        }
    }
}
