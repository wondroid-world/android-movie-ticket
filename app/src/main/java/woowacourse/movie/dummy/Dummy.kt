package woowacourse.movie.dummy

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ScreeningPeriod
import java.time.LocalDate

object Dummy {
    val data: List<Movie> =
        listOf(
            Movie(
                id = 1L,
                title = "해리 포터와 마법사의 돌",
                poster = "harry_potter_and_the_sorcerers_stone",
                screeningPeriod =
                    ScreeningPeriod(
                        LocalDate.of(2025, 4, 1),
                        LocalDate.of(2025, 4, 25),
                    ),
                runningTime = 152,
            ),
        )
}
