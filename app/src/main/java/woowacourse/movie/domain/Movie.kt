package woowacourse.movie.domain

import java.time.LocalDate

data class Movie(
    val id: Long,
    val title: String,
    val poster: String,
    val screeningDate: LocalDate,
    val runningTime: Int,
)
