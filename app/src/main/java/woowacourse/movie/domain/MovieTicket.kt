package woowacourse.movie.domain

import java.time.LocalDate

data class MovieTicket(
    val movie: Movie,
    val showTime: LocalDate,
    val peopleCount: Int = 1,
    val isBooked: Boolean = true,
) {
    fun cancel(): CancelResult =
        if (!this.isBooked) {
            CancelResult.Error(CancelError.IsNotBooked)
        } else {
            CancelResult.Success(MovieTicket(this.movie, this.showTime, this.peopleCount, false))
        }
}