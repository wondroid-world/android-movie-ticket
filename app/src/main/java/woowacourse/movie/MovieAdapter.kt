package woowacourse.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.domain.Movie

class MovieAdapter(
    private val movies: List<Movie>,
    private val bookedClickListener: (Movie) -> Unit,
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
            movieViewHolder = MovieViewHolder(view, movies, bookedClickListener)
            view.tag = movieViewHolder
        } else {
            view = convertView
            movieViewHolder = view.tag as MovieViewHolder
        }
        movieViewHolder.bind(position)
        return view
    }
}
