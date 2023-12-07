package src

data class MatriculaInvalidaException(val msg: String): RuntimeException(msg)
