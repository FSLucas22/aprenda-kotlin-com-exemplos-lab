package src.modelo

import src.Minutos

data class Formacao(
    val nome: String,
    val conteudos: List<ConteudoEducacional>,
    private val matricula: Matricula = Matricula()
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
}
