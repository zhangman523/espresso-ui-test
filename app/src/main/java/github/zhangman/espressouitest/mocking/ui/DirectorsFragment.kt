package github.zhangman.espressouitest.mocking.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import github.zhangman.espressouitest.R
import kotlinx.android.synthetic.main.fragment_directors.*

/**
 * Created by admin on 2021/2/25 15:06.
 * Email: zhangman523@126.com
 */
class DirectorsFragment : Fragment() {
    private val directors: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            directors.addAll(args.get("args_directors") as List<String>)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_directors, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDirectors()
    }

    private fun setDirectors(){
        directors_text.text = stringBuilderForDirectors(directors)
    }

    companion object{
        fun stringBuilderForDirectors(directors: ArrayList<String>): String{
            val sb = StringBuilder()
            for(director in directors){
                sb.append(director + "\n")
            }
            return sb.toString()
        }
    }
}