package com.example.app2.quiz

import com.example.app2.data.model.Subject
import com.example.app2.data.model.Tense
import com.example.app2.data.model.Verb

object DistractorGenerator {

    fun generate(
        verb: Verb,
        tense: Tense,
        subject: Subject,
        correctAnswer: String,
        allVerbs: List<Verb>
    ): List<String> {
        val distractors = mutableSetOf<String>()

        // Strategy A: same verb, different subjects, same tense
        Subject.entries.forEach { otherSubject ->
            if (otherSubject != subject && distractors.size < 3) {
                val form = getForm(verb, tense, otherSubject)
                if (form != null && form != correctAnswer) distractors.add(form)
            }
        }

        // Strategy B: different verbs, same conjugationType, same tense+subject
        if (distractors.size < 3) {
            allVerbs
                .filter { it.infinitive != verb.infinitive && it.conjugationType == verb.conjugationType }
                .shuffled()
                .forEach { otherVerb ->
                    if (distractors.size >= 3) return@forEach
                    val form = getForm(otherVerb, tense, subject)
                    if (form != null && form != correctAnswer) distractors.add(form)
                }
        }

        // Strategy C: fallback — same verb, adjacent tenses, same subject
        if (distractors.size < 3) {
            val tenseList = Tense.entries
            val idx = tenseList.indexOf(tense)
            val adjacentTenses = listOfNotNull(
                tenseList.getOrNull(idx - 1),
                tenseList.getOrNull(idx + 1),
                tenseList.getOrNull(idx - 2),
                tenseList.getOrNull(idx + 2)
            )
            adjacentTenses.forEach { adjTense ->
                if (distractors.size >= 3) return@forEach
                val form = getForm(verb, adjTense, subject)
                if (form != null && form != correctAnswer) distractors.add(form)
            }
        }

        // Last resort: fill with forms from any verb/tense/subject
        if (distractors.size < 3) {
            allVerbs.shuffled().forEach { anyVerb ->
                Tense.entries.forEach { anyTense ->
                    Subject.entries.forEach { anySubject ->
                        if (distractors.size >= 3) return@forEach
                        val form = getForm(anyVerb, anyTense, anySubject)
                        if (form != null && form != correctAnswer && !distractors.contains(form))
                            distractors.add(form)
                    }
                }
                if (distractors.size >= 3) return@forEach
            }
        }

        return distractors.take(3)
    }

    fun getForm(verb: Verb, tense: Tense, subject: Subject): String? {
        val c = verb.conjugations
        return when (tense) {
            Tense.INDICATIVO_PRESENTE -> when (subject) {
                Subject.EU -> c.indicativoPresente.eu
                Subject.TU -> c.indicativoPresente.tu
                Subject.ELE -> c.indicativoPresente.ele
                Subject.NOS -> c.indicativoPresente.nos
                Subject.VOS -> c.indicativoPresente.vos
                Subject.ELES -> c.indicativoPresente.eles
            }
            Tense.INDICATIVO_PRETERITO_PERFEITO -> when (subject) {
                Subject.EU -> c.indicativoPreteritoPerfeito.eu
                Subject.TU -> c.indicativoPreteritoPerfeito.tu
                Subject.ELE -> c.indicativoPreteritoPerfeito.ele
                Subject.NOS -> c.indicativoPreteritoPerfeito.nos
                Subject.VOS -> c.indicativoPreteritoPerfeito.vos
                Subject.ELES -> c.indicativoPreteritoPerfeito.eles
            }
            Tense.INDICATIVO_PRETERITO_IMPERFEITO -> when (subject) {
                Subject.EU -> c.indicativoPreteritoImperfeito.eu
                Subject.TU -> c.indicativoPreteritoImperfeito.tu
                Subject.ELE -> c.indicativoPreteritoImperfeito.ele
                Subject.NOS -> c.indicativoPreteritoImperfeito.nos
                Subject.VOS -> c.indicativoPreteritoImperfeito.vos
                Subject.ELES -> c.indicativoPreteritoImperfeito.eles
            }
            Tense.INDICATIVO_PRETERITO_MAIS_QUE_PERFEITO -> when (subject) {
                Subject.EU -> c.indicativoPreteritoMaisQuePerfeito.eu
                Subject.TU -> c.indicativoPreteritoMaisQuePerfeito.tu
                Subject.ELE -> c.indicativoPreteritoMaisQuePerfeito.ele
                Subject.NOS -> c.indicativoPreteritoMaisQuePerfeito.nos
                Subject.VOS -> c.indicativoPreteritoMaisQuePerfeito.vos
                Subject.ELES -> c.indicativoPreteritoMaisQuePerfeito.eles
            }
            Tense.INDICATIVO_FUTURO -> when (subject) {
                Subject.EU -> c.indicativoFuturo.eu
                Subject.TU -> c.indicativoFuturo.tu
                Subject.ELE -> c.indicativoFuturo.ele
                Subject.NOS -> c.indicativoFuturo.nos
                Subject.VOS -> c.indicativoFuturo.vos
                Subject.ELES -> c.indicativoFuturo.eles
            }
            Tense.INDICATIVO_CONDICIONAL -> when (subject) {
                Subject.EU -> c.indicativoCondicional.eu
                Subject.TU -> c.indicativoCondicional.tu
                Subject.ELE -> c.indicativoCondicional.ele
                Subject.NOS -> c.indicativoCondicional.nos
                Subject.VOS -> c.indicativoCondicional.vos
                Subject.ELES -> c.indicativoCondicional.eles
            }
            Tense.CONJUNTIVO_PRESENTE -> when (subject) {
                Subject.EU -> c.conjuntivoPresente.eu
                Subject.TU -> c.conjuntivoPresente.tu
                Subject.ELE -> c.conjuntivoPresente.ele
                Subject.NOS -> c.conjuntivoPresente.nos
                Subject.VOS -> c.conjuntivoPresente.vos
                Subject.ELES -> c.conjuntivoPresente.eles
            }
            Tense.CONJUNTIVO_PRETERITO_IMPERFEITO -> when (subject) {
                Subject.EU -> c.conjuntivoPreteritoImperfeito.eu
                Subject.TU -> c.conjuntivoPreteritoImperfeito.tu
                Subject.ELE -> c.conjuntivoPreteritoImperfeito.ele
                Subject.NOS -> c.conjuntivoPreteritoImperfeito.nos
                Subject.VOS -> c.conjuntivoPreteritoImperfeito.vos
                Subject.ELES -> c.conjuntivoPreteritoImperfeito.eles
            }
            Tense.CONJUNTIVO_FUTURO -> when (subject) {
                Subject.EU -> c.conjuntivoFuturo.eu
                Subject.TU -> c.conjuntivoFuturo.tu
                Subject.ELE -> c.conjuntivoFuturo.ele
                Subject.NOS -> c.conjuntivoFuturo.nos
                Subject.VOS -> c.conjuntivoFuturo.vos
                Subject.ELES -> c.conjuntivoFuturo.eles
            }
            Tense.IMPERATIVO_AFIRMATIVO -> when (subject) {
                Subject.EU -> null
                Subject.TU -> c.imperativoAfirmativo.tu
                Subject.ELE -> c.imperativoAfirmativo.ele
                Subject.NOS -> c.imperativoAfirmativo.nos
                Subject.VOS -> c.imperativoAfirmativo.vos
                Subject.ELES -> c.imperativoAfirmativo.eles
            }
            Tense.IMPERATIVO_NEGATIVO -> when (subject) {
                Subject.EU -> null
                Subject.TU -> c.imperativoNegativo.tu
                Subject.ELE -> c.imperativoNegativo.ele
                Subject.NOS -> c.imperativoNegativo.nos
                Subject.VOS -> c.imperativoNegativo.vos
                Subject.ELES -> c.imperativoNegativo.eles
            }
        }
    }
}
