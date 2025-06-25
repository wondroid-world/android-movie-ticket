package woowacourse.movie.view.movies

import woowacourse.movie.intentmodel.TicketingIntentModel
import woowacourse.movie.uimodel.MovieUiModel

interface MoviesContract {
    interface Presenter {
        fun initData()

        fun bookMovie(movie: MovieUiModel)
    }

    interface View {
        fun initAdapter(items: List<MoviesViewType>)

        fun moveOtherView(movieIntentModel: TicketingIntentModel)
    }
}
