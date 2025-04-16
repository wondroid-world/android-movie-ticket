package woowacourse.movie.domain

import woowacourse.movie.R
import java.time.LocalDate

class Movies(
    val movies: List<Movie>,
) {
    companion object {
        val MOVIES: List<Movie> = listOf(
            Movie(
                "마미",
                R.drawable.mammy_poster,
                LocalDate.of(2025, 4, 15),
                125
            )
        )
    }
}
