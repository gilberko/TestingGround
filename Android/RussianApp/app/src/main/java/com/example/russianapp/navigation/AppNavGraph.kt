package com.example.russianapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.russianapp.screens.AdjectiveConjugationScreen
import com.example.russianapp.screens.AdjectivesScreen
import com.example.russianapp.screens.ConditionalsScreen
import com.example.russianapp.screens.DateTimeScreen
import com.example.russianapp.screens.NegationScreen
import com.example.russianapp.screens.NounDeclensionScreen
import com.example.russianapp.screens.PrepositionsScreen
import com.example.russianapp.screens.AdverbsScreen
import com.example.russianapp.screens.PeopleAnimalsScreen
import com.example.russianapp.screens.PlacesScreen
import com.example.russianapp.screens.WorkScreen
import com.example.russianapp.screens.AlphabetScreen
import com.example.russianapp.screens.ColorsScreen
import com.example.russianapp.screens.ConfigScreen
import com.example.russianapp.screens.DictionaryScreen
import com.example.russianapp.screens.FoodScreen
import com.example.russianapp.screens.GrammarCasesScreen
import com.example.russianapp.screens.HomeScreen
import com.example.russianapp.screens.MovementScreen
import com.example.russianapp.screens.NumbersScreen
import com.example.russianapp.screens.TutorialScreen
import com.example.russianapp.screens.VerbConjugationScreen
import com.example.russianapp.screens.AdjectiveQuizScreen
import com.example.russianapp.screens.ParticipleScreen
import com.example.russianapp.screens.TypesOfAnyScreen
import com.example.russianapp.screens.VerbQuizScreen
import com.example.russianapp.screens.VerbsScreen
import com.example.russianapp.screens.VocabularyEngToRusScreen
import com.example.russianapp.screens.VocabularyRusToEngScreen
import com.example.russianapp.viewmodel.ConfigViewModel

sealed class Screen(val route: String) {
    object Home                   : Screen("home")
    object Tutorial               : Screen("tutorial")
    object Dictionary             : Screen("dictionary")
    object Config                 : Screen("config")
    object Alphabet               : Screen("alphabet")
    object GrammarCases           : Screen("grammar_cases")
    object VerbConjugation        : Screen("verb_conjugation")
    object AdjectiveConjugation   : Screen("adjective_conjugation")
    object NounDeclension         : Screen("noun_declension")
    object DateTime               : Screen("date_time")
    object Prepositions           : Screen("prepositions")
    object Negation               : Screen("negation")
    object Conditionals           : Screen("conditionals")
    object Participles            : Screen("participles")
    object TypesOfAny             : Screen("types_of_any")
    object DictVerbs              : Screen("dict_verbs")
    object DictColors             : Screen("dict_colors")
    object DictNumbers            : Screen("dict_numbers")
    object DictMovement           : Screen("dict_movement")
    object DictAdjectives         : Screen("dict_adjectives")
    object DictAdverbs            : Screen("dict_adverbs")
    object DictFood               : Screen("dict_food")
    object DictPlaces             : Screen("dict_places")
    object DictPeopleAnimals      : Screen("dict_people_animals")
    object DictWork               : Screen("dict_work")
    object QuizAdjective          : Screen("quiz_adjective")
    object QuizVerb               : Screen("quiz_verb")
    object QuizVocabEngToRus      : Screen("quiz_vocab_eng_to_rus")
    object QuizVocabRusToEng      : Screen("quiz_vocab_rus_to_eng")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    val configViewModel: ConfigViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                onOpenTutorial      = { navController.navigate(Screen.Tutorial.route) },
                onOpenDictionary    = { navController.navigate(Screen.Dictionary.route) },
                onOpenConfig        = { navController.navigate(Screen.Config.route) },
                onOpenAdjectiveQuiz = { navController.navigate(Screen.QuizAdjective.route) },
                onOpenVerbQuiz          = { navController.navigate(Screen.QuizVerb.route) },
                onOpenVocabEngToRus     = { navController.navigate(Screen.QuizVocabEngToRus.route) },
                onOpenVocabRusToEng     = { navController.navigate(Screen.QuizVocabRusToEng.route) }
            )
        }
        composable(Screen.Tutorial.route) {
            TutorialScreen(
                onBack                  = { navController.popBackStack() },
                onAlphabet              = { navController.navigate(Screen.Alphabet.route) },
                onGrammarCases          = { navController.navigate(Screen.GrammarCases.route) },
                onVerbConjugation       = { navController.navigate(Screen.VerbConjugation.route) },
                onAdjectiveConjugation  = { navController.navigate(Screen.AdjectiveConjugation.route) },
                onNounDeclension        = { navController.navigate(Screen.NounDeclension.route) },
                onDateTime              = { navController.navigate(Screen.DateTime.route) },
                onPrepositions          = { navController.navigate(Screen.Prepositions.route) },
                onNegation              = { navController.navigate(Screen.Negation.route) },
                onConditionals          = { navController.navigate(Screen.Conditionals.route) },
                onParticiples           = { navController.navigate(Screen.Participles.route) },
                onTypesOfAny            = { navController.navigate(Screen.TypesOfAny.route) }
            )
        }
        composable(Screen.Dictionary.route) {
            DictionaryScreen(
                onBack          = { navController.popBackStack() },
                onVerbs         = { navController.navigate(Screen.DictVerbs.route) },
                onColors        = { navController.navigate(Screen.DictColors.route) },
                onNumbers       = { navController.navigate(Screen.DictNumbers.route) },
                onMovement      = { navController.navigate(Screen.DictMovement.route) },
                onAdjectives    = { navController.navigate(Screen.DictAdjectives.route) },
                onAdverbs       = { navController.navigate(Screen.DictAdverbs.route) },
                onFood          = { navController.navigate(Screen.DictFood.route) },
                onPlaces        = { navController.navigate(Screen.DictPlaces.route) },
                onPeopleAnimals = { navController.navigate(Screen.DictPeopleAnimals.route) },
                onWork          = { navController.navigate(Screen.DictWork.route) }
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
        composable(Screen.AdjectiveConjugation.route) {
            AdjectiveConjugationScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.NounDeclension.route) {
            NounDeclensionScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DateTime.route) {
            DateTimeScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Prepositions.route) {
            PrepositionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Negation.route) {
            NegationScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Conditionals.route) {
            ConditionalsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Participles.route) {
            ParticipleScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TypesOfAny.route) {
            TypesOfAnyScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictVerbs.route) {
            VerbsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictColors.route) {
            ColorsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictNumbers.route) {
            NumbersScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictMovement.route) {
            MovementScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictAdjectives.route) {
            AdjectivesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictAdverbs.route) {
            AdverbsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictFood.route) {
            FoodScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictPlaces.route) {
            PlacesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictPeopleAnimals.route) {
            PeopleAnimalsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictWork.route) {
            WorkScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.QuizAdjective.route) {
            AdjectiveQuizScreen(
                configViewModel = configViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.QuizVerb.route) {
            VerbQuizScreen(
                configViewModel = configViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.QuizVocabEngToRus.route) {
            VocabularyEngToRusScreen(
                configViewModel = configViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.QuizVocabRusToEng.route) {
            VocabularyRusToEngScreen(
                configViewModel = configViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
