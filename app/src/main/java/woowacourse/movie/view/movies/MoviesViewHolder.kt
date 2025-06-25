package woowacourse.movie.view.movies

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieUiModel

class MoviesViewHolder(
    private val view: View,
    private val bookMovie: (MovieUiModel) -> Unit,
) : RecyclerView.ViewHolder(view) {
    private lateinit var movie: MovieUiModel

    init {
        view.findViewById<Button>(R.id.button_main_movie_book).setOnClickListener {
            bookMovie(movie)
        }
    }

    private val title = view.findViewById<TextView>(R.id.textView_main_movie_title)
    private val screeningPeriod =
        view.findViewById<TextView>(R.id.textView_main_movie_screening_period)
    private val runningTime = view.findViewById<TextView>(R.id.textView_main_movie_running_time)
    private val poster = view.findViewById<ImageView>(R.id.imageView_main_movie_poster)

    fun bind(movie: MoviesViewType.Movie) {
        this.movie = movie.movie

        title.text = this.movie.title
        screeningPeriod.text =
            screeningPeriod.context.getString(
                R.string.movie_screening_date,
                this.movie.screeningPeriod.start.year,
                this.movie.screeningPeriod.start.monthValue,
                this.movie.screeningPeriod.start.dayOfMonth,
                this.movie.screeningPeriod.end.year,
                this.movie.screeningPeriod.end.monthValue,
                this.movie.screeningPeriod.end.dayOfMonth,
            )
        runningTime.text =
            runningTime.context.getString(R.string.movie_running_time, this.movie.runningTime)
        this.movie.posterImage(poster.context).load(poster, poster.context)
        view.findViewById<Button>(R.id.button_main_movie_book).setOnClickListener {
            bookMovie(this.movie)
        }
    }
}
