package tm.com.balary.features.home.data.entity.version

import kotlinx.serialization.Serializable
import tm.com.balary.features.home.domain.model.VersionCheckModel

@Serializable
data class VersionCheckItem(
    val description_ru: String?,
    val description_tm: String?,
    val device: String?,
    val id: Int,
    val is_required: Boolean,
    val title_ru: String?,
    val title_tm: String?,
    val version_number: String?
) {
    fun toUIEntity(): VersionCheckModel {
        return VersionCheckModel(
            description_ru = description_ru ?: "",
            description_tm = description_tm ?: "",
            device = device ?: "",
            id = id,
            is_required = is_required,
            title_ru = title_ru ?: "",
            title_tm = title_tm ?: "",
            version_number = version_number ?: "",
        )
    }
}