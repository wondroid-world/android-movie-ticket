package woowacourse.movie.view.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.view.movies.MovieViewHolder

class MovieAdapter(
    private val movies: List<Movie>,
    private val choiceClickListener: (Movie) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = movies[position].id

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val view: View
        val movieViewHolder: MovieViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
            movieViewHolder = MovieViewHolder(view, movies, choiceClickListener)
            view.tag = movieViewHolder
        } else {
            view = convertView
            movieViewHolder = view.tag as MovieViewHolder
        }
        movieViewHolder.bind(position)
        return view
    }
}
