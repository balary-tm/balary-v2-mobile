package tm.com.balary.features.home.presentation.state

import tm.com.balary.features.home.domain.model.HomeProductModel

data class HomeProductState(
    val loading: Boolean = true,
    val error: String? = null,
    val productModel: HomeProductModel? = null
)
