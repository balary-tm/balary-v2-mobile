package tm.com.balary.features.home.presentation.state

import tm.com.balary.features.home.domain.model.BannerModel

data class BannerState(
    val loading: Boolean = true,
    val error: String? = null,
    val banners: List<BannerModel>? = null
)
