package woowacourse.movie.uimodel

data class Seat(
    val row: Int,
    val col: Int,
) {
    fun name(): String {
        val row =
            when (this.row) {
                0 -> "A"
                1 -> "B"
                2 -> "C"
                3 -> "D"
                4 -> "E"
                else -> throw IllegalArgumentException("행은 5개의 행만 존재합니다")
            }

        val col: String = (this.col + 1).toString()

        return row + col
    }
}
