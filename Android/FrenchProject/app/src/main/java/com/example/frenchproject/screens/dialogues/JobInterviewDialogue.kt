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

private data class JobLine(val speaker: String, val fr: String, val en: String)

private val jobLines = listOf(
    JobLine("RH", "Bonjour, Monsieur Martin. Asseyez-vous, je vous en prie. Je suis Claire Dupont, responsable des ressources humaines.", "Good morning, Mr. Martin. Please sit down. I'm Claire Dupont, HR manager."),
    JobLine("Candidat", "Bonjour, Madame Dupont. Merci de me recevoir.", "Good morning, Madame Dupont. Thank you for seeing me."),
    JobLine("RH", "Pouvez-vous me parler de votre expérience en développement C++ ?", "Can you tell me about your experience in C++ development?"),
    JobLine("Candidat", "Bien sûr. J'ai dix ans d'expérience en C++, principalement dans les systèmes embarqués et le développement bas niveau sous Linux — drivers, modules kernel et applications réseau.", "Of course. I have ten years of experience in C++, mainly in embedded systems and low-level Linux development — drivers, kernel modules and network applications."),
    JobLine("RH", "Intéressant. Pouvez-vous décrire un projet sur lequel vous avez travaillé récemment ?", "Interesting. Can you describe a project you worked on recently?"),
    JobLine("Candidat", "Oui. J'ai développé un VPN sous Linux. J'ai utilisé l'interface TUN/TAP pour créer un tunnel réseau, j'ai implémenté le chiffrement avec OpenSSL, et le trafic était encapsulé dans des paquets UDP.", "Yes. I developed a VPN on Linux. I used the TUN/TAP interface to create a network tunnel, implemented encryption with OpenSSL, and traffic was encapsulated in UDP packets."),
    JobLine("RH", "Très impressionnant. Avez-vous des questions pour nous ?", "Very impressive. Do you have any questions for us?"),
    JobLine("Candidat", "Oui. Pouvez-vous me parler de l'équipe ? Combien de développeurs travaillent sur ce projet ?", "Yes. Can you tell me about the team? How many developers work on this project?"),
    JobLine("RH", "Nous avons une équipe de douze développeurs, dont quatre spécialisés en C++. Nous travaillons en méthode agile, avec des sprints de deux semaines.", "We have a team of twelve developers, four of whom specialize in C++. We work in agile with two-week sprints."),
    JobLine("Candidat", "Et quels sont les projets principaux sur lesquels l'équipe travaille en ce moment ?", "And what are the main projects the team is working on right now?"),
    JobLine("RH", "Nous développons une plateforme de communication sécurisée pour les entreprises — beaucoup de défis techniques intéressants.", "We're developing a secure communications platform for businesses — lots of interesting technical challenges."),
    JobLine("Candidat", "Ça a l'air vraiment passionnant. Merci pour ces informations, Madame Dupont.", "That sounds really exciting. Thank you for this information, Madame Dupont."),
    JobLine("RH", "Merci à vous, Monsieur Martin. Nous vous contacterons dans les prochains jours.", "Thank you, Mr. Martin. We will be in touch in the coming days.")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobInterviewScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Job Interview", color = Color.White) },
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
            item { JobSectionHeader("En français") }
            items(jobLines.size) { i ->
                JobCard(line = jobLines[i], showFrench = true)
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { JobSectionHeader("English Translation") }
            items(jobLines.size) { i ->
                JobCard(line = jobLines[i], showFrench = false)
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun JobSectionHeader(title: String) {
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
private fun JobCard(line: JobLine, showFrench: Boolean) {
    val isCandidat = line.speaker == "Candidat"
    val containerColor = if (isCandidat)
        MaterialTheme.colorScheme.secondaryContainer
    else
        MaterialTheme.colorScheme.tertiaryContainer
    val onContainerColor = if (isCandidat)
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
                modifier = Modifier.width(68.dp)
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
