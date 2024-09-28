package tm.com.balary.features.home.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import tm.com.balary.features.home.domain.model.BannerModel

@Entity
data class BannerLocalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val external_url: String?,
    val media_path: String?,
    val media_type: String?,
    val destination_type: String?,
    val destination_id: String?,
    val thumbnail_path: String?,
) {
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
