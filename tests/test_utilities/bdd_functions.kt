package tests.test_utilities

/*
* Funções úteis para auxiliar na estruturação de testes
*/

data class Context<T>(val context: T) {
    inline infix fun <R> whenDone(function: T.() -> R): Result<T, R> = Result(context, function(context))
}

data class Result<T, R>(val context: T, val result: R) {
    inline infix fun then(function: T.(R) -> Unit): Unit = function(context, result)
}

inline fun <T> given(function: () -> T): Context<T> = Context(function())