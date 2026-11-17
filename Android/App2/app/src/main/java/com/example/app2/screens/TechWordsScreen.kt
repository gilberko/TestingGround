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


private val generalTechWords = listOf(
    DictWord("o código", "code", "standard Portuguese word"),
    DictWord("o software", "software", "English word used as-is"),
    DictWord("o hardware", "hardware", "English word used as-is"),
    DictWord("compilar", "to compile"),
    DictWord("a compilação", "compilation"),
    DictWord("programar", "to program / to code"),
    DictWord("o/a programador/a", "programmer / developer"),
    DictWord("depurar / fazer debug", "to debug", "\"fazer debug\" is more common"),
    DictWord("corrigir / arranjar", "to fix", "\"arranjar\" more colloquial"),
    DictWord("um erro", "an error"),
    DictWord("um bug", "a bug", "English word used universally"),
    DictWord("testar", "to test"),
    DictWord("implementar", "to implement"),
    DictWord("o servidor", "the server"),
    DictWord("a base de dados", "the database"),
    DictWord("a aplicação / a app", "the application / app", "\"app\" universally used"),
    DictWord("o sistema", "the system"),
    DictWord("a rede", "the network"),
    DictWord("fazer deploy", "to deploy", "English term used as-is in tech"),
    DictWord("a funcionalidade", "the feature"),
    DictWord("o utilizador", "the user"),
    DictWord("a interface", "the interface"),
    DictWord("a internet", "the internet"),
    DictWord("o cabo", "the cable"),
    DictWord("o adaptador", "the adapter"),
    DictWord("o cabo de alimentação", "the power cable", "also: cabo de corrente"),
    DictWord("o adaptador USB", "the USB adapter"),
    DictWord("a inteligência artificial (IA)", "artificial intelligence (AI)"),
    DictWord("o algoritmo", "the algorithm"),
    DictWord("a heurística", "heuristics"),
    DictWord("a versão", "the version"),
    DictWord("o sistema operativo", "the operating system", "European Portuguese"),
    DictWord("a máquina virtual", "the virtual machine"),
    DictWord("o malware", "malware", "used directly in PT"),
    DictWord("a proteção", "protection"),
    DictWord("a linha cronológica / a timeline", "the timeline", "both forms used"),
    DictWord("verdadeiro", "true"),
    DictWord("falso", "false"),
    DictWord("o processo", "the process"),
    DictWord("a thread", "the thread", "formally \"fio de execução\", but developers say \"thread\" directly"),
    DictWord("o design", "the design", "used directly, e.g. \"design de interface\"")
)

private val workWords = listOf(
    DictWord("a reunião", "the meeting"),
    DictWord("a estimativa", "the estimate"),
    DictWord("o prazo", "the deadline"),
    DictWord("o atraso", "the delay"),
    DictWord("o projeto", "the project"),
    DictWord("a tarefa", "the task"),
    DictWord("o requisito", "the requirement"),
    DictWord("a sprint", "the sprint", "English word used in Agile"),
    DictWord("o backlog", "the backlog", "English word used as-is"),
    DictWord("o relatório", "the report"),
    DictWord("a entrega", "the delivery / release"),
    DictWord("a revisão", "the review / revision")
)

private val deviceWords = listOf(
    DictWord("o telemóvel", "mobile phone", "European Portuguese"),
    DictWord("o computador", "computer"),
    DictWord("o portátil", "laptop", "European Portuguese"),
    DictWord("o tablet", "tablet", "borrowed term"),
    DictWord("o dispositivo", "device"),
    DictWord("o dispositivo virtual", "virtual device"),
    DictWord("o dispositivo físico", "physical device")
)

private val techVerbWords = listOf(
    DictWord("ligar / conectar", "to connect", "ligar more common in EP"),
    DictWord("desligar / desconectar", "to disconnect", "desligar more common in EP"),
    DictWord("desenvolver", "to develop"),
    DictWord("encontrar um bug", "to find a bug"),
    DictWord("pesquisar / investigar", "to research"),
    DictWord("instalar", "to install"),
    DictWord("remover / desinstalar", "to remove / uninstall"),
    DictWord("infecionar", "to infect", "EP spelling"),
    DictWord("fechar o bug", "to close the bug"),
    DictWord("lançar uma nova versão", "to release a new version")
)

private val techAdjectiveWords = listOf(
    DictWord("simples", "simple", "invariable — same for m/f/pl"),
    DictWord("complicado/a", "complicated"),
    DictWord("difícil", "difficult"),
    DictWord("fácil", "easy")
)

private val gitWords = listOf(
    DictWord("fazer commit", "to commit", "informally also \"comitar\""),
    DictWord("um commit", "a commit"),
    DictWord("reverter", "to revert", "e.g. \"reverter um commit\""),
    DictWord("fazer push", "to push"),
    DictWord("um pull request (PR)", "a pull request", "GitHub term"),
    DictWord("um merge request (MR)", "a merge request", "GitLab's equivalent of a pull request"),
    DictWord("fazer rebase", "to rebase"),
    DictWord("sincronizar / fazer sync", "to sync"),
    DictWord("fazer squash", "to squash", "combine multiple commits into one")
)

private val rolesProcessWords = listOf(
    DictWord("a revisão de código", "code review"),
    DictWord("o/a investigador/a de segurança", "security researcher", "European Portuguese; Brazilian Portuguese says \"pesquisador\""),
    DictWord("a engenharia inversa", "reverse engineering"),
    DictWord("a causa raiz", "the root cause"),
    DictWord("os testes", "testing"),
    DictWord("o controlo de qualidade (QA)", "QA / quality assurance", "the abbreviation \"QA\" is also used directly"),
    DictWord("o/a gestor/a de produto", "product manager", "European Portuguese; Brazilian Portuguese says \"gerente de produto\"")
)

private val bugsFixesWords = listOf(
    DictWord("a depuração / o debug", "debugging", "noun form of \"depurar / fazer debug\""),
    DictWord("uma correção / um fix", "a fix", "\"fix\" used informally as a noun"),
    DictWord("um patch", "a patch", "English term used as-is"),
    DictWord("partiu o código / estragou o código", "it broke the code", "colloquial dev speech"),
    DictWord("não compila", "doesn't compile"),
    DictWord("crasha / está a crashar", "it crashes", "\"crashar\" is a common borrowed slang verb"),
    DictWord("a alocação de memória", "memory allocation"),
    DictWord("a corrupção de memória", "memory corruption"),
    DictWord("uma condição de corrida", "a race condition", "many developers also just say \"race condition\" in English"),
    DictWord("um buffer overflow", "a buffer overflow", "English term used as-is"),
    DictWord("um stack overflow", "a stack overflow", "English term used as-is; also the name of the well-known Q&A site"),
    DictWord("memória livre", "free memory", "noun — available/unused memory"),
    DictWord("libertar memória", "to release / free memory", "verb — to deallocate"),
    DictWord("anexar ao processo", "to attach to the process", "debugger term"),
    DictWord("anexar à thread", "to attach to the thread"),
    DictWord("desanexar", "to detach")
)

private val englishBorrowings = listOf(
    "software", "hardware", "bug", "debug", "deploy", "sprint", "backlog",
    "commit", "pull request", "merge", "branch", "pipeline", "framework",
    "API", "cloud", "DevOps", "agile", "scrum", "release", "feature"
)

@Composable
fun TechWordsScreen(onBack: () -> Unit) {
    DictScreenScaffold(title = "Tech Vocabulary", onBack = onBack) {
        item { DictCategoryCard("General Tech Words", generalTechWords) }
        item { DictCategoryCard("Devices", deviceWords) }
        item { DictCategoryCard("Work & Meetings", workWords) }
        item { DictCategoryCard("Useful Verbs", techVerbWords) }
        item { DictCategoryCard("Useful Adjectives", techAdjectiveWords) }
        item { DictCategoryCard("Git & Version Control", gitWords) }
        item { DictCategoryCard("Roles & Process", rolesProcessWords) }
        item { DictCategoryCard("Bugs, Fixes & Crashes", bugsFixesWords) }
        item { EnglishBorrowingsCard() }
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
