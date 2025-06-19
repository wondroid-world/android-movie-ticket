package woowacourse.movie

import woowacourse.movie.domain.Movie
import woowacourse.movie.intentmodel.MovieIntentModel
import java.time.LocalDate

object MovieFixture {
    const val SUMMARY_MOVIE_TITLE = "해리 포터와 마법사의 돌"
    const val SUMMARY_MOVIE_SCREENING_DATE = "2025.4.1"

    val movieIntentModel =
        MovieIntentModel(
            id = 1L,
            name = "해리 포터와 마법사의 돌",
            screeningDate = LocalDate.of(2025, 4, 1),
        )
}
