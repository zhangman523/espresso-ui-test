package github.zhangman.espressouitest.simlpeactivity

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import github.zhangman.espressouitest.R
import github.zhangman.espressouitest.simpleactivity.FirstActivity
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by admin on 2021/2/24 21:40.
 * Email: zhangman523@126.com
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class FirstActivityTest {
    @Test
    fun test_isActivityInView() {
        val activityScenario = ActivityScenario.launch(FirstActivity::class.java)

        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_title_nextButton() {
        val activityScenario = ActivityScenario.launch(FirstActivity::class.java)

        onView(withId(R.id.activity_main_title)).check(matches(isDisplayed()))

        onView(withId(R.id.button_next_activity)).check(matches(isDisplayed()))//method 1

        onView(withId(R.id.button_next_activity)).check(matches(withEffectiveVisibility(Visibility.VISIBLE))) //method 2


    }

    @Test
    fun test_isTitleDisplayed() {
        val activityScenario = ActivityScenario.launch(FirstActivity::class.java)

        onView(withId(R.id.activity_main_title)).check(matches(withText(
            R.string.text_main_activity
        )))

    }

    @Test
    fun test_navSecondaryActivity() {
        val activityScenario = ActivityScenario.launch(FirstActivity::class.java)

        onView(withId(R.id.button_next_activity)).perform(click())

        onView(withId(R.id.secondary)).check(matches(isDisplayed()))
    }

    @Test
    fun test_backPress_toMainActivity() {
        val activityScenario = ActivityScenario.launch(FirstActivity::class.java)

        onView(withId(R.id.button_next_activity)).perform(click())

        onView(withId(R.id.secondary)).check(matches(isDisplayed()))

        onView(withId(R.id.button_back_activity)).perform(click())// method 1

//        pressBack()//method 2

        onView(withId(R.id.main)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }
}