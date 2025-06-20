package woowacourse.movie.view.movies

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.`is`
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.dummy.Dummy
import woowacourse.movie.uimodel.MovieUiModel

class MoviesActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MoviesActivity::class.java)

    @Test
    fun 영화목록이_있다() {
        onView(withId(R.id.main_movies)).check(matches(isDisplayed()))
    }

    @Test
    fun 영화목록_첫번째_요소의_값을_보여준다() {
        val movie =
            Dummy.data.map { movie ->
                MovieUiModel(
                    id = movie.id,
                    title = movie.title,
                    poster = movie.poster,
                    screeningPeriod = movie.screeningPeriod,
                    runningTime = movie.runningTime,
                )
            }[0]
        val context = ApplicationProvider.getApplicationContext<Context>()

        onData(`is`(movie))
            .onChildView(withId(R.id.textView_main_movie_title))
            .check(matches(withText(movie.title)))
        onData(`is`(movie)).onChildView(withId(R.id.textView_main_movie_screening_period)).check(
            matches(
                withText(
                    context.getString(
                        R.string.movie_screening_date,
                        movie.screeningPeriod.start.year,
                        movie.screeningPeriod.start.monthValue,
                        movie.screeningPeriod.start.dayOfMonth,
                        movie.screeningPeriod.end.year,
                        movie.screeningPeriod.end.monthValue,
                        movie.screeningPeriod.end.dayOfMonth,
                    ),
                ),
            ),
        )
        onData(`is`(movie)).onChildView(withId(R.id.textView_main_movie_running_time)).check(
            matches(
                withText(
                    context.getString(
                        R.string.movie_running_time,
                        movie.runningTime,
                    ),
                ),
            ),
        )
        onData(`is`(movie))
            .onChildView(withId(R.id.imageView_main_movie_poster))
            .check(matches(isDisplayed()))
        onData(`is`(movie))
            .onChildView(withId(R.id.button_main_movie_book))
            .check(matches(withText(R.string.main_movie_book)))
    }

    @Test
    fun 지금_예매를_클릭하면_선택_완료_버튼이_보인다() {
        // when: 사용자가 지금 예매 버튼을 클릭하면
        onView(withId(R.id.button_main_movie_book))
            .perform(click())

        // then: 화면에 선택 완료 버튼이 표시된다
        onView(withId(R.id.button_ticketing_movie_selected))
            .check(matches(withText(TICKETING_MOVIE_SELECTED)))
    }

    companion object {
        private const val TICKETING_MOVIE_SELECTED: String = "선택 완료"
    }
}
