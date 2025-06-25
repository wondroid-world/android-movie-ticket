package woowacourse.movie.view.movies

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class AdViewHolder(
    private val view: View,
) : RecyclerView.ViewHolder(view) {
    val ad = view.findViewById<ImageView>(R.id.imageView_main_ad)
}
