package com.example.russianapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.russianapp.screens.AlphabetScreen
import com.example.russianapp.screens.ColorsScreen
import com.example.russianapp.screens.ConfigScreen
import com.example.russianapp.screens.DictionaryScreen
import com.example.russianapp.screens.GrammarCasesScreen
import com.example.russianapp.screens.HomeScreen
import com.example.russianapp.screens.TutorialScreen
import com.example.russianapp.screens.VerbConjugationScreen
import com.example.russianapp.screens.VerbsScreen
import com.example.russianapp.viewmodel.ConfigViewModel

sealed class Screen(val route: String) {
    object Home            : Screen("home")
    object Tutorial        : Screen("tutorial")
    object Dictionary      : Screen("dictionary")
    object Config          : Screen("config")
    object Alphabet        : Screen("alphabet")
    object GrammarCases    : Screen("grammar_cases")
    object VerbConjugation : Screen("verb_conjugation")
    object DictVerbs       : Screen("dict_verbs")
    object DictColors      : Screen("dict_colors")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    val configViewModel: ConfigViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                onOpenTutorial   = { navController.navigate(Screen.Tutorial.route) },
                onOpenDictionary = { navController.navigate(Screen.Dictionary.route) },
                onOpenConfig     = { navController.navigate(Screen.Config.route) }
            )
        }
        composable(Screen.Tutorial.route) {
            TutorialScreen(
                onBack            = { navController.popBackStack() },
                onAlphabet        = { navController.navigate(Screen.Alphabet.route) },
                onGrammarCases    = { navController.navigate(Screen.GrammarCases.route) },
                onVerbConjugation = { navController.navigate(Screen.VerbConjugation.route) }
            )
        }
        composable(Screen.Dictionary.route) {
            DictionaryScreen(
                onBack   = { navController.popBackStack() },
                onVerbs  = { navController.navigate(Screen.DictVerbs.route) },
                onColors = { navController.navigate(Screen.DictColors.route) }
            )
        }
        composable(Screen.Config.route) {
            ConfigScreen(
                configViewModel = configViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Alphabet.route) {
            AlphabetScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.GrammarCases.route) {
            GrammarCasesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.VerbConjugation.route) {
            VerbConjugationScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictVerbs.route) {
            VerbsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictColors.route) {
            ColorsScreen(onBack = { navController.popBackStack() })
        }
    }
}
