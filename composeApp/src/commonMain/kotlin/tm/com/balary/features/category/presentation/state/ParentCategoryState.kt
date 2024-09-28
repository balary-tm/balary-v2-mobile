package tm.com.balary.features.category.presentation.state

import tm.com.balary.features.category.domain.model.CategoryModel

data class ParentCategoryState(
    val loading: Boolean = true,
    val error: String? = null,
    val categories: List<CategoryModel>? = null
)
