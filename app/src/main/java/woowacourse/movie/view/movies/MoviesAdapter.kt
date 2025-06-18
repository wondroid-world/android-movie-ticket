package woowacourse.movie.view.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieUiModel

class MoviesAdapter(
    private val movies: List<MovieUiModel>,
    private val bookMovie: (MovieUiModel) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): MovieUiModel = movies[position]

    // ListView는 내부적으로 아이템을 구분할 때 이 ID를 기준으로 처리
    override fun getItemId(position: Int): Long = movies[position].id

    // 리스트 순서가 바뀌어도 아이다가 그대로임을 보장 -> ListView는 그대로 재사용함
    override fun hasStableIds(): Boolean = true

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        val movie = getItem(position)

        val title = view.findViewById<TextView>(R.id.textView_main_movie_title)
        val screeningDate = view.findViewById<TextView>(R.id.textView_main_movie_screening_date)
        val runningTime = view.findViewById<TextView>(R.id.textView_main_movie_running_time)
        val poster = view.findViewById<ImageView>(R.id.imageView_main_movie_poster)
        val button = view.findViewById<Button>(R.id.button_main_movie_book)
        title.text = movie.title
        screeningDate.text = parent.context.getString(
            R.string.movie_screening_date,
            movie.screeningDate.year,
            movie.screeningDate.monthValue,
            movie.screeningDate.dayOfMonth
        )
        runningTime.text =
            parent.context.getString(R.string.movie_running_time, movie.runningTime)
        movie.posterImage(parent.context).load(poster, parent.context)
        button.setOnClickListener {
            bookMovie(movie)
        }

        return view
    }
}
