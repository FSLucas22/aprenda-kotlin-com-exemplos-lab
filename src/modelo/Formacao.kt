package src.modelo

import src.Minutos
import src.Porcentagem
import src.exceptions.ConclusaoInvalidaException

data class Formacao(
    val nome: String,
    val conteudos: List<ConteudoEducacional>,
    private val matricula: Matricula = Matricula(),
) {
    val inscritos
        get() = matricula.inscritos(this)

    val duracao: Minutos = Minutos(conteudos.sumOf { it.duracao.valor })

    infix fun matricular(usuario: Usuario) {
        matricula.matricular(usuario, this)
    }

    infix fun cancelarMatriculaDe(usuario: Usuario) {
        matricula.cancelarMatricula(usuario, this)
    }

    fun concluirConteudo(usuario: Usuario, indiceConteudo: Int) {
        validaConclusao(usuario, indiceConteudo)

        val usuarioFormacao = matricula.retornarUsuarioFormacao(usuario, this)
        usuarioFormacao.concluirConteudo(indiceConteudo)
    }

    private fun validaConclusao(usuario: Usuario, indiceConteudo: Int) {
        if (usuario !in inscritos) {
            throw ConclusaoInvalidaException("Usuário não matriculado")
        }

        if (indiceConteudo !in conteudos.indices) {
            throw ConclusaoInvalidaException("Indice de conteúdo inválido")
        }
    }

    fun concluidosPor(usuario: Usuario): List<Int> {
        return matricula.retornarUsuarioFormacao(usuario, this)
            .retornarConcluidos()
    }

    fun calcularProgresso(usuario: Usuario): Porcentagem {
        return matricula.retornarUsuarioFormacao(usuario, this)
            .calcularProgresso()
    }
}
