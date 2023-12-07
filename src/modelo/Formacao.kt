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
        usuario.adicionarFormacao(this)
    }

    fun cancelarMatricula(usuario: Usuario) {
        matricula.cancelarMatricula(usuario)
        usuario.removerFormacao(this)
    }

    fun concluirConteudo(usuario: Usuario, indiceConteudo: Int) {
        validaConclusao(usuario, indiceConteudo)
        usuario.concluirConteudo(this, indiceConteudo)
    }

    private fun validaConclusao(usuario: Usuario, indiceConteudo: Int) {
        if (usuario !in inscritos) {
            throw ConclusaoInvalidaException("Usuário não matriculado")
        } else if (indiceConteudo >= conteudos.size) {
            throw ConclusaoInvalidaException("Indice de conteúdo inválido")
        }
    }
}
