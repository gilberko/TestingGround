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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

/**
 * One example: what a learner might naively expect (shown struck through),
 * what is actually written, the English meaning, and why it changes.
 */
private data class RocExample(
    val expected: String,
    val actual: String,
    val english: String,
    val why: String = ""
)

private data class RocTopic(
    val name: String,
    val subtitle: String,
    val points: List<String>,
    val examples: List<RocExample>,
    val accentNote: String? = null,
    val note: String? = null
)

private data class RocSection(
    val title: String,
    val topics: List<RocTopic>
)

private val rocSections = listOf(
    RocSection(
        title = "Before We Start: Hyphen & Accents",
        topics = listOf(
            RocTopic(
                name = "Do we have to keep the hyphen (-)?",
                subtitle = "Yes — always, when the pronoun comes after or inside the verb",
                points = listOf(
                    "Pronoun AFTER the verb (ênclise): hyphen is mandatory — comprá-lo, dá-me, levanta-te",
                    "Pronoun INSIDE the verb (mesóclise, future/conditional): two hyphens — comprá-lo-ei, dar-te-ia",
                    "Pronoun BEFORE the verb (próclise): NO hyphen — não o comprei, já te disse",
                    "This is the official spelling rule (Acordo Ortográfico 1990, Base XVII). Writing \"comprálo\" or \"comprá lo\" is a spelling mistake",
                    "Two pronouns glued together (mo, to, lho) are written as one word: dá-mo (one hyphen, after the verb)"
                ),
                examples = listOf(
                    RocExample("compra-lo", "comprá-lo", "to buy it", "Your version was missing the accent — see below why it's needed."),
                    RocExample("comprálo", "comprá-lo", "to buy it", "The hyphen cannot be dropped."),
                    RocExample("não comprei-o", "não o comprei", "I didn't buy it", "não pulls the pronoun before the verb — then there is no hyphen at all.")
                ),
                accentNote = "When the final -r/-s/-z disappears, the stress stays where it was — on what is now the last syllable. " +
                    "Portuguese spelling must then show that stress: final stressed open -a → á (comprá-lo, fá-lo); " +
                    "final stressed closed -e/-o → ê/ô (vendê-lo, fê-lo, pô-lo); " +
                    "final -i/-u → no accent needed, because words ending in i/u are stressed on the last syllable by default (parti-lo, di-lo, pu-lo) — " +
                    "except after another vowel (hiatus), where í is written: possuí-lo, atraí-lo, construí-lo."
            )
        )
    ),
    RocSection(
        title = "1. Verbs Ending in -r, -s, -z + o, a, os, as",
        topics = listOf(
            RocTopic(
                name = "-r / -s / -z disappears, o → lo",
                subtitle = "o, a, os, as become lo, la, los, las",
                points = listOf(
                    "The verb loses its final r, s or z",
                    "The pronoun gets an l: o → lo, a → la, os → los, as → las",
                    "Works with any form ending in -r, -s, -z: infinitives, tu forms, nós forms, past tense fiz/fez/quis/pus…",
                    "Hyphen: mandatory",
                    "Historical reason: the old form was comprar-lo; the r assimilated to the l (comprallo) and then simplified to comprá-lo"
                ),
                examples = listOf(
                    RocExample("comprar o", "comprá-lo", "to buy it", "-r dropped; acute added on the stressed final a."),
                    RocExample("vender a", "vendê-la", "to sell it (fem.)", "-r dropped; circumflex for the stressed closed e."),
                    RocExample("partir os", "parti-los", "to break them", "-r dropped; no accent — a final i is stressed anyway."),
                    RocExample("pôr o", "pô-lo", "to put it", "-r dropped; pôr already had ô, it stays."),
                    RocExample("possuir o", "possuí-lo", "to own it", "-r dropped; í marks that u and i are separate syllables (hiatus)."),
                    RocExample("Eu fiz o.", "Eu fi-lo.", "I did it.", "-z dropped; final i — no accent."),
                    RocExample("Ele fez o.", "Ele fê-lo.", "He did it.", "-z dropped; ê (closed e, stressed)."),
                    RocExample("Ele faz o.", "Ele fá-lo.", "He does it.", "-z dropped; á (stressed open a)."),
                    RocExample("Ele diz o.", "Ele di-lo.", "He says it.", "-z dropped; no accent."),
                    RocExample("Ele traz o.", "Ele trá-lo.", "He brings it.", "-z dropped; á."),
                    RocExample("Ele quis o.", "Ele qui-lo.", "He wanted it.", "-s dropped; no accent."),
                    RocExample("Eu pus o.", "Eu pu-lo.", "I put it.", "-s dropped; final u — no accent."),
                    RocExample("Tu comes o.", "Tu come-lo.", "You eat it.", "-s dropped; NO accent: the stress stays on CO- (come), it was never on the last syllable."),
                    RocExample("Nós compramos o.", "Nós compramo-lo.", "We buy it.", "-s dropped; no accent: stress stays on -PRA-.")
                ),
                accentNote = "Accent is ADDED only when the dropped letter leaves a stressed final a/e/o exposed (comprá, vendê, fá, fê). " +
                    "When the stress is earlier in the word (come-lo, compramo-lo) nothing is added. pôr keeps its existing ô.",
                note = "Old/literary bonus: eis + o → ei-lo (\"here it is\")."
            )
        )
    ),
    RocSection(
        title = "2. Verbs Ending in a Nasal Sound + o, a, os, as",
        topics = listOf(
            RocTopic(
                name = "-m / -ão / -õe: add an n",
                subtitle = "o, a, os, as become no, na, nos, nas",
                points = listOf(
                    "Nothing is removed from the verb",
                    "An n is added to the pronoun: o → no, a → na, os → nos, as → nas",
                    "Reason: it keeps the nasal sound flowing into the pronoun instead of two vowels clashing",
                    "Hyphen: mandatory"
                ),
                examples = listOf(
                    RocExample("Eles compram o.", "Eles compram-no.", "They buy it.", "-m verb: o → no."),
                    RocExample("Eles dão a.", "Eles dão-na.", "They give it (fem.).", "-ão verb: a → na."),
                    RocExample("Ele põe o.", "Ele põe-no.", "He puts it.", "-õe verb: o → no."),
                    RocExample("Elas têm as.", "Elas têm-nas.", "They have them (fem.).", "-m verb: as → nas."),
                    RocExample("Fizeram os.", "Fizeram-nos.", "They did them.", "-m verb: os → nos.")
                ),
                accentNote = "No accents added or removed — dão, põe, têm keep exactly the accents they already had.",
                note = "Watch out: põe-nos / fizeram-nos can also mean \"puts US\" / \"they made US\" (nos = us). Context decides."
            )
        )
    ),
    RocSection(
        title = "3. Mesóclise — Pronoun Inside Future & Conditional",
        topics = listOf(
            RocTopic(
                name = "Future: infinitive + pronoun + ending",
                subtitle = "comprar-ei → comprar + o + ei → comprá-lo-ei",
                points = listOf(
                    "The future and conditional are built as infinitive + ending (comprar + ei, comprar + ia)",
                    "When the pronoun goes after the verb, it is placed BETWEEN the two parts",
                    "With o/a/os/as the infinitive part follows rule 1: -r drops, o → lo, accent as needed",
                    "Irregular stems (far-, dir-, trar-) do the same: far + o → fá-lo",
                    "Two hyphens, both mandatory"
                ),
                examples = listOf(
                    RocExample("Comprarei-o.", "Comprá-lo-ei.", "I will buy it.", "-r of comprar drops; á added; o → lo."),
                    RocExample("Venderemos-a.", "Vendê-la-emos.", "We will sell it (fem.).", "-r drops; ê added."),
                    RocExample("Partirão-os.", "Parti-los-ão.", "They will break them.", "-r drops; no accent (final i)."),
                    RocExample("Farei-o.", "Fá-lo-ei.", "I will do it.", "Stem far-: -r drops; á."),
                    RocExample("Direi-o.", "Di-lo-ei.", "I will say it.", "Stem dir-: -r drops; no accent."),
                    RocExample("Trarei-o.", "Trá-lo-ei.", "I will bring it.", "Stem trar-: -r drops; á."),
                    RocExample("Darei-te.", "Dar-te-ei.", "I will give you.", "te does not change the verb — the r stays."),
                    RocExample("Lembrarei-me.", "Lembrar-me-ei.", "I will remember.", "Reflexive me — r stays.")
                ),
                accentNote = "Same as rule 1: comprá-, vendê-, fá-, trá- get an accent because their stressed final vowel is exposed; parti-, di- don't."
            ),
            RocTopic(
                name = "Conditional",
                subtitle = "comprar-ia → comprá-lo-ia",
                points = listOf(
                    "Exactly the same mechanism, with the conditional endings -ia, -ias, -íamos, -iam"
                ),
                examples = listOf(
                    RocExample("Compraria-o.", "Comprá-lo-ia.", "I would buy it.", "-r drops; á added."),
                    RocExample("Faria-a.", "Fá-la-ia.", "I would do it (fem.).", "far- stem; á."),
                    RocExample("Diria-o.", "Di-lo-ia.", "I would say it.", "dir- stem; no accent."),
                    RocExample("Diria-lhe.", "Dir-lhe-ia.", "I would tell him/her.", "lhe doesn't change the verb.")
                ),
                note = "In real life: mesóclise is formal/written. If a word like não, que, nunca, já, também, alguém comes before the verb, " +
                    "the pronoun goes BEFORE it instead (não o comprarei — no hyphen). In everyday speech people avoid it altogether: " +
                    "\"vou comprá-lo\" (I'm going to buy it) or \"comprava-o\" (imperfect used as conditional)."
            )
        )
    ),
    RocSection(
        title = "4. Combining Two Object Pronouns",
        topics = listOf(
            RocTopic(
                name = "Indirect (to whom) + direct (it/them)",
                subtitle = "me + o = mo, lhe + a = lha, nos + o = no-lo …",
                points = listOf(
                    "Order: indirect first, then direct",
                    "me + o, a, os, as → mo, ma, mos, mas",
                    "te + o, a, os, as → to, ta, tos, tas",
                    "lhe + o, a, os, as → lho, lha, lhos, lhas",
                    "lhes + o, a, os, as → lho, lha, lhos, lhas (same! — the plural \"to them\" is lost, context tells)",
                    "nos + o, a, os, as → no-lo, no-la, no-los, no-las (the s of nos drops, o → lo — rule 1 again)",
                    "vos + o, a, os, as → vo-lo, vo-la, vo-los, vo-las",
                    "The combined pronoun does not change the verb: dar + mo → dar-mo (the r stays)"
                ),
                examples = listOf(
                    RocExample("Ele deu-me-o.","Ele deu-mo.", "He gave it to me.", "me + o → mo."),
                    RocExample("Não me o dês.", "Não mo dês.", "Don't give it to me.", "não → pronoun before the verb, no hyphen."),
                    RocExample("Já te o disse.", "Já to disse.", "I already told you (it).", "te + o → to."),
                    RocExample("Dei-lhe a.", "Dei-lha.", "I gave it (fem.) to him/her.", "lhe + a → lha."),
                    RocExample("Dei-lhes os.", "Dei-lhos.", "I gave them to them.", "lhes + os → lhos."),
                    RocExample("Ele mostrou-nos o.", "Ele mostrou-no-lo.", "He showed it to us.", "nos + o → no-lo (literary)."),
                    RocExample("Quero dar-te o.", "Quero dar-to.", "I want to give it to you.", "dar keeps its r — only a bare o/a after the verb removes it.")
                ),
                accentNote = "No accents involved. mo/to/lho are one word; no-lo/vo-lo have their own internal hyphen.",
                note = "In spoken EP, mo, to, lho are still common (much more than in Brazil), but people also often just drop the direct one: " +
                    "\"Deu-me\" instead of \"Deu-mo\". no-lo and vo-lo sound bookish."
            )
        )
    ),
    RocSection(
        title = "5. de, em, por, a + o, a, os, as",
        topics = listOf(
            RocTopic(
                name = "Preposition + definite article",
                subtitle = "These are obligatory — you never write \"de o livro\"",
                points = listOf(
                    "de + o, a, os, as → do, da, dos, das",
                    "em + o, a, os, as → no, na, nos, nas",
                    "por + o, a, os, as → pelo, pela, pelos, pelas (old form \"per\" + lo)",
                    "a + o, a, os, as → ao, à, aos, às",
                    "With um/uma: em + um → num, numa (very common in EP); de + um → dum, duma (optional, more informal); a + um → never contracts (a um)",
                    "para does not contract in writing (para o) — \"pro\" is speech only"
                ),
                examples = listOf(
                    RocExample("Venho de o Porto.", "Venho do Porto.", "I come from Porto.", "de + o → do."),
                    RocExample("Estou em a cozinha.", "Estou na cozinha.", "I'm in the kitchen.", "em + a → na."),
                    RocExample("Passei por o parque.", "Passei pelo parque.", "I went through the park.", "por + o → pelo."),
                    RocExample("Vou a a praia.", "Vou à praia.", "I'm going to the beach.", "a + a → à (crase)."),
                    RocExample("Vou a os Açores.", "Vou aos Açores.", "I'm going to the Azores.", "a + os → aos — no accent."),
                    RocExample("Vivo em um prédio.", "Vivo num prédio.", "I live in a building.", "em + um → num.")
                ),
                accentNote = "Only à/às get an accent: the grave accent ( ` ) marks crase — two a's fused into one. ao/aos have no accent because no two a's met."
            )
        )
    ),
    RocSection(
        title = "6. de, em, a + Demonstratives",
        topics = listOf(
            RocTopic(
                name = "de + este / esse / aquele / isto / isso / aquilo",
                subtitle = "The e of de disappears",
                points = listOf(
                    "de + este, esta, estes, estas → deste, desta, destes, destas",
                    "de + esse, essa, esses, essas → desse, dessa, desses, dessas",
                    "de + aquele, aquela, aqueles, aquelas → daquele, daquela, daqueles, daquelas",
                    "de + isto, isso, aquilo → disto, disso, daquilo",
                    "Also: de + aqui, aí, ali → daqui, daí, dali"
                ),
                examples = listOf(
                    RocExample("Gosto de este bolo.", "Gosto deste bolo.", "I like this cake.", "de + este → deste."),
                    RocExample("Não sei nada de isso.", "Não sei nada disso.", "I don't know anything about that.", "de + isso → disso."),
                    RocExample("Saio de aqui às cinco.", "Saio daqui às cinco.", "I leave here at five.", "de + aqui → daqui.")
                )
            ),
            RocTopic(
                name = "em + este / esse / aquele / isto / isso / aquilo",
                subtitle = "em becomes n-",
                points = listOf(
                    "em + este, esta, estes, estas → neste, nesta, nestes, nestas",
                    "em + esse, essa, esses, essas → nesse, nessa, nesses, nessas",
                    "em + aquele, aquela, aqueles, aquelas → naquele, naquela, naqueles, naquelas",
                    "em + isto, isso, aquilo → nisto, nisso, naquilo",
                    "em + aqui/aí/ali does NOT contract (and is rarely needed — aqui already means \"in here\")"
                ),
                examples = listOf(
                    RocExample("Moro em esta rua.", "Moro nesta rua.", "I live on this street.", "em + esta → nesta."),
                    RocExample("Em aquele dia…", "Naquele dia…", "On that day…", "em + aquele → naquele."),
                    RocExample("Não pensei em isso.", "Não pensei nisso.", "I didn't think about that.", "em + isso → nisso.")
                )
            ),
            RocTopic(
                name = "a + aquele / aquilo",
                subtitle = "Only the aquel- forms contract with a",
                points = listOf(
                    "a + aquele, aquela, aqueles, aquelas → àquele, àquela, àqueles, àquelas",
                    "a + aquilo → àquilo",
                    "a + este / esse / isto / isso → NO contraction: a este, a esse, a isto, a isso"
                ),
                examples = listOf(
                    RocExample("Fui a aquela loja.", "Fui àquela loja.", "I went to that shop.", "a + aquela → àquela (crase)."),
                    RocExample("Não ligues a aquilo.", "Não ligues àquilo.", "Don't pay attention to that.", "a + aquilo → àquilo."),
                    RocExample("Dei o livro a este rapaz.", "Dei o livro a este rapaz.", "I gave the book to this boy.", "No change — a + este never contracts.")
                ),
                accentNote = "àquele / àquilo get a grave accent ( ` ) — the a of the preposition fuses with the a of aquele (crase), exactly like à."
            )
        )
    ),
    RocSection(
        title = "7. com, de, em + Personal Pronouns",
        topics = listOf(
            RocTopic(
                name = "com + mim / ti / si / nós / vós",
                subtitle = "These fuse into special forms",
                points = listOf(
                    "com + mim → comigo",
                    "com + ti → contigo",
                    "com + si → consigo (also the polite \"with you\": Posso falar consigo?)",
                    "com + nós → connosco (EP spelling with -sc-; Brazil writes conosco)",
                    "com + vós → convosco",
                    "com + ele, ela, eles, elas → NO contraction: com ele, com ela",
                    "Exception (grammarians disagree): before todos, mesmos, próprios, outros or a number, \"com nós\" / \"com vós\" is used — com nós todos, com nós dois. Many speakers say connosco todos too."
                ),
                examples = listOf(
                    RocExample("Vens com mim?", "Vens comigo?", "Are you coming with me?", "com + mim → comigo."),
                    RocExample("Vou com ti.", "Vou contigo.", "I'm going with you.", "com + ti → contigo."),
                    RocExample("Ela jantou com nós.", "Ela jantou connosco.", "She had dinner with us.", "com + nós → connosco."),
                    RocExample("Falei com ele.", "Falei com ele.", "I spoke with him.", "No change — com + ele never fuses.")
                )
            ),
            RocTopic(
                name = "de / em + ele / ela / eles / elas",
                subtitle = "These DO contract",
                points = listOf(
                    "de + ele, ela, eles, elas → dele, dela, deles, delas (also the possessive \"his/her/their\" — see Possessives)",
                    "em + ele, ela, eles, elas → nele, nela, neles, nelas",
                    "de / em + mim, ti, si, nós, vós → NO contraction: de mim, em ti, de si, em nós, de vós",
                    "a / por / para + any pronoun → NO contraction: a ele, por ela, para mim"
                ),
                examples = listOf(
                    RocExample("Gosto de ele.", "Gosto dele.", "I like him.", "de + ele → dele."),
                    RocExample("Confio em ela.", "Confio nela.", "I trust her.", "em + ela → nela."),
                    RocExample("Penso em ti.", "Penso em ti.", "I think about you.", "No change — em + ti doesn't fuse."),
                    RocExample("Ele gosta de mim.", "Ele gosta de mim.", "He likes me.", "No change — de + mim doesn't fuse.")
                ),
                note = "See section 11: when ele/ela is the SUBJECT of an infinitive, careful writing does NOT contract: apesar de ela estar cansada."
            )
        )
    ),
    RocSection(
        title = "8. Spelling Changes in Conjugation (Keeping the Sound)",
        topics = listOf(
            RocTopic(
                name = "Why does the spelling change?",
                subtitle = "c and g change their sound depending on the next vowel",
                points = listOf(
                    "c before a/o/u = K sound (casa); c before e/i = S sound (cedo)",
                    "g before a/o/u = hard G (gato); g before e/i = ZH sound (gente)",
                    "ç is the S sound before a/o/u — and ç is never written before e/i",
                    "When an ending changes the vowel after c/g, the spelling must change so the SOUND of the stem stays the same",
                    "These are spelling changes, not irregular verbs — the pronunciation is perfectly regular"
                ),
                examples = listOf(
                    RocExample("ficar → eu ficei", "eu fiquei", "I stayed", "c + e would sound like S (\"fisei\"); qu keeps the K sound."),
                    RocExample("pagar → eu pagei", "eu paguei", "I paid", "g + e would sound ZH (\"pajei\"); gu keeps the hard G."),
                    RocExample("começar → eu começei", "eu comecei", "I started", "ç is never written before e — plain c already sounds S there."),
                    RocExample("conhecer → eu conheco", "eu conheço", "I know", "c + o would sound K; ç keeps the S."),
                    RocExample("vencer → eu venco", "eu venço", "I win", "Same: c → ç before o."),
                    RocExample("proteger → eu protego", "eu protejo", "I protect", "g + o would sound hard G; j keeps the ZH sound."),
                    RocExample("dirigir → eu dirigo", "eu dirijo", "I drive", "Same: g → j before o."),
                    RocExample("conseguir → eu conseguo", "eu consigo", "I manage", "gu → g before o (u only needed before e/i). Also e → i, see next card."),
                    RocExample("distinguir → eu distinguo", "eu distingo", "I distinguish", "gu → g before o.")
                ),
                accentNote = "No accents involved — only letters change (c↔qu, g↔gu, c↔ç, g↔j)."
            ),
            RocTopic(
                name = "Stem vowel changes: dormir → durmo, sentir → sinto",
                subtitle = "A real sound change (not just spelling) in some -ir verbs",
                points = listOf(
                    "o → u in the eu form of the present: dormir → durmo, cobrir → cubro, descobrir → descubro, tossir → tusso, engolir → engulo",
                    "e → i in the eu form of the present: sentir → sinto, mentir → minto, preferir → prefiro, vestir → visto, servir → sirvo, repetir → repito, seguir → sigo, conseguir → consigo, sugerir → sugiro, divertir → divirto, ferir → firo",
                    "e → i in eu, tu, ele and eles (all forms with stress on the stem): agredir → agrido, agrides, agride, agridem; also prevenir (previno, prevines), progredir (progrido)",
                    "u → o in tu, ele and eles (the opposite direction): subir → subo, sobes, sobe, sobem; fugir → fujo, foges; consumir → consomes; sacudir → sacodes; cuspir → cospes",
                    "Other irregular eu forms: pedir → peço, medir → meço, ouvir → ouço / oiço, perder → perco",
                    "Why: in Latin, the eu form ended in -io (dormio, sentio). That i 'pulled' the stem vowel higher (o → u, e → i) — the i later disappeared but the change stayed",
                    "The other forms keep the infinitive vowel: dormes, dorme, dormimos; sentes, sente, sentimos"
                ),
                examples = listOf(
                    RocExample("dormir → eu dormo", "eu durmo", "I sleep", "o → u in the eu form only (tu dormes)."),
                    RocExample("sentir → eu sento", "eu sinto", "I feel", "e → i (eu sento = I sit down, from sentar!)."),
                    RocExample("preferir → eu prefero", "eu prefiro", "I prefer", "e → i."),
                    RocExample("vestir → eu vesto", "eu visto", "I wear / I dress", "e → i."),
                    RocExample("subir → ele sube", "ele sobe", "he goes up", "u → o in tu/ele/eles."),
                    RocExample("pedir → eu pedo", "eu peço", "I ask for", "Irregular: d → ç.")
                )
            )
        )
    ),
    RocSection(
        title = "9. Changes in the Subjunctive (Conjuntivo)",
        topics = listOf(
            RocTopic(
                name = "Built from the eu form — so it inherits the changes",
                subtitle = "Take eu (present), remove -o, add the opposite vowel endings",
                points = listOf(
                    "-ar verbs get -e endings; -er/-ir verbs get -a endings",
                    "Rule 8 applies again: -ar verbs now have c/g before e → qu, gu, c",
                    "And every change in the eu form (protejo, consigo, durmo, sinto, peço…) carries through ALL persons of the present subjunctive, including nós",
                    "ficar: que eu fique, tu fiques, ele fique, nós fiquemos",
                    "pagar: que eu pague, paguemos · começar: que eu comece, comecemos",
                    "proteger: que eu proteja, protejamos · dirigir: que eu dirija",
                    "conseguir: que eu consiga, consigamos · seguir: que eu siga",
                    "conhecer: que eu conheça · pedir: que eu peça, peçamos · medir: que eu meça",
                    "ouvir: que eu ouça / oiça · perder: que eu perca",
                    "dormir: que eu durma, tu durmas, nós durmamos · cobrir: que eu cubra · tossir: que eu tussa",
                    "sentir: que eu sinta, sintamos · mentir: minta · preferir: prefira · servir: sirva · vestir: vista"
                ),
                examples = listOf(
                    RocExample("que eu fice", "que eu fique", "that I stay", "c + e → qu."),
                    RocExample("que eu page", "que eu pague", "that I pay", "g + e → gu."),
                    RocExample("que eu começe", "que eu comece", "that I start", "No ç before e."),
                    RocExample("que eu protega", "que eu proteja", "that I protect", "From eu protejo."),
                    RocExample("que eu consegua", "que eu consiga", "that I manage", "From eu consigo."),
                    RocExample("que eu peda", "que eu peça", "that I ask for", "From eu peço."),
                    RocExample("que eu dorma", "que eu durma", "that I sleep", "From eu durmo."),
                    RocExample("que nós sentamos", "que nós sintamos", "that we feel", "From eu sinto — even nós keeps the i."),
                    RocExample("Espero que ela durma bem.", "Espero que ela durma bem.", "I hope she sleeps well.", "Correct as is — durma.")
                ),
                accentNote = "No accents added or removed — only letters and stem vowels change."
            )
        )
    ),
    RocSection(
        title = "10. Personal Infinitive + o, a",
        topics = listOf(
            RocTopic(
                name = "comprarmos + o → comprarmo-lo",
                subtitle = "Yes — it's rule 1 again: the final -s drops",
                points = listOf(
                    "The personal infinitive ends in -s for tu (comprares), nós (comprarmos), eles (comprarem — nasal, rule 2)",
                    "-s + o/a: s drops, o → lo",
                    "-m + o/a (eles): add n — comprarem-no",
                    "The r inside (compraRmos) stays, because it's not the last letter",
                    "Hyphen: mandatory"
                ),
                examples = listOf(
                    RocExample("para comprarmos o", "para comprarmo-lo", "for us to buy it", "-s dropped, o → lo."),
                    RocExample("para comprares o", "para comprare-lo", "for you to buy it", "-s dropped."),
                    RocExample("até fazermos a", "até fazermo-la", "until we do it", "-s dropped."),
                    RocExample("para comprarem o", "para comprarem-no", "for them to buy it", "-m: o → no (rule 2)."),
                    RocExample("É para comprarmos o hoje.", "É para comprarmo-lo hoje.", "We're supposed to buy it today.", "")
                ),
                accentNote = "NO accent is added: the stress stays on -MAR- / -RE- / -ZER-, which is not the last syllable (comprarmo, comprare). " +
                    "Compare the plain infinitive comprar → comprá-lo: there the stressed a becomes the last letter, so it needs á."
            )
        )
    ),
    RocSection(
        title = "11. When We Do NOT Contract",
        topics = listOf(
            RocTopic(
                name = "Preposition + subject of an infinitive",
                subtitle = "Antes de o João chegar — not \"antes do João chegar\" (in careful writing)",
                points = listOf(
                    "Here de does not belong to \"o João\" — it belongs to the whole clause \"o João chegar\"",
                    "o João / ela is the SUBJECT of the infinitive (who arrives? João), and a subject is not normally preceded by a preposition",
                    "So the norm (school, formal writing, Ciberdúvidas-style advice) keeps them separate: de o, de a, de ele, em o…",
                    "Test: is there an infinitive right after, and is the noun/pronoun the one doing it? → don't contract",
                    "Reality check: in speech and informal writing the contracted form (antes do João chegar) is extremely common, and some grammarians tolerate it — but the separate form is the safe written choice"
                ),
                examples = listOf(
                    RocExample("Antes do João chegar, vamos preparar tudo.", "Antes de o João chegar, vamos preparar tudo.", "Before João arrives, let's get everything ready.", "o João is the subject of chegar."),
                    RocExample("Apesar dela estar cansada, continuou.", "Apesar de ela estar cansada, continuou.", "Although she was tired, she carried on.", "ela is the subject of estar."),
                    RocExample("O facto do preço ter subido…", "O facto de o preço ter subido…", "The fact that the price went up…", "o preço is the subject of ter subido."),
                    RocExample("Depois da reunião acabar…", "Depois de a reunião acabar…", "After the meeting ends…", "a reunião is the subject of acabar."),
                    RocExample("Está na hora dos miúdos irem dormir.", "Está na hora de os miúdos irem dormir.", "It's time for the kids to go to sleep.", "os miúdos is the subject of irem."),
                    RocExample("Antes de o jantar…", "Antes do jantar…", "Before dinner…", "Contrast: no infinitive — here you MUST contract.")
                )
            ),
            RocTopic(
                name = "Preposition + object pronoun o / a",
                subtitle = "This one is never contracted",
                points = listOf(
                    "When o/a/os/as is the object pronoun (him/her/it/them) of a following infinitive, it never merges with the preposition",
                    "These are pronouns, not articles — do/no/pelo only exist for articles"
                ),
                examples = listOf(
                    RocExample("Gostava do ver.", "Gostava de o ver.", "I'd like to see him.", "o = him (object of ver)."),
                    RocExample("Pensei na convidar.", "Pensei em a convidar.", "I thought about inviting her.", "a = her."),
                    RocExample("Acabei pelos ajudar.", "Acabei por os ajudar.", "I ended up helping them.", "os = them.")
                ),
                note = "You can also write these with the pronoun after the infinitive: Gostava de vê-lo. Pensei em convidá-la. Both orders are correct in EP."
            )
        )
    ),
    RocSection(
        title = "12. se, me, te, lhe … Don't Change the Verb",
        topics = listOf(
            RocTopic(
                name = "Only o, a, os, as trigger the change",
                subtitle = "fazer + se → fazer-se (nothing drops)",
                points = listOf(
                    "se, me, te, lhe, lhes, nos, vos attach with a hyphen but the verb stays whole",
                    "No letter drops, no accent is added",
                    "Hyphen: mandatory, same as with o/a",
                    "ONE exception: a nós verb form (ending -mos) + nos (reflexive/us) drops its -s: levantamos + nos → levantamo-nos",
                    "Same for the old vós imperative/forms + vos (rare today)"
                ),
                examples = listOf(
                    RocExample("fazê-se", "fazer-se", "to become / to be done", "se doesn't remove the r."),
                    RocExample("dá-lhe (from dar)", "dar-lhe", "to give him/her", "Infinitive keeps r. (dá-lhe = imperative \"give him\" — a different form.)"),
                    RocExample("comprá-te um presente", "comprar-te um presente", "to buy you a present","te doesn't remove the r."),
                    RocExample("fi-te", "fiz-te", "I made you", "z stays."),
                    RocExample("di-me", "diz-me", "tell me / he tells me", "z stays."),
                    RocExample("Levantamos-nos.", "Levantamo-nos.", "We get up.", "Exception: -mos + nos → -mo-nos."),
                    RocExample("Sentamos-nos.", "Sentamo-nos.", "We sit down.", "Same exception."),
                    RocExample("Vamos-nos embora.", "Vamo-nos embora.", "Let's go (away).", "Same exception.")
                ),
                accentNote = "No accent changes with se/me/te/lhe/nos/vos."
            )
        )
    ),
    RocSection(
        title = "Summary of Accent Rules",
        topics = listOf(
            RocTopic(
                name = "When accents are added, kept or not needed",
                subtitle = "",
                points = listOf(
                    "ADDED á: verb now ends in a stressed a — comprá-lo, fá-lo, trá-lo, comprá-lo-ei, fá-lo-ia",
                    "ADDED ê: verb now ends in a stressed closed e — vendê-lo, fazê-lo, vê-lo, lê-lo, tê-lo, fê-lo",
                    "KEPT ô: pôr → pô-lo",
                    "ADDED í: i in hiatus after a vowel — possuí-lo, atraí-lo, construí-lo, distribuí-lo",
                    "NONE: final i/u — parti-lo, di-lo, fi-lo, qui-lo, pu-lo, parti-los-ão",
                    "NONE: stress was not on the last syllable — come-lo, compramo-lo, comprarmo-lo, levantamo-nos",
                    "UNCHANGED: nasal verbs keep their accents — dão-no, põe-no, têm-nas",
                    "GRAVE ` (crase): a + a → à, a + aquele → àquele, a + aquilo → àquilo",
                    "Spelling changes (fiquei, protejo, consiga, durma) never involve accents",
                    "Hyphen: always after the verb / inside the verb; never when the pronoun comes first"
                ),
                examples = emptyList()
            )
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RulesOfContractionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contractions & Changes", maxLines = 1) },
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
            item {
                Text(
                    text = "Struck-through = what you might expect. Bold = what is actually written.",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            rocSections.forEach { section ->
                item { RocSectionHeader(section.title) }
                section.topics.forEach { topic ->
                    item { RocCard(topic) }
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun RocSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
    )
}

@Composable
private fun RocCard(entry: RocTopic) {
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
            if (entry.accentNote != null) {
                Text(
                    text = "Accents: ${entry.accentNote}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
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
            if (entry.examples.isNotEmpty()) {
                HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
                Text(
                    text = "Examples:",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                entry.examples.forEach { ex -> RocExampleRow(ex) }
            }
        }
    }
}

@Composable
private fun RocExampleRow(ex: RocExample) {
    val same = ex.expected == ex.actual
    Column(modifier = Modifier.padding(bottom = 6.dp)) {
        Text(
            text = buildAnnotatedString {
                if (!same) {
                    withStyle(
                        SpanStyle(
                            textDecoration = TextDecoration.LineThrough,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    ) { append(ex.expected) }
                    append("  →  ")
                }
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append(ex.actual) }
            },
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = ex.english,
            style = MaterialTheme.typography.bodySmall,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        if (ex.why.isNotEmpty()) {
            Text(
                text = ex.why,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
