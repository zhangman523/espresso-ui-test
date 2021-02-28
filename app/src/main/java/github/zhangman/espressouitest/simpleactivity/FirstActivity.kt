package github.zhangman.espressouitest.simpleactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import github.zhangman.espressouitest.R
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_first)

    button_next_activity.setOnClickListener {
      val intent = Intent(this, SecondaryActivity::class.java)
      startActivity(intent)
    }
  }
}