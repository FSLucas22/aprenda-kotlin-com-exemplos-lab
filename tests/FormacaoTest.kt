package tests

import src.MatriculaInvalidaException
import src.Minutos
import src.Porcentagem
import src.modelo.ConteudoEducacional
import src.modelo.Formacao
import src.modelo.Nivel
import src.modelo.Usuario
import tests.test_utilities.*

fun newUsuario() = Usuario("Lucas", 24)

fun newFormacao(): Formacao {
    val conteudo = ConteudoEducacional("Kotlin Basics", Minutos(60.0), Nivel.BASICO)
    return Formacao("Programação Kotlin", listOf(conteudo))
}

val testesFormacao = newSession(
    Test("Deve matricular um usuário") {
        given { object {
            val usuario = Usuario("Lucas", 24)
            val conteudo = ConteudoEducacional("Kotlin Basics", Minutos(60.0), Nivel.BASICO)
            val formacao = Formacao("Programação Kotlin", listOf(conteudo))
        } } whenDone {
            formacao.matricular(usuario)
            formacao.inscritos
        } then {
            assertEquals(1, it.size)
            assertEquals(usuario, it[0])
            assertEquals(Porcentagem(.0), usuario.progressos[formacao])
        }
    },

    Test("Não deve matricular o mesmo usuário duas vezes") {
        given {
            val usuario = Usuario("Lucas", 24)
            val conteudo = ConteudoEducacional("Kotlin Basics", Minutos(60.0), Nivel.BASICO)
            val formacao = Formacao("Programacao Kotlin", listOf(conteudo))

            formacao.matricular(usuario)

            object {
                val usuario = usuario
                val formacao = formacao
            }
        } whenDone {
            catchThrowable(MatriculaInvalidaException::class.java) {
                formacao.matricular(usuario)
            }
        } then {
            assertEquals("Usuário já matriculado", it.msg)
        }
    },

    Test("Deve lançar erro ao tentar cancelar matricula inexistente") {
        given { object {
            val usuario = newUsuario()
            val formacao = newFormacao()
        } } whenDone {
            catchThrowable(MatriculaInvalidaException::class.java) {
                formacao.cancelarMatricula(usuario)
            }
        } then {
            assertEquals("Usuário não matriculado", it.msg)
        }
    },

    Test("Deve cancelar uma matricula") {
        given {
            val usuario = newUsuario()
            val formacao = newFormacao()

            formacao.matricular(usuario)

            object {
                val usuario = usuario
                val formacao = formacao
            }
        } whenDone {
            formacao.cancelarMatricula(usuario)
        } then {
            assertEquals(0, formacao.inscritos.size)
            assertTrue(formacao !in usuario.progressos)
        }
    }
)