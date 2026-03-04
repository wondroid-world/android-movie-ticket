package woowacourse.movie.intent

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.Movie
import java.time.LocalDate

@Parcelize
data class MovieIntentModel(
    val id: Long,
    val title: String,
    val showtime: LocalDate,
    val runningTime: Int,
    val poster: Int
) : Parcelable

fun MovieIntentModel.toDomain(): Movie = Movie(
    id = id,
    title = title,
    showtime = showtime,
    runningTime = runningTime,
    poster = poster,
)

fun Movie.toIntentModel(): MovieIntentModel = MovieIntentModel(
    id = id,
    title = title,
    showtime = showtime,
    runningTime = runningTime,
    poster = poster,
)