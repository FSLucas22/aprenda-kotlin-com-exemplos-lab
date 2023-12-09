package src.modelo

import src.Porcentagem

typealias ConteudosConcluidos = MutableList<Int>

data class UsuarioFormacao(
    val usuario: Usuario,
    val formacao: Formacao,
    val concluidos: ConteudosConcluidos = mutableListOf()
) {

    fun retornarConcluidos() = concluidos.toList()

    fun calcularProgresso(): Porcentagem {
        TODO(
            "Calcule a porcentagem de conteúdos concluídos pelo usuário," +
                "com base na duração da $formacao} e da duração " +
                "dos conteúdos concluídos por ele " +
                "(Indices dos conteúdos concluídos está progressos[formacao]"
        )
    }

    fun concluirConteudo(indiceConteudo: Int) {
        concluidos.add(indiceConteudo)
    }

    override fun equals(other: Any?): Boolean {
        if(other !is UsuarioFormacao) {
            return super.equals(other)
        }

        return other.usuario == usuario && other.formacao == formacao
    }
}