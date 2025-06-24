package woowacourse.movie.intentmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class SummaryIntentModel(
    val id: Long,
    val title: String,
    val screeningDateTime: LocalDateTime,
    val seats: List<Seat>,
    val peopleCount: Int,
    val totalAmount: Int,
) : Parcelable

