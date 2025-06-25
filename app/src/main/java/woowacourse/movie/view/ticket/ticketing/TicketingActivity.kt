package woowacourse.movie.view.ticket.ticketing

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.intentmodel.SeatIntentModel
import woowacourse.movie.intentmodel.TicketingIntentModel
import woowacourse.movie.uimodel.MovieUiModel
import woowacourse.movie.util.BuildVersion
import woowacourse.movie.view.ticket.seat.SeatActivity
import java.time.LocalDate
import java.time.LocalTime

class TicketingActivity :
    AppCompatActivity(),
    TicketingContract.View {
    private val presenter by lazy { TicketingPresenter(this) }
    private val viewHolder by lazy { TicketingViewHolder(this, presenter) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        val ticketingIntentModel =
            BuildVersion().getParcelableClass(
                intent,
                TICKETING_INTENT_KEY,
                TicketingIntentModel::class,
            )
        presenter.initData(ticketingIntentModel)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(
            TICKETING_COUNT_KEY,
            viewHolder.peopleCount.text
                .toString()
                .toInt(),
        )
        outState.putInt(TICKETING_DATE_KEY, viewHolder.dates.selectedItemPosition)
        outState.putInt(TICKETING_TIME_KEY, viewHolder.times.selectedItemPosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedCount = savedInstanceState.getInt(TICKETING_COUNT_KEY)
        val savedDatePosition = savedInstanceState.getInt(TICKETING_DATE_KEY)
        val savedTimePosition =  savedInstanceState.getInt(TICKETING_TIME_KEY)

        presenter.updateCount(savedCount)
        presenter.updateDatePosition(savedDatePosition)
        presenter.updateTimePosition(savedTimePosition)
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

    override fun bind(movie: MovieUiModel) {
        viewHolder.bind(movie)
    }

    override fun initTimeSpinnerAdapter(movieAvailableTimes: List<LocalTime>) {
        viewHolder.times.adapter =
            ArrayAdapter(
                this@TicketingActivity,
                android.R.layout.simple_spinner_item,
                movieAvailableTimes,
            )
        viewHolder.times.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    presenter.selectedTime(movieAvailableTimes[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    override fun initDateSpinnerAdapter(movieAvailableDates: List<LocalDate>) {
        viewHolder.dates.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                movieAvailableDates,
            )
        viewHolder.dates.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    presenter.selectedDate(movieAvailableDates[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    override fun showSelectedMovie(seat: SeatIntentModel) {
        val intent = SeatActivity.intent(this, seat)
        startActivity(intent)
    }

    override fun showNoAvailableDates() {
        Toast
            .makeText(
                applicationContext,
                getString(R.string.ticketing_movie_already_closed),
                Toast.LENGTH_SHORT,
            ).show()
        finish()
    }

    override fun showSelectedDate(position: Int) {
        viewHolder.dates.setSelection(position)
    }

    override fun showSelectedTime(position: Int) {
        viewHolder.times.setSelection(position)
    }

    override fun peopleCount(count: Int) {
        viewHolder.bindPeopleCount(count)
    }

    override fun toastMessage() {
        Toast
            .makeText(
                this,
                getString(R.string.ticketing_people_count_minimum),
                Toast.LENGTH_LONG,
            ).show()
    }

    companion object {
        private const val TICKETING_INTENT_KEY: String = "ticketing"
        private const val TICKETING_COUNT_KEY: String = "ticketingCount"
        private const val TICKETING_DATE_KEY: String = "ticketingDate"
        private const val TICKETING_TIME_KEY: String = "ticketingTime"

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
