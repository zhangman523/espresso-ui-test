package github.zhangman.espressouitest.intent.gallery

import android.app.Activity.RESULT_OK
import android.app.Instrumentation.*
import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import github.zhangman.espressouitest.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by admin on 2021/2/25 17:12.
 * Email: zhangman523@126.com
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class GalleryIntentActivityTest {

    @get:Rule
    val intentsTestRule = IntentsTestRule(GalleryIntentActivity::class.java)

    @Test
    fun test_validateGalleryIntent() {

        //GIVEN
        val expectedIntent: Matcher<Intent> = allOf(
            hasAction(Intent.ACTION_PICK),
            hasData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        )

        val activityResult = createGalleryPickActivityResultStub()

        intending(expectedIntent).respondWith(activityResult)

        //Execute and Verify
        onView(withId(R.id.button_open_gallery)).perform(click())
        intended(expectedIntent)
    }

    private fun createGalleryPickActivityResultStub(): ActivityResult {
        val resources: Resources = InstrumentationRegistry.getInstrumentation().targetContext.resources
        val imageUri =
            Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                        resources.getResourcePackageName(R.mipmap.ic_launcher) + "/" +
                        resources.getResourceTypeName(R.mipmap.ic_launcher) + "/" +
                        resources.getResourceEntryName(R.mipmap.ic_launcher)
            )
        val resultIntent = Intent()
        resultIntent.data = imageUri
        return ActivityResult(RESULT_OK, resultIntent)
    }
}