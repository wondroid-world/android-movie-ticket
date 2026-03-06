package woowacourse.movie.data

import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ScreeningPeriod
import java.time.LocalDate

object MovieDummy {
    val movies: List<Movie> =
        listOf(
            Movie(
                id = 1L,
                title = "해리 포터와 마법사의 돌",
                poster = R.drawable.harry_potter_and_the_sorcerers_stone,
                screeningPeriod =
                    ScreeningPeriod(
                        LocalDate.of(2026, 6, 1),
                        LocalDate.of(2026, 6, 25),
                    ),
                runningTime = 152,
            ),
        )
}