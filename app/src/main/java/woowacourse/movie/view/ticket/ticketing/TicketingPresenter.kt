package woowacourse.movie.view.ticket.ticketing

import woowacourse.movie.domain.ScreeningPeriod
import woowacourse.movie.intentmodel.SummaryIntentModel
import woowacourse.movie.intentmodel.TicketingIntentModel
import woowacourse.movie.uimodel.MovieUiModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class TicketingPresenter(
    private val view: TicketingContract.View,
) : TicketingContract.Presenter {
    private var count: Int = PEOPLE_COUNT_DEFAULT_VALUE
    private lateinit var movie: MovieUiModel
    private lateinit var movieAvailableDates: List<LocalDate>
    private lateinit var movieAvailableTimes: List<LocalDateTime>
    private lateinit var movieDate: LocalDate
    private lateinit var movieTime: LocalDateTime

    override fun initData(ticketingIntentModel: TicketingIntentModel) {
        this.movie =
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

        movieAvailableDates = movie.screeningPeriod.availableDates(LocalDate.now())
        if (movieAvailableDates.isEmpty()) {
            view.showNoAvailableDates()
        }
        movieDate = movieAvailableDates[0]
        movieAvailableTimes = movie.screeningPeriod.availableTime(movieDate, LocalDateTime.now())

        view.bind(movie)
        view.initDateSpinnerAdapter(movieAvailableDates)
        view.initTimeSpinnerAdapter(movieAvailableTimes.map { it.toLocalTime() })
        view.peopleCount(count)
    }

    override fun selectedDate(date: LocalDate) {
        this.movieDate = date
        movieAvailableTimes = movie.screeningPeriod.availableTime(movieDate, LocalDateTime.now())
        view.initTimeSpinnerAdapter(movieAvailableTimes.map { it.toLocalTime() })
    }

    override fun selectedTime(time: LocalTime) {
        this.movieTime =
            LocalDateTime.of(
                movieDate.year,
                movieDate.monthValue,
                movieDate.dayOfMonth,
                time.hour,
                time.minute,
            )
    }

    override fun selectedMovie() {
        val summary =
            SummaryIntentModel(
                id = movie.id,
                title = movie.title,
                screeningDateTime = movieTime,
                peopleCount = count,
                totalAmount = count * 13000,
            )
        view.showSelectedMovie(summary)
    }

    override fun minusCount() {
        when {
            (count > PEOPLE_COUNT_DEFAULT_VALUE) -> {
                count--
                view.peopleCount(count)
            }

            else ->
                view.toastMessage()
        }
    }

    override fun plusCount() {
        count++
        view.peopleCount(count)
    }

    companion object {
        private const val PEOPLE_COUNT_DEFAULT_VALUE: Int = 1
    }
}
