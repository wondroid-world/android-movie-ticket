package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.MovieFixture
import woowacourse.movie.view.movies.MoviesContract
import woowacourse.movie.view.movies.MoviesPresenter

class MoviesPresentTest {
    private lateinit var view: MoviesContract.View
    private lateinit var presenter: MoviesContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = MoviesPresenter(view)
    }

    @Test
    fun `initData를 호출하면, view의 initAdapter를 호출한다`() {
        // given
        every { view.initAdapter(any()) } just Runs

        // when: initData를 호출
        presenter.initData()

        // then: initAdapter를 호출
        verify { view.initAdapter(any()) }
    }

    @Test
    fun `bookMovie를 호출하면, view의 moveOtherView를 호출한다`() {
        every { view.moveOtherView(any()) } just Runs

        // when: bookMovie를 호출
        presenter.bookMovie(MovieFixture.movieUiModel)

        // then: moveOtherView를 호출
        verify { view.moveOtherView(any()) }
    }
}
