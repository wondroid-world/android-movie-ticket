package woowacourse.movie.intentmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TicketingIntentModel(
    val id: Long,
    val title: String,
    val poster: String,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: Int,
) : Parcelable {
    companion object {
        fun of(
            id: Long,
            title: String,
            poster: String,
            screeningPeriod: woowacourse.movie.domain.ScreeningPeriod,
            runningTime: Int,
        ): TicketingIntentModel =
            TicketingIntentModel(
                id = id,
                title = title,
                poster = poster,
                screeningPeriod =
                    ScreeningPeriod(
                        screeningPeriod.start,
                        screeningPeriod.end,
                    ),
                runningTime = runningTime,
            )
    }
}
