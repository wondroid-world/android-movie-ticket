package woowacourse.movie.domain

import java.time.LocalDate
import java.time.LocalDateTime

data class MovieTicket(
    val movie: Movie,
    val showTime: LocalDateTime,
    val peopleCount: Int = 1,
    val totalAmount: Int,
    val isBooked: Boolean = true,
) {
    fun cancel(): CancelResult =
        if (!this.isBooked) {
            CancelResult.Error(CancelError.IsNotBooked)
        } else {
            CancelResult.Success(
                MovieTicket(
                    this.movie,
                    this.showTime,
                    this.peopleCount,
                    this.totalAmount,
                    false
                )
            )
        }
}