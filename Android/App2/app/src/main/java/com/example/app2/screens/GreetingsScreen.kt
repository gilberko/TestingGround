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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Basics and Greetings") },
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
            item { BasicWordsCard() }
            item { EssentialPhrasesCard() }
            item { AskingForClarificationCard() }
            item { MakingSuggestionsCard() }
            item { GreetingsCard() }
        }
    }
}

@Composable
private fun GreetingsCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // ── Time-of-day greetings ─────────────────────────────────────
            Text("Time-of-day Greetings", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            GreetingEntry(
                term = "Bom dia",
                meaning = "Good morning",
                description = "Used from when you wake up until roughly noon. Literally \"good day\".",
                examples = listOf(
                    "Bom dia! Como está?" to "Good morning! How are you?",
                    "Bom dia a todos." to "Good morning, everyone."
                )
            )
            GreetingEntry(
                term = "Boa tarde",
                meaning = "Good afternoon / Good day",
                description = "Used from noon until around 8 pm, when it starts getting dark.",
                examples = listOf(
                    "Boa tarde, posso ajudar?" to "Good afternoon, can I help?",
                    "Boa tarde, tudo bem?" to "Good afternoon, everything okay?"
                )
            )
            GreetingEntry(
                term = "Boa noite",
                meaning = "Good evening / Good night",
                description = "Used from roughly 8 pm onwards — both as a greeting when arriving AND as a farewell when leaving at night. Unlike English, one phrase covers both.",
                examples = listOf(
                    "Boa noite! Bem-vindo." to "Good evening! Welcome.",
                    "Boa noite, até amanhã." to "Good night, see you tomorrow."
                )
            )
            GreetingEntry(
                term = "Boas",
                meaning = "Hi / Hey (informal)",
                description = "Casual shortening of any of the above — used at any time of day among friends. Very colloquial; avoid in formal settings.",
                examples = listOf(
                    "Boas! Tudo bem?" to "Hey! All good?",
                    "Boas, pessoal!" to "Hey, guys!"
                )
            )
            GreetingEntry(
                term = "Olá",
                meaning = "Hello",
                description = "Neutral, works at any time of day in both casual and moderately formal situations.",
                examples = listOf(
                    "Olá, como te chamas?" to "Hello, what's your name?",
                    "Olá! Entra, entra." to "Hello! Come in, come in."
                )
            )
            GreetingEntry(
                term = "Olá, pessoal! / Boas, pessoal!",
                meaning = "Hi, everyone! / Hey, guys!",
                description = "Pessoal literally means \"people / staff\" as a collective noun, but in greetings it's used informally to address a group — like saying \"folks\", \"guys\", or \"everyone\" in English. Very common when walking into a room with several people, starting a video, or posting in a group chat.",
                examples = listOf(
                    "Olá, pessoal! Como estão todos?" to "Hi, everyone! How is everyone?",
                    "Boas, pessoal! Já chegámos." to "Hey, guys! We've arrived."
                ),
                note = "Pessoal itself is grammatically singular (like English \"everyone\"), but it's only ever used to address a group — you wouldn't say \"olá, pessoal\" to a single person, you'd just say \"olá\"."
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            // ── Farewells & Thanks ────────────────────────────────────────
            Text("Farewells & Thanks", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            GreetingEntry(
                term = "Obrigado / Obrigada",
                meaning = "Thank you",
                description = "The speaker agrees in gender with themselves — a male speaker says obrigado, a female speaker says obrigada. It is not about the listener.",
                examples = listOf(
                    "Obrigado pela ajuda!" to "Thank you for the help! (male speaking)",
                    "Obrigada, foi muito gentil." to "Thank you, that was very kind. (female speaking)"
                )
            )
            GreetingEntry(
                term = "Tchau",
                meaning = "Bye (informal)",
                description = "Borrowed from Italian ciao. Very common in everyday speech.",
                examples = listOf(
                    "Tchau! Até logo." to "Bye! See you soon.",
                    "Tchau, cuida-te." to "Bye, take care."
                )
            )
            GreetingEntry(
                term = "Até logo",
                meaning = "See you soon / Bye for now",
                description = "Implies you expect to see the person again fairly soon — often later that day. Logo means \"soon\".",
                examples = listOf(
                    "Vou ao supermercado, até logo." to "I'm going to the supermarket, see you soon.",
                    "Até logo! Boa sorte." to "Bye for now! Good luck."
                )
            )
            GreetingEntry(
                term = "Até à próxima",
                meaning = "Until next time / See you next time",
                description = "No specific timeframe — just \"next time we happen to meet\". More open-ended than até logo; use it when you don't know when you'll see them again.",
                examples = listOf(
                    "Foi um prazer. Até à próxima!" to "It was a pleasure. Until next time!",
                    "Até à próxima visita." to "Until the next visit."
                ),
                note = "Difference: até logo = see you soon (probably today); até à próxima = next time we meet (no specific date)."
            )
            GreetingEntry(
                term = "Até já",
                meaning = "See you in a moment / Be right back",
                description = "Even shorter-term than até logo — implies you'll be back within minutes.",
                examples = listOf(
                    "Vou à casa de banho, até já." to "I'm going to the bathroom, back in a moment.",
                    "Até já!" to "See you in a sec!"
                )
            )
            GreetingEntry(
                term = "Até amanhã à noite / Até à noite / Até segunda / Até para a semana",
                meaning = "See you tomorrow evening / See you tonight / See you Monday / See you next week",
                description = "Até (\"until\") combines productively with almost any time expression to make a farewell — até + [when]. \"Amanhã à noite\" = tomorrow evening/night; \"à noite\" alone = tonight/this evening; weekday names (segunda, terça...) work directly; \"para a semana\" is the everyday EP way of saying \"next week\" (also seen in \"para o ano\" = next year).",
                examples = listOf(
                    "Até amanhã à noite!" to "See you tomorrow evening!",
                    "Tenho de ir, até à noite." to "I have to go, see you tonight.",
                    "Até segunda!" to "See you Monday!",
                    "Até para a semana!" to "See you next week!"
                ),
                note = "\"Até à próxima semana\" is also correct and a little more formal/neutral than \"até para a semana\", which is the everyday colloquial choice."
            )
            GreetingEntry(
                term = "Vejo-te quando voltares. / Liga-me quando voltares.",
                meaning = "See you when you get back. / Call me when you get back.",
                description = "Quando (\"when\") referring to a future, not-yet-certain event triggers the future subjunctive — voltares, not the indicative voltas. This is a key EP grammar pattern: any future quando/se clause about something not yet realized uses this subjunctive form. Ligar is the standard EP verb for \"to call\" on the phone.",
                examples = listOf(
                    "Vejo-te quando voltares." to "I'll see you when you get back.",
                    "Liga-me quando voltares." to "Call me when you get back.",
                    "Ligo-te quando chegar." to "I'll call you when I arrive."
                ),
                note = "Avoid \"até quando voltares\" as a farewell — até quando on its own usually reads as a question (\"until when?\"). \"Vejo-te quando...\" sidesteps that ambiguity."
            )
            GreetingEntry(
                term = "Está a ficar tarde, tenho de ir.",
                meaning = "It's getting late, I have to go.",
                description = "Está a ficar tarde uses the EP continuous (estar a + infinitive), not the Brazilian gerund (ficando). Tenho de + infinitive is the prescriptively standard way to say \"I have to\"; tenho que is also extremely common in everyday speech.",
                examples = listOf(
                    "Está a ficar tarde, tenho de ir." to "It's getting late, I have to go.",
                    "Tenho mesmo de ir, já é tarde." to "I really have to go, it's already late."
                )
            )
        }
    }
}

@Composable
private fun BasicWordsCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Basic Words", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            GreetingEntry(
                term = "Sim",
                meaning = "Yes",
                description = "Standard affirmative. Stronger agreement: \"Claro\" (of course), \"Com certeza\" (certainly), \"Claro que sim\" (of course yes). Casual/colloquial: \"Pois\" — a very EP word meaning \"yeah\" or \"right\", used as a filler or to show you're listening.",
                examples = listOf(
                    "Sim, está certo." to "Yes, that's right.",
                    "Claro que sim, com prazer." to "Of course, with pleasure.",
                    "Pois, tens razão." to "Yeah, you're right. (casual EP)"
                )
            )
            GreetingEntry(
                term = "Pois",
                meaning = "yeah / right / well... (filler, agreement, or \"because\")",
                description = "Pois is one of the most distinctively Portuguese words, and it shifts meaning with context. As a casual filler it means \"yeah\" or \"right\" (see Sim above). \"Pois é\" intensifies this into \"that's right / that's how it is\", often with a touch of resignation. \"Pois claro\" means \"of course\". In more formal or written Portuguese, pois can also act as a conjunction meaning \"because / since\".",
                examples = listOf(
                    "Pois é, não há nada a fazer." to "That's right, there's nothing to be done.",
                    "Pois claro que sim!" to "Of course!",
                    "Não saí, pois estava a chover." to "I didn't go out, because it was raining."
                ),
                note = "In shops or restaurants, staff often greet you with \"Pois não?\" to mean \"Can I help you?\" — despite containing não (no), it's a polite, affirmative-sounding phrase, not a negative one."
            )
            GreetingEntry(
                term = "Não",
                meaning = "No",
                description = "Standard negative. Also used as a tag-question particle at the end of sentences.",
                examples = listOf(
                    "Não, obrigado/obrigada." to "No, thank you. (male/female speaker)",
                    "É bonito, não é?" to "It's beautiful, isn't it?",
                    "Não percebo." to "I don't understand."
                )
            )
            GreetingEntry(
                term = "E",
                meaning = "And",
                description = "Conjunction. No gender or number agreement. Pronounced like English \"ee\". Unlike Spanish (y → e), Portuguese \"e\" does not formally change before vowels.",
                examples = listOf(
                    "Café e pastel de nata." to "Coffee and custard tart.",
                    "Tu e eu." to "You and I."
                )
            )
            GreetingEntry(
                term = "Ou",
                meaning = "Or",
                description = "Conjunction. \"Ou...ou...\" = either...or...",
                examples = listOf(
                    "Café ou chá?" to "Coffee or tea?",
                    "Ou vais tu, ou fico eu." to "Either you go, or I stay."
                )
            )
        }
    }
}

@Composable
private fun EssentialPhrasesCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Essential Phrases", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            GreetingEntry(
                term = "Por favor / Se faz favor",
                meaning = "Please",
                description = "\"Por favor\" is the standard form and widely understood. \"Se faz favor\" is very EP-specific — commonly used to call a waiter's attention or request something politely. It literally means \"if you please\" and sounds more natural to locals than \"por favor\" in many situations.",
                examples = listOf(
                    "Uma bica, se faz favor." to "An espresso, please. (EP, calling the waiter)",
                    "Por favor, pode repetir?" to "Please, can you repeat?",
                    "Se faz favor!" to "Excuse me! / Over here, please! (to get attention)"
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            GreetingEntry(
                term = "Olá / Boas",
                meaning = "Hi / Hello",
                description = "\"Olá\" is neutral and works in both casual and moderately formal settings. \"Boas\" is a casual colloquial shortening used among friends at any time of day. See the Time-of-day Greetings section below for bom dia / boa tarde / boa noite.",
                examples = listOf(
                    "Olá, tudo bem?" to "Hi, everything okay?",
                    "Boas, pessoal!" to "Hey, guys! (casual)"
                )
            )
            GreetingEntry(
                term = "Tchau / Adeus",
                meaning = "Bye / Goodbye",
                description = "\"Tchau\" is the everyday casual goodbye (borrowed from Italian ciao). \"Adeus\" is more formal and carries a sense of finality — used when you don't expect to see the person again soon, or in formal/written contexts.",
                examples = listOf(
                    "Tchau, até amanhã!" to "Bye, see you tomorrow!",
                    "Adeus e boa sorte." to "Goodbye and good luck. (more final)"
                ),
                note = "Difference: tchau = casual everyday bye; adeus = formal or final farewell."
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            GreetingEntry(
                term = "Com licença",
                meaning = "Excuse me (to get past)",
                description = "Used when physically moving past someone, leaving a table, or entering a space. Purely practical — no apology implied, just asking for room.",
                examples = listOf(
                    "Com licença, posso passar?" to "Excuse me, may I get through?",
                    "Com licença." to "Excuse me. (stepping past someone on a bus)"
                )
            )
            GreetingEntry(
                term = "Desculpe / Desculpa",
                meaning = "Excuse me / Sorry",
                description = "\"Desculpe\" is formal (você form); \"Desculpa\" is informal (tu form). Used after bumping into someone, making a small mistake, or getting someone's attention to apologize.",
                examples = listOf(
                    "Desculpe, não vi." to "Sorry, I didn't see. (formal)",
                    "Desculpa, podes repetir?" to "Sorry, can you repeat? (informal/tu)",
                    "Desculpe a confusão." to "Sorry for the confusion. (formal)"
                ),
                note = "Difference from com licença: desculpe/desculpa implies an apology; com licença is just a practical request for space."
            )
            GreetingEntry(
                term = "Lamento",
                meaning = "I'm sorry (deeper regret)",
                description = "Used when expressing deeper sympathy or regret — hearing bad news, condolences, or a more sincere apology. More formal and emotionally weightier than desculpe.",
                examples = listOf(
                    "Lamento muito." to "I'm very sorry.",
                    "Lamento ouvir isso." to "I'm sorry to hear that."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            GreetingEntry(
                term = "Como está? / Como estás? / Tudo bem?",
                meaning = "How are you?",
                description = "\"Como está?\" is formal (você / o senhor / a senhora). \"Como estás?\" is informal (tu). \"Tudo bem?\" is very casual — literally \"everything good?\" — the most common everyday equivalent.",
                examples = listOf(
                    "Bom dia, como está?" to "Good morning, how are you? (formal)",
                    "Olá! Como estás?" to "Hi! How are you? (informal/tu)",
                    "Tudo bem?" to "All good? / How's it going? (casual)"
                ),
                note = "\"Tudo bom?\" is a variant you'll also hear, though slightly more Brazilian. In EP, \"tudo bem?\" is more common."
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            GreetingEntry(
                term = "Muito prazer / Prazer em conhecê-lo/a",
                meaning = "Nice to meet you",
                description = "\"Muito prazer\" (literally \"much pleasure\") is the standard gender-neutral phrase — the safest option. For formal introductions, \"Prazer em conhecê-lo\" (meeting a man) or \"Prazer em conhecê-la\" (meeting a woman) adds politeness. \"Prazer!\" alone is casual.",
                examples = listOf(
                    "Muito prazer, chamo-me Ana." to "Nice to meet you, my name is Ana.",
                    "Prazer em conhecê-lo, senhor Ferreira." to "Pleased to meet you, Mr. Ferreira. (formal, meeting a man)",
                    "Prazer em conhecê-la." to "Pleased to meet you. (meeting a woman)",
                    "Prazer!" to "Nice to meet you! (casual)"
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            GreetingEntry(
                term = "De onde é? / De onde és?",
                meaning = "Where are you from?",
                description = "\"De onde é?\" is formal (você); \"De onde és?\" is informal (tu). Both literally mean \"from where are you?\"",
                examples = listOf(
                    "De onde é, se não se importa que eu pergunte?" to "Where are you from, if you don't mind me asking? (formal)",
                    "De onde és?" to "Where are you from? (informal/tu)"
                )
            )
            GreetingEntry(
                term = "Sou de...",
                meaning = "I'm from...",
                description = "\"Sou de\" + city or country. When the country takes a masculine article, use the contraction \"do\" (de + o); with a feminine article, use \"da\" (de + a). Most countries take \"de\" directly with no article.",
                examples = listOf(
                    "Sou de Lisboa." to "I'm from Lisbon.",
                    "Sou de Portugal." to "I'm from Portugal.",
                    "Sou do Brasil." to "I'm from Brazil. (de + o Brasil → do Brasil)",
                    "Sou da França." to "I'm from France. (de + a França → da França)",
                    "Sou de Inglaterra." to "I'm from England. (no article)"
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            GreetingEntry(
                term = "Estou bem / Estou ótimo / Estou ótima",
                meaning = "I'm fine / I'm great",
                description = "\"Estou bem\" is gender-neutral and the safest answer. \"Estou ótimo\" (male speaker) / \"Estou ótima\" (female speaker) means I'm great. \"Mais ou menos\" = so-so. A typical exchange: \"Tudo bem?\" → \"Tudo bem, obrigado/obrigada!\"",
                examples = listOf(
                    "Estou bem, obrigado." to "I'm fine, thank you. (male speaker)",
                    "Estou ótima, e tu?" to "I'm great, and you? (female speaker)",
                    "Mais ou menos." to "So-so.",
                    "Tudo bem, obrigada!" to "All good, thank you! (female speaker)"
                )
            )
            GreetingEntry(
                term = "Obrigado / Obrigada",
                meaning = "Thank you",
                description = "The speaker agrees in gender with themselves — a male speaker says obrigado, a female speaker says obrigada. See the Farewells & Thanks section below for full detail and examples.",
                examples = listOf(
                    "Obrigado!" to "Thank you! (male speaker)",
                    "Obrigada!" to "Thank you! (female speaker)"
                )
            )
        }
    }
}

@Composable
private fun AskingForClarificationCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Asking For Clarification", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            GreetingEntry(
                term = "Como?",
                meaning = "What? / Sorry? / Come again?",
                description = "The simplest, most common way to ask someone to repeat what they said, used constantly in casual conversation. Said with a questioning tone — not rude, just an automatic reflex when you don't catch something.",
                examples = listOf(
                    "Como? Não ouvi bem." to "What? I didn't hear that properly.",
                    "— Vou chegar tarde. — Como?" to "— I'll be late. — Sorry, what?"
                )
            )
            GreetingEntry(
                term = "Pode repetir? / Podes repetir? / Podia repetir? / Podias repetir?",
                meaning = "Can you repeat? / Could you repeat?",
                description = "Pode (formal, você) and podes (informal, tu) ask directly in the present. Podia and podias use the imperfect to soften the request into something more polite — much like English shifts from \"can\" to \"could\".",
                examples = listOf(
                    "Pode repetir, por favor?" to "Can you repeat, please? (formal)",
                    "Podes repetir? Não percebi." to "Can you repeat? I didn't understand. (informal)",
                    "Podia repetir mais devagar?" to "Could you repeat more slowly? (polite, formal)"
                ),
                note = "Within each register, the imperfect form (podia/podias) sounds softer and more polite than the present (pode/podes)."
            )
            GreetingEntry(
                term = "O que disse? / O que disseste?",
                meaning = "What did you say?",
                description = "Disse is the preterite (simple past) of dizer. O que disse? uses the formal/você verb form; o que disseste? uses the informal tu form.",
                examples = listOf(
                    "O que disse? Não ouvi bem." to "What did you say? I didn't hear well. (formal)",
                    "O que disseste? Repete, por favor." to "What did you say? Repeat, please. (informal)"
                )
            )
            GreetingEntry(
                term = "Não percebi. / Não entendi.",
                meaning = "I didn't understand.",
                description = "Percebi and entendi are both preterite (\"didn't understand\", a one-time past event), as opposed to não percebo (\"I don't understand\", an ongoing state — see the Não entry above). Percebi is the everyday EP-preferred choice; entendi is also correct and slightly more neutral/formal.",
                examples = listOf(
                    "Desculpe, não percebi." to "Sorry, I didn't understand. (everyday EP)",
                    "Não entendi a pergunta." to "I didn't understand the question."
                )
            )
        }
    }
}

@Composable
private fun MakingSuggestionsCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Making Suggestions", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            GreetingEntry(
                term = "Que tal?",
                meaning = "how about? / what do you think?",
                description = "Used to make suggestions or ask for someone's opinion. Can be followed by a noun, an infinitive, or the personal infinitive construction (e.g. irmos = us going).",
                examples = listOf(
                    "Que tal um café?" to "How about a coffee?",
                    "Que tal irmos ao cinema?" to "How about we go to the cinema?",
                    "Que tal o jantar?" to "How was the dinner?"
                ),
                note = "\"Que tal irmos\" uses the EP personal infinitive — irmos (we go). Very natural in everyday speech."
            )
            GreetingEntry(
                term = "Vamos...",
                meaning = "Let's... / We're going to...",
                description = "Vamos is the nós (we) form of ir (to go), used productively as \"let's\" before another verb in the infinitive — exactly like English \"let's\". Without a following verb, vamos alone can also just mean \"let's go! / come on!\".",
                examples = listOf(
                    "Vamos comer." to "Let's eat.",
                    "Vamos ver." to "Let's see.",
                    "Vamos!" to "Let's go! / Come on!"
                )
            )
            GreetingEntry(
                term = "Vamos a algum lado?",
                meaning = "Let's go somewhere? / Shall we go somewhere?",
                description = "Algum lado literally means \"some side\" but idiomatically means \"somewhere\". Used with ir (a) to suggest going out without specifying a destination yet.",
                examples = listOf(
                    "Vamos a algum lado este fim de semana?" to "Shall we go somewhere this weekend?",
                    "Não me apetece ficar em casa, vamos a algum lado." to "I don't feel like staying home, let's go somewhere."
                )
            )
            GreetingEntry(
                term = "Vamos comer alguma coisa? / Vamos petiscar?",
                meaning = "Wanna grab a bite? / Wanna get some snacks?",
                description = "Vamos comer alguma coisa? is the direct, neutral way to suggest grabbing food. Petiscar (from petiscos, Portugal's tapas-style small dishes) is a more colloquial, culturally EP-specific option, evoking a casual snack-and-drinks outing rather than a full meal.",
                examples = listOf(
                    "Vamos comer alguma coisa, estou cheio de fome." to "Let's grab a bite, I'm starving.",
                    "Apetece-te petiscar?" to "Feel like grabbing some snacks/tapas?"
                ),
                note = "Apetece-te...? (\"do you feel like...?\") is a very EP construction — apetecer works like gostar, with the person who feels the urge marked by an indirect object pronoun (apetece-me, apetece-te, apetece-lhe)."
            )
        }
    }
}

@Composable
private fun GreetingEntry(
    term: String,
    meaning: String,
    description: String,
    examples: List<Pair<String, String>>,
    note: String? = null
) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(term, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
        Text(meaning, style = MaterialTheme.typography.bodyMedium, fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.primary)
        Text(description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(2.dp))
        examples.forEach { (pt, en) ->
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
        if (note != null) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                "Note: $note",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}
