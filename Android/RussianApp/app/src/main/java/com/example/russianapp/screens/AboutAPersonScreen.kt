package com.example.russianapp.screens

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutAPersonScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("About A Person & Actions") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {

            // ── Short-form adjectives ────────────────────────────────────────
            item { PersonSectionHeader("Краткие прилагательные — Short-Form Adjectives") }
            item {
                PersonCard(title = "What are short-form adjectives?") {
                    Text(
                        text = "Short-form adjectives (краткие прилагательные) are predicative-only: " +
                                "they always follow the subject as a predicate and never appear before a noun. " +
                                "They agree with the subject in gender and number. " +
                                "The present-tense copula \"to be\" is omitted in Russian.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "рад / рада exists only as a short form — there is no full-form adjective for \"glad\".",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            item {
                PersonCard(title = "Short-form adjective forms — M / F / N / Pl") {
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Meaning", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.4f))
                        Text("M", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.weight(1f))
                        Text("F", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.weight(1f))
                        Text("N", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Text("Pl", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        listOf("glad *",    "рад",      "рада",    "радо",    "рады"),
                        listOf("ready",     "готов",    "готова",  "готово",  "готовы"),
                        listOf("sick / ill","болен",    "больна",  "больно",  "больны"),
                        listOf("right",     "прав",     "права",   "право",   "правы"),
                        listOf("busy",      "занят",    "занята",  "занято",  "заняты"),
                        listOf("free",      "свободен", "свободна","свободно","свободны")
                    ).forEachIndexed { i, row ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 4.dp)
                        ) {
                            Text(row[0], style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.weight(1.4f))
                            Text(row[1], style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.weight(1f))
                            Text(row[2], style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.weight(1f))
                            Text(row[3], style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                            Text(row[4], style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        }
                    }
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "* рад / рада — short-form only, no long-form adjective exists.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.height(8.dp))
                    PersonExampleRow("Я рад тебя видеть.", "I'm glad to see you. (male speaker)")
                    PersonExampleRow("Я рада тебя видеть.", "I'm glad to see you. (female speaker)")
                    PersonExampleRow("Он готов. / Она готова.", "He is ready. / She is ready.")
                    PersonExampleRow("Ты прав. / Ты права.", "You are right. (m / f)")
                    PersonExampleRow("Они заняты.", "They are busy.")
                    PersonExampleRow("Я свободна в пятницу.", "I'm free on Friday. (female speaker)")
                }
            }

            // ── Нужно / Надо ─────────────────────────────────────────────────
            item { PersonSectionHeader("Нужно / Надо — Need To / Have To") }
            item {
                PersonCard(title = "Impersonal dative construction (безличная конструкция с дативом)") {
                    Text(
                        text = "Structure:  [Dative person]  +  нужно / надо  +  [Infinitive]\n\n" +
                                "The \"subject\" who needs to do something is in the DATIVE case, not nominative. " +
                                "Neither нужно nor надо changes form — they are invariant predicates.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "нужно — slightly more formal / written",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "надо — everyday colloquial, same meaning",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }
            item {
                PersonCard(title = "Dative pronouns — who needs to act") {
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Nominative", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f))
                        Text("Dative", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.weight(1f))
                        Text("Meaning", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        Triple("я",   "мне",  "I"),
                        Triple("ты",  "тебе", "you"),
                        Triple("он",  "ему",  "he"),
                        Triple("она", "ей",   "she"),
                        Triple("мы",  "нам",  "we"),
                        Triple("вы",  "вам",  "you (pl/formal)"),
                        Triple("они", "им",   "they")
                    ).forEachIndexed { i, (nom, dat, eng) ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 4.dp)
                        ) {
                            Text(nom, style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.weight(1.3f))
                            Text(dat, style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.weight(1f))
                            Text(eng, style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.weight(1.2f))
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    PersonExampleRow("Мне нужно идти.", "I need to go.")
                    PersonExampleRow("Тебе надо учиться.", "You need to study.")
                    PersonExampleRow("Ей нужно позвонить врачу.", "She needs to call the doctor.")
                    PersonExampleRow("Нам надо поторопиться.", "We need to hurry.")
                    PersonExampleRow("Им нужно знать правила.", "They need to know the rules.")
                }
            }

            // ── Должен ───────────────────────────────────────────────────────
            item { PersonSectionHeader("Должен — Must / Have To / Supposed To") }
            item {
                PersonCard(title = "Modal short-form adjective (модальное краткое прилагательное)") {
                    Text(
                        text = "Должен is a short-form adjective used as a modal word meaning " +
                                "\"must / have to / supposed to\". Unlike нужно/надо, it AGREES with the " +
                                "subject in gender and number.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("M", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.weight(1f))
                        Text("F", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.weight(1f))
                        Text("N", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Text("Pl", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        listOf("Present", "должен",      "должна",      "должно",      "должны"),
                        listOf("Past",    "был должен",  "была должна", "было должно", "были должны"),
                        listOf("Future",  "буду должен", "будет должна","будет должно","будут должны")
                    ).forEachIndexed { i, row ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 4.dp)
                        ) {
                            Text(row[0], style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                            Text(row[1], style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.weight(1f))
                            Text(row[2], style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.weight(1f))
                            Text(row[3], style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                            Text(row[4], style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    PersonExampleRow("Я должен идти.", "I have to go. (male speaker)")
                    PersonExampleRow("Я должна идти.", "I have to go. (female speaker)")
                    PersonExampleRow("Он должен работать.", "He has to work.")
                    PersonExampleRow("Она должна позвонить.", "She has to call.")
                    PersonExampleRow("Мы должны знать правила.", "We have to know the rules.")
                    PersonExampleRow("Дети должны слушать.", "Children must listen.")
                    PersonExampleRow("Она была должна ответить.", "She was supposed to answer.")
                }
            }
            item {
                PersonCard(title = "Должен vs Нужно / Надо — when to use which") {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "Должен — personal obligation",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "Use when obligation comes from a person's role, promise, or duty. " +
                                        "The word agrees with the subject.",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(Modifier.height(6.dp))
                            Text(
                                text = "Нужно / Надо — general necessity or need",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "Use for practical needs or circumstances. Invariant — never changes form.",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    PersonExampleRow(
                        "Он должен быть там в 9. / Ему нужно быть там в 9.",
                        "He must be there at 9. (duty) / He needs to be there at 9. (necessity)"
                    )
                }
            }

            // ── Нельзя ───────────────────────────────────────────────────────
            item { PersonSectionHeader("Нельзя — Forbidden / Not Possible") }
            item {
                PersonCard(title = "Impersonal modal predicate (безличное модальное слово)") {
                    Text(
                        text = "Нельзя is completely invariant — it never changes form. " +
                                "It is used with an infinitive and has two distinct meanings depending on context.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Meaning", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f))
                        Text("Description", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(2.5f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        "Prohibition" to "It is not allowed / must not / forbidden",
                        "Impossibility" to "One cannot / it is not possible"
                    ).forEachIndexed { i, (meaning, desc) ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 4.dp)
                        ) {
                            Text(meaning, style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.weight(1.3f))
                            Text(desc, style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier.weight(2.5f))
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "A dative pronoun can optionally name the person restricted: Мне нельзя… " +
                                "(I'm not allowed to…)",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Opposite: можно (it is allowed / one may)",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.height(8.dp))
                    PersonExampleRow("Здесь нельзя курить.", "Smoking is not allowed here. (prohibition)")
                    PersonExampleRow("Нельзя опаздывать.", "One must not be late. (prohibition)")
                    PersonExampleRow("Мне нельзя есть сахар.", "I'm not allowed to eat sugar. (prohibition)")
                    PersonExampleRow("Ему нельзя пить алкоголь.", "He's not allowed to drink alcohol. (prohibition)")
                    PersonExampleRow("Тут нельзя парковаться.", "You can't park here. (prohibition)")
                    PersonExampleRow("Это нельзя объяснить.", "This cannot be explained. (impossibility)")
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun PersonSectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun PersonCard(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}

@Composable
private fun PersonExampleRow(russian: String, english: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(russian, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
        Text(english, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
    Spacer(modifier = Modifier.height(2.dp))
}
