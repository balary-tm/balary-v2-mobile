package tm.com.balary.features.basket.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class CheckOrderResponse(
    val cart_discount: Double,
    val cart_discount_price: Double,
    val cart_price: Double,
    val cart_quantity: Int,
    val new_discount: Double,
    val new_discount_price: Double,
    val new_price: Double,
    val new_quantity: Int,
    val out_of_stock: Boolean,
    val product_id: Int
)