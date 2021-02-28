package github.zhangman.espressouitest.dialogInputTest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import github.zhangman.espressouitest.R
import kotlinx.android.synthetic.main.activity_dialog_input_test.*

class DialogInputTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_input_test)

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
                }
                title(R.string.text_enter_name)
                positiveButton(R.string.text_ok)
            }
    }

    private fun setNameToTextView(name: String) {
        text_name.text = name
    }
}