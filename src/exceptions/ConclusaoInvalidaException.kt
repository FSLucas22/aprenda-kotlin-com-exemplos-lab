package src.exceptions

data class ConclusaoInvalidaException(val msg: String): RuntimeException(msg)
