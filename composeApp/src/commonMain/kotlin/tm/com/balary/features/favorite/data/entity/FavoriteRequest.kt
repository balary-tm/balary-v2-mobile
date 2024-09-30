package tm.com.balary.features.favorite.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class FavoriteRequest(
    val page: Int = 1,
    val limit: Int = 20
)
