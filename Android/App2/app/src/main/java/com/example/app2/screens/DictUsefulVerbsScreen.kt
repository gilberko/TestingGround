package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class DuvVerb(
    val verb: String,
    val english: String,
    val examples: List<Pair<String, String>>,
    val note: String = ""
)

private data class DuvGroup(val title: String, val verbs: List<DuvVerb>, val headerNote: String = "")

private val duvGroups = listOf(
    DuvGroup(
        "Being, Having & Existing",
        listOf(
            DuvVerb(
                "ser", "to be (identity, origin, profession, time, permanent traits)",
                listOf(
                    "Sou engenheiro." to "I'm an engineer.",
                    "Ela é de Lisboa." to "She is from Lisbon.",
                    "São três horas." to "It's three o'clock."
                ),
                "Also the passive auxiliary: A casa foi vendida. — The house was sold."
            ),
            DuvVerb(
                "estar", "to be (temporary state, location, ongoing action)",
                listOf(
                    "Estou cansado." to "I'm tired.",
                    "O João está em casa." to "João is at home.",
                    "Estamos a jantar." to "We're having dinner. (estar a + infinitive = -ing)"
                )
            ),
            DuvVerb(
                "haver", "there is / there are (impersonal); also auxiliary",
                listOf(
                    "Há um café ao virar da esquina." to "There's a café around the corner.",
                    "Havia muita gente na festa." to "There were a lot of people at the party.",
                    "Moro aqui há cinco anos." to "I've lived here for five years. (há = ago / for)"
                ),
                "As \"there is\", always 3rd person singular: há, havia, houve, haverá. In speech, ter often replaces it in BP — EP prefers há."
            ),
            DuvVerb(
                "ter", "to have",
                listOf(
                    "Tenho dois irmãos." to "I have two brothers.",
                    "Tens de ir ao médico." to "You have to go to the doctor. (ter de = have to)",
                    "Tenho fome / sede / frio." to "I'm hungry / thirsty / cold."
                ),
                "Also forms compound tenses: tenho feito = I have been doing."
            ),
            DuvVerb(
                "ficar", "to stay; to become / end up; to be located",
                listOf(
                    "Fico em casa hoje." to "I'm staying home today.",
                    "Ela ficou contente." to "She became / was happy (as a result).",
                    "Onde fica a estação?" to "Where is the station (located)?",
                    "Fica-te bem." to "It suits you."
                )
            ),
            DuvVerb(
                "tornar-se", "to become (a gradual or lasting change)",
                listOf(
                    "Tornou-se médico." to "He became a doctor.",
                    "A cidade tornou-se muito cara." to "The city has become very expensive."
                ),
                "ficar is more common for emotions and quick changes (fiquei nervoso); tornar-se for identity / lasting changes. Also tornar a + infinitive = to do again."
            )
        )
    ),
    DuvGroup(
        "Going, Coming & Moving",
        listOf(
            DuvVerb(
                "ir", "to go",
                listOf(
                    "Vou ao supermercado." to "I'm going to the supermarket.",
                    "Vamos para Portugal em maio." to "We're going to Portugal in May.",
                    "Vou comprar pão." to "I'm going to buy bread. (ir + infinitive = future)"
                )
            ),
            DuvVerb(
                "vir", "to come (towards the speaker)",
                listOf(
                    "Vens à festa?" to "Are you coming to the party?",
                    "Ela vem de Braga." to "She comes from Braga.",
                    "Vem cá!" to "Come here!"
                ),
                "ir = movement away from the speaker, vir = towards the speaker: Vou aí. — I'm coming (to where you are)."
            ),
            DuvVerb(
                "sair", "to leave / go out",
                listOf(
                    "Saio do trabalho às seis." to "I leave work at six.",
                    "Vamos sair hoje à noite?" to "Shall we go out tonight?",
                    "O comboio já saiu." to "The train has already left."
                )
            ),
            DuvVerb(
                "chegar", "to arrive; to be enough",
                listOf(
                    "Cheguei a casa tarde." to "I got home late.",
                    "A que horas chega o avião?" to "What time does the plane arrive?",
                    "Chega! / Já chega." to "Enough! / That's enough."
                ),
                "chegar a (a place), not chegar em (BP)."
            ),
            DuvVerb(
                "andar", "to walk; to go around; to be (lately)",
                listOf(
                    "Gosto de andar a pé." to "I like walking.",
                    "Ando muito cansado." to "I've been very tired lately.",
                    "Ando a aprender português." to "I've been learning Portuguese.",
                    "andar de bicicleta / de carro / de comboio" to "to ride a bike / go by car / by train"
                )
            ),
            DuvVerb(
                "conduzir", "to drive",
                listOf(
                    "Sabes conduzir?" to "Can you drive?",
                    "Conduzo para o trabalho todos os dias." to "I drive to work every day."
                ),
                "EP word; BP uses dirigir. Present: conduzo, conduzes, conduz, conduzimos, conduzem."
            ),
            DuvVerb(
                "trazer", "to bring (here, towards the speaker)",
                listOf(
                    "Trago o vinho." to "I'll bring the wine.",
                    "Trouxeste o guarda-chuva?" to "Did you bring the umbrella?"
                ),
                "levar = to take (away from the speaker): Leva o casaco. — Take your coat."
            ),
            DuvVerb(
                "mudar", "to change (something); to move",
                listOf(
                    "Mudei de ideias." to "I changed my mind.",
                    "Temos de mudar o pneu." to "We have to change the tyre.",
                    "O tempo mudou." to "The weather changed.",
                    "mudar de casa / de emprego" to "to move house / change jobs"
                )
            ),
            DuvVerb(
                "mudar-se", "to move (house / city) — yes, it exists",
                listOf(
                    "Mudei-me para Lisboa em 2020." to "I moved to Lisbon in 2020.",
                    "Vamos mudar-nos no próximo mês." to "We're moving next month."
                ),
                "mudar-se para = move (oneself) to a new place. mudar (de casa) without -se means the same in speech: Mudámos de casa."
            )
        )
    ),
    DuvGroup(
        "Knowing, Thinking & Remembering",
        listOf(
            DuvVerb(
                "saber", "to know (facts); to know how to",
                listOf(
                    "Sei onde ele mora." to "I know where he lives.",
                    "Sabes nadar?" to "Can you swim? (do you know how to)",
                    "Não sei." to "I don't know."
                ),
                "conhecer = to know a person or place: Conheço o Porto."
            ),
            DuvVerb(
                "lembrar-se (de)", "to remember",
                listOf(
                    "Lembras-te de mim?" to "Do you remember me?",
                    "Não me lembro do nome dele." to "I don't remember his name."
                ),
                "lembrar (without -se) = to remind: Lembra-me de ligar à Ana. — Remind me to call Ana."
            ),
            DuvVerb(
                "pensar", "to think (reflect, consider); to intend",
                listOf(
                    "Estou a pensar em ti." to "I'm thinking about you. (pensar em)",
                    "Penso ir a Paris no verão." to "I'm thinking of going to Paris this summer.",
                    "Pensa bem antes de decidir." to "Think carefully before you decide."
                )
            ),
            DuvVerb(
                "achar", "to think / reckon (opinion); to find",
                listOf(
                    "Acho que vai chover." to "I think it's going to rain.",
                    "Achas que sim?" to "Do you think so?",
                    "Acho este filme aborrecido." to "I find this film boring."
                ),
                "achar vs pensar: for a quick opinion (\"I think that…\"), achar is the everyday choice: Acho que sim. pensar is for the act of thinking or reflecting (pensar em, pensar sobre). Penso que… also gives an opinion but sounds more considered / formal."
            ),
            DuvVerb(
                "entender", "to understand",
                listOf(
                    "Não entendo esta palavra." to "I don't understand this word.",
                    "Entendeste?" to "Did you understand? / Got it?"
                ),
                "perceber is very common in EP with the same meaning: Não percebo. — I don't get it."
            )
        )
    ),
    DuvGroup(
        "Ability & Achievement",
        listOf(
            DuvVerb(
                "poder", "can / may (permission, possibility)",
                listOf(
                    "Posso entrar?" to "May I come in?",
                    "Podes ajudar-me?" to "Can you help me?",
                    "Pode chover amanhã." to "It may rain tomorrow."
                )
            ),
            DuvVerb(
                "conseguir", "to manage to / be able to (succeed)",
                listOf(
                    "Não consigo abrir a porta." to "I can't (manage to) open the door.",
                    "Conseguiste o emprego?" to "Did you get the job?"
                ),
                "saber = know how · poder = be allowed / possible · conseguir = actually manage to do it."
            )
        )
    ),
    DuvGroup(
        "Speaking & Communicating",
        listOf(
            DuvVerb(
                "falar", "to speak / talk",
                listOf(
                    "Falas inglês?" to "Do you speak English?",
                    "Preciso de falar contigo." to "I need to talk to you.",
                    "Estamos a falar de futebol." to "We're talking about football."
                )
            ),
            DuvVerb(
                "dizer", "to say / tell",
                listOf(
                    "O que disseste?" to "What did you say?",
                    "Diz-me a verdade." to "Tell me the truth.",
                    "Como se diz … em português?" to "How do you say … in Portuguese?"
                )
            ),
            DuvVerb(
                "ouvir", "to hear / listen",
                listOf(
                    "Ouço música no carro." to "I listen to music in the car.",
                    "Não te ouço bem." to "I can't hear you well."
                ),
                "eu ouço (irregular 1st person); oiço is also very common in EP."
            ),
            DuvVerb(
                "escrever", "to write",
                listOf(
                    "Escreve o teu nome aqui." to "Write your name here.",
                    "Escrevi-lhe uma carta." to "I wrote him / her a letter."
                ),
                "Participle: escrito."
            ),
            DuvVerb(
                "mandar", "to send; to order / tell someone to",
                listOf(
                    "Manda-me uma mensagem." to "Send me a message.",
                    "O chefe mandou-nos ficar." to "The boss told us to stay."
                ),
                "enviar = to send (more formal)."
            ),
            DuvVerb(
                "receber", "to receive / get",
                listOf(
                    "Recebeste o meu email?" to "Did you get my email?",
                    "Recebo o salário no fim do mês." to "I get paid at the end of the month."
                )
            )
        )
    ),
    DuvGroup(
        "Everyday Actions",
        listOf(
            DuvVerb(
                "fazer", "to do / make",
                listOf(
                    "O que fazes?" to "What do you do? / What are you doing?",
                    "Faço o jantar." to "I'm making dinner.",
                    "Faz frio." to "It's cold. (weather)",
                    "Faz anos hoje." to "It's his / her birthday today."
                )
            ),
            DuvVerb(
                "dar", "to give",
                listOf(
                    "Dá-me o livro, por favor." to "Give me the book, please.",
                    "Não dá." to "It's not possible / It doesn't work.",
                    "dar um passeio" to "to go for a walk",
                    "A janela dá para o rio." to "The window looks out on the river."
                )
            ),
            DuvVerb(
                "abrir", "to open",
                listOf(
                    "Podes abrir a janela?" to "Can you open the window?",
                    "A loja abre às nove." to "The shop opens at nine."
                ),
                "Participle: aberto (irregular)."
            ),
            DuvVerb(
                "fechar", "to close / shut",
                listOf(
                    "Fecha a porta, por favor." to "Close the door, please.",
                    "O museu fecha à segunda." to "The museum is closed on Mondays."
                )
            ),
            DuvVerb(
                "ver", "to see / watch",
                listOf(
                    "Vês aquele prédio?" to "Do you see that building?",
                    "Vimos um filme ontem." to "We watched a film yesterday."
                ),
                "olhar (para) = to look (at)."
            ),
            DuvVerb(
                "comprar", "to buy",
                listOf(
                    "Comprei um carro novo." to "I bought a new car.",
                    "Onde posso comprar bilhetes?" to "Where can I buy tickets?"
                )
            ),
            DuvVerb(
                "encontrar", "to find; to meet (by chance)",
                listOf(
                    "Não encontro as chaves." to "I can't find my keys.",
                    "Encontrei o Pedro no café." to "I ran into Pedro at the café."
                )
            ),
            DuvVerb(
                "encontrar-se", "to meet up (arranged); to be (located / in a state)",
                listOf(
                    "Encontramo-nos às oito?" to "Shall we meet at eight?",
                    "O hotel encontra-se no centro." to "The hotel is located in the centre. (formal)",
                    "O diretor encontra-se em reunião." to "The director is in a meeting. (formal)"
                ),
                "encontrar alguém = run into / find someone · encontrar-se com alguém = meet up with someone."
            ),
            DuvVerb(
                "brincar", "to play (children); to joke",
                listOf(
                    "As crianças estão a brincar no jardim." to "The children are playing in the garden.",
                    "Estou a brincar!" to "I'm joking! / Just kidding!"
                ),
                "jogar = to play a game / sport (jogar futebol); tocar = to play an instrument (tocar guitarra)."
            ),
            DuvVerb(
                "comer", "to eat",
                listOf(
                    "Já comeste?" to "Have you eaten yet?",
                    "Vamos comer fora." to "Let's eat out."
                )
            ),
            DuvVerb(
                "beber", "to drink",
                listOf(
                    "Queres beber alguma coisa?" to "Do you want something to drink?",
                    "Não bebo álcool." to "I don't drink alcohol."
                ),
                "tomar is used for coffee and medicine: tomar um café / tomar um comprimido."
            )
        )
    )
)

@Composable
fun DictUsefulVerbsScreen(onBack: () -> Unit) {
    DictScreenScaffold(title = "Useful Verbs", onBack = onBack) {
        items(duvGroups) { group ->
            DictCardShell(title = group.title, headerNote = group.headerNote) {
                group.verbs.forEachIndexed { index, verb ->
                    if (index > 0) HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                    DuvVerbBlock(verb)
                }
            }
        }
    }
}

@Composable
private fun DuvVerbBlock(verb: DuvVerb) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = verb.verb,
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(0.8f)
            )
            Text(
                text = verb.english,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.weight(1.2f)
            )
        }
        verb.examples.forEach { (pt, en) -> DictExample(pt, en) }
        if (verb.note.isNotEmpty()) {
            Text(
                text = verb.note,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}
