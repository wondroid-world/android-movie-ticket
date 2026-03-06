package woowacourse.movie.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.BuildVersion
import woowacourse.movie.R
import woowacourse.movie.domain.MovieTicket
import woowacourse.movie.intent.MovieTicketIntentModel
import woowacourse.movie.intent.toDomain

class BookedActivity : AppCompatActivity() {
    private val movieTicket: MovieTicket by lazy {
        BuildVersion.getParcelableClass(intent, MOVIE_TICKET, MovieTicketIntentModel::class)
            .toDomain()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initMovie()
    }

    private fun initMovie() {
        val title = findViewById<TextView>(R.id.tv_booked_movie_title)
        val screeningDateTime =
            findViewById<TextView>(R.id.tv_booked_movie_screening_date_time)
        val peopleCount = findViewById<TextView>(R.id.tv_booked_movie_people_count)
        val totalAmount = findViewById<TextView>(R.id.tv_booked_movie_total_amount)

        title.text = movieTicket.movie.title
        screeningDateTime.text =
            screeningDateTime.context.getString(
                R.string.summary_movie_screening_date_time,
                movieTicket.showTime.year,
                movieTicket.showTime.monthValue,
                movieTicket.showTime.dayOfMonth,
                movieTicket.showTime.hour,
                movieTicket.showTime.minute,
            )
        peopleCount.text =
            peopleCount.context.getString(R.string.summary_movie_people_count, movieTicket.peopleCount)
        totalAmount.text =
            totalAmount.context.getString(R.string.summary_movie_total_amount, movieTicket.totalAmount)
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