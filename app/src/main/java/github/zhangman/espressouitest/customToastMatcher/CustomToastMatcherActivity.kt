package github.zhangman.espressouitest.customToastMatcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import github.zhangman.espressouitest.R
import kotlinx.android.synthetic.main.activity_dialog_input_test.*

class CustomToastMatcherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_toast_matcher)

        button_launch_dialog.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        MaterialDialog(this)
            .show {
                input(
                    waitForPositiveButton = true,
                    allowEmpty = false
                ) { dialog, name ->
                    setNameToTextView(name.toString())
                    showToast(buildToastMessage(name.toString()))
                }
                title(R.string.text_enter_name)
                positiveButton(R.string.text_ok)
            }
    }

    private fun setNameToTextView(name: String) {
        text_name.text = name
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun buildToastMessage(name: String): String {
            return "You name is $name"
        }
    }
}