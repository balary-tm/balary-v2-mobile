package tm.com.balary.features.home.data.entity

import kotlinx.serialization.Serializable
import tm.com.balary.features.home.data.datasource.local.BannerLocalEntity
import tm.com.balary.features.home.domain.model.BannerModel

@Serializable
data class BannerEntity(
    val external_url: String?,
    val id: Int,
    val media_path: String?,
    val media_type: String?,
    val destination_type: String?,
    val destination_id: String?,
    val thumbnail_path: String?,
) {
    fun toCacheEntity(): BannerLocalEntity {
        return BannerLocalEntity(
            id,
            external_url,
            media_path,
            media_type,
            destination_type,
            destination_id,
            thumbnail_path,
        )
    }
    fun toUIEntity(): BannerModel {
        return BannerModel(
            external_url,
            id,
            media_path,
            media_type,
            destination_type,
            destination_id,
            thumbnail_path,
        )
    }
}