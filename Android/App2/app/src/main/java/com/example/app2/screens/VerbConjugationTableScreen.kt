package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app2.data.model.ImperativoConjugation
import com.example.app2.data.model.ImperativoNegativoConjugation
import com.example.app2.data.model.Subject
import com.example.app2.data.model.Tense
import com.example.app2.data.model.TenseConjugation
import com.example.app2.data.model.Verb
import com.example.app2.data.model.VerbConjugations
import com.example.app2.data.repository.VerbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

val RegularSampleVerbs = listOf("falar", "comer", "partir")
val IrregularSampleVerbs = listOf(
    "ter", "dar", "pôr", "ser", "estar", "fazer", "trazer", "ver", "vir", "ir", "sair"
)

private const val MISSING_FORM = "—"

private fun TenseConjugation.toSubjectMap(): Map<Subject, String> = mapOf(
    Subject.EU to eu, Subject.TU to tu, Subject.ELE to ele,
    Subject.NOS to nos, Subject.VOS to vos, Subject.ELES to eles
)

private fun ImperativoConjugation.toSubjectMap(): Map<Subject, String> = mapOf(
    Subject.TU to tu, Subject.ELE to ele, Subject.NOS to nos, Subject.VOS to vos, Subject.ELES to eles
)

private fun ImperativoNegativoConjugation.toSubjectMap(): Map<Subject, String> = mapOf(
    Subject.TU to tu, Subject.ELE to ele, Subject.NOS to nos, Subject.VOS to vos, Subject.ELES to eles
)

private fun formsForTense(c: VerbConjugations, tense: Tense): Map<Subject, String>? = when (tense) {
    Tense.INDICATIVO_PRESENTE -> c.indicativoPresente.toSubjectMap()
    Tense.INDICATIVO_PRETERITO_PERFEITO -> c.indicativoPreteritoPerfeito.toSubjectMap()
    Tense.INDICATIVO_PRETERITO_IMPERFEITO -> c.indicativoPreteritoImperfeito.toSubjectMap()
    Tense.INDICATIVO_PRETERITO_MAIS_QUE_PERFEITO -> c.indicativoPreteritoMaisQuePerfeito.toSubjectMap()
    Tense.INDICATIVO_FUTURO -> c.indicativoFuturo.toSubjectMap()
    Tense.INDICATIVO_CONDICIONAL -> c.indicativoCondicional.toSubjectMap()
    Tense.CONJUNTIVO_PRESENTE -> c.conjuntivoPresente.toSubjectMap()
    Tense.CONJUNTIVO_PRETERITO_IMPERFEITO -> c.conjuntivoPreteritoImperfeito.toSubjectMap()
    Tense.CONJUNTIVO_FUTURO -> c.conjuntivoFuturo.toSubjectMap()
    Tense.INFINITIVO_PESSOAL -> c.infinitivoPessoal?.toSubjectMap()
    Tense.IMPERATIVO_AFIRMATIVO -> c.imperativoAfirmativo.toSubjectMap()
    Tense.IMPERATIVO_NEGATIVO -> c.imperativoNegativo.toSubjectMap()
    else -> null
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerbConjugationTableScreen(
    title: String,
    verbInfinitives: List<String>,
    tenses: List<Tense>,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var verbs by remember { mutableStateOf<List<Verb>>(emptyList()) }

    LaunchedEffect(verbInfinitives) {
        withContext(Dispatchers.IO) {
            val allVerbs = VerbRepository(context).verbs
            verbs = verbInfinitives.mapNotNull { inf -> allVerbs.find { it.infinitive == inf } }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (verbs.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item { Spacer(modifier = Modifier.height(4.dp)) }
                verbs.forEach { verb ->
                    item { VerbConjugationCard(verb = verb, tenses = tenses) }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

@Composable
private fun VerbConjugationCard(verb: Verb, tenses: List<Tense>) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "${verb.infinitive} — ${verb.english}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1.4f)
                )
                listOf("eu", "tu", "ele/ela").forEach { subj ->
                    Text(
                        text = subj,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1.4f)
                )
                listOf("nós", "vós", "eles/elas").forEach { subj ->
                    Text(
                        text = subj,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            tenses.forEachIndexed { index, tense ->
                val forms = formsForTense(verb.conjugations, tense) ?: return@forEachIndexed
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 1.dp)
                ) {
                    Text(
                        text = tense.displayLabel,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1.4f)
                    )
                    listOf(Subject.EU, Subject.TU, Subject.ELE).forEach { subj ->
                        Text(
                            text = forms[subj] ?: MISSING_FORM,
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp)
                ) {
                    Text(
                        text = "",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1.4f)
                    )
                    listOf(Subject.NOS, Subject.VOS, Subject.ELES).forEach { subj ->
                        Text(
                            text = forms[subj] ?: MISSING_FORM,
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                if (index < tenses.lastIndex) {
                    HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
                }
            }
        }
    }
}
