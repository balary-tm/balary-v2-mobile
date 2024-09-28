package tm.com.balary.features.category.presentation.state

import tm.com.balary.features.category.domain.model.ChildCategoryModel

data class ChildCategoryState(
    val loading: Boolean = true,
    val error: String? = null,
    val category: ChildCategoryModel? = null
)
