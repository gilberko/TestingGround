package com.example.czechappredo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
                composable("dictionary") { DictionaryHubScreen(navController) }
                composable("basic_words") { BasicWordsScreen(navController) }
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
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            NavButton(label = "Letters & Pronunciation") {
                navController.navigate("letters_pronunciation")
            }
            Spacer(modifier = Modifier.height(20.dp))
            NavButton(label = "About Nouns And Basic Demonstratives") {
                navController.navigate("nouns_demonstratives")
            }
            Spacer(modifier = Modifier.height(20.dp))
            NavButton(label = "Cases") {
                navController.navigate("cases")
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            NavButton(label = "Basic Words, Expressions & Greetings") {
                navController.navigate("basic_words")
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
