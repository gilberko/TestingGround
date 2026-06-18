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

private data class CCQEntry(
    val nominative: String,
    val english: String,
    val caseName: String,
    val form: String,
    val gender: String,   // "Masculine", "Feminine", "Neuter"
    val number: String    // "Singular", "Plural"
)

private fun phrase(
    nom: String, eng: String,
    gen: String, dat: String, acc: String, voc: String, lok: String, ins: String,
    gender: String, number: String
): List<CCQEntry> = listOf(
    CCQEntry(nom, eng, "Genitiv (of / without)", gen, gender, number),
    CCQEntry(nom, eng, "Dativ (to / for)", dat, gender, number),
    CCQEntry(nom, eng, "Akuzativ (direct object)", acc, gender, number),
    CCQEntry(nom, eng, "Vokativ (calling out)", voc, gender, number),
    CCQEntry(nom, eng, "Lokál (about / in)", lok, gender, number),
    CCQEntry(nom, eng, "Instrumentál (with / by)", ins, gender, number)
)

private val ccqBank: List<CCQEntry> = listOf(
    phrase("chytrý muž",    "a smart man",            "chytrého muže",   "chytrému muži",     "chytrého muže",  "chytrý muži",    "chytrém muži",     "chytrým mužem",    "Masculine", "Singular"),
    phrase("chytří muži",   "smart men",              "chytrých mužů",   "chytrým mužům",     "chytré muže",    "chytří muži",    "chytrých mužích",  "chytrými muži",    "Masculine", "Plural"),
    phrase("krásná žena",   "a beautiful woman",      "krásné ženy",     "krásné ženě",       "krásnou ženu",   "krásná ženo",    "krásné ženě",      "krásnou ženou",    "Feminine",  "Singular"),
    phrase("krásné ženy",   "beautiful women",        "krásných žen",    "krásným ženám",     "krásné ženy",    "krásné ženy",    "krásných ženách",  "krásnými ženami",  "Feminine",  "Plural"),
    phrase("červené auto",  "a red car",              "červeného auta",  "červenému autu",    "červené auto",   "červené auto",   "červeném autě",    "červeným autem",   "Neuter",    "Singular"),
    phrase("červená auta",  "red cars",               "červených aut",   "červeným autům",    "červená auta",   "červená auta",   "červených autech", "červenými auty",   "Neuter",    "Plural"),
    phrase("nový telefon",  "a new telephone",        "nového telefonu", "novému telefonu",   "nový telefon",   "nový telefon",   "novém telefonu",   "novým telefonem",  "Masculine", "Singular"),
    phrase("cizí student",  "a foreign student",      "cizího studenta", "cizímu studentovi", "cizího studenta","cizí studente",  "cizím studentovi", "cizím studentem",  "Masculine", "Singular"),
    phrase("moderní kuchyně","a modern kitchen",      "moderní kuchyně", "moderní kuchyni",   "moderní kuchyni","moderní kuchyně","moderní kuchyni",  "moderní kuchyní",  "Feminine",  "Singular"),
    phrase("modré moře",    "a blue sea",             "modrého moře",    "modrému moři",      "modré moře",     "modré moře",     "modrém moři",      "modrým mořem",     "Neuter",    "Singular"),
    phrase("červený hrad",  "a red castle",           "červeného hradu", "červenému hradu",   "červený hrad",   "červený hrade",  "červeném hradě",   "červeným hradem",  "Masculine", "Singular"),
    phrase("červené hrady", "red castles",            "červených hradů", "červeným hradům",   "červené hrady",  "červené hrady",  "červených hradech","červenými hrady",  "Masculine", "Plural"),
    phrase("bílá kočka",    "a white cat",            "bílé kočky",      "bílé kočce",        "bílou kočku",    "bílá kočko",     "bílé kočce",       "bílou kočkou",     "Feminine",  "Singular"),
    phrase("bílé kočky",    "white cats",             "bílých koček",    "bílým kočkám",      "bílé kočky",     "bílé kočky",     "bílých kočkách",   "bílými kočkami",   "Feminine",  "Plural"),
    phrase("nový dům",      "a new house",            "nového domu",     "novému domu",       "nový dům",       "nový dome",      "novém domě",       "novým domem",      "Masculine", "Singular"),
    phrase("nové domy",     "new houses",             "nových domů",     "novým domům",       "nové domy",      "nové domy",      "nových domech",    "novými domy",      "Masculine", "Plural"),
    phrase("drahý byt",     "an expensive apartment", "drahého bytu",    "drahému bytu",      "drahý byt",      "drahý byte",     "drahém bytě",      "drahým bytem",     "Masculine", "Singular"),
    phrase("drahé byty",    "expensive apartments",   "drahých bytů",    "drahým bytům",      "drahé byty",     "drahé byty",     "drahých bytech",   "drahými byty",     "Masculine", "Plural")
).flatten()

private fun generateCaseConjQuestions(
    selectedCases: Set<String>,
    selectedCombos: Set<Pair<String, String>>,
    questionCount: Int
): List<QuizQuestion> {
    val filtered = ccqBank.filter { entry ->
        entry.caseName in selectedCases && (entry.number to entry.gender) in selectedCombos
    }
    val selected = filtered.shuffled().take(questionCount.coerceAtMost(filtered.size))
    return selected.map { entry ->
        val prompt = "${entry.nominative}  —  ${entry.english}  —  ${entry.caseName}"
        val correct = entry.form
        val samePhraseOtherCases = ccqBank.filter {
            it.nominative == entry.nominative && it.caseName != entry.caseName && it.form != correct
        }.shuffled().distinctBy { it.form }
        val fallback = ccqBank.filter {
            it.nominative != entry.nominative && it.form != correct
        }.shuffled()
        val wrongs = (samePhraseOtherCases + fallback).distinctBy { it.form }.take(3).map { it.form }
        QuizQuestion(prompt, correct, (wrongs + correct).shuffled())
    }
}

// ── Screen ────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaseConjugationQuizScreen(navController: NavController) {
    val allCases = remember {
        setOf(
            "Genitiv (of / without)",
            "Dativ (to / for)",
            "Akuzativ (direct object)",
            "Vokativ (calling out)",
            "Lokál (about / in)",
            "Instrumentál (with / by)"
        )
    }
    val allCombos = remember {
        setOf(
            "Singular" to "Masculine",
            "Singular" to "Feminine",
            "Singular" to "Neuter",
            "Plural" to "Masculine",
            "Plural" to "Feminine",
            "Plural" to "Neuter"
        )
    }
    var showSettings by remember { mutableStateOf(true) }
    var selectedCases by remember { mutableStateOf(allCases) }
    var selectedCombos by remember { mutableStateOf(allCombos) }
    var questionCount by remember { mutableStateOf(10) }
    var quizKey by remember { mutableStateOf(0) }

    if (showSettings) {
        CCQSettingsScreen(
            navController = navController,
            allCases = allCases,
            selectedCases = selectedCases,
            onCasesChanged = { selectedCases = it },
            allCombos = allCombos,
            selectedCombos = selectedCombos,
            onCombosChanged = { selectedCombos = it },
            questionCount = questionCount,
            onQuestionCountChanged = { questionCount = it },
            onStart = { showSettings = false; quizKey++ }
        )
    } else {
        key(quizKey) {
            CCQContent(
                navController = navController,
                selectedCases = selectedCases,
                selectedCombos = selectedCombos,
                questionCount = questionCount,
                onPlayAgain = { showSettings = true }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CCQSettingsScreen(
    navController: NavController,
    allCases: Set<String>,
    selectedCases: Set<String>,
    onCasesChanged: (Set<String>) -> Unit,
    allCombos: Set<Pair<String, String>>,
    selectedCombos: Set<Pair<String, String>>,
    onCombosChanged: (Set<Pair<String, String>>) -> Unit,
    questionCount: Int,
    onQuestionCountChanged: (Int) -> Unit,
    onStart: () -> Unit
) {
    val canStart = selectedCases.isNotEmpty() && selectedCombos.isNotEmpty()

    val caseDisplayLabels = mapOf(
        "Genitiv (of / without)" to "Genitiv",
        "Dativ (to / for)" to "Dativ",
        "Akuzativ (direct object)" to "Akuzativ",
        "Vokativ (calling out)" to "Vokativ",
        "Lokál (about / in)" to "Lokál",
        "Instrumentál (with / by)" to "Instrumentál"
    )

    val orderedCombos = listOf(
        "Singular" to "Masculine",
        "Singular" to "Feminine",
        "Singular" to "Neuter",
        "Plural" to "Masculine",
        "Plural" to "Feminine",
        "Plural" to "Neuter"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Case Conjugation Quiz", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text("Cases to Include", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
            Spacer(modifier = Modifier.height(4.dp))

            allCases.forEach { caseName ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = caseName in selectedCases,
                        onCheckedChange = { checked ->
                            onCasesChanged(if (checked) selectedCases + caseName else selectedCases - caseName)
                        }
                    )
                    Text(
                        text = caseDisplayLabels[caseName] ?: caseName,
                        fontSize = 15.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))

            Text("Gender & Number", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
            Spacer(modifier = Modifier.height(4.dp))

            orderedCombos.forEach { combo ->
                val (number, gender) = combo
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = combo in selectedCombos,
                        onCheckedChange = { checked ->
                            onCombosChanged(if (checked) selectedCombos + combo else selectedCombos - combo)
                        }
                    )
                    Text(
                        text = "$number — $gender",
                        fontSize = 15.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))

            Text("Number of Questions", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { onQuestionCountChanged(questionCount - 1) },
                    enabled = questionCount > 5,
                    modifier = Modifier.size(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text("−", fontSize = 20.sp, color = Color.White)
                }
                Text(
                    text = questionCount.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 24.dp),
                    textAlign = TextAlign.Center
                )
                Button(
                    onClick = { onQuestionCountChanged(questionCount + 1) },
                    enabled = questionCount < 50,
                    modifier = Modifier.size(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text("+", fontSize = 20.sp, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onStart,
                enabled = canStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 56.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue)
            ) {
                Text("Start Quiz", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CCQContent(
    navController: NavController,
    selectedCases: Set<String>,
    selectedCombos: Set<Pair<String, String>>,
    questionCount: Int,
    onPlayAgain: () -> Unit
) {
    val questions = remember { generateCaseConjQuestions(selectedCases, selectedCombos, questionCount) }
    var currentIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var score by remember { mutableStateOf(0) }
    var quizFinished by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Case Conjugation Quiz", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
            CCQScoreScreen(
                score = score,
                total = questions.size,
                onPlayAgain = onPlayAgain,
                onHome = {
                    navController.navigate("home") { popUpTo("home") { inclusive = false } }
                },
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            CCQQuestionCard(
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
private fun CCQQuestionCard(
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
        Text(
            text = "Question $questionNumber / $totalQuestions",
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(20.dp))
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
        Spacer(modifier = Modifier.height(8.dp))
        if (parts.size >= 3) {
            Text(
                text = "→ ${parts[2]}",
                fontSize = 16.sp,
                color = Color(0xFF1565C0),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
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
                    fontSize = 15.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
        if (selectedAnswer != null) {
            Spacer(modifier = Modifier.height(24.dp))
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
private fun CCQScoreScreen(
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
            modifier = Modifier.width(220.dp).heightIn(min = 56.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue)
        ) {
            Text("Play Again", fontSize = 17.sp, color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))
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
