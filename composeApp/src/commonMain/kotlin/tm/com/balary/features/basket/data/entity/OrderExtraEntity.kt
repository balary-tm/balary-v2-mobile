package tm.com.balary.features.basket.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class OrderExtraEntity(
    val delivery_price: Double? = null,
    val express_order_price: Double? = null,
    val intervals: Intervals? = null,
    val min_total_price: Double? = null
)