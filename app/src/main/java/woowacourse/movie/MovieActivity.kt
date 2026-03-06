package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.intent.toIntentModel

class MovieActivity : AppCompatActivity() {

    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        val listView = findViewById<ListView>(R.id.lv_movies)
        adapter = MovieAdapter(MovieDummy.movies) { movie ->
            val intent = BookedActivity.intent(this, movie.toIntentModel())
            startActivity(intent)
        }
        listView.adapter = adapter
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
}