package tm.com.balary.features.category.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class SubCategoryEntity(
    val category: ParentCategoryEntityItem,
    val children: List<ParentCategoryEntityItem>
)