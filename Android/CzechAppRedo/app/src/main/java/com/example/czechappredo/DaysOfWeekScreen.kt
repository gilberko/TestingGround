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
fun DaysOfWeekScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Days and Months", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            DWSection("Days of the Week")
            DWRow("pondělí", "Monday")
            DWRow("úterý", "Tuesday")
            DWRow("středa", "Wednesday")
            DWRow("čtvrtek", "Thursday")
            DWRow("pátek", "Friday")
            DWRow("sobota", "Saturday")
            DWRow("neděle", "Sunday")

            DWSection("On [day] — v / ve + Accusative")
            DWRow("v pondělí", "on Monday", "pondělí = neuter; Accusative = Nominative — no change")
            DWRow("v úterý", "on Tuesday", "úterý = neuter; Accusative = Nominative — no change")
            DWRow("ve středu", "on Wednesday", "středa (f.) → středu in Accusative; ve before stř- cluster")
            DWRow("ve čtvrtek", "on Thursday", "čtvrtek (m.inanim.) → Accusative = Nominative — no change; ve before čtv- cluster")
            DWRow("v pátek", "on Friday", "pátek (m.inanim.) → Accusative = Nominative — no change")
            DWRow("v sobotu", "on Saturday", "sobota (f.) → sobotu in Accusative")
            DWRow("v neděli", "on Sunday", "neděle (f.) → neděli in Accusative")
            DWNote("ve is used instead of v before words starting with consonant clusters that are hard to pronounce after v: ve středu, ve čtvrtek.")
            DWNote("v/ve does not always take Accusative — it depends on meaning. When expressing location (where something is), it takes Locative: v Praze (in Prague), ve škole (at school), v Brně (in Brno). When expressing time — specifically days of the week and certain time expressions — it takes Accusative: v pondělí, ve středu, v sobotu. Czech treats days of the week as an Accusative time expression, as if you are moving into that day.")
            DWNote("Historical note: In old/archaic Czech, v/ve + Accusative was also used for directional motion — physically moving into a place. Example: vběhnout v dům (to run into a house), where v dům is Accusative of dům. In modern Czech this usage has almost entirely disappeared, replaced by do + Genitive: vběhnout do domu. You may still encounter it in literary texts or old proverbs, but it is not used in everyday speech.")

            DWSection("Recurring Events — Using the Present Tense")
            DWNote("To say \"on Fridays\" (as a recurring habit), Czech uses the same day form as for a single day. The habitual meaning comes from the verb: chodím (I habitually go) vs. jdu (I am going now).")
            DWRow("V pátek chodím do posilovny.", "On Fridays I go to the gym.")
            DWRow("V neděli a v úterý jdu do kanceláře.", "I go to the office on Sundays and Tuesdays.")

            DWSection("Every [Day] — každý / každou / každé + Accusative")
            DWNote("každý / každou / každé agrees with the gender and case of the noun that follows.")
            DWRow("každé pondělí", "every Monday", "pondělí = neuter")
            DWRow("každé úterý", "every Tuesday", "úterý = neuter")
            DWRow("každou středu", "every Wednesday", "středa = fem., Acc. středu")
            DWRow("každý čtvrtek", "every Thursday", "čtvrtek = masc. inanim., Acc. unchanged")
            DWRow("každý pátek", "every Friday")
            DWRow("každou sobotu", "every Saturday", "sobota = fem., Acc. sobotu")
            DWRow("každou neděli", "every Sunday", "neděle = fem., Acc. neděli")
            DWRow("každý týden", "every week")
            DWRow("každý měsíc", "every month")

            DWSection("A Week / Half a Week / Weekend")
            DWRow("týden", "a week / one week")
            DWRow("půl týdne", "half a week", "půl + Genitive: týdne")
            DWRow("víkend", "the weekend")
            DWRow("o víkendu", "on the weekend / on weekends", "o + Locative: víkendu")
            DWRow("přes víkend", "over the weekend")

            DWSection("Months of the Year")
            DWNote("All 12 month names are masculine nouns. Use v + Locative to say \"in [month]\".")
            DWRow("leden  →  v lednu", "January / in January")
            DWRow("únor  →  v únoru", "February / in February")
            DWRow("březen  →  v březnu", "March / in March")
            DWRow("duben  →  v dubnu", "April / in April")
            DWRow("květen  →  v květnu", "May / in May")
            DWRow("červen  →  v červnu", "June / in June")
            DWRow("červenec  →  v červenci", "July / in July")
            DWRow("srpen  →  v srpnu", "August / in August")
            DWRow("září  →  v září", "September / in September", "indeclinable — no change")
            DWRow("říjen  →  v říjnu", "October / in October")
            DWRow("listopad  →  v listopadu", "November / in November")
            DWRow("prosinec  →  v prosinci", "December / in December")

            DWSection("Month Name Origins")
            DWNote("Czech month names come from Old Slavic and describe natural phenomena, agricultural activities, and seasonal events observed in the Bohemian countryside — unlike most European languages, which use Latin/Roman god names.")
            DWRow("leden", "January", "led = ice — the month of ice")
            DWRow("únor", "February", "nor = burrow — animals still hiding in their winter dens")
            DWRow("březen", "March", "bříza = birch tree — when birch sap starts to rise")
            DWRow("duben", "April", "dub = oak tree — when oak trees begin to bud")
            DWRow("květen", "May", "květ = flower / blossom — the month of flowering")
            DWRow("červen", "June", "červ = worm / grub — named for the kermes, a crimson dye insect; also the root of červená (red)")
            DWRow("červenec", "July", "literally 'little červen' — the second crimson month in the old Slavic calendar")
            DWRow("srpen", "August", "srp = sickle — the harvest month")
            DWRow("září", "September", "zářit = to shine / glow — the month of shining autumn light")
            DWRow("říjen", "October", "říje = rut — the season when deer and stags mate")
            DWRow("listopad", "November", "list = leaf + padat = to fall — leaf fall; the most transparent Czech month name")
            DWRow("prosinec", "December", "from an archaic root meaning pale / grey light — the darkest month of the year")

            DWSection("Talking About Months")
            DWRow("V lednu letím do Londýna.", "In January I'm flying to London.")
            DWRow("Každý únor je tam festival.", "Every February there's a festival there.")
            DWRow("minulý měsíc", "last month")
            DWRow("příští měsíc", "next month")
            DWRow("příští červenec", "next July")
            DWRow("minulý únor", "last February")
            DWRow("minulý týden", "last week")
            DWRow("příští týden", "next week")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun DWSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun DWRow(czech: String, english: String, note: String = "") {
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

@Composable
private fun DWNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}
