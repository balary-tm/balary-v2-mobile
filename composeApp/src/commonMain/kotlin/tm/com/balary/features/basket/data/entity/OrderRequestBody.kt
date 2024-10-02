package tm.com.balary.features.basket.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class OrderRequestBody(
    val address: Address,
    val delivery_day: String,
    val delivery_interval_id: Int,
    val delivery_type: String,
    val note: String,
    val order_lines: List<OrderLine>,
    val payment_type: String,
    val phone: String
)