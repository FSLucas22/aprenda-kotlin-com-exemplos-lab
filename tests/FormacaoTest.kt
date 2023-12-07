package tests

import src.Minutos
import src.Porcentagem
import src.modelo.ConteudoEducacional
import src.modelo.Formacao
import src.modelo.Nivel
import src.modelo.Usuario
import tests.test_utilities.*

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
    }
)