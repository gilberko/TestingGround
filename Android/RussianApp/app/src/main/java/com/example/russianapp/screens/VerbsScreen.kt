package com.example.russianapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

// ── Data ─────────────────────────────────────────────────────────────────────

private data class VerbEntry(
    val english: String,
    val imperfective: String,
    val perfective: String   // "—" when no true perfective pair exists
)

private val commonVerbs = listOf(
    VerbEntry("to be",                    "быть",       "—"),
    VerbEntry("to speak / talk",          "говорить",   "поговорить"),
    VerbEntry("to say",                   "говорить",   "сказать"),
    VerbEntry("to do / make",             "делать",     "сделать"),
    VerbEntry("to give",                  "давать",     "дать"),
    VerbEntry("to take",                  "брать",      "взять"),
    VerbEntry("to go (on foot, unidirectional)", "идти", "пойти"),
    VerbEntry("to go (on foot, habitual)", "ходить",   "—"),
    VerbEntry("to go (by vehicle, unidirectional)", "ехать", "поехать"),
    VerbEntry("to go (by vehicle, habitual)", "ездить", "—"),
    VerbEntry("to listen",                "слушать",    "послушать"),
    VerbEntry("to hear",                  "слышать",    "услышать"),
    VerbEntry("to see",                   "видеть",     "увидеть"),
    VerbEntry("to look / watch",          "смотреть",   "посмотреть"),
    VerbEntry("to read",                  "читать",     "прочитать"),
    VerbEntry("to write",                 "писать",     "написать"),
    VerbEntry("to think",                 "думать",     "подумать"),
    VerbEntry("to know",                  "знать",      "узнать"),
    VerbEntry("to understand",            "понимать",   "понять"),
    VerbEntry("to love / like",           "любить",     "полюбить"),
    VerbEntry("to want",                  "хотеть",     "захотеть"),
    VerbEntry("to be able to / can",      "мочь",       "смочь"),
    VerbEntry("to live",                  "жить",       "прожить"),
    VerbEntry("to work",                  "работать",   "поработать"),
    VerbEntry("to eat",                   "есть",       "съесть"),
    VerbEntry("to drink",                 "пить",       "выпить"),
    VerbEntry("to sleep",                 "спать",      "поспать"),
    VerbEntry("to open",                  "открывать",  "открыть"),
    VerbEntry("to close",                 "закрывать",  "закрыть"),
    VerbEntry("to buy",                   "покупать",   "купить"),
    VerbEntry("to sell",                  "продавать",  "продать"),
    VerbEntry("to help",                  "помогать",   "помочь"),
    VerbEntry("to ask (a question)",      "спрашивать", "спросить"),
    VerbEntry("to answer",                "отвечать",   "ответить"),
    VerbEntry("to begin / start",         "начинать",   "начать"),
    VerbEntry("to finish / end",          "заканчивать","закончить"),
    VerbEntry("to arrive (on foot)",      "приходить",  "прийти"),
    VerbEntry("to leave (on foot)",       "уходить",    "уйти"),
    VerbEntry("to arrive (by vehicle)",   "приезжать",  "приехать"),
    VerbEntry("to leave (by vehicle)",    "уезжать",    "уехать"),
    VerbEntry("to search / look for",     "искать",     "найти"),
    VerbEntry("to find",                  "находить",   "найти"),
    VerbEntry("to lose",                  "терять",     "потерять"),
    VerbEntry("to meet",                  "встречать",  "встретить"),
    VerbEntry("to wait",                  "ждать",      "подождать"),
    VerbEntry("to receive / get",         "получать",   "получить"),
    VerbEntry("to pay",                   "платить",    "заплатить"),
    VerbEntry("to learn / study",         "учить",      "выучить"),
    VerbEntry("to forget",                "забывать",   "забыть"),
    VerbEntry("to remember / memorize",   "запоминать", "запомнить")
)

private data class MotionVerbEntry(
    val pronoun: String,
    val idti: String,    // идти — unidirectional (right now, one trip)
    val khodit: String   // ходить — multidirectional / habitual
)

private val motionFootRows = listOf(
    MotionVerbEntry("я",      "иду",   "хожу"),
    MotionVerbEntry("ты",     "идёшь", "ходишь"),
    MotionVerbEntry("он/она", "идёт",  "ходит"),
    MotionVerbEntry("мы",     "идём",  "ходим"),
    MotionVerbEntry("вы",     "идёте", "ходите"),
    MotionVerbEntry("они",    "идут",  "ходят")
)

private data class MotionVehicleEntry(
    val pronoun: String,
    val ekhat: String,   // ехать — unidirectional
    val ezdit: String    // ездить — habitual
)

private val motionVehicleRows = listOf(
    MotionVehicleEntry("я",      "еду",    "езжу"),
    MotionVehicleEntry("ты",     "едешь",  "ездишь"),
    MotionVehicleEntry("он/она", "едет",   "ездит"),
    MotionVehicleEntry("мы",     "едем",   "ездим"),
    MotionVehicleEntry("вы",     "едете",  "ездите"),
    MotionVehicleEntry("они",    "едут",   "ездят")
)

// ── Composables ───────────────────────────────────────────────────────────────

@Composable
private fun SectionHeader(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        HorizontalDivider(
            modifier = Modifier.padding(top = 4.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun AspectExplanationCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Almost every Russian verb belongs to one of two aspects:",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Imperfective (несовершенный вид)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Describes ongoing, repeated, or habitual actions — the process itself, not the result. Used for all three tenses.",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "• Я читаю книгу. (I am reading a book — right now.)",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 2.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Perfective (совершенный вид)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Describes a completed action with a definite result. Has no present tense — only past and future.",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "• Я прочитал книгу. (I read / finished reading the book — it's done.)",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 2.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Verbs are listed below as: Imperfective / Perfective. \"—\" means no standard perfective pair exists.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun VerbTableHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        Text(
            text = "English",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(2f)
        )
        Text(
            text = "Imperfective",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.weight(1.5f)
        )
        Text(
            text = "Perfective",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.weight(1.5f)
        )
    }
}

@Composable
private fun VerbRow(verb: VerbEntry, isEven: Boolean) {
    val bgColor = if (isEven)
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
    else
        MaterialTheme.colorScheme.surface
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 1.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 5.dp)
        ) {
            Text(
                text = verb.english,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(2f)
            )
            Text(
                text = verb.imperfective,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1.5f)
            )
            Text(
                text = verb.perfective,
                style = MaterialTheme.typography.bodySmall,
                color = if (verb.perfective == "—")
                    MaterialTheme.colorScheme.onSurfaceVariant
                else
                    MaterialTheme.colorScheme.secondary,
                modifier = Modifier.weight(1.5f)
            )
        }
    }
}

@Composable
private fun MotionVerbsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Russian has paired motion verbs that distinguish whether movement is in one specific direction (right now, a single trip) or habitual/repeated/back-and-forth.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(12.dp))

            // On foot
            Text(
                text = "On foot: идти vs ходить",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "идти — going somewhere right now, in one direction\nходить — going regularly, habitually, or back and forth",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "• Я иду в магазин. (I am going to the shop — right now, one trip.)",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = "• Я хожу в магазин каждый день. (I go to the shop every day.)",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Conjugation table for foot motion
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("",          modifier = Modifier.weight(1f), style = MaterialTheme.typography.labelSmall)
                Text("идти",     modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text("ходить",   modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            }
            motionFootRows.forEach { row ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp)) {
                    Text(row.pronoun,  modifier = Modifier.weight(1f),   style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(row.idti,     modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.bodySmall)
                    Text(row.khodit,   modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.bodySmall)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(12.dp))

            // By vehicle
            Text(
                text = "By vehicle: ехать vs ездить",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "ехать — travelling right now, in one direction\nездить — travelling regularly or back and forth",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "• Я еду в Москву. (I am going to Moscow — right now, by vehicle.)",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = "• Я езжу в Москву каждый месяц. (I go to Moscow every month.)",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Conjugation table for vehicle motion
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("",          modifier = Modifier.weight(1f),   style = MaterialTheme.typography.labelSmall)
                Text("ехать",    modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text("ездить",   modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            }
            motionVehicleRows.forEach { row ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp)) {
                    Text(row.pronoun,  modifier = Modifier.weight(1f),   style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(row.ekhat,    modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.bodySmall)
                    Text(row.ezdit,    modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

// ── Screen ────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerbsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Verbs") },
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
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { SectionHeader("Verb Aspects") }
            item { AspectExplanationCard() }

            item { SectionHeader("50 Common Verbs") }
            item { VerbTableHeader() }
            items(commonVerbs.mapIndexed { i, v -> i to v }) { (index, verb) ->
                VerbRow(verb, isEven = index % 2 == 0)
            }

            item { SectionHeader("Verbs of Motion") }
            item { MotionVerbsCard() }
        }
    }
}
