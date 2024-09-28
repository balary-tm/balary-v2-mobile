package tm.com.balary.features.category.domain.model

data class ChildCategoryModel(
    val categories: List<CategoryModel>,
    val parent: CategoryModel
)
