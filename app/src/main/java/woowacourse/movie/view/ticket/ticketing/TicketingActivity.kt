package woowacourse.movie.view.ticket.ticketing

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.intentmodel.SummaryIntentModel
import woowacourse.movie.intentmodel.TicketingIntentModel
import woowacourse.movie.uimodel.MovieUiModel
import woowacourse.movie.util.BuildVersion
import woowacourse.movie.view.ticket.summary.SummaryActivity
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

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
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

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
    }

    override fun showSelectedMovie(summary: SummaryIntentModel) {
        AlertDialog
            .Builder(this)
            .setTitle(getString(R.string.ticketing_reservation_check))
            .setMessage(getString(R.string.ticketing_reservation_message))
            .setPositiveButton(getString(R.string.ticketing_reservation_complete)) { _, _ ->
                val intent = SummaryActivity.Companion.intent(this, summary)
                startActivity(intent)
            }.setNegativeButton(getString(R.string.ticketing_reservation_cancel)) { dialog, _ -> dialog.dismiss() }
            .show()
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

