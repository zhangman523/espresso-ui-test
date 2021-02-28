package github.zhangman.espressouitest.fragmentisolation.data.source

import github.zhangman.espressouitest.fragmentisolation.data.DummyMovies.INFINITY_WAR
import github.zhangman.espressouitest.fragmentisolation.data.DummyMovies.THE_RUNDOWN
import github.zhangman.espressouitest.fragmentisolation.data.Movie

/**
 * Created by admin on 2021/2/25 14:45.
 * Email: zhangman523@126.com
 */
object MoviesRemoteDataSource : MoviesDataSource {
    private var MOVIES_REMOTE_DATA = LinkedHashMap<Int, Movie>(2)

    init {
        addMovie(INFINITY_WAR)
        addMovie(THE_RUNDOWN)
    }

    override fun getMovie(movieId: Int): Movie? {
        return MOVIES_REMOTE_DATA[movieId]
    }

    private fun addMovie(movie: Movie) {
        MOVIES_REMOTE_DATA.put(movie.id, movie)
    }
}