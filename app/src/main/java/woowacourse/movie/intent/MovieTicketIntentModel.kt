package woowacourse.movie.intent

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.CancelError
import woowacourse.movie.domain.CancelResult
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieTicket
import java.time.LocalDate
import java.time.LocalDateTime

@Parcelize
data class MovieTicketIntentModel(
    val movie: MovieIntentModel,
    val showTime: LocalDateTime,
    val peopleCount: Int,
    val totalAmount: Int,
    val isBooked: Boolean,
): Parcelable

fun MovieTicketIntentModel.toDomain() = MovieTicket(
    movie.toDomain(),
    showTime,
    peopleCount,
    totalAmount,
    isBooked
)

fun  MovieTicket.toIntentModel() = MovieTicketIntentModel(
    movie.toIntentModel(),
    showTime,
    peopleCount,
    totalAmount,
    isBooked
)