package tests.test_utilities

/*
* Funções úteis para auxiliar na estruturação de testes
*/

inline fun <T> given(function: () -> T): T = function()
inline infix fun <T, R> T.whenDone(function: T.() -> R): R = function()
inline infix fun <T> T.then(function: (T) -> Unit): Unit = function(this)