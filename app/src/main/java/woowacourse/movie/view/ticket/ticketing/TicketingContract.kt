package woowacourse.movie.view.ticket.ticketing

import woowacourse.movie.intentmodel.SummaryIntentModel
import woowacourse.movie.intentmodel.TicketingIntentModel
import woowacourse.movie.uimodel.MovieUiModel
import java.time.LocalDate
import java.time.LocalTime

interface TicketingContract {
    interface Presenter {
        fun initData(ticketingIntentModel: TicketingIntentModel)

        fun selectedMovie()

        fun selectedTime(time: LocalTime)

        fun selectedDate(date: LocalDate)

        fun minusCount()

        fun plusCount()
    }

    interface View {
        fun bind(movie: MovieUiModel)

        fun peopleCount(count: Int)

        fun toastMessage()

        fun initDateSpinnerAdapter(movieAvailableDates: List<LocalDate>)

        fun initTimeSpinnerAdapter(movieAvailableTimes: List<LocalTime>)

        fun showSelectedMovie(summary: SummaryIntentModel)

        fun showNoAvailableDates()
    }
}
