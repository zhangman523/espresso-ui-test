package github.zhangman.espressouitest.idlingResource

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingwithmitch.espressouitestexamples.util.TopSpacingItemDecoration
import github.zhangman.espressouitest.R
import github.zhangman.espressouitest.mocking.data.Movie
import github.zhangman.espressouitest.mocking.data.source.MoviesDataSource
import github.zhangman.espressouitest.mocking.ui.MovieDetailFragment
import github.zhangman.espressouitest.recyclerview.FakeMovieData
import github.zhangman.espressouitest.recyclerview.MoviesListAdapter
import github.zhangman.espressouitest.util.EspressoIdlingResource
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.ClassCastException

/**
 * Created by admin on 2021/2/27 17:39.
 * Email: zhangman523@126.com
 */
class MovieListFragment(
    val moviesDataSource: MoviesDataSource
) : Fragment(),
    MoviesListAdapter.Interaction {

    private val TAG: String = "MovieListFragment"

    override fun onItemSelected(position: Int, item: Movie) {
        activity?.run {
            val bundle = Bundle()
            bundle.putInt("movie_id", item.id)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieDetailFragment::class.java, bundle)
                .addToBackStack("MovieDetailFragment")
                .commit()
        }
    }

    lateinit var listAdapter: MoviesListAdapter
    lateinit var uiCommunicationListener: UICommunicationListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            uiCommunicationListener = context as UICommunicationListener
        } catch (e: ClassCastException) {
            Log.e(TAG, "Must implement interface in $activity: ${e.message}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        getData()
    }

    private fun getData() {
        EspressoIdlingResource.increment()
        uiCommunicationListener.loading(true)
        val job = GlobalScope.launch(IO) {
            delay(FakeMovieData.FAKE_NETWORK_DELAY)
        }
        job.invokeOnCompletion {
            GlobalScope.launch(Main) {
                uiCommunicationListener.loading(false)
                listAdapter.submitList(moviesDataSource.getMovies())
                EspressoIdlingResource.decrement()
            }
        }
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            removeItemDecoration(TopSpacingItemDecoration(30))
            addItemDecoration(TopSpacingItemDecoration(30))
            listAdapter = MoviesListAdapter(this@MovieListFragment)
            adapter = listAdapter
        }
    }


}