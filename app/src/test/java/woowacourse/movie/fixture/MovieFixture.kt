package woowacourse.movie.fixture

import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import java.time.LocalDate


object MovieFixture {
    val movie = Movie(
        1,
        "해리포터와 마법사의 돌",
        LocalDate.of(2025, 4, 1),
        152,
        R.drawable.harry_potter_and_the_sorcerers_stone.toString(),
    )
}
