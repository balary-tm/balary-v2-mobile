package tm.com.balary.features.home.domain.model

import kotlinx.serialization.Serializable
import tm.com.balary.features.home.data.entity.ads.AdsType

@Serializable
data class HomeAdsModel(
    val ad_type: AdsType,
    val description_ru: String?,
    val description_tm: String?,
    val for_home: Boolean,
    val id: Int,
    val media_path: String?,
    val is_active: Boolean,
    val title_ru: String?,
    val title_tm: String?,
    val destination_id: String?,
    val destination_type: String?,
    val external_url: String?,
    val media_type: String?,
    val thumbnail_path: String?,
)
