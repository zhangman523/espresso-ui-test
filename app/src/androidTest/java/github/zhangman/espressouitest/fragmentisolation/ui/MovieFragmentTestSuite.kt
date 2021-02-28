package github.zhangman.espressouitest.fragmentisolation.ui

import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Created by admin on 2021/2/25 15:50.
 * Email: zhangman523@126.com
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
    MovieDetailFragmentTest::class,
    DirectorsFragmentTest::class,
    StarActorsFragmentTest::class,
    MovieNavigationTest::class
)
class MovieFragmentTestSuite