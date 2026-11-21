package com.example.czechappredo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Greetings", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {

            // ── Greetings ─────────────────────────────────────────────────
            GRSection("Greetings")
            GRRow("Dobré ráno", "Good morning")
            GRRow("Dobrý den", "Good day / Hello", "standard greeting throughout the day")
            GRRow("Dobré odpoledne", "Good afternoon")
            GRRow("Dobrý večer", "Good evening")
            GRRow("Dobrou noc", "Good night")
            GRRow("Ahoj", "Hi / Bye", "informal — used among friends")
            GRRow("Čau", "Hi / Bye", "very informal, from Italian ciao")
            GRRow("Nashledanou", "Goodbye", "formal farewell")
            GRRow("Uvidíme se později", "See you later", "lit. \"we'll see each other later\"; colloquial alternative: Tak zatím / Zatím")
            GRRow("Uvidíme se v pondělí", "See you on Monday", "v + day name = \"on [day]\"")
            GRRow("Uvidíme se příště", "See you next time", "příště = next time")
            GRRow("Uvidíme se brzy", "See you soon", "more common alternative in everyday speech: Na shledanou / Ahoj")

            // ── Special Occasion Greetings ────────────────────────────────
            GRSection("Special Occasion Greetings")
            GRRow("Všechno nejlepší k narozeninám!", "Happy Birthday!", "lit. \"All the best for your birthday\" — the most common form")
            GRRow("Šťastné narozeniny!", "Happy Birthday!", "more direct form")
            GRRow("Veselé Vánoce!", "Merry Christmas!")
            GRRow("Šťastný Nový rok!", "Happy New Year!")
            GRRow("Veselé svátky!", "Happy Holidays! / Merry Celebrations!")
            GRRow("Šťastné svátky!", "Happy Holidays!")
            GRNote("Vánoce (Christmas) is always plural in Czech. Veselé and Šťastné agree with the plural: Veselé Vánoce! The same applies to svátky (holidays / celebrations).")

            // ── Pleasantries ──────────────────────────────────────────────
            GRSection("Pleasantries")
            GRRow("Prosím", "Please / You're welcome / Here you go", "extremely versatile — context tells you which meaning")
            GRRow("Děkuji", "Thank you", "formal")
            GRRow("Děkuju", "Thank you", "colloquial — same meaning, more casual")
            GRNote("Like the -uji/-uju distinction seen in verbs (studuji/studuju), děkuji and děkuju are fully interchangeable. Děkuju is very common in everyday spoken Czech.")
            GRRow("Děkuji mockrát / Mockrát děkuji", "Thank you very much")
            GRRow("Děkuji moc / Děkuju moc", "Thank you very much", "colloquial; moc = a lot / very much used informally as an adverb. Word order can be reversed: Moc děkuji / Moc děkuju. In formal or written Czech, mockrát or velmi is preferred.")
            GRRow("Díky moc", "Thanks a lot", "very casual; combines díky (informal thanks) with moc (a lot). Common in everyday spoken Czech.")
            GRRow("Díky", "Thanks", "informal")
            GRRow("Promiňte", "Excuse me / Sorry", "formal")
            GRRow("Promiň", "Excuse me / Sorry", "informal")
            GRNote("You're welcome — responding to děkuji:")
            GRRow("Prosím.", "You're welcome.", "when said in response to děkuji, prosím means 'you're welcome'. It is the most natural and common reply.")
            GRRow("Není zač.", "Don't mention it.", "lit. 'There is nothing to thank for.' Very common in everyday Czech.")
            GRRow("Rádo se stalo.", "My pleasure.", "lit. 'It happened gladly.' Slightly warmer / more formal.")

            // ── Welcome ───────────────────────────────────────────────────
            GRSection("Welcome")
            GRRow("Vítejte!", "Welcome!", "formal or addressing a group")
            GRRow("Vítej!", "Welcome!", "informal, addressing one person")
            GRRow("Vítejte v Praze!", "Welcome to Prague!", "uses Locative: Praha → Praze")
            GRRow("Vítejte v České republice!", "Welcome to the Czech Republic!")

            // ── Good Wishes — Have a Good Day / Weekend ───────────────────
            GRSection("Good Wishes — Have a Good Day / Weekend")
            GRRow("Hezký den! / Přeji hezký den.", "Have a nice day!", "said when parting; přeji = I wish. Formal: Přeji Vám hezký den.")
            GRRow("Mějte se hezky! / Měj se hezky!", "Take care! / Have a good one!", "formal / informal; very common goodbye. Short form: Mějte se! / Měj se!")
            GRRow("Hezký víkend! / Přeji hezký víkend.", "Have a good weekend!", "typical on a Friday afternoon")
            GRRow("Hezký večer!", "Have a nice evening!")
            GRRow("Dobrou chuť!", "Enjoy your meal! / Bon appétit!", "said before eating")
            GRRow("Šťastnou cestu!", "Have a good trip!")
            GRRow("Hodně štěstí!", "Good luck!", "lit. 'a lot of luck'")
            GRNote("Most of these wishes are in the accusative, because an implied přeji (I wish) stands in front: (Přeji) hezký den. (Přeji) šťastnou cestu. (Přeji) dobrou chuť. Exceptions: Hodně štěstí! uses the genitive after hodně (a lot of), and Mějte se hezky! is an imperative (lit. \"Have yourselves nicely!\").")

            // ── Letter and Email Writing ──────────────────────────────────
            GRSection("Letter and Email Writing")
            GRNote("Openings — the name goes into the vocative case:")
            GRRow("Vážený pane Nováku, / Vážená paní Nováková,", "Dear Mr Novák, / Dear Mrs Nováková,", "formal. pan → pane, Novák → Nováku (vocative); women's -ová surnames don't change in the vocative.")
            GRRow("Vážený pane, / Vážená paní,", "Dear Sir, / Dear Madam,", "when you don't know the name")
            GRRow("Vážené dámy, vážení pánové,", "Ladies and Gentlemen, / Dear Sir or Madam,", "formal letter to a company or institution")
            GRRow("Dobrý den,", "Hello,", "the most common neutral opening of a business email")
            GRRow("Milý Petře, / Milá Jano,", "Dear Petr, / Dear Jana,", "informal / friendly; vocative: Petr → Petře, Jana → Jano")
            GRRow("Ahoj Petře, / Ahoj,", "Hi Petr, / Hi,", "informal — friends and colleagues on ty")
            GRNote("The body:")
            GRRow("Prosím o radu ohledně ___.", "Please advise on ___.", "ohledně + genitive = regarding: Prosím o radu ohledně smlouvy. = Please advise on the contract.")
            GRRow("Dejte mi prosím vědět, ___.", "Please let me know ___.", "informal: Dej mi prosím vědět.")
            GRRow("V příloze posílám ___.", "Please find attached ___.", "lit. 'In the attachment I am sending ___'. příloha = attachment")
            GRRow("Navazuji na naši schůzku.", "Following up on our meeting.")
            GRRow("Děkuji za Vaši zprávu. / Děkuji za Vaši odpověď.", "Thank you for your message. / Thank you for your reply.")
            GRRow("Předem děkuji.", "Thanks in advance.", "also: Děkuji předem za odpověď.")
            GRRow("Těším se na Vaši odpověď.", "I look forward to your reply.")
            GRRow("V případě dotazů mě neváhejte kontaktovat.", "If you have any questions, don't hesitate to contact me.")
            GRNote("Closings:")
            GRRow("S pozdravem", "Regards / Best regards", "the standard closing of almost every formal email")
            GRRow("S přátelským pozdravem", "Kind regards", "lit. 'with a friendly greeting'")
            GRRow("S úctou", "Yours truly / Yours faithfully / Respectfully", "very formal — official letters")
            GRRow("Srdečně zdravím", "Warm regards / Best wishes", "lit. 'I greet (you) heartily' — warm but still polite")
            GRRow("Díky / Měj se / Ahoj", "Thanks / Take care / Bye", "informal closings between friends")
            GRNote("Courtesy capitals: when writing to one person you address formally, Vy, Váš, Vám, Vás are often written with a capital V — Děkuji Vám za Vaši odpověď. It is a sign of respect, not a grammar rule.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun GRSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun GRNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun GRRow(czech: String, english: String, note: String = "") {
    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)) {
                    append(czech)
                }
                withStyle(SpanStyle(fontSize = 16.sp, color = Color.DarkGray)) {
                    append("  —  $english")
                }
            }
        )
        if (note.isNotEmpty()) {
            Text(
                text = note,
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray,
                modifier = Modifier.padding(start = 4.dp, top = 1.dp)
            )
        }
    }
}
