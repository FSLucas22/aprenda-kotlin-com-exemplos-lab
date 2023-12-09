package tests

import src.exceptions.MatriculaInvalidaException
import src.Minutos
import src.Porcentagem
import src.exceptions.ConclusaoInvalidaException
import src.modelo.*
import tests.test_utilities.*

fun newUsuario() = Usuario("Lucas", 24)

fun newConteudoEducacional(minutos: Minutos = Minutos(60.0)) = ConteudoEducacional(
    "Kotlin Basics",
    minutos,
    Nivel.BASICO
)

fun newFormacao(vararg conteudo: ConteudoEducacional): Formacao {
    return Formacao("Programação Kotlin", listOf(*conteudo))
}

val testesFormacao = newSession(
    Test("Deve matricular um usuário") {
        given { object {
            val usuario = newUsuario()
            val formacao = newFormacao()
        } } whenDone {
            formacao.matricular(usuario)
            formacao.inscritos
        } then {
            assertEquals(1, it.size)
            assertEquals(usuario, it[0])
        }
    },

    Test("Não deve matricular o mesmo usuário duas vezes") {
        given {
            val usuario = newUsuario()
            val formacao = newFormacao()

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
        }
    },

    Test("Deve ter como duração a soma das durações dos ConteudosEducacionais") {
        given { object {
            val formacao = newFormacao(
                newConteudoEducacional(Minutos(3.0)),
                newConteudoEducacional(Minutos(6.0))
            )
        } } whenDone {
            formacao.duracao
        } then {
            assertEquals(Minutos(9.0), formacao.duracao)
        }
    },

    Test("Deve lançar erro ao tentar concluir conteúdo de usuário não matriculado") {
        given { object {
            val formacao = newFormacao()
            val usuario = newUsuario()
        } } whenDone {
            catchThrowable(ConclusaoInvalidaException::class.java) {
                formacao.concluirConteudo(usuario, 0)
            }
        } then {
            assertEquals(it.msg, "Usuário não matriculado")
        }
    },

    Test("Deve lançar erro ao tentar concluir conteúdo não presente na formação") {
        given {
            val formacao = newFormacao()
            val usuario = newUsuario()

            formacao.matricular(usuario)

            object {
                val formacao = formacao
                val usuario = usuario
            }
        } whenDone { object {
            val msgIndiceMaior = catchThrowable(ConclusaoInvalidaException::class.java) {
                formacao.concluirConteudo(usuario, 0)
            }.msg

            val msgIndiceNegativo = catchThrowable(ConclusaoInvalidaException::class.java) {
                formacao.concluirConteudo(usuario, -1)
            }.msg
        } } then {
            assertEquals(it.msgIndiceMaior, "Indice de conteúdo inválido")
            assertEquals(it.msgIndiceNegativo, "Indice de conteúdo inválido")
        }
    },

    Test("Deve retornar o indice dos conteúdos concluídos") {
        given {
            val formacao = newFormacao(
                newConteudoEducacional(),
                newConteudoEducacional()
            )
            val usuario = newUsuario()

            formacao.matricular(usuario)
            formacao.concluirConteudo(usuario, 1)
            object {
                val formacao = formacao
                val usuario = usuario
            }
        } whenDone {
            formacao.concluidosPor(usuario)
        } then {
            assertEquals(listOf(1), it)
        }
    },

    Test("Deve calcular o progresso com base nos conteúdos concluídos") {
        given {
            val usuario = newUsuario()
            val formacao = newFormacao(
                newConteudoEducacional(Minutos(40.0)),
                newConteudoEducacional(Minutos(60.0)),
            )

            formacao.matricular(usuario)
            formacao.concluirConteudo(usuario, 1)

            object {
                val usuario = usuario
                val formacao = formacao
            }
        } whenDone {
            formacao.calcularProgresso(usuario)
        } then {
            assertEquals(Porcentagem(60.0), it)
        }
    },

    Test("Dois UsuarioFormacao devem ser iguais caso o usuario e a formação sejam idênticos") {
        given { object {
            val usuario = newUsuario()
            val formacao = newFormacao(newConteudoEducacional(), newConteudoEducacional())
            val usuarioFormacao = UsuarioFormacao(usuario, formacao)
            val usuarioFormacao2 = UsuarioFormacao(usuario, formacao)
        } } whenDone {
            usuarioFormacao2.concluirConteudo(1)
        } then {
            assertEquals(usuarioFormacao, usuarioFormacao2)
        }
    }
)