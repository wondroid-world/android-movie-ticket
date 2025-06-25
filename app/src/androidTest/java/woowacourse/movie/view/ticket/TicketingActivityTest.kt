package woowacourse.movie.view.ticket

import android.content.Context
import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.MovieFixture
import woowacourse.movie.R
import woowacourse.movie.domain.ScreeningPeriod
import woowacourse.movie.intentmodel.TicketingIntentModel
import woowacourse.movie.uimodel.MovieUiModel
import woowacourse.movie.view.ticket.ticketing.TicketingActivity

class TicketingActivityTest {
    private lateinit var context: Context
    private lateinit var ticketingIntentModel: TicketingIntentModel
    private lateinit var scenario: ActivityScenario<TicketingActivity>

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext<Context>()
        ticketingIntentModel = MovieFixture.ticketingIntentModel
        val intent = TicketingActivity.intent(context, ticketingIntentModel)
        scenario = ActivityScenario.launch<TicketingActivity>(intent)
    }

    @Test
    fun 영화_정보를_보여준다() {
        val movieUiModel =
            MovieUiModel(
                id = ticketingIntentModel.id,
                title = ticketingIntentModel.title,
                poster = ticketingIntentModel.poster,
                screeningPeriod =
                    ScreeningPeriod(
                        ticketingIntentModel.screeningPeriod.start,
                        ticketingIntentModel.screeningPeriod.end,
                    ),
                runningTime = ticketingIntentModel.runningTime,
            )
        onView(withId(R.id.imageView_ticketing_movie_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.textView_ticketing_movie_title)).check(matches(withText(movieUiModel.title)))
        onView(withId(R.id.textView_ticketing_movie_screening_period)).check(
            matches(
                withText(
                    context.getString(
                        R.string.movie_screening_date,
                        movieUiModel.screeningPeriod.start.year,
                        movieUiModel.screeningPeriod.start.monthValue,
                        movieUiModel.screeningPeriod.start.dayOfMonth,
                        movieUiModel.screeningPeriod.end.year,
                        movieUiModel.screeningPeriod.end.monthValue,
                        movieUiModel.screeningPeriod.end.dayOfMonth,
                    ),
                ),
            ),
        )
        onView(withId(R.id.textView_ticketing_movie_running_time)).check(
            matches(
                withText(
                    context.getString(
                        R.string.movie_running_time,
                        movieUiModel.runningTime,
                    ),
                ),
            ),
        )
    }

    @Test
    fun 영화_관람_인원_수의_초기값은_1이다() {
        onView(withId(R.id.textView_ticketing_movie_people_count)).check(
            matches(
                withText(
                    PEOPLE_COUNT_DEFAULT_VALUE.toString(),
                ),
            ),
        )
    }

    @Test
    fun 플러스_버튼을_누르면_영화_관람_인원_수의_초기값에서_1이_증가한다() {
        // when: 사용자가 플러스 버튼을 누르면
        onView(withId(R.id.button_ticketing_plus_people_count)).perform(click())

        // then: defuault 값에서 1증가한다
        onView(withId(R.id.textView_ticketing_movie_people_count)).check(
            matches(
                withText(
                    (PEOPLE_COUNT_DEFAULT_VALUE + 1).toString(),
                ),
            ),
        )
    }

    @Test
    fun 플러스_버튼을_누르고_마이너스_버튼을_누르면_영화_관람_인원_수의_초기값이_그대로이다() {
        // when: 사용자가 플러스 버튼을 누르고 마이너스 버튼을 누르면,
        onView(withId(R.id.button_ticketing_plus_people_count)).perform(click())
        onView(withId(R.id.button_ticketing_minus_people_count)).perform(click())

        // then: defuault 값에서 그대로이다
        onView(withId(R.id.textView_ticketing_movie_people_count)).check(
            matches(
                withText(
                    PEOPLE_COUNT_DEFAULT_VALUE.toString(),
                ),
            ),
        )
    }

    @Test
    fun 예매_완료를_클릭하면_좌석_선택으로_넘어간다() {
        // when: Dialog화면에서_예매_완료를_클릭
        onView(withId(R.id.button_ticketing_movie_selected)).perform(click())

        // then: 영화_좌석_선택페이지로_넘어간다
        onView(withText(SEAT_SCREEN)).check(matches(isDisplayed()))
    }

    @Test
    fun 화면을_회전해도_인원수는_변하지_않는다() {
        onView(withId(R.id.button_ticketing_plus_people_count)).perform(click())

        scenario.onActivity { it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE }

        onView(withId(R.id.textView_ticketing_movie_people_count)).check(
            matches(
                withText((PEOPLE_COUNT_DEFAULT_VALUE + 1).toString()),
            ),
        )
    }

    companion object {
        private const val PEOPLE_COUNT_DEFAULT_VALUE: Int = 1
        private const val SEAT_SCREEN = "SCREEN"
    }
}
