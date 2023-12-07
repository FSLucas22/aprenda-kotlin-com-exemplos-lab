package src.modelo

import src.Minutos
import src.exceptions.ConclusaoInvalidaException

data class Formacao(
    val nome: String,
    val conteudos: List<ConteudoEducacional>,
    val matricula: Matricula = Matricula()
) {
    val inscritos = matricula.inscritos

    val duracao: Minutos = Minutos(conteudos.sumOf { it.duracao.valor })

    fun matricular(usuario: Usuario) {
        matricula.matricular(usuario)
        usuario.progressos[this] = mutableListOf()
    }

    fun cancelarMatricula(usuario: Usuario) {
        matricula.cancelarMatricula(usuario)
        usuario.progressos.remove(this)
    }

    fun concluirConteudo(usuario: Usuario, indiceConteudo: Int) {
        validaConclusao(usuario, indiceConteudo)
        usuario.progressos[this]?.run {
            add(indiceConteudo)
        } ?: { usuario.progressos[this] = mutableListOf() }
    }

    private fun validaConclusao(usuario: Usuario, indiceConteudo: Int) {
        if (usuario !in inscritos) {
            throw ConclusaoInvalidaException("Usuário não matriculado")
        } else if (indiceConteudo >= conteudos.size) {
            throw ConclusaoInvalidaException("Indice de conteúdo inválido")
        }
    }
}
