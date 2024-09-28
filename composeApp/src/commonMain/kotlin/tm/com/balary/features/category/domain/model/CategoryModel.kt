package tm.com.balary.features.category.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryModel(
    val children: List<CategoryModel>?,
    val created_at: String?,
    val icon_md: String?,
    val icon_sm: String?,
    val id: Int,
    val is_active: Boolean,
    val is_onmain: Boolean,
    val parentId: Int?,
    val slide: String?,
    val title_ru: String?,
    val title_tm: String?,
    val updated_at: String?
)