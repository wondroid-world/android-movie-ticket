package woowacourse.movie.view.ticket.ticketing

import woowacourse.movie.intentmodel.SeatIntentModel
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

        fun updateCount(count: Int?)

        fun updateDatePosition(position: Int)

        fun updateTimePosition(position: Int)
    }

    interface View {
        fun bind(movie: MovieUiModel)

        fun peopleCount(count: Int)

        fun toastMessage()

        fun initDateSpinnerAdapter(movieAvailableDates: List<LocalDate>)

        fun initTimeSpinnerAdapter(movieAvailableTimes: List<LocalTime>)

        fun showSelectedMovie(seat: SeatIntentModel)

        fun showNoAvailableDates()

        fun showSelectedDate(position: Int)

        fun showSelectedTime(position: Int)
    }
}
