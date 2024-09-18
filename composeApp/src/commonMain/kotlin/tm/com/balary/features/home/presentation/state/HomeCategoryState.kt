package tm.com.balary.features.home.presentation.state

import tm.com.balary.features.home.domain.model.HomeCategory

data class HomeCategoryState(
    val loading: Boolean = true,
    val error: String? = null,
    val category: List<HomeCategory>? = null
)
