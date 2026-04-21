package com.example.czechapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private val officeSchool = listOf(
    "school" to "škola", "university" to "univerzita",
    "classroom" to "třída / učebna", "teacher / professor" to "učitel / profesor",
    "student" to "student / žák",
    "lesson / class" to "hodina / lekce", "homework" to "domácí úkol",
    "exam / test" to "zkouška / test",
    "grade / mark" to "známka", "report card" to "vysvědčení",
    "book" to "kniha", "textbook" to "učebnice",
    "notebook" to "sešit", "pen" to "pero",
    "pencil" to "tužka", "ruler" to "pravítko",
    "eraser / rubber" to "guma", "calculator" to "kalkulačka",
    "blackboard" to "tabule", "chalk" to "křída",
    "library" to "knihovna", "subject" to "předmět",
    "mathematics" to "matematika", "Czech language" to "čeština",
    "history" to "dějepis", "geography" to "zeměpis",
    "science" to "přírodovědné předměty",
    "physical education" to "tělesná výchova",
    "music" to "hudba", "art" to "výtvarná výchova",
    "office" to "kancelář", "desk" to "pracovní stůl",
    "computer" to "počítač", "printer" to "tiskárna",
    "paper" to "papír", "folder" to "složka / šanon",
    "envelope" to "obálka", "stamp" to "poštovní známka",
    "meeting" to "schůze / schůzka",
    "presentation" to "prezentace",
    "email" to "e-mail", "phone call" to "telefonní hovor"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfficeSchoolScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Office & School") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                            Text("English", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                            Text("Czech", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                        }
                        HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                        officeSchool.forEachIndexed { i, (en, cz) ->
                            val bg = if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(0.4f) else MaterialTheme.colorScheme.surface
                            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp), colors = CardDefaults.cardColors(bg), elevation = CardDefaults.cardElevation(0.dp)) {
                                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp)) {
                                    Text(en, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.5f))
                                    Text(cz, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
