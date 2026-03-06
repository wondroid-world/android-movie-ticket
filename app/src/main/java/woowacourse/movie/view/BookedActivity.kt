package woowacourse.movie.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.MovieTicket
import woowacourse.movie.intent.MovieTicketIntentModel
import woowacourse.movie.intent.toDomain

class BookedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        val movie: MovieTicketIntentModel? = intent.getParcelableExtra(MOVIE_TICKET)
        movie?.let { initMovie(it.toDomain()) }
    }

    private fun initMovie(movieTicket: MovieTicket) {
        val title = findViewById<TextView>(R.id.tv_booked_movie_title)
        val showtime = findViewById<TextView>(R.id.tv_booked_movie_screening_date)
        title.text = movieTicket.movie.title
        showtime.text = showtime.context.getString(
            R.string.summary_movie_screening_date,
            movieTicket.showTime.year,
            movieTicket.showTime.monthValue,
            movieTicket.showTime.dayOfMonth
        )
    }

    private fun initView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_booked)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.booked)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    companion object {
        private const val MOVIE_TICKET = "movie_ticket"
        fun intent(context: Context, movieTicket: MovieTicketIntentModel): Intent {
            val intent = Intent(context, BookedActivity::class.java)
            intent.putExtra(MOVIE_TICKET, movieTicket)
            return intent
        }
    }
}