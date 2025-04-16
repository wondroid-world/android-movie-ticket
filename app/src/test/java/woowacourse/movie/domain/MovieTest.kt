package woowacourse.movie.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import woowacourse.movie.R
import java.time.LocalDate

class MovieTest {
    @Test
    fun `영화에는 영화 제목, 포스터, 상영일, 러닝타임이 있다`() {
        assertDoesNotThrow { Movie("마미", R.drawable.mammy_poster, LocalDate.of(2025, 4, 15), 125) }
    }
}
