package src.exceptions

data class MatriculaInvalidaException(val msg: String): RuntimeException(msg)
