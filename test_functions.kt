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


fun main() {
    assertTrue(1 == 1)
    // assertTrue(1 == 0)
    assertFalse(1 == 0)
    // assertFalse(1 == 1)
    assertEquals(1, 1)
    assertEquals(1, 0)
}