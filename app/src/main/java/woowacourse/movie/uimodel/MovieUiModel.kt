package woowacourse.movie.uimodel

import android.content.Context
import java.time.LocalDate

data class MovieUiModel(
    val id: Long,
    val title: String,
    private val poster: String,
    val screeningDate: LocalDate,
    val runningTime: Int,
) {
    fun posterImage(context: Context): Poster {
        return when (isDrawable(context)) {
            true -> {
                val resId =
                    context.resources.getIdentifier(this.poster, DEFAULT_TYPE, context.packageName)
                Poster.Drawable(resId)
            }

            false -> Poster.Remote(this.poster)
        }
    }

    private fun isDrawable(context: Context): Boolean {
        val resId = context.resources.getIdentifier(this.poster, DEFAULT_TYPE, context.packageName)
        return resId != NOT_EXISTED
    }

    companion object {
        private const val NOT_EXISTED: Int = 0
        private const val DEFAULT_TYPE: String = "drawable"
    }
}


