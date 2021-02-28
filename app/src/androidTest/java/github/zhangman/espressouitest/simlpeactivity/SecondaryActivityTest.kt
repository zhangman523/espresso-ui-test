package github.zhangman.espressouitest.simlpeactivity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import github.zhangman.espressouitest.R
import github.zhangman.espressouitest.simpleactivity.SecondaryActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by admin on 2021/2/24 22:05.
 * Email: zhangman523@126.com
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class SecondaryActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(SecondaryActivity::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.secondary)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_title_backButton() {
        onView(withId(R.id.activity_secondary_title)).check(matches(isDisplayed()))

        onView(withId(R.id.button_back_activity)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isTitleTextDisplayed() {
        onView(withId(R.id.activity_secondary_title))
            .check(matches(withText(R.string.text_secondary_activity)))

    }

}