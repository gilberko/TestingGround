package com.example.app2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app2.screens.HomeScreen
import com.example.app2.screens.QuizScreen
import com.example.app2.screens.ResultsScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Quiz : Screen("quiz")
    object Results : Screen("results")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(onStartQuiz = { navController.navigate(Screen.Quiz.route) })
        }
        composable(Screen.Quiz.route) {
            QuizScreen(
                onQuizComplete = { navController.navigate(Screen.Results.route) }
            )
        }
        composable(Screen.Results.route) {
            ResultsScreen(
                navController = navController,
                onPlayAgain = {
                    navController.popBackStack(Screen.Quiz.route, inclusive = true)
                    navController.navigate(Screen.Quiz.route)
                },
                onHome = {
                    navController.popBackStack(Screen.Home.route, inclusive = false)
                }
            )
        }
    }
}
