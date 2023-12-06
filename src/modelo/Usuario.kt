package src.modelo

import src.Porcentagem

data class Usuario(
    val nome: String,
    var idade: Int,
) {
    val progressos = mutableMapOf<Formacao, Porcentagem>()
}