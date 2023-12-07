package tests.test_utilities

/*
* Funções úteis para realizar assertions em testes
*/

fun assertTrue(
    condition: Boolean,
    message: String = "expected true got false") {
    if (!condition) {
        throw AssertionError(message)
    }
}

inline fun <reified T: Throwable> catchThrowable(clazz: Class<T>, function: () -> Any): T {
    try {
        function()
        throw AssertionError("Did not throw ${clazz.name}")
    } catch (e: RuntimeException) {
        if (e is T) {
            return e
        }
        throw e
    }
}

fun assertFalse(
    condition: Boolean,
    message: String = "expected false got true"
) = assertTrue(!condition, message)

fun <T> assertEquals(
    expected: T,
    gotted: T,
    message: String = "expected $expected but got $gotted"
) = assertTrue(expected == gotted, message)