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
    val form: String
)

private fun phrase(
    nom: String, eng: String,
    gen: String, dat: String, acc: String, voc: String, lok: String, ins: String
): List<CCQEntry> = listOf(
    CCQEntry(nom, eng, "Genitiv (of / without)", gen),
    CCQEntry(nom, eng, "Dativ (to / for)", dat),
    CCQEntry(nom, eng, "Akuzativ (direct object)", acc),
    CCQEntry(nom, eng, "Vokativ (calling out)", voc),
    CCQEntry(nom, eng, "Lokál (about / in)", lok),
    CCQEntry(nom, eng, "Instrumentál (with / by)", ins)
)

private val ccqBank: List<CCQEntry> = listOf(
    phrase("chytrý muž",    "a smart man",           "chytrého muže",   "chytrému muži",     "chytrého muže",  "chytrý muži",    "chytrém muži",     "chytrým mužem"),
    phrase("chytří muži",   "smart men",             "chytrých mužů",   "chytrým mužům",     "chytré muže",    "chytří muži",    "chytrých mužích",  "chytrými muži"),
    phrase("krásná žena",   "a beautiful woman",     "krásné ženy",     "krásné ženě",       "krásnou ženu",   "krásná ženo",    "krásné ženě",      "krásnou ženou"),
    phrase("krásné ženy",   "beautiful women",       "krásných žen",    "krásným ženám",     "krásné ženy",    "krásné ženy",    "krásných ženách",  "krásnými ženami"),
    phrase("červené auto",  "a red car",             "červeného auta",  "červenému autu",    "červené auto",   "červené auto",   "červeném autě",    "červeným autem"),
    phrase("červená auta",  "red cars",              "červených aut",   "červeným autům",    "červená auta",   "červená auta",   "červených autech", "červenými auty"),
    phrase("nový telefon",  "a new telephone",       "nového telefonu", "novému telefonu",   "nový telefon",   "nový telefon",   "novém telefonu",   "novým telefonem"),
    phrase("cizí student",  "a foreign student",     "cizího studenta", "cizímu studentovi", "cizího studenta","cizí studente",  "cizím studentovi", "cizím studentem"),
    phrase("moderní kuchyně","a modern kitchen",     "moderní kuchyně", "moderní kuchyni",   "moderní kuchyni","moderní kuchyně","moderní kuchyni",  "moderní kuchyní"),
    phrase("modré moře",    "a blue sea",            "modrého moře",    "modrému moři",      "modré moře",     "modré moře",     "modrém moři",      "modrým mořem"),
    phrase("červený hrad",  "a red castle",          "červeného hradu", "červenému hradu",   "červený hrad",   "červený hrade",  "červeném hradě",   "červeným hradem"),
    phrase("červené hrady", "red castles",           "červených hradů", "červeným hradům",   "červené hrady",  "červené hrady",  "červených hradech","červenými hrady"),
    phrase("bílá kočka",    "a white cat",           "bílé kočky",      "bílé kočce",        "bílou kočku",    "bílá kočko",     "bílé kočce",       "bílou kočkou"),
    phrase("bílé kočky",    "white cats",            "bílých koček",    "bílým kočkám",      "bílé kočky",     "bílé kočky",     "bílých kočkách",   "bílými kočkami"),
    phrase("nový dům",      "a new house",           "nového domu",     "novému domu",       "nový dům",       "nový dome",      "novém domě",       "novým domem"),
    phrase("nové domy",     "new houses",            "nových domů",     "novým domům",       "nové domy",      "nové domy",      "nových domech",    "novými domy"),
    phrase("drahý byt",     "an expensive apartment","drahého bytu",    "drahému bytu",      "drahý byt",      "drahý byte",     "drahém bytě",      "drahým bytem"),
    phrase("drahé byty",    "expensive apartments",  "drahých bytů",    "drahým bytům",      "drahé byty",     "drahé byty",     "drahých bytech",   "drahými byty")
).flatten()

private fun generateCaseConjQuestions(): List<QuizQuestion> {
    val selected = ccqBank.shuffled().take(10)
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
    var quizKey by remember { mutableStateOf(0) }
    key(quizKey) {
        CCQContent(navController = navController, onPlayAgain = { quizKey++ })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CCQContent(navController: NavController, onPlayAgain: () -> Unit) {
    val questions = remember { generateCaseConjQuestions() }
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
        // Czech nominative phrase
        if (parts.isNotEmpty()) {
            Text(
                text = parts[0],
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
        // English meaning
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
        // Case asked
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
