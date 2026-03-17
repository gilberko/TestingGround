package com.example.app2.data.model

enum class Tense(val displayLabel: String) {
    INDICATIVO_PRESENTE("Presente do Indicativo"),
    INDICATIVO_PRETERITO_PERFEITO("Pretérito Perfeito"),
    INDICATIVO_PRETERITO_IMPERFEITO("Pretérito Imperfeito"),
    INDICATIVO_PRETERITO_MAIS_QUE_PERFEITO("Pretérito Mais-que-Perfeito"),
    INDICATIVO_FUTURO("Futuro do Presente"),
    INDICATIVO_CONDICIONAL("Condicional"),
    CONJUNTIVO_PRESENTE("Presente do Conjuntivo"),
    CONJUNTIVO_PRETERITO_IMPERFEITO("Imperfeito do Conjuntivo"),
    CONJUNTIVO_FUTURO("Futuro do Conjuntivo"),
    IMPERATIVO_AFIRMATIVO("Imperativo Afirmativo"),
    IMPERATIVO_NEGATIVO("Imperativo Negativo")
}

enum class Subject(val displayLabel: String) {
    EU("eu"),
    TU("tu"),
    ELE("ele/ela/você"),
    NOS("nós"),
    VOS("vós"),
    ELES("eles/elas/vocês")
}
