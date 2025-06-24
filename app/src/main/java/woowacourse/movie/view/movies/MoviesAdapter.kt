package woowacourse.movie.view.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieUiModel

class MoviesAdapter(
    private val movies: List<MovieUiModel>,
    private val bookMovie: (MovieUiModel) -> Unit,
) : RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(
            view = view,
            movies = movies,
            bookMovie = bookMovie,
        )
    }

    override fun onBindViewHolder(
        holder: MoviesViewHolder,
        position: Int,
    ) {
        holder.bind(position)
    }

    override fun getItemId(position: Int): Long = movies[position].id

    override fun getItemCount(): Int = movies.size
}
