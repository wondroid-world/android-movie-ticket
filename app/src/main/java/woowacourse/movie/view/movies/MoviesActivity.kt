package woowacourse.movie.view.movies

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.dummy.Dummy
import woowacourse.movie.intentmodel.TicketingIntentModel
import woowacourse.movie.uimodel.MovieUiModel
import woowacourse.movie.view.ticket.ticketing.TicketingActivity

class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initAdapter()
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

    private fun initAdapter() {
        val movies = changeUiModel()
        val adapter = MoviesAdapter(movies, bookMovie())
        val moviesView = findViewById<ListView>(R.id.main_movies)
        moviesView.adapter = adapter
    }

    private fun changeUiModel(): List<MovieUiModel> {
        val movies =
            Dummy.data.map { movie ->
                MovieUiModel(
                    id = movie.id,
                    title = movie.title,
                    poster = movie.poster,
                    screeningPeriod = movie.screeningPeriod,
                    runningTime = movie.runningTime,
                )
            }

        return movies
    }

    private fun bookMovie(): (MovieUiModel) -> Unit =
        { movie ->
            val movieIntentModel =
                changeMovieIntentModel(movie)
            moveOtherView(movieIntentModel)
        }

    private fun changeMovieIntentModel(movie: MovieUiModel): TicketingIntentModel {
        val ticketingIntentModel =
            TicketingIntentModel.of(
                id = movie.id,
                title = movie.title,
                poster = movie.poster,
                screeningPeriod = movie.screeningPeriod,
                runningTime = movie.runningTime,
            )
        return ticketingIntentModel
    }

    private fun moveOtherView(movie: TicketingIntentModel) {
        val intent = TicketingActivity.intent(this, movie)
        startActivity(intent)
    }
}
