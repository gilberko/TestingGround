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

// ── Data ──────────────────────────────────────────────────────────────────────

private data class DQCategory(val title: String, val words: List<WordPair>)

private val dqCategories: List<DQCategory> = listOf(
    DQCategory("Time and Date", listOf(
        WordPair("January", "leden"),
        WordPair("February", "únor"),
        WordPair("March", "březen"),
        WordPair("April", "duben"),
        WordPair("May", "květen"),
        WordPair("June", "červen"),
        WordPair("July", "červenec"),
        WordPair("August", "srpen"),
        WordPair("September", "září"),
        WordPair("October", "říjen"),
        WordPair("November", "listopad"),
        WordPair("December", "prosinec"),
        WordPair("Monday", "pondělí"),
        WordPair("Tuesday", "úterý"),
        WordPair("Wednesday", "středa"),
        WordPair("Thursday", "čtvrtek"),
        WordPair("Friday", "pátek"),
        WordPair("Saturday", "sobota"),
        WordPair("Sunday", "neděle"),
        WordPair("a week", "týden"),
        WordPair("a month", "měsíc"),
        WordPair("a day", "den"),
        WordPair("a year", "rok"),
        WordPair("a decade", "desetiletí"),
        WordPair("today", "dnes"),
        WordPair("now", "teď"),
        WordPair("early", "časně"),
        WordPair("soon", "brzy"),
        WordPair("late", "pozdě"),
        WordPair("morning", "ráno"),
        WordPair("noon", "poledne"),
        WordPair("afternoon", "odpoledne"),
        WordPair("night", "noc"),
        WordPair("midnight", "půlnoc"),
        WordPair("yesterday", "včera"),
        WordPair("tomorrow", "zítra")
    )),
    DQCategory("Numbers", listOf(
        WordPair("0", "nula"),
        WordPair("1", "jedna"),
        WordPair("2", "dva"),
        WordPair("3", "tři"),
        WordPair("4", "čtyři"),
        WordPair("5", "pět"),
        WordPair("6", "šest"),
        WordPair("7", "sedm"),
        WordPair("8", "osm"),
        WordPair("9", "devět"),
        WordPair("10", "deset"),
        WordPair("20", "dvacet"),
        WordPair("30", "třicet"),
        WordPair("40", "čtyřicet"),
        WordPair("50", "padesát"),
        WordPair("60", "šedesát"),
        WordPair("70", "sedmdesát"),
        WordPair("80", "osmdesát"),
        WordPair("90", "devadesát"),
        WordPair("100", "sto"),
        WordPair("200", "dvě stě"),
        WordPair("300", "tři sta"),
        WordPair("400", "čtyři sta"),
        WordPair("500", "pět set"),
        WordPair("600", "šest set"),
        WordPair("700", "sedm set"),
        WordPair("800", "osm set"),
        WordPair("900", "devět set"),
        WordPair("1 000", "tisíc"),
        WordPair("2 000", "dva tisíce"),
        WordPair("3 000", "tři tisíce"),
        WordPair("4 000", "čtyři tisíce"),
        WordPair("5 000", "pět tisíc"),
        WordPair("6 000", "šest tisíc"),
        WordPair("7 000", "sedm tisíc"),
        WordPair("8 000", "osm tisíc"),
        WordPair("9 000", "devět tisíc"),
        WordPair("10 000", "deset tisíc"),
        WordPair("100 000", "sto tisíc"),
        WordPair("1 000 000", "milion"),
        WordPair("-1", "minus jedna"),
        WordPair("-3", "minus tři"),
        WordPair("-5", "minus pět"),
        WordPair("-8", "minus osm"),
        WordPair("-10", "minus deset"),
        WordPair("-20", "minus dvacet"),
        WordPair("-40", "minus čtyřicet"),
        WordPair("-100", "minus sto")
    )),
    DQCategory("Occupations", listOf(
        WordPair("programmer (male)", "programátor"),
        WordPair("programmer (female)", "programátorka"),
        WordPair("engineer (male)", "inženýr"),
        WordPair("engineer (female)", "inženýrka"),
        WordPair("judge (male)", "soudce"),
        WordPair("judge (female)", "soudkyně"),
        WordPair("policeman", "policista"),
        WordPair("policewoman", "policistka"),
        WordPair("actor", "herec"),
        WordPair("actress", "herečka"),
        WordPair("film director (male)", "režisér"),
        WordPair("film director (female)", "režisérka"),
        WordPair("investor (male)", "investor"),
        WordPair("investor (female)", "investorka"),
        WordPair("waiter", "číšník"),
        WordPair("waitress", "servírka / číšnice"),
        WordPair("chef (male)", "šéfkuchař"),
        WordPair("chef (female)", "šéfkuchařka"),
        WordPair("boss (male)", "šéf"),
        WordPair("boss (female)", "šéfová"),
        WordPair("manager (male)", "manažer"),
        WordPair("manager (female)", "manažerka"),
        WordPair("student (male)", "student"),
        WordPair("student (female)", "studentka"),
        WordPair("professor (male)", "profesor"),
        WordPair("professor (female)", "profesorka"),
        WordPair("coach (male)", "trenér"),
        WordPair("coach (female)", "trenérka"),
        WordPair("athlete (male)", "sportovec"),
        WordPair("athlete (female)", "sportovkyně"),
        WordPair("lawyer (male)", "právník"),
        WordPair("lawyer (female)", "právnice"),
        WordPair("accountant (male / female)", "účetní"),
        WordPair("teacher (male)", "učitel"),
        WordPair("teacher (female)", "učitelka"),
        WordPair("artist (male)", "umělec"),
        WordPair("artist (female)", "umělkyně"),
        WordPair("lifeguard (male)", "plavčík"),
        WordPair("lifeguard (female)", "plavčice"),
        WordPair("doctor (male)", "lékař"),
        WordPair("doctor (female)", "lékařka"),
        WordPair("nurse (male)", "zdravotní bratr"),
        WordPair("nurse (female)", "zdravotní sestra"),
        WordPair("dentist (male)", "zubař"),
        WordPair("dentist (female)", "zubařka"),
        WordPair("surgeon (male)", "chirurg"),
        WordPair("surgeon (female)", "chirurgyně"),
        WordPair("veterinarian (male)", "veterinář"),
        WordPair("veterinarian (female)", "veterinářka"),
        WordPair("soldier (male)", "voják"),
        WordPair("soldier (female)", "vojačka")
    )),
    DQCategory("Traveling and Tourism", listOf(
        WordPair("hotel", "hotel"),
        WordPair("pool", "bazén"),
        WordPair("ocean", "oceán"),
        WordPair("beach", "pláž"),
        WordPair("sea", "moře"),
        WordPair("mountains", "hory"),
        WordPair("a trip", "výlet"),
        WordPair("map", "mapa"),
        WordPair("hotel reservation", "rezervace hotelu"),
        WordPair("restaurant", "restaurace"),
        WordPair("passport", "cestovní pas"),
        WordPair("driver's license", "řidičský průkaz"),
        WordPair("car rental", "půjčovna aut"),
        WordPair("plane", "letadlo"),
        WordPair("plane ticket", "letenka"),
        WordPair("bus", "autobus"),
        WordPair("bus ticket", "jízdenka na autobus"),
        WordPair("train", "vlak"),
        WordPair("train station", "nádraží"),
        WordPair("train platform", "nástupiště"),
        WordPair("train ticket", "jízdenka na vlak")
    )),
    DQCategory("Weather", listOf(
        WordPair("rain", "déšť"),
        WordPair("snow", "sníh"),
        WordPair("cold", "studený"),
        WordPair("hot", "horký"),
        WordPair("warm", "teplý"),
        WordPair("chilly", "chladný"),
        WordPair("ice", "led"),
        WordPair("clouds", "mraky"),
        WordPair("weather", "počasí"),
        WordPair("wind", "vítr"),
        WordPair("tornado", "tornádo"),
        WordPair("storm", "bouře"),
        WordPair("snowflake", "sněhová vločka"),
        WordPair("dry", "suchý"),
        WordPair("humid", "vlhký"),
        WordPair("humidity", "vlhkost"),
        WordPair("temperature", "teplota")
    )),
    DQCategory("Adjectives", listOf(
        WordPair("strong", "silný"),
        WordPair("weak", "slabý"),
        WordPair("fat", "tlustý"),
        WordPair("skinny", "hubený"),
        WordPair("wide", "široký"),
        WordPair("narrow", "úzký"),
        WordPair("long", "dlouhý"),
        WordPair("short (length)", "krátký"),
        WordPair("high / tall", "vysoký"),
        WordPair("low", "nízký"),
        WordPair("short (person) / small", "malý"),
        WordPair("rich", "bohatý"),
        WordPair("poor", "chudý"),
        WordPair("smart", "chytrý"),
        WordPair("stupid", "hloupý"),
        WordPair("funny", "vtipný"),
        WordPair("sad", "smutný"),
        WordPair("curious", "zvědavý"),
        WordPair("scary", "děsivý"),
        WordPair("surprising", "překvapivý"),
        WordPair("expected", "očekávaný"),
        WordPair("unexpected", "nečekaný"),
        WordPair("wise", "moudrý"),
        WordPair("tasty", "chutný"),
        WordPair("salty", "slaný"),
        WordPair("sweet", "sladký"),
        WordPair("bitter", "hořký"),
        WordPair("spicy", "pálivý"),
        WordPair("educated", "vzdělaný"),
        WordPair("tired", "unavený"),
        WordPair("sleepy", "ospalý"),
        WordPair("anxious", "úzkostný"),
        WordPair("clumsy", "nešikovný"),
        WordPair("perfect", "dokonalý"),
        WordPair("accurate", "přesný"),
        WordPair("inaccurate", "nepřesný"),
        WordPair("religious", "věřící / náboženský"),
        WordPair("secular", "světský / sekulární"),
        WordPair("expensive", "drahý"),
        WordPair("cheap", "levný"),
        WordPair("crazy", "šílený / bláznivý"),
        WordPair("difficult", "obtížný"),
        WordPair("easy", "snadný")
    )),
    DQCategory("Sport", listOf(
        WordPair("coach", "trenér"),
        WordPair("player", "hráč"),
        WordPair("ball", "míč"),
        WordPair("tournament", "turnaj"),
        WordPair("team", "tým"),
        WordPair("referee", "rozhodčí"),
        WordPair("basketball", "basketbal"),
        WordPair("soccer", "fotbal"),
        WordPair("tennis", "tenis"),
        WordPair("golf", "golf"),
        WordPair("wrestling", "zápas / zápasení"),
        WordPair("boxing", "box"),
        WordPair("athlete", "sportovec"),
        WordPair("crowd", "dav / diváci"),
        WordPair("stadium", "stadion"),
        WordPair("result", "výsledek"),
        WordPair("tie / draw", "remíza"),
        WordPair("kick", "kop"),
        WordPair("penalty kick", "penalta"),
        WordPair("offside", "ofsajd"),
        WordPair("time out", "oddechový čas"),
        WordPair("substitution", "střídání")
    )),
    DQCategory("Business and Marketing", listOf(
        WordPair("stocks / shares", "akcie"),
        WordPair("companies", "společnosti / firmy"),
        WordPair("to buy", "koupit"),
        WordPair("to sell", "prodat"),
        WordPair("tax", "daň"),
        WordPair("income", "příjem"),
        WordPair("revenue", "tržby"),
        WordPair("profit", "zisk"),
        WordPair("loss", "ztráta"),
        WordPair("stock options", "akciové opce"),
        WordPair("perception", "vnímání"),
        WordPair("framing", "rámování"),
        WordPair("market", "trh"),
        WordPair("ad (advertisement)", "inzerát"),
        WordPair("commercial (TV / radio)", "reklama / reklamní spot"),
        WordPair("marketing", "marketing"),
        WordPair("campaign", "kampaň"),
        WordPair("costs", "náklady"),
        WordPair("persuasion", "přesvědčování"),
        WordPair("fallacy", "logický klam"),
        WordPair("money", "peníze"),
        WordPair("investment", "investice"),
        WordPair("risk", "riziko"),
        WordPair("regulation", "regulace")
    )),
    DQCategory("Politics", listOf(
        WordPair("political party", "politická strana"),
        WordPair("prime minister", "předseda vlády / premiér"),
        WordPair("president", "prezident"),
        WordPair("elections", "volby"),
        WordPair("votes", "hlasy"),
        WordPair("ballot", "hlasovací lístek"),
        WordPair("parliament", "parlament"),
        WordPair("campaign", "kampaň"),
        WordPair("propaganda", "propaganda"),
        WordPair("minister", "ministr"),
        WordPair("Secretary of State (US)", "ministr zahraničí USA"),
        WordPair("king", "král"),
        WordPair("queen", "královna"),
        WordPair("court", "soud"),
        WordPair("law", "zákon"),
        WordPair("rules", "pravidla"),
        WordPair("regulations", "předpisy"),
        WordPair("appeal", "odvolání"),
        WordPair("legislator", "zákonodárce"),
        WordPair("coalition", "koalice"),
        WordPair("impeachment", "impeachment / ústavní žaloba"),
        WordPair("bill (proposed law)", "návrh zákona")
    ))
)

private fun generateDQQuestions(category: DQCategory, engToCzech: Boolean): List<QuizQuestion> {
    fun answerOf(pair: WordPair) = if (engToCzech) pair.czech else pair.english
    return category.words.shuffled().take(10).map { pair ->
        val correctAnswer = answerOf(pair)
        val wrongAnswers = category.words
            .map { answerOf(it) }
            .filter { it != correctAnswer }
            .distinct()
            .shuffled()
            .take(3)
        QuizQuestion(
            prompt = if (engToCzech) pair.english else pair.czech,
            correctAnswer = correctAnswer,
            options = (wrongAnswers + correctAnswer).shuffled()
        )
    }
}

// ── Screen ────────────────────────────────────────────────────────────────────

@Composable
fun DictionaryQuizScreen(navController: NavController) {
    var showSettings by remember { mutableStateOf(true) }
    var engToCzech by remember { mutableStateOf(true) }
    var categoryIndex by remember { mutableStateOf(0) }
    var quizKey by remember { mutableStateOf(0) }

    if (showSettings) {
        DQSettingsScreen(
            navController = navController,
            engToCzech = engToCzech,
            onDirectionChanged = { engToCzech = it },
            categoryIndex = categoryIndex,
            onCategoryChanged = { categoryIndex = it },
            onStart = { showSettings = false; quizKey++ }
        )
    } else {
        key(quizKey) {
            DQContent(
                navController = navController,
                category = dqCategories[categoryIndex],
                engToCzech = engToCzech,
                onPlayAgain = { showSettings = true }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DQSettingsScreen(
    navController: NavController,
    engToCzech: Boolean,
    onDirectionChanged: (Boolean) -> Unit,
    categoryIndex: Int,
    onCategoryChanged: (Int) -> Unit,
    onStart: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dictionary Quiz", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text("Direction", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
            Spacer(Modifier.height(4.dp))
            listOf(
                true to "English to Czech",
                false to "Czech to English"
            ).forEach { (value, label) ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    RadioButton(selected = engToCzech == value, onClick = { onDirectionChanged(value) })
                    Text(label, fontSize = 15.sp)
                }
            }

            Spacer(Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(Modifier.height(8.dp))

            Text("Category", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
            Spacer(Modifier.height(4.dp))
            dqCategories.forEachIndexed { index, category ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    RadioButton(selected = categoryIndex == index, onClick = { onCategoryChanged(index) })
                    Text(category.title, fontSize = 15.sp)
                }
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = onStart,
                modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue)
            ) {
                Text("Start Quiz", fontSize = 18.sp, color = Color.White)
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DQContent(
    navController: NavController,
    category: DQCategory,
    engToCzech: Boolean,
    onPlayAgain: () -> Unit
) {
    val questions = remember { generateDQQuestions(category, engToCzech) }
    var currentIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var score by remember { mutableStateOf(0) }
    var quizFinished by remember { mutableStateOf(false) }
    val direction = if (engToCzech) "Eng → Cz" else "Cz → Eng"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${category.title} ($direction)", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        if (quizFinished) {
            DQScoreScreen(
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
            DQQuestionCard(
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
private fun DQQuestionCard(
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
        Text(text = "Question $questionNumber / $totalQuestions", fontSize = 14.sp, color = Color.Gray)
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
                Text(text = option, fontSize = 16.sp, color = Color.White, textAlign = TextAlign.Center)
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
private fun DQScoreScreen(
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
        Text(text = "Quiz Complete!", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
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
