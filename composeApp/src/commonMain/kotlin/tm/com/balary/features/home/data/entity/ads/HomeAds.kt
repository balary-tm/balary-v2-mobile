package tm.com.balary.features.home.data.entity.ads

import kotlinx.serialization.Serializable
import tm.com.balary.features.home.domain.model.HomeAdsModel

enum class AdsType {
    POPUP,
    SHEET
}

@Serializable
data class HomeAds(
    val ad_type: String?,
    val description_ru: String?,
    val description_tm: String?,
    val destination_id: String?,
    val destination_type: String?,
    val external_url: String?,
    val for_home: Boolean,
    val id: Int,
    val is_active: Boolean,
    val media_path: String?,
    val media_type: String?,
    val thumbnail_path: String?,
    val title_ru: String?,
    val title_tm: String?
) {
    fun getAdsType(): AdsType {
        if (ad_type.isNullOrEmpty())
            return AdsType.POPUP
        if (ad_type == "sheet" && title_tm.isNullOrEmpty().not() && title_ru.isNullOrEmpty().not())
            return AdsType.SHEET
        return AdsType.POPUP
    }

    fun toUIEntity(): HomeAdsModel {
        return HomeAdsModel(
            ad_type = getAdsType(),
            description_ru = description_ru,
            description_tm = description_tm,
            for_home = for_home,
            id = id,
            media_path = media_path,
            is_active = is_active,
            title_ru = title_ru,
            title_tm = title_tm,
            destination_id = destination_id,
            destination_type = destination_type,
            external_url = external_url,
            media_type = media_type,
            thumbnail_path = thumbnail_path
        )
    }
}