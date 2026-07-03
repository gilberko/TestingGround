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

private val coreVerbs = listOf(
    VerbEntry("to be",                    "быть",       "—"),
    VerbEntry("to do / make",             "делать",     "сделать"),
    VerbEntry("to give",                  "давать",     "дать"),
    VerbEntry("to take",                  "брать",      "взять"),
    VerbEntry("to have / possess",        "иметь",      "—"),
    VerbEntry("to know",                  "знать",      "узнать"),
    VerbEntry("to understand",            "понимать",   "понять"),
    VerbEntry("to want",                  "хотеть",     "захотеть"),
    VerbEntry("to be able to / can",      "мочь",       "смочь"),
    VerbEntry("to see",                   "видеть",     "увидеть"),
    VerbEntry("to look / watch",          "смотреть",   "посмотреть"),
    VerbEntry("to choose",                "выбирать",   "выбрать"),
    VerbEntry("to try",                   "пробовать",  "попробовать"),
    VerbEntry("to open",                  "открывать",  "открыть"),
    VerbEntry("to close",                 "закрывать",  "закрыть"),
    VerbEntry("to turn on",               "включать",   "включить"),
    VerbEntry("to turn off",              "выключать",  "выключить"),
    VerbEntry("to begin / start",         "начинать",   "начать"),
    VerbEntry("to finish / end",          "заканчивать","закончить"),
    VerbEntry("to change",                "менять",     "изменить"),
    VerbEntry("to search / look for",     "искать",     "найти"),
    VerbEntry("to find",                  "находить",   "найти"),
    VerbEntry("to lose",                  "терять",     "потерять"),
    VerbEntry("to forget",                "забывать",   "забыть"),
    VerbEntry("to remember / memorize",   "запоминать", "запомнить"),
    VerbEntry("to receive / get",         "получать",   "получить"),
    VerbEntry("to think",                 "думать",     "подумать"),
    VerbEntry("to live",                  "жить",       "прожить"),
    VerbEntry("to own",                   "владеть",        "—"),
    VerbEntry("to replace",               "заменять",       "заменить"),
    VerbEntry("to use",                   "пользоваться",   "воспользоваться"),
    VerbEntry("to examine",               "осматривать",    "осмотреть"),
    VerbEntry("to investigate",           "разбираться",    "разобраться")
)

private val emotionVerbs = listOf(
    VerbEntry("to love / like",           "любить",         "полюбить"),
    VerbEntry("to hope",                  "надеяться",      "понадеяться"),
    VerbEntry("to dream",                 "мечтать",        "помечтать"),
    VerbEntry("to believe",               "верить",         "поверить"),
    VerbEntry("to laugh",                 "смеяться",       "засмеяться"),
    VerbEntry("to cry",                   "плакать",        "заплакать"),
    VerbEntry("to fear",                  "бояться",        "испугаться"),
    VerbEntry("to relax",                 "расслабляться",  "расслабиться")
)

private val eatingVerbs = listOf(
    VerbEntry("to eat",                   "есть",       "съесть"),
    VerbEntry("to drink",                 "пить",       "выпить"),
    VerbEntry("to have breakfast",        "завтракать", "позавтракать"),
    VerbEntry("to have lunch",            "обедать",    "пообедать"),
    VerbEntry("to have dinner",           "ужинать",    "поужинать"),
    VerbEntry("to prepare / cook",        "готовить",   "приготовить")
)

private val socialVerbs = listOf(
    VerbEntry("to speak / talk",          "говорить",   "поговорить"),
    VerbEntry("to say",                   "говорить",   "сказать"),
    VerbEntry("to listen",                "слушать",    "послушать"),
    VerbEntry("to hear",                  "слышать",    "услышать"),
    VerbEntry("to read",                  "читать",     "прочитать"),
    VerbEntry("to write",                 "писать",     "написать"),
    VerbEntry("to ask (a question)",      "спрашивать", "спросить"),
    VerbEntry("to answer",                "отвечать",   "ответить"),
    VerbEntry("to meet",                  "встречать",  "встретить"),
    VerbEntry("to help",                  "помогать",   "помочь"),
    VerbEntry("to wait",                  "ждать",      "подождать"),
    VerbEntry("to explain",               "объяснять",  "объяснить"),
    VerbEntry("to discuss",               "обсуждать",  "обсудить"),
    VerbEntry("to ask (for something)",   "просить",    "попросить"),
    VerbEntry("to call (on the phone)",   "звонить",    "позвонить"),
    VerbEntry("to approve",               "одобрять",   "одобрить"),
    VerbEntry("to deny",                  "отклонять",  "отклонить"),
    VerbEntry("to allow",                 "разрешать",  "разрешить"),
    VerbEntry("to arrange",               "устраивать", "устроить")
)

private val movementVerbs = listOf(
    VerbEntry("to go (on foot, unidirectional)",     "идти",    "пойти"),
    VerbEntry("to go (on foot, habitual)",            "ходить",  "—"),
    VerbEntry("to go (by vehicle, unidirectional)",   "ехать",   "поехать"),
    VerbEntry("to go (by vehicle, habitual)",         "ездить",  "—"),
    VerbEntry("to run",                   "бегать",         "пробежать"),
    VerbEntry("to hike",                  "ходить в поход", "—"),
    VerbEntry("to arrive (on foot)",      "приходить",      "прийти"),
    VerbEntry("to leave (on foot)",       "уходить",        "уйти"),
    VerbEntry("to arrive (by vehicle)",   "приезжать",      "приехать"),
    VerbEntry("to leave (by vehicle)",    "уезжать",        "уехать"),
    VerbEntry("to drive (a vehicle)",     "водить (машину)", "—"),
    VerbEntry("to park",                  "парковать",      "припарковать"),
    VerbEntry("to navigate",              "ориентироваться","сориентироваться"),
    VerbEntry("to bring",                 "приносить",      "принести"),
    VerbEntry("to move (something)",      "двигать",        "двинуть")
)

private val houseVerbs = listOf(
    VerbEntry("to sleep",                 "спать",       "поспать"),
    VerbEntry("to wake up",               "просыпаться", "проснуться"),
    VerbEntry("to get dressed",           "одеваться",   "одеться"),
    VerbEntry("to fix",                   "чинить",      "починить"),
    VerbEntry("to wash",                  "мыть",        "помыть"),
    VerbEntry("to clean",                 "убирать",     "убрать"),
    VerbEntry("to hang (a picture)",      "вешать",      "повесить"),
    VerbEntry("to pour",                  "наливать",    "налить"),
    VerbEntry("to rest",                  "отдыхать",    "отдохнуть")
)

private val workStudyMoneyVerbs = listOf(
    VerbEntry("to work",                  "работать",       "поработать"),
    VerbEntry("to learn / memorize",      "учить",          "выучить"),
    VerbEntry("to buy",                   "покупать",       "купить"),
    VerbEntry("to sell",                  "продавать",      "продать"),
    VerbEntry("to pay",                   "платить",        "заплатить"),
    VerbEntry("to study (attend school)", "учиться",        "поучиться"),
    VerbEntry("to teach (someone)",       "учить",          "научить"),
    VerbEntry("to practice",              "тренироваться",  "потренироваться"),
    VerbEntry("to manage / cope",         "справляться",    "справиться"),
    VerbEntry("to schedule / plan",       "планировать",    "запланировать"),
    VerbEntry("to monitor",               "следить",        "проследить"),
    VerbEntry("to earn",                  "зарабатывать",   "заработать"),
    VerbEntry("to spend (time)",          "проводить",      "провести"),
    VerbEntry("to waste (money)",         "тратить",        "потратить"),
    VerbEntry("to rent",                  "снимать",        "снять"),
    VerbEntry("to loan (lend)",           "одалживать",     "одолжить"),
    VerbEntry("to borrow",                "занимать",       "занять"),
    VerbEntry("to return (something)",    "возвращать",     "вернуть"),
    VerbEntry("to owe",                   "быть должным",   "задолжать")
)

private val hobbyVerbs = listOf(
    VerbEntry("to paint / draw",          "рисовать",   "нарисовать"),
    VerbEntry("to dance",                 "танцевать",  "потанцевать"),
    VerbEntry("to sing",                  "петь",       "спеть"),
    VerbEntry("to act",                   "поступать",  "поступить")
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

private data class WaterAirRow(val pronoun: String, val plyt: String, val plavat: String, val letet: String, val letat: String)
private val waterAirRows = listOf(
    WaterAirRow("я",      "плыву",   "плаваю",   "лечу",   "летаю"),
    WaterAirRow("ты",     "плывёшь", "плаваешь", "летишь", "летаешь"),
    WaterAirRow("он/она", "плывёт",  "плавает",  "летит",  "летает"),
    WaterAirRow("мы",     "плывём",  "плаваем",  "летим",  "летаем"),
    WaterAirRow("вы",     "плывёте", "плаваете", "летите", "летаете"),
    WaterAirRow("они",    "плывут",  "плавают",  "летят",  "летают")
)

private data class MotionPrefixRow(val prefix: String, val meaning: String, val foot: String, val vehicle: String, val air: String, val water: String)
private val motionPrefixRows = listOf(
    MotionPrefixRow("по-",    "set off / depart",    "пойти",   "поехать",   "полететь",   "поплыть"),
    MotionPrefixRow("при-",   "arrive",               "прийти",  "приехать",  "прилететь",  "приплыть"),
    MotionPrefixRow("у-",     "leave / depart",       "уйти",    "уехать",    "улететь",    "уплыть"),
    MotionPrefixRow("в-/во-", "enter",                "войти",   "въехать",   "влететь",    "вплыть"),
    MotionPrefixRow("вы-",    "exit / go out",        "выйти",   "выехать",   "вылететь",   "выплыть"),
    MotionPrefixRow("за-",    "stop by / go beyond",  "зайти",   "заехать",   "залететь",   "заплыть"),
    MotionPrefixRow("от-",    "move away from",       "отойти",  "отъехать",  "отлететь",   "отплыть"),
    MotionPrefixRow("до-",    "reach destination",    "дойти",   "доехать",   "долететь",   "доплыть"),
    MotionPrefixRow("пере-",  "cross / transfer",     "перейти", "переехать", "перелететь", "переплыть"),
    MotionPrefixRow("про-",   "pass through / by",    "пройти",  "проехать",  "пролететь",  "проплыть"),
    MotionPrefixRow("под-",   "approach",             "подойти", "подъехать", "подлететь",  "подплыть")
)

private data class GeneralPrefixEntry(val prefix: String, val meaning: String, val examples: List<String>)
private val generalPrefixEntries = listOf(
    GeneralPrefixEntry("в- / вы-", "in/out, on/off",
        listOf(
            "включить (turn on) / выключить (turn off)",
            "войти (enter) / выйти (exit)",
            "вписать (write in) / выписать (write out)"
        )),
    GeneralPrefixEntry("от- / за-", "open/close, completion/start",
        listOf(
            "открыть (to open) / закрыть (to close)",
            "ответить (to answer) / заплатить (to pay)",
            "отключить (to disconnect) / записать (to record)"
        )),
    GeneralPrefixEntry("на-", "filling, start of action",
        listOf(
            "написать (to write — perfective)",
            "налить (to pour in)",
            "набрать (to gather / dial a number)"
        )),
    GeneralPrefixEntry("пере-", "redo / re- / across",
        listOf(
            "перечитать (to reread)",
            "переписать (to rewrite)",
            "перевести (to translate / transfer)"
        )),
    GeneralPrefixEntry("про-", "through / completely",
        listOf(
            "прочитать (to read through — perfective)",
            "просмотреть (to look through / review)"
        )),
    GeneralPrefixEntry("раз- / рас-", "apart / intensification",
        listOf(
            "разобрать (to take apart / analyze)",
            "расписать (to elaborate in writing)",
            "разговорить (to get someone talking)"
        ))
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

@Composable
private fun WaterAirMotionCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "By water / by air",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "The same verb pair плыть / плавать covers both swimming and sailing — context makes the meaning clear. " +
                        "лететь / летать follows the same unidirectional vs. habitual pattern for flying.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "• Я плыву к берегу. (I am swimming/sailing to shore — right now.)",
                style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic
            )
            Text(
                text = "• Он плавает каждое утро. (He swims every morning.)",
                style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic
            )
            Text(
                text = "• Самолёт летит в Москву. (The plane is flying to Moscow — right now.)",
                style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Table header
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("",         modifier = Modifier.weight(0.8f), style = MaterialTheme.typography.labelSmall)
                Text("плыть",   modifier = Modifier.weight(1.1f), style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text("плавать", modifier = Modifier.weight(1.1f), style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text("лететь",  modifier = Modifier.weight(1.1f), style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text("летать",  modifier = Modifier.weight(1.1f), style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            }
            waterAirRows.forEach { row ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp)) {
                    Text(row.pronoun, modifier = Modifier.weight(0.8f), style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(row.plyt,   modifier = Modifier.weight(1.1f), style = MaterialTheme.typography.bodySmall)
                    Text(row.plavat, modifier = Modifier.weight(1.1f), style = MaterialTheme.typography.bodySmall)
                    Text(row.letet,  modifier = Modifier.weight(1.1f), style = MaterialTheme.typography.bodySmall)
                    Text(row.letat,  modifier = Modifier.weight(1.1f), style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
private fun MotionPrefixesCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Prefixes attach to motion verbs to create perfective verbs with a specific directional meaning. " +
                        "The same prefix applies to all motion verb pairs (foot, vehicle, air, water).",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Table header
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("Prefix",   modifier = Modifier.weight(0.9f), style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text("Meaning",  modifier = Modifier.weight(1.3f), style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text("Foot",     modifier = Modifier.weight(1f),   style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text("Vehicle",  modifier = Modifier.weight(1f),   style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text("Air",      modifier = Modifier.weight(1f),   style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text("Water",    modifier = Modifier.weight(1f),   style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
            motionPrefixRows.forEachIndexed { i, row ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)
                ) {
                    Text(row.prefix,  modifier = Modifier.weight(0.9f), style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)
                    Text(row.meaning, modifier = Modifier.weight(1.3f), style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(row.foot,    modifier = Modifier.weight(1f),   style = MaterialTheme.typography.bodySmall)
                    Text(row.vehicle, modifier = Modifier.weight(1f),   style = MaterialTheme.typography.bodySmall)
                    Text(row.air,     modifier = Modifier.weight(1f),   style = MaterialTheme.typography.bodySmall)
                    Text(row.water,   modifier = Modifier.weight(1f),   style = MaterialTheme.typography.bodySmall)
                }
                if (i < motionPrefixRows.lastIndex) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))
                }
            }
        }
    }
}

@Composable
private fun GeneralPrefixesCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Many Russian prefixes consistently change verb meaning across different roots. " +
                        "They often create aspectual (perfective) pairs and alter the direction or scope of the action.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(10.dp))
            generalPrefixEntries.forEachIndexed { i, entry ->
                if (i > 0) Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "${entry.prefix}  —  ${entry.meaning}",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(2.dp))
                entry.examples.forEach { example ->
                    Text(
                        text = "• $example",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 8.dp, bottom = 1.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Note: prefix meaning depends on the root verb. For example, вы- means \"off\" with включить/выключить " +
                        "but \"exit\" with войти/выйти.",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
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

            item { SectionHeader("Core Verbs") }
            item { VerbTableHeader() }
            items(coreVerbs.mapIndexed { i, v -> i to v }) { (index, verb) ->
                VerbRow(verb, isEven = index % 2 == 0)
            }

            item { SectionHeader("Emotions & Feelings") }
            item { VerbTableHeader() }
            items(emotionVerbs.mapIndexed { i, v -> i to v }) { (index, verb) ->
                VerbRow(verb, isEven = index % 2 == 0)
            }

            item { SectionHeader("Eating & Drinking") }
            item { VerbTableHeader() }
            items(eatingVerbs.mapIndexed { i, v -> i to v }) { (index, verb) ->
                VerbRow(verb, isEven = index % 2 == 0)
            }

            item { SectionHeader("Social & Communication") }
            item { VerbTableHeader() }
            items(socialVerbs.mapIndexed { i, v -> i to v }) { (index, verb) ->
                VerbRow(verb, isEven = index % 2 == 0)
            }

            item { SectionHeader("Movement & Travel") }
            item { VerbTableHeader() }
            items(movementVerbs.mapIndexed { i, v -> i to v }) { (index, verb) ->
                VerbRow(verb, isEven = index % 2 == 0)
            }
            item {
                Text(
                    text = "Note: водить (машину) has no natural perfective — same dash convention as ходить/ездить.",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            item { SectionHeader("Around The House") }
            item { VerbTableHeader() }
            items(houseVerbs.mapIndexed { i, v -> i to v }) { (index, verb) ->
                VerbRow(verb, isEven = index % 2 == 0)
            }

            item { SectionHeader("Work, Study & Money") }
            item { VerbTableHeader() }
            items(workStudyMoneyVerbs.mapIndexed { i, v -> i to v }) { (index, verb) ->
                VerbRow(verb, isEven = index % 2 == 0)
            }
            item {
                Text(
                    text = "Note: учить means \"to learn / memorize\" (→ выучить) when its object is a subject, " +
                            "but \"to teach someone\" (→ научить) when its object is a person.",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            item { SectionHeader("Hobbies & Activities") }
            item { VerbTableHeader() }
            items(hobbyVerbs.mapIndexed { i, v -> i to v }) { (index, verb) ->
                VerbRow(verb, isEven = index % 2 == 0)
            }

            item { SectionHeader("Verbs of Motion") }
            item { MotionVerbsCard() }
            item { WaterAirMotionCard() }

            item { SectionHeader("Motion Verb Prefixes") }
            item { MotionPrefixesCard() }

            item { SectionHeader("Verb Prefixes (General)") }
            item { GeneralPrefixesCard() }
        }
    }
}
