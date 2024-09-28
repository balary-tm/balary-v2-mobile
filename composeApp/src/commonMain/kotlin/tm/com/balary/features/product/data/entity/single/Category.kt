package tm.com.balary.features.product.data.entity.single

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int,
    val title: Title
)