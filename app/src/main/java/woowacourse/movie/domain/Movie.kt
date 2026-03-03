package woowacourse.movie.domain

import java.time.LocalTime

data class Movie(
    val title: String,
    val showtime: LocalTime,
    val runningTime: Int,
    val poster: String,
)
