package woowacourse.movie.view.ticket.ticketing

import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieUiModel

class TicketingViewHolder(
    view: TicketingActivity,
    private val presenter: TicketingContract.Presenter,
) {
    val dates = view.findViewById<Spinner>(R.id.spinner_ticketing_movie_date)
    val times = view.findViewById<Spinner>(R.id.spinner_ticketing_movie_time)
    val poster = view.findViewById<ImageView>(R.id.imageView_ticketing_movie_poster)
    val title = view.findViewById<TextView>(R.id.textView_ticketing_movie_title)
    val screeningPeriod =
        view.findViewById<TextView>(R.id.textView_ticketing_movie_screening_period)
    val runningTime = view.findViewById<TextView>(R.id.textView_ticketing_movie_running_time)
    val minusPeopleCount = view.findViewById<Button>(R.id.button_ticketing_minus_people_count)
    val plusPeopleCount = view.findViewById<Button>(R.id.button_ticketing_plus_people_count)
    val selectedMovie = view.findViewById<Button>(R.id.button_ticketing_movie_selected)
    val peopleCount = view.findViewById<TextView>(R.id.textView_ticketing_movie_people_count)

    init {
        minusPeopleCount.setOnClickListener {
            presenter.minusCount()
        }
        plusPeopleCount.setOnClickListener {
            presenter.plusCount()
        }
        selectedMovie.setOnClickListener {
            presenter.selectedMovie()
        }
    }

    fun bind(movie: MovieUiModel) {
        movie.posterImage(poster.context).load(poster, poster.context)
        title.text = movie.title
        screeningPeriod.text =
            screeningPeriod.context.getString(
                R.string.movie_screening_date,
                movie.screeningPeriod.start.year,
                movie.screeningPeriod.start.monthValue,
                movie.screeningPeriod.start.dayOfMonth,
                movie.screeningPeriod.end.year,
                movie.screeningPeriod.end.monthValue,
                movie.screeningPeriod.end.dayOfMonth,
            )
        runningTime.text =
            runningTime.context.getString(R.string.movie_running_time, movie.runningTime)
    }

    fun bindPeopleCount(count: Int) {
        peopleCount.text = count.toString()
    }
}
