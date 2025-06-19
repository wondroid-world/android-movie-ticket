package woowacourse.movie.domain

import java.time.LocalDate

data class ScreeningPeriod(
    val start: LocalDate,
    val end: LocalDate,
)
