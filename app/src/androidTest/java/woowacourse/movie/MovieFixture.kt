package woowacourse.movie

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ScreeningPeriod
import woowacourse.movie.intentmodel.MovieIntentModel
import java.time.LocalDate

object MovieFixture {
    const val SUMMARY_MOVIE_TITLE = "해리 포터와 마법사의 돌"
    const val SUMMARY_MOVIE_SCREENING_DATE = "2025.4.1"

    val movieIntentModel =
        MovieIntentModel.of(
            id = 1L,
            name = "해리 포터와 마법사의 돌",
            screeningPeriod = ScreeningPeriod(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 25)),
        )

    val data: List<Movie> =
        listOf(
            Movie(
                id = 1L,
                title = "해리 포터와 마법사의 돌",
                poster = "harry_potter_and_the_sorcerers_stone",
                screeningPeriod = ScreeningPeriod(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 25)),
                runningTime = 152,
            ),
        )
}
