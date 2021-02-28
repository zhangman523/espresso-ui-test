package github.zhangman.espressouitest.fragmentisolation.data

/**
 * Created by admin on 2021/2/25 14:38.
 * Email: zhangman523@126.com
 */
data class Movie(
    val id: Int,
    val title: String,
    val image: String,
    val description: String,
    val directors: ArrayList<String>?,
    val star_actors: ArrayList<String>?
)