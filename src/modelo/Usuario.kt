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

    fun adicionarFormacao(formacao: Formacao) {
        progressos.putIfAbsent(formacao, mutableListOf())
    }

    fun removerFormacao(formacao: Formacao) {
        progressos.remove(formacao)
    }

    fun concluirConteudo(formacao: Formacao, indiceConteudo: Int) {
        progressos[formacao]?.run {
            add(indiceConteudo)
        }
    }
}