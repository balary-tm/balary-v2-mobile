package tm.com.balary.features.product.data.entity.single

import kotlinx.serialization.Serializable
import tm.com.balary.features.home.data.entity.product.ProductEntity

@Serializable
data class SimilarProduct(
    val data: List<ProductEntity>
)
