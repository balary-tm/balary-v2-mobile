package tm.com.balary.features.favorite.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    val limit: Int,
    val page: Int,
    val total: Int
)