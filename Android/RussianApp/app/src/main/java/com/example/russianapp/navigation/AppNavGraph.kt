package com.example.russianapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.russianapp.screens.ConfigScreen
import com.example.russianapp.screens.DictionaryScreen
import com.example.russianapp.screens.HomeScreen
import com.example.russianapp.screens.TutorialScreen
import com.example.russianapp.viewmodel.ConfigViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Tutorial : Screen("tutorial")
    object Dictionary : Screen("dictionary")
    object Config : Screen("config")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    val configViewModel: ConfigViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                onOpenTutorial = { navController.navigate(Screen.Tutorial.route) },
                onOpenDictionary = { navController.navigate(Screen.Dictionary.route) },
                onOpenConfig = { navController.navigate(Screen.Config.route) }
            )
        }
        composable(Screen.Tutorial.route) {
            TutorialScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Dictionary.route) {
            DictionaryScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Config.route) {
            ConfigScreen(
                configViewModel = configViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
