package com.example.app2.quiz

import com.example.app2.data.model.Subject
import com.example.app2.data.model.Tense
import com.example.app2.data.model.Verb

object VerbForms {

    fun gerund(verb: Verb): String {
        verb.gerundOverride?.let { return it }
        return when {
            verb.infinitive.endsWith("ar") -> verb.infinitive.dropLast(2) + "ando"
            verb.infinitive.endsWith("er") -> verb.infinitive.dropLast(2) + "endo"
            verb.infinitive.endsWith("ir") -> verb.infinitive.dropLast(2) + "indo"
            else -> verb.infinitive + "ndo"
        }
    }

    fun pastParticiple(verb: Verb): String {
        verb.pastParticipleOverride?.let { return it }
        return when {
            verb.infinitive.endsWith("ar") -> verb.infinitive.dropLast(2) + "ado"
            verb.infinitive.endsWith("er") -> verb.infinitive.dropLast(2) + "ido"
            verb.infinitive.endsWith("ir") -> verb.infinitive.dropLast(2) + "ido"
            else -> verb.infinitive + "ido"
        }
    }

    fun passiveForm(verb: Verb, tense: Tense, subject: Subject): String? {
        val ser = SER[tense]?.get(subject) ?: return null
        return "$ser ${pastParticiple(verb)}"
    }

    val SER: Map<Tense, Map<Subject, String>> = mapOf(
        Tense.PASSIVA_PRESENTE to mapOf(
            Subject.EU to "sou",
            Subject.TU to "és",
            Subject.ELE to "é",
            Subject.NOS to "somos",
            Subject.VOS to "sois",
            Subject.ELES to "são"
        ),
        Tense.PASSIVA_PRETERITO_PERFEITO to mapOf(
            Subject.EU to "fui",
            Subject.TU to "foste",
            Subject.ELE to "foi",
            Subject.NOS to "fomos",
            Subject.VOS to "fostes",
            Subject.ELES to "foram"
        ),
        Tense.PASSIVA_PRETERITO_IMPERFEITO to mapOf(
            Subject.EU to "era",
            Subject.TU to "eras",
            Subject.ELE to "era",
            Subject.NOS to "éramos",
            Subject.VOS to "éreis",
            Subject.ELES to "eram"
        ),
        Tense.PASSIVA_FUTURO to mapOf(
            Subject.EU to "serei",
            Subject.TU to "serás",
            Subject.ELE to "será",
            Subject.NOS to "seremos",
            Subject.VOS to "sereis",
            Subject.ELES to "serão"
        ),
        Tense.PASSIVA_CONDICIONAL to mapOf(
            Subject.EU to "seria",
            Subject.TU to "serias",
            Subject.ELE to "seria",
            Subject.NOS to "seríamos",
            Subject.VOS to "seríeis",
            Subject.ELES to "seriam"
        )
    )
}
