package woowacourse.movie.domain

import java.time.LocalDate

data class Movie(
    val id: Long,
    val title: String,
    val showtime: LocalDate,
    val runningTime: Int,
    val poster: String,
)
