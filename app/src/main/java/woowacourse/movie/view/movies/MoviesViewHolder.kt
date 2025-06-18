package woowacourse.movie.view.movies

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieUiModel

class MoviesViewHolder(
    private val view: View,
    private val bookMovie: (MovieUiModel) -> Unit,
) {
    private val title = view.findViewById<TextView>(R.id.textView_main_movie_title)
    private val screeningDate = view.findViewById<TextView>(R.id.textView_main_movie_screening_date)
    private val runningTime = view.findViewById<TextView>(R.id.textView_main_movie_running_time)
    private val poster = view.findViewById<ImageView>(R.id.imageView_main_movie_poster)
    private val button = view.findViewById<Button>(R.id.button_main_movie_book)

    fun bind(movie: MovieUiModel) {
        title.text = movie.title
        screeningDate.text = screeningDate.context.getString(
            R.string.movie_screening_date,
            movie.screeningDate.year,
            movie.screeningDate.monthValue,
            movie.screeningDate.dayOfMonth
        )
        runningTime.text =
            runningTime.context.getString(R.string.movie_running_time, movie.runningTime)
        movie.posterImage(poster.context).load(poster, poster.context)
        button.setOnClickListener {
            bookMovie(movie)
        }
    }
}
