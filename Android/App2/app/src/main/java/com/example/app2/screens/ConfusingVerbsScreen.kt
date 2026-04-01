package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

private data class ConfusingVerbEntry(
    val title: String,
    val sections: List<VerbSection>,
    val tip: String
)

private data class VerbSection(
    val verb: String,
    val meaning: String,
    val examples: List<Pair<String, String>>
)

private val confusingVerbs = listOf(
    ConfusingVerbEntry(
        title = "mandar vs. enviar — To Send",
        sections = listOf(
            VerbSection(
                verb = "mandar",
                meaning = "To send (casual) — also to order / command someone to do something",
                examples = listOf(
                    "Mandei um email." to "I sent an email. (everyday)",
                    "Ela mandou-me embora." to "She sent me away / told me to leave.",
                    "O chefe mandou-me fazer o relatório." to "The boss ordered me to do the report.",
                    "Manda-me uma mensagem." to "Send me a message. (casual)"
                )
            ),
            VerbSection(
                verb = "enviar",
                meaning = "To send (formal/written) — preferred in professional contexts and UI labels",
                examples = listOf(
                    "Enviámos a encomenda ontem." to "We sent the package yesterday.",
                    "Clica em \"Enviar\"." to "Click \"Send.\" (button label)",
                    "O documento foi enviado por email." to "The document was sent by email."
                )
            )
        ),
        tip = "In casual speech, prefer mandar. In formal writing, business communication, or app interfaces, prefer enviar."
    ),
    ConfusingVerbEntry(
        title = "tomar vs. beber — To Drink / To Take",
        sections = listOf(
            VerbSection(
                verb = "beber",
                meaning = "To drink — used for any liquid in a general sense",
                examples = listOf(
                    "Bebo café de manhã." to "I drink coffee in the morning.",
                    "Queres beber água?" to "Do you want to drink some water?",
                    "Não deves beber e conduzir." to "You shouldn't drink and drive."
                )
            ),
            VerbSection(
                verb = "tomar",
                meaning = "To take — but also used with hot drinks and meals in fixed expressions",
                examples = listOf(
                    "Tomar um café." to "To have a coffee. (most natural expression)",
                    "Vamos tomar um copo?" to "Shall we have a drink? (informal/social)",
                    "Tomo o pequeno-almoço às oito." to "I have breakfast at eight.",
                    "Tomei um comprimido." to "I took a pill.",
                    "Vou tomar banho." to "I'm going to have a shower.",
                    "Tomei um táxi." to "I took a taxi."
                )
            )
        ),
        tip = "Use beber for general drinking of any liquid. Use tomar for meals, hot drinks (café, chá), medicine, transport, and hygiene."
    ),
    ConfusingVerbEntry(
        title = "trazer vs. levar — To Bring vs. To Take",
        sections = listOf(
            VerbSection(
                verb = "trazer",
                meaning = "To bring — movement towards the speaker or the place we are",
                examples = listOf(
                    "Traz-me um copo de água, por favor." to "Bring me a glass of water, please.",
                    "Trouxe pão da padaria." to "I brought bread from the bakery.",
                    "Podes trazer o livro amanhã?" to "Can you bring the book tomorrow?"
                )
            ),
            VerbSection(
                verb = "levar",
                meaning = "To take / carry — movement away from the speaker, to another place",
                examples = listOf(
                    "Leva o cão a passear." to "Take the dog for a walk.",
                    "Levei o relatório para o escritório." to "I took the report to the office.",
                    "Leva um casaco — está frio." to "Take a jacket — it's cold."
                )
            )
        ),
        tip = "Think of it as direction: trazer = bring here (towards you); levar = take there (away from you)."
    ),
    ConfusingVerbEntry(
        title = "entender vs. perceber — To Understand",
        sections = listOf(
            VerbSection(
                verb = "perceber",
                meaning = "To understand / get it — strongly preferred in European Portuguese",
                examples = listOf(
                    "Percebes?" to "Do you understand? / Get it?",
                    "Não percebi nada." to "I didn't understand anything.",
                    "Percebo o problema." to "I understand the problem.",
                    "Já percebi." to "I got it now. / I understand now."
                )
            ),
            VerbSection(
                verb = "entender",
                meaning = "To understand — also correct, but sounds slightly more formal or Brazilian; has an additional meaning",
                examples = listOf(
                    "Não me entendes." to "You don't understand me.",
                    "Entendo o que dizes." to "I understand what you're saying.",
                    "Eles entendem-se bem." to "They get along well. (distinct meaning: to get along with each other)"
                )
            )
        ),
        tip = "Default to perceber in everyday European Portuguese. Note: entender-se com alguém means to get along with someone — a different meaning entirely."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfusingVerbsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Slightly Confusing Verbs") },
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
            items(confusingVerbs) { entry ->
                ConfusingVerbCard(entry)
            }
        }
    }
}

@Composable
private fun ConfusingVerbCard(entry: ConfusingVerbEntry) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(entry.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            entry.sections.forEachIndexed { index, section ->
                if (index > 0) HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
                Text(section.verb, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                Text(section.meaning, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(2.dp))
                section.examples.forEach { (pt, en) ->
                    Text(
                        "\u2022 $pt",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        "  $en",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
            Text(
                "Tip: ${entry.tip}",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}
