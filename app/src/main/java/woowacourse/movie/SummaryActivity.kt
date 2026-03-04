package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Movie
import woowacourse.movie.intent.MovieIntentModel
import woowacourse.movie.intent.toDomain

class SummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        val movie = intent.getParcelableExtra<MovieIntentModel>(NAME_OF_VALUE)
        movie?.let { initMovie(it.toDomain()) }
    }

    private fun initMovie(movie: Movie) {
        val title = findViewById<TextView>(R.id.tv_summary_movie_title)
        val showtime = findViewById<TextView>(R.id.tv_summary_movie_screening_date)
        title.text = movie.title
        showtime.text = showtime.context.getString(
            R.string.movie_screening_date,
            movie.showtime.year,
            movie.showtime.monthValue,
            movie.showtime.dayOfMonth
        )
    }

    private fun initView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_summary)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.summary)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    companion object {
        private const val NAME_OF_VALUE = "movie"
        fun intent(context: Context, movie: MovieIntentModel): Intent {
            val intent = Intent(context, SummaryActivity::class.java)
            intent.putExtra(NAME_OF_VALUE, movie)
            return intent
        }
    }
}