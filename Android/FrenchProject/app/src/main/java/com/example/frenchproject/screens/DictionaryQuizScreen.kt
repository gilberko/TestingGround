package com.example.frenchproject.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchLight

// ── Data ──────────────────────────────────────────────────────────────────────

private data class FrQuizWord(val english: String, val french: String)
private data class FrQuizCategory(val title: String, val words: List<FrQuizWord>)
private data class FrQuizQuestion(val prompt: String, val correctAnswer: String, val options: List<String>)

// French has only two grammatical genders — masculine and feminine (no neuter).
private fun m(fr: String) = "$fr (m.)"
private fun f(fr: String) = "$fr (f.)"
private fun mf(fr: String) = "$fr (m./f.)"
private fun fpl(fr: String) = "$fr (f. pl.)"
private fun both(masc: String, fem: String) = "$masc (m.) / $fem (f.)"

private val frQuizCategories: List<FrQuizCategory> = listOf(
    FrQuizCategory("People and Family", listOf(
        FrQuizWord("man", m("l'homme")),
        FrQuizWord("woman", f("la femme")),
        FrQuizWord("boy", m("le garçon")),
        FrQuizWord("girl", f("la fille")),
        FrQuizWord("guy", m("le mec / le type")),
        FrQuizWord("daughter", f("la fille")),
        FrQuizWord("son", m("le fils")),
        FrQuizWord("son-in-law", m("le gendre")),
        FrQuizWord("daughter-in-law", f("la belle-fille")),
        FrQuizWord("uncle", m("l'oncle")),
        FrQuizWord("aunt", f("la tante")),
        FrQuizWord("grandfather", m("le grand-père")),
        FrQuizWord("grandmother", f("la grand-mère")),
        FrQuizWord("nephew", m("le neveu")),
        FrQuizWord("niece", f("la nièce")),
        FrQuizWord("grandson", m("le petit-fils")),
        FrQuizWord("granddaughter", f("la petite-fille")),
        FrQuizWord("baby", m("le bébé")),
        FrQuizWord("toddler", m("le tout-petit"))
    )),
    FrQuizCategory("Food and Drinks", listOf(
        FrQuizWord("plate", f("l'assiette")),
        FrQuizWord("knife", m("le couteau")),
        FrQuizWord("fork", f("la fourchette")),
        FrQuizWord("spoon", f("la cuillère")),
        FrQuizWord("teaspoon", f("la petite cuillère")),
        FrQuizWord("fish", m("le poisson")),
        FrQuizWord("chicken", m("le poulet")),
        FrQuizWord("meat", f("la viande")),
        FrQuizWord("beef", m("le bœuf")),
        FrQuizWord("cheese", m("le fromage")),
        FrQuizWord("bread", m("le pain")),
        FrQuizWord("milk", m("le lait")),
        FrQuizWord("wine", m("le vin")),
        FrQuizWord("beer", f("la bière")),
        FrQuizWord("coffee", m("le café")),
        FrQuizWord("tea", m("le thé")),
        FrQuizWord("water", f("l'eau")),
        FrQuizWord("soda", m("le soda")),
        FrQuizWord("cucumber", m("le concombre")),
        FrQuizWord("eggplant", f("l'aubergine")),
        FrQuizWord("tomato", f("la tomate")),
        FrQuizWord("pepper (spice)", m("le poivre")),
        FrQuizWord("salt", m("le sel")),
        FrQuizWord("parsley", m("le persil")),
        FrQuizWord("dill", m("l'aneth")),
        FrQuizWord("cumin", m("le cumin")),
        FrQuizWord("paprika", m("le paprika")),
        FrQuizWord("egg", m("l'œuf")),
        FrQuizWord("steak", m("le steak")),
        FrQuizWord("onion", m("l'oignon")),
        FrQuizWord("garlic", m("l'ail")),
        FrQuizWord("olive", f("l'olive")),
        FrQuizWord("oil", f("l'huile")),
        FrQuizWord("vinegar", m("le vinaigre")),
        FrQuizWord("potato", f("la pomme de terre")),
        FrQuizWord("sweet potato", f("la patate douce")),
        FrQuizWord("zucchini", f("la courgette")),
        FrQuizWord("cake", m("le gâteau")),
        FrQuizWord("ice", f("la glace")),
        FrQuizWord("orange", f("l'orange")),
        FrQuizWord("apple", f("la pomme")),
        FrQuizWord("pear", f("la poire")),
        FrQuizWord("peach", f("la pêche")),
        FrQuizWord("grapes", m("le raisin")),
        FrQuizWord("melon", m("le melon")),
        FrQuizWord("watermelon", f("la pastèque")),
        FrQuizWord("avocado", m("l'avocat")),
        FrQuizWord("celery", m("le céleri")),
        FrQuizWord("sauce", f("la sauce")),
        FrQuizWord("soup", f("la soupe")),
        FrQuizWord("salad", f("la salade")),
        FrQuizWord("breakfast", m("le petit-déjeuner")),
        FrQuizWord("lunch", m("le déjeuner")),
        FrQuizWord("dinner", m("le dîner")),
        FrQuizWord("snack", m("l'en-cas")),
        FrQuizWord("dessert", m("le dessert")),
        FrQuizWord("ice cream", f("la glace")),
        FrQuizWord("milkshake", m("le milk-shake")),
        FrQuizWord("cookie", m("le biscuit")),
        FrQuizWord("popsicle", f("la sucette glacée")),
        FrQuizWord("hamburger", m("le hamburger")),
        FrQuizWord("pizza", f("la pizza")),
        FrQuizWord("pasta", fpl("les pâtes")),
        FrQuizWord("lasagna", fpl("les lasagnes")),
        FrQuizWord("meatballs", fpl("les boulettes de viande")),
        FrQuizWord("napkin", f("la serviette")),
        FrQuizWord("menu", f("la carte")),
        FrQuizWord("restaurant", m("le restaurant"))
    )),
    FrQuizCategory("Time Expressions", listOf(
        FrQuizWord("a minute", f("une minute")),
        FrQuizWord("a moment", m("un moment")),
        FrQuizWord("a second", f("une seconde")),
        FrQuizWord("an hour", f("une heure")),
        FrQuizWord("a day", m("un jour")),
        FrQuizWord("a week", f("une semaine")),
        FrQuizWord("a month", m("un mois")),
        FrQuizWord("a year", m("un an")),
        FrQuizWord("a decade", f("une décennie")),
        FrQuizWord("weekend", m("le week-end")),
        // Adverbs — no gender
        FrQuizWord("yesterday", "hier"),
        FrQuizWord("tomorrow", "demain"),
        FrQuizWord("now", "maintenant"),
        FrQuizWord("morning", m("le matin")),
        FrQuizWord("noon", m("midi")),
        FrQuizWord("afternoon", m("l'après-midi")),
        FrQuizWord("evening", m("le soir")),
        FrQuizWord("night", f("la nuit")),
        FrQuizWord("midnight", m("minuit")),
        FrQuizWord("midday", m("midi"))
    )),
    FrQuizCategory("Professions", listOf(
        FrQuizWord("judge", both("le juge", "la juge")),
        FrQuizWord("police officer", both("le policier", "la policière")),
        FrQuizWord("soldier", both("le soldat", "la soldate")),
        FrQuizWord("engineer", both("l'ingénieur", "l'ingénieure")),
        FrQuizWord("actor / actress", both("l'acteur", "l'actrice")),
        FrQuizWord("accountant", both("le comptable", "la comptable")),
        FrQuizWord("lawyer", both("l'avocat", "l'avocate")),
        FrQuizWord("politician", both("l'homme politique", "la femme politique")),
        FrQuizWord("student", both("l'étudiant", "l'étudiante")),
        FrQuizWord("teacher", both("l'enseignant", "l'enseignante")),
        FrQuizWord("professor", both("le professeur", "la professeure")),
        FrQuizWord("painter", both("le peintre", "la peintre")),
        FrQuizWord("cobbler", both("le cordonnier", "la cordonnière")),
        FrQuizWord("writer", both("l'écrivain", "l'écrivaine")),
        FrQuizWord("poet", both("le poète", "la poétesse")),
        FrQuizWord("salesperson", both("le vendeur", "la vendeuse")),
        FrQuizWord("cashier", both("le caissier", "la caissière"))
    )),
    FrQuizCategory("Sport", listOf(
        FrQuizWord("soccer", m("le football")),
        FrQuizWord("baseball", m("le baseball")),
        FrQuizWord("basketball", m("le basket-ball")),
        FrQuizWord("wrestling", f("la lutte")),
        FrQuizWord("boxing", f("la boxe")),
        FrQuizWord("golf", m("le golf")),
        FrQuizWord("ball", m("le ballon")),
        FrQuizWord("coach", m("l'entraîneur")),
        FrQuizWord("referee", mf("l'arbitre")),
        FrQuizWord("athlete", mf("l'athlète")),
        FrQuizWord("swimming", f("la natation")),
        FrQuizWord("tournament", m("le tournoi")),
        FrQuizWord("stadium", m("le stade")),
        FrQuizWord("goalkeeper", m("le gardien de but")),
        FrQuizWord("offside", m("le hors-jeu")),
        FrQuizWord("substitution", m("le remplacement")),
        FrQuizWord("timeout", m("le temps mort")),
        FrQuizWord("foul", f("la faute")),
        FrQuizWord("penalty kick", m("le penalty")),
        FrQuizWord("kick", m("le coup de pied")),
        FrQuizWord("horseback riding", f("l'équitation")),
        FrQuizWord("weight lifting", f("l'haltérophilie")),
        FrQuizWord("team", f("l'équipe")),
        FrQuizWord("championship", m("le championnat")),
        FrQuizWord("champion", both("le champion", "la championne")),
        FrQuizWord("world cup", f("la Coupe du monde")),
        FrQuizWord("opponent", mf("l'adversaire")),
        FrQuizWord("medal", f("la médaille")),
        FrQuizWord("gold", m("l'or")),
        FrQuizWord("silver", m("l'argent")),
        FrQuizWord("bronze", m("le bronze"))
    )),
    FrQuizCategory("Work", listOf(
        FrQuizWord("work", m("le travail")),
        FrQuizWord("manager", both("le manager", "la manager")),
        FrQuizWord("boss", both("le patron", "la patronne")),
        FrQuizWord("employee", both("l'employé", "l'employée")),
        FrQuizWord("salary", m("le salaire")),
        FrQuizWord("desk", m("le bureau")),
        FrQuizWord("computer", m("l'ordinateur")),
        FrQuizWord("laptop", m("l'ordinateur portable")),
        FrQuizWord("screen", m("l'écran")),
        FrQuizWord("printer", f("l'imprimante")),
        FrQuizWord("chair", f("la chaise")),
        FrQuizWord("colleague", both("le collègue", "la collègue")),
        FrQuizWord("pen", m("le stylo")),
        FrQuizWord("pencil", m("le crayon")),
        FrQuizWord("paper", m("le papier")),
        FrQuizWord("envelope", f("l'enveloppe")),
        FrQuizWord("project", m("le projet")),
        FrQuizWord("meeting", f("la réunion")),
        FrQuizWord("office", m("le bureau"))
    ))
)

private fun generateFrQuizQuestions(category: FrQuizCategory, engToFr: Boolean): List<FrQuizQuestion> {
    fun promptOf(w: FrQuizWord) = if (engToFr) w.english else w.french
    fun answerOf(w: FrQuizWord) = if (engToFr) w.french else w.english
    return category.words.shuffled().take(10).map { word ->
        val prompt = promptOf(word)
        val correctAnswer = answerOf(word)
        // Skip words sharing the prompt (e.g. "la fille" = girl/daughter) so no
        // other valid answer is offered as a wrong option.
        val wrongAnswers = category.words
            .filter { promptOf(it) != prompt }
            .map { answerOf(it) }
            .filter { it != correctAnswer }
            .distinct()
            .shuffled()
            .take(3)
        FrQuizQuestion(prompt, correctAnswer, (wrongAnswers + correctAnswer).shuffled())
    }
}

private val QuizGreen = Color(0xFF2E7D32)
private val QuizRed = Color(0xFFC62828)
private val QuizGray = Color(0xFF9E9E9E)

// ── Screen ────────────────────────────────────────────────────────────────────

@Composable
fun DictionaryQuizScreen(onBack: () -> Unit, onHome: () -> Unit) {
    var showSettings by remember { mutableStateOf(true) }
    var engToFr by remember { mutableStateOf(true) }
    var categoryIndex by remember { mutableIntStateOf(0) }
    var quizKey by remember { mutableIntStateOf(0) }

    if (showSettings) {
        FrQuizSettings(
            onBack = onBack,
            engToFr = engToFr,
            onDirectionChanged = { engToFr = it },
            categoryIndex = categoryIndex,
            onCategoryChanged = { categoryIndex = it },
            onStart = { showSettings = false; quizKey++ }
        )
    } else {
        key(quizKey) {
            FrQuizContent(
                category = frQuizCategories[categoryIndex],
                engToFr = engToFr,
                onBack = { showSettings = true },
                onPlayAgain = { showSettings = true },
                onHome = onHome
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FrQuizScaffold(title: String, onBack: () -> Unit, content: @Composable (Modifier) -> Unit) {
    Scaffold(
        containerColor = FrenchLight,
        topBar = {
            TopAppBar(
                title = { Text(title, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FrenchBlue)
            )
        }
    ) { innerPadding ->
        content(Modifier.padding(innerPadding))
    }
}

@Composable
private fun FrQuizSettings(
    onBack: () -> Unit,
    engToFr: Boolean,
    onDirectionChanged: (Boolean) -> Unit,
    categoryIndex: Int,
    onCategoryChanged: (Int) -> Unit,
    onStart: () -> Unit
) {
    FrQuizScaffold(title = "Dictionary Quiz", onBack = onBack) { modifier ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text("Direction", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = FrenchBlue)
            Spacer(Modifier.height(4.dp))
            listOf(
                true to "English to French",
                false to "French to English"
            ).forEach { (value, label) ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    RadioButton(
                        selected = engToFr == value,
                        onClick = { onDirectionChanged(value) },
                        colors = RadioButtonDefaults.colors(selectedColor = FrenchBlue)
                    )
                    Text(label, fontSize = 15.sp)
                }
            }

            Spacer(Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(Modifier.height(8.dp))

            Text("Category", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = FrenchBlue)
            Spacer(Modifier.height(4.dp))
            frQuizCategories.forEachIndexed { index, category ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    RadioButton(
                        selected = categoryIndex == index,
                        onClick = { onCategoryChanged(index) },
                        colors = RadioButtonDefaults.colors(selectedColor = FrenchBlue)
                    )
                    Text("${category.title} (${category.words.size} words)", fontSize = 15.sp)
                }
            }

            Spacer(Modifier.height(12.dp))
            Text(
                "French has only two genders: masculine (m.) and feminine (f.) — there is no neuter. " +
                    "The gender is shown next to every French word, which matters most after l', where the article hides it.",
                fontSize = 13.sp,
                color = Color.DarkGray
            )

            Spacer(Modifier.height(24.dp))
            Button(
                onClick = onStart,
                modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = FrenchBlue)
            ) {
                Text("Start Quiz", fontSize = 18.sp, color = Color.White)
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun FrQuizContent(
    category: FrQuizCategory,
    engToFr: Boolean,
    onBack: () -> Unit,
    onPlayAgain: () -> Unit,
    onHome: () -> Unit
) {
    val questions = remember { generateFrQuizQuestions(category, engToFr) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    val picks = remember { mutableStateListOf<String>() }
    var quizFinished by remember { mutableStateOf(false) }
    val direction = if (engToFr) "En → Fr" else "Fr → En"

    FrQuizScaffold(title = "${category.title} ($direction)", onBack = onBack) { modifier ->
        if (quizFinished) {
            FrQuizResults(
                questions = questions,
                picks = picks,
                onPlayAgain = onPlayAgain,
                onHome = onHome,
                modifier = modifier
            )
        } else {
            FrQuizQuestionCard(
                question = questions[currentIndex],
                questionNumber = currentIndex + 1,
                totalQuestions = questions.size,
                selectedAnswer = selectedAnswer,
                onAnswerSelected = { answer ->
                    if (selectedAnswer == null) {
                        selectedAnswer = answer
                        picks.add(answer)
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
                modifier = modifier
            )
        }
    }
}

@Composable
private fun FrQuizQuestionCard(
    question: FrQuizQuestion,
    questionNumber: Int,
    totalQuestions: Int,
    selectedAnswer: String?,
    onAnswerSelected: (String) -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Question $questionNumber / $totalQuestions", fontSize = 14.sp, color = Color.Gray)
        Spacer(Modifier.height(16.dp))
        Text(
            text = question.prompt,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(28.dp))
        question.options.forEach { option ->
            val bgColor = when {
                selectedAnswer == null -> FrenchBlue
                option == question.correctAnswer -> QuizGreen
                option == selectedAnswer -> QuizRed
                else -> QuizGray
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
                Text(option, fontSize = 16.sp, color = Color.White, textAlign = TextAlign.Center)
            }
        }
        if (selectedAnswer != null) {
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = onNext,
                modifier = Modifier.width(200.dp).heightIn(min = 52.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = FrenchBlue)
            ) {
                Text(
                    if (questionNumber < totalQuestions) "Next" else "See Results",
                    fontSize = 17.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun FrQuizResults(
    questions: List<FrQuizQuestion>,
    picks: List<String>,
    onPlayAgain: () -> Unit,
    onHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    val score = questions.indices.count { picks.getOrNull(it) == questions[it].correctAnswer }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Quiz Complete!", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(Modifier.height(8.dp))
        Text("You got $score / ${questions.size} correct!", fontSize = 20.sp, color = Color.DarkGray)
        Spacer(Modifier.height(20.dp))

        questions.forEachIndexed { index, q ->
            val pick = picks.getOrNull(index)
            val correct = pick == q.correctAnswer
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (correct) "✓" else "✗",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(32.dp)
                            .background(if (correct) QuizGreen else QuizRed, RoundedCornerShape(6.dp))
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text("${index + 1}. ${q.prompt}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text(
                            "Your answer: ${pick ?: "—"}",
                            fontSize = 14.sp,
                            color = if (correct) QuizGreen else QuizRed
                        )
                        if (!correct) {
                            Text("Correct: ${q.correctAnswer}", fontSize = 14.sp, color = QuizGreen)
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        Button(
            onClick = onPlayAgain,
            modifier = Modifier.width(220.dp).heightIn(min = 56.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = FrenchBlue)
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
        Spacer(Modifier.height(24.dp))
    }
}
