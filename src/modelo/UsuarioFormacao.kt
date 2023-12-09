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
        val duracaoConcluidos = concluidos.sumOf {
            formacao.conteudos[it].duracao.valor
        }

        return Porcentagem(duracaoConcluidos / formacao.duracao.valor * 100)
    }

    fun concluirConteudo(indiceConteudo: Int) {
        concluidos.add(indiceConteudo)
    }
}