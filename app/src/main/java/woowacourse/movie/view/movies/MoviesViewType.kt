package woowacourse.movie.view.movies

import woowacourse.movie.uimodel.MovieUiModel

sealed class MoviesViewType(
    val viewType: Int,
) {
    data class Movie(
        val movie: MovieUiModel,
    ) : MoviesViewType(viewType = TYPE_MOVIE)

    data object Ad : MoviesViewType(viewType = TYPE_AD)

    companion object {
        const val TYPE_MOVIE: Int = 0
        const val TYPE_AD = 1
    }
}
