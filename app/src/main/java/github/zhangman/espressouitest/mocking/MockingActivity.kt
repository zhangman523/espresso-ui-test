package github.zhangman.espressouitest.mocking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.request.RequestOptions
import github.zhangman.espressouitest.R
import github.zhangman.espressouitest.mocking.data.source.MoviesDataSource
import github.zhangman.espressouitest.mocking.data.source.MoviesRemoteDataSource
import github.zhangman.espressouitest.mocking.factory.MovieFragmentFactory
import github.zhangman.espressouitest.mocking.ui.MovieDetailFragment

class MockingActivity : AppCompatActivity() {

    // dependencies (typically would be injected with dagger)
    lateinit var requestOptions: RequestOptions
    lateinit var moviesDataSource: MoviesDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        initDependencies()
        supportFragmentManager.fragmentFactory = MovieFragmentFactory(
            requestOptions,
            moviesDataSource
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mocking)
        init()
    }

    private fun init() {
        if (supportFragmentManager.fragments.size == 0) {
            val movieId = 1
            val bundle = Bundle()
            bundle.putInt("movie_id", movieId)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieDetailFragment::class.java, bundle)
                .commit()
        }
    }

    private fun initDependencies() {

        // glide
        requestOptions = RequestOptions
            .placeholderOf(R.drawable.default_image)
            .error(R.drawable.default_image)

        // Data Source
        moviesDataSource = MoviesRemoteDataSource()
    }
}