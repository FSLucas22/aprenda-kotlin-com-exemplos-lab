package src.modelo
typealias ConteudosConcluidos = MutableList<Int>

data class Usuario(
    val nome: String,
    var idade: Int,
) {
    val progressos = mutableMapOf<Formacao, ConteudosConcluidos>()
}