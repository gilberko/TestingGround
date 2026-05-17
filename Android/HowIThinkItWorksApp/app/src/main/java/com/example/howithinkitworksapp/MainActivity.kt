package com.example.howithinkitworksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
                    composable("neuromarketing_hub") { NeuromarketingHubScreen(navController) }
                    composable("biases_hub") { BiasesSelectionScreen(navController) }
                    composable("biases_section/{biasKey}") { backStackEntry ->
                        val key = backStackEntry.arguments?.getString("biasKey") ?: ""
                        BiasesSectionScreen(key, navController)
                    }
                    composable("priming") { PrimingScreen(navController) }
                    composable("general_decision_making") { GeneralDecisionMakingScreen(navController) }
                    composable("brain_areas_emotions") { BrainAreasAndEmotionsScreen(navController) }
                    composable("neurotransmitters") { NeurotransmittersScreen(navController) }
                    composable("dopamine") { DopamineScreen(navController) }
                    composable("cortisol") { CortisolScreen(navController) }
                    composable("vagus_nerve") { VagusNerveScreen(navController) }
                    composable("brain_and_its_chemistry") { BrainAndItsChemistryHubScreen(navController) }
                    composable("winning_and_losing") { WinningAndLosingScreen(navController) }
                    composable("meditation_hub") { MeditationHubScreen(navController) }
                    composable("meditation_whats_it_about") { MeditationWhatsItAboutScreen(navController) }
                    composable("meditation_breathing") { MeditationBreathingScreen(navController) }
                    composable("meditation_body_scanning") { MeditationBodyScanningScreen(navController) }
                    composable("meditation_emotions") { MeditationEmotionsScreen(navController) }
                    composable("motivation") { MotivationScreen(navController) }
                    composable("about_music_hub") { AboutMusicHubScreen(navController) }
                    composable("about_music_rhythm") { AboutMusicRhythmScreen(navController) }
                    composable("about_music_motives") { AboutMusicMotivesScreen(navController) }
                    composable("about_music_harmony") { AboutMusicHarmonyScreen(navController) }
                    composable("about_music_lyrics") { AboutMusicLyricsScreen(navController) }
                    composable("about_music_song_structure") { AboutMusicSongStructureScreen(navController) }
                    composable("grab_attention") { GrabTheirAttentionScreen(navController) }
                    composable("avoidance") { AvoidanceScreen(navController) }
                }
            }
        }
    }
}

val ButtonBlue = Color(0xFF1565C0)

@Composable
fun MainScreen(navController: NavController) {
    val buttons = listOf(
        "Hypnosis" to "hypnosis_hub",
        "Neuromarketing and Persuasion" to "neuromarketing_hub",
        "Meditation" to "meditation_hub",
        "General Decision Making" to "general_decision_making",
        "The Brain And Its Chemistry" to "brain_and_its_chemistry",
        "Vagus Nerve" to "vagus_nerve",
        "Winning And Losing" to "winning_and_losing",
        "Motivation" to "motivation",
        "About Music" to "about_music_hub",
        "Avoidance" to "avoidance"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "How I Think It Works",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 40.dp)
        )
        buttons.forEach { (label, route) ->
            Button(
                onClick = { navController.navigate(route) },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(56.dp)
            ) {
                Text(text = label, color = Color.White, fontSize = 18.sp)
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
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
