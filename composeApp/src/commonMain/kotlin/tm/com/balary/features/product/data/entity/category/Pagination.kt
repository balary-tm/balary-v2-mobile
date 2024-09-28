package tm.com.balary.features.product.data.entity.category

import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    val limit: Int,
    val page: Int,
    val total: Int
)