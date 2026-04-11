package com.example.frenchproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.frenchproject.screens.DictionaryHubScreen
import com.example.frenchproject.screens.HomeScreen
import com.example.frenchproject.screens.LearningHubScreen
import com.example.frenchproject.screens.dictionary.AdjectivesScreen
import com.example.frenchproject.screens.dictionary.AnimalsScreen
import com.example.frenchproject.screens.dictionary.ColorsScreen
import com.example.frenchproject.screens.dictionary.CommonVerbsScreen
import com.example.frenchproject.screens.dictionary.SchoolWorkScreen
import com.example.frenchproject.screens.learning.ArticlesScreen
import com.example.frenchproject.screens.learning.AskingQuestionsScreen
import com.example.frenchproject.screens.learning.EtreAvoirScreen
import com.example.frenchproject.screens.learning.ReflexiveVerbsScreen
import com.example.frenchproject.screens.learning.SubjectPronounsScreen
import com.example.frenchproject.screens.learning.TensesScreen

sealed class Screen(val route: String) {
    object Home              : Screen("home")
    object LearningHub       : Screen("learning_hub")
    object DictionaryHub     : Screen("dictionary_hub")
    object SubjectPronouns   : Screen("subject_pronouns")
    object Articles          : Screen("articles")
    object EtreAvoir         : Screen("etre_avoir")
    object Tenses            : Screen("tenses")
    object ReflexiveVerbs    : Screen("reflexive_verbs")
    object AskingQuestions   : Screen("asking_questions")
    object Colors            : Screen("colors")
    object Animals           : Screen("animals")
    object CommonVerbs       : Screen("common_verbs")
    object Adjectives        : Screen("adjectives")
    object SchoolWork        : Screen("school_work")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                onLearning   = { navController.navigate(Screen.LearningHub.route) },
                onDictionary = { navController.navigate(Screen.DictionaryHub.route) }
            )
        }
        composable(Screen.LearningHub.route) {
            LearningHubScreen(
                onBack             = { navController.popBackStack() },
                onSubjectPronouns  = { navController.navigate(Screen.SubjectPronouns.route) },
                onArticles         = { navController.navigate(Screen.Articles.route) },
                onEtreAvoir        = { navController.navigate(Screen.EtreAvoir.route) },
                onTenses           = { navController.navigate(Screen.Tenses.route) },
                onReflexiveVerbs   = { navController.navigate(Screen.ReflexiveVerbs.route) },
                onAskingQuestions  = { navController.navigate(Screen.AskingQuestions.route) }
            )
        }
        composable(Screen.DictionaryHub.route) {
            DictionaryHubScreen(
                onBack         = { navController.popBackStack() },
                onColors       = { navController.navigate(Screen.Colors.route) },
                onAnimals      = { navController.navigate(Screen.Animals.route) },
                onCommonVerbs  = { navController.navigate(Screen.CommonVerbs.route) },
                onAdjectives   = { navController.navigate(Screen.Adjectives.route) },
                onSchoolWork   = { navController.navigate(Screen.SchoolWork.route) }
            )
        }
        composable(Screen.SubjectPronouns.route) {
            SubjectPronounsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Articles.route) {
            ArticlesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EtreAvoir.route) {
            EtreAvoirScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Tenses.route) {
            TensesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.ReflexiveVerbs.route) {
            ReflexiveVerbsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AskingQuestions.route) {
            AskingQuestionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Colors.route) {
            ColorsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Animals.route) {
            AnimalsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.CommonVerbs.route) {
            CommonVerbsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Adjectives.route) {
            AdjectivesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.SchoolWork.route) {
            SchoolWorkScreen(onBack = { navController.popBackStack() })
        }
    }
}
