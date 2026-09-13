package com.example.frenchproject.screens.dialogues

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

private data class BugLine(val speaker: String, val fr: String, val en: String)

private val bugLines = listOf(
    BugLine("Ingénieur Noyau", "Salut, tu as une minute ? Je voudrais parler du bug client où la machine se bloque complètement.", "Hi, got a minute? I want to talk about the customer bug where the machine hangs completely."),
    BugLine("Ingénieur Applicatif", "Oui, j'ai vu le ticket. Tu as réussi à le reproduire ?", "Yes, I saw the ticket. Have you managed to reproduce it?"),
    BugLine("Ingénieur Noyau", "Pas encore. J'essaie de le reproduire sur la VM pour pouvoir récupérer un dump mémoire pendant que la machine est bloquée.", "Not yet. I'm trying to reproduce it on the VM so I can grab a memory dump while the machine is hung."),
    BugLine("Ingénieur Applicatif", "Tu as une théorie pour l'instant ?", "Do you have a theory so far?"),
    BugLine("Ingénieur Noyau", "Je soupçonne soit un interblocage entre deux threads, soit un thread qui prend un mutex et ne le libère jamais.", "I suspect either a deadlock between two threads, or a thread that takes a mutex and never releases it."),
    BugLine("Ingénieur Applicatif", "D'accord. Dès que tu as le dump, préviens-moi.", "Okay. As soon as you have the dump, let me know."),
    BugLine("Ingénieur Applicatif", "De mon côté, j'ai un autre bug à te signaler : une lenteur signalée par plusieurs clients côté applicatif.", "On my side, I have another bug to flag: some slowness reported by several customers on the application side."),
    BugLine("Ingénieur Noyau", "Tu as trouvé la cause ?", "Did you find the cause?"),
    BugLine("Ingénieur Applicatif", "Oui. Une API est appelée beaucoup trop souvent, et en plus elle est un peu lente en elle-même.", "Yes. An API is being called way too often, and on top of that it's a bit slow by itself."),
    BugLine("Ingénieur Noyau", "Tu penses faire quoi ? Appeler l'API autrement, ou éviter de l'appeler ?", "What are you thinking of doing? Call the API differently, or avoid calling it?"),
    BugLine("Ingénieur Applicatif", "Deux options : soit récupérer les données autrement, sans passer par cette API, soit mettre en place un mécanisme de cache.", "Two options: either fetch the data a different way, without going through that API, or set up a caching mechanism."),
    BugLine("Ingénieur Noyau", "Un cache, ça peut marcher. Mais fais attention à ce qu'il ne grossisse pas indéfiniment.", "A cache could work. But be careful it doesn't grow indefinitely."),
    BugLine("Ingénieur Applicatif", "Oui, exactement. Je pense utiliser une stratégie LRU pour évincer les entrées les moins récemment utilisées.", "Yes, exactly. I'm thinking of using an LRU strategy to evict the least recently used entries."),
    BugLine("Ingénieur Noyau", "Bonne idée. Comme ça le cache reste borné et tu gardes les performances.", "Good idea. That way the cache stays bounded and you keep the performance gains.")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoftwareEngineeringBugsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Software Engineering Bugs", color = Color.White) },
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
                .padding(horizontal = 12.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item { BugSectionHeader("En français") }
            items(bugLines.size) { i ->
                BugCard(line = bugLines[i], showFrench = true)
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { BugSectionHeader("English Translation") }
            items(bugLines.size) { i ->
                BugCard(line = bugLines[i], showFrench = false)
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun BugSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = FrenchBlue,
        modifier = Modifier.padding(vertical = 8.dp)
    )
    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.4f))
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun BugCard(line: BugLine, showFrench: Boolean) {
    val isKernel = line.speaker == "Ingénieur Noyau"
    val containerColor = if (isKernel)
        MaterialTheme.colorScheme.secondaryContainer
    else
        MaterialTheme.colorScheme.tertiaryContainer
    val onContainerColor = if (isKernel)
        MaterialTheme.colorScheme.onSecondaryContainer
    else
        MaterialTheme.colorScheme.onTertiaryContainer

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = line.speaker,
                fontWeight = FontWeight.Bold,
                color = onContainerColor,
                fontSize = 12.sp,
                modifier = Modifier.width(110.dp)
            )
            Text(
                text = if (showFrench) line.fr else line.en,
                style = MaterialTheme.typography.bodySmall,
                color = onContainerColor,
                lineHeight = 18.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
