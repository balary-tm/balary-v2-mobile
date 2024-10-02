package tm.com.balary.features.basket.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Intervals(
    val today: List<OrderDay>? = null,
    val tomorrow: List<OrderDay>? = null
)