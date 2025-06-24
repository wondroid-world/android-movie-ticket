package woowacourse.movie.domain

data class Seat(
    private val row: Int,
    private val col: Int,
) {
    private val grade: Grade = Grade.grade(row = row)
    val price = grade.price

    init {
        require(0 <= row && row <= 4) { "좌석은 5행으로 구성되어 있습니다" }
        require(0 <= col && col <= 3) { "좌석은 4열로 구성되어 있습니다" }
    }
}
