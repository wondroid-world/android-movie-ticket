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
    private var count: Int = 1
    private lateinit var ticketingIntentModel: TicketingIntentModel
    private lateinit var movie: MovieUiModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        ticketingIntentModel =
            BuildVersion().getParcelableClass(
                intent,
                TICKETING_INTENT_KEY,
                TicketingIntentModel::class,
            )
        movie =
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
                (count > 1) -> {
                    count--
                    peopleCount.text = count.toString()
                }

                else -> Toast.makeText(this, "영화 관람 인원이 최소 1명이상이어야 합니다.", Toast.LENGTH_LONG).show()
            }
        }
        plusPeopleCount.setOnClickListener {
            count++
            peopleCount.text = count.toString()
        }
        selectedMovie.setOnClickListener {
            AlertDialog
                .Builder(this)
                .setTitle("예매 확인")
                .setMessage("정말 예매하시겠습니까?")
                .setPositiveButton("예매 완료") { _, _ ->
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
                }.setNegativeButton("취소") { dialog, _ -> dialog.dismiss() }
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
