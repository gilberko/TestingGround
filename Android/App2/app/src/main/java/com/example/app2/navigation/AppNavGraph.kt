package com.example.app2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.app2.screens.SplashScreen
import com.example.app2.screens.ConjugationAdvancedScreen
import com.example.app2.screens.ConjugationBasicScreen
import com.example.app2.screens.CommonVerbsScreen
import com.example.app2.screens.NumbersScreen
import com.example.app2.screens.PrepositionsScreen
import com.example.app2.screens.PronunciationScreen
import com.example.app2.screens.TimeExpressionsScreen
import com.example.app2.screens.TutorialScreen
import com.example.app2.screens.DictionaryScreen
import com.example.app2.screens.ColorsScreen
import com.example.app2.screens.AdjectivesScreen
import com.example.app2.screens.AdverbsScreen
import com.example.app2.screens.CommonWordsScreen
import com.example.app2.screens.MovementScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Config : Screen("config")
    object Quiz : Screen("quiz")
    object Results : Screen("results")
    object PrepQuiz : Screen("prep_quiz")
    object PrepResults : Screen("prep_results")
    object Tutorial : Screen("tutorial")
    object ConjugationBasic : Screen("tutorial_conj_basic")
    object ConjugationAdvanced : Screen("tutorial_conj_advanced")
    object TutorialPrepositions : Screen("tutorial_prepositions")
    object TutorialPronunciation : Screen("tutorial_pronunciation")
    object TutorialCommonVerbs : Screen("tutorial_common_verbs")
    object TutorialTimeExpressions : Screen("tutorial_time_expressions")
    object TutorialNumbers : Screen("tutorial_numbers")
    object Dictionary : Screen("dictionary")
    object DictColors : Screen("dict_colors")
    object DictAdjectives : Screen("dict_adjectives")
    object DictAdverbs : Screen("dict_adverbs")
    object DictCommonWords : Screen("dict_common_words")
    object DictMovement : Screen("dict_movement")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    // Created outside NavHost so it is scoped to the Activity, shared across all screens
    val configViewModel: ConfigViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onStartQuiz = { navController.navigate(Screen.Quiz.route) },
                onOpenConfig = { navController.navigate(Screen.Config.route) },
                onStartPrepQuiz = { navController.navigate(Screen.PrepQuiz.route) },
                onOpenTutorial = { navController.navigate(Screen.Tutorial.route) },
                onOpenDictionary = { navController.navigate(Screen.Dictionary.route) }
            )
        }
        composable(Screen.Config.route) {
            ConfigScreen(
                configViewModel = configViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Quiz.route) {
            val quizMode by configViewModel.quizMode.collectAsState()
            QuizScreen(
                configViewModel = configViewModel,
                onQuizComplete = { navController.navigate(Screen.Results.route) },
                quizMode = quizMode,
                onOpenTutorial = { navController.navigate(Screen.Tutorial.route) }
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
                onQuizComplete = { navController.navigate(Screen.PrepResults.route) },
                onOpenTutorial = { navController.navigate(Screen.Tutorial.route) }
            )
        }
        composable(Screen.Tutorial.route) {
            TutorialScreen(
                onBack = { navController.popBackStack() },
                onConjugationBasic = { navController.navigate(Screen.ConjugationBasic.route) },
                onConjugationAdvanced = { navController.navigate(Screen.ConjugationAdvanced.route) },
                onPronunciation = { navController.navigate(Screen.TutorialPronunciation.route) }
            )
        }
        composable(Screen.Dictionary.route) {
            DictionaryScreen(
                onBack = { navController.popBackStack() },
                onPrepositions = { navController.navigate(Screen.TutorialPrepositions.route) },
                onCommonVerbs = { navController.navigate(Screen.TutorialCommonVerbs.route) },
                onTimeExpressions = { navController.navigate(Screen.TutorialTimeExpressions.route) },
                onNumbers = { navController.navigate(Screen.TutorialNumbers.route) },
                onColors = { navController.navigate(Screen.DictColors.route) },
                onAdjectives = { navController.navigate(Screen.DictAdjectives.route) },
                onAdverbs = { navController.navigate(Screen.DictAdverbs.route) },
                onCommonWords = { navController.navigate(Screen.DictCommonWords.route) },
                onMovement = { navController.navigate(Screen.DictMovement.route) }
            )
        }
        composable(Screen.DictColors.route) {
            ColorsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictAdjectives.route) {
            AdjectivesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictAdverbs.route) {
            AdverbsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictCommonWords.route) {
            CommonWordsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictMovement.route) {
            MovementScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.ConjugationBasic.route) {
            ConjugationBasicScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.ConjugationAdvanced.route) {
            ConjugationAdvancedScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TutorialPrepositions.route) {
            PrepositionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TutorialPronunciation.route) {
            PronunciationScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TutorialCommonVerbs.route) {
            CommonVerbsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TutorialTimeExpressions.route) {
            TimeExpressionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TutorialNumbers.route) {
            NumbersScreen(onBack = { navController.popBackStack() })
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
