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

        return Porcentagem.de(duracaoConcluidos, formacao.duracao.valor)
    }

    infix fun concluirConteudoDo(indiceConteudo: Int) {
        concluidos.add(indiceConteudo)
    }
}