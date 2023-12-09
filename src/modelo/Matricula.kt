package src.modelo

import src.exceptions.MatriculaInvalidaException

class Matricula(
    private val matriculas: MutableList<UsuarioFormacao> = mutableListOf()
) {
    fun inscritos(formacao: Formacao) = matriculas
        .filter { it.formacao == formacao }.map { it.usuario }

    fun matricular(usuarioFormacao: UsuarioFormacao) {
        validaMatricula(usuarioFormacao)
        matriculas.add(usuarioFormacao)
    }

    fun cancelarMatricula(usuarioFormacao: UsuarioFormacao) {
        validaDesmatricula(usuarioFormacao)
        matriculas.remove(usuarioFormacao)
    }

    fun retornarUsuarioFormacao(usuarioFormacao: UsuarioFormacao) = matriculas
        .filter { it == usuarioFormacao }[0]

    private fun validaMatricula(usuarioFormacao: UsuarioFormacao) {
        if (usuarioFormacao in matriculas) {
            throw MatriculaInvalidaException("Usuário já matriculado")
        }
    }

    private fun validaDesmatricula(usuarioFormacao: UsuarioFormacao) {
        if (usuarioFormacao !in matriculas) {
            throw MatriculaInvalidaException("Usuário não matriculado")
        }
    }
}