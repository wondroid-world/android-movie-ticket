package woowacourse.movie.view.movies

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.intent.toIntentModel
import woowacourse.movie.view.BookingActivity

class MoviesActivity :
    AppCompatActivity(),
    MoviesContract.View {
    private val presenter: MoviesContract.Presenter by lazy { MoviesPresenter(this) }
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        val listView = findViewById<ListView>(R.id.lv_movies)
        presenter.loadMovies()
        listView.adapter = adapter
    }

    private fun initView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_movies)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun showMovies(movies: List<Movie>) {
        adapter =
            MovieAdapter(movies) { movie ->
                val intent = BookingActivity.intent(this, movie.toIntentModel())
                startActivity(intent)
            }
    }
}
