package tm.com.balary.features.category.data.entity

import kotlinx.serialization.Serializable
import tm.com.balary.core.Constant
import tm.com.balary.features.category.data.local.ParentCategoryLocalEntity
import tm.com.balary.features.category.domain.model.CategoryModel

@Serializable
data class ParentCategoryEntityItem(
    val children: List<ParentCategoryEntityItem>? = emptyList(),
    val created_at: String? = null,
    val icon_md: String? = null,
    val icon_sm: String? = null,
    val id: Int,
    val is_active: Boolean = false,
    val is_onmain: Boolean = false,
    val parentId: Int? = null,
    val slide: String? = null,
    val title_ru: String? = null,
    val title_tm: String? = null,
    val updated_at: String? = null
) {
    fun toLocalEntity(pId: Int?): ParentCategoryLocalEntity {
        return ParentCategoryLocalEntity(
            created_at = created_at,
            icon_md = icon_md,
            icon_sm = icon_sm,
            id = id,
            is_active = is_active,
            is_onmain = is_onmain,
            parentId = pId,
            slide = slide,
            title_ru = title_ru,
            title_tm = title_tm,
            updated_at = updated_at,
        )
    }

    fun toUIEntity(): CategoryModel {
        return CategoryModel(
            children = children?.map { it.toUIEntity() },
            created_at = created_at,
            icon_md = "${Constant.BASE_URL}/${icon_md?.replace(Constant.BASE_URL,"")}",
            icon_sm = "${Constant.BASE_URL}/${icon_sm?.replace(Constant.BASE_URL,"")}",
            id = id,
            is_active = is_active,
            is_onmain = is_onmain,
            parentId = parentId,
            slide = slide,
            title_ru = title_ru,
            title_tm = title_tm,
            updated_at = updated_at,
        )
    }
}