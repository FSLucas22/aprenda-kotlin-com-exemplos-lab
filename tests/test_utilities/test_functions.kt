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

fun assertFalse(
    condition: Boolean,
    message: String = "expected false got true"
) = assertTrue(!condition, message)

fun <T> assertEquals(
    expected: T,
    gotted: T,
    message: String = "expected $expected to equal $gotted"
) = assertTrue(expected == gotted, message)