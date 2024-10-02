package tm.com.balary.features.basket.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class OrderDay(
    val end_time: String? = null,
    val id: Int,
    val start_time: String? = null
)