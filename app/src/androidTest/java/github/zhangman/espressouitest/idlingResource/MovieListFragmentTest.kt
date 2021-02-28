package github.zhangman.espressouitest.idlingResource

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import github.zhangman.espressouitest.R
import github.zhangman.espressouitest.mocking.ui.DirectorsFragment
import github.zhangman.espressouitest.mocking.ui.StarActorsFragment
import github.zhangman.espressouitest.recyclerview.FakeMovieData
import github.zhangman.espressouitest.recyclerview.MoviesListAdapter.*
import github.zhangman.espressouitest.util.EspressoIdlingResourceRule
import org.hamcrest.core.IsNot.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by admin on 2021/2/28 14:18.
 * Email: zhangman523@126.com
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MovieListFragmentTest {
    @get: Rule
    val activityRule = ActivityScenarioRule(IdlingResourceActivity::class.java)

    @get: Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    val LIST_ITEM_IN_TEST = 4
    val MOVIE_IN_TEST = FakeMovieData.movies[LIST_ITEM_IN_TEST]

//    @Before
//    fun registerIdlingResource() {
//        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
//    }
//
//    @After
//    fun unregisterIdlingResource() {
//        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
//    }

    @Test
    fun test_isListFragmentVisible_onAppLaunch() {
        onView(withId(R.id.recycler_view))
            .check(matches(isDisplayed()))

        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun test_selectListItem_isDetailFragmentVisible() {
        //Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.movie_title))
            .check(matches(withText(MOVIE_IN_TEST.title)))
    }


    @Test
    fun test_backNavigation_toMovieListFragment() {
        //Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.movie_title))
            .check(matches(withText(MOVIE_IN_TEST.title)))

        pressBack()

        // Confirm MovieListFragment in view
        onView(withId(R.id.recycler_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_navDirectorsFragment_validateDirectorsList() {

        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.movie_title))
            .check(matches(withText(MOVIE_IN_TEST.title)))

        // Nav to DirectorsFragment
        onView(withId(R.id.movie_directiors)).perform(click())

        // Confirm correct directors are visible
        onView(withId(R.id.directors_text))
            .check(
                matches(
                    withText(
                        DirectorsFragment.stringBuilderForDirectors(MOVIE_IN_TEST.directors!!)
                    )
                )
            )
    }

    @Test
    fun test_navStarActorsFragment_validateActorsList() {
        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view))
            .perform(
                actionOnItemAtPosition<MovieViewHolder>(
                    LIST_ITEM_IN_TEST,
                    click()
                )
            )

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.movie_title))
            .check(matches(withText(MOVIE_IN_TEST.title)))

        // Nav to DirectorsFragment
        onView(withId(R.id.movie_star_actors)).perform(click())

        // Confirm correct directors are visible
        onView(withId(R.id.star_actors_text))
            .check(
                matches(
                    withText(
                        StarActorsFragment.stringBuilderForStarActors(MOVIE_IN_TEST.star_actors!!)
                    )
                )
            )
    }
}