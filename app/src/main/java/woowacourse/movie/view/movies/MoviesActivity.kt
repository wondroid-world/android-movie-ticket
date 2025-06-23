package woowacourse.movie.view.movies

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.intentmodel.TicketingIntentModel
import woowacourse.movie.uimodel.MovieUiModel
import woowacourse.movie.view.ticket.ticketing.TicketingActivity

class MoviesActivity :
    AppCompatActivity(),
    MoviesContract.View {
    private val presenter by lazy { MoviesPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        presenter.initData()
    }

    private fun initView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun initAdapter(movies: List<MovieUiModel>) {
        val adapter = MoviesAdapter(movies, presenter::bookMovie)
        val moviesView = findViewById<ListView>(R.id.main_movies)
        moviesView.adapter = adapter
    }

    override fun moveOtherView(movie: TicketingIntentModel) {
        val intent = TicketingActivity.intent(this, movie)
        startActivity(intent)
    }
}
