package woowacourse.movie.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.Movie

class MovieViewHolder(
    private val view: View,
    private val movies: List<Movie>,
    private val bookingClickListener: (Movie) -> Unit,
) {
    private var position = NO_POSITION

    init {
        view.findViewById<TextView>(R.id.tv_movie_book).setOnClickListener {
            bookingClickListener(movies[position])
        }
    }
    private val title = view.findViewById<TextView>(R.id.tv_movie_title)
    private val showtime = view.findViewById<TextView>(R.id.tv_movie_showtime)
    private val runningTime = view.findViewById<TextView>(R.id.tv_movie_running_time)
    private val poster = view.findViewById<ImageView>(R.id.iv_movie_poster)

    fun bind(position: Int) {
        this.position = position
        val movie = movies[position]

        title.text = movie.title
        showtime.text = showtime.context.getString(
            R.string.movies_movie_screening_date,
            movie.screeningPeriod.start.year,
            movie.screeningPeriod.start.monthValue,
            movie.screeningPeriod.start.dayOfMonth,
            movie.screeningPeriod.end.year,
            movie.screeningPeriod.end.monthValue,
            movie.screeningPeriod.end.dayOfMonth,
        )
        runningTime.text =
            runningTime.context.getString(R.string.movies_movie_running_time, movie.runningTime)
        poster.setImageResource(movie.poster)
    }

    companion object {
        private const val NO_POSITION: Int = -1
    }
}