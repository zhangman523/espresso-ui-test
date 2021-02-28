package github.zhangman.espressouitest.customToastMatcher

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import github.zhangman.espressouitest.R
import github.zhangman.espressouitest.customToastMatcher.CustomToastMatcherActivity.Companion.buildToastMessage
import github.zhangman.espressouitest.dialogInputTest.DialogInputTestActivity
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by admin on 2021/2/26 23:57.
 * Email: zhangman523@126.com
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class CustomToastMatcherActivityTest {
    @Test
    fun test_showDialog_captureNameInput() {

        //GIVEN
        val activityScenario = ActivityScenario.launch(CustomToastMatcherActivity::class.java)
        val EXPECTED_NAME = "Mitch"

        //Execute and Verify
        onView(ViewMatchers.withId(R.id.button_launch_dialog)).perform(ViewActions.click())

        onView(withText(R.string.text_enter_name))
            .check(matches(isDisplayed()))

        onView(withText(R.string.text_ok)).perform(ViewActions.click())

        onView(withText(R.string.text_enter_name))
            .check(matches(isDisplayed()))

        //enter some input
        onView(ViewMatchers.withId(R.id.md_input_message))
            .perform(ViewActions.typeText(EXPECTED_NAME))

        onView(withText(R.string.text_ok)).perform(ViewActions.click())
        // make sure dialog is gone
        onView(withText(R.string.text_enter_name))
            .check(doesNotExist())

        // confirm name is set to TextView in activity
        onView(ViewMatchers.withId(R.id.text_name))
            .check(matches(withText(EXPECTED_NAME)))

        //test if toast is displayed
        onView(withText(buildToastMessage(EXPECTED_NAME)))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }
}