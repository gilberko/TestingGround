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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// ── Data ─────────────────────────────────────────────────────────────────────

private data class PCQEntry(
    val nominative: String,  // Czech pronoun label shown in question
    val english: String,     // English translation
    val type: String,        // "personal" or "possessive"
    val gender: String,      // "" for personal; "masc/neut", "fem", "pl" for possessive
    val case: String,        // "Genitive", "Dative", "Accusative", "Locative", "Instrumental"
    val form: String         // all valid forms, separated by " / "
)

private val pcqBank: List<PCQEntry> = listOf(

    // ── Personal / Object pronouns ─────────────────────────────────────────
    // já (I)
    PCQEntry("já", "I", "personal", "", "Genitive",     "mě / mne"),
    PCQEntry("já", "I", "personal", "", "Dative",       "mi / mně"),
    PCQEntry("já", "I", "personal", "", "Accusative",   "mě / mne"),
    PCQEntry("já", "I", "personal", "", "Locative",     "mně"),
    PCQEntry("já", "I", "personal", "", "Instrumental", "mnou"),

    // ty (you sg.)
    PCQEntry("ty", "you (sg.)", "personal", "", "Genitive",     "tě / tebe"),
    PCQEntry("ty", "you (sg.)", "personal", "", "Dative",       "ti / tobě"),
    PCQEntry("ty", "you (sg.)", "personal", "", "Accusative",   "tě / tebe"),
    PCQEntry("ty", "you (sg.)", "personal", "", "Locative",     "tobě"),
    PCQEntry("ty", "you (sg.)", "personal", "", "Instrumental", "tebou"),

    // on / ono (he / it)
    PCQEntry("on / ono", "he / it", "personal", "", "Genitive",     "ho / jeho"),
    PCQEntry("on / ono", "he / it", "personal", "", "Dative",       "mu / jemu"),
    PCQEntry("on / ono", "he / it", "personal", "", "Accusative",   "ho / jej / jeho"),
    PCQEntry("on / ono", "he / it", "personal", "", "Locative",     "něm"),
    PCQEntry("on / ono", "he / it", "personal", "", "Instrumental", "jím / ním"),

    // ona (she)
    PCQEntry("ona", "she", "personal", "", "Genitive",     "jí / ní"),
    PCQEntry("ona", "she", "personal", "", "Dative",       "jí / ní"),
    PCQEntry("ona", "she", "personal", "", "Accusative",   "ji / ni"),
    PCQEntry("ona", "she", "personal", "", "Locative",     "ní"),
    PCQEntry("ona", "she", "personal", "", "Instrumental", "jí / ní"),

    // my (we)
    PCQEntry("my", "we", "personal", "", "Genitive",     "nás"),
    PCQEntry("my", "we", "personal", "", "Dative",       "nám"),
    PCQEntry("my", "we", "personal", "", "Accusative",   "nás"),
    PCQEntry("my", "we", "personal", "", "Locative",     "nás"),
    PCQEntry("my", "we", "personal", "", "Instrumental", "námi"),

    // vy (you pl. / formal)
    PCQEntry("vy", "you (pl.)", "personal", "", "Genitive",     "vás"),
    PCQEntry("vy", "you (pl.)", "personal", "", "Dative",       "vám"),
    PCQEntry("vy", "you (pl.)", "personal", "", "Accusative",   "vás"),
    PCQEntry("vy", "you (pl.)", "personal", "", "Locative",     "vás"),
    PCQEntry("vy", "you (pl.)", "personal", "", "Instrumental", "vámi"),

    // oni / ony (they)
    PCQEntry("oni / ony", "they", "personal", "", "Genitive",     "jich / nich"),
    PCQEntry("oni / ony", "they", "personal", "", "Dative",       "jim / nim"),
    PCQEntry("oni / ony", "they", "personal", "", "Accusative",   "je / ně"),
    PCQEntry("oni / ony", "they", "personal", "", "Locative",     "nich"),
    PCQEntry("oni / ony", "they", "personal", "", "Instrumental", "jimi / nimi"),

    // ── Possessive pronouns ────────────────────────────────────────────────
    // Accusative is excluded — it equals genitive for masc animate or nominative for other genders.

    // můj / moje (já → my)
    PCQEntry("můj (já)",  "my",  "possessive", "masc/neut", "Genitive",     "mého"),
    PCQEntry("můj (já)",  "my",  "possessive", "fem",       "Genitive",     "mé / mojí"),
    PCQEntry("můj (já)",  "my",  "possessive", "pl",        "Genitive",     "mých"),
    PCQEntry("můj (já)",  "my",  "possessive", "masc/neut", "Dative",       "mému"),
    PCQEntry("můj (já)",  "my",  "possessive", "fem",       "Dative",       "mé / mojí"),
    PCQEntry("můj (já)",  "my",  "possessive", "pl",        "Dative",       "mým"),
    PCQEntry("můj (já)",  "my",  "possessive", "masc/neut", "Locative",     "mém"),
    PCQEntry("můj (já)",  "my",  "possessive", "fem",       "Locative",     "mé"),
    PCQEntry("můj (já)",  "my",  "possessive", "pl",        "Locative",     "mých"),
    PCQEntry("můj (já)",  "my",  "possessive", "masc/neut", "Instrumental", "mým"),
    PCQEntry("můj (já)",  "my",  "possessive", "fem",       "Instrumental", "mojí / mou"),
    PCQEntry("můj (já)",  "my",  "possessive", "pl",        "Instrumental", "mými"),

    // tvůj / tvoje (ty → your)
    PCQEntry("tvůj (ty)", "your", "possessive", "masc/neut", "Genitive",     "tvého"),
    PCQEntry("tvůj (ty)", "your", "possessive", "fem",       "Genitive",     "tvé / tvojí"),
    PCQEntry("tvůj (ty)", "your", "possessive", "pl",        "Genitive",     "tvých"),
    PCQEntry("tvůj (ty)", "your", "possessive", "masc/neut", "Dative",       "tvému"),
    PCQEntry("tvůj (ty)", "your", "possessive", "fem",       "Dative",       "tvé / tvojí"),
    PCQEntry("tvůj (ty)", "your", "possessive", "pl",        "Dative",       "tvým"),
    PCQEntry("tvůj (ty)", "your", "possessive", "masc/neut", "Locative",     "tvém"),
    PCQEntry("tvůj (ty)", "your", "possessive", "fem",       "Locative",     "tvé"),
    PCQEntry("tvůj (ty)", "your", "possessive", "pl",        "Locative",     "tvých"),
    PCQEntry("tvůj (ty)", "your", "possessive", "masc/neut", "Instrumental", "tvým"),
    PCQEntry("tvůj (ty)", "your", "possessive", "fem",       "Instrumental", "tvojí / tvou"),
    PCQEntry("tvůj (ty)", "your", "possessive", "pl",        "Instrumental", "tvými"),

    // její (ona → her)
    PCQEntry("její (ona)", "her", "possessive", "masc/neut", "Genitive",     "jejího"),
    PCQEntry("její (ona)", "her", "possessive", "fem",       "Genitive",     "její"),
    PCQEntry("její (ona)", "her", "possessive", "pl",        "Genitive",     "jejích"),
    PCQEntry("její (ona)", "her", "possessive", "masc/neut", "Dative",       "jejímu"),
    PCQEntry("její (ona)", "her", "possessive", "fem",       "Dative",       "její"),
    PCQEntry("její (ona)", "her", "possessive", "pl",        "Dative",       "jejím"),
    PCQEntry("její (ona)", "her", "possessive", "masc/neut", "Locative",     "jejím"),
    PCQEntry("její (ona)", "her", "possessive", "fem",       "Locative",     "její"),
    PCQEntry("její (ona)", "her", "possessive", "pl",        "Locative",     "jejích"),
    PCQEntry("její (ona)", "her", "possessive", "masc/neut", "Instrumental", "jejím"),
    PCQEntry("její (ona)", "her", "possessive", "fem",       "Instrumental", "její"),
    PCQEntry("její (ona)", "her", "possessive", "pl",        "Instrumental", "jejími"),

    // náš / naše (my → our)
    PCQEntry("náš (my)",  "our", "possessive", "masc/neut", "Genitive",     "našeho"),
    PCQEntry("náš (my)",  "our", "possessive", "fem",       "Genitive",     "naší"),
    PCQEntry("náš (my)",  "our", "possessive", "pl",        "Genitive",     "našich"),
    PCQEntry("náš (my)",  "our", "possessive", "masc/neut", "Dative",       "našemu"),
    PCQEntry("náš (my)",  "our", "possessive", "fem",       "Dative",       "naší"),
    PCQEntry("náš (my)",  "our", "possessive", "pl",        "Dative",       "našim"),
    PCQEntry("náš (my)",  "our", "possessive", "masc/neut", "Locative",     "našem"),
    PCQEntry("náš (my)",  "our", "possessive", "fem",       "Locative",     "naší"),
    PCQEntry("náš (my)",  "our", "possessive", "pl",        "Locative",     "našich"),
    PCQEntry("náš (my)",  "our", "possessive", "masc/neut", "Instrumental", "naším"),
    PCQEntry("náš (my)",  "our", "possessive", "fem",       "Instrumental", "naší"),
    PCQEntry("náš (my)",  "our", "possessive", "pl",        "Instrumental", "našimi"),

    // váš / vaše (vy → your pl.)
    PCQEntry("váš (vy)",  "your (pl.)", "possessive", "masc/neut", "Genitive",     "vašeho"),
    PCQEntry("váš (vy)",  "your (pl.)", "possessive", "fem",       "Genitive",     "vaší"),
    PCQEntry("váš (vy)",  "your (pl.)", "possessive", "pl",        "Genitive",     "vašich"),
    PCQEntry("váš (vy)",  "your (pl.)", "possessive", "masc/neut", "Dative",       "vašemu"),
    PCQEntry("váš (vy)",  "your (pl.)", "possessive", "fem",       "Dative",       "vaší"),
    PCQEntry("váš (vy)",  "your (pl.)", "possessive", "pl",        "Dative",       "vašim"),
    PCQEntry("váš (vy)",  "your (pl.)", "possessive", "masc/neut", "Locative",     "vašem"),
    PCQEntry("váš (vy)",  "your (pl.)", "possessive", "fem",       "Locative",     "vaší"),
    PCQEntry("váš (vy)",  "your (pl.)", "possessive", "pl",        "Locative",     "vašich"),
    PCQEntry("váš (vy)",  "your (pl.)", "possessive", "masc/neut", "Instrumental", "vaším"),
    PCQEntry("váš (vy)",  "your (pl.)", "possessive", "fem",       "Instrumental", "vaší"),
    PCQEntry("váš (vy)",  "your (pl.)", "possessive", "pl",        "Instrumental", "vašimi"),

    // svůj (reflexive possessive — one's own)
    PCQEntry("svůj",      "one's own", "possessive", "masc/neut", "Genitive",     "svého"),
    PCQEntry("svůj",      "one's own", "possessive", "fem",       "Genitive",     "své / svojí"),
    PCQEntry("svůj",      "one's own", "possessive", "pl",        "Genitive",     "svých"),
    PCQEntry("svůj",      "one's own", "possessive", "masc/neut", "Dative",       "svému"),
    PCQEntry("svůj",      "one's own", "possessive", "fem",       "Dative",       "své / svojí"),
    PCQEntry("svůj",      "one's own", "possessive", "pl",        "Dative",       "svým"),
    PCQEntry("svůj",      "one's own", "possessive", "masc/neut", "Locative",     "svém"),
    PCQEntry("svůj",      "one's own", "possessive", "fem",       "Locative",     "své"),
    PCQEntry("svůj",      "one's own", "possessive", "pl",        "Locative",     "svých"),
    PCQEntry("svůj",      "one's own", "possessive", "masc/neut", "Instrumental", "svým"),
    PCQEntry("svůj",      "one's own", "possessive", "fem",       "Instrumental", "svojí / svou"),
    PCQEntry("svůj",      "one's own", "possessive", "pl",        "Instrumental", "svými"),
)

private fun generatePCQQuestions(
    selectedCases: Set<String>,
    selectedType: String
): List<QuizQuestion> {
    val filtered = pcqBank.filter { entry ->
        entry.case in selectedCases && when (selectedType) {
            "personal"   -> entry.type == "personal"
            "possessive" -> entry.type == "possessive"
            else         -> true
        }
    }
    val selected = filtered.shuffled().take(10.coerceAtMost(filtered.size))
    return selected.map { entry ->
        val genderTag = if (entry.gender.isNotEmpty()) ", ${entry.gender}" else ""
        val prompt = "${entry.nominative}  —  ${entry.english} (${entry.type}${genderTag})  —  ${entry.case}"
        val correct = entry.form
        val sameContext = pcqBank.filter {
            it.nominative == entry.nominative && it.gender == entry.gender &&
            it.case != entry.case && it.form != correct
        }.shuffled().distinctBy { it.form }
        val fallback = pcqBank.filter {
            it.type == entry.type && it.nominative != entry.nominative && it.form != correct
        }.shuffled()
        val wrongs = (sameContext + fallback).distinctBy { it.form }.take(3).map { it.form }
        QuizQuestion(prompt, correct, (wrongs + correct).shuffled())
    }
}

// ── Screen ────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PronounCasesQuizScreen(navController: NavController) {
    val allCases = remember { setOf("Genitive", "Dative", "Accusative", "Locative", "Instrumental") }
    var showSettings by remember { mutableStateOf(true) }
    var selectedCases by remember { mutableStateOf(allCases) }
    var selectedType by remember { mutableStateOf("both") }
    var quizKey by remember { mutableStateOf(0) }

    if (showSettings) {
        PCQSettingsScreen(
            navController = navController,
            allCases = allCases,
            selectedCases = selectedCases,
            onCasesChanged = { selectedCases = it },
            selectedType = selectedType,
            onTypeChanged = { selectedType = it },
            onStart = { showSettings = false; quizKey++ }
        )
    } else {
        key(quizKey) {
            PCQContent(
                navController = navController,
                selectedCases = selectedCases,
                selectedType = selectedType,
                onPlayAgain = { showSettings = true }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PCQSettingsScreen(
    navController: NavController,
    allCases: Set<String>,
    selectedCases: Set<String>,
    onCasesChanged: (Set<String>) -> Unit,
    selectedType: String,
    onTypeChanged: (String) -> Unit,
    onStart: () -> Unit
) {
    val orderedCases = listOf("Genitive", "Dative", "Accusative", "Locative", "Instrumental")
    val canStart = selectedCases.isNotEmpty()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pronoun Cases Quiz", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
            Text("Cases to Include", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
            Spacer(Modifier.height(4.dp))
            orderedCases.forEach { caseName ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Checkbox(
                        checked = caseName in selectedCases,
                        onCheckedChange = { checked ->
                            onCasesChanged(if (checked) selectedCases + caseName else selectedCases - caseName)
                        }
                    )
                    Text(caseName, fontSize = 15.sp)
                }
            }
            Text(
                "Note: Accusative is included for personal pronouns only. Possessive accusative equals genitive (masc animate) or nominative (other) and is not quizzed.",
                fontSize = 12.sp, fontStyle = FontStyle.Italic, color = Color.Gray,
                modifier = Modifier.padding(start = 8.dp, top = 2.dp, bottom = 4.dp)
            )

            Spacer(Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(Modifier.height(8.dp))

            Text("Pronoun Type", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
            Spacer(Modifier.height(4.dp))

            listOf(
                "personal"   to "Personal / Object pronouns only",
                "possessive" to "Possessive pronouns only",
                "both"       to "Both"
            ).forEach { (value, label) ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    RadioButton(selected = selectedType == value, onClick = { onTypeChanged(value) })
                    Text(label, fontSize = 15.sp)
                }
            }
            Text(
                "Note: jeho (his/its) and jejich (their) never change form and are not quizzed. Svůj (one's own — reflexive possessive) is included in the possessive section.",
                fontSize = 12.sp, fontStyle = FontStyle.Italic, color = Color.Gray,
                modifier = Modifier.padding(start = 8.dp, top = 2.dp, bottom = 4.dp)
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = onStart,
                enabled = canStart,
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
private fun PCQContent(
    navController: NavController,
    selectedCases: Set<String>,
    selectedType: String,
    onPlayAgain: () -> Unit
) {
    val questions = remember { generatePCQQuestions(selectedCases, selectedType) }
    var currentIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var score by remember { mutableStateOf(0) }
    var quizFinished by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pronoun Cases Quiz", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
            PCQScoreScreen(
                score = score,
                total = questions.size,
                onPlayAgain = onPlayAgain,
                onHome = { navController.navigate("home") { popUpTo("home") { inclusive = false } } },
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            PCQQuestionCard(
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
private fun PCQQuestionCard(
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

    val parts = question.prompt.split("  —  ")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Question $questionNumber / $totalQuestions", fontSize = 14.sp, color = Color.Gray)
        Spacer(Modifier.height(20.dp))
        if (parts.isNotEmpty()) {
            Text(
                text = parts[0],
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
        if (parts.size >= 2) {
            Text(
                text = parts[1],
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(Modifier.height(8.dp))
        if (parts.size >= 3) {
            Text(
                text = "→ ${parts[2]}",
                fontSize = 16.sp,
                color = ButtonBlue,
                textAlign = TextAlign.Center
            )
        }
        Spacer(Modifier.height(24.dp))
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
                Text(text = option, fontSize = 15.sp, color = Color.White, textAlign = TextAlign.Center)
            }
        }
        if (selectedAnswer != null) {
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = onNext,
                modifier = Modifier.width(200.dp).heightIn(min = 52.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue)
            ) {
                Text("Next", fontSize = 17.sp, color = Color.White)
            }
        }
    }
}

@Composable
private fun PCQScoreScreen(
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
        Text("Quiz Complete!", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(Modifier.height(16.dp))
        Text(
            text = "You got $score / $total correct!",
            fontSize = 20.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(40.dp))
        Button(
            onClick = onPlayAgain,
            modifier = Modifier.width(220.dp).heightIn(min = 56.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue)
        ) {
            Text("Play Again", fontSize = 17.sp, color = Color.White)
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onHome,
            modifier = Modifier.width(220.dp).heightIn(min = 56.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF546E7A))
        ) {
            Text("Home", fontSize = 17.sp, color = Color.White)
        }
    }
}
