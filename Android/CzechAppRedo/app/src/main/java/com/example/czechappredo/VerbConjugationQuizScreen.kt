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

// ── Data ─────────────────────────────────────────────────────────────────────

private data class VerbConjData(
    val infinitive: String,
    val presJa: String, val presTy: String, val presOnOna: String,
    val presMy: String, val presVy: String, val presOniOny: String,
    val pastJa: String, val pastTy: String, val pastOnOna: String,
    val pastMy: String, val pastVy: String, val pastOniOny: String
)

private data class VCQEntry(
    val infinitive: String,
    val pronoun: String,
    val tense: String,
    val form: String
)

private val vcqBank: List<VerbConjData> = listOf(
    VerbConjData("dělat",
        "dělám", "děláš", "dělá", "děláme", "děláte", "dělají",
        "dělal/a jsem", "dělal/a jsi", "dělal/dělala", "dělali/y jsme", "dělali/y jste", "dělali/y"),
    VerbConjData("pracovat",
        "pracuji/pracuju", "pracuješ", "pracuje", "pracujeme", "pracujete", "pracují/pracujou",
        "pracoval/a jsem", "pracoval/a jsi", "pracoval/pracovala", "pracovali/y jsme", "pracovali/y jste", "pracovali/y"),
    VerbConjData("mluvit",
        "mluvím", "mluvíš", "mluví", "mluvíme", "mluvíte", "mluví",
        "mluvil/a jsem", "mluvil/a jsi", "mluvil/mluvila", "mluvili/y jsme", "mluvili/y jste", "mluvili/y"),
    VerbConjData("být",
        "jsem", "jsi", "je", "jsme", "jste", "jsou",
        "byl/a jsem", "byl/a jsi", "byl/byla", "byli/y jsme", "byli/y jste", "byli/y"),
    VerbConjData("jít",
        "jdu", "jdeš", "jde", "jdeme", "jdete", "jdou",
        "šel/šla jsem", "šel/šla jsi", "šel/šla", "šli/šly jsme", "šli/šly jste", "šli/šly"),
    VerbConjData("mít",
        "mám", "máš", "má", "máme", "máte", "mají",
        "měl/a jsem", "měl/a jsi", "měl/měla", "měli/y jsme", "měli/y jste", "měli/y"),
    VerbConjData("chtít",
        "chci", "chceš", "chce", "chceme", "chcete", "chtějí",
        "chtěl/a jsem", "chtěl/a jsi", "chtěl/chtěla", "chtěli/y jsme", "chtěli/y jste", "chtěli/y"),
    VerbConjData("vědět",
        "vím", "víš", "ví", "víme", "víte", "vědí",
        "věděl/a jsem", "věděl/a jsi", "věděl/věděla", "věděli/y jsme", "věděli/y jste", "věděli/y"),
    VerbConjData("jíst",
        "jím", "jíš", "jí", "jíme", "jíte", "jedí",
        "jedl/a jsem", "jedl/a jsi", "jedl/jedla", "jedli/y jsme", "jedli/y jste", "jedli/y"),
    VerbConjData("psát",
        "píšu/píši", "píšeš", "píše", "píšeme", "píšete", "píšou/píší",
        "psal/a jsem", "psal/a jsi", "psal/psala", "psali/y jsme", "psali/y jste", "psali/y"),
    VerbConjData("číst",
        "čtu", "čteš", "čte", "čteme", "čtete", "čtou",
        "četl/a jsem", "četl/a jsi", "četl/četla", "četli/y jsme", "četli/y jste", "četli/y"),
    VerbConjData("vařit",
        "vařím", "vaříš", "vaří", "vaříme", "vaříte", "vaří",
        "vařil/a jsem", "vařil/a jsi", "vařil/vařila", "vařili/y jsme", "vařili/y jste", "vařili/y"),
    VerbConjData("spát",
        "spím", "spíš", "spí", "spíme", "spíte", "spí",
        "spal/a jsem", "spal/a jsi", "spal/spala", "spali/y jsme", "spali/y jste", "spali/y"),
    VerbConjData("moci/moct",
        "mohu/můžu", "můžeš", "může", "můžeme", "můžete", "mohou/můžou",
        "mohl/a jsem", "mohl/a jsi", "mohl/mohla", "mohli/y jsme", "mohli/y jste", "mohli/y"),
    VerbConjData("studovat",
        "studuji/studuju", "studuješ", "studuje", "studujeme", "studujete", "studují/studujou",
        "studoval/a jsem", "studoval/a jsi", "studoval/studovala", "studovali/y jsme", "studovali/y jste", "studovali/y"),
    VerbConjData("vidět",
        "vidím", "vidíš", "vidí", "vidíme", "vidíte", "vidí",
        "viděl/a jsem", "viděl/a jsi", "viděl/viděla", "viděli/y jsme", "viděli/y jste", "viděli/y"),
    VerbConjData("rozumět",
        "rozumím", "rozumíš", "rozumí", "rozumíme", "rozumíte", "rozumí",
        "rozuměl/a jsem", "rozuměl/a jsi", "rozuměl/rozuměla", "rozuměli/y jsme", "rozuměli/y jste", "rozuměli/y"),
    VerbConjData("brát",
        "beru", "bereš", "bere", "bereme", "berete", "berou",
        "bral/a jsem", "bral/a jsi", "bral/brala", "brali/y jsme", "brali/y jste", "brali/y"),
    VerbConjData("říkat",
        "říkám", "říkáš", "říká", "říkáme", "říkáte", "říkají",
        "říkal/a jsem", "říkal/a jsi", "říkal/říkala", "říkali/y jsme", "říkali/y jste", "říkali/y"),
    VerbConjData("chodit",
        "chodím", "chodíš", "chodí", "chodíme", "chodíte", "chodí",
        "chodil/a jsem", "chodil/a jsi", "chodil/chodila", "chodili/y jsme", "chodili/y jste", "chodili/y"),
    VerbConjData("volat",
        "volám", "voláš", "volá", "voláme", "voláte", "volají",
        "volal/a jsem", "volal/a jsi", "volal/volala", "volali/y jsme", "volali/y jste", "volali/y"),
    VerbConjData("kupovat",
        "kupuji/kupuju", "kupuješ", "kupuje", "kupujeme", "kupujete", "kupují/kupujou",
        "kupoval/a jsem", "kupoval/a jsi", "kupoval/kupovala", "kupovali/y jsme", "kupovali/y jste", "kupovali/y"),
    VerbConjData("pomáhat",
        "pomáhám", "pomáháš", "pomáhá", "pomáháme", "pomáháte", "pomáhají",
        "pomáhal/a jsem", "pomáhal/a jsi", "pomáhal/pomáhala", "pomáhali/y jsme", "pomáhali/y jste", "pomáhali/y"),
    // Reflexive — se
    VerbConjData("učit se",
        "učím se", "učíš se", "učí se", "učíme se", "učíte se", "učí se",
        "učil/a jsem se", "učil/a jsi se", "učil/učila se", "učili/y jsme se", "učili/y jste se", "učili/y se"),
    VerbConjData("ptát se",
        "ptám se", "ptáš se", "ptá se", "ptáme se", "ptáte se", "ptají se",
        "ptal/a jsem se", "ptal/a jsi se", "ptal/ptala se", "ptali/y jsme se", "ptali/y jste se", "ptali/y se"),
    VerbConjData("bát se",
        "bojím se", "bojíš se", "bojí se", "bojíme se", "bojíte se", "bojí se",
        "bál/a jsem se", "bál/a jsi se", "bál/bála se", "báli/y jsme se", "báli/y jste se", "báli/y se"),
    VerbConjData("mýt se",
        "myji/myju se", "myješ se", "myje se", "myjeme se", "myjete se", "myjí/myjou se",
        "myl/a jsem se", "myl/a jsi se", "myl/myla se", "myli/y jsme se", "myli/y jste se", "myli/y se"),
    // Reflexive — si
    VerbConjData("pamatovat si",
        "pamatuji/pamatuju si", "pamatuješ si", "pamatuje si", "pamatujeme si", "pamatujete si", "pamatují/pamatujou si",
        "pamatoval/a jsem si", "pamatoval/a jsi si", "pamatoval/pamatovala si", "pamatovali/y jsme si", "pamatovali/y jste si", "pamatovali/y si")
)

private val vcqEntries: List<VCQEntry> by lazy {
    vcqBank.flatMap { v ->
        listOf(
            VCQEntry(v.infinitive, "já", "present", v.presJa),
            VCQEntry(v.infinitive, "ty", "present", v.presTy),
            VCQEntry(v.infinitive, "on/ona", "present", v.presOnOna),
            VCQEntry(v.infinitive, "my", "present", v.presMy),
            VCQEntry(v.infinitive, "vy", "present", v.presVy),
            VCQEntry(v.infinitive, "oni/ony", "present", v.presOniOny),
            VCQEntry(v.infinitive, "já", "past", v.pastJa),
            VCQEntry(v.infinitive, "ty", "past", v.pastTy),
            VCQEntry(v.infinitive, "on/ona", "past", v.pastOnOna),
            VCQEntry(v.infinitive, "my", "past", v.pastMy),
            VCQEntry(v.infinitive, "vy", "past", v.pastVy),
            VCQEntry(v.infinitive, "oni/ony", "past", v.pastOniOny)
        )
    }
}

private fun generateVerbConjQuestions(): List<QuizQuestion> {
    val selected = vcqEntries.shuffled().take(10)
    return selected.map { entry ->
        val prompt = "${entry.infinitive}  —  ${entry.pronoun}  —  ${entry.tense}"
        val correct = entry.form
        val sameVerbSameTense = vcqEntries.filter {
            it.infinitive == entry.infinitive && it.tense == entry.tense && it.form != correct
        }.shuffled()
        val wrongPool = (sameVerbSameTense +
            vcqEntries.filter { it.tense == entry.tense && it.form != correct })
            .distinctBy { it.form }
        val wrongs = wrongPool.take(3).map { it.form }
        QuizQuestion(prompt, correct, (wrongs + correct).shuffled())
    }
}

// ── Screen ────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerbConjugationQuizScreen(navController: NavController) {
    var quizKey by remember { mutableStateOf(0) }
    key(quizKey) {
        VerbConjContent(navController = navController, onPlayAgain = { quizKey++ })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun VerbConjContent(navController: NavController, onPlayAgain: () -> Unit) {
    val questions = remember { generateVerbConjQuestions() }
    var currentIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var score by remember { mutableStateOf(0) }
    var quizFinished by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Verb Conjugation Quiz", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
            VCQScoreScreen(
                score = score,
                total = questions.size,
                onPlayAgain = onPlayAgain,
                onHome = {
                    navController.navigate("home") { popUpTo("home") { inclusive = false } }
                },
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            VCQQuestionCard(
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
private fun VCQQuestionCard(
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

    // Split prompt into parts for styled display
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
        // Infinitive
        if (parts.isNotEmpty()) {
            Text(
                text = parts[0],
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
        // Pronoun + tense on one line
        if (parts.size >= 3) {
            Text(
                text = "${parts[1]}  ·  ${parts[2]}",
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
private fun VCQScoreScreen(
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
