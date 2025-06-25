package woowacourse.movie.intentmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class SeatIntentModel(
    val id: Long,
    val title: String,
    val screeningDateTime: LocalDateTime,
    val peopleCount: Int,
) : Parcelable

