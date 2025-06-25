package woowacourse.movie.view.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieUiModel

class MoviesAdapter(
    private val items: List<MoviesViewType>,
    private val bookMovie: (MovieUiModel) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        when (viewType) {
            MoviesViewType.TYPE_MOVIE -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
                return MoviesViewHolder(
                    view = view,
                    bookMovie = bookMovie,
                )
            }

            MoviesViewType.TYPE_AD -> {
                val view =
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_ad, parent, false)
                return AdViewHolder(view)
            }

            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is MoviesViewHolder -> holder.bind(items[position] as MoviesViewType.Movie)
            is AdViewHolder -> holder.ad
        }
    }

    override fun getItemViewType(position: Int): Int = items[position].viewType

    override fun getItemCount(): Int = items.size
}
