package com.example.frenchproject.screens.learning

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

private data class TenseConjRow(val subject: String, val form: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TensesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tenses", color = Color.White) },
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
                    text = "French has several important tenses. This guide covers the most commonly used ones — from everyday present tense to past, future, and conditional — plus the essential concept of the participe passé.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // ── Participe Passé ──────────────────────────────────────────────
            item { TenseSectionTitle("Participe Passé — Past Participle") }
            item {
                InfoCard {
                    BodyText("The participe passé is used in compound tenses (like passé composé) and also as an adjective. It is formed from the infinitive:")
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        "-ER verbs  →  remove -er, add -é      parler → parlé\n" +
                        "-IR verbs  →  remove -ir, add -i       finir  → fini\n" +
                        "-RE verbs  →  remove -re, add -u       vendre → vendu"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Common irregular participes passés:", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    CodeBlock(
                        "être   → été        avoir  → eu\n" +
                        "aller  → allé       faire  → fait\n" +
                        "voir   → vu         dire   → dit\n" +
                        "prendre→ pris       venir  → venu\n" +
                        "pouvoir→ pu         vouloir→ voulu\n" +
                        "savoir → su         mettre → mis\n" +
                        "écrire → écrit      lire   → lu\n" +
                        "boire  → bu         ouvrir → ouvert"
                    )
                }
            }

            // ── Présent ──────────────────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(16.dp))
                TenseSectionTitle("Présent — Present Tense")
            }
            item {
                InfoCard {
                    Text("When to use:", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    BodyText("• Actions happening right now\n• Habitual or repeated actions\n• General truths or facts")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Regular -ER verbs (e.g. parler — to speak):", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    ConjTable(listOf(
                        TenseConjRow("je", "parle"),
                        TenseConjRow("tu", "parles"),
                        TenseConjRow("il / elle / on", "parle"),
                        TenseConjRow("nous", "parlons"),
                        TenseConjRow("vous", "parlez"),
                        TenseConjRow("ils / elles", "parlent")
                    ))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Regular -IR verbs (e.g. finir — to finish):", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    ConjTable(listOf(
                        TenseConjRow("je", "finis"),
                        TenseConjRow("tu", "finis"),
                        TenseConjRow("il / elle / on", "finit"),
                        TenseConjRow("nous", "finissons"),
                        TenseConjRow("vous", "finissez"),
                        TenseConjRow("ils / elles", "finissent")
                    ))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Regular -RE verbs (e.g. vendre — to sell):", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    ConjTable(listOf(
                        TenseConjRow("je", "vends"),
                        TenseConjRow("tu", "vends"),
                        TenseConjRow("il / elle / on", "vend"),
                        TenseConjRow("nous", "vendons"),
                        TenseConjRow("vous", "vendez"),
                        TenseConjRow("ils / elles", "vendent")
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    ExampleText("Je parle français. → I speak French.\nNous vendons des livres. → We sell books.")
                }
            }

            // ── Futur Proche ─────────────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(16.dp))
                TenseSectionTitle("Futur Proche — Near Future")
            }
            item {
                InfoCard {
                    Text("When to use:", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    BodyText("• Actions about to happen or planned in the near future\n• Equivalent to English \"going to...\"\n• Very common in everyday spoken French")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Formation: aller (conjugated) + infinitive", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    ConjTable(listOf(
                        TenseConjRow("je vais", "parler"),
                        TenseConjRow("tu vas", "parler"),
                        TenseConjRow("il / elle va", "parler"),
                        TenseConjRow("nous allons", "parler"),
                        TenseConjRow("vous allez", "parler"),
                        TenseConjRow("ils / elles vont", "parler")
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    ExampleText("Je vais manger. → I'm going to eat.\nElle va partir demain. → She is going to leave tomorrow.\nNous allons voir un film. → We are going to watch a film.")
                }
            }

            // ── Futur Simple ─────────────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(16.dp))
                TenseSectionTitle("Futur Simple — Simple Future")
            }
            item {
                InfoCard {
                    Text("When to use:", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    BodyText("• Future events (often more distant or uncertain)\n• Formal and written French\n• After quand, lorsque, dès que, aussitôt que (when English uses present)")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Formation: infinitive stem + endings", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    CodeBlock("-ai  /  -as  /  -a  /  -ons  /  -ez  /  -ont")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("(-RE verbs: drop the final -e before adding endings)")
                    Spacer(modifier = Modifier.height(8.dp))
                    ConjTable(listOf(
                        TenseConjRow("je", "parlerai"),
                        TenseConjRow("tu", "parleras"),
                        TenseConjRow("il / elle / on", "parlera"),
                        TenseConjRow("nous", "parlerons"),
                        TenseConjRow("vous", "parlerez"),
                        TenseConjRow("ils / elles", "parleront")
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Irregular future stems:", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    CodeBlock(
                        "être  → ser-     avoir → aur-\n" +
                        "aller → ir-      faire → fer-\n" +
                        "voir  → verr-    venir → viendr-\n" +
                        "pouvoir→ pourr-  vouloir→ voudr-\n" +
                        "savoir → saur-   tenir → tiendr-"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ExampleText("Il parlera demain. → He will speak tomorrow.\nQuand tu arriveras, appelle-moi. → When you arrive, call me.")
                }
            }

            // ── Passé Composé ────────────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(16.dp))
                TenseSectionTitle("Passé Composé — Compound Past")
            }
            item {
                InfoCard {
                    Text("When to use:", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    BodyText("• Completed, specific events in the past\n• Actions with a clear beginning or end\n• Most common past tense in spoken French\n• Contrast: use imparfait for background/ongoing — use passé composé for events that happened")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Formation: avoir or être (present) + participe passé", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    ConjTable(listOf(
                        TenseConjRow("j'", "ai parlé"),
                        TenseConjRow("tu", "as parlé"),
                        TenseConjRow("il / elle / on", "a parlé"),
                        TenseConjRow("nous", "avons parlé"),
                        TenseConjRow("vous", "avez parlé"),
                        TenseConjRow("ils / elles", "ont parlé")
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("See « Être et Avoir » for the list of verbs that use être as auxiliary and the agreement rules.")
                    Spacer(modifier = Modifier.height(8.dp))
                    ExampleText("J'ai mangé une pizza. → I ate a pizza.\nElle est arrivée hier. → She arrived yesterday.\nNous avons fini le travail. → We finished the work.")
                }
            }

            // ── Imparfait ────────────────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(16.dp))
                TenseSectionTitle("Imparfait — Imperfect")
            }
            item {
                InfoCard {
                    Text("When to use:", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    BodyText("• Ongoing or repeated past actions (\"used to\", \"was doing\")\n• Background descriptions in a narrative\n• States of mind, feelings, or conditions in the past\n• Habitual actions in the past")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Formation:", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    BodyText("Take the nous form of the présent, drop -ons, then add these endings:")
                    Spacer(modifier = Modifier.height(4.dp))
                    CodeBlock("-ais  /  -ais  /  -ait  /  -ions  /  -iez  /  -aient")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("parler: nous parl-ons → stem: parl-")
                    Spacer(modifier = Modifier.height(4.dp))
                    ConjTable(listOf(
                        TenseConjRow("je", "parlais"),
                        TenseConjRow("tu", "parlais"),
                        TenseConjRow("il / elle / on", "parlait"),
                        TenseConjRow("nous", "parlions"),
                        TenseConjRow("vous", "parliez"),
                        TenseConjRow("ils / elles", "parlaient")
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("⚠ Only être is irregular: stem is ét-", style = MaterialTheme.typography.bodySmall, color = Color(0xFF8B0000), fontStyle = FontStyle.Italic)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Passé composé vs. Imparfait:", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    ExampleText(
                        "Je lisais quand il est entré.\n" +
                        "→ I was reading (imparfait — ongoing background) when he entered (passé composé — completed event).\n\n" +
                        "Quand j'étais enfant, je jouais au foot.\n" +
                        "→ When I was a child, I used to play football. (habitual past)"
                    )
                }
            }

            // ── Conditionnel Présent ─────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(16.dp))
                TenseSectionTitle("Conditionnel Présent — Conditional")
            }
            item {
                InfoCard {
                    Text("When to use:", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    BodyText("• Polite requests (the most common everyday use)\n• Hypothetical situations (\"I would...\")\n• Unconfirmed or reported information (\"it would appear that...\")\n• The result clause of si + imparfait sentences")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Formation: futur simple stem + imparfait endings", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    CodeBlock("-ais  /  -ais  /  -ait  /  -ions  /  -iez  /  -aient")
                    Spacer(modifier = Modifier.height(8.dp))
                    ConjTable(listOf(
                        TenseConjRow("je", "parlerais"),
                        TenseConjRow("tu", "parlerais"),
                        TenseConjRow("il / elle / on", "parlerait"),
                        TenseConjRow("nous", "parlerions"),
                        TenseConjRow("vous", "parleriez"),
                        TenseConjRow("ils / elles", "parleraient")
                    ))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Key example — polite request:", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                        elevation = CardDefaults.cardElevation(0.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "Je voudrais un café, s'il vous plaît.",
                                fontWeight = FontWeight.Bold,
                                color = FrenchBlue,
                                fontSize = 15.sp
                            )
                            Text(
                                text = "→ I would like a coffee, please.",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = FrenchNavy
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Je voudrais is vouloir (to want) in conditionnel — far more polite than je veux (I want).",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    ExampleText(
                        "Si j'avais de l'argent, j'achèterais une maison.\n" +
                        "→ If I had money, I would buy a house.\n\n" +
                        "Il faudrait partir maintenant.\n" +
                        "→ We should leave now. (polite obligation)"
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun TenseSectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = FrenchBlue,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.4f))
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun InfoCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

@Composable
private fun ConjTable(rows: List<TenseConjRow>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF0F3FA), RoundedCornerShape(6.dp))
            .padding(8.dp)
    ) {
        rows.forEachIndexed { index, row ->
            if (index > 0) HorizontalDivider(color = Color(0xFFD0D8E8), modifier = Modifier.padding(vertical = 2.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = row.subject,
                    color = FrenchNavy,
                    fontSize = 13.sp,
                    modifier = Modifier.width(130.dp)
                )
                Text(
                    text = row.form,
                    fontWeight = FontWeight.Bold,
                    color = FrenchBlue,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
private fun CodeBlock(code: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF0F3FA), RoundedCornerShape(4.dp))
            .padding(8.dp)
    ) {
        Text(
            text = code,
            fontFamily = FontFamily.Monospace,
            fontSize = 12.sp,
            color = FrenchNavy,
            lineHeight = 20.sp
        )
    }
}

@Composable
private fun BodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        lineHeight = 22.sp
    )
}

@Composable
private fun ExampleText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        fontStyle = FontStyle.Italic,
        color = FrenchBlue,
        lineHeight = 20.sp
    )
}
