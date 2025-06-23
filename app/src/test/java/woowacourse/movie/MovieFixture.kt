package woowacourse.movie

import woowacourse.movie.domain.ScreeningPeriod
import woowacourse.movie.intentmodel.SummaryIntentModel
import woowacourse.movie.intentmodel.TicketingIntentModel
import woowacourse.movie.uimodel.MovieUiModel
import java.time.LocalDate
import java.time.LocalDateTime

object MovieFixture {
    val summaryIntentModel =
        SummaryIntentModel(
            id = 1L,
            title = "해리 포터와 마법사의 돌",
            screeningDateTime = LocalDateTime.of(2025, 4, 15, 11, 0),
            peopleCount = 2,
            totalAmount = 26000,
        )

    val movieUiModel =
        MovieUiModel(
            id = 1L,
            title = "해리 포터와 마법사의 돌",
            poster = "harry_potter_and_the_sorcerers_stone",
            screeningPeriod = ScreeningPeriod(LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 25)),
            runningTime = 152,
        )
}
