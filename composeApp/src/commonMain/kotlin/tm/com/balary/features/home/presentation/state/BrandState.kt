package tm.com.balary.features.home.presentation.state

import tm.com.balary.features.home.domain.model.HomeBrandModel

data class BrandState(
    val loading: Boolean = true,
    val error: String? = null,
    val brand: List<HomeBrandModel>? = null
)
