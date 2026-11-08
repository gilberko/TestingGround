package com.example.czechappredo

import androidx.compose.foundation.background
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

private val NBEWarn = Color(0xFFE65100)

private val NBECases = listOf("1 Nom", "2 Gen", "3 Dat", "4 Acc", "5 Voc", "6 Loc", "7 Ins")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NounConjugationByEndingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Noun Conjugation By Endings", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
            NBENote("The textbook way is to memorize 14 model nouns (vzory: pán, hrad, muž, stroj, předseda, soudce, žena, růže, píseň, kost, město, moře, kuře, stavení). This screen takes a different route: look at how the noun ENDS in the nominative, ask whether it is a male person/animal, and most of the endings follow. The full pattern tables still live under Conjugation Examples → Noun Conjugation.")
            NBENote("In the tables below, each cell shows the ending, then an example. Cells in orange marked ⚠ are places where the same ending commonly goes two ways — those are the bits you still have to learn word by word.")

            // ---------------------------------------------------------------
            NBESection("Your Observations — Checked")
            NBERow("1. Nominative -a → Genitive -y  ✔ Correct", "žena → ženy. And it is even stronger than it looks: it also works for MASCULINE nouns in -a — předseda → předsedy, kolega → kolegy. Rare exception: Greek-origin neuters in -a (drama → dramatu, schéma → schématu).")
            NBERow("2. -e stays the same in the Genitive  ✔ Correct", "Works in all three genders: růže → růže (fem.), moře → moře (neut.), soudce → soudce (masc.). The common exception is the kuře type (young animals and beings: kuře, kotě, štěně, dítě), which inserts -et-: kuře → kuřete.")
            NBERow("3. -e and -i/-í stay the same across many cases  ✔ Mostly correct", "-e nouns keep -e in Nom, Gen and Voc, then switch to -i in Dat and Loc (růže → růži, moře → moři). -í nouns are even simpler: stavení is identical in EVERY singular case except the Instrumental (stavením). Soft-consonant nouns (muž, stroj, píseň, kost) also lean heavily on -i.")
            NBERow("4. Learning by ending instead of by pattern  ✔ Valid", "The nominative ending predicts the gender and the pattern for the large majority of nouns, and many endings are shared across genders (e.g. Genitive plural -ů for EVERY masculine noun, Instrumental singular -em for every masculine and neuter noun). The 14 patterns collapse into a handful of ending groups plus a few shortcut rules.")
            NBERow("5. Animate Accusative = Genitive, like Russian  ⚠ Half right", "In Czech this happens ONLY in the masculine animate SINGULAR: vidím pána (= Gen pána) but vidím hrad. In the PLURAL Czech does NOT do it, unlike Russian: vidím pány — same -y as vidím hrady, not *pánů. In the plural, animacy shows up in the Nominative instead (páni vs. hrady) and in past-tense agreement (páni byli vs. hrady byly).")
            NBERow("6. pan and the word for woman", "The model noun is pán (lord / gentleman); pan (Mr., the title: pan Novák) declines the same way, just with a short a. Woman = žena (also \"wife\").")

            // ---------------------------------------------------------------
            NBESection("The Idea — Let the Ending Do the Work")
            NBENote("Step 1: what does the noun end in? Step 2: if it is masculine, is it a person/animal (animate) or a thing (inanimate)? That is usually enough to pick the endings.")
            NBERow("-a", "Feminine → like žena (kniha, škola, sestra). If it means a male person → like předseda (kolega, táta, turista).")
            NBERow("-e / -ě", "Usually feminine → like růže (ulice, práce, kuchyně). Some are neuter → like moře (srdce, letiště, hřiště). Male persons → like soudce (správce, průvodce). Young animals/beings → like kuře (kotě, štěně, dítě).  ⚠ This is the one truly ambiguous ending — see Exceptions.")
            NBERow("-o", "Neuter → like město (okno, auto, jablko, kolo).")
            NBERow("-í", "Neuter → like stavení (náměstí, nádraží, cvičení — most nouns in -ní / -tí). Note: some -í and -ý nouns are really adjectives used as nouns (krejčí = tailor, vrátný = doorman) and decline like adjectives.")
            NBERow("Hard consonant (h, ch, k, r, d, t, n, g)", "Masculine → like pán if animate (student, kluk, Čech) or like hrad if inanimate (most, stůl, park).")
            NBERow("Soft consonant (ž, š, č, ř, c, j, ď, ť, ň)", "Masculine → like muž if animate (otec, cizinec, král) or like stroj if inanimate (pokoj, klíč, počítač).  ⚠ But some are feminine — see below.")
            NBERow("Consonant, but feminine", "Every noun in -ost is feminine like kost (radost, místnost, možnost). Others are feminine like píseň / tramvaj (postel, kancelář, tramvaj) or like noc (věc, moc) — these must be learned with their gender.")
            NBERow("-um (foreign neuters)", "Drop -um and decline the rest: centrum → centra, centru (like město); muzeum → muzea, muzeu, but Gen pl muzeí (vowel before -um → soft plural endings).")
            NBENote("Hard vs. soft consonants: hard = h, ch, k, r, d, t, n, g; soft = ž, š, č, ř, c, j, ď, ť, ň; ambiguous = b, f, l, m, p, s, v, z (hotel declines hard, učitel soft). See Letters & Pronunciation → Hard, Soft, and Ambiguous Consonants.")

            // ---------------------------------------------------------------
            NBESection("Shortcuts That Work Across Genders")
            NBENote("Learn these first — they cover a huge share of all forms without any pattern at all.")
            NBERow("Neuter: Nom = Acc = Voc", "Always, singular and plural: město / město / město; města / města / města.")
            NBERow("Vocative plural = Nominative plural", "Always, every gender: ženy!, páni!, města!")
            NBERow("Accusative plural = Nominative plural", "Always, EXCEPT masculine animate (páni → vidím pány; muži → vidím muže).")
            NBERow("Masculine inanimate: Acc sg = Nom sg", "vidím hrad, vidím stroj. Animate takes the Genitive form: vidím pána, vidím muže.")
            NBERow("Dative sg = Locative sg (almost always)", "ženě / ženě, růži / růži, pánovi / pánovi, stroji / stroji. Only hard masc. inanimate and -o neuters may differ (hradu / hradě).")
            NBERow("Nouns in -a share most of the singular", "Gen -y, Acc -u, Voc -o, Ins -ou whether feminine (žena) or masculine (předseda). Only Dat/Loc reveal gender: -ě (ženě) vs. -ovi (předsedovi).")
            NBERow("Nouns in -e share most of the singular", "Nom = Gen = Voc = -e, and Dat/Loc = -i in all genders. Only the Accusative and Instrumental reveal gender: růži / růží (fem.) vs. moře / mořem, soudce / soudcem.")
            NBERow("Instrumental singular", "-em for every masculine and neuter noun (pánem, hradem, mužem, městem, mořem); -ou for -a nouns (ženou, předsedou); -í for feminines in -e or a consonant (růží, písní, kostí).")
            NBERow("Genitive plural", "-ů for EVERY masculine noun (pánů, hradů, mužů, strojů, předsedů, soudců). No ending at all for -a and -o nouns (žen, měst). -í for -e, -í and consonant feminines (moří, stavení, písní, kostí).")
            NBERow("Dative / Locative / Instrumental plural", "Dat -ům / -ám / -ím, Loc -ech / -ách / -ích, Ins -y / -ami / -i / -emi. Rule of thumb: -a nouns that are feminine get the -á- set (ženám, ženách, ženami); soft nouns get the -í- set (mořím, mořích); hard masculines/neuters get -ům / -ech / -y.")

            // ---------------------------------------------------------------
            NBESection("Singular — Tables by Ending")

            NBETable(
                "Ending -a",
                listOf("žena (fem.)", "předseda (masc.)"),
                listOf(
                    listOf("-a\nžena", "-a\npředseda"),
                    listOf("-y\nženy", "-y\npředsedy"),
                    listOf("⚠ -ě\nženě", "-ovi\npředsedovi"),
                    listOf("-u\nženu", "-u\npředsedu"),
                    listOf("-o\nženo", "-o\npředsedo"),
                    listOf("⚠ -ě\nženě", "-ovi\npředsedovi"),
                    listOf("-ou\nženou", "-ou\npředsedou")
                )
            )
            NBENote("⚠ Feminine Dat/Loc -ě changes the consonant before it: k → c (ruka → ruce, matka → matce), h → z (noha → noze, Praha → Praze), r → ř (sestra → sestře).")

            NBETable(
                "Ending -e / -ě",
                listOf("růže (fem.)", "moře (neut.)", "soudce (masc.)", "kuře ⚠"),
                listOf(
                    listOf("-e\nrůže", "-e\nmoře", "-e\nsoudce", "-e\nkuře"),
                    listOf("-e\nrůže", "-e\nmoře", "-e\nsoudce", "⚠ -ete\nkuřete"),
                    listOf("-i\nrůži", "-i\nmoři", "-ovi / -i\nsoudcovi", "⚠ -eti\nkuřeti"),
                    listOf("-i\nrůži", "-e\nmoře", "-e\nsoudce", "-e\nkuře"),
                    listOf("-e\nrůže", "-e\nmoře", "-e\nsoudce", "-e\nkuře"),
                    listOf("-i\nrůži", "-i\nmoři", "-ovi / -i\nsoudcovi", "⚠ -eti\nkuřeti"),
                    listOf("-í\nrůží", "-em\nmořem", "-em\nsoudcem", "⚠ -etem\nkuřetem")
                )
            )
            NBENote("Your observation in action: -e stays put in Nom/Gen/Voc for all of them, and becomes -i in Dat/Loc. kuře (and kotě, štěně, dítě) is the exception that inserts -et-.")

            NBETable(
                "Ending -o",
                listOf("město", "jablko (k/h/ch)"),
                listOf(
                    listOf("-o\nměsto", "-o\njablko"),
                    listOf("-a\nměsta", "-a\njablka"),
                    listOf("-u\nměstu", "-u\njablku"),
                    listOf("-o\nměsto", "-o\njablko"),
                    listOf("-o\nměsto", "-o\njablko"),
                    listOf("⚠ -ě / -u\nměstě / městu", "-u\njablku"),
                    listOf("-em\nměstem", "-em\njablkem")
                )
            )
            NBENote("⚠ Locative: v městě / ve městě and v okně / v oknu both exist; after k, h, ch it is always -u (v jablku).")

            NBETable(
                "Ending -í",
                listOf("stavení (neut.)"),
                listOf(
                    listOf("-í\nstavení"),
                    listOf("-í\nstavení"),
                    listOf("-í\nstavení"),
                    listOf("-í\nstavení"),
                    listOf("-í\nstavení"),
                    listOf("-í\nstavení"),
                    listOf("-ím\nstavením")
                )
            )
            NBENote("The easiest group: nothing changes except the Instrumental. Same for náměstí, nádraží, cvičení.")

            NBETable(
                "Hard consonant (masculine)",
                listOf("pán (anim.)", "hrad (inan.)", "les (inan.) ⚠"),
                listOf(
                    listOf("—\npán", "—\nhrad", "—\nles"),
                    listOf("-a\npána", "⚠ -u\nhradu", "⚠ -a\nlesa"),
                    listOf("-ovi / -u\npánovi", "-u\nhradu", "-u\nlesu"),
                    listOf("-a (= Gen)\npána", "— (= Nom)\nhrad", "— (= Nom)\nles"),
                    listOf("⚠ -e\npane", "-e\nhrade", "-e\nlese"),
                    listOf("-ovi / -u\npánovi", "⚠ -ě / -u\nhradě / hradu", "-e\nlese"),
                    listOf("-em\npánem", "-em\nhradem", "-em\nlesem")
                )
            )
            NBENote("⚠ Genitive of inanimate hard nouns: most take -u (hradu, domu, parku, stolu), but a common group takes -a (lesa, zákona, sýra / sýru) — learn these with the word.")
            NBENote("⚠ Locative: -ě/-e (v hradě, v lese, na stole) or -u (v parku, v domu / v domě, o zákonu / o zákoně). After k, h, ch it is always -u (v parku).")
            NBENote("⚠ Vocative: normally -e (pane!, hrade!), but -u after k, h, ch (kluku!, Čechu!, synu!) and r after a consonant becomes ř (bratr → bratře!, Petr → Petře!).")

            NBETable(
                "Soft consonant (masculine)",
                listOf("muž (anim.)", "stroj (inan.)"),
                listOf(
                    listOf("—\nmuž", "—\nstroj"),
                    listOf("-e\nmuže", "-e\nstroje"),
                    listOf("-ovi / -i\nmužovi / muži", "-i\nstroji"),
                    listOf("-e (= Gen)\nmuže", "— (= Nom)\nstroj"),
                    listOf("⚠ -i\nmuži", "-i\nstroji"),
                    listOf("-ovi / -i\nmužovi / muži", "-i\nstroji"),
                    listOf("-em\nmužem", "-em\nstrojem")
                )
            )
            NBENote("Notice: soft masculines look just like -e nouns once you add an ending — muže / stroje mirror soudce / moře. ⚠ Nouns in -ec have Vocative -če: otec → otče!, chlapec → chlapče!")

            NBETable(
                "Consonant (feminine)",
                listOf("píseň", "kost (-ost)", "noc"),
                listOf(
                    listOf("—\npíseň", "—\nkost", "—\nnoc"),
                    listOf("⚠ -e / -ě\npísně", "⚠ -i\nkosti", "⚠ -i\nnoci"),
                    listOf("-i\npísni", "-i\nkosti", "-i\nnoci"),
                    listOf("— (= Nom)\npíseň", "— (= Nom)\nkost", "— (= Nom)\nnoc"),
                    listOf("-i\npísni", "-i\nkosti", "-i\nnoci"),
                    listOf("-i\npísni", "-i\nkosti", "-i\nnoci"),
                    listOf("-í\npísní", "-í\nkostí", "-í\nnocí")
                )
            )
            NBENote("⚠ The only singular difference is the Genitive: píseň-type takes -e (písně, tramvaje, postele), kost-type and noc-type take -i (kosti, radosti, noci). Everything else is -i / -í, just like růže.")

            // ---------------------------------------------------------------
            NBESection("Plural — Tables by Ending")
            NBENote("Remember: Vocative plural = Nominative plural everywhere, and Accusative plural = Nominative plural except for masculine animate nouns.")

            NBETable(
                "Ending -a",
                listOf("žena (fem.)", "předseda (masc.)"),
                listOf(
                    listOf("-y\nženy", "-ové\npředsedové"),
                    listOf("⚠ —\nžen", "-ů\npředsedů"),
                    listOf("-ám\nženám", "-ům\npředsedům"),
                    listOf("-y\nženy", "-y\npředsedy"),
                    listOf("-y\nženy", "-ové\npředsedové"),
                    listOf("-ách\nženách", "⚠ -ech\npředsedech"),
                    listOf("-ami\nženami", "-y\npředsedy")
                )
            )
            NBENote("⚠ Genitive plural with no ending often inserts an e to break up consonants: matka → matek, sestra → sester. ⚠ Masculine -ga/-ka nouns soften in the Locative: kolega → kolezích.")

            NBETable(
                "Ending -e / -ě",
                listOf("růže (fem.)", "moře (neut.)", "soudce (masc.)", "kuře ⚠"),
                listOf(
                    listOf("-e\nrůže", "-e\nmoře", "-i / -ové\nsoudci", "⚠ -ata\nkuřata"),
                    listOf("⚠ -í\nrůží", "-í\nmoří", "-ů\nsoudců", "⚠ -at\nkuřat"),
                    listOf("-ím\nrůžím", "-ím\nmořím", "-ům\nsoudcům", "⚠ -atům\nkuřatům"),
                    listOf("-e\nrůže", "-e\nmoře", "-e\nsoudce", "⚠ -ata\nkuřata"),
                    listOf("-e\nrůže", "-e\nmoře", "-i / -ové\nsoudci", "⚠ -ata\nkuřata"),
                    listOf("-ích\nrůžích", "-ích\nmořích", "-ích\nsoudcích", "⚠ -atech\nkuřatech"),
                    listOf("-emi\nrůžemi", "-i\nmoři", "-i\nsoudci", "⚠ -aty\nkuřaty")
                )
            )
            NBENote("Again, -e stays -e in Nom/Acc/Voc for feminine and neuter. ⚠ Feminine Genitive plural is either -í (růží, práce → prací) or no ending (ulice → ulic) — both are common.")

            NBETable(
                "Ending -o",
                listOf("město", "jablko (k/h/ch)"),
                listOf(
                    listOf("-a\nměsta", "-a\njablka"),
                    listOf("⚠ —\nměst", "⚠ —\njablek"),
                    listOf("-ům\nměstům", "-ům\njablkům"),
                    listOf("-a\nměsta", "-a\njablka"),
                    listOf("-a\nměsta", "-a\njablka"),
                    listOf("-ech\nměstech", "⚠ -ích / -ách\njablcích"),
                    listOf("-y\nměsty", "-y\njablky")
                )
            )
            NBENote("⚠ Inserted e in the Genitive plural: okno → oken, jablko → jablek. ⚠ After k/h/ch the Locative plural softens: jablcích (also jablkách).")

            NBETable(
                "Ending -í",
                listOf("stavení (neut.)"),
                listOf(
                    listOf("-í\nstavení"),
                    listOf("-í\nstavení"),
                    listOf("-ím\nstavením"),
                    listOf("-í\nstavení"),
                    listOf("-í\nstavení"),
                    listOf("-ích\nstaveních"),
                    listOf("-ími\nstaveními")
                )
            )

            NBETable(
                "Hard consonant (masculine)",
                listOf("pán (anim.)", "hrad (inan.)", "les (inan.)"),
                listOf(
                    listOf("⚠ -i / -ové\npáni / pánové", "-y\nhrady", "-y\nlesy"),
                    listOf("-ů\npánů", "-ů\nhradů", "-ů\nlesů"),
                    listOf("-ům\npánům", "-ům\nhradům", "-ům\nlesům"),
                    listOf("-y (NOT = Gen)\npány", "-y\nhrady", "-y\nlesy"),
                    listOf("= Nom\npáni / pánové", "-y\nhrady", "-y\nlesy"),
                    listOf("-ech\npánech", "-ech\nhradech", "⚠ -ích\nlesích"),
                    listOf("-y\npány", "-y\nhrady", "-y\nlesy")
                )
            )
            NBENote("⚠ Animate Nominative plural -i softens the consonant before it: kluk → kluci, Čech → Češi, doktor → doktoři. Some words prefer -ové (syn → synové) and nouns in -an take -é (občan → občané).")
            NBENote("⚠ After k, h, ch the Locative plural becomes -cích / -zích / -ších: park → parcích, kluk → klucích, Čech → Češích.")

            NBETable(
                "Soft consonant (masculine)",
                listOf("muž (anim.)", "stroj (inan.)"),
                listOf(
                    listOf("⚠ -i / -ové\nmuži", "-e\nstroje"),
                    listOf("-ů\nmužů", "-ů\nstrojů"),
                    listOf("-ům\nmužům", "-ům\nstrojům"),
                    listOf("-e (NOT = Gen)\nmuže", "-e\nstroje"),
                    listOf("= Nom\nmuži", "-e\nstroje"),
                    listOf("-ích\nmužích", "-ích\nstrojích"),
                    listOf("-i\nmuži", "-i\nstroji")
                )
            )
            NBENote("⚠ Nouns in -tel take -é in the Nominative plural: učitel → učitelé, přítel → přátelé (irregular).")

            NBETable(
                "Consonant (feminine)",
                listOf("píseň", "kost (-ost)", "noc"),
                listOf(
                    listOf("-e / -ě\npísně", "-i\nkosti", "-i\nnoci"),
                    listOf("-í\npísní", "-í\nkostí", "-í\nnocí"),
                    listOf("-ím\npísním", "⚠ -em\nkostem", "-ím\nnocím"),
                    listOf("-e / -ě\npísně", "-i\nkosti", "-i\nnoci"),
                    listOf("-e / -ě\npísně", "-i\nkosti", "-i\nnoci"),
                    listOf("-ích\npísních", "⚠ -ech\nkostech", "-ích\nnocích"),
                    listOf("-emi / -ěmi\npísněmi", "⚠ -mi\nkostmi", "-emi\nnocemi")
                )
            )
            NBENote("⚠ The kost type breaks from the rest in the Dat/Loc/Ins plural: kostem, kostech, kostmi (and likewise radostem, radostech, radostmi), while píseň and noc keep the soft -ím / -ích / -emi set.")

            // ---------------------------------------------------------------
            NBESection("Why Masculine Needs Animate vs. Inanimate")
            NBENote("Endings alone can't tell you whether a masculine noun is a living being, and Czech grammar cares about this in exactly four places:")
            NBERow("1. Accusative singular", "Animate = Genitive: Vidím pána / muže. Inanimate = Nominative: Vidím hrad / stroj.")
            NBERow("2. Dative / Locative singular -ovi", "Only animate nouns take -ovi: pánovi, mužovi, soudcovi. Inanimate never do: hradu, stroji.")
            NBERow("3. Nominative plural", "Animate -i / -ové / -é (páni, muži, synové, učitelé); inanimate -y / -e (hrady, stroje).")
            NBERow("4. Agreement in the plural", "Adjectives and past-tense verbs follow it: Ti velcí páni byli tady. vs. Ty velké hrady byly tady.")
            NBENote("Unlike Russian, Czech does NOT use the Genitive for the animate Accusative PLURAL: vidím pány (like hrady), not *pánů. Feminine and neuter nouns never distinguish animacy at all (vidím ženu, vidím kuře).")

            // ---------------------------------------------------------------
            NBESection("Where Endings Don't Tell the Whole Story")
            NBERow("-e can be any gender", "růže (fem.), moře (neut.), soudce (masc.), kuře (neut., -et- type). You need to know the gender — but as shown above, the singular is mostly identical anyway.")
            NBERow("Consonant ending: masculine or feminine?", "Usually masculine, but -ost words are always feminine (radost, místnost) and some others are too (píseň, tramvaj, postel, kancelář, noc, věc). Learn these with their gender.")
            NBERow("Ambiguous consonants b, f, l, m, p, s, v, z", "Can decline hard or soft: hotel → hotely (hard), učitel → učitele (soft).")
            NBERow("The disappearing e", "Many nouns lose an e once an ending is added: pes → psa, otec → otce, den → dne, domeček → domečku.")
            NBERow("Vowel shortening", "A long ů / í in the last syllable often shortens: dům → domu, stůl → stolu, kůň → koně.")
            NBERow("Genuinely irregular", "dítě → děti (plural is feminine-like), člověk → lidé, oko → oči, ucho → uši, ruka → ruce (old dual forms), přítel → přátelé, den (dne, dni, dny / dni).")

            // ---------------------------------------------------------------
            NBESection("How This Simplifies Things")
            NBENote("Instead of 14 patterns × 14 cells (196 forms), you learn:")
            NBERow("1. About six ending groups", "-a, -e, -o, -í, hard consonant, soft consonant (+ the consonant feminines).")
            NBERow("2. The cross-gender shortcuts", "Neuter Nom = Acc = Voc; Voc pl = Nom pl; Gen pl -ů for all masculines; Instr sg -em / -ou / -í; Dat = Loc in the singular.")
            NBERow("3. Two questions per noun", "What does it end in? Is it a male person/animal? Then only the ⚠ spots (Gen -u/-a, Loc -ě/-u, animate Nom pl, consonant changes) need word-by-word memory.")
            NBENote("When in doubt, the complete pattern tables are under Conjugation Examples → Noun Conjugation.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun NBESection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun NBERow(czech: String, english: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)) {
                    append(czech)
                }
            }
        )
        Text(
            text = english,
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(start = 4.dp, top = 1.dp)
        )
    }
}

@Composable
private fun NBENote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

@Composable
private fun NBETable(title: String, columns: List<String>, rows: List<List<String>>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 4.dp)
    ) {
        Text(title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE3F2FD))
                .padding(vertical = 4.dp)
        ) {
            Text("Case", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ButtonBlue, modifier = Modifier.weight(0.7f))
            columns.forEach { col ->
                Text(col, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ButtonBlue, modifier = Modifier.weight(1f))
            }
        }
        HorizontalDivider(color = Color.LightGray)
        rows.forEachIndexed { i, cells ->
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                Text(NBECases[i], fontSize = 12.sp, color = Color.DarkGray, modifier = Modifier.weight(0.7f))
                cells.forEach { cell ->
                    val warn = cell.startsWith("⚠")
                    val lines = cell.split("\n", limit = 2)
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = if (warn) NBEWarn else ButtonBlue)) {
                                append(lines[0])
                            }
                            if (lines.size > 1) {
                                append("\n")
                                withStyle(SpanStyle(color = if (warn) NBEWarn else Color.Black)) {
                                    append(lines[1])
                                }
                            }
                        },
                        fontSize = 12.sp,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            HorizontalDivider(color = Color(0xFFEEEEEE))
        }
    }
}
