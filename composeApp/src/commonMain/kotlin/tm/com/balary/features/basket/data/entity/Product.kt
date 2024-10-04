package tm.com.balary.features.basket.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val cart_discount: Double,
    val cart_discount_price: Double,
    val cart_price: Double,
    val product_id: Int,
    val quantity: Int
)