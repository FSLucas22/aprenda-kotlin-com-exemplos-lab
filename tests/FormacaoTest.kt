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
            formacao matricula usuario
            formacao.inscritos
        } then {
            assertEquals(1, it.size)
            assertEquals(usuario, it[0])
        }
    },

    Test("Não deve matricular o mesmo usuário duas vezes") {
        given(object {
            val usuario = newUsuario()
            val formacao = newFormacao()
        }) {
            formacao matricula usuario
        } whenDone {
            catchThrowable(MatriculaInvalidaException::class.java) {
                formacao matricula usuario
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
                formacao cancelaMatriculaDe usuario
            }
        } then {
            assertEquals("Usuário não matriculado", it.msg)
        }
    },

    Test("Deve cancelar uma matricula") {
        given(object {
            val usuario = newUsuario()
            val formacao = newFormacao()
        }) {
            formacao matricula usuario
        } whenDone {
            formacao cancelaMatriculaDe usuario
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
        given(object {
            val formacao = newFormacao()
            val usuario = newUsuario()
        }) {
            formacao matricula usuario
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
        given(object {
            val formacao = newFormacao(
                newConteudoEducacional(),
                newConteudoEducacional()
            )
            val usuario = newUsuario()
        }) {
            formacao matricula usuario
            formacao.concluirConteudo(usuario, 1)
        } whenDone {
            formacao retornaConcluidosPor usuario
        } then {
            assertEquals(listOf(1), it)
        }
    },

    Test("Deve calcular o progresso com base nos conteúdos concluídos") {
        given(object {
            val usuario = newUsuario()
            val formacao = newFormacao(
                newConteudoEducacional(Minutos(40.0)),
                newConteudoEducacional(Minutos(60.0)),
            )
        }) {
            formacao matricula usuario
            formacao.concluirConteudo(usuario, 1)
        } whenDone {
            formacao calculaProgressoDe usuario
        } then {
            assertEquals(Porcentagem(60.0), it)
        }
    },
)