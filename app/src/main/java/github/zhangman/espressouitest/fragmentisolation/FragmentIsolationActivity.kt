package github.zhangman.espressouitest.fragmentisolation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import github.zhangman.espressouitest.R
import github.zhangman.espressouitest.fragmentisolation.factory.MovieFragmentFactory
import github.zhangman.espressouitest.fragmentisolation.ui.MovieDetailFragment

class FragmentIsolationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = MovieFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_isolation)

        init()
    }

    fun init() {
        if (supportFragmentManager.fragments.size == 0) {
            val movieid = 1
            val bundle = Bundle()
            bundle.putInt("movie_id", movieid)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieDetailFragment::class.java, bundle)
                .commit()
        }
    }
}