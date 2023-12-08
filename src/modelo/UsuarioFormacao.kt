package src.modelo

typealias ConteudosConcluidos = MutableList<Int>

data class UsuarioFormacao(
    val usuario: Usuario,
    val formacao: Formacao,
    val concluidos: ConteudosConcluidos = mutableListOf()
) {

    fun calculaProgresso() {
        TODO(
            "Calcule a porcentagem de conteúdos concluídos pelo usuário," +
                "com base na duração da $formacao} e da duração " +
                "dos conteúdos concluídos por ele " +
                "(Indices dos conteúdos concluídos está progressos[formacao]"
        )
    }

    fun concluirConteudo(formacao: Formacao, indiceConteudo: Int) {
        concluidos.add(indiceConteudo)
    }
}