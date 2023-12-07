package tests.test_utilities

typealias TestFunction = () -> Unit

enum class TestResult {
    NOT_EXECUTED,
    FAILED,
    PASSED
}

data class Test(
    val name: String,
    val testFunction: TestFunction,
) {
    var result: TestResult = TestResult.NOT_EXECUTED
}

data class TestSession(
    val tests: MutableList<Test> = mutableListOf()
) {
    fun run(): TestSession {
        tests.forEach {
            try {
                it.testFunction()
                it.result = TestResult.PASSED
            } catch (_: Throwable) {
                it.result = TestResult.FAILED
            }
        }
        return this
    }

    fun add(vararg test: Test) = tests.addAll(test)

    fun showResults() {
        val testNumber = tests.size
        var notExecuted = 0
        var passed = 0
        var failed = 0

        tests.forEach {
            when(it.result) {
                TestResult.NOT_EXECUTED -> {
                    println("Teste: ${it.name} - NÃO EXECUTOU")
                    notExecuted++
                }
                TestResult.FAILED -> {
                    println("Teste: ${it.name} - FALHOU")
                    failed++
                }
                TestResult.PASSED -> failed++
            }
        }

        println("Testes: $testNumber | Passados: $passed | Falhados: $failed")
    }
}

fun newSession(vararg test: Test): TestSession {
    val session = TestSession()
    session.add(*test)
    return session
}