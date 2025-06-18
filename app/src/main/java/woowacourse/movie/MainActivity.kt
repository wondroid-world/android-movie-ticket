package woowacourse.movie

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.dummy.Dummy
import woowacourse.movie.view.ticket.Summary
import woowacourse.movie.intentmodel.MovieIntentModel
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        val movieIntentModel = MovieIntentModel(
            name = Dummy.data[0].title,
            screeningDate = Dummy.data[0].screeningDate
        )
        moveOtherView(movieIntentModel)
    }

    private fun initView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun moveOtherView(movieIntentModel: MovieIntentModel) {
        val intent = Summary.intent(this, movieIntentModel)
        startActivity(intent)
    }
}
