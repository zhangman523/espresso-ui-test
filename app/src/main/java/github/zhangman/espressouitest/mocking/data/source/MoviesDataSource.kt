package github.zhangman.espressouitest.mocking.data.source

import github.zhangman.espressouitest.mocking.data.Movie

/**
 * Created by admin on 2021/2/25 14:44.
 * Email: zhangman523@126.com
 */
interface MoviesDataSource {
    fun getMovie(movieId: Int): Movie?

    fun getMovies(): List<Movie>
}