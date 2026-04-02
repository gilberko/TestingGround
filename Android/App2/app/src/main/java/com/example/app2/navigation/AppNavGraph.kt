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
import com.example.app2.screens.ReflexiveVerbsScreen
import com.example.app2.screens.MorePrepositionsScreen
import com.example.app2.screens.CliticPronounsScreen
import com.example.app2.screens.IrregularVerbsScreen
import com.example.app2.screens.TensesScreen
import com.example.app2.screens.ComparisonsScreen
import com.example.app2.screens.QuestionsScreen
import com.example.app2.screens.UsefulVerbsScreen
import com.example.app2.screens.VocabQuizScreen
import com.example.app2.screens.VocabResultsScreen
import com.example.app2.screens.SomeNoneAllScreen
import com.example.app2.screens.ConfusingVerbsScreen
import com.example.app2.screens.TechWordsScreen
import com.example.app2.screens.GreetingsScreen
import com.example.app2.screens.MathScreen
import com.example.app2.data.model.QuizDirection

sealed class Screen(val route: String) {
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
    object TutorialReflexiveVerbs : Screen("tutorial_reflexive_verbs")
    object TutorialMorePrepositions : Screen("tutorial_more_prepositions")
    object TutorialCliticPronouns : Screen("tutorial_clitic_pronouns")
    object TutorialIrregularVerbs : Screen("tutorial_irregular_verbs")
    object TutorialUsefulVerbs : Screen("tutorial_useful_verbs")
    object TutorialTenses : Screen("tutorial_tenses")
    object TutorialComparisons : Screen("tutorial_comparisons")
    object TutorialQuestions : Screen("tutorial_questions")
    object DictSomeNoneAll : Screen("dict_some_none_all")
    object DictConfusingVerbs : Screen("dict_confusing_verbs")
    object DictTechWords : Screen("dict_tech_words")
    object DictGreetings : Screen("dict_greetings")
    object DictMath : Screen("dict_math")
    object VocabQuizEnToPt : Screen("vocab_quiz_en_to_pt")
    object VocabQuizPtToEn : Screen("vocab_quiz_pt_to_en")
    object VocabResultsEnToPt : Screen("vocab_results_en_to_pt")
    object VocabResultsPtToEn : Screen("vocab_results_pt_to_en")
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
                onStartPrepQuiz = { navController.navigate(Screen.PrepQuiz.route) },
                onOpenTutorial = { navController.navigate(Screen.Tutorial.route) },
                onOpenDictionary = { navController.navigate(Screen.Dictionary.route) },
                onStartVocabQuizEnToPt = { navController.navigate(Screen.VocabQuizEnToPt.route) },
                onStartVocabQuizPtToEn = { navController.navigate(Screen.VocabQuizPtToEn.route) }
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
                onPrepositions = { navController.navigate(Screen.TutorialPrepositions.route) },
                onConjugationBasic = { navController.navigate(Screen.ConjugationBasic.route) },
                onConjugationAdvanced = { navController.navigate(Screen.ConjugationAdvanced.route) },
                onPronunciation = { navController.navigate(Screen.TutorialPronunciation.route) },
                onReflexiveVerbs = { navController.navigate(Screen.TutorialReflexiveVerbs.route) },
                onMorePrepositions = { navController.navigate(Screen.TutorialMorePrepositions.route) },
                onCliticPronouns = { navController.navigate(Screen.TutorialCliticPronouns.route) },
                onIrregularVerbs = { navController.navigate(Screen.TutorialIrregularVerbs.route) },
                onUsefulVerbs = { navController.navigate(Screen.TutorialUsefulVerbs.route) },
                onTenses = { navController.navigate(Screen.TutorialTenses.route) },
                onComparisons = { navController.navigate(Screen.TutorialComparisons.route) },
                onQuestions = { navController.navigate(Screen.TutorialQuestions.route) }
            )
        }
        composable(Screen.Dictionary.route) {
            DictionaryScreen(
                onBack = { navController.popBackStack() },
                onCommonVerbs = { navController.navigate(Screen.TutorialCommonVerbs.route) },
                onTimeExpressions = { navController.navigate(Screen.TutorialTimeExpressions.route) },
                onNumbers = { navController.navigate(Screen.TutorialNumbers.route) },
                onColors = { navController.navigate(Screen.DictColors.route) },
                onAdjectives = { navController.navigate(Screen.DictAdjectives.route) },
                onAdverbs = { navController.navigate(Screen.DictAdverbs.route) },
                onCommonWords = { navController.navigate(Screen.DictCommonWords.route) },
                onMovement = { navController.navigate(Screen.DictMovement.route) },
                onSomeNoneAll = { navController.navigate(Screen.DictSomeNoneAll.route) },
                onConfusingVerbs = { navController.navigate(Screen.DictConfusingVerbs.route) },
                onTechWords = { navController.navigate(Screen.DictTechWords.route) },
                onGreetings = { navController.navigate(Screen.DictGreetings.route) },
                onMath = { navController.navigate(Screen.DictMath.route) }
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
        composable(Screen.DictSomeNoneAll.route) {
            SomeNoneAllScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictConfusingVerbs.route) {
            ConfusingVerbsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictTechWords.route) {
            TechWordsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictGreetings.route) {
            GreetingsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictMath.route) {
            MathScreen(onBack = { navController.popBackStack() })
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
        composable(Screen.TutorialReflexiveVerbs.route) {
            ReflexiveVerbsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TutorialMorePrepositions.route) {
            MorePrepositionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TutorialCliticPronouns.route) {
            CliticPronounsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TutorialIrregularVerbs.route) {
            IrregularVerbsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TutorialUsefulVerbs.route) {
            UsefulVerbsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TutorialTenses.route) {
            TensesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TutorialComparisons.route) {
            ComparisonsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TutorialQuestions.route) {
            QuestionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.VocabQuizEnToPt.route) {
            VocabQuizScreen(
                direction = QuizDirection.EN_TO_PT,
                configViewModel = configViewModel,
                onQuizComplete = { navController.navigate(Screen.VocabResultsEnToPt.route) },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.VocabQuizPtToEn.route) {
            VocabQuizScreen(
                direction = QuizDirection.PT_TO_EN,
                configViewModel = configViewModel,
                onQuizComplete = { navController.navigate(Screen.VocabResultsPtToEn.route) },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.VocabResultsEnToPt.route) {
            VocabResultsScreen(
                navController = navController,
                quizRoute = Screen.VocabQuizEnToPt.route,
                onPlayAgain = {
                    navController.popBackStack(Screen.VocabQuizEnToPt.route, inclusive = true)
                    navController.navigate(Screen.VocabQuizEnToPt.route)
                },
                onHome = { navController.popBackStack(Screen.Home.route, inclusive = false) }
            )
        }
        composable(Screen.VocabResultsPtToEn.route) {
            VocabResultsScreen(
                navController = navController,
                quizRoute = Screen.VocabQuizPtToEn.route,
                onPlayAgain = {
                    navController.popBackStack(Screen.VocabQuizPtToEn.route, inclusive = true)
                    navController.navigate(Screen.VocabQuizPtToEn.route)
                },
                onHome = { navController.popBackStack(Screen.Home.route, inclusive = false) }
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
