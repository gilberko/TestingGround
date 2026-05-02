package com.example.czechappredo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeScreen(navController) }
                composable("learning") { LearningHubScreen(navController) }
                composable("letters_pronunciation") { LettersPronunciationScreen(navController) }
                composable("nouns_demonstratives") { NounsAndDemonstrativesScreen(navController) }
                composable("cases") { CasesScreen(navController) }
                composable("present_tense") { PresentTenseVerbConjugationScreen(navController) }
                composable("dictionary") { DictionaryHubScreen(navController) }
                composable("basic_words") { BasicWordsScreen(navController) }
                composable("jobs_professions") { JobsAndProfessionsScreen(navController) }
                composable("places") { PlacesScreen(navController) }
                composable("verbs_of_movement") { VerbsOfMovementScreen(navController) }
                composable("prepositions") { PrepositionsScreen(navController) }
                composable("questions") { QuestionsScreen(navController) }
                composable("useful_verbs") { UsefulVerbsScreen(navController) }
                composable("numbers") { NumbersScreen(navController) }
                composable("movement") { MovementScreen(navController) }
                composable("people_family") { PeopleAndFamilyScreen(navController) }
                composable("transportation") { TransportationScreen(navController) }
                composable("food") { FoodScreen(navController) }
                composable("animals") { AnimalsScreen(navController) }
                composable("adjectives") { AdjectivesScreen(navController) }
                composable("adjectives_list") { AdjectivesListScreen(navController) }
                composable("this_and_that") { ThisAndThatScreen(navController) }
                composable("possessive_pronouns") { PossessivePronounsScreen(navController) }
                composable("colors_materials") { ColorsAndMaterialsScreen(navController) }
                composable("countries") { CountriesScreen(navController) }
                composable("vacation") { VacationScreen(navController) }
                composable("quiz_eng_czech") { QuizScreen(navController, engToCzech = true) }
                composable("quiz_czech_eng") { QuizScreen(navController, engToCzech = false) }
                composable("dialogues") { DialoguesHubScreen(navController) }
                composable("dialogue_restaurant") { RestaurantDialogueScreen(navController) }
                composable("dialogue_directions") { DirectionsDialogueScreen(navController) }
            }
        }
    }
}

internal val ButtonBlue = Color(0xFF1565C0)

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "A Stranger In A Strange Land",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "Czech Edition",
            fontSize = 16.sp,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(48.dp))
        NavButton(label = "Learning The Language") { navController.navigate("learning") }
        Spacer(modifier = Modifier.height(20.dp))
        NavButton(label = "Simple Dictionary") { navController.navigate("dictionary") }
        Spacer(modifier = Modifier.height(20.dp))
        NavButton(label = "Quiz — English to Czech") { navController.navigate("quiz_eng_czech") }
        Spacer(modifier = Modifier.height(20.dp))
        NavButton(label = "Quiz — Czech to English") { navController.navigate("quiz_czech_eng") }
        Spacer(modifier = Modifier.height(20.dp))
        NavButton(label = "Sample Dialogues") { navController.navigate("dialogues") }
        Spacer(modifier = Modifier.height(48.dp))
    }
}

@Composable
fun NavButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(280.dp)
            .heightIn(min = 64.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue)
    ) {
        Text(text = label, fontSize = 18.sp, color = Color.White, textAlign = TextAlign.Center)
    }
}

@Composable
fun DictNavButton(label: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 64.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue)
    ) {
        Text(text = label, fontSize = 16.sp, color = Color.White, textAlign = TextAlign.Center)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningHubScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Learning The Language", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        val learningItems = listOf(
            "Letters & Pronunciation" to "letters_pronunciation",
            "About Nouns And Basic Demonstratives" to "nouns_demonstratives",
            "Cases" to "cases",
            "Present Tense Verb Conjugation" to "present_tense",
            "Verbs Of Movement" to "verbs_of_movement",
            "Prepositions" to "prepositions",
            "Questions" to "questions",
            "Adjectives" to "adjectives",
            "This and That" to "this_and_that",
            "Possessive Pronouns" to "possessive_pronouns"
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            learningItems.chunked(2).forEach { pair ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    pair.forEach { (label, route) ->
                        DictNavButton(label = label, modifier = Modifier.weight(1f)) {
                            navController.navigate(route)
                        }
                    }
                    if (pair.size == 1) Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryHubScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Simple Dictionary", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        val items = listOf(
            "Basic Words, Expressions & Greetings" to "basic_words",
            "Jobs & Professions" to "jobs_professions",
            "Places" to "places",
            "Useful Conjugated Verbs" to "useful_verbs",
            "Numbers" to "numbers",
            "Movement & Directions" to "movement",
            "People & Family" to "people_family",
            "Transportation" to "transportation",
            "Food" to "food",
            "Animals" to "animals",
            "Adjectives" to "adjectives_list",
            "Colors & Materials" to "colors_materials",
            "Countries & Languages" to "countries",
            "Vacation" to "vacation"
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items.chunked(2).forEach { pair ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    pair.forEach { (label, route) ->
                        DictNavButton(label = label, modifier = Modifier.weight(1f)) {
                            navController.navigate(route)
                        }
                    }
                    if (pair.size == 1) Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComingSoonScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Come Back Soon.", fontSize = 22.sp, color = Color.Black)
        }
    }
}
