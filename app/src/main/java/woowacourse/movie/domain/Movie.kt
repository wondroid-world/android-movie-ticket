package woowacourse.movie.domain

data class Movie(
    val id: Long,
    val title: String,
    val poster: String,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: Int,
)
