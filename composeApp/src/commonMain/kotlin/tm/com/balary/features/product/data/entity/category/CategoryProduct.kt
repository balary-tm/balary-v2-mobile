package tm.com.balary.features.product.data.entity.category

import kotlinx.serialization.Serializable
import tm.com.balary.features.home.data.entity.product.ProductEntity

@Serializable
data class CategoryProduct(
    val `data`: List<ProductEntity>,
    val pagination: Pagination
)