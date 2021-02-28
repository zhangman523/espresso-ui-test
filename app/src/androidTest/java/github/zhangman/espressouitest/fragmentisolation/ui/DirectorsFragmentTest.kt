package github.zhangman.espressouitest.fragmentisolation.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import github.zhangman.espressouitest.R
import github.zhangman.espressouitest.fragmentisolation.factory.MovieFragmentFactory
import kotlinx.android.synthetic.main.fragment_directors.view.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by admin on 2021/2/25 15:36.
 * Email: zhangman523@126.com
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class DirectorsFragmentTest {
    @Test
    fun test_isDirectorsListVisible() {

        //SETUP
        val directors = arrayListOf("R.J. Stewart", "James Vanderbilt")
        val fragmentFactory = MovieFragmentFactory()
        val bundle = Bundle()
        bundle.putStringArrayList("args_directors", directors)

        val scenario = launchFragmentInContainer<DirectorsFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        onView(withId(R.id.directors_text)).check(
            matches(
                withText(
                    DirectorsFragment.stringBuilderForDirectors(
                        directors
                    )
                )
            )
        )
    }
}