package com.example.czechappredo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class WordPair(val english: String, val czech: String)
data class QuizQuestion(val prompt: String, val correctAnswer: String, val options: List<String>)

val quizWordBank: List<WordPair> = listOf(
    // Animals (18)
    WordPair("bird", "pták"),
    WordPair("eagle", "orel"),
    WordPair("parrot", "papoušek"),
    WordPair("dolphin", "delfín"),
    WordPair("shark", "žralok"),
    WordPair("whale", "velryba"),
    WordPair("dog", "pes"),
    WordPair("cat", "kočka"),
    WordPair("horse", "kůň"),
    WordPair("lion", "lev"),
    WordPair("tiger", "tygr"),
    WordPair("giraffe", "žirafa"),
    WordPair("bear", "medvěd"),
    WordPair("snake", "had"),
    WordPair("crocodile", "krokodýl"),
    WordPair("spider", "pavouk"),
    WordPair("mouse", "myš"),
    WordPair("kangaroo", "klokan"),
    // Places (14)
    WordPair("airport", "letiště"),
    WordPair("train station", "nádraží"),
    WordPair("bus stop", "autobusová zastávka"),
    WordPair("hospital", "nemocnice"),
    WordPair("school", "škola"),
    WordPair("university", "univerzita"),
    WordPair("restaurant", "restaurace"),
    WordPair("café", "kavárna"),
    WordPair("hotel", "hotel"),
    WordPair("cinema", "kino"),
    WordPair("church", "kostel"),
    WordPair("forest", "les"),
    WordPair("bridge", "most"),
    WordPair("square", "náměstí"),
    // Food & Drink (20)
    WordPair("bread", "chléb"),
    WordPair("milk", "mléko"),
    WordPair("butter", "máslo"),
    WordPair("cheese", "sýr"),
    WordPair("beef", "hovězí maso"),
    WordPair("pork", "vepřové maso"),
    WordPair("potato", "brambor"),
    WordPair("tomato", "rajče"),
    WordPair("onion", "cibule"),
    WordPair("lemon", "citron"),
    WordPair("strawberries", "jahody"),
    WordPair("rice", "rýže"),
    WordPair("soup", "polévka"),
    WordPair("cake", "dort"),
    WordPair("ice cream", "zmrzlina"),
    WordPair("beer", "pivo"),
    WordPair("wine", "víno"),
    WordPair("coffee", "káva"),
    WordPair("tea", "čaj"),
    WordPair("water", "voda"),
    // Transportation (10)
    WordPair("car", "auto"),
    WordPair("bus", "autobus"),
    WordPair("train", "vlak"),
    WordPair("airplane", "letadlo"),
    WordPair("ship", "loď"),
    WordPair("boat", "člun"),
    WordPair("bicycle", "kolo"),
    WordPair("taxi", "taxi"),
    WordPair("motorcycle", "motorka"),
    WordPair("truck", "nákladní auto"),
    // Colors (12)
    WordPair("black", "černý"),
    WordPair("white", "bílý"),
    WordPair("red", "červený"),
    WordPair("blue", "modrý"),
    WordPair("green", "zelený"),
    WordPair("yellow", "žlutý"),
    WordPair("orange", "oranžový"),
    WordPair("pink", "růžový"),
    WordPair("brown", "hnědý"),
    WordPair("gray", "šedý"),
    WordPair("purple", "fialový"),
    WordPair("gold", "zlatý"),
    // Adjectives (16)
    WordPair("big", "velký"),
    WordPair("small", "malý"),
    WordPair("tall", "vysoký"),
    WordPair("fast", "rychlý"),
    WordPair("slow", "pomalý"),
    WordPair("hot", "horký"),
    WordPair("cold", "studený"),
    WordPair("expensive", "drahý"),
    WordPair("cheap", "levný"),
    WordPair("good", "dobrý"),
    WordPair("bad", "špatný"),
    WordPair("new", "nový"),
    WordPair("old", "starý"),
    WordPair("young", "mladý"),
    WordPair("beautiful", "krásný"),
    WordPair("boring", "nudný"),
    // People & Family (14)
    WordPair("man", "muž"),
    WordPair("woman", "žena"),
    WordPair("child", "dítě"),
    WordPair("baby", "miminko"),
    WordPair("father", "otec"),
    WordPair("mother", "matka"),
    WordPair("husband", "manžel"),
    WordPair("wife", "manželka"),
    WordPair("son", "syn"),
    WordPair("daughter", "dcera"),
    WordPair("brother", "bratr"),
    WordPair("sister", "sestra"),
    WordPair("grandfather", "dědeček"),
    WordPair("grandmother", "babička"),
    // Jobs (10)
    WordPair("teacher", "učitel"),
    WordPair("doctor", "lékař"),
    WordPair("lawyer", "právník"),
    WordPair("actor", "herec"),
    WordPair("singer", "zpěvák"),
    WordPair("cook", "kuchař"),
    WordPair("waiter", "číšník"),
    WordPair("driver", "řidič"),
    WordPair("soldier", "voják"),
    WordPair("engineer", "inženýr"),
    // Verbs — infinitive (20)
    WordPair("to go (on foot)", "jít"),
    WordPair("to go (by vehicle)", "jet"),
    WordPair("to eat", "jíst"),
    WordPair("to drink", "pít"),
    WordPair("to sleep", "spát"),
    WordPair("to work", "pracovat"),
    WordPair("to study", "studovat"),
    WordPair("to speak", "mluvit"),
    WordPair("to see", "vidět"),
    WordPair("to buy", "kupovat"),
    WordPair("to sell", "prodávat"),
    WordPair("to wait", "čekat"),
    WordPair("to run", "běžet"),
    WordPair("to think", "myslet"),
    WordPair("to give", "dát"),
    WordPair("to take", "vzít"),
    WordPair("to look for", "hledat"),
    WordPair("to open", "otevírat"),
    WordPair("to close", "zavírat"),
    WordPair("to play", "hrát"),
    // Movement & Directions (16)
    WordPair("straight ahead", "rovně"),
    WordPair("to the left", "doleva"),
    WordPair("to the right", "doprava"),
    WordPair("forward", "dopředu"),
    WordPair("far", "daleko"),
    WordPair("near", "blízko"),
    WordPair("north", "sever"),
    WordPair("south", "jih"),
    WordPair("east", "východ"),
    WordPair("west", "západ"),
    WordPair("upward", "nahoru"),
    WordPair("downward", "dolů"),
    WordPair("back", "zpátky"),
    WordPair("on the left", "vlevo"),
    WordPair("on the right", "vpravo"),
    WordPair("next to", "vedle")
)

fun generateQuestions(engToCzech: Boolean): List<QuizQuestion> {
    val shuffled = quizWordBank.shuffled()
    val selected = shuffled.take(10)
    return selected.map { pair ->
        val prompt = if (engToCzech) pair.english else pair.czech
        val correctAnswer = if (engToCzech) pair.czech else pair.english
        val wrongAnswers = quizWordBank
            .filter { it != pair }
            .shuffled()
            .take(3)
            .map { if (engToCzech) it.czech else it.english }
        val options = (wrongAnswers + correctAnswer).shuffled()
        QuizQuestion(prompt, correctAnswer, options)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(navController: NavController, engToCzech: Boolean) {
    val title = if (engToCzech) "Quiz — English to Czech" else "Quiz — Czech to English"
    var quizKey by remember { mutableStateOf(0) }
    key(quizKey) {
        QuizContent(
            navController = navController,
            engToCzech = engToCzech,
            title = title,
            onPlayAgain = { quizKey++ }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun QuizContent(
    navController: NavController,
    engToCzech: Boolean,
    title: String,
    onPlayAgain: () -> Unit
) {
    val questions = remember { generateQuestions(engToCzech) }
    var currentIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var score by remember { mutableStateOf(0) }
    var quizFinished by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        if (quizFinished) {
            ScoreScreen(
                score = score,
                total = questions.size,
                onPlayAgain = onPlayAgain,
                onHome = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = false }
                    }
                },
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            QuestionCard(
                question = questions[currentIndex],
                questionNumber = currentIndex + 1,
                totalQuestions = questions.size,
                selectedAnswer = selectedAnswer,
                onAnswerSelected = { answer ->
                    if (selectedAnswer == null) {
                        selectedAnswer = answer
                        if (answer == questions[currentIndex].correctAnswer) score++
                    }
                },
                onNext = {
                    if (currentIndex < questions.size - 1) {
                        currentIndex++
                        selectedAnswer = null
                    } else {
                        quizFinished = true
                    }
                },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun QuestionCard(
    question: QuizQuestion,
    questionNumber: Int,
    totalQuestions: Int,
    selectedAnswer: String?,
    onAnswerSelected: (String) -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    val correctGreen = Color(0xFF2E7D32)
    val wrongRed = Color(0xFFC62828)
    val dimGray = Color(0xFF9E9E9E)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Question $questionNumber / $totalQuestions",
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = question.prompt,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(28.dp))
        question.options.forEach { option ->
            val bgColor = when {
                selectedAnswer == null -> ButtonBlue
                option == question.correctAnswer -> correctGreen
                option == selectedAnswer -> wrongRed
                else -> dimGray
            }
            Button(
                onClick = { onAnswerSelected(option) },
                enabled = selectedAnswer == null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .heightIn(min = 56.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = bgColor,
                    disabledContainerColor = bgColor
                )
            ) {
                Text(
                    text = option,
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
        if (selectedAnswer != null) {
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onNext,
                modifier = Modifier
                    .width(200.dp)
                    .heightIn(min = 52.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue)
            ) {
                Text("Next", fontSize = 17.sp, color = Color.White)
            }
        }
    }
}

@Composable
private fun ScoreScreen(
    score: Int,
    total: Int,
    onPlayAgain: () -> Unit,
    onHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Quiz Complete!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "You got $score / $total correct!",
            fontSize = 20.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = onPlayAgain,
            modifier = Modifier
                .width(220.dp)
                .heightIn(min = 56.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue)
        ) {
            Text("Play Again", fontSize = 17.sp, color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onHome,
            modifier = Modifier
                .width(220.dp)
                .heightIn(min = 56.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF546E7A))
        ) {
            Text("Home", fontSize = 17.sp, color = Color.White)
        }
    }
}
