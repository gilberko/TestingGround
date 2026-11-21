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
fun BasicWordsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Basic Words and Expressions", fontSize = 17.sp, fontWeight = FontWeight.Bold) },
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
            // ── Yes & No ──────────────────────────────────────────────────
            BWSection("Yes & No")
            BWRow("ano", "yes", "formal / written form")
            BWRow("jo", "yes", "informal, spoken")
            BWRow("ne", "no")
            BWRow("no", "well… / yeah (colloquial)", "careful — this is NOT 'no' (that's ne). A spoken filler / particle: No, nevím. = Well, I don't know. No jo. = Yeah, right. No tak! = Come on!")

            // ── Connectors ────────────────────────────────────────────────
            BWSection("Connectors")
            BWRow("a", "and")
            BWRow("nebo", "or")
            BWRow("ale", "but")
            BWRow("avšak", "however / but", "formal / written; usually opens the clause: Chtěl jsem přijít, avšak nemohl jsem. = I wanted to come, however I couldn't.")
            BWRow("nicméně", "nevertheless / nonetheless / however", "contrastive, slightly formal: Bylo to drahé, nicméně jsme to koupili. = It was expensive; nevertheless, we bought it.")
            BWRow("taky / také", "also / too", "taky is the colloquial spoken form; také is the formal/written form. Já taky. = Me too.")
            BWRow("tak", "so / well / then")
            BWRow("i", "and / also / even", "literary variant of 'a'; i...i = both...and; e.g. i já = me too")
            BWRow("znovu / znova", "again", "znovu is the standard written form; znova is a colloquial variant — more common in Moravia and in spoken Czech. Both are fully correct and interchangeable.")
            BWRow("zase / zas", "again (colloquial)", "another common variant of znovu; zas is the clipped spoken form. Often carries a slight nuance of 'yet again' or 'once more', though in everyday speech it's essentially interchangeable. zase can also mean 'on the other hand': Já zase nemám čas. = I, on the other hand, don't have time.")
            BWRow("opět", "again", "formal / written variant of znovu and zase: Opět prší. = It's raining again.")
            BWRow("anebo", "or (emphatic)", "stronger variant of nebo; implies 'or else'")
            BWRow("jako", "as a / like", "jako + role/profession: Pracuji jako programátor. (man) / Pracuji jako manažerka. (woman). Also means \"like\" in comparisons: Je to jako doma. = It's like home.")
            BWNote("In Czech, job descriptions use no article after jako — just the noun directly. Women use the feminine form of the profession: manažer (m.) → manažerka (f.), programátor (m.) → programátorka (f.).")
            BWRow("pak", "then / afterwards", "common in spoken Czech — temporal sequence")
            BWRow("potom", "then / afterwards", "neutral variant; pak and potom are largely interchangeable")
            BWNote("More formal alternatives: nato = right after that; poté = after that (written/formal). Example: Nejdřív jsem pracoval, pak jsem šel domů. — First I worked, then I went home.")

            // ── Aby — So That / In Order To ──────────────────────────────
            BWSection("Aby — So That / In Order To")
            BWNote("aby is the Czech equivalent of Russian чтобы — a conjunction that introduces a purpose or goal clause ('so that' / 'in order to'). Like Russian чтобы, it pairs with a verb in a past-tense-shaped form: aby conjugates for person and number, and the verb that follows takes its L-participle (the same form used to build the past tense, e.g. dělal, pomohl, mohla), agreeing in gender and number with the subject of that clause.")
            BWRow("abych", "so that I…", "1st person singular: Přišel jsem, abych ti pomohl. = I came so that I could help you.")
            BWRow("abys", "so that you…", "2nd person singular, informal: Volám ti, abys nezapomněl. = I'm calling you so you won't forget.")
            BWRow("aby", "so that he/she/it…", "3rd person singular: Naučila se česky, aby mohla číst knihy v originále. = She learned Czech so she could read books in the original.")
            BWRow("abychom", "so that we…", "1st person plural: Sešli jsme se, abychom to probrali. = We met up so that we could discuss it.")
            BWRow("abyste", "so that you…", "2nd person plural / formal: Pospěšte si, abyste nepřišli pozdě. = Hurry up so you won't be late.")
            BWRow("aby", "so that they…", "3rd person plural — same form as 3rd person singular: Zavřeli okno, aby jim nebyla zima. = They closed the window so they wouldn't be cold.")
            BWNote("More examples: Zavřel okno, aby mu nebyla zima. = He closed the window so he wouldn't be cold (mu = dative 'to him', nebyla agrees with the neuter-feeling 'zima' as feminine). Chci, abys byl šťastný. = I want you to be happy (lit. 'I want, so-that-you were happy' — Czech often uses aby where English uses an infinitive after verbs like chtít).")
            BWNote("See also: Connecting Words → Connecting Sentences (Learning hub) for the full conjugation table and more sentence patterns.")

            // ── There Is / There Was ─────────────────────────────────────
            BWSection("There Is / There Was")
            BWNote("Czech has no expletive 'there' like English 'there is' or French 'il y a'. You simply use the verb být (to be) — je / jsou for present, byl / byla / bylo / byli / byly for past — and put the location wherever it fits naturally in the sentence. Word order is flexible; the location often comes first.")
            BWRow("Na stole je kniha.", "There is a book on the table.", "lit. 'On the table is a book.' — je = is, singular")
            BWRow("Na stole jsou knihy.", "There are books on the table.", "jsou = are, plural")
            BWRow("V lednici je mléko.", "There is milk in the fridge.")
            BWRow("V parku jsou děti.", "There are children in the park.")
            BWRow("Není tu nikdo.", "There is no one here.", "není = is not; double negation with nikdo (no one). tu = here.")
            BWRow("Nejsou tu žádné židle.", "There are no chairs here.", "nejsou = are not; žádné = none / not any")
            BWNote("Past tense agrees with the gender and number of the subject (the thing that 'is there'). The l-participle of být takes 4 endings: byl (m.), byla (f.), bylo (n.), byli/byly (pl.).")
            BWRow("Na stole byla kniha.", "There was a book on the table.", "kniha is feminine → byla")
            BWRow("Na stole byl chléb.", "There was bread on the table.", "chléb is masculine → byl")
            BWRow("Na stole bylo jablko.", "There was an apple on the table.", "jablko is neuter → bylo")
            BWRow("Na stole byly knihy.", "There were books on the table.", "knihy is feminine plural → byly")
            BWRow("V parku byli lidé.", "There were people in the park.", "lidé is animate masculine plural → byli (with -i)")
            BWNote("Optional intensifier tam (there) can be added for emphasis or to point at a specific place: Byla tam kniha. = There was a book (there). The verbs existovat (to exist) and nacházet se (to be located) are more formal alternatives, used in writing.")

            // ── Maybe, Something, Anything ───────────────────────────────
            BWSection("Maybe, Something, Anything")
            BWNote("Czech indefinite words for uncertain quantities and possibilities. Note the ně- prefix: it marks indefiniteness (něco = something, někdo = someone, někde = somewhere). Compare to the negative ni- prefix already seen: nic / nikdo / nikde.")
            BWRow("možná", "maybe / perhaps", "the most common word for 'maybe'. Možná přijdu. = Maybe I'll come. Goes at the start of the clause or after the verb. Neutral in tone.")
            BWRow("snad", "perhaps / hopefully", "softer than možná — often carries a hopeful tone. Snad to půjde. = Hopefully it'll work out.")
            BWRow("asi", "probably / approximately", "expresses likelihood. Asi přijde. = He'll probably come. Also used for rough numbers: asi deset = about ten.")
            BWRow("třeba", "maybe / for example", "colloquial: Třeba přijde. = Maybe he'll come. Also 'for example': Vezmi si třeba jablko. = Take an apple, for example. In je třeba it means 'it is necessary': Je třeba to udělat. = It needs to be done.")
            BWRow("něco", "something","Něco vidím. = I see something. Něco k jídlu? = Something to eat?")
            BWRow("někdo", "someone / somebody", "Někdo zvoní. = Someone is ringing (the bell).")
            BWRow("někde", "somewhere", "Někde jsem to viděl. = I saw it somewhere.")
            BWRow("někdy", "sometimes / sometime", "Někdy chodím do kina. = I sometimes go to the cinema. Also: Přijď někdy. = Come by sometime.")
            BWRow("nějaký", "some / a / some kind of", "indefinite adjective; agrees in gender. Nějaký člověk volal. = Some person called. Máš nějakou otázku? = Do you have a (any) question?")
            BWNote("Pattern summary: ně- = indefinite (some-), ni- = negative (no-), vše-/každý = universal (every-). Examples: něco / nic / všechno (something / nothing / everything), někdo / nikdo / každý (someone / no one / everyone), někde / nikde / všude (somewhere / nowhere / everywhere), někdy / nikdy / vždy (sometimes / never / always). Learning these triplets is one of the fastest ways to expand your everyday vocabulary.")

            // ── Today, Yesterday, Tomorrow ───────────────────────────────
            BWSection("Today, Yesterday, Tomorrow")
            BWRow("dnes", "today")
            BWRow("dneska", "today", "colloquial form, interchangeable with dnes in everyday speech")
            BWRow("včera", "yesterday")
            BWRow("zítra", "tomorrow")
            BWNote("Czech has no articles. Time words go directly into the sentence: Jdu tam dnes. = I'm going there today. Přijdeš zítra? = Will you come tomorrow?")

            // ── Before, After, Previously & In The Future ────────────────
            BWSection("Before, After, Previously & In The Future")
            BWRow("dříve", "before / previously / earlier", "Dříve jsem bydlel v Brně. = I used to live in Brno before / I lived in Brno earlier.")
            BWRow("předtím", "before that / beforehand", "Předtím jsem o tom nevěděl. = Before that, I didn't know about it. See also Connecting Sentences for než / dříve než / předtím než (\"before\" + a full clause).")
            BWRow("před + instr.", "before (preposition — time or place)", "před obědem = before lunch; před domem = in front of the house. před takes the Instrumental case.")
            BWRow("po + loc.", "after (preposition)", "po obědě = after lunch. po takes the Locative case. The adverb 'then / afterwards' is already covered by pak / potom above.")
            BWRow("v budoucnu / v budoucnosti", "in the future", "V budoucnu se to změní. = In the future, this will change.")
            BWRow("brzy / brzo", "soon / early", "brzo is the spoken form. Přijdu brzy. = I'll come soon. Also 'early': Vstávám brzy. = I get up early.")
            BWRow("zanedlouho", "before long / shortly", "one word: Zanedlouho se setmí. = It will get dark before long.")

            // ── Here, There & Right Now ───────────────────────────────────
            BWSection("Here, There & Right Now")
            BWRow("tady", "here / right here", "the most common word for 'here' in everyday speech: Bydlím tady. = I live here.")
            BWRow("tu", "here", "neutral, very common too, especially as an unstressed word: Je tu někdo? = Is anyone here?")
            BWRow("zde", "here", "formal / written — signs and official texts: Zde se nekouří. = No smoking here.")
            BWRow("tam", "there", "Bydlím tam. = I live there. Jdi tam. = Go there. Pairs with tady the way 'here / there' do in English.")
            BWRow("teď", "now", "Teď nemám čas. = I don't have time now.")
            BWRow("nyní", "now", "formal / written variant of teď: Obchod je nyní zavřený. = The shop is now closed.")
            BWRow("právě", "just / right now", "Právě jsem přišel domů. = I just came home. Also means 'exactly': Právě proto. = Exactly because of that.")
            BWRow("právě teď", "just now / right now", "combines právě (just/exactly) + teď (now)")
            BWRow("hned", "right away / immediately", "implies immediacy: Přijdu hned. = I'll come right away.")
            BWRow("hned teď", "right now (this very moment)", "slightly stronger than právě teď — right this instant")
            BWRow("přímo tady", "right here", "přímo = directly / exactly; emphasizes the specific location")

            // ── Still, Already, Yet ──────────────────────────────────────
            BWSection("Still, Already, Yet (ještě / už)")
            BWNote("These two adverbs are the Czech equivalents of Russian ещё and уже. They mark whether an action is ongoing or has finished — together they cover most uses of English 'still', 'already', 'yet', 'anymore', and 'one more'.")
            BWRow("ještě", "still / yet / more", "ongoing or additional. Ještě pracuju. = I'm still working. Ještě jedno pivo. = One more beer. Ještě ne. = Not yet.")
            BWRow("už", "already / now (with negation: anymore)", "completed or changed state. Už jsem doma. = I'm already home. Už nepracuju. = I don't work anymore.")
            BWRow("pořád", "still / all the time / constantly", "colloquial and very common: Pořád tu bydlí. = He still lives here. Pořád prší. = It keeps raining (all the time). Can sound slightly annoyed: Pořád si stěžuješ! = You're always complaining!")
            BWRow("stále", "still / constantly", "more neutral / formal equivalent of pořád: Stále pracuje. = He is still working.")
            BWRow("ještě ne", "not yet","Ještě ne, počkej chvíli. = Not yet, wait a moment.")
            BWRow("už ano / už jo", "already / yes already", "Už ano. = Yes, already. / Affirmative answer to 'Are you done yet?'")
            BWRow("ještě jednou", "one more time / once more", "Zopakuj to ještě jednou. = Repeat it one more time.")
            BWRow("ještě nikdy", "never yet / never before", "double negative with a negated verb: Ještě nikdy jsem tam nebyl. = I've never been there before.")
            BWRow("už nikdy", "never again", "Už nikdy to neudělám. = I'll never do it again.")
            BWNote("Emphatic negation: combine už with vůbec (at all / in general) to intensify a negative statement into 'not at all' / 'absolutely not'. Here už doesn't mean 'already' — it acts as an intensifying particle that sharpens the negation, much like English 'at all' sharpens 'not'.")
            BWRow("vůbec", "at all / in general", "the actual carrier of the 'at all' meaning; už only intensifies it. Vůbec nerozumím. = I don't understand at all.")
            BWRow("už vůbec ne", "not at all / absolutely not", "Líbí se ti to? — Už vůbec ne! = Do you like it? — Not at all! Stronger and more dismissive than a plain 'ne'.")
            BWRow("už vůbec nerozumím", "I don't understand at all", "Už vůbec nerozumím, co tím myslíš. = I have no idea at all what you mean by that.")
            BWRow("to se mi už vůbec nelíbí", "I don't like that at all", "už vůbec strengthens nelíbí (don't like) into a much stronger rejection than nelíbí se mi alone.")
            BWNote("Mental model: ještě = the situation hasn't changed yet (or there's more to come). už = the situation has changed — something started, or with ne- something stopped. už vůbec ne = a separate, emphatic pattern: už here intensifies vůbec's negation rather than marking a change of state.")

            // ── Suddenly, All Of A Sudden & Totally ───────────────────────
            BWSection("Suddenly, All Of A Sudden & Totally")
            BWRow("náhle", "suddenly", "more formal / written. Náhle přestalo pršet. = It suddenly stopped raining.")
            BWRow("najednou", "suddenly / all of a sudden", "the most common, colloquial word. Najednou začal křičet. = All of a sudden he started shouting. Also means 'all at once / in one go': Udělej to najednou. = Do it all at once.")
            BWRow("úplně", "totally / completely", "a different word from vůbec (at all) — also appears in Adverbs. Souhlasím úplně. = I totally agree.")

            // ── Very — Velmi / Hodně / Moc ───────────────────────────────
            BWSection("Very — Velmi / Hodně / Moc")
            BWNote("Czech has three common words for 'very / a lot', each with a different register:")
            BWRow("velmi", "very", "formal/literary — most natural before adjectives and adverbs: velmi dobrý (very good), velmi rychle (very fast). Rarely used with verbs.")
            BWRow("hodně", "a lot / very", "neutral/colloquial — works before adjectives, adverbs, and also with verbs: hodně dobrý (very good), hodně se usmívá (smiles a lot), studuju hodně (I study a lot).")
            BWRow("moc", "very much / a lot", "familiar/casual — most frequent in everyday speech; often interchangeable with hodně: moc dobrý, moc děkuji (thanks a lot). Also a noun: moc = power.")
            BWNote("Quick rule: very + adjective/adverb → velmi in formal contexts, hodně/moc informally. With verbs meaning 'a lot' → hodně or moc, not velmi.")
            BWNote("Examples: Je velmi inteligentní. (formal) / Je hodně chytrý. (neutral) / Je moc hodný. (warm/casual)")

            // ── Only, Just, Nothing But ──────────────────────────────────
            BWSection("Only, Just, Nothing But")
            BWRow("jen / jenom", "only / just", "interchangeable; jen is slightly shorter, jenom slightly more emphatic")
            BWRow("pouze", "only", "more formal than jen; used in written Czech and formal speech — identical in meaning")
            BWRow("jen jedno jablko", "only one apple", "example of jen as a restrictive particle")
            BWRow("teprve", "only / not until", "implies 'not sooner than expected': Teprve teď rozumím. = Only now do I understand.")
            BWRow("teprve teď", "only now", "emphasizes that something is happening later than expected")
            BWNote("jen/jenom = 'only' as a restriction (I only want water = Chci jen vodu.). teprve = 'only' as in 'not until now' — there's an implied sense that it took longer than expected.")

            // ── Never, Nowhere, No One ───────────────────────────────────
            BWSection("Never, Nowhere, No One")
            BWNote("Czech requires double negation — use these negative words together with a negated verb. See also: Learning → Negation.")
            BWRow("nikdy", "never", "Nikdy nejím maso. = I never eat meat.")
            BWRow("nikde", "nowhere / not anywhere", "Nikde ho nevidím. = I don't see him anywhere.")
            BWRow("nikdo", "no one / nobody", "Nikdo nepřišel. = No one came.")

            // ── Always, Everywhere, Everyone ─────────────────────────────
            BWSection("Always, Everywhere, Everyone")
            BWRow("vždy / vždycky", "always", "vždy is slightly more formal/written; vždycky is more colloquial — both very common")
            BWRow("pokaždé", "every time", "po (each) + každé (each); Pokaždé se směje. = He laughs every time.")
            BWRow("všude", "everywhere", "Hledám tě všude. = I'm looking for you everywhere.")
            BWRow("každý", "everyone / each one", "masculine singular form; gender agreement: každý muž (every man), každá žena (every woman), každé dítě (every child)")
            BWRow("všichni", "everyone / all of them", "plural for mixed or male groups; all-female group: všechny")
            BWNote("každý = each individual person (emphasizes one by one). všichni = all of them as a group. Both are common; the choice depends on whether you're thinking of individuals or the whole group.")

            // ── Even, Neither...Nor... ────────────────────────────────────
            BWSection("Even, Neither...Nor...")
            BWRow("ani", "not even / even (in negative)", "Ani jedno jablko jsem nedostal. = I didn't get even one apple. Requires a negative verb.")
            BWRow("ani … ani …", "neither … nor …", "Nechci ani kávu, ani čaj. = I want neither coffee nor tea. Always used with a negative verb.")
            BWRow("dokonce", "even (additive / emphatic)", "Dokonce přišel včas. = He even came on time. Works in positive sentences — adds a sense of surprise or extra emphasis.")
            BWNote("ani = 'not even' (requires a negated verb). dokonce = 'even' in the sense of 'surprisingly also' — works in positive sentences without any negation.")

            // ── While, Between & Against ──────────────────────────────────
            BWSection("While, Between & Against")
            BWRow("zatímco", "while / whereas", "introduces simultaneity or contrast. Zatímco já vařím, ty prostíráš stůl. = While I cook, you set the table.")
            BWRow("mezi + instr.", "between / among", "static location, Instrumental case. Sedím mezi kamarády. = I'm sitting among friends. Motion 'to a spot between' uses the Accusative instead.")
            BWRow("proti + dat.", "against", "Dative case. Jsem proti tomu. = I'm against that. Hraje proti nám. = He's playing against us.")
            BWRow("chvíle / chvilka", "a moment / a while", "chvíle = a while (f. noun); chvilka = diminutive, a little while")
            BWRow("na chvíli / chvilku", "for a while / for a moment", "accusative after na: Odskočím na chvíli. = I'll step away for a moment.")
            BWRow("za chvíli", "in a moment / in a little while", "near future. Přijdu za chvíli. = I'll come in a moment.")
            BWRow("před chvílí", "a moment ago / just now", "Byl tady před chvílí. = He was here a moment ago.")
            BWRow("chvíli počkej", "wait a moment", "casual imperative. Chvíli počkej, hned jsem. = Wait a moment, I'll be right there.")

            // ── Because Of — Kvůli ────────────────────────────────────────
            BWSection("Because Of — Kvůli")
            BWRow("kvůli + dat.", "because of / for the sake of", "a preposition — always followed by the Dative case")
            BWRow("Kvůli válce museli odejít.", "Because of the war, they had to leave.", "válka (war) → Dative válce")
            BWRow("Nejeli jsme kvůli špatnému počasí.", "We didn't go because of the bad weather.", "špatné počasí → Dative špatnému počasí")
            BWRow("Udělal jsem to kvůli tobě.", "I did it because of you / for your sake.", "ty → Dative tobě")
            BWNote("kvůli + noun gives the cause; protože + a full clause does the same with a verb: Odešli kvůli válce. = Odešli, protože byla válka. (They left because there was a war.) The genitive (kvůli války) is a regional dialect form — standard Czech uses the Dative.")

            // ── Possible & Not Possible — Lze / Nelze ─────────────────────
            BWSection("Possible & Not Possible — Lze / Nelze")
            BWRow("lze", "it is possible / one can", "formal, impersonal + infinitive: Lze platit kartou. = One can pay by card.")
            BWRow("nelze", "it is not possible / one cannot", "Tady nelze parkovat. = Parking is not possible here. Na to nelze zapomenout. = That can't be forgotten.")
            BWNote("lze / nelze never change form — no person, no gender, no tense of their own (past: bylo možné / nebylo možné). Very common on signs, in instructions and in official texts. In everyday speech people usually say jde / nejde (To nejde. = That's not possible / It doesn't work.) or je možné / není možné.")

            // ── Allowed, Not Allowed & Forbidden ──────────────────────────
            BWSection("Allowed, Not Allowed & Forbidden")
            BWRow("dovoleno / povoleno", "allowed / permitted", "Je to dovoleno? = Is it allowed? Parkování povoleno. = Parking permitted.")
            BWRow("smí se", "one may / it's allowed", "impersonal form of smět (to be allowed): Smí se tu kouřit? = Is smoking allowed here?")
            BWRow("nesmí se", "one must not / not allowed", "Tady se nesmí kouřit. = You can't smoke here. Personal: Nesmíš to říct. = You mustn't say it.")
            BWRow("není dovoleno / není povoleno", "is not allowed / not permitted", "Fotografování není dovoleno. = Photography is not allowed.")
            BWRow("zakázáno", "forbidden / prohibited", "official and strong: Kouření je zakázáno. = Smoking is forbidden.")
            BWRow("zákaz + gen.", "ban / prohibition (noun)", "the typical sign wording: Zákaz kouření = No smoking. Zákaz vstupu = No entry. Zákaz vjezdu = No entry for vehicles.")
            BWNote("dovolen / povolen / zakázán agree with the noun: Vstup zakázán. (m.) · Kouření zakázáno. (n.) · Ta kniha je zakázaná / zakázána. (f.). For must / have to / need to and the verbs dovolit / zakázat, see Simple Dictionary → Need To, Have To, Allowed.")

            // ── Correct, Super & Examples ─────────────────────────────────
            BWSection("Correct, Super & Examples")
            BWRow("správný / správná / správné", "correct / right (adj.)", "agrees in gender: správný čas (m.), správná odpověď (f.) = the correct answer, správné řešení (n.)")
            BWRow("správně", "correctly / That's right!", "adverb: Odpověděl správně. = He answered correctly. On its own as a reply: Správně! = Correct! / Right!")
            BWRow("super", "super / great", "colloquial loanword — never changes its ending: To je super! = That's great! super film, super den. Standard alternative: skvělý / skvělé (To je skvělé!).")
            BWRow("příklad", "example", "masculine noun: Dej mi příklad. = Give me an example. Plural: příklady.")
            BWRow("například (např.)", "for example / e.g.", "Mám rád ovoce, například jablka. = I like fruit, for example apples. Colloquial: třeba. Literary: kupříkladu.")

            // ── For A Long Time — Dlouho ──────────────────────────────────
            BWSection("For A Long Time — Dlouho")
            BWRow("dlouho", "for a long time / long", "adverb of time. Čekal jsem dlouho. = I waited a long time.")
            BWRow("příliš dlouho", "too long", "Nečekal jsem příliš dlouho. = I didn't wait too long.")
            BWRow("Dlouho jsme se neviděli.", "It's been a while. / Long time no see.", "lit. 'We haven't seen each other for a long time' — the standard Czech way to say 'it's been a while' between people meeting again.")
            BWRow("Je to dlouho, co jsme se neviděli.", "It's been a long time since we've seen each other.", "more explicit form of the same idea")
            BWNote("'Dlouho' is an adverb (how long). The closest Czech equivalent to English 'it's been a while' is 'Dlouho jsme se neviděli.' — literally 'We haven't seen each other for a long time.'")

            // ── Odd and Even (Numbers) ────────────────────────────────────
            BWSection("Odd and Even (Numbers)")
            BWNote("Used in mathematics, page numbers, street address numbering, and everyday scheduling.")
            BWRow("sudý", "even (number)", "2, 4, 6, 8… — sudé číslo = even number; sudá strana = even side")
            BWRow("lichý", "odd (number)", "1, 3, 5, 7… — liché číslo = odd number; lichá strana = odd side")
            BWRow("sudá čísla", "even numbers")
            BWRow("lichá čísla", "odd numbers")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun BWSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun BWNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun BWRow(czech: String, english: String, note: String = "") {
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
