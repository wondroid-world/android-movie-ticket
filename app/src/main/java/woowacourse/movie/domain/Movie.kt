package woowacourse.movie.domain

import java.time.LocalDate

data class Movie(
    val title: String,
    val poster: Int,
    val screeningDate: LocalDate,
    val runningTime: Int,
)
