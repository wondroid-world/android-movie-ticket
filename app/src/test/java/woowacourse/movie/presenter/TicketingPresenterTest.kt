package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.MovieFixture
import woowacourse.movie.view.ticket.ticketing.TicketingContract
import woowacourse.movie.view.ticket.ticketing.TicketingPresenter
import java.time.LocalTime

class TicketingPresenterTest {
    private lateinit var view: TicketingContract.View
    private lateinit var presenter: TicketingContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = TicketingPresenter(view)
    }

    @Test
    fun `initData를 호출하면, bind, initDataSpinnerAdapter, initTimeSpinnerAdapter가 호출된다`() {
        // given: ticketingIntentModel이 주어지면,
        every { view.bind(any()) } just Runs
        every { view.peopleCount(any()) } just Runs
        every { view.initDateSpinnerAdapter(any()) } just Runs
        every { view.initTimeSpinnerAdapter(any()) } just Runs

        // when: initData를 호출시
        presenter.initData(MovieFixture.ticketingIntentModel)

        // then: bind, initDataSpinnerAdapter, initTimeSpinnerAdapter 호출됨
        verifyAll {
            view.bind(any())
            view.peopleCount(any())
            view.initDateSpinnerAdapter(any())
            view.initTimeSpinnerAdapter(any())
        }
    }

    @Test
    fun `selectedDate를 호출하면, initTimeSpinnerAdapter를 호출한다`() {
        presenter.initData(MovieFixture.ticketingIntentModel)
        clearMocks(view)

        // given: 날짜를 고르면
        val date =
            MovieFixture.ticketingIntentModel.screeningPeriod.start
                .plusDays(1)
        every { view.initTimeSpinnerAdapter(any()) } just Runs

        // when: selected를 호출
        presenter.selectedDate(date)

        // then: initTimeSpinnerAdapter를 호출
        verify {
            view.initTimeSpinnerAdapter(any())
        }
    }

    @Test
    fun `selectedMovie 호출하면,showSelectedMovie를 호출한다`() {
        presenter.initData(MovieFixture.ticketingIntentModel)
        presenter.selectedDate(
            MovieFixture.ticketingIntentModel.screeningPeriod.start
                .plusDays(1),
        )
        presenter.selectedTime(LocalTime.of(10, 0))
        clearMocks(view)

        every { view.showSelectedMovie(any()) } just Runs

        // when: selectedMovie 호출
        presenter.selectedMovie()

        // then: showSelectedMovie를 호출
        verify {
            view.showSelectedMovie(any())
        }
    }

    @Test
    fun `plusCount를 호출하면, poplecount를 호출한다`() {
        every { view.peopleCount(any()) } just Runs

        presenter.plusCount()

        verify { view.peopleCount(any()) }
    }

    @Test
    fun `count가 1일 때, minusCount를 호출하면, toastMessage를 호출한다`() {
        // given: count가 1일 때
        every { view.toastMessage() } just Runs

        // when: minuscount가 호출
        presenter.minusCount()

        // then: toastMessage 호출
        verify { view.toastMessage() }
    }

    @Test
    fun `count가 1일 때, minusCount를 호출하면, poplecount를 호출한다`() {
        presenter.plusCount()
        clearMocks(view)

        every { view.peopleCount(any()) } just Runs

        presenter.plusCount()

        verify { view.peopleCount(any()) }
    }
}
