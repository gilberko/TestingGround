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
            Button(onClick = { navController.navigate("questions") }, modifier = Modifier.weight(1f)) { Text("Questions and Answers") }
            Button(onClick = { navController.navigate("subject_pronouns") }, modifier = Modifier.weight(1f)) { Text("Subject Pronouns") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("possessives") }, modifier = Modifier.weight(1f)) { Text("Possessives") }
            Button(onClick = { navController.navigate("object_pronouns") }, modifier = Modifier.weight(1f)) { Text("Object Pronouns") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("verbs_tenses") }, modifier = Modifier.weight(1f)) { Text("Verbs And Tenses") }
            Button(onClick = { navController.navigate("exists_have_want") }, modifier = Modifier.weight(1f)) { Text("Exists, Have, Want") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("adjectives") }, modifier = Modifier.weight(1f)) { Text("Adjectives") }
            Button(onClick = { navController.navigate("adverbs") }, modifier = Modifier.weight(1f)) { Text("Adverbs") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("letters_for_hebrew") }, modifier = Modifier.weight(1f)) { Text("The Letters for Hebrew Speakers") }
            Button(onClick = { navController.navigate("prepositions") }, modifier = Modifier.weight(1f)) { Text("Prepositions") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("letters_transliteration") }, modifier = Modifier.weight(1f)) { Text("Letters Transliteration") }
            Button(onClick = { navController.navigate("nouns_singular_plural") }, modifier = Modifier.weight(1f)) { Text("Nouns - Singular and Plural") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("comparisons") }, modifier = Modifier.weight(1f)) { Text("Comparisons") }
            Button(onClick = { navController.navigate("construct_state") }, modifier = Modifier.weight(1f)) { Text("Construct State") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("every_some") }, modifier = Modifier.weight(1f)) { Text("Every, Some, . . .") }
            Button(onClick = { navController.navigate("numbers_learning") }, modifier = Modifier.weight(1f)) { Text("Numbers and Time") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("adjectives_adverbs_overview") }, modifier = Modifier.weight(1f)) { Text("Overview of Adjectives and Adverbs") }
            Button(onClick = { navController.navigate("il_article") }, modifier = Modifier.weight(1f)) { Text("il — The Definite Article") }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("this_that_here_there") }, modifier = Modifier.weight(1f)) { Text("This and That, Here and There") }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
