package tm.com.balary.features.home.data.entity

import kotlinx.serialization.Serializable
import tm.com.balary.features.home.domain.model.BannerModel

@Serializable
data class BannerEntity(
    val destination_url: String,
    val id: Int,
    val image_url: String,
    val media_type: String
) {
    fun toUIEntity(): BannerModel {
        return BannerModel(
            destination = destination_url,
            id = id,
            image = image_url,
            media_type = media_type
        )
    }
}