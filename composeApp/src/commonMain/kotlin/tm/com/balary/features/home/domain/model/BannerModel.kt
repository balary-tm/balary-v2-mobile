package tm.com.balary.features.home.domain.model

data class BannerModel(
    val external_url: String?,
    val id: Int,
    val media_path: String?,
    val media_type: String?,
    val destination_type: String?,
    val destination_id: String?,
    val thumbnail_path: String?,
)
