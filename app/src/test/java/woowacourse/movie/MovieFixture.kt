package woowacourse.movie

import woowacourse.movie.intentmodel.SummaryIntentModel
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
}
