package woowacourse.movie.domain

data class MovieTicket(
    val movie: Movie,
    val isBooked: Boolean = true,
) {
    fun cancel(): CancelResult =
        if (!this.isBooked) {
            CancelResult.Error(CancelError.IsNotBooked)
        } else {
            CancelResult.Success(MovieTicket(this.movie, false))
        }
}