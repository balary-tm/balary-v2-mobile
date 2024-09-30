package tm.com.balary.features.favorite.data.entity

import kotlinx.serialization.Serializable
import tm.com.balary.features.home.data.entity.product.ProductEntity

@Serializable
data class FavoriteProductResponse(
    val `data`: List<ProductEntity>,
    val pagination: Pagination
)