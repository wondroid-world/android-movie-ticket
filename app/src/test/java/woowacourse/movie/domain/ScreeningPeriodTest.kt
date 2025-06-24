package woowacourse.movie.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ScreeningPeriodTest {
    private lateinit var screeningPeriod: ScreeningPeriod
    private lateinit var start: LocalDate
    private lateinit var end: LocalDate

    @BeforeEach
    fun setUp() {
        start = LocalDate.of(2025, 6, 1)
        end = LocalDate.of(2025, 6, 25)
        screeningPeriod = ScreeningPeriod(start, end)
    }

    @Test
    fun `상영 기간 끝나는 날짜가 보고 싶은 날짜 전이면 빈 리스트를 준다`() {
        // given: 상영 기간이 주어짐
        // when: 끝나는 날짜 하루 뒤면,
        val day = end.plusDays(1)
        val days = screeningPeriod.availableDates(day)

        // then: 빈  리스트를 반환한다.
        Assertions.assertThat(days).isEmpty()
    }

    @Test
    fun `상영 기간 시작 전 날짜를 입력하면, 상영 시작부터 끝날 때까지 날짜 개수를 반환한다`() {
        // given: 상영 기간이 주어짐
        // when: 시작 날짜 하루 전이면,
        val day = start.minusDays(1)
        val days = screeningPeriod.availableDates(day)

        // then: 리스트 요소의 개수는 25개 이다
        Assertions.assertThat(days.size).isEqualTo(25)
    }

    @Test
    fun `상영 기간 중간 날짜를 입력하면, 상영 중간부터 끝날 때까지 날짜 개수를 반환한다`() {
        // given: 상영 기간이 주어짐
        // when: 시작 날짜 10일 후면,
        val day = start.plusDays(10)
        val days = screeningPeriod.availableDates(day)

        // then: 리스트 요소의 개수는 15개 이다
        Assertions.assertThat(days.size).isEqualTo(15)
    }

    @Test
    fun `입력 시간보다 입력 날짜가 전이면, 빈 리스트를 반환한다`() {
        // given: 상영 기간이 주어짐
        // when: 입력 시간보다 입력 날짜가 전
        val day = start
        val time = LocalDateTime.of(start.plusDays(1), LocalTime.of(10, 0))
        val times = screeningPeriod.availableTime(day, time)

        // then: 빈 리스트 반환
        Assertions.assertThat(times).isEmpty()
    }

    @Test
    fun `주중이면, 상영 시작 시간은 10시이다`() {
        // given: 상영 기간이 주어짐
        // when: 주중
        val day = start.plusDays(1)
        val time = LocalDateTime.of(start.plusDays(1), LocalTime.of(8, 0))
        val times = screeningPeriod.availableTime(day, time)

        // then: 시작 시간은 10시
        Assertions.assertThat(times[0]).isEqualTo(LocalDateTime.of(start.plusDays(1), LocalTime.of(10, 0)))
    }

    @Test
    fun `주말이면, 상영 시작 시간은 9시이다`() {
        // given: 상영 기간이 주어짐
        // when: 주말
        val day = start
        val time = LocalDateTime.of(start, LocalTime.of(8, 0))
        val times = screeningPeriod.availableTime(day, time)

        // then: 시작 시간은 10시
        Assertions.assertThat(times[0]).isEqualTo(LocalDateTime.of(start, LocalTime.of(9, 0)))
    }
}
