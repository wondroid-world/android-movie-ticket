package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.fixture.MovieFixture

class MovieTicketTest {
    @Test
    fun `예약되지 않은 표는 취소할 수 없다`() {
        // given
        val ticket = MovieTicket(MovieFixture.movie, false)
        // when
        val result = ticket.cancel()
        // then
        assertThat(result).isEqualTo(CancelResult.Error(CancelError.IsNotBooked))
    }

    @Test
    fun `예약된 표는 취소할 수  있다`() {
        // given
        val ticket = MovieTicket(MovieFixture.movie, true)
        // when
        val result = ticket.cancel()
        // then
        assertThat(result).isEqualTo(CancelResult.Success(MovieTicket(MovieFixture.movie, false)))
    }
}