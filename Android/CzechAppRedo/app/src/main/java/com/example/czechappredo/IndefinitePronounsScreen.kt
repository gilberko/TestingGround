package com.example.czechappredo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndefinitePronounsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Someone, Somewhere...", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            IPSection("Indefinite Pronouns and Adverbs")
            IPNote("These words let you refer to unspecified people, things, places, times, and manners.")
            IPNote("Formed with the prefix někdo / něco / někde... (some-) or nikdo / nic / nikde... (no-).")
            IPNote("Only the pronouns někdo and něco decline through cases. Location, time, and manner words are adverbs and do not change form.")
            IPNote("Important: the negative series (nikdo, nic, nikde...) requires double negation — the verb must also be negated: Nevidím nikoho. (I don't see anyone.)")

            IPSection("Někdo — Someone")
            IPNote("Declines like a noun. Unlike English, the n- prefix stays throughout all cases.")
            IPTable(
                headers = listOf("Case", "Form", "Example"),
                rows = listOf(
                    Triple("Nominative", "někdo", "Někdo přišel. (Someone came.)"),
                    Triple("Genitive", "někoho", "Čekám na někoho. (I'm waiting for someone.)"),
                    Triple("Dative", "někomu", "Řekl jsem to někomu. (I told that to someone.)"),
                    Triple("Accusative", "někoho", "Vidím někoho. (I see someone.)"),
                    Triple("Locative", "o někom", "Mluvím o někom. (I'm talking about someone.)"),
                    Triple("Instrumental", "s někým", "Šel jsem s někým. (I went with someone.)")
                )
            )
            IPNote("Note: Vocative — not applicable. You cannot address 'someone' in the vocative because you don't know who they are.")

            IPSection("Něco — Something")
            IPNote("Also declines, but notice that Nominative and Accusative share the form něco — something is always inanimate.")
            IPTable(
                headers = listOf("Case", "Form", "Example"),
                rows = listOf(
                    Triple("Nominative", "něco", "Něco se stalo. (Something happened.)"),
                    Triple("Genitive", "něčeho", "Bojím se něčeho. (I'm afraid of something.)"),
                    Triple("Dative", "něčemu", "Věřím něčemu. (I believe in something.)"),
                    Triple("Accusative", "něco", "Vidím něco. (I see something.)"),
                    Triple("Locative", "o něčem", "Mluvím o něčem. (I'm talking about something.)"),
                    Triple("Instrumental", "s něčím", "Přišel s něčím. (He came with something.)")
                )
            )

            IPSection("Somewhere — Location and Direction")
            IPNote("These are adverbs — they do not decline and never change form based on case.")
            IPNote("někde = somewhere (static location):  Bydlí někde v Praze. (He lives somewhere in Prague.)")
            IPNote("někam = to somewhere (direction / motion toward):  Jdu někam. (I'm going somewhere.)")
            IPNote("odněkud = from somewhere (origin):  Přišla odněkud. (She came from somewhere.)")
            IPNote("Compare: Je někde. (He is somewhere.) vs. Jde někam. (He is going somewhere.) — location vs. direction.")

            IPSection("Sometime — Time")
            IPNote("někdy = sometimes / sometime / at some point (adverb, does not decline):")
            IPNote("Někdy přijdu. (I'll come sometime.)")
            IPNote("Někdy chodím do kina. (I sometimes go to the cinema.)")
            IPNote("Přijdeš někdy? (Will you come sometime?)")

            IPSection("Somehow / Somewhat — Manner and Degree")
            IPNote("nějak = somehow / in some way (adverb, does not decline):")
            IPNote("Nějak to zvládneme. (We'll manage it somehow.)")
            IPNote("Nějak to udělám. (I'll do it somehow.)")
            IPNote("Vypadá nějak smutně. (He looks somehow sad.)")
            IPNote("trochu = a little / somewhat / a bit (adverb of degree, does not decline):")
            IPNote("Jsem trochu unavený. (I'm somewhat / a bit tired.)")
            IPNote("Mluví trochu česky. (She speaks a little Czech.)")
            IPNote("poněkud = somewhat / rather (more formal degree adverb):")
            IPNote("Je to poněkud složité. (It is somewhat complex.)")
            IPNote("Note: nějak = in some manner (somehow), trochu / poněkud = to some degree (somewhat / a little). These cover the two meanings of English 'somewhat'.")

            IPSection("Some Kind Of — Nějaký")
            IPNote("nějaký (m) / nějaká (f) / nějaké (n) = some (kind of) / a certain. Declines as a hard adjective.")
            IPNote("Hledám nějaký hotel. (I'm looking for some hotel.)")
            IPNote("Mám nějaký problém. (I have some / a certain problem.)")
            IPNote("Řekl mi nějakou historku. (He told me some story.)")
            IPNote("Měli jsme nějaké zpoždění. (We had some delay.)")

            IPSection("Negative Counterparts")
            IPNote("IMPORTANT: Czech requires double negation — the verb must also be negated.")
            IPTable(
                headers = listOf("Positive", "Negative", "Meaning"),
                rows = listOf(
                    Triple("někdo", "nikdo", "nobody"),
                    Triple("něco", "nic", "nothing"),
                    Triple("někde", "nikde", "nowhere (static)"),
                    Triple("někam", "nikam", "to nowhere (motion)"),
                    Triple("odněkud", "odnikud", "from nowhere"),
                    Triple("někdy", "nikdy", "never"),
                    Triple("nějak", "nijak", "in no way"),
                    Triple("nějaký", "žádný", "no / none / not any")
                )
            )
            IPNote("Nikdo declension: nikoho / nikomu / nikoho / o nikom / s nikým")
            IPNote("Nic declension: ničeho / ničemu / nic / o ničem / s ničím")
            IPNote("Examples of double negation:")
            IPNote("Nevidím nikoho. (I don't see anyone / I see nobody.)")
            IPNote("Nic neříkám. (I'm not saying anything / I say nothing.)")
            IPNote("Nikdy nejím maso. (I never eat meat.)")
            IPNote("Nikde ho nenajdu. (I can't find him anywhere / I find him nowhere.)")

            IPSection("Anyone, Wherever — The -si and -koli Variants")
            IPNote("The basic někdo / něco / někde... series covers everyday speech. Czech has two additional series built on the same roots:")
            IPNote("• -si suffix (kdosi, cosi, kdesi...): literary and archaic — conveys vagueness or mystery. You will encounter it in books and older texts, but native speakers rarely use it in modern conversation.")
            IPNote("• -koli / -koliv suffix (kdokoli, cokoli, kdekoli...): modern and standard — expresses free choice, meaning 'any... at all' or 'whoever / whatever / wherever'. Used in conditionals, futures, and imperatives.")
            IPNote("Note: -koli and -koliv are fully interchangeable — same meaning, just stylistic variants (kdokoli = kdokoliv, cokoli = cokoliv, etc.).")
            IPNote("There is no Czech word 'nekto' — that is the Russian word некто. The Czech equivalent is někdo, covered above.")
            IPTable4(
                headers = listOf("Category", "Some- (basic)", "-si (literary)", "-koli (free-choice)"),
                rows = listOf(
                    listOf("Person", "někdo", "kdosi", "kdokoli"),
                    listOf("Thing", "něco", "cosi", "cokoli"),
                    listOf("Place (static)", "někde", "kdesi", "kdekoli"),
                    listOf("Place (motion)", "někam", "kamsi", "kamkoli"),
                    listOf("Time", "někdy", "kdysi *", "kdykoli"),
                    listOf("Manner", "nějak", "jaksi", "jakkoli")
                )
            )
            IPNote("* kdysi ≠ někdy — kdysi specifically means 'once (in the past) / once upon a time'. It always refers to past time with a nostalgic tone, not just 'sometime' in general.")

            IPSection("Three-Way Contrast — Examples")
            IPNote("PERSON")
            IPNote("Někdo zavolal. — Someone called. (a real but unspecified person)")
            IPNote("Kdosi zaklepal na dveře. — A certain someone knocked on the door. (mysterious, literary)")
            IPNote("Kdokoli může přijít. — Anyone can come. (no restriction)")
            IPNote("THING")
            IPNote("Řekl mi něco důležitého. — He told me something important.")
            IPNote("Cosi se stalo. — A certain something happened. (literary, vague atmosphere)")
            IPNote("Sněz cokoli chceš. — Eat whatever you want.")
            IPNote("PLACE (static)")
            IPNote("Klíče jsou někde v bytě. — The keys are somewhere in the apartment.")
            IPNote("Kdesi v dálce slyšel zvony. — Somewhere far away he heard bells. (literary)")
            IPNote("Kdekoli bydlíš, jsi vítán. — Wherever you live, you're welcome.")
            IPNote("TIME")
            IPNote("Někdy ho navštívím. — I'll visit him sometime.")
            IPNote("Kdysi jsme byli přátelé. — Once (in the past) we were friends. (specifically past, nostalgic)")
            IPNote("Zavolej kdykoli. — Call anytime. (any time at all)")
            IPNote("MANNER")
            IPNote("Nějak to zvládneme. — We'll manage it somehow.")
            IPNote("Vypadá jaksi unaveně. — He looks kind of/somewhat tired. (informal, colloquial)")
            IPNote("Jakkoli to uděláš, bude to dobře. — However you do it, it will be fine.")

            IPSection("Usage Notes")
            IPNote("The -koli series works best in conditionals, futures, and imperatives. In a simple past statement with no conditional meaning it can sound unnatural.")
            IPNote("The -si series appears mainly in literature and formal writing. Learners should be able to recognise it in texts but do not need to actively use it in speech.")
            IPNote("jaksi carries a slightly informal, colloquial undertone — it implies approximation or mild uncertainty: 'kind of', 'sort of', 'somehow'.")

            IPSection("What about Vocative?")
            IPNote("Indefinite pronouns and adverbs have no vocative. The vocative case is for directly addressing someone by name or title. You would not address 'someone' (you don't know who they are) or 'something' in the vocative. Not applicable here.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun IPSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun IPNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun IPTable4(
    headers: List<String>,
    rows: List<List<String>>,
    weights: List<Float> = listOf(0.75f, 0.65f, 0.65f, 0.85f)
) {
    Spacer(modifier = Modifier.height(6.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                headers.forEachIndexed { i, h ->
                    Text(
                        text = h,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ButtonBlue,
                        modifier = Modifier.weight(weights[i])
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 4.dp),
                color = Color.LightGray,
                thickness = 0.5.dp
            )
            rows.forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                ) {
                    row.forEachIndexed { i, cell ->
                        Text(
                            text = cell,
                            fontSize = if (i == 0) 13.sp else 12.sp,
                            fontWeight = if (i == 0) FontWeight.Normal else FontWeight.Bold,
                            color = if (i == 0) Color.DarkGray else Color.Black,
                            modifier = Modifier.weight(weights[i])
                        )
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun IPTable(
    headers: List<String>,
    rows: List<Triple<String, String, String>>,
    weights: List<Float> = listOf(0.7f, 0.9f, 1.6f)
) {
    Spacer(modifier = Modifier.height(6.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                headers.forEachIndexed { i, h ->
                    Text(
                        text = h,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ButtonBlue,
                        modifier = Modifier.weight(weights[i])
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 4.dp),
                color = Color.LightGray,
                thickness = 0.5.dp
            )
            rows.forEach { (a, b, c) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                ) {
                    Text(text = a, fontSize = 13.sp, color = Color.DarkGray, modifier = Modifier.weight(weights[0]))
                    Text(text = b, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(weights[1]))
                    Text(text = c, fontSize = 12.sp, fontStyle = FontStyle.Italic, color = Color.Gray, modifier = Modifier.weight(weights[2]))
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}
