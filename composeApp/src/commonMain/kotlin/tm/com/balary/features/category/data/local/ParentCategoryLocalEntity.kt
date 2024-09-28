package tm.com.balary.features.category.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ParentCategoryLocalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val created_at: String?,
    val icon_md: String?,
    val icon_sm: String?,
    val is_active: Boolean,
    val is_onmain: Boolean,
    val parentId: Int?,
    val slide: String?,
    val title_ru: String?,
    val title_tm: String?,
    val updated_at: String?
)