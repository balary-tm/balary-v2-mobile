package tm.com.balary.features.product.data.entity.single

import kotlinx.serialization.Serializable

@Serializable
data class ProductMedia(
    val media_path: String,
    val thumbnail: String,
    val type: String
)