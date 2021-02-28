package github.zhangman.espressouitest.intent.camera

import android.view.View
import android.widget.ImageView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

/**
 * Created by admin on 2021/2/26 16:41.
 * Email: zhangman523@126.com
 */
object ImageViewHasDrawableMatcher {

    fun hasDrawable(): BoundedMatcher<View, ImageView> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("has drawable")
            }

            override fun matchesSafely(item: ImageView?): Boolean {
                return item?.drawable != null
            }

        }
    }
}