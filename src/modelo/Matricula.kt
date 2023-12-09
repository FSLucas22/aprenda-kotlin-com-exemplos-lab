package src.modelo

import src.exceptions.MatriculaInvalidaException

class Matricula(
    private val matriculas: MutableList<UsuarioFormacao> = mutableListOf()
) {
    fun inscritos(formacao: Formacao) = matriculas
        .filter { it.formacao == formacao }.map { it.usuario }

    fun matricular(usuario: Usuario, formacao: Formacao) {
        validaMatricula(usuario, formacao)
        matriculas.add(UsuarioFormacao(usuario, formacao))
    }

    fun cancelarMatricula(usuario: Usuario, formacao: Formacao) {
        validaDesmatricula(usuario, formacao)
        matriculas.remove(retornarUsuarioFormacao(usuario, formacao))
    }

    fun retornarUsuarioFormacao(usuario: Usuario, formacao: Formacao) = matriculas
        .filter { it.usuario == usuario && it.formacao == formacao }[0]

    private fun validaMatricula(usuario: Usuario, formacao: Formacao) {
        if (usuario in inscritos(formacao)) {
            throw MatriculaInvalidaException("Usuário já matriculado")
        }
    }

    private fun validaDesmatricula(usuario: Usuario, formacao: Formacao) {
        if (usuario !in inscritos(formacao)) {
            throw MatriculaInvalidaException("Usuário não matriculado")
        }
    }
}