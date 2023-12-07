package src.modelo
typealias ConteudosConcluidos = MutableList<Int>

data class Usuario(
    val nome: String,
    var idade: Int,
) {
    val progressos = mutableMapOf<Formacao, ConteudosConcluidos>()

    fun calculaProgresso(formacao: Formacao) {
        TODO("Calcule a porcentagem de conteúdos concluídos pelo usuário," +
            "com base na duração da $formacao e da duração dos conteúdos concluídos por ele " +
            "(Indices dos conteúdos concluídos está progressos[formacao]")
    }
}