package com.example.frenchproject.screens.dictionary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

private data class WordEntry(val french: String, val english: String)
private data class WordSection(val title: String, val entries: List<WordEntry>)

private val sections = listOf(
    WordSection("Types of School", listOf(
        WordEntry("l'école (f)", "school (primary)"),
        WordEntry("la maternelle", "kindergarten / nursery school"),
        WordEntry("l'école primaire (f)", "primary / elementary school"),
        WordEntry("le collège", "middle school (grades 6–9)"),
        WordEntry("le lycée", "high school (grades 10–12)"),
        WordEntry("l'université (f)", "university"),
        WordEntry("la grande école", "elite higher education institution"),
        WordEntry("la salle de classe", "classroom"),
        WordEntry("la cour de récréation", "playground / schoolyard")
    )),
    WordSection("People at School", listOf(
        WordEntry("le directeur / la directrice", "principal / headteacher"),
        WordEntry("le professeur / la professeure", "teacher / professor"),
        WordEntry("l'enseignant / l'enseignante", "teacher / educator"),
        WordEntry("l'élève (m/f)", "student (primary / secondary)"),
        WordEntry("l'étudiant / l'étudiante", "student (university)"),
        WordEntry("le camarade de classe", "classmate"),
        WordEntry("le conseiller / la conseillère", "school counselor"),
        WordEntry("le proviseur", "principal (lycée)")
    )),
    WordSection("Classroom Objects", listOf(
        WordEntry("le stylo", "pen"),
        WordEntry("le crayon", "pencil"),
        WordEntry("la gomme", "eraser"),
        WordEntry("le papier", "paper"),
        WordEntry("le livre", "book"),
        WordEntry("le manuel", "textbook"),
        WordEntry("le cahier", "notebook / exercise book"),
        WordEntry("le dictionnaire", "dictionary"),
        WordEntry("la règle", "ruler"),
        WordEntry("les ciseaux (m pl)", "scissors"),
        WordEntry("le cartable", "school bag / backpack"),
        WordEntry("le tableau (noir)", "blackboard / (smart) board"),
        WordEntry("la craie", "chalk"),
        WordEntry("le marqueur", "marker"),
        WordEntry("la trousse", "pencil case"),
        WordEntry("la colle", "glue"),
        WordEntry("le taille-crayon", "pencil sharpener")
    )),
    WordSection("Places & Facilities", listOf(
        WordEntry("la bibliothèque", "library"),
        WordEntry("le bureau", "office / desk"),
        WordEntry("la cantine", "school cafeteria"),
        WordEntry("le laboratoire", "laboratory"),
        WordEntry("le gymnase", "gymnasium"),
        WordEntry("le couloir", "corridor / hallway")
    )),
    WordSection("School Life", listOf(
        WordEntry("l'examen (m)", "exam / test"),
        WordEntry("les devoirs (m pl)", "homework"),
        WordEntry("la récréation / la récré", "break / recess"),
        WordEntry("la note", "grade / mark"),
        WordEntry("le bulletin scolaire", "school report card"),
        WordEntry("le cours", "class / lesson"),
        WordEntry("l'emploi du temps (m)", "timetable / schedule"),
        WordEntry("la matière", "school subject"),
        WordEntry("le trimestre", "term / quarter")
    )),
    WordSection("Verbs — Learning & Working", listOf(
        WordEntry("apprendre", "to learn"),
        WordEntry("enseigner", "to teach"),
        WordEntry("écrire", "to write"),
        WordEntry("lire", "to read"),
        WordEntry("dessiner", "to draw"),
        WordEntry("préparer", "to prepare"),
        WordEntry("faire", "to do / to make"),
        WordEntry("pratiquer", "to practise"),
        WordEntry("étudier", "to study"),
        WordEntry("comprendre", "to understand"),
        WordEntry("répéter", "to repeat / to review"),
        WordEntry("calculer", "to calculate"),
        WordEntry("répondre", "to answer"),
        WordEntry("poser une question", "to ask a question"),
        WordEntry("corriger", "to correct"),
        WordEntry("réussir (un examen)", "to pass (an exam)"),
        WordEntry("échouer (un examen)", "to fail (an exam)")
    ))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolWorkScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("School & Work", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FrenchBlue)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                Text(
                    text = "Vocabulary related to school, education, and learning. Articles are shown with each noun to indicate gender: le / la / l' (singular), les (plural).",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            sections.forEach { section ->
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = section.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = FrenchBlue,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.35f))
                    Spacer(modifier = Modifier.height(4.dp))
                }
                items(section.entries.size) { i -> WordCard(section.entries[i]) }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun WordCard(entry: WordEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 9.dp)
        ) {
            Text(
                text = entry.french,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 14.sp,
                modifier = Modifier.width(210.dp)
            )
            Text(
                text = entry.english,
                style = MaterialTheme.typography.bodyMedium,
                color = FrenchNavy
            )
        }
    }
}
