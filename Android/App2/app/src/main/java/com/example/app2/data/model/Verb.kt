package com.example.app2.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Verb(
    val infinitive: String,
    val english: String,
    val conjugationType: String,
    val conjugations: VerbConjugations
)

@Serializable
data class VerbConjugations(
    val indicativoPresente: TenseConjugation,
    val indicativoPreteritoPerfeito: TenseConjugation,
    val indicativoPreteritoImperfeito: TenseConjugation,
    val indicativoPreteritoMaisQuePerfeito: TenseConjugation,
    val indicativoFuturo: TenseConjugation,
    val indicativoCondicional: TenseConjugation,
    val conjuntivoPresente: TenseConjugation,
    val conjuntivoPreteritoImperfeito: TenseConjugation,
    val conjuntivoFuturo: TenseConjugation,
    val imperativoAfirmativo: ImperativoConjugation,
    val imperativoNegativo: ImperativoNegativoConjugation
)

@Serializable
data class TenseConjugation(
    val eu: String,
    val tu: String,
    val ele: String,
    val nos: String,
    val vos: String,
    val eles: String
)

@Serializable
data class ImperativoConjugation(
    val tu: String,
    val ele: String,
    val nos: String,
    val vos: String,
    val eles: String
)

@Serializable
data class ImperativoNegativoConjugation(
    val tu: String,
    val ele: String,
    val nos: String,
    val vos: String,
    val eles: String
)
