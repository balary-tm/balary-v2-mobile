package tm.com.balary.features.category.data.entity

data class Category(
    val id: Int,
    val parentId: Int,
    val title_ru: String,
    val title_tm: String
)