package woowacourse.movie.view.ticket

import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.MovieFixture
import woowacourse.movie.R
import woowacourse.movie.view.ticket.summary.SummaryActivity

@RunWith(AndroidJUnit4::class)
class SummaryActivityTest {
    @Before
    fun setup() {
        val intent =
            SummaryActivity.intent(
                context = ApplicationProvider.getApplicationContext<Context>(),
                summary = MovieFixture.summaryIntentModel,
            )
        ActivityScenario.launch<SummaryActivity>(intent)
    }

    @Test
    fun 안내_멘트가_있다() {
        onView(withId(R.id.textView_summary_notification_message))
            .check(
                matches(
                    withText(
                        SUMMARY_NOTIFICATION_MESSAGE,
                    ),
                ),
            )
    }

    @Test
    fun 예약_정보가_표시된다() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        onView(withId(R.id.textView_summary_movie_title))
            .check(
                matches(
                    withText(
                        MovieFixture.summaryIntentModel.title,
                    ),
                ),
            )
        onView(withId(R.id.textView_summary_movie_screening_date_time))
            .check(
                matches(
                    withText(
                        context.getString(
                            R.string.summary_movie_screening_date_time,
                            MovieFixture.summaryIntentModel.screeningDateTime.year,
                            MovieFixture.summaryIntentModel.screeningDateTime.monthValue,
                            MovieFixture.summaryIntentModel.screeningDateTime.dayOfMonth,
                            MovieFixture.summaryIntentModel.screeningDateTime.hour,
                            MovieFixture.summaryIntentModel.screeningDateTime.minute,
                        ),
                    ),
                ),
            )
        onView(withId(R.id.textView_summary_movie_people_count)).check(
            matches(
                withText(
                    context.getString(
                        R.string.summary_movie_people_count,
                        MovieFixture.summaryIntentModel.peopleCount,
                    ),
                ),
            ),
        )
        onView(withId(R.id.textView_summary_movie_total_amount)).check(
            matches(
                withText(
                    context.getString(
                        R.string.summary_movie_total_amount,
                        MovieFixture.summaryIntentModel.totalAmount,
                    ),
                ),
            ),
        )
    }

    companion object {
        private const val SUMMARY_NOTIFICATION_MESSAGE = "영화 상영 시작 시간 15분 전까지\n취소가 가능합니다."
    }
}
