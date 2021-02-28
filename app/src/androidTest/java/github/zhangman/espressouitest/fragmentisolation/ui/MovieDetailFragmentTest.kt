package github.zhangman.espressouitest.fragmentisolation.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import github.zhangman.espressouitest.R
import github.zhangman.espressouitest.fragmentisolation.data.DummyMovies.THE_RUNDOWN
import github.zhangman.espressouitest.fragmentisolation.factory.MovieFragmentFactory
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by admin on 2021/2/25 15:28.
 * Email: zhangman523@126.com
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailFragmentTest {

    @Test
    fun test_isMovieDataVisible() {

        //SETUP
        val movie = THE_RUNDOWN
        val fragmentFactory = MovieFragmentFactory()
        val bundle = Bundle()
        bundle.putInt("movie_id", movie.id)

        val scenario = launchFragmentInContainer<MovieDetailFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        //VERIFY
        onView(withId(R.id.movie_title)).check(matches(withText(movie.title)))

        onView(withId(R.id.movie_description)).check(matches(withText(movie.description)))
    }
}