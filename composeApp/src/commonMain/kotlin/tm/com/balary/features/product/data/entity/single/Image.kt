package tm.com.balary.features.product.data.entity.single

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val lg: String,
    val md: String,
    val sm: String
)