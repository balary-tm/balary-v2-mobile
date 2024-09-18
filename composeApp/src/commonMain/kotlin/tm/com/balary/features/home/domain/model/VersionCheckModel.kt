package tm.com.balary.features.home.domain.model


data class VersionCheckModel(
    val description_ru: String,
    val description_tm: String,
    val device: String,
    val id: Int,
    val is_required: Boolean,
    val title_ru: String,
    val title_tm: String,
    val version_number: String
)