package github.zhangman.espressouitest.intent.camera

import android.app.Activity.RESULT_OK
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import github.zhangman.espressouitest.R
import github.zhangman.espressouitest.intent.camera.ImageViewHasDrawableMatcher.hasDrawable
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by admin on 2021/2/26 15:47.
 * Email: zhangman523@126.com
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class CameraIntentActivityTest {
    @get: Rule
    val intentTestRule = IntentsTestRule(CameraIntentActivity::class.java)

    @Test
    fun test_cameraIntent_isBitmapSetToImageView() {

        //GIVEN
        val activityResult = createImageCaptureActivityResultStub()

        val expectedIntent: Matcher<Intent> = hasAction(MediaStore.ACTION_IMAGE_CAPTURE)
        intending(expectedIntent).respondWith(activityResult)

        // Execute and Verify
        onView(withId(R.id.image)).check(matches(not(hasDrawable())))
        onView(withId(R.id.button_launch_camera)).perform(click())
        intending(expectedIntent)
        
        onView(withId(R.id.image)).check(matches(hasDrawable()))
    }

    private fun createImageCaptureActivityResultStub(): ActivityResult {
        val bundle = Bundle()
        bundle.putParcelable(
            KEY_IMAGE_DATA,
            BitmapFactory.decodeResource(
                intentTestRule.activity.resources,
                R.drawable.ic_launcher_background
            )
        )
        val resultData = Intent()
        resultData.putExtras(bundle)
        return ActivityResult(RESULT_OK, resultData)
    }
}