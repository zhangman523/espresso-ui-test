package github.zhangman.espressouitest.simlpeactivity

import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Created by admin on 2021/2/24 22:25.
 * Email: zhangman523@126.com
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
    FirstActivityTest::class,
    SecondaryActivityTest::class
)
class ActivityTestSuite