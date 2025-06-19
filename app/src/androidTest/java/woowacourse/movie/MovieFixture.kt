package woowacourse.movie

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ScreeningPeriod
import woowacourse.movie.intentmodel.MovieIntentModel
import woowacourse.movie.intentmodel.SummaryIntentModel
import java.time.LocalDate
import java.time.LocalDateTime

object MovieFixture {
    val summaryIntentModel =
        SummaryIntentModel(
            id = 1L,
            title = "해리 포터와 마법사의 돌",
            screeningDateTime = LocalDateTime.of(2025, 4, 15, 11, 0),
            peopleCount = 2,
            totalAmount = 26000
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
