package woowacourse.movie.view.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieUiModel

class MoviesAdapter(
    private val movies: List<MovieUiModel>,
    private val bookMovie: (MovieUiModel) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): MovieUiModel = movies[position]

    override fun getItemId(position: Int): Long = movies[position].id

    override fun hasStableIds(): Boolean = true

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val view: View
        val moviesViewHolder: MoviesViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
            moviesViewHolder =
                MoviesViewHolder(
                    view = view,
                    movies = movies,
                    bookMovie = bookMovie,
                )
            view.tag = moviesViewHolder
        } else {
            view = convertView
            moviesViewHolder = view.tag as MoviesViewHolder
        }

        moviesViewHolder.bind(position)
        return view
    }
}
