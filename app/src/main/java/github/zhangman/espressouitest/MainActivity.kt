package github.zhangman.espressouitest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import github.zhangman.espressouitest.customToastMatcher.CustomToastMatcherActivity
import github.zhangman.espressouitest.dialogInputTest.DialogInputTestActivity
import github.zhangman.espressouitest.fragmentisolation.FragmentIsolationActivity
import github.zhangman.espressouitest.idlingResource.IdlingResourceActivity
import github.zhangman.espressouitest.intent.camera.CameraIntentActivity
import github.zhangman.espressouitest.intent.gallery.GalleryIntentActivity
import github.zhangman.espressouitest.mocking.MockingActivity
import github.zhangman.espressouitest.recyclerview.RecyclerViewTestActivity
import github.zhangman.espressouitest.simpleactivity.FirstActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by admin on 2021/2/25 10:37.
 * Email: zhangman523@126.com
 */
class MainActivity : AppCompatActivity() {

    val dataSet: Array<DemosAdapter.Demo> = arrayOf(
        DemosAdapter.Demo("Simple Activity", "test simple activity ", FirstActivity::class.java),
        DemosAdapter.Demo(
            "Fragment Isolation",
            "test fragment isolation ",
            FragmentIsolationActivity::class.java
        ),
        DemosAdapter.Demo(
            "Gallery Intent",
            "test gallery intent",
            GalleryIntentActivity::class.java
        ),
        DemosAdapter.Demo(
            "Camera Intent",
            "test Camera intent",
            CameraIntentActivity::class.java
        ),
        DemosAdapter.Demo(
            "Dialog Input",
            "Dialog Input Testing",
            DialogInputTestActivity::class.java
        ),
        DemosAdapter.Demo(
            "Custom Toast Matcher",
            "Custom Toast Matcher Testing",
            CustomToastMatcherActivity::class.java
        ),
        DemosAdapter.Demo(
            "Mockk with Espresso",
            "Mockk with Espresso Introduction",
            MockingActivity::class.java
        ),
        DemosAdapter.Demo(
            "RecyclerView UI Testing",
            "RecyclerView UI Testing",
            RecyclerViewTestActivity::class.java
        ),
        DemosAdapter.Demo(
            "Espresso Idling Resource",
            "Espresso Idling Resource",
            IdlingResourceActivity::class.java
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayoutManager = LinearLayoutManager(this)
        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = DemosAdapter(dataSet)
        }

    }

    fun start(activity: Class<*>, layoutFileId: Int) {
        val intent = Intent(this, activity).apply {
            putExtra("layout_file_id", layoutFileId)
        }
        startActivity(intent)
    }
}