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
fun ConditionsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Conditions", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // ── Section 1: Three Words for "If" — Real Conditions ─────────
            ConSection("Three Words for \"If\" — Real Conditions")
            ConNote("Real conditions talk about things that might actually happen. Czech has three words for 'if', each with a slightly different character. In all three cases, use normal present or future tense — no special verb forms needed.")
            ConTable(
                headers = listOf("Word", "Meaning", "Character"),
                rows = listOf(
                    listOf("jestli", "if", "most common in speech; neutral; closest to Russian если"),
                    listOf("pokud", "if / provided that", "slightly more formal; implies \"to the extent that / as long as\""),
                    listOf("když", "when / if", "primarily \"when\"; works for habits and recurring conditions")
                ),
                weights = listOf(0.55f, 0.65f, 1.5f)
            )
            ConNote("For real conditions, all three are often interchangeable. But there is nuance: pokud implies a condition that must be satisfied; když leans toward 'whenever' or 'in the case that'.")
            ConRow("Jestli bude zítra hezké počasí, půjdeme na pláž.", "If the weather is nice tomorrow, we'll go to the beach.")
            ConRow("Pokud budeš mít čas, zavolej mi.", "If you have time, call me.")
            ConRow("Pokud přijdou na čas, začneme.", "Provided they arrive on time, we'll start.")
            ConRow("Když mám čas, čtu knihy.", "When/If I have time, I read books.")
            ConRow("Když prší, zůstávám doma.", "When it rains, I stay home.")
            ConNote("Confirmed: Když = habits. 'Když mám čas, čtu knihy' expresses a recurring pattern, not a one-off event — just like Russian когда for habitual situations. For a specific one-off future event, jestli/pokud are more natural: Jestli bude pršet zítra, nepůjdeme ven. = If it rains tomorrow, we won't go out.")
            ConNote("Structure: jestli / pokud / když + [present or future tense] → result also in present or future. The condition clause can come first or second — word order is flexible.")
            ConNote("Note: jestli also works as 'whether' in indirect questions: Nevím, jestli přijde. = I don't know whether he'll come. This is a separate use — not a condition, just indirect speech.")

            // ── Section 2: The Conditional Particle ───────────────────────
            ConSection("The Conditional Particle — bych, bys, by...")
            ConNote("For hypothetical and counterfactual conditions, Czech uses a conditional mood: a conditional particle + the l-form (past participle) of the verb.")
            ConNote("Russian has the invariant particle 'бы' — one form for all persons. Czech works the same way in meaning, but the particle CONJUGATES for person. This is the key difference: Czech 'by' has six distinct forms.")
            ConTable(
                headers = listOf("Person", "Czech", "Meaning"),
                rows = listOf(
                    listOf("já (I)", "bych", "I would"),
                    listOf("ty (you, sg.)", "bys", "you would"),
                    listOf("on / ona / ono", "by", "he / she / it would"),
                    listOf("my (we)", "bychom", "we would"),
                    listOf("vy (you, pl./formal)", "byste", "you would"),
                    listOf("oni / ony", "by", "they would")
                ),
                weights = listOf(1.0f, 0.65f, 1.0f)
            )
            ConNote("'by' is both the 3rd person form and the base form of the particle. The endings -ch / -s / -chom / -ste are the same person-marking endings as in jsem / jsi / jsme / jste — the same system as the past tense helper.")
            ConNote("Formation: [l-form of main verb] + [by-form]. The l-form agrees with the grammatical subject in gender and number — exactly as in the past tense.")
            ConRow("koupil bych / koupila bych", "I would buy (man speaking / woman speaking)")
            ConRow("koupil bys / koupila bys", "you would buy (man / woman)")
            ConRow("koupil by / koupila by", "he would buy / she would buy")
            ConRow("koupili bychom", "we would buy (mixed or male group)")
            ConRow("koupili byste", "you would buy (plural or formal)")
            ConRow("koupili by", "they would buy")
            ConNote("Word order: the by-form sits in the second position of the clause — the same clitic rule as jsem/jsi in the past tense. 'Koupil bych auto.' is correct. Never start a sentence with the by-form: 'Bych koupil auto.' is wrong.")

            // ── Section 3: Mohl Bych ──────────────────────────────────────
            ConSection("Mohl Bych — A Common Conditional")
            ConNote("One of the most useful conditional phrases in Czech is 'mohl bych' — meaning 'I could' or 'I would be able to'.")
            ConNote("'mohl' is the past l-participle (masculine singular) of the verb moct = 'to be able to / can'. Combined with the by-form, it forms the conditional mood of moct — used for hypothetical ability and polite requests.")
            ConNote("Past tense of moct — full paradigm:")
            ConTable(
                headers = listOf("Person", "Past (masc.)", "Past (fem.)"),
                rows = listOf(
                    listOf("já", "jsem mohl", "jsem mohla"),
                    listOf("ty", "jsi mohl", "jsi mohla"),
                    listOf("on / ona / ono", "mohl / mohla / mohlo", ""),
                    listOf("my", "jsme mohli", "jsme mohly"),
                    listOf("vy", "jste mohli", "jste mohly"),
                    listOf("oni / ony", "mohli", "mohly")
                ),
                weights = listOf(0.9f, 1.1f, 1.1f)
            )
            ConNote("Conditional of moct — mohl bych (all persons):")
            ConTable(
                headers = listOf("Person", "Conditional (masc.)", "Conditional (fem.)"),
                rows = listOf(
                    listOf("já", "mohl bych", "mohla bych"),
                    listOf("ty", "mohl bys", "mohla bys"),
                    listOf("on / ona / ono", "mohl by / mohla by / mohlo by", ""),
                    listOf("my", "mohli bychom", "mohly bychom"),
                    listOf("vy", "mohli byste", "mohly byste"),
                    listOf("oni / ony", "mohli by", "mohly by")
                ),
                weights = listOf(0.9f, 1.15f, 1.05f)
            )
            ConNote("Key distinction:")
            ConNote("  mohl jsem = 'I was able to / I could' — PAST INDICATIVE. It actually happened or was possible.")
            ConNote("  mohl bych = 'I could / I would be able to' — CONDITIONAL. Hypothetical or polite.")
            ConRow("Mohl bych ti pomoci, kdybych měl čas.", "I could help you if I had time. (hypothetical)")
            ConRow("Mohl bych se zeptat?", "Could I ask a question? (polite request)")
            ConRow("Mohla bych dostat sklenici vody?", "Could I have a glass of water? (polite request, woman speaking)")
            ConRow("Mohli bychom to udělat zítra.", "We could do it tomorrow. (possibility)")
            ConRow("Mohl jsem to udělat sám.", "I was able to do it myself. (actual past ability — contrast with conditional)")

            // ── Section 5: Hypothetical Conditions ────────────────────────
            ConSection("Hypothetical Conditions — Kdybych, Kdybys, Kdyby...")
            ConNote("For hypothetical conditions ('If I won the lottery...', 'If I were you...'), Czech uses kdybych/kdybys/kdyby... in the if-clause. This is the direct equivalent of Russian 'если бы'. The difference: Russian keeps 'если' and 'бы' as two separate words; Czech fuses them into one word.")
            ConNote("Russian: если бы + past tense → Czech: kdyby-form + l-form. The l-form is the same past participle used in ordinary past tense sentences — just like Russian's past tense doubles as the conditional form.")
            ConTable(
                headers = listOf("Person", "Czech", "Meaning"),
                rows = listOf(
                    listOf("já", "kdybych", "if I"),
                    listOf("ty", "kdybys", "if you (sg.)"),
                    listOf("on / ona / ono", "kdyby", "if he / she / it"),
                    listOf("my", "kdybychom", "if we"),
                    listOf("vy", "kdybyste", "if you (pl./formal)"),
                    listOf("oni / ony", "kdyby", "if they")
                ),
                weights = listOf(0.9f, 0.9f, 1.0f)
            )
            ConNote("The endings in kdybych / kdybys / kdybychom / kdybyste are the same -ch / -s / -chom / -ste as in bych / bys / bychom / byste. kdyby is the base/3rd-person form. The pattern is the same table — just with 'kdy-' prepended.")
            ConNote("Structure of a hypothetical condition:")
            ConNote("  If-clause:  kdyby-form + l-form of verb")
            ConNote("  Result:     l-form of verb + by-form (bych / bys / by...)")
            ConNote("Both clauses use the l-form. The by-form in the result sits in second position.")
            ConRow("Kdybych vyhrál/a loterii, koupil/a bych auto.", "If I won the lottery, I would buy a car.")
            ConRow("Kdybych byl/a na tvém místě, přijal/a bych tuto práci.", "If I were you (lit. in your place), I would take this job.")
            ConRow("Kdybys měl/a čas, přišel/přišla bys?", "If you had time, would you come?")
            ConRow("Kdyby měl více peněz, koupil by větší dům.", "If he had more money, he would buy a bigger house.")
            ConRow("Kdybychom věděli, řekli bychom ti to.", "If we knew, we would tell you.")
            ConRow("Kdyby přišli včas, stihli by vlak.", "If they came on time, they would catch the train.")
            ConNote("'If I were you' — Czech uses 'kdybych byl/a na tvém místě' (lit. if I were in your place). Czech has no subjunctive 'were' — it uses the same l-form as the past tense: byl (man) / byla (woman). The hypothetical meaning comes from kdybych, not from a special verb form.")
            ConNote("Confirmed: Yes, Czech combines если бы into kdybych/kdybys/kdyby... Just as you observed. And the standalone bych/bys/by... forms appear in the result clause — those are the same system just used separately.")

            // ── Section 6: Past / Counterfactual Conditions ───────────────
            ConSection("Past / Counterfactual Conditions")
            ConNote("Counterfactual conditions talk about things that did NOT happen — a road not taken: 'If I had bought that stock 7 years ago...' Czech handles these in two ways.")
            ConNote("1. Past condition with a present result (most common in practice):")
            ConNote("Use the simple Type 2 form (kdybych + l-form) in the if-clause. The 'pastness' comes from using a perfective verb, which expresses a completed action. This is very common and widely understood.")
            ConRow("Kdybych koupil/a tu akcii před 7 lety, byl/a bych teď bohatý/á.", "If I had bought that stock 7 years ago, I would be rich now.")
            ConRow("Kdybych se víc učil/a, uměl/a bych teď mluvit plynně.", "If I had studied more, I would speak fluently now.")
            ConNote("Here, 'kdybych koupil' (perfective past l-form) signals the completed past action that didn't happen. 'byl/a bych bohatý/á' uses the by-form of být + adjective to express the current hypothetical state.")
            ConNote("2. Past condition with a past result (full Type 3 — explicit past counterfactual):")
            ConNote("Add 'byl/a' before the main verb's l-form in BOTH clauses. This explicitly marks both the condition and the result as events that did not happen in the past.")
            ConRow("Kdybych byl/a víc studoval/a, složil/a bych zkoušku.", "If I had studied more, I would have passed the exam.")
            ConRow("Kdybys byl/a přišel/přišla včas, stihl/a bys vlak.", "If you had arrived on time, you would have caught the train.")
            ConRow("Kdyby počkal, nebyl by zameškal tu příležitost.", "If he had waited, he would not have missed that opportunity.")
            ConNote("Structure of full Type 3:")
            ConNote("  If-clause:  kdyby-form + byl/a + l-form of main verb")
            ConNote("  Result:     byl/a + by-form + l-form of main verb")
            ConNote("Russian comparison: Russian если бы + past tense covers BOTH Type 2 and Type 3 without structural difference — Russian doesn't distinguish them. Czech can distinguish: Type 2 = simple l-form only; Type 3 = byl/a + l-form. In informal spoken Czech, the simpler Type 2 form is frequently used even for past counterfactuals — both are understood from context.")

            // ── Section 7: Summary ─────────────────────────────────────────
            ConSection("Summary — All Three Types")
            ConNote("Quick reference for all three conditional types:")
            ConTable(
                headers = listOf("Type", "If-clause", "Result clause"),
                rows = listOf(
                    listOf("1. Real", "jestli/pokud/když\n+ present/future", "present or future tense"),
                    listOf("2. Hypothetical", "kdyby-form\n+ l-form", "l-form + by-form"),
                    listOf("3. Counterfactual", "kdyby-form + byl/a\n+ l-form", "byl/a + by-form\n+ l-form")
                ),
                weights = listOf(0.85f, 1.1f, 1.05f)
            )
            ConRow("Jestli bude pršet, zůstanu doma.", "Type 1 — If it rains, I'll stay home.")
            ConRow("Kdybych měl auto, odvezl bych tě.", "Type 2 — If I had a car, I would drive you.")
            ConRow("Kdybych byl koupil ten dům, byl bych rád.", "Type 3 — If I had bought that house, I would have been happy.")
            ConNote("English equivalents: Type 1 = 'If it rains, I'll stay.' Type 2 = 'If it rained, I would stay.' Type 3 = 'If it had rained, I would have stayed.'")
            ConNote("Russian comparison: Type 1 uses если + present/future — same structure as Czech. Types 2 and 3 both use если бы + past tense in Russian, no structural distinction between them. Czech distinguishes the two: Type 2 uses kdyby-form + l-form; Type 3 adds a 'byl/a' layer. In casual Czech speech, the Type 2 form is widely used for past counterfactuals too — context (time words like před rokem, tehdy) makes the meaning clear.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ConSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = ButtonBlue
    )
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun ConNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

@Composable
private fun ConRow(czech: String, english: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = czech,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = english,
            fontSize = 15.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(start = 6.dp)
        )
    }
}

@Composable
private fun ConTable(headers: List<String>, rows: List<List<String>>, weights: List<Float>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            headers.forEachIndexed { i, h ->
                Text(
                    text = h,
                    modifier = Modifier
                        .weight(weights[i])
                        .padding(horizontal = 4.dp, vertical = 4.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = ButtonBlue
                )
            }
        }
        HorizontalDivider(color = ButtonBlue, thickness = 1.dp)
        rows.forEach { row ->
            Row(modifier = Modifier.fillMaxWidth()) {
                row.forEachIndexed { i, cell ->
                    Text(
                        text = cell,
                        modifier = Modifier
                            .weight(weights[i])
                            .padding(horizontal = 4.dp, vertical = 5.dp),
                        fontSize = 13.sp,
                        color = Color.Black
                    )
                }
            }
            HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp)
        }
    }
}
