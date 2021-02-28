package github.zhangman.espressouitest.idlingResource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.request.RequestOptions
import github.zhangman.espressouitest.R
import github.zhangman.espressouitest.mocking.data.source.MoviesDataSource
import github.zhangman.espressouitest.mocking.data.source.MoviesRemoteDataSource
import github.zhangman.espressouitest.mocking.factory.MovieFragmentFactory
import kotlinx.android.synthetic.main.activity_recycler_view_test.*

class IdlingResourceActivity : AppCompatActivity(), UICommunicationListener {

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

    override fun loading(isLoading: Boolean) {
        if (isLoading) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.INVISIBLE
        }
    }

}