package com.example.howithinkitworksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.howithinkitworksapp.ui.theme.HowIThinkItWorksAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HowIThinkItWorksAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") { MainScreen(navController) }
                    composable("topic/{name}") { backStackEntry ->
                        val name = backStackEntry.arguments?.getString("name") ?: ""
                        TopicScreen(name, navController)
                    }
                    composable("hypnosis_hub") { HypnosisHubScreen(navController) }
                    composable("hypnosis_section/{topicKey}") { backStackEntry ->
                        val key = backStackEntry.arguments?.getString("topicKey") ?: ""
                        HypnosisSectionScreen(key, navController)
                    }
                }
            }
        }
    }
}

val ButtonBlue = Color(0xFF1565C0)

@Composable
fun MainScreen(navController: NavController) {
    val topics = listOf("Hypnosis", "Neuromarketing", "Meditation")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "How I Think It Works",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 40.dp)
        )
        topics.forEach { topic ->
            Button(
                onClick = {
                    navController.navigate(
                        if (topic == "Hypnosis") "hypnosis_hub" else "topic/$topic"
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(56.dp)
            ) {
                Text(text = topic, color = Color.White, fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun TopicScreen(name: String, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = name,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Text(
            text = "Come back soon.",
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 40.dp)
        )
        Button(
            onClick = { navController.popBackStack() },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue)
        ) {
            Text(text = "Back", color = Color.White, fontSize = 16.sp)
        }
    }
}
