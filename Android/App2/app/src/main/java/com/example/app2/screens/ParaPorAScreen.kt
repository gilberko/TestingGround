package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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

private data class PpaTopic(
    val name: String,
    val subtitle: String,
    val points: List<String>,
    val examples: List<Pair<String, String>>,
    val note: String? = null
)

private data class PpaSection(
    val title: String,
    val topics: List<PpaTopic>
)

private val ppaSections = listOf(
    PpaSection(
        title = "Overview & Contractions",
        topics = listOf(
            PpaTopic(
                name = "a · para · por",
                subtitle = "Three small words, three different jobs",
                points = listOf(
                    "a — a point: a place you visit briefly, an exact time, a rate, the indirect object (to whom)",
                    "para — a direction / goal: destination, purpose, recipient, deadline",
                    "por — a path / cause: through, by, because of, in exchange for, per, by whom (agent)",
                    "Quick memory hook: para = towards a goal ➜, por = through / because of ↻"
                ),
                examples = listOf(
                    "Vou ao café." to "I'm going to the café (short visit).",
                    "Isto é para ti." to "This is for you (recipient).",
                    "Passei pelo parque." to "I went through the park."
                )
            ),
            PpaTopic(
                name = "Contractions",
                subtitle = "a and por merge with the article — para does not",
                points = listOf(
                    "a + o = ao · a + a = à · a + os = aos · a + as = às",
                    "por + o = pelo · por + a = pela · por + os = pelos · por + as = pelas",
                    "para never contracts in writing: para o, para a",
                    "In fast speech para is often pronounced \"pra\" (pro = para o) — informal, don't write it"
                ),
                examples = listOf(
                    "Vou à praia." to "I'm going to the beach.",
                    "Pelo menos tentei." to "At least I tried.",
                    "Vou para o trabalho." to "I'm going to work."
                )
            )
        )
    ),
    PpaSection(
        title = "Going Somewhere",
        topics = listOf(
            PpaTopic(
                name = "ir a vs ir para",
                subtitle = "Short visit vs longer stay / destination",
                points = listOf(
                    "ir a — a short visit or errand; you'll come back (to the supermarket, the doctor, Lisbon for the day)",
                    "ir para — a longer stay or a move, or stressing the direction / final destination",
                    "ir para casa is the normal way to say 'go home' (you're heading back to stay)",
                    "Directions also use para: vire para a direita, vá para trás"
                ),
                examples = listOf(
                    "Vou ao supermercado." to "I'm going to the supermarket (and coming back).",
                    "Fui a Lisboa no sábado." to "I went to Lisbon on Saturday (a trip).",
                    "Vou para Lisboa." to "I'm moving / heading to Lisbon (to stay).",
                    "Vou para casa." to "I'm going home."
                ),
                note = "This is a strong tendency, not a strict rule — a corpus study of European Portuguese found both used with motion verbs, and casual speech increasingly uses para for everything. But using para for a quick errand can sound like you're moving there."
            ),
            PpaTopic(
                name = "por — through / via / along",
                subtitle = "A place on the way, not the goal",
                points = listOf(
                    "por marks the path: a place you pass through or along, not necessarily stopping",
                    "Also the route you take: pela autoestrada, por aqui, por ali",
                    "Also vague location 'around': por aqui = around here"
                ),
                examples = listOf(
                    "Passei por Coimbra a caminho do Porto." to "I went through Coimbra on the way to Porto.",
                    "Vamos pela autoestrada." to "Let's go by the motorway.",
                    "O ladrão entrou pela janela." to "The thief came in through the window.",
                    "Há uma farmácia por aqui?" to "Is there a pharmacy around here?"
                )
            )
        )
    ),
    PpaSection(
        title = "Purpose vs Reason",
        topics = listOf(
            PpaTopic(
                name = "para — purpose, goal, target",
                subtitle = "Looking forward: in order to / for whom / by when",
                points = listOf(
                    "para + infinitive = in order to",
                    "para + person = intended recipient / beneficiary",
                    "para + time = deadline",
                    "para + opinion: para mim = in my opinion"
                ),
                examples = listOf(
                    "Estudo para passar no exame." to "I study (in order) to pass the exam.",
                    "Comprei flores para a minha mãe." to "I bought flowers for my mother.",
                    "Preciso disto para sexta-feira." to "I need this by Friday.",
                    "Para mim, é caro." to "For me / in my opinion, it's expensive."
                )
            ),
            PpaTopic(
                name = "por — cause, reason, motive",
                subtitle = "Looking backward: because of / out of / on behalf of",
                points = listOf(
                    "por = the reason something happened (out of love, because of the rain)",
                    "por causa de = because of",
                    "obrigado por = thanks for (the reason you're grateful)",
                    "por = on behalf of / instead of someone"
                ),
                examples = listOf(
                    "Fiz isto por amor." to "I did this out of love.",
                    "Não saímos por causa da chuva." to "We didn't go out because of the rain.",
                    "Obrigado pela ajuda." to "Thanks for the help.",
                    "Eu falo por ele." to "I'll speak on his behalf."
                ),
                note = "Trabalho para a empresa = the company is my employer (goal/recipient). Trabalho pela empresa = for the company's sake, or in its place."
            )
        )
    ),
    PpaSection(
        title = "How You Get There (Transport & Communication)",
        topics = listOf(
            PpaTopic(
                name = "de carro, de comboio… (not por, not a)",
                subtitle = "Means of transport uses de",
                points = listOf(
                    "Vehicles take de: de carro, de autocarro, de comboio, de avião, de metro, de barco, de bicicleta, de mota",
                    "Fixed exceptions with a: a pé (on foot), a cavalo (on horseback)",
                    "With an article you use em: no carro do João, no comboio das 8"
                ),
                examples = listOf(
                    "Vou de comboio para o Porto." to "I'm going to Porto by train.",
                    "Fomos de carro." to "We went by car.",
                    "Vou a pé." to "I'm going on foot.",
                    "Fui no carro da Ana." to "I went in Ana's car."
                ),
                note = "So 'a pé' is right, but it doesn't generalise — never say *a carro* or *por carro*."
            ),
            PpaTopic(
                name = "por — route or channel",
                subtitle = "By land, by sea, by e-mail",
                points = listOf(
                    "por mar / por terra / por via aérea — the route",
                    "por e-mail, por telefone, pelo correio, por mensagem — the channel of communication"
                ),
                examples = listOf(
                    "Enviei o contrato por e-mail." to "I sent the contract by e-mail.",
                    "Falámos por telefone." to "We spoke by phone.",
                    "A encomenda veio por mar." to "The order came by sea."
                )
            )
        )
    ),
    PpaSection(
        title = "Sending & Giving",
        topics = listOf(
            PpaTopic(
                name = "para vs a vs por with sending",
                subtitle = "Final recipient · who receives the act · by whom / via what",
                points = listOf(
                    "para alguém — the final destination / intended recipient",
                    "a alguém — the indirect object of verbs like dar, entregar, dizer, mostrar: the person who receives the act itself",
                    "por / pelo — the means or the intermediary that carries it",
                    "With enviar / escrever both a and para work: a is more standard/formal, para more everyday"
                ),
                examples = listOf(
                    "Enviei um pacote para a Ana." to "I sent a package to Ana (she's the final recipient).",
                    "Dei o pacote ao estafeta." to "I gave the package to the courier.",
                    "Dei o pacote ao estafeta para a Ana." to "I gave the courier the package for Ana.",
                    "Enviei-o pelos correios." to "I sent it through the post office.",
                    "Escrevi à Ana. / Escrevi para a Ana." to "I wrote to Ana (both correct)."
                )
            )
        )
    ),
    PpaSection(
        title = "Agent, Price & Exchange",
        topics = listOf(
            PpaTopic(
                name = "por — by whom (passive agent)",
                subtitle = "escrito por, feito por, pintado por",
                points = listOf(
                    "In the passive voice, the doer is introduced by por",
                    "Contracts with the article: pelo autor, pela professora"
                ),
                examples = listOf(
                    "Os Lusíadas foram escritos por Camões." to "Os Lusíadas was written by Camões.",
                    "A casa foi pintada pelo meu pai." to "The house was painted by my father."
                )
            ),
            PpaTopic(
                name = "por — price & exchange",
                subtitle = "for (a price) / for (something in return)",
                points = listOf(
                    "comprar / vender / pagar por + price",
                    "trocar X por Y = swap X for Y",
                    "por also means 'per': por dia, por pessoa, duas vezes por semana"
                ),
                examples = listOf(
                    "Comprei-o por 10 euros." to "I bought it for 10 euros.",
                    "Troquei o carro por uma bicicleta." to "I traded the car for a bicycle.",
                    "São 20 euros por pessoa." to "It's 20 euros per person.",
                    "Vou ao ginásio duas vezes por semana." to "I go to the gym twice a week."
                )
            ),
            PpaTopic(
                name = "a — unit price, rate, speed",
                subtitle = "at (so much each)",
                points = listOf(
                    "Prices quoted per unit use a / ao / à",
                    "Speeds and distances: a 100 km/h, a 5 km daqui"
                ),
                examples = listOf(
                    "As laranjas estão a 2 euros o quilo." to "Oranges are 2 euros a kilo.",
                    "Ia a 120 km/h." to "I was going at 120 km/h.",
                    "A praia fica a 5 km daqui." to "The beach is 5 km from here."
                )
            )
        )
    ),
    PpaSection(
        title = "Time",
        topics = listOf(
            PpaTopic(
                name = "a · para · por with time",
                subtitle = "exact moment · deadline · approximate / duration",
                points = listOf(
                    "a — an exact time or date: às 3, à meia-noite, a 4 de julho, ao fim de semana",
                    "para — a deadline or target time: para amanhã, para as 5",
                    "por — approximate time or a rough duration: por volta das 3, por uns dias, por agora"
                ),
                examples = listOf(
                    "A reunião é às três." to "The meeting is at three.",
                    "O relatório é para amanhã." to "The report is due tomorrow.",
                    "Chego por volta das três." to "I'll arrive around three.",
                    "Fiquei lá por uns dias." to "I stayed there for a few days."
                ),
                note = "For a plain duration EP usually needs no preposition at all (Fiquei lá três dias) or uses durante."
            )
        )
    ),
    PpaSection(
        title = "Verbs That Take a Fixed Preposition",
        topics = listOf(
            PpaTopic(
                name = "Verbs + a",
                subtitle = "Usually followed by an infinitive or a place",
                points = listOf(
                    "começar a — to start to · aprender a — to learn to · ajudar a — to help to",
                    "voltar a + inf. — to do again · habituar-se a — to get used to",
                    "obrigar a — to force to · chegar a — to arrive at (EP: never chegar em)"
                ),
                examples = listOf(
                    "Comecei a aprender português." to "I started learning Portuguese.",
                    "Voltei a ligar-lhe." to "I called him again.",
                    "Chegámos a Lisboa às 10." to "We arrived in Lisbon at 10."
                )
            ),
            PpaTopic(
                name = "Verbs + para",
                subtitle = "Direction or purpose built into the verb",
                points = listOf(
                    "olhar para — to look at (EP)",
                    "trabalhar para — to work for (an employer)",
                    "servir para — to be used for · preparar-se para — to get ready for",
                    "mudar-se para — to move (house) to"
                ),
                examples = listOf(
                    "Olha para mim!" to "Look at me!",
                    "Isto serve para quê?" to "What is this for?",
                    "Mudei-me para o Porto." to "I moved to Porto."
                )
            ),
            PpaTopic(
                name = "Verbs + por",
                subtitle = "Path, waiting, choosing, ending up",
                points = listOf(
                    "passar por — to pass through / go through (a situation)",
                    "esperar por — to wait for · perguntar por — to ask after someone",
                    "lutar por — to fight for · optar por — to opt for · interessar-se por — to be interested in",
                    "acabar por + inf. — to end up doing · começar por + inf. — to start by doing"
                ),
                examples = listOf(
                    "Espera por mim!" to "Wait for me!",
                    "A tua mãe perguntou por ti." to "Your mother asked about you.",
                    "Acabei por ficar em casa." to "I ended up staying home.",
                    "Vamos começar pelo princípio." to "Let's start at the beginning."
                )
            )
        )
    ),
    PpaSection(
        title = "Common Expressions",
        topics = listOf(
            PpaTopic(
                name = "Expressions with por",
                subtitle = "",
                points = listOf(
                    "pelo menos — at least · por favor — please",
                    "por exemplo — for example · por acaso — by chance / actually",
                    "por isso — that's why / so · por causa de — because of",
                    "por enquanto — for now · por fim — finally · por vezes — sometimes",
                    "por volta de — around (time) · pelos vistos — apparently (very EP)"
                ),
                examples = listOf(
                    "Pelos vistos, ele já saiu." to "Apparently he's already left.",
                    "Por acaso, conheço-o." to "As it happens, I know him."
                )
            ),
            PpaTopic(
                name = "Expressions with para",
                subtitle = "",
                points = listOf(
                    "para sempre — forever · para já — for now / for the moment (EP)",
                    "para trás — backwards · para a frente — forwards",
                    "para variar — for a change · para quê? — what for?"
                ),
                examples = listOf(
                    "Para já, está tudo bem." to "For now, everything's fine.",
                    "Hoje, para variar, está sol." to "Today, for a change, it's sunny."
                )
            ),
            PpaTopic(
                name = "Expressions with a",
                subtitle = "",
                points = listOf(
                    "a pé — on foot · às vezes — sometimes · aos poucos — little by little",
                    "à vontade — at ease / feel free · a partir de — from (onwards)",
                    "ao lado de — next to · à espera de — waiting for · à pressa — in a hurry"
                ),
                examples = listOf(
                    "Fique à vontade." to "Make yourself at home.",
                    "A partir de amanhã, começo a dieta." to "From tomorrow on, I start the diet."
                )
            ),
            PpaTopic(
                name = "estar a · estar para · estar por",
                subtitle = "Same verb, three prepositions, three meanings (EP)",
                points = listOf(
                    "estar a + inf. — to be doing (in progress)",
                    "estar para + inf. — to be about to / intending to; não estar para = not be in the mood for",
                    "estar por + inf. — still to be done (passive-like: nobody has done it yet)"
                ),
                examples = listOf(
                    "Estou a limpar a casa." to "I'm cleaning the house.",
                    "Estou para sair." to "I'm about to leave.",
                    "Não estou para isso." to "I'm not in the mood for that.",
                    "A casa está por limpar." to "The house is still to be cleaned."
                )
            )
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParaPorAScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Para vs Por vs A") },
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { Spacer(modifier = Modifier.height(4.dp)) }
            ppaSections.forEach { section ->
                item { PpaSectionHeader(section.title) }
                section.topics.forEach { topic ->
                    item { PpaCard(topic) }
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun PpaSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
    )
}

@Composable
private fun PpaCard(entry: PpaTopic) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = entry.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            if (entry.subtitle.isNotEmpty()) {
                Text(
                    text = entry.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            entry.points.forEach { bullet ->
                Text(
                    text = "• $bullet",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
                )
            }
            if (entry.note != null) {
                Text(
                    text = "Note: ${entry.note}",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            Text(
                text = "Examples:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            entry.examples.forEach { (pt, en) ->
                Column(modifier = Modifier.padding(bottom = 4.dp)) {
                    Text(
                        text = pt,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = en,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
