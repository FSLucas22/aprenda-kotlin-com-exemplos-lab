package src.modelo

import src.MatriculaInvalidaException
import src.Porcentagem

data class Formacao(val nome: String, val conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    val duracao: Porcentagem
        get() {
            TODO("Use a lista de $conteudos para calcular a duração total da formação, em minutos")
        }

    fun matricular(usuario: Usuario) {
        validaInscricao(usuario)
        inscritos.add(usuario)
        usuario.progressos[this] = Porcentagem(.0)
    }

    private fun validaInscricao(usuario: Usuario) {
        if (usuario in inscritos) {
            throw MatriculaInvalidaException("Usuário já matriculado")
        }
    }

    private fun validaDesmatricula(usuario: Usuario) {
        if (usuario !in inscritos) {
            throw MatriculaInvalidaException("Usuário não matriculado")
        }
    }

    fun cancelarMatricula(usuario: Usuario) {
        validaDesmatricula(usuario)
        inscritos.remove(usuario)
        usuario.progressos.remove(this)
    }

    fun concluirConteudo(usuario: Usuario, indiceConteudo: Int) {
        TODO("Utilize o parametro $indiceConteudo para pegar o ConteudoEducacional a ser concluído")
        TODO("Atualize atributo progressos do $usuario para a formação do usuário com base na:" +
            "Duração do conteúdo concluído" +
            "Progresso atual do usuário na formação" +
            "Duração total da formação"
        )
    }
}
