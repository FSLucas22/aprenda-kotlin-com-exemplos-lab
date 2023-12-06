import jdk.jfr.Percentage

// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }
@JvmInline
value class Porcentagem(val valor: Double)

@JvmInline
value class Minutos(val valor: Double)

data class Usuario(
    val nome: String,
    var idade: Int,
) {
    val progressos = mutableMapOf<Formacao, Porcentagem>()
}

data class ConteudoEducacional(
    val nome: String,
    val duracao: Minutos,
)

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

fun main() {
    TODO("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")
}