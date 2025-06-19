package woowacourse.movie.intentmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieIntentModel(
    val id: Long,
    val name: String,
    val screeningPeriod: ScreeningPeriod,
) : Parcelable {
    companion object {
        fun of(
            id: Long,
            name: String,
            screeningPeriod: woowacourse.movie.domain.ScreeningPeriod,
        ): MovieIntentModel =
            MovieIntentModel(
                id = id,
                name = name,
                screeningPeriod =
                    ScreeningPeriod(
                        screeningPeriod.start,
                        screeningPeriod.end,
                    ),
            )
    }
}
