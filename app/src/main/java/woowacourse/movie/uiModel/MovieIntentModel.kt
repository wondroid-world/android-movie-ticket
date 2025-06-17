package woowacourse.movie.uiModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class MovieIntentModel(
    val name: String,
    val screeningDate: LocalDate,
) : Parcelable
