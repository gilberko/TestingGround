package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class TechEntry(val pt: String, val en: String, val note: String = "")

private val generalTechWords = listOf(
    TechEntry("o código", "code", "standard Portuguese word"),
    TechEntry("o software", "software", "English word used as-is"),
    TechEntry("o hardware", "hardware", "English word used as-is"),
    TechEntry("compilar", "to compile"),
    TechEntry("a compilação", "compilation"),
    TechEntry("programar", "to program / to code"),
    TechEntry("o/a programador/a", "programmer / developer"),
    TechEntry("depurar / fazer debug", "to debug", "\"fazer debug\" is more common"),
    TechEntry("corrigir / arranjar", "to fix", "\"arranjar\" more colloquial"),
    TechEntry("um erro", "an error"),
    TechEntry("um bug", "a bug", "English word used universally"),
    TechEntry("testar", "to test"),
    TechEntry("implementar", "to implement"),
    TechEntry("o servidor", "the server"),
    TechEntry("a base de dados", "the database"),
    TechEntry("a aplicação / a app", "the application / app", "\"app\" universally used"),
    TechEntry("o sistema", "the system"),
    TechEntry("a rede", "the network"),
    TechEntry("fazer deploy", "to deploy", "English term used as-is in tech"),
    TechEntry("a funcionalidade", "the feature"),
    TechEntry("o utilizador", "the user"),
    TechEntry("a interface", "the interface"),
    TechEntry("a internet", "the internet"),
    TechEntry("o cabo", "the cable"),
    TechEntry("o adaptador", "the adapter"),
    TechEntry("o cabo de alimentação", "the power cable", "also: cabo de corrente"),
    TechEntry("o adaptador USB", "the USB adapter"),
    TechEntry("a inteligência artificial (IA)", "artificial intelligence (AI)"),
    TechEntry("o algoritmo", "the algorithm"),
    TechEntry("a heurística", "heuristics"),
    TechEntry("a versão", "the version"),
    TechEntry("o sistema operativo", "the operating system", "European Portuguese"),
    TechEntry("a máquina virtual", "the virtual machine"),
    TechEntry("o malware", "malware", "used directly in PT"),
    TechEntry("a proteção", "protection"),
    TechEntry("a linha cronológica / a timeline", "the timeline", "both forms used")
)

private val workWords = listOf(
    TechEntry("a reunião", "the meeting"),
    TechEntry("a estimativa", "the estimate"),
    TechEntry("o prazo", "the deadline"),
    TechEntry("o atraso", "the delay"),
    TechEntry("o projeto", "the project"),
    TechEntry("a tarefa", "the task"),
    TechEntry("o requisito", "the requirement"),
    TechEntry("a sprint", "the sprint", "English word used in Agile"),
    TechEntry("o backlog", "the backlog", "English word used as-is"),
    TechEntry("o relatório", "the report"),
    TechEntry("a entrega", "the delivery / release"),
    TechEntry("a revisão", "the review / revision")
)

private val deviceWords = listOf(
    TechEntry("o telemóvel", "mobile phone", "European Portuguese"),
    TechEntry("o computador", "computer"),
    TechEntry("o portátil", "laptop", "European Portuguese"),
    TechEntry("o tablet", "tablet", "borrowed term"),
    TechEntry("o dispositivo", "device"),
    TechEntry("o dispositivo virtual", "virtual device"),
    TechEntry("o dispositivo físico", "physical device")
)

private val techVerbWords = listOf(
    TechEntry("ligar / conectar", "to connect", "ligar more common in EP"),
    TechEntry("desligar / desconectar", "to disconnect", "desligar more common in EP"),
    TechEntry("desenvolver", "to develop"),
    TechEntry("encontrar um bug", "to find a bug"),
    TechEntry("pesquisar / investigar", "to research"),
    TechEntry("instalar", "to install"),
    TechEntry("remover / desinstalar", "to remove / uninstall"),
    TechEntry("infecionar", "to infect", "EP spelling"),
    TechEntry("fechar o bug", "to close the bug"),
    TechEntry("lançar uma nova versão", "to release a new version")
)

private val techAdjectiveWords = listOf(
    TechEntry("simples", "simple", "invariable — same for m/f/pl"),
    TechEntry("complicado/a", "complicated"),
    TechEntry("difícil", "difficult"),
    TechEntry("fácil", "easy")
)

private val gitWords = listOf(
    TechEntry("fazer commit", "to commit", "informally also \"comitar\""),
    TechEntry("um commit", "a commit"),
    TechEntry("reverter", "to revert", "e.g. \"reverter um commit\""),
    TechEntry("fazer push", "to push"),
    TechEntry("um pull request (PR)", "a pull request", "GitHub term"),
    TechEntry("um merge request (MR)", "a merge request", "GitLab's equivalent of a pull request"),
    TechEntry("fazer rebase", "to rebase"),
    TechEntry("sincronizar / fazer sync", "to sync"),
    TechEntry("fazer squash", "to squash", "combine multiple commits into one")
)

private val rolesProcessWords = listOf(
    TechEntry("a revisão de código", "code review"),
    TechEntry("o/a investigador/a de segurança", "security researcher", "European Portuguese; Brazilian Portuguese says \"pesquisador\""),
    TechEntry("a engenharia inversa", "reverse engineering"),
    TechEntry("a causa raiz", "the root cause"),
    TechEntry("os testes", "testing"),
    TechEntry("o controlo de qualidade (QA)", "QA / quality assurance", "the abbreviation \"QA\" is also used directly"),
    TechEntry("o/a gestor/a de produto", "product manager", "European Portuguese; Brazilian Portuguese says \"gerente de produto\"")
)

private val bugsFixesWords = listOf(
    TechEntry("a depuração / o debug", "debugging", "noun form of \"depurar / fazer debug\""),
    TechEntry("uma correção / um fix", "a fix", "\"fix\" used informally as a noun"),
    TechEntry("um patch", "a patch", "English term used as-is"),
    TechEntry("partiu o código / estragou o código", "it broke the code", "colloquial dev speech"),
    TechEntry("não compila", "doesn't compile"),
    TechEntry("crasha / está a crashar", "it crashes", "\"crashar\" is a common borrowed slang verb"),
    TechEntry("a alocação de memória", "memory allocation"),
    TechEntry("a corrupção de memória", "memory corruption"),
    TechEntry("uma condição de corrida", "a race condition", "many developers also just say \"race condition\" in English"),
    TechEntry("um buffer overflow", "a buffer overflow", "English term used as-is"),
    TechEntry("um stack overflow", "a stack overflow", "English term used as-is; also the name of the well-known Q&A site")
)

private val englishBorrowings = listOf(
    "software", "hardware", "bug", "debug", "deploy", "sprint", "backlog",
    "commit", "pull request", "merge", "branch", "pipeline", "framework",
    "API", "cloud", "DevOps", "agile", "scrum", "release", "feature"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechWordsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tech Vocabulary") },
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
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { TechWordCard("General Tech Words", generalTechWords) }
            item { TechWordCard("Devices", deviceWords) }
            item { TechWordCard("Work & Meetings", workWords) }
            item { TechWordCard("Useful Verbs", techVerbWords) }
            item { TechWordCard("Useful Adjectives", techAdjectiveWords) }
            item { TechWordCard("Git & Version Control", gitWords) }
            item { TechWordCard("Roles & Process", rolesProcessWords) }
            item { TechWordCard("Bugs, Fixes & Crashes", bugsFixesWords) }
            item { EnglishBorrowingsCard() }
        }
    }
}

@Composable
private fun TechWordCard(title: String, entries: List<TechEntry>) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))

            // Header row
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Portuguese",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1.1f),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "English",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(0.9f),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            entries.forEach { entry ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1.1f)) {
                        Text(entry.pt, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                        if (entry.note.isNotEmpty()) {
                            Text(
                                entry.note,
                                style = MaterialTheme.typography.labelSmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                    Text(
                        entry.en,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(0.9f),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun EnglishBorrowingsCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                "When English is Used Directly",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                "In Portuguese tech environments, many English terms are used directly without translation — even in formal speech and writing. When in doubt, the English term is usually understood and accepted.",
                style = MaterialTheme.typography.bodySmall
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
            Text(
                englishBorrowings.joinToString(" \u2022 "),
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
