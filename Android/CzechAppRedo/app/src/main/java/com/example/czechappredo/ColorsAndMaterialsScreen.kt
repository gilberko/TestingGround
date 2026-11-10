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
fun ColorsAndMaterialsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Colors & Materials", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // ── Colors ────────────────────────────────────────────────────
            CMSection("Colors")
            CMNote("Color words in Czech are adjectives and agree in gender with the noun they describe. The forms given below are masculine / feminine / neuter.")
            CMNote("Example: černý kůň (black horse, masc) — černá kočka (black cat, fem) — černé auto (black car, neut)")
            Spacer(modifier = Modifier.height(4.dp))
            CMRow("barva", "color", "f. — the noun meaning \"color\"; the adjective forms below describe which color")
            CMRow("černý / černá / černé", "black")
            CMRow("bílý / bílá / bílé", "white")
            CMRow("zelený / zelená / zelené", "green")
            CMRow("růžový / růžová / růžové", "pink")
            CMRow("oranžový / oranžová / oranžové", "orange")
            CMRow("červený / červená / červené", "red")
            CMRow("hnědý / hnědá / hnědé", "brown")
            CMRow("modrý / modrá / modré", "blue", "světle modrý / světle modrá / světle modré = light blue")
            CMRow("fialový / fialová / fialové", "purple / violet")
            CMRow("šedý / šedá / šedé", "gray")
            CMRow("tyrkysový / tyrkysová / tyrkysové", "turquoise")
            CMRow("žlutý / žlutá / žluté", "yellow")
            CMRow("zlatý / zlatá / zlaté", "gold", "e.g. zlatá medaile — gold medal")
            CMRow("stříbrný / stříbrná / stříbrné", "silver")
            CMRow("bronzový / bronzová / bronzové", "bronze")

            // ── Materials ─────────────────────────────────────────────────
            CMSection("Materials")
            CMNote("Each material has a noun form and an adjective form. The adjective is used to describe what something is made of and also agrees in gender.")
            Spacer(modifier = Modifier.height(4.dp))
            CMRow("kámen / kamenný", "stone / (made of) stone")
            CMRow("dřevo / dřevěný", "wood / wooden")
            CMRow("hliník / hliníkový", "aluminium / aluminium (adj)")
            CMRow("ocel / ocelový", "steel / (made of) steel")
            CMRow("písek / písčitý", "sand / sandy")
            CMRow("sklo / skleněný", "glass / (made of) glass", "e.g. skleněná láhev — glass bottle")
            CMRow("plast / plastový", "plastic / (made of) plastic", "e.g. plastové hračky — plastic toys")
            CMRow("kov / kovový", "metal / metallic (made of metal)", "e.g. kovová krabice — metal box. For a metallic paint or sheen use metalický: metalická barva / metalický lak.")
            CMRow("papír / papírový", "paper / (made of) paper")
            CMNote("When a material has no common adjective you can always say z + genitive: šaty z lycry (a dress made of Lycra), hrad z písku (a sand castle).")

            CMSection("Metals")
            CMRow("železo / železný", "iron / (made of) iron", "e.g. železná brána — iron gate. The clothes iron is žehlička.")
            CMRow("zlato / zlatý", "gold / golden", "zlatý is both the colour and \"made of gold\": zlatý prsten — gold ring")
            CMRow("stříbro / stříbrný", "silver / (made of) silver")
            CMRow("bronz / bronzový", "bronze / (made of) bronze")
            CMRow("olovo / olověný", "lead / (made of) lead", "e.g. olověný vojáček — lead toy soldier. bezolovnatý benzín = unleaded petrol.")

            CMSection("Fabrics and Clothing")
            CMRow("látka / látkový", "fabric / (made of) fabric", "e.g. látková taška — fabric/cloth bag. Also tkanina (woven fabric, more formal).")
            CMRow("hedvábí / hedvábný", "silk / silk, silky", "hedvábný = made of silk (hedvábná košile) and also silky-smooth (hedvábné vlasy). hebký = soft to the touch.")
            CMRow("bavlna / bavlněný", "cotton / (made of) cotton", "e.g. bavlněné tričko — cotton T-shirt")
            CMRow("kůže / kožený", "leather / (made of) leather", "kůže is also skin. e.g. kožená bunda — leather jacket")
            CMRow("semiš / semišový", "suede / (made of) suede", "e.g. semišové boty — suede shoes")
            CMRow("elastan / elastanový", "spandex / elastane", "Czech uses elastan for what Americans call spandex")
            CMRow("lycra", "Lycra", "a brand name for elastane; usually z lycry (made of Lycra)")
            CMRow("latex / latexový", "latex / (made of) latex", "e.g. latexové rukavice — latex gloves")
            CMRow("guma / gumový", "rubber / (made of) rubber", "e.g. gumové holínky — rubber boots. guma is also an eraser and an elastic band. pryž / pryžový is the technical word.")

            CMSection("Natural and Building Materials")
            CMRow("hlína / hliněný", "dirt, soil, clay / earthen, clay", "e.g. hliněný hrnec — clay pot. Careful: hlína (soil) ≠ hliník (aluminium).")
            CMRow("špína / špinavý", "dirt (filth) / dirty", "the \"dirty\" kind of dirt, not soil")
            CMRow("uhlí / uhelný", "coal / coal (adj)", "n., uncountable. e.g. uhelný důl — coal mine")
            CMRow("uhlík / uhlíkový", "carbon / carbon (adj)", "uhlí (coal) ≠ uhlík (carbon). uhlíkové vlákno = carbon fibre (colloquially karbon).")
            CMRow("diamant / diamantový", "diamond / (made of) diamond", "a cut diamond is also briliant")
            CMRow("azbest / azbestový", "asbestos / asbestos (adj)")
            CMRow("sklolaminát / sklolaminátový", "fiberglass / (made of) fiberglass", "glass-reinforced plastic (boats, car parts). skelná vata = glass-wool insulation.")
            CMRow("kryptonit / kryptonitový", "kryptonite / (made of) kryptonite")

            CMSection("Liquids, Gases and Fuels")
            CMRow("tekutina / tekutý", "fluid, liquid / liquid, fluid", "In Czech physics tekutiny covers both liquids and gases — same as English \"fluid\". In everyday speech tekutina = a liquid (pijte hodně tekutin).")
            CMRow("kapalina / kapalný", "liquid (technical) / liquid", "e.g. kapalný dusík — liquid nitrogen")
            CMRow("plyn / plynný", "gas / gaseous", "plynný = in a gas state. plynový = running on gas: plynový sporák — gas stove. (US \"gas\" for a car = benzín.)")
            CMRow("oheň / ohnivý", "fire / fiery", "e.g. ohnivý drak — fire dragon")
            CMRow("olej / olejový", "oil / oil (adj)", "e.g. olejová barva — oil paint. Crude oil = ropa.")
            CMRow("inkoust / inkoustový", "ink / ink (adj)", "e.g. inkoustová tiskárna — inkjet printer")
            CMRow("palivo", "fuel", "n. pohonné hmoty = motor fuels (at a petrol station)")
            CMRow("benzín / benzínový", "gasoline, petrol / petrol (adj)", "English \"benzine\" ≈ Czech benzín. Careful: benzen = benzene (the chemical).")
            CMRow("nafta / naftový", "diesel / diesel (adj)", "Diesel is not a type of gasoline — it's a separate, heavier fuel. Officially motorová nafta; diesel in Czech usually means the engine or a diesel car.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CMSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun CMNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun CMRow(czech: String, english: String, note: String = "") {
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
