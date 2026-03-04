package woowacourse.movie

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.domain.Movie

class MovieViewHolder(
    private val view: View,
    private val movies: List<Movie>,
    private val bookedClickListener: (Movie) -> Unit,
) {
    private var position = NO_POSITION

    init {
        view.findViewById<TextView>(R.id.button_main_movie_book).setOnClickListener {
            bookedClickListener(movies[position])
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
            R.string.movie_screening_date,
            movie.showtime.year,
            movie.showtime.monthValue,
            movie.showtime.dayOfMonth
        )
        runningTime.text =
            runningTime.context.getString(R.string.movie_running_time, movie.runningTime)
        poster.setImageResource(movie.poster)
    }

    companion object {
        private const val NO_POSITION: Int = -1
    }
}