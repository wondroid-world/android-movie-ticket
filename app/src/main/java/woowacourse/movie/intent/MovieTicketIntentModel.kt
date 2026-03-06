package woowacourse.movie.intent

import woowacourse.movie.domain.CancelError
import woowacourse.movie.domain.CancelResult
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieTicket
import java.time.LocalDate

data class MovieTicketIntentModel(
    val movie: MovieIntentModel,
    val showTime: LocalDate,
    val isBooked: Boolean,
)

fun MovieTicketIntentModel.toDomain() = MovieTicket(
    movie.toDomain(),
    showTime,
    isBooked
)

fun  MovieTicket.toIntentModel() = MovieTicketIntentModel(
    movie.toIntentModel(),
    showTime,
    isBooked
)