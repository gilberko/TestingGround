package com.example.arabicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.arabicapp.screens.AdjectivesScreen
import com.example.arabicapp.screens.AdverbsScreen
import com.example.arabicapp.screens.CommonAdjectivesScreen
import com.example.arabicapp.screens.CommonAdverbsScreen
import com.example.arabicapp.screens.CommonVerbsScreen
import com.example.arabicapp.screens.CommonWordsScreen
import com.example.arabicapp.screens.ConditionsScreen
import com.example.arabicapp.screens.DictionaryScreen
import com.example.arabicapp.screens.FutureTenseScreen
import com.example.arabicapp.screens.LearningScreen
import com.example.arabicapp.screens.NegationScreen
import com.example.arabicapp.screens.NumbersScreen
import com.example.arabicapp.screens.ObjectPronounsScreen
import com.example.arabicapp.screens.PastTenseScreen
import com.example.arabicapp.screens.PossessivesScreen
import com.example.arabicapp.screens.QuestionsScreen
import com.example.arabicapp.screens.SpokenVsMSAScreen
import com.example.arabicapp.screens.SubjectPronounsScreen
import com.example.arabicapp.screens.LettersForHebrewSpeakersScreen
import com.example.arabicapp.screens.PracticeLettersScreen
import com.example.arabicapp.screens.VowelsScreen
import com.example.arabicapp.screens.TheLettersScreen
import com.example.arabicapp.ui.theme.ArabicAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArabicAppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("learning") {
            ScreenWithBackButton(title = "Learning The Language", onBack = { navController.popBackStack() }) {
                LearningScreen(navController)
            }
        }
        composable("dictionary") {
            ScreenWithBackButton(title = "Simple Dictionary", onBack = { navController.popBackStack() }) {
                DictionaryScreen(navController)
            }
        }
        composable("practice_letters") {
            ScreenWithBackButton(title = "Practice Letters", onBack = { navController.popBackStack() }) {
                PracticeLettersScreen()
            }
        }
        composable("the_letters") {
            ScreenWithBackButton(title = "The Letters", onBack = { navController.popBackStack() }) {
                TheLettersScreen()
            }
        }
        composable("spoken_vs_msa") {
            ScreenWithBackButton(title = "Spoken Arabic vs MSA", onBack = { navController.popBackStack() }) {
                SpokenVsMSAScreen()
            }
        }
        composable("negation") {
            ScreenWithBackButton(title = "Negation", onBack = { navController.popBackStack() }) {
                NegationScreen()
            }
        }
        composable("questions") {
            ScreenWithBackButton(title = "Questions", onBack = { navController.popBackStack() }) {
                QuestionsScreen()
            }
        }
        composable("subject_pronouns") {
            ScreenWithBackButton(title = "Subject Pronouns", onBack = { navController.popBackStack() }) {
                SubjectPronounsScreen()
            }
        }
        composable("numbers") {
            ScreenWithBackButton(title = "The Numbers", onBack = { navController.popBackStack() }) {
                NumbersScreen()
            }
        }
        composable("common_words") {
            ScreenWithBackButton(title = "Common Words", onBack = { navController.popBackStack() }) {
                CommonWordsScreen()
            }
        }
        composable("common_verbs") {
            ScreenWithBackButton(title = "Common Verbs", onBack = { navController.popBackStack() }) {
                CommonVerbsScreen()
            }
        }
        composable("possessives") {
            ScreenWithBackButton(title = "Possessives", onBack = { navController.popBackStack() }) {
                PossessivesScreen()
            }
        }
        composable("object_pronouns") {
            ScreenWithBackButton(title = "Object Pronouns", onBack = { navController.popBackStack() }) {
                ObjectPronounsScreen()
            }
        }
        composable("past_tense") {
            ScreenWithBackButton(title = "Past Tense", onBack = { navController.popBackStack() }) {
                PastTenseScreen()
            }
        }
        composable("future_tense") {
            ScreenWithBackButton(title = "Future Tense", onBack = { navController.popBackStack() }) {
                FutureTenseScreen()
            }
        }
        composable("adjectives") {
            ScreenWithBackButton(title = "Adjectives", onBack = { navController.popBackStack() }) {
                AdjectivesScreen()
            }
        }
        composable("adverbs") {
            ScreenWithBackButton(title = "Adverbs", onBack = { navController.popBackStack() }) {
                AdverbsScreen()
            }
        }
        composable("conditions") {
            ScreenWithBackButton(title = "Conditions", onBack = { navController.popBackStack() }) {
                ConditionsScreen()
            }
        }
        composable("common_adjectives") {
            ScreenWithBackButton(title = "Common Adjectives", onBack = { navController.popBackStack() }) {
                CommonAdjectivesScreen()
            }
        }
        composable("common_adverbs") {
            ScreenWithBackButton(title = "Common Adverbs", onBack = { navController.popBackStack() }) {
                CommonAdverbsScreen()
            }
        }
        composable("letters_for_hebrew") {
            ScreenWithBackButton(title = "The Letters for Hebrew Speakers", onBack = { navController.popBackStack() }) {
                LettersForHebrewSpeakersScreen()
            }
        }
        composable("vowels") {
            ScreenWithBackButton(title = "Vowels", onBack = { navController.popBackStack() }) {
                VowelsScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenWithBackButton(title: String, onBack: () -> Unit, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            content()
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate("learning") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Text("Learning The Language")
            }
            Button(
                onClick = { navController.navigate("dictionary") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Text("Simple Dictionary")
            }
            Button(
                onClick = { navController.navigate("practice_letters") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Practice Letters")
            }
        }
    }
}
