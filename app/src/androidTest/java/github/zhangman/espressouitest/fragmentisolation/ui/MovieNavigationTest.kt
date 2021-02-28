package github.zhangman.espressouitest.fragmentisolation.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import github.zhangman.espressouitest.R
import github.zhangman.espressouitest.fragmentisolation.FragmentIsolationActivity
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by admin on 2021/2/25 16:04.
 * Email: zhangman523@126.com
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MovieNavigationTest {

    @Test
    fun test_movieFragmentsNavigation() {

        //SETUP
        val activityScenario = ActivityScenario.launch(FragmentIsolationActivity::class.java)

        // Nav DirectorsFragment
        onView(withId(R.id.movie_directiors)).perform(click())

        // VERIFY
        onView(withId(R.id.fragment_movie_directors_parent)).check(matches(isDisplayed()))

        pressBack()

        // VERIFY
        onView(withId(R.id.fragment_movie_detail_parent)).check(matches(isDisplayed()))

        // Nav StarActorsFragment
        onView(withId(R.id.movie_star_actors)).perform(click())

        // VERIFY
        onView(withId(R.id.fragment_movie_star_actors_parent)).check(matches(isDisplayed()))
    }
}