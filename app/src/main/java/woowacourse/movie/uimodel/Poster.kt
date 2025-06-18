package woowacourse.movie.uimodel

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.GenericLifecycleObserver
import com.bumptech.glide.Glide

sealed class Poster {
    data class Drawable(val resId: Int): Poster()
    data class Remote(val url: String): Poster()

    fun load(imageView: ImageView, context: Context) {
        when (this) {
            is Drawable -> imageView.setImageResource(resId)
            is Remote -> Glide.with(context).load(url).into(imageView)
        }
    }
}
