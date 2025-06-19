package woowacourse.movie.view.ticket

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.intentmodel.MovieIntentModel

class TicketingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            movie: MovieIntentModel,
        ): Intent =
            Intent(context, TicketingActivity::class.java).putExtra(
                TICKETING_INTENT_KEY,
                movie,
            )
    }
}
