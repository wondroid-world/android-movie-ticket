package woowacourse.movie.intentmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Seat(
    val row: Int,
    val col: Int,
) : Parcelable
