package src

@JvmInline
value class Porcentagem(val valor: Double) {
    companion object {
        fun de(quantidade: Double, total: Double) = Porcentagem(quantidade / total * 100)
    }
}

@JvmInline
value class Minutos(val valor: Double)