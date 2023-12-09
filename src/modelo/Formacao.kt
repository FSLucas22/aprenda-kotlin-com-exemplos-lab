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

    fun matricular(usuario: Usuario) {
        matricula.matricular(UsuarioFormacao(usuario, this))
    }

    fun cancelarMatricula(usuario: Usuario) {
        matricula.cancelarMatricula(UsuarioFormacao(usuario, this))
    }

    fun concluirConteudo(usuario: Usuario, indiceConteudo: Int) {
        validaConclusao(usuario, indiceConteudo)

        val usuarioFormacao = matricula.retornarUsuarioFormacao(
            UsuarioFormacao(usuario, this)
        )

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
        return matricula.retornarUsuarioFormacao(
            UsuarioFormacao(usuario, this)
        ).retornarConcluidos()
    }

    fun calcularProgresso(usuario: Usuario): Porcentagem {
        return matricula.retornarUsuarioFormacao(
            UsuarioFormacao(usuario, this)
        ).calcularProgresso()
    }
}
