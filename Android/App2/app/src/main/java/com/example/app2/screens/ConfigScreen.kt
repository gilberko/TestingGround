package com.example.app2.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app2.data.model.RegularityFilter
import com.example.app2.data.model.Subject
import com.example.app2.data.model.Tense
import com.example.app2.quiz.ConfigViewModel
import com.example.app2.quiz.QuizMode

private val ACTIVE_TENSES = listOf(
    Tense.INDICATIVO_PRESENTE,
    Tense.INDICATIVO_PRETERITO_PERFEITO,
    Tense.INDICATIVO_PRETERITO_IMPERFEITO,
    Tense.INDICATIVO_PRETERITO_MAIS_QUE_PERFEITO,
    Tense.INDICATIVO_FUTURO,
    Tense.INDICATIVO_CONDICIONAL,
    Tense.CONJUNTIVO_PRESENTE,
    Tense.CONJUNTIVO_PRETERITO_IMPERFEITO,
    Tense.CONJUNTIVO_FUTURO,
    Tense.IMPERATIVO_AFIRMATIVO,
    Tense.IMPERATIVO_NEGATIVO
)

private val NON_FINITE_TENSES = listOf(Tense.GERUND)

private val PASSIVE_TENSES = listOf(
    Tense.PASSIVA_PRESENTE,
    Tense.PASSIVA_PRETERITO_PERFEITO,
    Tense.PASSIVA_PRETERITO_IMPERFEITO,
    Tense.PASSIVA_FUTURO,
    Tense.PASSIVA_CONDICIONAL
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigScreen(configViewModel: ConfigViewModel, onBack: () -> Unit) {
    val quizMode by configViewModel.quizMode.collectAsState()
    val selectedTenses by configViewModel.selectedTenses.collectAsState()
    val selectedSubjects by configViewModel.selectedSubjects.collectAsState()
    val regularityFilter by configViewModel.regularityFilter.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Quiz Settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            // Answer Mode section
            item {
                Text(
                    text = "Answer Mode",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
            }
            items(QuizMode.entries) { mode ->
                Row(
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = mode == quizMode,
                        onClick = { configViewModel.setQuizMode(mode) }
                    )
                    Text(
                        text = when (mode) {
                            QuizMode.MULTIPLE_CHOICE -> "Multiple Choice"
                            QuizMode.FREE_TEXT -> "Free Text"
                        },
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            // Active Tenses section
            item {
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                Text(
                    text = "Active Tenses",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
                Text(
                    text = "No selection = all tenses included",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            items(ACTIVE_TENSES) { tense ->
                Row(
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = tense in selectedTenses,
                        onCheckedChange = { configViewModel.toggleTense(tense) }
                    )
                    Text(text = tense.displayLabel, modifier = Modifier.padding(start = 8.dp))
                }
            }

            // Non-Finite Forms section
            item {
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                Text(
                    text = "Non-Finite Forms",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            items(NON_FINITE_TENSES) { tense ->
                Row(
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = tense in selectedTenses,
                        onCheckedChange = { configViewModel.toggleTense(tense) }
                    )
                    Text(text = tense.displayLabel, modifier = Modifier.padding(start = 8.dp))
                }
            }

            // Passive Voice section
            item {
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                Text(
                    text = "Passive Voice",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            items(PASSIVE_TENSES) { tense ->
                Row(
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = tense in selectedTenses,
                        onCheckedChange = { configViewModel.toggleTense(tense) }
                    )
                    Text(text = tense.displayLabel, modifier = Modifier.padding(start = 8.dp))
                }
            }

            // Subjects section
            item {
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                Text(
                    text = "Subjects",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "No selection = all subjects included",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            items(Subject.entries) { subject ->
                Row(
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = subject in selectedSubjects,
                        onCheckedChange = { configViewModel.toggleSubject(subject) }
                    )
                    Text(text = subject.displayLabel, modifier = Modifier.padding(start = 8.dp))
                }
            }

            // Verb Regularity section
            item {
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                Text(
                    text = "Verb Regularity",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            items(RegularityFilter.entries) { filter ->
                Row(
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = filter == regularityFilter,
                        onClick = { configViewModel.setRegularityFilter(filter) }
                    )
                    Text(text = filter.displayLabel, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}
