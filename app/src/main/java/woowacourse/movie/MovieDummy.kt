package woowacourse.movie

import woowacourse.movie.domain.Movie
import java.time.LocalDate

object MovieDummy {
    val movies: List<Movie> = listOf(
        Movie(
            1,
            "해리포터와 마법사의 돌",
            LocalDate.of(2025, 4, 1),
            125,
            R.drawable.harry_potter_and_the_sorcerers_stone
        )
    )
}
