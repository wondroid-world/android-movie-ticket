package woowacourse.movie.intent

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ScreeningPeriod
import java.time.LocalDate

@Parcelize
data class MovieIntentModel(
    val id: Long,
    val title: String,
    val screeningPeriod: ScreeningPeriodIntentModel,
    val runningTime: Int,
    val poster: Int
) : Parcelable

fun MovieIntentModel.toDomain(): Movie = Movie(
    id = id,
    title = title,
    screeningPeriod = screeningPeriod.toDomain(),
    runningTime = runningTime,
    poster = poster,
)

fun Movie.toIntentModel(): MovieIntentModel = MovieIntentModel(
    id = id,
    title = title,
    screeningPeriod = screeningPeriod.toIntentModel(),
    runningTime = runningTime,
    poster = poster,
)