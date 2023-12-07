package src.modelo

import src.MatriculaInvalidaException

data class Matricula(
    val inscritos: MutableList<Usuario> = mutableListOf()
) {
    fun matricular(usuario: Usuario) {
        validaMatricula(usuario)
        inscritos.add(usuario)
    }

    fun cancelarMatricula(usuario: Usuario) {
        validaDesmatricula(usuario)
        inscritos.remove(usuario)
    }

    private fun validaMatricula(usuario: Usuario) {
        if (usuario in inscritos) {
            throw MatriculaInvalidaException("Usuário já matriculado")
        }
    }

    private fun validaDesmatricula(usuario: Usuario) {
        if (usuario !in inscritos) {
            throw MatriculaInvalidaException("Usuário não matriculado")
        }
    }
}