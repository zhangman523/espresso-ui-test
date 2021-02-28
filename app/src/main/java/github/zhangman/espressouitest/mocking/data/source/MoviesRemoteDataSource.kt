package github.zhangman.espressouitest.mocking.data.source

import github.zhangman.espressouitest.mocking.data.Movie
import github.zhangman.espressouitest.recyclerview.FakeMovieData


/**
 * Created by admin on 2021/2/25 14:45.
 * Email: zhangman523@126.com
 */
class MoviesRemoteDataSource : MoviesDataSource {

    private var MOVIES_REMOTE_DATA = LinkedHashMap<Int, Movie>(FakeMovieData.movies.size)

    init {
        for (movie in FakeMovieData.movies) {
            addMovie(movie)
        }
    }

    override fun getMovie(movieId: Int): Movie? {
        return MOVIES_REMOTE_DATA[movieId]
    }

    override fun getMovies(): List<Movie> {
        return ArrayList(MOVIES_REMOTE_DATA.values)
    }

    private fun addMovie(
        movie: Movie
    ) {
        MOVIES_REMOTE_DATA.put(movie.id, movie)
    }


}


