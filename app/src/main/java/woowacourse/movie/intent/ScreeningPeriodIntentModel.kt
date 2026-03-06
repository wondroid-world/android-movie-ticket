package woowacourse.movie.intent

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.ScreeningPeriod
import java.time.LocalDate

@Parcelize
data class ScreeningPeriodIntentModel(
    val start: LocalDate,
    val end: LocalDate,
) : Parcelable

fun ScreeningPeriodIntentModel.toDomain() = ScreeningPeriod(start, end)

fun ScreeningPeriod.toIntentModel() = ScreeningPeriodIntentModel(start, end)