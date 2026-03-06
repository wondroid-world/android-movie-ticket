package woowacourse.movie.domain

import java.time.LocalDate

data class Movie(
    val id: Long,
    val title: String,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: Int,
    val poster: Int,
)