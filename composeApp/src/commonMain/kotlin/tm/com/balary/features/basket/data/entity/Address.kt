package tm.com.balary.features.basket.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val district: String,
    val floor: Float,
    val house: String,
    val latitude: Double,
    val longitude: Double,
    val room: String,
    val street: String
)