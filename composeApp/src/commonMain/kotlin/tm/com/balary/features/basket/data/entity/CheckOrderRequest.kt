package tm.com.balary.features.basket.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class CheckOrderRequest(
    val products: List<Product>
)