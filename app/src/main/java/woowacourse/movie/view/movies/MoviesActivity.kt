package woowacourse.movie.view.movies

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.intentmodel.TicketingIntentModel
import woowacourse.movie.view.ticket.ticketing.TicketingActivity

class MoviesActivity :
    AppCompatActivity(),
    MoviesContract.View {
    private val presenter by lazy { MoviesPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        presenter.initData()
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

    override fun initAdapter(items: List<MoviesViewType>) {
        val adapter = MoviesAdapter(items, presenter::bookMovie)
        val moviesView = findViewById<RecyclerView>(R.id.recyclerview_movies)
        moviesView.layoutManager = LinearLayoutManager(this)
        moviesView.adapter = adapter
    }

    override fun moveOtherView(movie: TicketingIntentModel) {
        val intent = TicketingActivity.intent(this, movie)
        startActivity(intent)
    }
}
