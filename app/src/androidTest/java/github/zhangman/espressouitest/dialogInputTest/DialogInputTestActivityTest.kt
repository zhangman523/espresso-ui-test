package github.zhangman.espressouitest.dialogInputTest

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import github.zhangman.espressouitest.R
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by admin on 2021/2/26 23:30.
 * Email: zhangman523@126.com
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class DialogInputTestActivityTest {
    @Test
    fun test_showDialog_captureNameInput() {

        //GIVEN
        val activityScenario = ActivityScenario.launch(DialogInputTestActivity::class.java)
        val EXPECTED_NAME = "Mitch"

        //Execute and Verify
        onView(withId(R.id.button_launch_dialog)).perform(click())

        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed()))

        onView(withText(R.string.text_ok)).perform(click())

        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed()))

        //enter some input
        onView(withId(R.id.md_input_message)).perform(typeText(EXPECTED_NAME))

        onView(withText(R.string.text_ok)).perform(click())
        // make sure dialog is gone
        onView(withText(R.string.text_enter_name)).check(doesNotExist())

        // confirm name is set to TextView in activity
        onView(withId(R.id.text_name)).check(matches(withText(EXPECTED_NAME)))
    }
}