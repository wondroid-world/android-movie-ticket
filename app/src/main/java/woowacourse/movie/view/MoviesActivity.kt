package woowacourse.movie.view

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.data.MovieDummy
import woowacourse.movie.intent.toIntentModel

class MoviesActivity : AppCompatActivity() {

    private val adapter: MovieAdapter by lazy {
        MovieAdapter(MovieDummy.movies) { movie ->
            val intent = BookingActivity.intent(this, movie.toIntentModel())
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        val listView = findViewById<ListView>(R.id.lv_movies)
        listView.adapter = adapter
    }

    private fun initView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_movies)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}