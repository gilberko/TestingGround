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
fun CasualExpressionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Casual Expressions") },
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
            item { GreetingsCatchingUpCard() }
            item { CheckingInCard() }
            item { FamilyHealthCard() }
            item { LocationMovingCard() }
            item { MoneyPaymentsCard() }
            item { AgreeingDisagreeingCard() }
            item { AskingSomeoneOutCard() }
            item { EmotionsReactionsCard() }
            item { GettingAroundCard() }
        }
    }
}

// ── Section 1: Greetings & Catching Up ───────────────────────────────────────

@Composable
private fun GreetingsCatchingUpCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Greetings & Catching Up", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            CasualEntry(
                english = "Hi, how are you?",
                portuguese = listOf("Olá, como estás?", "Olá, como está?"),
                note = "\"Estás\" is the informal tu form used between friends; \"está\" is the more formal você form"
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "I haven't seen you in years",
                portuguese = listOf("Não te vejo há anos")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "I can't believe it!",
                portuguese = listOf("Não acredito!", "Não pode ser!")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "I'm fine, thank you",
                portuguese = listOf("Estou bem, obrigado / obrigada")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "Actually not so good",
                portuguese = listOf("Na verdade, não muito bem", "Pois, não estou muito bem")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "Could have been better",
                portuguese = listOf("Podia estar melhor", "Podia ser melhor")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "What's new in Lisbon?",
                portuguese = listOf("Que novidades em Lisboa?", "O que há de novo em Lisboa?"),
                note = "Replace \"Lisboa\" with any city name"
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "Nothing's new",
                portuguese = listOf("Nada de novo", "Sem novidades")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "Funny you should ask",
                portuguese = listOf("Que coincidência perguntares isso", "Engraçado perguntares isso"),
                note = "Not a fixed expression like in English — say it naturally using the tu form"
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "We should do this again sometime soon",
                portuguese = listOf("Devíamos repetir isto em breve", "Temos de nos ver mais vezes")
            )
        }
    }
}

// ── Section 2: Checking In On Someone ────────────────────────────────────────

@Composable
private fun CheckingInCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Checking In On Someone", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            CasualEntry(
                english = "Are you still living in Porto?",
                portuguese = listOf("Ainda vives no Porto?", "Ainda moras no Porto?"),
                note = "\"Morar\" is very common in EP for living/residing somewhere"
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "Are you still working at that company?",
                portuguese = listOf("Ainda trabalhas naquela empresa?")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "No, I was fired a few weeks ago",
                portuguese = listOf("Não, fui despedido/despedida há umas semanas", "Deram-me o despedimento há umas semanas"),
                note = "Despedido (masculine) / despedida (feminine)"
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "Yes, I'm still working there",
                portuguese = listOf("Sim, ainda estou lá")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "What are you doing these days?",
                portuguese = listOf("O que é que andas a fazer ultimamente?", "Em que é que andas?")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "Are you still in touch with Toby?",
                portuguese = listOf("Ainda tens contacto com o Toby?", "Ainda falas com o Toby?")
            )
        }
    }
}

// ── Section 3: Family & Health ────────────────────────────────────────────────

@Composable
private fun FamilyHealthCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Family & Health", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            CasualEntry(
                english = "How is the family?",
                portuguese = listOf("Como está a família?", "Como está a tua família?")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "The wife/husband and kids are alright",
                portuguese = listOf("A mulher/o marido e os miúdos estão bem"),
                note = "\"Os miúdos\" is casual EP for kids/children"
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "We have a new boy/girl",
                portuguese = listOf("Temos um menino/menina novo/nova", "Temos mais um rapaz/uma rapariga")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "My father is not so good these days",
                portuguese = listOf("O meu pai não está muito bem ultimamente")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "I'm not feeling very well",
                portuguese = listOf("Não me estou a sentir muito bem", "Não estou a sentir-me bem"),
                note = "Both are correct; the first (me before the verb) is typical EP colloquial word order"
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "You should see a doctor",
                portuguese = listOf("Devias ir ao médico", "Deverias ver um médico")
            )
        }
    }
}

// ── Section 4: Location & Moving ─────────────────────────────────────────────

@Composable
private fun LocationMovingCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Location & Moving", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            CasualEntry(
                english = "What are you doing in the city?",
                portuguese = listOf("O que estás a fazer na cidade?", "O que andas a fazer por aqui?")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "I'm here on business",
                portuguese = listOf("Estou aqui a trabalho", "Estou cá em trabalho"),
                note = "\"Cá\" means \"here\" and is very common in EP"
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "I just moved here",
                portuguese = listOf("Acabei de me mudar para cá", "Mudei-me para cá há pouco")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "I'm looking for an apartment here",
                portuguese = listOf("Ando à procura de um apartamento aqui", "Estou à procura de um apartamento aqui")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "We're thinking about moving here",
                portuguese = listOf("Estamos a pensar mudar-nos para cá", "Estamos a ponderar mudar para cá")
            )
        }
    }
}

// ── Section 5: Money & Payments ───────────────────────────────────────────────

@Composable
private fun MoneyPaymentsCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Money & Payments", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            CasualEntry(
                english = "How much is it?",
                portuguese = listOf("Quanto custa?", "Quanto é?")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "How much did you pay for it?",
                portuguese = listOf("Quanto pagaste por isso?", "Por quanto ficou?")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "It's a bit expensive",
                portuguese = listOf("É um bocado caro", "Está um bocado caro")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "Can I pay with a card?",
                portuguese = listOf("Posso pagar com cartão?")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "I don't have cash",
                portuguese = listOf("Não tenho dinheiro", "Não tenho dinheiro em numerário"),
                note = "\"Numerário\" is the formal EP word for cash; in informal speech just \"dinheiro\" is enough"
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "Is there an ATM nearby?",
                portuguese = listOf("Há uma caixa multibanco aqui perto?"),
                note = "ATMs in Portugal are called \"multibanco\" (the national network name) — \"ATM\" is understood but not the local word"
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "Can you cover me and I'll pay you back?",
                portuguese = listOf("Podes pagar por mim que eu depois pago-te?", "Podes adiantar e eu pago-te a seguir?")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "I think you owe me money",
                portuguese = listOf("Acho que me deves dinheiro", "Parece-me que me ficas a dever dinheiro")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "I think I returned it",
                portuguese = listOf("Acho que já te devolvi", "Acho que já te paguei")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "Sure, here you go",
                portuguese = listOf("Claro, aqui tens", "Toma"),
                note = "\"Toma\" (literally \"take\") is very casual EP for handing something over"
            )
        }
    }
}

// ── Section 6: Agreeing & Disagreeing ────────────────────────────────────────

@Composable
private fun AgreeingDisagreeingCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Agreeing & Disagreeing", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            CasualEntry(
                english = "What? No? Are you sure?",
                portuguese = listOf("O quê? Não? Tens a certeza?", "A sério? Tens a certeza?")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "Yes, I'm sure",
                portuguese = listOf("Sim, tenho a certeza", "Sim, de certeza")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "You're right, sorry",
                portuguese = listOf("Tens razão, desculpa", "Tens razão, lamento"),
                note = "\"Lamento\" is slightly more formal or heartfelt than \"desculpa\""
            )
        }
    }
}

// ── Section 7: Asking Someone Out ────────────────────────────────────────────

@Composable
private fun AskingSomeoneOutCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Asking Someone Out", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            CasualEntry(
                english = "I was wondering if you'd like to go out with me sometime",
                portuguese = listOf("Estava a pensar se gostavas de sair comigo um dia", "Queria saber se te apetecia sair comigo")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "No, I have a boyfriend",
                portuguese = listOf("Não, tenho namorado"),
                note = "If a man is turning down a woman: \"Não, tenho namorada\""
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "No, I don't have time for that now",
                portuguese = listOf("Não, não tenho tempo para isso agora", "Não, agora não tenho disponibilidade")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "Sure, that would be fun",
                portuguese = listOf("Claro, ia ser divertido", "Claro, porque não?")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "I was waiting for you to ask",
                portuguese = listOf("Estava à espera que perguntasses", "Só estava à espera que me convidasses")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "What's your number?",
                portuguese = listOf("Qual é o teu número?", "Dás-me o teu número?")
            )
        }
    }
}

// ── Section 8: Emotions & Reactions ──────────────────────────────────────────

@Composable
private fun EmotionsReactionsCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Emotions & Reactions", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            CasualEntry(
                english = "I'm mad at you",
                portuguese = listOf("Estou chateado/chateada contigo", "Estou zangado/zangada contigo"),
                note = "\"Chateado\" is more common in EP; \"zangado\" is slightly stronger"
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "I'm so glad",
                portuguese = listOf("Fico tão contente!", "Que bom!", "Que alegria!")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "I'm excited for you",
                portuguese = listOf("Estou entusiasmado/entusiasmada por ti", "Fico contente por ti", "Estou animado/animada por ti")
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "Have fun",
                portuguese = listOf("Diverte-te!", "Divirtam-se!"),
                note = "\"Divirtam-se\" when addressing a group"
            )
        }
    }
}

// ── Section 9: Getting Around / Traffic ──────────────────────────────────────

@Composable
private fun GettingAroundCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Getting Around / Traffic", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            CasualEntry(
                english = "I'm stuck in traffic",
                portuguese = listOf("Estou preso/presa no trânsito", "Estou engarrafado/engarrafada"),
                note = "\"Engarrafado\" (literally \"bottled up\") is the natural EP word for being stuck in a traffic jam"
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            CasualEntry(
                english = "I'll be there soon",
                portuguese = listOf("Estou quase", "Já chego", "Já vou a chegar"),
                note = "\"Estou quase\" is the most natural casual EP for \"almost there / be there soon\""
            )
        }
    }
}

// ── Shared entry composable ───────────────────────────────────────────────────

@Composable
private fun CasualEntry(
    english: String,
    portuguese: List<String>,
    note: String? = null
) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(english, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
        portuguese.forEach { pt ->
            Text(
                "• $pt",
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.primary
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
