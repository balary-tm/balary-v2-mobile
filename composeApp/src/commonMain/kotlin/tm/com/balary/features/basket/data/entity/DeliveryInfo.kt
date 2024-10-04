package tm.com.balary.features.basket.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class DeliveryInfo(
    val content_ru: String,
    val content_tm: String,
    val id: Int,
    val page_code: String,
    val title_ru: String,
    val title_tm: String
)