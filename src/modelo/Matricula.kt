package src.modelo

import src.exceptions.MatriculaInvalidaException

data class Matricula(
    val matriculas: MutableList<UsuarioFormacao> = mutableListOf()
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