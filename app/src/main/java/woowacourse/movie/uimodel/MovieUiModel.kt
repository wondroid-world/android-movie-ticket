package woowacourse.movie.uimodel

import android.content.Context
import java.time.LocalDate

data class MovieUiModel(
    val title: String,
    val poster: String,
    val screeningDate: LocalDate,
    val runningTime: Int,
) {
    fun posterImage(context: Context): Poster {
        return when (isDrawable(context)) {
            true -> {
                val resId =
                    context.resources.getIdentifier(this.poster, "drawable", context.packageName)
                Poster.Drawable(resId)
            }

            false -> Poster.Remote(this.poster)
        }
    }

    private fun isDrawable(context: Context): Boolean {
        val resId = context.resources.getIdentifier(this.poster, "drawable", context.packageName)
        return resId != 0
    }
}


