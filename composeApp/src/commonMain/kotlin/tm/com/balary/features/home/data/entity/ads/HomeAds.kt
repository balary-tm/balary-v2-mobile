package tm.com.balary.features.home.data.entity.ads

import kotlinx.serialization.Serializable

enum class AdsType {
    POPUP,
    SHEET
}

@Serializable
data class HomeAds(
    val ad_type: String?,
    val category_id: Int?,
    val description_ru: String?,
    val description_tm: String?,
    val destination_url: String?,
    val for_home: Boolean,
    val id: Int,
    val img_path: String?,
    val is_active: Boolean,
    val title_ru: String?,
    val title_tm: String?
) {
    fun getAdsType(): AdsType {
        if(ad_type.isNullOrEmpty())
            return AdsType.POPUP
        if(ad_type == "sheet" && title_tm.isNullOrEmpty().not() && title_ru.isNullOrEmpty().not())
            return AdsType.SHEET
        return AdsType.POPUP
    }
}