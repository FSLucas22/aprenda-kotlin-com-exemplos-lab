package src.modelo

import src.Minutos

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class ConteudoEducacional(
    val nome: String,
    val duracao: Minutos,
    val nivel: Nivel
)