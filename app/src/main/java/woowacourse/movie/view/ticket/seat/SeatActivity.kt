package woowacourse.movie.view.ticket.seat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.intentmodel.SeatIntentModel

class SeatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    companion object {
        private const val SEAT_INTENT_KEY = "seat"

        fun intent(
            context: Context,
            seatIntentModel: SeatIntentModel,
        ): Intent {
            val intent = Intent(context, SeatActivity::class.java)
            intent.putExtra(SEAT_INTENT_KEY, seatIntentModel)
            return intent
        }
    }
}
