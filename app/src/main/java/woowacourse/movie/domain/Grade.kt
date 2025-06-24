package woowacourse.movie.domain

sealed class Grade(
    val price: Int,
) {
    object A : Grade(price = 12_000)

    object B : Grade(price = 10_000)

    object S : Grade(price = 15_000)

    companion object {
        fun grade(row: Int): Grade =
            when (row) {
                0, 1 -> Grade.B
                2, 3 -> Grade.S
                4 -> Grade.A
                else -> throw IllegalArgumentException("존재하지 않는 행입니다")
            }
    }
}
