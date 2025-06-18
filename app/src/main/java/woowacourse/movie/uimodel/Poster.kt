package woowacourse.movie.uimodel

sealed class Poster {
    data class Drawable(val resId: Int): Poster()
    data class Remote(val url: String): Poster()
}
