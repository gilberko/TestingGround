package com.example.frenchproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.frenchproject.screens.DictionaryHubScreen
import com.example.frenchproject.screens.HomeScreen
import com.example.frenchproject.screens.LearningHubScreen
import com.example.frenchproject.screens.SplashScreen
import com.example.frenchproject.screens.dictionary.HomeImprovementScreen
import com.example.frenchproject.screens.dictionary.NatureScreen
import com.example.frenchproject.screens.dictionary.SportsScreen
import com.example.frenchproject.screens.dictionary.TechScreen
import com.example.frenchproject.screens.learning.ALotOrALittleScreen
import com.example.frenchproject.screens.learning.ComparisonsScreen
import com.example.frenchproject.screens.learning.ConditionalsScreen
import com.example.frenchproject.screens.learning.EveryoneNooneScreen
import com.example.frenchproject.screens.learning.ObjectPronounsScreen
import com.example.frenchproject.screens.learning.PossessiveScreen
import com.example.frenchproject.screens.dictionary.AdjectivesScreen
import com.example.frenchproject.screens.dictionary.AnimalsScreen
import com.example.frenchproject.screens.dictionary.ColorsScreen
import com.example.frenchproject.screens.dictionary.CommonVerbsScreen
import com.example.frenchproject.screens.dictionary.FoodScreen
import com.example.frenchproject.screens.dictionary.GreetingsScreen
import com.example.frenchproject.screens.dictionary.JobsScreen
import com.example.frenchproject.screens.dictionary.NumbersScreen
import com.example.frenchproject.screens.dictionary.SchoolWorkScreen
import com.example.frenchproject.screens.dictionary.TimesScreen
import com.example.frenchproject.screens.learning.ArticlesScreen
import com.example.frenchproject.screens.learning.AskingQuestionsScreen
import com.example.frenchproject.screens.learning.DemonstrativesScreen
import com.example.frenchproject.screens.learning.NegationScreen
import com.example.frenchproject.screens.learning.PassiveScreen
import com.example.frenchproject.screens.learning.PrepositionsScreen
import com.example.frenchproject.screens.learning.EtreAvoirScreen
import com.example.frenchproject.screens.learning.ReflexiveVerbsScreen
import com.example.frenchproject.screens.learning.SubjectPronounsScreen
import com.example.frenchproject.screens.learning.TensesScreen

sealed class Screen(val route: String) {
    object Splash            : Screen("splash")
    object Home              : Screen("home")
    object LearningHub       : Screen("learning_hub")
    object DictionaryHub     : Screen("dictionary_hub")
    object SubjectPronouns   : Screen("subject_pronouns")
    object Articles          : Screen("articles")
    object EtreAvoir         : Screen("etre_avoir")
    object Tenses            : Screen("tenses")
    object ReflexiveVerbs    : Screen("reflexive_verbs")
    object AskingQuestions   : Screen("asking_questions")
    object Demonstratives    : Screen("demonstratives")
    object Negation          : Screen("negation")
    object Colors            : Screen("colors")
    object Animals           : Screen("animals")
    object CommonVerbs       : Screen("common_verbs")
    object Adjectives        : Screen("adjectives")
    object SchoolWork        : Screen("school_work")
    object Greetings         : Screen("greetings")
    object Food              : Screen("food")
    object Numbers           : Screen("numbers")
    object Times             : Screen("times")
    object Jobs              : Screen("jobs")
    object Prepositions      : Screen("prepositions")
    object Passive           : Screen("passive")
    object Conditionals      : Screen("conditionals")
    object HomeImprovement   : Screen("home_improvement")
    object Sports            : Screen("sports")
    object ObjectPronouns    : Screen("object_pronouns")
    object Possessive        : Screen("possessive")
    object Comparisons       : Screen("comparisons")
    object Nature            : Screen("nature")
    object Tech              : Screen("tech")
    object EveryoneNoone     : Screen("everyone_noone")
    object ALotOrALittle     : Screen("a_lot_or_a_little")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onFinished = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
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
                onAskingQuestions  = { navController.navigate(Screen.AskingQuestions.route) },
                onDemonstratives   = { navController.navigate(Screen.Demonstratives.route) },
                onNegation         = { navController.navigate(Screen.Negation.route) },
                onPrepositions     = { navController.navigate(Screen.Prepositions.route) },
                onPassive          = { navController.navigate(Screen.Passive.route) },
                onConditionals     = { navController.navigate(Screen.Conditionals.route) },
                onObjectPronouns   = { navController.navigate(Screen.ObjectPronouns.route) },
                onPossessive       = { navController.navigate(Screen.Possessive.route) },
                onComparisons      = { navController.navigate(Screen.Comparisons.route) },
                onEveryoneNoone    = { navController.navigate(Screen.EveryoneNoone.route) },
                onALotOrALittle    = { navController.navigate(Screen.ALotOrALittle.route) }
            )
        }
        composable(Screen.DictionaryHub.route) {
            DictionaryHubScreen(
                onBack         = { navController.popBackStack() },
                onColors       = { navController.navigate(Screen.Colors.route) },
                onAnimals      = { navController.navigate(Screen.Animals.route) },
                onCommonVerbs  = { navController.navigate(Screen.CommonVerbs.route) },
                onAdjectives   = { navController.navigate(Screen.Adjectives.route) },
                onSchoolWork   = { navController.navigate(Screen.SchoolWork.route) },
                onGreetings    = { navController.navigate(Screen.Greetings.route) },
                onFood         = { navController.navigate(Screen.Food.route) },
                onNumbers      = { navController.navigate(Screen.Numbers.route) },
                onTimes            = { navController.navigate(Screen.Times.route) },
                onJobs             = { navController.navigate(Screen.Jobs.route) },
                onHomeImprovement  = { navController.navigate(Screen.HomeImprovement.route) },
                onSports           = { navController.navigate(Screen.Sports.route) },
                onNature           = { navController.navigate(Screen.Nature.route) },
                onTech             = { navController.navigate(Screen.Tech.route) }
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
        composable(Screen.Demonstratives.route) {
            DemonstrativesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Negation.route) {
            NegationScreen(onBack = { navController.popBackStack() })
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
        composable(Screen.Greetings.route) {
            GreetingsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Food.route) {
            FoodScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Numbers.route) {
            NumbersScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Times.route) {
            TimesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Jobs.route) {
            JobsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Prepositions.route) {
            PrepositionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Passive.route) {
            PassiveScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Conditionals.route) {
            ConditionalsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.HomeImprovement.route) {
            HomeImprovementScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Sports.route) {
            SportsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.ObjectPronouns.route) {
            ObjectPronounsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Possessive.route) {
            PossessiveScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Comparisons.route) {
            ComparisonsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Nature.route) {
            NatureScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Tech.route) {
            TechScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EveryoneNoone.route) {
            EveryoneNooneScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.ALotOrALittle.route) {
            ALotOrALittleScreen(onBack = { navController.popBackStack() })
        }
    }
}
