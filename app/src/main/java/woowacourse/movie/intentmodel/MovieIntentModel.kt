package woowacourse.movie.intentmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class MovieIntentModel(
    val id: Long,
    val name: String,
    val screeningDate: LocalDate,
) : Parcelable
