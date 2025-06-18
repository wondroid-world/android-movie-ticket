package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.dummy.Dummy
import woowacourse.movie.view.ticket.Summary
import woowacourse.movie.intentmodel.MovieIntentModel
import woowacourse.movie.uimodel.MovieUiModel
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
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

    private fun moveOtherView(movieIntentModel: MovieIntentModel) {
        val intent = Summary.intent(this, movieIntentModel)
        startActivity(intent)
    }

    private fun initAdapter() {
        val movies = changeUiModel()
        val adapter = MoviesAdapter(movies, bookMovie())
        val moviesView = findViewById<ListView>(R.id.main_movies)
        moviesView.adapter = adapter
    }

    private fun changeUiModel(): List<MovieUiModel> {
        val movies = Dummy.data.map { movie ->
            MovieUiModel(
                id = movie.id,
                title = movie.title,
                poster = movie.poster,
                screeningDate = movie.screeningDate,
                runningTime = movie.runningTime,
            )
        }
        return movies
    }

    private fun bookMovie(): (MovieUiModel) -> Unit = { movie ->
        val movieIntentModel = MovieIntentModel(
            id = movie.id,
            name = movie.title,
            screeningDate = movie.screeningDate
        )
        moveOtherView(movieIntentModel)
    }
}
