package tm.com.balary.features.basket.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class OrderExtraEntity(
    val delivery_price: Double? = null,
    val express_order_price: Double? = null,
    val intervals: Intervals? = null,
    val min_total_price: Double? = null,
    val free_delivery_minimum: Double? = null,
    val office_address_lon: Double? = null,
    val office_address_lat: Double? = null,
    val work_start_time: String? = null,
    val work_end_time: String? = null,
    val office_address_tm: String? = null,
    val office_address_ru: String? = null,
    val express_delivery_desc_ru: String? = null,
    val express_delivery_desc_tm: String? = null,
    val order_description_tm: String? = null,
    val order_description_ru: String? = null,
    val delivery_info_page: DeliveryInfo? =null
)