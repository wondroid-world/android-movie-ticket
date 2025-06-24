package woowacourse.movie.domain

import java.time.LocalDateTime

data class Summary(
    val movie: Movie,
    val screeningDateTime: LocalDateTime,
    val peopleCount: Int,
    val bookingSeats: List<Seat> = emptyList(),
) {
    fun totalAmount(): Int = bookingSeats.sumOf { it.price }
}



