package src.modelo

import src.Minutos
import src.Porcentagem

data class Formacao(
    val nome: String,
    val conteudos: List<ConteudoEducacional>,
    val matricula: Matricula = Matricula()
) {
    val inscritos = matricula.inscritos

    val duracao: Minutos = Minutos(conteudos.sumOf { it.duracao.valor })

    fun matricular(usuario: Usuario) {
        matricula.matricular(usuario)
        usuario.progressos[this] = Porcentagem(.0)
    }

    fun cancelarMatricula(usuario: Usuario) {
        matricula.cancelarMatricula(usuario)
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
