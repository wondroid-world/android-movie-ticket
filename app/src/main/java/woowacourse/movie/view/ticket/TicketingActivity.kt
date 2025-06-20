package woowacourse.movie.view.ticket

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.ScreeningPeriod
import woowacourse.movie.intentmodel.SummaryIntentModel
import woowacourse.movie.intentmodel.TicketingIntentModel
import woowacourse.movie.uimodel.MovieUiModel
import woowacourse.movie.util.BuildVersion
import java.time.LocalDateTime

class TicketingActivity : AppCompatActivity() {
    private var count: Int = PEOPLE_COUNT_DEFAULT_VALUE
    private val ticketingIntentModel: TicketingIntentModel by lazy {
        BuildVersion().getParcelableClass(
            intent,
            TICKETING_INTENT_KEY,
            TicketingIntentModel::class,
        )
    }
    private val movie: MovieUiModel by lazy {
        MovieUiModel(
            id = ticketingIntentModel.id,
            title = ticketingIntentModel.title,
            poster = ticketingIntentModel.poster,
            screeningPeriod =
                ScreeningPeriod(
                    ticketingIntentModel.screeningPeriod.start,
                    ticketingIntentModel.screeningPeriod.end,
                ),
            runningTime = ticketingIntentModel.runningTime,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        bindData()
    }

    private fun bindData() {
        val poster = findViewById<ImageView>(R.id.imageView_ticketing_movie_poster)
        val title = findViewById<TextView>(R.id.textView_ticketing_movie_title)
        val screeningPeriod = findViewById<TextView>(R.id.textView_ticketing_movie_screening_period)
        val runningTime = findViewById<TextView>(R.id.textView_ticketing_movie_running_time)
        val peopleCount = findViewById<TextView>(R.id.textView_ticketing_movie_people_count)
        val minusPeopleCount = findViewById<Button>(R.id.button_ticketing_minus_people_count)
        val plusPeopleCount = findViewById<Button>(R.id.button_ticketing_plus_people_count)
        val selectedMovie = findViewById<Button>(R.id.button_ticketing_movie_selected)

        movie.posterImage(poster.context).load(poster, poster.context)
        title.text = movie.title
        screeningPeriod.text =
            screeningPeriod.context.getString(
                R.string.movie_screening_date,
                movie.screeningPeriod.start.year,
                movie.screeningPeriod.start.monthValue,
                movie.screeningPeriod.start.dayOfMonth,
                movie.screeningPeriod.end.year,
                movie.screeningPeriod.end.monthValue,
                movie.screeningPeriod.end.dayOfMonth,
            )
        runningTime.text =
            runningTime.context.getString(R.string.movie_running_time, movie.runningTime)
        peopleCount.text = count.toString()
        minusPeopleCount.setOnClickListener {
            when {
                (count > PEOPLE_COUNT_DEFAULT_VALUE) -> {
                    count--
                    peopleCount.text = count.toString()
                }

                else ->
                    Toast
                        .makeText(
                            this,
                            getString(R.string.ticketing_people_count_minimum),
                            Toast.LENGTH_LONG,
                        ).show()
            }
        }
        plusPeopleCount.setOnClickListener {
            count++
            peopleCount.text = count.toString()
        }
        selectedMovie.setOnClickListener {
            AlertDialog
                .Builder(this)
                .setTitle(getString(R.string.ticketing_reservation_check))
                .setMessage(getString(R.string.ticketing_reservation_message))
                .setPositiveButton(getString(R.string.ticketing_reservation_complete)) { _, _ ->
                    val summary =
                        SummaryIntentModel(
                            id = movie.id,
                            title = movie.title,
                            screeningDateTime = LocalDateTime.of(2025, 4, 15, 11, 0),
                            peopleCount = count,
                            totalAmount = count * 13000,
                        )
                    val intent = SummaryActivity.intent(this, summary)
                    startActivity(intent)
                }.setNegativeButton(getString(R.string.ticketing_reservation_cancel)) { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    private fun initView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_ticketing)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ticketing)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    companion object {
        private const val PEOPLE_COUNT_DEFAULT_VALUE: Int = 1
        private const val TICKETING_INTENT_KEY: String = "ticketing"

        fun intent(
            context: Context,
            movie: TicketingIntentModel,
        ): Intent =
            Intent(context, TicketingActivity::class.java).putExtra(
                TICKETING_INTENT_KEY,
                movie,
            )
    }
}
