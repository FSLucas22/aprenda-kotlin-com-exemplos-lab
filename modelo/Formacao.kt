package modelo

import Porcentagem

data class Formacao(val nome: String, val conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    val duracao: Porcentagem
        get() {
            TODO("Use a lista de $conteudos para calcular a duração total da formação, em minutos")
        }

    fun matricular(usuario: Usuario) {
        TODO("Utilize o parâmetro $usuario para simular uma matrícula (usar a lista de $inscritos).")
        TODO("Atualize o atributo progressos do $usuario, adicionando a formação")
    }

    fun concluirConteudo(usuario: Usuario, indiceConteudo: Int) {
        TODO("Atualize o atributo progressos para a formação do usuário com base na:" +
            "Duração do conteúdo concluído" +
            "Progresso atual do usuário na formação" +
            "Duração total da formação"
        )
    }
}
