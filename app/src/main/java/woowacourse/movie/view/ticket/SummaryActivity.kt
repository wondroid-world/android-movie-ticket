package woowacourse.movie.view.ticket

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.intentmodel.SummaryIntentModel
import woowacourse.movie.util.BuildVersion

class SummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        val summary =
            BuildVersion().getParcelableClass(
                intent,
                SUMMARY_INTENT_KEY,
                SummaryIntentModel::class,
            )
        val title = findViewById<TextView>(R.id.textView_summary_movie_title)
        val screeningDateTime =
            findViewById<TextView>(R.id.textView_summary_movie_screening_date_time)
        val peopleCount = findViewById<TextView>(R.id.textView_summary_movie_people_count)
        val totalAmount = findViewById<TextView>(R.id.textView_summary_movie_total_amount)

        title.text = summary.title
        screeningDateTime.text =
            screeningDateTime.context.getString(
                R.string.summary_movie_screening_date_time,
                summary.screeningDateTime.year,
                summary.screeningDateTime.monthValue,
                summary.screeningDateTime.dayOfMonth,
                summary.screeningDateTime.hour,
                summary.screeningDateTime.minute,
            )
        peopleCount.text =
            peopleCount.context.getString(R.string.summary_movie_people_count, summary.peopleCount)
        totalAmount.text =
            totalAmount.context.getString(R.string.summary_movie_total_amount, summary.totalAmount)
    }

    private fun initView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_summary)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.summary)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    companion object {
        private const val SUMMARY_INTENT_KEY = "summary"

        fun intent(
            context: Context,
            summary: SummaryIntentModel,
        ): Intent {
            val intent =
                Intent(context, SummaryActivity::class.java)
                    .putExtra(SUMMARY_INTENT_KEY, summary)
            return intent
        }
    }
}
