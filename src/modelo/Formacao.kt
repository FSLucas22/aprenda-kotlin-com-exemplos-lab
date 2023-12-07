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

    fun cancelarMatricula(usuario: Usuario) {
        TODO("Utilize o parâmetro $usuario para simular um cancelamento de matricula" +
            "(usar a lista de $inscritos).")
        TODO("Remover a formação do atributo progressos do $usuario")
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
