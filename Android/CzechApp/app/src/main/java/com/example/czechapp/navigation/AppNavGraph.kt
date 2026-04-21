package com.example.czechapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.czechapp.screens.AboutAPersonScreen
import com.example.czechapp.screens.AdjectiveAgreementScreen
import com.example.czechapp.screens.AdjectivesScreen
import com.example.czechapp.screens.AdverbsScreen
import com.example.czechapp.screens.AirportConversationScreen
import com.example.czechapp.screens.AspectScreen
import com.example.czechapp.screens.BeachConversationScreen
import com.example.czechapp.screens.ClothesScreen
import com.example.czechapp.screens.ColorsScreen
import com.example.czechapp.screens.ComparisonsScreen
import com.example.czechapp.screens.ConditionalsScreen
import com.example.czechapp.screens.ConfigScreen
import com.example.czechapp.screens.ConversationsScreen
import com.example.czechapp.screens.CookingScreen
import com.example.czechapp.screens.DateTimeScreen
import com.example.czechapp.screens.DictionaryScreen
import com.example.czechapp.screens.DirectionsConversationScreen
import com.example.czechapp.screens.FoodScreen
import com.example.czechapp.screens.GrammarCasesScreen
import com.example.czechapp.screens.HomeScreen
import com.example.czechapp.screens.HouseScreen
import com.example.czechapp.screens.JobConversationScreen
import com.example.czechapp.screens.LettersScreen
import com.example.czechapp.screens.MiscScreen
import com.example.czechapp.screens.MovementScreen
import com.example.czechapp.screens.MyselfScreen
import com.example.czechapp.screens.NegationScreen
import com.example.czechapp.screens.NounDeclensionScreen
import com.example.czechapp.screens.NumbersScreen
import com.example.czechapp.screens.OfficeSchoolScreen
import com.example.czechapp.screens.PeopleAnimalsScreen
import com.example.czechapp.screens.PlacesScreen
import com.example.czechapp.screens.PrepositionsScreen
import com.example.czechapp.screens.QuantifiersScreen
import com.example.czechapp.screens.RestaurantConversationScreen
import com.example.czechapp.screens.TechMeetingConversationScreen
import com.example.czechapp.screens.TutorialScreen
import com.example.czechapp.screens.VacationScreen
import com.example.czechapp.screens.VerbConjugationScreen
import com.example.czechapp.screens.VerbQuizScreen
import com.example.czechapp.screens.VerbsScreen
import com.example.czechapp.screens.VocabularyCzechToEngScreen
import com.example.czechapp.screens.VocabularyEngToCzechScreen
import com.example.czechapp.screens.WorkScreen
import com.example.czechapp.viewmodel.ConfigViewModel

sealed class Screen(val route: String) {
    object Home                        : Screen("home")
    object Tutorial                    : Screen("tutorial")
    object Dictionary                  : Screen("dictionary")
    object Config                      : Screen("config")
    object Letters                     : Screen("letters")
    object GrammarCases                : Screen("grammar_cases")
    object VerbConjugation             : Screen("verb_conjugation")
    object NounDeclension              : Screen("noun_declension")
    object AdjectiveAgreement          : Screen("adjective_agreement")
    object DateTime                    : Screen("date_time")
    object Prepositions                : Screen("prepositions")
    object Negation                    : Screen("negation")
    object Conditionals                : Screen("conditionals")
    object Aspect                      : Screen("aspect")
    object Myself                      : Screen("myself")
    object AboutAPerson                : Screen("about_a_person")
    object Comparisons                 : Screen("comparisons")
    object Misc                        : Screen("misc")
    object DictVerbs                   : Screen("dict_verbs")
    object DictColors                  : Screen("dict_colors")
    object DictNumbers                 : Screen("dict_numbers")
    object DictMovement                : Screen("dict_movement")
    object DictAdjectives              : Screen("dict_adjectives")
    object DictAdverbs                 : Screen("dict_adverbs")
    object DictFood                    : Screen("dict_food")
    object DictPlaces                  : Screen("dict_places")
    object DictPeopleAnimals           : Screen("dict_people_animals")
    object DictWork                    : Screen("dict_work")
    object DictHouse                   : Screen("dict_house")
    object DictQuantifiers             : Screen("dict_quantifiers")
    object DictClothes                 : Screen("dict_clothes")
    object DictVacation                : Screen("dict_vacation")
    object DictCooking                 : Screen("dict_cooking")
    object DictOfficeSchool            : Screen("dict_office_school")
    object QuizVerb                    : Screen("quiz_verb")
    object QuizVocabEngToCzech         : Screen("quiz_vocab_eng_to_czech")
    object QuizVocabCzechToEng         : Screen("quiz_vocab_czech_to_eng")
    object Conversations               : Screen("conversations")
    object ConversationDirections      : Screen("conversation_directions")
    object ConversationRestaurant      : Screen("conversation_restaurant")
    object ConversationBeach           : Screen("conversation_beach")
    object ConversationTechMeeting     : Screen("conversation_tech_meeting")
    object ConversationJob             : Screen("conversation_job")
    object ConversationAirport         : Screen("conversation_airport")
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
                onOpenVerbQuiz      = { navController.navigate(Screen.QuizVerb.route) },
                onOpenVocabEngToCzech = { navController.navigate(Screen.QuizVocabEngToCzech.route) },
                onOpenVocabCzechToEng = { navController.navigate(Screen.QuizVocabCzechToEng.route) },
                onOpenConversations = { navController.navigate(Screen.Conversations.route) }
            )
        }
        composable(Screen.Tutorial.route) {
            TutorialScreen(
                onBack                  = { navController.popBackStack() },
                onLetters               = { navController.navigate(Screen.Letters.route) },
                onGrammarCases          = { navController.navigate(Screen.GrammarCases.route) },
                onVerbConjugation       = { navController.navigate(Screen.VerbConjugation.route) },
                onNounDeclension        = { navController.navigate(Screen.NounDeclension.route) },
                onAdjectiveAgreement    = { navController.navigate(Screen.AdjectiveAgreement.route) },
                onDateTime              = { navController.navigate(Screen.DateTime.route) },
                onPrepositions          = { navController.navigate(Screen.Prepositions.route) },
                onNegation              = { navController.navigate(Screen.Negation.route) },
                onConditionals          = { navController.navigate(Screen.Conditionals.route) },
                onAspect                = { navController.navigate(Screen.Aspect.route) },
                onMyself                = { navController.navigate(Screen.Myself.route) },
                onAboutAPerson          = { navController.navigate(Screen.AboutAPerson.route) },
                onComparisons           = { navController.navigate(Screen.Comparisons.route) },
                onMisc                  = { navController.navigate(Screen.Misc.route) }
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
                onWork          = { navController.navigate(Screen.DictWork.route) },
                onHouse         = { navController.navigate(Screen.DictHouse.route) },
                onQuantifiers   = { navController.navigate(Screen.DictQuantifiers.route) },
                onClothes       = { navController.navigate(Screen.DictClothes.route) },
                onVacation      = { navController.navigate(Screen.DictVacation.route) },
                onCooking       = { navController.navigate(Screen.DictCooking.route) },
                onOfficeSchool  = { navController.navigate(Screen.DictOfficeSchool.route) }
            )
        }
        composable(Screen.Config.route) {
            ConfigScreen(configViewModel = configViewModel, onBack = { navController.popBackStack() })
        }
        composable(Screen.Letters.route) {
            LettersScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.GrammarCases.route) {
            GrammarCasesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.VerbConjugation.route) {
            VerbConjugationScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.NounDeclension.route) {
            NounDeclensionScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AdjectiveAgreement.route) {
            AdjectiveAgreementScreen(onBack = { navController.popBackStack() })
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
        composable(Screen.Aspect.route) {
            AspectScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Myself.route) {
            MyselfScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AboutAPerson.route) {
            AboutAPersonScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Comparisons.route) {
            ComparisonsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Misc.route) {
            MiscScreen(onBack = { navController.popBackStack() })
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
        composable(Screen.DictHouse.route) {
            HouseScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictQuantifiers.route) {
            QuantifiersScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictClothes.route) {
            ClothesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictVacation.route) {
            VacationScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictCooking.route) {
            CookingScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DictOfficeSchool.route) {
            OfficeSchoolScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.QuizVerb.route) {
            VerbQuizScreen(configViewModel = configViewModel, onBack = { navController.popBackStack() })
        }
        composable(Screen.QuizVocabEngToCzech.route) {
            VocabularyEngToCzechScreen(configViewModel = configViewModel, onBack = { navController.popBackStack() })
        }
        composable(Screen.QuizVocabCzechToEng.route) {
            VocabularyCzechToEngScreen(configViewModel = configViewModel, onBack = { navController.popBackStack() })
        }
        composable(Screen.Conversations.route) {
            ConversationsScreen(
                onBack          = { navController.popBackStack() },
                onDirections    = { navController.navigate(Screen.ConversationDirections.route) },
                onRestaurant    = { navController.navigate(Screen.ConversationRestaurant.route) },
                onBeach         = { navController.navigate(Screen.ConversationBeach.route) },
                onTechMeeting   = { navController.navigate(Screen.ConversationTechMeeting.route) },
                onJob           = { navController.navigate(Screen.ConversationJob.route) },
                onAirport       = { navController.navigate(Screen.ConversationAirport.route) }
            )
        }
        composable(Screen.ConversationDirections.route) {
            DirectionsConversationScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.ConversationRestaurant.route) {
            RestaurantConversationScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.ConversationBeach.route) {
            BeachConversationScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.ConversationTechMeeting.route) {
            TechMeetingConversationScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.ConversationJob.route) {
            JobConversationScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.ConversationAirport.route) {
            AirportConversationScreen(onBack = { navController.popBackStack() })
        }
    }
}
