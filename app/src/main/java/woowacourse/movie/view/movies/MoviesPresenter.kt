package woowacourse.movie.view.movies

import woowacourse.movie.data.MovieDummy

class MoviesPresenter(
    private val view: MoviesContract.View,
) : MoviesContract.Presenter {
    override fun loadMovies() {
        val movies = MovieDummy.movies
        view.showMovies(movies)
    }
}