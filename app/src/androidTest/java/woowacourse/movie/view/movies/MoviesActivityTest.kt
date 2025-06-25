package woowacourse.movie.view.movies

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
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
        onView(withId(R.id.recyclerview_movies)).check(matches(isDisplayed()))
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

        onView(withId(R.id.recyclerview_movies))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0),
            )

        onView(withText(movie.title)).check(matches(isDisplayed()))
        onView(
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
        ).check(matches(isDisplayed()))
        onView(
            withText(
                context.getString(
                    R.string.movie_running_time,
                    movie.runningTime,
                ),
            ),
        ).check(matches(isDisplayed()))
    }

    @Test
    fun 영화목록_세번째에_광고를_보여준다() {
        onView(withId(R.id.recyclerview_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                4,
            ),
        )

        onView(withId(R.id.imageView_main_ad)).check(matches(isDisplayed()))
    }
}
