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

private data class T2ConjRow(val subject: String, val form: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tenses2Screen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tenses — Part 2", color = Color.White) },
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
                T2BodyText(
                    "This section covers the French imperative mood and the continuous tenses. " +
                    "The imperative is used to give commands or make requests. " +
                    "Continuous tenses express an ongoing action at a specific moment in time."
                )
            }

            // ── Imperative — Introduction ────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(8.dp))
                T2SectionTitle("The Imperative — What Is It?")
            }
            item {
                T2InfoCard {
                    T2BodyText("The imperative is used to give commands, instructions, or requests. It has only three forms — second person singular (tu), first person plural (nous), and second person plural (vous). No subject pronoun is used.")
                    Spacer(modifier = Modifier.height(8.dp))
                    T2BodyText("• tu form  → gives a direct command to one person (informal)\n• nous form → suggests doing something together (e.g. Allons-y!)\n• vous form → polite or plural command")
                }
            }

            // ── Positive Imperative — ER verbs ──────────────────────────────
            item {
                Spacer(modifier = Modifier.height(16.dp))
                T2SectionTitle("Positive Imperative — -ER Verbs")
            }
            item {
                T2InfoCard {
                    Text("Example: parler (to speak)", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    T2ConjTable(listOf(
                        T2ConjRow("tu", "Parle !"),
                        T2ConjRow("nous", "Parlons !"),
                        T2ConjRow("vous", "Parlez !")
                    ))
                    Spacer(modifier = Modifier.height(10.dp))
                    T2RuleBox("⚠ Special rule: For -ER verbs, the tu form drops the final -s from the present tense.\nje parles → Parle ! (not Parles !)\nException: before y or en, the -s is restored: Parles-en ! (Talk about it!)")
                    Spacer(modifier = Modifier.height(8.dp))
                    T2ExampleText("Parle plus doucement ! → Speak more softly!\nParlons français ! → Let's speak French!\nParlez lentement, s'il vous plaît. → Please speak slowly.")
                }
            }

            // ── Positive Imperative — IR verbs ──────────────────────────────
            item {
                Spacer(modifier = Modifier.height(16.dp))
                T2SectionTitle("Positive Imperative — -IR Verbs")
            }
            item {
                T2InfoCard {
                    Text("Example: finir (to finish)", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    T2ConjTable(listOf(
                        T2ConjRow("tu", "Finis !"),
                        T2ConjRow("nous", "Finissons !"),
                        T2ConjRow("vous", "Finissez !")
                    ))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Note: -IR verb tu forms keep the final -s (unlike -ER verbs).", style = MaterialTheme.typography.bodySmall, color = FrenchNavy)
                    Spacer(modifier = Modifier.height(8.dp))
                    T2ExampleText("Finis tes devoirs ! → Finish your homework!\nChoisissons un restaurant ! → Let's choose a restaurant!\nRéfléchissez avant de répondre. → Think before answering.")
                }
            }

            // ── Positive Imperative — RE verbs ──────────────────────────────
            item {
                Spacer(modifier = Modifier.height(16.dp))
                T2SectionTitle("Positive Imperative — -RE Verbs")
            }
            item {
                T2InfoCard {
                    Text("Example: répondre (to answer)", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    T2ConjTable(listOf(
                        T2ConjRow("tu", "Réponds !"),
                        T2ConjRow("nous", "Répondons !"),
                        T2ConjRow("vous", "Répondez !")
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    T2ExampleText("Réponds à ma question ! → Answer my question!\nAttendons ici. → Let's wait here.\nVendez-moi ce livre ! → Sell me that book!")
                }
            }

            // ── Negative Imperative ──────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(16.dp))
                T2SectionTitle("Negative Imperative")
            }
            item {
                T2InfoCard {
                    T2BodyText("To make a negative command, wrap the verb with ne ... pas, just as in ordinary negation.")
                    Spacer(modifier = Modifier.height(8.dp))
                    T2CodeBlock(
                        "ne + imperative verb + pas"
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("-ER (parler):", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    T2ConjTable(listOf(
                        T2ConjRow("tu", "Ne parle pas !"),
                        T2ConjRow("nous", "Ne parlons pas !"),
                        T2ConjRow("vous", "Ne parlez pas !")
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("-IR (finir):", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    T2ConjTable(listOf(
                        T2ConjRow("tu", "Ne finis pas !"),
                        T2ConjRow("nous", "Ne finissons pas !"),
                        T2ConjRow("vous", "Ne finissez pas !")
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("-RE (répondre):", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    T2ConjTable(listOf(
                        T2ConjRow("tu", "Ne réponds pas !"),
                        T2ConjRow("nous", "Ne répondons pas !"),
                        T2ConjRow("vous", "Ne répondez pas !")
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    T2ExampleText("Ne parle pas si vite ! → Don't speak so fast!\nNe finissons pas en retard. → Let's not finish late.\nNe répondez pas sans réfléchir. → Don't answer without thinking.")
                }
            }

            // ── Irregular Imperatives ────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(16.dp))
                T2SectionTitle("Irregular Imperatives")
            }
            item {
                T2InfoCard {
                    T2BodyText("Several very common verbs have irregular imperative forms that must be memorised:")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("être (to be):", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    T2ConjTable(listOf(
                        T2ConjRow("tu", "Sois !"),
                        T2ConjRow("nous", "Soyons !"),
                        T2ConjRow("vous", "Soyez !")
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("avoir (to have):", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    T2ConjTable(listOf(
                        T2ConjRow("tu", "Aie !"),
                        T2ConjRow("nous", "Ayons !"),
                        T2ConjRow("vous", "Ayez !")
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("aller (to go):", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    T2ConjTable(listOf(
                        T2ConjRow("tu", "Va !"),
                        T2ConjRow("nous", "Allons !"),
                        T2ConjRow("vous", "Allez !")
                    ))
                    Spacer(modifier = Modifier.height(6.dp))
                    T2RuleBox("Note: va becomes vas before y — Vas-y ! (Go ahead! / Go there!)")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("faire (to do/make):", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    T2ConjTable(listOf(
                        T2ConjRow("tu", "Fais !"),
                        T2ConjRow("nous", "Faisons !"),
                        T2ConjRow("vous", "Faites !")
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("savoir (to know):", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    T2ConjTable(listOf(
                        T2ConjRow("tu", "Sache !"),
                        T2ConjRow("nous", "Sachons !"),
                        T2ConjRow("vous", "Sachez !")
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    T2ExampleText(
                        "Sois patient ! → Be patient!\n" +
                        "Ayez confiance ! → Have confidence!\n" +
                        "Va te coucher ! → Go to bed!\n" +
                        "Fais attention ! → Be careful!\n" +
                        "Sachez que je serai là. → Know that I will be there."
                    )
                }
            }

            // ── Continuous Tenses — Introduction ────────────────────────────
            item {
                Spacer(modifier = Modifier.height(16.dp))
                T2SectionTitle("Continuous Tenses — What Exists in French?")
            }
            item {
                T2InfoCard {
                    T2BodyText("Unlike English, French has no separate progressive verb form (no equivalent of \"-ing\" endings). Instead, French uses the construction:")
                    Spacer(modifier = Modifier.height(8.dp))
                    T2CodeBlock("être (conjugated) + en train de + infinitive")
                    Spacer(modifier = Modifier.height(8.dp))
                    T2BodyText("This construction exists in three time frames:\n• Present continuous — être (présent) + en train de\n• Past continuous — être (imparfait) + en train de\n• Future continuous — être (futur simple) + en train de")
                    Spacer(modifier = Modifier.height(8.dp))
                    T2RuleBox("\"En train de\" literally means \"in the process of\". It emphasises that the action is actively ongoing at the moment described.")
                }
            }

            // ── Present Continuous ───────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(16.dp))
                T2SectionTitle("Present Continuous")
            }
            item {
                T2InfoCard {
                    T2BodyText("Use the présent of être + en train de + infinitive to express an action happening right now.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Example: être en train de manger (to be eating)", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    T2ConjTable(listOf(
                        T2ConjRow("je", "suis en train de manger"),
                        T2ConjRow("tu", "es en train de manger"),
                        T2ConjRow("il / elle / on", "est en train de manger"),
                        T2ConjRow("nous", "sommes en train de manger"),
                        T2ConjRow("vous", "êtes en train de manger"),
                        T2ConjRow("ils / elles", "sont en train de manger")
                    ))
                    Spacer(modifier = Modifier.height(10.dp))
                    T2ExampleText(
                        "Je suis en train de lire. → I am reading (right now).\n" +
                        "Elle est en train de travailler. → She is working.\n" +
                        "Ils sont en train de discuter. → They are discussing.\n" +
                        "Ne le dérange pas — il est en train d'étudier. → Don't disturb him — he's studying."
                    )
                }
            }

            // ── Past Continuous ──────────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(16.dp))
                T2SectionTitle("Past Continuous")
            }
            item {
                T2InfoCard {
                    T2BodyText("Use the imparfait of être + en train de + infinitive to express an action that was in progress at a specific past moment, often interrupted by another event.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Example: être en train de travailler (to be working)", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    T2ConjTable(listOf(
                        T2ConjRow("je", "étais en train de travailler"),
                        T2ConjRow("tu", "étais en train de travailler"),
                        T2ConjRow("il / elle / on", "était en train de travailler"),
                        T2ConjRow("nous", "étions en train de travailler"),
                        T2ConjRow("vous", "étiez en train de travailler"),
                        T2ConjRow("ils / elles", "étaient en train de travailler")
                    ))
                    Spacer(modifier = Modifier.height(10.dp))
                    T2ExampleText(
                        "J'étais en train de dormir quand il a appelé. → I was sleeping when he called.\n" +
                        "Elle était en train de cuisiner. → She was cooking.\n" +
                        "Nous étions en train de discuter quand il est arrivé. → We were talking when he arrived.\n" +
                        "Ils étaient en train de jouer dans le jardin. → They were playing in the garden."
                    )
                }
            }

            // ── Future Continuous ────────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(16.dp))
                T2SectionTitle("Future Continuous")
            }
            item {
                T2InfoCard {
                    T2BodyText("Use the futur simple of être + en train de + infinitive to express an action that will be in progress at a specific future moment.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Example: être en train de voyager (to be travelling)", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    T2ConjTable(listOf(
                        T2ConjRow("je", "serai en train de voyager"),
                        T2ConjRow("tu", "seras en train de voyager"),
                        T2ConjRow("il / elle / on", "sera en train de voyager"),
                        T2ConjRow("nous", "serons en train de voyager"),
                        T2ConjRow("vous", "serez en train de voyager"),
                        T2ConjRow("ils / elles", "seront en train de voyager")
                    ))
                    Spacer(modifier = Modifier.height(10.dp))
                    T2ExampleText(
                        "À cette heure-là, je serai en train de dormir. → At that hour, I'll be sleeping.\n" +
                        "Demain matin, elle sera en train de préparer le dîner. → Tomorrow morning, she'll be preparing dinner.\n" +
                        "Quand tu arriveras, nous serons en train de manger. → When you arrive, we'll be eating."
                    )
                }
            }

            // ── Common Examples ──────────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(16.dp))
                T2SectionTitle("Common Examples — Mixed")
            }
            item {
                T2InfoCard {
                    T2ExampleText(
                        "Imperative:\n" +
                        "Mange tes légumes ! → Eat your vegetables!\n" +
                        "Ne cours pas dans les couloirs ! → Don't run in the corridors!\n" +
                        "Soyez à l'heure ! → Be on time!\n" +
                        "Allons-y ! → Let's go!\n\n" +
                        "Present continuous:\n" +
                        "Je suis en train de préparer le repas. → I'm preparing the meal.\n\n" +
                        "Past continuous:\n" +
                        "Il était en train de réparer la voiture quand il a commencé à pleuvoir.\n" +
                        "→ He was fixing the car when it started to rain.\n\n" +
                        "Future continuous:\n" +
                        "Dans deux heures, vous serez en train d'atterrir à Paris.\n" +
                        "→ In two hours, you'll be landing in Paris."
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun T2SectionTitle(title: String) {
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
private fun T2InfoCard(content: @Composable ColumnScope.() -> Unit) {
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
private fun T2ConjTable(rows: List<T2ConjRow>) {
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
private fun T2RuleBox(text: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = FrenchNavy,
            modifier = Modifier.padding(10.dp),
            lineHeight = 19.sp
        )
    }
}

@Composable
private fun T2CodeBlock(code: String) {
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
private fun T2BodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        lineHeight = 22.sp
    )
}

@Composable
private fun T2ExampleText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        fontStyle = FontStyle.Italic,
        color = FrenchBlue,
        lineHeight = 20.sp
    )
}
