package woowacourse.movie.view

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.BuildVersion
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieTicket
import woowacourse.movie.intent.MovieIntentModel
import woowacourse.movie.intent.MovieTicketIntentModel
import woowacourse.movie.intent.toDomain
import woowacourse.movie.intent.toIntentModel
import java.time.LocalDate
import java.time.LocalDateTime

class BookingActivity : AppCompatActivity() {
    private lateinit var movieDate: LocalDate
    private lateinit var movieTime: LocalDateTime

    private val dates by lazy {
        findViewById<Spinner>(R.id.spinner_booking_movie_date)
    }
    private val times by lazy { findViewById<Spinner>(R.id.spinner_booking_movie_time) }

    private val movie by lazy {
        BuildVersion.getParcelableClass(
            intent,
            MOVIE,
            MovieIntentModel::class
        ).toDomain()
    }

    private var count: Int = PEOPLE_COUNT_DEFAULT_VALUE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initSpinner()
        initData()
    }

    private fun initData() {
        val title = findViewById<TextView>(R.id.tv_booking_movie_title)
        val screeningPeriod = findViewById<TextView>(R.id.tv_booking_movie_screening_period)
        val runningTime = findViewById<TextView>(R.id.tv_booking_movie_running_time)
        val poster = findViewById<ImageView>(R.id.iv_booking_movie_poster)
        val peopleCount = findViewById<TextView>(R.id.tv_booking_movie_people_count)
        val minusPeopleCount = findViewById<Button>(R.id.button_booking_minus_people_count)
        val plusPeopleCount = findViewById<Button>(R.id.button_booking_plus_people_count)
        val booking = findViewById<Button>(R.id.button_booking_movie_selected)

        movie.let {
            poster.setImageResource(movie.poster)
            title.text = movie.title
            screeningPeriod.text = screeningPeriod.context.getString(
                R.string.movies_movie_screening_date,
                movie.screeningPeriod.start.year,
                movie.screeningPeriod.start.monthValue,
                movie.screeningPeriod.start.dayOfMonth,
                movie.screeningPeriod.end.year,
                movie.screeningPeriod.end.monthValue,
                movie.screeningPeriod.end.dayOfMonth,
            )
            runningTime.text = runningTime.context.getString(
                R.string.movies_movie_running_time,
                movie.runningTime
            )
        }

        peopleCount.text = count.toString()
        minusPeopleCount.setOnClickListener {
            when {
                (count > PEOPLE_COUNT_DEFAULT_VALUE) -> {
                    count--
                    peopleCount.text = peopleCount.toString()
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

        booking.setOnClickListener {
            AlertDialog
                .Builder(this)
                .setTitle(getString(R.string.ticketing_reservation_check))
                .setMessage(getString(R.string.ticketing_reservation_message))
                .setPositiveButton(getString(R.string.ticketing_reservation_complete)) { _, _ ->
                    val movieTicket =
                        MovieTicket(
                            movie = movie,
                            showTime = movieTime,
                            peopleCount = count,
                            totalAmount = count * 13000,
                        )
                    val intent = BookedActivity.intent(this, movieTicket.toIntentModel())
                    startActivity(intent)
                }
                .setNegativeButton(getString(R.string.ticketing_reservation_cancel)) { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    private fun initView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initSpinner() {
        val availableDates = movie.screeningPeriod.availableDates(LocalDate.now())

        if (availableDates.isEmpty()) {
            Toast.makeText(this, "이미 상영이 종료된 영화입니다", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        movieDate = availableDates[0]
        var availableTimes =
            movie.screeningPeriod.availableTime(movieDate, LocalDateTime.now())

        dates.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                availableDates,
            )
        times.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                availableTimes.map { it.toLocalTime() },
            )
        dates.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    movieDate = availableDates[position]
                    availableTimes =
                        movie.screeningPeriod.availableTime(movieDate, LocalDateTime.now())
                    times.adapter =
                        ArrayAdapter(
                            this@BookingActivity,
                            android.R.layout.simple_spinner_item,
                            availableTimes.map { it.toLocalTime() },
                        )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

        times.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val time = availableTimes[position]
                    movieTime =
                        LocalDateTime.of(
                            movieDate.year,
                            movieDate.monthValue,
                            movieDate.dayOfMonth,
                            time.hour,
                            time.minute,
                        )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
    }


    companion object {
        private const val PEOPLE_COUNT_DEFAULT_VALUE: Int = 1
        private const val MOVIE = "movie"
        fun intent(context: Context, movie: MovieIntentModel): Intent {
            val intent = Intent(context, BookingActivity::class.java)
            intent.putExtra(MOVIE, movie)
            return intent
        }
    }
}