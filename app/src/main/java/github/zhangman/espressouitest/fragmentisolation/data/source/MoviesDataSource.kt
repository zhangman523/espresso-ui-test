package github.zhangman.espressouitest.fragmentisolation.data.source

import github.zhangman.espressouitest.fragmentisolation.data.Movie

/**
 * Created by admin on 2021/2/25 14:44.
 * Email: zhangman523@126.com
 */
interface MoviesDataSource {
    fun getMovie(movieId: Int): Movie?
}