package tm.com.balary.features.basket.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class OrderLine(
    val quantity: Int,
    val variant_id: Int
)