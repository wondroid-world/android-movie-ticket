package woowacourse.movie.view.movies

import woowacourse.movie.intentmodel.TicketingIntentModel
import woowacourse.movie.uimodel.MovieUiModel

interface MoviesContract {
    interface Presenter {
        fun initData()

        fun bookMovie(movie: MovieUiModel)
    }

    interface View {
        fun initAdapter(movies: List<MovieUiModel>)

        fun moveOtherView(movieIntentModel: TicketingIntentModel)
    }
}
