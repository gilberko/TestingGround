package com.example.russianapp.data

import com.google.gson.annotations.SerializedName

// ── Adjective data ────────────────────────────────────────────────────────────

data class AdjectiveQuizEntry(
    val id: String,
    @SerializedName("nominative_masc") val nominativeMasc: String,
    val english: String,
    @SerializedName("nom_masc")      val nomMasc: String,
    @SerializedName("nom_fem")       val nomFem: String,
    @SerializedName("nom_neut")      val nomNeut: String,
    @SerializedName("nom_pl")        val nomPl: String,
    @SerializedName("gen_masc")      val genMasc: String,
    @SerializedName("gen_fem")       val genFem: String,
    @SerializedName("gen_neut")      val genNeut: String,
    @SerializedName("gen_pl")        val genPl: String,
    @SerializedName("dat_masc")      val datMasc: String,
    @SerializedName("dat_fem")       val datFem: String,
    @SerializedName("dat_neut")      val datNeut: String,
    @SerializedName("dat_pl")        val datPl: String,
    @SerializedName("acc_masc_inan") val accMascInan: String,
    @SerializedName("acc_masc_anim") val accMascAnim: String,
    @SerializedName("acc_fem")       val accFem: String,
    @SerializedName("acc_neut")      val accNeut: String,
    @SerializedName("acc_pl_inan")   val accPlInan: String,
    @SerializedName("acc_pl_anim")   val accPlAnim: String,
    @SerializedName("ins_masc")      val insMasc: String,
    @SerializedName("ins_fem")       val insFem: String,
    @SerializedName("ins_neut")      val insNeut: String,
    @SerializedName("ins_pl")        val insPl: String,
    @SerializedName("pre_masc")      val preMasc: String,
    @SerializedName("pre_fem")       val preFem: String,
    @SerializedName("pre_neut")      val preNeut: String,
    @SerializedName("pre_pl")        val prePl: String
)

data class AdjectivesQuizFile(val adjectives: List<AdjectiveQuizEntry>)

fun AdjectiveQuizEntry.allForms(): List<Pair<String, String>> = listOf(
    "Nominative Masculine"       to nomMasc,
    "Nominative Feminine"        to nomFem,
    "Nominative Neuter"          to nomNeut,
    "Nominative Plural"          to nomPl,
    "Genitive Masculine"         to genMasc,
    "Genitive Feminine"          to genFem,
    "Genitive Neuter"            to genNeut,
    "Genitive Plural"            to genPl,
    "Dative Masculine"           to datMasc,
    "Dative Feminine"            to datFem,
    "Dative Neuter"              to datNeut,
    "Dative Plural"              to datPl,
    "Accusative Masc (inanimate)" to accMascInan,
    "Accusative Masc (animate)"  to accMascAnim,
    "Accusative Feminine"        to accFem,
    "Accusative Neuter"          to accNeut,
    "Accusative Plural (inan)"   to accPlInan,
    "Accusative Plural (anim)"   to accPlAnim,
    "Instrumental Masculine"     to insMasc,
    "Instrumental Feminine"      to insFem,
    "Instrumental Neuter"        to insNeut,
    "Instrumental Plural"        to insPl,
    "Prepositional Masculine"    to preMasc,
    "Prepositional Feminine"     to preFem,
    "Prepositional Neuter"       to preNeut,
    "Prepositional Plural"       to prePl
)

// ── Noun data ─────────────────────────────────────────────────────────────────

data class NounQuizEntry(
    val id: String,
    @SerializedName("nom_sg")  val nomSg:  String,
    val english: String,
    val gender: String,
    @SerializedName("gen_sg")  val genSg:  String,
    @SerializedName("dat_sg")  val datSg:  String,
    @SerializedName("acc_sg")  val accSg:  String,
    @SerializedName("ins_sg")  val insSg:  String,
    @SerializedName("pre_sg")  val preSg:  String,
    @SerializedName("nom_pl")  val nomPl:  String,
    @SerializedName("gen_pl")  val genPl:  String,
    @SerializedName("dat_pl")  val datPl:  String,
    @SerializedName("acc_pl")  val accPl:  String,
    @SerializedName("ins_pl")  val insPl:  String,
    @SerializedName("pre_pl")  val prePl:  String
)

data class NounsQuizFile(val nouns: List<NounQuizEntry>)

fun NounQuizEntry.allForms(): List<Pair<String, String>> = listOf(
    "Genitive Singular"      to genSg,
    "Dative Singular"        to datSg,
    "Accusative Singular"    to accSg,
    "Instrumental Singular"  to insSg,
    "Prepositional Singular" to preSg,
    "Nominative Plural"      to nomPl,
    "Genitive Plural"        to genPl,
    "Dative Plural"          to datPl,
    "Accusative Plural"      to accPl,
    "Instrumental Plural"    to insPl,
    "Prepositional Plural"   to prePl
)

// ── Verb data ─────────────────────────────────────────────────────────────────

data class VerbQuizEntry(
    val id: String,
    val infinitive: String,
    val english: String,
    val perfective: String,
    val ya: String,
    val ty: String,
    @SerializedName("on_ona") val onOna: String,
    val my: String,
    val vy: String,
    val oni: String
)

data class VerbsQuizFile(val verbs: List<VerbQuizEntry>)

fun VerbQuizEntry.allForms(): List<Pair<String, String>> = listOf(
    "я"      to ya,
    "ты"     to ty,
    "он/она" to onOna,
    "мы"     to my,
    "вы"     to vy,
    "они"    to oni
)

// ── Shared quiz state ─────────────────────────────────────────────────────────

data class QuizQuestion(
    val promptWord: String,
    val promptLabel: String,
    val questionText: String,
    val correctAnswer: String,
    val choices: List<String>
)

// ── Vocabulary data ───────────────────────────────────────────────────────────

data class VocabularyEntry(val id: String, val english: String, val russian: String)
data class VocabularyQuizFile(val vocabulary: List<VocabularyEntry>)

// ── Shared quiz state ─────────────────────────────────────────────────────────

data class QuizState(
    val questions: List<QuizQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val selectedAnswer: String? = null,
    val score: Int = 0,
    val isFinished: Boolean = false
) {
    val currentQuestion: QuizQuestion? get() = questions.getOrNull(currentIndex)
    val isAnswered: Boolean get() = selectedAnswer != null
}
