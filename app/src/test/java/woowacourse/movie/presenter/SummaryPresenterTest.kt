package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.MovieFixture
import woowacourse.movie.uimodel.SummaryUiModel
import woowacourse.movie.view.ticket.summary.SummaryContract
import woowacourse.movie.view.ticket.summary.SummaryPresenter

class SummaryPresenterTest {
    private lateinit var presenter: SummaryContract.Presenter
    private lateinit var view: SummaryContract.View

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = SummaryPresenter(view)
    }

    @Test
    fun `getData 호출시 view의 showData가 호출된다`() {
        // given : 예매 정보가 주어지면,
        every { view.showData(any()) } just Runs

        val expected =
            SummaryUiModel(
                id = MovieFixture.summaryIntentModel.id,
                title = MovieFixture.summaryIntentModel.title,
                screeningDateTime = MovieFixture.summaryIntentModel.screeningDateTime,
                peopleCount = MovieFixture.summaryIntentModel.peopleCount,
                totalAmount = MovieFixture.summaryIntentModel.totalAmount,
            )

        // when : getData를 호출 시
        presenter.getData(MovieFixture.summaryIntentModel)

        // then : showData가 호출된다
        verify { view.showData(expected) }
    }
}
