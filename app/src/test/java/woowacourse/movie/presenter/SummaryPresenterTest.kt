package woowacourse.movie.presenter

import io.kotest.core.spec.style.AnnotationSpec.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.view.ticket.summary.SummaryContract
import woowacourse.movie.view.ticket.summary.SummaryPresenter

class SummaryPresenterTest {
    private lateinit var presenter: SummaryContract.Presenter
    private lateinit var view: SummaryContract.View

    @BeforeEach
    fun setUp() {
        presenter = SummaryPresenter(view)
    }

    @Test
    fun `getData 호출시 view의 showData가 호출된다`() {
        // given, when : 예매 정보가 들어오면,
    }
}
