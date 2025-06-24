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
                        LocalDate.of(2025, 6, 1),
                        LocalDate.of(2025, 6, 25),
                    ),
                runningTime = 152,
            ),
            Movie(
                id = 2L,
                title = "해리포터와 비밀의 방",
                poster = "harry_potter_and_the_chamber_of_secrets",
                screeningPeriod =
                    ScreeningPeriod(
                        LocalDate.of(2025, 6, 1),
                        LocalDate.of(2025, 6, 28),
                    ),
                runningTime = 162,
            ),
            Movie(
                id = 3L,
                title = "해리포터와 아즈카반의 죄수",
                poster = "harry_potter_and_the_prisoner_of_azkaban",
                screeningPeriod =
                    ScreeningPeriod(
                        LocalDate.of(2025, 7, 1),
                        LocalDate.of(2025, 7, 31),
                    ),
                runningTime = 141,
            ),
            Movie(
                id = 4L,
                title = "해리포터와 불의 잔",
                poster = "harry_potter_and_the_goblet_of_fire",
                screeningPeriod =
                    ScreeningPeriod(
                        LocalDate.of(2025, 8, 1),
                        LocalDate.of(2025, 8, 25),
                    ),
                runningTime = 157,
            ),
        )
}
