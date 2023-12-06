package modelo

import Minutos

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class ConteudoEducacional(
    val nome: String,
    val duracao: Minutos,
    val nivel: Nivel
)