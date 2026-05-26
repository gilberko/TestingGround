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
fun LearningScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 32.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navController.navigate("the_letters") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("The Letters")
        }
        Button(
            onClick = { navController.navigate("spoken_vs_msa") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Spoken Arabic vs MSA")
        }
        Button(
            onClick = { navController.navigate("negation") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Negation")
        }
        Button(
            onClick = { navController.navigate("questions") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Questions")
        }
        Button(
            onClick = { navController.navigate("subject_pronouns") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Subject Pronouns")
        }
        Button(
            onClick = { navController.navigate("possessives") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Possessives")
        }
        Button(
            onClick = { navController.navigate("object_pronouns") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Object Pronouns")
        }
        Button(
            onClick = { navController.navigate("past_tense") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Past Tense")
        }
        Button(
            onClick = { navController.navigate("future_tense") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Future Tense")
        }
        Button(
            onClick = { navController.navigate("adjectives") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Adjectives")
        }
        Button(
            onClick = { navController.navigate("adverbs") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Adverbs")
        }
        Button(
            onClick = { navController.navigate("conditions") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Conditions")
        }
        Button(
            onClick = { navController.navigate("letters_for_hebrew") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("The Letters for Hebrew Speakers")
        }
    }
}
