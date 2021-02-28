package github.zhangman.espressouitest.util

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.idling.CountingIdlingResource
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.lang.Exception

/**
 * Option 1
 */
//class EspressoIdlingResourceRule : TestRule {
//    override fun apply(base: Statement?, description: Description?): Statement {
//        return IdlingResourceStatement(base)
//    }
//
//    class IdlingResourceStatement(private val base: Statement?) : Statement() {
//        private val idlingResource: CountingIdlingResource =
//            EspressoIdlingResource.countingIdlingResource
//
//        override fun evaluate() {
//            //Before
//            IdlingRegistry.getInstance().register(idlingResource)
//            try {
//                base?.evaluate()
//                    ?: throw Exception("Error evaluation test. Base statement is null.")
//            } finally {
//                //After
//                IdlingRegistry.getInstance().unregister(idlingResource)
//            }
//        }
//
//    }
//}
/**
 * Option 2
 * Simplified version of option#1. (TestWatcher class implements Test Rule)
 */
class EspressoIdlingResourceRule : TestWatcher() {
    private val idlingResource: CountingIdlingResource =
        EspressoIdlingResource.countingIdlingResource
    //Before
    override fun starting(description: Description?) {
        IdlingRegistry.getInstance().register(idlingResource)
        super.starting(description)
    }
    //After
    override fun finished(description: Description?) {
        IdlingRegistry.getInstance().unregister(idlingResource)
        super.finished(description)
    }
}