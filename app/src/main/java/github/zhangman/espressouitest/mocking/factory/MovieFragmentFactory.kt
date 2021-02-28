package github.zhangman.espressouitest.mocking.factory

import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.request.RequestOptions
import github.zhangman.espressouitest.mocking.data.source.MoviesDataSource
import github.zhangman.espressouitest.mocking.ui.DirectorsFragment
import github.zhangman.espressouitest.mocking.ui.MovieDetailFragment
import github.zhangman.espressouitest.mocking.ui.StarActorsFragment
import github.zhangman.espressouitest.recyclerview.MovieListFragment

/**
 * Created by admin on 2021/2/25 14:29.
 * Email: zhangman523@126.com
 */
class MovieFragmentFactory(
    private val requestOptions: RequestOptions? = null,
    private val moviesDataSource: MoviesDataSource? = null
) : FragmentFactory() {
    private val TAG: String = "MovieFragmentFactory"

    override fun instantiate(classLoader: ClassLoader, className: String) =

        when (className) {
            MovieListFragment::class.java.name -> {
                if (moviesDataSource != null) {
                    MovieListFragment(moviesDataSource)
                } else {
                    super.instantiate(classLoader, className)
                }
            }
            github.zhangman.espressouitest.idlingResource.MovieListFragment::class.java.name -> {
                if (moviesDataSource != null) {
                    github.zhangman.espressouitest.idlingResource.MovieListFragment(moviesDataSource)
                } else {
                    super.instantiate(classLoader, className)
                }
            }
            MovieDetailFragment::class.java.name -> {
                if (requestOptions != null
                    && moviesDataSource != null
                ) {
                    MovieDetailFragment(
                        requestOptions,
                        moviesDataSource
                    )
                } else {
                    super.instantiate(classLoader, className)
                }
            }

            DirectorsFragment::class.java.name -> {
                DirectorsFragment()
            }

            StarActorsFragment::class.java.name -> {
                StarActorsFragment()
            }

            else -> {
                super.instantiate(classLoader, className)
            }
        }
}