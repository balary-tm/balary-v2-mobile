package tm.com.balary.features.product.data.entity.single

import kotlinx.serialization.Serializable

@Serializable
data class Brand(
    val id: Int,
    val name: String
)