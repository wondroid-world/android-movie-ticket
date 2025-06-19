package woowacourse.movie.view.movies

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieUiModel

class MoviesViewHolder(
    private val view: View,
    private val movies: List<MovieUiModel>,
    private val bookMovie: (MovieUiModel) -> Unit,
) {
    private var position = NO_POSITION

    init {
        view.findViewById<Button>(R.id.button_main_movie_book).setOnClickListener {
            bookMovie(movies[position])
        }
    }

    private val title = view.findViewById<TextView>(R.id.textView_main_movie_title)
    private val screeningPeriod = view.findViewById<TextView>(R.id.textView_main_movie_screening_period)
    private val runningTime = view.findViewById<TextView>(R.id.textView_main_movie_running_time)
    private val poster = view.findViewById<ImageView>(R.id.imageView_main_movie_poster)

    fun bind(position: Int) {
        this.position = position
        val movie = movies[this.position]

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
        movie.posterImage(poster.context).load(poster, poster.context)
        view.findViewById<Button>(R.id.button_main_movie_book).setOnClickListener {
            bookMovie(movies[position])
        }
    }

    companion object {
        private const val NO_POSITION: Int = -1
    }
}
