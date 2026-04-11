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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

private data class FrenchVerb(
    val infinitive: String,
    val english: String,
    val note: String? = null
)

private data class VerbSection(val title: String, val verbs: List<FrenchVerb>)

private val verbSections = listOf(
    VerbSection("Essential Irregular Verbs", listOf(
        FrenchVerb("être", "to be", "je suis, tu es, il est, nous sommes, vous êtes, ils sont"),
        FrenchVerb("avoir", "to have", "j'ai, tu as, il a, nous avons, vous avez, ils ont"),
        FrenchVerb("aller", "to go", "je vais, tu vas, il va, nous allons, vous allez, ils vont"),
        FrenchVerb("faire", "to do / to make", "je fais, tu fais, il fait, nous faisons, vous faites, ils font"),
        FrenchVerb("dire", "to say / to tell", "je dis, tu dis, il dit, nous disons, vous dites, ils disent"),
        FrenchVerb("voir", "to see", "je vois, tu vois, il voit, nous voyons, vous voyez, ils voient"),
        FrenchVerb("venir", "to come", "je viens, tu viens, il vient, nous venons, vous venez, ils viennent"),
        FrenchVerb("pouvoir", "to be able to / can", "je peux, tu peux, il peut, nous pouvons, vous pouvez, ils peuvent"),
        FrenchVerb("vouloir", "to want", "je veux, tu veux, il veut, nous voulons, vous voulez, ils veulent"),
        FrenchVerb("savoir", "to know (a fact)", "je sais, tu sais, il sait, nous savons, vous savez, ils savent"),
        FrenchVerb("prendre", "to take", "je prends, tu prends, il prend, nous prenons, vous prenez, ils prennent"),
        FrenchVerb("mettre", "to put / to place", "je mets, tu mets, il met, nous mettons, vous mettez, ils mettent"),
        FrenchVerb("partir", "to leave / to depart", "je pars, tu pars, il part, nous partons, vous partez, ils partent"),
        FrenchVerb("appeler", "to call", "j'appelle, tu appelles, il appelle, nous appelons, vous appelez, ils appellent — note double-l"),
        FrenchVerb("écrire", "to write", "j'écris, tu écris, il écrit, nous écrivons, vous écrivez, ils écrivent"),
        FrenchVerb("lire", "to read", "je lis, tu lis, il lit, nous lisons, vous lisez, ils lisent"),
        FrenchVerb("boire", "to drink", "je bois, tu bois, il boit, nous buvons, vous buvez, ils boivent")
    )),
    VerbSection("Regular -ER Verbs", listOf(
        FrenchVerb("parler", "to speak / to talk"),
        FrenchVerb("manger", "to eat", "nous mangeons — spelling change before -o"),
        FrenchVerb("écouter", "to listen (to)"),
        FrenchVerb("regarder", "to look at / to watch"),
        FrenchVerb("aimer", "to love / to like"),
        FrenchVerb("penser", "to think"),
        FrenchVerb("trouver", "to find"),
        FrenchVerb("donner", "to give"),
        FrenchVerb("rester", "to stay / to remain"),
        FrenchVerb("chercher", "to look for / to search"),
        FrenchVerb("travailler", "to work"),
        FrenchVerb("habiter", "to live (somewhere)"),
        FrenchVerb("arriver", "to arrive"),
        FrenchVerb("commencer", "to begin / to start"),
        FrenchVerb("utiliser", "to use"),
        FrenchVerb("montrer", "to show"),
        FrenchVerb("entrer", "to enter"),
        FrenchVerb("passer", "to pass / to spend (time)")
    )),
    VerbSection("Regular -IR Verbs", listOf(
        FrenchVerb("finir", "to finish"),
        FrenchVerb("choisir", "to choose"),
        FrenchVerb("réussir", "to succeed"),
        FrenchVerb("grandir", "to grow up"),
        FrenchVerb("obéir", "to obey"),
        FrenchVerb("remplir", "to fill")
    )),
    VerbSection("Regular -RE Verbs", listOf(
        FrenchVerb("vendre", "to sell"),
        FrenchVerb("répondre", "to answer / to respond"),
        FrenchVerb("attendre", "to wait (for)"),
        FrenchVerb("entendre", "to hear"),
        FrenchVerb("perdre", "to lose"),
        FrenchVerb("rendre", "to return / to give back"),
        FrenchVerb("comprendre", "to understand", "conjugates like prendre"),
        FrenchVerb("apprendre", "to learn", "conjugates like prendre")
    ))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonVerbsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Common Verbs", color = Color.White) },
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
                    text = "French verbs fall into three main groups: -ER, -IR, and -RE. Irregular verbs must be memorized individually. The conjugation shown in the note is the présent (present tense).",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            verbSections.forEach { section ->
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
                items(section.verbs.size) { i -> VerbCard(section.verbs[i]) }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun VerbCard(verb: FrenchVerb) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 9.dp)
        ) {
            Row {
                Text(
                    text = verb.infinitive,
                    fontWeight = FontWeight.Bold,
                    color = FrenchBlue,
                    fontSize = 15.sp,
                    modifier = Modifier.width(130.dp)
                )
                Text(
                    text = verb.english,
                    style = MaterialTheme.typography.bodyMedium,
                    color = FrenchNavy
                )
            }
            if (verb.note != null) {
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = verb.note,
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
