package github.zhangman.espressouitest.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.request.RequestOptions
import github.zhangman.espressouitest.R
import github.zhangman.espressouitest.mocking.data.source.MoviesDataSource
import github.zhangman.espressouitest.mocking.data.source.MoviesRemoteDataSource
import github.zhangman.espressouitest.mocking.factory.MovieFragmentFactory

class RecyclerViewTestActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_recycler_view_test)

        init()
    }

    private fun init() {
        if (supportFragmentManager.fragments.size == 0) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieListFragment::class.java, null)
                .commit()
        }
    }

    private fun initDependencies() {
        if (!::requestOptions.isInitialized) {
            // glide
            requestOptions = RequestOptions
                .placeholderOf(R.drawable.default_image)
                .error(R.drawable.default_image)
        }
        if (!::moviesDataSource.isInitialized) {
            // Data Source
            moviesDataSource = MoviesRemoteDataSource()
        }
    }

}