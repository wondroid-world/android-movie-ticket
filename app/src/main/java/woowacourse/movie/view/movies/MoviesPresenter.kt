package woowacourse.movie.view.movies

import woowacourse.movie.dummy.Dummy
import woowacourse.movie.intentmodel.TicketingIntentModel
import woowacourse.movie.uimodel.MovieUiModel

class MoviesPresenter(
    private val view: MoviesContract.View,
) : MoviesContract.Presenter {
    override fun initData() {
        val movies =
            Dummy.data.map { movie ->
                MovieUiModel(
                    id = movie.id,
                    title = movie.title,
                    poster = movie.poster,
                    screeningPeriod = movie.screeningPeriod,
                    runningTime = movie.runningTime,
                )
            }
        val items = mutableListOf<MoviesViewType>()
        movies.forEachIndexed { index, movie ->
            items.add(MoviesViewType.Movie(movie))
            if ((index + 1) % 3 == 0) {
                items.add(MoviesViewType.Ad)
            }
        }
        view.initAdapter(items)
    }

    override fun bookMovie(movie: MovieUiModel) {
        val ticketingIntentModel =
            TicketingIntentModel.of(
                id = movie.id,
                title = movie.title,
                poster = movie.poster,
                screeningPeriod = movie.screeningPeriod,
                runningTime = movie.runningTime,
            )

        view.moveOtherView(ticketingIntentModel)
    }
}
