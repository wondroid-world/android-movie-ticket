package woowacourse.movie.uimodel

import java.time.LocalDateTime

data class SummaryUiModel(
    val id: Long,
    val title: String,
    val screeningDateTime: LocalDateTime,
    val seats: List<Seat>,
    val peopleCount: Int,
    val totalAmount: Int,
)


