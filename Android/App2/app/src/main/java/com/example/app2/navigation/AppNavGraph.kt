package com.example.app2.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app2.quiz.ConfigViewModel
import com.example.app2.screens.ConfigScreen
import com.example.app2.screens.HomeScreen
import com.example.app2.screens.PrepQuizScreen
import com.example.app2.screens.PrepResultsScreen
import com.example.app2.screens.QuizScreen
import com.example.app2.screens.ResultsScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Config : Screen("config")
    object Quiz : Screen("quiz")
    object Results : Screen("results")
    object PrepQuiz : Screen("prep_quiz")
    object PrepResults : Screen("prep_results")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    // Created outside NavHost so it is scoped to the Activity, shared across all screens
    val configViewModel: ConfigViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                onStartQuiz = { navController.navigate(Screen.Quiz.route) },
                onOpenConfig = { navController.navigate(Screen.Config.route) },
                onStartPrepQuiz = { navController.navigate(Screen.PrepQuiz.route) }
            )
        }
        composable(Screen.Config.route) {
            ConfigScreen(
                configViewModel = configViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Quiz.route) {
            QuizScreen(
                configViewModel = configViewModel,
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
        composable(Screen.PrepQuiz.route) {
            PrepQuizScreen(
                onQuizComplete = { navController.navigate(Screen.PrepResults.route) }
            )
        }
        composable(Screen.PrepResults.route) {
            PrepResultsScreen(
                navController = navController,
                onPlayAgain = {
                    navController.popBackStack(Screen.PrepQuiz.route, inclusive = true)
                    navController.navigate(Screen.PrepQuiz.route)
                },
                onHome = {
                    navController.popBackStack(Screen.Home.route, inclusive = false)
                }
            )
        }
    }
}
