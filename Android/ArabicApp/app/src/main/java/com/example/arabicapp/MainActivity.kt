package com.example.arabicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.arabicapp.screens.PrepositionsConnectingWordsScreen
import com.example.arabicapp.screens.FirstWordsScreen
import com.example.arabicapp.screens.ComparisonsScreen
import com.example.arabicapp.screens.TechInterviewDialogueScreen
import com.example.arabicapp.screens.AdjectivesScreen
import com.example.arabicapp.screens.AdverbsScreen
import com.example.arabicapp.screens.LettersTransliterationScreen
import com.example.arabicapp.screens.NounsSingularPluralScreen
import com.example.arabicapp.screens.VerbConjugationOverviewScreen
import com.example.arabicapp.screens.CommonAdjectivesScreen
import com.example.arabicapp.screens.CommonAdverbsScreen
import com.example.arabicapp.screens.CommonVerbsScreen
import com.example.arabicapp.screens.CommonWordsScreen
import com.example.arabicapp.screens.ConditionsScreen
import com.example.arabicapp.screens.ContinuousScreen
import com.example.arabicapp.screens.DictionaryScreen
import com.example.arabicapp.screens.FutureTenseScreen
import com.example.arabicapp.screens.HubBackground
import com.example.arabicapp.screens.LearningScreen
import com.example.arabicapp.screens.NegationScreen
import com.example.arabicapp.screens.NumbersScreen
import com.example.arabicapp.screens.NumbersLearningScreen
import com.example.arabicapp.screens.ObjectPronounsScreen
import com.example.arabicapp.screens.PassiveVoiceScreen
import com.example.arabicapp.screens.PastTenseScreen
import com.example.arabicapp.screens.PossessivesScreen
import com.example.arabicapp.screens.PrepositionsScreen
import com.example.arabicapp.screens.QuestionsScreen
import com.example.arabicapp.screens.SpokenVsMSAScreen
import com.example.arabicapp.screens.SplashScreen
import com.example.arabicapp.screens.SubjectPronounsScreen
import com.example.arabicapp.screens.ColorsScreen
import com.example.arabicapp.screens.FoodsScreen
import com.example.arabicapp.screens.GreetingsScreen
import com.example.arabicapp.screens.SmallTalkScreen
import com.example.arabicapp.screens.CommonExpressionsScreen
import com.example.arabicapp.screens.LettersForHebrewSpeakersScreen
import com.example.arabicapp.screens.LocationScreen
import com.example.arabicapp.screens.PracticeLettersEnToArScreen
import com.example.arabicapp.screens.PracticeLettersScreen
import com.example.arabicapp.screens.TimeScreen
import com.example.arabicapp.screens.VowelsScreen
import com.example.arabicapp.screens.TheLettersScreen
import com.example.arabicapp.screens.JobsAndOccupationsScreen
import com.example.arabicapp.screens.CityAndNatureScreen
import com.example.arabicapp.screens.TransportationScreen
import com.example.arabicapp.screens.DialoguesScreen
import com.example.arabicapp.screens.MarketMeetingScreen
import com.example.arabicapp.screens.RestaurantDialogueScreen
import com.example.arabicapp.screens.PresentTenseScreen
import com.example.arabicapp.screens.SimpleConjugatedVerbsScreen
import com.example.arabicapp.screens.DirectionsDialogueScreen
import com.example.arabicapp.screens.ImperativeScreen
import com.example.arabicapp.screens.PlacesAndEventsScreen
import com.example.arabicapp.screens.ClothesScreen
import com.example.arabicapp.screens.BodyScreen
import com.example.arabicapp.screens.MedicalScreen
import com.example.arabicapp.screens.MoneyScreen
import com.example.arabicapp.screens.HobbiesScreen
import com.example.arabicapp.screens.GovernmentArmyScreen
import com.example.arabicapp.screens.ArtsMediaScreen
import com.example.arabicapp.screens.ConstructStateScreen
import com.example.arabicapp.screens.EverySomeScreen
import com.example.arabicapp.screens.ExamplesScreen
import com.example.arabicapp.screens.DirectionsExamplesScreen
import com.example.arabicapp.screens.PrepositionsExamplesScreen
import com.example.arabicapp.screens.WorkOfficeScreen
import com.example.arabicapp.screens.ThisThatHereThereScreen
import com.example.arabicapp.screens.AdjectivesAdverbsOverviewScreen
import com.example.arabicapp.screens.WeatherScreen
import com.example.arabicapp.screens.VerbFormsScreen
import com.example.arabicapp.screens.RealEstateScreen
import com.example.arabicapp.screens.MathEngineeringScreen
import com.example.arabicapp.screens.TourismTravelScreen
import com.example.arabicapp.screens.ReadingExamplesScreen
import com.example.arabicapp.screens.ReadingSentencesScreen
import com.example.arabicapp.screens.ReadingWordsScreen
import com.example.arabicapp.screens.FirstDateDialogueScreen
import com.example.arabicapp.screens.TrainStationDialogueScreen
import com.example.arabicapp.screens.VerbsTensesScreen
import com.example.arabicapp.screens.ExistsHaveWantScreen
import com.example.arabicapp.screens.IlArticleScreen
import com.example.arabicapp.ui.theme.ArabicAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArabicAppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(onFinished = {
                navController.navigate("home") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("learning") {
            ScreenWithBackButton(title = "Learning The Language", onBack = { navController.popBackStack() }) {
                HubBackground {
                    LearningScreen(navController)
                }
            }
        }
        composable("dictionary") {
            ScreenWithBackButton(title = "Simple Dictionary", onBack = { navController.popBackStack() }) {
                HubBackground {
                    DictionaryScreen(navController)
                }
            }
        }
        composable("examples") {
            ScreenWithBackButton(title = "Examples", onBack = { navController.popBackStack() }) {
                HubBackground {
                    ExamplesScreen(navController)
                }
            }
        }
        composable("examples_directions") {
            ScreenWithBackButton(title = "Examples — Directions", onBack = { navController.popBackStack() }) {
                DirectionsExamplesScreen()
            }
        }
        composable("examples_prepositions") {
            ScreenWithBackButton(title = "Examples — Prepositions", onBack = { navController.popBackStack() }) {
                PrepositionsExamplesScreen()
            }
        }
        composable("reading_examples") {
            ScreenWithBackButton(title = "Examples Of Reading In Arabic", onBack = { navController.popBackStack() }) {
                HubBackground {
                    ReadingExamplesScreen(navController)
                }
            }
        }
        composable("reading_words") {
            ScreenWithBackButton(title = "Reading — Words", onBack = { navController.popBackStack() }) {
                ReadingWordsScreen()
            }
        }
        composable("reading_sentences") {
            ScreenWithBackButton(title = "Reading — Sentences", onBack = { navController.popBackStack() }) {
                ReadingSentencesScreen()
            }
        }
        composable("practice_letters") {
            ScreenWithBackButton(title = "Practice Letters", onBack = { navController.popBackStack() }) {
                PracticeLettersScreen()
            }
        }
        composable("the_letters") {
            ScreenWithBackButton(title = "The Letters", onBack = { navController.popBackStack() }) {
                TheLettersScreen()
            }
        }
        composable("spoken_vs_msa") {
            ScreenWithBackButton(title = "Spoken Arabic vs MSA", onBack = { navController.popBackStack() }) {
                SpokenVsMSAScreen()
            }
        }
        composable("negation") {
            ScreenWithBackButton(title = "Negation", onBack = { navController.popBackStack() }) {
                NegationScreen()
            }
        }
        composable("questions") {
            ScreenWithBackButton(title = "Questions and Answers", onBack = { navController.popBackStack() }) {
                QuestionsScreen()
            }
        }
        composable("subject_pronouns") {
            ScreenWithBackButton(title = "Subject Pronouns", onBack = { navController.popBackStack() }) {
                SubjectPronounsScreen()
            }
        }
        composable("numbers") {
            ScreenWithBackButton(title = "The Numbers", onBack = { navController.popBackStack() }) {
                NumbersScreen()
            }
        }
        composable("common_words") {
            ScreenWithBackButton(title = "Common Words", onBack = { navController.popBackStack() }) {
                CommonWordsScreen()
            }
        }
        composable("common_verbs") {
            ScreenWithBackButton(title = "Common Verbs", onBack = { navController.popBackStack() }) {
                CommonVerbsScreen()
            }
        }
        composable("possessives") {
            ScreenWithBackButton(title = "Possessives", onBack = { navController.popBackStack() }) {
                PossessivesScreen()
            }
        }
        composable("object_pronouns") {
            ScreenWithBackButton(title = "Object Pronouns", onBack = { navController.popBackStack() }) {
                ObjectPronounsScreen()
            }
        }
        composable("past_tense") {
            ScreenWithBackButton(title = "Past Tense", onBack = { navController.popBackStack() }) {
                PastTenseScreen()
            }
        }
        composable("future_tense") {
            ScreenWithBackButton(title = "Future Tense", onBack = { navController.popBackStack() }) {
                FutureTenseScreen()
            }
        }
        composable("adjectives") {
            ScreenWithBackButton(title = "Adjectives", onBack = { navController.popBackStack() }) {
                AdjectivesScreen()
            }
        }
        composable("adverbs") {
            ScreenWithBackButton(title = "Adverbs", onBack = { navController.popBackStack() }) {
                AdverbsScreen()
            }
        }
        composable("conditions") {
            ScreenWithBackButton(title = "Conditions", onBack = { navController.popBackStack() }) {
                ConditionsScreen()
            }
        }
        composable("common_adjectives") {
            ScreenWithBackButton(title = "Common Adjectives", onBack = { navController.popBackStack() }) {
                CommonAdjectivesScreen()
            }
        }
        composable("common_adverbs") {
            ScreenWithBackButton(title = "Common Adverbs", onBack = { navController.popBackStack() }) {
                CommonAdverbsScreen()
            }
        }
        composable("letters_for_hebrew") {
            ScreenWithBackButton(title = "The Letters for Hebrew Speakers", onBack = { navController.popBackStack() }) {
                LettersForHebrewSpeakersScreen()
            }
        }
        composable("vowels") {
            ScreenWithBackButton(title = "Vowels", onBack = { navController.popBackStack() }) {
                VowelsScreen()
            }
        }
        composable("practice_letters_en_to_ar") {
            ScreenWithBackButton(title = "Practice Letters — English to Arabic", onBack = { navController.popBackStack() }) {
                PracticeLettersEnToArScreen()
            }
        }
        composable("colors") {
            ScreenWithBackButton(title = "Colors", onBack = { navController.popBackStack() }) {
                ColorsScreen()
            }
        }
        composable("greetings") {
            ScreenWithBackButton(title = "Politeness and Greetings", onBack = { navController.popBackStack() }) {
                GreetingsScreen()
            }
        }
        composable("dict_small_talk") {
            ScreenWithBackButton(title = "Small Talk", onBack = { navController.popBackStack() }) {
                SmallTalkScreen()
            }
        }
        composable("dict_common_expressions") {
            ScreenWithBackButton(title = "Common Expressions", onBack = { navController.popBackStack() }) {
                CommonExpressionsScreen()
            }
        }
        composable("foods_eating") {
            ScreenWithBackButton(title = "Foods and Eating", onBack = { navController.popBackStack() }) {
                FoodsScreen()
            }
        }
        composable("time_words") {
            ScreenWithBackButton(title = "Time", onBack = { navController.popBackStack() }) {
                TimeScreen()
            }
        }
        composable("location_movement") {
            ScreenWithBackButton(title = "Location and Movement", onBack = { navController.popBackStack() }) {
                LocationScreen()
            }
        }
        composable("jobs_occupations") {
            ScreenWithBackButton(title = "Jobs and Occupations", onBack = { navController.popBackStack() }) {
                JobsAndOccupationsScreen()
            }
        }
        composable("city_nature") {
            ScreenWithBackButton(title = "City and Nature", onBack = { navController.popBackStack() }) {
                CityAndNatureScreen()
            }
        }
        composable("transportation") {
            ScreenWithBackButton(title = "Transportation", onBack = { navController.popBackStack() }) {
                TransportationScreen()
            }
        }
        composable("prepositions") {
            ScreenWithBackButton(title = "Prepositions and Conjunctions", onBack = { navController.popBackStack() }) {
                PrepositionsScreen()
            }
        }
        composable("passive_voice") {
            ScreenWithBackButton(title = "Passive Voice", onBack = { navController.popBackStack() }) {
                PassiveVoiceScreen()
            }
        }
        composable("continuous") {
            ScreenWithBackButton(title = "Continuous", onBack = { navController.popBackStack() }) {
                ContinuousScreen()
            }
        }
        composable("dialogues") {
            ScreenWithBackButton(title = "Simple Dialogues", onBack = { navController.popBackStack() }) {
                HubBackground {
                    DialoguesScreen(navController)
                }
            }
        }
        composable("market_meeting") {
            ScreenWithBackButton(title = "Meeting in the Market", onBack = { navController.popBackStack() }) {
                MarketMeetingScreen()
            }
        }
        composable("restaurant_dialogue") {
            ScreenWithBackButton(title = "At the Restaurant", onBack = { navController.popBackStack() }) {
                RestaurantDialogueScreen()
            }
        }
        composable("present_tense") {
            ScreenWithBackButton(title = "Present Tense", onBack = { navController.popBackStack() }) {
                PresentTenseScreen()
            }
        }
        composable("simple_conj_verbs") {
            ScreenWithBackButton(title = "Simple Conjugated Verbs", onBack = { navController.popBackStack() }) {
                SimpleConjugatedVerbsScreen()
            }
        }
        composable("directions_dialogue") {
            ScreenWithBackButton(title = "Asking For Directions", onBack = { navController.popBackStack() }) {
                DirectionsDialogueScreen()
            }
        }
        composable("imperative") {
            ScreenWithBackButton(title = "Imperative", onBack = { navController.popBackStack() }) {
                ImperativeScreen()
            }
        }
        composable("letters_transliteration") {
            ScreenWithBackButton(title = "Letters Transliteration", onBack = { navController.popBackStack() }) {
                LettersTransliterationScreen()
            }
        }
        composable("adjectives_adverbs_overview") {
            ScreenWithBackButton(title = "Overview of Adjectives and Adverbs", onBack = { navController.popBackStack() }) {
                AdjectivesAdverbsOverviewScreen()
            }
        }
        composable("verb_forms") {
            ScreenWithBackButton(title = "Verb Forms", onBack = { navController.popBackStack() }) {
                VerbFormsScreen()
            }
        }
        composable("dict_weather") {
            ScreenWithBackButton(title = "The Weather", onBack = { navController.popBackStack() }) {
                WeatherScreen()
            }
        }
        composable("dict_real_estate") {
            ScreenWithBackButton(title = "Homes, Apartments and Real Estate", onBack = { navController.popBackStack() }) {
                RealEstateScreen()
            }
        }
        composable("every_some") {
            ScreenWithBackButton(title = "Every, Some, . . .", onBack = { navController.popBackStack() }) {
                EverySomeScreen()
            }
        }
        composable("numbers_learning") {
            ScreenWithBackButton(title = "Numbers and Time", onBack = { navController.popBackStack() }) {
                NumbersLearningScreen()
            }
        }
        composable("construct_state") {
            ScreenWithBackButton(title = "Construct State", onBack = { navController.popBackStack() }) {
                ConstructStateScreen()
            }
        }
        composable("verb_conjugation_overview") {
            ScreenWithBackButton(title = "Verb Conjugation Overview", onBack = { navController.popBackStack() }) {
                VerbConjugationOverviewScreen()
            }
        }
        composable("nouns_singular_plural") {
            ScreenWithBackButton(title = "Nouns - Singular and Plural", onBack = { navController.popBackStack() }) {
                NounsSingularPluralScreen()
            }
        }
        composable("dict_first_words") {
            ScreenWithBackButton(title = "First Words", onBack = { navController.popBackStack() }) {
                FirstWordsScreen()
            }
        }
        composable("dict_prepositions_connecting") {
            ScreenWithBackButton(title = "Prepositions and Connecting Words", onBack = { navController.popBackStack() }) {
                PrepositionsConnectingWordsScreen()
            }
        }
        composable("dict_places_events") {
            ScreenWithBackButton(title = "Places and Events", onBack = { navController.popBackStack() }) {
                PlacesAndEventsScreen()
            }
        }
        composable("dict_clothes") {
            ScreenWithBackButton(title = "Clothes", onBack = { navController.popBackStack() }) {
                ClothesScreen()
            }
        }
        composable("dict_body") {
            ScreenWithBackButton(title = "Body", onBack = { navController.popBackStack() }) {
                BodyScreen()
            }
        }
        composable("dict_medical") {
            ScreenWithBackButton(title = "Medical", onBack = { navController.popBackStack() }) {
                MedicalScreen()
            }
        }
        composable("dict_money") {
            ScreenWithBackButton(title = "Money", onBack = { navController.popBackStack() }) {
                MoneyScreen()
            }
        }
        composable("dict_hobbies") {
            ScreenWithBackButton(title = "Hobbies", onBack = { navController.popBackStack() }) {
                HobbiesScreen()
            }
        }
        composable("dict_government_army") {
            ScreenWithBackButton(title = "Government and Army", onBack = { navController.popBackStack() }) {
                GovernmentArmyScreen()
            }
        }
        composable("dict_arts_media") {
            ScreenWithBackButton(title = "Arts and Media", onBack = { navController.popBackStack() }) {
                ArtsMediaScreen()
            }
        }
        composable("dict_math_engineering") {
            ScreenWithBackButton(title = "Math and Engineering", onBack = { navController.popBackStack() }) {
                MathEngineeringScreen()
            }
        }
        composable("dict_tourism_travel") {
            ScreenWithBackButton(title = "Tourism and Traveling", onBack = { navController.popBackStack() }) {
                TourismTravelScreen()
            }
        }
        composable("dict_work_office") {
            ScreenWithBackButton(title = "Work and Office", onBack = { navController.popBackStack() }) {
                WorkOfficeScreen()
            }
        }
        composable("comparisons") {
            ScreenWithBackButton(title = "Comparisons", onBack = { navController.popBackStack() }) {
                ComparisonsScreen()
            }
        }
        composable("tech_interview_dialogue") {
            ScreenWithBackButton(title = "Tech Interview", onBack = { navController.popBackStack() }) {
                TechInterviewDialogueScreen()
            }
        }
        composable("first_date_dialogue") {
            ScreenWithBackButton(title = "First Date", onBack = { navController.popBackStack() }) {
                FirstDateDialogueScreen()
            }
        }
        composable("train_station_dialogue") {
            ScreenWithBackButton(title = "Train Station", onBack = { navController.popBackStack() }) {
                TrainStationDialogueScreen()
            }
        }
        composable("verbs_tenses") {
            ScreenWithBackButton(title = "Verbs And Tenses", onBack = { navController.popBackStack() }) {
                HubBackground {
                    VerbsTensesScreen(navController)
                }
            }
        }
        composable("exists_have_want") {
            ScreenWithBackButton(title = "Exists, Have, Want", onBack = { navController.popBackStack() }) {
                ExistsHaveWantScreen()
            }
        }
        composable("il_article") {
            ScreenWithBackButton(title = "il — The Definite Article", onBack = { navController.popBackStack() }) {
                IlArticleScreen()
            }
        }
        composable("this_that_here_there") {
            ScreenWithBackButton(title = "This and That, Here and There", onBack = { navController.popBackStack() }) {
                ThisThatHereThereScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenWithBackButton(title: String, onBack: () -> Unit, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            content()
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    HubBackground {
        Scaffold(containerColor = Color.Transparent) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navController.navigate("learning") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                ) {
                    Text("Learning The Language")
                }
                Button(
                    onClick = { navController.navigate("dictionary") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                ) {
                    Text("Simple Dictionary")
                }
                Button(
                    onClick = { navController.navigate("examples") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                ) {
                    Text("Examples")
                }
                Button(
                    onClick = { navController.navigate("practice_letters") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                ) {
                    Text("Practice Letters")
                }
                Button(
                    onClick = { navController.navigate("practice_letters_en_to_ar") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                ) {
                    Text("Practice Letters — English to Arabic")
                }
                Button(
                    onClick = { navController.navigate("dialogues") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Simple Dialogues")
                }
            }
        }
    }
}
