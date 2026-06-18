package com.example.czechappredo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolAndUniversityScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("School and University", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {

            SUSection("People at School")
            SURow("profesor / profesorka", "professor (m./f.)", "university level; address: pane profesore / paní profesorko")
            SURow("učitel / učitelka", "teacher (m./f.)", "primary/secondary school; address: pane učiteli / paní učitelko")
            SURow("asistent / asistentka", "teaching assistant / assistant (m./f.)", "cvičící = lab/seminar instructor (university)")
            SURow("student / studentka", "student (m./f.)", "used for university students; at lower schools, use žák/žákyně")
            SURow("žák / žákyně", "pupil (m./f.)", "primary and secondary school student; cf. student = university level")
            SURow("ředitel / ředitelka", "principal / headteacher (m./f.)", "also: director of any institution; at school: ředitel školy")

            SUSection("School Levels")
            SURow("mateřská škola / školka", "kindergarten / preschool", "mateřská škola = formal; školka = everyday colloquial; ages 3–6")
            SURow("základní škola", "elementary / primary school", "f.; grades 1–9; první stupeň = grades 1–5, druhý stupeň = grades 6–9")
            SURow("první třída / první ročník", "first grade", "f./m.; třída = common; ročník = more academic/formal equivalent")
            SURow("druhá třída", "second grade")
            SURow("šestá třída", "sixth grade")
            SURow("střední škola", "high school / secondary school", "f.; covers gymnázium (academic) and vocational schools; ages ~15–19")
            SURow("gymnázium", "grammar school / academic high school", "n.; most academically focused secondary school; leads to maturita")
            SURow("maturita", "school-leaving exam", "f.; the final exam at the end of střední škola; full name: maturitní zkouška")
            SURow("vysoká škola / univerzita", "university / college", "f.; VŠ = any higher education; univerzita = specifically a university")

            SUSection("School Subjects")
            SURow("matematika", "mathematics / math", "f.; colloquial: matika; hodina matematiky = math class")
            SURow("přírodověda / přírodní vědy", "science / natural science", "přírodověda f. = elementary school science subject; přírodní vědy = natural sciences (general)")
            SURow("biologie", "biology", "f.")
            SURow("chemie", "chemistry", "f.")
            SURow("fyzika", "physics", "f.")
            SURow("ekonomie", "economics (academic discipline)", "f.; the academic study of economics; contrast: ekonomika = a country's economy / applied economics")
            SURow("ekonomika", "economy / applied economics", "f.; e.g. česká ekonomika = the Czech economy")
            SURow("softwarové inženýrství", "software engineering", "n.; softwarový inženýr m. = software engineer")
            SURow("inženýrství", "engineering", "n.; strojní inženýrství = mechanical engineering; stavební inženýrství = civil engineering")
            SURow("literatura", "literature", "f.")
            SURow("poezie", "poetry", "f.")
            SURow("jazyky / cizí jazyky", "languages / foreign languages", "m.pl.; hodina jazyků = language class; jazykový kurz = language course")

            SUSection("Assignments and Grades")
            SUNote("úkol vs. zadání: úkol = the task itself (what you must do/submit); zadání = the brief/specification (the written description of what to do). You receive a zadání and then complete the úkol.")
            SURow("úkol", "task / homework / assignment", "m.; domácí úkol = homework specifically (lit. 'home task'); úkoly = tasks (pl.)")
            SURow("zadání", "assignment brief / task specification", "n.; the written description of what to do; Dostali jsme zadání = We received the assignment brief")
            SURow("předmět", "subject / course", "m.; most common word for a school/university subject; Jaký předmět máš? = What subject do you have?")
            SURow("kurz", "course (a series of lessons)", "m.; kurz angličtiny = English course; cf. předmět = school subject")
            SURow("hodina", "lesson / class period", "f.; lit. 'hour'; hodina matematiky = math lesson; mám hodinu = I have a class")
            SURow("lekce", "lesson (in a book/course)", "f.; the chapter/unit in a textbook; e.g. lekce 3 = lesson 3")
            SURow("test / písemka", "test / written test", "test m. = formal; písemka f. = informal / colloquial for any written test or quiz")
            SURow("zkouška", "exam / examination", "f.; often a spoken or written final exam at university; složit zkoušku = to pass an exam")
            SURow("zkouškové (období)", "exam period", "n.; short for zkouškové období; the end-of-semester exam window")
            SURow("známka / hodnocení", "grade / mark", "známka f. = the grade/mark; hodnocení n. = assessment/evaluation")
            SUNote("Czech grading scale: 1 = výborně (excellent), 2 = chvalitebně (good), 3 = dobře (satisfactory), 4 = dostatečně (sufficient), 5 = nedostatečně (fail). Opposite of the US A–F scale — 1 is the best!")
            SURow("propadnout", "to fail (a grade/exam)", "perf.; propadl (m.) / propadla (f.); also: dostat pětku = to get a 5 (fail); neudělat zkoušku = to fail an exam")
            SURow("složit / udělat zkoušku", "to pass an exam", "složit = formal; udělat = colloquial; uspět = to succeed/pass")

            SUSection("Classroom Supplies")
            SURow("taška / batoh", "bag / backpack", "taška f. = bag (general); batoh m. = backpack; školní taška = school bag")
            SURow("sešit", "exercise book / notebook", "m.; the lined notebook used for writing in school")
            SURow("kniha", "book", "f.")
            SURow("učebnice", "textbook / study book", "f.; the book used in class for a given subject; cf. kniha = any book")
            SURow("tužka", "pencil", "f.; mechanická tužka = mechanical pencil")
            SURow("pero", "pen", "n.; also: propisovačka / kulička f. = ballpoint pen (colloquial)")
            SURow("guma", "eraser / rubber", "f.; also means 'rubber' (the material) — context makes it clear in a school setting")
            SURow("fixa / fix", "marker / felt-tip pen", "fixa f.; fixy pl. = markers; popisovač m. = whiteboard marker")
            SURow("ořezávátko", "pencil sharpener", "n.")
            SURow("papír", "paper", "m.; list papíru = a sheet of paper")
            SURow("lepidlo", "glue", "n.; lepidlová tyčinka = glue stick; lepicí páska = tape")
            SURow("nůžky", "scissors", "f.pl. — always plural in Czech (like English 'scissors'); say: jedny nůžky, not jedno nůžky")
            SURow("pravítko", "ruler", "n.; for measuring/drawing straight lines")

            SUSection("Classroom and School Spaces")
            SURow("třída / učebna", "classroom", "třída f. = the class group AND the room; učebna f. = specifically the room (lab, seminar room)")
            SURow("lavice", "school desk / bench", "f.; the classic 2-person school bench; stůl m. = a regular desk or table")
            SURow("tabule", "board (blackboard / whiteboard)", "f.; černá tabule = blackboard; bílá tabule = whiteboard; interaktivní tabule = smartboard")
            SURow("křída", "chalk", "f.; kreslit křídou = to write in chalk")
            SURow("kreslicí deska / rýsovací prkno", "drawing board / drafting board", "kreslicí deska f. = art drawing board; rýsovací prkno n. = technical drafting board")
            SURow("počítač", "computer", "m.")
            SURow("notebook / laptop", "laptop", "m.; both words used equally in Czech")
            SURow("kancelář", "office", "f.; ředitelna f. = the principal's office specifically")

            SUSection("Useful Phrases")
            SURow("výlet / školní výlet", "field trip / school trip", "výlet m. = any trip/excursion; školní výlet = school trip; exkurze f. = formal educational visit (factory, museum)")
            SURow("odevzdat úkol do pondělí", "to submit / hand in the assignment by Monday", "odevzdat = perf. (to hand in); do pondělí = by Monday (do + gen.; pondělí is indeclinable)")
            SURow("dodat úkol do pondělí", "to deliver the assignment by Monday", "dodat = perf. (to supply/deliver); more often used for project deliverables than school homework")
            SURow("mít hodinu", "to have a class / lesson", "Mám hodinu matematiky = I have a math class; Nemám hodinu = I don't have class")
            SURow("chodit do školy", "to go to school / attend school", "chodit = imperf.; Chodím na gymnázium = I go to a grammar school")
            SURow("dělat / psát test", "to take a test", "dělat test = informal; psát test = also common (lit. 'to write a test')")
            SURow("školní autobus", "school bus", "m.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SUSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun SURow(czech: String, english: String, note: String = "") {
    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)) {
                    append(czech)
                }
                withStyle(SpanStyle(fontSize = 16.sp, color = Color.DarkGray)) {
                    append("  —  $english")
                }
            }
        )
        if (note.isNotEmpty()) {
            Text(
                text = note,
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray,
                modifier = Modifier.padding(start = 4.dp, top = 1.dp)
            )
        }
    }
}

@Composable
private fun SUNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
