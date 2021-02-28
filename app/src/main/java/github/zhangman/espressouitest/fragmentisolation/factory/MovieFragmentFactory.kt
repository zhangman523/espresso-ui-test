package github.zhangman.espressouitest.fragmentisolation.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import github.zhangman.espressouitest.fragmentisolation.ui.DirectorsFragment
import github.zhangman.espressouitest.fragmentisolation.ui.MovieDetailFragment
import github.zhangman.espressouitest.fragmentisolation.ui.StarActorsFragment

/**
 * Created by admin on 2021/2/25 14:29.
 * Email: zhangman523@126.com
 */
class MovieFragmentFactory : FragmentFactory() {
    private val TAG: String = "MovieFragmentFactory"

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        when (className) {
            MovieDetailFragment::class.java.name -> {
                return MovieDetailFragment()
            }

            DirectorsFragment::class.java.name -> {
                return DirectorsFragment()
            }
            StarActorsFragment::class.java.name -> {
                return StarActorsFragment()
            }
            else -> {
                return super.instantiate(classLoader, className)
            }
        }
    }
}